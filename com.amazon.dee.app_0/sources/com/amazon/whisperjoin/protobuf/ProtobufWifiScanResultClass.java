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
public final class ProtobufWifiScanResultClass {
    private static Descriptors.FileDescriptor descriptor;
    private static final Descriptors.Descriptor internal_static_protobuf_ProtobufWifiScanResultCollection_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_protobuf_ProtobufWifiScanResultCollection_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_protobuf_ProtobufWifiScanResult_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_protobuf_ProtobufWifiScanResult_fieldAccessorTable;

    /* loaded from: classes13.dex */
    public static final class ProtobufWifiScanResult extends GeneratedMessageV3 implements ProtobufWifiScanResultOrBuilder {
        public static final int FREQUENCYBAND_FIELD_NUMBER = 3;
        public static final int SIGNALSTRENGTH_FIELD_NUMBER = 4;
        public static final int SSID_FIELD_NUMBER = 1;
        public static final int WIFIKEYMANAGEMENT_FIELD_NUMBER = 2;
        private static final long serialVersionUID = 0;
        private int bitField0_;
        private int frequencyBand_;
        private byte memoizedIsInitialized;
        private int signalStrength_;
        private volatile Object ssid_;
        private int wifiKeyManagement_;
        private static final ProtobufWifiScanResult DEFAULT_INSTANCE = new ProtobufWifiScanResult();
        @Deprecated
        public static final Parser<ProtobufWifiScanResult> PARSER = new AbstractParser<ProtobufWifiScanResult>() { // from class: com.amazon.whisperjoin.protobuf.ProtobufWifiScanResultClass.ProtobufWifiScanResult.1
            @Override // com.google.protobuf.Parser
            /* renamed from: parsePartialFrom */
            public ProtobufWifiScanResult mo10082parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new ProtobufWifiScanResult(codedInputStream, extensionRegistryLite);
            }
        };

