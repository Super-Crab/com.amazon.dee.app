package com.amazon.whisperjoin.protobuf;

import com.amazon.whisperjoin.protobuf.ProtobufWifiKeyManagementClass;
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
import com.google.protobuf.Internal;
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
public final class ProtobufWifiBaseConfigurationClass {
    private static Descriptors.FileDescriptor descriptor;
    private static final Descriptors.Descriptor internal_static_protobuf_ProtobufWifiBaseConfigurationCollection_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_protobuf_ProtobufWifiBaseConfigurationCollection_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_protobuf_ProtobufWifiBaseConfiguration_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_protobuf_ProtobufWifiBaseConfiguration_fieldAccessorTable;

    /* loaded from: classes13.dex */
    public static final class ProtobufWifiBaseConfiguration extends GeneratedMessageV3 implements ProtobufWifiBaseConfigurationOrBuilder {
        public static final int ISHIDDENNETWORK_FIELD_NUMBER = 4;
        public static final int NETWORKPRIORITY_FIELD_NUMBER = 3;
        public static final int SSID_FIELD_NUMBER = 1;
        public static final int WIFIKEYMANAGEMENT_FIELD_NUMBER = 2;
        private static final long serialVersionUID = 0;
        private int bitField0_;
        private boolean isHiddenNetwork_;
        private byte memoizedIsInitialized;
        private int networkPriority_;
        private volatile Object ssid_;
        private int wifiKeyManagement_;
        private static final ProtobufWifiBaseConfiguration DEFAULT_INSTANCE = new ProtobufWifiBaseConfiguration();
        @Deprecated
        public static final Parser<ProtobufWifiBaseConfiguration> PARSER = new AbstractParser<ProtobufWifiBaseConfiguration>() { // from class: com.amazon.whisperjoin.protobuf.ProtobufWifiBaseConfigurationClass.ProtobufWifiBaseConfiguration.1
            @Override // com.google.protobuf.Parser
            /* renamed from: parsePartialFrom */
            public ProtobufWifiBaseConfiguration mo10082parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new ProtobufWifiBaseConfiguration(codedInputStream, extensionRegistryLite);
            }
        };

