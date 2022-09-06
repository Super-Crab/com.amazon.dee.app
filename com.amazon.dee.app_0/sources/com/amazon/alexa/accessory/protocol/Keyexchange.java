package com.amazon.alexa.accessory.protocol;

import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.List;
/* loaded from: classes6.dex */
public final class Keyexchange {

    /* renamed from: com.amazon.alexa.accessory.protocol.Keyexchange$1  reason: invalid class name */
    /* loaded from: classes6.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke = new int[GeneratedMessageLite.MethodToInvoke.values().length];

        static {
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.NEW_MUTABLE_INSTANCE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.IS_INITIALIZED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.MAKE_IMMUTABLE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.NEW_BUILDER.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.VISIT.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.MERGE_FROM_STREAM.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.GET_DEFAULT_INSTANCE.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.GET_PARSER.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.GET_MEMOIZED_IS_INITIALIZED.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.SET_MEMOIZED_IS_INITIALIZED.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
        }
    }

    /* loaded from: classes6.dex */
    public static final class AcknowledgeHandshake extends GeneratedMessageLite<AcknowledgeHandshake, Builder> implements AcknowledgeHandshakeOrBuilder {
        public static final int COUNTER_SEED_FIELD_NUMBER = 3;
        private static final AcknowledgeHandshake DEFAULT_INSTANCE = new AcknowledgeHandshake();
        public static final int KEY_FIELD_NUMBER = 5;
        public static final int NONCE_APP_FIELD_NUMBER = 4;
        public static final int ODC_HMAC_FIELD_NUMBER = 7;
        private static volatile Parser<AcknowledgeHandshake> PARSER = null;
        public static final int SELECTED_CIPHER_SUITE_FIELD_NUMBER = 2;
        public static final int SELECTED_PROTOCOL_VERSION_FIELD_NUMBER = 1;
        public static final int VALIDATION_HMAC_FIELD_NUMBER = 6;
        private MessageCounter counterSeed_;
        private ByteString key_;
        private ByteString nonceApp_;
        private ByteString odcHmac_;
        private int selectedCipherSuite_;
        private int selectedProtocolVersion_;
        private ByteString validationHmac_;

        /* loaded from: classes6.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<AcknowledgeHandshake, Builder> implements AcknowledgeHandshakeOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                this();
            }

            public Builder clearCounterSeed() {
                copyOnWrite();
                ((AcknowledgeHandshake) this.instance).clearCounterSeed();
                return this;
            }

            public Builder clearKey() {
                copyOnWrite();
                ((AcknowledgeHandshake) this.instance).clearKey();
                return this;
            }

            public Builder clearNonceApp() {
                copyOnWrite();
                ((AcknowledgeHandshake) this.instance).clearNonceApp();
                return this;
            }

            public Builder clearOdcHmac() {
                copyOnWrite();
                ((AcknowledgeHandshake) this.instance).clearOdcHmac();
                return this;
            }

            public Builder clearSelectedCipherSuite() {
                copyOnWrite();
                ((AcknowledgeHandshake) this.instance).clearSelectedCipherSuite();
                return this;
            }

            public Builder clearSelectedProtocolVersion() {
                copyOnWrite();
                ((AcknowledgeHandshake) this.instance).clearSelectedProtocolVersion();
                return this;
            }

            public Builder clearValidationHmac() {
                copyOnWrite();
                ((AcknowledgeHandshake) this.instance).clearValidationHmac();
                return this;
            }

            @Override // com.amazon.alexa.accessory.protocol.Keyexchange.AcknowledgeHandshakeOrBuilder
            public MessageCounter getCounterSeed() {
                return ((AcknowledgeHandshake) this.instance).getCounterSeed();
            }

            @Override // com.amazon.alexa.accessory.protocol.Keyexchange.AcknowledgeHandshakeOrBuilder
            public ByteString getKey() {
                return ((AcknowledgeHandshake) this.instance).getKey();
            }

            @Override // com.amazon.alexa.accessory.protocol.Keyexchange.AcknowledgeHandshakeOrBuilder
            public ByteString getNonceApp() {
                return ((AcknowledgeHandshake) this.instance).getNonceApp();
            }

            @Override // com.amazon.alexa.accessory.protocol.Keyexchange.AcknowledgeHandshakeOrBuilder
            public ByteString getOdcHmac() {
                return ((AcknowledgeHandshake) this.instance).getOdcHmac();
            }

            @Override // com.amazon.alexa.accessory.protocol.Keyexchange.AcknowledgeHandshakeOrBuilder
            public CipherSuite getSelectedCipherSuite() {
                return ((AcknowledgeHandshake) this.instance).getSelectedCipherSuite();
            }

            @Override // com.amazon.alexa.accessory.protocol.Keyexchange.AcknowledgeHandshakeOrBuilder
            public int getSelectedCipherSuiteValue() {
                return ((AcknowledgeHandshake) this.instance).getSelectedCipherSuiteValue();
            }

            @Override // com.amazon.alexa.accessory.protocol.Keyexchange.AcknowledgeHandshakeOrBuilder
            public int getSelectedProtocolVersion() {
                return ((AcknowledgeHandshake) this.instance).getSelectedProtocolVersion();
            }

            @Override // com.amazon.alexa.accessory.protocol.Keyexchange.AcknowledgeHandshakeOrBuilder
            public ByteString getValidationHmac() {
                return ((AcknowledgeHandshake) this.instance).getValidationHmac();
            }

            @Override // com.amazon.alexa.accessory.protocol.Keyexchange.AcknowledgeHandshakeOrBuilder
            public boolean hasCounterSeed() {
                return ((AcknowledgeHandshake) this.instance).hasCounterSeed();
            }

            public Builder mergeCounterSeed(MessageCounter messageCounter) {
                copyOnWrite();
                ((AcknowledgeHandshake) this.instance).mergeCounterSeed(messageCounter);
                return this;
            }

            public Builder setCounterSeed(MessageCounter messageCounter) {
                copyOnWrite();
                ((AcknowledgeHandshake) this.instance).setCounterSeed(messageCounter);
                return this;
            }

            public Builder setKey(ByteString byteString) {
                copyOnWrite();
                ((AcknowledgeHandshake) this.instance).setKey(byteString);
                return this;
            }

            public Builder setNonceApp(ByteString byteString) {
                copyOnWrite();
                ((AcknowledgeHandshake) this.instance).setNonceApp(byteString);
                return this;
            }

            public Builder setOdcHmac(ByteString byteString) {
                copyOnWrite();
                ((AcknowledgeHandshake) this.instance).setOdcHmac(byteString);
                return this;
            }

            public Builder setSelectedCipherSuite(CipherSuite cipherSuite) {
                copyOnWrite();
                ((AcknowledgeHandshake) this.instance).setSelectedCipherSuite(cipherSuite);
                return this;
            }

            public Builder setSelectedCipherSuiteValue(int i) {
                copyOnWrite();
                ((AcknowledgeHandshake) this.instance).setSelectedCipherSuiteValue(i);
                return this;
            }

            public Builder setSelectedProtocolVersion(int i) {
                copyOnWrite();
                ((AcknowledgeHandshake) this.instance).setSelectedProtocolVersion(i);
                return this;
            }

            public Builder setValidationHmac(ByteString byteString) {
                copyOnWrite();
                ((AcknowledgeHandshake) this.instance).setValidationHmac(byteString);
                return this;
            }

            private Builder() {
                super(AcknowledgeHandshake.DEFAULT_INSTANCE);
            }

            public Builder setCounterSeed(MessageCounter.Builder builder) {
                copyOnWrite();
                ((AcknowledgeHandshake) this.instance).setCounterSeed(builder);
                return this;
            }
        }

        static {
            DEFAULT_INSTANCE.makeImmutable();
        }

        private AcknowledgeHandshake() {
            ByteString byteString = ByteString.EMPTY;
            this.nonceApp_ = byteString;
            this.key_ = byteString;
            this.validationHmac_ = byteString;
            this.odcHmac_ = byteString;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearCounterSeed() {
            this.counterSeed_ = null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearKey() {
            this.key_ = getDefaultInstance().getKey();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearNonceApp() {
            this.nonceApp_ = getDefaultInstance().getNonceApp();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearOdcHmac() {
            this.odcHmac_ = getDefaultInstance().getOdcHmac();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearSelectedCipherSuite() {
            this.selectedCipherSuite_ = 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearSelectedProtocolVersion() {
            this.selectedProtocolVersion_ = 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearValidationHmac() {
            this.validationHmac_ = getDefaultInstance().getValidationHmac();
        }

        public static AcknowledgeHandshake getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeCounterSeed(MessageCounter messageCounter) {
            MessageCounter messageCounter2 = this.counterSeed_;
            if (messageCounter2 != null && messageCounter2 != MessageCounter.getDefaultInstance()) {
                this.counterSeed_ = MessageCounter.newBuilder(this.counterSeed_).mergeFrom((MessageCounter.Builder) messageCounter).mo10085buildPartial();
            } else {
                this.counterSeed_ = messageCounter;
            }
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static AcknowledgeHandshake parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (AcknowledgeHandshake) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static AcknowledgeHandshake parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (AcknowledgeHandshake) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<AcknowledgeHandshake> parser() {
            return DEFAULT_INSTANCE.mo9954getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setCounterSeed(MessageCounter messageCounter) {
            if (messageCounter != null) {
                this.counterSeed_ = messageCounter;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setKey(ByteString byteString) {
            if (byteString != null) {
                this.key_ = byteString;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setNonceApp(ByteString byteString) {
            if (byteString != null) {
                this.nonceApp_ = byteString;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setOdcHmac(ByteString byteString) {
            if (byteString != null) {
                this.odcHmac_ = byteString;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setSelectedCipherSuite(CipherSuite cipherSuite) {
            if (cipherSuite != null) {
                this.selectedCipherSuite_ = cipherSuite.getNumber();
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setSelectedCipherSuiteValue(int i) {
            this.selectedCipherSuite_ = i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setSelectedProtocolVersion(int i) {
            this.selectedProtocolVersion_ = i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setValidationHmac(ByteString byteString) {
            if (byteString != null) {
                this.validationHmac_ = byteString;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            boolean z = false;
            switch (methodToInvoke.ordinal()) {
                case 0:
                    return DEFAULT_INSTANCE;
                case 1:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) obj;
                    AcknowledgeHandshake acknowledgeHandshake = (AcknowledgeHandshake) obj2;
                    this.selectedProtocolVersion_ = visitor.visitInt(this.selectedProtocolVersion_ != 0, this.selectedProtocolVersion_, acknowledgeHandshake.selectedProtocolVersion_ != 0, acknowledgeHandshake.selectedProtocolVersion_);
                    this.selectedCipherSuite_ = visitor.visitInt(this.selectedCipherSuite_ != 0, this.selectedCipherSuite_, acknowledgeHandshake.selectedCipherSuite_ != 0, acknowledgeHandshake.selectedCipherSuite_);
                    this.counterSeed_ = (MessageCounter) visitor.visitMessage(this.counterSeed_, acknowledgeHandshake.counterSeed_);
                    this.nonceApp_ = visitor.visitByteString(this.nonceApp_ != ByteString.EMPTY, this.nonceApp_, acknowledgeHandshake.nonceApp_ != ByteString.EMPTY, acknowledgeHandshake.nonceApp_);
                    this.key_ = visitor.visitByteString(this.key_ != ByteString.EMPTY, this.key_, acknowledgeHandshake.key_ != ByteString.EMPTY, acknowledgeHandshake.key_);
                    this.validationHmac_ = visitor.visitByteString(this.validationHmac_ != ByteString.EMPTY, this.validationHmac_, acknowledgeHandshake.validationHmac_ != ByteString.EMPTY, acknowledgeHandshake.validationHmac_);
                    boolean z2 = this.odcHmac_ != ByteString.EMPTY;
                    ByteString byteString = this.odcHmac_;
                    if (acknowledgeHandshake.odcHmac_ != ByteString.EMPTY) {
                        z = true;
                    }
                    this.odcHmac_ = visitor.visitByteString(z2, byteString, z, acknowledgeHandshake.odcHmac_);
                    GeneratedMessageLite.MergeFromVisitor mergeFromVisitor = GeneratedMessageLite.MergeFromVisitor.INSTANCE;
                    return this;
                case 2:
                    return (byte) 1;
                case 3:
                    return null;
                case 4:
                    CodedInputStream codedInputStream = (CodedInputStream) obj;
                    ExtensionRegistryLite extensionRegistryLite = (ExtensionRegistryLite) obj2;
                    if (extensionRegistryLite == null) {
                        throw new NullPointerException();
                    }
                    while (!z) {
                        try {
                            int readTag = codedInputStream.readTag();
                            if (readTag != 0) {
                                if (readTag == 8) {
                                    this.selectedProtocolVersion_ = codedInputStream.readUInt32();
                                } else if (readTag == 16) {
                                    this.selectedCipherSuite_ = codedInputStream.readEnum();
                                } else if (readTag == 26) {
                                    MessageCounter.Builder mo10081toBuilder = this.counterSeed_ != null ? this.counterSeed_.mo10081toBuilder() : null;
                                    this.counterSeed_ = (MessageCounter) codedInputStream.readMessage(MessageCounter.parser(), extensionRegistryLite);
                                    if (mo10081toBuilder != null) {
                                        mo10081toBuilder.mergeFrom((MessageCounter.Builder) this.counterSeed_);
                                        this.counterSeed_ = mo10081toBuilder.mo10085buildPartial();
                                    }
                                } else if (readTag == 34) {
                                    this.nonceApp_ = codedInputStream.readBytes();
                                } else if (readTag == 42) {
                                    this.key_ = codedInputStream.readBytes();
                                } else if (readTag == 50) {
                                    this.validationHmac_ = codedInputStream.readBytes();
                                } else if (readTag != 58) {
                                    if (!parseUnknownField(readTag, codedInputStream)) {
                                    }
                                } else {
                                    this.odcHmac_ = codedInputStream.readBytes();
                                }
                            }
                            z = true;
                        } catch (InvalidProtocolBufferException e) {
                            throw new RuntimeException(e.setUnfinishedMessage(this));
                        } catch (IOException e2) {
                            throw new RuntimeException(new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this));
                        }
                    }
                    break;
                case 5:
                    return null;
                case 6:
                    return new AcknowledgeHandshake();
                case 7:
                    return new Builder(null);
                case 8:
                    break;
                case 9:
                    if (PARSER == null) {
                        synchronized (AcknowledgeHandshake.class) {
                            if (PARSER == null) {
                                PARSER = new GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
                            }
                        }
                    }
                    return PARSER;
                default:
                    throw new UnsupportedOperationException();
            }
            return DEFAULT_INSTANCE;
        }

        @Override // com.amazon.alexa.accessory.protocol.Keyexchange.AcknowledgeHandshakeOrBuilder
        public MessageCounter getCounterSeed() {
            MessageCounter messageCounter = this.counterSeed_;
            return messageCounter == null ? MessageCounter.getDefaultInstance() : messageCounter;
        }

        @Override // com.amazon.alexa.accessory.protocol.Keyexchange.AcknowledgeHandshakeOrBuilder
        public ByteString getKey() {
            return this.key_;
        }

        @Override // com.amazon.alexa.accessory.protocol.Keyexchange.AcknowledgeHandshakeOrBuilder
        public ByteString getNonceApp() {
            return this.nonceApp_;
        }

        @Override // com.amazon.alexa.accessory.protocol.Keyexchange.AcknowledgeHandshakeOrBuilder
        public ByteString getOdcHmac() {
            return this.odcHmac_;
        }

        @Override // com.amazon.alexa.accessory.protocol.Keyexchange.AcknowledgeHandshakeOrBuilder
        public CipherSuite getSelectedCipherSuite() {
            CipherSuite forNumber = CipherSuite.forNumber(this.selectedCipherSuite_);
            return forNumber == null ? CipherSuite.UNRECOGNIZED : forNumber;
        }

        @Override // com.amazon.alexa.accessory.protocol.Keyexchange.AcknowledgeHandshakeOrBuilder
        public int getSelectedCipherSuiteValue() {
            return this.selectedCipherSuite_;
        }

        @Override // com.amazon.alexa.accessory.protocol.Keyexchange.AcknowledgeHandshakeOrBuilder
        public int getSelectedProtocolVersion() {
            return this.selectedProtocolVersion_;
        }

        @Override // com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            int i3 = this.selectedProtocolVersion_;
            if (i3 != 0) {
                i2 = 0 + CodedOutputStream.computeUInt32Size(1, i3);
            }
            if (this.selectedCipherSuite_ != CipherSuite.SYMMETRIC_WITH_AES_256_GCM_SHA256.getNumber()) {
                i2 += CodedOutputStream.computeEnumSize(2, this.selectedCipherSuite_);
            }
            if (this.counterSeed_ != null) {
                i2 += CodedOutputStream.computeMessageSize(3, getCounterSeed());
            }
            if (!this.nonceApp_.isEmpty()) {
                i2 += CodedOutputStream.computeBytesSize(4, this.nonceApp_);
            }
            if (!this.key_.isEmpty()) {
                i2 += CodedOutputStream.computeBytesSize(5, this.key_);
            }
            if (!this.validationHmac_.isEmpty()) {
                i2 += CodedOutputStream.computeBytesSize(6, this.validationHmac_);
            }
            if (!this.odcHmac_.isEmpty()) {
                i2 += CodedOutputStream.computeBytesSize(7, this.odcHmac_);
            }
            int serializedSize = this.unknownFields.getSerializedSize() + i2;
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.amazon.alexa.accessory.protocol.Keyexchange.AcknowledgeHandshakeOrBuilder
        public ByteString getValidationHmac() {
            return this.validationHmac_;
        }

        @Override // com.amazon.alexa.accessory.protocol.Keyexchange.AcknowledgeHandshakeOrBuilder
        public boolean hasCounterSeed() {
            return this.counterSeed_ != null;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            int i = this.selectedProtocolVersion_;
            if (i != 0) {
                codedOutputStream.writeUInt32(1, i);
            }
            if (this.selectedCipherSuite_ != CipherSuite.SYMMETRIC_WITH_AES_256_GCM_SHA256.getNumber()) {
                codedOutputStream.writeEnum(2, this.selectedCipherSuite_);
            }
            if (this.counterSeed_ != null) {
                codedOutputStream.writeMessage(3, getCounterSeed());
            }
            if (!this.nonceApp_.isEmpty()) {
                codedOutputStream.writeBytes(4, this.nonceApp_);
            }
            if (!this.key_.isEmpty()) {
                codedOutputStream.writeBytes(5, this.key_);
            }
            if (!this.validationHmac_.isEmpty()) {
                codedOutputStream.writeBytes(6, this.validationHmac_);
            }
            if (!this.odcHmac_.isEmpty()) {
                codedOutputStream.writeBytes(7, this.odcHmac_);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public static Builder newBuilder(AcknowledgeHandshake acknowledgeHandshake) {
            return DEFAULT_INSTANCE.createBuilder(acknowledgeHandshake);
        }

        public static AcknowledgeHandshake parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (AcknowledgeHandshake) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static AcknowledgeHandshake parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (AcknowledgeHandshake) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static AcknowledgeHandshake parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (AcknowledgeHandshake) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setCounterSeed(MessageCounter.Builder builder) {
            this.counterSeed_ = builder.mo10084build();
        }

        public static AcknowledgeHandshake parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (AcknowledgeHandshake) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static AcknowledgeHandshake parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (AcknowledgeHandshake) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static AcknowledgeHandshake parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (AcknowledgeHandshake) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static AcknowledgeHandshake parseFrom(InputStream inputStream) throws IOException {
            return (AcknowledgeHandshake) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static AcknowledgeHandshake parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (AcknowledgeHandshake) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static AcknowledgeHandshake parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (AcknowledgeHandshake) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static AcknowledgeHandshake parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (AcknowledgeHandshake) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes6.dex */
    public interface AcknowledgeHandshakeOrBuilder extends MessageLiteOrBuilder {
        MessageCounter getCounterSeed();

