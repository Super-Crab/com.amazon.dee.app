package com.amazon.whisperjoin.common.sharedtypes.protobuf.provisioning.data.crypto;

import com.amazon.whispercloak.protobuf.SecureTransportProtos;
import com.amazon.whisperjoin.common.sharedtypes.cryptography.ProvisionableDeviceAuthenticationData;
import com.amazon.whisperjoin.common.sharedtypes.exceptions.DataSerializationError;
import com.amazon.whisperjoin.common.sharedtypes.protobuf.TypeSerializer;
import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes13.dex */
public class ProtoProvisionableDeviceAuthenticationData implements TypeSerializer<ProvisionableDeviceAuthenticationData> {
    private List<byte[]> getByteArrayList(List<ByteString> list) {
        ArrayList arrayList = new ArrayList();
        for (ByteString byteString : list) {
            arrayList.add(byteString.toByteArray());
        }
        return arrayList;
    }

    private List<ByteString> getByteStringList(List<byte[]> list) {
        ArrayList arrayList = new ArrayList();
        for (byte[] bArr : list) {
            arrayList.add(ByteString.copyFrom(bArr));
        }
        return arrayList;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.whisperjoin.common.sharedtypes.protobuf.TypeSerializer
    /* renamed from: deserialize */
    public ProvisionableDeviceAuthenticationData mo5427deserialize(byte[] bArr) {
        try {
            SecureTransportProtos.ProvisionableDeviceAuthenticationData parseFrom = SecureTransportProtos.ProvisionableDeviceAuthenticationData.parseFrom(bArr);
            return new ProvisionableDeviceAuthenticationData(getByteArrayList(parseFrom.getCertificateChainList()), parseFrom.getSignature().toByteArray());
        } catch (InvalidProtocolBufferException e) {
            throw new DataSerializationError("Unable to deserialize ProvisionableDeviceAuthenticationData", e);
        }
    }

    @Override // com.amazon.whisperjoin.common.sharedtypes.protobuf.TypeSerializer
    public byte[] serialize(ProvisionableDeviceAuthenticationData provisionableDeviceAuthenticationData) {
        return SecureTransportProtos.ProvisionableDeviceAuthenticationData.newBuilder().addAllCertificateChain(getByteStringList(provisionableDeviceAuthenticationData.getCertificateChain())).setSignature(ByteString.copyFrom(provisionableDeviceAuthenticationData.getSignature())).mo10084build().toByteArray();
    }
}
