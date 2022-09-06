package com.amazon.rtcmedia.util;

import android.content.Context;
/* loaded from: classes13.dex */
public class RTCContextUtils {
    private static Context applicationContext;
    private static RtcscLogger mLog = RtcscLogger.getLogger(RTCContextUtils.class);

    public static Context getApplicationContext() {
        return applicationContext;
    }

    public static void initialize(Context context) {
        if (context != null) {
            applicationContext = context;
            mLog.i("initialize: Application context has been set.");
            return;
        }
        throw new IllegalArgumentException("Application context cannot be null for ContextUtils.initialize.");
    }
}
