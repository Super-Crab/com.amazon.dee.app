package org.apache.thrift.orig.transport;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.dee.app.data.reactnative.bridges.HttpClientModule;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import javax.security.sasl.SaslClient;
import javax.security.sasl.SaslException;
import javax.security.sasl.SaslServer;
import org.apache.thrift.orig.EncodingUtils;
import org.apache.thrift.orig.TByteArrayOutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/* loaded from: classes4.dex */
abstract class TSaslTransport extends TTransport {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    protected static final int DEFAULT_MAX_LENGTH = Integer.MAX_VALUE;
    private static final Logger LOGGER = LoggerFactory.getLogger(TSaslTransport.class);
    protected static final int MECHANISM_NAME_BYTES = 1;
    protected static final int PAYLOAD_LENGTH_BYTES = 4;
    protected static final int STATUS_BYTES = 1;
    private final byte[] messageHeader;
    private TMemoryInputTransport readBuffer;
    private SaslParticipant sasl;
    private boolean shouldWrap;
    protected TTransport underlyingTransport;
    private final TByteArrayOutputStream writeBuffer;

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes4.dex */
    public enum NegotiationStatus {
        START((byte) 1),
        OK((byte) 2),
        BAD((byte) 3),
        ERROR((byte) 4),
        COMPLETE((byte) 5);
        
        private static final Map<Byte, NegotiationStatus> reverseMap = new HashMap();
        private final byte value;

        static {
            NegotiationStatus[] negotiationStatusArr;
            for (NegotiationStatus negotiationStatus : (NegotiationStatus[]) NegotiationStatus.class.getEnumConstants()) {
                reverseMap.put(Byte.valueOf(negotiationStatus.getValue()), negotiationStatus);
            }
        }

        NegotiationStatus(byte b) {
            this.value = b;
        }

        public static NegotiationStatus byValue(byte b) {
            return reverseMap.get(Byte.valueOf(b));
        }

