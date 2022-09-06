package com.amazon.whisperjoin.protobuf;

import com.amazon.whisperjoin.protobuf.ProtobufDataMapClass;
import com.amazon.whisperjoin.protobuf.ProtobufWifiConnectionDetailsClass;
import com.amazonaws.mobileconnectors.remoteconfiguration.clientcontextdecorator.RemoteConfigurationAndroidClientContextDecorator;
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
import com.google.protobuf.SingleFieldBuilderV3;
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
/* loaded from: classes13.dex */
public final class ProtobufDeviceDetailsClass {
    private static Descriptors.FileDescriptor descriptor;
    private static final Descriptors.Descriptor internal_static_protobuf_ProtobufDeviceDetails_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_protobuf_ProtobufDeviceDetails_fieldAccessorTable;

    /* loaded from: classes13.dex */
    public static final class ProtobufDeviceDetails extends GeneratedMessageV3 implements ProtobufDeviceDetailsOrBuilder {
        public static final int DEVICECAPABILITIESMAP_FIELD_NUMBER = 6;
        public static final int DEVICEFIRMWAREREVISION_FIELD_NUMBER = 5;
        public static final int DEVICEHARDWAREREVISION_FIELD_NUMBER = 4;
        public static final int DEVICEMODELNUMBER_FIELD_NUMBER = 2;
        public static final int DEVICESERIALNUMBER_FIELD_NUMBER = 3;
        public static final int LASTCONNECTIONWIFIDETAILS_FIELD_NUMBER = 7;
        public static final int MANUFACTURER_FIELD_NUMBER = 1;
        public static final int NETWORKSYNCTOKEN_FIELD_NUMBER = 8;
        public static final int SDKVERSION_FIELD_NUMBER = 9;
        private static final long serialVersionUID = 0;
        private int bitField0_;
        private ProtobufDataMapClass.ProtobufDataMap deviceCapabilitiesMap_;
        private volatile Object deviceFirmwareRevision_;
        private volatile Object deviceHardwareRevision_;
        private volatile Object deviceModelNumber_;
        private volatile Object deviceSerialNumber_;
        private ProtobufWifiConnectionDetailsClass.ProtobufWifiConnectionDetails lastConnectionWifiDetails_;
        private volatile Object manufacturer_;
        private byte memoizedIsInitialized;
        private volatile Object networkSyncToken_;
        private volatile Object sdkVersion_;
        private static final ProtobufDeviceDetails DEFAULT_INSTANCE = new ProtobufDeviceDetails();
        @Deprecated
        public static final Parser<ProtobufDeviceDetails> PARSER = new AbstractParser<ProtobufDeviceDetails>() { // from class: com.amazon.whisperjoin.protobuf.ProtobufDeviceDetailsClass.ProtobufDeviceDetails.1
            @Override // com.google.protobuf.Parser
            /* renamed from: parsePartialFrom */
            public ProtobufDeviceDetails mo10082parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new ProtobufDeviceDetails(codedInputStream, extensionRegistryLite);
            }
        };

