package com.amazon.alexa.accessory.avsclient.bootup;
/* loaded from: classes.dex */
public interface ReadinessSupplier {

    /* loaded from: classes.dex */
    public interface Listener {
        void onReadyState(boolean z);
    }

    void deregisterReadinessListener(Listener listener);

    void registerReadinessListener(Listener listener);
}
