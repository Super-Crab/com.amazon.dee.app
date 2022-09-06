package com.amazon.devicesetup.protobuf.data.wifi;

import com.amazon.devicesetup.exceptions.DataSerializationError;
import com.amazon.devicesetup.provisioning.data.wifi.WifiKeyManagement;
import com.amazon.devicesetup.provisioning.data.wifi.WifiScanResult;
import com.amazon.devicesetup.provisioning.data.wifi.WifiScanResultCollection;
import com.amazon.devicesetup.serializer.TypeSerializer;
import com.amazon.whisperjoin.protobuf.ProtobufWifiKeyManagementClass;
import com.amazon.whisperjoin.protobuf.ProtobufWifiScanResultClass;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.protobuf.InvalidProtocolBufferException;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes12.dex */
public class ProtoWifiScanResultCollection implements TypeSerializer<WifiScanResultCollection> {
    private ProtobufWifiScanResultClass.ProtobufWifiScanResult createProtobufWifiScanResult(WifiScanResult wifiScanResult) {
        return ProtobufWifiScanResultClass.ProtobufWifiScanResult.newBuilder().setSsid(wifiScanResult.getSsid()).setWifiKeyManagement(getProtobufKeyManagement(wifiScanResult.getKeyManagement())).setSignalStrength(wifiScanResult.getSignalStrength()).setFrequencyBand(wifiScanResult.getFrequencyBand()).mo10084build();
    }

    private WifiScanResult createWifiScanResult(ProtobufWifiScanResultClass.ProtobufWifiScanResult protobufWifiScanResult) {
        return new WifiScanResult(protobufWifiScanResult.getSsid(), getWifiKeyManagement(protobufWifiScanResult.getWifiKeyManagement()), protobufWifiScanResult.getFrequencyBand(), protobufWifiScanResult.getSignalStrength());
    }

    private ProtobufWifiKeyManagementClass.ProtobufWifiKeyManagement.WifiKeyManagement getProtobufKeyManagement(WifiKeyManagement wifiKeyManagement) {
        return ProtobufWifiKeyManagementClass.ProtobufWifiKeyManagement.WifiKeyManagement.valueOf(wifiKeyManagement.ordinal());
    }

    private WifiKeyManagement getWifiKeyManagement(ProtobufWifiKeyManagementClass.ProtobufWifiKeyManagement.WifiKeyManagement wifiKeyManagement) {
        return WifiKeyManagement.values()[wifiKeyManagement.ordinal()];
    }

    public ProtobufWifiScanResultClass.ProtobufWifiScanResultCollection getProtobufWifiScanResultCollection(WifiScanResultCollection wifiScanResultCollection) {
        ArrayList arrayList = new ArrayList(wifiScanResultCollection.getSetCollection());
        ProtobufWifiScanResultClass.ProtobufWifiScanResultCollection.Builder newBuilder = ProtobufWifiScanResultClass.ProtobufWifiScanResultCollection.newBuilder();
        for (int i = 0; i < arrayList.size(); i++) {
            newBuilder.addProtobufWifiScanResultCollection(createProtobufWifiScanResult((WifiScanResult) arrayList.get(i)));
        }
        return newBuilder.mo10084build();
    }

    public WifiScanResultCollection getWifiScanResultCollection(ProtobufWifiScanResultClass.ProtobufWifiScanResultCollection protobufWifiScanResultCollection) {
        List<ProtobufWifiScanResultClass.ProtobufWifiScanResult> protobufWifiScanResultCollectionList = protobufWifiScanResultCollection.getProtobufWifiScanResultCollectionList();
        ArrayList arrayList = new ArrayList();
        for (ProtobufWifiScanResultClass.ProtobufWifiScanResult protobufWifiScanResult : protobufWifiScanResultCollectionList) {
            arrayList.add(createWifiScanResult(protobufWifiScanResult));
        }
        return new WifiScanResultCollection(arrayList);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.devicesetup.serializer.TypeSerializer
    /* renamed from: deserialize */
    public WifiScanResultCollection mo4042deserialize(byte[] bArr) {
        if (bArr == null) {
            return new WifiScanResultCollection();
        }
        try {
            return getWifiScanResultCollection(ProtobufWifiScanResultClass.ProtobufWifiScanResultCollection.parseFrom(bArr));
        } catch (InvalidProtocolBufferException e) {
            throw new DataSerializationError(GeneratedOutlineSupport1.outline36(e, GeneratedOutlineSupport1.outline107("Illegal data: ")), e);
        }
    }

    @Override // com.amazon.devicesetup.serializer.TypeSerializer
    public byte[] serialize(WifiScanResultCollection wifiScanResultCollection) {
        return getProtobufWifiScanResultCollection(wifiScanResultCollection).toByteArray();
    }
}
