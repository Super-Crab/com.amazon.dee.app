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
import com.google.protobuf.ProtocolMessageEnum;
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
/* loaded from: classes13.dex */
public final class ProtobufProvisioningStatusClass {
    private static Descriptors.FileDescriptor descriptor;
    private static final Descriptors.Descriptor internal_static_protobuf_ProtobufProvisioningStatus_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_protobuf_ProtobufProvisioningStatus_fieldAccessorTable;

    /* loaded from: classes13.dex */
    public static final class ProtobufProvisioningStatus extends GeneratedMessageV3 implements ProtobufProvisioningStatusOrBuilder {
        public static final int INSECUREMODESUPPORTED_FIELD_NUMBER = 2;
        public static final int STATE_FIELD_NUMBER = 1;
        private static final long serialVersionUID = 0;
        private int bitField0_;
        private boolean insecureModeSupported_;
        private byte memoizedIsInitialized;
        private int state_;
        private static final ProtobufProvisioningStatus DEFAULT_INSTANCE = new ProtobufProvisioningStatus();
        @Deprecated
        public static final Parser<ProtobufProvisioningStatus> PARSER = new AbstractParser<ProtobufProvisioningStatus>() { // from class: com.amazon.whisperjoin.protobuf.ProtobufProvisioningStatusClass.ProtobufProvisioningStatus.1
            @Override // com.google.protobuf.Parser
            /* renamed from: parsePartialFrom */
            public ProtobufProvisioningStatus mo10082parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new ProtobufProvisioningStatus(codedInputStream, extensionRegistryLite);
            }
        };

        /* loaded from: classes13.dex */
        public enum State implements ProtocolMessageEnum {
            PROVISIONING_SESSION_IDLE(0),
            WAITING_FOR_PROVISIONER(1),
            AUTHORIZING_PROVISIONER(2),
            ACTIVELY_PROVISIONING(3),
            PROVISIONING_COMPLETE(4),
            PROVISIONING_TERMINATED(5),
            CONNECTED_TO_PROVISIONER(6);
            
            public static final int ACTIVELY_PROVISIONING_VALUE = 3;
            public static final int AUTHORIZING_PROVISIONER_VALUE = 2;
            public static final int CONNECTED_TO_PROVISIONER_VALUE = 6;
            public static final int PROVISIONING_COMPLETE_VALUE = 4;
            public static final int PROVISIONING_SESSION_IDLE_VALUE = 0;
            public static final int PROVISIONING_TERMINATED_VALUE = 5;
            public static final int WAITING_FOR_PROVISIONER_VALUE = 1;
            private final int value;
            private static final Internal.EnumLiteMap<State> internalValueMap = new Internal.EnumLiteMap<State>() { // from class: com.amazon.whisperjoin.protobuf.ProtobufProvisioningStatusClass.ProtobufProvisioningStatus.State.1
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // com.google.protobuf.Internal.EnumLiteMap
                /* renamed from: findValueByNumber */
                public State mo9850findValueByNumber(int i) {
                    return State.forNumber(i);
                }
            };
            private static final State[] VALUES = values();

            State(int i) {
                this.value = i;
            }

            public static State forNumber(int i) {
                switch (i) {
                    case 0:
                        return PROVISIONING_SESSION_IDLE;
                    case 1:
                        return WAITING_FOR_PROVISIONER;
                    case 2:
                        return AUTHORIZING_PROVISIONER;
                    case 3:
                        return ACTIVELY_PROVISIONING;
                    case 4:
                        return PROVISIONING_COMPLETE;
                    case 5:
                        return PROVISIONING_TERMINATED;
                    case 6:
                        return CONNECTED_TO_PROVISIONER;
                    default:
                        return null;
                }
            }

            public static final Descriptors.EnumDescriptor getDescriptor() {
                return ProtobufProvisioningStatus.getDescriptor().getEnumTypes().get(0);
            }

            public static Internal.EnumLiteMap<State> internalGetValueMap() {
                return internalValueMap;
            }

