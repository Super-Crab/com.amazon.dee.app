package com.amazon.devicesetup.protobuf.data.device;

import com.amazon.devicesetup.exceptions.DataSerializationError;
import com.amazon.devicesetup.protobuf.data.configuration.ProtoDataMap;
import com.amazon.devicesetup.protobuf.data.wifi.ProtoWifiConnectionDetails;
import com.amazon.devicesetup.provisioning.data.device.DeviceDetails;
import com.amazon.devicesetup.serializer.TypeSerializer;
import com.amazon.whisperjoin.protobuf.ProtobufDeviceDetailsClass;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.protobuf.InvalidProtocolBufferException;
/* loaded from: classes12.dex */
public class ProtoDeviceDetails implements TypeSerializer<DeviceDetails> {
    private final ProtoDataMap protoDataMap;
    private final ProtoWifiConnectionDetails protoWifiConnectionDetails;

    public ProtoDeviceDetails(ProtoDataMap protoDataMap, ProtoWifiConnectionDetails protoWifiConnectionDetails) {
        this.protoDataMap = protoDataMap;
        this.protoWifiConnectionDetails = protoWifiConnectionDetails;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.devicesetup.serializer.TypeSerializer
    /* renamed from: deserialize */
    public DeviceDetails mo4042deserialize(byte[] bArr) {
        try {
            ProtobufDeviceDetailsClass.ProtobufDeviceDetails parseFrom = ProtobufDeviceDetailsClass.ProtobufDeviceDetails.parseFrom(bArr);
            return new DeviceDetails(parseFrom.getManufacturer(), parseFrom.getDeviceModelNumber(), parseFrom.getDeviceSerialNumber(), parseFrom.getDeviceHardwareRevision(), parseFrom.getDeviceFirmwareRevision(), this.protoDataMap.deserializeMap(parseFrom.getDeviceCapabilitiesMap()), parseFrom.hasLastConnectionWifiDetails() ? this.protoWifiConnectionDetails.getWifiConnectionDetails(parseFrom.getLastConnectionWifiDetails()) : null, parseFrom.getNetworkSyncToken(), parseFrom.getSdkVersion());
        } catch (InvalidProtocolBufferException e) {
            throw new DataSerializationError(GeneratedOutlineSupport1.outline36(e, GeneratedOutlineSupport1.outline107("Illegal data: ")), e);
        }
    }

    @Override // com.amazon.devicesetup.serializer.TypeSerializer
    public byte[] serialize(DeviceDetails deviceDetails) {
        ProtobufDeviceDetailsClass.ProtobufDeviceDetails.Builder deviceCapabilitiesMap = ProtobufDeviceDetailsClass.ProtobufDeviceDetails.newBuilder().setManufacturer(deviceDetails.getManufacturer()).setDeviceModelNumber(deviceDetails.getDeviceModelNumber()).setDeviceSerialNumber(deviceDetails.getDeviceSerialNumber()).setDeviceHardwareRevision(deviceDetails.getDeviceHardwareRevision()).setDeviceFirmwareRevision(deviceDetails.getDeviceFirmwareRevision()).setDeviceCapabilitiesMap(this.protoDataMap.serializeMap(deviceDetails.getDeviceCapabilitiesMap()));
        if (deviceDetails.hasLastConnectionWifiDetails()) {
            deviceCapabilitiesMap.setLastConnectionWifiDetails(this.protoWifiConnectionDetails.getProtobufWifiConnectionDetails(deviceDetails.getLastConnectionWifiDetails()));
        }
        if (deviceDetails.hasNetworkSyncToken()) {
            deviceCapabilitiesMap.setNetworkSyncToken(deviceDetails.getNetworkSyncToken());
        }
        if (deviceDetails.getSdkVersion() != null) {
            deviceCapabilitiesMap.setSdkVersion(deviceDetails.getSdkVersion());
        }
        return deviceCapabilitiesMap.mo10084build().toByteArray();
    }
}
