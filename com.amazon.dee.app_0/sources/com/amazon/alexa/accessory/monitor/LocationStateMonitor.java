package com.amazon.alexa.accessory.monitor;
/* loaded from: classes.dex */
public interface LocationStateMonitor {

    /* loaded from: classes.dex */
    public interface Observer {
        void onLocationDisabled();

        void onLocationEnabled();
    }

    void addObserver(Observer observer);

    void removeObserver(Observer observer);
}
