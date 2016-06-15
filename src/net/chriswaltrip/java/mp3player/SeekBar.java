package net.chriswaltrip.java.mp3player;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JProgressBar;
import javazoom.jlgui.basicplayer.BasicPlayerException;

public class SeekBar extends JProgressBar {

    private int updatedValue = 0; //sharing between different scopes

    public void updateSeekBar(long progress, float totalVal) {
        BackgroundExecutor.get().execute(new UpdatingTask(progress, totalVal)); //Another thread will calculate the relative position
        setValue(updatedValue);
    }

    private class UpdatingTask implements Runnable {

        long progress;
        float totalVal;
        int lastSeekVal;

        public UpdatingTask(long progress, float totalVal) {
            this.progress = progress;
            this.totalVal = totalVal;
        }

        @Override
        public void run() {
            int lp = (int) (progress / 1000); //progress comes in microseconds
            int seekLenght = getMaximum();
            int n = (int) ((lp / (totalVal * 1000)) * seekLenght);
            updatedValue = lastSeekVal + n;
        }
    }

    public SeekBar() {
        super();
        setMaximum(10000); //it's smoother this way
        addMouseListener(new MouseListener() {

            @Override
            public void mouseReleased(MouseEvent e) {
                
            }

            @Override
            public void mousePressed(MouseEvent e) {
                float val = ((float) e.getX() / getWidth()) * getMaximum();
                try {
                    returnValueToPlayer(val);
                } catch (BasicPlayerException ex) {
                    Logger.getLogger(SeekBar.class.getName()).log(Level.SEVERE, null, ex);
                }
                setValue((int) val);
                System.out.println("SeekBar pressed: " + val + " x: " + e.getX());
            }

            @Override
            public void mouseExited(MouseEvent e) {
                
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                
            }
        });
    }

    private void returnValueToPlayer(float val) throws BasicPlayerException {
    }
}

class BackgroundExecutor {

    private static ExecutorService backgroundEx = Executors.newCachedThreadPool(); //UI thread shouldn't do math

    public BackgroundExecutor() {
    }

    public static ExecutorService get() {
        return backgroundEx;
    }
}
