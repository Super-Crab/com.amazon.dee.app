package amazon.speech.tts.asp;

import amazon.speech.util.DebugUtil;
import amazon.speech.util.Log;
import android.content.Context;
/* loaded from: classes.dex */
public class AspPlaybackReporter {
    static final int ASP_CMD_NOTIFY_TTS_STATUS = 1;
    public static final String ASP_FEATURE = "middleware_asp";
    IAspWrapper mAspWrapper;
    private static final String TAG = DebugUtil.getTag(DebugUtil.Module.CSM, AspPlaybackReporter.class);
    static final byte[] PLAYBACK_START = {1, 0, 0, 0};
    static final byte[] PLAYBACK_STOP = {0, 0, 0, 0};
    static final byte[] REPLY = new byte[0];

    public AspPlaybackReporter(Context context) {
        this(getAspWrapper(context));
    }

    private static AspWrapper getAspWrapper(Context context) {
        if (context != null) {
            if (!context.getPackageManager().hasSystemFeature("middleware_asp")) {
                return null;
            }
            return new AspWrapper();
        }
        throw new IllegalArgumentException("Context cannot be null");
    }

    public void reportAspPlayback(boolean z) {
        if (this.mAspWrapper != null) {
            String str = TAG;
            Log.d(str, "reportAspPlayback " + z);
            this.mAspWrapper.command(1, z ? PLAYBACK_START : PLAYBACK_STOP, REPLY);
        }
    }

    AspPlaybackReporter(IAspWrapper iAspWrapper) {
        this.mAspWrapper = iAspWrapper;
    }
}
