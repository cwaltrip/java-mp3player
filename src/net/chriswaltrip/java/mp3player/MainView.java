package net.chriswaltrip.java.mp3player;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.KeyEvent;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class MainView extends JFrame {
    
    DefaultListModel<String> playList = new DefaultListModel();
    JPanel container = new JPanel();
    JButton playButton = new JButton();
    JButton addButton = new JButton();
    JButton nextButton = new JButton();
    JButton prevButton = new JButton();
    JButton shStButton = new JButton();
    JButton shWfButton = new JButton();
    JButton shDiButton = new JButton();
    JButton deleteButton = new JButton();
    JButton deleteAllButton = new JButton();
    JMenuBar topMenu = new JMenuBar();
    JList<String> jPlayList = new JList<>(playList);
    JScrollPane listScroller = new JScrollPane(jPlayList);
    JPanel playingPanel = new JPanel();
    JLabel playingLabel = new JLabel();
    JLabel stLabel = new JLabel();
    JLabel etLabel = new JLabel();
    SeekBar seekbar = new SeekBar();
    
    // Public constructor
    public MainView() {
        initView();
//        initMenu();
//        initBehavior();
    }
    
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
	c.gridwidth = 5;
	c.gridx = 0;
	c.gridy = 0;
        c.ipady = 20;
	container.add(seekbar, c);

        // Start Time label
        stLabel.setText("00:00");
	c.fill = GridBagConstraints.HORIZONTAL;
	c.weightx = 0.5;
	c.gridwidth = 1;
	c.gridx = 0;
	c.gridy = 1;
        c.ipady = 0;
	container.add(stLabel, c);
        
        // End Time label
        etLabel.setText("00:00");
	c.fill = GridBagConstraints.HORIZONTAL;
	c.weightx = 0.5;
	c.gridwidth = 1;
	c.gridx = 4;
	c.gridy = 1;
	container.add(etLabel, c);
                
        // Now Playing marquee
//        playingPanel.setLayout(new BoxLayout(playingPanel, BoxLayout.PAGE_AXIS));
        playingLabel.setText("Now Playing: ");
        playingLabel.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
//        playingLabel.setBounds(5, 0, 100, 40);
//        playingPanel.add(playingLabel);
        
	c.fill = GridBagConstraints.HORIZONTAL;
	c.weightx = 0.5;
	c.gridwidth = 5;
	c.gridx = 0;
	c.gridy = 2;
        c.ipady = 10;
	container.add(playingLabel, c);        

        // Previous Track button
        prevButton.setText("Prev");
	c.fill = GridBagConstraints.HORIZONTAL;
	c.weightx = 0.5;
	c.gridwidth = 1;
	c.gridx = 0;
	c.gridy = 3;
        c.ipady = 0;
	container.add(prevButton, c);

        // Play Track button
        playButton.setText("Play");
        playButton.setMnemonic(KeyEvent.VK_SPACE);
	c.fill = GridBagConstraints.HORIZONTAL;
	c.weightx = 0.5;
	c.gridwidth = 1;
	c.gridx = 1;
	c.gridy = 3;
	container.add(playButton, c);

        // Next Track button
        nextButton.setText("Next");
	c.fill = GridBagConstraints.HORIZONTAL;
	c.weightx = 0.5;
	c.gridwidth = 1;
	c.gridx = 3;
	c.gridy = 3;
	container.add(nextButton, c);
        
        // Add Song button
        addButton.setText("Add...");
	c.fill = GridBagConstraints.HORIZONTAL;
	c.weightx = 0.5;
	c.gridwidth = 1;
	c.gridx = 4;
	c.gridy = 3;
	container.add(nextButton, c);
        
        // Scrolling playlist
        c.fill = GridBagConstraints.BOTH;
	c.weightx = 1.0;
        c.weighty = 1.0; 
	c.gridwidth = 5;
	c.gridx = 0;
	c.gridy = 4;
	container.add(listScroller, c);
        
        // Stat button
        shStButton.setText("Stat");
	c.fill = GridBagConstraints.HORIZONTAL;
	c.weightx = 0.5;
	c.gridwidth = 1;
	c.gridx = 0;
	c.gridy = 5;
	container.add(shStButton, c);

        // ShWf button
        shWfButton.setText("ShWf");
	c.fill = GridBagConstraints.HORIZONTAL;
	c.weightx = 0.5;
	c.gridwidth = 1;
	c.gridx = 1;
	c.gridy = 5;
	container.add(shWfButton, c);
        
        // ShDi button
        shDiButton.setText("ShDi");
	c.fill = GridBagConstraints.HORIZONTAL;
	c.weightx = 0.5;
	c.gridwidth = 1;
	c.gridx = 2;
	c.gridy = 5;
	container.add(shDiButton, c);
        
        // Delete button
        deleteButton.setText("X");
	c.fill = GridBagConstraints.HORIZONTAL;
//        c.anchor = GridBagConstraints.PAGE_END;
	c.weightx = 0.5;
	c.gridwidth = 1;
	c.gridx = 4;
	c.gridy = 5;
	container.add(deleteButton, c);

    }
    
}
