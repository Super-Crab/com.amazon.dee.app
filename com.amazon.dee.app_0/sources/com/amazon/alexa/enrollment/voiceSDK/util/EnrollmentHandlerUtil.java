package com.amazon.alexa.enrollment.voiceSDK.util;

import android.os.Handler;
import android.os.Looper;
/* loaded from: classes7.dex */
public class EnrollmentHandlerUtil {
    public void runWithDelay(Runnable runnable, long j) {
        new Handler(Looper.getMainLooper()).postDelayed(runnable, j);
    }
}
