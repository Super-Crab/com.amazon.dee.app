package com.amazon.alexa.accessory.internal.connection;

import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.io.SizedSource;
import com.amazon.alexa.accessory.transport.TransportReceiver;
/* loaded from: classes.dex */
public final class DelegateTransportReceiver implements TransportReceiver {
    private final TransportReceiver defaultReceiver;
    private volatile TransportReceiver receiver;

    public DelegateTransportReceiver(TransportReceiver transportReceiver) {
        Preconditions.notNull(transportReceiver, "defaultReceiver");
        this.defaultReceiver = transportReceiver;
    }

    public void clear() {
        this.receiver = null;
    }

    @Override // com.amazon.alexa.accessory.transport.TransportReceiver
    public void onDataReceived(SizedSource sizedSource, int i) throws Exception {
        TransportReceiver transportReceiver = this.receiver;
        if (transportReceiver == null) {
            this.defaultReceiver.onDataReceived(sizedSource, i);
        } else {
            transportReceiver.onDataReceived(sizedSource, i);
        }
    }

    public void setReceiver(TransportReceiver transportReceiver) {
        this.receiver = transportReceiver;
    }
}
