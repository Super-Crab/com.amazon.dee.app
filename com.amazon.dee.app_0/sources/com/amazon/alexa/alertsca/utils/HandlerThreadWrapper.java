package com.amazon.alexa.alertsca.utils;

import android.os.Handler;
import android.os.HandlerThread;
/* loaded from: classes6.dex */
public class HandlerThreadWrapper {
    private final Handler handler;
    private final HandlerThread handlerThread;

    public HandlerThreadWrapper(String str) {
        this.handlerThread = new HandlerThread(str);
        this.handlerThread.start();
        this.handler = new Handler(this.handlerThread.getLooper());
    }

    public void post(Runnable runnable) {
        this.handler.post(runnable);
    }

    public void quitSafely() {
        this.handlerThread.quitSafely();
    }
}
