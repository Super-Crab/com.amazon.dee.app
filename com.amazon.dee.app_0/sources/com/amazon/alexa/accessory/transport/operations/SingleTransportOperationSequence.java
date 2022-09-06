package com.amazon.alexa.accessory.transport.operations;

import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.transport.TransportPriority;
/* loaded from: classes6.dex */
public final class SingleTransportOperationSequence implements TransportOperationSequence {
    private final TransportOperation abortOperation;
    private volatile boolean aborted;
    private volatile boolean completed;
    private final TransportOperation operation;

    public SingleTransportOperationSequence(TransportOperation transportOperation, TransportOperation transportOperation2) {
        Preconditions.notNull(transportOperation, "operation");
        this.operation = transportOperation;
        this.abortOperation = transportOperation2;
    }

    @Override // com.amazon.alexa.accessory.transport.operations.TransportOperationSequence
    public void abort() {
        this.aborted = true;
    }

    @Override // com.amazon.alexa.accessory.transport.operations.TransportOperationSequence
    public String getKey() {
        return this.operation.getKey();
    }

    @Override // com.amazon.alexa.accessory.transport.operations.TransportOperationSequence
    public TransportPriority getPriority() {
        return this.operation.getPriority();
    }

    @Override // com.amazon.alexa.accessory.transport.operations.TransportOperationSequence
    public TransportOperation next() {
        if (this.completed) {
            return null;
        }
        this.completed = true;
        return this.aborted ? this.abortOperation : this.operation;
    }

    public SingleTransportOperationSequence(TransportOperation transportOperation) {
        this(transportOperation, null);
    }
}
