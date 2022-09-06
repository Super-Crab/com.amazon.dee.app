package com.amazon.alexa.handsfree.settings.wakewordsettings.wakewordsettingstype;

import android.os.Handler;
import android.os.Looper;
import androidx.annotation.NonNull;
/* loaded from: classes8.dex */
public class WakeWordSettingsThreadHelper {
    private static Handler handler = new Handler(Looper.getMainLooper());

    private WakeWordSettingsThreadHelper() {
        throw new UnsupportedOperationException();
    }

    public static void runOnUiThread(@NonNull Runnable runnable) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            runnable.run();
        } else {
            handler.post(runnable);
        }
    }
}
