package net.chriswaltrip.java.mp3player;

import java.io.File;
import javax.swing.filechooser.FileFilter;

public class AudioFileFilter extends FileFilter {

    @Override
    public boolean accept(File f) {
        if (f.isDirectory()) {
            return true;
        }
        if (f.getName().endsWith(".mp3") || f.getName().endsWith(".MP3")) {
            return true;
        }
//        if (f.getName().endsWith(".wav") || f.getName().endsWith(".WAV")) {
//            return true;
//        }
        return false;
    }

    @Override
    public String getDescription() {
//        return "Supported audio files (.mp3, .wav)";
        return "MP3 files (.mp3)";
    }
    
}
