package com.amazon.rtcmedia.webrtc;

import org.webrtc.EglBase;
/* loaded from: classes13.dex */
public class EglBaseWrapper {
    private static EglBase.Context eglContext;
    private static int eglRefCount;
    private static EglBase rootEglBase;

    public static synchronized EglBase.Context getEglBaseContext() {
        EglBase.Context context;
        synchronized (EglBaseWrapper.class) {
            if (rootEglBase != null) {
                if (eglContext == null) {
                    eglContext = rootEglBase.mo13074getEglBaseContext();
                }
            } else {
                rootEglBase = EglBase.create();
                eglContext = rootEglBase.mo13074getEglBaseContext();
            }
            eglRefCount++;
            context = eglContext;
        }
        return context;
    }

    public static synchronized void release() {
        synchronized (EglBaseWrapper.class) {
            if (eglRefCount > 0) {
                eglRefCount--;
            }
            if (eglRefCount == 0 && rootEglBase != null) {
                rootEglBase.release();
                rootEglBase = null;
                eglContext = null;
            }
        }
    }
}