        ByteString getKey();

        ByteString getNonceApp();

        ByteString getOdcHmac();

        CipherSuite getSelectedCipherSuite();

        int getSelectedCipherSuiteValue();

        int getSelectedProtocolVersion();

        ByteString getValidationHmac();

        boolean hasCounterSeed();
    }

    /* loaded from: classes6.dex */
    public static final class AcknowledgeResetKey extends GeneratedMessageLite<AcknowledgeResetKey, Builder> implements AcknowledgeResetKeyOrBuilder {
        private static final AcknowledgeResetKey DEFAULT_INSTANCE = new AcknowledgeResetKey();
        public static final int NONCE_APP_FIELD_NUMBER = 1;
        public static final int ODC_HMAC_FIELD_NUMBER = 3;
        private static volatile Parser<AcknowledgeResetKey> PARSER = null;
        public static final int VALIDATION_HMAC_FIELD_NUMBER = 2;
        private ByteString nonceApp_;
        private ByteString odcHmac_;
        private ByteString validationHmac_;

        /* loaded from: classes6.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<AcknowledgeResetKey, Builder> implements AcknowledgeResetKeyOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                this();
            }

            public Builder clearNonceApp() {
                copyOnWrite();
                ((AcknowledgeResetKey) this.instance).clearNonceApp();
                return this;
            }

            public Builder clearOdcHmac() {
                copyOnWrite();
                ((AcknowledgeResetKey) this.instance).clearOdcHmac();
                return this;
            }

            public Builder clearValidationHmac() {
                copyOnWrite();
                ((AcknowledgeResetKey) this.instance).clearValidationHmac();
                return this;
            }

            @Override // com.amazon.alexa.accessory.protocol.Keyexchange.AcknowledgeResetKeyOrBuilder
            public ByteString getNonceApp() {
                return ((AcknowledgeResetKey) this.instance).getNonceApp();
            }

            @Override // com.amazon.alexa.accessory.protocol.Keyexchange.AcknowledgeResetKeyOrBuilder
            public ByteString getOdcHmac() {
                return ((AcknowledgeResetKey) this.instance).getOdcHmac();
            }

            @Override // com.amazon.alexa.accessory.protocol.Keyexchange.AcknowledgeResetKeyOrBuilder
            public ByteString getValidationHmac() {
                return ((AcknowledgeResetKey) this.instance).getValidationHmac();
            }

            public Builder setNonceApp(ByteString byteString) {
                copyOnWrite();
                ((AcknowledgeResetKey) this.instance).setNonceApp(byteString);
                return this;
            }

            public Builder setOdcHmac(ByteString byteString) {
                copyOnWrite();
                ((AcknowledgeResetKey) this.instance).setOdcHmac(byteString);
                return this;
            }

            public Builder setValidationHmac(ByteString byteString) {
                copyOnWrite();
                ((AcknowledgeResetKey) this.instance).setValidationHmac(byteString);
                return this;
            }

            private Builder() {
                super(AcknowledgeResetKey.DEFAULT_INSTANCE);
            }
        }

        static {
            DEFAULT_INSTANCE.makeImmutable();
        }

        private AcknowledgeResetKey() {
            ByteString byteString = ByteString.EMPTY;
            this.nonceApp_ = byteString;
            this.validationHmac_ = byteString;
            this.odcHmac_ = byteString;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearNonceApp() {
            this.nonceApp_ = getDefaultInstance().getNonceApp();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearOdcHmac() {
            this.odcHmac_ = getDefaultInstance().getOdcHmac();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearValidationHmac() {
            this.validationHmac_ = getDefaultInstance().getValidationHmac();
        }

        public static AcknowledgeResetKey getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static AcknowledgeResetKey parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (AcknowledgeResetKey) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static AcknowledgeResetKey parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (AcknowledgeResetKey) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<AcknowledgeResetKey> parser() {
            return DEFAULT_INSTANCE.mo9954getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setNonceApp(ByteString byteString) {
            if (byteString != null) {
                this.nonceApp_ = byteString;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setOdcHmac(ByteString byteString) {
            if (byteString != null) {
                this.odcHmac_ = byteString;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setValidationHmac(ByteString byteString) {
            if (byteString != null) {
                this.validationHmac_ = byteString;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            boolean z = false;
            switch (methodToInvoke.ordinal()) {
                case 0:
                    return DEFAULT_INSTANCE;
                case 1:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) obj;
                    AcknowledgeResetKey acknowledgeResetKey = (AcknowledgeResetKey) obj2;
                    this.nonceApp_ = visitor.visitByteString(this.nonceApp_ != ByteString.EMPTY, this.nonceApp_, acknowledgeResetKey.nonceApp_ != ByteString.EMPTY, acknowledgeResetKey.nonceApp_);
                    this.validationHmac_ = visitor.visitByteString(this.validationHmac_ != ByteString.EMPTY, this.validationHmac_, acknowledgeResetKey.validationHmac_ != ByteString.EMPTY, acknowledgeResetKey.validationHmac_);
                    boolean z2 = this.odcHmac_ != ByteString.EMPTY;
                    ByteString byteString = this.odcHmac_;
                    if (acknowledgeResetKey.odcHmac_ != ByteString.EMPTY) {
                        z = true;
                    }
                    this.odcHmac_ = visitor.visitByteString(z2, byteString, z, acknowledgeResetKey.odcHmac_);
                    GeneratedMessageLite.MergeFromVisitor mergeFromVisitor = GeneratedMessageLite.MergeFromVisitor.INSTANCE;
                    return this;
                case 2:
                    return (byte) 1;
                case 3:
                    return null;
                case 4:
                    CodedInputStream codedInputStream = (CodedInputStream) obj;
                    if (((ExtensionRegistryLite) obj2) == null) {
                        throw new NullPointerException();
                    }
                    while (!z) {
                        try {
                            int readTag = codedInputStream.readTag();
                            if (readTag != 0) {
                                if (readTag == 10) {
                                    this.nonceApp_ = codedInputStream.readBytes();
                                } else if (readTag == 18) {
                                    this.validationHmac_ = codedInputStream.readBytes();
                                } else if (readTag != 26) {
                                    if (!parseUnknownField(readTag, codedInputStream)) {
                                    }
                                } else {
                                    this.odcHmac_ = codedInputStream.readBytes();
                                }
                            }
                            z = true;
                        } catch (InvalidProtocolBufferException e) {
                            throw new RuntimeException(e.setUnfinishedMessage(this));
                        } catch (IOException e2) {
                            throw new RuntimeException(new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this));
                        }
                    }
                    break;
                case 5:
                    return null;
                case 6:
                    return new AcknowledgeResetKey();
                case 7:
                    return new Builder(null);
                case 8:
                    break;
                case 9:
                    if (PARSER == null) {
                        synchronized (AcknowledgeResetKey.class) {
                            if (PARSER == null) {
                                PARSER = new GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
                            }
                        }
                    }
                    return PARSER;
                default:
                    throw new UnsupportedOperationException();
            }
            return DEFAULT_INSTANCE;
        }

        @Override // com.amazon.alexa.accessory.protocol.Keyexchange.AcknowledgeResetKeyOrBuilder
        public ByteString getNonceApp() {
            return this.nonceApp_;
        }

        @Override // com.amazon.alexa.accessory.protocol.Keyexchange.AcknowledgeResetKeyOrBuilder
        public ByteString getOdcHmac() {
            return this.odcHmac_;
        }

        @Override // com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            if (!this.nonceApp_.isEmpty()) {
                i2 = 0 + CodedOutputStream.computeBytesSize(1, this.nonceApp_);
            }
            if (!this.validationHmac_.isEmpty()) {
                i2 += CodedOutputStream.computeBytesSize(2, this.validationHmac_);
            }
            if (!this.odcHmac_.isEmpty()) {
                i2 += CodedOutputStream.computeBytesSize(3, this.odcHmac_);
            }
            int serializedSize = this.unknownFields.getSerializedSize() + i2;
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.amazon.alexa.accessory.protocol.Keyexchange.AcknowledgeResetKeyOrBuilder
        public ByteString getValidationHmac() {
            return this.validationHmac_;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (!this.nonceApp_.isEmpty()) {
                codedOutputStream.writeBytes(1, this.nonceApp_);
            }
            if (!this.validationHmac_.isEmpty()) {
                codedOutputStream.writeBytes(2, this.validationHmac_);
            }
            if (!this.odcHmac_.isEmpty()) {
                codedOutputStream.writeBytes(3, this.odcHmac_);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public static Builder newBuilder(AcknowledgeResetKey acknowledgeResetKey) {
            return DEFAULT_INSTANCE.createBuilder(acknowledgeResetKey);
        }

        public static AcknowledgeResetKey parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (AcknowledgeResetKey) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static AcknowledgeResetKey parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (AcknowledgeResetKey) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static AcknowledgeResetKey parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (AcknowledgeResetKey) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static AcknowledgeResetKey parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (AcknowledgeResetKey) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static AcknowledgeResetKey parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (AcknowledgeResetKey) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static AcknowledgeResetKey parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (AcknowledgeResetKey) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static AcknowledgeResetKey parseFrom(InputStream inputStream) throws IOException {
            return (AcknowledgeResetKey) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static AcknowledgeResetKey parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (AcknowledgeResetKey) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static AcknowledgeResetKey parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (AcknowledgeResetKey) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static AcknowledgeResetKey parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (AcknowledgeResetKey) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes6.dex */
    public interface AcknowledgeResetKeyOrBuilder extends MessageLiteOrBuilder {
        ByteString getNonceApp();

