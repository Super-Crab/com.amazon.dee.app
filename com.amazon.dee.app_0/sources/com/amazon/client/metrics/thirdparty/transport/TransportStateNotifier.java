package com.amazon.client.metrics.thirdparty.transport;
/* loaded from: classes11.dex */
public interface TransportStateNotifier {

    /* loaded from: classes11.dex */
    public interface TransportWarmedListener {
        void notifyTransportWarmed();
    }

    void listenForTransportWarmed(TransportWarmedListener transportWarmedListener);
}
