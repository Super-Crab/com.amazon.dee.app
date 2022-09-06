package org.bouncycastle.operator.bc;

import java.security.Key;
import org.bouncycastle.operator.GenericKey;
/* loaded from: classes5.dex */
class OperatorUtils {
    OperatorUtils() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static byte[] getKeyBytes(GenericKey genericKey) {
        if (genericKey.getRepresentation() instanceof Key) {
            return ((Key) genericKey.getRepresentation()).getEncoded();
        }
        if (!(genericKey.getRepresentation() instanceof byte[])) {
            throw new IllegalArgumentException("unknown generic key type");
        }
        return (byte[]) genericKey.getRepresentation();
    }
}
