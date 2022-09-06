package com.amazon.alexa.accessory.transport.codecs.V3;

import com.amazon.alexa.accessory.AccessoryTransport;
import com.amazon.alexa.accessory.internal.io.BufferPool;
import com.amazon.alexa.accessory.internal.util.IOUtils;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.io.Buffer;
import com.amazon.alexa.accessory.io.DataSink;
import com.amazon.alexa.accessory.io.SizedSource;
import com.amazon.alexa.accessory.metrics.MetricsConstants;
import com.amazon.alexa.accessory.transport.TransportPriority;
import com.amazon.alexa.accessory.transport.operations.TransportOperation;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import java.io.IOException;
import java.util.Locale;
/* loaded from: classes6.dex */
public final class V3PacketTransportOperation implements TransportOperation {
    public static final int AUTHENTICATED_TRANSACTION_BIT_MASK = 2;
    private static final int LENGTH_MAXIMUM_SIZE_BYTES = 255;
    private static final int PAYLOAD_MAXIMUM_SIZE_BYTES = 65535;
    private final SizedSource data;
    private final boolean isAuthenticated;
    private final String key;
    private final TransportPriority priority;
    private final int sequence;
    private final int stream;
    private final int totalPackets;
    private final int totalSize;
    private final int transactionId;
    private final V3TransactionType transactionType;
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
        private V3TransactionType transactionType;
        private AccessoryTransport transport;
        private int totalPackets = 1;
        private int sequence = 1;
        private boolean isAuthenticated = false;

        Builder() {
        }

        public Builder authenticated(boolean z) {
            this.isAuthenticated = z;
            return this;
        }

        public V3PacketTransportOperation build() {
            Preconditions.notNull(this.key, "key");
            Preconditions.notNull(this.transport, "transport");
            Preconditions.precondition(this.isStreamSet, "stream is not set");
            Preconditions.precondition(this.isTransactionIdSet, "transaction id is not set");
            Preconditions.precondition(this.isTotalSizeSet, "total size is not set");
            Preconditions.notNull(this.priority, "priority");
            Preconditions.notNull(this.data, "data");
            Preconditions.notNull(this.transactionType, MetricsConstants.Connection.TRANSACTION_TYPE);
            Preconditions.precondition(this.transactionType != V3TransactionType.CONTROL_PACKET, MetricsConstants.Connection.TRANSACTION_TYPE);
            Preconditions.precondition(!this.isAuthenticated, "authenticated control packets not yet supported");
            return new V3PacketTransportOperation(this);
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

        public Builder transactionType(V3TransactionType v3TransactionType) {
            this.transactionType = v3TransactionType;
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
        int i = 2;
        Logger.d("Writing a v3 transport packet: stream=%d, transaction=%d, sequence=%d, packetType=%s, packetLength=%d, totalLength=%d", Integer.valueOf(this.stream), Integer.valueOf(this.transactionId), Integer.valueOf(this.sequence), this.transactionType.toString(), Integer.valueOf(this.data.size()), Integer.valueOf(this.totalSize));
        DataSink dataSink = new DataSink(this.transport.sink());
        Logger.v("Writing a v3 transport packet {");
        dataSink.writeBits(this.stream, 4);
        Logger.v(" - stream=%d", Integer.valueOf(this.stream));
        dataSink.writeBits(this.transactionId, 4);
        Logger.v(" - transactionId=%d", Integer.valueOf(this.transactionId));
        dataSink.writeBits(this.sequence, 4);
        Logger.v(" - sequence=%d", Integer.valueOf(this.sequence));
        int value = this.transactionType.getValue();
        dataSink.writeBits(value, 2);
        Logger.v(" - transactionType=%d", Integer.valueOf(value));
        if (this.data.size() <= 65535) {
            dataSink.writeBit(0);
            Logger.v(" - acknowledgement=%d", 0);
            boolean z = this.data.size() > 255;
            int i2 = z ? 1 : 0;
            int i3 = z ? 1 : 0;
            dataSink.writeBit(i2);
            Logger.v(" - extendedLength=%b", Boolean.valueOf(z));
            int i4 = 16;
            if (V3TransactionType.FIRST_PACKET == this.transactionType) {
                if (!this.isAuthenticated) {
                    i = 0;
                }
                int i5 = i | 0;
                dataSink.write(i5);
                Logger.v(" - featureFlags=%d", Integer.valueOf(i5));
                dataSink.writeBits(this.totalSize, 16);
                Logger.v(" - totalLength=%d", Integer.valueOf(this.totalSize));
            }
            int size = this.data.size();
            if (!z) {
                i4 = 8;
            }
            dataSink.writeBits(size, i4);
            Logger.v(" - length=%d", Integer.valueOf(this.data.size()));
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

    private V3PacketTransportOperation(Builder builder) {
        this.transactionId = builder.transactionId;
        this.totalSize = builder.totalSize;
        this.totalPackets = builder.totalPackets;
        this.sequence = builder.sequence;
        this.stream = builder.stream;
        this.transport = builder.transport;
        this.priority = builder.priority;
        this.key = builder.key;
        this.isAuthenticated = builder.isAuthenticated;
        this.transactionType = builder.transactionType;
        this.data = builder.data;
    }
}
