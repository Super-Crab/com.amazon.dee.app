package org.bouncycastle.tls;

import androidx.core.view.ViewCompat;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;
import org.bouncycastle.tls.SessionParameters;
import org.bouncycastle.tls.crypto.TlsSecret;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Integers;
/* loaded from: classes5.dex */
public abstract class TlsProtocol implements TlsCloseable {
    protected static final short ADS_MODE_0_N = 1;
    protected static final short ADS_MODE_0_N_FIRSTONLY = 2;
    protected static final short ADS_MODE_1_Nsub1 = 0;
    protected static final short CS_CLIENT_CERTIFICATE = 15;
    protected static final short CS_CLIENT_CERTIFICATE_VERIFY = 17;
    protected static final short CS_CLIENT_END_OF_EARLY_DATA = 13;
    protected static final short CS_CLIENT_FINISHED = 18;
    protected static final short CS_CLIENT_HELLO = 1;
    protected static final short CS_CLIENT_HELLO_RETRY = 3;
    protected static final short CS_CLIENT_KEY_EXCHANGE = 16;
    protected static final short CS_CLIENT_SUPPLEMENTAL_DATA = 14;
    protected static final short CS_END = 21;
    protected static final short CS_SERVER_CERTIFICATE = 7;
    protected static final short CS_SERVER_CERTIFICATE_REQUEST = 11;
    protected static final short CS_SERVER_CERTIFICATE_STATUS = 8;
    protected static final short CS_SERVER_CERTIFICATE_VERIFY = 9;
    protected static final short CS_SERVER_ENCRYPTED_EXTENSIONS = 5;
    protected static final short CS_SERVER_FINISHED = 20;
    protected static final short CS_SERVER_HELLO = 4;
    protected static final short CS_SERVER_HELLO_DONE = 12;
    protected static final short CS_SERVER_HELLO_RETRY_REQUEST = 2;
    protected static final short CS_SERVER_KEY_EXCHANGE = 10;
    protected static final short CS_SERVER_SESSION_TICKET = 19;
    protected static final short CS_SERVER_SUPPLEMENTAL_DATA = 6;
    protected static final short CS_START = 0;
    protected static final Integer EXT_RenegotiationInfo = Integers.valueOf(65281);
    protected static final Integer EXT_SessionTicket = Integers.valueOf(35);
    private ByteQueue alertQueue;
    private volatile boolean appDataReady;
    private volatile boolean appDataSplitEnabled;
    private volatile int appDataSplitMode;
    private ByteQueue applicationDataQueue;
    protected boolean blocking;
    protected Hashtable clientExtensions;
    private volatile boolean closed;
    protected short connection_state;
    protected boolean expectSessionTicket;
    private volatile boolean failedWithError;
    TlsHandshakeHash handshakeHash;
    private ByteQueue handshakeQueue;
    protected ByteQueueInputStream inputBuffers;
    protected ByteQueueOutputStream outputBuffer;
    protected boolean receivedChangeCipherSpec;
    RecordStream recordStream;
    private volatile boolean resumableHandshake;
    protected boolean resumedSession;
    protected byte[] retryCookie;
    protected int retryGroup;
    protected Hashtable serverExtensions;
    protected TlsSecret sessionMasterSecret;
    protected SessionParameters sessionParameters;
    private TlsInputStream tlsInputStream;
    private TlsOutputStream tlsOutputStream;
    protected TlsSession tlsSession;