        ByteString getOdcHmac();

        ByteString getValidationHmac();
    }

    /* loaded from: classes6.dex */
    public enum CipherSuite implements Internal.EnumLite {
        SYMMETRIC_WITH_AES_256_GCM_SHA256(0),
        SYMMETRIC_WITH_AES_256_CBC_PKCS7PADDING_SHA256(1),
        SYMMETRIC_WITH_AES_128_GCM_SHA256(2),
        SYMMETRIC_WITH_AES_128_CBC_PKCS7PADDING_SHA256(3),
        UNRECOGNIZED(-1);
        
        public static final int SYMMETRIC_WITH_AES_128_CBC_PKCS7PADDING_SHA256_VALUE = 3;
        public static final int SYMMETRIC_WITH_AES_128_GCM_SHA256_VALUE = 2;
        public static final int SYMMETRIC_WITH_AES_256_CBC_PKCS7PADDING_SHA256_VALUE = 1;
        public static final int SYMMETRIC_WITH_AES_256_GCM_SHA256_VALUE = 0;
        private static final Internal.EnumLiteMap<CipherSuite> internalValueMap = new Internal.EnumLiteMap<CipherSuite>() { // from class: com.amazon.alexa.accessory.protocol.Keyexchange.CipherSuite.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.google.protobuf.Internal.EnumLiteMap
            /* renamed from: findValueByNumber */
            public CipherSuite mo9850findValueByNumber(int i) {
                return CipherSuite.forNumber(i);
            }
        };
        private final int value;

        CipherSuite(int i) {
            this.value = i;
        }

        public static CipherSuite forNumber(int i) {
            if (i != 0) {
                if (i == 1) {
                    return SYMMETRIC_WITH_AES_256_CBC_PKCS7PADDING_SHA256;
                }
                if (i == 2) {
                    return SYMMETRIC_WITH_AES_128_GCM_SHA256;
                }
                if (i == 3) {
                    return SYMMETRIC_WITH_AES_128_CBC_PKCS7PADDING_SHA256;
                }
                return null;
            }
            return SYMMETRIC_WITH_AES_256_GCM_SHA256;
        }

        public static Internal.EnumLiteMap<CipherSuite> internalGetValueMap() {
            return internalValueMap;
        }

        @Override // com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            if (this != UNRECOGNIZED) {
                return this.value;
            }
            throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
        }

        @Deprecated
        public static CipherSuite valueOf(int i) {
            return forNumber(i);
        }
    }

    /* loaded from: classes6.dex */
    public static final class CompleteHandshake extends GeneratedMessageLite<CompleteHandshake, Builder> implements CompleteHandshakeOrBuilder {
        private static final CompleteHandshake DEFAULT_INSTANCE = new CompleteHandshake();
        public static final int IS_SUCCESSFUL_FIELD_NUMBER = 1;
        private static volatile Parser<CompleteHandshake> PARSER = null;
        public static final int TEST_BLOB_FIELD_NUMBER = 2;
        private boolean isSuccessful_;
        private TestCipherBlob testBlob_;

        /* loaded from: classes6.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<CompleteHandshake, Builder> implements CompleteHandshakeOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                this();
            }

            public Builder clearIsSuccessful() {
                copyOnWrite();
                ((CompleteHandshake) this.instance).clearIsSuccessful();
                return this;
            }

            public Builder clearTestBlob() {
                copyOnWrite();
                ((CompleteHandshake) this.instance).clearTestBlob();
                return this;
            }

            @Override // com.amazon.alexa.accessory.protocol.Keyexchange.CompleteHandshakeOrBuilder
            public boolean getIsSuccessful() {
                return ((CompleteHandshake) this.instance).getIsSuccessful();
            }

            @Override // com.amazon.alexa.accessory.protocol.Keyexchange.CompleteHandshakeOrBuilder
            public TestCipherBlob getTestBlob() {
                return ((CompleteHandshake) this.instance).getTestBlob();
            }

            @Override // com.amazon.alexa.accessory.protocol.Keyexchange.CompleteHandshakeOrBuilder
            public boolean hasTestBlob() {
                return ((CompleteHandshake) this.instance).hasTestBlob();
            }

            public Builder mergeTestBlob(TestCipherBlob testCipherBlob) {
                copyOnWrite();
                ((CompleteHandshake) this.instance).mergeTestBlob(testCipherBlob);
                return this;
            }

            public Builder setIsSuccessful(boolean z) {
                copyOnWrite();
                ((CompleteHandshake) this.instance).setIsSuccessful(z);
                return this;
            }

            public Builder setTestBlob(TestCipherBlob testCipherBlob) {
                copyOnWrite();
                ((CompleteHandshake) this.instance).setTestBlob(testCipherBlob);
                return this;
            }

            private Builder() {
                super(CompleteHandshake.DEFAULT_INSTANCE);
            }

            public Builder setTestBlob(TestCipherBlob.Builder builder) {
                copyOnWrite();
                ((CompleteHandshake) this.instance).setTestBlob(builder);
                return this;
            }
        }

        static {
            DEFAULT_INSTANCE.makeImmutable();
        }

        private CompleteHandshake() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearIsSuccessful() {
            this.isSuccessful_ = false;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearTestBlob() {
            this.testBlob_ = null;
        }

        public static CompleteHandshake getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeTestBlob(TestCipherBlob testCipherBlob) {
            TestCipherBlob testCipherBlob2 = this.testBlob_;
            if (testCipherBlob2 != null && testCipherBlob2 != TestCipherBlob.getDefaultInstance()) {
                this.testBlob_ = TestCipherBlob.newBuilder(this.testBlob_).mergeFrom((TestCipherBlob.Builder) testCipherBlob).mo10085buildPartial();
            } else {
                this.testBlob_ = testCipherBlob;
            }
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static CompleteHandshake parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (CompleteHandshake) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static CompleteHandshake parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (CompleteHandshake) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<CompleteHandshake> parser() {
            return DEFAULT_INSTANCE.mo9954getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setIsSuccessful(boolean z) {
            this.isSuccessful_ = z;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setTestBlob(TestCipherBlob testCipherBlob) {
            if (testCipherBlob != null) {
                this.testBlob_ = testCipherBlob;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            switch (methodToInvoke.ordinal()) {
                case 0:
                    return DEFAULT_INSTANCE;
                case 1:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) obj;
                    CompleteHandshake completeHandshake = (CompleteHandshake) obj2;
                    boolean z = this.isSuccessful_;
                    boolean z2 = completeHandshake.isSuccessful_;
                    this.isSuccessful_ = visitor.visitBoolean(z, z, z2, z2);
                    this.testBlob_ = (TestCipherBlob) visitor.visitMessage(this.testBlob_, completeHandshake.testBlob_);
                    GeneratedMessageLite.MergeFromVisitor mergeFromVisitor = GeneratedMessageLite.MergeFromVisitor.INSTANCE;
                    return this;
                case 2:
                    return (byte) 1;
                case 3:
                    return null;
                case 4:
                    CodedInputStream codedInputStream = (CodedInputStream) obj;
                    ExtensionRegistryLite extensionRegistryLite = (ExtensionRegistryLite) obj2;
                    if (extensionRegistryLite == null) {
                        throw new NullPointerException();
                    }
                    boolean z3 = false;
                    while (!z3) {
                        try {
                            int readTag = codedInputStream.readTag();
                            if (readTag != 0) {
                                if (readTag == 8) {
                                    this.isSuccessful_ = codedInputStream.readBool();
                                } else if (readTag != 18) {
                                    if (!parseUnknownField(readTag, codedInputStream)) {
                                    }
                                } else {
                                    TestCipherBlob.Builder mo10081toBuilder = this.testBlob_ != null ? this.testBlob_.mo10081toBuilder() : null;
                                    this.testBlob_ = (TestCipherBlob) codedInputStream.readMessage(TestCipherBlob.parser(), extensionRegistryLite);
                                    if (mo10081toBuilder != null) {
                                        mo10081toBuilder.mergeFrom((TestCipherBlob.Builder) this.testBlob_);
                                        this.testBlob_ = mo10081toBuilder.mo10085buildPartial();
                                    }
                                }
                            }
                            z3 = true;
                        } catch (InvalidProtocolBufferException e) {
                            throw new RuntimeException(e.setUnfinishedMessage(this));
                        } catch (IOException e2) {
                            throw new RuntimeException(new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this));
                        }
                    }
                    break;
                case 5:
                    return null;
                case 6:
                    return new CompleteHandshake();
                case 7:
                    return new Builder(null);
                case 8:
                    break;
                case 9:
                    if (PARSER == null) {
                        synchronized (CompleteHandshake.class) {
                            if (PARSER == null) {
                                PARSER = new GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
                            }
                        }
                    }
                    return PARSER;
                default:
                    throw new UnsupportedOperationException();
            }
            return DEFAULT_INSTANCE;
        }

        @Override // com.amazon.alexa.accessory.protocol.Keyexchange.CompleteHandshakeOrBuilder
        public boolean getIsSuccessful() {
            return this.isSuccessful_;
        }

        @Override // com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            boolean z = this.isSuccessful_;
            if (z) {
                i2 = 0 + CodedOutputStream.computeBoolSize(1, z);
            }
            if (this.testBlob_ != null) {
                i2 += CodedOutputStream.computeMessageSize(2, getTestBlob());
            }
            int serializedSize = this.unknownFields.getSerializedSize() + i2;
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.amazon.alexa.accessory.protocol.Keyexchange.CompleteHandshakeOrBuilder
        public TestCipherBlob getTestBlob() {
            TestCipherBlob testCipherBlob = this.testBlob_;
            return testCipherBlob == null ? TestCipherBlob.getDefaultInstance() : testCipherBlob;
        }

        @Override // com.amazon.alexa.accessory.protocol.Keyexchange.CompleteHandshakeOrBuilder
        public boolean hasTestBlob() {
            return this.testBlob_ != null;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            boolean z = this.isSuccessful_;
            if (z) {
                codedOutputStream.writeBool(1, z);
            }
            if (this.testBlob_ != null) {
                codedOutputStream.writeMessage(2, getTestBlob());
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public static Builder newBuilder(CompleteHandshake completeHandshake) {
            return DEFAULT_INSTANCE.createBuilder(completeHandshake);
        }

        public static CompleteHandshake parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (CompleteHandshake) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static CompleteHandshake parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (CompleteHandshake) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static CompleteHandshake parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (CompleteHandshake) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setTestBlob(TestCipherBlob.Builder builder) {
            this.testBlob_ = builder.mo10084build();
        }

        public static CompleteHandshake parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (CompleteHandshake) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static CompleteHandshake parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (CompleteHandshake) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static CompleteHandshake parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (CompleteHandshake) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static CompleteHandshake parseFrom(InputStream inputStream) throws IOException {
            return (CompleteHandshake) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static CompleteHandshake parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (CompleteHandshake) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static CompleteHandshake parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (CompleteHandshake) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static CompleteHandshake parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (CompleteHandshake) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes6.dex */
    public interface CompleteHandshakeOrBuilder extends MessageLiteOrBuilder {
        boolean getIsSuccessful();

        TestCipherBlob getTestBlob();

        boolean hasTestBlob();
    }

    /* loaded from: classes6.dex */
    public static final class ConfirmResetKey extends GeneratedMessageLite<ConfirmResetKey, Builder> implements ConfirmResetKeyOrBuilder {
        private static final ConfirmResetKey DEFAULT_INSTANCE = new ConfirmResetKey();
        public static final int IS_SUCCESSFUL_FIELD_NUMBER = 1;
        private static volatile Parser<ConfirmResetKey> PARSER = null;
        public static final int TEST_BLOB_FIELD_NUMBER = 2;
        private boolean isSuccessful_;
        private TestCipherBlob testBlob_;

