package com.amazon.alexa.accessory.transport.codecs;

import com.amazon.alexa.accessory.AccessoryTransport;
import com.amazon.alexa.accessory.transport.TransportData;
import com.amazon.alexa.accessory.transport.TransportTransaction;
import com.amazon.alexa.accessory.transport.operations.TransportOperationSequence;
import java.io.IOException;
/* loaded from: classes6.dex */
public interface TransportCodec {

    /* loaded from: classes6.dex */
    public interface Factory {
        TransportCodec create(AccessoryTransport accessoryTransport) throws IOException;
    }

    /* loaded from: classes6.dex */
    public interface OperationSequenceEncoder {
        void encoded(TransportOperationSequence transportOperationSequence) throws IOException;
    }

    TransportData decode(OperationSequenceEncoder operationSequenceEncoder) throws IOException;

    TransportOperationSequence encode(TransportTransaction transportTransaction) throws IOException;
}
