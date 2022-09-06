package org.bouncycastle.operator.jcajce;

import java.security.Key;
import javax.crypto.spec.SecretKeySpec;
import org.bouncycastle.operator.GenericKey;
/* loaded from: classes5.dex */
class OperatorUtils {
    OperatorUtils() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Key getJceKey(GenericKey genericKey) {
        if (genericKey.getRepresentation() instanceof Key) {
            return (Key) genericKey.getRepresentation();
        }
        if (!(genericKey.getRepresentation() instanceof byte[])) {
            throw new IllegalArgumentException("unknown generic key type");
        }
        return new SecretKeySpec((byte[]) genericKey.getRepresentation(), "ENC");
    }
}