        /* loaded from: classes6.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<ConfirmResetKey, Builder> implements ConfirmResetKeyOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                this();
            }

            public Builder clearIsSuccessful() {
                copyOnWrite();
                ((ConfirmResetKey) this.instance).clearIsSuccessful();
                return this;
            }

            public Builder clearTestBlob() {
                copyOnWrite();
                ((ConfirmResetKey) this.instance).clearTestBlob();
                return this;
            }

            @Override // com.amazon.alexa.accessory.protocol.Keyexchange.ConfirmResetKeyOrBuilder
            public boolean getIsSuccessful() {
                return ((ConfirmResetKey) this.instance).getIsSuccessful();
            }

            @Override // com.amazon.alexa.accessory.protocol.Keyexchange.ConfirmResetKeyOrBuilder
            public TestCipherBlob getTestBlob() {
                return ((ConfirmResetKey) this.instance).getTestBlob();
            }

            @Override // com.amazon.alexa.accessory.protocol.Keyexchange.ConfirmResetKeyOrBuilder
            public boolean hasTestBlob() {
                return ((ConfirmResetKey) this.instance).hasTestBlob();
            }

            public Builder mergeTestBlob(TestCipherBlob testCipherBlob) {
                copyOnWrite();
                ((ConfirmResetKey) this.instance).mergeTestBlob(testCipherBlob);
                return this;
            }

            public Builder setIsSuccessful(boolean z) {
                copyOnWrite();
                ((ConfirmResetKey) this.instance).setIsSuccessful(z);
                return this;
            }

            public Builder setTestBlob(TestCipherBlob testCipherBlob) {
                copyOnWrite();
                ((ConfirmResetKey) this.instance).setTestBlob(testCipherBlob);
                return this;
            }

            private Builder() {
                super(ConfirmResetKey.DEFAULT_INSTANCE);
            }

            public Builder setTestBlob(TestCipherBlob.Builder builder) {
                copyOnWrite();
                ((ConfirmResetKey) this.instance).setTestBlob(builder);
                return this;
            }
        }

        static {
            DEFAULT_INSTANCE.makeImmutable();
        }

        private ConfirmResetKey() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearIsSuccessful() {
            this.isSuccessful_ = false;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearTestBlob() {
            this.testBlob_ = null;
        }

        public static ConfirmResetKey getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeTestBlob(TestCipherBlob testCipherBlob) {
            TestCipherBlob testCipherBlob2 = this.testBlob_;
            if (testCipherBlob2 != null && testCipherBlob2 != TestCipherBlob.getDefaultInstance()) {
                this.testBlob_ = TestCipherBlob.newBuilder(this.testBlob_).mergeFrom((TestCipherBlob.Builder) testCipherBlob).mo10085buildPartial();
            } else {
                this.testBlob_ = testCipherBlob;
            }
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static ConfirmResetKey parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (ConfirmResetKey) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static ConfirmResetKey parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (ConfirmResetKey) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<ConfirmResetKey> parser() {
            return DEFAULT_INSTANCE.mo9954getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setIsSuccessful(boolean z) {
            this.isSuccessful_ = z;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setTestBlob(TestCipherBlob testCipherBlob) {
            if (testCipherBlob != null) {
                this.testBlob_ = testCipherBlob;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            switch (methodToInvoke.ordinal()) {
                case 0:
                    return DEFAULT_INSTANCE;
                case 1:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) obj;
                    ConfirmResetKey confirmResetKey = (ConfirmResetKey) obj2;
                    boolean z = this.isSuccessful_;
                    boolean z2 = confirmResetKey.isSuccessful_;
                    this.isSuccessful_ = visitor.visitBoolean(z, z, z2, z2);
                    this.testBlob_ = (TestCipherBlob) visitor.visitMessage(this.testBlob_, confirmResetKey.testBlob_);
                    GeneratedMessageLite.MergeFromVisitor mergeFromVisitor = GeneratedMessageLite.MergeFromVisitor.INSTANCE;
                    return this;
                case 2:
                    return (byte) 1;
                case 3:
                    return null;
                case 4:
                    CodedInputStream codedInputStream = (CodedInputStream) obj;
                    ExtensionRegistryLite extensionRegistryLite = (ExtensionRegistryLite) obj2;
                    if (extensionRegistryLite == null) {
                        throw new NullPointerException();
                    }
                    boolean z3 = false;
                    while (!z3) {
                        try {
                            int readTag = codedInputStream.readTag();
                            if (readTag != 0) {
                                if (readTag == 8) {
                                    this.isSuccessful_ = codedInputStream.readBool();
                                } else if (readTag != 18) {
                                    if (!parseUnknownField(readTag, codedInputStream)) {
                                    }
                                } else {
                                    TestCipherBlob.Builder mo10081toBuilder = this.testBlob_ != null ? this.testBlob_.mo10081toBuilder() : null;
                                    this.testBlob_ = (TestCipherBlob) codedInputStream.readMessage(TestCipherBlob.parser(), extensionRegistryLite);
                                    if (mo10081toBuilder != null) {
                                        mo10081toBuilder.mergeFrom((TestCipherBlob.Builder) this.testBlob_);
                                        this.testBlob_ = mo10081toBuilder.mo10085buildPartial();
                                    }
                                }
                            }
                            z3 = true;
                        } catch (InvalidProtocolBufferException e) {
                            throw new RuntimeException(e.setUnfinishedMessage(this));
                        } catch (IOException e2) {
                            throw new RuntimeException(new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this));
                        }
                    }
                    break;
                case 5:
                    return null;
                case 6:
                    return new ConfirmResetKey();
                case 7:
                    return new Builder(null);
                case 8:
                    break;
                case 9:
                    if (PARSER == null) {
                        synchronized (ConfirmResetKey.class) {
                            if (PARSER == null) {
                                PARSER = new GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
                            }
                        }
                    }
                    return PARSER;
                default:
                    throw new UnsupportedOperationException();
            }
            return DEFAULT_INSTANCE;
        }

        @Override // com.amazon.alexa.accessory.protocol.Keyexchange.ConfirmResetKeyOrBuilder
        public boolean getIsSuccessful() {
            return this.isSuccessful_;
        }

        @Override // com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            boolean z = this.isSuccessful_;
            if (z) {
                i2 = 0 + CodedOutputStream.computeBoolSize(1, z);
            }
            if (this.testBlob_ != null) {
                i2 += CodedOutputStream.computeMessageSize(2, getTestBlob());
            }
            int serializedSize = this.unknownFields.getSerializedSize() + i2;
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.amazon.alexa.accessory.protocol.Keyexchange.ConfirmResetKeyOrBuilder
        public TestCipherBlob getTestBlob() {
            TestCipherBlob testCipherBlob = this.testBlob_;
            return testCipherBlob == null ? TestCipherBlob.getDefaultInstance() : testCipherBlob;
        }

        @Override // com.amazon.alexa.accessory.protocol.Keyexchange.ConfirmResetKeyOrBuilder
        public boolean hasTestBlob() {
            return this.testBlob_ != null;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            boolean z = this.isSuccessful_;
            if (z) {
                codedOutputStream.writeBool(1, z);
            }
            if (this.testBlob_ != null) {
                codedOutputStream.writeMessage(2, getTestBlob());
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public static Builder newBuilder(ConfirmResetKey confirmResetKey) {
            return DEFAULT_INSTANCE.createBuilder(confirmResetKey);
        }

        public static ConfirmResetKey parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ConfirmResetKey) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static ConfirmResetKey parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (ConfirmResetKey) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static ConfirmResetKey parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (ConfirmResetKey) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setTestBlob(TestCipherBlob.Builder builder) {
            this.testBlob_ = builder.mo10084build();
        }

        public static ConfirmResetKey parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (ConfirmResetKey) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static ConfirmResetKey parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (ConfirmResetKey) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static ConfirmResetKey parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (ConfirmResetKey) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static ConfirmResetKey parseFrom(InputStream inputStream) throws IOException {
            return (ConfirmResetKey) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static ConfirmResetKey parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ConfirmResetKey) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static ConfirmResetKey parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (ConfirmResetKey) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static ConfirmResetKey parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ConfirmResetKey) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes6.dex */
    public interface ConfirmResetKeyOrBuilder extends MessageLiteOrBuilder {
        boolean getIsSuccessful();

        TestCipherBlob getTestBlob();

        boolean hasTestBlob();
    }

    /* loaded from: classes6.dex */
    public enum HandshakeFlags implements Internal.EnumLite {
        USER_CONFIRMATION_REQUIRED(0),
        KEY_RENEGOTIATION_ALLOWED(1),
        GENERATE_ODC_KEY_DURING_HANDSHAKE(2),
        UNRECOGNIZED(-1);
        
        public static final int GENERATE_ODC_KEY_DURING_HANDSHAKE_VALUE = 2;
        public static final int KEY_RENEGOTIATION_ALLOWED_VALUE = 1;
        public static final int USER_CONFIRMATION_REQUIRED_VALUE = 0;
        private static final Internal.EnumLiteMap<HandshakeFlags> internalValueMap = new Internal.EnumLiteMap<HandshakeFlags>() { // from class: com.amazon.alexa.accessory.protocol.Keyexchange.HandshakeFlags.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.google.protobuf.Internal.EnumLiteMap
            /* renamed from: findValueByNumber */
            public HandshakeFlags mo9850findValueByNumber(int i) {
                return HandshakeFlags.forNumber(i);
            }
        };
        private final int value;

        HandshakeFlags(int i) {
            this.value = i;
        }

        public static HandshakeFlags forNumber(int i) {
            if (i != 0) {
                if (i == 1) {
                    return KEY_RENEGOTIATION_ALLOWED;
                }
                if (i == 2) {
                    return GENERATE_ODC_KEY_DURING_HANDSHAKE;
                }
                return null;
            }
            return USER_CONFIRMATION_REQUIRED;
        }

        public static Internal.EnumLiteMap<HandshakeFlags> internalGetValueMap() {
            return internalValueMap;
        }

        @Override // com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            if (this != UNRECOGNIZED) {
                return this.value;
            }
            throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
        }

        @Deprecated
        public static HandshakeFlags valueOf(int i) {
            return forNumber(i);
        }
    }

    /* loaded from: classes6.dex */
    public static final class InitiateHandshake extends GeneratedMessageLite<InitiateHandshake, Builder> implements InitiateHandshakeOrBuilder {
        public static final int FLAGS_FIELD_NUMBER = 5;
        public static final int MAX_SUPPORTED_PROTOCOL_VERSION_FIELD_NUMBER = 2;
        public static final int MIN_SUPPORTED_PROTOCOL_VERSION_FIELD_NUMBER = 1;
        public static final int NONCE_ACCESSORY_FIELD_NUMBER = 4;
        private static volatile Parser<InitiateHandshake> PARSER = null;
        public static final int SUPPORTED_CIPHER_SUITES_FIELD_NUMBER = 3;
        private int bitField0_;
        private int flagsMemoizedSerializedSize;
        private int maxSupportedProtocolVersion_;
        private int minSupportedProtocolVersion_;
        private int supportedCipherSuitesMemoizedSerializedSize;
        private static final Internal.ListAdapter.Converter<Integer, CipherSuite> supportedCipherSuites_converter_ = new Internal.ListAdapter.Converter<Integer, CipherSuite>() { // from class: com.amazon.alexa.accessory.protocol.Keyexchange.InitiateHandshake.1
            @Override // com.google.protobuf.Internal.ListAdapter.Converter
            public CipherSuite convert(Integer num) {
                CipherSuite forNumber = CipherSuite.forNumber(num.intValue());
                return forNumber == null ? CipherSuite.UNRECOGNIZED : forNumber;
            }
        };
        private static final Internal.ListAdapter.Converter<Integer, HandshakeFlags> flags_converter_ = new Internal.ListAdapter.Converter<Integer, HandshakeFlags>() { // from class: com.amazon.alexa.accessory.protocol.Keyexchange.InitiateHandshake.2
            @Override // com.google.protobuf.Internal.ListAdapter.Converter
            public HandshakeFlags convert(Integer num) {
                HandshakeFlags forNumber = HandshakeFlags.forNumber(num.intValue());
                return forNumber == null ? HandshakeFlags.UNRECOGNIZED : forNumber;
            }
        };
        private static final InitiateHandshake DEFAULT_INSTANCE = new InitiateHandshake();
        private Internal.IntList supportedCipherSuites_ = GeneratedMessageLite.emptyIntList();
        private ByteString nonceAccessory_ = ByteString.EMPTY;
        private Internal.IntList flags_ = GeneratedMessageLite.emptyIntList();

