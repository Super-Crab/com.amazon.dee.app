package com.amazon.imageutilities;

import com.google.common.base.Strings;
import java.math.BigInteger;
import javax.annotation.Nonnull;
/* loaded from: classes12.dex */
public class StringDigestUtility {
    static final int HEX_DIGIT_COUNT = 32;
    static final String HEX_FORMAT = "%x";

    private String getDigestWithPrependedZeros(@Nonnull String str) {
        return str.length() < 32 ? Strings.padStart(str, 32, '0') : str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String getHexEncodedDigest(@Nonnull byte[] bArr) {
        return getDigestWithPrependedZeros(String.format(HEX_FORMAT, new BigInteger(1, bArr)));
    }
}
