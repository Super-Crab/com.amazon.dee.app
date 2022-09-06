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
public final class ProtobufWifiConnectionDetailsClass {
    private static Descriptors.FileDescriptor descriptor;
    private static final Descriptors.Descriptor internal_static_protobuf_ProtobufWifiConnectionDetails_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_protobuf_ProtobufWifiConnectionDetails_fieldAccessorTable;

    /* loaded from: classes13.dex */
    public static final class ProtobufWifiConnectionDetails extends GeneratedMessageV3 implements ProtobufWifiConnectionDetailsOrBuilder {
        private static final ProtobufWifiConnectionDetails DEFAULT_INSTANCE = new ProtobufWifiConnectionDetails();
        @Deprecated
        public static final Parser<ProtobufWifiConnectionDetails> PARSER = new AbstractParser<ProtobufWifiConnectionDetails>() { // from class: com.amazon.whisperjoin.protobuf.ProtobufWifiConnectionDetailsClass.ProtobufWifiConnectionDetails.1
            @Override // com.google.protobuf.Parser
            /* renamed from: parsePartialFrom */
            public ProtobufWifiConnectionDetails mo10082parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new ProtobufWifiConnectionDetails(codedInputStream, extensionRegistryLite);
            }
        };
        public static final int SSID_FIELD_NUMBER = 1;
        public static final int STATE_FIELD_NUMBER = 3;
        public static final int WIFIKEYMANAGEMENT_FIELD_NUMBER = 2;
        private static final long serialVersionUID = 0;
        private int bitField0_;
        private byte memoizedIsInitialized;
        private volatile Object ssid_;
        private int state_;
        private int wifiKeyManagement_;

        /* loaded from: classes13.dex */
        public enum State implements ProtocolMessageEnum {
            INITIATING_CONNECTION_ATTEMPT(0),
            DISCONNECTED(1),
            CONNECTING(2),
            ASSOCIATED(3),
            CONNECTED(4),
            CONNECTION_FAILED_INTERNAL_ERROR(-1),
            CONNECTED_BEHIND_CAPTIVE_PORTAL(-2),
            CONNECTED_LIMITED_CONNECTIVITY(-3),
            CONNECTION_FAILED_PSK_AUTHENTICATION(-4),
            CONNECTION_FAILED_AP_NOT_FOUND(-5);
            
            public static final int ASSOCIATED_VALUE = 3;
            public static final int CONNECTED_BEHIND_CAPTIVE_PORTAL_VALUE = -2;
            public static final int CONNECTED_LIMITED_CONNECTIVITY_VALUE = -3;
            public static final int CONNECTED_VALUE = 4;
            public static final int CONNECTING_VALUE = 2;
            public static final int CONNECTION_FAILED_AP_NOT_FOUND_VALUE = -5;
            public static final int CONNECTION_FAILED_INTERNAL_ERROR_VALUE = -1;
            public static final int CONNECTION_FAILED_PSK_AUTHENTICATION_VALUE = -4;
            public static final int DISCONNECTED_VALUE = 1;
            public static final int INITIATING_CONNECTION_ATTEMPT_VALUE = 0;
            private final int value;
            private static final Internal.EnumLiteMap<State> internalValueMap = new Internal.EnumLiteMap<State>() { // from class: com.amazon.whisperjoin.protobuf.ProtobufWifiConnectionDetailsClass.ProtobufWifiConnectionDetails.State.1
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
                        return CONNECTION_FAILED_AP_NOT_FOUND;
                    case -4:
                        return CONNECTION_FAILED_PSK_AUTHENTICATION;
                    case -3:
                        return CONNECTED_LIMITED_CONNECTIVITY;
                    case -2:
                        return CONNECTED_BEHIND_CAPTIVE_PORTAL;
                    case -1:
                        return CONNECTION_FAILED_INTERNAL_ERROR;
                    case 0:
                        return INITIATING_CONNECTION_ATTEMPT;
                    case 1:
                        return DISCONNECTED;
                    case 2:
                        return CONNECTING;
                    case 3:
                        return ASSOCIATED;
                    case 4:
                        return CONNECTED;
                    default:
                        return null;
                }
            }

