package com.amazon.whisperjoin.protobuf;

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
import com.google.protobuf.RepeatedFieldBuilderV3;
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/* loaded from: classes13.dex */
public final class ProtobufProvisionableEventTypeClass {
    private static Descriptors.FileDescriptor descriptor;
    private static final Descriptors.Descriptor internal_static_protobuf_ProtobufProvisionableEventTypeCollection_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_protobuf_ProtobufProvisionableEventTypeCollection_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_protobuf_ProtobufProvisionableEventType_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_protobuf_ProtobufProvisionableEventType_fieldAccessorTable;

    /* loaded from: classes13.dex */
    public static final class ProtobufProvisionableEventType extends GeneratedMessageV3 implements ProtobufProvisionableEventTypeOrBuilder {
        public static final int EVENTTYPE_FIELD_NUMBER = 2;
        public static final int EVENTUUID_FIELD_NUMBER = 1;
        private static final long serialVersionUID = 0;
        private int bitField0_;
        private int eventType_;
        private ByteString eventUuid_;
        private byte memoizedIsInitialized;
        private static final ProtobufProvisionableEventType DEFAULT_INSTANCE = new ProtobufProvisionableEventType();
        @Deprecated
        public static final Parser<ProtobufProvisionableEventType> PARSER = new AbstractParser<ProtobufProvisionableEventType>() { // from class: com.amazon.whisperjoin.protobuf.ProtobufProvisionableEventTypeClass.ProtobufProvisionableEventType.1
            @Override // com.google.protobuf.Parser
            /* renamed from: parsePartialFrom */
            public ProtobufProvisionableEventType mo10082parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new ProtobufProvisionableEventType(codedInputStream, extensionRegistryLite);
            }
        };

