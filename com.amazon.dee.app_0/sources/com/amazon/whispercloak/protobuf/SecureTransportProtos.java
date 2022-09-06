package com.amazon.whispercloak.protobuf;

import com.amazon.dee.app.services.metrics.AlexaMetricsConstants;
import com.amazon.identity.auth.device.datastore.DatabaseHelper;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.protobuf.AbstractMessage;
import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.AbstractParser;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.Parser;
import com.google.protobuf.SingleFieldBuilderV3;
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/* loaded from: classes13.dex */
public final class SecureTransportProtos {
    private static Descriptors.FileDescriptor descriptor;
    private static final Descriptors.Descriptor internal_static_protobuf_AesGCMSecureMessage_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_protobuf_AesGCMSecureMessage_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_protobuf_AuthenticatedECDHEKeyExchangeRequest_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_protobuf_AuthenticatedECDHEKeyExchangeRequest_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_protobuf_AuthenticatedECDHEKeyExchangeResponse_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_protobuf_AuthenticatedECDHEKeyExchangeResponse_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_protobuf_KeyExchangeError_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_protobuf_KeyExchangeError_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_protobuf_ProvisionableDeviceAuthenticationData_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_protobuf_ProvisionableDeviceAuthenticationData_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_protobuf_UnauthenticatedECDHEKeyExchangeRequest_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_protobuf_UnauthenticatedECDHEKeyExchangeRequest_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_protobuf_UnauthenticatedECDHEKeyExchangeResponse_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_protobuf_UnauthenticatedECDHEKeyExchangeResponse_fieldAccessorTable;

    /* loaded from: classes13.dex */
    public static final class AesGCMSecureMessage extends GeneratedMessageV3 implements AesGCMSecureMessageOrBuilder {
        public static final int AAD_FIELD_NUMBER = 4;
        public static final int INITIALIZATIONVECTOR_FIELD_NUMBER = 1;
        public static final int MAC_FIELD_NUMBER = 3;
        public static final int PAYLOAD_FIELD_NUMBER = 2;
        private static final long serialVersionUID = 0;
        private ByteString aad_;
        private int bitField0_;
        private ByteString initializationVector_;
        private ByteString mac_;
        private byte memoizedIsInitialized;
        private ByteString payload_;
        private static final AesGCMSecureMessage DEFAULT_INSTANCE = new AesGCMSecureMessage();
        @Deprecated
        public static final Parser<AesGCMSecureMessage> PARSER = new AbstractParser<AesGCMSecureMessage>() { // from class: com.amazon.whispercloak.protobuf.SecureTransportProtos.AesGCMSecureMessage.1
            @Override // com.google.protobuf.Parser
            /* renamed from: parsePartialFrom */
            public AesGCMSecureMessage mo10082parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new AesGCMSecureMessage(codedInputStream, extensionRegistryLite);
            }
        };

