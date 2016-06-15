package net.chriswaltrip.java.mp3player;

import it.pievis.utils.BackgroundExecutor;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javazoom.jlgui.basicplayer.BasicPlayerException;
import javazoom.jlgui.basicplayer.BasicController;
import javazoom.jlgui.basicplayer.BasicPlayerEvent;
import javazoom.jlgui.basicplayer.BasicPlayerListener;
import it.pievis.utils.Utils;
import javax.swing.SwingConstants;

public class MainView extends JFrame {
    
    AudioPlayer player = AudioPlayer.getPlayer();
    
    JPanel container = new JPanel();
    JMenuBar topMenu = new JMenuBar();
    JFileChooser chooser = new JFileChooser();
    DefaultListModel<String> playList = new DefaultListModel();
    JList<String> jPlayList = new JList<>(playList);
    JScrollPane listScroller = new JScrollPane(jPlayList);
    SeekBar seekBar = new SeekBar();
    JLabel startTimeLabel = new JLabel();
    JLabel endTimeLabel = new JLabel();
    JLabel playingLabel = new JLabel();
    JButton playButton = new JButton();
    JButton addButton = new JButton();
    JButton nextButton = new JButton();
    JButton prevButton = new JButton();
    JButton deleteButton = new JButton();
    ImageIcon nextIcon = new ImageIcon(getClass().getResource("/icons/player_fwd.png"));
    ImageIcon prevIcon = new ImageIcon(getClass().getResource("/icons/player_rew.png"));
    ImageIcon playIcon = new ImageIcon(getClass().getResource("/icons/player_play.png"));
    ImageIcon pauseIcon = new ImageIcon(getClass().getResource("/icons/player_pause.png"));
    ImageIcon addIcon = new ImageIcon(getClass().getResource("/icons/audio.png"));
    ImageIcon deleteIcon = new ImageIcon(getClass().getResource("/icons/delete.png"));    
    
    ScheduledExecutorService timersExec = Executors.newSingleThreadScheduledExecutor();	
    ScheduledExecutorService titleExec = Executors.newSingleThreadScheduledExecutor();
    float currentDuration = 0; 

    int dispIndex = 0;
    boolean goBack = false;
    final static int maxPlayingLabelChars = 36;
    
    // Public constructor
    public MainView() {
        initView();
        initBehavior();
    }
    
