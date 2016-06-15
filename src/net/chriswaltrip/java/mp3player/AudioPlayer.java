package net.chriswaltrip.java.mp3player;

import java.io.File;
import java.util.ArrayList;
import javazoom.jlgui.basicplayer.BasicPlayer;
import javazoom.jlgui.basicplayer.BasicPlayerException;

public class AudioPlayer extends BasicPlayer {
    
    // The singleton itself
    private static AudioPlayer singleton = null;
    
    // Song list and index
    private ArrayList<String> playList = new ArrayList<>();
    private int index = 0;
    
    // Player status
    private boolean paused = true;
    private boolean playing = false;
    private boolean seeking = false;
    
    // Song file data
    private byte[] data;
    
    // Song file metadata
    private float durationInSeconds = 0;
    private int frameSize = 0;
    private float frameRate = 0;
    private long currentMs = 0;
    private int lastSeekMs = 0;
    
    private AudioPlayer() {
        super();
        AudioPlayerListener listener = new AudioPlayerListener(this);
        super.addBasicPlayerListener(listener);
    }
    
    public static AudioPlayer getPlayer() {
        if (singleton == null) {
            singleton = new AudioPlayer();
        }
        return singleton;
    }
    
    @Override
    public void play() throws BasicPlayerException {
        if (playList.isEmpty()) {
            return;
        }
        if (!paused || !playing) {
            File file = new File(playList.get(index));
            System.out.println("Starting playback: " + file.getAbsolutePath());
            open(file);
            super.play();
            playing = true;
            paused = false;
        }
        if (paused) {
            resume();
        }
//        startPlayback();
    }

    @Override
    public void stop() throws BasicPlayerException {
//        stopPlayback();
        super.stop();
        playing = false;
        paused = false;
        System.out.println("Stopped");
    }

    @Override
    public void pause() throws BasicPlayerException {
        super.pausePlayback();
        paused = true;
        System.out.println("Paused");
    }

    @Override
    public void resume() throws BasicPlayerException {
        super.resumePlayback();
        paused = false;
        System.out.println("Resumed");
    }
    
    public void addSong(String path) {
        playList.add(path);
    }
    
    public void removeSong(int index) {
        playList.remove(index);
    }
    
    public void removeSong(String path) {
        playList.remove(path);
    }
    
    public void nextSong() throws BasicPlayerException {
        if (playList.isEmpty()) {
            return;
        }
        lastSeekMs = 0;
        paused = false;
        index = (index + 1) % playList.size();
        play();
    }
    
    public void prevSong() throws BasicPlayerException {
        if (playList.isEmpty()) {
            return;
        }
        lastSeekMs = 0;
        paused = false;
        index = (index - 1) % playList.size();
        play();
    }

    /**
     * Getters and setters for status fields
     */
    
    public ArrayList<String> getPlaylist() {
        return playList;
    }
    
    public void setPlaylist(ArrayList<String> list) {
        playList = list;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
        lastSeekMs = 0;
    }
    
    public boolean isPlaying() {
        return playing;
    }

    public void setPlaying(boolean playing) {
        this.playing = playing;
    }

    public boolean isPaused() {
        return paused;
    }

    public void setPaused(boolean paused) {
        this.paused = paused;
    }

    public boolean isSeeking() {
        return seeking;
    }

    public void setSeeking(boolean seeking) {
        this.seeking = seeking;
    }

    public float getDurationInSeconds() {
        return durationInSeconds;
    }

    public void setDurationInSeconds(float durationInSeconds) {
        this.durationInSeconds = durationInSeconds;
    }

    public int getFrameSize() {
        return frameSize;
    }

    public void setFrameSize(int frameSize) {
        this.frameSize = frameSize;
    }

    public float getFrameRate() {
        return frameRate;
    }

    public void setFrameRate(float frameRate) {
        this.frameRate = frameRate;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public long getCurrentMs() {
        return currentMs;
    }

    public void setCurrentMs(long currentMs) {
        this.currentMs = currentMs;
    }

    public int getLastSeekMs() {
        return lastSeekMs;
    }

    public void setLastSeekMs(int lastSeekMs) {
        this.lastSeekMs = lastSeekMs;
    }
    
    public long getProgressMicroseconds(){
        return currentMs + lastSeekMs;
    }
    
}
