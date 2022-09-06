package com.amazon.rtcmedia.util;

import com.amazon.rtcmedia.webrtc.EglBaseWrapper;
import org.webrtc.EglBase;
/* loaded from: classes13.dex */
public class EglBaseProvider {
    private static EglBase.Context eglContext;

    public static synchronized EglBase.Context getEglBaseContext() {
        EglBase.Context context;
        synchronized (EglBaseProvider.class) {
            if (eglContext == null) {
                eglContext = EglBaseWrapper.getEglBaseContext();
            }
            context = eglContext;
        }
        return context;
    }

    public static synchronized void release() {
        synchronized (EglBaseProvider.class) {
            EglBaseWrapper.release();
            eglContext = null;
        }
    }
}
