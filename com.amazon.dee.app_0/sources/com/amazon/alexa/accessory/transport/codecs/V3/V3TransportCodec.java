package com.amazon.alexa.accessory.transport.codecs.V3;

import android.util.SparseArray;
import com.amazon.alexa.accessory.AccessoryTransport;
import com.amazon.alexa.accessory.internal.util.IOUtils;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.io.ByteArraySource;
import com.amazon.alexa.accessory.io.DataSource;
import com.amazon.alexa.accessory.io.SizedSource;
import com.amazon.alexa.accessory.transport.TransportData;
import com.amazon.alexa.accessory.transport.TransportFeature;
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
import com.google.common.collect.Sets;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;
/* loaded from: classes6.dex */
public final class V3TransportCodec implements TransportCodec {
    public static final int ENCRYPTED_TRANSACTION_BIT_MASK = 1;
    private static final int MAXIMUM_SEQUENCE = 15;
    private static final int MAXIMUM_TRANSACTION_ID = 15;
    private static final int MINIMUM_SEQUENCE = 0;
    private static final Set<TransportFeature> SUPPORTED = Sets.immutableEnumSet(TransportFeature.AUTHENTICATION, TransportFeature.ENCRYPTION, TransportFeature.REQUIRE_RESPONSE);
    private final Set<TransportFeature> enabledFeatures;
    private final int maxTransactionSizeBytes;
    private final int packetFragmentSizeBytes;
    private final V3TransactionDataDecryptor payloadDecryptor;
    private int transactionId;
    private final SparseArray<V3Transaction> transactions;
    private final AccessoryTransport transport;

    /* renamed from: com.amazon.alexa.accessory.transport.codecs.V3.V3TransportCodec$1  reason: invalid class name */
    /* loaded from: classes6.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$alexa$accessory$transport$codecs$V3$V3TransactionType = new int[V3TransactionType.values().length];

        static {
            try {
                $SwitchMap$com$amazon$alexa$accessory$transport$codecs$V3$V3TransactionType[V3TransactionType.CONTROL_PACKET.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$transport$codecs$V3$V3TransactionType[V3TransactionType.FIRST_PACKET.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$transport$codecs$V3$V3TransactionType[V3TransactionType.NORMAL_PACKET.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$transport$codecs$V3$V3TransactionType[V3TransactionType.FINAL_PACKET.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    /* loaded from: classes6.dex */
    public static final class InvalidControlPacketLengthException extends V3TransportException {
        private final int length;

        public InvalidControlPacketLengthException(int i) {
            super(GeneratedOutlineSupport1.outline49("Invalid length of a packet ", i));
            this.length = i;
        }

        public int getLength() {
            return this.length;
        }

        @Override // com.amazon.alexa.accessory.transport.codecs.V3.V3TransportCodec.V3TransportException
        public /* bridge */ /* synthetic */ int getTransportVersion() {
            return super.getTransportVersion();
        }
    }

    /* loaded from: classes6.dex */
    public static final class InvalidTransactionTypeException extends V3TransportException {
        private final int transactionType;

        public InvalidTransactionTypeException(int i) {
            super(GeneratedOutlineSupport1.outline49("Invalid transaction type ", i));
            this.transactionType = i;
        }

        public int getTransactionType() {
            return this.transactionType;
        }

        @Override // com.amazon.alexa.accessory.transport.codecs.V3.V3TransportCodec.V3TransportException
        public /* bridge */ /* synthetic */ int getTransportVersion() {
            return super.getTransportVersion();
        }
    }

    /* loaded from: classes6.dex */
    public static final class TransactionCollisionException extends V3TransportException {
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

        @Override // com.amazon.alexa.accessory.transport.codecs.V3.V3TransportCodec.V3TransportException
        public /* bridge */ /* synthetic */ int getTransportVersion() {
            return super.getTransportVersion();
        }
    }

    /* loaded from: classes6.dex */
    public static final class TransactionLengthMismatchException extends V3TransportException {
        private final int actualLengthBytes;
        private final int expectedLengthBytes;

        public TransactionLengthMismatchException(int i, int i2) {
            super(GeneratedOutlineSupport1.outline53("The sum of transaction packet-lengths does not add up to the transaction's total length; expected (bytes): ", i, "; actual (bytes): ", i2));
            this.expectedLengthBytes = i;
            this.actualLengthBytes = i2;
        }

        public int getActualLengthBytes() {
            return this.actualLengthBytes;
        }

        public int getExpectedLengthBytes() {
            return this.expectedLengthBytes;
        }

        @Override // com.amazon.alexa.accessory.transport.codecs.V3.V3TransportCodec.V3TransportException
        public /* bridge */ /* synthetic */ int getTransportVersion() {
            return super.getTransportVersion();
        }
    }

