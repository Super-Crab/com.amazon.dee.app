package org.bouncycastle.tls.crypto.impl.jcajce;

import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.MGF1ParameterSpec;
import java.security.spec.PSSParameterSpec;
import org.bouncycastle.jcajce.util.JcaJceHelper;
import org.bouncycastle.tls.HashAlgorithm;
/* loaded from: classes5.dex */
class RSAUtil {
    RSAUtil() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String getDigestSigAlgName(String str) {
        int indexOf = str.indexOf(45);
        if (indexOf <= 0 || str.startsWith("SHA3")) {
            return str;
        }
        return str.substring(0, indexOf) + str.substring(indexOf + 1);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static AlgorithmParameterSpec getPSSParameterSpec(short s, String str, JcaJceHelper jcaJceHelper) {
        return new PSSParameterSpec(str, "MGF1", new MGF1ParameterSpec(str), HashAlgorithm.getOutputSize(s), 1);
    }
}
