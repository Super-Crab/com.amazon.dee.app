package com.amazon.whisperjoin.protobuf;

import com.amazon.dee.app.services.metrics.AlexaMetricsConstants;
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
public final class ProtobufCBLRegistrationDetailsClass {
    private static Descriptors.FileDescriptor descriptor;
    private static final Descriptors.Descriptor internal_static_protobuf_ProtobufCBLRegistrationDetails_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_protobuf_ProtobufCBLRegistrationDetails_fieldAccessorTable;

    /* loaded from: classes13.dex */
    public static final class ProtobufCBLRegistrationDetails extends GeneratedMessageV3 implements ProtobufCBLRegistrationDetailsOrBuilder {
        public static final int HTTPCODE_FIELD_NUMBER = 3;
        public static final int MESSAGE_FIELD_NUMBER = 2;
        public static final int STATE_FIELD_NUMBER = 1;
        private static final long serialVersionUID = 0;
        private int bitField0_;
        private int httpCode_;
        private byte memoizedIsInitialized;
        private volatile Object message_;
        private int state_;
        private static final ProtobufCBLRegistrationDetails DEFAULT_INSTANCE = new ProtobufCBLRegistrationDetails();
        @Deprecated
        public static final Parser<ProtobufCBLRegistrationDetails> PARSER = new AbstractParser<ProtobufCBLRegistrationDetails>() { // from class: com.amazon.whisperjoin.protobuf.ProtobufCBLRegistrationDetailsClass.ProtobufCBLRegistrationDetails.1
            @Override // com.google.protobuf.Parser
            /* renamed from: parsePartialFrom */
            public ProtobufCBLRegistrationDetails mo10082parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new ProtobufCBLRegistrationDetails(codedInputStream, extensionRegistryLite);
            }
        };

        /* loaded from: classes13.dex */
        public enum State implements ProtocolMessageEnum {
            NOT_REGISTERED(0),
            COMPLETING_REGISTRATION(1),
            REGISTRATION_COMPLETE(2),
            REGISTRATION_FAILED_TOKEN_INVALID(-1),
            REGISTRATION_FAILED_TOKEN_EXPIRED(-2),
            REGISTRATION_FAILED_SERVER_NOT_REACHABLE(-3),
            REGISTRATION_FAILED_SERVER_ERROR(-4),
            REGISTRATION_FAILED_OTHER(-5);
            
            public static final int COMPLETING_REGISTRATION_VALUE = 1;
            public static final int NOT_REGISTERED_VALUE = 0;
            public static final int REGISTRATION_COMPLETE_VALUE = 2;
            public static final int REGISTRATION_FAILED_OTHER_VALUE = -5;
            public static final int REGISTRATION_FAILED_SERVER_ERROR_VALUE = -4;
            public static final int REGISTRATION_FAILED_SERVER_NOT_REACHABLE_VALUE = -3;
            public static final int REGISTRATION_FAILED_TOKEN_EXPIRED_VALUE = -2;
            public static final int REGISTRATION_FAILED_TOKEN_INVALID_VALUE = -1;
            private final int value;
            private static final Internal.EnumLiteMap<State> internalValueMap = new Internal.EnumLiteMap<State>() { // from class: com.amazon.whisperjoin.protobuf.ProtobufCBLRegistrationDetailsClass.ProtobufCBLRegistrationDetails.State.1
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
                    case -5:
                        return REGISTRATION_FAILED_OTHER;
                    case -4:
                        return REGISTRATION_FAILED_SERVER_ERROR;
                    case -3:
                        return REGISTRATION_FAILED_SERVER_NOT_REACHABLE;
                    case -2:
                        return REGISTRATION_FAILED_TOKEN_EXPIRED;
                    case -1:
                        return REGISTRATION_FAILED_TOKEN_INVALID;
                    case 0:
                        return NOT_REGISTERED;
                    case 1:
                        return COMPLETING_REGISTRATION;
                    case 2:
                        return REGISTRATION_COMPLETE;
                    default:
                        return null;
                }
            }

            public static final Descriptors.EnumDescriptor getDescriptor() {
                return ProtobufCBLRegistrationDetails.getDescriptor().getEnumTypes().get(0);
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

        public static ProtobufCBLRegistrationDetails getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return ProtobufCBLRegistrationDetailsClass.internal_static_protobuf_ProtobufCBLRegistrationDetails_descriptor;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.mo10081toBuilder();
        }

