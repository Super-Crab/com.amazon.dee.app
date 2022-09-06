package com.amazon.alexa.accessory.transport;
/* loaded from: classes6.dex */
public interface TransportDispatcher {
    void abort(TransportTransaction transportTransaction);

    void dispatch(TransportTransaction transportTransaction);
}