        /* loaded from: classes6.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<InitiateHandshake, Builder> implements InitiateHandshakeOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                this();
            }

            public Builder addAllFlags(Iterable<? extends HandshakeFlags> iterable) {
                copyOnWrite();
                ((InitiateHandshake) this.instance).addAllFlags(iterable);
                return this;
            }

            public Builder addAllFlagsValue(Iterable<Integer> iterable) {
                copyOnWrite();
                ((InitiateHandshake) this.instance).addAllFlagsValue(iterable);
                return this;
            }

            public Builder addAllSupportedCipherSuites(Iterable<? extends CipherSuite> iterable) {
                copyOnWrite();
                ((InitiateHandshake) this.instance).addAllSupportedCipherSuites(iterable);
                return this;
            }

            public Builder addAllSupportedCipherSuitesValue(Iterable<Integer> iterable) {
                copyOnWrite();
                ((InitiateHandshake) this.instance).addAllSupportedCipherSuitesValue(iterable);
                return this;
            }

            public Builder addFlags(HandshakeFlags handshakeFlags) {
                copyOnWrite();
                ((InitiateHandshake) this.instance).addFlags(handshakeFlags);
                return this;
            }

            public Builder addFlagsValue(int i) {
                ((InitiateHandshake) this.instance).addFlagsValue(i);
                return this;
            }

            public Builder addSupportedCipherSuites(CipherSuite cipherSuite) {
                copyOnWrite();
                ((InitiateHandshake) this.instance).addSupportedCipherSuites(cipherSuite);
                return this;
            }

            public Builder addSupportedCipherSuitesValue(int i) {
                ((InitiateHandshake) this.instance).addSupportedCipherSuitesValue(i);
                return this;
            }

            public Builder clearFlags() {
                copyOnWrite();
                ((InitiateHandshake) this.instance).clearFlags();
                return this;
            }

            public Builder clearMaxSupportedProtocolVersion() {
                copyOnWrite();
                ((InitiateHandshake) this.instance).clearMaxSupportedProtocolVersion();
                return this;
            }

            public Builder clearMinSupportedProtocolVersion() {
                copyOnWrite();
                ((InitiateHandshake) this.instance).clearMinSupportedProtocolVersion();
                return this;
            }

            public Builder clearNonceAccessory() {
                copyOnWrite();
                ((InitiateHandshake) this.instance).clearNonceAccessory();
                return this;
            }

            public Builder clearSupportedCipherSuites() {
                copyOnWrite();
                ((InitiateHandshake) this.instance).clearSupportedCipherSuites();
                return this;
            }

            @Override // com.amazon.alexa.accessory.protocol.Keyexchange.InitiateHandshakeOrBuilder
            public HandshakeFlags getFlags(int i) {
                return ((InitiateHandshake) this.instance).getFlags(i);
            }

            @Override // com.amazon.alexa.accessory.protocol.Keyexchange.InitiateHandshakeOrBuilder
            public int getFlagsCount() {
                return ((InitiateHandshake) this.instance).getFlagsCount();
            }

            @Override // com.amazon.alexa.accessory.protocol.Keyexchange.InitiateHandshakeOrBuilder
            public List<HandshakeFlags> getFlagsList() {
                return ((InitiateHandshake) this.instance).getFlagsList();
            }

            @Override // com.amazon.alexa.accessory.protocol.Keyexchange.InitiateHandshakeOrBuilder
            public int getFlagsValue(int i) {
                return ((InitiateHandshake) this.instance).getFlagsValue(i);
            }

            @Override // com.amazon.alexa.accessory.protocol.Keyexchange.InitiateHandshakeOrBuilder
            public List<Integer> getFlagsValueList() {
                return Collections.unmodifiableList(((InitiateHandshake) this.instance).getFlagsValueList());
            }

            @Override // com.amazon.alexa.accessory.protocol.Keyexchange.InitiateHandshakeOrBuilder
            public int getMaxSupportedProtocolVersion() {
                return ((InitiateHandshake) this.instance).getMaxSupportedProtocolVersion();
            }

            @Override // com.amazon.alexa.accessory.protocol.Keyexchange.InitiateHandshakeOrBuilder
            public int getMinSupportedProtocolVersion() {
                return ((InitiateHandshake) this.instance).getMinSupportedProtocolVersion();
            }

            @Override // com.amazon.alexa.accessory.protocol.Keyexchange.InitiateHandshakeOrBuilder
            public ByteString getNonceAccessory() {
                return ((InitiateHandshake) this.instance).getNonceAccessory();
            }

            @Override // com.amazon.alexa.accessory.protocol.Keyexchange.InitiateHandshakeOrBuilder
            public CipherSuite getSupportedCipherSuites(int i) {
                return ((InitiateHandshake) this.instance).getSupportedCipherSuites(i);
            }

            @Override // com.amazon.alexa.accessory.protocol.Keyexchange.InitiateHandshakeOrBuilder
            public int getSupportedCipherSuitesCount() {
                return ((InitiateHandshake) this.instance).getSupportedCipherSuitesCount();
            }

            @Override // com.amazon.alexa.accessory.protocol.Keyexchange.InitiateHandshakeOrBuilder
            public List<CipherSuite> getSupportedCipherSuitesList() {
                return ((InitiateHandshake) this.instance).getSupportedCipherSuitesList();
            }

            @Override // com.amazon.alexa.accessory.protocol.Keyexchange.InitiateHandshakeOrBuilder
            public int getSupportedCipherSuitesValue(int i) {
                return ((InitiateHandshake) this.instance).getSupportedCipherSuitesValue(i);
            }

            @Override // com.amazon.alexa.accessory.protocol.Keyexchange.InitiateHandshakeOrBuilder
            public List<Integer> getSupportedCipherSuitesValueList() {
                return Collections.unmodifiableList(((InitiateHandshake) this.instance).getSupportedCipherSuitesValueList());
            }

            public Builder setFlags(int i, HandshakeFlags handshakeFlags) {
                copyOnWrite();
                ((InitiateHandshake) this.instance).setFlags(i, handshakeFlags);
                return this;
            }

            public Builder setFlagsValue(int i, int i2) {
                copyOnWrite();
                ((InitiateHandshake) this.instance).setFlagsValue(i, i2);
                return this;
            }

            public Builder setMaxSupportedProtocolVersion(int i) {
                copyOnWrite();
                ((InitiateHandshake) this.instance).setMaxSupportedProtocolVersion(i);
                return this;
            }

            public Builder setMinSupportedProtocolVersion(int i) {
                copyOnWrite();
                ((InitiateHandshake) this.instance).setMinSupportedProtocolVersion(i);
                return this;
            }

            public Builder setNonceAccessory(ByteString byteString) {
                copyOnWrite();
                ((InitiateHandshake) this.instance).setNonceAccessory(byteString);
                return this;
            }

            public Builder setSupportedCipherSuites(int i, CipherSuite cipherSuite) {
                copyOnWrite();
                ((InitiateHandshake) this.instance).setSupportedCipherSuites(i, cipherSuite);
                return this;
            }

            public Builder setSupportedCipherSuitesValue(int i, int i2) {
                copyOnWrite();
                ((InitiateHandshake) this.instance).setSupportedCipherSuitesValue(i, i2);
                return this;
            }

            private Builder() {
                super(InitiateHandshake.DEFAULT_INSTANCE);
            }
        }

        static {
            DEFAULT_INSTANCE.makeImmutable();
        }

        private InitiateHandshake() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addAllFlags(Iterable<? extends HandshakeFlags> iterable) {
            ensureFlagsIsMutable();
            for (HandshakeFlags handshakeFlags : iterable) {
                this.flags_.addInt(handshakeFlags.getNumber());
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addAllFlagsValue(Iterable<Integer> iterable) {
            ensureFlagsIsMutable();
            for (Integer num : iterable) {
                this.flags_.addInt(num.intValue());
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addAllSupportedCipherSuites(Iterable<? extends CipherSuite> iterable) {
            ensureSupportedCipherSuitesIsMutable();
            for (CipherSuite cipherSuite : iterable) {
                this.supportedCipherSuites_.addInt(cipherSuite.getNumber());
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addAllSupportedCipherSuitesValue(Iterable<Integer> iterable) {
            ensureSupportedCipherSuitesIsMutable();
            for (Integer num : iterable) {
                this.supportedCipherSuites_.addInt(num.intValue());
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addFlags(HandshakeFlags handshakeFlags) {
            if (handshakeFlags != null) {
                ensureFlagsIsMutable();
                this.flags_.addInt(handshakeFlags.getNumber());
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addFlagsValue(int i) {
            ensureFlagsIsMutable();
            this.flags_.addInt(i);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addSupportedCipherSuites(CipherSuite cipherSuite) {
            if (cipherSuite != null) {
                ensureSupportedCipherSuitesIsMutable();
                this.supportedCipherSuites_.addInt(cipherSuite.getNumber());
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addSupportedCipherSuitesValue(int i) {
            ensureSupportedCipherSuitesIsMutable();
            this.supportedCipherSuites_.addInt(i);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearFlags() {
            this.flags_ = GeneratedMessageLite.emptyIntList();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearMaxSupportedProtocolVersion() {
            this.maxSupportedProtocolVersion_ = 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearMinSupportedProtocolVersion() {
            this.minSupportedProtocolVersion_ = 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearNonceAccessory() {
            this.nonceAccessory_ = getDefaultInstance().getNonceAccessory();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearSupportedCipherSuites() {
            this.supportedCipherSuites_ = GeneratedMessageLite.emptyIntList();
        }

        private void ensureFlagsIsMutable() {
            if (!this.flags_.isModifiable()) {
                this.flags_ = GeneratedMessageLite.mutableCopy(this.flags_);
            }
        }

        private void ensureSupportedCipherSuitesIsMutable() {
            if (!this.supportedCipherSuites_.isModifiable()) {
                this.supportedCipherSuites_ = GeneratedMessageLite.mutableCopy(this.supportedCipherSuites_);
            }
        }

        public static InitiateHandshake getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static InitiateHandshake parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (InitiateHandshake) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static InitiateHandshake parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (InitiateHandshake) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<InitiateHandshake> parser() {
            return DEFAULT_INSTANCE.mo9954getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setFlags(int i, HandshakeFlags handshakeFlags) {
            if (handshakeFlags != null) {
                ensureFlagsIsMutable();
                this.flags_.setInt(i, handshakeFlags.getNumber());
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setFlagsValue(int i, int i2) {
            ensureFlagsIsMutable();
            this.flags_.setInt(i, i2);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setMaxSupportedProtocolVersion(int i) {
            this.maxSupportedProtocolVersion_ = i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setMinSupportedProtocolVersion(int i) {
            this.minSupportedProtocolVersion_ = i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setNonceAccessory(ByteString byteString) {
            if (byteString != null) {
                this.nonceAccessory_ = byteString;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setSupportedCipherSuites(int i, CipherSuite cipherSuite) {
            if (cipherSuite != null) {
                ensureSupportedCipherSuitesIsMutable();
                this.supportedCipherSuites_.setInt(i, cipherSuite.getNumber());
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setSupportedCipherSuitesValue(int i, int i2) {
            ensureSupportedCipherSuitesIsMutable();
            this.supportedCipherSuites_.setInt(i, i2);
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            boolean z = false;
            switch (methodToInvoke.ordinal()) {
                case 0:
                    return DEFAULT_INSTANCE;
                case 1:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) obj;
                    InitiateHandshake initiateHandshake = (InitiateHandshake) obj2;
                    this.minSupportedProtocolVersion_ = visitor.visitInt(this.minSupportedProtocolVersion_ != 0, this.minSupportedProtocolVersion_, initiateHandshake.minSupportedProtocolVersion_ != 0, initiateHandshake.minSupportedProtocolVersion_);
                    this.maxSupportedProtocolVersion_ = visitor.visitInt(this.maxSupportedProtocolVersion_ != 0, this.maxSupportedProtocolVersion_, initiateHandshake.maxSupportedProtocolVersion_ != 0, initiateHandshake.maxSupportedProtocolVersion_);
                    this.supportedCipherSuites_ = visitor.visitIntList(this.supportedCipherSuites_, initiateHandshake.supportedCipherSuites_);
                    boolean z2 = this.nonceAccessory_ != ByteString.EMPTY;
                    ByteString byteString = this.nonceAccessory_;
                    if (initiateHandshake.nonceAccessory_ != ByteString.EMPTY) {
                        z = true;
                    }
                    this.nonceAccessory_ = visitor.visitByteString(z2, byteString, z, initiateHandshake.nonceAccessory_);
                    this.flags_ = visitor.visitIntList(this.flags_, initiateHandshake.flags_);
                    if (visitor == GeneratedMessageLite.MergeFromVisitor.INSTANCE) {
                        this.bitField0_ |= initiateHandshake.bitField0_;
                    }
                    return this;
                case 2:
                    return (byte) 1;
                case 3:
                    return null;
                case 4:
                    CodedInputStream codedInputStream = (CodedInputStream) obj;
                    if (((ExtensionRegistryLite) obj2) == null) {
                        throw new NullPointerException();
                    }
                    while (!z) {
                        try {
                            int readTag = codedInputStream.readTag();
                            if (readTag != 0) {
                                if (readTag == 8) {
                                    this.minSupportedProtocolVersion_ = codedInputStream.readUInt32();
                                } else if (readTag == 16) {
                                    this.maxSupportedProtocolVersion_ = codedInputStream.readUInt32();
                                } else if (readTag == 24) {
                                    if (!this.supportedCipherSuites_.isModifiable()) {
                                        this.supportedCipherSuites_ = GeneratedMessageLite.mutableCopy(this.supportedCipherSuites_);
                                    }
                                    this.supportedCipherSuites_.addInt(codedInputStream.readEnum());
                                } else if (readTag == 26) {
                                    if (!this.supportedCipherSuites_.isModifiable()) {
                                        this.supportedCipherSuites_ = GeneratedMessageLite.mutableCopy(this.supportedCipherSuites_);
                                    }
                                    int pushLimit = codedInputStream.pushLimit(codedInputStream.readRawVarint32());
                                    while (codedInputStream.getBytesUntilLimit() > 0) {
                                        this.supportedCipherSuites_.addInt(codedInputStream.readEnum());
                                    }
                                    codedInputStream.popLimit(pushLimit);
                                } else if (readTag == 34) {
                                    this.nonceAccessory_ = codedInputStream.readBytes();
                                } else if (readTag == 40) {
                                    if (!this.flags_.isModifiable()) {
                                        this.flags_ = GeneratedMessageLite.mutableCopy(this.flags_);
                                    }
                                    this.flags_.addInt(codedInputStream.readEnum());
                                } else if (readTag != 42) {
                                    if (!parseUnknownField(readTag, codedInputStream)) {
                                    }
                                } else {
                                    if (!this.flags_.isModifiable()) {
                                        this.flags_ = GeneratedMessageLite.mutableCopy(this.flags_);
                                    }
                                    int pushLimit2 = codedInputStream.pushLimit(codedInputStream.readRawVarint32());
                                    while (codedInputStream.getBytesUntilLimit() > 0) {
                                        this.flags_.addInt(codedInputStream.readEnum());
                                    }
                                    codedInputStream.popLimit(pushLimit2);
                                }
                            }
                            z = true;
                        } catch (InvalidProtocolBufferException e) {
                            throw new RuntimeException(e.setUnfinishedMessage(this));
                        } catch (IOException e2) {
                            throw new RuntimeException(new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this));
                        }
                    }
                    break;
                case 5:
                    this.supportedCipherSuites_.makeImmutable();
                    this.flags_.makeImmutable();
                    return null;
                case 6:
                    return new InitiateHandshake();
                case 7:
                    return new Builder(null);
                case 8:
                    break;
                case 9:
                    if (PARSER == null) {
                        synchronized (InitiateHandshake.class) {
                            if (PARSER == null) {
                                PARSER = new GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
                            }
                        }
                    }
                    return PARSER;
                default:
                    throw new UnsupportedOperationException();
            }
            return DEFAULT_INSTANCE;
        }

        @Override // com.amazon.alexa.accessory.protocol.Keyexchange.InitiateHandshakeOrBuilder
        public HandshakeFlags getFlags(int i) {
            return flags_converter_.convert(Integer.valueOf(this.flags_.getInt(i)));
        }

        @Override // com.amazon.alexa.accessory.protocol.Keyexchange.InitiateHandshakeOrBuilder
        public int getFlagsCount() {
            return this.flags_.size();
        }

        @Override // com.amazon.alexa.accessory.protocol.Keyexchange.InitiateHandshakeOrBuilder
        public List<HandshakeFlags> getFlagsList() {
            return new Internal.ListAdapter(this.flags_, flags_converter_);
        }

        @Override // com.amazon.alexa.accessory.protocol.Keyexchange.InitiateHandshakeOrBuilder
        public int getFlagsValue(int i) {
            return this.flags_.getInt(i);
        }

        @Override // com.amazon.alexa.accessory.protocol.Keyexchange.InitiateHandshakeOrBuilder
        public List<Integer> getFlagsValueList() {
            return this.flags_;
        }

        @Override // com.amazon.alexa.accessory.protocol.Keyexchange.InitiateHandshakeOrBuilder
        public int getMaxSupportedProtocolVersion() {
            return this.maxSupportedProtocolVersion_;
        }

        @Override // com.amazon.alexa.accessory.protocol.Keyexchange.InitiateHandshakeOrBuilder
        public int getMinSupportedProtocolVersion() {
            return this.minSupportedProtocolVersion_;
        }

        @Override // com.amazon.alexa.accessory.protocol.Keyexchange.InitiateHandshakeOrBuilder
        public ByteString getNonceAccessory() {
            return this.nonceAccessory_;
        }

        @Override // com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = this.minSupportedProtocolVersion_;
            int computeUInt32Size = i2 != 0 ? CodedOutputStream.computeUInt32Size(1, i2) + 0 : 0;
            int i3 = this.maxSupportedProtocolVersion_;
            if (i3 != 0) {
                computeUInt32Size += CodedOutputStream.computeUInt32Size(2, i3);
            }
            int i4 = 0;
            for (int i5 = 0; i5 < this.supportedCipherSuites_.size(); i5++) {
                i4 += CodedOutputStream.computeEnumSizeNoTag(this.supportedCipherSuites_.getInt(i5));
            }
            int i6 = computeUInt32Size + i4;
            if (!getSupportedCipherSuitesList().isEmpty()) {
                i6 = i6 + 1 + CodedOutputStream.computeUInt32SizeNoTag(i4);
            }
            this.supportedCipherSuitesMemoizedSerializedSize = i4;
            if (!this.nonceAccessory_.isEmpty()) {
                i6 += CodedOutputStream.computeBytesSize(4, this.nonceAccessory_);
            }
            int i7 = 0;
            for (int i8 = 0; i8 < this.flags_.size(); i8++) {
                i7 += CodedOutputStream.computeEnumSizeNoTag(this.flags_.getInt(i8));
            }
            int i9 = i6 + i7;
            if (!getFlagsList().isEmpty()) {
                i9 = i9 + 1 + CodedOutputStream.computeUInt32SizeNoTag(i7);
            }
            this.flagsMemoizedSerializedSize = i7;
            int serializedSize = this.unknownFields.getSerializedSize() + i9;
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.amazon.alexa.accessory.protocol.Keyexchange.InitiateHandshakeOrBuilder
        public CipherSuite getSupportedCipherSuites(int i) {
            return supportedCipherSuites_converter_.convert(Integer.valueOf(this.supportedCipherSuites_.getInt(i)));
        }

        @Override // com.amazon.alexa.accessory.protocol.Keyexchange.InitiateHandshakeOrBuilder
        public int getSupportedCipherSuitesCount() {
            return this.supportedCipherSuites_.size();
        }

        @Override // com.amazon.alexa.accessory.protocol.Keyexchange.InitiateHandshakeOrBuilder
        public List<CipherSuite> getSupportedCipherSuitesList() {
            return new Internal.ListAdapter(this.supportedCipherSuites_, supportedCipherSuites_converter_);
        }

        @Override // com.amazon.alexa.accessory.protocol.Keyexchange.InitiateHandshakeOrBuilder
        public int getSupportedCipherSuitesValue(int i) {
            return this.supportedCipherSuites_.getInt(i);
        }

        @Override // com.amazon.alexa.accessory.protocol.Keyexchange.InitiateHandshakeOrBuilder
        public List<Integer> getSupportedCipherSuitesValueList() {
            return this.supportedCipherSuites_;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            getSerializedSize();
            int i = this.minSupportedProtocolVersion_;
            if (i != 0) {
                codedOutputStream.writeUInt32(1, i);
            }
            int i2 = this.maxSupportedProtocolVersion_;
            if (i2 != 0) {
                codedOutputStream.writeUInt32(2, i2);
            }
            if (getSupportedCipherSuitesList().size() > 0) {
                codedOutputStream.writeUInt32NoTag(26);
                codedOutputStream.writeUInt32NoTag(this.supportedCipherSuitesMemoizedSerializedSize);
            }
            for (int i3 = 0; i3 < this.supportedCipherSuites_.size(); i3++) {
                codedOutputStream.writeEnumNoTag(this.supportedCipherSuites_.getInt(i3));
            }
            if (!this.nonceAccessory_.isEmpty()) {
                codedOutputStream.writeBytes(4, this.nonceAccessory_);
            }
            if (getFlagsList().size() > 0) {
                codedOutputStream.writeUInt32NoTag(42);
                codedOutputStream.writeUInt32NoTag(this.flagsMemoizedSerializedSize);
            }
            for (int i4 = 0; i4 < this.flags_.size(); i4++) {
                codedOutputStream.writeEnumNoTag(this.flags_.getInt(i4));
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public static Builder newBuilder(InitiateHandshake initiateHandshake) {
            return DEFAULT_INSTANCE.createBuilder(initiateHandshake);
        }

        public static InitiateHandshake parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (InitiateHandshake) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static InitiateHandshake parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (InitiateHandshake) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static InitiateHandshake parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (InitiateHandshake) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static InitiateHandshake parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (InitiateHandshake) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static InitiateHandshake parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (InitiateHandshake) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static InitiateHandshake parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (InitiateHandshake) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static InitiateHandshake parseFrom(InputStream inputStream) throws IOException {
            return (InitiateHandshake) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static InitiateHandshake parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (InitiateHandshake) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static InitiateHandshake parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (InitiateHandshake) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static InitiateHandshake parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (InitiateHandshake) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes6.dex */
    public interface InitiateHandshakeOrBuilder extends MessageLiteOrBuilder {
        HandshakeFlags getFlags(int i);

