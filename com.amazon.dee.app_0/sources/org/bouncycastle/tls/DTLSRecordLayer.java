package org.bouncycastle.tls;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.SocketTimeoutException;
import org.bouncycastle.asn1.cmc.BodyPartID;
import org.bouncycastle.tls.crypto.TlsCipher;
import org.bouncycastle.tls.crypto.TlsEncodeResult;
import org.bouncycastle.tls.crypto.TlsNullNullCipher;
/* loaded from: classes5.dex */
class DTLSRecordLayer implements DatagramTransport {
    private static final int MAX_FRAGMENT_LENGTH = 16384;
    private static final int RECORD_HEADER_LENGTH = 13;
    private static final long RETRANSMIT_TIMEOUT = 240000;
    private static final long TCP_MSL = 120000;
    private final TlsContext context;
    private volatile boolean inConnection;
    private final TlsPeer peer;
    private volatile int plaintextLimit;
    private DTLSEpoch readEpoch;
    private final DatagramTransport transport;
    private DTLSEpoch writeEpoch;
    private final ByteQueue recordQueue = new ByteQueue();
    private final Object writeLock = new Object();
    private volatile boolean closed = false;
    private volatile boolean failed = false;
    private volatile ProtocolVersion readVersion = null;
    private volatile ProtocolVersion writeVersion = null;
    private DTLSHandshakeRetransmit retransmit = null;
    private DTLSEpoch retransmitEpoch = null;
    private Timeout retransmitTimeout = null;
    private TlsHeartbeat heartbeat = null;
    private boolean heartBeatResponder = false;
    private HeartbeatMessage heartbeatInFlight = null;
    private Timeout heartbeatTimeout = null;
    private int heartbeatResendMillis = -1;
    private Timeout heartbeatResendTimeout = null;
    private volatile boolean inHandshake = true;
    private DTLSEpoch currentEpoch = new DTLSEpoch(0, TlsNullNullCipher.INSTANCE);
    private DTLSEpoch pendingEpoch = null;

    /* JADX INFO: Access modifiers changed from: package-private */
    public DTLSRecordLayer(TlsContext tlsContext, TlsPeer tlsPeer, DatagramTransport datagramTransport) {
        this.context = tlsContext;
        this.peer = tlsPeer;
        this.transport = datagramTransport;
        DTLSEpoch dTLSEpoch = this.currentEpoch;
        this.readEpoch = dTLSEpoch;
        this.writeEpoch = dTLSEpoch;
        setPlaintextLimit(16384);
    }

    private void closeTransport() {
        if (!this.closed) {
            try {
                if (!this.failed) {
                    warn((short) 0, null);
                }
                this.transport.close();
            } catch (Exception unused) {
            }
            this.closed = true;
        }
    }

    private static long getMacSequenceNumber(int i, long j) {
        return ((i & BodyPartID.bodyIdMax) << 48) | j;
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x0049 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:25:0x004a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private int processRecord(int r22, byte[] r23, byte[] r24, int r25) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 490
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.tls.DTLSRecordLayer.processRecord(int, byte[], byte[], int):int");
    }