    // Initialize the GUI
    private void initView() {
        
        // Set up the JFrame
        setTitle("MP3 Music Player");
        setSize(480, 360);
	setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        // Set up the container, set layout
        container.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        getContentPane().add(container);
        
        // Seek Bar
	c.fill = GridBagConstraints.BOTH;
	c.weightx = 0.5;
	c.gridwidth = 3;
	c.gridx = 0;
	c.gridy = 0;
        c.ipady = 20;
	container.add(seekBar, c);

        // Start Time label
        startTimeLabel.setText("00:00");
	c.fill = GridBagConstraints.HORIZONTAL;
	c.weightx = 0.5;
	c.gridwidth = 1;
	c.gridx = 0;
	c.gridy = 1;
        c.ipady = 0;
	container.add(startTimeLabel, c);
        
        // End Time label
        endTimeLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        endTimeLabel.setText("00:00");
	c.fill = GridBagConstraints.HORIZONTAL;
	c.weightx = 0.5;
	c.gridwidth = 1;
	c.gridx = 2;
	c.gridy = 1;
	container.add(endTimeLabel, c);
                
        // Now Playing marquee
        playingLabel.setOpaque(true);
        playingLabel.setBackground(Color.BLACK);
        playingLabel.setForeground(Color.WHITE);
        playingLabel.setText(" Now Playing: ");
        playingLabel.setBounds(5, 0, 100, 40);
	c.fill = GridBagConstraints.HORIZONTAL;
	c.weightx = 0.5;
	c.gridwidth = 3;
	c.gridx = 0;
	c.gridy = 2;
        c.ipady = 10;
	container.add(playingLabel, c);        

        // Previous Track button
        prevButton.setText("Prev");
        prevButton.setIcon(prevIcon);
	c.fill = GridBagConstraints.HORIZONTAL;
	c.weightx = 0.5;
	c.gridwidth = 1;
	c.gridx = 0;
	c.gridy = 3;
        c.ipady = 0;
	container.add(prevButton, c);

        // Play Track button
        playButton.setText("Play");
        playButton.setIcon(playIcon);
        playButton.setMnemonic(KeyEvent.VK_SPACE);
	c.fill = GridBagConstraints.HORIZONTAL;
	c.weightx = 0.5;
	c.gridwidth = 1;
	c.gridx = 1;
	c.gridy = 3;
	container.add(playButton, c);

        // Next Track button
        nextButton.setText("Next");
        nextButton.setIcon(nextIcon);
	c.fill = GridBagConstraints.HORIZONTAL;
	c.weightx = 0.5;
	c.gridwidth = 1;
	c.gridx = 2;
	c.gridy = 3;
	container.add(nextButton, c);
        
        // Scrolling playlist
        c.fill = GridBagConstraints.BOTH;
	c.weightx = 1.0;
        c.weighty = 1.0; 
	c.gridwidth = 3;
	c.gridx = 0;
	c.gridy = 4;
	container.add(listScroller, c);
        
        // Add Song button
        addButton.setText("Add");
        addButton.setIcon(addIcon);
	c.fill = GridBagConstraints.HORIZONTAL;
	c.weightx = 0.5;
	c.gridwidth = 1;
	c.gridx = 0;
	c.gridy = 5;
	container.add(addButton, c);
        
        // Delete Song button
        deleteButton.setText("Del");
        deleteButton.setIcon(deleteIcon);
	c.fill = GridBagConstraints.HORIZONTAL;
//        c.anchor = GridBagConstraints.PAGE_END;
	c.weightx = 0.5;
	c.gridwidth = 1;
	c.gridx = 2;
	c.gridy = 5;
	container.add(deleteButton, c);

    }
    
    private void initBehavior() {
        chooser.setMultiSelectionEnabled(true);
        chooser.setFileFilter(new AudioFileFilter());
        
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int returnVal = chooser.showOpenDialog(addButton);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    File[] files = chooser.getSelectedFiles();
                    for (File f : files) {
                        player.addSong(f.getAbsolutePath());
                        playList.addElement(f.getName());
                        System.out.println("Added file " + f.getName() + " to playlist");
                    }
                } else {
                    System.out.println("No file selected");
                }
            }

        });
        
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Executed Outside UI Thread
                BackgroundExecutor.get().execute(new Runnable() {

                    @Override
                    public void run() {
                        int[] indexes = jPlayList.getSelectedIndices();
                        int removed = 0;
                        for (int i : indexes) {
                            System.out.println("Removed Song (" + (i - removed) + ")" + playList.get(i - removed));
                            player.removeSong(i - removed);
                            playList.remove(i - removed);
                            removed++;
                        }
                    }
                });
            }

        });
        
        prevButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                try {
                    player.prevSong();
//                    seekbar.resetLastSeek();
                } catch (BasicPlayerException ex) {
                    System.out.println("Error calling the previous song");
                    ex.printStackTrace();
                }
            }

        });
        
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                try {
                    togglePlay();
                } catch (BasicPlayerException ex) {
                    ex.printStackTrace();
                }
            }

        });
        
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                try {
                    player.nextSong();
//                    seekbar.resetLastSeek();
                } catch (BasicPlayerException ex) {
                    System.out.println("Error calling the next song");
                    ex.printStackTrace();
                }

            }

        });
        
        jPlayList.setModel(playList);
        jPlayList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        jPlayList.setLayoutOrientation(JList.VERTICAL);
        
        jPlayList.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                JList list = (JList) evt.getSource();
                if (evt.getClickCount() == 2) {
                    System.out.println("Double click detected, moving to selected item.");
                    int index = list.locationToIndex(evt.getPoint());
                    player.setIndex(index);
                    try {
                        player.play();
                    } catch (BasicPlayerException ev) {
                        ev.printStackTrace();
                    }
                }
            }
        });
        
