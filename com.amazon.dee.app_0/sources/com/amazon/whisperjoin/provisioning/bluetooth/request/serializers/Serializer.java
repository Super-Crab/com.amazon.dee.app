package com.amazon.whisperjoin.provisioning.bluetooth.request.serializers;
/* loaded from: classes13.dex */
public interface Serializer {
    <T extends Validatable> T deserialize(byte[] bArr, Class<T> cls);

    byte[] serialize(Object obj);
}
