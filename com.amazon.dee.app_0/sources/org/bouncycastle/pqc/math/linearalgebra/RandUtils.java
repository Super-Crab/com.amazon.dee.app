package org.bouncycastle.pqc.math.linearalgebra;

import java.security.SecureRandom;
/* loaded from: classes5.dex */
public class RandUtils {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static int nextInt(SecureRandom secureRandom, int i) {
        int nextInt;
        int i2;
        if (((-i) & i) == i) {
            return (int) ((i * (secureRandom.nextInt() >>> 1)) >> 31);
        }
        do {
            nextInt = secureRandom.nextInt() >>> 1;
            i2 = nextInt % i;
        } while ((i - 1) + (nextInt - i2) < 0);
        return i2;
    }
}
