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
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
/* loaded from: classes13.dex */
public final class ProtobufWifiConfigurationClass {
    private static Descriptors.FileDescriptor descriptor;
    private static final Descriptors.Descriptor internal_static_protobuf_ProtobufWifiConfiguration_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_protobuf_ProtobufWifiConfiguration_fieldAccessorTable;

    /* loaded from: classes13.dex */
    public static final class ProtobufWifiConfiguration extends GeneratedMessageV3 implements ProtobufWifiConfigurationOrBuilder {
        public static final int ISHIDDENNETWORK_FIELD_NUMBER = 4;
        public static final int NETWORKPRIORITY_FIELD_NUMBER = 3;
        public static final int PSK_FIELD_NUMBER = 6;
        public static final int SSID_FIELD_NUMBER = 1;
        public static final int WEPKEY_FIELD_NUMBER = 5;
        public static final int WIFIKEYMANAGEMENT_FIELD_NUMBER = 2;
        private static final long serialVersionUID = 0;
        private int bitField0_;
        private boolean isHiddenNetwork_;
        private byte memoizedIsInitialized;
        private int networkPriority_;
        private volatile Object psk_;
        private volatile Object ssid_;
        private volatile Object wepKey_;
        private int wifiKeyManagement_;
        private static final ProtobufWifiConfiguration DEFAULT_INSTANCE = new ProtobufWifiConfiguration();
        @Deprecated
        public static final Parser<ProtobufWifiConfiguration> PARSER = new AbstractParser<ProtobufWifiConfiguration>() { // from class: com.amazon.whisperjoin.protobuf.ProtobufWifiConfigurationClass.ProtobufWifiConfiguration.1
            @Override // com.google.protobuf.Parser
            /* renamed from: parsePartialFrom */
            public ProtobufWifiConfiguration mo10082parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new ProtobufWifiConfiguration(codedInputStream, extensionRegistryLite);
            }
        };

