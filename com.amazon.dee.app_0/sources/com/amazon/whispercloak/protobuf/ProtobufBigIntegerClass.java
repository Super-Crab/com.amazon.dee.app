package com.amazon.whispercloak.protobuf;

import com.amazon.identity.auth.map.device.token.MAPCookie;
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
public final class ProtobufBigIntegerClass {
    private static Descriptors.FileDescriptor descriptor;
    private static final Descriptors.Descriptor internal_static_protobuf_ProtobufBigInteger_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_protobuf_ProtobufBigInteger_fieldAccessorTable;

    /* loaded from: classes13.dex */
    public static final class ProtobufBigInteger extends GeneratedMessageV3 implements ProtobufBigIntegerOrBuilder {
        private static final ProtobufBigInteger DEFAULT_INSTANCE = new ProtobufBigInteger();
        @Deprecated
        public static final Parser<ProtobufBigInteger> PARSER = new AbstractParser<ProtobufBigInteger>() { // from class: com.amazon.whispercloak.protobuf.ProtobufBigIntegerClass.ProtobufBigInteger.1
            @Override // com.google.protobuf.Parser
            /* renamed from: parsePartialFrom */
            public ProtobufBigInteger mo10082parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new ProtobufBigInteger(codedInputStream, extensionRegistryLite);
            }
        };
        public static final int VALUE_FIELD_NUMBER = 1;
        private static final long serialVersionUID = 0;
        private int bitField0_;
        private byte memoizedIsInitialized;
        private ByteString value_;

