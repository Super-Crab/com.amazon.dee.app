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
public final class ProtobufProvisionableEventNotificationClass {
    private static Descriptors.FileDescriptor descriptor;
    private static final Descriptors.Descriptor internal_static_protobuf_ProtobufProvisionableEventNotification_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_protobuf_ProtobufProvisionableEventNotification_fieldAccessorTable;

    /* loaded from: classes13.dex */
    public static final class ProtobufProvisionableEventNotification extends GeneratedMessageV3 implements ProtobufProvisionableEventNotificationOrBuilder {
        public static final int EVENTKEY_FIELD_NUMBER = 1;
        public static final int EVENTTYPE_FIELD_NUMBER = 2;
        private static final long serialVersionUID = 0;
        private int bitField0_;
        private int eventKey_;
        private int eventType_;
        private byte memoizedIsInitialized;
        private static final ProtobufProvisionableEventNotification DEFAULT_INSTANCE = new ProtobufProvisionableEventNotification();
        @Deprecated
        public static final Parser<ProtobufProvisionableEventNotification> PARSER = new AbstractParser<ProtobufProvisionableEventNotification>() { // from class: com.amazon.whisperjoin.protobuf.ProtobufProvisionableEventNotificationClass.ProtobufProvisionableEventNotification.1
            @Override // com.google.protobuf.Parser
            /* renamed from: parsePartialFrom */
            public ProtobufProvisionableEventNotification mo10082parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new ProtobufProvisionableEventNotification(codedInputStream, extensionRegistryLite);
            }
        };

