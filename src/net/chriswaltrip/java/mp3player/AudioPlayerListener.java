package net.chriswaltrip.java.mp3player;

import java.io.File;
import java.util.Map;
import javazoom.jlgui.basicplayer.BasicController;
import javazoom.jlgui.basicplayer.BasicPlayerEvent;
import javazoom.jlgui.basicplayer.BasicPlayerListener;

public class AudioPlayerListener implements BasicPlayerListener {

    private AudioPlayer player;
    private long audioFileLength = 0L;
    private int fSize = 0;
    private float fRate = 0F;
    private float duration = 0F;

    public AudioPlayerListener(AudioPlayer player) {
        this.player = player;
    }

    @Override
    public void opened(Object stream, Map props) {
        File file = new File(player.getPlaylist().get(player.getIndex()));
        audioFileLength = file.length();
        fSize = (int) props.get("mp3.framesize.bytes");
        fRate = (float) props.get("mp3.framerate.fps");
        duration = (audioFileLength / (fSize * fRate));
        player.setFrameSize(fSize);
        player.setFrameRate(fRate);
        player.setDurationInSeconds(duration);
        System.out.println("Frame size: " + fSize + ", Frame rate: " + fRate);
        System.out.println("File length: " + duration + " seconds");
    }

    @Override
    public void progress(int bytesread, long microseconds, byte[] pcmdata, Map properties) {
        player.setCurrentMs(microseconds);
        player.setData(pcmdata);
    }

    @Override
    public void stateUpdated(BasicPlayerEvent event) {
        if (event.getCode() == BasicPlayerEvent.EOM) {
            player.setLastSeekMs(0);
            player.setPaused(true);
            player.setPlaying(false);
            System.out.println("EOM event");
        }
        if (event.getCode() == BasicPlayerEvent.SEEKING) {
            player.setSeeking(true);
        }
        if (event.getCode() == BasicPlayerEvent.SEEKED) {
            player.setSeeking(false);
        }
    }

    @Override
    public void setController(BasicController arg0) {
        System.out.println("Call to unimplemented Mp3PlayerListener.setController()");
    }

}
