package amazon.speech.tts.playback;

import amazon.speech.dual.playback.DualPlaybackController;
import amazon.speech.playback.ITtsPlaybackController;
import amazon.speech.util.Log;
import android.content.Context;
/* loaded from: classes.dex */
public class TtsPlaybackControllerFactory {
    static String FIRE_TV_FEATURE = "amazon.hardware.fire_tv";
    private static String TAG = "TtsPlaybackControllerFactory";

    public static ITtsPlaybackController createPlaybackController(Context context) {
        if (context.getPackageManager().hasSystemFeature(FIRE_TV_FEATURE)) {
            Log.d(TAG, "using DualPlaybackController");
            return new DualPlaybackController(context);
        }
        Log.d(TAG, "using DefaultPlaybackController");
        return new DefaultPlaybackController();
    }
}
