package com.amazon.alexa.accessory.internal.monitor;
/* loaded from: classes.dex */
public interface NotificationFilterStatusMonitor {

    /* loaded from: classes.dex */
    public interface Observer {
        void onForwardNotificationChanged(boolean z);
    }

    void addObserver(Observer observer);

    boolean isNotificationForwardingEnabled();

    void removeObserver(Observer observer);
}