    /* loaded from: classes6.dex */
    public static final class UnexpectedTransactionException extends V3TransportException {
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

        @Override // com.amazon.alexa.accessory.transport.codecs.V3.V3TransportCodec.V3TransportException
        public /* bridge */ /* synthetic */ int getTransportVersion() {
            return super.getTransportVersion();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes6.dex */
    public static final class V3Transaction {
        public final byte[] bytes;
        public final int id;
        public final boolean isEncrypted;
        public final int key;
        public int length;
        public final boolean requireAck;
        public int sequence;
        public final int stream;
        public final int totalLength;

        public V3Transaction(int i, int i2, int i3, int i4, int i5, boolean z, boolean z2) {
            this.key = i;
            this.id = i2;
            this.stream = i3;
            this.totalLength = i4;
            this.sequence = i5;
            this.requireAck = z;
            this.isEncrypted = z2;
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

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes6.dex */
    public static abstract class V3TransportException extends IOException {
        /* JADX INFO: Access modifiers changed from: protected */
        public V3TransportException(String str) {
            super(str);
        }

        public int getTransportVersion() {
            return 3;
        }
    }

    public V3TransportCodec(AccessoryTransport accessoryTransport, int i, int i2, Set<TransportFeature> set, V3TransactionDataDecryptor v3TransactionDataDecryptor) {
        Preconditions.notNull(accessoryTransport, "transport");
        Preconditions.notNull(set, "requiredToSupport");
        Preconditions.notNull(v3TransactionDataDecryptor, "payloadDecryptor");
        boolean z = true;
        Preconditions.precondition(i > 0, "maxTransactionSizeBytes");
        Preconditions.precondition(i2 > 0, "packetFragmentSizeBytes");
        Preconditions.precondition(i < i2 ? false : z, "packetFragmentSizeBytes");
        Preconditions.precondition(TransportFeature.isSufficient(set, SUPPORTED), "requiredToSupport");
        this.transport = accessoryTransport;
        this.maxTransactionSizeBytes = i;
        this.packetFragmentSizeBytes = i2;
        this.payloadDecryptor = v3TransactionDataDecryptor;
        this.transactions = new SparseArray<>();
        this.enabledFeatures = set;
    }

    private static boolean addFinalPacketSourceToTransaction(DataSource dataSource, V3Transaction v3Transaction, int i) throws IOException {
        int min = Math.min(i, v3Transaction.totalLength - v3Transaction.length);
        int i2 = i - min;
        if (i2 > 0) {
            i = min;
        }
        addSourceToTransaction(dataSource, v3Transaction, i);
        if (i2 > 0) {
            byte[] bArr = new byte[i2];
            dataSource.get(bArr);
            Logger.v("Discarding excess bytes for transaction in the final packet: %s", bArr);
            return false;
        }
        return v3Transaction.isCompleted();
    }

    private static void addSourceToTransaction(DataSource dataSource, V3Transaction v3Transaction, int i) throws IOException {
        int i2 = v3Transaction.length;
        int i3 = i2 + i;
        int i4 = v3Transaction.totalLength;
        if (i3 <= i4) {
            if (Logger.shouldLog(Logger.Level.VERBOSE)) {
                byte[] bArr = new byte[i];
                dataSource.get(bArr, 0, i);
                Logger.v(" - payload", bArr, 0, bArr.length);
                v3Transaction.append(new DataSource(new ByteArraySource(bArr)), i);
                return;
            }
            v3Transaction.append(dataSource, i);
            return;
        }
        throw new TransactionLengthMismatchException(i4, i2 + i);
    }

    private V3Transaction decodeTransaction(DataSource dataSource, TransportCodec.OperationSequenceEncoder operationSequenceEncoder) throws IOException {
        Logger.v("Waiting to decode transaction v3 (%s)", this.transport.getAccessory());
        int bits = dataSource.getBits(4);
        Logger.v("Decoding v3 transport transaction {");
        Logger.v(" - stream=%d", Integer.valueOf(bits));
        int bits2 = dataSource.getBits(4);
        Logger.v(" - transactionId=%d", Integer.valueOf(bits2));
        int bits3 = dataSource.getBits(4);
        Logger.v(" - sequence=%d", Integer.valueOf(bits3));
        int bits4 = dataSource.getBits(2);
        Logger.v(" - transactionType=%d", Integer.valueOf(bits4));
        V3TransactionType fromValue = V3TransactionType.fromValue(bits4);
        int ordinal = fromValue.ordinal();
        if (ordinal != 0) {
            if (ordinal == 1 || ordinal == 2) {
                V3Transaction handleContinuousPacket = handleContinuousPacket(dataSource, bits, bits2, bits3, fromValue, operationSequenceEncoder);
                return handleContinuousPacket == null ? decodeTransaction(dataSource, operationSequenceEncoder) : handleContinuousPacket;
            } else if (ordinal == 3) {
                handleControlPacket(dataSource, bits, bits2);
                return decodeTransaction(dataSource, operationSequenceEncoder);
            } else {
                throw new InvalidTransactionTypeException(bits4);
            }
        }
        return handleFirstPacket(dataSource, bits, bits2, bits3);
    }

    private static int divideWithRemainder(int i, int i2) {
        int i3 = i / i2;
        return i % i2 != 0 ? i3 + 1 : i3;
    }

    private V3Transaction handleContinuousPacket(DataSource dataSource, int i, int i2, int i3, V3TransactionType v3TransactionType, TransportCodec.OperationSequenceEncoder operationSequenceEncoder) throws IOException {
        int makeIdentifier = makeIdentifier(i, i2);
        V3Transaction v3Transaction = this.transactions.get(makeIdentifier);
        if (v3Transaction != null) {
            Logger.v(" - acknowledgement=%d", Integer.valueOf(dataSource.getBit()));
            boolean z = dataSource.getBit() != 0;
            Logger.v(" - extendedLength=%b", Boolean.valueOf(z));
            int bits = dataSource.getBits(z ? 16 : 8);
            Logger.v(" - length=%d", Integer.valueOf(bits));
            if (bits >= 0) {
                if (V3TransactionType.FINAL_PACKET == v3TransactionType) {
                    int i4 = v3Transaction.length;
                    if (!addFinalPacketSourceToTransaction(dataSource, v3Transaction, bits)) {
                        Logger.d("Aborting transaction %d on stream %d. Transaction length mismatch. Expected: %d, Actual: %d", Integer.valueOf(i2), Integer.valueOf(i), Integer.valueOf(v3Transaction.totalLength), Integer.valueOf(i4 + bits));
                        this.transactions.remove(makeIdentifier);
                        operationSequenceEncoder.encoded(makeTransactionAbort(v3Transaction, 4));
                        return null;
                    }
                } else {
                    addSourceToTransaction(dataSource, v3Transaction, bits);
                }
                Logger.v(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
                int i5 = v3Transaction.sequence + 1;
                v3Transaction.sequence = i5;
                if (i5 > 15) {
                    v3Transaction.sequence = 0;
                }
                if (v3Transaction.sequence == i3) {
                    return v3Transaction;
                }
                StringBuilder outline110 = GeneratedOutlineSupport1.outline110("Aborting transaction ", i2, " on a stream ", i, ". Invalid sequence number ");
                outline110.append(i3);
                Logger.d(outline110.toString());
                this.transactions.remove(makeIdentifier);
                operationSequenceEncoder.encoded(makeTransactionAbort(v3Transaction, 3));
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
        Logger.v(" - encrypted=%b", Boolean.valueOf((dataSource.get() & 1) != 0));
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

    private V3Transaction handleFirstPacket(DataSource dataSource, int i, int i2, int i3) throws IOException {
        Logger.v(" - Transaction is a first packet.... continuing decoding");
        int makeIdentifier = makeIdentifier(i, i2);
        if (this.transactions.get(makeIdentifier) == null) {
            boolean z = dataSource.getBit() != 0;
            Logger.v(" - requireAck=%b", Boolean.valueOf(z));
            boolean z2 = dataSource.getBit() != 0;
            Logger.v(" - extendedLength=%b", Boolean.valueOf(z2));
            boolean z3 = (dataSource.get() & 1) == 1;
            Logger.v(" - encrypted=%b", Boolean.valueOf(z3));
            int word = dataSource.getWord();
            Logger.v(" - totalLength=%d", Integer.valueOf(word));
            if (word >= 0) {
                int bits = dataSource.getBits(z2 ? 16 : 8);
                Logger.v(" - length=%d", Integer.valueOf(bits));
                if (bits >= 0) {
                    V3Transaction v3Transaction = new V3Transaction(makeIdentifier, i2, i, word, i3, z, z3);
                    addSourceToTransaction(dataSource, v3Transaction, bits);
                    this.transactions.put(makeIdentifier, v3Transaction);
                    Logger.v(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
                    return v3Transaction;
                }
                throw new RuntimeException(GeneratedOutlineSupport1.outline49("A negative int was parsed from at most 16 bits: ", bits));
            }
            throw new RuntimeException(GeneratedOutlineSupport1.outline49("A negative int was parsed from 8 bits: ", word));
        }
        throw new TransactionCollisionException(i2, i);
    }

    private static int makeIdentifier(int i, int i2) {
        return ((i << 4) & 240) | (i2 & 15);
    }

    private TransportOperationSequence makeTransactionAbort(V3Transaction v3Transaction, int i) {
        return makeTransactionControlResponse(v3Transaction, false, i);
    }

    private TransportOperationSequence makeTransactionAck(V3Transaction v3Transaction) {
        return makeTransactionControlResponse(v3Transaction, true, 0);
    }

    private TransportOperationSequence makeTransactionControlResponse(V3Transaction v3Transaction, boolean z, int i) {
        return new SingleTransportOperationSequence(V3ControlPacketTransportOperation.newBuilder().key(UUID.randomUUID().toString()).transport(this.transport).stream(v3Transaction.stream).transactionId(v3Transaction.id).controlType(z ? 1 : 2).errorCode(i).priority(TransportPriority.HIGH).build());
    }

    @Override // com.amazon.alexa.accessory.transport.codecs.TransportCodec
    public TransportData decode(TransportCodec.OperationSequenceEncoder operationSequenceEncoder) throws IOException {
        V3Transaction decodeTransaction;
        ByteArraySource byteArraySource;
        DataSource dataSource = new DataSource(this.transport.source());
        do {
            decodeTransaction = decodeTransaction(dataSource, operationSequenceEncoder);
            Logger.d("Decoded a transaction v3. transactionId=%d, stream=%d, sequence=%d, length=%d, totalLength=%d", Integer.valueOf(decodeTransaction.id), Integer.valueOf(decodeTransaction.stream), Integer.valueOf(decodeTransaction.sequence), Integer.valueOf(decodeTransaction.length), Integer.valueOf(decodeTransaction.totalLength));
        } while (!decodeTransaction.isCompleted());
        if (!decodeTransaction.isEncrypted) {
            byteArraySource = new ByteArraySource(decodeTransaction.bytes, 0, decodeTransaction.length);
            Logger.d("Received a complete transport data v3 (%d bytes) stream %d (%s)", Integer.valueOf(decodeTransaction.length), Integer.valueOf(decodeTransaction.stream), this.transport.getAccessory());
        } else {
            try {
                byteArraySource = new ByteArraySource(this.payloadDecryptor.decryptDataBlob(decodeTransaction.bytes));
                Logger.d("Received a complete transport data v3 (%d encrypted bytes, %d bytes) stream %d (%s)", Integer.valueOf(decodeTransaction.length), Integer.valueOf(byteArraySource.size()), Integer.valueOf(decodeTransaction.stream), this.transport.getAccessory());
            } catch (V3TransportException e) {
                operationSequenceEncoder.encoded(makeTransactionAbort(decodeTransaction, 2));
                Logger.e("Failed to decrypt v3 transport data. transactionId=%d, stream=%d, sequence=%d, length=%d, totalLength=%d", e, Integer.valueOf(decodeTransaction.id), Integer.valueOf(decodeTransaction.stream), Integer.valueOf(decodeTransaction.sequence), Integer.valueOf(decodeTransaction.length), Integer.valueOf(decodeTransaction.totalLength));
                throw e;
            }
        }
        if (decodeTransaction.requireAck) {
            operationSequenceEncoder.encoded(makeTransactionAck(decodeTransaction));
        }
        this.transactions.remove(decodeTransaction.key);
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
                int min = Math.min(length, this.maxTransactionSizeBytes);
                arrayList.addAll(encode(transportTransaction.getKey(), sourceToByteArray, sourceToByteArray.length - length, min, transportTransaction.getStream(), divideWithRemainder(min, this.packetFragmentSizeBytes), false, transportTransaction.getPriority()));
                length -= min;
                int i = this.transactionId + 1;
                this.transactionId = i;
                if (i > 15) {
                    this.transactionId = 0;
                }
            }
        }
        if (transportTransaction.isCommit()) {
            arrayList.add(new FlushTransportOperation(transportTransaction.getKey(), this.transport, transportTransaction.getPriority()));
        }
        return new LinearTransportOperationSequence(transportTransaction.getKey(), transportTransaction.getPriority(), arrayList);
    }

    private List<? extends TransportOperation> encode(String str, byte[] bArr, int i, int i2, int i3, int i4, boolean z, TransportPriority transportPriority) {
        ArrayList arrayList = new ArrayList(i4);
        V3TransactionType v3TransactionType = V3TransactionType.FIRST_PACKET;
        int i5 = 0;
        int i6 = i2;
        while (i6 > 0) {
            int min = Math.min(i6, this.packetFragmentSizeBytes);
            arrayList.add(V3PacketTransportOperation.newBuilder().key(str).transport(this.transport).data(new ByteArraySource(bArr, (i + i2) - i6, min)).stream(i3).transactionId(this.transactionId).sequence(i5).transactionType(v3TransactionType).totalPackets(i4).totalSize(i2).authenticated(z).priority(transportPriority).build());
            i6 -= min;
            i5++;
            if (i5 > 15) {
                i5 = 0;
            }
            v3TransactionType = i6 <= this.packetFragmentSizeBytes ? V3TransactionType.FINAL_PACKET : V3TransactionType.NORMAL_PACKET;
        }
        return arrayList;
    }
}
