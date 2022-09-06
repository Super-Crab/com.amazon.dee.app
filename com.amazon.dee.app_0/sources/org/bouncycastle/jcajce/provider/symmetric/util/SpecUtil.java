package org.bouncycastle.jcajce.provider.symmetric.util;

import java.security.AlgorithmParameters;
import java.security.spec.AlgorithmParameterSpec;
/* loaded from: classes4.dex */
class SpecUtil {
    SpecUtil() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static AlgorithmParameterSpec extractSpec(AlgorithmParameters algorithmParameters, Class[] clsArr) {
        try {
            return algorithmParameters.getParameterSpec(AlgorithmParameterSpec.class);
        } catch (Exception unused) {
            for (int i = 0; i != clsArr.length; i++) {
                if (clsArr[i] != null) {
                    try {
                        return algorithmParameters.getParameterSpec(clsArr[i]);
                    } catch (Exception unused2) {
                    }
                }
            }
            return null;
        }
    }
}