        public static ProtobufProvisionableEventNotification getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return ProtobufProvisionableEventNotificationClass.internal_static_protobuf_ProtobufProvisionableEventNotification_descriptor;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.mo10081toBuilder();
        }

        public static ProtobufProvisionableEventNotification parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (ProtobufProvisionableEventNotification) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static ProtobufProvisionableEventNotification parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.mo8402parseFrom(byteBuffer);
        }

        public static Parser<ProtobufProvisionableEventNotification> parser() {
            return PARSER;
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof ProtobufProvisionableEventNotification)) {
                return super.equals(obj);
            }
            ProtobufProvisionableEventNotification protobufProvisionableEventNotification = (ProtobufProvisionableEventNotification) obj;
            boolean z = hasEventKey() == protobufProvisionableEventNotification.hasEventKey();
            if (hasEventKey()) {
                z = z && getEventKey() == protobufProvisionableEventNotification.getEventKey();
            }
            boolean z2 = z && hasEventType() == protobufProvisionableEventNotification.hasEventType();
            if (hasEventType()) {
                z2 = z2 && getEventType() == protobufProvisionableEventNotification.getEventType();
            }
            return z2 && this.unknownFields.equals(protobufProvisionableEventNotification.unknownFields);
        }

        @Override // com.amazon.whisperjoin.protobuf.ProtobufProvisionableEventNotificationClass.ProtobufProvisionableEventNotificationOrBuilder
        public int getEventKey() {
            return this.eventKey_;
        }

        @Override // com.amazon.whisperjoin.protobuf.ProtobufProvisionableEventNotificationClass.ProtobufProvisionableEventNotificationOrBuilder
        public int getEventType() {
            return this.eventType_;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
        /* renamed from: getParserForType */
        public Parser<ProtobufProvisionableEventNotification> mo9954getParserForType() {
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
                i2 = 0 + CodedOutputStream.computeSInt32Size(1, this.eventKey_);
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

        @Override // com.amazon.whisperjoin.protobuf.ProtobufProvisionableEventNotificationClass.ProtobufProvisionableEventNotificationOrBuilder
        public boolean hasEventKey() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // com.amazon.whisperjoin.protobuf.ProtobufProvisionableEventNotificationClass.ProtobufProvisionableEventNotificationOrBuilder
        public boolean hasEventType() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public int hashCode() {
            int i = this.memoizedHashCode;
            if (i != 0) {
                return i;
            }
            int hashCode = getDescriptor().hashCode() + 779;
            if (hasEventKey()) {
                hashCode = GeneratedOutlineSupport1.outline4(hashCode, 37, 1, 53) + getEventKey();
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
            return ProtobufProvisionableEventNotificationClass.internal_static_protobuf_ProtobufProvisionableEventNotification_fieldAccessorTable.ensureFieldAccessorsInitialized(ProtobufProvisionableEventNotification.class, Builder.class);
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
            if (!hasEventKey()) {
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
                codedOutputStream.writeSInt32(1, this.eventKey_);
            }
            if ((this.bitField0_ & 2) == 2) {
                codedOutputStream.writeSInt32(2, this.eventType_);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        /* loaded from: classes13.dex */
        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements ProtobufProvisionableEventNotificationOrBuilder {
            private int bitField0_;
            private int eventKey_;
            private int eventType_;

            public static final Descriptors.Descriptor getDescriptor() {
                return ProtobufProvisionableEventNotificationClass.internal_static_protobuf_ProtobufProvisionableEventNotification_descriptor;
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = GeneratedMessageV3.alwaysUseFieldBuilders;
            }

            public Builder clearEventKey() {
                this.bitField0_ &= -2;
                this.eventKey_ = 0;
                onChanged();
                return this;
            }

            public Builder clearEventType() {
                this.bitField0_ &= -3;
                this.eventType_ = 0;
                onChanged();
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return ProtobufProvisionableEventNotificationClass.internal_static_protobuf_ProtobufProvisionableEventNotification_descriptor;
            }

            @Override // com.amazon.whisperjoin.protobuf.ProtobufProvisionableEventNotificationClass.ProtobufProvisionableEventNotificationOrBuilder
            public int getEventKey() {
                return this.eventKey_;
            }

            @Override // com.amazon.whisperjoin.protobuf.ProtobufProvisionableEventNotificationClass.ProtobufProvisionableEventNotificationOrBuilder
            public int getEventType() {
                return this.eventType_;
            }

            @Override // com.amazon.whisperjoin.protobuf.ProtobufProvisionableEventNotificationClass.ProtobufProvisionableEventNotificationOrBuilder
            public boolean hasEventKey() {
                return (this.bitField0_ & 1) == 1;
            }

            @Override // com.amazon.whisperjoin.protobuf.ProtobufProvisionableEventNotificationClass.ProtobufProvisionableEventNotificationOrBuilder
            public boolean hasEventType() {
                return (this.bitField0_ & 2) == 2;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return ProtobufProvisionableEventNotificationClass.internal_static_protobuf_ProtobufProvisionableEventNotification_fieldAccessorTable.ensureFieldAccessorsInitialized(ProtobufProvisionableEventNotification.class, Builder.class);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return hasEventKey() && hasEventType();
            }

            public Builder setEventKey(int i) {
                this.bitField0_ |= 1;
                this.eventKey_ = i;
                onChanged();
                return this;
            }

            public Builder setEventType(int i) {
                this.bitField0_ |= 2;
                this.eventType_ = i;
                onChanged();
                return this;
            }

            private Builder() {
                maybeForceBuilderInitialization();
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            /* renamed from: addRepeatedField */
            public Builder mo10083addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.mo10083addRepeatedField(fieldDescriptor, obj);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: build */
            public ProtobufProvisionableEventNotification mo10084build() {
                ProtobufProvisionableEventNotification mo10085buildPartial = mo10085buildPartial();
                if (mo10085buildPartial.isInitialized()) {
                    return mo10085buildPartial;
                }
                throw AbstractMessage.Builder.newUninitializedMessageException((Message) mo10085buildPartial);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: buildPartial */
            public ProtobufProvisionableEventNotification mo10085buildPartial() {
                ProtobufProvisionableEventNotification protobufProvisionableEventNotification = new ProtobufProvisionableEventNotification(this);
                int i = this.bitField0_;
                int i2 = 1;
                if ((i & 1) != 1) {
                    i2 = 0;
                }
                protobufProvisionableEventNotification.eventKey_ = this.eventKey_;
                if ((i & 2) == 2) {
                    i2 |= 2;
                }
                protobufProvisionableEventNotification.eventType_ = this.eventType_;
                protobufProvisionableEventNotification.bitField0_ = i2;
                onBuilt();
                return protobufProvisionableEventNotification;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            /* renamed from: clearField */
            public Builder mo10088clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (Builder) super.mo10088clearField(fieldDescriptor);
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            /* renamed from: getDefaultInstanceForType */
            public ProtobufProvisionableEventNotification mo10094getDefaultInstanceForType() {
                return ProtobufProvisionableEventNotification.getDefaultInstance();
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

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                maybeForceBuilderInitialization();
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: clear */
            public Builder mo10087clear() {
                super.mo10087clear();
                this.eventKey_ = 0;
                this.bitField0_ &= -2;
                this.eventType_ = 0;
                this.bitField0_ &= -3;
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
                if (message instanceof ProtobufProvisionableEventNotification) {
                    return mergeFrom((ProtobufProvisionableEventNotification) message);
                }
                super.mo10096mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(ProtobufProvisionableEventNotification protobufProvisionableEventNotification) {
                if (protobufProvisionableEventNotification == ProtobufProvisionableEventNotification.getDefaultInstance()) {
                    return this;
                }
                if (protobufProvisionableEventNotification.hasEventKey()) {
                    setEventKey(protobufProvisionableEventNotification.getEventKey());
                }
                if (protobufProvisionableEventNotification.hasEventType()) {
                    setEventType(protobufProvisionableEventNotification.getEventType());
                }
                mo10099mergeUnknownFields(((GeneratedMessageV3) protobufProvisionableEventNotification).unknownFields);
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
            public com.amazon.whisperjoin.protobuf.ProtobufProvisionableEventNotificationClass.ProtobufProvisionableEventNotification.Builder mo10097mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
                /*
                    r2 = this;
                    r0 = 0
                    com.google.protobuf.Parser<com.amazon.whisperjoin.protobuf.ProtobufProvisionableEventNotificationClass$ProtobufProvisionableEventNotification> r1 = com.amazon.whisperjoin.protobuf.ProtobufProvisionableEventNotificationClass.ProtobufProvisionableEventNotification.PARSER     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                    java.lang.Object r3 = r1.mo10082parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                    com.amazon.whisperjoin.protobuf.ProtobufProvisionableEventNotificationClass$ProtobufProvisionableEventNotification r3 = (com.amazon.whisperjoin.protobuf.ProtobufProvisionableEventNotificationClass.ProtobufProvisionableEventNotification) r3     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
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
                    com.amazon.whisperjoin.protobuf.ProtobufProvisionableEventNotificationClass$ProtobufProvisionableEventNotification r4 = (com.amazon.whisperjoin.protobuf.ProtobufProvisionableEventNotificationClass.ProtobufProvisionableEventNotification) r4     // Catch: java.lang.Throwable -> Lf
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
                throw new UnsupportedOperationException("Method not decompiled: com.amazon.whisperjoin.protobuf.ProtobufProvisionableEventNotificationClass.ProtobufProvisionableEventNotification.Builder.mo10097mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.amazon.whisperjoin.protobuf.ProtobufProvisionableEventNotificationClass$ProtobufProvisionableEventNotification$Builder");
            }
        }

        public static Builder newBuilder(ProtobufProvisionableEventNotification protobufProvisionableEventNotification) {
            return DEFAULT_INSTANCE.mo10081toBuilder().mergeFrom(protobufProvisionableEventNotification);
        }

        public static ProtobufProvisionableEventNotification parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.mo8403parseFrom(byteBuffer, extensionRegistryLite);
        }

        private ProtobufProvisionableEventNotification(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        public static ProtobufProvisionableEventNotification parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ProtobufProvisionableEventNotification) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static ProtobufProvisionableEventNotification parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.mo8396parseFrom(byteString);
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        /* renamed from: getDefaultInstanceForType */
        public ProtobufProvisionableEventNotification mo10094getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        /* renamed from: toBuilder */
        public Builder mo10081toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        public static ProtobufProvisionableEventNotification parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.mo8397parseFrom(byteString, extensionRegistryLite);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        /* renamed from: newBuilderForType */
        public Builder mo10079newBuilderForType() {
            return newBuilder();
        }

        private ProtobufProvisionableEventNotification() {
            this.memoizedIsInitialized = (byte) -1;
            this.eventKey_ = 0;
            this.eventType_ = 0;
        }

        public static ProtobufProvisionableEventNotification parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.mo8404parseFrom(bArr);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageV3
        /* renamed from: newBuilderForType */
        public Builder mo10080newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public static ProtobufProvisionableEventNotification parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.mo8407parseFrom(bArr, extensionRegistryLite);
        }

        public static ProtobufProvisionableEventNotification parseFrom(InputStream inputStream) throws IOException {
            return (ProtobufProvisionableEventNotification) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        private ProtobufProvisionableEventNotification(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            this();
            if (extensionRegistryLite != null) {
                UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
                boolean z = false;
                while (!z) {
                    try {
                        try {
                            int readTag = codedInputStream.readTag();
                            if (readTag != 0) {
                                if (readTag == 8) {
                                    this.bitField0_ |= 1;
                                    this.eventKey_ = codedInputStream.readSInt32();
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

        public static ProtobufProvisionableEventNotification parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ProtobufProvisionableEventNotification) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static ProtobufProvisionableEventNotification parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (ProtobufProvisionableEventNotification) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static ProtobufProvisionableEventNotification parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ProtobufProvisionableEventNotification) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes13.dex */
    public interface ProtobufProvisionableEventNotificationOrBuilder extends MessageOrBuilder {
        int getEventKey();

        int getEventType();

        boolean hasEventKey();

        boolean hasEventType();
    }

    static {
        Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\nVWhisperJoinProtocolBuffersModel/schema/ble/events/ProvisionableEventNotification.proto\u0012\bprotobuf\"M\n&ProtobufProvisionableEventNotification\u0012\u0010\n\beventKey\u0018\u0001 \u0002(\u0011\u0012\u0011\n\teventType\u0018\u0002 \u0002(\u0011BN\n\u001fcom.amazon.whisperjoin.protobufB+ProtobufProvisionableEventNotificationClass"}, new Descriptors.FileDescriptor[0], new Descriptors.FileDescriptor.InternalDescriptorAssigner() { // from class: com.amazon.whisperjoin.protobuf.ProtobufProvisionableEventNotificationClass.1
            @Override // com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner
            public ExtensionRegistry assignDescriptors(Descriptors.FileDescriptor fileDescriptor) {
                Descriptors.FileDescriptor unused = ProtobufProvisionableEventNotificationClass.descriptor = fileDescriptor;
                return null;
            }
        });
        internal_static_protobuf_ProtobufProvisionableEventNotification_descriptor = getDescriptor().getMessageTypes().get(0);
        internal_static_protobuf_ProtobufProvisionableEventNotification_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_protobuf_ProtobufProvisionableEventNotification_descriptor, new String[]{"EventKey", "EventType"});
    }

    private ProtobufProvisionableEventNotificationClass() {
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
