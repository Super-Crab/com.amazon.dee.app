package com.amazon.alexa.accessory.transport.codecs.muffin;

import com.amazon.alexa.accessory.AccessoryTransport;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.io.ByteArraySource;
import com.amazon.alexa.accessory.io.DataSource;
import com.amazon.alexa.accessory.transport.TransportData;
import com.amazon.alexa.accessory.transport.TransportTransaction;
import com.amazon.alexa.accessory.transport.codecs.TransportCodec;
import com.amazon.alexa.accessory.transport.operations.FlushTransportOperation;
import com.amazon.alexa.accessory.transport.operations.LinearTransportOperationSequence;
import com.amazon.alexa.accessory.transport.operations.TransportOperationSequence;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.util.ArrayList;
/* loaded from: classes6.dex */
public final class MuffinTransportCodec implements TransportCodec {
    private final AccessoryTransport transport;

    /* loaded from: classes6.dex */
    public static final class InvalidTransportFlagsException extends V1TransportException {
        private final int actualFlags;
        private final int expectedFlags;

        public InvalidTransportFlagsException(int i, int i2) {
            super(GeneratedOutlineSupport1.outline54("Flags must be set to ", i, ". Instead found ", i2, "!"));
            this.expectedFlags = i;
            this.actualFlags = i2;
        }

        public int getActualFlags() {
            return this.actualFlags;
        }

        public int getExpectedFlags() {
            return this.expectedFlags;
        }

        @Override // com.amazon.alexa.accessory.transport.codecs.muffin.MuffinTransportCodec.V1TransportException
        public /* bridge */ /* synthetic */ int getTransportVersion() {
            return super.getTransportVersion();
        }
    }

    /* loaded from: classes6.dex */
    public static final class InvalidTransportVersionException extends V1TransportException {
        private final int actualVersion;

        public InvalidTransportVersionException(int i) {
            super(GeneratedOutlineSupport1.outline52("Invalid transport header version ", i, ". Expected version 1!"));
            this.actualVersion = i;
        }

        public int getActualVersion() {
            return this.actualVersion;
        }

        @Override // com.amazon.alexa.accessory.transport.codecs.muffin.MuffinTransportCodec.V1TransportException
        public /* bridge */ /* synthetic */ int getTransportVersion() {
            return super.getTransportVersion();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes6.dex */
    public static abstract class V1TransportException extends IOException {
        public int getTransportVersion() {
            return 1;
        }

        private V1TransportException(String str) {
            super(str);
        }
    }

    public MuffinTransportCodec(AccessoryTransport accessoryTransport) {
        Preconditions.notNull(accessoryTransport, "transport");
        this.transport = accessoryTransport;
    }

    @Override // com.amazon.alexa.accessory.transport.codecs.TransportCodec
    public TransportData decode(TransportCodec.OperationSequenceEncoder operationSequenceEncoder) throws IOException {
        DataSource dataSource = new DataSource(this.transport.source());
        int bits = dataSource.getBits(4);
        Logger.v("Decoding v1 transport packet {");
        Logger.v(" - version=%d", Integer.valueOf(bits));
        if (bits == 1) {
            int bits2 = dataSource.getBits(5);
            Logger.v(" - stream=%d", Integer.valueOf(bits2));
            int bits3 = dataSource.getBits(6);
            Logger.v(" - flags=%d", Integer.valueOf(bits3));
            if (bits3 == 0) {
                boolean z = dataSource.getBit() != 0;
                Logger.v(" - extendedLength=%b", Boolean.valueOf(z));
                int bits4 = dataSource.getBits(z ? 16 : 8);
                Logger.v(" - length=%d", Integer.valueOf(bits4));
                if (bits4 >= 0) {
                    byte[] bArr = new byte[bits4];
                    dataSource.get(bArr);
                    Logger.v(" - payload", bArr, 0, bArr.length);
                    Logger.v(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
                    Logger.d("Received a packet v%d (%d bytes) stream %d (%s)", Integer.valueOf(bits), Integer.valueOf(bits4), Integer.valueOf(bits2), this.transport.getAccessory());
                    return new TransportData(new ByteArraySource(bArr), bits2);
                }
                throw new RuntimeException(GeneratedOutlineSupport1.outline49("A negative int was parsed from at most 16 bits: ", bits4));
            }
            throw new InvalidTransportFlagsException(0, bits3);
        }
        throw new InvalidTransportVersionException(bits);
    }

    @Override // com.amazon.alexa.accessory.transport.codecs.TransportCodec
    public TransportOperationSequence encode(TransportTransaction transportTransaction) throws IOException {
        ArrayList arrayList = new ArrayList();
        if (transportTransaction.hasData()) {
            arrayList.add(new MuffinPacketTransportOperation(transportTransaction.getKey(), this.transport, transportTransaction.getData(), transportTransaction.getStream(), transportTransaction.getPriority()));
        }
        if (transportTransaction.isCommit()) {
            arrayList.add(new FlushTransportOperation(transportTransaction.getKey(), this.transport, transportTransaction.getPriority()));
        }
        return new LinearTransportOperationSequence(transportTransaction.getKey(), transportTransaction.getPriority(), arrayList);
    }
}
