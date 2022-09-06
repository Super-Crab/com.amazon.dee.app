package com.amazon.whisperjoin.protobuf;

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
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.Parser;
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
/* loaded from: classes13.dex */
public final class ProtobufCBLRegistrationRequestClass {
    private static Descriptors.FileDescriptor descriptor;
    private static final Descriptors.Descriptor internal_static_protobuf_ProtobufCBLRegistrationRequest_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_protobuf_ProtobufCBLRegistrationRequest_fieldAccessorTable;

    /* loaded from: classes13.dex */
    public static final class ProtobufCBLRegistrationRequest extends GeneratedMessageV3 implements ProtobufCBLRegistrationRequestOrBuilder {
        public static final int EXPIRATION_FIELD_NUMBER = 2;
        public static final int LINKCODE_FIELD_NUMBER = 1;
        private static final long serialVersionUID = 0;
        private int bitField0_;
        private long expiration_;
        private volatile Object linkCode_;
        private byte memoizedIsInitialized;
        private static final ProtobufCBLRegistrationRequest DEFAULT_INSTANCE = new ProtobufCBLRegistrationRequest();
        @Deprecated
        public static final Parser<ProtobufCBLRegistrationRequest> PARSER = new AbstractParser<ProtobufCBLRegistrationRequest>() { // from class: com.amazon.whisperjoin.protobuf.ProtobufCBLRegistrationRequestClass.ProtobufCBLRegistrationRequest.1
            @Override // com.google.protobuf.Parser
            /* renamed from: parsePartialFrom */
            public ProtobufCBLRegistrationRequest mo10082parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new ProtobufCBLRegistrationRequest(codedInputStream, extensionRegistryLite);
            }
        };

