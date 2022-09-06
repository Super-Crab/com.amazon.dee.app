package org.bouncycastle.its.asn1;

import org.bouncycastle.util.Arrays;
/* loaded from: classes4.dex */
class Utils {
    Utils() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static byte[] octetStringFixed(byte[] bArr) {
        if (bArr.length < 1 || bArr.length > 32) {
            throw new IllegalArgumentException("octet string out of range");
        }
        return Arrays.clone(bArr);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static byte[] octetStringFixed(byte[] bArr, int i) {
        if (bArr.length == i) {
            return bArr;
        }
        throw new IllegalArgumentException("octet string out of range");
    }
}
