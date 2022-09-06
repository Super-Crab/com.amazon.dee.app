package com.amazon.alexa.accessory.transport.codecs.V2;

import com.amazon.alexa.accessory.AccessoryTransport;
import com.amazon.alexa.accessory.internal.io.BufferPool;
import com.amazon.alexa.accessory.internal.util.IOUtils;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.io.Buffer;
import com.amazon.alexa.accessory.io.DataSink;
import com.amazon.alexa.accessory.io.SizedSource;
import com.amazon.alexa.accessory.transport.TransportPriority;
import com.amazon.alexa.accessory.transport.operations.TransportOperation;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import java.io.IOException;
import java.util.Locale;
/* loaded from: classes6.dex */
public final class V2PacketTransportOperation implements TransportOperation {
    private static final int LENGTH_MAXIMUM_SIZE = 255;
    private static final int PAYLOAD_MAXIMUM_SIZE = 65535;
    private static final int TRANSACTION_TYPE_FINAL = 2;
    private static final int TRANSACTION_TYPE_FIRST = 0;
    private static final int TRANSACTION_TYPE_NORMAL = 1;
    private final SizedSource data;
    private final String key;
    private final TransportPriority priority;
    private final int sequence;
    private final int stream;
    private final int totalPackets;
    private final int totalSize;
    private final int transactionId;
    private final AccessoryTransport transport;

    /* loaded from: classes6.dex */
    public static final class Builder {
        private SizedSource data;
        private boolean isStreamSet;
        private boolean isTotalSizeSet;
        private boolean isTransactionIdSet;
        private String key;
        private TransportPriority priority;
        private int stream;
        private int totalSize;
        private int transactionId;
        private AccessoryTransport transport;
        private int totalPackets = 1;
        private int sequence = 1;

        Builder() {
        }

        public V2PacketTransportOperation build() {
            Preconditions.notNull(this.key, "key");
            Preconditions.notNull(this.transport, "transport");
            Preconditions.precondition(this.isStreamSet, "stream is not set");
            Preconditions.precondition(this.isTransactionIdSet, "transaction id is not set");
            Preconditions.precondition(this.isTotalSizeSet, "total size is not set");
            Preconditions.notNull(this.priority, "priority");
            Preconditions.notNull(this.data, "data");
            return new V2PacketTransportOperation(this);
        }

        public Builder data(SizedSource sizedSource) {
            this.data = sizedSource;
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

        public Builder sequence(int i) {
            this.sequence = i;
            return this;
        }

        public Builder stream(int i) {
            this.stream = i;
            this.isStreamSet = true;
            return this;
        }

        public Builder totalPackets(int i) {
            this.totalPackets = i;
            return this;
        }

        public Builder totalSize(int i) {
            this.totalSize = i;
            this.isTotalSizeSet = true;
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

    private int getTransactionType(int i) {
        if (i == 1) {
            return 0;
        }
        return i == this.totalPackets ? 2 : 1;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    @Override // com.amazon.alexa.accessory.transport.operations.TransportOperation
    public void execute() throws IOException {
        Logger.d("Writing a packet v2 (%d bytes), sequence %d and transactionId %d on stream %d (%s)", Integer.valueOf(this.data.size()), Integer.valueOf(this.sequence), Integer.valueOf(this.transactionId), Integer.valueOf(this.stream), this.transport.getAccessory());
        DataSink dataSink = new DataSink(this.transport.sink());
        Logger.v("Writing a v2 transport packet {");
        dataSink.writeBits(this.stream, 4);
        Logger.v(" - stream=%d", Integer.valueOf(this.stream));
        dataSink.writeBits(this.transactionId, 4);
        Logger.v(" - transactionId=%d", Integer.valueOf(this.transactionId));
        dataSink.writeBits(this.sequence, 4);
        Logger.v(" - sequence=%d", Integer.valueOf(this.sequence));
        int transactionType = getTransactionType(this.sequence);
        dataSink.writeBits(transactionType, 2);
        Logger.v(" - transactionType=%d", Integer.valueOf(transactionType));
        if (this.data.size() <= 65535) {
            dataSink.writeBit(0);
            Logger.v(" - acknowledgement=%d", 0);
            boolean z = this.data.size() > 255;
            int i = z ? 1 : 0;
            int i2 = z ? 1 : 0;
            dataSink.writeBit(i);
            Logger.v(" - extendedLength=%b", Boolean.valueOf(z));
            dataSink.writeBits(this.data.size(), z ? 16 : 8);
            Logger.v(" - length=%d", Integer.valueOf(this.data.size()));
            if (this.sequence == 1) {
                dataSink.writeBits(this.totalSize, 16);
                Logger.v(" - totalLength=%d", Integer.valueOf(this.totalSize));
            }
            this.data.reset();
            if (Logger.shouldLog(Logger.Level.VERBOSE)) {
                BufferPool shared = BufferPool.shared();
                Buffer request = shared.request(this.data.size());
                IOUtils.transfer(this.data, request);
                Logger.v(" - payload:", request.data(), 0, request.size());
                Logger.v(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
                IOUtils.transfer(request, dataSink);
                shared.recycle(request);
            } else {
                dataSink.write(this.data);
            }
            IOUtils.closeQuietly(this.data);
            return;
        }
        throw new IOException(String.format(Locale.US, "Exceeded maximum payload size %d bytes. Maximum is %d bytes", Integer.valueOf(this.data.size()), 65535));
    }

    @Override // com.amazon.alexa.accessory.transport.operations.TransportOperation
    public String getKey() {
        return this.key;
    }

    @Override // com.amazon.alexa.accessory.transport.operations.TransportOperation
    public TransportPriority getPriority() {
        return this.priority;
    }

    private V2PacketTransportOperation(Builder builder) {
        this.transactionId = builder.transactionId;
        this.totalSize = builder.totalSize;
        this.totalPackets = builder.totalPackets;
        this.sequence = builder.sequence;
        this.stream = builder.stream;
        this.transport = builder.transport;
        this.priority = builder.priority;
        this.key = builder.key;
        this.data = builder.data;
    }
}