        public byte getValue() {
            return this.value;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes4.dex */
    public static class SaslResponse {
        public byte[] payload;
        public NegotiationStatus status;

        public SaslResponse(NegotiationStatus negotiationStatus, byte[] bArr) {
            this.status = negotiationStatus;
            this.payload = bArr;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes4.dex */
    public enum SaslRole {
        SERVER,
        CLIENT
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public TSaslTransport(TTransport tTransport) {
        this.shouldWrap = false;
        this.readBuffer = new TMemoryInputTransport();
        this.writeBuffer = new TByteArrayOutputStream(1024);
        this.messageHeader = new byte[5];
        this.underlyingTransport = tTransport;
    }

    private void readFrame() throws TTransportException, SaslException {
        int readLength = readLength();
        if (readLength >= 0) {
            byte[] bArr = new byte[readLength];
            LOGGER.debug("{}: reading data length: {}", getRole(), Integer.valueOf(readLength));
            this.underlyingTransport.readAll(bArr, 0, readLength);
            if (this.shouldWrap) {
                bArr = this.sasl.unwrap(bArr, 0, bArr.length);
                LOGGER.debug("data length after unwrap: {}", Integer.valueOf(bArr.length));
            }
            this.readBuffer.reset(bArr);
            return;
        }
        throw new TTransportException(GeneratedOutlineSupport1.outline52("Read a negative frame size (", readLength, ")!"));
    }

    @Override // org.apache.thrift.orig.transport.TTransport
    public void close() {
        this.underlyingTransport.close();
        try {
            this.sasl.dispose();
        } catch (SaslException unused) {
        }
    }

    @Override // org.apache.thrift.orig.transport.TTransport
    public void flush() throws TTransportException {
        byte[] bArr = this.writeBuffer.get();
        int len = this.writeBuffer.len();
        this.writeBuffer.reset();
        if (this.shouldWrap) {
            LOGGER.debug("data length before wrap: {}", Integer.valueOf(len));
            try {
                bArr = this.sasl.wrap(bArr, 0, len);
                len = bArr.length;
            } catch (SaslException e) {
                throw new TTransportException((Throwable) e);
            }
        }
        LOGGER.debug("writing data length: {}", Integer.valueOf(len));
        writeLength(len);
        this.underlyingTransport.write(bArr, 0, len);
        this.underlyingTransport.flush();
    }

    protected abstract SaslRole getRole();

    public SaslClient getSaslClient() {
        return this.sasl.saslClient;
    }

    public SaslServer getSaslServer() {
        return this.sasl.saslServer;
    }

    public TTransport getUnderlyingTransport() {
        return this.underlyingTransport;
    }

    protected abstract void handleSaslStartMessage() throws TTransportException, SaslException;

    @Override // org.apache.thrift.orig.transport.TTransport
    public boolean isOpen() {
        SaslParticipant saslParticipant;
        return this.underlyingTransport.isOpen() && (saslParticipant = this.sasl) != null && saslParticipant.isComplete();
    }

    @Override // org.apache.thrift.orig.transport.TTransport
    public void open() throws TTransportException {
        LOGGER.debug("opening transport {}", this);
        SaslParticipant saslParticipant = this.sasl;
        if (saslParticipant != null && saslParticipant.isComplete()) {
            throw new TTransportException("SASL transport already open");
        }
        if (!this.underlyingTransport.isOpen()) {
            this.underlyingTransport.open();
        }
        try {
            handleSaslStartMessage();
            LOGGER.debug("{}: Start message handled", getRole());
            SaslResponse saslResponse = null;
            while (true) {
                if (this.sasl.isComplete()) {
                    break;
                }
                saslResponse = receiveSaslMessage();
                if (saslResponse.status != NegotiationStatus.COMPLETE && saslResponse.status != NegotiationStatus.OK) {
                    throw new TTransportException("Expected COMPLETE or OK, got " + saslResponse.status);
                }
                byte[] evaluateChallengeOrResponse = this.sasl.evaluateChallengeOrResponse(saslResponse.payload);
                if (saslResponse.status == NegotiationStatus.COMPLETE && getRole() == SaslRole.CLIENT) {
                    LOGGER.debug("{}: All done!", getRole());
                    break;
                }
                sendSaslMessage(this.sasl.isComplete() ? NegotiationStatus.COMPLETE : NegotiationStatus.OK, evaluateChallengeOrResponse);
            }
            LOGGER.debug("{}: Main negotiation loop complete", getRole());
            if (getRole() == SaslRole.CLIENT && (saslResponse == null || saslResponse.status == NegotiationStatus.OK)) {
                LOGGER.debug("{}: SASL Client receiving last message", getRole());
                SaslResponse receiveSaslMessage = receiveSaslMessage();
                if (receiveSaslMessage.status != NegotiationStatus.COMPLETE) {
                    throw new TTransportException("Expected SASL COMPLETE, but got " + receiveSaslMessage.status);
                }
            }
        } catch (SaslException e) {
            try {
                LOGGER.error("SASL negotiation failure", e);
                sendAndThrowMessage(NegotiationStatus.BAD, e.getMessage());
            } finally {
                this.underlyingTransport.close();
            }
        }
        String str = (String) this.sasl.getNegotiatedProperty("javax.security.sasl.qop");
        if (str == null || str.equalsIgnoreCase(HttpClientModule.ElementsRequestKey.AUTH)) {
            return;
        }
        this.shouldWrap = true;
    }

    @Override // org.apache.thrift.orig.transport.TTransport
    public int read(byte[] bArr, int i, int i2) throws TTransportException {
        if (isOpen()) {
            int read = this.readBuffer.read(bArr, i, i2);
            if (read > 0) {
                return read;
            }
            try {
                readFrame();
                return this.readBuffer.read(bArr, i, i2);
            } catch (SaslException e) {
                throw new TTransportException((Throwable) e);
            }
        }
        throw new TTransportException("SASL authentication not complete");
    }

    protected int readLength() throws TTransportException {
        byte[] bArr = new byte[4];
        this.underlyingTransport.readAll(bArr, 0, bArr.length);
        return EncodingUtils.decodeBigEndian(bArr);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public SaslResponse receiveSaslMessage() throws TTransportException {
        TTransport tTransport = this.underlyingTransport;
        byte[] bArr = this.messageHeader;
        tTransport.readAll(bArr, 0, bArr.length);
        byte[] bArr2 = this.messageHeader;
        byte b = bArr2[0];
        byte[] bArr3 = new byte[EncodingUtils.decodeBigEndian(bArr2, 1)];
        this.underlyingTransport.readAll(bArr3, 0, bArr3.length);
        NegotiationStatus byValue = NegotiationStatus.byValue(b);
        if (byValue == null) {
            sendAndThrowMessage(NegotiationStatus.ERROR, GeneratedOutlineSupport1.outline49("Invalid status ", b));
        } else if (byValue == NegotiationStatus.BAD || byValue == NegotiationStatus.ERROR) {
            try {
                String str = new String(bArr3, "UTF-8");
                throw new TTransportException("Peer indicated failure: " + str);
            } catch (UnsupportedEncodingException e) {
                throw new TTransportException(e);
            }
        }
        if (LOGGER.isDebugEnabled()) {
            Logger logger = LOGGER;
            logger.debug(getRole() + ": Received message with status {} and payload length {}", byValue, Integer.valueOf(bArr3.length));
        }
        return new SaslResponse(byValue, bArr3);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void sendAndThrowMessage(NegotiationStatus negotiationStatus, String str) throws TTransportException {
        try {
            sendSaslMessage(negotiationStatus, str.getBytes());
        } catch (Exception e) {
            LOGGER.warn("Could not send failure response", (Throwable) e);
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            sb.append("\nAlso, could not send response: ");
            str = GeneratedOutlineSupport1.outline42(e, sb);
        }
        throw new TTransportException(str);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void sendSaslMessage(NegotiationStatus negotiationStatus, byte[] bArr) throws TTransportException {
        if (bArr == null) {
            bArr = new byte[0];
        }
        this.messageHeader[0] = negotiationStatus.getValue();
        EncodingUtils.encodeBigEndian(bArr.length, this.messageHeader, 1);
        if (LOGGER.isDebugEnabled()) {
            Logger logger = LOGGER;
            logger.debug(getRole() + ": Writing message with status {} and payload length {}", negotiationStatus, Integer.valueOf(bArr.length));
        }
        this.underlyingTransport.write(this.messageHeader);
        this.underlyingTransport.write(bArr);
        this.underlyingTransport.flush();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setSaslServer(SaslServer saslServer) {
        this.sasl = new SaslParticipant(saslServer);
    }

    @Override // org.apache.thrift.orig.transport.TTransport
    public void write(byte[] bArr, int i, int i2) throws TTransportException {
        if (isOpen()) {
            this.writeBuffer.write(bArr, i, i2);
            return;
        }
        throw new TTransportException("SASL authentication not complete");
    }

    protected void writeLength(int i) throws TTransportException {
        byte[] bArr = new byte[4];
        TFramedTransport.encodeFrameSize(i, bArr);
        this.underlyingTransport.write(bArr);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes4.dex */
    public static class SaslParticipant {
        public SaslClient saslClient;
        public SaslServer saslServer;

        public SaslParticipant(SaslServer saslServer) {
            this.saslServer = saslServer;
        }

        public void dispose() throws SaslException {
            SaslClient saslClient = this.saslClient;
            if (saslClient != null) {
                saslClient.dispose();
            } else {
                this.saslServer.dispose();
            }
        }

        public byte[] evaluateChallengeOrResponse(byte[] bArr) throws SaslException {
            SaslClient saslClient = this.saslClient;
            if (saslClient != null) {
                return saslClient.evaluateChallenge(bArr);
            }
            return this.saslServer.evaluateResponse(bArr);
        }

        public Object getNegotiatedProperty(String str) {
            SaslClient saslClient = this.saslClient;
            if (saslClient != null) {
                return saslClient.getNegotiatedProperty(str);
            }
            return this.saslServer.getNegotiatedProperty(str);
        }

        public boolean isComplete() {
            SaslClient saslClient = this.saslClient;
            if (saslClient != null) {
                return saslClient.isComplete();
            }
            return this.saslServer.isComplete();
        }

        public byte[] unwrap(byte[] bArr, int i, int i2) throws SaslException {
            SaslClient saslClient = this.saslClient;
            if (saslClient != null) {
                return saslClient.unwrap(bArr, i, i2);
            }
            return this.saslServer.unwrap(bArr, i, i2);
        }

        public byte[] wrap(byte[] bArr, int i, int i2) throws SaslException {
            SaslClient saslClient = this.saslClient;
            if (saslClient != null) {
                return saslClient.wrap(bArr, i, i2);
            }
            return this.saslServer.wrap(bArr, i, i2);
        }

        public SaslParticipant(SaslClient saslClient) {
            this.saslClient = saslClient;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public TSaslTransport(SaslClient saslClient, TTransport tTransport) {
        this.shouldWrap = false;
        this.readBuffer = new TMemoryInputTransport();
        this.writeBuffer = new TByteArrayOutputStream(1024);
        this.messageHeader = new byte[5];
        this.sasl = new SaslParticipant(saslClient);
        this.underlyingTransport = tTransport;
    }
}
