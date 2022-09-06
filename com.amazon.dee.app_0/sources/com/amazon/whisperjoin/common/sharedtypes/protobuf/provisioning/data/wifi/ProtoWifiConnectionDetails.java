package com.amazon.whisperjoin.common.sharedtypes.protobuf.provisioning.data.wifi;

import com.amazon.whisperjoin.common.sharedtypes.exceptions.DataSerializationError;
import com.amazon.whisperjoin.common.sharedtypes.protobuf.TypeSerializer;
import com.amazon.whisperjoin.common.sharedtypes.provisioning.data.wifi.WifiConnectionDetails;
import com.amazon.whisperjoin.common.sharedtypes.provisioning.data.wifi.WifiKeyManagement;
import com.amazon.whisperjoin.protobuf.ProtobufWifiConnectionDetailsClass;
import com.amazon.whisperjoin.protobuf.ProtobufWifiKeyManagementClass;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.protobuf.InvalidProtocolBufferException;
/* loaded from: classes13.dex */
public class ProtoWifiConnectionDetails implements TypeSerializer<WifiConnectionDetails> {
    private ProtobufWifiKeyManagementClass.ProtobufWifiKeyManagement.WifiKeyManagement getProtobufKeyManagement(WifiKeyManagement wifiKeyManagement) {
        return ProtobufWifiKeyManagementClass.ProtobufWifiKeyManagement.WifiKeyManagement.valueOf(wifiKeyManagement.ordinal());
    }

    private ProtobufWifiConnectionDetailsClass.ProtobufWifiConnectionDetails.State getProtobufState(WifiConnectionDetails.State state) {
        return ProtobufWifiConnectionDetailsClass.ProtobufWifiConnectionDetails.State.valueOf(state.getValue());
    }

    private WifiConnectionDetails.State getState(ProtobufWifiConnectionDetailsClass.ProtobufWifiConnectionDetails.State state) {
        return (WifiConnectionDetails.State) Enum.valueOf(WifiConnectionDetails.State.class, state.name());
    }

    private WifiKeyManagement getWifiKeyManagement(ProtobufWifiKeyManagementClass.ProtobufWifiKeyManagement.WifiKeyManagement wifiKeyManagement) {
        return WifiKeyManagement.values()[wifiKeyManagement.ordinal()];
    }

    public ProtobufWifiConnectionDetailsClass.ProtobufWifiConnectionDetails getProtobufWifiConnectionDetails(WifiConnectionDetails wifiConnectionDetails) {
        return ProtobufWifiConnectionDetailsClass.ProtobufWifiConnectionDetails.newBuilder().setSsid(wifiConnectionDetails.getSsid()).setWifiKeyManagement(getProtobufKeyManagement(wifiConnectionDetails.getKeyManagement())).setState(getProtobufState(wifiConnectionDetails.getConnectionState())).mo10084build();
    }

    public WifiConnectionDetails getWifiConnectionDetails(ProtobufWifiConnectionDetailsClass.ProtobufWifiConnectionDetails protobufWifiConnectionDetails) {
        return new WifiConnectionDetails(protobufWifiConnectionDetails.getSsid(), getWifiKeyManagement(protobufWifiConnectionDetails.getWifiKeyManagement()), getState(protobufWifiConnectionDetails.getState()));
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.whisperjoin.common.sharedtypes.protobuf.TypeSerializer
    /* renamed from: deserialize */
    public WifiConnectionDetails mo5427deserialize(byte[] bArr) {
        try {
            return getWifiConnectionDetails(ProtobufWifiConnectionDetailsClass.ProtobufWifiConnectionDetails.parseFrom(bArr));
        } catch (InvalidProtocolBufferException e) {
            throw new DataSerializationError(GeneratedOutlineSupport1.outline36(e, GeneratedOutlineSupport1.outline107("Illegal data: ")), e);
        }
    }

    @Override // com.amazon.whisperjoin.common.sharedtypes.protobuf.TypeSerializer
    public byte[] serialize(WifiConnectionDetails wifiConnectionDetails) {
        return getProtobufWifiConnectionDetails(wifiConnectionDetails).toByteArray();
    }
}
