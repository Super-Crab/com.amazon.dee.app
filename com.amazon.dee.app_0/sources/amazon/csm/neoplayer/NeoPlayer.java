package amazon.csm.neoplayer;

import android.media.AudioAttributes;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeoutException;
/* loaded from: classes.dex */
public interface NeoPlayer {

    /* loaded from: classes.dex */
    public interface Listener {
        void onPlayerError(Exception exc);

        void onPlayerStateChanged(NeoPlayerState neoPlayerState);

        void onSlowReadDetected(long j, long j2);
    }

    /* loaded from: classes.dex */
    public enum NeoPlayerState {
        STATE_DEFAULT,
        STATE_IDLE,
        STATE_PREPARING,
        STATE_BUFFERING,
        STATE_READY,
        STATE_ENDED
    }

    /* loaded from: classes.dex */
    public enum State {
        STATE_STOPPED,
        STATE_CONFIGURED,
        STATE_RUNNING,
        STATE_RELEASED
    }

    long getCurrentPosition();

    void loadStream(InputStream inputStream) throws IOException, TimeoutException;

    void play() throws TimeoutException;

    void release();

    void setAttributes(AudioAttributes audioAttributes);

    void setListener(Listener listener);

    void stop() throws TimeoutException;
}