        public static ProtobufWifiScanResult getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return ProtobufWifiScanResultClass.internal_static_protobuf_ProtobufWifiScanResult_descriptor;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.mo10081toBuilder();
        }

        public static ProtobufWifiScanResult parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (ProtobufWifiScanResult) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static ProtobufWifiScanResult parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.mo8402parseFrom(byteBuffer);
        }

        public static Parser<ProtobufWifiScanResult> parser() {
            return PARSER;
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof ProtobufWifiScanResult)) {
                return super.equals(obj);
            }
            ProtobufWifiScanResult protobufWifiScanResult = (ProtobufWifiScanResult) obj;
            boolean z = hasSsid() == protobufWifiScanResult.hasSsid();
            if (hasSsid()) {
                z = z && getSsid().equals(protobufWifiScanResult.getSsid());
            }
            boolean z2 = z && hasWifiKeyManagement() == protobufWifiScanResult.hasWifiKeyManagement();
            if (hasWifiKeyManagement()) {
                z2 = z2 && this.wifiKeyManagement_ == protobufWifiScanResult.wifiKeyManagement_;
            }
            boolean z3 = z2 && hasFrequencyBand() == protobufWifiScanResult.hasFrequencyBand();
            if (hasFrequencyBand()) {
                z3 = z3 && getFrequencyBand() == protobufWifiScanResult.getFrequencyBand();
            }
            boolean z4 = z3 && hasSignalStrength() == protobufWifiScanResult.hasSignalStrength();
            if (hasSignalStrength()) {
                z4 = z4 && getSignalStrength() == protobufWifiScanResult.getSignalStrength();
            }
            return z4 && this.unknownFields.equals(protobufWifiScanResult.unknownFields);
        }

        @Override // com.amazon.whisperjoin.protobuf.ProtobufWifiScanResultClass.ProtobufWifiScanResultOrBuilder
        public int getFrequencyBand() {
            return this.frequencyBand_;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
        /* renamed from: getParserForType */
        public Parser<ProtobufWifiScanResult> mo9954getParserForType() {
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
                i2 += CodedOutputStream.computeSInt32Size(3, this.frequencyBand_);
            }
            if ((this.bitField0_ & 8) == 8) {
                i2 += CodedOutputStream.computeSInt32Size(4, this.signalStrength_);
            }
            int serializedSize = this.unknownFields.getSerializedSize() + i2;
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.amazon.whisperjoin.protobuf.ProtobufWifiScanResultClass.ProtobufWifiScanResultOrBuilder
        public int getSignalStrength() {
            return this.signalStrength_;
        }

        @Override // com.amazon.whisperjoin.protobuf.ProtobufWifiScanResultClass.ProtobufWifiScanResultOrBuilder
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

        @Override // com.amazon.whisperjoin.protobuf.ProtobufWifiScanResultClass.ProtobufWifiScanResultOrBuilder
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

        @Override // com.amazon.whisperjoin.protobuf.ProtobufWifiScanResultClass.ProtobufWifiScanResultOrBuilder
        public ProtobufWifiKeyManagementClass.ProtobufWifiKeyManagement.WifiKeyManagement getWifiKeyManagement() {
            ProtobufWifiKeyManagementClass.ProtobufWifiKeyManagement.WifiKeyManagement valueOf = ProtobufWifiKeyManagementClass.ProtobufWifiKeyManagement.WifiKeyManagement.valueOf(this.wifiKeyManagement_);
            return valueOf == null ? ProtobufWifiKeyManagementClass.ProtobufWifiKeyManagement.WifiKeyManagement.NONE : valueOf;
        }

        @Override // com.amazon.whisperjoin.protobuf.ProtobufWifiScanResultClass.ProtobufWifiScanResultOrBuilder
        public boolean hasFrequencyBand() {
            return (this.bitField0_ & 4) == 4;
        }

        @Override // com.amazon.whisperjoin.protobuf.ProtobufWifiScanResultClass.ProtobufWifiScanResultOrBuilder
        public boolean hasSignalStrength() {
            return (this.bitField0_ & 8) == 8;
        }

        @Override // com.amazon.whisperjoin.protobuf.ProtobufWifiScanResultClass.ProtobufWifiScanResultOrBuilder
        public boolean hasSsid() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // com.amazon.whisperjoin.protobuf.ProtobufWifiScanResultClass.ProtobufWifiScanResultOrBuilder
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
            if (hasFrequencyBand()) {
                hashCode = GeneratedOutlineSupport1.outline4(hashCode, 37, 3, 53) + getFrequencyBand();
            }
            if (hasSignalStrength()) {
                hashCode = GeneratedOutlineSupport1.outline4(hashCode, 37, 4, 53) + getSignalStrength();
            }
            int hashCode2 = this.unknownFields.hashCode() + (hashCode * 29);
            this.memoizedHashCode = hashCode2;
            return hashCode2;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return ProtobufWifiScanResultClass.internal_static_protobuf_ProtobufWifiScanResult_fieldAccessorTable.ensureFieldAccessorsInitialized(ProtobufWifiScanResult.class, Builder.class);
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
            } else if (!hasFrequencyBand()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            } else if (!hasSignalStrength()) {
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
                codedOutputStream.writeSInt32(3, this.frequencyBand_);
            }
            if ((this.bitField0_ & 8) == 8) {
                codedOutputStream.writeSInt32(4, this.signalStrength_);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        /* loaded from: classes13.dex */
        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements ProtobufWifiScanResultOrBuilder {
            private int bitField0_;
            private int frequencyBand_;
            private int signalStrength_;
            private Object ssid_;
            private int wifiKeyManagement_;

            public static final Descriptors.Descriptor getDescriptor() {
                return ProtobufWifiScanResultClass.internal_static_protobuf_ProtobufWifiScanResult_descriptor;
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = GeneratedMessageV3.alwaysUseFieldBuilders;
            }

            public Builder clearFrequencyBand() {
                this.bitField0_ &= -5;
                this.frequencyBand_ = 0;
                onChanged();
                return this;
            }

            public Builder clearSignalStrength() {
                this.bitField0_ &= -9;
                this.signalStrength_ = 0;
                onChanged();
                return this;
            }

            public Builder clearSsid() {
                this.bitField0_ &= -2;
                this.ssid_ = ProtobufWifiScanResult.getDefaultInstance().getSsid();
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
                return ProtobufWifiScanResultClass.internal_static_protobuf_ProtobufWifiScanResult_descriptor;
            }

            @Override // com.amazon.whisperjoin.protobuf.ProtobufWifiScanResultClass.ProtobufWifiScanResultOrBuilder
            public int getFrequencyBand() {
                return this.frequencyBand_;
            }

            @Override // com.amazon.whisperjoin.protobuf.ProtobufWifiScanResultClass.ProtobufWifiScanResultOrBuilder
            public int getSignalStrength() {
                return this.signalStrength_;
            }

            @Override // com.amazon.whisperjoin.protobuf.ProtobufWifiScanResultClass.ProtobufWifiScanResultOrBuilder
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

            @Override // com.amazon.whisperjoin.protobuf.ProtobufWifiScanResultClass.ProtobufWifiScanResultOrBuilder
            public ByteString getSsidBytes() {
                Object obj = this.ssid_;
                if (obj instanceof String) {
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.ssid_ = copyFromUtf8;
                    return copyFromUtf8;
                }
                return (ByteString) obj;
            }

            @Override // com.amazon.whisperjoin.protobuf.ProtobufWifiScanResultClass.ProtobufWifiScanResultOrBuilder
            public ProtobufWifiKeyManagementClass.ProtobufWifiKeyManagement.WifiKeyManagement getWifiKeyManagement() {
                ProtobufWifiKeyManagementClass.ProtobufWifiKeyManagement.WifiKeyManagement valueOf = ProtobufWifiKeyManagementClass.ProtobufWifiKeyManagement.WifiKeyManagement.valueOf(this.wifiKeyManagement_);
                return valueOf == null ? ProtobufWifiKeyManagementClass.ProtobufWifiKeyManagement.WifiKeyManagement.NONE : valueOf;
            }

            @Override // com.amazon.whisperjoin.protobuf.ProtobufWifiScanResultClass.ProtobufWifiScanResultOrBuilder
            public boolean hasFrequencyBand() {
                return (this.bitField0_ & 4) == 4;
            }

            @Override // com.amazon.whisperjoin.protobuf.ProtobufWifiScanResultClass.ProtobufWifiScanResultOrBuilder
            public boolean hasSignalStrength() {
                return (this.bitField0_ & 8) == 8;
            }

            @Override // com.amazon.whisperjoin.protobuf.ProtobufWifiScanResultClass.ProtobufWifiScanResultOrBuilder
            public boolean hasSsid() {
                return (this.bitField0_ & 1) == 1;
            }

            @Override // com.amazon.whisperjoin.protobuf.ProtobufWifiScanResultClass.ProtobufWifiScanResultOrBuilder
            public boolean hasWifiKeyManagement() {
                return (this.bitField0_ & 2) == 2;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return ProtobufWifiScanResultClass.internal_static_protobuf_ProtobufWifiScanResult_fieldAccessorTable.ensureFieldAccessorsInitialized(ProtobufWifiScanResult.class, Builder.class);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return hasSsid() && hasWifiKeyManagement() && hasFrequencyBand() && hasSignalStrength();
            }

            public Builder setFrequencyBand(int i) {
                this.bitField0_ |= 4;
                this.frequencyBand_ = i;
                onChanged();
                return this;
            }

            public Builder setSignalStrength(int i) {
                this.bitField0_ |= 8;
                this.signalStrength_ = i;
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
            public ProtobufWifiScanResult mo10084build() {
                ProtobufWifiScanResult mo10085buildPartial = mo10085buildPartial();
                if (mo10085buildPartial.isInitialized()) {
                    return mo10085buildPartial;
                }
                throw AbstractMessage.Builder.newUninitializedMessageException((Message) mo10085buildPartial);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: buildPartial */
            public ProtobufWifiScanResult mo10085buildPartial() {
                ProtobufWifiScanResult protobufWifiScanResult = new ProtobufWifiScanResult(this);
                int i = this.bitField0_;
                int i2 = 1;
                if ((i & 1) != 1) {
                    i2 = 0;
                }
                protobufWifiScanResult.ssid_ = this.ssid_;
                if ((i & 2) == 2) {
                    i2 |= 2;
                }
                protobufWifiScanResult.wifiKeyManagement_ = this.wifiKeyManagement_;
                if ((i & 4) == 4) {
                    i2 |= 4;
                }
                protobufWifiScanResult.frequencyBand_ = this.frequencyBand_;
                if ((i & 8) == 8) {
                    i2 |= 8;
                }
                protobufWifiScanResult.signalStrength_ = this.signalStrength_;
                protobufWifiScanResult.bitField0_ = i2;
                onBuilt();
                return protobufWifiScanResult;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            /* renamed from: clearField */
            public Builder mo10088clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (Builder) super.mo10088clearField(fieldDescriptor);
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            /* renamed from: getDefaultInstanceForType */
            public ProtobufWifiScanResult mo10094getDefaultInstanceForType() {
                return ProtobufWifiScanResult.getDefaultInstance();
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
                this.frequencyBand_ = 0;
                this.bitField0_ &= -5;
                this.signalStrength_ = 0;
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
                if (message instanceof ProtobufWifiScanResult) {
                    return mergeFrom((ProtobufWifiScanResult) message);
                }
                super.mo10096mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(ProtobufWifiScanResult protobufWifiScanResult) {
                if (protobufWifiScanResult == ProtobufWifiScanResult.getDefaultInstance()) {
                    return this;
                }
                if (protobufWifiScanResult.hasSsid()) {
                    this.bitField0_ |= 1;
                    this.ssid_ = protobufWifiScanResult.ssid_;
                    onChanged();
                }
                if (protobufWifiScanResult.hasWifiKeyManagement()) {
                    setWifiKeyManagement(protobufWifiScanResult.getWifiKeyManagement());
                }
                if (protobufWifiScanResult.hasFrequencyBand()) {
                    setFrequencyBand(protobufWifiScanResult.getFrequencyBand());
                }
                if (protobufWifiScanResult.hasSignalStrength()) {
                    setSignalStrength(protobufWifiScanResult.getSignalStrength());
                }
                mo10099mergeUnknownFields(((GeneratedMessageV3) protobufWifiScanResult).unknownFields);
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
            public com.amazon.whisperjoin.protobuf.ProtobufWifiScanResultClass.ProtobufWifiScanResult.Builder mo10097mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
                /*
                    r2 = this;
                    r0 = 0
                    com.google.protobuf.Parser<com.amazon.whisperjoin.protobuf.ProtobufWifiScanResultClass$ProtobufWifiScanResult> r1 = com.amazon.whisperjoin.protobuf.ProtobufWifiScanResultClass.ProtobufWifiScanResult.PARSER     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                    java.lang.Object r3 = r1.mo10082parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                    com.amazon.whisperjoin.protobuf.ProtobufWifiScanResultClass$ProtobufWifiScanResult r3 = (com.amazon.whisperjoin.protobuf.ProtobufWifiScanResultClass.ProtobufWifiScanResult) r3     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
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
                    com.amazon.whisperjoin.protobuf.ProtobufWifiScanResultClass$ProtobufWifiScanResult r4 = (com.amazon.whisperjoin.protobuf.ProtobufWifiScanResultClass.ProtobufWifiScanResult) r4     // Catch: java.lang.Throwable -> Lf
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
                throw new UnsupportedOperationException("Method not decompiled: com.amazon.whisperjoin.protobuf.ProtobufWifiScanResultClass.ProtobufWifiScanResult.Builder.mo10097mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.amazon.whisperjoin.protobuf.ProtobufWifiScanResultClass$ProtobufWifiScanResult$Builder");
            }
        }

        public static Builder newBuilder(ProtobufWifiScanResult protobufWifiScanResult) {
            return DEFAULT_INSTANCE.mo10081toBuilder().mergeFrom(protobufWifiScanResult);
        }

        public static ProtobufWifiScanResult parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.mo8403parseFrom(byteBuffer, extensionRegistryLite);
        }

        private ProtobufWifiScanResult(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        public static ProtobufWifiScanResult parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ProtobufWifiScanResult) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static ProtobufWifiScanResult parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.mo8396parseFrom(byteString);
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        /* renamed from: getDefaultInstanceForType */
        public ProtobufWifiScanResult mo10094getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        /* renamed from: toBuilder */
        public Builder mo10081toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        public static ProtobufWifiScanResult parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.mo8397parseFrom(byteString, extensionRegistryLite);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        /* renamed from: newBuilderForType */
        public Builder mo10079newBuilderForType() {
            return newBuilder();
        }

        private ProtobufWifiScanResult() {
            this.memoizedIsInitialized = (byte) -1;
            this.ssid_ = "";
            this.wifiKeyManagement_ = 0;
            this.frequencyBand_ = 0;
            this.signalStrength_ = 0;
        }

        public static ProtobufWifiScanResult parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.mo8404parseFrom(bArr);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageV3
        /* renamed from: newBuilderForType */
        public Builder mo10080newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public static ProtobufWifiScanResult parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.mo8407parseFrom(bArr, extensionRegistryLite);
        }

        public static ProtobufWifiScanResult parseFrom(InputStream inputStream) throws IOException {
            return (ProtobufWifiScanResult) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static ProtobufWifiScanResult parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ProtobufWifiScanResult) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        private ProtobufWifiScanResult(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                    this.frequencyBand_ = codedInputStream.readSInt32();
                                } else if (readTag != 32) {
                                    if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                    }
                                } else {
                                    this.bitField0_ |= 8;
                                    this.signalStrength_ = codedInputStream.readSInt32();
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

        public static ProtobufWifiScanResult parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (ProtobufWifiScanResult) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static ProtobufWifiScanResult parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ProtobufWifiScanResult) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes13.dex */
    public static final class ProtobufWifiScanResultCollection extends GeneratedMessageV3 implements ProtobufWifiScanResultCollectionOrBuilder {
        private static final ProtobufWifiScanResultCollection DEFAULT_INSTANCE = new ProtobufWifiScanResultCollection();
        @Deprecated
        public static final Parser<ProtobufWifiScanResultCollection> PARSER = new AbstractParser<ProtobufWifiScanResultCollection>() { // from class: com.amazon.whisperjoin.protobuf.ProtobufWifiScanResultClass.ProtobufWifiScanResultCollection.1
            @Override // com.google.protobuf.Parser
            /* renamed from: parsePartialFrom */
            public ProtobufWifiScanResultCollection mo10082parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new ProtobufWifiScanResultCollection(codedInputStream, extensionRegistryLite);
            }
        };
        public static final int PROTOBUFWIFISCANRESULTCOLLECTION_FIELD_NUMBER = 1;
        private static final long serialVersionUID = 0;
        private byte memoizedIsInitialized;
        private List<ProtobufWifiScanResult> protobufWifiScanResultCollection_;

        public static ProtobufWifiScanResultCollection getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return ProtobufWifiScanResultClass.internal_static_protobuf_ProtobufWifiScanResultCollection_descriptor;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.mo10081toBuilder();
        }

        public static ProtobufWifiScanResultCollection parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (ProtobufWifiScanResultCollection) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static ProtobufWifiScanResultCollection parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.mo8402parseFrom(byteBuffer);
        }

        public static Parser<ProtobufWifiScanResultCollection> parser() {
            return PARSER;
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof ProtobufWifiScanResultCollection)) {
                return super.equals(obj);
            }
            ProtobufWifiScanResultCollection protobufWifiScanResultCollection = (ProtobufWifiScanResultCollection) obj;
            return (getProtobufWifiScanResultCollectionList().equals(protobufWifiScanResultCollection.getProtobufWifiScanResultCollectionList())) && this.unknownFields.equals(protobufWifiScanResultCollection.unknownFields);
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
        /* renamed from: getParserForType */
        public Parser<ProtobufWifiScanResultCollection> mo9954getParserForType() {
            return PARSER;
        }

        @Override // com.amazon.whisperjoin.protobuf.ProtobufWifiScanResultClass.ProtobufWifiScanResultCollectionOrBuilder
        public ProtobufWifiScanResult getProtobufWifiScanResultCollection(int i) {
            return this.protobufWifiScanResultCollection_.get(i);
        }

        @Override // com.amazon.whisperjoin.protobuf.ProtobufWifiScanResultClass.ProtobufWifiScanResultCollectionOrBuilder
        public int getProtobufWifiScanResultCollectionCount() {
            return this.protobufWifiScanResultCollection_.size();
        }

        @Override // com.amazon.whisperjoin.protobuf.ProtobufWifiScanResultClass.ProtobufWifiScanResultCollectionOrBuilder
        public List<ProtobufWifiScanResult> getProtobufWifiScanResultCollectionList() {
            return this.protobufWifiScanResultCollection_;
        }

        @Override // com.amazon.whisperjoin.protobuf.ProtobufWifiScanResultClass.ProtobufWifiScanResultCollectionOrBuilder
        public ProtobufWifiScanResultOrBuilder getProtobufWifiScanResultCollectionOrBuilder(int i) {
            return this.protobufWifiScanResultCollection_.get(i);
        }

        @Override // com.amazon.whisperjoin.protobuf.ProtobufWifiScanResultClass.ProtobufWifiScanResultCollectionOrBuilder
        public List<? extends ProtobufWifiScanResultOrBuilder> getProtobufWifiScanResultCollectionOrBuilderList() {
            return this.protobufWifiScanResultCollection_;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            for (int i3 = 0; i3 < this.protobufWifiScanResultCollection_.size(); i3++) {
                i2 += CodedOutputStream.computeMessageSize(1, this.protobufWifiScanResultCollection_.get(i3));
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
            if (getProtobufWifiScanResultCollectionCount() > 0) {
                hashCode = GeneratedOutlineSupport1.outline4(hashCode, 37, 1, 53) + getProtobufWifiScanResultCollectionList().hashCode();
            }
            int hashCode2 = this.unknownFields.hashCode() + (hashCode * 29);
            this.memoizedHashCode = hashCode2;
            return hashCode2;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return ProtobufWifiScanResultClass.internal_static_protobuf_ProtobufWifiScanResultCollection_fieldAccessorTable.ensureFieldAccessorsInitialized(ProtobufWifiScanResultCollection.class, Builder.class);
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
            for (int i = 0; i < getProtobufWifiScanResultCollectionCount(); i++) {
                if (!getProtobufWifiScanResultCollection(i).isInitialized()) {
                    this.memoizedIsInitialized = (byte) 0;
                    return false;
                }
            }
            this.memoizedIsInitialized = (byte) 1;
            return true;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            for (int i = 0; i < this.protobufWifiScanResultCollection_.size(); i++) {
                codedOutputStream.writeMessage(1, this.protobufWifiScanResultCollection_.get(i));
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        /* loaded from: classes13.dex */
        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements ProtobufWifiScanResultCollectionOrBuilder {
            private int bitField0_;
            private RepeatedFieldBuilderV3<ProtobufWifiScanResult, ProtobufWifiScanResult.Builder, ProtobufWifiScanResultOrBuilder> protobufWifiScanResultCollectionBuilder_;
            private List<ProtobufWifiScanResult> protobufWifiScanResultCollection_;

            private void ensureProtobufWifiScanResultCollectionIsMutable() {
                if ((this.bitField0_ & 1) != 1) {
                    this.protobufWifiScanResultCollection_ = new ArrayList(this.protobufWifiScanResultCollection_);
                    this.bitField0_ |= 1;
                }
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return ProtobufWifiScanResultClass.internal_static_protobuf_ProtobufWifiScanResultCollection_descriptor;
            }

            private RepeatedFieldBuilderV3<ProtobufWifiScanResult, ProtobufWifiScanResult.Builder, ProtobufWifiScanResultOrBuilder> getProtobufWifiScanResultCollectionFieldBuilder() {
                if (this.protobufWifiScanResultCollectionBuilder_ == null) {
                    List<ProtobufWifiScanResult> list = this.protobufWifiScanResultCollection_;
                    boolean z = true;
                    if ((this.bitField0_ & 1) != 1) {
                        z = false;
                    }
                    this.protobufWifiScanResultCollectionBuilder_ = new RepeatedFieldBuilderV3<>(list, z, getParentForChildren(), isClean());
                    this.protobufWifiScanResultCollection_ = null;
                }
                return this.protobufWifiScanResultCollectionBuilder_;
            }

            private void maybeForceBuilderInitialization() {
                if (GeneratedMessageV3.alwaysUseFieldBuilders) {
                    getProtobufWifiScanResultCollectionFieldBuilder();
                }
            }

            public Builder addAllProtobufWifiScanResultCollection(Iterable<? extends ProtobufWifiScanResult> iterable) {
                RepeatedFieldBuilderV3<ProtobufWifiScanResult, ProtobufWifiScanResult.Builder, ProtobufWifiScanResultOrBuilder> repeatedFieldBuilderV3 = this.protobufWifiScanResultCollectionBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureProtobufWifiScanResultCollectionIsMutable();
                    AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.protobufWifiScanResultCollection_);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addAllMessages(iterable);
                }
                return this;
            }

            public Builder addProtobufWifiScanResultCollection(ProtobufWifiScanResult protobufWifiScanResult) {
                RepeatedFieldBuilderV3<ProtobufWifiScanResult, ProtobufWifiScanResult.Builder, ProtobufWifiScanResultOrBuilder> repeatedFieldBuilderV3 = this.protobufWifiScanResultCollectionBuilder_;
                if (repeatedFieldBuilderV3 != null) {
                    repeatedFieldBuilderV3.addMessage(protobufWifiScanResult);
                } else if (protobufWifiScanResult != null) {
                    ensureProtobufWifiScanResultCollectionIsMutable();
                    this.protobufWifiScanResultCollection_.add(protobufWifiScanResult);
                    onChanged();
                } else {
                    throw new NullPointerException();
                }
                return this;
            }

            public ProtobufWifiScanResult.Builder addProtobufWifiScanResultCollectionBuilder() {
                return getProtobufWifiScanResultCollectionFieldBuilder().addBuilder(ProtobufWifiScanResult.getDefaultInstance());
            }

            public Builder clearProtobufWifiScanResultCollection() {
                RepeatedFieldBuilderV3<ProtobufWifiScanResult, ProtobufWifiScanResult.Builder, ProtobufWifiScanResultOrBuilder> repeatedFieldBuilderV3 = this.protobufWifiScanResultCollectionBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    this.protobufWifiScanResultCollection_ = Collections.emptyList();
                    this.bitField0_ &= -2;
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.clear();
                }
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return ProtobufWifiScanResultClass.internal_static_protobuf_ProtobufWifiScanResultCollection_descriptor;
            }

            @Override // com.amazon.whisperjoin.protobuf.ProtobufWifiScanResultClass.ProtobufWifiScanResultCollectionOrBuilder
            public ProtobufWifiScanResult getProtobufWifiScanResultCollection(int i) {
                RepeatedFieldBuilderV3<ProtobufWifiScanResult, ProtobufWifiScanResult.Builder, ProtobufWifiScanResultOrBuilder> repeatedFieldBuilderV3 = this.protobufWifiScanResultCollectionBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return this.protobufWifiScanResultCollection_.get(i);
                }
                return repeatedFieldBuilderV3.getMessage(i);
            }

            public ProtobufWifiScanResult.Builder getProtobufWifiScanResultCollectionBuilder(int i) {
                return getProtobufWifiScanResultCollectionFieldBuilder().getBuilder(i);
            }

            public List<ProtobufWifiScanResult.Builder> getProtobufWifiScanResultCollectionBuilderList() {
                return getProtobufWifiScanResultCollectionFieldBuilder().getBuilderList();
            }

            @Override // com.amazon.whisperjoin.protobuf.ProtobufWifiScanResultClass.ProtobufWifiScanResultCollectionOrBuilder
            public int getProtobufWifiScanResultCollectionCount() {
                RepeatedFieldBuilderV3<ProtobufWifiScanResult, ProtobufWifiScanResult.Builder, ProtobufWifiScanResultOrBuilder> repeatedFieldBuilderV3 = this.protobufWifiScanResultCollectionBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return this.protobufWifiScanResultCollection_.size();
                }
                return repeatedFieldBuilderV3.getCount();
            }

            @Override // com.amazon.whisperjoin.protobuf.ProtobufWifiScanResultClass.ProtobufWifiScanResultCollectionOrBuilder
            public List<ProtobufWifiScanResult> getProtobufWifiScanResultCollectionList() {
                RepeatedFieldBuilderV3<ProtobufWifiScanResult, ProtobufWifiScanResult.Builder, ProtobufWifiScanResultOrBuilder> repeatedFieldBuilderV3 = this.protobufWifiScanResultCollectionBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return Collections.unmodifiableList(this.protobufWifiScanResultCollection_);
                }
                return repeatedFieldBuilderV3.getMessageList();
            }

            @Override // com.amazon.whisperjoin.protobuf.ProtobufWifiScanResultClass.ProtobufWifiScanResultCollectionOrBuilder
            public ProtobufWifiScanResultOrBuilder getProtobufWifiScanResultCollectionOrBuilder(int i) {
                RepeatedFieldBuilderV3<ProtobufWifiScanResult, ProtobufWifiScanResult.Builder, ProtobufWifiScanResultOrBuilder> repeatedFieldBuilderV3 = this.protobufWifiScanResultCollectionBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return this.protobufWifiScanResultCollection_.get(i);
                }
                return repeatedFieldBuilderV3.getMessageOrBuilder(i);
            }

            @Override // com.amazon.whisperjoin.protobuf.ProtobufWifiScanResultClass.ProtobufWifiScanResultCollectionOrBuilder
            public List<? extends ProtobufWifiScanResultOrBuilder> getProtobufWifiScanResultCollectionOrBuilderList() {
                RepeatedFieldBuilderV3<ProtobufWifiScanResult, ProtobufWifiScanResult.Builder, ProtobufWifiScanResultOrBuilder> repeatedFieldBuilderV3 = this.protobufWifiScanResultCollectionBuilder_;
                if (repeatedFieldBuilderV3 != null) {
                    return repeatedFieldBuilderV3.getMessageOrBuilderList();
                }
                return Collections.unmodifiableList(this.protobufWifiScanResultCollection_);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return ProtobufWifiScanResultClass.internal_static_protobuf_ProtobufWifiScanResultCollection_fieldAccessorTable.ensureFieldAccessorsInitialized(ProtobufWifiScanResultCollection.class, Builder.class);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                for (int i = 0; i < getProtobufWifiScanResultCollectionCount(); i++) {
                    if (!getProtobufWifiScanResultCollection(i).isInitialized()) {
                        return false;
                    }
                }
                return true;
            }

            public Builder removeProtobufWifiScanResultCollection(int i) {
                RepeatedFieldBuilderV3<ProtobufWifiScanResult, ProtobufWifiScanResult.Builder, ProtobufWifiScanResultOrBuilder> repeatedFieldBuilderV3 = this.protobufWifiScanResultCollectionBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureProtobufWifiScanResultCollectionIsMutable();
                    this.protobufWifiScanResultCollection_.remove(i);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.remove(i);
                }
                return this;
            }

            public Builder setProtobufWifiScanResultCollection(int i, ProtobufWifiScanResult protobufWifiScanResult) {
                RepeatedFieldBuilderV3<ProtobufWifiScanResult, ProtobufWifiScanResult.Builder, ProtobufWifiScanResultOrBuilder> repeatedFieldBuilderV3 = this.protobufWifiScanResultCollectionBuilder_;
                if (repeatedFieldBuilderV3 != null) {
                    repeatedFieldBuilderV3.setMessage(i, protobufWifiScanResult);
                } else if (protobufWifiScanResult != null) {
                    ensureProtobufWifiScanResultCollectionIsMutable();
                    this.protobufWifiScanResultCollection_.set(i, protobufWifiScanResult);
                    onChanged();
                } else {
                    throw new NullPointerException();
                }
                return this;
            }

            private Builder() {
                this.protobufWifiScanResultCollection_ = Collections.emptyList();
                maybeForceBuilderInitialization();
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            /* renamed from: addRepeatedField */
            public Builder mo10083addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.mo10083addRepeatedField(fieldDescriptor, obj);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: build */
            public ProtobufWifiScanResultCollection mo10084build() {
                ProtobufWifiScanResultCollection mo10085buildPartial = mo10085buildPartial();
                if (mo10085buildPartial.isInitialized()) {
                    return mo10085buildPartial;
                }
                throw AbstractMessage.Builder.newUninitializedMessageException((Message) mo10085buildPartial);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: buildPartial */
            public ProtobufWifiScanResultCollection mo10085buildPartial() {
                ProtobufWifiScanResultCollection protobufWifiScanResultCollection = new ProtobufWifiScanResultCollection(this);
                int i = this.bitField0_;
                RepeatedFieldBuilderV3<ProtobufWifiScanResult, ProtobufWifiScanResult.Builder, ProtobufWifiScanResultOrBuilder> repeatedFieldBuilderV3 = this.protobufWifiScanResultCollectionBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    if ((i & 1) == 1) {
                        this.protobufWifiScanResultCollection_ = Collections.unmodifiableList(this.protobufWifiScanResultCollection_);
                        this.bitField0_ &= -2;
                    }
                    protobufWifiScanResultCollection.protobufWifiScanResultCollection_ = this.protobufWifiScanResultCollection_;
                } else {
                    protobufWifiScanResultCollection.protobufWifiScanResultCollection_ = repeatedFieldBuilderV3.build();
                }
                onBuilt();
                return protobufWifiScanResultCollection;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            /* renamed from: clearField */
            public Builder mo10088clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (Builder) super.mo10088clearField(fieldDescriptor);
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            /* renamed from: getDefaultInstanceForType */
            public ProtobufWifiScanResultCollection mo10094getDefaultInstanceForType() {
                return ProtobufWifiScanResultCollection.getDefaultInstance();
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

            public ProtobufWifiScanResult.Builder addProtobufWifiScanResultCollectionBuilder(int i) {
                return getProtobufWifiScanResultCollectionFieldBuilder().addBuilder(i, ProtobufWifiScanResult.getDefaultInstance());
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
                RepeatedFieldBuilderV3<ProtobufWifiScanResult, ProtobufWifiScanResult.Builder, ProtobufWifiScanResultOrBuilder> repeatedFieldBuilderV3 = this.protobufWifiScanResultCollectionBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    this.protobufWifiScanResultCollection_ = Collections.emptyList();
                    this.bitField0_ &= -2;
                } else {
                    repeatedFieldBuilderV3.clear();
                }
                return this;
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.protobufWifiScanResultCollection_ = Collections.emptyList();
                maybeForceBuilderInitialization();
            }

            public Builder addProtobufWifiScanResultCollection(int i, ProtobufWifiScanResult protobufWifiScanResult) {
                RepeatedFieldBuilderV3<ProtobufWifiScanResult, ProtobufWifiScanResult.Builder, ProtobufWifiScanResultOrBuilder> repeatedFieldBuilderV3 = this.protobufWifiScanResultCollectionBuilder_;
                if (repeatedFieldBuilderV3 != null) {
                    repeatedFieldBuilderV3.addMessage(i, protobufWifiScanResult);
                } else if (protobufWifiScanResult != null) {
                    ensureProtobufWifiScanResultCollectionIsMutable();
                    this.protobufWifiScanResultCollection_.add(i, protobufWifiScanResult);
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
                if (message instanceof ProtobufWifiScanResultCollection) {
                    return mergeFrom((ProtobufWifiScanResultCollection) message);
                }
                super.mo10096mergeFrom(message);
                return this;
            }

            public Builder setProtobufWifiScanResultCollection(int i, ProtobufWifiScanResult.Builder builder) {
                RepeatedFieldBuilderV3<ProtobufWifiScanResult, ProtobufWifiScanResult.Builder, ProtobufWifiScanResultOrBuilder> repeatedFieldBuilderV3 = this.protobufWifiScanResultCollectionBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureProtobufWifiScanResultCollectionIsMutable();
                    this.protobufWifiScanResultCollection_.set(i, builder.mo10084build());
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.setMessage(i, builder.mo10084build());
                }
                return this;
            }

            public Builder mergeFrom(ProtobufWifiScanResultCollection protobufWifiScanResultCollection) {
                if (protobufWifiScanResultCollection == ProtobufWifiScanResultCollection.getDefaultInstance()) {
                    return this;
                }
                if (this.protobufWifiScanResultCollectionBuilder_ == null) {
                    if (!protobufWifiScanResultCollection.protobufWifiScanResultCollection_.isEmpty()) {
                        if (this.protobufWifiScanResultCollection_.isEmpty()) {
                            this.protobufWifiScanResultCollection_ = protobufWifiScanResultCollection.protobufWifiScanResultCollection_;
                            this.bitField0_ &= -2;
                        } else {
                            ensureProtobufWifiScanResultCollectionIsMutable();
                            this.protobufWifiScanResultCollection_.addAll(protobufWifiScanResultCollection.protobufWifiScanResultCollection_);
                        }
                        onChanged();
                    }
                } else if (!protobufWifiScanResultCollection.protobufWifiScanResultCollection_.isEmpty()) {
                    if (!this.protobufWifiScanResultCollectionBuilder_.isEmpty()) {
                        this.protobufWifiScanResultCollectionBuilder_.addAllMessages(protobufWifiScanResultCollection.protobufWifiScanResultCollection_);
                    } else {
                        this.protobufWifiScanResultCollectionBuilder_.dispose();
                        RepeatedFieldBuilderV3<ProtobufWifiScanResult, ProtobufWifiScanResult.Builder, ProtobufWifiScanResultOrBuilder> repeatedFieldBuilderV3 = null;
                        this.protobufWifiScanResultCollectionBuilder_ = null;
                        this.protobufWifiScanResultCollection_ = protobufWifiScanResultCollection.protobufWifiScanResultCollection_;
                        this.bitField0_ &= -2;
                        if (GeneratedMessageV3.alwaysUseFieldBuilders) {
                            repeatedFieldBuilderV3 = getProtobufWifiScanResultCollectionFieldBuilder();
                        }
                        this.protobufWifiScanResultCollectionBuilder_ = repeatedFieldBuilderV3;
                    }
                }
                mo10099mergeUnknownFields(((GeneratedMessageV3) protobufWifiScanResultCollection).unknownFields);
                onChanged();
                return this;
            }

            public Builder addProtobufWifiScanResultCollection(ProtobufWifiScanResult.Builder builder) {
                RepeatedFieldBuilderV3<ProtobufWifiScanResult, ProtobufWifiScanResult.Builder, ProtobufWifiScanResultOrBuilder> repeatedFieldBuilderV3 = this.protobufWifiScanResultCollectionBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureProtobufWifiScanResultCollectionIsMutable();
                    this.protobufWifiScanResultCollection_.add(builder.mo10084build());
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addMessage(builder.mo10084build());
                }
                return this;
            }

            public Builder addProtobufWifiScanResultCollection(int i, ProtobufWifiScanResult.Builder builder) {
                RepeatedFieldBuilderV3<ProtobufWifiScanResult, ProtobufWifiScanResult.Builder, ProtobufWifiScanResultOrBuilder> repeatedFieldBuilderV3 = this.protobufWifiScanResultCollectionBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureProtobufWifiScanResultCollectionIsMutable();
                    this.protobufWifiScanResultCollection_.add(i, builder.mo10084build());
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
            public com.amazon.whisperjoin.protobuf.ProtobufWifiScanResultClass.ProtobufWifiScanResultCollection.Builder mo10097mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
                /*
                    r2 = this;
                    r0 = 0
                    com.google.protobuf.Parser<com.amazon.whisperjoin.protobuf.ProtobufWifiScanResultClass$ProtobufWifiScanResultCollection> r1 = com.amazon.whisperjoin.protobuf.ProtobufWifiScanResultClass.ProtobufWifiScanResultCollection.PARSER     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                    java.lang.Object r3 = r1.mo10082parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                    com.amazon.whisperjoin.protobuf.ProtobufWifiScanResultClass$ProtobufWifiScanResultCollection r3 = (com.amazon.whisperjoin.protobuf.ProtobufWifiScanResultClass.ProtobufWifiScanResultCollection) r3     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
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
                    com.amazon.whisperjoin.protobuf.ProtobufWifiScanResultClass$ProtobufWifiScanResultCollection r4 = (com.amazon.whisperjoin.protobuf.ProtobufWifiScanResultClass.ProtobufWifiScanResultCollection) r4     // Catch: java.lang.Throwable -> Lf
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
                throw new UnsupportedOperationException("Method not decompiled: com.amazon.whisperjoin.protobuf.ProtobufWifiScanResultClass.ProtobufWifiScanResultCollection.Builder.mo10097mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.amazon.whisperjoin.protobuf.ProtobufWifiScanResultClass$ProtobufWifiScanResultCollection$Builder");
            }
        }

        public static Builder newBuilder(ProtobufWifiScanResultCollection protobufWifiScanResultCollection) {
            return DEFAULT_INSTANCE.mo10081toBuilder().mergeFrom(protobufWifiScanResultCollection);
        }

        public static ProtobufWifiScanResultCollection parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.mo8403parseFrom(byteBuffer, extensionRegistryLite);
        }

        private ProtobufWifiScanResultCollection(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        public static ProtobufWifiScanResultCollection parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ProtobufWifiScanResultCollection) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static ProtobufWifiScanResultCollection parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.mo8396parseFrom(byteString);
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        /* renamed from: getDefaultInstanceForType */
        public ProtobufWifiScanResultCollection mo10094getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        /* renamed from: toBuilder */
        public Builder mo10081toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        public static ProtobufWifiScanResultCollection parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.mo8397parseFrom(byteString, extensionRegistryLite);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        /* renamed from: newBuilderForType */
        public Builder mo10079newBuilderForType() {
            return newBuilder();
        }

        private ProtobufWifiScanResultCollection() {
            this.memoizedIsInitialized = (byte) -1;
            this.protobufWifiScanResultCollection_ = Collections.emptyList();
        }

        public static ProtobufWifiScanResultCollection parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.mo8404parseFrom(bArr);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageV3
        /* renamed from: newBuilderForType */
        public Builder mo10080newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public static ProtobufWifiScanResultCollection parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.mo8407parseFrom(bArr, extensionRegistryLite);
        }

        public static ProtobufWifiScanResultCollection parseFrom(InputStream inputStream) throws IOException {
            return (ProtobufWifiScanResultCollection) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        /* JADX WARN: Multi-variable type inference failed */
        private ProtobufWifiScanResultCollection(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                        this.protobufWifiScanResultCollection_ = new ArrayList();
                                        z2 |= true;
                                    }
                                    this.protobufWifiScanResultCollection_.add(codedInputStream.readMessage(ProtobufWifiScanResult.PARSER, extensionRegistryLite));
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
                            this.protobufWifiScanResultCollection_ = Collections.unmodifiableList(this.protobufWifiScanResultCollection_);
                        }
                        this.unknownFields = newBuilder.mo10084build();
                        makeExtensionsImmutable();
                    }
                }
                return;
            }
            throw new NullPointerException();
        }

        public static ProtobufWifiScanResultCollection parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ProtobufWifiScanResultCollection) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static ProtobufWifiScanResultCollection parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (ProtobufWifiScanResultCollection) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static ProtobufWifiScanResultCollection parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ProtobufWifiScanResultCollection) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes13.dex */
    public interface ProtobufWifiScanResultCollectionOrBuilder extends MessageOrBuilder {
        ProtobufWifiScanResult getProtobufWifiScanResultCollection(int i);

        int getProtobufWifiScanResultCollectionCount();

        List<ProtobufWifiScanResult> getProtobufWifiScanResultCollectionList();

        ProtobufWifiScanResultOrBuilder getProtobufWifiScanResultCollectionOrBuilder(int i);

        List<? extends ProtobufWifiScanResultOrBuilder> getProtobufWifiScanResultCollectionOrBuilderList();
    }

    /* loaded from: classes13.dex */
    public interface ProtobufWifiScanResultOrBuilder extends MessageOrBuilder {
        int getFrequencyBand();

        int getSignalStrength();

        String getSsid();

        ByteString getSsidBytes();

        ProtobufWifiKeyManagementClass.ProtobufWifiKeyManagement.WifiKeyManagement getWifiKeyManagement();

        boolean hasFrequencyBand();

        boolean hasSignalStrength();

        boolean hasSsid();

        boolean hasWifiKeyManagement();
    }

    static {
        Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\nRWhisperJoinProtocolBuffersModel/schema/provisioning/data/wifi/WifiScanResult.proto\u0012\bprotobuf\u001aUWhisperJoinProtocolBuffersModel/schema/provisioning/data/wifi/WifiKeyManagement.proto\"§\u0001\n\u0016ProtobufWifiScanResult\u0012\f\n\u0004ssid\u0018\u0001 \u0002(\t\u0012P\n\u0011wifiKeyManagement\u0018\u0002 \u0002(\u000e25.protobuf.ProtobufWifiKeyManagement.WifiKeyManagement\u0012\u0015\n\rfrequencyBand\u0018\u0003 \u0002(\u0011\u0012\u0016\n\u000esignalStrength\u0018\u0004 \u0002(\u0011\"n\n ProtobufWifiScanResultCollection\u0012J\n protobufWifiScanResultCollection\u0018\u0001 \u0003(\u000b2 .protobuf.ProtobufWifiScanResultB>\n\u001fcom.amazon.whisperjoin.protobufB\u001bProtobufWifiScanResultClass"}, new Descriptors.FileDescriptor[]{ProtobufWifiKeyManagementClass.getDescriptor()}, new Descriptors.FileDescriptor.InternalDescriptorAssigner() { // from class: com.amazon.whisperjoin.protobuf.ProtobufWifiScanResultClass.1
            @Override // com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner
            public ExtensionRegistry assignDescriptors(Descriptors.FileDescriptor fileDescriptor) {
                Descriptors.FileDescriptor unused = ProtobufWifiScanResultClass.descriptor = fileDescriptor;
                return null;
            }
        });
        internal_static_protobuf_ProtobufWifiScanResult_descriptor = getDescriptor().getMessageTypes().get(0);
        internal_static_protobuf_ProtobufWifiScanResult_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_protobuf_ProtobufWifiScanResult_descriptor, new String[]{"Ssid", "WifiKeyManagement", "FrequencyBand", "SignalStrength"});
        internal_static_protobuf_ProtobufWifiScanResultCollection_descriptor = getDescriptor().getMessageTypes().get(1);
        internal_static_protobuf_ProtobufWifiScanResultCollection_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_protobuf_ProtobufWifiScanResultCollection_descriptor, new String[]{"ProtobufWifiScanResultCollection"});
        ProtobufWifiKeyManagementClass.getDescriptor();
    }

    private ProtobufWifiScanResultClass() {
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
