package com.amazon.communication;
/* loaded from: classes12.dex */
public interface ScreenEventMonitor {
    void deregisterScreenEventListener(ScreenEventListener screenEventListener);

    void registerScreenEventListener(ScreenEventListener screenEventListener);

    void start();

    void stop();
}
