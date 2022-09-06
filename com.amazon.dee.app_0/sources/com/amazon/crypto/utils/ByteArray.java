package com.amazon.crypto.utils;

import androidx.annotation.NonNull;
import java.security.SecureRandom;
import java.util.Random;
/* loaded from: classes12.dex */
public final class ByteArray {
    private static final Random random = new SecureRandom();

    private ByteArray() {
        UtilityInstanceAttempt.in(this);
    }

    @NonNull
    public static byte[] random(int i) throws IllegalArgumentException {
        boolean z = true;
        boolean[] zArr = new boolean[1];
        if (i < 0) {
            z = false;
        }
        zArr[0] = z;
        Preconditions.isTrue(zArr);
        byte[] bArr = new byte[i];
        random.nextBytes(bArr);
        return bArr;
    }
}
