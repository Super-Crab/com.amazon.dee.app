package com.amazon.whisperjoin.common.sharedtypes.protobuf.provisioning.data.provisioning;

import com.amazon.whisperjoin.common.sharedtypes.exceptions.DataSerializationError;
import com.amazon.whisperjoin.common.sharedtypes.protobuf.TypeSerializer;
import com.amazon.whisperjoin.common.sharedtypes.protobuf.provisioning.data.configuration.ProtoDataMap;
import com.amazon.whisperjoin.common.sharedtypes.protobuf.provisioning.data.wifi.ProtoWifiConnectionDetails;
import com.amazon.whisperjoin.common.sharedtypes.provisioning.data.provisioning.DeviceDetails;
import com.amazon.whisperjoin.common.sharedtypes.provisioning.data.provisioning.DeviceDetailsProtoData;
import com.amazon.whisperjoin.protobuf.ProtobufDeviceDetailsClass;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.protobuf.InvalidProtocolBufferException;
/* loaded from: classes13.dex */
public class ProtoDeviceDetails implements TypeSerializer<DeviceDetailsProtoData> {
    private final ProtoDataMap mProtoDataMap;
    private final ProtoWifiConnectionDetails mProtoWifiConnectionDetails;

    public ProtoDeviceDetails(ProtoDataMap protoDataMap, ProtoWifiConnectionDetails protoWifiConnectionDetails) {
        this.mProtoDataMap = protoDataMap;
        this.mProtoWifiConnectionDetails = protoWifiConnectionDetails;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.whisperjoin.common.sharedtypes.protobuf.TypeSerializer
    /* renamed from: deserialize */
    public DeviceDetailsProtoData mo5427deserialize(byte[] bArr) {
        try {
            ProtobufDeviceDetailsClass.ProtobufDeviceDetails parseFrom = ProtobufDeviceDetailsClass.ProtobufDeviceDetails.parseFrom(bArr);
            return new DeviceDetailsProtoData(new DeviceDetails(parseFrom.getManufacturer(), parseFrom.getDeviceModelNumber(), parseFrom.getDeviceSerialNumber(), parseFrom.getDeviceHardwareRevision(), parseFrom.getDeviceFirmwareRevision(), this.mProtoDataMap.deserializeMap(parseFrom.getDeviceCapabilitiesMap()), parseFrom.hasLastConnectionWifiDetails() ? this.mProtoWifiConnectionDetails.getWifiConnectionDetails(parseFrom.getLastConnectionWifiDetails()) : null, parseFrom.getSdkVersion()), parseFrom.getNetworkSyncToken());
        } catch (InvalidProtocolBufferException e) {
            throw new DataSerializationError(GeneratedOutlineSupport1.outline36(e, GeneratedOutlineSupport1.outline107("Illegal data: ")), e);
        }
    }

    @Override // com.amazon.whisperjoin.common.sharedtypes.protobuf.TypeSerializer
    public byte[] serialize(DeviceDetailsProtoData deviceDetailsProtoData) {
        ProtobufDeviceDetailsClass.ProtobufDeviceDetails.Builder deviceCapabilitiesMap = ProtobufDeviceDetailsClass.ProtobufDeviceDetails.newBuilder().setManufacturer(deviceDetailsProtoData.getDeviceDetails().getManufacturer()).setDeviceModelNumber(deviceDetailsProtoData.getDeviceDetails().getDeviceModelNumber()).setDeviceSerialNumber(deviceDetailsProtoData.getDeviceDetails().getDeviceSerialNumber()).setDeviceHardwareRevision(deviceDetailsProtoData.getDeviceDetails().getDeviceHardwareRevision()).setDeviceFirmwareRevision(deviceDetailsProtoData.getDeviceDetails().getDeviceFirmwareRevision()).setDeviceCapabilitiesMap(this.mProtoDataMap.serializeMap(deviceDetailsProtoData.getDeviceDetails().getDeviceCapabilitiesMap()));
        if (deviceDetailsProtoData.getDeviceDetails().hasLastConnectionWifiDetails()) {
            deviceCapabilitiesMap.setLastConnectionWifiDetails(this.mProtoWifiConnectionDetails.getProtobufWifiConnectionDetails(deviceDetailsProtoData.getDeviceDetails().getLastConnectionWifiDetails()));
        }
        if (deviceDetailsProtoData.getNetworkSyncToken() != null) {
            deviceCapabilitiesMap.setNetworkSyncToken(deviceDetailsProtoData.getNetworkSyncToken());
        }
        if (deviceDetailsProtoData.getDeviceDetails().getSdkVersion() != null) {
            deviceCapabilitiesMap.setSdkVersion(deviceDetailsProtoData.getDeviceDetails().getSdkVersion());
        }
        return deviceCapabilitiesMap.mo10084build().toByteArray();
    }
}
