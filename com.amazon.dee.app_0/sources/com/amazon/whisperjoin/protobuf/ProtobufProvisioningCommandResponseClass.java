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
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.Parser;
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
/* loaded from: classes13.dex */
public final class ProtobufProvisioningCommandResponseClass {
    private static Descriptors.FileDescriptor descriptor;
    private static final Descriptors.Descriptor internal_static_protobuf_ProtobufProvisioningCommandResponse_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_protobuf_ProtobufProvisioningCommandResponse_fieldAccessorTable;

    /* loaded from: classes13.dex */
    public static final class ProtobufProvisioningCommandResponse extends GeneratedMessageV3 implements ProtobufProvisioningCommandResponseOrBuilder {
        public static final int DATA_FIELD_NUMBER = 3;
        private static final ProtobufProvisioningCommandResponse DEFAULT_INSTANCE = new ProtobufProvisioningCommandResponse();
        @Deprecated
        public static final Parser<ProtobufProvisioningCommandResponse> PARSER = new AbstractParser<ProtobufProvisioningCommandResponse>() { // from class: com.amazon.whisperjoin.protobuf.ProtobufProvisioningCommandResponseClass.ProtobufProvisioningCommandResponse.1
            @Override // com.google.protobuf.Parser
            /* renamed from: parsePartialFrom */
            public ProtobufProvisioningCommandResponse mo10082parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new ProtobufProvisioningCommandResponse(codedInputStream, extensionRegistryLite);
            }
        };
        public static final int STATUS_FIELD_NUMBER = 2;
        public static final int UUID_FIELD_NUMBER = 1;
        private static final long serialVersionUID = 0;
        private int bitField0_;
        private ByteString data_;
        private byte memoizedIsInitialized;
        private int status_;
        private ByteString uuid_;