            public static final Descriptors.EnumDescriptor getDescriptor() {
                return ProtobufWifiConnectionDetails.getDescriptor().getEnumTypes().get(0);
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

        public static ProtobufWifiConnectionDetails getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return ProtobufWifiConnectionDetailsClass.internal_static_protobuf_ProtobufWifiConnectionDetails_descriptor;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.mo10081toBuilder();
        }

        public static ProtobufWifiConnectionDetails parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (ProtobufWifiConnectionDetails) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static ProtobufWifiConnectionDetails parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.mo8402parseFrom(byteBuffer);
        }

        public static Parser<ProtobufWifiConnectionDetails> parser() {
            return PARSER;
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof ProtobufWifiConnectionDetails)) {
                return super.equals(obj);
            }
            ProtobufWifiConnectionDetails protobufWifiConnectionDetails = (ProtobufWifiConnectionDetails) obj;
            boolean z = hasSsid() == protobufWifiConnectionDetails.hasSsid();
            if (hasSsid()) {
                z = z && getSsid().equals(protobufWifiConnectionDetails.getSsid());
            }
            boolean z2 = z && hasWifiKeyManagement() == protobufWifiConnectionDetails.hasWifiKeyManagement();
            if (hasWifiKeyManagement()) {
                z2 = z2 && this.wifiKeyManagement_ == protobufWifiConnectionDetails.wifiKeyManagement_;
            }
            boolean z3 = z2 && hasState() == protobufWifiConnectionDetails.hasState();
            if (hasState()) {
                z3 = z3 && this.state_ == protobufWifiConnectionDetails.state_;
            }
            return z3 && this.unknownFields.equals(protobufWifiConnectionDetails.unknownFields);
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
        /* renamed from: getParserForType */
        public Parser<ProtobufWifiConnectionDetails> mo9954getParserForType() {
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
            if ((this.bitField0_ & 4) == 4) {
                i2 += CodedOutputStream.computeEnumSize(3, this.state_);
            }
            int serializedSize = this.unknownFields.getSerializedSize() + i2;
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.amazon.whisperjoin.protobuf.ProtobufWifiConnectionDetailsClass.ProtobufWifiConnectionDetailsOrBuilder
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

        @Override // com.amazon.whisperjoin.protobuf.ProtobufWifiConnectionDetailsClass.ProtobufWifiConnectionDetailsOrBuilder
        public ByteString getSsidBytes() {
            Object obj = this.ssid_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.ssid_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override // com.amazon.whisperjoin.protobuf.ProtobufWifiConnectionDetailsClass.ProtobufWifiConnectionDetailsOrBuilder
        public State getState() {
            State valueOf = State.valueOf(this.state_);
            return valueOf == null ? State.INITIATING_CONNECTION_ATTEMPT : valueOf;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        @Override // com.amazon.whisperjoin.protobuf.ProtobufWifiConnectionDetailsClass.ProtobufWifiConnectionDetailsOrBuilder
        public ProtobufWifiKeyManagementClass.ProtobufWifiKeyManagement.WifiKeyManagement getWifiKeyManagement() {
            ProtobufWifiKeyManagementClass.ProtobufWifiKeyManagement.WifiKeyManagement valueOf = ProtobufWifiKeyManagementClass.ProtobufWifiKeyManagement.WifiKeyManagement.valueOf(this.wifiKeyManagement_);
            return valueOf == null ? ProtobufWifiKeyManagementClass.ProtobufWifiKeyManagement.WifiKeyManagement.NONE : valueOf;
        }

        @Override // com.amazon.whisperjoin.protobuf.ProtobufWifiConnectionDetailsClass.ProtobufWifiConnectionDetailsOrBuilder
        public boolean hasSsid() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // com.amazon.whisperjoin.protobuf.ProtobufWifiConnectionDetailsClass.ProtobufWifiConnectionDetailsOrBuilder
        public boolean hasState() {
            return (this.bitField0_ & 4) == 4;
        }

        @Override // com.amazon.whisperjoin.protobuf.ProtobufWifiConnectionDetailsClass.ProtobufWifiConnectionDetailsOrBuilder
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
            if (hasState()) {
                hashCode = GeneratedOutlineSupport1.outline4(hashCode, 37, 3, 53) + this.state_;
            }
            int hashCode2 = this.unknownFields.hashCode() + (hashCode * 29);
            this.memoizedHashCode = hashCode2;
            return hashCode2;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return ProtobufWifiConnectionDetailsClass.internal_static_protobuf_ProtobufWifiConnectionDetails_fieldAccessorTable.ensureFieldAccessorsInitialized(ProtobufWifiConnectionDetails.class, Builder.class);
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
            } else if (!hasState()) {
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
            if ((this.bitField0_ & 4) == 4) {
                codedOutputStream.writeEnum(3, this.state_);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        /* loaded from: classes13.dex */
        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements ProtobufWifiConnectionDetailsOrBuilder {
            private int bitField0_;
            private Object ssid_;
            private int state_;
            private int wifiKeyManagement_;

            public static final Descriptors.Descriptor getDescriptor() {
                return ProtobufWifiConnectionDetailsClass.internal_static_protobuf_ProtobufWifiConnectionDetails_descriptor;
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = GeneratedMessageV3.alwaysUseFieldBuilders;
            }

            public Builder clearSsid() {
                this.bitField0_ &= -2;
                this.ssid_ = ProtobufWifiConnectionDetails.getDefaultInstance().getSsid();
                onChanged();
                return this;
            }

            public Builder clearState() {
                this.bitField0_ &= -5;
                this.state_ = 0;
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
                return ProtobufWifiConnectionDetailsClass.internal_static_protobuf_ProtobufWifiConnectionDetails_descriptor;
            }

            @Override // com.amazon.whisperjoin.protobuf.ProtobufWifiConnectionDetailsClass.ProtobufWifiConnectionDetailsOrBuilder
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

            @Override // com.amazon.whisperjoin.protobuf.ProtobufWifiConnectionDetailsClass.ProtobufWifiConnectionDetailsOrBuilder
            public ByteString getSsidBytes() {
                Object obj = this.ssid_;
                if (obj instanceof String) {
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.ssid_ = copyFromUtf8;
                    return copyFromUtf8;
                }
                return (ByteString) obj;
            }

            @Override // com.amazon.whisperjoin.protobuf.ProtobufWifiConnectionDetailsClass.ProtobufWifiConnectionDetailsOrBuilder
            public State getState() {
                State valueOf = State.valueOf(this.state_);
                return valueOf == null ? State.INITIATING_CONNECTION_ATTEMPT : valueOf;
            }

            @Override // com.amazon.whisperjoin.protobuf.ProtobufWifiConnectionDetailsClass.ProtobufWifiConnectionDetailsOrBuilder
            public ProtobufWifiKeyManagementClass.ProtobufWifiKeyManagement.WifiKeyManagement getWifiKeyManagement() {
                ProtobufWifiKeyManagementClass.ProtobufWifiKeyManagement.WifiKeyManagement valueOf = ProtobufWifiKeyManagementClass.ProtobufWifiKeyManagement.WifiKeyManagement.valueOf(this.wifiKeyManagement_);
                return valueOf == null ? ProtobufWifiKeyManagementClass.ProtobufWifiKeyManagement.WifiKeyManagement.NONE : valueOf;
            }

            @Override // com.amazon.whisperjoin.protobuf.ProtobufWifiConnectionDetailsClass.ProtobufWifiConnectionDetailsOrBuilder
            public boolean hasSsid() {
                return (this.bitField0_ & 1) == 1;
            }

            @Override // com.amazon.whisperjoin.protobuf.ProtobufWifiConnectionDetailsClass.ProtobufWifiConnectionDetailsOrBuilder
            public boolean hasState() {
                return (this.bitField0_ & 4) == 4;
            }

            @Override // com.amazon.whisperjoin.protobuf.ProtobufWifiConnectionDetailsClass.ProtobufWifiConnectionDetailsOrBuilder
            public boolean hasWifiKeyManagement() {
                return (this.bitField0_ & 2) == 2;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return ProtobufWifiConnectionDetailsClass.internal_static_protobuf_ProtobufWifiConnectionDetails_fieldAccessorTable.ensureFieldAccessorsInitialized(ProtobufWifiConnectionDetails.class, Builder.class);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return hasSsid() && hasWifiKeyManagement() && hasState();
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

            public Builder setState(State state) {
                if (state != null) {
                    this.bitField0_ |= 4;
                    this.state_ = state.getNumber();
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
            public ProtobufWifiConnectionDetails mo10084build() {
                ProtobufWifiConnectionDetails mo10085buildPartial = mo10085buildPartial();
                if (mo10085buildPartial.isInitialized()) {
                    return mo10085buildPartial;
                }
                throw AbstractMessage.Builder.newUninitializedMessageException((Message) mo10085buildPartial);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: buildPartial */
            public ProtobufWifiConnectionDetails mo10085buildPartial() {
                ProtobufWifiConnectionDetails protobufWifiConnectionDetails = new ProtobufWifiConnectionDetails(this);
                int i = this.bitField0_;
                int i2 = 1;
                if ((i & 1) != 1) {
                    i2 = 0;
                }
                protobufWifiConnectionDetails.ssid_ = this.ssid_;
                if ((i & 2) == 2) {
                    i2 |= 2;
                }
                protobufWifiConnectionDetails.wifiKeyManagement_ = this.wifiKeyManagement_;
                if ((i & 4) == 4) {
                    i2 |= 4;
                }
                protobufWifiConnectionDetails.state_ = this.state_;
                protobufWifiConnectionDetails.bitField0_ = i2;
                onBuilt();
                return protobufWifiConnectionDetails;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            /* renamed from: clearField */
            public Builder mo10088clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (Builder) super.mo10088clearField(fieldDescriptor);
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            /* renamed from: getDefaultInstanceForType */
            public ProtobufWifiConnectionDetails mo10094getDefaultInstanceForType() {
                return ProtobufWifiConnectionDetails.getDefaultInstance();
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
                this.state_ = 0;
                this.bitField0_ &= -5;
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
                if (message instanceof ProtobufWifiConnectionDetails) {
                    return mergeFrom((ProtobufWifiConnectionDetails) message);
                }
                super.mo10096mergeFrom(message);
                return this;
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.ssid_ = "";
                this.wifiKeyManagement_ = 0;
                this.state_ = 0;
                maybeForceBuilderInitialization();
            }

            public Builder mergeFrom(ProtobufWifiConnectionDetails protobufWifiConnectionDetails) {
                if (protobufWifiConnectionDetails == ProtobufWifiConnectionDetails.getDefaultInstance()) {
                    return this;
                }
                if (protobufWifiConnectionDetails.hasSsid()) {
                    this.bitField0_ |= 1;
                    this.ssid_ = protobufWifiConnectionDetails.ssid_;
                    onChanged();
                }
                if (protobufWifiConnectionDetails.hasWifiKeyManagement()) {
                    setWifiKeyManagement(protobufWifiConnectionDetails.getWifiKeyManagement());
                }
                if (protobufWifiConnectionDetails.hasState()) {
                    setState(protobufWifiConnectionDetails.getState());
                }
                mo10099mergeUnknownFields(((GeneratedMessageV3) protobufWifiConnectionDetails).unknownFields);
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
            public com.amazon.whisperjoin.protobuf.ProtobufWifiConnectionDetailsClass.ProtobufWifiConnectionDetails.Builder mo10097mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
                /*
                    r2 = this;
                    r0 = 0
                    com.google.protobuf.Parser<com.amazon.whisperjoin.protobuf.ProtobufWifiConnectionDetailsClass$ProtobufWifiConnectionDetails> r1 = com.amazon.whisperjoin.protobuf.ProtobufWifiConnectionDetailsClass.ProtobufWifiConnectionDetails.PARSER     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                    java.lang.Object r3 = r1.mo10082parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                    com.amazon.whisperjoin.protobuf.ProtobufWifiConnectionDetailsClass$ProtobufWifiConnectionDetails r3 = (com.amazon.whisperjoin.protobuf.ProtobufWifiConnectionDetailsClass.ProtobufWifiConnectionDetails) r3     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
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
                    com.amazon.whisperjoin.protobuf.ProtobufWifiConnectionDetailsClass$ProtobufWifiConnectionDetails r4 = (com.amazon.whisperjoin.protobuf.ProtobufWifiConnectionDetailsClass.ProtobufWifiConnectionDetails) r4     // Catch: java.lang.Throwable -> Lf
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
                throw new UnsupportedOperationException("Method not decompiled: com.amazon.whisperjoin.protobuf.ProtobufWifiConnectionDetailsClass.ProtobufWifiConnectionDetails.Builder.mo10097mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.amazon.whisperjoin.protobuf.ProtobufWifiConnectionDetailsClass$ProtobufWifiConnectionDetails$Builder");
            }
        }

        public static Builder newBuilder(ProtobufWifiConnectionDetails protobufWifiConnectionDetails) {
            return DEFAULT_INSTANCE.mo10081toBuilder().mergeFrom(protobufWifiConnectionDetails);
        }

        public static ProtobufWifiConnectionDetails parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.mo8403parseFrom(byteBuffer, extensionRegistryLite);
        }

        private ProtobufWifiConnectionDetails(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        public static ProtobufWifiConnectionDetails parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ProtobufWifiConnectionDetails) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static ProtobufWifiConnectionDetails parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.mo8396parseFrom(byteString);
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        /* renamed from: getDefaultInstanceForType */
        public ProtobufWifiConnectionDetails mo10094getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        /* renamed from: toBuilder */
        public Builder mo10081toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        public static ProtobufWifiConnectionDetails parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.mo8397parseFrom(byteString, extensionRegistryLite);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        /* renamed from: newBuilderForType */
        public Builder mo10079newBuilderForType() {
            return newBuilder();
        }

        private ProtobufWifiConnectionDetails() {
            this.memoizedIsInitialized = (byte) -1;
            this.ssid_ = "";
            this.wifiKeyManagement_ = 0;
            this.state_ = 0;
        }

        public static ProtobufWifiConnectionDetails parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.mo8404parseFrom(bArr);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageV3
        /* renamed from: newBuilderForType */
        public Builder mo10080newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public static ProtobufWifiConnectionDetails parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.mo8407parseFrom(bArr, extensionRegistryLite);
        }

        public static ProtobufWifiConnectionDetails parseFrom(InputStream inputStream) throws IOException {
            return (ProtobufWifiConnectionDetails) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static ProtobufWifiConnectionDetails parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ProtobufWifiConnectionDetails) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        private ProtobufWifiConnectionDetails(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                    if (readTag == 10) {
                                        ByteString readBytes = codedInputStream.readBytes();
                                        this.bitField0_ = 1 | this.bitField0_;
                                        this.ssid_ = readBytes;
                                    } else if (readTag == 16) {
                                        int readEnum = codedInputStream.readEnum();
                                        if (ProtobufWifiKeyManagementClass.ProtobufWifiKeyManagement.WifiKeyManagement.valueOf(readEnum) == null) {
                                            newBuilder.mergeVarintField(2, readEnum);
                                        } else {
                                            this.bitField0_ |= 2;
                                            this.wifiKeyManagement_ = readEnum;
                                        }
                                    } else if (readTag != 24) {
                                        if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                        }
                                    } else {
                                        int readEnum2 = codedInputStream.readEnum();
                                        if (State.valueOf(readEnum2) == null) {
                                            newBuilder.mergeVarintField(3, readEnum2);
                                        } else {
                                            this.bitField0_ |= 4;
                                            this.state_ = readEnum2;
                                        }
                                    }
                                }
                                z = true;
                            } catch (IOException e) {
                                throw new InvalidProtocolBufferException(e).setUnfinishedMessage(this);
                            }
                        } catch (InvalidProtocolBufferException e2) {
                            throw e2.setUnfinishedMessage(this);
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

        public static ProtobufWifiConnectionDetails parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (ProtobufWifiConnectionDetails) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static ProtobufWifiConnectionDetails parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ProtobufWifiConnectionDetails) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes13.dex */
    public interface ProtobufWifiConnectionDetailsOrBuilder extends MessageOrBuilder {
        String getSsid();

        ByteString getSsidBytes();

        ProtobufWifiConnectionDetails.State getState();

        ProtobufWifiKeyManagementClass.ProtobufWifiKeyManagement.WifiKeyManagement getWifiKeyManagement();

        boolean hasSsid();

        boolean hasState();

        boolean hasWifiKeyManagement();
    }

    static {
        Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\nYWhisperJoinProtocolBuffersModel/schema/provisioning/data/wifi/WifiConnectionDetails.proto\u0012\bprotobuf\u001aUWhisperJoinProtocolBuffersModel/schema/provisioning/data/wifi/WifiKeyManagement.proto\"\u0095\u0004\n\u001dProtobufWifiConnectionDetails\u0012\f\n\u0004ssid\u0018\u0001 \u0002(\t\u0012P\n\u0011wifiKeyManagement\u0018\u0002 \u0002(\u000e25.protobuf.ProtobufWifiKeyManagement.WifiKeyManagement\u0012<\n\u0005state\u0018\u0003 \u0002(\u000e2-.protobuf.ProtobufWifiConnectionDetails.State\"Õ\u0002\n\u0005State\u0012!\n\u001dINITIATING_CONNECTION_ATTEMPT\u0010\u0000\u0012\u0010\n\fDISCONNECTED\u0010\u0001\u0012\u000e\n\nCONNECTING\u0010\u0002\u0012\u000e\n\nASSOCIATED\u0010\u0003\u0012\r\n\tCONNECTED\u0010\u0004\u0012-\n CONNECTION_FAILED_INTERNAL_ERROR\u0010ÿÿÿÿÿÿÿÿÿ\u0001\u0012,\n\u001fCONNECTED_BEHIND_CAPTIVE_PORTAL\u0010þÿÿÿÿÿÿÿÿ\u0001\u0012+\n\u001eCONNECTED_LIMITED_CONNECTIVITY\u0010ýÿÿÿÿÿÿÿÿ\u0001\u00121\n$CONNECTION_FAILED_PSK_AUTHENTICATION\u0010üÿÿÿÿÿÿÿÿ\u0001\u0012+\n\u001eCONNECTION_FAILED_AP_NOT_FOUND\u0010ûÿÿÿÿÿÿÿÿ\u0001BE\n\u001fcom.amazon.whisperjoin.protobufB\"ProtobufWifiConnectionDetailsClass"}, new Descriptors.FileDescriptor[]{ProtobufWifiKeyManagementClass.getDescriptor()}, new Descriptors.FileDescriptor.InternalDescriptorAssigner() { // from class: com.amazon.whisperjoin.protobuf.ProtobufWifiConnectionDetailsClass.1
            @Override // com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner
            public ExtensionRegistry assignDescriptors(Descriptors.FileDescriptor fileDescriptor) {
                Descriptors.FileDescriptor unused = ProtobufWifiConnectionDetailsClass.descriptor = fileDescriptor;
                return null;
            }
        });
        internal_static_protobuf_ProtobufWifiConnectionDetails_descriptor = getDescriptor().getMessageTypes().get(0);
        internal_static_protobuf_ProtobufWifiConnectionDetails_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_protobuf_ProtobufWifiConnectionDetails_descriptor, new String[]{"Ssid", "WifiKeyManagement", "State"});
        ProtobufWifiKeyManagementClass.getDescriptor();
    }

    private ProtobufWifiConnectionDetailsClass() {
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
