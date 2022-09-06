package com.amazon.alexa.accessory.transport.operations;

import com.amazon.alexa.accessory.transport.TransportPriority;
/* loaded from: classes6.dex */
public interface TransportOperationSequence {
    void abort();

    String getKey();

    TransportPriority getPriority();

    TransportOperation next();
}
