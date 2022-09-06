package com.amazon.alexa.accessory.capabilities.bulkdata.identifiers;

import com.amazon.alexa.accessory.capabilities.bulkdata.BulkDataUtils;
import java.util.Arrays;
/* loaded from: classes.dex */
public class DataIdentifier {
    public static final int PROTOCOL_MAX_BYTES = 16;
    private final byte[] dataIdentifier;

    public DataIdentifier(byte[] bArr) {
        this.dataIdentifier = bArr;
    }

    public byte[] getBytes() {
        byte[] bArr = this.dataIdentifier;
        return Arrays.copyOf(bArr, bArr.length);
    }

    public String toString() {
        return BulkDataUtils.toHexString(this.dataIdentifier, 16);
    }
}
