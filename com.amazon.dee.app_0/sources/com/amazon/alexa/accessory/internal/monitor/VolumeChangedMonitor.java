package com.amazon.alexa.accessory.internal.monitor;
/* loaded from: classes.dex */
public interface VolumeChangedMonitor {

    /* loaded from: classes.dex */
    public interface Observer {
        void onVolumeChanged();
    }

    void addObserver(Observer observer);

    void removeObserver(Observer observer);
}