    private void raiseAlert(short s, short s2, String str, Throwable th) throws IOException {
        this.peer.notifyAlertRaised(s, s2, str, th);
        sendRecord((short) 21, new byte[]{(byte) s, (byte) s2}, 0, 2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static byte[] receiveClientHelloRecord(byte[] bArr, int i, int i2) throws IOException {
        if (i2 >= 13 && 22 == TlsUtils.readUint8(bArr, i + 0)) {
            if (!ProtocolVersion.DTLSv10.isEqualOrEarlierVersionOf(TlsUtils.readVersion(bArr, i + 1)) || TlsUtils.readUint16(bArr, i + 3) != 0) {
                return null;
            }
            int readUint16 = TlsUtils.readUint16(bArr, i + 11);
            if (i2 < readUint16 + 13 || readUint16 > 16384) {
                return null;
            }
            int i3 = i + 13;
            return TlsUtils.copyOfRangeExact(bArr, i3, readUint16 + i3);
        }
        return null;
    }

    private int receiveDatagram(byte[] bArr, int i, int i2, int i3) throws IOException {
        try {
            return this.transport.receive(bArr, i, i2, i3);
        } catch (SocketTimeoutException unused) {
            return -1;
        } catch (InterruptedIOException e) {
            e.bytesTransferred = 0;
            throw e;
        }
    }

    private int receiveRecord(byte[] bArr, int i, int i2, int i3) throws IOException {
        int i4;
        if (this.recordQueue.available() > 0) {
            if (this.recordQueue.available() >= 13) {
                byte[] bArr2 = new byte[2];
                this.recordQueue.read(bArr2, 0, 2, 11);
                i4 = TlsUtils.readUint16(bArr2, 0);
            } else {
                i4 = 0;
            }
            int min = Math.min(this.recordQueue.available(), i4 + 13);
            this.recordQueue.removeData(bArr, i, min, 0);
            return min;
        }
        int receiveDatagram = receiveDatagram(bArr, i, i2, i3);
        if (receiveDatagram < 13) {
            return receiveDatagram;
        }
        this.inConnection = true;
        int readUint16 = TlsUtils.readUint16(bArr, i + 11) + 13;
        if (receiveDatagram <= readUint16) {
            return receiveDatagram;
        }
        this.recordQueue.addData(bArr, i + readUint16, receiveDatagram - readUint16);
        return readUint16;
    }

    private void resetHeartbeat() {
        this.heartbeatInFlight = null;
        this.heartbeatResendMillis = -1;
        this.heartbeatResendTimeout = null;
        this.heartbeatTimeout = new Timeout(this.heartbeat.getIdleMillis());
    }

    private static void sendDatagram(DatagramSender datagramSender, byte[] bArr, int i, int i2) throws IOException {
        try {
            datagramSender.send(bArr, i, i2);
        } catch (InterruptedIOException e) {
            e.bytesTransferred = 0;
            throw e;
        }
    }

    private void sendHeartbeatMessage(HeartbeatMessage heartbeatMessage) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        heartbeatMessage.encode(byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        sendRecord((short) 24, byteArray, 0, byteArray.length);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void sendHelloVerifyRequestRecord(DatagramSender datagramSender, long j, byte[] bArr) throws IOException {
        TlsUtils.checkUint16(bArr.length);
        byte[] bArr2 = new byte[bArr.length + 13];
        TlsUtils.writeUint8((short) 22, bArr2, 0);
        TlsUtils.writeVersion(ProtocolVersion.DTLSv10, bArr2, 1);
        TlsUtils.writeUint16(0, bArr2, 3);
        TlsUtils.writeUint48(j, bArr2, 5);
        TlsUtils.writeUint16(bArr.length, bArr2, 11);
        System.arraycopy(bArr, 0, bArr2, 13, bArr.length);
        sendDatagram(datagramSender, bArr2, 0, bArr2.length);
    }

    private void sendRecord(short s, byte[] bArr, int i, int i2) throws IOException {
        if (this.writeVersion == null) {
            return;
        }
        if (i2 > this.plaintextLimit) {
            throw new TlsFatalAlert((short) 80);
        }
        if (i2 < 1 && s != 23) {
            throw new TlsFatalAlert((short) 80);
        }
        synchronized (this.writeLock) {
            int epoch = this.writeEpoch.getEpoch();
            long allocateSequenceNumber = this.writeEpoch.allocateSequenceNumber();
            long macSequenceNumber = getMacSequenceNumber(epoch, allocateSequenceNumber);
            ProtocolVersion protocolVersion = this.writeVersion;
            TlsEncodeResult encodePlaintext = this.writeEpoch.getCipher().encodePlaintext(macSequenceNumber, s, protocolVersion, 13, bArr, i, i2);
            int i3 = encodePlaintext.len - 13;
            TlsUtils.checkUint16(i3);
            TlsUtils.writeUint8(encodePlaintext.recordType, encodePlaintext.buf, encodePlaintext.off + 0);
            TlsUtils.writeVersion(protocolVersion, encodePlaintext.buf, encodePlaintext.off + 1);
            TlsUtils.writeUint16(epoch, encodePlaintext.buf, encodePlaintext.off + 3);
            TlsUtils.writeUint48(allocateSequenceNumber, encodePlaintext.buf, encodePlaintext.off + 5);
            TlsUtils.writeUint16(i3, encodePlaintext.buf, encodePlaintext.off + 11);
            sendDatagram(this.transport, encodePlaintext.buf, encodePlaintext.off, encodePlaintext.len);
        }
    }

    @Override // org.bouncycastle.tls.TlsCloseable
    public void close() throws IOException {
        if (!this.closed) {
            if (this.inHandshake && this.inConnection) {
                warn((short) 90, "User canceled handshake");
            }
            closeTransport();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void fail(short s) {
        if (!this.closed) {
            if (this.inConnection) {
                try {
                    raiseAlert((short) 2, s, null, null);
                } catch (Exception unused) {
                }
            }
            this.failed = true;
            closeTransport();
        }
    }

    void failed() {
        if (!this.closed) {
            this.failed = true;
            closeTransport();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getReadEpoch() {
        return this.readEpoch.getEpoch();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ProtocolVersion getReadVersion() {
        return this.readVersion;
    }

    @Override // org.bouncycastle.tls.DatagramReceiver
    public int getReceiveLimit() throws IOException {
        return Math.min(this.plaintextLimit, this.readEpoch.getCipher().getPlaintextLimit(this.transport.getReceiveLimit() - 13));
    }

    @Override // org.bouncycastle.tls.DatagramSender
    public int getSendLimit() throws IOException {
        return Math.min(this.plaintextLimit, this.writeEpoch.getCipher().getPlaintextLimit(this.transport.getSendLimit() - 13));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void handshakeSuccessful(DTLSHandshakeRetransmit dTLSHandshakeRetransmit) {
        DTLSEpoch dTLSEpoch = this.readEpoch;
        DTLSEpoch dTLSEpoch2 = this.currentEpoch;
        if (dTLSEpoch == dTLSEpoch2 || this.writeEpoch == dTLSEpoch2) {
            throw new IllegalStateException();
        }
        if (dTLSHandshakeRetransmit != null) {
            this.retransmit = dTLSHandshakeRetransmit;
            this.retransmitEpoch = dTLSEpoch2;
            this.retransmitTimeout = new Timeout(RETRANSMIT_TIMEOUT);
        }
        this.inHandshake = false;
        this.currentEpoch = this.pendingEpoch;
        this.pendingEpoch = null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void initHeartbeat(TlsHeartbeat tlsHeartbeat, boolean z) {
        if (!this.inHandshake) {
            this.heartbeat = tlsHeartbeat;
            this.heartBeatResponder = z;
            if (tlsHeartbeat == null) {
                return;
            }
            resetHeartbeat();
            return;
        }
        throw new IllegalStateException();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void initPendingEpoch(TlsCipher tlsCipher) {
        if (this.pendingEpoch == null) {
            this.pendingEpoch = new DTLSEpoch(this.writeEpoch.getEpoch() + 1, tlsCipher);
            return;
        }
        throw new IllegalStateException();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isClosed() {
        return this.closed;
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x008b  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x00aa A[LOOP:0: B:3:0x000a->B:31:0x00aa, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:35:0x00a9 A[SYNTHETIC] */
    @Override // org.bouncycastle.tls.DatagramReceiver
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public int receive(byte[] r10, int r11, int r12, int r13) throws java.io.IOException {
        /*
            r9 = this;
            long r0 = java.lang.System.currentTimeMillis()
            org.bouncycastle.tls.Timeout r2 = org.bouncycastle.tls.Timeout.forWaitMillis(r13, r0)
            r3 = 0
            r4 = r3
        La:
            if (r13 < 0) goto Lb4
            org.bouncycastle.tls.Timeout r5 = r9.retransmitTimeout
            if (r5 == 0) goto L20
            long r5 = r5.remainingMillis(r0)
            r7 = 1
            int r5 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r5 >= 0) goto L20
            r9.retransmit = r3
            r9.retransmitEpoch = r3
            r9.retransmitTimeout = r3
        L20:
            org.bouncycastle.tls.Timeout r5 = r9.heartbeatTimeout
            boolean r5 = org.bouncycastle.tls.Timeout.hasExpired(r5, r0)
            r6 = 1
            if (r5 == 0) goto L5e
            org.bouncycastle.tls.HeartbeatMessage r5 = r9.heartbeatInFlight
            if (r5 != 0) goto L56
            org.bouncycastle.tls.TlsContext r5 = r9.context
            org.bouncycastle.tls.TlsHeartbeat r7 = r9.heartbeat
            byte[] r7 = r7.generatePayload()
            org.bouncycastle.tls.HeartbeatMessage r5 = org.bouncycastle.tls.HeartbeatMessage.create(r5, r6, r7)
            r9.heartbeatInFlight = r5
            org.bouncycastle.tls.Timeout r5 = new org.bouncycastle.tls.Timeout
            org.bouncycastle.tls.TlsHeartbeat r7 = r9.heartbeat
            int r7 = r7.getTimeoutMillis()
            long r7 = (long) r7
            r5.<init>(r7, r0)
            r9.heartbeatTimeout = r5
            r5 = 1000(0x3e8, float:1.401E-42)
            r9.heartbeatResendMillis = r5
            org.bouncycastle.tls.Timeout r5 = new org.bouncycastle.tls.Timeout
            int r7 = r9.heartbeatResendMillis
            long r7 = (long) r7
            r5.<init>(r7, r0)
            goto L76
        L56:
            org.bouncycastle.tls.TlsTimeoutException r10 = new org.bouncycastle.tls.TlsTimeoutException
            java.lang.String r11 = "Heartbeat timed out"
            r10.<init>(r11)
            throw r10
        L5e:
            org.bouncycastle.tls.Timeout r5 = r9.heartbeatResendTimeout
            boolean r5 = org.bouncycastle.tls.Timeout.hasExpired(r5, r0)
            if (r5 == 0) goto L7d
            int r5 = r9.heartbeatResendMillis
            int r5 = org.bouncycastle.tls.DTLSReliableHandshake.backOff(r5)
            r9.heartbeatResendMillis = r5
            org.bouncycastle.tls.Timeout r5 = new org.bouncycastle.tls.Timeout
            int r7 = r9.heartbeatResendMillis
            long r7 = (long) r7
            r5.<init>(r7, r0)
        L76:
            r9.heartbeatResendTimeout = r5
            org.bouncycastle.tls.HeartbeatMessage r5 = r9.heartbeatInFlight
            r9.sendHeartbeatMessage(r5)
        L7d:
            org.bouncycastle.tls.Timeout r5 = r9.heartbeatTimeout
            int r13 = org.bouncycastle.tls.Timeout.constrainWaitMillis(r13, r5, r0)
            org.bouncycastle.tls.Timeout r5 = r9.heartbeatResendTimeout
            int r13 = org.bouncycastle.tls.Timeout.constrainWaitMillis(r13, r5, r0)
            if (r13 >= 0) goto L8c
            r13 = r6
        L8c:
            int r0 = r9.getReceiveLimit()
            int r0 = java.lang.Math.min(r12, r0)
            int r0 = r0 + 13
            if (r4 == 0) goto L9b
            int r1 = r4.length
            if (r1 >= r0) goto L9e
        L9b:
            byte[] r1 = new byte[r0]
            r4 = r1
        L9e:
            r1 = 0
            int r13 = r9.receiveRecord(r4, r1, r0, r13)
            int r13 = r9.processRecord(r13, r4, r10, r11)
            if (r13 < 0) goto Laa
            return r13
        Laa:
            long r0 = java.lang.System.currentTimeMillis()
            int r13 = org.bouncycastle.tls.Timeout.getWaitMillis(r2, r0)
            goto La
        Lb4:
            r10 = -1
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.tls.DTLSRecordLayer.receive(byte[], int, int, int):int");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void resetAfterHelloVerifyRequestServer(long j) {
        this.inConnection = true;
        this.currentEpoch.setSequenceNumber(j);
        this.currentEpoch.getReplayWindow().reset(j);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void resetWriteEpoch() {
        DTLSEpoch dTLSEpoch = this.retransmitEpoch;
        if (dTLSEpoch == null) {
            dTLSEpoch = this.currentEpoch;
        }
        this.writeEpoch = dTLSEpoch;
    }

    @Override // org.bouncycastle.tls.DatagramSender
    public void send(byte[] bArr, int i, int i2) throws IOException {
        short s;
        if (this.inHandshake || this.writeEpoch == this.retransmitEpoch) {
            s = 22;
            if (TlsUtils.readUint8(bArr, i) == 20) {
                DTLSEpoch dTLSEpoch = null;
                if (this.inHandshake) {
                    dTLSEpoch = this.pendingEpoch;
                } else if (this.writeEpoch == this.retransmitEpoch) {
                    dTLSEpoch = this.currentEpoch;
                }
                if (dTLSEpoch == null) {
                    throw new IllegalStateException();
                }
                byte[] bArr2 = {1};
                sendRecord((short) 20, bArr2, 0, bArr2.length);
                this.writeEpoch = dTLSEpoch;
            }
        } else {
            s = 23;
        }
        sendRecord(s, bArr, i, i2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setPlaintextLimit(int i) {
        this.plaintextLimit = i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setReadVersion(ProtocolVersion protocolVersion) {
        this.readVersion = protocolVersion;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setWriteVersion(ProtocolVersion protocolVersion) {
        this.writeVersion = protocolVersion;
    }

    void warn(short s, String str) throws IOException {
        raiseAlert((short) 1, s, str, null);
    }
}
