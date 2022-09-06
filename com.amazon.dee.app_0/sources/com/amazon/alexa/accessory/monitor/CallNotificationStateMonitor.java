package com.amazon.alexa.accessory.monitor;
/* loaded from: classes.dex */
public interface CallNotificationStateMonitor {

    /* loaded from: classes.dex */
    public interface Observer {
        void onCallStatusChanged(int i);
    }

    void addObserver(Observer observer);

    int getCallNotificationStatus();

    void removeObserver(Observer observer);
}
