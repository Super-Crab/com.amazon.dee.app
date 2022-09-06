package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
@GwtCompatible
/* loaded from: classes3.dex */
final class Hashing {
    private static final long C1 = -862048943;
    private static final long C2 = 461845907;
    private static final int MAX_TABLE_SIZE = 1073741824;

    private Hashing() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int closedTableSize(int i, double d) {
        int max = Math.max(i, 2);
        int highestOneBit = Integer.highestOneBit(max);
        if (max > ((int) (d * highestOneBit))) {
            int i2 = highestOneBit << 1;
            if (i2 <= 0) {
                return 1073741824;
            }
            return i2;
        }
        return highestOneBit;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean needsResizing(int i, int i2, double d) {
        return ((double) i) > d * ((double) i2) && i2 < 1073741824;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int smear(int i) {
        return (int) (Integer.rotateLeft((int) (i * C1), 15) * C2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int smearedHash(@NullableDecl Object obj) {
        return smear(obj == null ? 0 : obj.hashCode());
    }
}
