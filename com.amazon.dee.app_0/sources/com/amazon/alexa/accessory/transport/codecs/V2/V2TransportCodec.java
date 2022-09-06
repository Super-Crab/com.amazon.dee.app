package com.amazon.alexa.accessory.transport.codecs.V2;

import android.util.SparseArray;
import com.amazon.alexa.accessory.AccessoryTransport;
import com.amazon.alexa.accessory.internal.util.IOUtils;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.io.ByteArraySource;
import com.amazon.alexa.accessory.io.DataSource;
import com.amazon.alexa.accessory.io.SizedSource;
import com.amazon.alexa.accessory.transport.TransportData;
import com.amazon.alexa.accessory.transport.TransportPriority;
import com.amazon.alexa.accessory.transport.TransportTransaction;
import com.amazon.alexa.accessory.transport.codecs.TransportCodec;
import com.amazon.alexa.accessory.transport.operations.FlushTransportOperation;
import com.amazon.alexa.accessory.transport.operations.LinearTransportOperationSequence;
import com.amazon.alexa.accessory.transport.operations.SingleTransportOperationSequence;
import com.amazon.alexa.accessory.transport.operations.TransportOperation;
import com.amazon.alexa.accessory.transport.operations.TransportOperationSequence;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;
/* loaded from: classes6.dex */
public final class V2TransportCodec implements TransportCodec {
    private static final int MAXIMUM_SEQUENCE = 7;
    private static final int MAXIMUM_TRANSACTION_ID = 7;
    public static final int TRANSACTION_TYPE_CONTROL_PACKET = 3;
    public static final int TRANSACTION_TYPE_FINAL_PACKET = 2;
    public static final int TRANSACTION_TYPE_FIRST_PACKET = 0;
    public static final int TRANSACTION_TYPE_NORMAL_PACKET = 1;
    private final int maxTransactionSize;
    private final int packetFragmentSize;
    private int transactionId;
    private final SparseArray<V2Transaction> transactions;
    private final AccessoryTransport transport;

    /* loaded from: classes6.dex */
    public static final class InvalidControlPacketLengthException extends V2TransportException {
        private final int length;

        public InvalidControlPacketLengthException(int i) {
            super(GeneratedOutlineSupport1.outline49("Invalid length of a packet ", i));
            this.length = i;
        }

        public int getLength() {
            return this.length;
        }

        @Override // com.amazon.alexa.accessory.transport.codecs.V2.V2TransportCodec.V2TransportException
        public /* bridge */ /* synthetic */ int getTransportVersion() {
            return super.getTransportVersion();
        }
    }

    /* loaded from: classes6.dex */
    public static final class InvalidTransactionTypeException extends V2TransportException {
        private final int transactionType;

        public InvalidTransactionTypeException(int i) {
            super(GeneratedOutlineSupport1.outline49("Invalid transaction type ", i));
            this.transactionType = i;
        }

        public int getTransactionType() {
            return this.transactionType;
        }

        @Override // com.amazon.alexa.accessory.transport.codecs.V2.V2TransportCodec.V2TransportException
        public /* bridge */ /* synthetic */ int getTransportVersion() {
            return super.getTransportVersion();
        }
    }

    /* loaded from: classes6.dex */
    public static final class TransactionCollisionException extends V2TransportException {
        private final int stream;
        private final int transactionId;

        public TransactionCollisionException(int i, int i2) {
            super(GeneratedOutlineSupport1.outline54("Detected a transaction collision. Transaction with id ", i, " on a stream ", i2, " already exists!"));
            this.transactionId = i;
            this.stream = i2;
        }

        public int getStream() {
            return this.stream;
        }

        public int getTransactionId() {
            return this.transactionId;
        }

        @Override // com.amazon.alexa.accessory.transport.codecs.V2.V2TransportCodec.V2TransportException
        public /* bridge */ /* synthetic */ int getTransportVersion() {
            return super.getTransportVersion();
        }
    }

    /* loaded from: classes6.dex */
    public static final class UnexpectedTransactionException extends V2TransportException {
        private final int stream;
        private final int transactionId;