        public static ProtobufWifiConfiguration getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return ProtobufWifiConfigurationClass.internal_static_protobuf_ProtobufWifiConfiguration_descriptor;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.mo10081toBuilder();
        }

        public static ProtobufWifiConfiguration parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (ProtobufWifiConfiguration) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static ProtobufWifiConfiguration parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.mo8402parseFrom(byteBuffer);
        }

        public static Parser<ProtobufWifiConfiguration> parser() {
            return PARSER;
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof ProtobufWifiConfiguration)) {
                return super.equals(obj);
            }
            ProtobufWifiConfiguration protobufWifiConfiguration = (ProtobufWifiConfiguration) obj;
            boolean z = hasSsid() == protobufWifiConfiguration.hasSsid();
            if (hasSsid()) {
                z = z && getSsid().equals(protobufWifiConfiguration.getSsid());
            }
            boolean z2 = z && hasWifiKeyManagement() == protobufWifiConfiguration.hasWifiKeyManagement();
            if (hasWifiKeyManagement()) {
                z2 = z2 && this.wifiKeyManagement_ == protobufWifiConfiguration.wifiKeyManagement_;
            }
            boolean z3 = z2 && hasNetworkPriority() == protobufWifiConfiguration.hasNetworkPriority();
            if (hasNetworkPriority()) {
                z3 = z3 && getNetworkPriority() == protobufWifiConfiguration.getNetworkPriority();
            }
            boolean z4 = z3 && hasIsHiddenNetwork() == protobufWifiConfiguration.hasIsHiddenNetwork();
            if (hasIsHiddenNetwork()) {
                z4 = z4 && getIsHiddenNetwork() == protobufWifiConfiguration.getIsHiddenNetwork();
            }
            boolean z5 = z4 && hasWepKey() == protobufWifiConfiguration.hasWepKey();
            if (hasWepKey()) {
                z5 = z5 && getWepKey().equals(protobufWifiConfiguration.getWepKey());
            }
            boolean z6 = z5 && hasPsk() == protobufWifiConfiguration.hasPsk();
            if (hasPsk()) {
                z6 = z6 && getPsk().equals(protobufWifiConfiguration.getPsk());
            }
            return z6 && this.unknownFields.equals(protobufWifiConfiguration.unknownFields);
        }

        @Override // com.amazon.whisperjoin.protobuf.ProtobufWifiConfigurationClass.ProtobufWifiConfigurationOrBuilder
        public boolean getIsHiddenNetwork() {
            return this.isHiddenNetwork_;
        }

        @Override // com.amazon.whisperjoin.protobuf.ProtobufWifiConfigurationClass.ProtobufWifiConfigurationOrBuilder
        public int getNetworkPriority() {
            return this.networkPriority_;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
        /* renamed from: getParserForType */
        public Parser<ProtobufWifiConfiguration> mo9954getParserForType() {
            return PARSER;
        }

        @Override // com.amazon.whisperjoin.protobuf.ProtobufWifiConfigurationClass.ProtobufWifiConfigurationOrBuilder
        public String getPsk() {
            Object obj = this.psk_;
            if (obj instanceof String) {
                return (String) obj;
            }
            ByteString byteString = (ByteString) obj;
            String stringUtf8 = byteString.toStringUtf8();
            if (byteString.isValidUtf8()) {
                this.psk_ = stringUtf8;
            }
            return stringUtf8;
        }

        @Override // com.amazon.whisperjoin.protobuf.ProtobufWifiConfigurationClass.ProtobufWifiConfigurationOrBuilder
        public ByteString getPskBytes() {
            Object obj = this.psk_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.psk_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
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
            if ((this.bitField0_ & 16) == 16) {
                i2 += GeneratedMessageV3.computeStringSize(5, this.wepKey_);
            }
            if ((this.bitField0_ & 32) == 32) {
                i2 += GeneratedMessageV3.computeStringSize(6, this.psk_);
            }
            int serializedSize = this.unknownFields.getSerializedSize() + i2;
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.amazon.whisperjoin.protobuf.ProtobufWifiConfigurationClass.ProtobufWifiConfigurationOrBuilder
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

        @Override // com.amazon.whisperjoin.protobuf.ProtobufWifiConfigurationClass.ProtobufWifiConfigurationOrBuilder
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

        @Override // com.amazon.whisperjoin.protobuf.ProtobufWifiConfigurationClass.ProtobufWifiConfigurationOrBuilder
        public String getWepKey() {
            Object obj = this.wepKey_;
            if (obj instanceof String) {
                return (String) obj;
            }
            ByteString byteString = (ByteString) obj;
            String stringUtf8 = byteString.toStringUtf8();
            if (byteString.isValidUtf8()) {
                this.wepKey_ = stringUtf8;
            }
            return stringUtf8;
        }

        @Override // com.amazon.whisperjoin.protobuf.ProtobufWifiConfigurationClass.ProtobufWifiConfigurationOrBuilder
        public ByteString getWepKeyBytes() {
            Object obj = this.wepKey_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.wepKey_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override // com.amazon.whisperjoin.protobuf.ProtobufWifiConfigurationClass.ProtobufWifiConfigurationOrBuilder
        public ProtobufWifiKeyManagementClass.ProtobufWifiKeyManagement.WifiKeyManagement getWifiKeyManagement() {
            ProtobufWifiKeyManagementClass.ProtobufWifiKeyManagement.WifiKeyManagement valueOf = ProtobufWifiKeyManagementClass.ProtobufWifiKeyManagement.WifiKeyManagement.valueOf(this.wifiKeyManagement_);
            return valueOf == null ? ProtobufWifiKeyManagementClass.ProtobufWifiKeyManagement.WifiKeyManagement.NONE : valueOf;
        }

        @Override // com.amazon.whisperjoin.protobuf.ProtobufWifiConfigurationClass.ProtobufWifiConfigurationOrBuilder
        public boolean hasIsHiddenNetwork() {
            return (this.bitField0_ & 8) == 8;
        }

        @Override // com.amazon.whisperjoin.protobuf.ProtobufWifiConfigurationClass.ProtobufWifiConfigurationOrBuilder
        public boolean hasNetworkPriority() {
            return (this.bitField0_ & 4) == 4;
        }

        @Override // com.amazon.whisperjoin.protobuf.ProtobufWifiConfigurationClass.ProtobufWifiConfigurationOrBuilder
        public boolean hasPsk() {
            return (this.bitField0_ & 32) == 32;
        }

        @Override // com.amazon.whisperjoin.protobuf.ProtobufWifiConfigurationClass.ProtobufWifiConfigurationOrBuilder
        public boolean hasSsid() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // com.amazon.whisperjoin.protobuf.ProtobufWifiConfigurationClass.ProtobufWifiConfigurationOrBuilder
        public boolean hasWepKey() {
            return (this.bitField0_ & 16) == 16;
        }

        @Override // com.amazon.whisperjoin.protobuf.ProtobufWifiConfigurationClass.ProtobufWifiConfigurationOrBuilder
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
            if (hasWepKey()) {
                hashCode = GeneratedOutlineSupport1.outline4(hashCode, 37, 5, 53) + getWepKey().hashCode();
            }
            if (hasPsk()) {
                hashCode = GeneratedOutlineSupport1.outline4(hashCode, 37, 6, 53) + getPsk().hashCode();
            }
            int hashCode2 = this.unknownFields.hashCode() + (hashCode * 29);
            this.memoizedHashCode = hashCode2;
            return hashCode2;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return ProtobufWifiConfigurationClass.internal_static_protobuf_ProtobufWifiConfiguration_fieldAccessorTable.ensureFieldAccessorsInitialized(ProtobufWifiConfiguration.class, Builder.class);
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
            if ((this.bitField0_ & 16) == 16) {
                GeneratedMessageV3.writeString(codedOutputStream, 5, this.wepKey_);
            }
            if ((this.bitField0_ & 32) == 32) {
                GeneratedMessageV3.writeString(codedOutputStream, 6, this.psk_);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        /* loaded from: classes13.dex */
        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements ProtobufWifiConfigurationOrBuilder {
            private int bitField0_;
            private boolean isHiddenNetwork_;
            private int networkPriority_;
            private Object psk_;
            private Object ssid_;
            private Object wepKey_;
            private int wifiKeyManagement_;

            public static final Descriptors.Descriptor getDescriptor() {
                return ProtobufWifiConfigurationClass.internal_static_protobuf_ProtobufWifiConfiguration_descriptor;
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

            public Builder clearPsk() {
                this.bitField0_ &= -33;
                this.psk_ = ProtobufWifiConfiguration.getDefaultInstance().getPsk();
                onChanged();
                return this;
            }

            public Builder clearSsid() {
                this.bitField0_ &= -2;
                this.ssid_ = ProtobufWifiConfiguration.getDefaultInstance().getSsid();
                onChanged();
                return this;
            }

            public Builder clearWepKey() {
                this.bitField0_ &= -17;
                this.wepKey_ = ProtobufWifiConfiguration.getDefaultInstance().getWepKey();
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
                return ProtobufWifiConfigurationClass.internal_static_protobuf_ProtobufWifiConfiguration_descriptor;
            }

            @Override // com.amazon.whisperjoin.protobuf.ProtobufWifiConfigurationClass.ProtobufWifiConfigurationOrBuilder
            public boolean getIsHiddenNetwork() {
                return this.isHiddenNetwork_;
            }

            @Override // com.amazon.whisperjoin.protobuf.ProtobufWifiConfigurationClass.ProtobufWifiConfigurationOrBuilder
            public int getNetworkPriority() {
                return this.networkPriority_;
            }

            @Override // com.amazon.whisperjoin.protobuf.ProtobufWifiConfigurationClass.ProtobufWifiConfigurationOrBuilder
            public String getPsk() {
                Object obj = this.psk_;
                if (!(obj instanceof String)) {
                    ByteString byteString = (ByteString) obj;
                    String stringUtf8 = byteString.toStringUtf8();
                    if (byteString.isValidUtf8()) {
                        this.psk_ = stringUtf8;
                    }
                    return stringUtf8;
                }
                return (String) obj;
            }

            @Override // com.amazon.whisperjoin.protobuf.ProtobufWifiConfigurationClass.ProtobufWifiConfigurationOrBuilder
            public ByteString getPskBytes() {
                Object obj = this.psk_;
                if (obj instanceof String) {
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.psk_ = copyFromUtf8;
                    return copyFromUtf8;
                }
                return (ByteString) obj;
            }

            @Override // com.amazon.whisperjoin.protobuf.ProtobufWifiConfigurationClass.ProtobufWifiConfigurationOrBuilder
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

            @Override // com.amazon.whisperjoin.protobuf.ProtobufWifiConfigurationClass.ProtobufWifiConfigurationOrBuilder
            public ByteString getSsidBytes() {
                Object obj = this.ssid_;
                if (obj instanceof String) {
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.ssid_ = copyFromUtf8;
                    return copyFromUtf8;
                }
                return (ByteString) obj;
            }

            @Override // com.amazon.whisperjoin.protobuf.ProtobufWifiConfigurationClass.ProtobufWifiConfigurationOrBuilder
            public String getWepKey() {
                Object obj = this.wepKey_;
                if (!(obj instanceof String)) {
                    ByteString byteString = (ByteString) obj;
                    String stringUtf8 = byteString.toStringUtf8();
                    if (byteString.isValidUtf8()) {
                        this.wepKey_ = stringUtf8;
                    }
                    return stringUtf8;
                }
                return (String) obj;
            }

            @Override // com.amazon.whisperjoin.protobuf.ProtobufWifiConfigurationClass.ProtobufWifiConfigurationOrBuilder
            public ByteString getWepKeyBytes() {
                Object obj = this.wepKey_;
                if (obj instanceof String) {
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.wepKey_ = copyFromUtf8;
                    return copyFromUtf8;
                }
                return (ByteString) obj;
            }

            @Override // com.amazon.whisperjoin.protobuf.ProtobufWifiConfigurationClass.ProtobufWifiConfigurationOrBuilder
            public ProtobufWifiKeyManagementClass.ProtobufWifiKeyManagement.WifiKeyManagement getWifiKeyManagement() {
                ProtobufWifiKeyManagementClass.ProtobufWifiKeyManagement.WifiKeyManagement valueOf = ProtobufWifiKeyManagementClass.ProtobufWifiKeyManagement.WifiKeyManagement.valueOf(this.wifiKeyManagement_);
                return valueOf == null ? ProtobufWifiKeyManagementClass.ProtobufWifiKeyManagement.WifiKeyManagement.NONE : valueOf;
            }

            @Override // com.amazon.whisperjoin.protobuf.ProtobufWifiConfigurationClass.ProtobufWifiConfigurationOrBuilder
            public boolean hasIsHiddenNetwork() {
                return (this.bitField0_ & 8) == 8;
            }

            @Override // com.amazon.whisperjoin.protobuf.ProtobufWifiConfigurationClass.ProtobufWifiConfigurationOrBuilder
            public boolean hasNetworkPriority() {
                return (this.bitField0_ & 4) == 4;
            }

            @Override // com.amazon.whisperjoin.protobuf.ProtobufWifiConfigurationClass.ProtobufWifiConfigurationOrBuilder
            public boolean hasPsk() {
                return (this.bitField0_ & 32) == 32;
            }

            @Override // com.amazon.whisperjoin.protobuf.ProtobufWifiConfigurationClass.ProtobufWifiConfigurationOrBuilder
            public boolean hasSsid() {
                return (this.bitField0_ & 1) == 1;
            }

            @Override // com.amazon.whisperjoin.protobuf.ProtobufWifiConfigurationClass.ProtobufWifiConfigurationOrBuilder
            public boolean hasWepKey() {
                return (this.bitField0_ & 16) == 16;
            }

            @Override // com.amazon.whisperjoin.protobuf.ProtobufWifiConfigurationClass.ProtobufWifiConfigurationOrBuilder
            public boolean hasWifiKeyManagement() {
                return (this.bitField0_ & 2) == 2;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return ProtobufWifiConfigurationClass.internal_static_protobuf_ProtobufWifiConfiguration_fieldAccessorTable.ensureFieldAccessorsInitialized(ProtobufWifiConfiguration.class, Builder.class);
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

            public Builder setPsk(String str) {
                if (str != null) {
                    this.bitField0_ |= 32;
                    this.psk_ = str;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder setPskBytes(ByteString byteString) {
                if (byteString != null) {
                    this.bitField0_ |= 32;
                    this.psk_ = byteString;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
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

            public Builder setWepKey(String str) {
                if (str != null) {
                    this.bitField0_ |= 16;
                    this.wepKey_ = str;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder setWepKeyBytes(ByteString byteString) {
                if (byteString != null) {
                    this.bitField0_ |= 16;
                    this.wepKey_ = byteString;
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
                this.wepKey_ = "";
                this.psk_ = "";
                maybeForceBuilderInitialization();
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            /* renamed from: addRepeatedField */
            public Builder mo10083addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.mo10083addRepeatedField(fieldDescriptor, obj);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: build */
            public ProtobufWifiConfiguration mo10084build() {
                ProtobufWifiConfiguration mo10085buildPartial = mo10085buildPartial();
                if (mo10085buildPartial.isInitialized()) {
                    return mo10085buildPartial;
                }
                throw AbstractMessage.Builder.newUninitializedMessageException((Message) mo10085buildPartial);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: buildPartial */
            public ProtobufWifiConfiguration mo10085buildPartial() {
                ProtobufWifiConfiguration protobufWifiConfiguration = new ProtobufWifiConfiguration(this);
                int i = this.bitField0_;
                int i2 = 1;
                if ((i & 1) != 1) {
                    i2 = 0;
                }
                protobufWifiConfiguration.ssid_ = this.ssid_;
                if ((i & 2) == 2) {
                    i2 |= 2;
                }
                protobufWifiConfiguration.wifiKeyManagement_ = this.wifiKeyManagement_;
                if ((i & 4) == 4) {
                    i2 |= 4;
                }
                protobufWifiConfiguration.networkPriority_ = this.networkPriority_;
                if ((i & 8) == 8) {
                    i2 |= 8;
                }
                protobufWifiConfiguration.isHiddenNetwork_ = this.isHiddenNetwork_;
                if ((i & 16) == 16) {
                    i2 |= 16;
                }
                protobufWifiConfiguration.wepKey_ = this.wepKey_;
                if ((i & 32) == 32) {
                    i2 |= 32;
                }
                protobufWifiConfiguration.psk_ = this.psk_;
                protobufWifiConfiguration.bitField0_ = i2;
                onBuilt();
                return protobufWifiConfiguration;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            /* renamed from: clearField */
            public Builder mo10088clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (Builder) super.mo10088clearField(fieldDescriptor);
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            /* renamed from: getDefaultInstanceForType */
            public ProtobufWifiConfiguration mo10094getDefaultInstanceForType() {
                return ProtobufWifiConfiguration.getDefaultInstance();
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
                this.wepKey_ = "";
                this.bitField0_ &= -17;
                this.psk_ = "";
                this.bitField0_ &= -33;
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
                if (message instanceof ProtobufWifiConfiguration) {
                    return mergeFrom((ProtobufWifiConfiguration) message);
                }
                super.mo10096mergeFrom(message);
                return this;
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.ssid_ = "";
                this.wifiKeyManagement_ = 0;
                this.wepKey_ = "";
                this.psk_ = "";
                maybeForceBuilderInitialization();
            }

            public Builder mergeFrom(ProtobufWifiConfiguration protobufWifiConfiguration) {
                if (protobufWifiConfiguration == ProtobufWifiConfiguration.getDefaultInstance()) {
                    return this;
                }
                if (protobufWifiConfiguration.hasSsid()) {
                    this.bitField0_ |= 1;
                    this.ssid_ = protobufWifiConfiguration.ssid_;
                    onChanged();
                }
                if (protobufWifiConfiguration.hasWifiKeyManagement()) {
                    setWifiKeyManagement(protobufWifiConfiguration.getWifiKeyManagement());
                }
                if (protobufWifiConfiguration.hasNetworkPriority()) {
                    setNetworkPriority(protobufWifiConfiguration.getNetworkPriority());
                }
                if (protobufWifiConfiguration.hasIsHiddenNetwork()) {
                    setIsHiddenNetwork(protobufWifiConfiguration.getIsHiddenNetwork());
                }
                if (protobufWifiConfiguration.hasWepKey()) {
                    this.bitField0_ |= 16;
                    this.wepKey_ = protobufWifiConfiguration.wepKey_;
                    onChanged();
                }
                if (protobufWifiConfiguration.hasPsk()) {
                    this.bitField0_ |= 32;
                    this.psk_ = protobufWifiConfiguration.psk_;
                    onChanged();
                }
                mo10099mergeUnknownFields(((GeneratedMessageV3) protobufWifiConfiguration).unknownFields);
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
            public com.amazon.whisperjoin.protobuf.ProtobufWifiConfigurationClass.ProtobufWifiConfiguration.Builder mo10097mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
                /*
                    r2 = this;
                    r0 = 0
                    com.google.protobuf.Parser<com.amazon.whisperjoin.protobuf.ProtobufWifiConfigurationClass$ProtobufWifiConfiguration> r1 = com.amazon.whisperjoin.protobuf.ProtobufWifiConfigurationClass.ProtobufWifiConfiguration.PARSER     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                    java.lang.Object r3 = r1.mo10082parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                    com.amazon.whisperjoin.protobuf.ProtobufWifiConfigurationClass$ProtobufWifiConfiguration r3 = (com.amazon.whisperjoin.protobuf.ProtobufWifiConfigurationClass.ProtobufWifiConfiguration) r3     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
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
                    com.amazon.whisperjoin.protobuf.ProtobufWifiConfigurationClass$ProtobufWifiConfiguration r4 = (com.amazon.whisperjoin.protobuf.ProtobufWifiConfigurationClass.ProtobufWifiConfiguration) r4     // Catch: java.lang.Throwable -> Lf
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
                throw new UnsupportedOperationException("Method not decompiled: com.amazon.whisperjoin.protobuf.ProtobufWifiConfigurationClass.ProtobufWifiConfiguration.Builder.mo10097mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.amazon.whisperjoin.protobuf.ProtobufWifiConfigurationClass$ProtobufWifiConfiguration$Builder");
            }
        }

        public static Builder newBuilder(ProtobufWifiConfiguration protobufWifiConfiguration) {
            return DEFAULT_INSTANCE.mo10081toBuilder().mergeFrom(protobufWifiConfiguration);
        }

        public static ProtobufWifiConfiguration parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.mo8403parseFrom(byteBuffer, extensionRegistryLite);
        }

        private ProtobufWifiConfiguration(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        public static ProtobufWifiConfiguration parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ProtobufWifiConfiguration) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static ProtobufWifiConfiguration parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.mo8396parseFrom(byteString);
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        /* renamed from: getDefaultInstanceForType */
        public ProtobufWifiConfiguration mo10094getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        /* renamed from: toBuilder */
        public Builder mo10081toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        public static ProtobufWifiConfiguration parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.mo8397parseFrom(byteString, extensionRegistryLite);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        /* renamed from: newBuilderForType */
        public Builder mo10079newBuilderForType() {
            return newBuilder();
        }

        private ProtobufWifiConfiguration() {
            this.memoizedIsInitialized = (byte) -1;
            this.ssid_ = "";
            this.wifiKeyManagement_ = 0;
            this.networkPriority_ = 0;
            this.isHiddenNetwork_ = false;
            this.wepKey_ = "";
            this.psk_ = "";
        }

        public static ProtobufWifiConfiguration parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.mo8404parseFrom(bArr);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageV3
        /* renamed from: newBuilderForType */
        public Builder mo10080newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public static ProtobufWifiConfiguration parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.mo8407parseFrom(bArr, extensionRegistryLite);
        }

        public static ProtobufWifiConfiguration parseFrom(InputStream inputStream) throws IOException {
            return (ProtobufWifiConfiguration) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static ProtobufWifiConfiguration parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ProtobufWifiConfiguration) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static ProtobufWifiConfiguration parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (ProtobufWifiConfiguration) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        private ProtobufWifiConfiguration(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                } else if (readTag == 32) {
                                    this.bitField0_ |= 8;
                                    this.isHiddenNetwork_ = codedInputStream.readBool();
                                } else if (readTag == 42) {
                                    ByteString readBytes2 = codedInputStream.readBytes();
                                    this.bitField0_ |= 16;
                                    this.wepKey_ = readBytes2;
                                } else if (readTag != 50) {
                                    if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                    }
                                } else {
                                    ByteString readBytes3 = codedInputStream.readBytes();
                                    this.bitField0_ |= 32;
                                    this.psk_ = readBytes3;
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

        public static ProtobufWifiConfiguration parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ProtobufWifiConfiguration) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes13.dex */
    public interface ProtobufWifiConfigurationOrBuilder extends MessageOrBuilder {
        boolean getIsHiddenNetwork();

        int getNetworkPriority();

        String getPsk();

        ByteString getPskBytes();

        String getSsid();

        ByteString getSsidBytes();

        String getWepKey();

        ByteString getWepKeyBytes();

        ProtobufWifiKeyManagementClass.ProtobufWifiKeyManagement.WifiKeyManagement getWifiKeyManagement();

        boolean hasIsHiddenNetwork();

        boolean hasNetworkPriority();

        boolean hasPsk();

        boolean hasSsid();

        boolean hasWepKey();

        boolean hasWifiKeyManagement();
    }

    static {
        Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\nUWhisperJoinProtocolBuffersModel/schema/provisioning/data/wifi/WifiConfiguration.proto\u0012\bprotobuf\u001aUWhisperJoinProtocolBuffersModel/schema/provisioning/data/wifi/WifiKeyManagement.proto\"Ê\u0001\n\u0019ProtobufWifiConfiguration\u0012\f\n\u0004ssid\u0018\u0001 \u0002(\t\u0012P\n\u0011wifiKeyManagement\u0018\u0002 \u0002(\u000e25.protobuf.ProtobufWifiKeyManagement.WifiKeyManagement\u0012\u0017\n\u000fnetworkPriority\u0018\u0003 \u0002(\u0011\u0012\u0017\n\u000fisHiddenNetwork\u0018\u0004 \u0002(\b\u0012\u000e\n\u0006wepKey\u0018\u0005 \u0001(\t\u0012\u000b\n\u0003psk\u0018\u0006 \u0001(\tBA\n\u001fcom.amazon.whisperjoin.protobufB\u001eProtobufWifiConfigurationClass"}, new Descriptors.FileDescriptor[]{ProtobufWifiKeyManagementClass.getDescriptor()}, new Descriptors.FileDescriptor.InternalDescriptorAssigner() { // from class: com.amazon.whisperjoin.protobuf.ProtobufWifiConfigurationClass.1
            @Override // com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner
            public ExtensionRegistry assignDescriptors(Descriptors.FileDescriptor fileDescriptor) {
                Descriptors.FileDescriptor unused = ProtobufWifiConfigurationClass.descriptor = fileDescriptor;
                return null;
            }
        });
        internal_static_protobuf_ProtobufWifiConfiguration_descriptor = getDescriptor().getMessageTypes().get(0);
        internal_static_protobuf_ProtobufWifiConfiguration_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_protobuf_ProtobufWifiConfiguration_descriptor, new String[]{"Ssid", "WifiKeyManagement", "NetworkPriority", "IsHiddenNetwork", "WepKey", "Psk"});
        ProtobufWifiKeyManagementClass.getDescriptor();
    }

    private ProtobufWifiConfigurationClass() {
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