        public static ProtobufBigInteger getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return ProtobufBigIntegerClass.internal_static_protobuf_ProtobufBigInteger_descriptor;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.mo10081toBuilder();
        }

        public static ProtobufBigInteger parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (ProtobufBigInteger) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static ProtobufBigInteger parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.mo8402parseFrom(byteBuffer);
        }

        public static Parser<ProtobufBigInteger> parser() {
            return PARSER;
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof ProtobufBigInteger)) {
                return super.equals(obj);
            }
            ProtobufBigInteger protobufBigInteger = (ProtobufBigInteger) obj;
            boolean z = hasValue() == protobufBigInteger.hasValue();
            if (hasValue()) {
                z = z && getValue().equals(protobufBigInteger.getValue());
            }
            return z && this.unknownFields.equals(protobufBigInteger.unknownFields);
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
        /* renamed from: getParserForType */
        public Parser<ProtobufBigInteger> mo9954getParserForType() {
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
                i2 = 0 + CodedOutputStream.computeBytesSize(1, this.value_);
            }
            int serializedSize = this.unknownFields.getSerializedSize() + i2;
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        @Override // com.amazon.whispercloak.protobuf.ProtobufBigIntegerClass.ProtobufBigIntegerOrBuilder
        public ByteString getValue() {
            return this.value_;
        }

        @Override // com.amazon.whispercloak.protobuf.ProtobufBigIntegerClass.ProtobufBigIntegerOrBuilder
        public boolean hasValue() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public int hashCode() {
            int i = this.memoizedHashCode;
            if (i != 0) {
                return i;
            }
            int hashCode = getDescriptor().hashCode() + 779;
            if (hasValue()) {
                hashCode = GeneratedOutlineSupport1.outline4(hashCode, 37, 1, 53) + getValue().hashCode();
            }
            int hashCode2 = this.unknownFields.hashCode() + (hashCode * 29);
            this.memoizedHashCode = hashCode2;
            return hashCode2;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return ProtobufBigIntegerClass.internal_static_protobuf_ProtobufBigInteger_fieldAccessorTable.ensureFieldAccessorsInitialized(ProtobufBigInteger.class, Builder.class);
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
            if (!hasValue()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            }
            this.memoizedIsInitialized = (byte) 1;
            return true;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                codedOutputStream.writeBytes(1, this.value_);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        /* loaded from: classes13.dex */
        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements ProtobufBigIntegerOrBuilder {
            private int bitField0_;
            private ByteString value_;

            public static final Descriptors.Descriptor getDescriptor() {
                return ProtobufBigIntegerClass.internal_static_protobuf_ProtobufBigInteger_descriptor;
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = GeneratedMessageV3.alwaysUseFieldBuilders;
            }

            public Builder clearValue() {
                this.bitField0_ &= -2;
                this.value_ = ProtobufBigInteger.getDefaultInstance().getValue();
                onChanged();
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return ProtobufBigIntegerClass.internal_static_protobuf_ProtobufBigInteger_descriptor;
            }

            @Override // com.amazon.whispercloak.protobuf.ProtobufBigIntegerClass.ProtobufBigIntegerOrBuilder
            public ByteString getValue() {
                return this.value_;
            }

            @Override // com.amazon.whispercloak.protobuf.ProtobufBigIntegerClass.ProtobufBigIntegerOrBuilder
            public boolean hasValue() {
                return (this.bitField0_ & 1) == 1;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return ProtobufBigIntegerClass.internal_static_protobuf_ProtobufBigInteger_fieldAccessorTable.ensureFieldAccessorsInitialized(ProtobufBigInteger.class, Builder.class);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return hasValue();
            }

            public Builder setValue(ByteString byteString) {
                if (byteString != null) {
                    this.bitField0_ |= 1;
                    this.value_ = byteString;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            private Builder() {
                this.value_ = ByteString.EMPTY;
                maybeForceBuilderInitialization();
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            /* renamed from: addRepeatedField */
            public Builder mo10083addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.mo10083addRepeatedField(fieldDescriptor, obj);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: build */
            public ProtobufBigInteger mo10084build() {
                ProtobufBigInteger mo10085buildPartial = mo10085buildPartial();
                if (mo10085buildPartial.isInitialized()) {
                    return mo10085buildPartial;
                }
                throw AbstractMessage.Builder.newUninitializedMessageException((Message) mo10085buildPartial);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: buildPartial */
            public ProtobufBigInteger mo10085buildPartial() {
                ProtobufBigInteger protobufBigInteger = new ProtobufBigInteger(this);
                int i = 1;
                if ((this.bitField0_ & 1) != 1) {
                    i = 0;
                }
                protobufBigInteger.value_ = this.value_;
                protobufBigInteger.bitField0_ = i;
                onBuilt();
                return protobufBigInteger;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            /* renamed from: clearField */
            public Builder mo10088clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (Builder) super.mo10088clearField(fieldDescriptor);
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            /* renamed from: getDefaultInstanceForType */
            public ProtobufBigInteger mo10094getDefaultInstanceForType() {
                return ProtobufBigInteger.getDefaultInstance();
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
                this.value_ = ByteString.EMPTY;
                this.bitField0_ &= -2;
                return this;
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.value_ = ByteString.EMPTY;
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
                if (message instanceof ProtobufBigInteger) {
                    return mergeFrom((ProtobufBigInteger) message);
                }
                super.mo10096mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(ProtobufBigInteger protobufBigInteger) {
                if (protobufBigInteger == ProtobufBigInteger.getDefaultInstance()) {
                    return this;
                }
                if (protobufBigInteger.hasValue()) {
                    setValue(protobufBigInteger.getValue());
                }
                mo10099mergeUnknownFields(((GeneratedMessageV3) protobufBigInteger).unknownFields);
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
            public com.amazon.whispercloak.protobuf.ProtobufBigIntegerClass.ProtobufBigInteger.Builder mo10097mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
                /*
                    r2 = this;
                    r0 = 0
                    com.google.protobuf.Parser<com.amazon.whispercloak.protobuf.ProtobufBigIntegerClass$ProtobufBigInteger> r1 = com.amazon.whispercloak.protobuf.ProtobufBigIntegerClass.ProtobufBigInteger.PARSER     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                    java.lang.Object r3 = r1.mo10082parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                    com.amazon.whispercloak.protobuf.ProtobufBigIntegerClass$ProtobufBigInteger r3 = (com.amazon.whispercloak.protobuf.ProtobufBigIntegerClass.ProtobufBigInteger) r3     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
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
                    com.amazon.whispercloak.protobuf.ProtobufBigIntegerClass$ProtobufBigInteger r4 = (com.amazon.whispercloak.protobuf.ProtobufBigIntegerClass.ProtobufBigInteger) r4     // Catch: java.lang.Throwable -> Lf
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
                throw new UnsupportedOperationException("Method not decompiled: com.amazon.whispercloak.protobuf.ProtobufBigIntegerClass.ProtobufBigInteger.Builder.mo10097mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.amazon.whispercloak.protobuf.ProtobufBigIntegerClass$ProtobufBigInteger$Builder");
            }
        }

        public static Builder newBuilder(ProtobufBigInteger protobufBigInteger) {
            return DEFAULT_INSTANCE.mo10081toBuilder().mergeFrom(protobufBigInteger);
        }

        public static ProtobufBigInteger parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ProtobufBigInteger) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static ProtobufBigInteger parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.mo8403parseFrom(byteBuffer, extensionRegistryLite);
        }

        private ProtobufBigInteger(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        public static ProtobufBigInteger parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.mo8396parseFrom(byteString);
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        /* renamed from: getDefaultInstanceForType */
        public ProtobufBigInteger mo10094getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        /* renamed from: toBuilder */
        public Builder mo10081toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        public static ProtobufBigInteger parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.mo8397parseFrom(byteString, extensionRegistryLite);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        /* renamed from: newBuilderForType */
        public Builder mo10079newBuilderForType() {
            return newBuilder();
        }

        private ProtobufBigInteger() {
            this.memoizedIsInitialized = (byte) -1;
            this.value_ = ByteString.EMPTY;
        }

        public static ProtobufBigInteger parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.mo8404parseFrom(bArr);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageV3
        /* renamed from: newBuilderForType */
        public Builder mo10080newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public static ProtobufBigInteger parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.mo8407parseFrom(bArr, extensionRegistryLite);
        }

        public static ProtobufBigInteger parseFrom(InputStream inputStream) throws IOException {
            return (ProtobufBigInteger) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        private ProtobufBigInteger(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                        this.bitField0_ |= 1;
                                        this.value_ = codedInputStream.readBytes();
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

        public static ProtobufBigInteger parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ProtobufBigInteger) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static ProtobufBigInteger parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (ProtobufBigInteger) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static ProtobufBigInteger parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ProtobufBigInteger) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes13.dex */
    public interface ProtobufBigIntegerOrBuilder extends MessageOrBuilder {
        ByteString getValue();

        boolean hasValue();
    }

    static {
        Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n8WhisperCloakProtocolBuffersModel/schema/BigInteger.proto\u0012\bprotobuf\"#\n\u0012ProtobufBigInteger\u0012\r\n\u0005value\u0018\u0001 \u0002(\fB;\n com.amazon.whispercloak.protobufB\u0017ProtobufBigIntegerClass"}, new Descriptors.FileDescriptor[0], new Descriptors.FileDescriptor.InternalDescriptorAssigner() { // from class: com.amazon.whispercloak.protobuf.ProtobufBigIntegerClass.1
            @Override // com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner
            public ExtensionRegistry assignDescriptors(Descriptors.FileDescriptor fileDescriptor) {
                Descriptors.FileDescriptor unused = ProtobufBigIntegerClass.descriptor = fileDescriptor;
                return null;
            }
        });
        internal_static_protobuf_ProtobufBigInteger_descriptor = getDescriptor().getMessageTypes().get(0);
        internal_static_protobuf_ProtobufBigInteger_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_protobuf_ProtobufBigInteger_descriptor, new String[]{MAPCookie.KEY_VALUE});
    }

    private ProtobufBigIntegerClass() {
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