        public static ProtobufCBLRegistrationDetails parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (ProtobufCBLRegistrationDetails) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static ProtobufCBLRegistrationDetails parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.mo8402parseFrom(byteBuffer);
        }

        public static Parser<ProtobufCBLRegistrationDetails> parser() {
            return PARSER;
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof ProtobufCBLRegistrationDetails)) {
                return super.equals(obj);
            }
            ProtobufCBLRegistrationDetails protobufCBLRegistrationDetails = (ProtobufCBLRegistrationDetails) obj;
            boolean z = hasState() == protobufCBLRegistrationDetails.hasState();
            if (hasState()) {
                z = z && this.state_ == protobufCBLRegistrationDetails.state_;
            }
            boolean z2 = z && hasMessage() == protobufCBLRegistrationDetails.hasMessage();
            if (hasMessage()) {
                z2 = z2 && getMessage().equals(protobufCBLRegistrationDetails.getMessage());
            }
            boolean z3 = z2 && hasHttpCode() == protobufCBLRegistrationDetails.hasHttpCode();
            if (hasHttpCode()) {
                z3 = z3 && getHttpCode() == protobufCBLRegistrationDetails.getHttpCode();
            }
            return z3 && this.unknownFields.equals(protobufCBLRegistrationDetails.unknownFields);
        }

        @Override // com.amazon.whisperjoin.protobuf.ProtobufCBLRegistrationDetailsClass.ProtobufCBLRegistrationDetailsOrBuilder
        public int getHttpCode() {
            return this.httpCode_;
        }

        @Override // com.amazon.whisperjoin.protobuf.ProtobufCBLRegistrationDetailsClass.ProtobufCBLRegistrationDetailsOrBuilder
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

        @Override // com.amazon.whisperjoin.protobuf.ProtobufCBLRegistrationDetailsClass.ProtobufCBLRegistrationDetailsOrBuilder
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
        public Parser<ProtobufCBLRegistrationDetails> mo9954getParserForType() {
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
                i2 += GeneratedMessageV3.computeStringSize(2, this.message_);
            }
            if ((this.bitField0_ & 4) == 4) {
                i2 += CodedOutputStream.computeSInt32Size(3, this.httpCode_);
            }
            int serializedSize = this.unknownFields.getSerializedSize() + i2;
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.amazon.whisperjoin.protobuf.ProtobufCBLRegistrationDetailsClass.ProtobufCBLRegistrationDetailsOrBuilder
        public State getState() {
            State valueOf = State.valueOf(this.state_);
            return valueOf == null ? State.NOT_REGISTERED : valueOf;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        @Override // com.amazon.whisperjoin.protobuf.ProtobufCBLRegistrationDetailsClass.ProtobufCBLRegistrationDetailsOrBuilder
        public boolean hasHttpCode() {
            return (this.bitField0_ & 4) == 4;
        }

        @Override // com.amazon.whisperjoin.protobuf.ProtobufCBLRegistrationDetailsClass.ProtobufCBLRegistrationDetailsOrBuilder
        public boolean hasMessage() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // com.amazon.whisperjoin.protobuf.ProtobufCBLRegistrationDetailsClass.ProtobufCBLRegistrationDetailsOrBuilder
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
            if (hasMessage()) {
                hashCode = GeneratedOutlineSupport1.outline4(hashCode, 37, 2, 53) + getMessage().hashCode();
            }
            if (hasHttpCode()) {
                hashCode = GeneratedOutlineSupport1.outline4(hashCode, 37, 3, 53) + getHttpCode();
            }
            int hashCode2 = this.unknownFields.hashCode() + (hashCode * 29);
            this.memoizedHashCode = hashCode2;
            return hashCode2;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return ProtobufCBLRegistrationDetailsClass.internal_static_protobuf_ProtobufCBLRegistrationDetails_fieldAccessorTable.ensureFieldAccessorsInitialized(ProtobufCBLRegistrationDetails.class, Builder.class);
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
            }
            this.memoizedIsInitialized = (byte) 1;
            return true;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                codedOutputStream.writeEnum(1, this.state_);
            }
            if ((this.bitField0_ & 2) == 2) {
                GeneratedMessageV3.writeString(codedOutputStream, 2, this.message_);
            }
            if ((this.bitField0_ & 4) == 4) {
                codedOutputStream.writeSInt32(3, this.httpCode_);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        /* loaded from: classes13.dex */
        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements ProtobufCBLRegistrationDetailsOrBuilder {
            private int bitField0_;
            private int httpCode_;
            private Object message_;
            private int state_;

            public static final Descriptors.Descriptor getDescriptor() {
                return ProtobufCBLRegistrationDetailsClass.internal_static_protobuf_ProtobufCBLRegistrationDetails_descriptor;
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = GeneratedMessageV3.alwaysUseFieldBuilders;
            }

            public Builder clearHttpCode() {
                this.bitField0_ &= -5;
                this.httpCode_ = 0;
                onChanged();
                return this;
            }

            public Builder clearMessage() {
                this.bitField0_ &= -3;
                this.message_ = ProtobufCBLRegistrationDetails.getDefaultInstance().getMessage();
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
                return ProtobufCBLRegistrationDetailsClass.internal_static_protobuf_ProtobufCBLRegistrationDetails_descriptor;
            }

            @Override // com.amazon.whisperjoin.protobuf.ProtobufCBLRegistrationDetailsClass.ProtobufCBLRegistrationDetailsOrBuilder
            public int getHttpCode() {
                return this.httpCode_;
            }

            @Override // com.amazon.whisperjoin.protobuf.ProtobufCBLRegistrationDetailsClass.ProtobufCBLRegistrationDetailsOrBuilder
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

            @Override // com.amazon.whisperjoin.protobuf.ProtobufCBLRegistrationDetailsClass.ProtobufCBLRegistrationDetailsOrBuilder
            public ByteString getMessageBytes() {
                Object obj = this.message_;
                if (obj instanceof String) {
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.message_ = copyFromUtf8;
                    return copyFromUtf8;
                }
                return (ByteString) obj;
            }

            @Override // com.amazon.whisperjoin.protobuf.ProtobufCBLRegistrationDetailsClass.ProtobufCBLRegistrationDetailsOrBuilder
            public State getState() {
                State valueOf = State.valueOf(this.state_);
                return valueOf == null ? State.NOT_REGISTERED : valueOf;
            }

            @Override // com.amazon.whisperjoin.protobuf.ProtobufCBLRegistrationDetailsClass.ProtobufCBLRegistrationDetailsOrBuilder
            public boolean hasHttpCode() {
                return (this.bitField0_ & 4) == 4;
            }

            @Override // com.amazon.whisperjoin.protobuf.ProtobufCBLRegistrationDetailsClass.ProtobufCBLRegistrationDetailsOrBuilder
            public boolean hasMessage() {
                return (this.bitField0_ & 2) == 2;
            }

            @Override // com.amazon.whisperjoin.protobuf.ProtobufCBLRegistrationDetailsClass.ProtobufCBLRegistrationDetailsOrBuilder
            public boolean hasState() {
                return (this.bitField0_ & 1) == 1;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return ProtobufCBLRegistrationDetailsClass.internal_static_protobuf_ProtobufCBLRegistrationDetails_fieldAccessorTable.ensureFieldAccessorsInitialized(ProtobufCBLRegistrationDetails.class, Builder.class);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return hasState();
            }

            public Builder setHttpCode(int i) {
                this.bitField0_ |= 4;
                this.httpCode_ = i;
                onChanged();
                return this;
            }

            public Builder setMessage(String str) {
                if (str != null) {
                    this.bitField0_ |= 2;
                    this.message_ = str;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder setMessageBytes(ByteString byteString) {
                if (byteString != null) {
                    this.bitField0_ |= 2;
                    this.message_ = byteString;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
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
            public ProtobufCBLRegistrationDetails mo10084build() {
                ProtobufCBLRegistrationDetails mo10085buildPartial = mo10085buildPartial();
                if (mo10085buildPartial.isInitialized()) {
                    return mo10085buildPartial;
                }
                throw AbstractMessage.Builder.newUninitializedMessageException((Message) mo10085buildPartial);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: buildPartial */
            public ProtobufCBLRegistrationDetails mo10085buildPartial() {
                ProtobufCBLRegistrationDetails protobufCBLRegistrationDetails = new ProtobufCBLRegistrationDetails(this);
                int i = this.bitField0_;
                int i2 = 1;
                if ((i & 1) != 1) {
                    i2 = 0;
                }
                protobufCBLRegistrationDetails.state_ = this.state_;
                if ((i & 2) == 2) {
                    i2 |= 2;
                }
                protobufCBLRegistrationDetails.message_ = this.message_;
                if ((i & 4) == 4) {
                    i2 |= 4;
                }
                protobufCBLRegistrationDetails.httpCode_ = this.httpCode_;
                protobufCBLRegistrationDetails.bitField0_ = i2;
                onBuilt();
                return protobufCBLRegistrationDetails;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            /* renamed from: clearField */
            public Builder mo10088clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (Builder) super.mo10088clearField(fieldDescriptor);
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            /* renamed from: getDefaultInstanceForType */
            public ProtobufCBLRegistrationDetails mo10094getDefaultInstanceForType() {
                return ProtobufCBLRegistrationDetails.getDefaultInstance();
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
                this.message_ = "";
                this.bitField0_ &= -3;
                this.httpCode_ = 0;
                this.bitField0_ &= -5;
                return this;
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.state_ = 0;
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
                if (message instanceof ProtobufCBLRegistrationDetails) {
                    return mergeFrom((ProtobufCBLRegistrationDetails) message);
                }
                super.mo10096mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(ProtobufCBLRegistrationDetails protobufCBLRegistrationDetails) {
                if (protobufCBLRegistrationDetails == ProtobufCBLRegistrationDetails.getDefaultInstance()) {
                    return this;
                }
                if (protobufCBLRegistrationDetails.hasState()) {
                    setState(protobufCBLRegistrationDetails.getState());
                }
                if (protobufCBLRegistrationDetails.hasMessage()) {
                    this.bitField0_ |= 2;
                    this.message_ = protobufCBLRegistrationDetails.message_;
                    onChanged();
                }
                if (protobufCBLRegistrationDetails.hasHttpCode()) {
                    setHttpCode(protobufCBLRegistrationDetails.getHttpCode());
                }
                mo10099mergeUnknownFields(((GeneratedMessageV3) protobufCBLRegistrationDetails).unknownFields);
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
            public com.amazon.whisperjoin.protobuf.ProtobufCBLRegistrationDetailsClass.ProtobufCBLRegistrationDetails.Builder mo10097mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
                /*
                    r2 = this;
                    r0 = 0
                    com.google.protobuf.Parser<com.amazon.whisperjoin.protobuf.ProtobufCBLRegistrationDetailsClass$ProtobufCBLRegistrationDetails> r1 = com.amazon.whisperjoin.protobuf.ProtobufCBLRegistrationDetailsClass.ProtobufCBLRegistrationDetails.PARSER     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                    java.lang.Object r3 = r1.mo10082parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                    com.amazon.whisperjoin.protobuf.ProtobufCBLRegistrationDetailsClass$ProtobufCBLRegistrationDetails r3 = (com.amazon.whisperjoin.protobuf.ProtobufCBLRegistrationDetailsClass.ProtobufCBLRegistrationDetails) r3     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
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
                    com.amazon.whisperjoin.protobuf.ProtobufCBLRegistrationDetailsClass$ProtobufCBLRegistrationDetails r4 = (com.amazon.whisperjoin.protobuf.ProtobufCBLRegistrationDetailsClass.ProtobufCBLRegistrationDetails) r4     // Catch: java.lang.Throwable -> Lf
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
                throw new UnsupportedOperationException("Method not decompiled: com.amazon.whisperjoin.protobuf.ProtobufCBLRegistrationDetailsClass.ProtobufCBLRegistrationDetails.Builder.mo10097mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.amazon.whisperjoin.protobuf.ProtobufCBLRegistrationDetailsClass$ProtobufCBLRegistrationDetails$Builder");
            }
        }

        public static Builder newBuilder(ProtobufCBLRegistrationDetails protobufCBLRegistrationDetails) {
            return DEFAULT_INSTANCE.mo10081toBuilder().mergeFrom(protobufCBLRegistrationDetails);
        }

        public static ProtobufCBLRegistrationDetails parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.mo8403parseFrom(byteBuffer, extensionRegistryLite);
        }

        private ProtobufCBLRegistrationDetails(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        public static ProtobufCBLRegistrationDetails parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ProtobufCBLRegistrationDetails) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static ProtobufCBLRegistrationDetails parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.mo8396parseFrom(byteString);
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        /* renamed from: getDefaultInstanceForType */
        public ProtobufCBLRegistrationDetails mo10094getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        /* renamed from: toBuilder */
        public Builder mo10081toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        public static ProtobufCBLRegistrationDetails parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.mo8397parseFrom(byteString, extensionRegistryLite);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        /* renamed from: newBuilderForType */
        public Builder mo10079newBuilderForType() {
            return newBuilder();
        }

        private ProtobufCBLRegistrationDetails() {
            this.memoizedIsInitialized = (byte) -1;
            this.state_ = 0;
            this.message_ = "";
            this.httpCode_ = 0;
        }

        public static ProtobufCBLRegistrationDetails parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.mo8404parseFrom(bArr);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageV3
        /* renamed from: newBuilderForType */
        public Builder mo10080newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public static ProtobufCBLRegistrationDetails parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.mo8407parseFrom(bArr, extensionRegistryLite);
        }

        public static ProtobufCBLRegistrationDetails parseFrom(InputStream inputStream) throws IOException {
            return (ProtobufCBLRegistrationDetails) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static ProtobufCBLRegistrationDetails parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ProtobufCBLRegistrationDetails) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        private ProtobufCBLRegistrationDetails(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                } else if (readTag == 18) {
                                    ByteString readBytes = codedInputStream.readBytes();
                                    this.bitField0_ |= 2;
                                    this.message_ = readBytes;
                                } else if (readTag != 24) {
                                    if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                    }
                                } else {
                                    this.bitField0_ |= 4;
                                    this.httpCode_ = codedInputStream.readSInt32();
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

        public static ProtobufCBLRegistrationDetails parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (ProtobufCBLRegistrationDetails) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static ProtobufCBLRegistrationDetails parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ProtobufCBLRegistrationDetails) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes13.dex */
    public interface ProtobufCBLRegistrationDetailsOrBuilder extends MessageOrBuilder {
        int getHttpCode();

        String getMessage();

        ByteString getMessageBytes();

        ProtobufCBLRegistrationDetails.State getState();

        boolean hasHttpCode();

        boolean hasMessage();

        boolean hasState();
    }

    static {
        Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\nbWhisperJoinProtocolBuffersModel/schema/provisioning/data/registration/CBLRegistrationDetails.proto\u0012\bprotobuf\"Æ\u0003\n\u001eProtobufCBLRegistrationDetails\u0012=\n\u0005state\u0018\u0001 \u0002(\u000e2..protobuf.ProtobufCBLRegistrationDetails.State\u0012\u000f\n\u0007message\u0018\u0002 \u0001(\t\u0012\u0010\n\bhttpCode\u0018\u0003 \u0001(\u0011\"Á\u0002\n\u0005State\u0012\u0012\n\u000eNOT_REGISTERED\u0010\u0000\u0012\u001b\n\u0017COMPLETING_REGISTRATION\u0010\u0001\u0012\u0019\n\u0015REGISTRATION_COMPLETE\u0010\u0002\u0012.\n!REGISTRATION_FAILED_TOKEN_INVALID\u0010ÿÿÿÿÿÿÿÿÿ\u0001\u0012.\n!REGISTRATION_FAILED_TOKEN_EXPIRED\u0010þÿÿÿÿÿÿÿÿ\u0001\u00125\n(REGISTRATION_FAILED_SERVER_NOT_REACHABLE\u0010ýÿÿÿÿÿÿÿÿ\u0001\u0012-\n REGISTRATION_FAILED_SERVER_ERROR\u0010üÿÿÿÿÿÿÿÿ\u0001\u0012&\n\u0019REGISTRATION_FAILED_OTHER\u0010ûÿÿÿÿÿÿÿÿ\u0001BF\n\u001fcom.amazon.whisperjoin.protobufB#ProtobufCBLRegistrationDetailsClass"}, new Descriptors.FileDescriptor[0], new Descriptors.FileDescriptor.InternalDescriptorAssigner() { // from class: com.amazon.whisperjoin.protobuf.ProtobufCBLRegistrationDetailsClass.1
            @Override // com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner
            public ExtensionRegistry assignDescriptors(Descriptors.FileDescriptor fileDescriptor) {
                Descriptors.FileDescriptor unused = ProtobufCBLRegistrationDetailsClass.descriptor = fileDescriptor;
                return null;
            }
        });
        internal_static_protobuf_ProtobufCBLRegistrationDetails_descriptor = getDescriptor().getMessageTypes().get(0);
        internal_static_protobuf_ProtobufCBLRegistrationDetails_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_protobuf_ProtobufCBLRegistrationDetails_descriptor, new String[]{"State", AlexaMetricsConstants.EventConstants.MESSAGE, "HttpCode"});
    }

    private ProtobufCBLRegistrationDetailsClass() {
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