        public static ProtobufCBLRegistrationRequest getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return ProtobufCBLRegistrationRequestClass.internal_static_protobuf_ProtobufCBLRegistrationRequest_descriptor;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.mo10081toBuilder();
        }

        public static ProtobufCBLRegistrationRequest parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (ProtobufCBLRegistrationRequest) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static ProtobufCBLRegistrationRequest parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.mo8402parseFrom(byteBuffer);
        }

        public static Parser<ProtobufCBLRegistrationRequest> parser() {
            return PARSER;
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof ProtobufCBLRegistrationRequest)) {
                return super.equals(obj);
            }
            ProtobufCBLRegistrationRequest protobufCBLRegistrationRequest = (ProtobufCBLRegistrationRequest) obj;
            boolean z = hasLinkCode() == protobufCBLRegistrationRequest.hasLinkCode();
            if (hasLinkCode()) {
                z = z && getLinkCode().equals(protobufCBLRegistrationRequest.getLinkCode());
            }
            boolean z2 = z && hasExpiration() == protobufCBLRegistrationRequest.hasExpiration();
            if (hasExpiration()) {
                z2 = z2 && getExpiration() == protobufCBLRegistrationRequest.getExpiration();
            }
            return z2 && this.unknownFields.equals(protobufCBLRegistrationRequest.unknownFields);
        }

        @Override // com.amazon.whisperjoin.protobuf.ProtobufCBLRegistrationRequestClass.ProtobufCBLRegistrationRequestOrBuilder
        public long getExpiration() {
            return this.expiration_;
        }

        @Override // com.amazon.whisperjoin.protobuf.ProtobufCBLRegistrationRequestClass.ProtobufCBLRegistrationRequestOrBuilder
        public String getLinkCode() {
            Object obj = this.linkCode_;
            if (obj instanceof String) {
                return (String) obj;
            }
            ByteString byteString = (ByteString) obj;
            String stringUtf8 = byteString.toStringUtf8();
            if (byteString.isValidUtf8()) {
                this.linkCode_ = stringUtf8;
            }
            return stringUtf8;
        }

        @Override // com.amazon.whisperjoin.protobuf.ProtobufCBLRegistrationRequestClass.ProtobufCBLRegistrationRequestOrBuilder
        public ByteString getLinkCodeBytes() {
            Object obj = this.linkCode_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.linkCode_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
        /* renamed from: getParserForType */
        public Parser<ProtobufCBLRegistrationRequest> mo9954getParserForType() {
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
                i2 = 0 + GeneratedMessageV3.computeStringSize(1, this.linkCode_);
            }
            if ((this.bitField0_ & 2) == 2) {
                i2 += CodedOutputStream.computeSInt64Size(2, this.expiration_);
            }
            int serializedSize = this.unknownFields.getSerializedSize() + i2;
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        @Override // com.amazon.whisperjoin.protobuf.ProtobufCBLRegistrationRequestClass.ProtobufCBLRegistrationRequestOrBuilder
        public boolean hasExpiration() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // com.amazon.whisperjoin.protobuf.ProtobufCBLRegistrationRequestClass.ProtobufCBLRegistrationRequestOrBuilder
        public boolean hasLinkCode() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public int hashCode() {
            int i = this.memoizedHashCode;
            if (i != 0) {
                return i;
            }
            int hashCode = getDescriptor().hashCode() + 779;
            if (hasLinkCode()) {
                hashCode = GeneratedOutlineSupport1.outline4(hashCode, 37, 1, 53) + getLinkCode().hashCode();
            }
            if (hasExpiration()) {
                hashCode = GeneratedOutlineSupport1.outline4(hashCode, 37, 2, 53) + Internal.hashLong(getExpiration());
            }
            int hashCode2 = this.unknownFields.hashCode() + (hashCode * 29);
            this.memoizedHashCode = hashCode2;
            return hashCode2;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return ProtobufCBLRegistrationRequestClass.internal_static_protobuf_ProtobufCBLRegistrationRequest_fieldAccessorTable.ensureFieldAccessorsInitialized(ProtobufCBLRegistrationRequest.class, Builder.class);
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
            if (!hasLinkCode()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            } else if (!hasExpiration()) {
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
                GeneratedMessageV3.writeString(codedOutputStream, 1, this.linkCode_);
            }
            if ((this.bitField0_ & 2) == 2) {
                codedOutputStream.writeSInt64(2, this.expiration_);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        /* loaded from: classes13.dex */
        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements ProtobufCBLRegistrationRequestOrBuilder {
            private int bitField0_;
            private long expiration_;
            private Object linkCode_;

            public static final Descriptors.Descriptor getDescriptor() {
                return ProtobufCBLRegistrationRequestClass.internal_static_protobuf_ProtobufCBLRegistrationRequest_descriptor;
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = GeneratedMessageV3.alwaysUseFieldBuilders;
            }

            public Builder clearExpiration() {
                this.bitField0_ &= -3;
                this.expiration_ = 0L;
                onChanged();
                return this;
            }

            public Builder clearLinkCode() {
                this.bitField0_ &= -2;
                this.linkCode_ = ProtobufCBLRegistrationRequest.getDefaultInstance().getLinkCode();
                onChanged();
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return ProtobufCBLRegistrationRequestClass.internal_static_protobuf_ProtobufCBLRegistrationRequest_descriptor;
            }

            @Override // com.amazon.whisperjoin.protobuf.ProtobufCBLRegistrationRequestClass.ProtobufCBLRegistrationRequestOrBuilder
            public long getExpiration() {
                return this.expiration_;
            }

            @Override // com.amazon.whisperjoin.protobuf.ProtobufCBLRegistrationRequestClass.ProtobufCBLRegistrationRequestOrBuilder
            public String getLinkCode() {
                Object obj = this.linkCode_;
                if (!(obj instanceof String)) {
                    ByteString byteString = (ByteString) obj;
                    String stringUtf8 = byteString.toStringUtf8();
                    if (byteString.isValidUtf8()) {
                        this.linkCode_ = stringUtf8;
                    }
                    return stringUtf8;
                }
                return (String) obj;
            }

            @Override // com.amazon.whisperjoin.protobuf.ProtobufCBLRegistrationRequestClass.ProtobufCBLRegistrationRequestOrBuilder
            public ByteString getLinkCodeBytes() {
                Object obj = this.linkCode_;
                if (obj instanceof String) {
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.linkCode_ = copyFromUtf8;
                    return copyFromUtf8;
                }
                return (ByteString) obj;
            }

            @Override // com.amazon.whisperjoin.protobuf.ProtobufCBLRegistrationRequestClass.ProtobufCBLRegistrationRequestOrBuilder
            public boolean hasExpiration() {
                return (this.bitField0_ & 2) == 2;
            }

            @Override // com.amazon.whisperjoin.protobuf.ProtobufCBLRegistrationRequestClass.ProtobufCBLRegistrationRequestOrBuilder
            public boolean hasLinkCode() {
                return (this.bitField0_ & 1) == 1;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return ProtobufCBLRegistrationRequestClass.internal_static_protobuf_ProtobufCBLRegistrationRequest_fieldAccessorTable.ensureFieldAccessorsInitialized(ProtobufCBLRegistrationRequest.class, Builder.class);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return hasLinkCode() && hasExpiration();
            }

            public Builder setExpiration(long j) {
                this.bitField0_ |= 2;
                this.expiration_ = j;
                onChanged();
                return this;
            }

            public Builder setLinkCode(String str) {
                if (str != null) {
                    this.bitField0_ |= 1;
                    this.linkCode_ = str;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder setLinkCodeBytes(ByteString byteString) {
                if (byteString != null) {
                    this.bitField0_ |= 1;
                    this.linkCode_ = byteString;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            private Builder() {
                this.linkCode_ = "";
                maybeForceBuilderInitialization();
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            /* renamed from: addRepeatedField */
            public Builder mo10083addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.mo10083addRepeatedField(fieldDescriptor, obj);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: build */
            public ProtobufCBLRegistrationRequest mo10084build() {
                ProtobufCBLRegistrationRequest mo10085buildPartial = mo10085buildPartial();
                if (mo10085buildPartial.isInitialized()) {
                    return mo10085buildPartial;
                }
                throw AbstractMessage.Builder.newUninitializedMessageException((Message) mo10085buildPartial);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: buildPartial */
            public ProtobufCBLRegistrationRequest mo10085buildPartial() {
                ProtobufCBLRegistrationRequest protobufCBLRegistrationRequest = new ProtobufCBLRegistrationRequest(this);
                int i = this.bitField0_;
                int i2 = 1;
                if ((i & 1) != 1) {
                    i2 = 0;
                }
                protobufCBLRegistrationRequest.linkCode_ = this.linkCode_;
                if ((i & 2) == 2) {
                    i2 |= 2;
                }
                protobufCBLRegistrationRequest.expiration_ = this.expiration_;
                protobufCBLRegistrationRequest.bitField0_ = i2;
                onBuilt();
                return protobufCBLRegistrationRequest;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            /* renamed from: clearField */
            public Builder mo10088clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (Builder) super.mo10088clearField(fieldDescriptor);
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            /* renamed from: getDefaultInstanceForType */
            public ProtobufCBLRegistrationRequest mo10094getDefaultInstanceForType() {
                return ProtobufCBLRegistrationRequest.getDefaultInstance();
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
                this.linkCode_ = "";
                this.bitField0_ &= -2;
                this.expiration_ = 0L;
                this.bitField0_ &= -3;
                return this;
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.linkCode_ = "";
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
                if (message instanceof ProtobufCBLRegistrationRequest) {
                    return mergeFrom((ProtobufCBLRegistrationRequest) message);
                }
                super.mo10096mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(ProtobufCBLRegistrationRequest protobufCBLRegistrationRequest) {
                if (protobufCBLRegistrationRequest == ProtobufCBLRegistrationRequest.getDefaultInstance()) {
                    return this;
                }
                if (protobufCBLRegistrationRequest.hasLinkCode()) {
                    this.bitField0_ |= 1;
                    this.linkCode_ = protobufCBLRegistrationRequest.linkCode_;
                    onChanged();
                }
                if (protobufCBLRegistrationRequest.hasExpiration()) {
                    setExpiration(protobufCBLRegistrationRequest.getExpiration());
                }
                mo10099mergeUnknownFields(((GeneratedMessageV3) protobufCBLRegistrationRequest).unknownFields);
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
            public com.amazon.whisperjoin.protobuf.ProtobufCBLRegistrationRequestClass.ProtobufCBLRegistrationRequest.Builder mo10097mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
                /*
                    r2 = this;
                    r0 = 0
                    com.google.protobuf.Parser<com.amazon.whisperjoin.protobuf.ProtobufCBLRegistrationRequestClass$ProtobufCBLRegistrationRequest> r1 = com.amazon.whisperjoin.protobuf.ProtobufCBLRegistrationRequestClass.ProtobufCBLRegistrationRequest.PARSER     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                    java.lang.Object r3 = r1.mo10082parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                    com.amazon.whisperjoin.protobuf.ProtobufCBLRegistrationRequestClass$ProtobufCBLRegistrationRequest r3 = (com.amazon.whisperjoin.protobuf.ProtobufCBLRegistrationRequestClass.ProtobufCBLRegistrationRequest) r3     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
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
                    com.amazon.whisperjoin.protobuf.ProtobufCBLRegistrationRequestClass$ProtobufCBLRegistrationRequest r4 = (com.amazon.whisperjoin.protobuf.ProtobufCBLRegistrationRequestClass.ProtobufCBLRegistrationRequest) r4     // Catch: java.lang.Throwable -> Lf
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
                throw new UnsupportedOperationException("Method not decompiled: com.amazon.whisperjoin.protobuf.ProtobufCBLRegistrationRequestClass.ProtobufCBLRegistrationRequest.Builder.mo10097mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.amazon.whisperjoin.protobuf.ProtobufCBLRegistrationRequestClass$ProtobufCBLRegistrationRequest$Builder");
            }
        }

        public static Builder newBuilder(ProtobufCBLRegistrationRequest protobufCBLRegistrationRequest) {
            return DEFAULT_INSTANCE.mo10081toBuilder().mergeFrom(protobufCBLRegistrationRequest);
        }

        public static ProtobufCBLRegistrationRequest parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.mo8403parseFrom(byteBuffer, extensionRegistryLite);
        }

        private ProtobufCBLRegistrationRequest(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        public static ProtobufCBLRegistrationRequest parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ProtobufCBLRegistrationRequest) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static ProtobufCBLRegistrationRequest parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.mo8396parseFrom(byteString);
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        /* renamed from: getDefaultInstanceForType */
        public ProtobufCBLRegistrationRequest mo10094getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        /* renamed from: toBuilder */
        public Builder mo10081toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        public static ProtobufCBLRegistrationRequest parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.mo8397parseFrom(byteString, extensionRegistryLite);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        /* renamed from: newBuilderForType */
        public Builder mo10079newBuilderForType() {
            return newBuilder();
        }

        private ProtobufCBLRegistrationRequest() {
            this.memoizedIsInitialized = (byte) -1;
            this.linkCode_ = "";
            this.expiration_ = 0L;
        }

        public static ProtobufCBLRegistrationRequest parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.mo8404parseFrom(bArr);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageV3
        /* renamed from: newBuilderForType */
        public Builder mo10080newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public static ProtobufCBLRegistrationRequest parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.mo8407parseFrom(bArr, extensionRegistryLite);
        }

        public static ProtobufCBLRegistrationRequest parseFrom(InputStream inputStream) throws IOException {
            return (ProtobufCBLRegistrationRequest) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        private ProtobufCBLRegistrationRequest(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                    this.linkCode_ = readBytes;
                                } else if (readTag != 16) {
                                    if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                    }
                                } else {
                                    this.bitField0_ |= 2;
                                    this.expiration_ = codedInputStream.readSInt64();
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

        public static ProtobufCBLRegistrationRequest parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ProtobufCBLRegistrationRequest) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static ProtobufCBLRegistrationRequest parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (ProtobufCBLRegistrationRequest) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static ProtobufCBLRegistrationRequest parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ProtobufCBLRegistrationRequest) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes13.dex */
    public interface ProtobufCBLRegistrationRequestOrBuilder extends MessageOrBuilder {
        long getExpiration();

        String getLinkCode();

        ByteString getLinkCodeBytes();

        boolean hasExpiration();

        boolean hasLinkCode();
    }

    static {
        Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\nbWhisperJoinProtocolBuffersModel/schema/provisioning/data/registration/CBLRegistrationRequest.proto\u0012\bprotobuf\"F\n\u001eProtobufCBLRegistrationRequest\u0012\u0010\n\blinkCode\u0018\u0001 \u0002(\t\u0012\u0012\n\nexpiration\u0018\u0002 \u0002(\u0012BF\n\u001fcom.amazon.whisperjoin.protobufB#ProtobufCBLRegistrationRequestClass"}, new Descriptors.FileDescriptor[0], new Descriptors.FileDescriptor.InternalDescriptorAssigner() { // from class: com.amazon.whisperjoin.protobuf.ProtobufCBLRegistrationRequestClass.1
            @Override // com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner
            public ExtensionRegistry assignDescriptors(Descriptors.FileDescriptor fileDescriptor) {
                Descriptors.FileDescriptor unused = ProtobufCBLRegistrationRequestClass.descriptor = fileDescriptor;
                return null;
            }
        });
        internal_static_protobuf_ProtobufCBLRegistrationRequest_descriptor = getDescriptor().getMessageTypes().get(0);
        internal_static_protobuf_ProtobufCBLRegistrationRequest_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_protobuf_ProtobufCBLRegistrationRequest_descriptor, new String[]{"LinkCode", "Expiration"});
    }

    private ProtobufCBLRegistrationRequestClass() {
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
