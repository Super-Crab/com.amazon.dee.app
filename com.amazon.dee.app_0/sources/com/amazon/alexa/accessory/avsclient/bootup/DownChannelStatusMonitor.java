package com.amazon.alexa.accessory.avsclient.bootup;
/* loaded from: classes.dex */
public interface DownChannelStatusMonitor {

    /* loaded from: classes.dex */
    public interface Observer {
        void onDownChannelStatusChanged(boolean z);
    }

    void addObserver(Observer observer);

    boolean getIsConnected();

    void removeObserver(Observer observer);
}
