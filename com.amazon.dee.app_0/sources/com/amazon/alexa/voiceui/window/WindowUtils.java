package com.amazon.alexa.voiceui.window;

import android.view.Window;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.utils.ApiThreadHelper;
/* loaded from: classes11.dex */
public final class WindowUtils {
    @VisibleForTesting
    static final int PASSTHROUGH_FLAGS = 56;

    private WindowUtils() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean doesCurrentWindowAllowPassthrough(Window window) {
        return (window.getAttributes().flags & 56) == 56;
    }

    public static void setTouchEventPassThrough(final Window window, final boolean z) {
        ApiThreadHelper.runOnUiThread(new Runnable() { // from class: com.amazon.alexa.voiceui.window.WindowUtils.1
            @Override // java.lang.Runnable
            public void run() {
                boolean z2 = WindowUtils.doesCurrentWindowAllowPassthrough(window) != z;
                if (z2 && z) {
                    window.addFlags(56);
                } else if (!z2) {
                } else {
                    window.clearFlags(56);
                }
            }
        });
    }
}
