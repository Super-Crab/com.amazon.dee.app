package com.amazon.alexa.accessory.internal.monitor;
/* loaded from: classes.dex */
public interface NetworkStatusMonitor {

    /* loaded from: classes.dex */
    public interface Observer {
        void onNetworkStatusChanged(boolean z);
    }

    void addObserver(Observer observer);

    boolean isConnected();

    void removeObserver(Observer observer);
}
