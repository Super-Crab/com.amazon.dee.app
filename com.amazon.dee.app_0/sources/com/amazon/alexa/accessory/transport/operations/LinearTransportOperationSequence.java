package com.amazon.alexa.accessory.transport.operations;

import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.transport.TransportPriority;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Collection;
import java.util.Deque;
/* loaded from: classes6.dex */
public final class LinearTransportOperationSequence implements TransportOperationSequence {
    private volatile boolean aborted;
    private final String key;
    private final Deque<TransportOperation> operations;
    private final TransportPriority priority;

    public LinearTransportOperationSequence(String str, TransportPriority transportPriority, Collection<? extends TransportOperation> collection) {
        Preconditions.notNull(str, "key");
        Preconditions.notNull(transportPriority, "priority");
        Preconditions.notNull(collection, "operations");
        this.key = str;
        this.operations = new ArrayDeque(collection);
        this.priority = transportPriority;
    }

    @Override // com.amazon.alexa.accessory.transport.operations.TransportOperationSequence
    public void abort() {
        this.aborted = true;
    }

    @Override // com.amazon.alexa.accessory.transport.operations.TransportOperationSequence
    public String getKey() {
        return this.key;
    }

    @Override // com.amazon.alexa.accessory.transport.operations.TransportOperationSequence
    public TransportPriority getPriority() {
        return this.priority;
    }

    @Override // com.amazon.alexa.accessory.transport.operations.TransportOperationSequence
    public TransportOperation next() {
        if (this.aborted || this.operations.isEmpty()) {
            return null;
        }
        return this.operations.poll();
    }

    public LinearTransportOperationSequence(String str, TransportPriority transportPriority, TransportOperation... transportOperationArr) {
        this(str, transportPriority, Arrays.asList(transportOperationArr));
    }
}
