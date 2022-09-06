package com.amazon.alexa.accessory.internal.monitor;
/* loaded from: classes.dex */
public interface ScoStatusMonitor {

    /* loaded from: classes.dex */
    public interface Observer {
        void onScoConnectionState(boolean z);
    }

    void addObserver(Observer observer);

    void removeObserver(Observer observer);
}
