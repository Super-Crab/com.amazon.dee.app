package com.amazon.devicesetup.protobuf.data.configuration;

import com.amazon.devicesetup.exceptions.DataSerializationError;
import com.amazon.devicesetup.provisioning.data.configuration.ConfigurationKeySet;
import com.amazon.devicesetup.serializer.TypeSerializer;
import com.amazon.whisperjoin.protobuf.ProtobufConfigurationKeySetClass;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.protobuf.InvalidProtocolBufferException;
import java.util.Collection;
/* loaded from: classes12.dex */
public class ProtoConfigurationKeySet implements TypeSerializer<ConfigurationKeySet> {
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.devicesetup.serializer.TypeSerializer
    /* renamed from: deserialize */
    public ConfigurationKeySet mo4042deserialize(byte[] bArr) {
        try {
            return new ConfigurationKeySet(ProtobufConfigurationKeySetClass.ProtobufConfigurationKeySet.parseFrom(bArr).mo5862getKeySetList());
        } catch (InvalidProtocolBufferException e) {
            throw new DataSerializationError(GeneratedOutlineSupport1.outline36(e, GeneratedOutlineSupport1.outline107("Illegal data: ")), e);
        }
    }

    @Override // com.amazon.devicesetup.serializer.TypeSerializer
    public byte[] serialize(ConfigurationKeySet configurationKeySet) {
        Collection<String> keySet = configurationKeySet.getKeySet();
        ProtobufConfigurationKeySetClass.ProtobufConfigurationKeySet.Builder newBuilder = ProtobufConfigurationKeySetClass.ProtobufConfigurationKeySet.newBuilder();
        for (String str : keySet) {
            newBuilder.addKeySet(str);
        }
        return newBuilder.mo10084build().toByteArray();
    }
}
