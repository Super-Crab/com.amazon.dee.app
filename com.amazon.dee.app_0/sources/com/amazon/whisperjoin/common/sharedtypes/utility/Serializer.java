package com.amazon.whisperjoin.common.sharedtypes.utility;

import com.google.common.reflect.TypeToken;
/* loaded from: classes13.dex */
public interface Serializer {
    <T> T deserialize(byte[] bArr, TypeToken<T> typeToken);

    <T> byte[] serialize(T t);
}
