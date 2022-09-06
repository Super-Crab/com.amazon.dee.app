package amazon.speech.simclient;

import amazon.speech.util.Log;
import amazon.speech.util.RuntimeDeviceTypeHelper;
import android.content.Context;
import com.amazon.metrics.MetricsUtil;
/* loaded from: classes.dex */
public class AudioPlayerFactory {
    private static final boolean DEBUG = true;
    static final int DEFAULT;
    private static final String TAG = "AudioPlayerFactory";

    static {
        if (RuntimeDeviceTypeHelper.isDeviceATablet()) {
            DEFAULT = 0;
        } else {
            DEFAULT = 1;
        }
    }

    public AudioPlayer createAudioPlayer(Context context) {
        Log.d(TAG, "Created NeoAudioPlayer");
        return new NeoAudioPlayer(context, MetricsUtil.getInstance());
    }
}
