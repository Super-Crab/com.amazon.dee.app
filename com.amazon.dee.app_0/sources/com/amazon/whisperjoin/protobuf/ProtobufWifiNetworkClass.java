package com.amazon.whisperjoin.protobuf;

import com.amazon.whisperjoin.protobuf.ProtobufWifiKeyManagementClass;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.protobuf.AbstractMessage;
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
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
/* loaded from: classes13.dex */
public final class ProtobufWifiNetworkClass {
    private static Descriptors.FileDescriptor descriptor;
    private static final Descriptors.Descriptor internal_static_protobuf_ProtobufWifiNetwork_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_protobuf_ProtobufWifiNetwork_fieldAccessorTable;

    /* loaded from: classes13.dex */
    public static final class ProtobufWifiNetwork extends GeneratedMessageV3 implements ProtobufWifiNetworkOrBuilder {
        private static final ProtobufWifiNetwork DEFAULT_INSTANCE = new ProtobufWifiNetwork();
        @Deprecated
        public static final Parser<ProtobufWifiNetwork> PARSER = new AbstractParser<ProtobufWifiNetwork>() { // from class: com.amazon.whisperjoin.protobuf.ProtobufWifiNetworkClass.ProtobufWifiNetwork.1
            @Override // com.google.protobuf.Parser
            /* renamed from: parsePartialFrom */
            public ProtobufWifiNetwork mo10082parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new ProtobufWifiNetwork(codedInputStream, extensionRegistryLite);
            }
        };
        public static final int SSID_FIELD_NUMBER = 1;
        public static final int WIFIKEYMANAGEMENT_FIELD_NUMBER = 2;
        private static final long serialVersionUID = 0;
        private int bitField0_;
        private byte memoizedIsInitialized;
        private volatile Object ssid_;
        private int wifiKeyManagement_;

