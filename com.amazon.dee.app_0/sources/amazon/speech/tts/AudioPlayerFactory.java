package amazon.speech.tts;

import amazon.speech.simclient.metrics.MetricsClient;
import amazon.speech.util.Log;
import android.content.Context;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class AudioPlayerFactory {
    private static final boolean DEBUG = true;
    private static final String TAG = "AudioPlayerFactory";
    private final boolean mFlagTTSAttrs;

    public AudioPlayerFactory() {
        this(false);
    }

    public AudioPlayer createAudioPlayer(Context context, MetricsClient metricsClient) {
        Log.d(TAG, "Created NeoAudioPlayer");
        return new NeoAudioPlayer(context, metricsClient, this.mFlagTTSAttrs);
    }

    public AudioPlayerFactory(boolean z) {
        this.mFlagTTSAttrs = z;
    }
}