        int getFlagsCount();

        List<HandshakeFlags> getFlagsList();

        int getFlagsValue(int i);

        List<Integer> getFlagsValueList();

        int getMaxSupportedProtocolVersion();

        int getMinSupportedProtocolVersion();

        ByteString getNonceAccessory();

        CipherSuite getSupportedCipherSuites(int i);

        int getSupportedCipherSuitesCount();

        List<CipherSuite> getSupportedCipherSuitesList();

        int getSupportedCipherSuitesValue(int i);

        List<Integer> getSupportedCipherSuitesValueList();
    }

    /* loaded from: classes6.dex */
    public static final class MessageCounter extends GeneratedMessageLite<MessageCounter, Builder> implements MessageCounterOrBuilder {
        public static final int COUNTER_HI_FIELD_NUMBER = 1;
        public static final int COUNTER_LO_FIELD_NUMBER = 2;
        private static final MessageCounter DEFAULT_INSTANCE = new MessageCounter();
        private static volatile Parser<MessageCounter> PARSER;
        private int counterHi_;
        private int counterLo_;

        /* loaded from: classes6.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<MessageCounter, Builder> implements MessageCounterOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                this();
            }

            public Builder clearCounterHi() {
                copyOnWrite();
                ((MessageCounter) this.instance).clearCounterHi();
                return this;
            }

            public Builder clearCounterLo() {
                copyOnWrite();
                ((MessageCounter) this.instance).clearCounterLo();
                return this;
            }

            @Override // com.amazon.alexa.accessory.protocol.Keyexchange.MessageCounterOrBuilder
            public int getCounterHi() {
                return ((MessageCounter) this.instance).getCounterHi();
            }

            @Override // com.amazon.alexa.accessory.protocol.Keyexchange.MessageCounterOrBuilder
            public int getCounterLo() {
                return ((MessageCounter) this.instance).getCounterLo();
            }

            public Builder setCounterHi(int i) {
                copyOnWrite();
                ((MessageCounter) this.instance).setCounterHi(i);
                return this;
            }

            public Builder setCounterLo(int i) {
                copyOnWrite();
                ((MessageCounter) this.instance).setCounterLo(i);
                return this;
            }

            private Builder() {
                super(MessageCounter.DEFAULT_INSTANCE);
            }
        }

        static {
            DEFAULT_INSTANCE.makeImmutable();
        }

        private MessageCounter() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearCounterHi() {
            this.counterHi_ = 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearCounterLo() {
            this.counterLo_ = 0;
        }

        public static MessageCounter getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static MessageCounter parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (MessageCounter) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static MessageCounter parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (MessageCounter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<MessageCounter> parser() {
            return DEFAULT_INSTANCE.mo9954getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setCounterHi(int i) {
            this.counterHi_ = i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setCounterLo(int i) {
            this.counterLo_ = i;
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            boolean z = false;
            switch (methodToInvoke.ordinal()) {
                case 0:
                    return DEFAULT_INSTANCE;
                case 1:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) obj;
                    MessageCounter messageCounter = (MessageCounter) obj2;
                    this.counterHi_ = visitor.visitInt(this.counterHi_ != 0, this.counterHi_, messageCounter.counterHi_ != 0, messageCounter.counterHi_);
                    boolean z2 = this.counterLo_ != 0;
                    int i = this.counterLo_;
                    if (messageCounter.counterLo_ != 0) {
                        z = true;
                    }
                    this.counterLo_ = visitor.visitInt(z2, i, z, messageCounter.counterLo_);
                    GeneratedMessageLite.MergeFromVisitor mergeFromVisitor = GeneratedMessageLite.MergeFromVisitor.INSTANCE;
                    return this;
                case 2:
                    return (byte) 1;
                case 3:
                    return null;
                case 4:
                    CodedInputStream codedInputStream = (CodedInputStream) obj;
                    if (((ExtensionRegistryLite) obj2) == null) {
                        throw new NullPointerException();
                    }
                    while (!z) {
                        try {
                            int readTag = codedInputStream.readTag();
                            if (readTag != 0) {
                                if (readTag == 8) {
                                    this.counterHi_ = codedInputStream.readUInt32();
                                } else if (readTag != 16) {
                                    if (!parseUnknownField(readTag, codedInputStream)) {
                                    }
                                } else {
                                    this.counterLo_ = codedInputStream.readUInt32();
                                }
                            }
                            z = true;
                        } catch (InvalidProtocolBufferException e) {
                            throw new RuntimeException(e.setUnfinishedMessage(this));
                        } catch (IOException e2) {
                            throw new RuntimeException(new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this));
                        }
                    }
                    break;
                case 5:
                    return null;
                case 6:
                    return new MessageCounter();
                case 7:
                    return new Builder(null);
                case 8:
                    break;
                case 9:
                    if (PARSER == null) {
                        synchronized (MessageCounter.class) {
                            if (PARSER == null) {
                                PARSER = new GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
                            }
                        }
                    }
                    return PARSER;
                default:
                    throw new UnsupportedOperationException();
            }
            return DEFAULT_INSTANCE;
        }

        @Override // com.amazon.alexa.accessory.protocol.Keyexchange.MessageCounterOrBuilder
        public int getCounterHi() {
            return this.counterHi_;
        }

        @Override // com.amazon.alexa.accessory.protocol.Keyexchange.MessageCounterOrBuilder
        public int getCounterLo() {
            return this.counterLo_;
        }

        @Override // com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            int i3 = this.counterHi_;
            if (i3 != 0) {
                i2 = 0 + CodedOutputStream.computeUInt32Size(1, i3);
            }
            int i4 = this.counterLo_;
            if (i4 != 0) {
                i2 += CodedOutputStream.computeUInt32Size(2, i4);
            }
            int serializedSize = this.unknownFields.getSerializedSize() + i2;
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            int i = this.counterHi_;
            if (i != 0) {
                codedOutputStream.writeUInt32(1, i);
            }
            int i2 = this.counterLo_;
            if (i2 != 0) {
                codedOutputStream.writeUInt32(2, i2);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public static Builder newBuilder(MessageCounter messageCounter) {
            return DEFAULT_INSTANCE.createBuilder(messageCounter);
        }

        public static MessageCounter parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (MessageCounter) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static MessageCounter parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (MessageCounter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static MessageCounter parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (MessageCounter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static MessageCounter parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (MessageCounter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static MessageCounter parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (MessageCounter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static MessageCounter parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (MessageCounter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static MessageCounter parseFrom(InputStream inputStream) throws IOException {
            return (MessageCounter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static MessageCounter parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (MessageCounter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static MessageCounter parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (MessageCounter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static MessageCounter parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (MessageCounter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes6.dex */
    public interface MessageCounterOrBuilder extends MessageLiteOrBuilder {
        int getCounterHi();

        int getCounterLo();
    }

    /* loaded from: classes6.dex */
    public static final class ResetKey extends GeneratedMessageLite<ResetKey, Builder> implements ResetKeyOrBuilder {
        private static final ResetKey DEFAULT_INSTANCE = new ResetKey();
        public static final int NEW_KEYS_BITMASK_FIELD_NUMBER = 3;
        public static final int NONCE_ACCESSORY_FIELD_NUMBER = 1;
        private static volatile Parser<ResetKey> PARSER = null;
        public static final int ROTATION_EXCLUSION_BITMASK_FIELD_NUMBER = 2;
        private int newKeysBitmask_;
        private ByteString nonceAccessory_ = ByteString.EMPTY;
        private int rotationExclusionBitmask_;

