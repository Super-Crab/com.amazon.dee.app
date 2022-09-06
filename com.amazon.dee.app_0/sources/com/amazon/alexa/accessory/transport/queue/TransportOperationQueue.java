package com.amazon.alexa.accessory.transport.queue;

import com.amazon.alexa.accessory.transport.operations.TransportOperation;
import com.amazon.alexa.accessory.transport.operations.TransportOperationSequence;
/* loaded from: classes6.dex */
public interface TransportOperationQueue {
    void abort(String str);

    TransportOperation dequeue();

    void enqueue(TransportOperationSequence transportOperationSequence);
}
