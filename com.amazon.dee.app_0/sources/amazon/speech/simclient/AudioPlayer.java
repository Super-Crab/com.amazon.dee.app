package amazon.speech.simclient;

import java.io.InputStream;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeoutException;
/* loaded from: classes.dex */
public interface AudioPlayer {

    /* loaded from: classes.dex */
    public static abstract class AudioPlayerListener {
        int mLastState;
        ScheduledFuture<?> mTimeoutFuture;

        public abstract void onPlayWhenReadyCommitted();

        public abstract void onPlayerError(Exception exc);

        public abstract void onPlayerStateChanged(boolean z, AudioPlayerState audioPlayerState);
    }

    /* loaded from: classes.dex */
    public enum AudioPlayerState {
        STATE_DEFAULT,
        STATE_IDLE,
        STATE_PREPARING,
        STATE_BUFFERING,
        STATE_READY,
        STATE_ENDED
    }

    long getCurrentPosition();

    void play(InputStream inputStream);

    void release();

    void removeListener();

    void setListener(AudioPlayerListener audioPlayerListener);

    void setVolume(float f);

    void stop() throws TimeoutException;
}
