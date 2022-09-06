package com.amazon.alexa.accessory.internal.monitor;
/* loaded from: classes.dex */
public interface A2dpPlayingMonitor {

    /* loaded from: classes.dex */
    public interface Observer {
        void onA2dpPlayingState(boolean z);
    }

    void addObserver(Observer observer);

    void removeObserver(Observer observer);
}