        public static ProtobufWifiBaseConfiguration getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return ProtobufWifiBaseConfigurationClass.internal_static_protobuf_ProtobufWifiBaseConfiguration_descriptor;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.mo10081toBuilder();
        }

        public static ProtobufWifiBaseConfiguration parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (ProtobufWifiBaseConfiguration) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static ProtobufWifiBaseConfiguration parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.mo8402parseFrom(byteBuffer);
        }

        public static Parser<ProtobufWifiBaseConfiguration> parser() {
            return PARSER;
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof ProtobufWifiBaseConfiguration)) {
                return super.equals(obj);
            }
            ProtobufWifiBaseConfiguration protobufWifiBaseConfiguration = (ProtobufWifiBaseConfiguration) obj;
            boolean z = hasSsid() == protobufWifiBaseConfiguration.hasSsid();
            if (hasSsid()) {
                z = z && getSsid().equals(protobufWifiBaseConfiguration.getSsid());
            }
            boolean z2 = z && hasWifiKeyManagement() == protobufWifiBaseConfiguration.hasWifiKeyManagement();
            if (hasWifiKeyManagement()) {
                z2 = z2 && this.wifiKeyManagement_ == protobufWifiBaseConfiguration.wifiKeyManagement_;
            }
            boolean z3 = z2 && hasNetworkPriority() == protobufWifiBaseConfiguration.hasNetworkPriority();
            if (hasNetworkPriority()) {
                z3 = z3 && getNetworkPriority() == protobufWifiBaseConfiguration.getNetworkPriority();
            }
            boolean z4 = z3 && hasIsHiddenNetwork() == protobufWifiBaseConfiguration.hasIsHiddenNetwork();
            if (hasIsHiddenNetwork()) {
                z4 = z4 && getIsHiddenNetwork() == protobufWifiBaseConfiguration.getIsHiddenNetwork();
            }
            return z4 && this.unknownFields.equals(protobufWifiBaseConfiguration.unknownFields);
        }

        @Override // com.amazon.whisperjoin.protobuf.ProtobufWifiBaseConfigurationClass.ProtobufWifiBaseConfigurationOrBuilder
        public boolean getIsHiddenNetwork() {
            return this.isHiddenNetwork_;
        }

        @Override // com.amazon.whisperjoin.protobuf.ProtobufWifiBaseConfigurationClass.ProtobufWifiBaseConfigurationOrBuilder
        public int getNetworkPriority() {
            return this.networkPriority_;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
        /* renamed from: getParserForType */
        public Parser<ProtobufWifiBaseConfiguration> mo9954getParserForType() {
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
                i2 += CodedOutputStream.computeSInt32Size(3, this.networkPriority_);
            }
            if ((this.bitField0_ & 8) == 8) {
                i2 += CodedOutputStream.computeBoolSize(4, this.isHiddenNetwork_);
            }
            int serializedSize = this.unknownFields.getSerializedSize() + i2;
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.amazon.whisperjoin.protobuf.ProtobufWifiBaseConfigurationClass.ProtobufWifiBaseConfigurationOrBuilder
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

        @Override // com.amazon.whisperjoin.protobuf.ProtobufWifiBaseConfigurationClass.ProtobufWifiBaseConfigurationOrBuilder
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

        @Override // com.amazon.whisperjoin.protobuf.ProtobufWifiBaseConfigurationClass.ProtobufWifiBaseConfigurationOrBuilder
        public ProtobufWifiKeyManagementClass.ProtobufWifiKeyManagement.WifiKeyManagement getWifiKeyManagement() {
            ProtobufWifiKeyManagementClass.ProtobufWifiKeyManagement.WifiKeyManagement valueOf = ProtobufWifiKeyManagementClass.ProtobufWifiKeyManagement.WifiKeyManagement.valueOf(this.wifiKeyManagement_);
            return valueOf == null ? ProtobufWifiKeyManagementClass.ProtobufWifiKeyManagement.WifiKeyManagement.NONE : valueOf;
        }

        @Override // com.amazon.whisperjoin.protobuf.ProtobufWifiBaseConfigurationClass.ProtobufWifiBaseConfigurationOrBuilder
        public boolean hasIsHiddenNetwork() {
            return (this.bitField0_ & 8) == 8;
        }

        @Override // com.amazon.whisperjoin.protobuf.ProtobufWifiBaseConfigurationClass.ProtobufWifiBaseConfigurationOrBuilder
        public boolean hasNetworkPriority() {
            return (this.bitField0_ & 4) == 4;
        }

        @Override // com.amazon.whisperjoin.protobuf.ProtobufWifiBaseConfigurationClass.ProtobufWifiBaseConfigurationOrBuilder
        public boolean hasSsid() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // com.amazon.whisperjoin.protobuf.ProtobufWifiBaseConfigurationClass.ProtobufWifiBaseConfigurationOrBuilder
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
            if (hasNetworkPriority()) {
                hashCode = GeneratedOutlineSupport1.outline4(hashCode, 37, 3, 53) + getNetworkPriority();
            }
            if (hasIsHiddenNetwork()) {
                hashCode = GeneratedOutlineSupport1.outline4(hashCode, 37, 4, 53) + Internal.hashBoolean(getIsHiddenNetwork());
            }
            int hashCode2 = this.unknownFields.hashCode() + (hashCode * 29);
            this.memoizedHashCode = hashCode2;
            return hashCode2;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return ProtobufWifiBaseConfigurationClass.internal_static_protobuf_ProtobufWifiBaseConfiguration_fieldAccessorTable.ensureFieldAccessorsInitialized(ProtobufWifiBaseConfiguration.class, Builder.class);
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
            } else if (!hasNetworkPriority()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            } else if (!hasIsHiddenNetwork()) {
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
                codedOutputStream.writeSInt32(3, this.networkPriority_);
            }
            if ((this.bitField0_ & 8) == 8) {
                codedOutputStream.writeBool(4, this.isHiddenNetwork_);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        /* loaded from: classes13.dex */
        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements ProtobufWifiBaseConfigurationOrBuilder {
            private int bitField0_;
            private boolean isHiddenNetwork_;
            private int networkPriority_;
            private Object ssid_;
            private int wifiKeyManagement_;

            public static final Descriptors.Descriptor getDescriptor() {
                return ProtobufWifiBaseConfigurationClass.internal_static_protobuf_ProtobufWifiBaseConfiguration_descriptor;
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = GeneratedMessageV3.alwaysUseFieldBuilders;
            }

            public Builder clearIsHiddenNetwork() {
                this.bitField0_ &= -9;
                this.isHiddenNetwork_ = false;
                onChanged();
                return this;
            }

            public Builder clearNetworkPriority() {
                this.bitField0_ &= -5;
                this.networkPriority_ = 0;
                onChanged();
                return this;
            }

            public Builder clearSsid() {
                this.bitField0_ &= -2;
                this.ssid_ = ProtobufWifiBaseConfiguration.getDefaultInstance().getSsid();
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
                return ProtobufWifiBaseConfigurationClass.internal_static_protobuf_ProtobufWifiBaseConfiguration_descriptor;
            }

            @Override // com.amazon.whisperjoin.protobuf.ProtobufWifiBaseConfigurationClass.ProtobufWifiBaseConfigurationOrBuilder
            public boolean getIsHiddenNetwork() {
                return this.isHiddenNetwork_;
            }

            @Override // com.amazon.whisperjoin.protobuf.ProtobufWifiBaseConfigurationClass.ProtobufWifiBaseConfigurationOrBuilder
            public int getNetworkPriority() {
                return this.networkPriority_;
            }

            @Override // com.amazon.whisperjoin.protobuf.ProtobufWifiBaseConfigurationClass.ProtobufWifiBaseConfigurationOrBuilder
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

            @Override // com.amazon.whisperjoin.protobuf.ProtobufWifiBaseConfigurationClass.ProtobufWifiBaseConfigurationOrBuilder
            public ByteString getSsidBytes() {
                Object obj = this.ssid_;
                if (obj instanceof String) {
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.ssid_ = copyFromUtf8;
                    return copyFromUtf8;
                }
                return (ByteString) obj;
            }

            @Override // com.amazon.whisperjoin.protobuf.ProtobufWifiBaseConfigurationClass.ProtobufWifiBaseConfigurationOrBuilder
            public ProtobufWifiKeyManagementClass.ProtobufWifiKeyManagement.WifiKeyManagement getWifiKeyManagement() {
                ProtobufWifiKeyManagementClass.ProtobufWifiKeyManagement.WifiKeyManagement valueOf = ProtobufWifiKeyManagementClass.ProtobufWifiKeyManagement.WifiKeyManagement.valueOf(this.wifiKeyManagement_);
                return valueOf == null ? ProtobufWifiKeyManagementClass.ProtobufWifiKeyManagement.WifiKeyManagement.NONE : valueOf;
            }

            @Override // com.amazon.whisperjoin.protobuf.ProtobufWifiBaseConfigurationClass.ProtobufWifiBaseConfigurationOrBuilder
            public boolean hasIsHiddenNetwork() {
                return (this.bitField0_ & 8) == 8;
            }

            @Override // com.amazon.whisperjoin.protobuf.ProtobufWifiBaseConfigurationClass.ProtobufWifiBaseConfigurationOrBuilder
            public boolean hasNetworkPriority() {
                return (this.bitField0_ & 4) == 4;
            }

            @Override // com.amazon.whisperjoin.protobuf.ProtobufWifiBaseConfigurationClass.ProtobufWifiBaseConfigurationOrBuilder
            public boolean hasSsid() {
                return (this.bitField0_ & 1) == 1;
            }

            @Override // com.amazon.whisperjoin.protobuf.ProtobufWifiBaseConfigurationClass.ProtobufWifiBaseConfigurationOrBuilder
            public boolean hasWifiKeyManagement() {
                return (this.bitField0_ & 2) == 2;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return ProtobufWifiBaseConfigurationClass.internal_static_protobuf_ProtobufWifiBaseConfiguration_fieldAccessorTable.ensureFieldAccessorsInitialized(ProtobufWifiBaseConfiguration.class, Builder.class);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return hasSsid() && hasWifiKeyManagement() && hasNetworkPriority() && hasIsHiddenNetwork();
            }

            public Builder setIsHiddenNetwork(boolean z) {
                this.bitField0_ |= 8;
                this.isHiddenNetwork_ = z;
                onChanged();
                return this;
            }

            public Builder setNetworkPriority(int i) {
                this.bitField0_ |= 4;
                this.networkPriority_ = i;
                onChanged();
                return this;
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
            public ProtobufWifiBaseConfiguration mo10084build() {
                ProtobufWifiBaseConfiguration mo10085buildPartial = mo10085buildPartial();
                if (mo10085buildPartial.isInitialized()) {
                    return mo10085buildPartial;
                }
                throw AbstractMessage.Builder.newUninitializedMessageException((Message) mo10085buildPartial);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: buildPartial */
            public ProtobufWifiBaseConfiguration mo10085buildPartial() {
                ProtobufWifiBaseConfiguration protobufWifiBaseConfiguration = new ProtobufWifiBaseConfiguration(this);
                int i = this.bitField0_;
                int i2 = 1;
                if ((i & 1) != 1) {
                    i2 = 0;
                }
                protobufWifiBaseConfiguration.ssid_ = this.ssid_;
                if ((i & 2) == 2) {
                    i2 |= 2;
                }
                protobufWifiBaseConfiguration.wifiKeyManagement_ = this.wifiKeyManagement_;
                if ((i & 4) == 4) {
                    i2 |= 4;
                }
                protobufWifiBaseConfiguration.networkPriority_ = this.networkPriority_;
                if ((i & 8) == 8) {
                    i2 |= 8;
                }
                protobufWifiBaseConfiguration.isHiddenNetwork_ = this.isHiddenNetwork_;
                protobufWifiBaseConfiguration.bitField0_ = i2;
                onBuilt();
                return protobufWifiBaseConfiguration;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            /* renamed from: clearField */
            public Builder mo10088clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (Builder) super.mo10088clearField(fieldDescriptor);
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            /* renamed from: getDefaultInstanceForType */
            public ProtobufWifiBaseConfiguration mo10094getDefaultInstanceForType() {
                return ProtobufWifiBaseConfiguration.getDefaultInstance();
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
                this.networkPriority_ = 0;
                this.bitField0_ &= -5;
                this.isHiddenNetwork_ = false;
                this.bitField0_ &= -9;
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
                if (message instanceof ProtobufWifiBaseConfiguration) {
                    return mergeFrom((ProtobufWifiBaseConfiguration) message);
                }
                super.mo10096mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(ProtobufWifiBaseConfiguration protobufWifiBaseConfiguration) {
                if (protobufWifiBaseConfiguration == ProtobufWifiBaseConfiguration.getDefaultInstance()) {
                    return this;
                }
                if (protobufWifiBaseConfiguration.hasSsid()) {
                    this.bitField0_ |= 1;
                    this.ssid_ = protobufWifiBaseConfiguration.ssid_;
                    onChanged();
                }
                if (protobufWifiBaseConfiguration.hasWifiKeyManagement()) {
                    setWifiKeyManagement(protobufWifiBaseConfiguration.getWifiKeyManagement());
                }
                if (protobufWifiBaseConfiguration.hasNetworkPriority()) {
                    setNetworkPriority(protobufWifiBaseConfiguration.getNetworkPriority());
                }
                if (protobufWifiBaseConfiguration.hasIsHiddenNetwork()) {
                    setIsHiddenNetwork(protobufWifiBaseConfiguration.getIsHiddenNetwork());
                }
                mo10099mergeUnknownFields(((GeneratedMessageV3) protobufWifiBaseConfiguration).unknownFields);
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
            public com.amazon.whisperjoin.protobuf.ProtobufWifiBaseConfigurationClass.ProtobufWifiBaseConfiguration.Builder mo10097mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
                /*
                    r2 = this;
                    r0 = 0
                    com.google.protobuf.Parser<com.amazon.whisperjoin.protobuf.ProtobufWifiBaseConfigurationClass$ProtobufWifiBaseConfiguration> r1 = com.amazon.whisperjoin.protobuf.ProtobufWifiBaseConfigurationClass.ProtobufWifiBaseConfiguration.PARSER     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                    java.lang.Object r3 = r1.mo10082parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                    com.amazon.whisperjoin.protobuf.ProtobufWifiBaseConfigurationClass$ProtobufWifiBaseConfiguration r3 = (com.amazon.whisperjoin.protobuf.ProtobufWifiBaseConfigurationClass.ProtobufWifiBaseConfiguration) r3     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
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
                    com.amazon.whisperjoin.protobuf.ProtobufWifiBaseConfigurationClass$ProtobufWifiBaseConfiguration r4 = (com.amazon.whisperjoin.protobuf.ProtobufWifiBaseConfigurationClass.ProtobufWifiBaseConfiguration) r4     // Catch: java.lang.Throwable -> Lf
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
                throw new UnsupportedOperationException("Method not decompiled: com.amazon.whisperjoin.protobuf.ProtobufWifiBaseConfigurationClass.ProtobufWifiBaseConfiguration.Builder.mo10097mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.amazon.whisperjoin.protobuf.ProtobufWifiBaseConfigurationClass$ProtobufWifiBaseConfiguration$Builder");
            }
        }

        public static Builder newBuilder(ProtobufWifiBaseConfiguration protobufWifiBaseConfiguration) {
            return DEFAULT_INSTANCE.mo10081toBuilder().mergeFrom(protobufWifiBaseConfiguration);
        }

        public static ProtobufWifiBaseConfiguration parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.mo8403parseFrom(byteBuffer, extensionRegistryLite);
        }

        private ProtobufWifiBaseConfiguration(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        public static ProtobufWifiBaseConfiguration parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ProtobufWifiBaseConfiguration) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static ProtobufWifiBaseConfiguration parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.mo8396parseFrom(byteString);
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        /* renamed from: getDefaultInstanceForType */
        public ProtobufWifiBaseConfiguration mo10094getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        /* renamed from: toBuilder */
        public Builder mo10081toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        public static ProtobufWifiBaseConfiguration parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.mo8397parseFrom(byteString, extensionRegistryLite);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        /* renamed from: newBuilderForType */
        public Builder mo10079newBuilderForType() {
            return newBuilder();
        }

        private ProtobufWifiBaseConfiguration() {
            this.memoizedIsInitialized = (byte) -1;
            this.ssid_ = "";
            this.wifiKeyManagement_ = 0;
            this.networkPriority_ = 0;
            this.isHiddenNetwork_ = false;
        }

        public static ProtobufWifiBaseConfiguration parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.mo8404parseFrom(bArr);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageV3
        /* renamed from: newBuilderForType */
        public Builder mo10080newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public static ProtobufWifiBaseConfiguration parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.mo8407parseFrom(bArr, extensionRegistryLite);
        }

        public static ProtobufWifiBaseConfiguration parseFrom(InputStream inputStream) throws IOException {
            return (ProtobufWifiBaseConfiguration) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static ProtobufWifiBaseConfiguration parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ProtobufWifiBaseConfiguration) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        private ProtobufWifiBaseConfiguration(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                } else if (readTag == 16) {
                                    int readEnum = codedInputStream.readEnum();
                                    if (ProtobufWifiKeyManagementClass.ProtobufWifiKeyManagement.WifiKeyManagement.valueOf(readEnum) == null) {
                                        newBuilder.mergeVarintField(2, readEnum);
                                    } else {
                                        this.bitField0_ |= 2;
                                        this.wifiKeyManagement_ = readEnum;
                                    }
                                } else if (readTag == 24) {
                                    this.bitField0_ |= 4;
                                    this.networkPriority_ = codedInputStream.readSInt32();
                                } else if (readTag != 32) {
                                    if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                    }
                                } else {
                                    this.bitField0_ |= 8;
                                    this.isHiddenNetwork_ = codedInputStream.readBool();
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

        public static ProtobufWifiBaseConfiguration parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (ProtobufWifiBaseConfiguration) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static ProtobufWifiBaseConfiguration parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ProtobufWifiBaseConfiguration) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes13.dex */
    public static final class ProtobufWifiBaseConfigurationCollection extends GeneratedMessageV3 implements ProtobufWifiBaseConfigurationCollectionOrBuilder {
        private static final ProtobufWifiBaseConfigurationCollection DEFAULT_INSTANCE = new ProtobufWifiBaseConfigurationCollection();
        @Deprecated
        public static final Parser<ProtobufWifiBaseConfigurationCollection> PARSER = new AbstractParser<ProtobufWifiBaseConfigurationCollection>() { // from class: com.amazon.whisperjoin.protobuf.ProtobufWifiBaseConfigurationClass.ProtobufWifiBaseConfigurationCollection.1
            @Override // com.google.protobuf.Parser
            /* renamed from: parsePartialFrom */
            public ProtobufWifiBaseConfigurationCollection mo10082parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new ProtobufWifiBaseConfigurationCollection(codedInputStream, extensionRegistryLite);
            }
        };
        public static final int PROTOBUFWIFIBASECONFIGURATIONCOLLECTION_FIELD_NUMBER = 1;
        private static final long serialVersionUID = 0;
        private byte memoizedIsInitialized;
        private List<ProtobufWifiBaseConfiguration> protobufWifiBaseConfigurationCollection_;

        public static ProtobufWifiBaseConfigurationCollection getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return ProtobufWifiBaseConfigurationClass.internal_static_protobuf_ProtobufWifiBaseConfigurationCollection_descriptor;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.mo10081toBuilder();
        }

        public static ProtobufWifiBaseConfigurationCollection parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (ProtobufWifiBaseConfigurationCollection) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static ProtobufWifiBaseConfigurationCollection parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.mo8402parseFrom(byteBuffer);
        }

        public static Parser<ProtobufWifiBaseConfigurationCollection> parser() {
            return PARSER;
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof ProtobufWifiBaseConfigurationCollection)) {
                return super.equals(obj);
            }
            ProtobufWifiBaseConfigurationCollection protobufWifiBaseConfigurationCollection = (ProtobufWifiBaseConfigurationCollection) obj;
            return (getProtobufWifiBaseConfigurationCollectionList().equals(protobufWifiBaseConfigurationCollection.getProtobufWifiBaseConfigurationCollectionList())) && this.unknownFields.equals(protobufWifiBaseConfigurationCollection.unknownFields);
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
        /* renamed from: getParserForType */
        public Parser<ProtobufWifiBaseConfigurationCollection> mo9954getParserForType() {
            return PARSER;
        }

        @Override // com.amazon.whisperjoin.protobuf.ProtobufWifiBaseConfigurationClass.ProtobufWifiBaseConfigurationCollectionOrBuilder
        public ProtobufWifiBaseConfiguration getProtobufWifiBaseConfigurationCollection(int i) {
            return this.protobufWifiBaseConfigurationCollection_.get(i);
        }

        @Override // com.amazon.whisperjoin.protobuf.ProtobufWifiBaseConfigurationClass.ProtobufWifiBaseConfigurationCollectionOrBuilder
        public int getProtobufWifiBaseConfigurationCollectionCount() {
            return this.protobufWifiBaseConfigurationCollection_.size();
        }

        @Override // com.amazon.whisperjoin.protobuf.ProtobufWifiBaseConfigurationClass.ProtobufWifiBaseConfigurationCollectionOrBuilder
        public List<ProtobufWifiBaseConfiguration> getProtobufWifiBaseConfigurationCollectionList() {
            return this.protobufWifiBaseConfigurationCollection_;
        }

        @Override // com.amazon.whisperjoin.protobuf.ProtobufWifiBaseConfigurationClass.ProtobufWifiBaseConfigurationCollectionOrBuilder
        public ProtobufWifiBaseConfigurationOrBuilder getProtobufWifiBaseConfigurationCollectionOrBuilder(int i) {
            return this.protobufWifiBaseConfigurationCollection_.get(i);
        }

        @Override // com.amazon.whisperjoin.protobuf.ProtobufWifiBaseConfigurationClass.ProtobufWifiBaseConfigurationCollectionOrBuilder
        public List<? extends ProtobufWifiBaseConfigurationOrBuilder> getProtobufWifiBaseConfigurationCollectionOrBuilderList() {
            return this.protobufWifiBaseConfigurationCollection_;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            for (int i3 = 0; i3 < this.protobufWifiBaseConfigurationCollection_.size(); i3++) {
                i2 += CodedOutputStream.computeMessageSize(1, this.protobufWifiBaseConfigurationCollection_.get(i3));
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
            if (getProtobufWifiBaseConfigurationCollectionCount() > 0) {
                hashCode = GeneratedOutlineSupport1.outline4(hashCode, 37, 1, 53) + getProtobufWifiBaseConfigurationCollectionList().hashCode();
            }
            int hashCode2 = this.unknownFields.hashCode() + (hashCode * 29);
            this.memoizedHashCode = hashCode2;
            return hashCode2;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return ProtobufWifiBaseConfigurationClass.internal_static_protobuf_ProtobufWifiBaseConfigurationCollection_fieldAccessorTable.ensureFieldAccessorsInitialized(ProtobufWifiBaseConfigurationCollection.class, Builder.class);
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
            for (int i = 0; i < getProtobufWifiBaseConfigurationCollectionCount(); i++) {
                if (!getProtobufWifiBaseConfigurationCollection(i).isInitialized()) {
                    this.memoizedIsInitialized = (byte) 0;
                    return false;
                }
            }
            this.memoizedIsInitialized = (byte) 1;
            return true;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            for (int i = 0; i < this.protobufWifiBaseConfigurationCollection_.size(); i++) {
                codedOutputStream.writeMessage(1, this.protobufWifiBaseConfigurationCollection_.get(i));
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        /* loaded from: classes13.dex */
        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements ProtobufWifiBaseConfigurationCollectionOrBuilder {
            private int bitField0_;
            private RepeatedFieldBuilderV3<ProtobufWifiBaseConfiguration, ProtobufWifiBaseConfiguration.Builder, ProtobufWifiBaseConfigurationOrBuilder> protobufWifiBaseConfigurationCollectionBuilder_;
            private List<ProtobufWifiBaseConfiguration> protobufWifiBaseConfigurationCollection_;

            private void ensureProtobufWifiBaseConfigurationCollectionIsMutable() {
                if ((this.bitField0_ & 1) != 1) {
                    this.protobufWifiBaseConfigurationCollection_ = new ArrayList(this.protobufWifiBaseConfigurationCollection_);
                    this.bitField0_ |= 1;
                }
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return ProtobufWifiBaseConfigurationClass.internal_static_protobuf_ProtobufWifiBaseConfigurationCollection_descriptor;
            }

            private RepeatedFieldBuilderV3<ProtobufWifiBaseConfiguration, ProtobufWifiBaseConfiguration.Builder, ProtobufWifiBaseConfigurationOrBuilder> getProtobufWifiBaseConfigurationCollectionFieldBuilder() {
                if (this.protobufWifiBaseConfigurationCollectionBuilder_ == null) {
                    List<ProtobufWifiBaseConfiguration> list = this.protobufWifiBaseConfigurationCollection_;
                    boolean z = true;
                    if ((this.bitField0_ & 1) != 1) {
                        z = false;
                    }
                    this.protobufWifiBaseConfigurationCollectionBuilder_ = new RepeatedFieldBuilderV3<>(list, z, getParentForChildren(), isClean());
                    this.protobufWifiBaseConfigurationCollection_ = null;
                }
                return this.protobufWifiBaseConfigurationCollectionBuilder_;
            }

            private void maybeForceBuilderInitialization() {
                if (GeneratedMessageV3.alwaysUseFieldBuilders) {
                    getProtobufWifiBaseConfigurationCollectionFieldBuilder();
                }
            }

            public Builder addAllProtobufWifiBaseConfigurationCollection(Iterable<? extends ProtobufWifiBaseConfiguration> iterable) {
                RepeatedFieldBuilderV3<ProtobufWifiBaseConfiguration, ProtobufWifiBaseConfiguration.Builder, ProtobufWifiBaseConfigurationOrBuilder> repeatedFieldBuilderV3 = this.protobufWifiBaseConfigurationCollectionBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureProtobufWifiBaseConfigurationCollectionIsMutable();
                    AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.protobufWifiBaseConfigurationCollection_);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addAllMessages(iterable);
                }
                return this;
            }

            public Builder addProtobufWifiBaseConfigurationCollection(ProtobufWifiBaseConfiguration protobufWifiBaseConfiguration) {
                RepeatedFieldBuilderV3<ProtobufWifiBaseConfiguration, ProtobufWifiBaseConfiguration.Builder, ProtobufWifiBaseConfigurationOrBuilder> repeatedFieldBuilderV3 = this.protobufWifiBaseConfigurationCollectionBuilder_;
                if (repeatedFieldBuilderV3 != null) {
                    repeatedFieldBuilderV3.addMessage(protobufWifiBaseConfiguration);
                } else if (protobufWifiBaseConfiguration != null) {
                    ensureProtobufWifiBaseConfigurationCollectionIsMutable();
                    this.protobufWifiBaseConfigurationCollection_.add(protobufWifiBaseConfiguration);
                    onChanged();
                } else {
                    throw new NullPointerException();
                }
                return this;
            }

            public ProtobufWifiBaseConfiguration.Builder addProtobufWifiBaseConfigurationCollectionBuilder() {
                return getProtobufWifiBaseConfigurationCollectionFieldBuilder().addBuilder(ProtobufWifiBaseConfiguration.getDefaultInstance());
            }

            public Builder clearProtobufWifiBaseConfigurationCollection() {
                RepeatedFieldBuilderV3<ProtobufWifiBaseConfiguration, ProtobufWifiBaseConfiguration.Builder, ProtobufWifiBaseConfigurationOrBuilder> repeatedFieldBuilderV3 = this.protobufWifiBaseConfigurationCollectionBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    this.protobufWifiBaseConfigurationCollection_ = Collections.emptyList();
                    this.bitField0_ &= -2;
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.clear();
                }
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return ProtobufWifiBaseConfigurationClass.internal_static_protobuf_ProtobufWifiBaseConfigurationCollection_descriptor;
            }

            @Override // com.amazon.whisperjoin.protobuf.ProtobufWifiBaseConfigurationClass.ProtobufWifiBaseConfigurationCollectionOrBuilder
            public ProtobufWifiBaseConfiguration getProtobufWifiBaseConfigurationCollection(int i) {
                RepeatedFieldBuilderV3<ProtobufWifiBaseConfiguration, ProtobufWifiBaseConfiguration.Builder, ProtobufWifiBaseConfigurationOrBuilder> repeatedFieldBuilderV3 = this.protobufWifiBaseConfigurationCollectionBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return this.protobufWifiBaseConfigurationCollection_.get(i);
                }
                return repeatedFieldBuilderV3.getMessage(i);
            }

            public ProtobufWifiBaseConfiguration.Builder getProtobufWifiBaseConfigurationCollectionBuilder(int i) {
                return getProtobufWifiBaseConfigurationCollectionFieldBuilder().getBuilder(i);
            }

            public List<ProtobufWifiBaseConfiguration.Builder> getProtobufWifiBaseConfigurationCollectionBuilderList() {
                return getProtobufWifiBaseConfigurationCollectionFieldBuilder().getBuilderList();
            }

            @Override // com.amazon.whisperjoin.protobuf.ProtobufWifiBaseConfigurationClass.ProtobufWifiBaseConfigurationCollectionOrBuilder
            public int getProtobufWifiBaseConfigurationCollectionCount() {
                RepeatedFieldBuilderV3<ProtobufWifiBaseConfiguration, ProtobufWifiBaseConfiguration.Builder, ProtobufWifiBaseConfigurationOrBuilder> repeatedFieldBuilderV3 = this.protobufWifiBaseConfigurationCollectionBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return this.protobufWifiBaseConfigurationCollection_.size();
                }
                return repeatedFieldBuilderV3.getCount();
            }

            @Override // com.amazon.whisperjoin.protobuf.ProtobufWifiBaseConfigurationClass.ProtobufWifiBaseConfigurationCollectionOrBuilder
            public List<ProtobufWifiBaseConfiguration> getProtobufWifiBaseConfigurationCollectionList() {
                RepeatedFieldBuilderV3<ProtobufWifiBaseConfiguration, ProtobufWifiBaseConfiguration.Builder, ProtobufWifiBaseConfigurationOrBuilder> repeatedFieldBuilderV3 = this.protobufWifiBaseConfigurationCollectionBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return Collections.unmodifiableList(this.protobufWifiBaseConfigurationCollection_);
                }
                return repeatedFieldBuilderV3.getMessageList();
            }

            @Override // com.amazon.whisperjoin.protobuf.ProtobufWifiBaseConfigurationClass.ProtobufWifiBaseConfigurationCollectionOrBuilder
            public ProtobufWifiBaseConfigurationOrBuilder getProtobufWifiBaseConfigurationCollectionOrBuilder(int i) {
                RepeatedFieldBuilderV3<ProtobufWifiBaseConfiguration, ProtobufWifiBaseConfiguration.Builder, ProtobufWifiBaseConfigurationOrBuilder> repeatedFieldBuilderV3 = this.protobufWifiBaseConfigurationCollectionBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return this.protobufWifiBaseConfigurationCollection_.get(i);
                }
                return repeatedFieldBuilderV3.getMessageOrBuilder(i);
            }

            @Override // com.amazon.whisperjoin.protobuf.ProtobufWifiBaseConfigurationClass.ProtobufWifiBaseConfigurationCollectionOrBuilder
            public List<? extends ProtobufWifiBaseConfigurationOrBuilder> getProtobufWifiBaseConfigurationCollectionOrBuilderList() {
                RepeatedFieldBuilderV3<ProtobufWifiBaseConfiguration, ProtobufWifiBaseConfiguration.Builder, ProtobufWifiBaseConfigurationOrBuilder> repeatedFieldBuilderV3 = this.protobufWifiBaseConfigurationCollectionBuilder_;
                if (repeatedFieldBuilderV3 != null) {
                    return repeatedFieldBuilderV3.getMessageOrBuilderList();
                }
                return Collections.unmodifiableList(this.protobufWifiBaseConfigurationCollection_);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return ProtobufWifiBaseConfigurationClass.internal_static_protobuf_ProtobufWifiBaseConfigurationCollection_fieldAccessorTable.ensureFieldAccessorsInitialized(ProtobufWifiBaseConfigurationCollection.class, Builder.class);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                for (int i = 0; i < getProtobufWifiBaseConfigurationCollectionCount(); i++) {
                    if (!getProtobufWifiBaseConfigurationCollection(i).isInitialized()) {
                        return false;
                    }
                }
                return true;
            }

            public Builder removeProtobufWifiBaseConfigurationCollection(int i) {
                RepeatedFieldBuilderV3<ProtobufWifiBaseConfiguration, ProtobufWifiBaseConfiguration.Builder, ProtobufWifiBaseConfigurationOrBuilder> repeatedFieldBuilderV3 = this.protobufWifiBaseConfigurationCollectionBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureProtobufWifiBaseConfigurationCollectionIsMutable();
                    this.protobufWifiBaseConfigurationCollection_.remove(i);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.remove(i);
                }
                return this;
            }

            public Builder setProtobufWifiBaseConfigurationCollection(int i, ProtobufWifiBaseConfiguration protobufWifiBaseConfiguration) {
                RepeatedFieldBuilderV3<ProtobufWifiBaseConfiguration, ProtobufWifiBaseConfiguration.Builder, ProtobufWifiBaseConfigurationOrBuilder> repeatedFieldBuilderV3 = this.protobufWifiBaseConfigurationCollectionBuilder_;
                if (repeatedFieldBuilderV3 != null) {
                    repeatedFieldBuilderV3.setMessage(i, protobufWifiBaseConfiguration);
                } else if (protobufWifiBaseConfiguration != null) {
                    ensureProtobufWifiBaseConfigurationCollectionIsMutable();
                    this.protobufWifiBaseConfigurationCollection_.set(i, protobufWifiBaseConfiguration);
                    onChanged();
                } else {
                    throw new NullPointerException();
                }
                return this;
            }

            private Builder() {
                this.protobufWifiBaseConfigurationCollection_ = Collections.emptyList();
                maybeForceBuilderInitialization();
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            /* renamed from: addRepeatedField */
            public Builder mo10083addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.mo10083addRepeatedField(fieldDescriptor, obj);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: build */
            public ProtobufWifiBaseConfigurationCollection mo10084build() {
                ProtobufWifiBaseConfigurationCollection mo10085buildPartial = mo10085buildPartial();
                if (mo10085buildPartial.isInitialized()) {
                    return mo10085buildPartial;
                }
                throw AbstractMessage.Builder.newUninitializedMessageException((Message) mo10085buildPartial);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: buildPartial */
            public ProtobufWifiBaseConfigurationCollection mo10085buildPartial() {
                ProtobufWifiBaseConfigurationCollection protobufWifiBaseConfigurationCollection = new ProtobufWifiBaseConfigurationCollection(this);
                int i = this.bitField0_;
                RepeatedFieldBuilderV3<ProtobufWifiBaseConfiguration, ProtobufWifiBaseConfiguration.Builder, ProtobufWifiBaseConfigurationOrBuilder> repeatedFieldBuilderV3 = this.protobufWifiBaseConfigurationCollectionBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    if ((i & 1) == 1) {
                        this.protobufWifiBaseConfigurationCollection_ = Collections.unmodifiableList(this.protobufWifiBaseConfigurationCollection_);
                        this.bitField0_ &= -2;
                    }
                    protobufWifiBaseConfigurationCollection.protobufWifiBaseConfigurationCollection_ = this.protobufWifiBaseConfigurationCollection_;
                } else {
                    protobufWifiBaseConfigurationCollection.protobufWifiBaseConfigurationCollection_ = repeatedFieldBuilderV3.build();
                }
                onBuilt();
                return protobufWifiBaseConfigurationCollection;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            /* renamed from: clearField */
            public Builder mo10088clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (Builder) super.mo10088clearField(fieldDescriptor);
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            /* renamed from: getDefaultInstanceForType */
            public ProtobufWifiBaseConfigurationCollection mo10094getDefaultInstanceForType() {
                return ProtobufWifiBaseConfigurationCollection.getDefaultInstance();
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

            public ProtobufWifiBaseConfiguration.Builder addProtobufWifiBaseConfigurationCollectionBuilder(int i) {
                return getProtobufWifiBaseConfigurationCollectionFieldBuilder().addBuilder(i, ProtobufWifiBaseConfiguration.getDefaultInstance());
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
                RepeatedFieldBuilderV3<ProtobufWifiBaseConfiguration, ProtobufWifiBaseConfiguration.Builder, ProtobufWifiBaseConfigurationOrBuilder> repeatedFieldBuilderV3 = this.protobufWifiBaseConfigurationCollectionBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    this.protobufWifiBaseConfigurationCollection_ = Collections.emptyList();
                    this.bitField0_ &= -2;
                } else {
                    repeatedFieldBuilderV3.clear();
                }
                return this;
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.protobufWifiBaseConfigurationCollection_ = Collections.emptyList();
                maybeForceBuilderInitialization();
            }

            public Builder addProtobufWifiBaseConfigurationCollection(int i, ProtobufWifiBaseConfiguration protobufWifiBaseConfiguration) {
                RepeatedFieldBuilderV3<ProtobufWifiBaseConfiguration, ProtobufWifiBaseConfiguration.Builder, ProtobufWifiBaseConfigurationOrBuilder> repeatedFieldBuilderV3 = this.protobufWifiBaseConfigurationCollectionBuilder_;
                if (repeatedFieldBuilderV3 != null) {
                    repeatedFieldBuilderV3.addMessage(i, protobufWifiBaseConfiguration);
                } else if (protobufWifiBaseConfiguration != null) {
                    ensureProtobufWifiBaseConfigurationCollectionIsMutable();
                    this.protobufWifiBaseConfigurationCollection_.add(i, protobufWifiBaseConfiguration);
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
                if (message instanceof ProtobufWifiBaseConfigurationCollection) {
                    return mergeFrom((ProtobufWifiBaseConfigurationCollection) message);
                }
                super.mo10096mergeFrom(message);
                return this;
            }

            public Builder setProtobufWifiBaseConfigurationCollection(int i, ProtobufWifiBaseConfiguration.Builder builder) {
                RepeatedFieldBuilderV3<ProtobufWifiBaseConfiguration, ProtobufWifiBaseConfiguration.Builder, ProtobufWifiBaseConfigurationOrBuilder> repeatedFieldBuilderV3 = this.protobufWifiBaseConfigurationCollectionBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureProtobufWifiBaseConfigurationCollectionIsMutable();
                    this.protobufWifiBaseConfigurationCollection_.set(i, builder.mo10084build());
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.setMessage(i, builder.mo10084build());
                }
                return this;
            }

            public Builder mergeFrom(ProtobufWifiBaseConfigurationCollection protobufWifiBaseConfigurationCollection) {
                if (protobufWifiBaseConfigurationCollection == ProtobufWifiBaseConfigurationCollection.getDefaultInstance()) {
                    return this;
                }
                if (this.protobufWifiBaseConfigurationCollectionBuilder_ == null) {
                    if (!protobufWifiBaseConfigurationCollection.protobufWifiBaseConfigurationCollection_.isEmpty()) {
                        if (this.protobufWifiBaseConfigurationCollection_.isEmpty()) {
                            this.protobufWifiBaseConfigurationCollection_ = protobufWifiBaseConfigurationCollection.protobufWifiBaseConfigurationCollection_;
                            this.bitField0_ &= -2;
                        } else {
                            ensureProtobufWifiBaseConfigurationCollectionIsMutable();
                            this.protobufWifiBaseConfigurationCollection_.addAll(protobufWifiBaseConfigurationCollection.protobufWifiBaseConfigurationCollection_);
                        }
                        onChanged();
                    }
                } else if (!protobufWifiBaseConfigurationCollection.protobufWifiBaseConfigurationCollection_.isEmpty()) {
                    if (!this.protobufWifiBaseConfigurationCollectionBuilder_.isEmpty()) {
                        this.protobufWifiBaseConfigurationCollectionBuilder_.addAllMessages(protobufWifiBaseConfigurationCollection.protobufWifiBaseConfigurationCollection_);
                    } else {
                        this.protobufWifiBaseConfigurationCollectionBuilder_.dispose();
                        RepeatedFieldBuilderV3<ProtobufWifiBaseConfiguration, ProtobufWifiBaseConfiguration.Builder, ProtobufWifiBaseConfigurationOrBuilder> repeatedFieldBuilderV3 = null;
                        this.protobufWifiBaseConfigurationCollectionBuilder_ = null;
                        this.protobufWifiBaseConfigurationCollection_ = protobufWifiBaseConfigurationCollection.protobufWifiBaseConfigurationCollection_;
                        this.bitField0_ &= -2;
                        if (GeneratedMessageV3.alwaysUseFieldBuilders) {
                            repeatedFieldBuilderV3 = getProtobufWifiBaseConfigurationCollectionFieldBuilder();
                        }
                        this.protobufWifiBaseConfigurationCollectionBuilder_ = repeatedFieldBuilderV3;
                    }
                }
                mo10099mergeUnknownFields(((GeneratedMessageV3) protobufWifiBaseConfigurationCollection).unknownFields);
                onChanged();
                return this;
            }

            public Builder addProtobufWifiBaseConfigurationCollection(ProtobufWifiBaseConfiguration.Builder builder) {
                RepeatedFieldBuilderV3<ProtobufWifiBaseConfiguration, ProtobufWifiBaseConfiguration.Builder, ProtobufWifiBaseConfigurationOrBuilder> repeatedFieldBuilderV3 = this.protobufWifiBaseConfigurationCollectionBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureProtobufWifiBaseConfigurationCollectionIsMutable();
                    this.protobufWifiBaseConfigurationCollection_.add(builder.mo10084build());
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addMessage(builder.mo10084build());
                }
                return this;
            }

            public Builder addProtobufWifiBaseConfigurationCollection(int i, ProtobufWifiBaseConfiguration.Builder builder) {
                RepeatedFieldBuilderV3<ProtobufWifiBaseConfiguration, ProtobufWifiBaseConfiguration.Builder, ProtobufWifiBaseConfigurationOrBuilder> repeatedFieldBuilderV3 = this.protobufWifiBaseConfigurationCollectionBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureProtobufWifiBaseConfigurationCollectionIsMutable();
                    this.protobufWifiBaseConfigurationCollection_.add(i, builder.mo10084build());
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
            public com.amazon.whisperjoin.protobuf.ProtobufWifiBaseConfigurationClass.ProtobufWifiBaseConfigurationCollection.Builder mo10097mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
                /*
                    r2 = this;
                    r0 = 0
                    com.google.protobuf.Parser<com.amazon.whisperjoin.protobuf.ProtobufWifiBaseConfigurationClass$ProtobufWifiBaseConfigurationCollection> r1 = com.amazon.whisperjoin.protobuf.ProtobufWifiBaseConfigurationClass.ProtobufWifiBaseConfigurationCollection.PARSER     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                    java.lang.Object r3 = r1.mo10082parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                    com.amazon.whisperjoin.protobuf.ProtobufWifiBaseConfigurationClass$ProtobufWifiBaseConfigurationCollection r3 = (com.amazon.whisperjoin.protobuf.ProtobufWifiBaseConfigurationClass.ProtobufWifiBaseConfigurationCollection) r3     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
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
                    com.amazon.whisperjoin.protobuf.ProtobufWifiBaseConfigurationClass$ProtobufWifiBaseConfigurationCollection r4 = (com.amazon.whisperjoin.protobuf.ProtobufWifiBaseConfigurationClass.ProtobufWifiBaseConfigurationCollection) r4     // Catch: java.lang.Throwable -> Lf
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
                throw new UnsupportedOperationException("Method not decompiled: com.amazon.whisperjoin.protobuf.ProtobufWifiBaseConfigurationClass.ProtobufWifiBaseConfigurationCollection.Builder.mo10097mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.amazon.whisperjoin.protobuf.ProtobufWifiBaseConfigurationClass$ProtobufWifiBaseConfigurationCollection$Builder");
            }
        }

        public static Builder newBuilder(ProtobufWifiBaseConfigurationCollection protobufWifiBaseConfigurationCollection) {
            return DEFAULT_INSTANCE.mo10081toBuilder().mergeFrom(protobufWifiBaseConfigurationCollection);
        }

        public static ProtobufWifiBaseConfigurationCollection parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.mo8403parseFrom(byteBuffer, extensionRegistryLite);
        }

        private ProtobufWifiBaseConfigurationCollection(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        public static ProtobufWifiBaseConfigurationCollection parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ProtobufWifiBaseConfigurationCollection) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static ProtobufWifiBaseConfigurationCollection parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.mo8396parseFrom(byteString);
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        /* renamed from: getDefaultInstanceForType */
        public ProtobufWifiBaseConfigurationCollection mo10094getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        /* renamed from: toBuilder */
        public Builder mo10081toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        public static ProtobufWifiBaseConfigurationCollection parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.mo8397parseFrom(byteString, extensionRegistryLite);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        /* renamed from: newBuilderForType */
        public Builder mo10079newBuilderForType() {
            return newBuilder();
        }

        private ProtobufWifiBaseConfigurationCollection() {
            this.memoizedIsInitialized = (byte) -1;
            this.protobufWifiBaseConfigurationCollection_ = Collections.emptyList();
        }

        public static ProtobufWifiBaseConfigurationCollection parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.mo8404parseFrom(bArr);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageV3
        /* renamed from: newBuilderForType */
        public Builder mo10080newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public static ProtobufWifiBaseConfigurationCollection parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.mo8407parseFrom(bArr, extensionRegistryLite);
        }

        public static ProtobufWifiBaseConfigurationCollection parseFrom(InputStream inputStream) throws IOException {
            return (ProtobufWifiBaseConfigurationCollection) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        /* JADX WARN: Multi-variable type inference failed */
        private ProtobufWifiBaseConfigurationCollection(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                        this.protobufWifiBaseConfigurationCollection_ = new ArrayList();
                                        z2 |= true;
                                    }
                                    this.protobufWifiBaseConfigurationCollection_.add(codedInputStream.readMessage(ProtobufWifiBaseConfiguration.PARSER, extensionRegistryLite));
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
                            this.protobufWifiBaseConfigurationCollection_ = Collections.unmodifiableList(this.protobufWifiBaseConfigurationCollection_);
                        }
                        this.unknownFields = newBuilder.mo10084build();
                        makeExtensionsImmutable();
                    }
                }
                return;
            }
            throw new NullPointerException();
        }

        public static ProtobufWifiBaseConfigurationCollection parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ProtobufWifiBaseConfigurationCollection) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static ProtobufWifiBaseConfigurationCollection parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (ProtobufWifiBaseConfigurationCollection) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static ProtobufWifiBaseConfigurationCollection parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ProtobufWifiBaseConfigurationCollection) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes13.dex */
    public interface ProtobufWifiBaseConfigurationCollectionOrBuilder extends MessageOrBuilder {
        ProtobufWifiBaseConfiguration getProtobufWifiBaseConfigurationCollection(int i);

        int getProtobufWifiBaseConfigurationCollectionCount();

        List<ProtobufWifiBaseConfiguration> getProtobufWifiBaseConfigurationCollectionList();

        ProtobufWifiBaseConfigurationOrBuilder getProtobufWifiBaseConfigurationCollectionOrBuilder(int i);

        List<? extends ProtobufWifiBaseConfigurationOrBuilder> getProtobufWifiBaseConfigurationCollectionOrBuilderList();
    }

    /* loaded from: classes13.dex */
    public interface ProtobufWifiBaseConfigurationOrBuilder extends MessageOrBuilder {
        boolean getIsHiddenNetwork();

        int getNetworkPriority();

        String getSsid();

        ByteString getSsidBytes();

        ProtobufWifiKeyManagementClass.ProtobufWifiKeyManagement.WifiKeyManagement getWifiKeyManagement();

        boolean hasIsHiddenNetwork();

        boolean hasNetworkPriority();

        boolean hasSsid();

        boolean hasWifiKeyManagement();
    }

    static {
        Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\nYWhisperJoinProtocolBuffersModel/schema/provisioning/data/wifi/WifiBaseConfiguration.proto\u0012\bprotobuf\u001aUWhisperJoinProtocolBuffersModel/schema/provisioning/data/wifi/WifiKeyManagement.proto\"±\u0001\n\u001dProtobufWifiBaseConfiguration\u0012\f\n\u0004ssid\u0018\u0001 \u0002(\t\u0012P\n\u0011wifiKeyManagement\u0018\u0002 \u0002(\u000e25.protobuf.ProtobufWifiKeyManagement.WifiKeyManagement\u0012\u0017\n\u000fnetworkPriority\u0018\u0003 \u0002(\u0011\u0012\u0017\n\u000fisHiddenNetwork\u0018\u0004 \u0002(\b\"\u0083\u0001\n'ProtobufWifiBaseConfigurationCollection\u0012X\n'protobufWifiBaseConfigurationCollection\u0018\u0001 \u0003(\u000b2'.protobuf.ProtobufWifiBaseConfigurationBE\n\u001fcom.amazon.whisperjoin.protobufB\"ProtobufWifiBaseConfigurationClass"}, new Descriptors.FileDescriptor[]{ProtobufWifiKeyManagementClass.getDescriptor()}, new Descriptors.FileDescriptor.InternalDescriptorAssigner() { // from class: com.amazon.whisperjoin.protobuf.ProtobufWifiBaseConfigurationClass.1
            @Override // com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner
            public ExtensionRegistry assignDescriptors(Descriptors.FileDescriptor fileDescriptor) {
                Descriptors.FileDescriptor unused = ProtobufWifiBaseConfigurationClass.descriptor = fileDescriptor;
                return null;
            }
        });
        internal_static_protobuf_ProtobufWifiBaseConfiguration_descriptor = getDescriptor().getMessageTypes().get(0);
        internal_static_protobuf_ProtobufWifiBaseConfiguration_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_protobuf_ProtobufWifiBaseConfiguration_descriptor, new String[]{"Ssid", "WifiKeyManagement", "NetworkPriority", "IsHiddenNetwork"});
        internal_static_protobuf_ProtobufWifiBaseConfigurationCollection_descriptor = getDescriptor().getMessageTypes().get(1);
        internal_static_protobuf_ProtobufWifiBaseConfigurationCollection_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_protobuf_ProtobufWifiBaseConfigurationCollection_descriptor, new String[]{"ProtobufWifiBaseConfigurationCollection"});
        ProtobufWifiKeyManagementClass.getDescriptor();
    }

    private ProtobufWifiBaseConfigurationClass() {
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
