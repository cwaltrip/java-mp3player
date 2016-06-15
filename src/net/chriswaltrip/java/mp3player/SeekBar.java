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

    // Sharing between different scopes
    private int updatedValue = 0; 

    public void updateSeekBar(long progress, float totalVal) {
         // Another thread will calculate the relative position
        BackgroundExecutor.get().execute(new UpdatingTask(progress, totalVal));
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
            // Progress is in microseconds
            int lp = (int) (progress / 1000); 
            int seekLenght = getMaximum();
            int n = (int) ((lp / (totalVal * 1000)) * seekLenght);
            updatedValue = lastSeekVal + n;
        }
    }

    public SeekBar() {
        super();
        // SetMaximum() to smooth the motion
        setMaximum(10000);
        addMouseListener(new MouseListener() {

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
            public void mouseReleased(MouseEvent e) { }

            @Override
            public void mouseExited(MouseEvent e) { }

            @Override
            public void mouseEntered(MouseEvent e) { }

            @Override
            public void mouseClicked(MouseEvent e) { }
        });
    }

    private void returnValueToPlayer(float val) throws BasicPlayerException {
        // ?
    }

}

class BackgroundExecutor {

     // UI thread shouldn't do math
    private static ExecutorService backgroundEx = Executors.newCachedThreadPool();

    public BackgroundExecutor() {
        
    }

    public static ExecutorService get() {
        return backgroundEx;
    }
}