        /* loaded from: classes6.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<ResetKey, Builder> implements ResetKeyOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                this();
            }

            public Builder clearNewKeysBitmask() {
                copyOnWrite();
                ((ResetKey) this.instance).clearNewKeysBitmask();
                return this;
            }

            public Builder clearNonceAccessory() {
                copyOnWrite();
                ((ResetKey) this.instance).clearNonceAccessory();
                return this;
            }

            public Builder clearRotationExclusionBitmask() {
                copyOnWrite();
                ((ResetKey) this.instance).clearRotationExclusionBitmask();
                return this;
            }

            @Override // com.amazon.alexa.accessory.protocol.Keyexchange.ResetKeyOrBuilder
            public int getNewKeysBitmask() {
                return ((ResetKey) this.instance).getNewKeysBitmask();
            }

            @Override // com.amazon.alexa.accessory.protocol.Keyexchange.ResetKeyOrBuilder
            public ByteString getNonceAccessory() {
                return ((ResetKey) this.instance).getNonceAccessory();
            }

            @Override // com.amazon.alexa.accessory.protocol.Keyexchange.ResetKeyOrBuilder
            public int getRotationExclusionBitmask() {
                return ((ResetKey) this.instance).getRotationExclusionBitmask();
            }

            public Builder setNewKeysBitmask(int i) {
                copyOnWrite();
                ((ResetKey) this.instance).setNewKeysBitmask(i);
                return this;
            }

            public Builder setNonceAccessory(ByteString byteString) {
                copyOnWrite();
                ((ResetKey) this.instance).setNonceAccessory(byteString);
                return this;
            }

            public Builder setRotationExclusionBitmask(int i) {
                copyOnWrite();
                ((ResetKey) this.instance).setRotationExclusionBitmask(i);
                return this;
            }

            private Builder() {
                super(ResetKey.DEFAULT_INSTANCE);
            }
        }

        static {
            DEFAULT_INSTANCE.makeImmutable();
        }

        private ResetKey() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearNewKeysBitmask() {
            this.newKeysBitmask_ = 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearNonceAccessory() {
            this.nonceAccessory_ = getDefaultInstance().getNonceAccessory();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearRotationExclusionBitmask() {
            this.rotationExclusionBitmask_ = 0;
        }

        public static ResetKey getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static ResetKey parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (ResetKey) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static ResetKey parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (ResetKey) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<ResetKey> parser() {
            return DEFAULT_INSTANCE.mo9954getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setNewKeysBitmask(int i) {
            this.newKeysBitmask_ = i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setNonceAccessory(ByteString byteString) {
            if (byteString != null) {
                this.nonceAccessory_ = byteString;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setRotationExclusionBitmask(int i) {
            this.rotationExclusionBitmask_ = i;
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            boolean z = false;
            switch (methodToInvoke.ordinal()) {
                case 0:
                    return DEFAULT_INSTANCE;
                case 1:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) obj;
                    ResetKey resetKey = (ResetKey) obj2;
                    this.nonceAccessory_ = visitor.visitByteString(this.nonceAccessory_ != ByteString.EMPTY, this.nonceAccessory_, resetKey.nonceAccessory_ != ByteString.EMPTY, resetKey.nonceAccessory_);
                    this.rotationExclusionBitmask_ = visitor.visitInt(this.rotationExclusionBitmask_ != 0, this.rotationExclusionBitmask_, resetKey.rotationExclusionBitmask_ != 0, resetKey.rotationExclusionBitmask_);
                    boolean z2 = this.newKeysBitmask_ != 0;
                    int i = this.newKeysBitmask_;
                    if (resetKey.newKeysBitmask_ != 0) {
                        z = true;
                    }
                    this.newKeysBitmask_ = visitor.visitInt(z2, i, z, resetKey.newKeysBitmask_);
                    GeneratedMessageLite.MergeFromVisitor mergeFromVisitor = GeneratedMessageLite.MergeFromVisitor.INSTANCE;
                    return this;
                case 2:
                    return (byte) 1;
                case 3:
                    return null;
                case 4:
                    CodedInputStream codedInputStream = (CodedInputStream) obj;
                    if (((ExtensionRegistryLite) obj2) == null) {
                        throw new NullPointerException();
                    }
                    while (!z) {
                        try {
                            int readTag = codedInputStream.readTag();
                            if (readTag != 0) {
                                if (readTag == 10) {
                                    this.nonceAccessory_ = codedInputStream.readBytes();
                                } else if (readTag == 16) {
                                    this.rotationExclusionBitmask_ = codedInputStream.readUInt32();
                                } else if (readTag != 24) {
                                    if (!parseUnknownField(readTag, codedInputStream)) {
                                    }
                                } else {
                                    this.newKeysBitmask_ = codedInputStream.readUInt32();
                                }
                            }
                            z = true;
                        } catch (InvalidProtocolBufferException e) {
                            throw new RuntimeException(e.setUnfinishedMessage(this));
                        } catch (IOException e2) {
                            throw new RuntimeException(new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this));
                        }
                    }
                    break;
                case 5:
                    return null;
                case 6:
                    return new ResetKey();
                case 7:
                    return new Builder(null);
                case 8:
                    break;
                case 9:
                    if (PARSER == null) {
                        synchronized (ResetKey.class) {
                            if (PARSER == null) {
                                PARSER = new GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
                            }
                        }
                    }
                    return PARSER;
                default:
                    throw new UnsupportedOperationException();
            }
            return DEFAULT_INSTANCE;
        }

        @Override // com.amazon.alexa.accessory.protocol.Keyexchange.ResetKeyOrBuilder
        public int getNewKeysBitmask() {
            return this.newKeysBitmask_;
        }

        @Override // com.amazon.alexa.accessory.protocol.Keyexchange.ResetKeyOrBuilder
        public ByteString getNonceAccessory() {
            return this.nonceAccessory_;
        }

        @Override // com.amazon.alexa.accessory.protocol.Keyexchange.ResetKeyOrBuilder
        public int getRotationExclusionBitmask() {
            return this.rotationExclusionBitmask_;
        }

        @Override // com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            if (!this.nonceAccessory_.isEmpty()) {
                i2 = 0 + CodedOutputStream.computeBytesSize(1, this.nonceAccessory_);
            }
            int i3 = this.rotationExclusionBitmask_;
            if (i3 != 0) {
                i2 += CodedOutputStream.computeUInt32Size(2, i3);
            }
            int i4 = this.newKeysBitmask_;
            if (i4 != 0) {
                i2 += CodedOutputStream.computeUInt32Size(3, i4);
            }
            int serializedSize = this.unknownFields.getSerializedSize() + i2;
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (!this.nonceAccessory_.isEmpty()) {
                codedOutputStream.writeBytes(1, this.nonceAccessory_);
            }
            int i = this.rotationExclusionBitmask_;
            if (i != 0) {
                codedOutputStream.writeUInt32(2, i);
            }
            int i2 = this.newKeysBitmask_;
            if (i2 != 0) {
                codedOutputStream.writeUInt32(3, i2);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public static Builder newBuilder(ResetKey resetKey) {
            return DEFAULT_INSTANCE.createBuilder(resetKey);
        }

        public static ResetKey parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ResetKey) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static ResetKey parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (ResetKey) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static ResetKey parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (ResetKey) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static ResetKey parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (ResetKey) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static ResetKey parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (ResetKey) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static ResetKey parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (ResetKey) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static ResetKey parseFrom(InputStream inputStream) throws IOException {
            return (ResetKey) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static ResetKey parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ResetKey) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static ResetKey parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (ResetKey) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static ResetKey parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ResetKey) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes6.dex */
    public interface ResetKeyOrBuilder extends MessageLiteOrBuilder {
        int getNewKeysBitmask();

        ByteString getNonceAccessory();

        int getRotationExclusionBitmask();
    }

    /* loaded from: classes6.dex */
    public static final class ResetRootKeys extends GeneratedMessageLite<ResetRootKeys, Builder> implements ResetRootKeysOrBuilder {
        private static final ResetRootKeys DEFAULT_INSTANCE = new ResetRootKeys();
        public static final int FORCE_RESET_FIELD_NUMBER = 1;
        private static volatile Parser<ResetRootKeys> PARSER;
        private boolean forceReset_;

        /* loaded from: classes6.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<ResetRootKeys, Builder> implements ResetRootKeysOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                this();
            }

            public Builder clearForceReset() {
                copyOnWrite();
                ((ResetRootKeys) this.instance).clearForceReset();
                return this;
            }

            @Override // com.amazon.alexa.accessory.protocol.Keyexchange.ResetRootKeysOrBuilder
            public boolean getForceReset() {
                return ((ResetRootKeys) this.instance).getForceReset();
            }

            public Builder setForceReset(boolean z) {
                copyOnWrite();
                ((ResetRootKeys) this.instance).setForceReset(z);
                return this;
            }

            private Builder() {
                super(ResetRootKeys.DEFAULT_INSTANCE);
            }
        }

        static {
            DEFAULT_INSTANCE.makeImmutable();
        }

        private ResetRootKeys() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearForceReset() {
            this.forceReset_ = false;
        }

        public static ResetRootKeys getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static ResetRootKeys parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (ResetRootKeys) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static ResetRootKeys parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (ResetRootKeys) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<ResetRootKeys> parser() {
            return DEFAULT_INSTANCE.mo9954getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setForceReset(boolean z) {
            this.forceReset_ = z;
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            switch (methodToInvoke.ordinal()) {
                case 0:
                    return DEFAULT_INSTANCE;
                case 1:
                    boolean z = this.forceReset_;
                    boolean z2 = ((ResetRootKeys) obj2).forceReset_;
                    this.forceReset_ = ((GeneratedMessageLite.Visitor) obj).visitBoolean(z, z, z2, z2);
                    GeneratedMessageLite.MergeFromVisitor mergeFromVisitor = GeneratedMessageLite.MergeFromVisitor.INSTANCE;
                    return this;
                case 2:
                    return (byte) 1;
                case 3:
                    return null;
                case 4:
                    CodedInputStream codedInputStream = (CodedInputStream) obj;
                    if (((ExtensionRegistryLite) obj2) == null) {
                        throw new NullPointerException();
                    }
                    boolean z3 = false;
                    while (!z3) {
                        try {
                            try {
                                int readTag = codedInputStream.readTag();
                                if (readTag != 0) {
                                    if (readTag != 8) {
                                        if (!parseUnknownField(readTag, codedInputStream)) {
                                        }
                                    } else {
                                        this.forceReset_ = codedInputStream.readBool();
                                    }
                                }
                                z3 = true;
                            } catch (IOException e) {
                                throw new RuntimeException(new InvalidProtocolBufferException(e.getMessage()).setUnfinishedMessage(this));
                            }
                        } catch (InvalidProtocolBufferException e2) {
                            throw new RuntimeException(e2.setUnfinishedMessage(this));
                        }
                    }
                    break;
                case 5:
                    return null;
                case 6:
                    return new ResetRootKeys();
                case 7:
                    return new Builder(null);
                case 8:
                    break;
                case 9:
                    if (PARSER == null) {
                        synchronized (ResetRootKeys.class) {
                            if (PARSER == null) {
                                PARSER = new GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
                            }
                        }
                    }
                    return PARSER;
                default:
                    throw new UnsupportedOperationException();
            }
            return DEFAULT_INSTANCE;
        }

        @Override // com.amazon.alexa.accessory.protocol.Keyexchange.ResetRootKeysOrBuilder
        public boolean getForceReset() {
            return this.forceReset_;
        }

        @Override // com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            boolean z = this.forceReset_;
            if (z) {
                i2 = 0 + CodedOutputStream.computeBoolSize(1, z);
            }
            int serializedSize = this.unknownFields.getSerializedSize() + i2;
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            boolean z = this.forceReset_;
            if (z) {
                codedOutputStream.writeBool(1, z);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public static Builder newBuilder(ResetRootKeys resetRootKeys) {
            return DEFAULT_INSTANCE.createBuilder(resetRootKeys);
        }

        public static ResetRootKeys parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ResetRootKeys) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static ResetRootKeys parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (ResetRootKeys) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static ResetRootKeys parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (ResetRootKeys) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static ResetRootKeys parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (ResetRootKeys) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static ResetRootKeys parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (ResetRootKeys) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static ResetRootKeys parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (ResetRootKeys) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static ResetRootKeys parseFrom(InputStream inputStream) throws IOException {
            return (ResetRootKeys) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static ResetRootKeys parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ResetRootKeys) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static ResetRootKeys parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (ResetRootKeys) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static ResetRootKeys parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ResetRootKeys) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes6.dex */
    public interface ResetRootKeysOrBuilder extends MessageLiteOrBuilder {
        boolean getForceReset();
    }

    /* loaded from: classes6.dex */
    public static final class TestCipherBlob extends GeneratedMessageLite<TestCipherBlob, Builder> implements TestCipherBlobOrBuilder {
        private static final TestCipherBlob DEFAULT_INSTANCE = new TestCipherBlob();
        private static volatile Parser<TestCipherBlob> PARSER = null;
        public static final int TEST_CIPHER_TEXT_FIELD_NUMBER = 2;
        public static final int TEST_HMAC_FIELD_NUMBER = 3;
        public static final int TEST_IV_FIELD_NUMBER = 1;
        private ByteString testCipherText_;
        private ByteString testHmac_;
        private ByteString testIv_;