        public static ProtobufProvisioningCommandResponse getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return ProtobufProvisioningCommandResponseClass.internal_static_protobuf_ProtobufProvisioningCommandResponse_descriptor;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.mo10081toBuilder();
        }

        public static ProtobufProvisioningCommandResponse parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (ProtobufProvisioningCommandResponse) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static ProtobufProvisioningCommandResponse parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.mo8402parseFrom(byteBuffer);
        }

        public static Parser<ProtobufProvisioningCommandResponse> parser() {
            return PARSER;
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof ProtobufProvisioningCommandResponse)) {
                return super.equals(obj);
            }
            ProtobufProvisioningCommandResponse protobufProvisioningCommandResponse = (ProtobufProvisioningCommandResponse) obj;
            boolean z = hasUuid() == protobufProvisioningCommandResponse.hasUuid();
            if (hasUuid()) {
                z = z && getUuid().equals(protobufProvisioningCommandResponse.getUuid());
            }
            boolean z2 = z && hasStatus() == protobufProvisioningCommandResponse.hasStatus();
            if (hasStatus()) {
                z2 = z2 && getStatus() == protobufProvisioningCommandResponse.getStatus();
            }
            boolean z3 = z2 && hasData() == protobufProvisioningCommandResponse.hasData();
            if (hasData()) {
                z3 = z3 && getData().equals(protobufProvisioningCommandResponse.getData());
            }
            return z3 && this.unknownFields.equals(protobufProvisioningCommandResponse.unknownFields);
        }

        @Override // com.amazon.whisperjoin.protobuf.ProtobufProvisioningCommandResponseClass.ProtobufProvisioningCommandResponseOrBuilder
        public ByteString getData() {
            return this.data_;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
        /* renamed from: getParserForType */
        public Parser<ProtobufProvisioningCommandResponse> mo9954getParserForType() {
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
                i2 = 0 + CodedOutputStream.computeBytesSize(1, this.uuid_);
            }
            if ((this.bitField0_ & 2) == 2) {
                i2 += CodedOutputStream.computeSInt32Size(2, this.status_);
            }
            if ((this.bitField0_ & 4) == 4) {
                i2 += CodedOutputStream.computeBytesSize(3, this.data_);
            }
            int serializedSize = this.unknownFields.getSerializedSize() + i2;
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.amazon.whisperjoin.protobuf.ProtobufProvisioningCommandResponseClass.ProtobufProvisioningCommandResponseOrBuilder
        public int getStatus() {
            return this.status_;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        @Override // com.amazon.whisperjoin.protobuf.ProtobufProvisioningCommandResponseClass.ProtobufProvisioningCommandResponseOrBuilder
        public ByteString getUuid() {
            return this.uuid_;
        }

        @Override // com.amazon.whisperjoin.protobuf.ProtobufProvisioningCommandResponseClass.ProtobufProvisioningCommandResponseOrBuilder
        public boolean hasData() {
            return (this.bitField0_ & 4) == 4;
        }

        @Override // com.amazon.whisperjoin.protobuf.ProtobufProvisioningCommandResponseClass.ProtobufProvisioningCommandResponseOrBuilder
        public boolean hasStatus() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // com.amazon.whisperjoin.protobuf.ProtobufProvisioningCommandResponseClass.ProtobufProvisioningCommandResponseOrBuilder
        public boolean hasUuid() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public int hashCode() {
            int i = this.memoizedHashCode;
            if (i != 0) {
                return i;
            }
            int hashCode = getDescriptor().hashCode() + 779;
            if (hasUuid()) {
                hashCode = GeneratedOutlineSupport1.outline4(hashCode, 37, 1, 53) + getUuid().hashCode();
            }
            if (hasStatus()) {
                hashCode = GeneratedOutlineSupport1.outline4(hashCode, 37, 2, 53) + getStatus();
            }
            if (hasData()) {
                hashCode = GeneratedOutlineSupport1.outline4(hashCode, 37, 3, 53) + getData().hashCode();
            }
            int hashCode2 = this.unknownFields.hashCode() + (hashCode * 29);
            this.memoizedHashCode = hashCode2;
            return hashCode2;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return ProtobufProvisioningCommandResponseClass.internal_static_protobuf_ProtobufProvisioningCommandResponse_fieldAccessorTable.ensureFieldAccessorsInitialized(ProtobufProvisioningCommandResponse.class, Builder.class);
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
            if (!hasUuid()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            } else if (!hasStatus()) {
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
                codedOutputStream.writeBytes(1, this.uuid_);
            }
            if ((this.bitField0_ & 2) == 2) {
                codedOutputStream.writeSInt32(2, this.status_);
            }
            if ((this.bitField0_ & 4) == 4) {
                codedOutputStream.writeBytes(3, this.data_);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        /* loaded from: classes13.dex */
        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements ProtobufProvisioningCommandResponseOrBuilder {
            private int bitField0_;
            private ByteString data_;
            private int status_;
            private ByteString uuid_;

            public static final Descriptors.Descriptor getDescriptor() {
                return ProtobufProvisioningCommandResponseClass.internal_static_protobuf_ProtobufProvisioningCommandResponse_descriptor;
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = GeneratedMessageV3.alwaysUseFieldBuilders;
            }

            public Builder clearData() {
                this.bitField0_ &= -5;
                this.data_ = ProtobufProvisioningCommandResponse.getDefaultInstance().getData();
                onChanged();
                return this;
            }

            public Builder clearStatus() {
                this.bitField0_ &= -3;
                this.status_ = 0;
                onChanged();
                return this;
            }

            public Builder clearUuid() {
                this.bitField0_ &= -2;
                this.uuid_ = ProtobufProvisioningCommandResponse.getDefaultInstance().getUuid();
                onChanged();
                return this;
            }

            @Override // com.amazon.whisperjoin.protobuf.ProtobufProvisioningCommandResponseClass.ProtobufProvisioningCommandResponseOrBuilder
            public ByteString getData() {
                return this.data_;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return ProtobufProvisioningCommandResponseClass.internal_static_protobuf_ProtobufProvisioningCommandResponse_descriptor;
            }

            @Override // com.amazon.whisperjoin.protobuf.ProtobufProvisioningCommandResponseClass.ProtobufProvisioningCommandResponseOrBuilder
            public int getStatus() {
                return this.status_;
            }

            @Override // com.amazon.whisperjoin.protobuf.ProtobufProvisioningCommandResponseClass.ProtobufProvisioningCommandResponseOrBuilder
            public ByteString getUuid() {
                return this.uuid_;
            }

            @Override // com.amazon.whisperjoin.protobuf.ProtobufProvisioningCommandResponseClass.ProtobufProvisioningCommandResponseOrBuilder
            public boolean hasData() {
                return (this.bitField0_ & 4) == 4;
            }

            @Override // com.amazon.whisperjoin.protobuf.ProtobufProvisioningCommandResponseClass.ProtobufProvisioningCommandResponseOrBuilder
            public boolean hasStatus() {
                return (this.bitField0_ & 2) == 2;
            }

            @Override // com.amazon.whisperjoin.protobuf.ProtobufProvisioningCommandResponseClass.ProtobufProvisioningCommandResponseOrBuilder
            public boolean hasUuid() {
                return (this.bitField0_ & 1) == 1;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return ProtobufProvisioningCommandResponseClass.internal_static_protobuf_ProtobufProvisioningCommandResponse_fieldAccessorTable.ensureFieldAccessorsInitialized(ProtobufProvisioningCommandResponse.class, Builder.class);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return hasUuid() && hasStatus();
            }

            public Builder setData(ByteString byteString) {
                if (byteString != null) {
                    this.bitField0_ |= 4;
                    this.data_ = byteString;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder setStatus(int i) {
                this.bitField0_ |= 2;
                this.status_ = i;
                onChanged();
                return this;
            }

            public Builder setUuid(ByteString byteString) {
                if (byteString != null) {
                    this.bitField0_ |= 1;
                    this.uuid_ = byteString;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            private Builder() {
                ByteString byteString = ByteString.EMPTY;
                this.uuid_ = byteString;
                this.data_ = byteString;
                maybeForceBuilderInitialization();
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            /* renamed from: addRepeatedField */
            public Builder mo10083addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.mo10083addRepeatedField(fieldDescriptor, obj);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: build */
            public ProtobufProvisioningCommandResponse mo10084build() {
                ProtobufProvisioningCommandResponse mo10085buildPartial = mo10085buildPartial();
                if (mo10085buildPartial.isInitialized()) {
                    return mo10085buildPartial;
                }
                throw AbstractMessage.Builder.newUninitializedMessageException((Message) mo10085buildPartial);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: buildPartial */
            public ProtobufProvisioningCommandResponse mo10085buildPartial() {
                ProtobufProvisioningCommandResponse protobufProvisioningCommandResponse = new ProtobufProvisioningCommandResponse(this);
                int i = this.bitField0_;
                int i2 = 1;
                if ((i & 1) != 1) {
                    i2 = 0;
                }
                protobufProvisioningCommandResponse.uuid_ = this.uuid_;
                if ((i & 2) == 2) {
                    i2 |= 2;
                }
                protobufProvisioningCommandResponse.status_ = this.status_;
                if ((i & 4) == 4) {
                    i2 |= 4;
                }
                protobufProvisioningCommandResponse.data_ = this.data_;
                protobufProvisioningCommandResponse.bitField0_ = i2;
                onBuilt();
                return protobufProvisioningCommandResponse;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            /* renamed from: clearField */
            public Builder mo10088clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (Builder) super.mo10088clearField(fieldDescriptor);
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            /* renamed from: getDefaultInstanceForType */
            public ProtobufProvisioningCommandResponse mo10094getDefaultInstanceForType() {
                return ProtobufProvisioningCommandResponse.getDefaultInstance();
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
                this.uuid_ = byteString;
                this.bitField0_ &= -2;
                this.status_ = 0;
                this.bitField0_ &= -3;
                this.data_ = byteString;
                this.bitField0_ &= -5;
                return this;
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                ByteString byteString = ByteString.EMPTY;
                this.uuid_ = byteString;
                this.data_ = byteString;
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
                if (message instanceof ProtobufProvisioningCommandResponse) {
                    return mergeFrom((ProtobufProvisioningCommandResponse) message);
                }
                super.mo10096mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(ProtobufProvisioningCommandResponse protobufProvisioningCommandResponse) {
                if (protobufProvisioningCommandResponse == ProtobufProvisioningCommandResponse.getDefaultInstance()) {
                    return this;
                }
                if (protobufProvisioningCommandResponse.hasUuid()) {
                    setUuid(protobufProvisioningCommandResponse.getUuid());
                }
                if (protobufProvisioningCommandResponse.hasStatus()) {
                    setStatus(protobufProvisioningCommandResponse.getStatus());
                }
                if (protobufProvisioningCommandResponse.hasData()) {
                    setData(protobufProvisioningCommandResponse.getData());
                }
                mo10099mergeUnknownFields(((GeneratedMessageV3) protobufProvisioningCommandResponse).unknownFields);
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
            public com.amazon.whisperjoin.protobuf.ProtobufProvisioningCommandResponseClass.ProtobufProvisioningCommandResponse.Builder mo10097mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
                /*
                    r2 = this;
                    r0 = 0
                    com.google.protobuf.Parser<com.amazon.whisperjoin.protobuf.ProtobufProvisioningCommandResponseClass$ProtobufProvisioningCommandResponse> r1 = com.amazon.whisperjoin.protobuf.ProtobufProvisioningCommandResponseClass.ProtobufProvisioningCommandResponse.PARSER     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                    java.lang.Object r3 = r1.mo10082parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                    com.amazon.whisperjoin.protobuf.ProtobufProvisioningCommandResponseClass$ProtobufProvisioningCommandResponse r3 = (com.amazon.whisperjoin.protobuf.ProtobufProvisioningCommandResponseClass.ProtobufProvisioningCommandResponse) r3     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
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
                    com.amazon.whisperjoin.protobuf.ProtobufProvisioningCommandResponseClass$ProtobufProvisioningCommandResponse r4 = (com.amazon.whisperjoin.protobuf.ProtobufProvisioningCommandResponseClass.ProtobufProvisioningCommandResponse) r4     // Catch: java.lang.Throwable -> Lf
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
                throw new UnsupportedOperationException("Method not decompiled: com.amazon.whisperjoin.protobuf.ProtobufProvisioningCommandResponseClass.ProtobufProvisioningCommandResponse.Builder.mo10097mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.amazon.whisperjoin.protobuf.ProtobufProvisioningCommandResponseClass$ProtobufProvisioningCommandResponse$Builder");
            }
        }

        public static Builder newBuilder(ProtobufProvisioningCommandResponse protobufProvisioningCommandResponse) {
            return DEFAULT_INSTANCE.mo10081toBuilder().mergeFrom(protobufProvisioningCommandResponse);
        }

        public static ProtobufProvisioningCommandResponse parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.mo8403parseFrom(byteBuffer, extensionRegistryLite);
        }

        private ProtobufProvisioningCommandResponse(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        public static ProtobufProvisioningCommandResponse parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ProtobufProvisioningCommandResponse) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static ProtobufProvisioningCommandResponse parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.mo8396parseFrom(byteString);
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        /* renamed from: getDefaultInstanceForType */
        public ProtobufProvisioningCommandResponse mo10094getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        /* renamed from: toBuilder */
        public Builder mo10081toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        public static ProtobufProvisioningCommandResponse parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.mo8397parseFrom(byteString, extensionRegistryLite);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        /* renamed from: newBuilderForType */
        public Builder mo10079newBuilderForType() {
            return newBuilder();
        }

        private ProtobufProvisioningCommandResponse() {
            this.memoizedIsInitialized = (byte) -1;
            ByteString byteString = ByteString.EMPTY;
            this.uuid_ = byteString;
            this.status_ = 0;
            this.data_ = byteString;
        }

        public static ProtobufProvisioningCommandResponse parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.mo8404parseFrom(bArr);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageV3
        /* renamed from: newBuilderForType */
        public Builder mo10080newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public static ProtobufProvisioningCommandResponse parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.mo8407parseFrom(bArr, extensionRegistryLite);
        }

        public static ProtobufProvisioningCommandResponse parseFrom(InputStream inputStream) throws IOException {
            return (ProtobufProvisioningCommandResponse) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static ProtobufProvisioningCommandResponse parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ProtobufProvisioningCommandResponse) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        private ProtobufProvisioningCommandResponse(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                    this.uuid_ = codedInputStream.readBytes();
                                } else if (readTag == 16) {
                                    this.bitField0_ |= 2;
                                    this.status_ = codedInputStream.readSInt32();
                                } else if (readTag != 26) {
                                    if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                    }
                                } else {
                                    this.bitField0_ |= 4;
                                    this.data_ = codedInputStream.readBytes();
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

        public static ProtobufProvisioningCommandResponse parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (ProtobufProvisioningCommandResponse) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static ProtobufProvisioningCommandResponse parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ProtobufProvisioningCommandResponse) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes13.dex */
    public interface ProtobufProvisioningCommandResponseOrBuilder extends MessageOrBuilder {
        ByteString getData();

        int getStatus();

        ByteString getUuid();

        boolean hasData();

        boolean hasStatus();

        boolean hasUuid();
    }

    static {
        Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\nUWhisperJoinProtocolBuffersModel/schema/ble/commands/ProvisioningCommandResponse.proto\u0012\bprotobuf\"Q\n#ProtobufProvisioningCommandResponse\u0012\f\n\u0004uuid\u0018\u0001 \u0002(\f\u0012\u000e\n\u0006status\u0018\u0002 \u0002(\u0011\u0012\f\n\u0004data\u0018\u0003 \u0001(\fBK\n\u001fcom.amazon.whisperjoin.protobufB(ProtobufProvisioningCommandResponseClass"}, new Descriptors.FileDescriptor[0], new Descriptors.FileDescriptor.InternalDescriptorAssigner() { // from class: com.amazon.whisperjoin.protobuf.ProtobufProvisioningCommandResponseClass.1
            @Override // com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner
            public ExtensionRegistry assignDescriptors(Descriptors.FileDescriptor fileDescriptor) {
                Descriptors.FileDescriptor unused = ProtobufProvisioningCommandResponseClass.descriptor = fileDescriptor;
                return null;
            }
        });
        internal_static_protobuf_ProtobufProvisioningCommandResponse_descriptor = getDescriptor().getMessageTypes().get(0);
        internal_static_protobuf_ProtobufProvisioningCommandResponse_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_protobuf_ProtobufProvisioningCommandResponse_descriptor, new String[]{"Uuid", "Status", "Data"});
    }

    private ProtobufProvisioningCommandResponseClass() {
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
