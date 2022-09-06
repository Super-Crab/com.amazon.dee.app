package com.amazon.alexa.accessory.transport.operations;

import com.amazon.alexa.accessory.AccessoryTransport;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.transport.TransportPriority;
import java.io.IOException;
/* loaded from: classes6.dex */
public final class FlushTransportOperation implements TransportOperation {
    private final String key;
    private final TransportPriority priority;
    private final AccessoryTransport transport;

    public FlushTransportOperation(String str, AccessoryTransport accessoryTransport, TransportPriority transportPriority) {
        Preconditions.notNull(str, "key");
        Preconditions.notNull(accessoryTransport, "transport");
        Preconditions.notNull(transportPriority, "priority");
        this.key = str;
        this.transport = accessoryTransport;
        this.priority = transportPriority;
    }

    @Override // com.amazon.alexa.accessory.transport.operations.TransportOperation
    public void execute() throws IOException {
        this.transport.sink().flush();
    }

    @Override // com.amazon.alexa.accessory.transport.operations.TransportOperation
    public String getKey() {
        return this.key;
    }

    @Override // com.amazon.alexa.accessory.transport.operations.TransportOperation
    public TransportPriority getPriority() {
        return this.priority;
    }
}
