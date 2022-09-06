package com.amazon.devicesetup.serializer;

import com.google.common.reflect.TypeToken;
/* loaded from: classes12.dex */
public interface Serializer {
    <T> T deserialize(byte[] bArr, TypeToken<T> typeToken);

    <T> byte[] serialize(T t);
}