        public UnexpectedTransactionException(int i, int i2) {
            super(GeneratedOutlineSupport1.outline53("Invalid packet. There is no active transaction with id ", i, " on a stream ", i2));
            this.transactionId = i;
            this.stream = i2;
        }

        public int getStream() {
            return this.stream;
        }

        public int getTransactionId() {
            return this.transactionId;
        }

        @Override // com.amazon.alexa.accessory.transport.codecs.V2.V2TransportCodec.V2TransportException
        public /* bridge */ /* synthetic */ int getTransportVersion() {
            return super.getTransportVersion();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes6.dex */
    public final class V2Transaction {
        public final byte[] bytes;
        public final int id;
        public final int key;
        public int length;
        public final boolean requireAck;
        public int sequence;
        public final int stream;
        public final int totalLength;

        public V2Transaction(int i, int i2, int i3, int i4, int i5, boolean z) {
            this.key = i;
            this.id = i2;
            this.stream = i3;
            this.totalLength = i4;
            this.sequence = i5;
            this.requireAck = z;
            this.bytes = new byte[i4];
        }

        public void append(DataSource dataSource, int i) throws IOException {
            dataSource.get(this.bytes, this.length, i);
            this.length += i;
        }

        public boolean isCompleted() {
            return this.length == this.totalLength;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes6.dex */
    public static abstract class V2TransportException extends IOException {
        public int getTransportVersion() {
            return 2;
        }

        private V2TransportException(String str) {
            super(str);
        }
    }

    public V2TransportCodec(AccessoryTransport accessoryTransport, int i, int i2) {
        Preconditions.notNull(accessoryTransport, "transport");
        this.transport = accessoryTransport;
        this.maxTransactionSize = i;
        this.packetFragmentSize = i2;
        this.transactions = new SparseArray<>();
    }

    private void addSourceToTransaction(DataSource dataSource, V2Transaction v2Transaction, int i) throws IOException {
        if (Logger.shouldLog(Logger.Level.VERBOSE)) {
            byte[] bArr = new byte[i];
            dataSource.get(bArr, 0, i);
            Logger.v(" - payload", bArr, 0, bArr.length);
            v2Transaction.append(new DataSource(new ByteArraySource(bArr)), i);
            return;
        }
        v2Transaction.append(dataSource, i);
    }

    private V2Transaction decodeTransaction(DataSource dataSource, TransportCodec.OperationSequenceEncoder operationSequenceEncoder) throws IOException {
        Logger.v("Waiting to decode transaction v2 (%s)", this.transport.getAccessory());
        int bits = dataSource.getBits(4);
        Logger.v("Decoding v2 transport transaction {");
        Logger.v(" - stream=%d", Integer.valueOf(bits));
        int bits2 = dataSource.getBits(4);
        Logger.v(" - transactionId=%d", Integer.valueOf(bits2));
        int bits3 = dataSource.getBits(4);
        Logger.v(" - sequence=%d", Integer.valueOf(bits3));
        int bits4 = dataSource.getBits(2);
        Logger.v(" - transactionType=%d", Integer.valueOf(bits4));
        if (bits4 != 0) {
            if (bits4 == 1 || bits4 == 2) {
                V2Transaction handleContinuousPacket = handleContinuousPacket(dataSource, bits, bits2, bits3, operationSequenceEncoder);
                return handleContinuousPacket == null ? decodeTransaction(dataSource, operationSequenceEncoder) : handleContinuousPacket;
            } else if (bits4 == 3) {
                handleControlPacket(dataSource, bits, bits2);
                return decodeTransaction(dataSource, operationSequenceEncoder);
            } else {
                throw new InvalidTransactionTypeException(bits4);
            }
        }
        return handleFirstPacket(dataSource, bits, bits2, bits3);
    }

    private int divideWithRemainder(int i, int i2) {
        int i3 = i / i2;
        return i % i2 != 0 ? i3 + 1 : i3;
    }

    private V2Transaction handleContinuousPacket(DataSource dataSource, int i, int i2, int i3, TransportCodec.OperationSequenceEncoder operationSequenceEncoder) throws IOException {
        int makeIdentifier = makeIdentifier(i, i2);
        V2Transaction v2Transaction = this.transactions.get(makeIdentifier);
        if (v2Transaction != null) {
            Logger.v(" - acknowledgement=%d", Integer.valueOf(dataSource.getBit()));
            boolean z = dataSource.getBit() != 0;
            Logger.v(" - extendedLength=%b", Boolean.valueOf(z));
            int bits = dataSource.getBits(z ? 16 : 8);
            Logger.v(" - length=%d", Integer.valueOf(bits));
            if (bits >= 0) {
                addSourceToTransaction(dataSource, v2Transaction, bits);
                Logger.v(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
                int i4 = v2Transaction.sequence + 1;
                v2Transaction.sequence = i4;
                if (i4 == i3) {
                    return v2Transaction;
                }
                StringBuilder outline110 = GeneratedOutlineSupport1.outline110("Aborting transaction ", i2, " on a stream ", i, ". Invalid sequence number ");
                outline110.append(i3);
                Logger.d(outline110.toString());
                this.transactions.remove(makeIdentifier);
                operationSequenceEncoder.encoded(new SingleTransportOperationSequence(V2ControlPacketTransportOperation.newBuilder().key(UUID.randomUUID().toString()).transport(this.transport).stream(v2Transaction.stream).transactionId(i2).controlType(2).errorCode(3).priority(TransportPriority.HIGH).build()));
                return null;
            }
            throw new RuntimeException(GeneratedOutlineSupport1.outline49("A negative int was parsed from at most 16 bits: ", bits));
        }
        throw new UnexpectedTransactionException(i2, i);
    }

    private void handleControlPacket(DataSource dataSource, int i, int i2) throws IOException {
        Logger.v(" - Transaction is a control packet.... continuing decoding");
        Logger.v(" - acknowledgement=%d", Integer.valueOf(dataSource.getBit()));
        boolean z = dataSource.getBit() != 0;
        Logger.v(" - extendedLength=%b", Boolean.valueOf(z));
        int bits = dataSource.getBits(z ? 16 : 8);
        Logger.v(" - length=%b", Integer.valueOf(bits));
        if (bits >= 2) {
            int i3 = dataSource.get();
            Logger.v(" - controlType=%d", Integer.valueOf(i3));
            int i4 = dataSource.get();
            Logger.v(" - errorCode=%d", Integer.valueOf(i4));
            if (bits > 2) {
                byte[] bArr = new byte[bits - 2];
                dataSource.get(bArr);
                Logger.v("Discarding extra bytes in control packet: %s", bArr);
            }
            if (i3 == 2) {
                StringBuilder outline110 = GeneratedOutlineSupport1.outline110("Aborting transaction ", i2, " on a stream ", i, " with code ");
                outline110.append(i4);
                Logger.d(outline110.toString());
                this.transactions.remove(makeIdentifier(i, i2));
            }
            Logger.v(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
            return;
        }
        throw new InvalidControlPacketLengthException(bits);
    }

    private V2Transaction handleFirstPacket(DataSource dataSource, int i, int i2, int i3) throws IOException {
        Logger.v(" - Transaction is a first packet.... continuing decoding");
        int makeIdentifier = makeIdentifier(i, i2);
        if (this.transactions.get(makeIdentifier) == null) {
            boolean z = dataSource.getBit() != 0;
            Logger.v(" - requireAck=%b", Boolean.valueOf(z));
            boolean z2 = dataSource.getBit() != 0;
            Logger.v(" - extendedLength=%b", Boolean.valueOf(z2));
            int bits = dataSource.getBits(z2 ? 16 : 8);
            Logger.v(" - length=%d", Integer.valueOf(bits));
            if (bits >= 0) {
                int word = dataSource.getWord();
                Logger.v(" - totalLength=%d", Integer.valueOf(word));
                if (word >= 0) {
                    V2Transaction v2Transaction = new V2Transaction(makeIdentifier, i2, i, word, i3, z);
                    addSourceToTransaction(dataSource, v2Transaction, bits);
                    this.transactions.put(makeIdentifier, v2Transaction);
                    Logger.v(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
                    return v2Transaction;
                }
                throw new RuntimeException(GeneratedOutlineSupport1.outline49("A negative int was parsed from 8 bits: ", word));
            }
            throw new RuntimeException(GeneratedOutlineSupport1.outline49("A negative int was parsed from at most 16 bits: ", bits));
        }
        throw new TransactionCollisionException(i2, i);
    }

    private int makeIdentifier(int i, int i2) {
        return ((i << 4) & 240) | (i2 & 15);
    }

    private TransportOperationSequence makeTransactionAck(V2Transaction v2Transaction) {
        return new SingleTransportOperationSequence(V2ControlPacketTransportOperation.newBuilder().key(UUID.randomUUID().toString()).transport(this.transport).stream(v2Transaction.stream).transactionId(v2Transaction.id).controlType(1).errorCode(0).priority(TransportPriority.HIGH).build());
    }

    @Override // com.amazon.alexa.accessory.transport.codecs.TransportCodec
    public TransportData decode(TransportCodec.OperationSequenceEncoder operationSequenceEncoder) throws IOException {
        V2Transaction decodeTransaction;
        DataSource dataSource = new DataSource(this.transport.source());
        do {
            decodeTransaction = decodeTransaction(dataSource, operationSequenceEncoder);
            Logger.v("Decoded a transaction v2. stream=%d, sequence=%d, length=%d, totalLength=%d", Integer.valueOf(decodeTransaction.stream), Integer.valueOf(decodeTransaction.sequence), Integer.valueOf(decodeTransaction.length), Integer.valueOf(decodeTransaction.totalLength));
        } while (!decodeTransaction.isCompleted());
        if (decodeTransaction.requireAck) {
            operationSequenceEncoder.encoded(makeTransactionAck(decodeTransaction));
        }
        this.transactions.remove(decodeTransaction.key);
        ByteArraySource byteArraySource = new ByteArraySource(decodeTransaction.bytes, 0, decodeTransaction.length);
        Logger.d("Received a complete transport data v2 (%d bytes) stream %d (%s)", Integer.valueOf(decodeTransaction.length), Integer.valueOf(decodeTransaction.stream), this.transport.getAccessory());
        return new TransportData(byteArraySource, decodeTransaction.stream);
    }

    @Override // com.amazon.alexa.accessory.transport.codecs.TransportCodec
    public TransportOperationSequence encode(TransportTransaction transportTransaction) throws IOException {
        ArrayList arrayList = new ArrayList();
        if (transportTransaction.hasData()) {
            SizedSource data = transportTransaction.getData();
            data.reset();
            byte[] sourceToByteArray = IOUtils.sourceToByteArray(data);
            IOUtils.closeQuietly(data);
            int length = sourceToByteArray.length;
            while (length > 0) {
                int min = Math.min(length, this.maxTransactionSize);
                arrayList.addAll(encode(transportTransaction.getKey(), sourceToByteArray, sourceToByteArray.length - length, min, transportTransaction.getStream(), divideWithRemainder(min, this.packetFragmentSize), transportTransaction.getPriority()));
                length -= min;
                int i = this.transactionId + 1;
                this.transactionId = i;
                if (i > 7) {
                    this.transactionId = 0;
                }
            }
        }
        if (transportTransaction.isCommit()) {
            arrayList.add(new FlushTransportOperation(transportTransaction.getKey(), this.transport, transportTransaction.getPriority()));
        }
        return new LinearTransportOperationSequence(transportTransaction.getKey(), transportTransaction.getPriority(), arrayList);
    }

    private Collection<? extends TransportOperation> encode(String str, byte[] bArr, int i, int i2, int i3, int i4, TransportPriority transportPriority) {
        ArrayList arrayList = new ArrayList();
        int i5 = i2;
        while (true) {
            int i6 = 1;
            while (i5 > 0) {
                int min = Math.min(i5, this.packetFragmentSize);
                arrayList.add(V2PacketTransportOperation.newBuilder().key(str).transport(this.transport).data(new ByteArraySource(bArr, (i + i2) - i5, min)).stream(i3).transactionId(this.transactionId).sequence(i6).totalPackets(i4).totalSize(i2).priority(transportPriority).build());
                i5 -= min;
                i6++;
                if (i6 > 7) {
                    break;
                }
            }
            return arrayList;
        }
    }
}