        public static ProtobufProvisionableEventType getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return ProtobufProvisionableEventTypeClass.internal_static_protobuf_ProtobufProvisionableEventType_descriptor;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.mo10081toBuilder();
        }

        public static ProtobufProvisionableEventType parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (ProtobufProvisionableEventType) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static ProtobufProvisionableEventType parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.mo8402parseFrom(byteBuffer);
        }

        public static Parser<ProtobufProvisionableEventType> parser() {
            return PARSER;
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof ProtobufProvisionableEventType)) {
                return super.equals(obj);
            }
            ProtobufProvisionableEventType protobufProvisionableEventType = (ProtobufProvisionableEventType) obj;
            boolean z = hasEventUuid() == protobufProvisionableEventType.hasEventUuid();
            if (hasEventUuid()) {
                z = z && getEventUuid().equals(protobufProvisionableEventType.getEventUuid());
            }
            boolean z2 = z && hasEventType() == protobufProvisionableEventType.hasEventType();
            if (hasEventType()) {
                z2 = z2 && getEventType() == protobufProvisionableEventType.getEventType();
            }
            return z2 && this.unknownFields.equals(protobufProvisionableEventType.unknownFields);
        }

        @Override // com.amazon.whisperjoin.protobuf.ProtobufProvisionableEventTypeClass.ProtobufProvisionableEventTypeOrBuilder
        public int getEventType() {
            return this.eventType_;
        }

        @Override // com.amazon.whisperjoin.protobuf.ProtobufProvisionableEventTypeClass.ProtobufProvisionableEventTypeOrBuilder
        public ByteString getEventUuid() {
            return this.eventUuid_;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
        /* renamed from: getParserForType */
        public Parser<ProtobufProvisionableEventType> mo9954getParserForType() {
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
                i2 = 0 + CodedOutputStream.computeBytesSize(1, this.eventUuid_);
            }
            if ((this.bitField0_ & 2) == 2) {
                i2 += CodedOutputStream.computeSInt32Size(2, this.eventType_);
            }
            int serializedSize = this.unknownFields.getSerializedSize() + i2;
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        @Override // com.amazon.whisperjoin.protobuf.ProtobufProvisionableEventTypeClass.ProtobufProvisionableEventTypeOrBuilder
        public boolean hasEventType() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // com.amazon.whisperjoin.protobuf.ProtobufProvisionableEventTypeClass.ProtobufProvisionableEventTypeOrBuilder
        public boolean hasEventUuid() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public int hashCode() {
            int i = this.memoizedHashCode;
            if (i != 0) {
                return i;
            }
            int hashCode = getDescriptor().hashCode() + 779;
            if (hasEventUuid()) {
                hashCode = GeneratedOutlineSupport1.outline4(hashCode, 37, 1, 53) + getEventUuid().hashCode();
            }
            if (hasEventType()) {
                hashCode = GeneratedOutlineSupport1.outline4(hashCode, 37, 2, 53) + getEventType();
            }
            int hashCode2 = this.unknownFields.hashCode() + (hashCode * 29);
            this.memoizedHashCode = hashCode2;
            return hashCode2;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return ProtobufProvisionableEventTypeClass.internal_static_protobuf_ProtobufProvisionableEventType_fieldAccessorTable.ensureFieldAccessorsInitialized(ProtobufProvisionableEventType.class, Builder.class);
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
            if (!hasEventUuid()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            } else if (!hasEventType()) {
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
                codedOutputStream.writeBytes(1, this.eventUuid_);
            }
            if ((this.bitField0_ & 2) == 2) {
                codedOutputStream.writeSInt32(2, this.eventType_);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        /* loaded from: classes13.dex */
        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements ProtobufProvisionableEventTypeOrBuilder {
            private int bitField0_;
            private int eventType_;
            private ByteString eventUuid_;

            public static final Descriptors.Descriptor getDescriptor() {
                return ProtobufProvisionableEventTypeClass.internal_static_protobuf_ProtobufProvisionableEventType_descriptor;
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = GeneratedMessageV3.alwaysUseFieldBuilders;
            }

            public Builder clearEventType() {
                this.bitField0_ &= -3;
                this.eventType_ = 0;
                onChanged();
                return this;
            }

            public Builder clearEventUuid() {
                this.bitField0_ &= -2;
                this.eventUuid_ = ProtobufProvisionableEventType.getDefaultInstance().getEventUuid();
                onChanged();
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return ProtobufProvisionableEventTypeClass.internal_static_protobuf_ProtobufProvisionableEventType_descriptor;
            }

            @Override // com.amazon.whisperjoin.protobuf.ProtobufProvisionableEventTypeClass.ProtobufProvisionableEventTypeOrBuilder
            public int getEventType() {
                return this.eventType_;
            }

            @Override // com.amazon.whisperjoin.protobuf.ProtobufProvisionableEventTypeClass.ProtobufProvisionableEventTypeOrBuilder
            public ByteString getEventUuid() {
                return this.eventUuid_;
            }

            @Override // com.amazon.whisperjoin.protobuf.ProtobufProvisionableEventTypeClass.ProtobufProvisionableEventTypeOrBuilder
            public boolean hasEventType() {
                return (this.bitField0_ & 2) == 2;
            }

            @Override // com.amazon.whisperjoin.protobuf.ProtobufProvisionableEventTypeClass.ProtobufProvisionableEventTypeOrBuilder
            public boolean hasEventUuid() {
                return (this.bitField0_ & 1) == 1;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return ProtobufProvisionableEventTypeClass.internal_static_protobuf_ProtobufProvisionableEventType_fieldAccessorTable.ensureFieldAccessorsInitialized(ProtobufProvisionableEventType.class, Builder.class);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return hasEventUuid() && hasEventType();
            }

            public Builder setEventType(int i) {
                this.bitField0_ |= 2;
                this.eventType_ = i;
                onChanged();
                return this;
            }

            public Builder setEventUuid(ByteString byteString) {
                if (byteString != null) {
                    this.bitField0_ |= 1;
                    this.eventUuid_ = byteString;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            private Builder() {
                this.eventUuid_ = ByteString.EMPTY;
                maybeForceBuilderInitialization();
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            /* renamed from: addRepeatedField */
            public Builder mo10083addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.mo10083addRepeatedField(fieldDescriptor, obj);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: build */
            public ProtobufProvisionableEventType mo10084build() {
                ProtobufProvisionableEventType mo10085buildPartial = mo10085buildPartial();
                if (mo10085buildPartial.isInitialized()) {
                    return mo10085buildPartial;
                }
                throw AbstractMessage.Builder.newUninitializedMessageException((Message) mo10085buildPartial);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: buildPartial */
            public ProtobufProvisionableEventType mo10085buildPartial() {
                ProtobufProvisionableEventType protobufProvisionableEventType = new ProtobufProvisionableEventType(this);
                int i = this.bitField0_;
                int i2 = 1;
                if ((i & 1) != 1) {
                    i2 = 0;
                }
                protobufProvisionableEventType.eventUuid_ = this.eventUuid_;
                if ((i & 2) == 2) {
                    i2 |= 2;
                }
                protobufProvisionableEventType.eventType_ = this.eventType_;
                protobufProvisionableEventType.bitField0_ = i2;
                onBuilt();
                return protobufProvisionableEventType;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            /* renamed from: clearField */
            public Builder mo10088clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (Builder) super.mo10088clearField(fieldDescriptor);
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            /* renamed from: getDefaultInstanceForType */
            public ProtobufProvisionableEventType mo10094getDefaultInstanceForType() {
                return ProtobufProvisionableEventType.getDefaultInstance();
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
                this.eventUuid_ = ByteString.EMPTY;
                this.bitField0_ &= -2;
                this.eventType_ = 0;
                this.bitField0_ &= -3;
                return this;
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.eventUuid_ = ByteString.EMPTY;
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
                if (message instanceof ProtobufProvisionableEventType) {
                    return mergeFrom((ProtobufProvisionableEventType) message);
                }
                super.mo10096mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(ProtobufProvisionableEventType protobufProvisionableEventType) {
                if (protobufProvisionableEventType == ProtobufProvisionableEventType.getDefaultInstance()) {
                    return this;
                }
                if (protobufProvisionableEventType.hasEventUuid()) {
                    setEventUuid(protobufProvisionableEventType.getEventUuid());
                }
                if (protobufProvisionableEventType.hasEventType()) {
                    setEventType(protobufProvisionableEventType.getEventType());
                }
                mo10099mergeUnknownFields(((GeneratedMessageV3) protobufProvisionableEventType).unknownFields);
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
            public com.amazon.whisperjoin.protobuf.ProtobufProvisionableEventTypeClass.ProtobufProvisionableEventType.Builder mo10097mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
                /*
                    r2 = this;
                    r0 = 0
                    com.google.protobuf.Parser<com.amazon.whisperjoin.protobuf.ProtobufProvisionableEventTypeClass$ProtobufProvisionableEventType> r1 = com.amazon.whisperjoin.protobuf.ProtobufProvisionableEventTypeClass.ProtobufProvisionableEventType.PARSER     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                    java.lang.Object r3 = r1.mo10082parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                    com.amazon.whisperjoin.protobuf.ProtobufProvisionableEventTypeClass$ProtobufProvisionableEventType r3 = (com.amazon.whisperjoin.protobuf.ProtobufProvisionableEventTypeClass.ProtobufProvisionableEventType) r3     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
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
                    com.amazon.whisperjoin.protobuf.ProtobufProvisionableEventTypeClass$ProtobufProvisionableEventType r4 = (com.amazon.whisperjoin.protobuf.ProtobufProvisionableEventTypeClass.ProtobufProvisionableEventType) r4     // Catch: java.lang.Throwable -> Lf
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
                throw new UnsupportedOperationException("Method not decompiled: com.amazon.whisperjoin.protobuf.ProtobufProvisionableEventTypeClass.ProtobufProvisionableEventType.Builder.mo10097mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.amazon.whisperjoin.protobuf.ProtobufProvisionableEventTypeClass$ProtobufProvisionableEventType$Builder");
            }
        }

        public static Builder newBuilder(ProtobufProvisionableEventType protobufProvisionableEventType) {
            return DEFAULT_INSTANCE.mo10081toBuilder().mergeFrom(protobufProvisionableEventType);
        }

        public static ProtobufProvisionableEventType parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.mo8403parseFrom(byteBuffer, extensionRegistryLite);
        }

        private ProtobufProvisionableEventType(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        public static ProtobufProvisionableEventType parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ProtobufProvisionableEventType) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static ProtobufProvisionableEventType parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.mo8396parseFrom(byteString);
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        /* renamed from: getDefaultInstanceForType */
        public ProtobufProvisionableEventType mo10094getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        /* renamed from: toBuilder */
        public Builder mo10081toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        public static ProtobufProvisionableEventType parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.mo8397parseFrom(byteString, extensionRegistryLite);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        /* renamed from: newBuilderForType */
        public Builder mo10079newBuilderForType() {
            return newBuilder();
        }

        private ProtobufProvisionableEventType() {
            this.memoizedIsInitialized = (byte) -1;
            this.eventUuid_ = ByteString.EMPTY;
            this.eventType_ = 0;
        }

        public static ProtobufProvisionableEventType parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.mo8404parseFrom(bArr);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageV3
        /* renamed from: newBuilderForType */
        public Builder mo10080newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public static ProtobufProvisionableEventType parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.mo8407parseFrom(bArr, extensionRegistryLite);
        }

        public static ProtobufProvisionableEventType parseFrom(InputStream inputStream) throws IOException {
            return (ProtobufProvisionableEventType) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        private ProtobufProvisionableEventType(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                    this.eventUuid_ = codedInputStream.readBytes();
                                } else if (readTag != 16) {
                                    if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                    }
                                } else {
                                    this.bitField0_ |= 2;
                                    this.eventType_ = codedInputStream.readSInt32();
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

        public static ProtobufProvisionableEventType parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ProtobufProvisionableEventType) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static ProtobufProvisionableEventType parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (ProtobufProvisionableEventType) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static ProtobufProvisionableEventType parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ProtobufProvisionableEventType) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes13.dex */
    public static final class ProtobufProvisionableEventTypeCollection extends GeneratedMessageV3 implements ProtobufProvisionableEventTypeCollectionOrBuilder {
        private static final ProtobufProvisionableEventTypeCollection DEFAULT_INSTANCE = new ProtobufProvisionableEventTypeCollection();
        @Deprecated
        public static final Parser<ProtobufProvisionableEventTypeCollection> PARSER = new AbstractParser<ProtobufProvisionableEventTypeCollection>() { // from class: com.amazon.whisperjoin.protobuf.ProtobufProvisionableEventTypeClass.ProtobufProvisionableEventTypeCollection.1
            @Override // com.google.protobuf.Parser
            /* renamed from: parsePartialFrom */
            public ProtobufProvisionableEventTypeCollection mo10082parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new ProtobufProvisionableEventTypeCollection(codedInputStream, extensionRegistryLite);
            }
        };
        public static final int PROTOBUFPROVISIONABLEEVENTTYPECOLLECTION_FIELD_NUMBER = 1;
        private static final long serialVersionUID = 0;
        private byte memoizedIsInitialized;
        private List<ProtobufProvisionableEventType> protobufProvisionableEventTypeCollection_;

        public static ProtobufProvisionableEventTypeCollection getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return ProtobufProvisionableEventTypeClass.internal_static_protobuf_ProtobufProvisionableEventTypeCollection_descriptor;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.mo10081toBuilder();
        }

        public static ProtobufProvisionableEventTypeCollection parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (ProtobufProvisionableEventTypeCollection) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static ProtobufProvisionableEventTypeCollection parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.mo8402parseFrom(byteBuffer);
        }

        public static Parser<ProtobufProvisionableEventTypeCollection> parser() {
            return PARSER;
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof ProtobufProvisionableEventTypeCollection)) {
                return super.equals(obj);
            }
            ProtobufProvisionableEventTypeCollection protobufProvisionableEventTypeCollection = (ProtobufProvisionableEventTypeCollection) obj;
            return (getProtobufProvisionableEventTypeCollectionList().equals(protobufProvisionableEventTypeCollection.getProtobufProvisionableEventTypeCollectionList())) && this.unknownFields.equals(protobufProvisionableEventTypeCollection.unknownFields);
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
        /* renamed from: getParserForType */
        public Parser<ProtobufProvisionableEventTypeCollection> mo9954getParserForType() {
            return PARSER;
        }

        @Override // com.amazon.whisperjoin.protobuf.ProtobufProvisionableEventTypeClass.ProtobufProvisionableEventTypeCollectionOrBuilder
        public ProtobufProvisionableEventType getProtobufProvisionableEventTypeCollection(int i) {
            return this.protobufProvisionableEventTypeCollection_.get(i);
        }

        @Override // com.amazon.whisperjoin.protobuf.ProtobufProvisionableEventTypeClass.ProtobufProvisionableEventTypeCollectionOrBuilder
        public int getProtobufProvisionableEventTypeCollectionCount() {
            return this.protobufProvisionableEventTypeCollection_.size();
        }

        @Override // com.amazon.whisperjoin.protobuf.ProtobufProvisionableEventTypeClass.ProtobufProvisionableEventTypeCollectionOrBuilder
        public List<ProtobufProvisionableEventType> getProtobufProvisionableEventTypeCollectionList() {
            return this.protobufProvisionableEventTypeCollection_;
        }

        @Override // com.amazon.whisperjoin.protobuf.ProtobufProvisionableEventTypeClass.ProtobufProvisionableEventTypeCollectionOrBuilder
        public ProtobufProvisionableEventTypeOrBuilder getProtobufProvisionableEventTypeCollectionOrBuilder(int i) {
            return this.protobufProvisionableEventTypeCollection_.get(i);
        }

        @Override // com.amazon.whisperjoin.protobuf.ProtobufProvisionableEventTypeClass.ProtobufProvisionableEventTypeCollectionOrBuilder
        public List<? extends ProtobufProvisionableEventTypeOrBuilder> getProtobufProvisionableEventTypeCollectionOrBuilderList() {
            return this.protobufProvisionableEventTypeCollection_;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            for (int i3 = 0; i3 < this.protobufProvisionableEventTypeCollection_.size(); i3++) {
                i2 += CodedOutputStream.computeMessageSize(1, this.protobufProvisionableEventTypeCollection_.get(i3));
            }
            int serializedSize = this.unknownFields.getSerializedSize() + i2;
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public int hashCode() {
            int i = this.memoizedHashCode;
            if (i != 0) {
                return i;
            }
            int hashCode = getDescriptor().hashCode() + 779;
            if (getProtobufProvisionableEventTypeCollectionCount() > 0) {
                hashCode = GeneratedOutlineSupport1.outline4(hashCode, 37, 1, 53) + getProtobufProvisionableEventTypeCollectionList().hashCode();
            }
            int hashCode2 = this.unknownFields.hashCode() + (hashCode * 29);
            this.memoizedHashCode = hashCode2;
            return hashCode2;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return ProtobufProvisionableEventTypeClass.internal_static_protobuf_ProtobufProvisionableEventTypeCollection_fieldAccessorTable.ensureFieldAccessorsInitialized(ProtobufProvisionableEventTypeCollection.class, Builder.class);
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
            for (int i = 0; i < getProtobufProvisionableEventTypeCollectionCount(); i++) {
                if (!getProtobufProvisionableEventTypeCollection(i).isInitialized()) {
                    this.memoizedIsInitialized = (byte) 0;
                    return false;
                }
            }
            this.memoizedIsInitialized = (byte) 1;
            return true;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            for (int i = 0; i < this.protobufProvisionableEventTypeCollection_.size(); i++) {
                codedOutputStream.writeMessage(1, this.protobufProvisionableEventTypeCollection_.get(i));
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        /* loaded from: classes13.dex */
        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements ProtobufProvisionableEventTypeCollectionOrBuilder {
            private int bitField0_;
            private RepeatedFieldBuilderV3<ProtobufProvisionableEventType, ProtobufProvisionableEventType.Builder, ProtobufProvisionableEventTypeOrBuilder> protobufProvisionableEventTypeCollectionBuilder_;
            private List<ProtobufProvisionableEventType> protobufProvisionableEventTypeCollection_;

            private void ensureProtobufProvisionableEventTypeCollectionIsMutable() {
                if ((this.bitField0_ & 1) != 1) {
                    this.protobufProvisionableEventTypeCollection_ = new ArrayList(this.protobufProvisionableEventTypeCollection_);
                    this.bitField0_ |= 1;
                }
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return ProtobufProvisionableEventTypeClass.internal_static_protobuf_ProtobufProvisionableEventTypeCollection_descriptor;
            }

            private RepeatedFieldBuilderV3<ProtobufProvisionableEventType, ProtobufProvisionableEventType.Builder, ProtobufProvisionableEventTypeOrBuilder> getProtobufProvisionableEventTypeCollectionFieldBuilder() {
                if (this.protobufProvisionableEventTypeCollectionBuilder_ == null) {
                    List<ProtobufProvisionableEventType> list = this.protobufProvisionableEventTypeCollection_;
                    boolean z = true;
                    if ((this.bitField0_ & 1) != 1) {
                        z = false;
                    }
                    this.protobufProvisionableEventTypeCollectionBuilder_ = new RepeatedFieldBuilderV3<>(list, z, getParentForChildren(), isClean());
                    this.protobufProvisionableEventTypeCollection_ = null;
                }
                return this.protobufProvisionableEventTypeCollectionBuilder_;
            }

            private void maybeForceBuilderInitialization() {
                if (GeneratedMessageV3.alwaysUseFieldBuilders) {
                    getProtobufProvisionableEventTypeCollectionFieldBuilder();
                }
            }

            public Builder addAllProtobufProvisionableEventTypeCollection(Iterable<? extends ProtobufProvisionableEventType> iterable) {
                RepeatedFieldBuilderV3<ProtobufProvisionableEventType, ProtobufProvisionableEventType.Builder, ProtobufProvisionableEventTypeOrBuilder> repeatedFieldBuilderV3 = this.protobufProvisionableEventTypeCollectionBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureProtobufProvisionableEventTypeCollectionIsMutable();
                    AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.protobufProvisionableEventTypeCollection_);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addAllMessages(iterable);
                }
                return this;
            }

            public Builder addProtobufProvisionableEventTypeCollection(ProtobufProvisionableEventType protobufProvisionableEventType) {
                RepeatedFieldBuilderV3<ProtobufProvisionableEventType, ProtobufProvisionableEventType.Builder, ProtobufProvisionableEventTypeOrBuilder> repeatedFieldBuilderV3 = this.protobufProvisionableEventTypeCollectionBuilder_;
                if (repeatedFieldBuilderV3 != null) {
                    repeatedFieldBuilderV3.addMessage(protobufProvisionableEventType);
                } else if (protobufProvisionableEventType != null) {
                    ensureProtobufProvisionableEventTypeCollectionIsMutable();
                    this.protobufProvisionableEventTypeCollection_.add(protobufProvisionableEventType);
                    onChanged();
                } else {
                    throw new NullPointerException();
                }
                return this;
            }

            public ProtobufProvisionableEventType.Builder addProtobufProvisionableEventTypeCollectionBuilder() {
                return getProtobufProvisionableEventTypeCollectionFieldBuilder().addBuilder(ProtobufProvisionableEventType.getDefaultInstance());
            }

            public Builder clearProtobufProvisionableEventTypeCollection() {
                RepeatedFieldBuilderV3<ProtobufProvisionableEventType, ProtobufProvisionableEventType.Builder, ProtobufProvisionableEventTypeOrBuilder> repeatedFieldBuilderV3 = this.protobufProvisionableEventTypeCollectionBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    this.protobufProvisionableEventTypeCollection_ = Collections.emptyList();
                    this.bitField0_ &= -2;
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.clear();
                }
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return ProtobufProvisionableEventTypeClass.internal_static_protobuf_ProtobufProvisionableEventTypeCollection_descriptor;
            }

            @Override // com.amazon.whisperjoin.protobuf.ProtobufProvisionableEventTypeClass.ProtobufProvisionableEventTypeCollectionOrBuilder
            public ProtobufProvisionableEventType getProtobufProvisionableEventTypeCollection(int i) {
                RepeatedFieldBuilderV3<ProtobufProvisionableEventType, ProtobufProvisionableEventType.Builder, ProtobufProvisionableEventTypeOrBuilder> repeatedFieldBuilderV3 = this.protobufProvisionableEventTypeCollectionBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return this.protobufProvisionableEventTypeCollection_.get(i);
                }
                return repeatedFieldBuilderV3.getMessage(i);
            }

            public ProtobufProvisionableEventType.Builder getProtobufProvisionableEventTypeCollectionBuilder(int i) {
                return getProtobufProvisionableEventTypeCollectionFieldBuilder().getBuilder(i);
            }

            public List<ProtobufProvisionableEventType.Builder> getProtobufProvisionableEventTypeCollectionBuilderList() {
                return getProtobufProvisionableEventTypeCollectionFieldBuilder().getBuilderList();
            }

            @Override // com.amazon.whisperjoin.protobuf.ProtobufProvisionableEventTypeClass.ProtobufProvisionableEventTypeCollectionOrBuilder
            public int getProtobufProvisionableEventTypeCollectionCount() {
                RepeatedFieldBuilderV3<ProtobufProvisionableEventType, ProtobufProvisionableEventType.Builder, ProtobufProvisionableEventTypeOrBuilder> repeatedFieldBuilderV3 = this.protobufProvisionableEventTypeCollectionBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return this.protobufProvisionableEventTypeCollection_.size();
                }
                return repeatedFieldBuilderV3.getCount();
            }

            @Override // com.amazon.whisperjoin.protobuf.ProtobufProvisionableEventTypeClass.ProtobufProvisionableEventTypeCollectionOrBuilder
            public List<ProtobufProvisionableEventType> getProtobufProvisionableEventTypeCollectionList() {
                RepeatedFieldBuilderV3<ProtobufProvisionableEventType, ProtobufProvisionableEventType.Builder, ProtobufProvisionableEventTypeOrBuilder> repeatedFieldBuilderV3 = this.protobufProvisionableEventTypeCollectionBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return Collections.unmodifiableList(this.protobufProvisionableEventTypeCollection_);
                }
                return repeatedFieldBuilderV3.getMessageList();
            }

            @Override // com.amazon.whisperjoin.protobuf.ProtobufProvisionableEventTypeClass.ProtobufProvisionableEventTypeCollectionOrBuilder
            public ProtobufProvisionableEventTypeOrBuilder getProtobufProvisionableEventTypeCollectionOrBuilder(int i) {
                RepeatedFieldBuilderV3<ProtobufProvisionableEventType, ProtobufProvisionableEventType.Builder, ProtobufProvisionableEventTypeOrBuilder> repeatedFieldBuilderV3 = this.protobufProvisionableEventTypeCollectionBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return this.protobufProvisionableEventTypeCollection_.get(i);
                }
                return repeatedFieldBuilderV3.getMessageOrBuilder(i);
            }

            @Override // com.amazon.whisperjoin.protobuf.ProtobufProvisionableEventTypeClass.ProtobufProvisionableEventTypeCollectionOrBuilder
            public List<? extends ProtobufProvisionableEventTypeOrBuilder> getProtobufProvisionableEventTypeCollectionOrBuilderList() {
                RepeatedFieldBuilderV3<ProtobufProvisionableEventType, ProtobufProvisionableEventType.Builder, ProtobufProvisionableEventTypeOrBuilder> repeatedFieldBuilderV3 = this.protobufProvisionableEventTypeCollectionBuilder_;
                if (repeatedFieldBuilderV3 != null) {
                    return repeatedFieldBuilderV3.getMessageOrBuilderList();
                }
                return Collections.unmodifiableList(this.protobufProvisionableEventTypeCollection_);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return ProtobufProvisionableEventTypeClass.internal_static_protobuf_ProtobufProvisionableEventTypeCollection_fieldAccessorTable.ensureFieldAccessorsInitialized(ProtobufProvisionableEventTypeCollection.class, Builder.class);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                for (int i = 0; i < getProtobufProvisionableEventTypeCollectionCount(); i++) {
                    if (!getProtobufProvisionableEventTypeCollection(i).isInitialized()) {
                        return false;
                    }
                }
                return true;
            }

            public Builder removeProtobufProvisionableEventTypeCollection(int i) {
                RepeatedFieldBuilderV3<ProtobufProvisionableEventType, ProtobufProvisionableEventType.Builder, ProtobufProvisionableEventTypeOrBuilder> repeatedFieldBuilderV3 = this.protobufProvisionableEventTypeCollectionBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureProtobufProvisionableEventTypeCollectionIsMutable();
                    this.protobufProvisionableEventTypeCollection_.remove(i);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.remove(i);
                }
                return this;
            }

            public Builder setProtobufProvisionableEventTypeCollection(int i, ProtobufProvisionableEventType protobufProvisionableEventType) {
                RepeatedFieldBuilderV3<ProtobufProvisionableEventType, ProtobufProvisionableEventType.Builder, ProtobufProvisionableEventTypeOrBuilder> repeatedFieldBuilderV3 = this.protobufProvisionableEventTypeCollectionBuilder_;
                if (repeatedFieldBuilderV3 != null) {
                    repeatedFieldBuilderV3.setMessage(i, protobufProvisionableEventType);
                } else if (protobufProvisionableEventType != null) {
                    ensureProtobufProvisionableEventTypeCollectionIsMutable();
                    this.protobufProvisionableEventTypeCollection_.set(i, protobufProvisionableEventType);
                    onChanged();
                } else {
                    throw new NullPointerException();
                }
                return this;
            }

            private Builder() {
                this.protobufProvisionableEventTypeCollection_ = Collections.emptyList();
                maybeForceBuilderInitialization();
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            /* renamed from: addRepeatedField */
            public Builder mo10083addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.mo10083addRepeatedField(fieldDescriptor, obj);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: build */
            public ProtobufProvisionableEventTypeCollection mo10084build() {
                ProtobufProvisionableEventTypeCollection mo10085buildPartial = mo10085buildPartial();
                if (mo10085buildPartial.isInitialized()) {
                    return mo10085buildPartial;
                }
                throw AbstractMessage.Builder.newUninitializedMessageException((Message) mo10085buildPartial);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: buildPartial */
            public ProtobufProvisionableEventTypeCollection mo10085buildPartial() {
                ProtobufProvisionableEventTypeCollection protobufProvisionableEventTypeCollection = new ProtobufProvisionableEventTypeCollection(this);
                int i = this.bitField0_;
                RepeatedFieldBuilderV3<ProtobufProvisionableEventType, ProtobufProvisionableEventType.Builder, ProtobufProvisionableEventTypeOrBuilder> repeatedFieldBuilderV3 = this.protobufProvisionableEventTypeCollectionBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    if ((i & 1) == 1) {
                        this.protobufProvisionableEventTypeCollection_ = Collections.unmodifiableList(this.protobufProvisionableEventTypeCollection_);
                        this.bitField0_ &= -2;
                    }
                    protobufProvisionableEventTypeCollection.protobufProvisionableEventTypeCollection_ = this.protobufProvisionableEventTypeCollection_;
                } else {
                    protobufProvisionableEventTypeCollection.protobufProvisionableEventTypeCollection_ = repeatedFieldBuilderV3.build();
                }
                onBuilt();
                return protobufProvisionableEventTypeCollection;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            /* renamed from: clearField */
            public Builder mo10088clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (Builder) super.mo10088clearField(fieldDescriptor);
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            /* renamed from: getDefaultInstanceForType */
            public ProtobufProvisionableEventTypeCollection mo10094getDefaultInstanceForType() {
                return ProtobufProvisionableEventTypeCollection.getDefaultInstance();
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

            public ProtobufProvisionableEventType.Builder addProtobufProvisionableEventTypeCollectionBuilder(int i) {
                return getProtobufProvisionableEventTypeCollectionFieldBuilder().addBuilder(i, ProtobufProvisionableEventType.getDefaultInstance());
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
                RepeatedFieldBuilderV3<ProtobufProvisionableEventType, ProtobufProvisionableEventType.Builder, ProtobufProvisionableEventTypeOrBuilder> repeatedFieldBuilderV3 = this.protobufProvisionableEventTypeCollectionBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    this.protobufProvisionableEventTypeCollection_ = Collections.emptyList();
                    this.bitField0_ &= -2;
                } else {
                    repeatedFieldBuilderV3.clear();
                }
                return this;
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.protobufProvisionableEventTypeCollection_ = Collections.emptyList();
                maybeForceBuilderInitialization();
            }

            public Builder addProtobufProvisionableEventTypeCollection(int i, ProtobufProvisionableEventType protobufProvisionableEventType) {
                RepeatedFieldBuilderV3<ProtobufProvisionableEventType, ProtobufProvisionableEventType.Builder, ProtobufProvisionableEventTypeOrBuilder> repeatedFieldBuilderV3 = this.protobufProvisionableEventTypeCollectionBuilder_;
                if (repeatedFieldBuilderV3 != null) {
                    repeatedFieldBuilderV3.addMessage(i, protobufProvisionableEventType);
                } else if (protobufProvisionableEventType != null) {
                    ensureProtobufProvisionableEventTypeCollectionIsMutable();
                    this.protobufProvisionableEventTypeCollection_.add(i, protobufProvisionableEventType);
                    onChanged();
                } else {
                    throw new NullPointerException();
                }
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
                if (message instanceof ProtobufProvisionableEventTypeCollection) {
                    return mergeFrom((ProtobufProvisionableEventTypeCollection) message);
                }
                super.mo10096mergeFrom(message);
                return this;
            }

            public Builder setProtobufProvisionableEventTypeCollection(int i, ProtobufProvisionableEventType.Builder builder) {
                RepeatedFieldBuilderV3<ProtobufProvisionableEventType, ProtobufProvisionableEventType.Builder, ProtobufProvisionableEventTypeOrBuilder> repeatedFieldBuilderV3 = this.protobufProvisionableEventTypeCollectionBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureProtobufProvisionableEventTypeCollectionIsMutable();
                    this.protobufProvisionableEventTypeCollection_.set(i, builder.mo10084build());
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.setMessage(i, builder.mo10084build());
                }
                return this;
            }

            public Builder mergeFrom(ProtobufProvisionableEventTypeCollection protobufProvisionableEventTypeCollection) {
                if (protobufProvisionableEventTypeCollection == ProtobufProvisionableEventTypeCollection.getDefaultInstance()) {
                    return this;
                }
                if (this.protobufProvisionableEventTypeCollectionBuilder_ == null) {
                    if (!protobufProvisionableEventTypeCollection.protobufProvisionableEventTypeCollection_.isEmpty()) {
                        if (this.protobufProvisionableEventTypeCollection_.isEmpty()) {
                            this.protobufProvisionableEventTypeCollection_ = protobufProvisionableEventTypeCollection.protobufProvisionableEventTypeCollection_;
                            this.bitField0_ &= -2;
                        } else {
                            ensureProtobufProvisionableEventTypeCollectionIsMutable();
                            this.protobufProvisionableEventTypeCollection_.addAll(protobufProvisionableEventTypeCollection.protobufProvisionableEventTypeCollection_);
                        }
                        onChanged();
                    }
                } else if (!protobufProvisionableEventTypeCollection.protobufProvisionableEventTypeCollection_.isEmpty()) {
                    if (!this.protobufProvisionableEventTypeCollectionBuilder_.isEmpty()) {
                        this.protobufProvisionableEventTypeCollectionBuilder_.addAllMessages(protobufProvisionableEventTypeCollection.protobufProvisionableEventTypeCollection_);
                    } else {
                        this.protobufProvisionableEventTypeCollectionBuilder_.dispose();
                        RepeatedFieldBuilderV3<ProtobufProvisionableEventType, ProtobufProvisionableEventType.Builder, ProtobufProvisionableEventTypeOrBuilder> repeatedFieldBuilderV3 = null;
                        this.protobufProvisionableEventTypeCollectionBuilder_ = null;
                        this.protobufProvisionableEventTypeCollection_ = protobufProvisionableEventTypeCollection.protobufProvisionableEventTypeCollection_;
                        this.bitField0_ &= -2;
                        if (GeneratedMessageV3.alwaysUseFieldBuilders) {
                            repeatedFieldBuilderV3 = getProtobufProvisionableEventTypeCollectionFieldBuilder();
                        }
                        this.protobufProvisionableEventTypeCollectionBuilder_ = repeatedFieldBuilderV3;
                    }
                }
                mo10099mergeUnknownFields(((GeneratedMessageV3) protobufProvisionableEventTypeCollection).unknownFields);
                onChanged();
                return this;
            }

            public Builder addProtobufProvisionableEventTypeCollection(ProtobufProvisionableEventType.Builder builder) {
                RepeatedFieldBuilderV3<ProtobufProvisionableEventType, ProtobufProvisionableEventType.Builder, ProtobufProvisionableEventTypeOrBuilder> repeatedFieldBuilderV3 = this.protobufProvisionableEventTypeCollectionBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureProtobufProvisionableEventTypeCollectionIsMutable();
                    this.protobufProvisionableEventTypeCollection_.add(builder.mo10084build());
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addMessage(builder.mo10084build());
                }
                return this;
            }

            public Builder addProtobufProvisionableEventTypeCollection(int i, ProtobufProvisionableEventType.Builder builder) {
                RepeatedFieldBuilderV3<ProtobufProvisionableEventType, ProtobufProvisionableEventType.Builder, ProtobufProvisionableEventTypeOrBuilder> repeatedFieldBuilderV3 = this.protobufProvisionableEventTypeCollectionBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureProtobufProvisionableEventTypeCollectionIsMutable();
                    this.protobufProvisionableEventTypeCollection_.add(i, builder.mo10084build());
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addMessage(i, builder.mo10084build());
                }
                return this;
            }

            /* JADX WARN: Removed duplicated region for block: B:16:0x0021  */
            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: mergeFrom */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public com.amazon.whisperjoin.protobuf.ProtobufProvisionableEventTypeClass.ProtobufProvisionableEventTypeCollection.Builder mo10097mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
                /*
                    r2 = this;
                    r0 = 0
                    com.google.protobuf.Parser<com.amazon.whisperjoin.protobuf.ProtobufProvisionableEventTypeClass$ProtobufProvisionableEventTypeCollection> r1 = com.amazon.whisperjoin.protobuf.ProtobufProvisionableEventTypeClass.ProtobufProvisionableEventTypeCollection.PARSER     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                    java.lang.Object r3 = r1.mo10082parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                    com.amazon.whisperjoin.protobuf.ProtobufProvisionableEventTypeClass$ProtobufProvisionableEventTypeCollection r3 = (com.amazon.whisperjoin.protobuf.ProtobufProvisionableEventTypeClass.ProtobufProvisionableEventTypeCollection) r3     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
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
                    com.amazon.whisperjoin.protobuf.ProtobufProvisionableEventTypeClass$ProtobufProvisionableEventTypeCollection r4 = (com.amazon.whisperjoin.protobuf.ProtobufProvisionableEventTypeClass.ProtobufProvisionableEventTypeCollection) r4     // Catch: java.lang.Throwable -> Lf
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
                throw new UnsupportedOperationException("Method not decompiled: com.amazon.whisperjoin.protobuf.ProtobufProvisionableEventTypeClass.ProtobufProvisionableEventTypeCollection.Builder.mo10097mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.amazon.whisperjoin.protobuf.ProtobufProvisionableEventTypeClass$ProtobufProvisionableEventTypeCollection$Builder");
            }
        }

        public static Builder newBuilder(ProtobufProvisionableEventTypeCollection protobufProvisionableEventTypeCollection) {
            return DEFAULT_INSTANCE.mo10081toBuilder().mergeFrom(protobufProvisionableEventTypeCollection);
        }

        public static ProtobufProvisionableEventTypeCollection parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.mo8403parseFrom(byteBuffer, extensionRegistryLite);
        }

        private ProtobufProvisionableEventTypeCollection(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        public static ProtobufProvisionableEventTypeCollection parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ProtobufProvisionableEventTypeCollection) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static ProtobufProvisionableEventTypeCollection parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.mo8396parseFrom(byteString);
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        /* renamed from: getDefaultInstanceForType */
        public ProtobufProvisionableEventTypeCollection mo10094getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        /* renamed from: toBuilder */
        public Builder mo10081toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        public static ProtobufProvisionableEventTypeCollection parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.mo8397parseFrom(byteString, extensionRegistryLite);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        /* renamed from: newBuilderForType */
        public Builder mo10079newBuilderForType() {
            return newBuilder();
        }

        private ProtobufProvisionableEventTypeCollection() {
            this.memoizedIsInitialized = (byte) -1;
            this.protobufProvisionableEventTypeCollection_ = Collections.emptyList();
        }

        public static ProtobufProvisionableEventTypeCollection parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.mo8404parseFrom(bArr);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageV3
        /* renamed from: newBuilderForType */
        public Builder mo10080newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public static ProtobufProvisionableEventTypeCollection parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.mo8407parseFrom(bArr, extensionRegistryLite);
        }

        public static ProtobufProvisionableEventTypeCollection parseFrom(InputStream inputStream) throws IOException {
            return (ProtobufProvisionableEventTypeCollection) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        /* JADX WARN: Multi-variable type inference failed */
        private ProtobufProvisionableEventTypeCollection(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                if (readTag != 10) {
                                    if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                    }
                                } else {
                                    if (!(z2 & true)) {
                                        this.protobufProvisionableEventTypeCollection_ = new ArrayList();
                                        z2 |= true;
                                    }
                                    this.protobufProvisionableEventTypeCollection_.add(codedInputStream.readMessage(ProtobufProvisionableEventType.PARSER, extensionRegistryLite));
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
                            this.protobufProvisionableEventTypeCollection_ = Collections.unmodifiableList(this.protobufProvisionableEventTypeCollection_);
                        }
                        this.unknownFields = newBuilder.mo10084build();
                        makeExtensionsImmutable();
                    }
                }
                return;
            }
            throw new NullPointerException();
        }

        public static ProtobufProvisionableEventTypeCollection parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ProtobufProvisionableEventTypeCollection) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static ProtobufProvisionableEventTypeCollection parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (ProtobufProvisionableEventTypeCollection) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static ProtobufProvisionableEventTypeCollection parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ProtobufProvisionableEventTypeCollection) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes13.dex */
    public interface ProtobufProvisionableEventTypeCollectionOrBuilder extends MessageOrBuilder {
        ProtobufProvisionableEventType getProtobufProvisionableEventTypeCollection(int i);

        int getProtobufProvisionableEventTypeCollectionCount();

        List<ProtobufProvisionableEventType> getProtobufProvisionableEventTypeCollectionList();

        ProtobufProvisionableEventTypeOrBuilder getProtobufProvisionableEventTypeCollectionOrBuilder(int i);

        List<? extends ProtobufProvisionableEventTypeOrBuilder> getProtobufProvisionableEventTypeCollectionOrBuilderList();
    }

    /* loaded from: classes13.dex */
    public interface ProtobufProvisionableEventTypeOrBuilder extends MessageOrBuilder {
        int getEventType();

        ByteString getEventUuid();

        boolean hasEventType();

        boolean hasEventUuid();
    }

    static {
        Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\nNWhisperJoinProtocolBuffersModel/schema/ble/events/ProvisionableEventType.proto\u0012\bprotobuf\"F\n\u001eProtobufProvisionableEventType\u0012\u0011\n\teventUuid\u0018\u0001 \u0002(\f\u0012\u0011\n\teventType\u0018\u0002 \u0002(\u0011\"\u0086\u0001\n(ProtobufProvisionableEventTypeCollection\u0012Z\n(protobufProvisionableEventTypeCollection\u0018\u0001 \u0003(\u000b2(.protobuf.ProtobufProvisionableEventTypeBF\n\u001fcom.amazon.whisperjoin.protobufB#ProtobufProvisionableEventTypeClass"}, new Descriptors.FileDescriptor[0], new Descriptors.FileDescriptor.InternalDescriptorAssigner() { // from class: com.amazon.whisperjoin.protobuf.ProtobufProvisionableEventTypeClass.1
            @Override // com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner
            public ExtensionRegistry assignDescriptors(Descriptors.FileDescriptor fileDescriptor) {
                Descriptors.FileDescriptor unused = ProtobufProvisionableEventTypeClass.descriptor = fileDescriptor;
                return null;
            }
        });
        internal_static_protobuf_ProtobufProvisionableEventType_descriptor = getDescriptor().getMessageTypes().get(0);
        internal_static_protobuf_ProtobufProvisionableEventType_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_protobuf_ProtobufProvisionableEventType_descriptor, new String[]{"EventUuid", "EventType"});
        internal_static_protobuf_ProtobufProvisionableEventTypeCollection_descriptor = getDescriptor().getMessageTypes().get(1);
        internal_static_protobuf_ProtobufProvisionableEventTypeCollection_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_protobuf_ProtobufProvisionableEventTypeCollection_descriptor, new String[]{"ProtobufProvisionableEventTypeCollection"});
    }

    private ProtobufProvisionableEventTypeClass() {
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