    /* JADX INFO: Access modifiers changed from: protected */
    public TlsProtocol() {
        this.applicationDataQueue = new ByteQueue(0);
        this.alertQueue = new ByteQueue(2);
        this.handshakeQueue = new ByteQueue(0);
        this.tlsInputStream = null;
        this.tlsOutputStream = null;
        this.closed = false;
        this.failedWithError = false;
        this.appDataReady = false;
        this.appDataSplitEnabled = true;
        this.resumableHandshake = false;
        this.appDataSplitMode = 0;
        this.tlsSession = null;
        this.sessionParameters = null;
        this.sessionMasterSecret = null;
        this.retryCookie = null;
        this.retryGroup = -1;
        this.clientExtensions = null;
        this.serverExtensions = null;
        this.connection_state = (short) 0;
        this.resumedSession = false;
        this.receivedChangeCipherSpec = false;
        this.expectSessionTicket = false;
        this.blocking = false;
        this.inputBuffers = new ByteQueueInputStream();
        this.outputBuffer = new ByteQueueOutputStream();
        this.recordStream = new RecordStream(this, this.inputBuffers, this.outputBuffer);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public TlsProtocol(InputStream inputStream, OutputStream outputStream) {
        this.applicationDataQueue = new ByteQueue(0);
        this.alertQueue = new ByteQueue(2);
        this.handshakeQueue = new ByteQueue(0);
        this.tlsInputStream = null;
        this.tlsOutputStream = null;
        this.closed = false;
        this.failedWithError = false;
        this.appDataReady = false;
        this.appDataSplitEnabled = true;
        this.resumableHandshake = false;
        this.appDataSplitMode = 0;
        this.tlsSession = null;
        this.sessionParameters = null;
        this.sessionMasterSecret = null;
        this.retryCookie = null;
        this.retryGroup = -1;
        this.clientExtensions = null;
        this.serverExtensions = null;
        this.connection_state = (short) 0;
        this.resumedSession = false;
        this.receivedChangeCipherSpec = false;
        this.expectSessionTicket = false;
        this.blocking = true;
        this.recordStream = new RecordStream(this, inputStream, outputStream);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static void assertEmpty(ByteArrayInputStream byteArrayInputStream) throws IOException {
        if (byteArrayInputStream.available() <= 0) {
            return;
        }
        throw new TlsFatalAlert((short) 50);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static byte[] createRandomBlock(boolean z, TlsContext tlsContext) {
        byte[] generateNonce = tlsContext.getNonceGenerator().generateNonce(32);
        if (z) {
            TlsUtils.writeGMTUnixTime(generateNonce, 0);
        }
        return generateNonce;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static byte[] createRenegotiationInfo(byte[] bArr) throws IOException {
        return TlsUtils.encodeOpaque8(bArr);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static void establishMasterSecret(TlsContext tlsContext, TlsKeyExchange tlsKeyExchange) throws IOException {
        TlsSecret generatePreMasterSecret = tlsKeyExchange.generatePreMasterSecret();
        if (generatePreMasterSecret != null) {
            try {
                tlsContext.getSecurityParametersHandshake().masterSecret = TlsUtils.calculateMasterSecret(tlsContext, generatePreMasterSecret);
                return;
            } finally {
                generatePreMasterSecret.destroy();
            }
        }
        throw new TlsFatalAlert((short) 80);
    }

    private void processAlertQueue() throws IOException {
        while (this.alertQueue.available() >= 2) {
            byte[] removeData = this.alertQueue.removeData(2, 0);
            handleAlertMessage(removeData[0], removeData[1]);
        }
    }

    private void processApplicationDataQueue() {
    }

    private void processChangeCipherSpec(byte[] bArr, int i, int i2) throws IOException {
        ProtocolVersion serverVersion = getContext().getServerVersion();
        if (serverVersion == null || TlsUtils.isTLSv13(serverVersion)) {
            throw new TlsFatalAlert((short) 10);
        }
        for (int i3 = 0; i3 < i2; i3++) {
            if (TlsUtils.readUint8(bArr, i + i3) != 1) {
                throw new TlsFatalAlert((short) 50);
            }
            if (this.receivedChangeCipherSpec || this.alertQueue.available() > 0 || this.handshakeQueue.available() > 0) {
                throw new TlsFatalAlert((short) 10);
            }
            this.recordStream.notifyChangeCipherSpecReceived();
            this.receivedChangeCipherSpec = true;
            handleChangeCipherSpecMessage();
        }
    }

    private void processHandshakeQueue(ByteQueue byteQueue) throws IOException {
        ProtocolVersion serverVersion;
        while (byteQueue.available() >= 4) {
            int readInt32 = byteQueue.readInt32();
            short s = (short) (readInt32 >>> 24);
            int i = (readInt32 & ViewCompat.MEASURED_SIZE_MASK) + 4;
            if (byteQueue.available() < i) {
                return;
            }
            if (s != 0 && ((serverVersion = getContext().getServerVersion()) == null || !TlsUtils.isTLSv13(serverVersion))) {
                checkReceivedChangeCipherSpec(20 == s);
            }
            HandshakeMessageInput readHandshakeMessage = byteQueue.readHandshakeMessage(i);
            if (s != 0 && s != 2 && s != 4 && s != 15 && s != 20 && s != 24) {
                readHandshakeMessage.updateHash(this.handshakeHash);
            }
            readHandshakeMessage.skip(4L);
            handleHandshakeMessage(s, readHandshakeMessage);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static Hashtable readExtensions(ByteArrayInputStream byteArrayInputStream) throws IOException {
        if (byteArrayInputStream.available() < 1) {
            return null;
        }
        byte[] readOpaque16 = TlsUtils.readOpaque16(byteArrayInputStream);
        assertEmpty(byteArrayInputStream);
        return readExtensionsData(readOpaque16);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static Hashtable readExtensionsData(byte[] bArr) throws IOException {
        Hashtable hashtable = new Hashtable();
        if (bArr.length > 0) {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
            while (hashtable.put(Integers.valueOf(TlsUtils.readUint16(byteArrayInputStream)), TlsUtils.readOpaque16(byteArrayInputStream)) == null) {
                if (byteArrayInputStream.available() <= 0) {
                    return hashtable;
                }
            }
            throw new TlsFatalAlert((short) 47);
        }
        return hashtable;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static Vector readSupplementalDataMessage(ByteArrayInputStream byteArrayInputStream) throws IOException {
        byte[] readOpaque24 = TlsUtils.readOpaque24(byteArrayInputStream, 1);
        assertEmpty(byteArrayInputStream);
        ByteArrayInputStream byteArrayInputStream2 = new ByteArrayInputStream(readOpaque24);
        Vector vector = new Vector();
        while (byteArrayInputStream2.available() > 0) {
            vector.addElement(new SupplementalDataEntry(TlsUtils.readUint16(byteArrayInputStream2), TlsUtils.readOpaque16(byteArrayInputStream2)));
        }
        return vector;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static void writeExtensions(OutputStream outputStream, Hashtable hashtable) throws IOException {
        if (hashtable == null || hashtable.isEmpty()) {
            return;
        }
        TlsUtils.writeOpaque16(writeExtensionsData(hashtable), outputStream);
    }

    protected static void writeExtensionsData(Hashtable hashtable, ByteArrayOutputStream byteArrayOutputStream) throws IOException {
        writeSelectedExtensions(byteArrayOutputStream, hashtable, true);
        writeSelectedExtensions(byteArrayOutputStream, hashtable, false);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static byte[] writeExtensionsData(Hashtable hashtable) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        writeExtensionsData(hashtable, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    protected static void writeSelectedExtensions(OutputStream outputStream, Hashtable hashtable, boolean z) throws IOException {
        Enumeration keys = hashtable.keys();
        while (keys.hasMoreElements()) {
            Integer num = (Integer) keys.nextElement();
            int intValue = num.intValue();
            byte[] bArr = (byte[]) hashtable.get(num);
            if (z == (bArr.length == 0)) {
                TlsUtils.checkUint16(intValue);
                TlsUtils.writeUint16(intValue, outputStream);
                TlsUtils.writeOpaque16(bArr, outputStream);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static void writeSupplementalData(OutputStream outputStream, Vector vector) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        for (int i = 0; i < vector.size(); i++) {
            SupplementalDataEntry supplementalDataEntry = (SupplementalDataEntry) vector.elementAt(i);
            int dataType = supplementalDataEntry.getDataType();
            TlsUtils.checkUint16(dataType);
            TlsUtils.writeUint16(dataType, byteArrayOutputStream);
            TlsUtils.writeOpaque16(supplementalDataEntry.getData(), byteArrayOutputStream);
        }
        TlsUtils.writeOpaque24(byteArrayOutputStream.toByteArray(), outputStream);
    }

    public int applicationDataAvailable() {
        return this.applicationDataQueue.available();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void applyMaxFragmentLengthExtension() throws IOException {
        short maxFragmentLength = getContext().getSecurityParametersHandshake().getMaxFragmentLength();
        if (maxFragmentLength >= 0) {
            if (!MaxFragmentLength.isValid(maxFragmentLength)) {
                throw new TlsFatalAlert((short) 80);
            }
            this.recordStream.setPlaintextLimit(1 << (maxFragmentLength + 8));
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void beginHandshake() throws IOException {
        AbstractTlsContext contextAdmin = getContextAdmin();
        TlsPeer peer = getPeer();
        this.handshakeHash = new DeferredHash(contextAdmin);
        this.connection_state = (short) 0;
        contextAdmin.handshakeBeginning(peer);
        contextAdmin.getSecurityParametersHandshake().extendedPadding = peer.shouldUseExtendedPadding();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void blockForHandshake() throws IOException {
        while (this.connection_state != 21) {
            if (isClosed()) {
                throw new TlsFatalAlert((short) 80);
            }
            safeReadRecord();
        }
    }

    protected void checkReceivedChangeCipherSpec(boolean z) throws IOException {
        if (z == this.receivedChangeCipherSpec) {
            return;
        }
        throw new TlsFatalAlert((short) 10);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void cleanupHandshake() {
        SecurityParameters securityParameters = getContext().getSecurityParameters();
        if (securityParameters != null) {
            securityParameters.clear();
        }
        this.tlsSession = null;
        this.sessionParameters = null;
        this.sessionMasterSecret = null;
        this.retryCookie = null;
        this.retryGroup = -1;
        this.clientExtensions = null;
        this.serverExtensions = null;
        this.resumedSession = false;
        this.receivedChangeCipherSpec = false;
        this.expectSessionTicket = false;
    }

    @Override // org.bouncycastle.tls.TlsCloseable
    public void close() throws IOException {
        handleClose(true);
    }

    protected void closeConnection() throws IOException {
        this.recordStream.close();
    }

    public void closeInput() throws IOException {
        if (!this.blocking) {
            if (this.closed) {
                return;
            }
            if (this.inputBuffers.available() > 0) {
                throw new EOFException();
            }
            if (this.appDataReady) {
                throw new TlsNoCloseNotifyException();
            }
            throw new TlsFatalAlert((short) 40);
        }
        throw new IllegalStateException("Cannot use closeInput() in blocking mode!");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void completeHandshake() throws IOException {
        try {
            this.recordStream.finaliseHandshake();
            this.connection_state = (short) 21;
            AbstractTlsContext contextAdmin = getContextAdmin();
            SecurityParameters securityParametersHandshake = contextAdmin.getSecurityParametersHandshake();
            if (securityParametersHandshake.getLocalVerifyData() == null || securityParametersHandshake.getPeerVerifyData() == null) {
                throw new TlsFatalAlert((short) 80);
            }
            this.handshakeHash = new DeferredHash(contextAdmin);
            this.alertQueue.shrink();
            this.handshakeQueue.shrink();
            this.appDataSplitEnabled = !TlsUtils.isTLSv11(contextAdmin);
            if (!this.appDataReady) {
                this.appDataReady = true;
                if (this.blocking) {
                    this.tlsInputStream = new TlsInputStream(this);
                    this.tlsOutputStream = new TlsOutputStream(this);
                }
            }
            if (this.sessionParameters == null) {
                this.sessionMasterSecret = securityParametersHandshake.getMasterSecret();
                this.sessionParameters = new SessionParameters.Builder().setCipherSuite(securityParametersHandshake.getCipherSuite()).setCompressionAlgorithm(securityParametersHandshake.getCompressionAlgorithm()).setExtendedMasterSecret(securityParametersHandshake.isExtendedMasterSecret()).setLocalCertificate(securityParametersHandshake.getLocalCertificate()).setMasterSecret(contextAdmin.getCrypto().adoptSecret(this.sessionMasterSecret)).setNegotiatedVersion(securityParametersHandshake.getNegotiatedVersion()).setPeerCertificate(securityParametersHandshake.getPeerCertificate()).setPSKIdentity(securityParametersHandshake.getPSKIdentity()).setSRPIdentity(securityParametersHandshake.getSRPIdentity()).setServerExtensions(this.serverExtensions).build();
                this.tlsSession = TlsUtils.importSession(this.tlsSession.getSessionID(), this.sessionParameters);
            }
            contextAdmin.handshakeComplete(getPeer(), this.tlsSession);
        } finally {
            cleanupHandshake();
        }
    }

    public void flush() throws IOException {
        this.recordStream.flush();
    }

    public int getAppDataSplitMode() {
        return this.appDataSplitMode;
    }

    public int getApplicationDataLimit() {
        return this.recordStream.getPlaintextLimit();
    }

    public int getAvailableInputBytes() {
        if (!this.blocking) {
            return applicationDataAvailable();
        }
        throw new IllegalStateException("Cannot use getAvailableInputBytes() in blocking mode! Use getInputStream().available() instead.");
    }

    public int getAvailableOutputBytes() {
        if (!this.blocking) {
            return this.outputBuffer.getBuffer().available();
        }
        throw new IllegalStateException("Cannot use getAvailableOutputBytes() in blocking mode! Use getOutputStream() instead.");
    }

    protected abstract TlsContext getContext();

    abstract AbstractTlsContext getContextAdmin();

    public InputStream getInputStream() {
        if (this.blocking) {
            return this.tlsInputStream;
        }
        throw new IllegalStateException("Cannot use InputStream in non-blocking mode! Use offerInput() instead.");
    }

    public OutputStream getOutputStream() {
        if (this.blocking) {
            return this.tlsOutputStream;
        }
        throw new IllegalStateException("Cannot use OutputStream in non-blocking mode! Use offerOutput() instead.");
    }

    protected abstract TlsPeer getPeer();

    protected void handleAlertMessage(short s, short s2) throws IOException {
        getPeer().notifyAlertReceived(s, s2);
        if (s == 1) {
            handleAlertWarningMessage(s2);
        } else {
            handleFailure();
            throw new TlsFatalAlertReceived(s2);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void handleAlertWarningMessage(short s) throws IOException {
        if (s == 0) {
            if (!this.appDataReady) {
                throw new TlsFatalAlert((short) 40);
            }
            handleClose(false);
        } else if (s == 41) {
            throw new TlsFatalAlert((short) 10);
        } else {
            if (s == 100) {
                throw new TlsFatalAlert((short) 40);
            }
        }
    }

    protected void handleChangeCipherSpecMessage() throws IOException {
    }

    protected void handleClose(boolean z) throws IOException {
        if (!this.closed) {
            this.closed = true;
            if (z && !this.appDataReady) {
                raiseAlertWarning((short) 90, "User canceled handshake");
            }
            raiseAlertWarning((short) 0, "Connection closed");
            if (this.connection_state != 21) {
                cleanupHandshake();
            }
            closeConnection();
        }
    }

    protected void handleException(short s, String str, Throwable th) throws IOException {
        if (((this.appDataReady || isResumableHandshake()) && (th instanceof InterruptedIOException)) || this.closed) {
            return;
        }
        raiseAlertFatal(s, str, th);
        handleFailure();
    }

    protected void handleFailure() throws IOException {
        this.closed = true;
        this.failedWithError = true;
        invalidateSession();
        if (this.connection_state != 21) {
            cleanupHandshake();
        }
        closeConnection();
    }

    protected abstract void handleHandshakeMessage(short s, HandshakeMessageInput handshakeMessageInput) throws IOException;

    /* JADX INFO: Access modifiers changed from: protected */
    public void invalidateSession() {
        TlsSecret tlsSecret = this.sessionMasterSecret;
        if (tlsSecret != null) {
            tlsSecret.destroy();
            this.sessionMasterSecret = null;
        }
        SessionParameters sessionParameters = this.sessionParameters;
        if (sessionParameters != null) {
            sessionParameters.clear();
            this.sessionParameters = null;
        }
        TlsSession tlsSession = this.tlsSession;
        if (tlsSession != null) {
            tlsSession.invalidate();
            this.tlsSession = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isApplicationDataReady() {
        return this.appDataReady;
    }

    public boolean isClosed() {
        return this.closed;
    }

    public boolean isHandshaking() {
        return !isClosed() && getContext().getSecurityParametersHandshake() != null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean isLegacyConnectionState() {
        switch (this.connection_state) {
            case 0:
            case 1:
            case 4:
            case 6:
            case 7:
            case 8:
            case 10:
            case 11:
            case 12:
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
            case 20:
            case 21:
                return true;
            case 2:
            case 3:
            case 5:
            case 9:
            case 13:
            default:
                return false;
        }
    }

    public boolean isResumableHandshake() {
        return this.resumableHandshake;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean isTLSv13ConnectionState() {
        switch (this.connection_state) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 7:
            case 9:
            case 11:
            case 13:
            case 15:
            case 17:
            case 18:
            case 20:
            case 21:
                return true;
            case 6:
            case 8:
            case 10:
            case 12:
            case 14:
            case 16:
            case 19:
            default:
                return false;
        }
    }

    public void offerInput(byte[] bArr) throws IOException {
        offerInput(bArr, 0, bArr.length);
    }

    public void offerInput(byte[] bArr, int i, int i2) throws IOException {
        if (!this.blocking) {
            if (this.closed) {
                throw new IOException("Connection is closed, cannot accept any more input");
            }
            if (this.inputBuffers.available() == 0 && safeReadFullRecord(bArr, i, i2)) {
                if (this.closed && this.connection_state != 21) {
                    throw new TlsFatalAlert((short) 80);
                }
                return;
            }
            this.inputBuffers.addBytes(bArr, i, i2);
            while (this.inputBuffers.available() >= 5) {
                byte[] bArr2 = new byte[5];
                if (5 != this.inputBuffers.peek(bArr2)) {
                    throw new TlsFatalAlert((short) 80);
                }
                if (this.inputBuffers.available() < safePreviewRecordHeader(bArr2).getRecordSize()) {
                    return;
                }
                safeReadRecord();
                if (this.closed) {
                    if (this.connection_state != 21) {
                        throw new TlsFatalAlert((short) 80);
                    }
                    return;
                }
            }
            return;
        }
        throw new IllegalStateException("Cannot use offerInput() in blocking mode! Use getInputStream() instead.");
    }

    public RecordPreview previewInputRecord(byte[] bArr) throws IOException {
        if (!this.blocking) {
            if (this.inputBuffers.available() != 0) {
                throw new IllegalStateException("Can only use previewInputRecord() for record-aligned input.");
            }
            if (this.closed) {
                throw new IOException("Connection is closed, cannot accept any more input");
            }
            return safePreviewRecordHeader(bArr);
        }
        throw new IllegalStateException("Cannot use previewInputRecord() in blocking mode!");
    }

    public RecordPreview previewOutputRecord(int i) throws IOException {
        if (!this.blocking) {
            if (this.outputBuffer.getBuffer().available() != 0) {
                throw new IllegalStateException("Can only use previewOutputRecord() for record-aligned output.");
            }
            if (this.closed) {
                throw new IOException("Connection is closed, cannot produce any more output");
            }
            if (i < 1) {
                return new RecordPreview(0, 0);
            }
            if (!this.appDataSplitEnabled) {
                return this.recordStream.previewOutputRecord(i);
            }
            int appDataSplitMode = getAppDataSplitMode();
            if (appDataSplitMode == 1 || appDataSplitMode == 2) {
                return RecordPreview.combine(this.recordStream.previewOutputRecord(0), this.recordStream.previewOutputRecord(i));
            }
            RecordPreview previewOutputRecord = this.recordStream.previewOutputRecord(1);
            return i > 1 ? RecordPreview.combine(previewOutputRecord, this.recordStream.previewOutputRecord(i - 1)) : previewOutputRecord;
        }
        throw new IllegalStateException("Cannot use previewOutputRecord() in blocking mode!");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void processFinishedMessage(ByteArrayInputStream byteArrayInputStream) throws IOException {
        TlsContext context = getContext();
        SecurityParameters securityParametersHandshake = context.getSecurityParametersHandshake();
        boolean isServer = context.isServer();
        byte[] readFully = TlsUtils.readFully(securityParametersHandshake.getVerifyDataLength(), byteArrayInputStream);
        assertEmpty(byteArrayInputStream);
        byte[] calculateVerifyData = TlsUtils.calculateVerifyData(context, this.handshakeHash, !isServer);
        if (Arrays.constantTimeAreEqual(calculateVerifyData, readFully)) {
            securityParametersHandshake.peerVerifyData = calculateVerifyData;
            if ((this.resumedSession && !securityParametersHandshake.isExtendedMasterSecret()) || securityParametersHandshake.getLocalVerifyData() != null) {
                return;
            }
            securityParametersHandshake.tlsUnique = calculateVerifyData;
            return;
        }
        throw new TlsFatalAlert((short) 51);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public short processMaxFragmentLengthExtension(Hashtable hashtable, Hashtable hashtable2, short s) throws IOException {
        short maxFragmentLengthExtension = TlsExtensionsUtils.getMaxFragmentLengthExtension(hashtable2);
        if (maxFragmentLengthExtension < 0 || (MaxFragmentLength.isValid(maxFragmentLengthExtension) && (this.resumedSession || maxFragmentLengthExtension == TlsExtensionsUtils.getMaxFragmentLengthExtension(hashtable)))) {
            return maxFragmentLengthExtension;
        }
        throw new TlsFatalAlert(s);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void processRecord(short s, byte[] bArr, int i, int i2) throws IOException {
        switch (s) {
            case 20:
                processChangeCipherSpec(bArr, i, i2);
                return;
            case 21:
                this.alertQueue.addData(bArr, i, i2);
                processAlertQueue();
                return;
            case 22:
                if (this.handshakeQueue.available() > 0) {
                    this.handshakeQueue.addData(bArr, i, i2);
                    processHandshakeQueue(this.handshakeQueue);
                    return;
                }
                ByteQueue byteQueue = new ByteQueue(bArr, i, i2);
                processHandshakeQueue(byteQueue);
                int available = byteQueue.available();
                if (available <= 0) {
                    return;
                }
                this.handshakeQueue.addData(bArr, (i + i2) - available, available);
                return;
            case 23:
                if (!this.appDataReady) {
                    throw new TlsFatalAlert((short) 10);
                }
                this.applicationDataQueue.addData(bArr, i, i2);
                processApplicationDataQueue();
                return;
            default:
                throw new TlsFatalAlert((short) 10);
        }
    }

    protected void raiseAlertFatal(short s, String str, Throwable th) throws IOException {
        getPeer().notifyAlertRaised((short) 2, s, str, th);
        try {
            this.recordStream.writeRecord((short) 21, new byte[]{2, (byte) s}, 0, 2);
        } catch (Exception unused) {
        }
    }

    protected void raiseAlertWarning(short s, String str) throws IOException {
        getPeer().notifyAlertRaised((short) 1, s, str, null);
        safeWriteRecord((short) 21, new byte[]{1, (byte) s}, 0, 2);
    }

    public int readApplicationData(byte[] bArr, int i, int i2) throws IOException {
        if (i2 < 1) {
            return 0;
        }
        while (this.applicationDataQueue.available() == 0) {
            if (this.closed) {
                if (this.failedWithError) {
                    throw new IOException("Cannot read application data on failed TLS connection");
                }
                return -1;
            } else if (!this.appDataReady) {
                throw new IllegalStateException("Cannot read application data until initial handshake completed.");
            } else {
                safeReadRecord();
            }
        }
        int min = Math.min(i2, this.applicationDataQueue.available());
        this.applicationDataQueue.removeData(bArr, i, min, 0);
        return min;
    }

    public int readInput(byte[] bArr, int i, int i2) {
        if (!this.blocking) {
            int min = Math.min(i2, this.applicationDataQueue.available());
            if (min < 1) {
                return 0;
            }
            this.applicationDataQueue.removeData(bArr, i, min, 0);
            return min;
        }
        throw new IllegalStateException("Cannot use readInput() in blocking mode! Use getInputStream() instead.");
    }

    public int readOutput(byte[] bArr, int i, int i2) {
        if (!this.blocking) {
            int min = Math.min(getAvailableOutputBytes(), i2);
            this.outputBuffer.getBuffer().removeData(bArr, i, min, 0);
            return min;
        }
        throw new IllegalStateException("Cannot use readOutput() in blocking mode! Use getOutputStream() instead.");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void refuseRenegotiation() throws IOException {
        if (!TlsUtils.isSSL(getContext())) {
            raiseAlertWarning((short) 100, "Renegotiation not supported");
            return;
        }
        throw new TlsFatalAlert((short) 40);
    }

    public void resumeHandshake() throws IOException {
        if (this.blocking) {
            if (!isHandshaking()) {
                throw new IllegalStateException("No handshake in progress");
            }
            blockForHandshake();
            return;
        }
        throw new IllegalStateException("Cannot use resumeHandshake() in non-blocking mode!");
    }

    protected RecordPreview safePreviewRecordHeader(byte[] bArr) throws IOException {
        try {
            return this.recordStream.previewRecordHeader(bArr);
        } catch (RuntimeException e) {
            handleException((short) 80, "Failed to read record", e);
            throw new TlsFatalAlert((short) 80, e);
        } catch (TlsFatalAlert e2) {
            handleException(e2.getAlertDescription(), "Failed to read record", e2);
            throw e2;
        } catch (IOException e3) {
            handleException((short) 80, "Failed to read record", e3);
            throw e3;
        }
    }

    protected boolean safeReadFullRecord(byte[] bArr, int i, int i2) throws IOException {
        try {
            return this.recordStream.readFullRecord(bArr, i, i2);
        } catch (RuntimeException e) {
            handleException((short) 80, "Failed to process record", e);
            throw new TlsFatalAlert((short) 80, e);
        } catch (TlsFatalAlert e2) {
            handleException(e2.getAlertDescription(), "Failed to process record", e2);
            throw e2;
        } catch (IOException e3) {
            handleException((short) 80, "Failed to process record", e3);
            throw e3;
        }
    }

    protected void safeReadRecord() throws IOException {
        try {
            if (this.recordStream.readRecord()) {
                return;
            }
            if (!this.appDataReady) {
                throw new TlsFatalAlert((short) 40);
            }
            handleFailure();
            throw new TlsNoCloseNotifyException();
        } catch (RuntimeException e) {
            handleException((short) 80, "Failed to read record", e);
            throw new TlsFatalAlert((short) 80, e);
        } catch (TlsFatalAlert e2) {
            handleException(e2.getAlertDescription(), "Failed to read record", e2);
            throw e2;
        } catch (TlsFatalAlertReceived e3) {
            throw e3;
        } catch (IOException e4) {
            handleException((short) 80, "Failed to read record", e4);
            throw e4;
        }
    }

    protected void safeWriteRecord(short s, byte[] bArr, int i, int i2) throws IOException {
        try {
            this.recordStream.writeRecord(s, bArr, i, i2);
        } catch (RuntimeException e) {
            handleException((short) 80, "Failed to write record", e);
            throw new TlsFatalAlert((short) 80, e);
        } catch (TlsFatalAlert e2) {
            handleException(e2.getAlertDescription(), "Failed to write record", e2);
            throw e2;
        } catch (IOException e3) {
            handleException((short) 80, "Failed to write record", e3);
            throw e3;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void send13CertificateMessage(Certificate certificate, OutputStream outputStream) throws IOException {
        if (certificate != null) {
            TlsContext context = getContext();
            SecurityParameters securityParametersHandshake = context.getSecurityParametersHandshake();
            if (securityParametersHandshake.getLocalCertificate() != null) {
                throw new TlsFatalAlert((short) 80);
            }
            HandshakeMessageOutput handshakeMessageOutput = new HandshakeMessageOutput((short) 11);
            certificate.encode(context, handshakeMessageOutput, outputStream);
            handshakeMessageOutput.send(this);
            securityParametersHandshake.localCertificate = certificate;
            return;
        }
        throw new TlsFatalAlert((short) 80);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void send13CertificateVerifyMessage(DigitallySigned digitallySigned) throws IOException {
        HandshakeMessageOutput handshakeMessageOutput = new HandshakeMessageOutput((short) 15);
        digitallySigned.encode(handshakeMessageOutput);
        handshakeMessageOutput.send(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void send13FinishedMessage() throws IOException {
        TlsContext context = getContext();
        SecurityParameters securityParametersHandshake = context.getSecurityParametersHandshake();
        byte[] calculateVerifyData = TlsUtils.calculateVerifyData(context, this.handshakeHash, context.isServer());
        securityParametersHandshake.localVerifyData = calculateVerifyData;
        if (securityParametersHandshake.getPeerVerifyData() == null) {
            securityParametersHandshake.tlsUnique = calculateVerifyData;
        }
        HandshakeMessageOutput.send(this, (short) 20, calculateVerifyData);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void sendCertificateMessage(Certificate certificate, OutputStream outputStream) throws IOException {
        TlsContext context = getContext();
        SecurityParameters securityParametersHandshake = context.getSecurityParametersHandshake();
        if (securityParametersHandshake.getLocalCertificate() == null) {
            if (certificate == null) {
                certificate = Certificate.EMPTY_CHAIN;
            }
            if (!certificate.isEmpty() || context.isServer() || !securityParametersHandshake.getNegotiatedVersion().isSSL()) {
                HandshakeMessageOutput handshakeMessageOutput = new HandshakeMessageOutput((short) 11);
                certificate.encode(context, handshakeMessageOutput, outputStream);
                handshakeMessageOutput.send(this);
            } else {
                raiseAlertWarning((short) 41, "SSLv3 client didn't provide credentials");
            }
            securityParametersHandshake.localCertificate = certificate;
            return;
        }
        throw new TlsFatalAlert((short) 80);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void sendChangeCipherSpec() throws IOException {
        sendChangeCipherSpecMessage();
        this.recordStream.enablePendingCipherWrite();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void sendChangeCipherSpecMessage() throws IOException {
        byte[] bArr = {1};
        safeWriteRecord((short) 20, bArr, 0, bArr.length);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void sendFinishedMessage() throws IOException {
        TlsContext context = getContext();
        SecurityParameters securityParametersHandshake = context.getSecurityParametersHandshake();
        byte[] calculateVerifyData = TlsUtils.calculateVerifyData(context, this.handshakeHash, context.isServer());
        securityParametersHandshake.localVerifyData = calculateVerifyData;
        if ((!this.resumedSession || securityParametersHandshake.isExtendedMasterSecret()) && securityParametersHandshake.getPeerVerifyData() == null) {
            securityParametersHandshake.tlsUnique = calculateVerifyData;
        }
        HandshakeMessageOutput.send(this, (short) 20, calculateVerifyData);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void sendSupplementalDataMessage(Vector vector) throws IOException {
        HandshakeMessageOutput handshakeMessageOutput = new HandshakeMessageOutput((short) 23);
        writeSupplementalData(handshakeMessageOutput, vector);
        handshakeMessageOutput.send(this);
    }

    public void setAppDataSplitMode(int i) {
        if (i < 0 || i > 2) {
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline49("Illegal appDataSplitMode mode: ", i));
        }
        this.appDataSplitMode = i;
    }

    public void setResumableHandshake(boolean z) {
        this.resumableHandshake = z;
    }

    public void writeApplicationData(byte[] bArr, int i, int i2) throws IOException {
        if (!this.closed) {
            if (!this.appDataReady) {
                throw new IllegalStateException("Cannot write application data until initial handshake completed.");
            }
            while (i2 > 0) {
                if (this.appDataSplitEnabled) {
                    int appDataSplitMode = getAppDataSplitMode();
                    if (appDataSplitMode != 1) {
                        if (appDataSplitMode != 2) {
                            safeWriteRecord((short) 23, bArr, i, 1);
                            i++;
                            i2--;
                        } else {
                            this.appDataSplitEnabled = false;
                        }
                    }
                    safeWriteRecord((short) 23, TlsUtils.EMPTY_BYTES, 0, 0);
                }
                if (i2 > 0) {
                    int min = Math.min(i2, this.recordStream.getPlaintextLimit());
                    safeWriteRecord((short) 23, bArr, i, min);
                    i += min;
                    i2 -= min;
                }
            }
            return;
        }
        throw new IOException("Cannot write application data on closed/failed TLS connection");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void writeHandshakeMessage(byte[] bArr, int i, int i2) throws IOException {
        if (i2 >= 4) {
            short readUint8 = TlsUtils.readUint8(bArr, i);
            if (readUint8 != 0 && readUint8 != 4 && readUint8 != 24) {
                this.handshakeHash.update(bArr, i, i2);
            }
            int i3 = 0;
            do {
                int min = Math.min(i2 - i3, this.recordStream.getPlaintextLimit());
                safeWriteRecord((short) 22, bArr, i + i3, min);
                i3 += min;
            } while (i3 < i2);
            return;
        }
        throw new TlsFatalAlert((short) 80);
    }
}