        /* loaded from: classes6.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<TestCipherBlob, Builder> implements TestCipherBlobOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                this();
            }

            public Builder clearTestCipherText() {
                copyOnWrite();
                ((TestCipherBlob) this.instance).clearTestCipherText();
                return this;
            }

            public Builder clearTestHmac() {
                copyOnWrite();
                ((TestCipherBlob) this.instance).clearTestHmac();
                return this;
            }

            public Builder clearTestIv() {
                copyOnWrite();
                ((TestCipherBlob) this.instance).clearTestIv();
                return this;
            }

            @Override // com.amazon.alexa.accessory.protocol.Keyexchange.TestCipherBlobOrBuilder
            public ByteString getTestCipherText() {
                return ((TestCipherBlob) this.instance).getTestCipherText();
            }

            @Override // com.amazon.alexa.accessory.protocol.Keyexchange.TestCipherBlobOrBuilder
            public ByteString getTestHmac() {
                return ((TestCipherBlob) this.instance).getTestHmac();
            }

            @Override // com.amazon.alexa.accessory.protocol.Keyexchange.TestCipherBlobOrBuilder
            public ByteString getTestIv() {
                return ((TestCipherBlob) this.instance).getTestIv();
            }

            public Builder setTestCipherText(ByteString byteString) {
                copyOnWrite();
                ((TestCipherBlob) this.instance).setTestCipherText(byteString);
                return this;
            }

            public Builder setTestHmac(ByteString byteString) {
                copyOnWrite();
                ((TestCipherBlob) this.instance).setTestHmac(byteString);
                return this;
            }

            public Builder setTestIv(ByteString byteString) {
                copyOnWrite();
                ((TestCipherBlob) this.instance).setTestIv(byteString);
                return this;
            }

            private Builder() {
                super(TestCipherBlob.DEFAULT_INSTANCE);
            }
        }

        static {
            DEFAULT_INSTANCE.makeImmutable();
        }

        private TestCipherBlob() {
            ByteString byteString = ByteString.EMPTY;
            this.testIv_ = byteString;
            this.testCipherText_ = byteString;
            this.testHmac_ = byteString;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearTestCipherText() {
            this.testCipherText_ = getDefaultInstance().getTestCipherText();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearTestHmac() {
            this.testHmac_ = getDefaultInstance().getTestHmac();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearTestIv() {
            this.testIv_ = getDefaultInstance().getTestIv();
        }

        public static TestCipherBlob getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static TestCipherBlob parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (TestCipherBlob) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static TestCipherBlob parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (TestCipherBlob) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<TestCipherBlob> parser() {
            return DEFAULT_INSTANCE.mo9954getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setTestCipherText(ByteString byteString) {
            if (byteString != null) {
                this.testCipherText_ = byteString;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setTestHmac(ByteString byteString) {
            if (byteString != null) {
                this.testHmac_ = byteString;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setTestIv(ByteString byteString) {
            if (byteString != null) {
                this.testIv_ = byteString;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            boolean z = false;
            switch (methodToInvoke.ordinal()) {
                case 0:
                    return DEFAULT_INSTANCE;
                case 1:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) obj;
                    TestCipherBlob testCipherBlob = (TestCipherBlob) obj2;
                    this.testIv_ = visitor.visitByteString(this.testIv_ != ByteString.EMPTY, this.testIv_, testCipherBlob.testIv_ != ByteString.EMPTY, testCipherBlob.testIv_);
                    this.testCipherText_ = visitor.visitByteString(this.testCipherText_ != ByteString.EMPTY, this.testCipherText_, testCipherBlob.testCipherText_ != ByteString.EMPTY, testCipherBlob.testCipherText_);
                    boolean z2 = this.testHmac_ != ByteString.EMPTY;
                    ByteString byteString = this.testHmac_;
                    if (testCipherBlob.testHmac_ != ByteString.EMPTY) {
                        z = true;
                    }
                    this.testHmac_ = visitor.visitByteString(z2, byteString, z, testCipherBlob.testHmac_);
                    GeneratedMessageLite.MergeFromVisitor mergeFromVisitor = GeneratedMessageLite.MergeFromVisitor.INSTANCE;
                    return this;
                case 2:
                    return (byte) 1;
                case 3:
                    return null;
                case 4:
                    CodedInputStream codedInputStream = (CodedInputStream) obj;
                    if (((ExtensionRegistryLite) obj2) == null) {
                        throw new NullPointerException();
                    }
                    while (!z) {
                        try {
                            int readTag = codedInputStream.readTag();
                            if (readTag != 0) {
                                if (readTag == 10) {
                                    this.testIv_ = codedInputStream.readBytes();
                                } else if (readTag == 18) {
                                    this.testCipherText_ = codedInputStream.readBytes();
                                } else if (readTag != 26) {
                                    if (!parseUnknownField(readTag, codedInputStream)) {
                                    }
                                } else {
                                    this.testHmac_ = codedInputStream.readBytes();
                                }
                            }
                            z = true;
                        } catch (InvalidProtocolBufferException e) {
                            throw new RuntimeException(e.setUnfinishedMessage(this));
                        } catch (IOException e2) {
                            throw new RuntimeException(new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this));
                        }
                    }
                    break;
                case 5:
                    return null;
                case 6:
                    return new TestCipherBlob();
                case 7:
                    return new Builder(null);
                case 8:
                    break;
                case 9:
                    if (PARSER == null) {
                        synchronized (TestCipherBlob.class) {
                            if (PARSER == null) {
                                PARSER = new GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
                            }
                        }
                    }
                    return PARSER;
                default:
                    throw new UnsupportedOperationException();
            }
            return DEFAULT_INSTANCE;
        }

        @Override // com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            if (!this.testIv_.isEmpty()) {
                i2 = 0 + CodedOutputStream.computeBytesSize(1, this.testIv_);
            }
            if (!this.testCipherText_.isEmpty()) {
                i2 += CodedOutputStream.computeBytesSize(2, this.testCipherText_);
            }
            if (!this.testHmac_.isEmpty()) {
                i2 += CodedOutputStream.computeBytesSize(3, this.testHmac_);
            }
            int serializedSize = this.unknownFields.getSerializedSize() + i2;
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.amazon.alexa.accessory.protocol.Keyexchange.TestCipherBlobOrBuilder
        public ByteString getTestCipherText() {
            return this.testCipherText_;
        }

        @Override // com.amazon.alexa.accessory.protocol.Keyexchange.TestCipherBlobOrBuilder
        public ByteString getTestHmac() {
            return this.testHmac_;
        }

        @Override // com.amazon.alexa.accessory.protocol.Keyexchange.TestCipherBlobOrBuilder
        public ByteString getTestIv() {
            return this.testIv_;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (!this.testIv_.isEmpty()) {
                codedOutputStream.writeBytes(1, this.testIv_);
            }
            if (!this.testCipherText_.isEmpty()) {
                codedOutputStream.writeBytes(2, this.testCipherText_);
            }
            if (!this.testHmac_.isEmpty()) {
                codedOutputStream.writeBytes(3, this.testHmac_);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public static Builder newBuilder(TestCipherBlob testCipherBlob) {
            return DEFAULT_INSTANCE.createBuilder(testCipherBlob);
        }

        public static TestCipherBlob parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (TestCipherBlob) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static TestCipherBlob parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (TestCipherBlob) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static TestCipherBlob parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (TestCipherBlob) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static TestCipherBlob parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (TestCipherBlob) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static TestCipherBlob parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (TestCipherBlob) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static TestCipherBlob parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (TestCipherBlob) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static TestCipherBlob parseFrom(InputStream inputStream) throws IOException {
            return (TestCipherBlob) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static TestCipherBlob parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (TestCipherBlob) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static TestCipherBlob parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (TestCipherBlob) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static TestCipherBlob parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (TestCipherBlob) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes6.dex */
    public interface TestCipherBlobOrBuilder extends MessageLiteOrBuilder {
        ByteString getTestCipherText();

        ByteString getTestHmac();

        ByteString getTestIv();
    }

    /* loaded from: classes6.dex */
    public enum UserConfirmationFailureReason implements Internal.EnumLite {
        UC_FAILURE_UNSPECIFIED(0),
        UC_FAILURE_USER_DECLINE(1),
        UC_FAILURE_TIMEOUT(2),
        UNRECOGNIZED(-1);
        
        public static final int UC_FAILURE_TIMEOUT_VALUE = 2;
        public static final int UC_FAILURE_UNSPECIFIED_VALUE = 0;
        public static final int UC_FAILURE_USER_DECLINE_VALUE = 1;
        private static final Internal.EnumLiteMap<UserConfirmationFailureReason> internalValueMap = new Internal.EnumLiteMap<UserConfirmationFailureReason>() { // from class: com.amazon.alexa.accessory.protocol.Keyexchange.UserConfirmationFailureReason.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.google.protobuf.Internal.EnumLiteMap
            /* renamed from: findValueByNumber */
            public UserConfirmationFailureReason mo9850findValueByNumber(int i) {
                return UserConfirmationFailureReason.forNumber(i);
            }
        };
        private final int value;

        UserConfirmationFailureReason(int i) {
            this.value = i;
        }

        public static UserConfirmationFailureReason forNumber(int i) {
            if (i != 0) {
                if (i == 1) {
                    return UC_FAILURE_USER_DECLINE;
                }
                if (i == 2) {
                    return UC_FAILURE_TIMEOUT;
                }
                return null;
            }
            return UC_FAILURE_UNSPECIFIED;
        }

        public static Internal.EnumLiteMap<UserConfirmationFailureReason> internalGetValueMap() {
            return internalValueMap;
        }

        @Override // com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            if (this != UNRECOGNIZED) {
                return this.value;
            }
            throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
        }

        @Deprecated
        public static UserConfirmationFailureReason valueOf(int i) {
            return forNumber(i);
        }
    }

    /* loaded from: classes6.dex */
    public static final class UserConfirmed extends GeneratedMessageLite<UserConfirmed, Builder> implements UserConfirmedOrBuilder {
        private static final UserConfirmed DEFAULT_INSTANCE = new UserConfirmed();
        public static final int FAILURE_REASON_FIELD_NUMBER = 2;
        public static final int HAS_USER_CONFIRMED_FIELD_NUMBER = 1;
        private static volatile Parser<UserConfirmed> PARSER;
        private int failureReason_;
        private boolean hasUserConfirmed_;

        /* loaded from: classes6.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<UserConfirmed, Builder> implements UserConfirmedOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                this();
            }

            public Builder clearFailureReason() {
                copyOnWrite();
                ((UserConfirmed) this.instance).clearFailureReason();
                return this;
            }

            public Builder clearHasUserConfirmed() {
                copyOnWrite();
                ((UserConfirmed) this.instance).clearHasUserConfirmed();
                return this;
            }

            @Override // com.amazon.alexa.accessory.protocol.Keyexchange.UserConfirmedOrBuilder
            public UserConfirmationFailureReason getFailureReason() {
                return ((UserConfirmed) this.instance).getFailureReason();
            }

            @Override // com.amazon.alexa.accessory.protocol.Keyexchange.UserConfirmedOrBuilder
            public int getFailureReasonValue() {
                return ((UserConfirmed) this.instance).getFailureReasonValue();
            }

            @Override // com.amazon.alexa.accessory.protocol.Keyexchange.UserConfirmedOrBuilder
            public boolean getHasUserConfirmed() {
                return ((UserConfirmed) this.instance).getHasUserConfirmed();
            }

            public Builder setFailureReason(UserConfirmationFailureReason userConfirmationFailureReason) {
                copyOnWrite();
                ((UserConfirmed) this.instance).setFailureReason(userConfirmationFailureReason);
                return this;
            }

            public Builder setFailureReasonValue(int i) {
                copyOnWrite();
                ((UserConfirmed) this.instance).setFailureReasonValue(i);
                return this;
            }

            public Builder setHasUserConfirmed(boolean z) {
                copyOnWrite();
                ((UserConfirmed) this.instance).setHasUserConfirmed(z);
                return this;
            }

            private Builder() {
                super(UserConfirmed.DEFAULT_INSTANCE);
            }
        }

        static {
            DEFAULT_INSTANCE.makeImmutable();
        }

        private UserConfirmed() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearFailureReason() {
            this.failureReason_ = 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearHasUserConfirmed() {
            this.hasUserConfirmed_ = false;
        }

        public static UserConfirmed getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static UserConfirmed parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (UserConfirmed) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static UserConfirmed parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (UserConfirmed) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<UserConfirmed> parser() {
            return DEFAULT_INSTANCE.mo9954getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setFailureReason(UserConfirmationFailureReason userConfirmationFailureReason) {
            if (userConfirmationFailureReason != null) {
                this.failureReason_ = userConfirmationFailureReason.getNumber();
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setFailureReasonValue(int i) {
            this.failureReason_ = i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setHasUserConfirmed(boolean z) {
            this.hasUserConfirmed_ = z;
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            boolean z = false;
            switch (methodToInvoke.ordinal()) {
                case 0:
                    return DEFAULT_INSTANCE;
                case 1:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) obj;
                    UserConfirmed userConfirmed = (UserConfirmed) obj2;
                    boolean z2 = this.hasUserConfirmed_;
                    boolean z3 = userConfirmed.hasUserConfirmed_;
                    this.hasUserConfirmed_ = visitor.visitBoolean(z2, z2, z3, z3);
                    boolean z4 = this.failureReason_ != 0;
                    int i = this.failureReason_;
                    if (userConfirmed.failureReason_ != 0) {
                        z = true;
                    }
                    this.failureReason_ = visitor.visitInt(z4, i, z, userConfirmed.failureReason_);
                    GeneratedMessageLite.MergeFromVisitor mergeFromVisitor = GeneratedMessageLite.MergeFromVisitor.INSTANCE;
                    return this;
                case 2:
                    return (byte) 1;
                case 3:
                    return null;
                case 4:
                    CodedInputStream codedInputStream = (CodedInputStream) obj;
                    if (((ExtensionRegistryLite) obj2) == null) {
                        throw new NullPointerException();
                    }
                    while (!z) {
                        try {
                            int readTag = codedInputStream.readTag();
                            if (readTag != 0) {
                                if (readTag == 8) {
                                    this.hasUserConfirmed_ = codedInputStream.readBool();
                                } else if (readTag != 16) {
                                    if (!parseUnknownField(readTag, codedInputStream)) {
                                    }
                                } else {
                                    this.failureReason_ = codedInputStream.readEnum();
                                }
                            }
                            z = true;
                        } catch (InvalidProtocolBufferException e) {
                            throw new RuntimeException(e.setUnfinishedMessage(this));
                        } catch (IOException e2) {
                            throw new RuntimeException(new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this));
                        }
                    }
                    break;
                case 5:
                    return null;
                case 6:
                    return new UserConfirmed();
                case 7:
                    return new Builder(null);
                case 8:
                    break;
                case 9:
                    if (PARSER == null) {
                        synchronized (UserConfirmed.class) {
                            if (PARSER == null) {
                                PARSER = new GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
                            }
                        }
                    }
                    return PARSER;
                default:
                    throw new UnsupportedOperationException();
            }
            return DEFAULT_INSTANCE;
        }

        @Override // com.amazon.alexa.accessory.protocol.Keyexchange.UserConfirmedOrBuilder
        public UserConfirmationFailureReason getFailureReason() {
            UserConfirmationFailureReason forNumber = UserConfirmationFailureReason.forNumber(this.failureReason_);
            return forNumber == null ? UserConfirmationFailureReason.UNRECOGNIZED : forNumber;
        }

        @Override // com.amazon.alexa.accessory.protocol.Keyexchange.UserConfirmedOrBuilder
        public int getFailureReasonValue() {
            return this.failureReason_;
        }

        @Override // com.amazon.alexa.accessory.protocol.Keyexchange.UserConfirmedOrBuilder
        public boolean getHasUserConfirmed() {
            return this.hasUserConfirmed_;
        }

        @Override // com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            boolean z = this.hasUserConfirmed_;
            if (z) {
                i2 = 0 + CodedOutputStream.computeBoolSize(1, z);
            }
            if (this.failureReason_ != UserConfirmationFailureReason.UC_FAILURE_UNSPECIFIED.getNumber()) {
                i2 += CodedOutputStream.computeEnumSize(2, this.failureReason_);
            }
            int serializedSize = this.unknownFields.getSerializedSize() + i2;
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            boolean z = this.hasUserConfirmed_;
            if (z) {
                codedOutputStream.writeBool(1, z);
            }
            if (this.failureReason_ != UserConfirmationFailureReason.UC_FAILURE_UNSPECIFIED.getNumber()) {
                codedOutputStream.writeEnum(2, this.failureReason_);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public static Builder newBuilder(UserConfirmed userConfirmed) {
            return DEFAULT_INSTANCE.createBuilder(userConfirmed);
        }

        public static UserConfirmed parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (UserConfirmed) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static UserConfirmed parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (UserConfirmed) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static UserConfirmed parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (UserConfirmed) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static UserConfirmed parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (UserConfirmed) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static UserConfirmed parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (UserConfirmed) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static UserConfirmed parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (UserConfirmed) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static UserConfirmed parseFrom(InputStream inputStream) throws IOException {
            return (UserConfirmed) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static UserConfirmed parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (UserConfirmed) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static UserConfirmed parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (UserConfirmed) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static UserConfirmed parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (UserConfirmed) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes6.dex */
    public interface UserConfirmedOrBuilder extends MessageLiteOrBuilder {
        UserConfirmationFailureReason getFailureReason();

        int getFailureReasonValue();

        boolean getHasUserConfirmed();
    }

    private Keyexchange() {
    }

    public static void registerAllExtensions(ExtensionRegistryLite extensionRegistryLite) {
    }
}
