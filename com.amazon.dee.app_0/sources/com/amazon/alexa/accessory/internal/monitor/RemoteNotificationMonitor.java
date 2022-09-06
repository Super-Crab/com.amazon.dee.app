package com.amazon.alexa.accessory.internal.monitor;

import java.util.Set;
/* loaded from: classes.dex */
public interface RemoteNotificationMonitor {

    /* loaded from: classes.dex */
    public interface Observer {
        void onRemoteNotification(Set<RemoteNotificationStatus> set);
    }

    void addObserver(Observer observer);

    void removeObserver(Observer observer);
}
