package com.amazon.alexa.accessory.internal.connection;

import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.transport.TransportDispatcher;
import com.amazon.alexa.accessory.transport.TransportTransaction;
/* loaded from: classes.dex */
public final class DelegateTransportDispatcher implements TransportDispatcher {
    private volatile TransportDispatcher dispatcher;

    @Override // com.amazon.alexa.accessory.transport.TransportDispatcher
    public void abort(TransportTransaction transportTransaction) {
        TransportDispatcher transportDispatcher = this.dispatcher;
        if (transportDispatcher == null) {
            Logger.w("Transport dispatcher is not supplied, abort transaction " + transportTransaction + " is ignored");
            return;
        }
        transportDispatcher.abort(transportTransaction);
    }

    @Override // com.amazon.alexa.accessory.transport.TransportDispatcher
    public void dispatch(TransportTransaction transportTransaction) {
        TransportDispatcher transportDispatcher = this.dispatcher;
        if (transportDispatcher == null) {
            Logger.w("Transport dispatcher is not supplied, ignoring transaction " + transportTransaction);
            return;
        }
        transportDispatcher.dispatch(transportTransaction);
    }

    public void setDispatcher(TransportDispatcher transportDispatcher) {
        this.dispatcher = transportDispatcher;
    }
}
