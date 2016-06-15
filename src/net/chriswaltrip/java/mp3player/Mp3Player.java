/*
 * Uses the BasicPlayer API from the jlGui project at:
 * http://www.javazoom.net/jlgui/api.html
 */

package net.chriswaltrip.java.mp3player;

import javax.swing.SwingUtilities;

public class Mp3Player {
    
    public static void main(String[] args) {
        // Launch main view/make visible in own thread
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                MainView main = new MainView();
//                main.pack();
                main.setVisible(true);
            }
        });
    }
}