package com.amazon.whisperjoin.common.sharedtypes.protobuf.provisioning.data.wifi;

import com.amazon.whisperjoin.common.sharedtypes.exceptions.DataSerializationError;
import com.amazon.whisperjoin.common.sharedtypes.protobuf.TypeSerializer;
import com.amazon.whisperjoin.common.sharedtypes.provisioning.data.wifi.WifiConfiguration;
import com.amazon.whisperjoin.common.sharedtypes.provisioning.data.wifi.WifiKeyManagement;
import com.amazon.whisperjoin.protobuf.ProtobufWifiConfigurationClass;
import com.amazon.whisperjoin.protobuf.ProtobufWifiKeyManagementClass;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.protobuf.InvalidProtocolBufferException;
/* loaded from: classes13.dex */
public class ProtoWifiConfiguration implements TypeSerializer<WifiConfiguration> {

    /* renamed from: com.amazon.whisperjoin.common.sharedtypes.protobuf.provisioning.data.wifi.ProtoWifiConfiguration$1  reason: invalid class name */
    /* loaded from: classes13.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$whisperjoin$common$sharedtypes$provisioning$data$wifi$WifiKeyManagement = new int[WifiKeyManagement.values().length];

        static {
            try {
                $SwitchMap$com$amazon$whisperjoin$common$sharedtypes$provisioning$data$wifi$WifiKeyManagement[WifiKeyManagement.NONE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$whisperjoin$common$sharedtypes$provisioning$data$wifi$WifiKeyManagement[WifiKeyManagement.WEP.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$whisperjoin$common$sharedtypes$provisioning$data$wifi$WifiKeyManagement[WifiKeyManagement.WPA_PSK.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    private ProtobufWifiKeyManagementClass.ProtobufWifiKeyManagement.WifiKeyManagement getProtobufKeyManagement(WifiKeyManagement wifiKeyManagement) {
        return ProtobufWifiKeyManagementClass.ProtobufWifiKeyManagement.WifiKeyManagement.valueOf(wifiKeyManagement.ordinal());
    }

    private WifiKeyManagement getWifiKeyManagement(ProtobufWifiKeyManagementClass.ProtobufWifiKeyManagement.WifiKeyManagement wifiKeyManagement) {
        return WifiKeyManagement.values()[wifiKeyManagement.ordinal()];
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.whisperjoin.common.sharedtypes.protobuf.TypeSerializer
    /* renamed from: deserialize */
    public WifiConfiguration mo5427deserialize(byte[] bArr) {
        try {
            ProtobufWifiConfigurationClass.ProtobufWifiConfiguration parseFrom = ProtobufWifiConfigurationClass.ProtobufWifiConfiguration.parseFrom(bArr);
            String ssid = parseFrom.getSsid();
            String wepKey = parseFrom.getWepKey();
            String psk = parseFrom.getPsk();
            int networkPriority = parseFrom.getNetworkPriority();
            boolean isHiddenNetwork = parseFrom.getIsHiddenNetwork();
            WifiKeyManagement wifiKeyManagement = getWifiKeyManagement(parseFrom.getWifiKeyManagement());
            int ordinal = wifiKeyManagement.ordinal();
            if (ordinal == 0) {
                return WifiConfiguration.createOpenWifiConfiguration(ssid, networkPriority, isHiddenNetwork);
            }
            if (ordinal == 1) {
                return WifiConfiguration.createWpaWifiConfiguration(ssid, psk, networkPriority, isHiddenNetwork);
            }
            if (ordinal == 2) {
                return WifiConfiguration.createWepWifiConfiguration(ssid, wepKey, networkPriority, isHiddenNetwork);
            }
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("WifiKeyManagement not supported: ");
            outline107.append(wifiKeyManagement.toString());
            throw new DataSerializationError(outline107.toString());
        } catch (InvalidProtocolBufferException e) {
            throw new DataSerializationError(GeneratedOutlineSupport1.outline36(e, GeneratedOutlineSupport1.outline107("Illegal data: ")), e);
        }
    }

    @Override // com.amazon.whisperjoin.common.sharedtypes.protobuf.TypeSerializer
    public byte[] serialize(WifiConfiguration wifiConfiguration) {
        ProtobufWifiConfigurationClass.ProtobufWifiConfiguration.Builder isHiddenNetwork = ProtobufWifiConfigurationClass.ProtobufWifiConfiguration.newBuilder().setSsid(wifiConfiguration.getSsid()).setWifiKeyManagement(getProtobufKeyManagement(wifiConfiguration.getKeyManagement())).setNetworkPriority(wifiConfiguration.getNetworkPriority()).setIsHiddenNetwork(wifiConfiguration.isHiddenNetwork());
        if (wifiConfiguration.getWepKey() != null) {
            isHiddenNetwork = isHiddenNetwork.setWepKey(wifiConfiguration.getWepKey());
        }
        if (wifiConfiguration.getPsk() != null) {
            isHiddenNetwork = isHiddenNetwork.setPsk(wifiConfiguration.getPsk());
        }
        return isHiddenNetwork.mo10084build().toByteArray();
    }
}