        public static ProtobufDeviceDetails getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return ProtobufDeviceDetailsClass.internal_static_protobuf_ProtobufDeviceDetails_descriptor;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.mo10081toBuilder();
        }

        public static ProtobufDeviceDetails parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (ProtobufDeviceDetails) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static ProtobufDeviceDetails parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.mo8402parseFrom(byteBuffer);
        }

        public static Parser<ProtobufDeviceDetails> parser() {
            return PARSER;
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof ProtobufDeviceDetails)) {
                return super.equals(obj);
            }
            ProtobufDeviceDetails protobufDeviceDetails = (ProtobufDeviceDetails) obj;
            boolean z = hasManufacturer() == protobufDeviceDetails.hasManufacturer();
            if (hasManufacturer()) {
                z = z && getManufacturer().equals(protobufDeviceDetails.getManufacturer());
            }
            boolean z2 = z && hasDeviceModelNumber() == protobufDeviceDetails.hasDeviceModelNumber();
            if (hasDeviceModelNumber()) {
                z2 = z2 && getDeviceModelNumber().equals(protobufDeviceDetails.getDeviceModelNumber());
            }
            boolean z3 = z2 && hasDeviceSerialNumber() == protobufDeviceDetails.hasDeviceSerialNumber();
            if (hasDeviceSerialNumber()) {
                z3 = z3 && getDeviceSerialNumber().equals(protobufDeviceDetails.getDeviceSerialNumber());
            }
            boolean z4 = z3 && hasDeviceHardwareRevision() == protobufDeviceDetails.hasDeviceHardwareRevision();
            if (hasDeviceHardwareRevision()) {
                z4 = z4 && getDeviceHardwareRevision().equals(protobufDeviceDetails.getDeviceHardwareRevision());
            }
            boolean z5 = z4 && hasDeviceFirmwareRevision() == protobufDeviceDetails.hasDeviceFirmwareRevision();
            if (hasDeviceFirmwareRevision()) {
                z5 = z5 && getDeviceFirmwareRevision().equals(protobufDeviceDetails.getDeviceFirmwareRevision());
            }
            boolean z6 = z5 && hasDeviceCapabilitiesMap() == protobufDeviceDetails.hasDeviceCapabilitiesMap();
            if (hasDeviceCapabilitiesMap()) {
                z6 = z6 && getDeviceCapabilitiesMap().equals(protobufDeviceDetails.getDeviceCapabilitiesMap());
            }
            boolean z7 = z6 && hasLastConnectionWifiDetails() == protobufDeviceDetails.hasLastConnectionWifiDetails();
            if (hasLastConnectionWifiDetails()) {
                z7 = z7 && getLastConnectionWifiDetails().equals(protobufDeviceDetails.getLastConnectionWifiDetails());
            }
            boolean z8 = z7 && hasNetworkSyncToken() == protobufDeviceDetails.hasNetworkSyncToken();
            if (hasNetworkSyncToken()) {
                z8 = z8 && getNetworkSyncToken().equals(protobufDeviceDetails.getNetworkSyncToken());
            }
            boolean z9 = z8 && hasSdkVersion() == protobufDeviceDetails.hasSdkVersion();
            if (hasSdkVersion()) {
                z9 = z9 && getSdkVersion().equals(protobufDeviceDetails.getSdkVersion());
            }
            return z9 && this.unknownFields.equals(protobufDeviceDetails.unknownFields);
        }

        @Override // com.amazon.whisperjoin.protobuf.ProtobufDeviceDetailsClass.ProtobufDeviceDetailsOrBuilder
        public ProtobufDataMapClass.ProtobufDataMap getDeviceCapabilitiesMap() {
            ProtobufDataMapClass.ProtobufDataMap protobufDataMap = this.deviceCapabilitiesMap_;
            return protobufDataMap == null ? ProtobufDataMapClass.ProtobufDataMap.getDefaultInstance() : protobufDataMap;
        }

        @Override // com.amazon.whisperjoin.protobuf.ProtobufDeviceDetailsClass.ProtobufDeviceDetailsOrBuilder
        public ProtobufDataMapClass.ProtobufDataMapOrBuilder getDeviceCapabilitiesMapOrBuilder() {
            ProtobufDataMapClass.ProtobufDataMap protobufDataMap = this.deviceCapabilitiesMap_;
            return protobufDataMap == null ? ProtobufDataMapClass.ProtobufDataMap.getDefaultInstance() : protobufDataMap;
        }

        @Override // com.amazon.whisperjoin.protobuf.ProtobufDeviceDetailsClass.ProtobufDeviceDetailsOrBuilder
        public String getDeviceFirmwareRevision() {
            Object obj = this.deviceFirmwareRevision_;
            if (obj instanceof String) {
                return (String) obj;
            }
            ByteString byteString = (ByteString) obj;
            String stringUtf8 = byteString.toStringUtf8();
            if (byteString.isValidUtf8()) {
                this.deviceFirmwareRevision_ = stringUtf8;
            }
            return stringUtf8;
        }

        @Override // com.amazon.whisperjoin.protobuf.ProtobufDeviceDetailsClass.ProtobufDeviceDetailsOrBuilder
        public ByteString getDeviceFirmwareRevisionBytes() {
            Object obj = this.deviceFirmwareRevision_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.deviceFirmwareRevision_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override // com.amazon.whisperjoin.protobuf.ProtobufDeviceDetailsClass.ProtobufDeviceDetailsOrBuilder
        public String getDeviceHardwareRevision() {
            Object obj = this.deviceHardwareRevision_;
            if (obj instanceof String) {
                return (String) obj;
            }
            ByteString byteString = (ByteString) obj;
            String stringUtf8 = byteString.toStringUtf8();
            if (byteString.isValidUtf8()) {
                this.deviceHardwareRevision_ = stringUtf8;
            }
            return stringUtf8;
        }

        @Override // com.amazon.whisperjoin.protobuf.ProtobufDeviceDetailsClass.ProtobufDeviceDetailsOrBuilder
        public ByteString getDeviceHardwareRevisionBytes() {
            Object obj = this.deviceHardwareRevision_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.deviceHardwareRevision_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override // com.amazon.whisperjoin.protobuf.ProtobufDeviceDetailsClass.ProtobufDeviceDetailsOrBuilder
        public String getDeviceModelNumber() {
            Object obj = this.deviceModelNumber_;
            if (obj instanceof String) {
                return (String) obj;
            }
            ByteString byteString = (ByteString) obj;
            String stringUtf8 = byteString.toStringUtf8();
            if (byteString.isValidUtf8()) {
                this.deviceModelNumber_ = stringUtf8;
            }
            return stringUtf8;
        }

        @Override // com.amazon.whisperjoin.protobuf.ProtobufDeviceDetailsClass.ProtobufDeviceDetailsOrBuilder
        public ByteString getDeviceModelNumberBytes() {
            Object obj = this.deviceModelNumber_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.deviceModelNumber_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override // com.amazon.whisperjoin.protobuf.ProtobufDeviceDetailsClass.ProtobufDeviceDetailsOrBuilder
        public String getDeviceSerialNumber() {
            Object obj = this.deviceSerialNumber_;
            if (obj instanceof String) {
                return (String) obj;
            }
            ByteString byteString = (ByteString) obj;
            String stringUtf8 = byteString.toStringUtf8();
            if (byteString.isValidUtf8()) {
                this.deviceSerialNumber_ = stringUtf8;
            }
            return stringUtf8;
        }

        @Override // com.amazon.whisperjoin.protobuf.ProtobufDeviceDetailsClass.ProtobufDeviceDetailsOrBuilder
        public ByteString getDeviceSerialNumberBytes() {
            Object obj = this.deviceSerialNumber_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.deviceSerialNumber_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override // com.amazon.whisperjoin.protobuf.ProtobufDeviceDetailsClass.ProtobufDeviceDetailsOrBuilder
        public ProtobufWifiConnectionDetailsClass.ProtobufWifiConnectionDetails getLastConnectionWifiDetails() {
            ProtobufWifiConnectionDetailsClass.ProtobufWifiConnectionDetails protobufWifiConnectionDetails = this.lastConnectionWifiDetails_;
            return protobufWifiConnectionDetails == null ? ProtobufWifiConnectionDetailsClass.ProtobufWifiConnectionDetails.getDefaultInstance() : protobufWifiConnectionDetails;
        }

        @Override // com.amazon.whisperjoin.protobuf.ProtobufDeviceDetailsClass.ProtobufDeviceDetailsOrBuilder
        public ProtobufWifiConnectionDetailsClass.ProtobufWifiConnectionDetailsOrBuilder getLastConnectionWifiDetailsOrBuilder() {
            ProtobufWifiConnectionDetailsClass.ProtobufWifiConnectionDetails protobufWifiConnectionDetails = this.lastConnectionWifiDetails_;
            return protobufWifiConnectionDetails == null ? ProtobufWifiConnectionDetailsClass.ProtobufWifiConnectionDetails.getDefaultInstance() : protobufWifiConnectionDetails;
        }

        @Override // com.amazon.whisperjoin.protobuf.ProtobufDeviceDetailsClass.ProtobufDeviceDetailsOrBuilder
        public String getManufacturer() {
            Object obj = this.manufacturer_;
            if (obj instanceof String) {
                return (String) obj;
            }
            ByteString byteString = (ByteString) obj;
            String stringUtf8 = byteString.toStringUtf8();
            if (byteString.isValidUtf8()) {
                this.manufacturer_ = stringUtf8;
            }
            return stringUtf8;
        }

        @Override // com.amazon.whisperjoin.protobuf.ProtobufDeviceDetailsClass.ProtobufDeviceDetailsOrBuilder
        public ByteString getManufacturerBytes() {
            Object obj = this.manufacturer_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.manufacturer_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override // com.amazon.whisperjoin.protobuf.ProtobufDeviceDetailsClass.ProtobufDeviceDetailsOrBuilder
        public String getNetworkSyncToken() {
            Object obj = this.networkSyncToken_;
            if (obj instanceof String) {
                return (String) obj;
            }
            ByteString byteString = (ByteString) obj;
            String stringUtf8 = byteString.toStringUtf8();
            if (byteString.isValidUtf8()) {
                this.networkSyncToken_ = stringUtf8;
            }
            return stringUtf8;
        }

        @Override // com.amazon.whisperjoin.protobuf.ProtobufDeviceDetailsClass.ProtobufDeviceDetailsOrBuilder
        public ByteString getNetworkSyncTokenBytes() {
            Object obj = this.networkSyncToken_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.networkSyncToken_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
        /* renamed from: getParserForType */
        public Parser<ProtobufDeviceDetails> mo9954getParserForType() {
            return PARSER;
        }

        @Override // com.amazon.whisperjoin.protobuf.ProtobufDeviceDetailsClass.ProtobufDeviceDetailsOrBuilder
        public String getSdkVersion() {
            Object obj = this.sdkVersion_;
            if (obj instanceof String) {
                return (String) obj;
            }
            ByteString byteString = (ByteString) obj;
            String stringUtf8 = byteString.toStringUtf8();
            if (byteString.isValidUtf8()) {
                this.sdkVersion_ = stringUtf8;
            }
            return stringUtf8;
        }

        @Override // com.amazon.whisperjoin.protobuf.ProtobufDeviceDetailsClass.ProtobufDeviceDetailsOrBuilder
        public ByteString getSdkVersionBytes() {
            Object obj = this.sdkVersion_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.sdkVersion_ = copyFromUtf8;
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
                i2 = 0 + GeneratedMessageV3.computeStringSize(1, this.manufacturer_);
            }
            if ((this.bitField0_ & 2) == 2) {
                i2 += GeneratedMessageV3.computeStringSize(2, this.deviceModelNumber_);
            }
            if ((this.bitField0_ & 4) == 4) {
                i2 += GeneratedMessageV3.computeStringSize(3, this.deviceSerialNumber_);
            }
            if ((this.bitField0_ & 8) == 8) {
                i2 += GeneratedMessageV3.computeStringSize(4, this.deviceHardwareRevision_);
            }
            if ((this.bitField0_ & 16) == 16) {
                i2 += GeneratedMessageV3.computeStringSize(5, this.deviceFirmwareRevision_);
            }
            if ((this.bitField0_ & 32) == 32) {
                i2 += CodedOutputStream.computeMessageSize(6, getDeviceCapabilitiesMap());
            }
            if ((this.bitField0_ & 64) == 64) {
                i2 += CodedOutputStream.computeMessageSize(7, getLastConnectionWifiDetails());
            }
            if ((this.bitField0_ & 128) == 128) {
                i2 += GeneratedMessageV3.computeStringSize(8, this.networkSyncToken_);
            }
            if ((this.bitField0_ & 256) == 256) {
                i2 += GeneratedMessageV3.computeStringSize(9, this.sdkVersion_);
            }
            int serializedSize = this.unknownFields.getSerializedSize() + i2;
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        @Override // com.amazon.whisperjoin.protobuf.ProtobufDeviceDetailsClass.ProtobufDeviceDetailsOrBuilder
        public boolean hasDeviceCapabilitiesMap() {
            return (this.bitField0_ & 32) == 32;
        }

        @Override // com.amazon.whisperjoin.protobuf.ProtobufDeviceDetailsClass.ProtobufDeviceDetailsOrBuilder
        public boolean hasDeviceFirmwareRevision() {
            return (this.bitField0_ & 16) == 16;
        }

        @Override // com.amazon.whisperjoin.protobuf.ProtobufDeviceDetailsClass.ProtobufDeviceDetailsOrBuilder
        public boolean hasDeviceHardwareRevision() {
            return (this.bitField0_ & 8) == 8;
        }

        @Override // com.amazon.whisperjoin.protobuf.ProtobufDeviceDetailsClass.ProtobufDeviceDetailsOrBuilder
        public boolean hasDeviceModelNumber() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // com.amazon.whisperjoin.protobuf.ProtobufDeviceDetailsClass.ProtobufDeviceDetailsOrBuilder
        public boolean hasDeviceSerialNumber() {
            return (this.bitField0_ & 4) == 4;
        }

        @Override // com.amazon.whisperjoin.protobuf.ProtobufDeviceDetailsClass.ProtobufDeviceDetailsOrBuilder
        public boolean hasLastConnectionWifiDetails() {
            return (this.bitField0_ & 64) == 64;
        }

        @Override // com.amazon.whisperjoin.protobuf.ProtobufDeviceDetailsClass.ProtobufDeviceDetailsOrBuilder
        public boolean hasManufacturer() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // com.amazon.whisperjoin.protobuf.ProtobufDeviceDetailsClass.ProtobufDeviceDetailsOrBuilder
        public boolean hasNetworkSyncToken() {
            return (this.bitField0_ & 128) == 128;
        }

        @Override // com.amazon.whisperjoin.protobuf.ProtobufDeviceDetailsClass.ProtobufDeviceDetailsOrBuilder
        public boolean hasSdkVersion() {
            return (this.bitField0_ & 256) == 256;
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public int hashCode() {
            int i = this.memoizedHashCode;
            if (i != 0) {
                return i;
            }
            int hashCode = getDescriptor().hashCode() + 779;
            if (hasManufacturer()) {
                hashCode = GeneratedOutlineSupport1.outline4(hashCode, 37, 1, 53) + getManufacturer().hashCode();
            }
            if (hasDeviceModelNumber()) {
                hashCode = GeneratedOutlineSupport1.outline4(hashCode, 37, 2, 53) + getDeviceModelNumber().hashCode();
            }
            if (hasDeviceSerialNumber()) {
                hashCode = GeneratedOutlineSupport1.outline4(hashCode, 37, 3, 53) + getDeviceSerialNumber().hashCode();
            }
            if (hasDeviceHardwareRevision()) {
                hashCode = GeneratedOutlineSupport1.outline4(hashCode, 37, 4, 53) + getDeviceHardwareRevision().hashCode();
            }
            if (hasDeviceFirmwareRevision()) {
                hashCode = GeneratedOutlineSupport1.outline4(hashCode, 37, 5, 53) + getDeviceFirmwareRevision().hashCode();
            }
            if (hasDeviceCapabilitiesMap()) {
                hashCode = GeneratedOutlineSupport1.outline4(hashCode, 37, 6, 53) + getDeviceCapabilitiesMap().hashCode();
            }
            if (hasLastConnectionWifiDetails()) {
                hashCode = GeneratedOutlineSupport1.outline4(hashCode, 37, 7, 53) + getLastConnectionWifiDetails().hashCode();
            }
            if (hasNetworkSyncToken()) {
                hashCode = GeneratedOutlineSupport1.outline4(hashCode, 37, 8, 53) + getNetworkSyncToken().hashCode();
            }
            if (hasSdkVersion()) {
                hashCode = GeneratedOutlineSupport1.outline4(hashCode, 37, 9, 53) + getSdkVersion().hashCode();
            }
            int hashCode2 = this.unknownFields.hashCode() + (hashCode * 29);
            this.memoizedHashCode = hashCode2;
            return hashCode2;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return ProtobufDeviceDetailsClass.internal_static_protobuf_ProtobufDeviceDetails_fieldAccessorTable.ensureFieldAccessorsInitialized(ProtobufDeviceDetails.class, Builder.class);
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
            if (!hasManufacturer()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            } else if (!hasDeviceModelNumber()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            } else if (!hasDeviceSerialNumber()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            } else if (!hasDeviceHardwareRevision()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            } else if (!hasDeviceFirmwareRevision()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            } else if (!hasDeviceCapabilitiesMap()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            } else if (!getDeviceCapabilitiesMap().isInitialized()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            } else if (hasLastConnectionWifiDetails() && !getLastConnectionWifiDetails().isInitialized()) {
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
                GeneratedMessageV3.writeString(codedOutputStream, 1, this.manufacturer_);
            }
            if ((this.bitField0_ & 2) == 2) {
                GeneratedMessageV3.writeString(codedOutputStream, 2, this.deviceModelNumber_);
            }
            if ((this.bitField0_ & 4) == 4) {
                GeneratedMessageV3.writeString(codedOutputStream, 3, this.deviceSerialNumber_);
            }
            if ((this.bitField0_ & 8) == 8) {
                GeneratedMessageV3.writeString(codedOutputStream, 4, this.deviceHardwareRevision_);
            }
            if ((this.bitField0_ & 16) == 16) {
                GeneratedMessageV3.writeString(codedOutputStream, 5, this.deviceFirmwareRevision_);
            }
            if ((this.bitField0_ & 32) == 32) {
                codedOutputStream.writeMessage(6, getDeviceCapabilitiesMap());
            }
            if ((this.bitField0_ & 64) == 64) {
                codedOutputStream.writeMessage(7, getLastConnectionWifiDetails());
            }
            if ((this.bitField0_ & 128) == 128) {
                GeneratedMessageV3.writeString(codedOutputStream, 8, this.networkSyncToken_);
            }
            if ((this.bitField0_ & 256) == 256) {
                GeneratedMessageV3.writeString(codedOutputStream, 9, this.sdkVersion_);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        /* loaded from: classes13.dex */
        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements ProtobufDeviceDetailsOrBuilder {
            private int bitField0_;
            private SingleFieldBuilderV3<ProtobufDataMapClass.ProtobufDataMap, ProtobufDataMapClass.ProtobufDataMap.Builder, ProtobufDataMapClass.ProtobufDataMapOrBuilder> deviceCapabilitiesMapBuilder_;
            private ProtobufDataMapClass.ProtobufDataMap deviceCapabilitiesMap_;
            private Object deviceFirmwareRevision_;
            private Object deviceHardwareRevision_;
            private Object deviceModelNumber_;
            private Object deviceSerialNumber_;
            private SingleFieldBuilderV3<ProtobufWifiConnectionDetailsClass.ProtobufWifiConnectionDetails, ProtobufWifiConnectionDetailsClass.ProtobufWifiConnectionDetails.Builder, ProtobufWifiConnectionDetailsClass.ProtobufWifiConnectionDetailsOrBuilder> lastConnectionWifiDetailsBuilder_;
            private ProtobufWifiConnectionDetailsClass.ProtobufWifiConnectionDetails lastConnectionWifiDetails_;
            private Object manufacturer_;
            private Object networkSyncToken_;
            private Object sdkVersion_;

            public static final Descriptors.Descriptor getDescriptor() {
                return ProtobufDeviceDetailsClass.internal_static_protobuf_ProtobufDeviceDetails_descriptor;
            }

            private SingleFieldBuilderV3<ProtobufDataMapClass.ProtobufDataMap, ProtobufDataMapClass.ProtobufDataMap.Builder, ProtobufDataMapClass.ProtobufDataMapOrBuilder> getDeviceCapabilitiesMapFieldBuilder() {
                if (this.deviceCapabilitiesMapBuilder_ == null) {
                    this.deviceCapabilitiesMapBuilder_ = new SingleFieldBuilderV3<>(getDeviceCapabilitiesMap(), getParentForChildren(), isClean());
                    this.deviceCapabilitiesMap_ = null;
                }
                return this.deviceCapabilitiesMapBuilder_;
            }

            private SingleFieldBuilderV3<ProtobufWifiConnectionDetailsClass.ProtobufWifiConnectionDetails, ProtobufWifiConnectionDetailsClass.ProtobufWifiConnectionDetails.Builder, ProtobufWifiConnectionDetailsClass.ProtobufWifiConnectionDetailsOrBuilder> getLastConnectionWifiDetailsFieldBuilder() {
                if (this.lastConnectionWifiDetailsBuilder_ == null) {
                    this.lastConnectionWifiDetailsBuilder_ = new SingleFieldBuilderV3<>(getLastConnectionWifiDetails(), getParentForChildren(), isClean());
                    this.lastConnectionWifiDetails_ = null;
                }
                return this.lastConnectionWifiDetailsBuilder_;
            }

            private void maybeForceBuilderInitialization() {
                if (GeneratedMessageV3.alwaysUseFieldBuilders) {
                    getDeviceCapabilitiesMapFieldBuilder();
                    getLastConnectionWifiDetailsFieldBuilder();
                }
            }

            public Builder clearDeviceCapabilitiesMap() {
                SingleFieldBuilderV3<ProtobufDataMapClass.ProtobufDataMap, ProtobufDataMapClass.ProtobufDataMap.Builder, ProtobufDataMapClass.ProtobufDataMapOrBuilder> singleFieldBuilderV3 = this.deviceCapabilitiesMapBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.deviceCapabilitiesMap_ = null;
                    onChanged();
                } else {
                    singleFieldBuilderV3.clear();
                }
                this.bitField0_ &= -33;
                return this;
            }

            public Builder clearDeviceFirmwareRevision() {
                this.bitField0_ &= -17;
                this.deviceFirmwareRevision_ = ProtobufDeviceDetails.getDefaultInstance().getDeviceFirmwareRevision();
                onChanged();
                return this;
            }

            public Builder clearDeviceHardwareRevision() {
                this.bitField0_ &= -9;
                this.deviceHardwareRevision_ = ProtobufDeviceDetails.getDefaultInstance().getDeviceHardwareRevision();
                onChanged();
                return this;
            }

            public Builder clearDeviceModelNumber() {
                this.bitField0_ &= -3;
                this.deviceModelNumber_ = ProtobufDeviceDetails.getDefaultInstance().getDeviceModelNumber();
                onChanged();
                return this;
            }

            public Builder clearDeviceSerialNumber() {
                this.bitField0_ &= -5;
                this.deviceSerialNumber_ = ProtobufDeviceDetails.getDefaultInstance().getDeviceSerialNumber();
                onChanged();
                return this;
            }

            public Builder clearLastConnectionWifiDetails() {
                SingleFieldBuilderV3<ProtobufWifiConnectionDetailsClass.ProtobufWifiConnectionDetails, ProtobufWifiConnectionDetailsClass.ProtobufWifiConnectionDetails.Builder, ProtobufWifiConnectionDetailsClass.ProtobufWifiConnectionDetailsOrBuilder> singleFieldBuilderV3 = this.lastConnectionWifiDetailsBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.lastConnectionWifiDetails_ = null;
                    onChanged();
                } else {
                    singleFieldBuilderV3.clear();
                }
                this.bitField0_ &= -65;
                return this;
            }

            public Builder clearManufacturer() {
                this.bitField0_ &= -2;
                this.manufacturer_ = ProtobufDeviceDetails.getDefaultInstance().getManufacturer();
                onChanged();
                return this;
            }

            public Builder clearNetworkSyncToken() {
                this.bitField0_ &= -129;
                this.networkSyncToken_ = ProtobufDeviceDetails.getDefaultInstance().getNetworkSyncToken();
                onChanged();
                return this;
            }

            public Builder clearSdkVersion() {
                this.bitField0_ &= -257;
                this.sdkVersion_ = ProtobufDeviceDetails.getDefaultInstance().getSdkVersion();
                onChanged();
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return ProtobufDeviceDetailsClass.internal_static_protobuf_ProtobufDeviceDetails_descriptor;
            }

            @Override // com.amazon.whisperjoin.protobuf.ProtobufDeviceDetailsClass.ProtobufDeviceDetailsOrBuilder
            public ProtobufDataMapClass.ProtobufDataMap getDeviceCapabilitiesMap() {
                SingleFieldBuilderV3<ProtobufDataMapClass.ProtobufDataMap, ProtobufDataMapClass.ProtobufDataMap.Builder, ProtobufDataMapClass.ProtobufDataMapOrBuilder> singleFieldBuilderV3 = this.deviceCapabilitiesMapBuilder_;
                if (singleFieldBuilderV3 == null) {
                    ProtobufDataMapClass.ProtobufDataMap protobufDataMap = this.deviceCapabilitiesMap_;
                    return protobufDataMap == null ? ProtobufDataMapClass.ProtobufDataMap.getDefaultInstance() : protobufDataMap;
                }
                return singleFieldBuilderV3.getMessage();
            }

            public ProtobufDataMapClass.ProtobufDataMap.Builder getDeviceCapabilitiesMapBuilder() {
                this.bitField0_ |= 32;
                onChanged();
                return getDeviceCapabilitiesMapFieldBuilder().getBuilder();
            }

            @Override // com.amazon.whisperjoin.protobuf.ProtobufDeviceDetailsClass.ProtobufDeviceDetailsOrBuilder
            public ProtobufDataMapClass.ProtobufDataMapOrBuilder getDeviceCapabilitiesMapOrBuilder() {
                SingleFieldBuilderV3<ProtobufDataMapClass.ProtobufDataMap, ProtobufDataMapClass.ProtobufDataMap.Builder, ProtobufDataMapClass.ProtobufDataMapOrBuilder> singleFieldBuilderV3 = this.deviceCapabilitiesMapBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessageOrBuilder();
                }
                ProtobufDataMapClass.ProtobufDataMap protobufDataMap = this.deviceCapabilitiesMap_;
                return protobufDataMap == null ? ProtobufDataMapClass.ProtobufDataMap.getDefaultInstance() : protobufDataMap;
            }

            @Override // com.amazon.whisperjoin.protobuf.ProtobufDeviceDetailsClass.ProtobufDeviceDetailsOrBuilder
            public String getDeviceFirmwareRevision() {
                Object obj = this.deviceFirmwareRevision_;
                if (!(obj instanceof String)) {
                    ByteString byteString = (ByteString) obj;
                    String stringUtf8 = byteString.toStringUtf8();
                    if (byteString.isValidUtf8()) {
                        this.deviceFirmwareRevision_ = stringUtf8;
                    }
                    return stringUtf8;
                }
                return (String) obj;
            }

            @Override // com.amazon.whisperjoin.protobuf.ProtobufDeviceDetailsClass.ProtobufDeviceDetailsOrBuilder
            public ByteString getDeviceFirmwareRevisionBytes() {
                Object obj = this.deviceFirmwareRevision_;
                if (obj instanceof String) {
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.deviceFirmwareRevision_ = copyFromUtf8;
                    return copyFromUtf8;
                }
                return (ByteString) obj;
            }

            @Override // com.amazon.whisperjoin.protobuf.ProtobufDeviceDetailsClass.ProtobufDeviceDetailsOrBuilder
            public String getDeviceHardwareRevision() {
                Object obj = this.deviceHardwareRevision_;
                if (!(obj instanceof String)) {
                    ByteString byteString = (ByteString) obj;
                    String stringUtf8 = byteString.toStringUtf8();
                    if (byteString.isValidUtf8()) {
                        this.deviceHardwareRevision_ = stringUtf8;
                    }
                    return stringUtf8;
                }
                return (String) obj;
            }

            @Override // com.amazon.whisperjoin.protobuf.ProtobufDeviceDetailsClass.ProtobufDeviceDetailsOrBuilder
            public ByteString getDeviceHardwareRevisionBytes() {
                Object obj = this.deviceHardwareRevision_;
                if (obj instanceof String) {
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.deviceHardwareRevision_ = copyFromUtf8;
                    return copyFromUtf8;
                }
                return (ByteString) obj;
            }

            @Override // com.amazon.whisperjoin.protobuf.ProtobufDeviceDetailsClass.ProtobufDeviceDetailsOrBuilder
            public String getDeviceModelNumber() {
                Object obj = this.deviceModelNumber_;
                if (!(obj instanceof String)) {
                    ByteString byteString = (ByteString) obj;
                    String stringUtf8 = byteString.toStringUtf8();
                    if (byteString.isValidUtf8()) {
                        this.deviceModelNumber_ = stringUtf8;
                    }
                    return stringUtf8;
                }
                return (String) obj;
            }

            @Override // com.amazon.whisperjoin.protobuf.ProtobufDeviceDetailsClass.ProtobufDeviceDetailsOrBuilder
            public ByteString getDeviceModelNumberBytes() {
                Object obj = this.deviceModelNumber_;
                if (obj instanceof String) {
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.deviceModelNumber_ = copyFromUtf8;
                    return copyFromUtf8;
                }
                return (ByteString) obj;
            }

            @Override // com.amazon.whisperjoin.protobuf.ProtobufDeviceDetailsClass.ProtobufDeviceDetailsOrBuilder
            public String getDeviceSerialNumber() {
                Object obj = this.deviceSerialNumber_;
                if (!(obj instanceof String)) {
                    ByteString byteString = (ByteString) obj;
                    String stringUtf8 = byteString.toStringUtf8();
                    if (byteString.isValidUtf8()) {
                        this.deviceSerialNumber_ = stringUtf8;
                    }
                    return stringUtf8;
                }
                return (String) obj;
            }

            @Override // com.amazon.whisperjoin.protobuf.ProtobufDeviceDetailsClass.ProtobufDeviceDetailsOrBuilder
            public ByteString getDeviceSerialNumberBytes() {
                Object obj = this.deviceSerialNumber_;
                if (obj instanceof String) {
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.deviceSerialNumber_ = copyFromUtf8;
                    return copyFromUtf8;
                }
                return (ByteString) obj;
            }

            @Override // com.amazon.whisperjoin.protobuf.ProtobufDeviceDetailsClass.ProtobufDeviceDetailsOrBuilder
            public ProtobufWifiConnectionDetailsClass.ProtobufWifiConnectionDetails getLastConnectionWifiDetails() {
                SingleFieldBuilderV3<ProtobufWifiConnectionDetailsClass.ProtobufWifiConnectionDetails, ProtobufWifiConnectionDetailsClass.ProtobufWifiConnectionDetails.Builder, ProtobufWifiConnectionDetailsClass.ProtobufWifiConnectionDetailsOrBuilder> singleFieldBuilderV3 = this.lastConnectionWifiDetailsBuilder_;
                if (singleFieldBuilderV3 == null) {
                    ProtobufWifiConnectionDetailsClass.ProtobufWifiConnectionDetails protobufWifiConnectionDetails = this.lastConnectionWifiDetails_;
                    return protobufWifiConnectionDetails == null ? ProtobufWifiConnectionDetailsClass.ProtobufWifiConnectionDetails.getDefaultInstance() : protobufWifiConnectionDetails;
                }
                return singleFieldBuilderV3.getMessage();
            }

            public ProtobufWifiConnectionDetailsClass.ProtobufWifiConnectionDetails.Builder getLastConnectionWifiDetailsBuilder() {
                this.bitField0_ |= 64;
                onChanged();
                return getLastConnectionWifiDetailsFieldBuilder().getBuilder();
            }

            @Override // com.amazon.whisperjoin.protobuf.ProtobufDeviceDetailsClass.ProtobufDeviceDetailsOrBuilder
            public ProtobufWifiConnectionDetailsClass.ProtobufWifiConnectionDetailsOrBuilder getLastConnectionWifiDetailsOrBuilder() {
                SingleFieldBuilderV3<ProtobufWifiConnectionDetailsClass.ProtobufWifiConnectionDetails, ProtobufWifiConnectionDetailsClass.ProtobufWifiConnectionDetails.Builder, ProtobufWifiConnectionDetailsClass.ProtobufWifiConnectionDetailsOrBuilder> singleFieldBuilderV3 = this.lastConnectionWifiDetailsBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessageOrBuilder();
                }
                ProtobufWifiConnectionDetailsClass.ProtobufWifiConnectionDetails protobufWifiConnectionDetails = this.lastConnectionWifiDetails_;
                return protobufWifiConnectionDetails == null ? ProtobufWifiConnectionDetailsClass.ProtobufWifiConnectionDetails.getDefaultInstance() : protobufWifiConnectionDetails;
            }

            @Override // com.amazon.whisperjoin.protobuf.ProtobufDeviceDetailsClass.ProtobufDeviceDetailsOrBuilder
            public String getManufacturer() {
                Object obj = this.manufacturer_;
                if (!(obj instanceof String)) {
                    ByteString byteString = (ByteString) obj;
                    String stringUtf8 = byteString.toStringUtf8();
                    if (byteString.isValidUtf8()) {
                        this.manufacturer_ = stringUtf8;
                    }
                    return stringUtf8;
                }
                return (String) obj;
            }

            @Override // com.amazon.whisperjoin.protobuf.ProtobufDeviceDetailsClass.ProtobufDeviceDetailsOrBuilder
            public ByteString getManufacturerBytes() {
                Object obj = this.manufacturer_;
                if (obj instanceof String) {
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.manufacturer_ = copyFromUtf8;
                    return copyFromUtf8;
                }
                return (ByteString) obj;
            }

            @Override // com.amazon.whisperjoin.protobuf.ProtobufDeviceDetailsClass.ProtobufDeviceDetailsOrBuilder
            public String getNetworkSyncToken() {
                Object obj = this.networkSyncToken_;
                if (!(obj instanceof String)) {
                    ByteString byteString = (ByteString) obj;
                    String stringUtf8 = byteString.toStringUtf8();
                    if (byteString.isValidUtf8()) {
                        this.networkSyncToken_ = stringUtf8;
                    }
                    return stringUtf8;
                }
                return (String) obj;
            }

            @Override // com.amazon.whisperjoin.protobuf.ProtobufDeviceDetailsClass.ProtobufDeviceDetailsOrBuilder
            public ByteString getNetworkSyncTokenBytes() {
                Object obj = this.networkSyncToken_;
                if (obj instanceof String) {
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.networkSyncToken_ = copyFromUtf8;
                    return copyFromUtf8;
                }
                return (ByteString) obj;
            }

            @Override // com.amazon.whisperjoin.protobuf.ProtobufDeviceDetailsClass.ProtobufDeviceDetailsOrBuilder
            public String getSdkVersion() {
                Object obj = this.sdkVersion_;
                if (!(obj instanceof String)) {
                    ByteString byteString = (ByteString) obj;
                    String stringUtf8 = byteString.toStringUtf8();
                    if (byteString.isValidUtf8()) {
                        this.sdkVersion_ = stringUtf8;
                    }
                    return stringUtf8;
                }
                return (String) obj;
            }

            @Override // com.amazon.whisperjoin.protobuf.ProtobufDeviceDetailsClass.ProtobufDeviceDetailsOrBuilder
            public ByteString getSdkVersionBytes() {
                Object obj = this.sdkVersion_;
                if (obj instanceof String) {
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.sdkVersion_ = copyFromUtf8;
                    return copyFromUtf8;
                }
                return (ByteString) obj;
            }

            @Override // com.amazon.whisperjoin.protobuf.ProtobufDeviceDetailsClass.ProtobufDeviceDetailsOrBuilder
            public boolean hasDeviceCapabilitiesMap() {
                return (this.bitField0_ & 32) == 32;
            }

            @Override // com.amazon.whisperjoin.protobuf.ProtobufDeviceDetailsClass.ProtobufDeviceDetailsOrBuilder
            public boolean hasDeviceFirmwareRevision() {
                return (this.bitField0_ & 16) == 16;
            }

            @Override // com.amazon.whisperjoin.protobuf.ProtobufDeviceDetailsClass.ProtobufDeviceDetailsOrBuilder
            public boolean hasDeviceHardwareRevision() {
                return (this.bitField0_ & 8) == 8;
            }

            @Override // com.amazon.whisperjoin.protobuf.ProtobufDeviceDetailsClass.ProtobufDeviceDetailsOrBuilder
            public boolean hasDeviceModelNumber() {
                return (this.bitField0_ & 2) == 2;
            }

            @Override // com.amazon.whisperjoin.protobuf.ProtobufDeviceDetailsClass.ProtobufDeviceDetailsOrBuilder
            public boolean hasDeviceSerialNumber() {
                return (this.bitField0_ & 4) == 4;
            }

            @Override // com.amazon.whisperjoin.protobuf.ProtobufDeviceDetailsClass.ProtobufDeviceDetailsOrBuilder
            public boolean hasLastConnectionWifiDetails() {
                return (this.bitField0_ & 64) == 64;
            }

            @Override // com.amazon.whisperjoin.protobuf.ProtobufDeviceDetailsClass.ProtobufDeviceDetailsOrBuilder
            public boolean hasManufacturer() {
                return (this.bitField0_ & 1) == 1;
            }

            @Override // com.amazon.whisperjoin.protobuf.ProtobufDeviceDetailsClass.ProtobufDeviceDetailsOrBuilder
            public boolean hasNetworkSyncToken() {
                return (this.bitField0_ & 128) == 128;
            }

            @Override // com.amazon.whisperjoin.protobuf.ProtobufDeviceDetailsClass.ProtobufDeviceDetailsOrBuilder
            public boolean hasSdkVersion() {
                return (this.bitField0_ & 256) == 256;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return ProtobufDeviceDetailsClass.internal_static_protobuf_ProtobufDeviceDetails_fieldAccessorTable.ensureFieldAccessorsInitialized(ProtobufDeviceDetails.class, Builder.class);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                if (hasManufacturer() && hasDeviceModelNumber() && hasDeviceSerialNumber() && hasDeviceHardwareRevision() && hasDeviceFirmwareRevision() && hasDeviceCapabilitiesMap() && getDeviceCapabilitiesMap().isInitialized()) {
                    return !hasLastConnectionWifiDetails() || getLastConnectionWifiDetails().isInitialized();
                }
                return false;
            }

            public Builder mergeDeviceCapabilitiesMap(ProtobufDataMapClass.ProtobufDataMap protobufDataMap) {
                ProtobufDataMapClass.ProtobufDataMap protobufDataMap2;
                SingleFieldBuilderV3<ProtobufDataMapClass.ProtobufDataMap, ProtobufDataMapClass.ProtobufDataMap.Builder, ProtobufDataMapClass.ProtobufDataMapOrBuilder> singleFieldBuilderV3 = this.deviceCapabilitiesMapBuilder_;
                if (singleFieldBuilderV3 == null) {
                    if ((this.bitField0_ & 32) == 32 && (protobufDataMap2 = this.deviceCapabilitiesMap_) != null && protobufDataMap2 != ProtobufDataMapClass.ProtobufDataMap.getDefaultInstance()) {
                        this.deviceCapabilitiesMap_ = ProtobufDataMapClass.ProtobufDataMap.newBuilder(this.deviceCapabilitiesMap_).mergeFrom(protobufDataMap).mo10085buildPartial();
                    } else {
                        this.deviceCapabilitiesMap_ = protobufDataMap;
                    }
                    onChanged();
                } else {
                    singleFieldBuilderV3.mergeFrom(protobufDataMap);
                }
                this.bitField0_ |= 32;
                return this;
            }

            public Builder mergeLastConnectionWifiDetails(ProtobufWifiConnectionDetailsClass.ProtobufWifiConnectionDetails protobufWifiConnectionDetails) {
                ProtobufWifiConnectionDetailsClass.ProtobufWifiConnectionDetails protobufWifiConnectionDetails2;
                SingleFieldBuilderV3<ProtobufWifiConnectionDetailsClass.ProtobufWifiConnectionDetails, ProtobufWifiConnectionDetailsClass.ProtobufWifiConnectionDetails.Builder, ProtobufWifiConnectionDetailsClass.ProtobufWifiConnectionDetailsOrBuilder> singleFieldBuilderV3 = this.lastConnectionWifiDetailsBuilder_;
                if (singleFieldBuilderV3 == null) {
                    if ((this.bitField0_ & 64) == 64 && (protobufWifiConnectionDetails2 = this.lastConnectionWifiDetails_) != null && protobufWifiConnectionDetails2 != ProtobufWifiConnectionDetailsClass.ProtobufWifiConnectionDetails.getDefaultInstance()) {
                        this.lastConnectionWifiDetails_ = ProtobufWifiConnectionDetailsClass.ProtobufWifiConnectionDetails.newBuilder(this.lastConnectionWifiDetails_).mergeFrom(protobufWifiConnectionDetails).mo10085buildPartial();
                    } else {
                        this.lastConnectionWifiDetails_ = protobufWifiConnectionDetails;
                    }
                    onChanged();
                } else {
                    singleFieldBuilderV3.mergeFrom(protobufWifiConnectionDetails);
                }
                this.bitField0_ |= 64;
                return this;
            }

            public Builder setDeviceCapabilitiesMap(ProtobufDataMapClass.ProtobufDataMap protobufDataMap) {
                SingleFieldBuilderV3<ProtobufDataMapClass.ProtobufDataMap, ProtobufDataMapClass.ProtobufDataMap.Builder, ProtobufDataMapClass.ProtobufDataMapOrBuilder> singleFieldBuilderV3 = this.deviceCapabilitiesMapBuilder_;
                if (singleFieldBuilderV3 != null) {
                    singleFieldBuilderV3.setMessage(protobufDataMap);
                } else if (protobufDataMap != null) {
                    this.deviceCapabilitiesMap_ = protobufDataMap;
                    onChanged();
                } else {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 32;
                return this;
            }

            public Builder setDeviceFirmwareRevision(String str) {
                if (str != null) {
                    this.bitField0_ |= 16;
                    this.deviceFirmwareRevision_ = str;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder setDeviceFirmwareRevisionBytes(ByteString byteString) {
                if (byteString != null) {
                    this.bitField0_ |= 16;
                    this.deviceFirmwareRevision_ = byteString;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder setDeviceHardwareRevision(String str) {
                if (str != null) {
                    this.bitField0_ |= 8;
                    this.deviceHardwareRevision_ = str;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder setDeviceHardwareRevisionBytes(ByteString byteString) {
                if (byteString != null) {
                    this.bitField0_ |= 8;
                    this.deviceHardwareRevision_ = byteString;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder setDeviceModelNumber(String str) {
                if (str != null) {
                    this.bitField0_ |= 2;
                    this.deviceModelNumber_ = str;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder setDeviceModelNumberBytes(ByteString byteString) {
                if (byteString != null) {
                    this.bitField0_ |= 2;
                    this.deviceModelNumber_ = byteString;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder setDeviceSerialNumber(String str) {
                if (str != null) {
                    this.bitField0_ |= 4;
                    this.deviceSerialNumber_ = str;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder setDeviceSerialNumberBytes(ByteString byteString) {
                if (byteString != null) {
                    this.bitField0_ |= 4;
                    this.deviceSerialNumber_ = byteString;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder setLastConnectionWifiDetails(ProtobufWifiConnectionDetailsClass.ProtobufWifiConnectionDetails protobufWifiConnectionDetails) {
                SingleFieldBuilderV3<ProtobufWifiConnectionDetailsClass.ProtobufWifiConnectionDetails, ProtobufWifiConnectionDetailsClass.ProtobufWifiConnectionDetails.Builder, ProtobufWifiConnectionDetailsClass.ProtobufWifiConnectionDetailsOrBuilder> singleFieldBuilderV3 = this.lastConnectionWifiDetailsBuilder_;
                if (singleFieldBuilderV3 != null) {
                    singleFieldBuilderV3.setMessage(protobufWifiConnectionDetails);
                } else if (protobufWifiConnectionDetails != null) {
                    this.lastConnectionWifiDetails_ = protobufWifiConnectionDetails;
                    onChanged();
                } else {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 64;
                return this;
            }

            public Builder setManufacturer(String str) {
                if (str != null) {
                    this.bitField0_ |= 1;
                    this.manufacturer_ = str;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder setManufacturerBytes(ByteString byteString) {
                if (byteString != null) {
                    this.bitField0_ |= 1;
                    this.manufacturer_ = byteString;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder setNetworkSyncToken(String str) {
                if (str != null) {
                    this.bitField0_ |= 128;
                    this.networkSyncToken_ = str;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder setNetworkSyncTokenBytes(ByteString byteString) {
                if (byteString != null) {
                    this.bitField0_ |= 128;
                    this.networkSyncToken_ = byteString;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder setSdkVersion(String str) {
                if (str != null) {
                    this.bitField0_ |= 256;
                    this.sdkVersion_ = str;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder setSdkVersionBytes(ByteString byteString) {
                if (byteString != null) {
                    this.bitField0_ |= 256;
                    this.sdkVersion_ = byteString;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            private Builder() {
                this.manufacturer_ = "";
                this.deviceModelNumber_ = "";
                this.deviceSerialNumber_ = "";
                this.deviceHardwareRevision_ = "";
                this.deviceFirmwareRevision_ = "";
                this.deviceCapabilitiesMap_ = null;
                this.lastConnectionWifiDetails_ = null;
                this.networkSyncToken_ = "";
                this.sdkVersion_ = "";
                maybeForceBuilderInitialization();
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            /* renamed from: addRepeatedField */
            public Builder mo10083addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.mo10083addRepeatedField(fieldDescriptor, obj);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: build */
            public ProtobufDeviceDetails mo10084build() {
                ProtobufDeviceDetails mo10085buildPartial = mo10085buildPartial();
                if (mo10085buildPartial.isInitialized()) {
                    return mo10085buildPartial;
                }
                throw AbstractMessage.Builder.newUninitializedMessageException((Message) mo10085buildPartial);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: buildPartial */
            public ProtobufDeviceDetails mo10085buildPartial() {
                ProtobufDeviceDetails protobufDeviceDetails = new ProtobufDeviceDetails(this);
                int i = this.bitField0_;
                int i2 = 1;
                if ((i & 1) != 1) {
                    i2 = 0;
                }
                protobufDeviceDetails.manufacturer_ = this.manufacturer_;
                if ((i & 2) == 2) {
                    i2 |= 2;
                }
                protobufDeviceDetails.deviceModelNumber_ = this.deviceModelNumber_;
                if ((i & 4) == 4) {
                    i2 |= 4;
                }
                protobufDeviceDetails.deviceSerialNumber_ = this.deviceSerialNumber_;
                if ((i & 8) == 8) {
                    i2 |= 8;
                }
                protobufDeviceDetails.deviceHardwareRevision_ = this.deviceHardwareRevision_;
                if ((i & 16) == 16) {
                    i2 |= 16;
                }
                protobufDeviceDetails.deviceFirmwareRevision_ = this.deviceFirmwareRevision_;
                if ((i & 32) == 32) {
                    i2 |= 32;
                }
                SingleFieldBuilderV3<ProtobufDataMapClass.ProtobufDataMap, ProtobufDataMapClass.ProtobufDataMap.Builder, ProtobufDataMapClass.ProtobufDataMapOrBuilder> singleFieldBuilderV3 = this.deviceCapabilitiesMapBuilder_;
                if (singleFieldBuilderV3 == null) {
                    protobufDeviceDetails.deviceCapabilitiesMap_ = this.deviceCapabilitiesMap_;
                } else {
                    protobufDeviceDetails.deviceCapabilitiesMap_ = singleFieldBuilderV3.build();
                }
                if ((i & 64) == 64) {
                    i2 |= 64;
                }
                SingleFieldBuilderV3<ProtobufWifiConnectionDetailsClass.ProtobufWifiConnectionDetails, ProtobufWifiConnectionDetailsClass.ProtobufWifiConnectionDetails.Builder, ProtobufWifiConnectionDetailsClass.ProtobufWifiConnectionDetailsOrBuilder> singleFieldBuilderV32 = this.lastConnectionWifiDetailsBuilder_;
                if (singleFieldBuilderV32 == null) {
                    protobufDeviceDetails.lastConnectionWifiDetails_ = this.lastConnectionWifiDetails_;
                } else {
                    protobufDeviceDetails.lastConnectionWifiDetails_ = singleFieldBuilderV32.build();
                }
                if ((i & 128) == 128) {
                    i2 |= 128;
                }
                protobufDeviceDetails.networkSyncToken_ = this.networkSyncToken_;
                if ((i & 256) == 256) {
                    i2 |= 256;
                }
                protobufDeviceDetails.sdkVersion_ = this.sdkVersion_;
                protobufDeviceDetails.bitField0_ = i2;
                onBuilt();
                return protobufDeviceDetails;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            /* renamed from: clearField */
            public Builder mo10088clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (Builder) super.mo10088clearField(fieldDescriptor);
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            /* renamed from: getDefaultInstanceForType */
            public ProtobufDeviceDetails mo10094getDefaultInstanceForType() {
                return ProtobufDeviceDetails.getDefaultInstance();
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
                this.manufacturer_ = "";
                this.bitField0_ &= -2;
                this.deviceModelNumber_ = "";
                this.bitField0_ &= -3;
                this.deviceSerialNumber_ = "";
                this.bitField0_ &= -5;
                this.deviceHardwareRevision_ = "";
                this.bitField0_ &= -9;
                this.deviceFirmwareRevision_ = "";
                this.bitField0_ &= -17;
                SingleFieldBuilderV3<ProtobufDataMapClass.ProtobufDataMap, ProtobufDataMapClass.ProtobufDataMap.Builder, ProtobufDataMapClass.ProtobufDataMapOrBuilder> singleFieldBuilderV3 = this.deviceCapabilitiesMapBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.deviceCapabilitiesMap_ = null;
                } else {
                    singleFieldBuilderV3.clear();
                }
                this.bitField0_ &= -33;
                SingleFieldBuilderV3<ProtobufWifiConnectionDetailsClass.ProtobufWifiConnectionDetails, ProtobufWifiConnectionDetailsClass.ProtobufWifiConnectionDetails.Builder, ProtobufWifiConnectionDetailsClass.ProtobufWifiConnectionDetailsOrBuilder> singleFieldBuilderV32 = this.lastConnectionWifiDetailsBuilder_;
                if (singleFieldBuilderV32 == null) {
                    this.lastConnectionWifiDetails_ = null;
                } else {
                    singleFieldBuilderV32.clear();
                }
                this.bitField0_ &= -65;
                this.networkSyncToken_ = "";
                this.bitField0_ &= -129;
                this.sdkVersion_ = "";
                this.bitField0_ &= -257;
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
                if (message instanceof ProtobufDeviceDetails) {
                    return mergeFrom((ProtobufDeviceDetails) message);
                }
                super.mo10096mergeFrom(message);
                return this;
            }

            public Builder setDeviceCapabilitiesMap(ProtobufDataMapClass.ProtobufDataMap.Builder builder) {
                SingleFieldBuilderV3<ProtobufDataMapClass.ProtobufDataMap, ProtobufDataMapClass.ProtobufDataMap.Builder, ProtobufDataMapClass.ProtobufDataMapOrBuilder> singleFieldBuilderV3 = this.deviceCapabilitiesMapBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.deviceCapabilitiesMap_ = builder.mo10084build();
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(builder.mo10084build());
                }
                this.bitField0_ |= 32;
                return this;
            }

            public Builder setLastConnectionWifiDetails(ProtobufWifiConnectionDetailsClass.ProtobufWifiConnectionDetails.Builder builder) {
                SingleFieldBuilderV3<ProtobufWifiConnectionDetailsClass.ProtobufWifiConnectionDetails, ProtobufWifiConnectionDetailsClass.ProtobufWifiConnectionDetails.Builder, ProtobufWifiConnectionDetailsClass.ProtobufWifiConnectionDetailsOrBuilder> singleFieldBuilderV3 = this.lastConnectionWifiDetailsBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.lastConnectionWifiDetails_ = builder.mo10084build();
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(builder.mo10084build());
                }
                this.bitField0_ |= 64;
                return this;
            }

            public Builder mergeFrom(ProtobufDeviceDetails protobufDeviceDetails) {
                if (protobufDeviceDetails == ProtobufDeviceDetails.getDefaultInstance()) {
                    return this;
                }
                if (protobufDeviceDetails.hasManufacturer()) {
                    this.bitField0_ |= 1;
                    this.manufacturer_ = protobufDeviceDetails.manufacturer_;
                    onChanged();
                }
                if (protobufDeviceDetails.hasDeviceModelNumber()) {
                    this.bitField0_ |= 2;
                    this.deviceModelNumber_ = protobufDeviceDetails.deviceModelNumber_;
                    onChanged();
                }
                if (protobufDeviceDetails.hasDeviceSerialNumber()) {
                    this.bitField0_ |= 4;
                    this.deviceSerialNumber_ = protobufDeviceDetails.deviceSerialNumber_;
                    onChanged();
                }
                if (protobufDeviceDetails.hasDeviceHardwareRevision()) {
                    this.bitField0_ |= 8;
                    this.deviceHardwareRevision_ = protobufDeviceDetails.deviceHardwareRevision_;
                    onChanged();
                }
                if (protobufDeviceDetails.hasDeviceFirmwareRevision()) {
                    this.bitField0_ |= 16;
                    this.deviceFirmwareRevision_ = protobufDeviceDetails.deviceFirmwareRevision_;
                    onChanged();
                }
                if (protobufDeviceDetails.hasDeviceCapabilitiesMap()) {
                    mergeDeviceCapabilitiesMap(protobufDeviceDetails.getDeviceCapabilitiesMap());
                }
                if (protobufDeviceDetails.hasLastConnectionWifiDetails()) {
                    mergeLastConnectionWifiDetails(protobufDeviceDetails.getLastConnectionWifiDetails());
                }
                if (protobufDeviceDetails.hasNetworkSyncToken()) {
                    this.bitField0_ |= 128;
                    this.networkSyncToken_ = protobufDeviceDetails.networkSyncToken_;
                    onChanged();
                }
                if (protobufDeviceDetails.hasSdkVersion()) {
                    this.bitField0_ |= 256;
                    this.sdkVersion_ = protobufDeviceDetails.sdkVersion_;
                    onChanged();
                }
                mo10099mergeUnknownFields(((GeneratedMessageV3) protobufDeviceDetails).unknownFields);
                onChanged();
                return this;
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.manufacturer_ = "";
                this.deviceModelNumber_ = "";
                this.deviceSerialNumber_ = "";
                this.deviceHardwareRevision_ = "";
                this.deviceFirmwareRevision_ = "";
                this.deviceCapabilitiesMap_ = null;
                this.lastConnectionWifiDetails_ = null;
                this.networkSyncToken_ = "";
                this.sdkVersion_ = "";
                maybeForceBuilderInitialization();
            }

            /* JADX WARN: Removed duplicated region for block: B:16:0x0021  */
            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: mergeFrom */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public com.amazon.whisperjoin.protobuf.ProtobufDeviceDetailsClass.ProtobufDeviceDetails.Builder mo10097mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
                /*
                    r2 = this;
                    r0 = 0
                    com.google.protobuf.Parser<com.amazon.whisperjoin.protobuf.ProtobufDeviceDetailsClass$ProtobufDeviceDetails> r1 = com.amazon.whisperjoin.protobuf.ProtobufDeviceDetailsClass.ProtobufDeviceDetails.PARSER     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                    java.lang.Object r3 = r1.mo10082parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                    com.amazon.whisperjoin.protobuf.ProtobufDeviceDetailsClass$ProtobufDeviceDetails r3 = (com.amazon.whisperjoin.protobuf.ProtobufDeviceDetailsClass.ProtobufDeviceDetails) r3     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
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
                    com.amazon.whisperjoin.protobuf.ProtobufDeviceDetailsClass$ProtobufDeviceDetails r4 = (com.amazon.whisperjoin.protobuf.ProtobufDeviceDetailsClass.ProtobufDeviceDetails) r4     // Catch: java.lang.Throwable -> Lf
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
                throw new UnsupportedOperationException("Method not decompiled: com.amazon.whisperjoin.protobuf.ProtobufDeviceDetailsClass.ProtobufDeviceDetails.Builder.mo10097mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.amazon.whisperjoin.protobuf.ProtobufDeviceDetailsClass$ProtobufDeviceDetails$Builder");
            }
        }

        public static Builder newBuilder(ProtobufDeviceDetails protobufDeviceDetails) {
            return DEFAULT_INSTANCE.mo10081toBuilder().mergeFrom(protobufDeviceDetails);
        }

        public static ProtobufDeviceDetails parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.mo8403parseFrom(byteBuffer, extensionRegistryLite);
        }

        private ProtobufDeviceDetails(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        public static ProtobufDeviceDetails parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ProtobufDeviceDetails) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static ProtobufDeviceDetails parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.mo8396parseFrom(byteString);
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        /* renamed from: getDefaultInstanceForType */
        public ProtobufDeviceDetails mo10094getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        /* renamed from: toBuilder */
        public Builder mo10081toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        public static ProtobufDeviceDetails parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.mo8397parseFrom(byteString, extensionRegistryLite);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        /* renamed from: newBuilderForType */
        public Builder mo10079newBuilderForType() {
            return newBuilder();
        }

        private ProtobufDeviceDetails() {
            this.memoizedIsInitialized = (byte) -1;
            this.manufacturer_ = "";
            this.deviceModelNumber_ = "";
            this.deviceSerialNumber_ = "";
            this.deviceHardwareRevision_ = "";
            this.deviceFirmwareRevision_ = "";
            this.networkSyncToken_ = "";
            this.sdkVersion_ = "";
        }

        public static ProtobufDeviceDetails parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.mo8404parseFrom(bArr);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageV3
        /* renamed from: newBuilderForType */
        public Builder mo10080newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public static ProtobufDeviceDetails parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.mo8407parseFrom(bArr, extensionRegistryLite);
        }

        public static ProtobufDeviceDetails parseFrom(InputStream inputStream) throws IOException {
            return (ProtobufDeviceDetails) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static ProtobufDeviceDetails parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ProtobufDeviceDetails) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static ProtobufDeviceDetails parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (ProtobufDeviceDetails) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static ProtobufDeviceDetails parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ProtobufDeviceDetails) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        private ProtobufDeviceDetails(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                    this.manufacturer_ = readBytes;
                                } else if (readTag == 18) {
                                    ByteString readBytes2 = codedInputStream.readBytes();
                                    this.bitField0_ |= 2;
                                    this.deviceModelNumber_ = readBytes2;
                                } else if (readTag == 26) {
                                    ByteString readBytes3 = codedInputStream.readBytes();
                                    this.bitField0_ |= 4;
                                    this.deviceSerialNumber_ = readBytes3;
                                } else if (readTag == 34) {
                                    ByteString readBytes4 = codedInputStream.readBytes();
                                    this.bitField0_ |= 8;
                                    this.deviceHardwareRevision_ = readBytes4;
                                } else if (readTag != 42) {
                                    ProtobufWifiConnectionDetailsClass.ProtobufWifiConnectionDetails.Builder builder = null;
                                    ProtobufDataMapClass.ProtobufDataMap.Builder mo10081toBuilder = null;
                                    if (readTag == 50) {
                                        mo10081toBuilder = (this.bitField0_ & 32) == 32 ? this.deviceCapabilitiesMap_.mo10081toBuilder() : mo10081toBuilder;
                                        this.deviceCapabilitiesMap_ = (ProtobufDataMapClass.ProtobufDataMap) codedInputStream.readMessage(ProtobufDataMapClass.ProtobufDataMap.PARSER, extensionRegistryLite);
                                        if (mo10081toBuilder != null) {
                                            mo10081toBuilder.mergeFrom(this.deviceCapabilitiesMap_);
                                            this.deviceCapabilitiesMap_ = mo10081toBuilder.mo10085buildPartial();
                                        }
                                        this.bitField0_ |= 32;
                                    } else if (readTag == 58) {
                                        builder = (this.bitField0_ & 64) == 64 ? this.lastConnectionWifiDetails_.mo10081toBuilder() : builder;
                                        this.lastConnectionWifiDetails_ = (ProtobufWifiConnectionDetailsClass.ProtobufWifiConnectionDetails) codedInputStream.readMessage(ProtobufWifiConnectionDetailsClass.ProtobufWifiConnectionDetails.PARSER, extensionRegistryLite);
                                        if (builder != null) {
                                            builder.mergeFrom(this.lastConnectionWifiDetails_);
                                            this.lastConnectionWifiDetails_ = builder.mo10085buildPartial();
                                        }
                                        this.bitField0_ |= 64;
                                    } else if (readTag == 66) {
                                        ByteString readBytes5 = codedInputStream.readBytes();
                                        this.bitField0_ |= 128;
                                        this.networkSyncToken_ = readBytes5;
                                    } else if (readTag != 74) {
                                        if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                        }
                                    } else {
                                        ByteString readBytes6 = codedInputStream.readBytes();
                                        this.bitField0_ |= 256;
                                        this.sdkVersion_ = readBytes6;
                                    }
                                } else {
                                    ByteString readBytes7 = codedInputStream.readBytes();
                                    this.bitField0_ |= 16;
                                    this.deviceFirmwareRevision_ = readBytes7;
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
    }

    /* loaded from: classes13.dex */
    public interface ProtobufDeviceDetailsOrBuilder extends MessageOrBuilder {
        ProtobufDataMapClass.ProtobufDataMap getDeviceCapabilitiesMap();

        ProtobufDataMapClass.ProtobufDataMapOrBuilder getDeviceCapabilitiesMapOrBuilder();

        String getDeviceFirmwareRevision();

        ByteString getDeviceFirmwareRevisionBytes();

        String getDeviceHardwareRevision();

        ByteString getDeviceHardwareRevisionBytes();

        String getDeviceModelNumber();

        ByteString getDeviceModelNumberBytes();

        String getDeviceSerialNumber();

        ByteString getDeviceSerialNumberBytes();

        ProtobufWifiConnectionDetailsClass.ProtobufWifiConnectionDetails getLastConnectionWifiDetails();

        ProtobufWifiConnectionDetailsClass.ProtobufWifiConnectionDetailsOrBuilder getLastConnectionWifiDetailsOrBuilder();

        String getManufacturer();

        ByteString getManufacturerBytes();

        String getNetworkSyncToken();

        ByteString getNetworkSyncTokenBytes();

        String getSdkVersion();

        ByteString getSdkVersionBytes();

        boolean hasDeviceCapabilitiesMap();

        boolean hasDeviceFirmwareRevision();

        boolean hasDeviceHardwareRevision();

        boolean hasDeviceModelNumber();

        boolean hasDeviceSerialNumber();

        boolean hasLastConnectionWifiDetails();

        boolean hasManufacturer();

        boolean hasNetworkSyncToken();

        boolean hasSdkVersion();
    }

    static {
        Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\nYWhisperJoinProtocolBuffersModel/schema/provisioning/data/provisioning/DeviceDetails.proto\u0012\bprotobuf\u001aTWhisperJoinProtocolBuffersModel/schema/provisioning/data/configuration/DataMap.proto\u001aYWhisperJoinProtocolBuffersModel/schema/provisioning/data/wifi/WifiConnectionDetails.proto\"Ø\u0002\n\u0015ProtobufDeviceDetails\u0012\u0014\n\fmanufacturer\u0018\u0001 \u0002(\t\u0012\u0019\n\u0011deviceModelNumber\u0018\u0002 \u0002(\t\u0012\u001a\n\u0012deviceSerialNumber\u0018\u0003 \u0002(\t\u0012\u001e\n\u0016deviceHardwareRevision\u0018\u0004 \u0002(\t\u0012\u001e\n\u0016deviceFirmwareRevision\u0018\u0005 \u0002(\t\u00128\n\u0015deviceCapabilitiesMap\u0018\u0006 \u0002(\u000b2\u0019.protobuf.ProtobufDataMap\u0012J\n\u0019lastConnectionWifiDetails\u0018\u0007 \u0001(\u000b2'.protobuf.ProtobufWifiConnectionDetails\u0012\u0018\n\u0010networkSyncToken\u0018\b \u0001(\t\u0012\u0012\n\nsdkVersion\u0018\t \u0001(\tB=\n\u001fcom.amazon.whisperjoin.protobufB\u001aProtobufDeviceDetailsClass"}, new Descriptors.FileDescriptor[]{ProtobufDataMapClass.getDescriptor(), ProtobufWifiConnectionDetailsClass.getDescriptor()}, new Descriptors.FileDescriptor.InternalDescriptorAssigner() { // from class: com.amazon.whisperjoin.protobuf.ProtobufDeviceDetailsClass.1
            @Override // com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner
            public ExtensionRegistry assignDescriptors(Descriptors.FileDescriptor fileDescriptor) {
                Descriptors.FileDescriptor unused = ProtobufDeviceDetailsClass.descriptor = fileDescriptor;
                return null;
            }
        });
        internal_static_protobuf_ProtobufDeviceDetails_descriptor = getDescriptor().getMessageTypes().get(0);
        internal_static_protobuf_ProtobufDeviceDetails_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_protobuf_ProtobufDeviceDetails_descriptor, new String[]{"Manufacturer", "DeviceModelNumber", RemoteConfigurationAndroidClientContextDecorator.DEVICE_SERIAL_NUMBER, "DeviceHardwareRevision", "DeviceFirmwareRevision", "DeviceCapabilitiesMap", "LastConnectionWifiDetails", "NetworkSyncToken", "SdkVersion"});
        ProtobufDataMapClass.getDescriptor();
        ProtobufWifiConnectionDetailsClass.getDescriptor();
    }

    private ProtobufDeviceDetailsClass() {
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