            @Override // com.google.protobuf.ProtocolMessageEnum
            public final Descriptors.EnumDescriptor getDescriptorForType() {
                return getDescriptor();
            }

            @Override // com.google.protobuf.ProtocolMessageEnum, com.google.protobuf.Internal.EnumLite
            public final int getNumber() {
                return this.value;
            }

            @Override // com.google.protobuf.ProtocolMessageEnum
            public final Descriptors.EnumValueDescriptor getValueDescriptor() {
                return getDescriptor().getValues().get(ordinal());
            }

            @Deprecated
            public static State valueOf(int i) {
                return forNumber(i);
            }

            public static State valueOf(Descriptors.EnumValueDescriptor enumValueDescriptor) {
                if (enumValueDescriptor.getType() == getDescriptor()) {
                    return VALUES[enumValueDescriptor.getIndex()];
                }
                throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
            }
        }

        public static ProtobufProvisioningStatus getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return ProtobufProvisioningStatusClass.internal_static_protobuf_ProtobufProvisioningStatus_descriptor;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.mo10081toBuilder();
        }

        public static ProtobufProvisioningStatus parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (ProtobufProvisioningStatus) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static ProtobufProvisioningStatus parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.mo8402parseFrom(byteBuffer);
        }

        public static Parser<ProtobufProvisioningStatus> parser() {
            return PARSER;
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof ProtobufProvisioningStatus)) {
                return super.equals(obj);
            }
            ProtobufProvisioningStatus protobufProvisioningStatus = (ProtobufProvisioningStatus) obj;
            boolean z = hasState() == protobufProvisioningStatus.hasState();
            if (hasState()) {
                z = z && this.state_ == protobufProvisioningStatus.state_;
            }
            boolean z2 = z && hasInsecureModeSupported() == protobufProvisioningStatus.hasInsecureModeSupported();
            if (hasInsecureModeSupported()) {
                z2 = z2 && getInsecureModeSupported() == protobufProvisioningStatus.getInsecureModeSupported();
            }
            return z2 && this.unknownFields.equals(protobufProvisioningStatus.unknownFields);
        }

        @Override // com.amazon.whisperjoin.protobuf.ProtobufProvisioningStatusClass.ProtobufProvisioningStatusOrBuilder
        public boolean getInsecureModeSupported() {
            return this.insecureModeSupported_;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
        /* renamed from: getParserForType */
        public Parser<ProtobufProvisioningStatus> mo9954getParserForType() {
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
                i2 = 0 + CodedOutputStream.computeEnumSize(1, this.state_);
            }
            if ((this.bitField0_ & 2) == 2) {
                i2 += CodedOutputStream.computeBoolSize(2, this.insecureModeSupported_);
            }
            int serializedSize = this.unknownFields.getSerializedSize() + i2;
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.amazon.whisperjoin.protobuf.ProtobufProvisioningStatusClass.ProtobufProvisioningStatusOrBuilder
        public State getState() {
            State valueOf = State.valueOf(this.state_);
            return valueOf == null ? State.PROVISIONING_SESSION_IDLE : valueOf;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        @Override // com.amazon.whisperjoin.protobuf.ProtobufProvisioningStatusClass.ProtobufProvisioningStatusOrBuilder
        public boolean hasInsecureModeSupported() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // com.amazon.whisperjoin.protobuf.ProtobufProvisioningStatusClass.ProtobufProvisioningStatusOrBuilder
        public boolean hasState() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public int hashCode() {
            int i = this.memoizedHashCode;
            if (i != 0) {
                return i;
            }
            int hashCode = getDescriptor().hashCode() + 779;
            if (hasState()) {
                hashCode = GeneratedOutlineSupport1.outline4(hashCode, 37, 1, 53) + this.state_;
            }
            if (hasInsecureModeSupported()) {
                hashCode = GeneratedOutlineSupport1.outline4(hashCode, 37, 2, 53) + Internal.hashBoolean(getInsecureModeSupported());
            }
            int hashCode2 = this.unknownFields.hashCode() + (hashCode * 29);
            this.memoizedHashCode = hashCode2;
            return hashCode2;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return ProtobufProvisioningStatusClass.internal_static_protobuf_ProtobufProvisioningStatus_fieldAccessorTable.ensureFieldAccessorsInitialized(ProtobufProvisioningStatus.class, Builder.class);
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
            if (!hasState()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            } else if (!hasInsecureModeSupported()) {
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
                codedOutputStream.writeEnum(1, this.state_);
            }
            if ((this.bitField0_ & 2) == 2) {
                codedOutputStream.writeBool(2, this.insecureModeSupported_);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        /* loaded from: classes13.dex */
        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements ProtobufProvisioningStatusOrBuilder {
            private int bitField0_;
            private boolean insecureModeSupported_;
            private int state_;

            public static final Descriptors.Descriptor getDescriptor() {
                return ProtobufProvisioningStatusClass.internal_static_protobuf_ProtobufProvisioningStatus_descriptor;
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = GeneratedMessageV3.alwaysUseFieldBuilders;
            }

            public Builder clearInsecureModeSupported() {
                this.bitField0_ &= -3;
                this.insecureModeSupported_ = false;
                onChanged();
                return this;
            }

            public Builder clearState() {
                this.bitField0_ &= -2;
                this.state_ = 0;
                onChanged();
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return ProtobufProvisioningStatusClass.internal_static_protobuf_ProtobufProvisioningStatus_descriptor;
            }

            @Override // com.amazon.whisperjoin.protobuf.ProtobufProvisioningStatusClass.ProtobufProvisioningStatusOrBuilder
            public boolean getInsecureModeSupported() {
                return this.insecureModeSupported_;
            }

            @Override // com.amazon.whisperjoin.protobuf.ProtobufProvisioningStatusClass.ProtobufProvisioningStatusOrBuilder
            public State getState() {
                State valueOf = State.valueOf(this.state_);
                return valueOf == null ? State.PROVISIONING_SESSION_IDLE : valueOf;
            }

            @Override // com.amazon.whisperjoin.protobuf.ProtobufProvisioningStatusClass.ProtobufProvisioningStatusOrBuilder
            public boolean hasInsecureModeSupported() {
                return (this.bitField0_ & 2) == 2;
            }

            @Override // com.amazon.whisperjoin.protobuf.ProtobufProvisioningStatusClass.ProtobufProvisioningStatusOrBuilder
            public boolean hasState() {
                return (this.bitField0_ & 1) == 1;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return ProtobufProvisioningStatusClass.internal_static_protobuf_ProtobufProvisioningStatus_fieldAccessorTable.ensureFieldAccessorsInitialized(ProtobufProvisioningStatus.class, Builder.class);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return hasState() && hasInsecureModeSupported();
            }

            public Builder setInsecureModeSupported(boolean z) {
                this.bitField0_ |= 2;
                this.insecureModeSupported_ = z;
                onChanged();
                return this;
            }

            public Builder setState(State state) {
                if (state != null) {
                    this.bitField0_ |= 1;
                    this.state_ = state.getNumber();
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            private Builder() {
                this.state_ = 0;
                maybeForceBuilderInitialization();
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            /* renamed from: addRepeatedField */
            public Builder mo10083addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.mo10083addRepeatedField(fieldDescriptor, obj);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: build */
            public ProtobufProvisioningStatus mo10084build() {
                ProtobufProvisioningStatus mo10085buildPartial = mo10085buildPartial();
                if (mo10085buildPartial.isInitialized()) {
                    return mo10085buildPartial;
                }
                throw AbstractMessage.Builder.newUninitializedMessageException((Message) mo10085buildPartial);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: buildPartial */
            public ProtobufProvisioningStatus mo10085buildPartial() {
                ProtobufProvisioningStatus protobufProvisioningStatus = new ProtobufProvisioningStatus(this);
                int i = this.bitField0_;
                int i2 = 1;
                if ((i & 1) != 1) {
                    i2 = 0;
                }
                protobufProvisioningStatus.state_ = this.state_;
                if ((i & 2) == 2) {
                    i2 |= 2;
                }
                protobufProvisioningStatus.insecureModeSupported_ = this.insecureModeSupported_;
                protobufProvisioningStatus.bitField0_ = i2;
                onBuilt();
                return protobufProvisioningStatus;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            /* renamed from: clearField */
            public Builder mo10088clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (Builder) super.mo10088clearField(fieldDescriptor);
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            /* renamed from: getDefaultInstanceForType */
            public ProtobufProvisioningStatus mo10094getDefaultInstanceForType() {
                return ProtobufProvisioningStatus.getDefaultInstance();
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
                this.state_ = 0;
                this.bitField0_ &= -2;
                this.insecureModeSupported_ = false;
                this.bitField0_ &= -3;
                return this;
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.state_ = 0;
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
                if (message instanceof ProtobufProvisioningStatus) {
                    return mergeFrom((ProtobufProvisioningStatus) message);
                }
                super.mo10096mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(ProtobufProvisioningStatus protobufProvisioningStatus) {
                if (protobufProvisioningStatus == ProtobufProvisioningStatus.getDefaultInstance()) {
                    return this;
                }
                if (protobufProvisioningStatus.hasState()) {
                    setState(protobufProvisioningStatus.getState());
                }
                if (protobufProvisioningStatus.hasInsecureModeSupported()) {
                    setInsecureModeSupported(protobufProvisioningStatus.getInsecureModeSupported());
                }
                mo10099mergeUnknownFields(((GeneratedMessageV3) protobufProvisioningStatus).unknownFields);
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
            public com.amazon.whisperjoin.protobuf.ProtobufProvisioningStatusClass.ProtobufProvisioningStatus.Builder mo10097mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
                /*
                    r2 = this;
                    r0 = 0
                    com.google.protobuf.Parser<com.amazon.whisperjoin.protobuf.ProtobufProvisioningStatusClass$ProtobufProvisioningStatus> r1 = com.amazon.whisperjoin.protobuf.ProtobufProvisioningStatusClass.ProtobufProvisioningStatus.PARSER     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                    java.lang.Object r3 = r1.mo10082parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                    com.amazon.whisperjoin.protobuf.ProtobufProvisioningStatusClass$ProtobufProvisioningStatus r3 = (com.amazon.whisperjoin.protobuf.ProtobufProvisioningStatusClass.ProtobufProvisioningStatus) r3     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
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
                    com.amazon.whisperjoin.protobuf.ProtobufProvisioningStatusClass$ProtobufProvisioningStatus r4 = (com.amazon.whisperjoin.protobuf.ProtobufProvisioningStatusClass.ProtobufProvisioningStatus) r4     // Catch: java.lang.Throwable -> Lf
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
                throw new UnsupportedOperationException("Method not decompiled: com.amazon.whisperjoin.protobuf.ProtobufProvisioningStatusClass.ProtobufProvisioningStatus.Builder.mo10097mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.amazon.whisperjoin.protobuf.ProtobufProvisioningStatusClass$ProtobufProvisioningStatus$Builder");
            }
        }

        public static Builder newBuilder(ProtobufProvisioningStatus protobufProvisioningStatus) {
            return DEFAULT_INSTANCE.mo10081toBuilder().mergeFrom(protobufProvisioningStatus);
        }

        public static ProtobufProvisioningStatus parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.mo8403parseFrom(byteBuffer, extensionRegistryLite);
        }

        private ProtobufProvisioningStatus(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        public static ProtobufProvisioningStatus parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ProtobufProvisioningStatus) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static ProtobufProvisioningStatus parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.mo8396parseFrom(byteString);
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        /* renamed from: getDefaultInstanceForType */
        public ProtobufProvisioningStatus mo10094getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        /* renamed from: toBuilder */
        public Builder mo10081toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        public static ProtobufProvisioningStatus parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.mo8397parseFrom(byteString, extensionRegistryLite);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        /* renamed from: newBuilderForType */
        public Builder mo10079newBuilderForType() {
            return newBuilder();
        }

        private ProtobufProvisioningStatus() {
            this.memoizedIsInitialized = (byte) -1;
            this.state_ = 0;
            this.insecureModeSupported_ = false;
        }

        public static ProtobufProvisioningStatus parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.mo8404parseFrom(bArr);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageV3
        /* renamed from: newBuilderForType */
        public Builder mo10080newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public static ProtobufProvisioningStatus parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.mo8407parseFrom(bArr, extensionRegistryLite);
        }

        public static ProtobufProvisioningStatus parseFrom(InputStream inputStream) throws IOException {
            return (ProtobufProvisioningStatus) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        private ProtobufProvisioningStatus(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                    int readEnum = codedInputStream.readEnum();
                                    if (State.valueOf(readEnum) == null) {
                                        newBuilder.mergeVarintField(1, readEnum);
                                    } else {
                                        this.bitField0_ = 1 | this.bitField0_;
                                        this.state_ = readEnum;
                                    }
                                } else if (readTag != 16) {
                                    if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                    }
                                } else {
                                    this.bitField0_ |= 2;
                                    this.insecureModeSupported_ = codedInputStream.readBool();
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

        public static ProtobufProvisioningStatus parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ProtobufProvisioningStatus) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static ProtobufProvisioningStatus parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (ProtobufProvisioningStatus) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static ProtobufProvisioningStatus parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ProtobufProvisioningStatus) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes13.dex */
    public interface ProtobufProvisioningStatusOrBuilder extends MessageOrBuilder {
        boolean getInsecureModeSupported();

        ProtobufProvisioningStatus.State getState();

        boolean hasInsecureModeSupported();

        boolean hasState();
    }

    static {
        Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\nLWhisperJoinProtocolBuffersModel/schema/provisioning/ProvisioningStatus.proto\u0012\bprotobuf\"Ê\u0002\n\u001aProtobufProvisioningStatus\u00129\n\u0005state\u0018\u0001 \u0002(\u000e2*.protobuf.ProtobufProvisioningStatus.State\u0012\u001d\n\u0015insecureModeSupported\u0018\u0002 \u0002(\b\"Ñ\u0001\n\u0005State\u0012\u001d\n\u0019PROVISIONING_SESSION_IDLE\u0010\u0000\u0012\u001b\n\u0017WAITING_FOR_PROVISIONER\u0010\u0001\u0012\u001b\n\u0017AUTHORIZING_PROVISIONER\u0010\u0002\u0012\u0019\n\u0015ACTIVELY_PROVISIONING\u0010\u0003\u0012\u0019\n\u0015PROVISIONING_COMPLETE\u0010\u0004\u0012\u001b\n\u0017PROVISIONING_TERMINATED\u0010\u0005\u0012\u001c\n\u0018CONNECTED_TO_PROVISIONER\u0010\u0006BB\n\u001fcom.amazon.whisperjoin.protobufB\u001fProtobufProvisioningStatusClass"}, new Descriptors.FileDescriptor[0], new Descriptors.FileDescriptor.InternalDescriptorAssigner() { // from class: com.amazon.whisperjoin.protobuf.ProtobufProvisioningStatusClass.1
            @Override // com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner
            public ExtensionRegistry assignDescriptors(Descriptors.FileDescriptor fileDescriptor) {
                Descriptors.FileDescriptor unused = ProtobufProvisioningStatusClass.descriptor = fileDescriptor;
                return null;
            }
        });
        internal_static_protobuf_ProtobufProvisioningStatus_descriptor = getDescriptor().getMessageTypes().get(0);
        internal_static_protobuf_ProtobufProvisioningStatus_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_protobuf_ProtobufProvisioningStatus_descriptor, new String[]{"State", "InsecureModeSupported"});
    }

    private ProtobufProvisioningStatusClass() {
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