        public static ProtobufWifiNetwork getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return ProtobufWifiNetworkClass.internal_static_protobuf_ProtobufWifiNetwork_descriptor;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.mo10081toBuilder();
        }

        public static ProtobufWifiNetwork parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (ProtobufWifiNetwork) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static ProtobufWifiNetwork parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.mo8402parseFrom(byteBuffer);
        }

        public static Parser<ProtobufWifiNetwork> parser() {
            return PARSER;
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof ProtobufWifiNetwork)) {
                return super.equals(obj);
            }
            ProtobufWifiNetwork protobufWifiNetwork = (ProtobufWifiNetwork) obj;
            boolean z = hasSsid() == protobufWifiNetwork.hasSsid();
            if (hasSsid()) {
                z = z && getSsid().equals(protobufWifiNetwork.getSsid());
            }
            boolean z2 = z && hasWifiKeyManagement() == protobufWifiNetwork.hasWifiKeyManagement();
            if (hasWifiKeyManagement()) {
                z2 = z2 && this.wifiKeyManagement_ == protobufWifiNetwork.wifiKeyManagement_;
            }
            return z2 && this.unknownFields.equals(protobufWifiNetwork.unknownFields);
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
        /* renamed from: getParserForType */
        public Parser<ProtobufWifiNetwork> mo9954getParserForType() {
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
                i2 = 0 + GeneratedMessageV3.computeStringSize(1, this.ssid_);
            }
            if ((this.bitField0_ & 2) == 2) {
                i2 += CodedOutputStream.computeEnumSize(2, this.wifiKeyManagement_);
            }
            int serializedSize = this.unknownFields.getSerializedSize() + i2;
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.amazon.whisperjoin.protobuf.ProtobufWifiNetworkClass.ProtobufWifiNetworkOrBuilder
        public String getSsid() {
            Object obj = this.ssid_;
            if (obj instanceof String) {
                return (String) obj;
            }
            ByteString byteString = (ByteString) obj;
            String stringUtf8 = byteString.toStringUtf8();
            if (byteString.isValidUtf8()) {
                this.ssid_ = stringUtf8;
            }
            return stringUtf8;
        }

        @Override // com.amazon.whisperjoin.protobuf.ProtobufWifiNetworkClass.ProtobufWifiNetworkOrBuilder
        public ByteString getSsidBytes() {
            Object obj = this.ssid_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.ssid_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        @Override // com.amazon.whisperjoin.protobuf.ProtobufWifiNetworkClass.ProtobufWifiNetworkOrBuilder
        public ProtobufWifiKeyManagementClass.ProtobufWifiKeyManagement.WifiKeyManagement getWifiKeyManagement() {
            ProtobufWifiKeyManagementClass.ProtobufWifiKeyManagement.WifiKeyManagement valueOf = ProtobufWifiKeyManagementClass.ProtobufWifiKeyManagement.WifiKeyManagement.valueOf(this.wifiKeyManagement_);
            return valueOf == null ? ProtobufWifiKeyManagementClass.ProtobufWifiKeyManagement.WifiKeyManagement.NONE : valueOf;
        }

        @Override // com.amazon.whisperjoin.protobuf.ProtobufWifiNetworkClass.ProtobufWifiNetworkOrBuilder
        public boolean hasSsid() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // com.amazon.whisperjoin.protobuf.ProtobufWifiNetworkClass.ProtobufWifiNetworkOrBuilder
        public boolean hasWifiKeyManagement() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public int hashCode() {
            int i = this.memoizedHashCode;
            if (i != 0) {
                return i;
            }
            int hashCode = getDescriptor().hashCode() + 779;
            if (hasSsid()) {
                hashCode = GeneratedOutlineSupport1.outline4(hashCode, 37, 1, 53) + getSsid().hashCode();
            }
            if (hasWifiKeyManagement()) {
                hashCode = GeneratedOutlineSupport1.outline4(hashCode, 37, 2, 53) + this.wifiKeyManagement_;
            }
            int hashCode2 = this.unknownFields.hashCode() + (hashCode * 29);
            this.memoizedHashCode = hashCode2;
            return hashCode2;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return ProtobufWifiNetworkClass.internal_static_protobuf_ProtobufWifiNetwork_fieldAccessorTable.ensureFieldAccessorsInitialized(ProtobufWifiNetwork.class, Builder.class);
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
            if (!hasSsid()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            } else if (!hasWifiKeyManagement()) {
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
                GeneratedMessageV3.writeString(codedOutputStream, 1, this.ssid_);
            }
            if ((this.bitField0_ & 2) == 2) {
                codedOutputStream.writeEnum(2, this.wifiKeyManagement_);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        /* loaded from: classes13.dex */
        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements ProtobufWifiNetworkOrBuilder {
            private int bitField0_;
            private Object ssid_;
            private int wifiKeyManagement_;

            public static final Descriptors.Descriptor getDescriptor() {
                return ProtobufWifiNetworkClass.internal_static_protobuf_ProtobufWifiNetwork_descriptor;
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = GeneratedMessageV3.alwaysUseFieldBuilders;
            }

            public Builder clearSsid() {
                this.bitField0_ &= -2;
                this.ssid_ = ProtobufWifiNetwork.getDefaultInstance().getSsid();
                onChanged();
                return this;
            }

            public Builder clearWifiKeyManagement() {
                this.bitField0_ &= -3;
                this.wifiKeyManagement_ = 0;
                onChanged();
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return ProtobufWifiNetworkClass.internal_static_protobuf_ProtobufWifiNetwork_descriptor;
            }

            @Override // com.amazon.whisperjoin.protobuf.ProtobufWifiNetworkClass.ProtobufWifiNetworkOrBuilder
            public String getSsid() {
                Object obj = this.ssid_;
                if (!(obj instanceof String)) {
                    ByteString byteString = (ByteString) obj;
                    String stringUtf8 = byteString.toStringUtf8();
                    if (byteString.isValidUtf8()) {
                        this.ssid_ = stringUtf8;
                    }
                    return stringUtf8;
                }
                return (String) obj;
            }

            @Override // com.amazon.whisperjoin.protobuf.ProtobufWifiNetworkClass.ProtobufWifiNetworkOrBuilder
            public ByteString getSsidBytes() {
                Object obj = this.ssid_;
                if (obj instanceof String) {
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.ssid_ = copyFromUtf8;
                    return copyFromUtf8;
                }
                return (ByteString) obj;
            }

            @Override // com.amazon.whisperjoin.protobuf.ProtobufWifiNetworkClass.ProtobufWifiNetworkOrBuilder
            public ProtobufWifiKeyManagementClass.ProtobufWifiKeyManagement.WifiKeyManagement getWifiKeyManagement() {
                ProtobufWifiKeyManagementClass.ProtobufWifiKeyManagement.WifiKeyManagement valueOf = ProtobufWifiKeyManagementClass.ProtobufWifiKeyManagement.WifiKeyManagement.valueOf(this.wifiKeyManagement_);
                return valueOf == null ? ProtobufWifiKeyManagementClass.ProtobufWifiKeyManagement.WifiKeyManagement.NONE : valueOf;
            }

            @Override // com.amazon.whisperjoin.protobuf.ProtobufWifiNetworkClass.ProtobufWifiNetworkOrBuilder
            public boolean hasSsid() {
                return (this.bitField0_ & 1) == 1;
            }

            @Override // com.amazon.whisperjoin.protobuf.ProtobufWifiNetworkClass.ProtobufWifiNetworkOrBuilder
            public boolean hasWifiKeyManagement() {
                return (this.bitField0_ & 2) == 2;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return ProtobufWifiNetworkClass.internal_static_protobuf_ProtobufWifiNetwork_fieldAccessorTable.ensureFieldAccessorsInitialized(ProtobufWifiNetwork.class, Builder.class);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return hasSsid() && hasWifiKeyManagement();
            }

            public Builder setSsid(String str) {
                if (str != null) {
                    this.bitField0_ |= 1;
                    this.ssid_ = str;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder setSsidBytes(ByteString byteString) {
                if (byteString != null) {
                    this.bitField0_ |= 1;
                    this.ssid_ = byteString;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder setWifiKeyManagement(ProtobufWifiKeyManagementClass.ProtobufWifiKeyManagement.WifiKeyManagement wifiKeyManagement) {
                if (wifiKeyManagement != null) {
                    this.bitField0_ |= 2;
                    this.wifiKeyManagement_ = wifiKeyManagement.getNumber();
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            private Builder() {
                this.ssid_ = "";
                this.wifiKeyManagement_ = 0;
                maybeForceBuilderInitialization();
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            /* renamed from: addRepeatedField */
            public Builder mo10083addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.mo10083addRepeatedField(fieldDescriptor, obj);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: build */
            public ProtobufWifiNetwork mo10084build() {
                ProtobufWifiNetwork mo10085buildPartial = mo10085buildPartial();
                if (mo10085buildPartial.isInitialized()) {
                    return mo10085buildPartial;
                }
                throw AbstractMessage.Builder.newUninitializedMessageException((Message) mo10085buildPartial);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: buildPartial */
            public ProtobufWifiNetwork mo10085buildPartial() {
                ProtobufWifiNetwork protobufWifiNetwork = new ProtobufWifiNetwork(this);
                int i = this.bitField0_;
                int i2 = 1;
                if ((i & 1) != 1) {
                    i2 = 0;
                }
                protobufWifiNetwork.ssid_ = this.ssid_;
                if ((i & 2) == 2) {
                    i2 |= 2;
                }
                protobufWifiNetwork.wifiKeyManagement_ = this.wifiKeyManagement_;
                protobufWifiNetwork.bitField0_ = i2;
                onBuilt();
                return protobufWifiNetwork;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            /* renamed from: clearField */
            public Builder mo10088clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (Builder) super.mo10088clearField(fieldDescriptor);
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            /* renamed from: getDefaultInstanceForType */
            public ProtobufWifiNetwork mo10094getDefaultInstanceForType() {
                return ProtobufWifiNetwork.getDefaultInstance();
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
                this.ssid_ = "";
                this.bitField0_ &= -2;
                this.wifiKeyManagement_ = 0;
                this.bitField0_ &= -3;
                return this;
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.ssid_ = "";
                this.wifiKeyManagement_ = 0;
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
                if (message instanceof ProtobufWifiNetwork) {
                    return mergeFrom((ProtobufWifiNetwork) message);
                }
                super.mo10096mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(ProtobufWifiNetwork protobufWifiNetwork) {
                if (protobufWifiNetwork == ProtobufWifiNetwork.getDefaultInstance()) {
                    return this;
                }
                if (protobufWifiNetwork.hasSsid()) {
                    this.bitField0_ |= 1;
                    this.ssid_ = protobufWifiNetwork.ssid_;
                    onChanged();
                }
                if (protobufWifiNetwork.hasWifiKeyManagement()) {
                    setWifiKeyManagement(protobufWifiNetwork.getWifiKeyManagement());
                }
                mo10099mergeUnknownFields(((GeneratedMessageV3) protobufWifiNetwork).unknownFields);
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
            public com.amazon.whisperjoin.protobuf.ProtobufWifiNetworkClass.ProtobufWifiNetwork.Builder mo10097mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
                /*
                    r2 = this;
                    r0 = 0
                    com.google.protobuf.Parser<com.amazon.whisperjoin.protobuf.ProtobufWifiNetworkClass$ProtobufWifiNetwork> r1 = com.amazon.whisperjoin.protobuf.ProtobufWifiNetworkClass.ProtobufWifiNetwork.PARSER     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                    java.lang.Object r3 = r1.mo10082parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                    com.amazon.whisperjoin.protobuf.ProtobufWifiNetworkClass$ProtobufWifiNetwork r3 = (com.amazon.whisperjoin.protobuf.ProtobufWifiNetworkClass.ProtobufWifiNetwork) r3     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
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
                    com.amazon.whisperjoin.protobuf.ProtobufWifiNetworkClass$ProtobufWifiNetwork r4 = (com.amazon.whisperjoin.protobuf.ProtobufWifiNetworkClass.ProtobufWifiNetwork) r4     // Catch: java.lang.Throwable -> Lf
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
                throw new UnsupportedOperationException("Method not decompiled: com.amazon.whisperjoin.protobuf.ProtobufWifiNetworkClass.ProtobufWifiNetwork.Builder.mo10097mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.amazon.whisperjoin.protobuf.ProtobufWifiNetworkClass$ProtobufWifiNetwork$Builder");
            }
        }

        public static Builder newBuilder(ProtobufWifiNetwork protobufWifiNetwork) {
            return DEFAULT_INSTANCE.mo10081toBuilder().mergeFrom(protobufWifiNetwork);
        }

        public static ProtobufWifiNetwork parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.mo8403parseFrom(byteBuffer, extensionRegistryLite);
        }

        private ProtobufWifiNetwork(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        public static ProtobufWifiNetwork parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ProtobufWifiNetwork) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static ProtobufWifiNetwork parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.mo8396parseFrom(byteString);
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        /* renamed from: getDefaultInstanceForType */
        public ProtobufWifiNetwork mo10094getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        /* renamed from: toBuilder */
        public Builder mo10081toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        public static ProtobufWifiNetwork parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.mo8397parseFrom(byteString, extensionRegistryLite);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        /* renamed from: newBuilderForType */
        public Builder mo10079newBuilderForType() {
            return newBuilder();
        }

        private ProtobufWifiNetwork() {
            this.memoizedIsInitialized = (byte) -1;
            this.ssid_ = "";
            this.wifiKeyManagement_ = 0;
        }

        public static ProtobufWifiNetwork parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.mo8404parseFrom(bArr);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageV3
        /* renamed from: newBuilderForType */
        public Builder mo10080newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public static ProtobufWifiNetwork parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.mo8407parseFrom(bArr, extensionRegistryLite);
        }

        public static ProtobufWifiNetwork parseFrom(InputStream inputStream) throws IOException {
            return (ProtobufWifiNetwork) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        private ProtobufWifiNetwork(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                    ByteString readBytes = codedInputStream.readBytes();
                                    this.bitField0_ = 1 | this.bitField0_;
                                    this.ssid_ = readBytes;
                                } else if (readTag != 16) {
                                    if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                    }
                                } else {
                                    int readEnum = codedInputStream.readEnum();
                                    if (ProtobufWifiKeyManagementClass.ProtobufWifiKeyManagement.WifiKeyManagement.valueOf(readEnum) == null) {
                                        newBuilder.mergeVarintField(2, readEnum);
                                    } else {
                                        this.bitField0_ |= 2;
                                        this.wifiKeyManagement_ = readEnum;
                                    }
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

        public static ProtobufWifiNetwork parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ProtobufWifiNetwork) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static ProtobufWifiNetwork parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (ProtobufWifiNetwork) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static ProtobufWifiNetwork parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ProtobufWifiNetwork) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes13.dex */
    public interface ProtobufWifiNetworkOrBuilder extends MessageOrBuilder {
        String getSsid();

        ByteString getSsidBytes();

        ProtobufWifiKeyManagementClass.ProtobufWifiKeyManagement.WifiKeyManagement getWifiKeyManagement();

        boolean hasSsid();

        boolean hasWifiKeyManagement();
    }

    static {
        Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\nOWhisperJoinProtocolBuffersModel/schema/provisioning/data/wifi/WifiNetwork.proto\u0012\bprotobuf\u001aUWhisperJoinProtocolBuffersModel/schema/provisioning/data/wifi/WifiKeyManagement.proto\"u\n\u0013ProtobufWifiNetwork\u0012\f\n\u0004ssid\u0018\u0001 \u0002(\t\u0012P\n\u0011wifiKeyManagement\u0018\u0002 \u0002(\u000e25.protobuf.ProtobufWifiKeyManagement.WifiKeyManagementB;\n\u001fcom.amazon.whisperjoin.protobufB\u0018ProtobufWifiNetworkClass"}, new Descriptors.FileDescriptor[]{ProtobufWifiKeyManagementClass.getDescriptor()}, new Descriptors.FileDescriptor.InternalDescriptorAssigner() { // from class: com.amazon.whisperjoin.protobuf.ProtobufWifiNetworkClass.1
            @Override // com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner
            public ExtensionRegistry assignDescriptors(Descriptors.FileDescriptor fileDescriptor) {
                Descriptors.FileDescriptor unused = ProtobufWifiNetworkClass.descriptor = fileDescriptor;
                return null;
            }
        });
        internal_static_protobuf_ProtobufWifiNetwork_descriptor = getDescriptor().getMessageTypes().get(0);
        internal_static_protobuf_ProtobufWifiNetwork_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_protobuf_ProtobufWifiNetwork_descriptor, new String[]{"Ssid", "WifiKeyManagement"});
        ProtobufWifiKeyManagementClass.getDescriptor();
    }

    private ProtobufWifiNetworkClass() {
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
