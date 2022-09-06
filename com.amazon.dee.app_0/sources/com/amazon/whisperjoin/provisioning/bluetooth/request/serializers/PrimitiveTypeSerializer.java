package com.amazon.whisperjoin.provisioning.bluetooth.request.serializers;

import java.util.Locale;
/* loaded from: classes13.dex */
public class PrimitiveTypeSerializer {
    public <T> T deserialize(byte[] bArr, Class<T> cls) {
        if (Byte.class.equals(cls)) {
            if (bArr.length == 1) {
                return (T) Byte.valueOf(bArr[0]);
            }
            throw new UnparsableDataException(String.format(Locale.ENGLISH, "Invalid byte input. Expected single byte input. Actual number of bytes=%d", Integer.valueOf(bArr.length)));
        }
        throw new UnparsableDataException("Unsupported primitive type");
    }

    public byte[] serialize(Object obj) {
        throw new UnsupportedOperationException("Not required");
    }
}
