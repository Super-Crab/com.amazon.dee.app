package com.amazon.alexa.accessory.monitor;
/* loaded from: classes.dex */
public interface MessageNotificationStateMonitor {

    /* loaded from: classes.dex */
    public interface Observer {
        void onUnreadMessageStatusChanged(boolean z);
    }

    void addObserver(Observer observer);

    boolean isUnreadMessageAvailable();

    void removeObserver(Observer observer);
}
