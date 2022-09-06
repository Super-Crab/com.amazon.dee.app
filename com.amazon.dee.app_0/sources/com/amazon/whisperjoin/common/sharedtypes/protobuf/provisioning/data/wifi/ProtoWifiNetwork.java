package com.amazon.whisperjoin.common.sharedtypes.protobuf.provisioning.data.wifi;

import com.amazon.whisperjoin.common.sharedtypes.exceptions.DataSerializationError;
import com.amazon.whisperjoin.common.sharedtypes.protobuf.TypeSerializer;
import com.amazon.whisperjoin.common.sharedtypes.provisioning.data.wifi.WifiKeyManagement;
import com.amazon.whisperjoin.common.sharedtypes.provisioning.data.wifi.WifiNetwork;
import com.amazon.whisperjoin.protobuf.ProtobufWifiKeyManagementClass;
import com.amazon.whisperjoin.protobuf.ProtobufWifiNetworkClass;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.protobuf.InvalidProtocolBufferException;
/* loaded from: classes13.dex */
public class ProtoWifiNetwork implements TypeSerializer<WifiNetwork> {
    private ProtobufWifiKeyManagementClass.ProtobufWifiKeyManagement.WifiKeyManagement getProtobufKeyManagement(WifiKeyManagement wifiKeyManagement) {
        return ProtobufWifiKeyManagementClass.ProtobufWifiKeyManagement.WifiKeyManagement.valueOf(wifiKeyManagement.ordinal());
    }

    private WifiKeyManagement getWifiKeyManagement(ProtobufWifiKeyManagementClass.ProtobufWifiKeyManagement.WifiKeyManagement wifiKeyManagement) {
        return WifiKeyManagement.values()[wifiKeyManagement.ordinal()];
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.whisperjoin.common.sharedtypes.protobuf.TypeSerializer
    /* renamed from: deserialize */
    public WifiNetwork mo5427deserialize(byte[] bArr) {
        try {
            ProtobufWifiNetworkClass.ProtobufWifiNetwork parseFrom = ProtobufWifiNetworkClass.ProtobufWifiNetwork.parseFrom(bArr);
            return new WifiNetwork(parseFrom.getSsid(), getWifiKeyManagement(parseFrom.getWifiKeyManagement()));
        } catch (InvalidProtocolBufferException e) {
            throw new DataSerializationError(GeneratedOutlineSupport1.outline36(e, GeneratedOutlineSupport1.outline107("Illegal data: ")), e);
        }
    }

    @Override // com.amazon.whisperjoin.common.sharedtypes.protobuf.TypeSerializer
    public byte[] serialize(WifiNetwork wifiNetwork) {
        return ProtobufWifiNetworkClass.ProtobufWifiNetwork.newBuilder().setSsid(wifiNetwork.getSsid()).setWifiKeyManagement(getProtobufKeyManagement(wifiNetwork.getKeyManagement())).mo10084build().toByteArray();
    }
}
