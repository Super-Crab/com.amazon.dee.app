package amazon.speech.simclient.focus;

import android.util.Log;
/* loaded from: classes.dex */
public enum UnbindBehavior {
    CLEAR_FOCUS,
    RELEASE_FOCUS;
    
    private static final String TAG = UnbindBehavior.class.getSimpleName();
    private static final UnbindBehavior DEFAULT = CLEAR_FOCUS;

    public static UnbindBehavior fromString(String str) {
        if (str == null) {
            return DEFAULT;
        }
        try {
            return valueOf(str);
        } catch (Exception e) {
            Log.w(TAG, String.format("Error converting '%s' to %s, defaulting to %s", str, UnbindBehavior.class.getSimpleName(), DEFAULT), e);
            return DEFAULT;
        }
    }
}
