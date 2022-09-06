package com.amazon.alexa.accessory.transport.codecs.V2;

import com.amazon.alexa.accessory.AccessoryTransport;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.io.DataSink;
import com.amazon.alexa.accessory.transport.TransportPriority;
import com.amazon.alexa.accessory.transport.operations.TransportOperation;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import java.io.IOException;
/* loaded from: classes6.dex */
public final class V2ControlPacketTransportOperation implements TransportOperation {
    public static final int CONTROL_TYPE_ABORT = 2;
    public static final int CONTROL_TYPE_ACK = 1;
    public static final int ERROR_ACK_FAIL = 2;
    public static final int ERROR_LENGTH_INVALID = 4;
    public static final int ERROR_NO_RESOURCE = 5;
    public static final int ERROR_SEQUENCE_NUMBER_INVALID = 3;
    public static final int ERROR_SUCCESS = 0;
    public static final int ERROR_WRITE_FAIL = 1;
    private static final int TRANSACTION_TYPE_CONTROL = 3;
    private final int controlType;
    private final int errorCode;
    private final String key;
    private final TransportPriority priority;
    private final int stream;
    private final int transactionId;
    private final AccessoryTransport transport;

    /* loaded from: classes6.dex */
    public static final class Builder {
        private boolean isStreamSet;
        private boolean isTransactionIdSet;
        private String key;
        private int stream;
        private int transactionId;
        private AccessoryTransport transport;
        private int controlType = 1;
        private int errorCode = 0;
        private TransportPriority priority = TransportPriority.HIGH;

        Builder() {
        }

        public V2ControlPacketTransportOperation build() {
            Preconditions.notNull(this.key, "key");
            Preconditions.notNull(this.transport, "transport");
            Preconditions.notNull(this.priority, "priority");
            Preconditions.precondition(this.isStreamSet, "stream is not set");
            Preconditions.precondition(this.isTransactionIdSet, "transaction id is not set");
            return new V2ControlPacketTransportOperation(this);
        }

        public Builder controlType(int i) {
            this.controlType = i;
            return this;
        }

        public Builder errorCode(int i) {
            this.errorCode = i;
            return this;
        }

        public Builder key(String str) {
            this.key = str;
            return this;
        }

        public Builder priority(TransportPriority transportPriority) {
            this.priority = transportPriority;
            return this;
        }

        public Builder stream(int i) {
            this.stream = i;
            this.isStreamSet = true;
            return this;
        }

        public Builder transactionId(int i) {
            this.transactionId = i;
            this.isTransactionIdSet = true;
            return this;
        }

        public Builder transport(AccessoryTransport accessoryTransport) {
            this.transport = accessoryTransport;
            return this;
        }
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    @Override // com.amazon.alexa.accessory.transport.operations.TransportOperation
    public void execute() throws IOException {
        Logger.d("Writing a control packet v2 with transactionId %d on stream %d %s", Integer.valueOf(this.transactionId), Integer.valueOf(this.stream), this.transport.getAccessory());
        DataSink dataSink = new DataSink(this.transport.sink());
        Logger.v("Writing a v2 transport packet {");
        dataSink.writeBits(this.stream, 4);
        Logger.v(" - stream=%d", Integer.valueOf(this.stream));
        dataSink.writeBits(this.transactionId, 4);
        Logger.v(" - transactionId=%d", Integer.valueOf(this.transactionId));
        dataSink.writeBits(1, 4);
        Logger.v(" - sequence=%d", 1);
        dataSink.writeBits(3, 2);
        Logger.v(" - transactionType=%d", 3);
        int i = this.errorCode == 0 ? 1 : 0;
        dataSink.writeBit(i);
        Logger.v(" - acknowledgement=%d", Integer.valueOf(i));
        dataSink.writeBit(0);
        Logger.v(" - extendedLength=%d", 0);
        dataSink.write(2);
        Logger.v(" - length=%d", 2);
        dataSink.write(this.controlType);
        Logger.v(" - command=%d", Integer.valueOf(this.controlType));
        dataSink.write(this.errorCode);
        Logger.v(" - errorCode=%d", Integer.valueOf(this.errorCode));
        Logger.v(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }

    @Override // com.amazon.alexa.accessory.transport.operations.TransportOperation
    public String getKey() {
        return this.key;
    }

    @Override // com.amazon.alexa.accessory.transport.operations.TransportOperation
    public TransportPriority getPriority() {
        return this.priority;
    }

    private V2ControlPacketTransportOperation(Builder builder) {
        this.transport = builder.transport;
        this.stream = builder.stream;
        this.transactionId = builder.transactionId;
        this.controlType = builder.controlType;
        this.errorCode = builder.errorCode;
        this.key = builder.key;
        this.priority = builder.priority;
    }
}