//        player.addBasicPlayerListener(new DefaultPlayerListener(player));
//        player.addBasicPlayerListener(new Mp3PlayerListener(player));

        player.addBasicPlayerListener(new BasicPlayerListener() {
            @Override
            public void stateUpdated(BasicPlayerEvent event) {
                if (event.getCode() == BasicPlayerEvent.EOM) {
                    try {
                        player.nextSong();
                    } catch (BasicPlayerException e) {
                        e.printStackTrace();
                    }
                    System.out.println("EOM event catched, calling next song.");
                }
                if (event.getCode() == BasicPlayerEvent.PAUSED) {
                    playButton.setText("Play");
                    playButton.setIcon(playIcon);
                }
                if (event.getCode() == BasicPlayerEvent.RESUMED) {
                    playButton.setText("Pause");
                    playButton.setIcon(pauseIcon);
                }
            }

            @Override
            public void setController(BasicController arg0) { }

            @Override
            public void progress(int bytesread, long microseconds, byte[] pcmdata, Map properties) {
                // Don't use microseconds directly because it resets on seeking
                seekBar.updateSeekBar(player.getProgressMicroseconds(), currentDuration);
            }

            @Override
            public void opened(Object arg0, Map arg1) {
                playButton.setText("Pause");
                playButton.setIcon(pauseIcon);
                jPlayList.setSelectedIndex(player.getIndex());
                playingLabel.setText(" Now Playing: " + playList.get(player.getIndex()));
                currentDuration = player.getDurationInSeconds();
            }
        });
        
        // Update time counters once per second
        timersExec.scheduleAtFixedRate(new Runnable() {

            @Override
            public void run() {
                updateTimers();
            }
        }, 0, 1, TimeUnit.SECONDS);

        // Update now-playing text once per second
        titleExec.scheduleAtFixedRate(new Runnable() {

            @Override
            public void run() {
                updatePlayingText();
            }
        }, 0, 1, TimeUnit.SECONDS);

    }
    
    // Toggle between play and pause on button press
    private void togglePlay() throws BasicPlayerException {
        if (playList.isEmpty()) {
            return;
        }
        if (!player.isPaused()) {
            player.pause();
            playButton.setText("Play");
            playButton.setIcon(playIcon);
        } else {
            player.play();
        }
    }
    
    // Update the start/end time timers
    private void updateTimers() {
        if (!player.isPaused()) {
            long lms = player.getProgressMicroseconds();
            String timer0 = Utils.getMinutesRapp(player.getProgressMicroseconds());
            String timer1 = Utils.getMinutesRapp((long) (currentDuration * 1000000) 
                    - player.getProgressMicroseconds());
            startTimeLabel.setText(timer0);
            endTimeLabel.setText(timer1);
        }
    }

    // Update the now-playing text
    private void updatePlayingText() {
        if (player.isPaused()) {
            return;
        }
        if (playList == null || (playList.size() == 0)) {
            return;
        }
        String currentSong = playList.get(player.getIndex());
        if (currentSong.length() > maxPlayingLabelChars) {
            if ((maxPlayingLabelChars + dispIndex) >= currentSong.length()) {
                goBack = true;
            }
            if (dispIndex == 0) {
                goBack = false;
            }
            String cutStr = currentSong.substring(dispIndex, maxPlayingLabelChars + dispIndex);
            playingLabel.setText(" Now Playing: " + cutStr);
            if (!goBack) {
                dispIndex++;
            } else {
                dispIndex--;
            }
        }
    }
    
}
