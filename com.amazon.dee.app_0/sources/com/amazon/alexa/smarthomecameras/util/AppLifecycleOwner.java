package com.amazon.alexa.smarthomecameras.util;

import java.util.ArrayList;
import java.util.List;
/* loaded from: classes10.dex */
public class AppLifecycleOwner {
    private final List<AppLifecycleListener> listeners = new ArrayList();

    public synchronized void deregisterListener(AppLifecycleListener appLifecycleListener) {
        this.listeners.remove(appLifecycleListener);
    }

    public synchronized void onAppBackground() {
        for (AppLifecycleListener appLifecycleListener : this.listeners) {
            appLifecycleListener.onAppBackground();
        }
    }

    public synchronized void onAppForeground() {
        for (AppLifecycleListener appLifecycleListener : this.listeners) {
            appLifecycleListener.onAppForeground();
        }
    }

    public synchronized void registerListener(AppLifecycleListener appLifecycleListener) {
        this.listeners.add(appLifecycleListener);
    }

    public synchronized void teardown() {
        this.listeners.clear();
    }
}
