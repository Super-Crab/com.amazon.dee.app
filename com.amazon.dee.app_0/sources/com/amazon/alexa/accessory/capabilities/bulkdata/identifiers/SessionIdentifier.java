package com.amazon.alexa.accessory.capabilities.bulkdata.identifiers;

import com.amazon.alexa.accessory.capabilities.bulkdata.BulkDataUtils;
import java.util.Arrays;
/* loaded from: classes.dex */
public class SessionIdentifier {
    public static final int PROTOCOL_MAX_BYTES = 16;
    private final byte[] sessionIdentifier;

    public SessionIdentifier(byte[] bArr) {
        this.sessionIdentifier = bArr;
    }

    public byte[] getBytes() {
        byte[] bArr = this.sessionIdentifier;
        return Arrays.copyOf(bArr, bArr.length);
    }

    public String toString() {
        return BulkDataUtils.toHexString(this.sessionIdentifier, 16);
    }
}
