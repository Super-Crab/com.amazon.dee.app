package com.amazon.communication.gw;

import amazon.communication.Message;
import amazon.communication.MessageFactory;
import com.amazon.communication.ProtocolException;
import com.amazon.communication.gw.GatewayHandshakeAcknowledge;
import com.amazon.communication.gw.GatewayHandshakeInitiate;
import com.amazon.communication.gw.GatewayHandshakeMessage;
import com.amazon.dp.logger.DPFormattedMessage;
import com.amazon.dp.logger.DPLogger;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.dp.framework.ByteBufferOutputStream;
import com.dp.framework.CodecException;
import com.dp.framework.StreamCodec;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.PostConstruct;
/* loaded from: classes12.dex */
public class GatewayHandshakeProtocol {
    private static final String FOOTER = "END";
    private static final int FOOTER_SIZE = 3;
    private static final String PROTOCOL_VERSION = "1.0";
    private static final DPLogger log = new DPLogger("TComm.GatewayHandshakeProtocol");
    private StreamCodec mStreamCodec;

    /* renamed from: com.amazon.communication.gw.GatewayHandshakeProtocol$1  reason: invalid class name */
    /* loaded from: classes12.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$communication$gw$GatewayHandshakeMessage$Type = new int[GatewayHandshakeMessage.Type.values().length];

        static {
            try {
                $SwitchMap$com$amazon$communication$gw$GatewayHandshakeMessage$Type[GatewayHandshakeMessage.Type.Initiate.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$communication$gw$GatewayHandshakeMessage$Type[GatewayHandshakeMessage.Type.Acknowledge.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public enum Section {
        ActiveCustomer("ACS"),
        AccountInformation("ACI"),
        AccountResult("ACR");
        
        public static final int SIZE = 3;
        private String mToken;

        Section(String str) {
            this.mToken = str;
        }

        public static Section fromToken(String str) {
            Section[] values;
            for (Section section : values()) {
                if (section.mToken.equals(str)) {
                    return section;
                }
            }
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline72("No enumeration found for token ", str));
        }

        @Override // java.lang.Enum
        public String toString() {
            return this.mToken;
        }
    }

    private GatewayHandshakeMessage decodeAcknowledge(InputStream inputStream) throws ProtocolException, CodecException {
        Section fromToken;
        String decodeString = this.mStreamCodec.decodeString(inputStream);
        if ("1.0".equals(decodeString)) {
            String decodeString2 = this.mStreamCodec.decodeString(inputStream);
            int decodeInt = this.mStreamCodec.decodeInt(inputStream);
            long decodeLong = this.mStreamCodec.decodeLong(inputStream);
            long decodeLong2 = this.mStreamCodec.decodeLong(inputStream);
            List list = null;
            while (true) {
                String decodeAsciiString = this.mStreamCodec.decodeAsciiString(inputStream, 3);
                if (FOOTER.equals(decodeAsciiString)) {
                    if (list == null) {
                        list = Collections.emptyList();
                    }
                    return new GatewayHandshakeAcknowledge(decodeInt, decodeString2, decodeLong, decodeLong2, list);
                }
                int decodeInt2 = this.mStreamCodec.decodeInt(inputStream);
                try {
                    fromToken = Section.fromToken(decodeAsciiString);
                } catch (IllegalArgumentException unused) {
                    log.info("decodeAcknowledge", "Unknown section header, skipping", "sectionHeader", decodeAsciiString, "sectionSize", Integer.valueOf(decodeInt2));
                    try {
                        inputStream.skip(decodeInt2);
                    } catch (IOException e) {
                        throw new ProtocolException(e);
                    }
                }
                if (Section.AccountResult.equals(fromToken)) {
                    int decodeInt3 = this.mStreamCodec.decodeInt(inputStream);
                    if (decodeInt3 > 0) {
                        list = new ArrayList(decodeInt3);
                        for (int i = 0; i < decodeInt3; i++) {
                            list.add(new GatewayHandshakeAcknowledge.AccountResult(this.mStreamCodec.decodeString(inputStream), this.mStreamCodec.decodeInt(inputStream)));
                        }
                    }
                } else {
                    throw new ProtocolException("Unknown section " + fromToken);
                }
            }
        } else {
            throw new ProtocolException(GeneratedOutlineSupport1.outline72("Unknown protocol version ", decodeString));
        }
    }

    private GatewayHandshakeMessage decodeInitiate(InputStream inputStream) throws ProtocolException, CodecException {
        String decodeString = this.mStreamCodec.decodeString(inputStream);
        if ("1.0".equals(decodeString)) {
            String decodeString2 = this.mStreamCodec.decodeString(inputStream);
            long decodeLong = this.mStreamCodec.decodeLong(inputStream);
            List list = null;
            String str = null;
            String str2 = null;
            String str3 = null;
            while (true) {
                String decodeAsciiString = this.mStreamCodec.decodeAsciiString(inputStream, 3);
                if (FOOTER.equals(decodeAsciiString)) {
                    if (list == null) {
                        list = Collections.emptyList();
                    }
                    return new GatewayHandshakeInitiate(decodeString2, decodeLong, str, str2, str3, list);
                }
                int decodeInt = this.mStreamCodec.decodeInt(inputStream);
                try {
                    Section fromToken = Section.fromToken(decodeAsciiString);
                    if (Section.AccountInformation.equals(fromToken)) {
                        int decodeInt2 = this.mStreamCodec.decodeInt(inputStream);
                        str = this.mStreamCodec.decodeString(inputStream);
                        str2 = this.mStreamCodec.decodeString(inputStream);
                        if (decodeInt2 > 0) {
                            list = new ArrayList(decodeInt2);
                            for (int i = 0; i < decodeInt2; i++) {
                                list.add(new GatewayHandshakeInitiate.AccountInformation(this.mStreamCodec.decodeString(inputStream), this.mStreamCodec.decodeString(inputStream), this.mStreamCodec.decodeByteArray(inputStream)));
                            }
                        }
                    } else if (Section.ActiveCustomer.equals(fromToken)) {
                        str3 = this.mStreamCodec.decodeString(inputStream);
                    } else {
                        throw new ProtocolException("Unknown section " + fromToken);
                    }
                } catch (IllegalArgumentException unused) {
                    log.info("decodeInitiate", "Unknown section header, skipping", "sectionHeader", decodeAsciiString, "sectionSize", Integer.valueOf(decodeInt));
                    try {
                        inputStream.skip(decodeInt);
                    } catch (IOException e) {
                        throw new ProtocolException(e);
                    }
                }
            }
        } else {
            throw new ProtocolException(GeneratedOutlineSupport1.outline72("Unknown protocol version ", decodeString));
        }
    }

    private Message encodeAcknowledge(GatewayHandshakeAcknowledge gatewayHandshakeAcknowledge) throws ProtocolException, CodecException {
        int i = 0;
        log.debug("encodeAcknowledge", "Encoding message", "message", gatewayHandshakeAcknowledge);
        int sizeOfInt = this.mStreamCodec.getSizeOfInt();
        int sizeOfLong = this.mStreamCodec.getSizeOfLong();
        int size = gatewayHandshakeAcknowledge.getAccountResults() == null ? 0 : gatewayHandshakeAcknowledge.getAccountResults().size();
        int sizeOfDelimiter = (this.mStreamCodec.getSizeOfDelimiter() * 9) + gatewayHandshakeAcknowledge.getMessageId().length() + GatewayHandshakeMessage.Type.Acknowledge.toString().length() + sizeOfInt + 3 + sizeOfInt + sizeOfInt + sizeOfLong + sizeOfLong + 3;
        if (size > 0) {
            int sizeOfDelimiter2 = (this.mStreamCodec.getSizeOfDelimiter() * 3) + sizeOfInt + 3 + sizeOfInt + 0;
            for (GatewayHandshakeAcknowledge.AccountResult accountResult : gatewayHandshakeAcknowledge.getAccountResults()) {
                sizeOfDelimiter2 += (this.mStreamCodec.getSizeOfDelimiter() * 3) + accountResult.directedCustomerId.length() + sizeOfInt + sizeOfInt;
            }
            sizeOfDelimiter += sizeOfDelimiter2;
            i = sizeOfDelimiter2;
        }
        ByteBuffer allocate = ByteBuffer.allocate(sizeOfDelimiter);
        ByteBufferOutputStream byteBufferOutputStream = new ByteBufferOutputStream(allocate);
        this.mStreamCodec.encodeAsciiString(GatewayHandshakeMessage.Type.Acknowledge.toString(), byteBufferOutputStream);
        this.mStreamCodec.encodeString("1.0", byteBufferOutputStream);
        this.mStreamCodec.encodeString(gatewayHandshakeAcknowledge.getMessageId(), byteBufferOutputStream);
        this.mStreamCodec.encodeInt(gatewayHandshakeAcknowledge.getStatus(), byteBufferOutputStream);
        this.mStreamCodec.encodeLong(gatewayHandshakeAcknowledge.getInitiateTimestamp(), byteBufferOutputStream);
        this.mStreamCodec.encodeLong(gatewayHandshakeAcknowledge.getAcknowledgeTimestamp(), byteBufferOutputStream);
        if (size > 0) {
            this.mStreamCodec.encodeAsciiString(Section.AccountResult.toString(), byteBufferOutputStream);
            this.mStreamCodec.encodeInt(i, byteBufferOutputStream);
            this.mStreamCodec.encodeInt(size, byteBufferOutputStream);
            for (GatewayHandshakeAcknowledge.AccountResult accountResult2 : gatewayHandshakeAcknowledge.getAccountResults()) {
                this.mStreamCodec.encodeString(accountResult2.directedCustomerId, byteBufferOutputStream);
                this.mStreamCodec.encodeInt(accountResult2.status, byteBufferOutputStream);
            }
        }
        this.mStreamCodec.encodeAsciiString(FOOTER, byteBufferOutputStream);
        allocate.rewind();
        return MessageFactory.createMessage(allocate);
    }

    private Message encodeInitiate(GatewayHandshakeInitiate gatewayHandshakeInitiate) throws ProtocolException, CodecException {
        int i;
        int i2 = 0;
        log.debug("encodeInitiate", "Encoding message", "message", gatewayHandshakeInitiate);
        int sizeOfInt = this.mStreamCodec.getSizeOfInt();
        int sizeOfLong = this.mStreamCodec.getSizeOfLong();
        int size = gatewayHandshakeInitiate.getAccountInformation() == null ? 0 : gatewayHandshakeInitiate.getAccountInformation().size();
        int sizeOfDelimiter = (this.mStreamCodec.getSizeOfDelimiter() * 7) + gatewayHandshakeInitiate.getMessageId().length() + GatewayHandshakeMessage.Type.Initiate.toString().length() + sizeOfInt + 3 + sizeOfInt + sizeOfLong + 3;
        if (gatewayHandshakeInitiate.getActiveCustomerId() != null) {
            i = (this.mStreamCodec.getSizeOfDelimiter() * 4) + gatewayHandshakeInitiate.getActiveCustomerId().length() + sizeOfInt + 3 + sizeOfInt;
            sizeOfDelimiter += i;
            log.debug("encodeInitiate", "Encoding active account", "activeAccountSessionLength", Integer.valueOf(i));
        } else {
            i = 0;
        }
        if (size > 0) {
            int length = gatewayHandshakeInitiate.getSignatureAlgorithm().length() + gatewayHandshakeInitiate.getCorpusAlgorithm().length() + GeneratedOutlineSupport1.outline1(sizeOfInt, 3, sizeOfInt, sizeOfInt) + sizeOfInt;
            i2 = (this.mStreamCodec.getSizeOfDelimiter() * 7) + length + 0;
            for (GatewayHandshakeInitiate.AccountInformation accountInformation : gatewayHandshakeInitiate.getAccountInformation()) {
                i2 += (this.mStreamCodec.getSizeOfDelimiter() * 6) + accountInformation.token.length() + accountInformation.directedCustomerId.length() + sizeOfInt + sizeOfInt + sizeOfInt + accountInformation.signature.length;
            }
            sizeOfDelimiter += i2;
        }
        ByteBuffer allocate = ByteBuffer.allocate(sizeOfDelimiter);
        ByteBufferOutputStream byteBufferOutputStream = new ByteBufferOutputStream(allocate);
        this.mStreamCodec.encodeAsciiString(GatewayHandshakeMessage.Type.Initiate.toString(), byteBufferOutputStream);
        this.mStreamCodec.encodeString("1.0", byteBufferOutputStream);
        this.mStreamCodec.encodeString(gatewayHandshakeInitiate.getMessageId(), byteBufferOutputStream);
        this.mStreamCodec.encodeLong(gatewayHandshakeInitiate.getInitiateTimestamp(), byteBufferOutputStream);
        if (gatewayHandshakeInitiate.getActiveCustomerId() != null) {
            this.mStreamCodec.encodeAsciiString(Section.ActiveCustomer.toString(), byteBufferOutputStream);
            this.mStreamCodec.encodeInt(i, byteBufferOutputStream);
            this.mStreamCodec.encodeString(gatewayHandshakeInitiate.getActiveCustomerId(), byteBufferOutputStream);
        }
        if (size > 0) {
            this.mStreamCodec.encodeAsciiString(Section.AccountInformation.toString(), byteBufferOutputStream);
            this.mStreamCodec.encodeInt(i2, byteBufferOutputStream);
            this.mStreamCodec.encodeInt(size, byteBufferOutputStream);
            this.mStreamCodec.encodeString(gatewayHandshakeInitiate.getCorpusAlgorithm(), byteBufferOutputStream);
            this.mStreamCodec.encodeString(gatewayHandshakeInitiate.getSignatureAlgorithm(), byteBufferOutputStream);
            for (GatewayHandshakeInitiate.AccountInformation accountInformation2 : gatewayHandshakeInitiate.getAccountInformation()) {
                this.mStreamCodec.encodeString(accountInformation2.directedCustomerId, byteBufferOutputStream);
                this.mStreamCodec.encodeString(accountInformation2.token, byteBufferOutputStream);
                this.mStreamCodec.encodeByteArray(accountInformation2.signature, byteBufferOutputStream);
            }
        }
        this.mStreamCodec.encodeAsciiString(FOOTER, byteBufferOutputStream);
        allocate.rewind();
        return MessageFactory.createMessage(allocate);
    }

    public GatewayHandshakeMessage decode(Message message) throws ProtocolException {
        Object obj;
        if (message != null) {
            InputStream payload = message.getPayload();
            try {
                try {
                    try {
                        GatewayHandshakeMessage.Type fromToken = GatewayHandshakeMessage.Type.fromToken(this.mStreamCodec.decodeAsciiString(payload, 3));
                        int ordinal = fromToken.ordinal();
                        if (ordinal == 0) {
                            GatewayHandshakeMessage decodeInitiate = decodeInitiate(payload);
                            try {
                                payload.close();
                            } catch (IOException unused) {
                            }
                            return decodeInitiate;
                        } else if (ordinal == 1) {
                            GatewayHandshakeMessage decodeAcknowledge = decodeAcknowledge(payload);
                            try {
                                payload.close();
                            } catch (IOException unused2) {
                            }
                            return decodeAcknowledge;
                        } else {
                            throw new IllegalArgumentException("Unknown messageType " + fromToken);
                        }
                    } catch (IllegalArgumentException e) {
                        throw new ProtocolException(DPFormattedMessage.toDPFormat("decode", "Unknown messageTypeString", "messageTypeString", obj), e);
                    }
                } catch (CodecException e2) {
                    throw new ProtocolException(e2);
                }
            } catch (Throwable th) {
                try {
                    payload.close();
                } catch (IOException unused3) {
                }
                throw th;
            }
        }
        throw new IllegalArgumentException("Message must not be null");
    }

    public Message encode(GatewayHandshakeMessage gatewayHandshakeMessage) throws ProtocolException {
        log.debug("encode", "Encoding message", "message", gatewayHandshakeMessage);
        if (gatewayHandshakeMessage != null) {
            try {
                int ordinal = gatewayHandshakeMessage.getType().ordinal();
                if (ordinal == 0) {
                    return encodeInitiate((GatewayHandshakeInitiate) gatewayHandshakeMessage);
                }
                if (ordinal == 1) {
                    return encodeAcknowledge((GatewayHandshakeAcknowledge) gatewayHandshakeMessage);
                }
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Unknown message type: ");
                outline107.append(gatewayHandshakeMessage.getType());
                throw new IllegalArgumentException(outline107.toString());
            } catch (CodecException e) {
                throw new ProtocolException(e);
            }
        }
        throw new IllegalArgumentException("Message must not be null");
    }

    @PostConstruct
    public void initialize() {
        if (this.mStreamCodec != null) {
            return;
        }
        throw new IllegalArgumentException("StreamCodec must not be null");
    }

    public void setStreamCodec(StreamCodec streamCodec) {
        this.mStreamCodec = streamCodec;
    }
}
