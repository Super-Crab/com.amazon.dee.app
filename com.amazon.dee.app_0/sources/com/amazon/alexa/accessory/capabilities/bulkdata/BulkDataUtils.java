package com.amazon.alexa.accessory.capabilities.bulkdata;

import com.google.common.io.BaseEncoding;
import com.google.protobuf.ByteString;
/* loaded from: classes.dex */
public final class BulkDataUtils {
    private BulkDataUtils() {
        throw new IllegalStateException("No instances!");
    }

    public static String toHexString(ByteString byteString, int i) {
        return byteString == null ? "" : toHexString(byteString.toByteArray(), i);
    }

    public static String toHexString(byte[] bArr, int i) {
        return (bArr == null || i <= 0) ? "" : BaseEncoding.base16().upperCase().encode(bArr, 0, Math.min(bArr.length, i));
    }
}