        public static AesGCMSecureMessage getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return SecureTransportProtos.internal_static_protobuf_AesGCMSecureMessage_descriptor;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.mo10081toBuilder();
        }

        public static AesGCMSecureMessage parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (AesGCMSecureMessage) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static AesGCMSecureMessage parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.mo8402parseFrom(byteBuffer);
        }

        public static Parser<AesGCMSecureMessage> parser() {
            return PARSER;
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof AesGCMSecureMessage)) {
                return super.equals(obj);
            }
            AesGCMSecureMessage aesGCMSecureMessage = (AesGCMSecureMessage) obj;
            boolean z = hasInitializationVector() == aesGCMSecureMessage.hasInitializationVector();
            if (hasInitializationVector()) {
                z = z && getInitializationVector().equals(aesGCMSecureMessage.getInitializationVector());
            }
            boolean z2 = z && hasPayload() == aesGCMSecureMessage.hasPayload();
            if (hasPayload()) {
                z2 = z2 && getPayload().equals(aesGCMSecureMessage.getPayload());
            }
            boolean z3 = z2 && hasMac() == aesGCMSecureMessage.hasMac();
            if (hasMac()) {
                z3 = z3 && getMac().equals(aesGCMSecureMessage.getMac());
            }
            boolean z4 = z3 && hasAad() == aesGCMSecureMessage.hasAad();
            if (hasAad()) {
                z4 = z4 && getAad().equals(aesGCMSecureMessage.getAad());
            }
            return z4 && this.unknownFields.equals(aesGCMSecureMessage.unknownFields);
        }

        @Override // com.amazon.whispercloak.protobuf.SecureTransportProtos.AesGCMSecureMessageOrBuilder
        public ByteString getAad() {
            return this.aad_;
        }

        @Override // com.amazon.whispercloak.protobuf.SecureTransportProtos.AesGCMSecureMessageOrBuilder
        public ByteString getInitializationVector() {
            return this.initializationVector_;
        }

        @Override // com.amazon.whispercloak.protobuf.SecureTransportProtos.AesGCMSecureMessageOrBuilder
        public ByteString getMac() {
            return this.mac_;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
        /* renamed from: getParserForType */
        public Parser<AesGCMSecureMessage> mo9954getParserForType() {
            return PARSER;
        }

        @Override // com.amazon.whispercloak.protobuf.SecureTransportProtos.AesGCMSecureMessageOrBuilder
        public ByteString getPayload() {
            return this.payload_;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            if ((this.bitField0_ & 1) == 1) {
                i2 = 0 + CodedOutputStream.computeBytesSize(1, this.initializationVector_);
            }
            if ((this.bitField0_ & 2) == 2) {
                i2 += CodedOutputStream.computeBytesSize(2, this.payload_);
            }
            if ((this.bitField0_ & 4) == 4) {
                i2 += CodedOutputStream.computeBytesSize(3, this.mac_);
            }
            if ((this.bitField0_ & 8) == 8) {
                i2 += CodedOutputStream.computeBytesSize(4, this.aad_);
            }
            int serializedSize = this.unknownFields.getSerializedSize() + i2;
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        @Override // com.amazon.whispercloak.protobuf.SecureTransportProtos.AesGCMSecureMessageOrBuilder
        public boolean hasAad() {
            return (this.bitField0_ & 8) == 8;
        }

        @Override // com.amazon.whispercloak.protobuf.SecureTransportProtos.AesGCMSecureMessageOrBuilder
        public boolean hasInitializationVector() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // com.amazon.whispercloak.protobuf.SecureTransportProtos.AesGCMSecureMessageOrBuilder
        public boolean hasMac() {
            return (this.bitField0_ & 4) == 4;
        }

        @Override // com.amazon.whispercloak.protobuf.SecureTransportProtos.AesGCMSecureMessageOrBuilder
        public boolean hasPayload() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public int hashCode() {
            int i = this.memoizedHashCode;
            if (i != 0) {
                return i;
            }
            int hashCode = getDescriptor().hashCode() + 779;
            if (hasInitializationVector()) {
                hashCode = GeneratedOutlineSupport1.outline4(hashCode, 37, 1, 53) + getInitializationVector().hashCode();
            }
            if (hasPayload()) {
                hashCode = GeneratedOutlineSupport1.outline4(hashCode, 37, 2, 53) + getPayload().hashCode();
            }
            if (hasMac()) {
                hashCode = GeneratedOutlineSupport1.outline4(hashCode, 37, 3, 53) + getMac().hashCode();
            }
            if (hasAad()) {
                hashCode = GeneratedOutlineSupport1.outline4(hashCode, 37, 4, 53) + getAad().hashCode();
            }
            int hashCode2 = this.unknownFields.hashCode() + (hashCode * 29);
            this.memoizedHashCode = hashCode2;
            return hashCode2;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return SecureTransportProtos.internal_static_protobuf_AesGCMSecureMessage_fieldAccessorTable.ensureFieldAccessorsInitialized(AesGCMSecureMessage.class, Builder.class);
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            byte b = this.memoizedIsInitialized;
            if (b == 1) {
                return true;
            }
            if (b == 0) {
                return false;
            }
            if (!hasInitializationVector()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            } else if (!hasPayload()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            } else if (!hasMac()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            } else if (!hasAad()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            } else {
                this.memoizedIsInitialized = (byte) 1;
                return true;
            }
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                codedOutputStream.writeBytes(1, this.initializationVector_);
            }
            if ((this.bitField0_ & 2) == 2) {
                codedOutputStream.writeBytes(2, this.payload_);
            }
            if ((this.bitField0_ & 4) == 4) {
                codedOutputStream.writeBytes(3, this.mac_);
            }
            if ((this.bitField0_ & 8) == 8) {
                codedOutputStream.writeBytes(4, this.aad_);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        /* loaded from: classes13.dex */
        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements AesGCMSecureMessageOrBuilder {
            private ByteString aad_;
            private int bitField0_;
            private ByteString initializationVector_;
            private ByteString mac_;
            private ByteString payload_;

            public static final Descriptors.Descriptor getDescriptor() {
                return SecureTransportProtos.internal_static_protobuf_AesGCMSecureMessage_descriptor;
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = GeneratedMessageV3.alwaysUseFieldBuilders;
            }

            public Builder clearAad() {
                this.bitField0_ &= -9;
                this.aad_ = AesGCMSecureMessage.getDefaultInstance().getAad();
                onChanged();
                return this;
            }

            public Builder clearInitializationVector() {
                this.bitField0_ &= -2;
                this.initializationVector_ = AesGCMSecureMessage.getDefaultInstance().getInitializationVector();
                onChanged();
                return this;
            }

            public Builder clearMac() {
                this.bitField0_ &= -5;
                this.mac_ = AesGCMSecureMessage.getDefaultInstance().getMac();
                onChanged();
                return this;
            }

            public Builder clearPayload() {
                this.bitField0_ &= -3;
                this.payload_ = AesGCMSecureMessage.getDefaultInstance().getPayload();
                onChanged();
                return this;
            }

            @Override // com.amazon.whispercloak.protobuf.SecureTransportProtos.AesGCMSecureMessageOrBuilder
            public ByteString getAad() {
                return this.aad_;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return SecureTransportProtos.internal_static_protobuf_AesGCMSecureMessage_descriptor;
            }

            @Override // com.amazon.whispercloak.protobuf.SecureTransportProtos.AesGCMSecureMessageOrBuilder
            public ByteString getInitializationVector() {
                return this.initializationVector_;
            }

            @Override // com.amazon.whispercloak.protobuf.SecureTransportProtos.AesGCMSecureMessageOrBuilder
            public ByteString getMac() {
                return this.mac_;
            }

            @Override // com.amazon.whispercloak.protobuf.SecureTransportProtos.AesGCMSecureMessageOrBuilder
            public ByteString getPayload() {
                return this.payload_;
            }

            @Override // com.amazon.whispercloak.protobuf.SecureTransportProtos.AesGCMSecureMessageOrBuilder
            public boolean hasAad() {
                return (this.bitField0_ & 8) == 8;
            }

            @Override // com.amazon.whispercloak.protobuf.SecureTransportProtos.AesGCMSecureMessageOrBuilder
            public boolean hasInitializationVector() {
                return (this.bitField0_ & 1) == 1;
            }

            @Override // com.amazon.whispercloak.protobuf.SecureTransportProtos.AesGCMSecureMessageOrBuilder
            public boolean hasMac() {
                return (this.bitField0_ & 4) == 4;
            }

            @Override // com.amazon.whispercloak.protobuf.SecureTransportProtos.AesGCMSecureMessageOrBuilder
            public boolean hasPayload() {
                return (this.bitField0_ & 2) == 2;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return SecureTransportProtos.internal_static_protobuf_AesGCMSecureMessage_fieldAccessorTable.ensureFieldAccessorsInitialized(AesGCMSecureMessage.class, Builder.class);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return hasInitializationVector() && hasPayload() && hasMac() && hasAad();
            }

            public Builder setAad(ByteString byteString) {
                if (byteString != null) {
                    this.bitField0_ |= 8;
                    this.aad_ = byteString;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder setInitializationVector(ByteString byteString) {
                if (byteString != null) {
                    this.bitField0_ |= 1;
                    this.initializationVector_ = byteString;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder setMac(ByteString byteString) {
                if (byteString != null) {
                    this.bitField0_ |= 4;
                    this.mac_ = byteString;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder setPayload(ByteString byteString) {
                if (byteString != null) {
                    this.bitField0_ |= 2;
                    this.payload_ = byteString;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            private Builder() {
                ByteString byteString = ByteString.EMPTY;
                this.initializationVector_ = byteString;
                this.payload_ = byteString;
                this.mac_ = byteString;
                this.aad_ = byteString;
                maybeForceBuilderInitialization();
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            /* renamed from: addRepeatedField */
            public Builder mo10083addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.mo10083addRepeatedField(fieldDescriptor, obj);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: build */
            public AesGCMSecureMessage mo10084build() {
                AesGCMSecureMessage mo10085buildPartial = mo10085buildPartial();
                if (mo10085buildPartial.isInitialized()) {
                    return mo10085buildPartial;
                }
                throw AbstractMessage.Builder.newUninitializedMessageException((Message) mo10085buildPartial);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: buildPartial */
            public AesGCMSecureMessage mo10085buildPartial() {
                AesGCMSecureMessage aesGCMSecureMessage = new AesGCMSecureMessage(this);
                int i = this.bitField0_;
                int i2 = 1;
                if ((i & 1) != 1) {
                    i2 = 0;
                }
                aesGCMSecureMessage.initializationVector_ = this.initializationVector_;
                if ((i & 2) == 2) {
                    i2 |= 2;
                }
                aesGCMSecureMessage.payload_ = this.payload_;
                if ((i & 4) == 4) {
                    i2 |= 4;
                }
                aesGCMSecureMessage.mac_ = this.mac_;
                if ((i & 8) == 8) {
                    i2 |= 8;
                }
                aesGCMSecureMessage.aad_ = this.aad_;
                aesGCMSecureMessage.bitField0_ = i2;
                onBuilt();
                return aesGCMSecureMessage;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            /* renamed from: clearField */
            public Builder mo10088clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (Builder) super.mo10088clearField(fieldDescriptor);
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            /* renamed from: getDefaultInstanceForType */
            public AesGCMSecureMessage mo10094getDefaultInstanceForType() {
                return AesGCMSecureMessage.getDefaultInstance();
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            /* renamed from: setField */
            public Builder mo10100setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.mo10100setField(fieldDescriptor, obj);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            /* renamed from: setRepeatedField */
            public Builder mo10101setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                return (Builder) super.mo10101setRepeatedField(fieldDescriptor, i, obj);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            /* renamed from: setUnknownFields */
            public final Builder mo10102setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.mo10102setUnknownFields(unknownFieldSet);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            /* renamed from: clearOneof */
            public Builder mo10090clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (Builder) super.mo10090clearOneof(oneofDescriptor);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            /* renamed from: mergeUnknownFields */
            public final Builder mo10099mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.mo10099mergeUnknownFields(unknownFieldSet);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: clear */
            public Builder mo10087clear() {
                super.mo10087clear();
                ByteString byteString = ByteString.EMPTY;
                this.initializationVector_ = byteString;
                this.bitField0_ &= -2;
                this.payload_ = byteString;
                this.bitField0_ &= -3;
                this.mac_ = byteString;
                this.bitField0_ &= -5;
                this.aad_ = byteString;
                this.bitField0_ &= -9;
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: clone */
            public Builder mo10093clone() {
                return (Builder) super.mo10093clone();
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            /* renamed from: mergeFrom */
            public Builder mo10096mergeFrom(Message message) {
                if (message instanceof AesGCMSecureMessage) {
                    return mergeFrom((AesGCMSecureMessage) message);
                }
                super.mo10096mergeFrom(message);
                return this;
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                ByteString byteString = ByteString.EMPTY;
                this.initializationVector_ = byteString;
                this.payload_ = byteString;
                this.mac_ = byteString;
                this.aad_ = byteString;
                maybeForceBuilderInitialization();
            }

            public Builder mergeFrom(AesGCMSecureMessage aesGCMSecureMessage) {
                if (aesGCMSecureMessage == AesGCMSecureMessage.getDefaultInstance()) {
                    return this;
                }
                if (aesGCMSecureMessage.hasInitializationVector()) {
                    setInitializationVector(aesGCMSecureMessage.getInitializationVector());
                }
                if (aesGCMSecureMessage.hasPayload()) {
                    setPayload(aesGCMSecureMessage.getPayload());
                }
                if (aesGCMSecureMessage.hasMac()) {
                    setMac(aesGCMSecureMessage.getMac());
                }
                if (aesGCMSecureMessage.hasAad()) {
                    setAad(aesGCMSecureMessage.getAad());
                }
                mo10099mergeUnknownFields(((GeneratedMessageV3) aesGCMSecureMessage).unknownFields);
                onChanged();
                return this;
            }

            /* JADX WARN: Removed duplicated region for block: B:16:0x0021  */
            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: mergeFrom */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public com.amazon.whispercloak.protobuf.SecureTransportProtos.AesGCMSecureMessage.Builder mo10097mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
                /*
                    r2 = this;
                    r0 = 0
                    com.google.protobuf.Parser<com.amazon.whispercloak.protobuf.SecureTransportProtos$AesGCMSecureMessage> r1 = com.amazon.whispercloak.protobuf.SecureTransportProtos.AesGCMSecureMessage.PARSER     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                    java.lang.Object r3 = r1.mo10082parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                    com.amazon.whispercloak.protobuf.SecureTransportProtos$AesGCMSecureMessage r3 = (com.amazon.whispercloak.protobuf.SecureTransportProtos.AesGCMSecureMessage) r3     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                    if (r3 == 0) goto Le
                    r2.mergeFrom(r3)
                Le:
                    return r2
                Lf:
                    r3 = move-exception
                    goto L1f
                L11:
                    r3 = move-exception
                    com.google.protobuf.MessageLite r4 = r3.getUnfinishedMessage()     // Catch: java.lang.Throwable -> Lf
                    com.amazon.whispercloak.protobuf.SecureTransportProtos$AesGCMSecureMessage r4 = (com.amazon.whispercloak.protobuf.SecureTransportProtos.AesGCMSecureMessage) r4     // Catch: java.lang.Throwable -> Lf
                    java.io.IOException r3 = r3.unwrapIOException()     // Catch: java.lang.Throwable -> L1d
                    throw r3     // Catch: java.lang.Throwable -> L1d
                L1d:
                    r3 = move-exception
                    r0 = r4
                L1f:
                    if (r0 == 0) goto L24
                    r2.mergeFrom(r0)
                L24:
                    throw r3
                */
                throw new UnsupportedOperationException("Method not decompiled: com.amazon.whispercloak.protobuf.SecureTransportProtos.AesGCMSecureMessage.Builder.mo10097mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.amazon.whispercloak.protobuf.SecureTransportProtos$AesGCMSecureMessage$Builder");
            }
        }

        public static Builder newBuilder(AesGCMSecureMessage aesGCMSecureMessage) {
            return DEFAULT_INSTANCE.mo10081toBuilder().mergeFrom(aesGCMSecureMessage);
        }

        public static AesGCMSecureMessage parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (AesGCMSecureMessage) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static AesGCMSecureMessage parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.mo8403parseFrom(byteBuffer, extensionRegistryLite);
        }

        private AesGCMSecureMessage(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        public static AesGCMSecureMessage parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.mo8396parseFrom(byteString);
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        /* renamed from: getDefaultInstanceForType */
        public AesGCMSecureMessage mo10094getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        /* renamed from: toBuilder */
        public Builder mo10081toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        public static AesGCMSecureMessage parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.mo8397parseFrom(byteString, extensionRegistryLite);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        /* renamed from: newBuilderForType */
        public Builder mo10079newBuilderForType() {
            return newBuilder();
        }

        private AesGCMSecureMessage() {
            this.memoizedIsInitialized = (byte) -1;
            ByteString byteString = ByteString.EMPTY;
            this.initializationVector_ = byteString;
            this.payload_ = byteString;
            this.mac_ = byteString;
            this.aad_ = byteString;
        }

        public static AesGCMSecureMessage parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.mo8404parseFrom(bArr);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageV3
        /* renamed from: newBuilderForType */
        public Builder mo10080newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public static AesGCMSecureMessage parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.mo8407parseFrom(bArr, extensionRegistryLite);
        }

        public static AesGCMSecureMessage parseFrom(InputStream inputStream) throws IOException {
            return (AesGCMSecureMessage) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static AesGCMSecureMessage parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (AesGCMSecureMessage) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static AesGCMSecureMessage parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (AesGCMSecureMessage) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static AesGCMSecureMessage parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (AesGCMSecureMessage) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        private AesGCMSecureMessage(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            this();
            if (extensionRegistryLite != null) {
                UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
                boolean z = false;
                while (!z) {
                    try {
                        try {
                            int readTag = codedInputStream.readTag();
                            if (readTag != 0) {
                                if (readTag == 10) {
                                    this.bitField0_ |= 1;
                                    this.initializationVector_ = codedInputStream.readBytes();
                                } else if (readTag == 18) {
                                    this.bitField0_ |= 2;
                                    this.payload_ = codedInputStream.readBytes();
                                } else if (readTag == 26) {
                                    this.bitField0_ |= 4;
                                    this.mac_ = codedInputStream.readBytes();
                                } else if (readTag != 34) {
                                    if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                    }
                                } else {
                                    this.bitField0_ |= 8;
                                    this.aad_ = codedInputStream.readBytes();
                                }
                            }
                            z = true;
                        } catch (InvalidProtocolBufferException e) {
                            throw e.setUnfinishedMessage(this);
                        } catch (IOException e2) {
                            throw new InvalidProtocolBufferException(e2).setUnfinishedMessage(this);
                        }
                    } finally {
                        this.unknownFields = newBuilder.mo10084build();
                        makeExtensionsImmutable();
                    }
                }
                return;
            }
            throw new NullPointerException();
        }
    }

    /* loaded from: classes13.dex */
    public interface AesGCMSecureMessageOrBuilder extends MessageOrBuilder {
        ByteString getAad();

        ByteString getInitializationVector();

        ByteString getMac();

        ByteString getPayload();

        boolean hasAad();

        boolean hasInitializationVector();

        boolean hasMac();

        boolean hasPayload();
    }

    /* loaded from: classes13.dex */
    public static final class AuthenticatedECDHEKeyExchangeRequest extends GeneratedMessageV3 implements AuthenticatedECDHEKeyExchangeRequestOrBuilder {
        public static final int DERECDHEPUBLICKEY_FIELD_NUMBER = 1;
        public static final int SIGNATURE_FIELD_NUMBER = 2;
        private static final long serialVersionUID = 0;
        private int bitField0_;
        private ByteString derECDHEPublicKey_;
        private byte memoizedIsInitialized;
        private ByteString signature_;
        private static final AuthenticatedECDHEKeyExchangeRequest DEFAULT_INSTANCE = new AuthenticatedECDHEKeyExchangeRequest();
        @Deprecated
        public static final Parser<AuthenticatedECDHEKeyExchangeRequest> PARSER = new AbstractParser<AuthenticatedECDHEKeyExchangeRequest>() { // from class: com.amazon.whispercloak.protobuf.SecureTransportProtos.AuthenticatedECDHEKeyExchangeRequest.1
            @Override // com.google.protobuf.Parser
            /* renamed from: parsePartialFrom */
            public AuthenticatedECDHEKeyExchangeRequest mo10082parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new AuthenticatedECDHEKeyExchangeRequest(codedInputStream, extensionRegistryLite);
            }
        };

        public static AuthenticatedECDHEKeyExchangeRequest getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return SecureTransportProtos.internal_static_protobuf_AuthenticatedECDHEKeyExchangeRequest_descriptor;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.mo10081toBuilder();
        }

        public static AuthenticatedECDHEKeyExchangeRequest parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (AuthenticatedECDHEKeyExchangeRequest) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static AuthenticatedECDHEKeyExchangeRequest parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.mo8402parseFrom(byteBuffer);
        }

        public static Parser<AuthenticatedECDHEKeyExchangeRequest> parser() {
            return PARSER;
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof AuthenticatedECDHEKeyExchangeRequest)) {
                return super.equals(obj);
            }
            AuthenticatedECDHEKeyExchangeRequest authenticatedECDHEKeyExchangeRequest = (AuthenticatedECDHEKeyExchangeRequest) obj;
            boolean z = hasDerECDHEPublicKey() == authenticatedECDHEKeyExchangeRequest.hasDerECDHEPublicKey();
            if (hasDerECDHEPublicKey()) {
                z = z && getDerECDHEPublicKey().equals(authenticatedECDHEKeyExchangeRequest.getDerECDHEPublicKey());
            }
            boolean z2 = z && hasSignature() == authenticatedECDHEKeyExchangeRequest.hasSignature();
            if (hasSignature()) {
                z2 = z2 && getSignature().equals(authenticatedECDHEKeyExchangeRequest.getSignature());
            }
            return z2 && this.unknownFields.equals(authenticatedECDHEKeyExchangeRequest.unknownFields);
        }

        @Override // com.amazon.whispercloak.protobuf.SecureTransportProtos.AuthenticatedECDHEKeyExchangeRequestOrBuilder
        public ByteString getDerECDHEPublicKey() {
            return this.derECDHEPublicKey_;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
        /* renamed from: getParserForType */
        public Parser<AuthenticatedECDHEKeyExchangeRequest> mo9954getParserForType() {
            return PARSER;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            if ((this.bitField0_ & 1) == 1) {
                i2 = 0 + CodedOutputStream.computeBytesSize(1, this.derECDHEPublicKey_);
            }
            if ((this.bitField0_ & 2) == 2) {
                i2 += CodedOutputStream.computeBytesSize(2, this.signature_);
            }
            int serializedSize = this.unknownFields.getSerializedSize() + i2;
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.amazon.whispercloak.protobuf.SecureTransportProtos.AuthenticatedECDHEKeyExchangeRequestOrBuilder
        public ByteString getSignature() {
            return this.signature_;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        @Override // com.amazon.whispercloak.protobuf.SecureTransportProtos.AuthenticatedECDHEKeyExchangeRequestOrBuilder
        public boolean hasDerECDHEPublicKey() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // com.amazon.whispercloak.protobuf.SecureTransportProtos.AuthenticatedECDHEKeyExchangeRequestOrBuilder
        public boolean hasSignature() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public int hashCode() {
            int i = this.memoizedHashCode;
            if (i != 0) {
                return i;
            }
            int hashCode = getDescriptor().hashCode() + 779;
            if (hasDerECDHEPublicKey()) {
                hashCode = GeneratedOutlineSupport1.outline4(hashCode, 37, 1, 53) + getDerECDHEPublicKey().hashCode();
            }
            if (hasSignature()) {
                hashCode = GeneratedOutlineSupport1.outline4(hashCode, 37, 2, 53) + getSignature().hashCode();
            }
            int hashCode2 = this.unknownFields.hashCode() + (hashCode * 29);
            this.memoizedHashCode = hashCode2;
            return hashCode2;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return SecureTransportProtos.internal_static_protobuf_AuthenticatedECDHEKeyExchangeRequest_fieldAccessorTable.ensureFieldAccessorsInitialized(AuthenticatedECDHEKeyExchangeRequest.class, Builder.class);
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            byte b = this.memoizedIsInitialized;
            if (b == 1) {
                return true;
            }
            if (b == 0) {
                return false;
            }
            if (!hasDerECDHEPublicKey()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            } else if (!hasSignature()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            } else {
                this.memoizedIsInitialized = (byte) 1;
                return true;
            }
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                codedOutputStream.writeBytes(1, this.derECDHEPublicKey_);
            }
            if ((this.bitField0_ & 2) == 2) {
                codedOutputStream.writeBytes(2, this.signature_);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        /* loaded from: classes13.dex */
        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements AuthenticatedECDHEKeyExchangeRequestOrBuilder {
            private int bitField0_;
            private ByteString derECDHEPublicKey_;
            private ByteString signature_;

            public static final Descriptors.Descriptor getDescriptor() {
                return SecureTransportProtos.internal_static_protobuf_AuthenticatedECDHEKeyExchangeRequest_descriptor;
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = GeneratedMessageV3.alwaysUseFieldBuilders;
            }

            public Builder clearDerECDHEPublicKey() {
                this.bitField0_ &= -2;
                this.derECDHEPublicKey_ = AuthenticatedECDHEKeyExchangeRequest.getDefaultInstance().getDerECDHEPublicKey();
                onChanged();
                return this;
            }

            public Builder clearSignature() {
                this.bitField0_ &= -3;
                this.signature_ = AuthenticatedECDHEKeyExchangeRequest.getDefaultInstance().getSignature();
                onChanged();
                return this;
            }

            @Override // com.amazon.whispercloak.protobuf.SecureTransportProtos.AuthenticatedECDHEKeyExchangeRequestOrBuilder
            public ByteString getDerECDHEPublicKey() {
                return this.derECDHEPublicKey_;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return SecureTransportProtos.internal_static_protobuf_AuthenticatedECDHEKeyExchangeRequest_descriptor;
            }

            @Override // com.amazon.whispercloak.protobuf.SecureTransportProtos.AuthenticatedECDHEKeyExchangeRequestOrBuilder
            public ByteString getSignature() {
                return this.signature_;
            }

            @Override // com.amazon.whispercloak.protobuf.SecureTransportProtos.AuthenticatedECDHEKeyExchangeRequestOrBuilder
            public boolean hasDerECDHEPublicKey() {
                return (this.bitField0_ & 1) == 1;
            }

            @Override // com.amazon.whispercloak.protobuf.SecureTransportProtos.AuthenticatedECDHEKeyExchangeRequestOrBuilder
            public boolean hasSignature() {
                return (this.bitField0_ & 2) == 2;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return SecureTransportProtos.internal_static_protobuf_AuthenticatedECDHEKeyExchangeRequest_fieldAccessorTable.ensureFieldAccessorsInitialized(AuthenticatedECDHEKeyExchangeRequest.class, Builder.class);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return hasDerECDHEPublicKey() && hasSignature();
            }

            public Builder setDerECDHEPublicKey(ByteString byteString) {
                if (byteString != null) {
                    this.bitField0_ |= 1;
                    this.derECDHEPublicKey_ = byteString;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder setSignature(ByteString byteString) {
                if (byteString != null) {
                    this.bitField0_ |= 2;
                    this.signature_ = byteString;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            private Builder() {
                ByteString byteString = ByteString.EMPTY;
                this.derECDHEPublicKey_ = byteString;
                this.signature_ = byteString;
                maybeForceBuilderInitialization();
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            /* renamed from: addRepeatedField */
            public Builder mo10083addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.mo10083addRepeatedField(fieldDescriptor, obj);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: build */
            public AuthenticatedECDHEKeyExchangeRequest mo10084build() {
                AuthenticatedECDHEKeyExchangeRequest mo10085buildPartial = mo10085buildPartial();
                if (mo10085buildPartial.isInitialized()) {
                    return mo10085buildPartial;
                }
                throw AbstractMessage.Builder.newUninitializedMessageException((Message) mo10085buildPartial);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: buildPartial */
            public AuthenticatedECDHEKeyExchangeRequest mo10085buildPartial() {
                AuthenticatedECDHEKeyExchangeRequest authenticatedECDHEKeyExchangeRequest = new AuthenticatedECDHEKeyExchangeRequest(this);
                int i = this.bitField0_;
                int i2 = 1;
                if ((i & 1) != 1) {
                    i2 = 0;
                }
                authenticatedECDHEKeyExchangeRequest.derECDHEPublicKey_ = this.derECDHEPublicKey_;
                if ((i & 2) == 2) {
                    i2 |= 2;
                }
                authenticatedECDHEKeyExchangeRequest.signature_ = this.signature_;
                authenticatedECDHEKeyExchangeRequest.bitField0_ = i2;
                onBuilt();
                return authenticatedECDHEKeyExchangeRequest;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            /* renamed from: clearField */
            public Builder mo10088clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (Builder) super.mo10088clearField(fieldDescriptor);
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            /* renamed from: getDefaultInstanceForType */
            public AuthenticatedECDHEKeyExchangeRequest mo10094getDefaultInstanceForType() {
                return AuthenticatedECDHEKeyExchangeRequest.getDefaultInstance();
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            /* renamed from: setField */
            public Builder mo10100setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.mo10100setField(fieldDescriptor, obj);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            /* renamed from: setRepeatedField */
            public Builder mo10101setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                return (Builder) super.mo10101setRepeatedField(fieldDescriptor, i, obj);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            /* renamed from: setUnknownFields */
            public final Builder mo10102setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.mo10102setUnknownFields(unknownFieldSet);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            /* renamed from: clearOneof */
            public Builder mo10090clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (Builder) super.mo10090clearOneof(oneofDescriptor);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            /* renamed from: mergeUnknownFields */
            public final Builder mo10099mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.mo10099mergeUnknownFields(unknownFieldSet);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: clear */
            public Builder mo10087clear() {
                super.mo10087clear();
                ByteString byteString = ByteString.EMPTY;
                this.derECDHEPublicKey_ = byteString;
                this.bitField0_ &= -2;
                this.signature_ = byteString;
                this.bitField0_ &= -3;
                return this;
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                ByteString byteString = ByteString.EMPTY;
                this.derECDHEPublicKey_ = byteString;
                this.signature_ = byteString;
                maybeForceBuilderInitialization();
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: clone */
            public Builder mo10093clone() {
                return (Builder) super.mo10093clone();
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            /* renamed from: mergeFrom */
            public Builder mo10096mergeFrom(Message message) {
                if (message instanceof AuthenticatedECDHEKeyExchangeRequest) {
                    return mergeFrom((AuthenticatedECDHEKeyExchangeRequest) message);
                }
                super.mo10096mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(AuthenticatedECDHEKeyExchangeRequest authenticatedECDHEKeyExchangeRequest) {
                if (authenticatedECDHEKeyExchangeRequest == AuthenticatedECDHEKeyExchangeRequest.getDefaultInstance()) {
                    return this;
                }
                if (authenticatedECDHEKeyExchangeRequest.hasDerECDHEPublicKey()) {
                    setDerECDHEPublicKey(authenticatedECDHEKeyExchangeRequest.getDerECDHEPublicKey());
                }
                if (authenticatedECDHEKeyExchangeRequest.hasSignature()) {
                    setSignature(authenticatedECDHEKeyExchangeRequest.getSignature());
                }
                mo10099mergeUnknownFields(((GeneratedMessageV3) authenticatedECDHEKeyExchangeRequest).unknownFields);
                onChanged();
                return this;
            }

            /* JADX WARN: Removed duplicated region for block: B:16:0x0021  */
            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: mergeFrom */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public com.amazon.whispercloak.protobuf.SecureTransportProtos.AuthenticatedECDHEKeyExchangeRequest.Builder mo10097mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
                /*
                    r2 = this;
                    r0 = 0
                    com.google.protobuf.Parser<com.amazon.whispercloak.protobuf.SecureTransportProtos$AuthenticatedECDHEKeyExchangeRequest> r1 = com.amazon.whispercloak.protobuf.SecureTransportProtos.AuthenticatedECDHEKeyExchangeRequest.PARSER     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                    java.lang.Object r3 = r1.mo10082parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                    com.amazon.whispercloak.protobuf.SecureTransportProtos$AuthenticatedECDHEKeyExchangeRequest r3 = (com.amazon.whispercloak.protobuf.SecureTransportProtos.AuthenticatedECDHEKeyExchangeRequest) r3     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                    if (r3 == 0) goto Le
                    r2.mergeFrom(r3)
                Le:
                    return r2
                Lf:
                    r3 = move-exception
                    goto L1f
                L11:
                    r3 = move-exception
                    com.google.protobuf.MessageLite r4 = r3.getUnfinishedMessage()     // Catch: java.lang.Throwable -> Lf
                    com.amazon.whispercloak.protobuf.SecureTransportProtos$AuthenticatedECDHEKeyExchangeRequest r4 = (com.amazon.whispercloak.protobuf.SecureTransportProtos.AuthenticatedECDHEKeyExchangeRequest) r4     // Catch: java.lang.Throwable -> Lf
                    java.io.IOException r3 = r3.unwrapIOException()     // Catch: java.lang.Throwable -> L1d
                    throw r3     // Catch: java.lang.Throwable -> L1d
                L1d:
                    r3 = move-exception
                    r0 = r4
                L1f:
                    if (r0 == 0) goto L24
                    r2.mergeFrom(r0)
                L24:
                    throw r3
                */
                throw new UnsupportedOperationException("Method not decompiled: com.amazon.whispercloak.protobuf.SecureTransportProtos.AuthenticatedECDHEKeyExchangeRequest.Builder.mo10097mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.amazon.whispercloak.protobuf.SecureTransportProtos$AuthenticatedECDHEKeyExchangeRequest$Builder");
            }
        }

        public static Builder newBuilder(AuthenticatedECDHEKeyExchangeRequest authenticatedECDHEKeyExchangeRequest) {
            return DEFAULT_INSTANCE.mo10081toBuilder().mergeFrom(authenticatedECDHEKeyExchangeRequest);
        }

        public static AuthenticatedECDHEKeyExchangeRequest parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (AuthenticatedECDHEKeyExchangeRequest) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static AuthenticatedECDHEKeyExchangeRequest parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.mo8403parseFrom(byteBuffer, extensionRegistryLite);
        }

        private AuthenticatedECDHEKeyExchangeRequest(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        public static AuthenticatedECDHEKeyExchangeRequest parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.mo8396parseFrom(byteString);
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        /* renamed from: getDefaultInstanceForType */
        public AuthenticatedECDHEKeyExchangeRequest mo10094getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        /* renamed from: toBuilder */
        public Builder mo10081toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        public static AuthenticatedECDHEKeyExchangeRequest parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.mo8397parseFrom(byteString, extensionRegistryLite);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        /* renamed from: newBuilderForType */
        public Builder mo10079newBuilderForType() {
            return newBuilder();
        }

        private AuthenticatedECDHEKeyExchangeRequest() {
            this.memoizedIsInitialized = (byte) -1;
            ByteString byteString = ByteString.EMPTY;
            this.derECDHEPublicKey_ = byteString;
            this.signature_ = byteString;
        }

        public static AuthenticatedECDHEKeyExchangeRequest parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.mo8404parseFrom(bArr);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageV3
        /* renamed from: newBuilderForType */
        public Builder mo10080newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public static AuthenticatedECDHEKeyExchangeRequest parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.mo8407parseFrom(bArr, extensionRegistryLite);
        }

        public static AuthenticatedECDHEKeyExchangeRequest parseFrom(InputStream inputStream) throws IOException {
            return (AuthenticatedECDHEKeyExchangeRequest) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static AuthenticatedECDHEKeyExchangeRequest parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (AuthenticatedECDHEKeyExchangeRequest) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        private AuthenticatedECDHEKeyExchangeRequest(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            this();
            if (extensionRegistryLite != null) {
                UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
                boolean z = false;
                while (!z) {
                    try {
                        try {
                            int readTag = codedInputStream.readTag();
                            if (readTag != 0) {
                                if (readTag == 10) {
                                    this.bitField0_ |= 1;
                                    this.derECDHEPublicKey_ = codedInputStream.readBytes();
                                } else if (readTag != 18) {
                                    if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                    }
                                } else {
                                    this.bitField0_ |= 2;
                                    this.signature_ = codedInputStream.readBytes();
                                }
                            }
                            z = true;
                        } catch (InvalidProtocolBufferException e) {
                            throw e.setUnfinishedMessage(this);
                        } catch (IOException e2) {
                            throw new InvalidProtocolBufferException(e2).setUnfinishedMessage(this);
                        }
                    } finally {
                        this.unknownFields = newBuilder.mo10084build();
                        makeExtensionsImmutable();
                    }
                }
                return;
            }
            throw new NullPointerException();
        }

        public static AuthenticatedECDHEKeyExchangeRequest parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (AuthenticatedECDHEKeyExchangeRequest) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static AuthenticatedECDHEKeyExchangeRequest parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (AuthenticatedECDHEKeyExchangeRequest) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes13.dex */
    public interface AuthenticatedECDHEKeyExchangeRequestOrBuilder extends MessageOrBuilder {
        ByteString getDerECDHEPublicKey();

        ByteString getSignature();

        boolean hasDerECDHEPublicKey();

        boolean hasSignature();
    }

    /* loaded from: classes13.dex */
    public static final class AuthenticatedECDHEKeyExchangeResponse extends GeneratedMessageV3 implements AuthenticatedECDHEKeyExchangeResponseOrBuilder {
        public static final int AUTHENTICATIONDATASECUREMESSAGE_FIELD_NUMBER = 2;
        public static final int DERECDHEPUBLICKEY_FIELD_NUMBER = 1;
        private static final long serialVersionUID = 0;
        private AesGCMSecureMessage authenticationDataSecureMessage_;
        private int bitField0_;
        private ByteString derECDHEPublicKey_;
        private byte memoizedIsInitialized;
        private static final AuthenticatedECDHEKeyExchangeResponse DEFAULT_INSTANCE = new AuthenticatedECDHEKeyExchangeResponse();
        @Deprecated
        public static final Parser<AuthenticatedECDHEKeyExchangeResponse> PARSER = new AbstractParser<AuthenticatedECDHEKeyExchangeResponse>() { // from class: com.amazon.whispercloak.protobuf.SecureTransportProtos.AuthenticatedECDHEKeyExchangeResponse.1
            @Override // com.google.protobuf.Parser
            /* renamed from: parsePartialFrom */
            public AuthenticatedECDHEKeyExchangeResponse mo10082parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new AuthenticatedECDHEKeyExchangeResponse(codedInputStream, extensionRegistryLite);
            }
        };

        public static AuthenticatedECDHEKeyExchangeResponse getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return SecureTransportProtos.internal_static_protobuf_AuthenticatedECDHEKeyExchangeResponse_descriptor;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.mo10081toBuilder();
        }

        public static AuthenticatedECDHEKeyExchangeResponse parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (AuthenticatedECDHEKeyExchangeResponse) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static AuthenticatedECDHEKeyExchangeResponse parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.mo8402parseFrom(byteBuffer);
        }

        public static Parser<AuthenticatedECDHEKeyExchangeResponse> parser() {
            return PARSER;
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof AuthenticatedECDHEKeyExchangeResponse)) {
                return super.equals(obj);
            }
            AuthenticatedECDHEKeyExchangeResponse authenticatedECDHEKeyExchangeResponse = (AuthenticatedECDHEKeyExchangeResponse) obj;
            boolean z = hasDerECDHEPublicKey() == authenticatedECDHEKeyExchangeResponse.hasDerECDHEPublicKey();
            if (hasDerECDHEPublicKey()) {
                z = z && getDerECDHEPublicKey().equals(authenticatedECDHEKeyExchangeResponse.getDerECDHEPublicKey());
            }
            boolean z2 = z && hasAuthenticationDataSecureMessage() == authenticatedECDHEKeyExchangeResponse.hasAuthenticationDataSecureMessage();
            if (hasAuthenticationDataSecureMessage()) {
                z2 = z2 && getAuthenticationDataSecureMessage().equals(authenticatedECDHEKeyExchangeResponse.getAuthenticationDataSecureMessage());
            }
            return z2 && this.unknownFields.equals(authenticatedECDHEKeyExchangeResponse.unknownFields);
        }

        @Override // com.amazon.whispercloak.protobuf.SecureTransportProtos.AuthenticatedECDHEKeyExchangeResponseOrBuilder
        public AesGCMSecureMessage getAuthenticationDataSecureMessage() {
            AesGCMSecureMessage aesGCMSecureMessage = this.authenticationDataSecureMessage_;
            return aesGCMSecureMessage == null ? AesGCMSecureMessage.getDefaultInstance() : aesGCMSecureMessage;
        }

        @Override // com.amazon.whispercloak.protobuf.SecureTransportProtos.AuthenticatedECDHEKeyExchangeResponseOrBuilder
        public AesGCMSecureMessageOrBuilder getAuthenticationDataSecureMessageOrBuilder() {
            AesGCMSecureMessage aesGCMSecureMessage = this.authenticationDataSecureMessage_;
            return aesGCMSecureMessage == null ? AesGCMSecureMessage.getDefaultInstance() : aesGCMSecureMessage;
        }

        @Override // com.amazon.whispercloak.protobuf.SecureTransportProtos.AuthenticatedECDHEKeyExchangeResponseOrBuilder
        public ByteString getDerECDHEPublicKey() {
            return this.derECDHEPublicKey_;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
        /* renamed from: getParserForType */
        public Parser<AuthenticatedECDHEKeyExchangeResponse> mo9954getParserForType() {
            return PARSER;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            if ((this.bitField0_ & 1) == 1) {
                i2 = 0 + CodedOutputStream.computeBytesSize(1, this.derECDHEPublicKey_);
            }
            if ((this.bitField0_ & 2) == 2) {
                i2 += CodedOutputStream.computeMessageSize(2, getAuthenticationDataSecureMessage());
            }
            int serializedSize = this.unknownFields.getSerializedSize() + i2;
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        @Override // com.amazon.whispercloak.protobuf.SecureTransportProtos.AuthenticatedECDHEKeyExchangeResponseOrBuilder
        public boolean hasAuthenticationDataSecureMessage() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // com.amazon.whispercloak.protobuf.SecureTransportProtos.AuthenticatedECDHEKeyExchangeResponseOrBuilder
        public boolean hasDerECDHEPublicKey() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public int hashCode() {
            int i = this.memoizedHashCode;
            if (i != 0) {
                return i;
            }
            int hashCode = getDescriptor().hashCode() + 779;
            if (hasDerECDHEPublicKey()) {
                hashCode = GeneratedOutlineSupport1.outline4(hashCode, 37, 1, 53) + getDerECDHEPublicKey().hashCode();
            }
            if (hasAuthenticationDataSecureMessage()) {
                hashCode = GeneratedOutlineSupport1.outline4(hashCode, 37, 2, 53) + getAuthenticationDataSecureMessage().hashCode();
            }
            int hashCode2 = this.unknownFields.hashCode() + (hashCode * 29);
            this.memoizedHashCode = hashCode2;
            return hashCode2;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return SecureTransportProtos.internal_static_protobuf_AuthenticatedECDHEKeyExchangeResponse_fieldAccessorTable.ensureFieldAccessorsInitialized(AuthenticatedECDHEKeyExchangeResponse.class, Builder.class);
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            byte b = this.memoizedIsInitialized;
            if (b == 1) {
                return true;
            }
            if (b == 0) {
                return false;
            }
            if (!hasDerECDHEPublicKey()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            } else if (!hasAuthenticationDataSecureMessage()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            } else if (!getAuthenticationDataSecureMessage().isInitialized()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            } else {
                this.memoizedIsInitialized = (byte) 1;
                return true;
            }
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                codedOutputStream.writeBytes(1, this.derECDHEPublicKey_);
            }
            if ((this.bitField0_ & 2) == 2) {
                codedOutputStream.writeMessage(2, getAuthenticationDataSecureMessage());
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        /* loaded from: classes13.dex */
        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements AuthenticatedECDHEKeyExchangeResponseOrBuilder {
            private SingleFieldBuilderV3<AesGCMSecureMessage, AesGCMSecureMessage.Builder, AesGCMSecureMessageOrBuilder> authenticationDataSecureMessageBuilder_;
            private AesGCMSecureMessage authenticationDataSecureMessage_;
            private int bitField0_;
            private ByteString derECDHEPublicKey_;

            private SingleFieldBuilderV3<AesGCMSecureMessage, AesGCMSecureMessage.Builder, AesGCMSecureMessageOrBuilder> getAuthenticationDataSecureMessageFieldBuilder() {
                if (this.authenticationDataSecureMessageBuilder_ == null) {
                    this.authenticationDataSecureMessageBuilder_ = new SingleFieldBuilderV3<>(getAuthenticationDataSecureMessage(), getParentForChildren(), isClean());
                    this.authenticationDataSecureMessage_ = null;
                }
                return this.authenticationDataSecureMessageBuilder_;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return SecureTransportProtos.internal_static_protobuf_AuthenticatedECDHEKeyExchangeResponse_descriptor;
            }

            private void maybeForceBuilderInitialization() {
                if (GeneratedMessageV3.alwaysUseFieldBuilders) {
                    getAuthenticationDataSecureMessageFieldBuilder();
                }
            }

            public Builder clearAuthenticationDataSecureMessage() {
                SingleFieldBuilderV3<AesGCMSecureMessage, AesGCMSecureMessage.Builder, AesGCMSecureMessageOrBuilder> singleFieldBuilderV3 = this.authenticationDataSecureMessageBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.authenticationDataSecureMessage_ = null;
                    onChanged();
                } else {
                    singleFieldBuilderV3.clear();
                }
                this.bitField0_ &= -3;
                return this;
            }

            public Builder clearDerECDHEPublicKey() {
                this.bitField0_ &= -2;
                this.derECDHEPublicKey_ = AuthenticatedECDHEKeyExchangeResponse.getDefaultInstance().getDerECDHEPublicKey();
                onChanged();
                return this;
            }

            @Override // com.amazon.whispercloak.protobuf.SecureTransportProtos.AuthenticatedECDHEKeyExchangeResponseOrBuilder
            public AesGCMSecureMessage getAuthenticationDataSecureMessage() {
                SingleFieldBuilderV3<AesGCMSecureMessage, AesGCMSecureMessage.Builder, AesGCMSecureMessageOrBuilder> singleFieldBuilderV3 = this.authenticationDataSecureMessageBuilder_;
                if (singleFieldBuilderV3 == null) {
                    AesGCMSecureMessage aesGCMSecureMessage = this.authenticationDataSecureMessage_;
                    return aesGCMSecureMessage == null ? AesGCMSecureMessage.getDefaultInstance() : aesGCMSecureMessage;
                }
                return singleFieldBuilderV3.getMessage();
            }

            public AesGCMSecureMessage.Builder getAuthenticationDataSecureMessageBuilder() {
                this.bitField0_ |= 2;
                onChanged();
                return getAuthenticationDataSecureMessageFieldBuilder().getBuilder();
            }

            @Override // com.amazon.whispercloak.protobuf.SecureTransportProtos.AuthenticatedECDHEKeyExchangeResponseOrBuilder
            public AesGCMSecureMessageOrBuilder getAuthenticationDataSecureMessageOrBuilder() {
                SingleFieldBuilderV3<AesGCMSecureMessage, AesGCMSecureMessage.Builder, AesGCMSecureMessageOrBuilder> singleFieldBuilderV3 = this.authenticationDataSecureMessageBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessageOrBuilder();
                }
                AesGCMSecureMessage aesGCMSecureMessage = this.authenticationDataSecureMessage_;
                return aesGCMSecureMessage == null ? AesGCMSecureMessage.getDefaultInstance() : aesGCMSecureMessage;
            }

            @Override // com.amazon.whispercloak.protobuf.SecureTransportProtos.AuthenticatedECDHEKeyExchangeResponseOrBuilder
            public ByteString getDerECDHEPublicKey() {
                return this.derECDHEPublicKey_;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return SecureTransportProtos.internal_static_protobuf_AuthenticatedECDHEKeyExchangeResponse_descriptor;
            }

            @Override // com.amazon.whispercloak.protobuf.SecureTransportProtos.AuthenticatedECDHEKeyExchangeResponseOrBuilder
            public boolean hasAuthenticationDataSecureMessage() {
                return (this.bitField0_ & 2) == 2;
            }

            @Override // com.amazon.whispercloak.protobuf.SecureTransportProtos.AuthenticatedECDHEKeyExchangeResponseOrBuilder
            public boolean hasDerECDHEPublicKey() {
                return (this.bitField0_ & 1) == 1;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return SecureTransportProtos.internal_static_protobuf_AuthenticatedECDHEKeyExchangeResponse_fieldAccessorTable.ensureFieldAccessorsInitialized(AuthenticatedECDHEKeyExchangeResponse.class, Builder.class);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return hasDerECDHEPublicKey() && hasAuthenticationDataSecureMessage() && getAuthenticationDataSecureMessage().isInitialized();
            }

            public Builder mergeAuthenticationDataSecureMessage(AesGCMSecureMessage aesGCMSecureMessage) {
                AesGCMSecureMessage aesGCMSecureMessage2;
                SingleFieldBuilderV3<AesGCMSecureMessage, AesGCMSecureMessage.Builder, AesGCMSecureMessageOrBuilder> singleFieldBuilderV3 = this.authenticationDataSecureMessageBuilder_;
                if (singleFieldBuilderV3 == null) {
                    if ((this.bitField0_ & 2) == 2 && (aesGCMSecureMessage2 = this.authenticationDataSecureMessage_) != null && aesGCMSecureMessage2 != AesGCMSecureMessage.getDefaultInstance()) {
                        this.authenticationDataSecureMessage_ = AesGCMSecureMessage.newBuilder(this.authenticationDataSecureMessage_).mergeFrom(aesGCMSecureMessage).mo10085buildPartial();
                    } else {
                        this.authenticationDataSecureMessage_ = aesGCMSecureMessage;
                    }
                    onChanged();
                } else {
                    singleFieldBuilderV3.mergeFrom(aesGCMSecureMessage);
                }
                this.bitField0_ |= 2;
                return this;
            }

            public Builder setAuthenticationDataSecureMessage(AesGCMSecureMessage aesGCMSecureMessage) {
                SingleFieldBuilderV3<AesGCMSecureMessage, AesGCMSecureMessage.Builder, AesGCMSecureMessageOrBuilder> singleFieldBuilderV3 = this.authenticationDataSecureMessageBuilder_;
                if (singleFieldBuilderV3 != null) {
                    singleFieldBuilderV3.setMessage(aesGCMSecureMessage);
                } else if (aesGCMSecureMessage != null) {
                    this.authenticationDataSecureMessage_ = aesGCMSecureMessage;
                    onChanged();
                } else {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 2;
                return this;
            }

            public Builder setDerECDHEPublicKey(ByteString byteString) {
                if (byteString != null) {
                    this.bitField0_ |= 1;
                    this.derECDHEPublicKey_ = byteString;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            private Builder() {
                this.derECDHEPublicKey_ = ByteString.EMPTY;
                this.authenticationDataSecureMessage_ = null;
                maybeForceBuilderInitialization();
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            /* renamed from: addRepeatedField */
            public Builder mo10083addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.mo10083addRepeatedField(fieldDescriptor, obj);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: build */
            public AuthenticatedECDHEKeyExchangeResponse mo10084build() {
                AuthenticatedECDHEKeyExchangeResponse mo10085buildPartial = mo10085buildPartial();
                if (mo10085buildPartial.isInitialized()) {
                    return mo10085buildPartial;
                }
                throw AbstractMessage.Builder.newUninitializedMessageException((Message) mo10085buildPartial);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: buildPartial */
            public AuthenticatedECDHEKeyExchangeResponse mo10085buildPartial() {
                AuthenticatedECDHEKeyExchangeResponse authenticatedECDHEKeyExchangeResponse = new AuthenticatedECDHEKeyExchangeResponse(this);
                int i = this.bitField0_;
                int i2 = 1;
                if ((i & 1) != 1) {
                    i2 = 0;
                }
                authenticatedECDHEKeyExchangeResponse.derECDHEPublicKey_ = this.derECDHEPublicKey_;
                if ((i & 2) == 2) {
                    i2 |= 2;
                }
                SingleFieldBuilderV3<AesGCMSecureMessage, AesGCMSecureMessage.Builder, AesGCMSecureMessageOrBuilder> singleFieldBuilderV3 = this.authenticationDataSecureMessageBuilder_;
                if (singleFieldBuilderV3 == null) {
                    authenticatedECDHEKeyExchangeResponse.authenticationDataSecureMessage_ = this.authenticationDataSecureMessage_;
                } else {
                    authenticatedECDHEKeyExchangeResponse.authenticationDataSecureMessage_ = singleFieldBuilderV3.build();
                }
                authenticatedECDHEKeyExchangeResponse.bitField0_ = i2;
                onBuilt();
                return authenticatedECDHEKeyExchangeResponse;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            /* renamed from: clearField */
            public Builder mo10088clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (Builder) super.mo10088clearField(fieldDescriptor);
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            /* renamed from: getDefaultInstanceForType */
            public AuthenticatedECDHEKeyExchangeResponse mo10094getDefaultInstanceForType() {
                return AuthenticatedECDHEKeyExchangeResponse.getDefaultInstance();
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            /* renamed from: setField */
            public Builder mo10100setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.mo10100setField(fieldDescriptor, obj);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            /* renamed from: setRepeatedField */
            public Builder mo10101setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                return (Builder) super.mo10101setRepeatedField(fieldDescriptor, i, obj);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            /* renamed from: setUnknownFields */
            public final Builder mo10102setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.mo10102setUnknownFields(unknownFieldSet);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            /* renamed from: clearOneof */
            public Builder mo10090clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (Builder) super.mo10090clearOneof(oneofDescriptor);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            /* renamed from: mergeUnknownFields */
            public final Builder mo10099mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.mo10099mergeUnknownFields(unknownFieldSet);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: clear */
            public Builder mo10087clear() {
                super.mo10087clear();
                this.derECDHEPublicKey_ = ByteString.EMPTY;
                this.bitField0_ &= -2;
                SingleFieldBuilderV3<AesGCMSecureMessage, AesGCMSecureMessage.Builder, AesGCMSecureMessageOrBuilder> singleFieldBuilderV3 = this.authenticationDataSecureMessageBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.authenticationDataSecureMessage_ = null;
                } else {
                    singleFieldBuilderV3.clear();
                }
                this.bitField0_ &= -3;
                return this;
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.derECDHEPublicKey_ = ByteString.EMPTY;
                this.authenticationDataSecureMessage_ = null;
                maybeForceBuilderInitialization();
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: clone */
            public Builder mo10093clone() {
                return (Builder) super.mo10093clone();
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            /* renamed from: mergeFrom */
            public Builder mo10096mergeFrom(Message message) {
                if (message instanceof AuthenticatedECDHEKeyExchangeResponse) {
                    return mergeFrom((AuthenticatedECDHEKeyExchangeResponse) message);
                }
                super.mo10096mergeFrom(message);
                return this;
            }

            public Builder setAuthenticationDataSecureMessage(AesGCMSecureMessage.Builder builder) {
                SingleFieldBuilderV3<AesGCMSecureMessage, AesGCMSecureMessage.Builder, AesGCMSecureMessageOrBuilder> singleFieldBuilderV3 = this.authenticationDataSecureMessageBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.authenticationDataSecureMessage_ = builder.mo10084build();
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(builder.mo10084build());
                }
                this.bitField0_ |= 2;
                return this;
            }

            public Builder mergeFrom(AuthenticatedECDHEKeyExchangeResponse authenticatedECDHEKeyExchangeResponse) {
                if (authenticatedECDHEKeyExchangeResponse == AuthenticatedECDHEKeyExchangeResponse.getDefaultInstance()) {
                    return this;
                }
                if (authenticatedECDHEKeyExchangeResponse.hasDerECDHEPublicKey()) {
                    setDerECDHEPublicKey(authenticatedECDHEKeyExchangeResponse.getDerECDHEPublicKey());
                }
                if (authenticatedECDHEKeyExchangeResponse.hasAuthenticationDataSecureMessage()) {
                    mergeAuthenticationDataSecureMessage(authenticatedECDHEKeyExchangeResponse.getAuthenticationDataSecureMessage());
                }
                mo10099mergeUnknownFields(((GeneratedMessageV3) authenticatedECDHEKeyExchangeResponse).unknownFields);
                onChanged();
                return this;
            }

            /* JADX WARN: Removed duplicated region for block: B:16:0x0021  */
            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: mergeFrom */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public com.amazon.whispercloak.protobuf.SecureTransportProtos.AuthenticatedECDHEKeyExchangeResponse.Builder mo10097mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
                /*
                    r2 = this;
                    r0 = 0
                    com.google.protobuf.Parser<com.amazon.whispercloak.protobuf.SecureTransportProtos$AuthenticatedECDHEKeyExchangeResponse> r1 = com.amazon.whispercloak.protobuf.SecureTransportProtos.AuthenticatedECDHEKeyExchangeResponse.PARSER     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                    java.lang.Object r3 = r1.mo10082parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                    com.amazon.whispercloak.protobuf.SecureTransportProtos$AuthenticatedECDHEKeyExchangeResponse r3 = (com.amazon.whispercloak.protobuf.SecureTransportProtos.AuthenticatedECDHEKeyExchangeResponse) r3     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                    if (r3 == 0) goto Le
                    r2.mergeFrom(r3)
                Le:
                    return r2
                Lf:
                    r3 = move-exception
                    goto L1f
                L11:
                    r3 = move-exception
                    com.google.protobuf.MessageLite r4 = r3.getUnfinishedMessage()     // Catch: java.lang.Throwable -> Lf
                    com.amazon.whispercloak.protobuf.SecureTransportProtos$AuthenticatedECDHEKeyExchangeResponse r4 = (com.amazon.whispercloak.protobuf.SecureTransportProtos.AuthenticatedECDHEKeyExchangeResponse) r4     // Catch: java.lang.Throwable -> Lf
                    java.io.IOException r3 = r3.unwrapIOException()     // Catch: java.lang.Throwable -> L1d
                    throw r3     // Catch: java.lang.Throwable -> L1d
                L1d:
                    r3 = move-exception
                    r0 = r4
                L1f:
                    if (r0 == 0) goto L24
                    r2.mergeFrom(r0)
                L24:
                    throw r3
                */
                throw new UnsupportedOperationException("Method not decompiled: com.amazon.whispercloak.protobuf.SecureTransportProtos.AuthenticatedECDHEKeyExchangeResponse.Builder.mo10097mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.amazon.whispercloak.protobuf.SecureTransportProtos$AuthenticatedECDHEKeyExchangeResponse$Builder");
            }
        }

        public static Builder newBuilder(AuthenticatedECDHEKeyExchangeResponse authenticatedECDHEKeyExchangeResponse) {
            return DEFAULT_INSTANCE.mo10081toBuilder().mergeFrom(authenticatedECDHEKeyExchangeResponse);
        }

        public static AuthenticatedECDHEKeyExchangeResponse parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (AuthenticatedECDHEKeyExchangeResponse) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static AuthenticatedECDHEKeyExchangeResponse parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.mo8403parseFrom(byteBuffer, extensionRegistryLite);
        }

        private AuthenticatedECDHEKeyExchangeResponse(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        public static AuthenticatedECDHEKeyExchangeResponse parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.mo8396parseFrom(byteString);
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        /* renamed from: getDefaultInstanceForType */
        public AuthenticatedECDHEKeyExchangeResponse mo10094getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        /* renamed from: toBuilder */
        public Builder mo10081toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        public static AuthenticatedECDHEKeyExchangeResponse parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.mo8397parseFrom(byteString, extensionRegistryLite);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        /* renamed from: newBuilderForType */
        public Builder mo10079newBuilderForType() {
            return newBuilder();
        }

        private AuthenticatedECDHEKeyExchangeResponse() {
            this.memoizedIsInitialized = (byte) -1;
            this.derECDHEPublicKey_ = ByteString.EMPTY;
        }

        public static AuthenticatedECDHEKeyExchangeResponse parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.mo8404parseFrom(bArr);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageV3
        /* renamed from: newBuilderForType */
        public Builder mo10080newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public static AuthenticatedECDHEKeyExchangeResponse parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.mo8407parseFrom(bArr, extensionRegistryLite);
        }

        public static AuthenticatedECDHEKeyExchangeResponse parseFrom(InputStream inputStream) throws IOException {
            return (AuthenticatedECDHEKeyExchangeResponse) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        private AuthenticatedECDHEKeyExchangeResponse(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            this();
            if (extensionRegistryLite != null) {
                UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
                boolean z = false;
                while (!z) {
                    try {
                        try {
                            int readTag = codedInputStream.readTag();
                            if (readTag != 0) {
                                if (readTag == 10) {
                                    this.bitField0_ |= 1;
                                    this.derECDHEPublicKey_ = codedInputStream.readBytes();
                                } else if (readTag != 18) {
                                    if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                    }
                                } else {
                                    AesGCMSecureMessage.Builder mo10081toBuilder = (this.bitField0_ & 2) == 2 ? this.authenticationDataSecureMessage_.mo10081toBuilder() : null;
                                    this.authenticationDataSecureMessage_ = (AesGCMSecureMessage) codedInputStream.readMessage(AesGCMSecureMessage.PARSER, extensionRegistryLite);
                                    if (mo10081toBuilder != null) {
                                        mo10081toBuilder.mergeFrom(this.authenticationDataSecureMessage_);
                                        this.authenticationDataSecureMessage_ = mo10081toBuilder.mo10085buildPartial();
                                    }
                                    this.bitField0_ |= 2;
                                }
                            }
                            z = true;
                        } catch (InvalidProtocolBufferException e) {
                            throw e.setUnfinishedMessage(this);
                        } catch (IOException e2) {
                            throw new InvalidProtocolBufferException(e2).setUnfinishedMessage(this);
                        }
                    } finally {
                        this.unknownFields = newBuilder.mo10084build();
                        makeExtensionsImmutable();
                    }
                }
                return;
            }
            throw new NullPointerException();
        }

        public static AuthenticatedECDHEKeyExchangeResponse parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (AuthenticatedECDHEKeyExchangeResponse) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static AuthenticatedECDHEKeyExchangeResponse parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (AuthenticatedECDHEKeyExchangeResponse) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static AuthenticatedECDHEKeyExchangeResponse parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (AuthenticatedECDHEKeyExchangeResponse) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes13.dex */
    public interface AuthenticatedECDHEKeyExchangeResponseOrBuilder extends MessageOrBuilder {
        AesGCMSecureMessage getAuthenticationDataSecureMessage();

        AesGCMSecureMessageOrBuilder getAuthenticationDataSecureMessageOrBuilder();

        ByteString getDerECDHEPublicKey();

        boolean hasAuthenticationDataSecureMessage();

        boolean hasDerECDHEPublicKey();
    }

    /* loaded from: classes13.dex */
    public static final class KeyExchangeError extends GeneratedMessageV3 implements KeyExchangeErrorOrBuilder {
        public static final int MESSAGE_FIELD_NUMBER = 1;
        private static final long serialVersionUID = 0;
        private int bitField0_;
        private byte memoizedIsInitialized;
        private volatile Object message_;
        private static final KeyExchangeError DEFAULT_INSTANCE = new KeyExchangeError();
        @Deprecated
        public static final Parser<KeyExchangeError> PARSER = new AbstractParser<KeyExchangeError>() { // from class: com.amazon.whispercloak.protobuf.SecureTransportProtos.KeyExchangeError.1
            @Override // com.google.protobuf.Parser
            /* renamed from: parsePartialFrom */
            public KeyExchangeError mo10082parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new KeyExchangeError(codedInputStream, extensionRegistryLite);
            }
        };

        public static KeyExchangeError getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return SecureTransportProtos.internal_static_protobuf_KeyExchangeError_descriptor;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.mo10081toBuilder();
        }

        public static KeyExchangeError parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (KeyExchangeError) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static KeyExchangeError parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.mo8402parseFrom(byteBuffer);
        }

        public static Parser<KeyExchangeError> parser() {
            return PARSER;
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof KeyExchangeError)) {
                return super.equals(obj);
            }
            KeyExchangeError keyExchangeError = (KeyExchangeError) obj;
            boolean z = hasMessage() == keyExchangeError.hasMessage();
            if (hasMessage()) {
                z = z && getMessage().equals(keyExchangeError.getMessage());
            }
            return z && this.unknownFields.equals(keyExchangeError.unknownFields);
        }

        @Override // com.amazon.whispercloak.protobuf.SecureTransportProtos.KeyExchangeErrorOrBuilder
        public String getMessage() {
            Object obj = this.message_;
            if (obj instanceof String) {
                return (String) obj;
            }
            ByteString byteString = (ByteString) obj;
            String stringUtf8 = byteString.toStringUtf8();
            if (byteString.isValidUtf8()) {
                this.message_ = stringUtf8;
            }
            return stringUtf8;
        }

        @Override // com.amazon.whispercloak.protobuf.SecureTransportProtos.KeyExchangeErrorOrBuilder
        public ByteString getMessageBytes() {
            Object obj = this.message_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.message_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
        /* renamed from: getParserForType */
        public Parser<KeyExchangeError> mo9954getParserForType() {
            return PARSER;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            if ((this.bitField0_ & 1) == 1) {
                i2 = 0 + GeneratedMessageV3.computeStringSize(1, this.message_);
            }
            int serializedSize = this.unknownFields.getSerializedSize() + i2;
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        @Override // com.amazon.whispercloak.protobuf.SecureTransportProtos.KeyExchangeErrorOrBuilder
        public boolean hasMessage() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public int hashCode() {
            int i = this.memoizedHashCode;
            if (i != 0) {
                return i;
            }
            int hashCode = getDescriptor().hashCode() + 779;
            if (hasMessage()) {
                hashCode = GeneratedOutlineSupport1.outline4(hashCode, 37, 1, 53) + getMessage().hashCode();
            }
            int hashCode2 = this.unknownFields.hashCode() + (hashCode * 29);
            this.memoizedHashCode = hashCode2;
            return hashCode2;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return SecureTransportProtos.internal_static_protobuf_KeyExchangeError_fieldAccessorTable.ensureFieldAccessorsInitialized(KeyExchangeError.class, Builder.class);
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            byte b = this.memoizedIsInitialized;
            if (b == 1) {
                return true;
            }
            if (b == 0) {
                return false;
            }
            if (!hasMessage()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            }
            this.memoizedIsInitialized = (byte) 1;
            return true;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                GeneratedMessageV3.writeString(codedOutputStream, 1, this.message_);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        /* loaded from: classes13.dex */
        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements KeyExchangeErrorOrBuilder {
            private int bitField0_;
            private Object message_;

            public static final Descriptors.Descriptor getDescriptor() {
                return SecureTransportProtos.internal_static_protobuf_KeyExchangeError_descriptor;
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = GeneratedMessageV3.alwaysUseFieldBuilders;
            }

            public Builder clearMessage() {
                this.bitField0_ &= -2;
                this.message_ = KeyExchangeError.getDefaultInstance().getMessage();
                onChanged();
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return SecureTransportProtos.internal_static_protobuf_KeyExchangeError_descriptor;
            }

            @Override // com.amazon.whispercloak.protobuf.SecureTransportProtos.KeyExchangeErrorOrBuilder
            public String getMessage() {
                Object obj = this.message_;
                if (!(obj instanceof String)) {
                    ByteString byteString = (ByteString) obj;
                    String stringUtf8 = byteString.toStringUtf8();
                    if (byteString.isValidUtf8()) {
                        this.message_ = stringUtf8;
                    }
                    return stringUtf8;
                }
                return (String) obj;
            }

            @Override // com.amazon.whispercloak.protobuf.SecureTransportProtos.KeyExchangeErrorOrBuilder
            public ByteString getMessageBytes() {
                Object obj = this.message_;
                if (obj instanceof String) {
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.message_ = copyFromUtf8;
                    return copyFromUtf8;
                }
                return (ByteString) obj;
            }

            @Override // com.amazon.whispercloak.protobuf.SecureTransportProtos.KeyExchangeErrorOrBuilder
            public boolean hasMessage() {
                return (this.bitField0_ & 1) == 1;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return SecureTransportProtos.internal_static_protobuf_KeyExchangeError_fieldAccessorTable.ensureFieldAccessorsInitialized(KeyExchangeError.class, Builder.class);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return hasMessage();
            }

            public Builder setMessage(String str) {
                if (str != null) {
                    this.bitField0_ |= 1;
                    this.message_ = str;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder setMessageBytes(ByteString byteString) {
                if (byteString != null) {
                    this.bitField0_ |= 1;
                    this.message_ = byteString;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            private Builder() {
                this.message_ = "";
                maybeForceBuilderInitialization();
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            /* renamed from: addRepeatedField */
            public Builder mo10083addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.mo10083addRepeatedField(fieldDescriptor, obj);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: build */
            public KeyExchangeError mo10084build() {
                KeyExchangeError mo10085buildPartial = mo10085buildPartial();
                if (mo10085buildPartial.isInitialized()) {
                    return mo10085buildPartial;
                }
                throw AbstractMessage.Builder.newUninitializedMessageException((Message) mo10085buildPartial);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: buildPartial */
            public KeyExchangeError mo10085buildPartial() {
                KeyExchangeError keyExchangeError = new KeyExchangeError(this);
                int i = 1;
                if ((this.bitField0_ & 1) != 1) {
                    i = 0;
                }
                keyExchangeError.message_ = this.message_;
                keyExchangeError.bitField0_ = i;
                onBuilt();
                return keyExchangeError;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            /* renamed from: clearField */
            public Builder mo10088clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (Builder) super.mo10088clearField(fieldDescriptor);
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            /* renamed from: getDefaultInstanceForType */
            public KeyExchangeError mo10094getDefaultInstanceForType() {
                return KeyExchangeError.getDefaultInstance();
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            /* renamed from: setField */
            public Builder mo10100setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.mo10100setField(fieldDescriptor, obj);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            /* renamed from: setRepeatedField */
            public Builder mo10101setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                return (Builder) super.mo10101setRepeatedField(fieldDescriptor, i, obj);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            /* renamed from: setUnknownFields */
            public final Builder mo10102setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.mo10102setUnknownFields(unknownFieldSet);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            /* renamed from: clearOneof */
            public Builder mo10090clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (Builder) super.mo10090clearOneof(oneofDescriptor);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            /* renamed from: mergeUnknownFields */
            public final Builder mo10099mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.mo10099mergeUnknownFields(unknownFieldSet);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: clear */
            public Builder mo10087clear() {
                super.mo10087clear();
                this.message_ = "";
                this.bitField0_ &= -2;
                return this;
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.message_ = "";
                maybeForceBuilderInitialization();
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: clone */
            public Builder mo10093clone() {
                return (Builder) super.mo10093clone();
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            /* renamed from: mergeFrom */
            public Builder mo10096mergeFrom(Message message) {
                if (message instanceof KeyExchangeError) {
                    return mergeFrom((KeyExchangeError) message);
                }
                super.mo10096mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(KeyExchangeError keyExchangeError) {
                if (keyExchangeError == KeyExchangeError.getDefaultInstance()) {
                    return this;
                }
                if (keyExchangeError.hasMessage()) {
                    this.bitField0_ |= 1;
                    this.message_ = keyExchangeError.message_;
                    onChanged();
                }
                mo10099mergeUnknownFields(((GeneratedMessageV3) keyExchangeError).unknownFields);
                onChanged();
                return this;
            }

            /* JADX WARN: Removed duplicated region for block: B:16:0x0021  */
            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: mergeFrom */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public com.amazon.whispercloak.protobuf.SecureTransportProtos.KeyExchangeError.Builder mo10097mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
                /*
                    r2 = this;
                    r0 = 0
                    com.google.protobuf.Parser<com.amazon.whispercloak.protobuf.SecureTransportProtos$KeyExchangeError> r1 = com.amazon.whispercloak.protobuf.SecureTransportProtos.KeyExchangeError.PARSER     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                    java.lang.Object r3 = r1.mo10082parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                    com.amazon.whispercloak.protobuf.SecureTransportProtos$KeyExchangeError r3 = (com.amazon.whispercloak.protobuf.SecureTransportProtos.KeyExchangeError) r3     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                    if (r3 == 0) goto Le
                    r2.mergeFrom(r3)
                Le:
                    return r2
                Lf:
                    r3 = move-exception
                    goto L1f
                L11:
                    r3 = move-exception
                    com.google.protobuf.MessageLite r4 = r3.getUnfinishedMessage()     // Catch: java.lang.Throwable -> Lf
                    com.amazon.whispercloak.protobuf.SecureTransportProtos$KeyExchangeError r4 = (com.amazon.whispercloak.protobuf.SecureTransportProtos.KeyExchangeError) r4     // Catch: java.lang.Throwable -> Lf
                    java.io.IOException r3 = r3.unwrapIOException()     // Catch: java.lang.Throwable -> L1d
                    throw r3     // Catch: java.lang.Throwable -> L1d
                L1d:
                    r3 = move-exception
                    r0 = r4
                L1f:
                    if (r0 == 0) goto L24
                    r2.mergeFrom(r0)
                L24:
                    throw r3
                */
                throw new UnsupportedOperationException("Method not decompiled: com.amazon.whispercloak.protobuf.SecureTransportProtos.KeyExchangeError.Builder.mo10097mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.amazon.whispercloak.protobuf.SecureTransportProtos$KeyExchangeError$Builder");
            }
        }

        public static Builder newBuilder(KeyExchangeError keyExchangeError) {
            return DEFAULT_INSTANCE.mo10081toBuilder().mergeFrom(keyExchangeError);
        }

        public static KeyExchangeError parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (KeyExchangeError) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static KeyExchangeError parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.mo8403parseFrom(byteBuffer, extensionRegistryLite);
        }

        private KeyExchangeError(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        public static KeyExchangeError parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.mo8396parseFrom(byteString);
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        /* renamed from: getDefaultInstanceForType */
        public KeyExchangeError mo10094getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        /* renamed from: toBuilder */
        public Builder mo10081toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        public static KeyExchangeError parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.mo8397parseFrom(byteString, extensionRegistryLite);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        /* renamed from: newBuilderForType */
        public Builder mo10079newBuilderForType() {
            return newBuilder();
        }

        private KeyExchangeError() {
            this.memoizedIsInitialized = (byte) -1;
            this.message_ = "";
        }

        public static KeyExchangeError parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.mo8404parseFrom(bArr);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageV3
        /* renamed from: newBuilderForType */
        public Builder mo10080newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public static KeyExchangeError parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.mo8407parseFrom(bArr, extensionRegistryLite);
        }

        public static KeyExchangeError parseFrom(InputStream inputStream) throws IOException {
            return (KeyExchangeError) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        private KeyExchangeError(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            this();
            if (extensionRegistryLite != null) {
                UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
                boolean z = false;
                while (!z) {
                    try {
                        try {
                            try {
                                int readTag = codedInputStream.readTag();
                                if (readTag != 0) {
                                    if (readTag != 10) {
                                        if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                        }
                                    } else {
                                        ByteString readBytes = codedInputStream.readBytes();
                                        this.bitField0_ = 1 | this.bitField0_;
                                        this.message_ = readBytes;
                                    }
                                }
                                z = true;
                            } catch (InvalidProtocolBufferException e) {
                                throw e.setUnfinishedMessage(this);
                            }
                        } catch (IOException e2) {
                            throw new InvalidProtocolBufferException(e2).setUnfinishedMessage(this);
                        }
                    } finally {
                        this.unknownFields = newBuilder.mo10084build();
                        makeExtensionsImmutable();
                    }
                }
                return;
            }
            throw new NullPointerException();
        }

        public static KeyExchangeError parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (KeyExchangeError) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static KeyExchangeError parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (KeyExchangeError) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static KeyExchangeError parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (KeyExchangeError) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes13.dex */
    public interface KeyExchangeErrorOrBuilder extends MessageOrBuilder {
        String getMessage();

        ByteString getMessageBytes();

        boolean hasMessage();
    }

    /* loaded from: classes13.dex */
    public static final class ProvisionableDeviceAuthenticationData extends GeneratedMessageV3 implements ProvisionableDeviceAuthenticationDataOrBuilder {
        public static final int CERTIFICATECHAIN_FIELD_NUMBER = 1;
        private static final ProvisionableDeviceAuthenticationData DEFAULT_INSTANCE = new ProvisionableDeviceAuthenticationData();
        @Deprecated
        public static final Parser<ProvisionableDeviceAuthenticationData> PARSER = new AbstractParser<ProvisionableDeviceAuthenticationData>() { // from class: com.amazon.whispercloak.protobuf.SecureTransportProtos.ProvisionableDeviceAuthenticationData.1
            @Override // com.google.protobuf.Parser
            /* renamed from: parsePartialFrom */
            public ProvisionableDeviceAuthenticationData mo10082parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new ProvisionableDeviceAuthenticationData(codedInputStream, extensionRegistryLite);
            }
        };
        public static final int SIGNATURE_FIELD_NUMBER = 2;
        private static final long serialVersionUID = 0;
        private int bitField0_;
        private List<ByteString> certificateChain_;
        private byte memoizedIsInitialized;
        private ByteString signature_;

        public static ProvisionableDeviceAuthenticationData getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return SecureTransportProtos.internal_static_protobuf_ProvisionableDeviceAuthenticationData_descriptor;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.mo10081toBuilder();
        }

        public static ProvisionableDeviceAuthenticationData parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (ProvisionableDeviceAuthenticationData) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static ProvisionableDeviceAuthenticationData parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.mo8402parseFrom(byteBuffer);
        }

        public static Parser<ProvisionableDeviceAuthenticationData> parser() {
            return PARSER;
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof ProvisionableDeviceAuthenticationData)) {
                return super.equals(obj);
            }
            ProvisionableDeviceAuthenticationData provisionableDeviceAuthenticationData = (ProvisionableDeviceAuthenticationData) obj;
            boolean z = (getCertificateChainList().equals(provisionableDeviceAuthenticationData.getCertificateChainList())) && hasSignature() == provisionableDeviceAuthenticationData.hasSignature();
            if (hasSignature()) {
                z = z && getSignature().equals(provisionableDeviceAuthenticationData.getSignature());
            }
            return z && this.unknownFields.equals(provisionableDeviceAuthenticationData.unknownFields);
        }

        @Override // com.amazon.whispercloak.protobuf.SecureTransportProtos.ProvisionableDeviceAuthenticationDataOrBuilder
        public ByteString getCertificateChain(int i) {
            return this.certificateChain_.get(i);
        }

        @Override // com.amazon.whispercloak.protobuf.SecureTransportProtos.ProvisionableDeviceAuthenticationDataOrBuilder
        public int getCertificateChainCount() {
            return this.certificateChain_.size();
        }

        @Override // com.amazon.whispercloak.protobuf.SecureTransportProtos.ProvisionableDeviceAuthenticationDataOrBuilder
        public List<ByteString> getCertificateChainList() {
            return this.certificateChain_;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
        /* renamed from: getParserForType */
        public Parser<ProvisionableDeviceAuthenticationData> mo9954getParserForType() {
            return PARSER;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            for (int i3 = 0; i3 < this.certificateChain_.size(); i3++) {
                i2 += CodedOutputStream.computeBytesSizeNoTag(this.certificateChain_.get(i3));
            }
            int size = (getCertificateChainList().size() * 1) + 0 + i2;
            if ((this.bitField0_ & 1) == 1) {
                size += CodedOutputStream.computeBytesSize(2, this.signature_);
            }
            int serializedSize = this.unknownFields.getSerializedSize() + size;
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.amazon.whispercloak.protobuf.SecureTransportProtos.ProvisionableDeviceAuthenticationDataOrBuilder
        public ByteString getSignature() {
            return this.signature_;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        @Override // com.amazon.whispercloak.protobuf.SecureTransportProtos.ProvisionableDeviceAuthenticationDataOrBuilder
        public boolean hasSignature() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public int hashCode() {
            int i = this.memoizedHashCode;
            if (i != 0) {
                return i;
            }
            int hashCode = getDescriptor().hashCode() + 779;
            if (getCertificateChainCount() > 0) {
                hashCode = GeneratedOutlineSupport1.outline4(hashCode, 37, 1, 53) + getCertificateChainList().hashCode();
            }
            if (hasSignature()) {
                hashCode = GeneratedOutlineSupport1.outline4(hashCode, 37, 2, 53) + getSignature().hashCode();
            }
            int hashCode2 = this.unknownFields.hashCode() + (hashCode * 29);
            this.memoizedHashCode = hashCode2;
            return hashCode2;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return SecureTransportProtos.internal_static_protobuf_ProvisionableDeviceAuthenticationData_fieldAccessorTable.ensureFieldAccessorsInitialized(ProvisionableDeviceAuthenticationData.class, Builder.class);
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            byte b = this.memoizedIsInitialized;
            if (b == 1) {
                return true;
            }
            if (b == 0) {
                return false;
            }
            if (!hasSignature()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            }
            this.memoizedIsInitialized = (byte) 1;
            return true;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            for (int i = 0; i < this.certificateChain_.size(); i++) {
                codedOutputStream.writeBytes(1, this.certificateChain_.get(i));
            }
            if ((this.bitField0_ & 1) == 1) {
                codedOutputStream.writeBytes(2, this.signature_);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        /* loaded from: classes13.dex */
        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements ProvisionableDeviceAuthenticationDataOrBuilder {
            private int bitField0_;
            private List<ByteString> certificateChain_;
            private ByteString signature_;

            private void ensureCertificateChainIsMutable() {
                if ((this.bitField0_ & 1) != 1) {
                    this.certificateChain_ = new ArrayList(this.certificateChain_);
                    this.bitField0_ |= 1;
                }
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return SecureTransportProtos.internal_static_protobuf_ProvisionableDeviceAuthenticationData_descriptor;
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = GeneratedMessageV3.alwaysUseFieldBuilders;
            }

            public Builder addAllCertificateChain(Iterable<? extends ByteString> iterable) {
                ensureCertificateChainIsMutable();
                AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.certificateChain_);
                onChanged();
                return this;
            }

            public Builder addCertificateChain(ByteString byteString) {
                if (byteString != null) {
                    ensureCertificateChainIsMutable();
                    this.certificateChain_.add(byteString);
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder clearCertificateChain() {
                this.certificateChain_ = Collections.emptyList();
                this.bitField0_ &= -2;
                onChanged();
                return this;
            }

            public Builder clearSignature() {
                this.bitField0_ &= -3;
                this.signature_ = ProvisionableDeviceAuthenticationData.getDefaultInstance().getSignature();
                onChanged();
                return this;
            }

            @Override // com.amazon.whispercloak.protobuf.SecureTransportProtos.ProvisionableDeviceAuthenticationDataOrBuilder
            public ByteString getCertificateChain(int i) {
                return this.certificateChain_.get(i);
            }

            @Override // com.amazon.whispercloak.protobuf.SecureTransportProtos.ProvisionableDeviceAuthenticationDataOrBuilder
            public int getCertificateChainCount() {
                return this.certificateChain_.size();
            }

            @Override // com.amazon.whispercloak.protobuf.SecureTransportProtos.ProvisionableDeviceAuthenticationDataOrBuilder
            public List<ByteString> getCertificateChainList() {
                return Collections.unmodifiableList(this.certificateChain_);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return SecureTransportProtos.internal_static_protobuf_ProvisionableDeviceAuthenticationData_descriptor;
            }

            @Override // com.amazon.whispercloak.protobuf.SecureTransportProtos.ProvisionableDeviceAuthenticationDataOrBuilder
            public ByteString getSignature() {
                return this.signature_;
            }

            @Override // com.amazon.whispercloak.protobuf.SecureTransportProtos.ProvisionableDeviceAuthenticationDataOrBuilder
            public boolean hasSignature() {
                return (this.bitField0_ & 2) == 2;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return SecureTransportProtos.internal_static_protobuf_ProvisionableDeviceAuthenticationData_fieldAccessorTable.ensureFieldAccessorsInitialized(ProvisionableDeviceAuthenticationData.class, Builder.class);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return hasSignature();
            }

            public Builder setCertificateChain(int i, ByteString byteString) {
                if (byteString != null) {
                    ensureCertificateChainIsMutable();
                    this.certificateChain_.set(i, byteString);
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder setSignature(ByteString byteString) {
                if (byteString != null) {
                    this.bitField0_ |= 2;
                    this.signature_ = byteString;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            private Builder() {
                this.certificateChain_ = Collections.emptyList();
                this.signature_ = ByteString.EMPTY;
                maybeForceBuilderInitialization();
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            /* renamed from: addRepeatedField */
            public Builder mo10083addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.mo10083addRepeatedField(fieldDescriptor, obj);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: build */
            public ProvisionableDeviceAuthenticationData mo10084build() {
                ProvisionableDeviceAuthenticationData mo10085buildPartial = mo10085buildPartial();
                if (mo10085buildPartial.isInitialized()) {
                    return mo10085buildPartial;
                }
                throw AbstractMessage.Builder.newUninitializedMessageException((Message) mo10085buildPartial);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: buildPartial */
            public ProvisionableDeviceAuthenticationData mo10085buildPartial() {
                ProvisionableDeviceAuthenticationData provisionableDeviceAuthenticationData = new ProvisionableDeviceAuthenticationData(this);
                int i = this.bitField0_;
                int i2 = 1;
                if ((i & 1) == 1) {
                    this.certificateChain_ = Collections.unmodifiableList(this.certificateChain_);
                    this.bitField0_ &= -2;
                }
                provisionableDeviceAuthenticationData.certificateChain_ = this.certificateChain_;
                if ((i & 2) != 2) {
                    i2 = 0;
                }
                provisionableDeviceAuthenticationData.signature_ = this.signature_;
                provisionableDeviceAuthenticationData.bitField0_ = i2;
                onBuilt();
                return provisionableDeviceAuthenticationData;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            /* renamed from: clearField */
            public Builder mo10088clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (Builder) super.mo10088clearField(fieldDescriptor);
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            /* renamed from: getDefaultInstanceForType */
            public ProvisionableDeviceAuthenticationData mo10094getDefaultInstanceForType() {
                return ProvisionableDeviceAuthenticationData.getDefaultInstance();
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            /* renamed from: setField */
            public Builder mo10100setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.mo10100setField(fieldDescriptor, obj);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            /* renamed from: setRepeatedField */
            public Builder mo10101setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                return (Builder) super.mo10101setRepeatedField(fieldDescriptor, i, obj);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            /* renamed from: setUnknownFields */
            public final Builder mo10102setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.mo10102setUnknownFields(unknownFieldSet);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            /* renamed from: clearOneof */
            public Builder mo10090clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (Builder) super.mo10090clearOneof(oneofDescriptor);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            /* renamed from: mergeUnknownFields */
            public final Builder mo10099mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.mo10099mergeUnknownFields(unknownFieldSet);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: clear */
            public Builder mo10087clear() {
                super.mo10087clear();
                this.certificateChain_ = Collections.emptyList();
                this.bitField0_ &= -2;
                this.signature_ = ByteString.EMPTY;
                this.bitField0_ &= -3;
                return this;
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.certificateChain_ = Collections.emptyList();
                this.signature_ = ByteString.EMPTY;
                maybeForceBuilderInitialization();
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: clone */
            public Builder mo10093clone() {
                return (Builder) super.mo10093clone();
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            /* renamed from: mergeFrom */
            public Builder mo10096mergeFrom(Message message) {
                if (message instanceof ProvisionableDeviceAuthenticationData) {
                    return mergeFrom((ProvisionableDeviceAuthenticationData) message);
                }
                super.mo10096mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(ProvisionableDeviceAuthenticationData provisionableDeviceAuthenticationData) {
                if (provisionableDeviceAuthenticationData == ProvisionableDeviceAuthenticationData.getDefaultInstance()) {
                    return this;
                }
                if (!provisionableDeviceAuthenticationData.certificateChain_.isEmpty()) {
                    if (this.certificateChain_.isEmpty()) {
                        this.certificateChain_ = provisionableDeviceAuthenticationData.certificateChain_;
                        this.bitField0_ &= -2;
                    } else {
                        ensureCertificateChainIsMutable();
                        this.certificateChain_.addAll(provisionableDeviceAuthenticationData.certificateChain_);
                    }
                    onChanged();
                }
                if (provisionableDeviceAuthenticationData.hasSignature()) {
                    setSignature(provisionableDeviceAuthenticationData.getSignature());
                }
                mo10099mergeUnknownFields(((GeneratedMessageV3) provisionableDeviceAuthenticationData).unknownFields);
                onChanged();
                return this;
            }

            /* JADX WARN: Removed duplicated region for block: B:16:0x0021  */
            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: mergeFrom */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public com.amazon.whispercloak.protobuf.SecureTransportProtos.ProvisionableDeviceAuthenticationData.Builder mo10097mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
                /*
                    r2 = this;
                    r0 = 0
                    com.google.protobuf.Parser<com.amazon.whispercloak.protobuf.SecureTransportProtos$ProvisionableDeviceAuthenticationData> r1 = com.amazon.whispercloak.protobuf.SecureTransportProtos.ProvisionableDeviceAuthenticationData.PARSER     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                    java.lang.Object r3 = r1.mo10082parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                    com.amazon.whispercloak.protobuf.SecureTransportProtos$ProvisionableDeviceAuthenticationData r3 = (com.amazon.whispercloak.protobuf.SecureTransportProtos.ProvisionableDeviceAuthenticationData) r3     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                    if (r3 == 0) goto Le
                    r2.mergeFrom(r3)
                Le:
                    return r2
                Lf:
                    r3 = move-exception
                    goto L1f
                L11:
                    r3 = move-exception
                    com.google.protobuf.MessageLite r4 = r3.getUnfinishedMessage()     // Catch: java.lang.Throwable -> Lf
                    com.amazon.whispercloak.protobuf.SecureTransportProtos$ProvisionableDeviceAuthenticationData r4 = (com.amazon.whispercloak.protobuf.SecureTransportProtos.ProvisionableDeviceAuthenticationData) r4     // Catch: java.lang.Throwable -> Lf
                    java.io.IOException r3 = r3.unwrapIOException()     // Catch: java.lang.Throwable -> L1d
                    throw r3     // Catch: java.lang.Throwable -> L1d
                L1d:
                    r3 = move-exception
                    r0 = r4
                L1f:
                    if (r0 == 0) goto L24
                    r2.mergeFrom(r0)
                L24:
                    throw r3
                */
                throw new UnsupportedOperationException("Method not decompiled: com.amazon.whispercloak.protobuf.SecureTransportProtos.ProvisionableDeviceAuthenticationData.Builder.mo10097mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.amazon.whispercloak.protobuf.SecureTransportProtos$ProvisionableDeviceAuthenticationData$Builder");
            }
        }

        public static Builder newBuilder(ProvisionableDeviceAuthenticationData provisionableDeviceAuthenticationData) {
            return DEFAULT_INSTANCE.mo10081toBuilder().mergeFrom(provisionableDeviceAuthenticationData);
        }

        public static ProvisionableDeviceAuthenticationData parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ProvisionableDeviceAuthenticationData) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static ProvisionableDeviceAuthenticationData parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.mo8403parseFrom(byteBuffer, extensionRegistryLite);
        }

        private ProvisionableDeviceAuthenticationData(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        public static ProvisionableDeviceAuthenticationData parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.mo8396parseFrom(byteString);
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        /* renamed from: getDefaultInstanceForType */
        public ProvisionableDeviceAuthenticationData mo10094getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        /* renamed from: toBuilder */
        public Builder mo10081toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        public static ProvisionableDeviceAuthenticationData parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.mo8397parseFrom(byteString, extensionRegistryLite);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        /* renamed from: newBuilderForType */
        public Builder mo10079newBuilderForType() {
            return newBuilder();
        }

        private ProvisionableDeviceAuthenticationData() {
            this.memoizedIsInitialized = (byte) -1;
            this.certificateChain_ = Collections.emptyList();
            this.signature_ = ByteString.EMPTY;
        }

        public static ProvisionableDeviceAuthenticationData parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.mo8404parseFrom(bArr);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageV3
        /* renamed from: newBuilderForType */
        public Builder mo10080newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public static ProvisionableDeviceAuthenticationData parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.mo8407parseFrom(bArr, extensionRegistryLite);
        }

        public static ProvisionableDeviceAuthenticationData parseFrom(InputStream inputStream) throws IOException {
            return (ProvisionableDeviceAuthenticationData) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static ProvisionableDeviceAuthenticationData parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ProvisionableDeviceAuthenticationData) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        private ProvisionableDeviceAuthenticationData(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            this();
            if (extensionRegistryLite != null) {
                UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
                boolean z = false;
                boolean z2 = false;
                while (true) {
                    if (z) {
                        break;
                    }
                    try {
                        try {
                            int readTag = codedInputStream.readTag();
                            if (readTag != 0) {
                                if (readTag == 10) {
                                    if (!(z2 & true)) {
                                        this.certificateChain_ = new ArrayList();
                                        z2 |= true;
                                    }
                                    this.certificateChain_.add(codedInputStream.readBytes());
                                } else if (readTag != 18) {
                                    if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                    }
                                } else {
                                    this.bitField0_ |= 1;
                                    this.signature_ = codedInputStream.readBytes();
                                }
                            }
                            z = true;
                        } catch (InvalidProtocolBufferException e) {
                            throw e.setUnfinishedMessage(this);
                        } catch (IOException e2) {
                            throw new InvalidProtocolBufferException(e2).setUnfinishedMessage(this);
                        }
                    } finally {
                        if (z2 & true) {
                            this.certificateChain_ = Collections.unmodifiableList(this.certificateChain_);
                        }
                        this.unknownFields = newBuilder.mo10084build();
                        makeExtensionsImmutable();
                    }
                }
                return;
            }
            throw new NullPointerException();
        }

        public static ProvisionableDeviceAuthenticationData parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (ProvisionableDeviceAuthenticationData) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static ProvisionableDeviceAuthenticationData parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ProvisionableDeviceAuthenticationData) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes13.dex */
    public interface ProvisionableDeviceAuthenticationDataOrBuilder extends MessageOrBuilder {
        ByteString getCertificateChain(int i);

        int getCertificateChainCount();

        List<ByteString> getCertificateChainList();

        ByteString getSignature();

        boolean hasSignature();
    }

    /* loaded from: classes13.dex */
    public static final class UnauthenticatedECDHEKeyExchangeRequest extends GeneratedMessageV3 implements UnauthenticatedECDHEKeyExchangeRequestOrBuilder {
        public static final int DERECDHEPUBLICKEY_FIELD_NUMBER = 1;
        public static final int SIGNATURE_FIELD_NUMBER = 2;
        private static final long serialVersionUID = 0;
        private int bitField0_;
        private ByteString derECDHEPublicKey_;
        private byte memoizedIsInitialized;
        private ByteString signature_;
        private static final UnauthenticatedECDHEKeyExchangeRequest DEFAULT_INSTANCE = new UnauthenticatedECDHEKeyExchangeRequest();
        @Deprecated
        public static final Parser<UnauthenticatedECDHEKeyExchangeRequest> PARSER = new AbstractParser<UnauthenticatedECDHEKeyExchangeRequest>() { // from class: com.amazon.whispercloak.protobuf.SecureTransportProtos.UnauthenticatedECDHEKeyExchangeRequest.1
            @Override // com.google.protobuf.Parser
            /* renamed from: parsePartialFrom */
            public UnauthenticatedECDHEKeyExchangeRequest mo10082parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new UnauthenticatedECDHEKeyExchangeRequest(codedInputStream, extensionRegistryLite);
            }
        };

        public static UnauthenticatedECDHEKeyExchangeRequest getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return SecureTransportProtos.internal_static_protobuf_UnauthenticatedECDHEKeyExchangeRequest_descriptor;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.mo10081toBuilder();
        }

        public static UnauthenticatedECDHEKeyExchangeRequest parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (UnauthenticatedECDHEKeyExchangeRequest) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static UnauthenticatedECDHEKeyExchangeRequest parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.mo8402parseFrom(byteBuffer);
        }

        public static Parser<UnauthenticatedECDHEKeyExchangeRequest> parser() {
            return PARSER;
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof UnauthenticatedECDHEKeyExchangeRequest)) {
                return super.equals(obj);
            }
            UnauthenticatedECDHEKeyExchangeRequest unauthenticatedECDHEKeyExchangeRequest = (UnauthenticatedECDHEKeyExchangeRequest) obj;
            boolean z = hasDerECDHEPublicKey() == unauthenticatedECDHEKeyExchangeRequest.hasDerECDHEPublicKey();
            if (hasDerECDHEPublicKey()) {
                z = z && getDerECDHEPublicKey().equals(unauthenticatedECDHEKeyExchangeRequest.getDerECDHEPublicKey());
            }
            boolean z2 = z && hasSignature() == unauthenticatedECDHEKeyExchangeRequest.hasSignature();
            if (hasSignature()) {
                z2 = z2 && getSignature().equals(unauthenticatedECDHEKeyExchangeRequest.getSignature());
            }
            return z2 && this.unknownFields.equals(unauthenticatedECDHEKeyExchangeRequest.unknownFields);
        }

        @Override // com.amazon.whispercloak.protobuf.SecureTransportProtos.UnauthenticatedECDHEKeyExchangeRequestOrBuilder
        public ByteString getDerECDHEPublicKey() {
            return this.derECDHEPublicKey_;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
        /* renamed from: getParserForType */
        public Parser<UnauthenticatedECDHEKeyExchangeRequest> mo9954getParserForType() {
            return PARSER;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            if ((this.bitField0_ & 1) == 1) {
                i2 = 0 + CodedOutputStream.computeBytesSize(1, this.derECDHEPublicKey_);
            }
            if ((this.bitField0_ & 2) == 2) {
                i2 += CodedOutputStream.computeBytesSize(2, this.signature_);
            }
            int serializedSize = this.unknownFields.getSerializedSize() + i2;
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.amazon.whispercloak.protobuf.SecureTransportProtos.UnauthenticatedECDHEKeyExchangeRequestOrBuilder
        public ByteString getSignature() {
            return this.signature_;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        @Override // com.amazon.whispercloak.protobuf.SecureTransportProtos.UnauthenticatedECDHEKeyExchangeRequestOrBuilder
        public boolean hasDerECDHEPublicKey() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // com.amazon.whispercloak.protobuf.SecureTransportProtos.UnauthenticatedECDHEKeyExchangeRequestOrBuilder
        public boolean hasSignature() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public int hashCode() {
            int i = this.memoizedHashCode;
            if (i != 0) {
                return i;
            }
            int hashCode = getDescriptor().hashCode() + 779;
            if (hasDerECDHEPublicKey()) {
                hashCode = GeneratedOutlineSupport1.outline4(hashCode, 37, 1, 53) + getDerECDHEPublicKey().hashCode();
            }
            if (hasSignature()) {
                hashCode = GeneratedOutlineSupport1.outline4(hashCode, 37, 2, 53) + getSignature().hashCode();
            }
            int hashCode2 = this.unknownFields.hashCode() + (hashCode * 29);
            this.memoizedHashCode = hashCode2;
            return hashCode2;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return SecureTransportProtos.internal_static_protobuf_UnauthenticatedECDHEKeyExchangeRequest_fieldAccessorTable.ensureFieldAccessorsInitialized(UnauthenticatedECDHEKeyExchangeRequest.class, Builder.class);
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            byte b = this.memoizedIsInitialized;
            if (b == 1) {
                return true;
            }
            if (b == 0) {
                return false;
            }
            if (!hasDerECDHEPublicKey()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            }
            this.memoizedIsInitialized = (byte) 1;
            return true;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                codedOutputStream.writeBytes(1, this.derECDHEPublicKey_);
            }
            if ((this.bitField0_ & 2) == 2) {
                codedOutputStream.writeBytes(2, this.signature_);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        /* loaded from: classes13.dex */
        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements UnauthenticatedECDHEKeyExchangeRequestOrBuilder {
            private int bitField0_;
            private ByteString derECDHEPublicKey_;
            private ByteString signature_;

            public static final Descriptors.Descriptor getDescriptor() {
                return SecureTransportProtos.internal_static_protobuf_UnauthenticatedECDHEKeyExchangeRequest_descriptor;
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = GeneratedMessageV3.alwaysUseFieldBuilders;
            }

            public Builder clearDerECDHEPublicKey() {
                this.bitField0_ &= -2;
                this.derECDHEPublicKey_ = UnauthenticatedECDHEKeyExchangeRequest.getDefaultInstance().getDerECDHEPublicKey();
                onChanged();
                return this;
            }

            public Builder clearSignature() {
                this.bitField0_ &= -3;
                this.signature_ = UnauthenticatedECDHEKeyExchangeRequest.getDefaultInstance().getSignature();
                onChanged();
                return this;
            }

            @Override // com.amazon.whispercloak.protobuf.SecureTransportProtos.UnauthenticatedECDHEKeyExchangeRequestOrBuilder
            public ByteString getDerECDHEPublicKey() {
                return this.derECDHEPublicKey_;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return SecureTransportProtos.internal_static_protobuf_UnauthenticatedECDHEKeyExchangeRequest_descriptor;
            }

            @Override // com.amazon.whispercloak.protobuf.SecureTransportProtos.UnauthenticatedECDHEKeyExchangeRequestOrBuilder
            public ByteString getSignature() {
                return this.signature_;
            }

            @Override // com.amazon.whispercloak.protobuf.SecureTransportProtos.UnauthenticatedECDHEKeyExchangeRequestOrBuilder
            public boolean hasDerECDHEPublicKey() {
                return (this.bitField0_ & 1) == 1;
            }

            @Override // com.amazon.whispercloak.protobuf.SecureTransportProtos.UnauthenticatedECDHEKeyExchangeRequestOrBuilder
            public boolean hasSignature() {
                return (this.bitField0_ & 2) == 2;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return SecureTransportProtos.internal_static_protobuf_UnauthenticatedECDHEKeyExchangeRequest_fieldAccessorTable.ensureFieldAccessorsInitialized(UnauthenticatedECDHEKeyExchangeRequest.class, Builder.class);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return hasDerECDHEPublicKey();
            }

            public Builder setDerECDHEPublicKey(ByteString byteString) {
                if (byteString != null) {
                    this.bitField0_ |= 1;
                    this.derECDHEPublicKey_ = byteString;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder setSignature(ByteString byteString) {
                if (byteString != null) {
                    this.bitField0_ |= 2;
                    this.signature_ = byteString;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            private Builder() {
                ByteString byteString = ByteString.EMPTY;
                this.derECDHEPublicKey_ = byteString;
                this.signature_ = byteString;
                maybeForceBuilderInitialization();
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            /* renamed from: addRepeatedField */
            public Builder mo10083addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.mo10083addRepeatedField(fieldDescriptor, obj);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: build */
            public UnauthenticatedECDHEKeyExchangeRequest mo10084build() {
                UnauthenticatedECDHEKeyExchangeRequest mo10085buildPartial = mo10085buildPartial();
                if (mo10085buildPartial.isInitialized()) {
                    return mo10085buildPartial;
                }
                throw AbstractMessage.Builder.newUninitializedMessageException((Message) mo10085buildPartial);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: buildPartial */
            public UnauthenticatedECDHEKeyExchangeRequest mo10085buildPartial() {
                UnauthenticatedECDHEKeyExchangeRequest unauthenticatedECDHEKeyExchangeRequest = new UnauthenticatedECDHEKeyExchangeRequest(this);
                int i = this.bitField0_;
                int i2 = 1;
                if ((i & 1) != 1) {
                    i2 = 0;
                }
                unauthenticatedECDHEKeyExchangeRequest.derECDHEPublicKey_ = this.derECDHEPublicKey_;
                if ((i & 2) == 2) {
                    i2 |= 2;
                }
                unauthenticatedECDHEKeyExchangeRequest.signature_ = this.signature_;
                unauthenticatedECDHEKeyExchangeRequest.bitField0_ = i2;
                onBuilt();
                return unauthenticatedECDHEKeyExchangeRequest;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            /* renamed from: clearField */
            public Builder mo10088clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (Builder) super.mo10088clearField(fieldDescriptor);
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            /* renamed from: getDefaultInstanceForType */
            public UnauthenticatedECDHEKeyExchangeRequest mo10094getDefaultInstanceForType() {
                return UnauthenticatedECDHEKeyExchangeRequest.getDefaultInstance();
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            /* renamed from: setField */
            public Builder mo10100setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.mo10100setField(fieldDescriptor, obj);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            /* renamed from: setRepeatedField */
            public Builder mo10101setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                return (Builder) super.mo10101setRepeatedField(fieldDescriptor, i, obj);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            /* renamed from: setUnknownFields */
            public final Builder mo10102setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.mo10102setUnknownFields(unknownFieldSet);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            /* renamed from: clearOneof */
            public Builder mo10090clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (Builder) super.mo10090clearOneof(oneofDescriptor);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            /* renamed from: mergeUnknownFields */
            public final Builder mo10099mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.mo10099mergeUnknownFields(unknownFieldSet);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: clear */
            public Builder mo10087clear() {
                super.mo10087clear();
                ByteString byteString = ByteString.EMPTY;
                this.derECDHEPublicKey_ = byteString;
                this.bitField0_ &= -2;
                this.signature_ = byteString;
                this.bitField0_ &= -3;
                return this;
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                ByteString byteString = ByteString.EMPTY;
                this.derECDHEPublicKey_ = byteString;
                this.signature_ = byteString;
                maybeForceBuilderInitialization();
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: clone */
            public Builder mo10093clone() {
                return (Builder) super.mo10093clone();
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            /* renamed from: mergeFrom */
            public Builder mo10096mergeFrom(Message message) {
                if (message instanceof UnauthenticatedECDHEKeyExchangeRequest) {
                    return mergeFrom((UnauthenticatedECDHEKeyExchangeRequest) message);
                }
                super.mo10096mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(UnauthenticatedECDHEKeyExchangeRequest unauthenticatedECDHEKeyExchangeRequest) {
                if (unauthenticatedECDHEKeyExchangeRequest == UnauthenticatedECDHEKeyExchangeRequest.getDefaultInstance()) {
                    return this;
                }
                if (unauthenticatedECDHEKeyExchangeRequest.hasDerECDHEPublicKey()) {
                    setDerECDHEPublicKey(unauthenticatedECDHEKeyExchangeRequest.getDerECDHEPublicKey());
                }
                if (unauthenticatedECDHEKeyExchangeRequest.hasSignature()) {
                    setSignature(unauthenticatedECDHEKeyExchangeRequest.getSignature());
                }
                mo10099mergeUnknownFields(((GeneratedMessageV3) unauthenticatedECDHEKeyExchangeRequest).unknownFields);
                onChanged();
                return this;
            }

            /* JADX WARN: Removed duplicated region for block: B:16:0x0021  */
            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: mergeFrom */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public com.amazon.whispercloak.protobuf.SecureTransportProtos.UnauthenticatedECDHEKeyExchangeRequest.Builder mo10097mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
                /*
                    r2 = this;
                    r0 = 0
                    com.google.protobuf.Parser<com.amazon.whispercloak.protobuf.SecureTransportProtos$UnauthenticatedECDHEKeyExchangeRequest> r1 = com.amazon.whispercloak.protobuf.SecureTransportProtos.UnauthenticatedECDHEKeyExchangeRequest.PARSER     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                    java.lang.Object r3 = r1.mo10082parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                    com.amazon.whispercloak.protobuf.SecureTransportProtos$UnauthenticatedECDHEKeyExchangeRequest r3 = (com.amazon.whispercloak.protobuf.SecureTransportProtos.UnauthenticatedECDHEKeyExchangeRequest) r3     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                    if (r3 == 0) goto Le
                    r2.mergeFrom(r3)
                Le:
                    return r2
                Lf:
                    r3 = move-exception
                    goto L1f
                L11:
                    r3 = move-exception
                    com.google.protobuf.MessageLite r4 = r3.getUnfinishedMessage()     // Catch: java.lang.Throwable -> Lf
                    com.amazon.whispercloak.protobuf.SecureTransportProtos$UnauthenticatedECDHEKeyExchangeRequest r4 = (com.amazon.whispercloak.protobuf.SecureTransportProtos.UnauthenticatedECDHEKeyExchangeRequest) r4     // Catch: java.lang.Throwable -> Lf
                    java.io.IOException r3 = r3.unwrapIOException()     // Catch: java.lang.Throwable -> L1d
                    throw r3     // Catch: java.lang.Throwable -> L1d
                L1d:
                    r3 = move-exception
                    r0 = r4
                L1f:
                    if (r0 == 0) goto L24
                    r2.mergeFrom(r0)
                L24:
                    throw r3
                */
                throw new UnsupportedOperationException("Method not decompiled: com.amazon.whispercloak.protobuf.SecureTransportProtos.UnauthenticatedECDHEKeyExchangeRequest.Builder.mo10097mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.amazon.whispercloak.protobuf.SecureTransportProtos$UnauthenticatedECDHEKeyExchangeRequest$Builder");
            }
        }

        public static Builder newBuilder(UnauthenticatedECDHEKeyExchangeRequest unauthenticatedECDHEKeyExchangeRequest) {
            return DEFAULT_INSTANCE.mo10081toBuilder().mergeFrom(unauthenticatedECDHEKeyExchangeRequest);
        }

        public static UnauthenticatedECDHEKeyExchangeRequest parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (UnauthenticatedECDHEKeyExchangeRequest) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static UnauthenticatedECDHEKeyExchangeRequest parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.mo8403parseFrom(byteBuffer, extensionRegistryLite);
        }

        private UnauthenticatedECDHEKeyExchangeRequest(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        public static UnauthenticatedECDHEKeyExchangeRequest parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.mo8396parseFrom(byteString);
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        /* renamed from: getDefaultInstanceForType */
        public UnauthenticatedECDHEKeyExchangeRequest mo10094getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        /* renamed from: toBuilder */
        public Builder mo10081toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        public static UnauthenticatedECDHEKeyExchangeRequest parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.mo8397parseFrom(byteString, extensionRegistryLite);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        /* renamed from: newBuilderForType */
        public Builder mo10079newBuilderForType() {
            return newBuilder();
        }

        private UnauthenticatedECDHEKeyExchangeRequest() {
            this.memoizedIsInitialized = (byte) -1;
            ByteString byteString = ByteString.EMPTY;
            this.derECDHEPublicKey_ = byteString;
            this.signature_ = byteString;
        }

        public static UnauthenticatedECDHEKeyExchangeRequest parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.mo8404parseFrom(bArr);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageV3
        /* renamed from: newBuilderForType */
        public Builder mo10080newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public static UnauthenticatedECDHEKeyExchangeRequest parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.mo8407parseFrom(bArr, extensionRegistryLite);
        }

        public static UnauthenticatedECDHEKeyExchangeRequest parseFrom(InputStream inputStream) throws IOException {
            return (UnauthenticatedECDHEKeyExchangeRequest) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static UnauthenticatedECDHEKeyExchangeRequest parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (UnauthenticatedECDHEKeyExchangeRequest) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        private UnauthenticatedECDHEKeyExchangeRequest(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            this();
            if (extensionRegistryLite != null) {
                UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
                boolean z = false;
                while (!z) {
                    try {
                        try {
                            int readTag = codedInputStream.readTag();
                            if (readTag != 0) {
                                if (readTag == 10) {
                                    this.bitField0_ |= 1;
                                    this.derECDHEPublicKey_ = codedInputStream.readBytes();
                                } else if (readTag != 18) {
                                    if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                    }
                                } else {
                                    this.bitField0_ |= 2;
                                    this.signature_ = codedInputStream.readBytes();
                                }
                            }
                            z = true;
                        } catch (InvalidProtocolBufferException e) {
                            throw e.setUnfinishedMessage(this);
                        } catch (IOException e2) {
                            throw new InvalidProtocolBufferException(e2).setUnfinishedMessage(this);
                        }
                    } finally {
                        this.unknownFields = newBuilder.mo10084build();
                        makeExtensionsImmutable();
                    }
                }
                return;
            }
            throw new NullPointerException();
        }

        public static UnauthenticatedECDHEKeyExchangeRequest parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (UnauthenticatedECDHEKeyExchangeRequest) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static UnauthenticatedECDHEKeyExchangeRequest parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (UnauthenticatedECDHEKeyExchangeRequest) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes13.dex */
    public interface UnauthenticatedECDHEKeyExchangeRequestOrBuilder extends MessageOrBuilder {
        ByteString getDerECDHEPublicKey();

        ByteString getSignature();

        boolean hasDerECDHEPublicKey();

        boolean hasSignature();
    }

    /* loaded from: classes13.dex */
    public static final class UnauthenticatedECDHEKeyExchangeResponse extends GeneratedMessageV3 implements UnauthenticatedECDHEKeyExchangeResponseOrBuilder {
        public static final int AUTHENTICATIONDATASECUREMESSAGE_FIELD_NUMBER = 2;
        public static final int DERECDHEPUBLICKEY_FIELD_NUMBER = 1;
        private static final long serialVersionUID = 0;
        private AesGCMSecureMessage authenticationDataSecureMessage_;
        private int bitField0_;
        private ByteString derECDHEPublicKey_;
        private byte memoizedIsInitialized;
        private static final UnauthenticatedECDHEKeyExchangeResponse DEFAULT_INSTANCE = new UnauthenticatedECDHEKeyExchangeResponse();
        @Deprecated
        public static final Parser<UnauthenticatedECDHEKeyExchangeResponse> PARSER = new AbstractParser<UnauthenticatedECDHEKeyExchangeResponse>() { // from class: com.amazon.whispercloak.protobuf.SecureTransportProtos.UnauthenticatedECDHEKeyExchangeResponse.1
            @Override // com.google.protobuf.Parser
            /* renamed from: parsePartialFrom */
            public UnauthenticatedECDHEKeyExchangeResponse mo10082parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new UnauthenticatedECDHEKeyExchangeResponse(codedInputStream, extensionRegistryLite);
            }
        };

        public static UnauthenticatedECDHEKeyExchangeResponse getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return SecureTransportProtos.internal_static_protobuf_UnauthenticatedECDHEKeyExchangeResponse_descriptor;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.mo10081toBuilder();
        }

        public static UnauthenticatedECDHEKeyExchangeResponse parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (UnauthenticatedECDHEKeyExchangeResponse) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static UnauthenticatedECDHEKeyExchangeResponse parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.mo8402parseFrom(byteBuffer);
        }

        public static Parser<UnauthenticatedECDHEKeyExchangeResponse> parser() {
            return PARSER;
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof UnauthenticatedECDHEKeyExchangeResponse)) {
                return super.equals(obj);
            }
            UnauthenticatedECDHEKeyExchangeResponse unauthenticatedECDHEKeyExchangeResponse = (UnauthenticatedECDHEKeyExchangeResponse) obj;
            boolean z = hasDerECDHEPublicKey() == unauthenticatedECDHEKeyExchangeResponse.hasDerECDHEPublicKey();
            if (hasDerECDHEPublicKey()) {
                z = z && getDerECDHEPublicKey().equals(unauthenticatedECDHEKeyExchangeResponse.getDerECDHEPublicKey());
            }
            boolean z2 = z && hasAuthenticationDataSecureMessage() == unauthenticatedECDHEKeyExchangeResponse.hasAuthenticationDataSecureMessage();
            if (hasAuthenticationDataSecureMessage()) {
                z2 = z2 && getAuthenticationDataSecureMessage().equals(unauthenticatedECDHEKeyExchangeResponse.getAuthenticationDataSecureMessage());
            }
            return z2 && this.unknownFields.equals(unauthenticatedECDHEKeyExchangeResponse.unknownFields);
        }

        @Override // com.amazon.whispercloak.protobuf.SecureTransportProtos.UnauthenticatedECDHEKeyExchangeResponseOrBuilder
        public AesGCMSecureMessage getAuthenticationDataSecureMessage() {
            AesGCMSecureMessage aesGCMSecureMessage = this.authenticationDataSecureMessage_;
            return aesGCMSecureMessage == null ? AesGCMSecureMessage.getDefaultInstance() : aesGCMSecureMessage;
        }

        @Override // com.amazon.whispercloak.protobuf.SecureTransportProtos.UnauthenticatedECDHEKeyExchangeResponseOrBuilder
        public AesGCMSecureMessageOrBuilder getAuthenticationDataSecureMessageOrBuilder() {
            AesGCMSecureMessage aesGCMSecureMessage = this.authenticationDataSecureMessage_;
            return aesGCMSecureMessage == null ? AesGCMSecureMessage.getDefaultInstance() : aesGCMSecureMessage;
        }

        @Override // com.amazon.whispercloak.protobuf.SecureTransportProtos.UnauthenticatedECDHEKeyExchangeResponseOrBuilder
        public ByteString getDerECDHEPublicKey() {
            return this.derECDHEPublicKey_;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
        /* renamed from: getParserForType */
        public Parser<UnauthenticatedECDHEKeyExchangeResponse> mo9954getParserForType() {
            return PARSER;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            if ((this.bitField0_ & 1) == 1) {
                i2 = 0 + CodedOutputStream.computeBytesSize(1, this.derECDHEPublicKey_);
            }
            if ((this.bitField0_ & 2) == 2) {
                i2 += CodedOutputStream.computeMessageSize(2, getAuthenticationDataSecureMessage());
            }
            int serializedSize = this.unknownFields.getSerializedSize() + i2;
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        @Override // com.amazon.whispercloak.protobuf.SecureTransportProtos.UnauthenticatedECDHEKeyExchangeResponseOrBuilder
        public boolean hasAuthenticationDataSecureMessage() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // com.amazon.whispercloak.protobuf.SecureTransportProtos.UnauthenticatedECDHEKeyExchangeResponseOrBuilder
        public boolean hasDerECDHEPublicKey() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public int hashCode() {
            int i = this.memoizedHashCode;
            if (i != 0) {
                return i;
            }
            int hashCode = getDescriptor().hashCode() + 779;
            if (hasDerECDHEPublicKey()) {
                hashCode = GeneratedOutlineSupport1.outline4(hashCode, 37, 1, 53) + getDerECDHEPublicKey().hashCode();
            }
            if (hasAuthenticationDataSecureMessage()) {
                hashCode = GeneratedOutlineSupport1.outline4(hashCode, 37, 2, 53) + getAuthenticationDataSecureMessage().hashCode();
            }
            int hashCode2 = this.unknownFields.hashCode() + (hashCode * 29);
            this.memoizedHashCode = hashCode2;
            return hashCode2;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return SecureTransportProtos.internal_static_protobuf_UnauthenticatedECDHEKeyExchangeResponse_fieldAccessorTable.ensureFieldAccessorsInitialized(UnauthenticatedECDHEKeyExchangeResponse.class, Builder.class);
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            byte b = this.memoizedIsInitialized;
            if (b == 1) {
                return true;
            }
            if (b == 0) {
                return false;
            }
            if (!hasDerECDHEPublicKey()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            } else if (hasAuthenticationDataSecureMessage() && !getAuthenticationDataSecureMessage().isInitialized()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            } else {
                this.memoizedIsInitialized = (byte) 1;
                return true;
            }
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                codedOutputStream.writeBytes(1, this.derECDHEPublicKey_);
            }
            if ((this.bitField0_ & 2) == 2) {
                codedOutputStream.writeMessage(2, getAuthenticationDataSecureMessage());
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        /* loaded from: classes13.dex */
        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements UnauthenticatedECDHEKeyExchangeResponseOrBuilder {
            private SingleFieldBuilderV3<AesGCMSecureMessage, AesGCMSecureMessage.Builder, AesGCMSecureMessageOrBuilder> authenticationDataSecureMessageBuilder_;
            private AesGCMSecureMessage authenticationDataSecureMessage_;
            private int bitField0_;
            private ByteString derECDHEPublicKey_;

            private SingleFieldBuilderV3<AesGCMSecureMessage, AesGCMSecureMessage.Builder, AesGCMSecureMessageOrBuilder> getAuthenticationDataSecureMessageFieldBuilder() {
                if (this.authenticationDataSecureMessageBuilder_ == null) {
                    this.authenticationDataSecureMessageBuilder_ = new SingleFieldBuilderV3<>(getAuthenticationDataSecureMessage(), getParentForChildren(), isClean());
                    this.authenticationDataSecureMessage_ = null;
                }
                return this.authenticationDataSecureMessageBuilder_;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return SecureTransportProtos.internal_static_protobuf_UnauthenticatedECDHEKeyExchangeResponse_descriptor;
            }

            private void maybeForceBuilderInitialization() {
                if (GeneratedMessageV3.alwaysUseFieldBuilders) {
                    getAuthenticationDataSecureMessageFieldBuilder();
                }
            }

            public Builder clearAuthenticationDataSecureMessage() {
                SingleFieldBuilderV3<AesGCMSecureMessage, AesGCMSecureMessage.Builder, AesGCMSecureMessageOrBuilder> singleFieldBuilderV3 = this.authenticationDataSecureMessageBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.authenticationDataSecureMessage_ = null;
                    onChanged();
                } else {
                    singleFieldBuilderV3.clear();
                }
                this.bitField0_ &= -3;
                return this;
            }

            public Builder clearDerECDHEPublicKey() {
                this.bitField0_ &= -2;
                this.derECDHEPublicKey_ = UnauthenticatedECDHEKeyExchangeResponse.getDefaultInstance().getDerECDHEPublicKey();
                onChanged();
                return this;
            }

            @Override // com.amazon.whispercloak.protobuf.SecureTransportProtos.UnauthenticatedECDHEKeyExchangeResponseOrBuilder
            public AesGCMSecureMessage getAuthenticationDataSecureMessage() {
                SingleFieldBuilderV3<AesGCMSecureMessage, AesGCMSecureMessage.Builder, AesGCMSecureMessageOrBuilder> singleFieldBuilderV3 = this.authenticationDataSecureMessageBuilder_;
                if (singleFieldBuilderV3 == null) {
                    AesGCMSecureMessage aesGCMSecureMessage = this.authenticationDataSecureMessage_;
                    return aesGCMSecureMessage == null ? AesGCMSecureMessage.getDefaultInstance() : aesGCMSecureMessage;
                }
                return singleFieldBuilderV3.getMessage();
            }

            public AesGCMSecureMessage.Builder getAuthenticationDataSecureMessageBuilder() {
                this.bitField0_ |= 2;
                onChanged();
                return getAuthenticationDataSecureMessageFieldBuilder().getBuilder();
            }

            @Override // com.amazon.whispercloak.protobuf.SecureTransportProtos.UnauthenticatedECDHEKeyExchangeResponseOrBuilder
            public AesGCMSecureMessageOrBuilder getAuthenticationDataSecureMessageOrBuilder() {
                SingleFieldBuilderV3<AesGCMSecureMessage, AesGCMSecureMessage.Builder, AesGCMSecureMessageOrBuilder> singleFieldBuilderV3 = this.authenticationDataSecureMessageBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessageOrBuilder();
                }
                AesGCMSecureMessage aesGCMSecureMessage = this.authenticationDataSecureMessage_;
                return aesGCMSecureMessage == null ? AesGCMSecureMessage.getDefaultInstance() : aesGCMSecureMessage;
            }

            @Override // com.amazon.whispercloak.protobuf.SecureTransportProtos.UnauthenticatedECDHEKeyExchangeResponseOrBuilder
            public ByteString getDerECDHEPublicKey() {
                return this.derECDHEPublicKey_;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return SecureTransportProtos.internal_static_protobuf_UnauthenticatedECDHEKeyExchangeResponse_descriptor;
            }

            @Override // com.amazon.whispercloak.protobuf.SecureTransportProtos.UnauthenticatedECDHEKeyExchangeResponseOrBuilder
            public boolean hasAuthenticationDataSecureMessage() {
                return (this.bitField0_ & 2) == 2;
            }

            @Override // com.amazon.whispercloak.protobuf.SecureTransportProtos.UnauthenticatedECDHEKeyExchangeResponseOrBuilder
            public boolean hasDerECDHEPublicKey() {
                return (this.bitField0_ & 1) == 1;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return SecureTransportProtos.internal_static_protobuf_UnauthenticatedECDHEKeyExchangeResponse_fieldAccessorTable.ensureFieldAccessorsInitialized(UnauthenticatedECDHEKeyExchangeResponse.class, Builder.class);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                if (!hasDerECDHEPublicKey()) {
                    return false;
                }
                return !hasAuthenticationDataSecureMessage() || getAuthenticationDataSecureMessage().isInitialized();
            }

            public Builder mergeAuthenticationDataSecureMessage(AesGCMSecureMessage aesGCMSecureMessage) {
                AesGCMSecureMessage aesGCMSecureMessage2;
                SingleFieldBuilderV3<AesGCMSecureMessage, AesGCMSecureMessage.Builder, AesGCMSecureMessageOrBuilder> singleFieldBuilderV3 = this.authenticationDataSecureMessageBuilder_;
                if (singleFieldBuilderV3 == null) {
                    if ((this.bitField0_ & 2) == 2 && (aesGCMSecureMessage2 = this.authenticationDataSecureMessage_) != null && aesGCMSecureMessage2 != AesGCMSecureMessage.getDefaultInstance()) {
                        this.authenticationDataSecureMessage_ = AesGCMSecureMessage.newBuilder(this.authenticationDataSecureMessage_).mergeFrom(aesGCMSecureMessage).mo10085buildPartial();
                    } else {
                        this.authenticationDataSecureMessage_ = aesGCMSecureMessage;
                    }
                    onChanged();
                } else {
                    singleFieldBuilderV3.mergeFrom(aesGCMSecureMessage);
                }
                this.bitField0_ |= 2;
                return this;
            }

            public Builder setAuthenticationDataSecureMessage(AesGCMSecureMessage aesGCMSecureMessage) {
                SingleFieldBuilderV3<AesGCMSecureMessage, AesGCMSecureMessage.Builder, AesGCMSecureMessageOrBuilder> singleFieldBuilderV3 = this.authenticationDataSecureMessageBuilder_;
                if (singleFieldBuilderV3 != null) {
                    singleFieldBuilderV3.setMessage(aesGCMSecureMessage);
                } else if (aesGCMSecureMessage != null) {
                    this.authenticationDataSecureMessage_ = aesGCMSecureMessage;
                    onChanged();
                } else {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 2;
                return this;
            }

            public Builder setDerECDHEPublicKey(ByteString byteString) {
                if (byteString != null) {
                    this.bitField0_ |= 1;
                    this.derECDHEPublicKey_ = byteString;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            private Builder() {
                this.derECDHEPublicKey_ = ByteString.EMPTY;
                this.authenticationDataSecureMessage_ = null;
                maybeForceBuilderInitialization();
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            /* renamed from: addRepeatedField */
            public Builder mo10083addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.mo10083addRepeatedField(fieldDescriptor, obj);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: build */
            public UnauthenticatedECDHEKeyExchangeResponse mo10084build() {
                UnauthenticatedECDHEKeyExchangeResponse mo10085buildPartial = mo10085buildPartial();
                if (mo10085buildPartial.isInitialized()) {
                    return mo10085buildPartial;
                }
                throw AbstractMessage.Builder.newUninitializedMessageException((Message) mo10085buildPartial);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: buildPartial */
            public UnauthenticatedECDHEKeyExchangeResponse mo10085buildPartial() {
                UnauthenticatedECDHEKeyExchangeResponse unauthenticatedECDHEKeyExchangeResponse = new UnauthenticatedECDHEKeyExchangeResponse(this);
                int i = this.bitField0_;
                int i2 = 1;
                if ((i & 1) != 1) {
                    i2 = 0;
                }
                unauthenticatedECDHEKeyExchangeResponse.derECDHEPublicKey_ = this.derECDHEPublicKey_;
                if ((i & 2) == 2) {
                    i2 |= 2;
                }
                SingleFieldBuilderV3<AesGCMSecureMessage, AesGCMSecureMessage.Builder, AesGCMSecureMessageOrBuilder> singleFieldBuilderV3 = this.authenticationDataSecureMessageBuilder_;
                if (singleFieldBuilderV3 == null) {
                    unauthenticatedECDHEKeyExchangeResponse.authenticationDataSecureMessage_ = this.authenticationDataSecureMessage_;
                } else {
                    unauthenticatedECDHEKeyExchangeResponse.authenticationDataSecureMessage_ = singleFieldBuilderV3.build();
                }
                unauthenticatedECDHEKeyExchangeResponse.bitField0_ = i2;
                onBuilt();
                return unauthenticatedECDHEKeyExchangeResponse;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            /* renamed from: clearField */
            public Builder mo10088clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (Builder) super.mo10088clearField(fieldDescriptor);
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            /* renamed from: getDefaultInstanceForType */
            public UnauthenticatedECDHEKeyExchangeResponse mo10094getDefaultInstanceForType() {
                return UnauthenticatedECDHEKeyExchangeResponse.getDefaultInstance();
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            /* renamed from: setField */
            public Builder mo10100setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.mo10100setField(fieldDescriptor, obj);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            /* renamed from: setRepeatedField */
            public Builder mo10101setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                return (Builder) super.mo10101setRepeatedField(fieldDescriptor, i, obj);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            /* renamed from: setUnknownFields */
            public final Builder mo10102setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.mo10102setUnknownFields(unknownFieldSet);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            /* renamed from: clearOneof */
            public Builder mo10090clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (Builder) super.mo10090clearOneof(oneofDescriptor);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            /* renamed from: mergeUnknownFields */
            public final Builder mo10099mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.mo10099mergeUnknownFields(unknownFieldSet);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: clear */
            public Builder mo10087clear() {
                super.mo10087clear();
                this.derECDHEPublicKey_ = ByteString.EMPTY;
                this.bitField0_ &= -2;
                SingleFieldBuilderV3<AesGCMSecureMessage, AesGCMSecureMessage.Builder, AesGCMSecureMessageOrBuilder> singleFieldBuilderV3 = this.authenticationDataSecureMessageBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.authenticationDataSecureMessage_ = null;
                } else {
                    singleFieldBuilderV3.clear();
                }
                this.bitField0_ &= -3;
                return this;
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.derECDHEPublicKey_ = ByteString.EMPTY;
                this.authenticationDataSecureMessage_ = null;
                maybeForceBuilderInitialization();
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: clone */
            public Builder mo10093clone() {
                return (Builder) super.mo10093clone();
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            /* renamed from: mergeFrom */
            public Builder mo10096mergeFrom(Message message) {
                if (message instanceof UnauthenticatedECDHEKeyExchangeResponse) {
                    return mergeFrom((UnauthenticatedECDHEKeyExchangeResponse) message);
                }
                super.mo10096mergeFrom(message);
                return this;
            }

            public Builder setAuthenticationDataSecureMessage(AesGCMSecureMessage.Builder builder) {
                SingleFieldBuilderV3<AesGCMSecureMessage, AesGCMSecureMessage.Builder, AesGCMSecureMessageOrBuilder> singleFieldBuilderV3 = this.authenticationDataSecureMessageBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.authenticationDataSecureMessage_ = builder.mo10084build();
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(builder.mo10084build());
                }
                this.bitField0_ |= 2;
                return this;
            }

            public Builder mergeFrom(UnauthenticatedECDHEKeyExchangeResponse unauthenticatedECDHEKeyExchangeResponse) {
                if (unauthenticatedECDHEKeyExchangeResponse == UnauthenticatedECDHEKeyExchangeResponse.getDefaultInstance()) {
                    return this;
                }
                if (unauthenticatedECDHEKeyExchangeResponse.hasDerECDHEPublicKey()) {
                    setDerECDHEPublicKey(unauthenticatedECDHEKeyExchangeResponse.getDerECDHEPublicKey());
                }
                if (unauthenticatedECDHEKeyExchangeResponse.hasAuthenticationDataSecureMessage()) {
                    mergeAuthenticationDataSecureMessage(unauthenticatedECDHEKeyExchangeResponse.getAuthenticationDataSecureMessage());
                }
                mo10099mergeUnknownFields(((GeneratedMessageV3) unauthenticatedECDHEKeyExchangeResponse).unknownFields);
                onChanged();
                return this;
            }

            /* JADX WARN: Removed duplicated region for block: B:16:0x0021  */
            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: mergeFrom */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public com.amazon.whispercloak.protobuf.SecureTransportProtos.UnauthenticatedECDHEKeyExchangeResponse.Builder mo10097mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
                /*
                    r2 = this;
                    r0 = 0
                    com.google.protobuf.Parser<com.amazon.whispercloak.protobuf.SecureTransportProtos$UnauthenticatedECDHEKeyExchangeResponse> r1 = com.amazon.whispercloak.protobuf.SecureTransportProtos.UnauthenticatedECDHEKeyExchangeResponse.PARSER     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                    java.lang.Object r3 = r1.mo10082parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                    com.amazon.whispercloak.protobuf.SecureTransportProtos$UnauthenticatedECDHEKeyExchangeResponse r3 = (com.amazon.whispercloak.protobuf.SecureTransportProtos.UnauthenticatedECDHEKeyExchangeResponse) r3     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                    if (r3 == 0) goto Le
                    r2.mergeFrom(r3)
                Le:
                    return r2
                Lf:
                    r3 = move-exception
                    goto L1f
                L11:
                    r3 = move-exception
                    com.google.protobuf.MessageLite r4 = r3.getUnfinishedMessage()     // Catch: java.lang.Throwable -> Lf
                    com.amazon.whispercloak.protobuf.SecureTransportProtos$UnauthenticatedECDHEKeyExchangeResponse r4 = (com.amazon.whispercloak.protobuf.SecureTransportProtos.UnauthenticatedECDHEKeyExchangeResponse) r4     // Catch: java.lang.Throwable -> Lf
                    java.io.IOException r3 = r3.unwrapIOException()     // Catch: java.lang.Throwable -> L1d
                    throw r3     // Catch: java.lang.Throwable -> L1d
                L1d:
                    r3 = move-exception
                    r0 = r4
                L1f:
                    if (r0 == 0) goto L24
                    r2.mergeFrom(r0)
                L24:
                    throw r3
                */
                throw new UnsupportedOperationException("Method not decompiled: com.amazon.whispercloak.protobuf.SecureTransportProtos.UnauthenticatedECDHEKeyExchangeResponse.Builder.mo10097mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.amazon.whispercloak.protobuf.SecureTransportProtos$UnauthenticatedECDHEKeyExchangeResponse$Builder");
            }
        }

        public static Builder newBuilder(UnauthenticatedECDHEKeyExchangeResponse unauthenticatedECDHEKeyExchangeResponse) {
            return DEFAULT_INSTANCE.mo10081toBuilder().mergeFrom(unauthenticatedECDHEKeyExchangeResponse);
        }

        public static UnauthenticatedECDHEKeyExchangeResponse parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (UnauthenticatedECDHEKeyExchangeResponse) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static UnauthenticatedECDHEKeyExchangeResponse parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.mo8403parseFrom(byteBuffer, extensionRegistryLite);
        }

        private UnauthenticatedECDHEKeyExchangeResponse(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        public static UnauthenticatedECDHEKeyExchangeResponse parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.mo8396parseFrom(byteString);
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        /* renamed from: getDefaultInstanceForType */
        public UnauthenticatedECDHEKeyExchangeResponse mo10094getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        /* renamed from: toBuilder */
        public Builder mo10081toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        public static UnauthenticatedECDHEKeyExchangeResponse parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.mo8397parseFrom(byteString, extensionRegistryLite);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        /* renamed from: newBuilderForType */
        public Builder mo10079newBuilderForType() {
            return newBuilder();
        }

        private UnauthenticatedECDHEKeyExchangeResponse() {
            this.memoizedIsInitialized = (byte) -1;
            this.derECDHEPublicKey_ = ByteString.EMPTY;
        }

        public static UnauthenticatedECDHEKeyExchangeResponse parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.mo8404parseFrom(bArr);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageV3
        /* renamed from: newBuilderForType */
        public Builder mo10080newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public static UnauthenticatedECDHEKeyExchangeResponse parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.mo8407parseFrom(bArr, extensionRegistryLite);
        }

        public static UnauthenticatedECDHEKeyExchangeResponse parseFrom(InputStream inputStream) throws IOException {
            return (UnauthenticatedECDHEKeyExchangeResponse) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        private UnauthenticatedECDHEKeyExchangeResponse(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            this();
            if (extensionRegistryLite != null) {
                UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
                boolean z = false;
                while (!z) {
                    try {
                        try {
                            int readTag = codedInputStream.readTag();
                            if (readTag != 0) {
                                if (readTag == 10) {
                                    this.bitField0_ |= 1;
                                    this.derECDHEPublicKey_ = codedInputStream.readBytes();
                                } else if (readTag != 18) {
                                    if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                    }
                                } else {
                                    AesGCMSecureMessage.Builder mo10081toBuilder = (this.bitField0_ & 2) == 2 ? this.authenticationDataSecureMessage_.mo10081toBuilder() : null;
                                    this.authenticationDataSecureMessage_ = (AesGCMSecureMessage) codedInputStream.readMessage(AesGCMSecureMessage.PARSER, extensionRegistryLite);
                                    if (mo10081toBuilder != null) {
                                        mo10081toBuilder.mergeFrom(this.authenticationDataSecureMessage_);
                                        this.authenticationDataSecureMessage_ = mo10081toBuilder.mo10085buildPartial();
                                    }
                                    this.bitField0_ |= 2;
                                }
                            }
                            z = true;
                        } catch (InvalidProtocolBufferException e) {
                            throw e.setUnfinishedMessage(this);
                        } catch (IOException e2) {
                            throw new InvalidProtocolBufferException(e2).setUnfinishedMessage(this);
                        }
                    } finally {
                        this.unknownFields = newBuilder.mo10084build();
                        makeExtensionsImmutable();
                    }
                }
                return;
            }
            throw new NullPointerException();
        }

        public static UnauthenticatedECDHEKeyExchangeResponse parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (UnauthenticatedECDHEKeyExchangeResponse) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static UnauthenticatedECDHEKeyExchangeResponse parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (UnauthenticatedECDHEKeyExchangeResponse) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static UnauthenticatedECDHEKeyExchangeResponse parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (UnauthenticatedECDHEKeyExchangeResponse) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes13.dex */
    public interface UnauthenticatedECDHEKeyExchangeResponseOrBuilder extends MessageOrBuilder {
        AesGCMSecureMessage getAuthenticationDataSecureMessage();

        AesGCMSecureMessageOrBuilder getAuthenticationDataSecureMessageOrBuilder();

        ByteString getDerECDHEPublicKey();

        boolean hasAuthenticationDataSecureMessage();

        boolean hasDerECDHEPublicKey();
    }

    static {
        Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n=WhisperCloakProtocolBuffersModel/schema/SecureTransport.proto\u0012\bprotobuf\"^\n\u0013AesGCMSecureMessage\u0012\u001c\n\u0014initializationVector\u0018\u0001 \u0002(\f\u0012\u000f\n\u0007payload\u0018\u0002 \u0002(\f\u0012\u000b\n\u0003mac\u0018\u0003 \u0002(\f\u0012\u000b\n\u0003aad\u0018\u0004 \u0002(\f\"T\n$AuthenticatedECDHEKeyExchangeRequest\u0012\u0019\n\u0011derECDHEPublicKey\u0018\u0001 \u0002(\f\u0012\u0011\n\tsignature\u0018\u0002 \u0002(\f\"\u008a\u0001\n%AuthenticatedECDHEKeyExchangeResponse\u0012\u0019\n\u0011derECDHEPublicKey\u0018\u0001 \u0002(\f\u0012F\n\u001fauthenticationDataSecureMessage\u0018\u0002 \u0002(\u000b2\u001d.protobuf.AesGCMSecureMessage\"T\n%ProvisionableDeviceAuthenticationData\u0012\u0018\n\u0010certificateChain\u0018\u0001 \u0003(\f\u0012\u0011\n\tsignature\u0018\u0002 \u0002(\f\"V\n&UnauthenticatedECDHEKeyExchangeRequest\u0012\u0019\n\u0011derECDHEPublicKey\u0018\u0001 \u0002(\f\u0012\u0011\n\tsignature\u0018\u0002 \u0001(\f\"\u008c\u0001\n'UnauthenticatedECDHEKeyExchangeResponse\u0012\u0019\n\u0011derECDHEPublicKey\u0018\u0001 \u0002(\f\u0012F\n\u001fauthenticationDataSecureMessage\u0018\u0002 \u0001(\u000b2\u001d.protobuf.AesGCMSecureMessage\"#\n\u0010KeyExchangeError\u0012\u000f\n\u0007message\u0018\u0001 \u0002(\tB9\n com.amazon.whispercloak.protobufB\u0015SecureTransportProtos"}, new Descriptors.FileDescriptor[0], new Descriptors.FileDescriptor.InternalDescriptorAssigner() { // from class: com.amazon.whispercloak.protobuf.SecureTransportProtos.1
            @Override // com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner
            public ExtensionRegistry assignDescriptors(Descriptors.FileDescriptor fileDescriptor) {
                Descriptors.FileDescriptor unused = SecureTransportProtos.descriptor = fileDescriptor;
                return null;
            }
        });
        internal_static_protobuf_AesGCMSecureMessage_descriptor = getDescriptor().getMessageTypes().get(0);
        internal_static_protobuf_AesGCMSecureMessage_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_protobuf_AesGCMSecureMessage_descriptor, new String[]{"InitializationVector", DatabaseHelper.appInfo_Payload, "Mac", "Aad"});
        internal_static_protobuf_AuthenticatedECDHEKeyExchangeRequest_descriptor = getDescriptor().getMessageTypes().get(1);
        internal_static_protobuf_AuthenticatedECDHEKeyExchangeRequest_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_protobuf_AuthenticatedECDHEKeyExchangeRequest_descriptor, new String[]{"DerECDHEPublicKey", "Signature"});
        internal_static_protobuf_AuthenticatedECDHEKeyExchangeResponse_descriptor = getDescriptor().getMessageTypes().get(2);
        internal_static_protobuf_AuthenticatedECDHEKeyExchangeResponse_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_protobuf_AuthenticatedECDHEKeyExchangeResponse_descriptor, new String[]{"DerECDHEPublicKey", "AuthenticationDataSecureMessage"});
        internal_static_protobuf_ProvisionableDeviceAuthenticationData_descriptor = getDescriptor().getMessageTypes().get(3);
        internal_static_protobuf_ProvisionableDeviceAuthenticationData_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_protobuf_ProvisionableDeviceAuthenticationData_descriptor, new String[]{"CertificateChain", "Signature"});
        internal_static_protobuf_UnauthenticatedECDHEKeyExchangeRequest_descriptor = getDescriptor().getMessageTypes().get(4);
        internal_static_protobuf_UnauthenticatedECDHEKeyExchangeRequest_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_protobuf_UnauthenticatedECDHEKeyExchangeRequest_descriptor, new String[]{"DerECDHEPublicKey", "Signature"});
        internal_static_protobuf_UnauthenticatedECDHEKeyExchangeResponse_descriptor = getDescriptor().getMessageTypes().get(5);
        internal_static_protobuf_UnauthenticatedECDHEKeyExchangeResponse_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_protobuf_UnauthenticatedECDHEKeyExchangeResponse_descriptor, new String[]{"DerECDHEPublicKey", "AuthenticationDataSecureMessage"});
        internal_static_protobuf_KeyExchangeError_descriptor = getDescriptor().getMessageTypes().get(6);
        internal_static_protobuf_KeyExchangeError_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_protobuf_KeyExchangeError_descriptor, new String[]{AlexaMetricsConstants.EventConstants.MESSAGE});
    }

    private SecureTransportProtos() {
    }

    public static Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }

    public static void registerAllExtensions(ExtensionRegistry extensionRegistry) {
        registerAllExtensions((ExtensionRegistryLite) extensionRegistry);
    }

    public static void registerAllExtensions(ExtensionRegistryLite extensionRegistryLite) {
    }
}
