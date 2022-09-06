package org.bouncycastle.tls.crypto.impl.jcajce;

import com.amazon.whispercloak.KeyUtils;
import java.math.BigInteger;
import java.security.AlgorithmParameters;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.interfaces.ECKey;
import java.security.interfaces.ECPrivateKey;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.ECField;
import java.security.spec.ECFieldF2m;
import java.security.spec.ECFieldFp;
import java.security.spec.ECGenParameterSpec;
import java.security.spec.ECParameterSpec;
import java.security.spec.EllipticCurve;
import org.bouncycastle.math.ec.ECCurve;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes5.dex */
public class ECUtil {
    ECUtil() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ECCurve convertCurve(EllipticCurve ellipticCurve, BigInteger bigInteger, int i) {
        ECField field = ellipticCurve.getField();
        BigInteger a = ellipticCurve.getA();
        BigInteger b = ellipticCurve.getB();
        if (field instanceof ECFieldFp) {
            return new ECCurve.Fp(((ECFieldFp) field).getP(), a, b, bigInteger, BigInteger.valueOf(i));
        }
        ECFieldF2m eCFieldF2m = (ECFieldF2m) field;
        int m = eCFieldF2m.getM();
        int[] convertMidTerms = convertMidTerms(eCFieldF2m.getMidTermsOfReductionPolynomial());
        return new ECCurve.F2m(m, convertMidTerms[0], convertMidTerms[1], convertMidTerms[2], a, b, bigInteger, BigInteger.valueOf(i));
    }

    static int[] convertMidTerms(int[] iArr) {
        int[] iArr2 = new int[3];
        if (iArr.length == 1) {
            iArr2[0] = iArr[0];
        } else if (iArr.length != 3) {
            throw new IllegalArgumentException("Only Trinomials and pentanomials supported");
        } else {
            if (iArr[0] < iArr[1] && iArr[0] < iArr[2]) {
                iArr2[0] = iArr[0];
                if (iArr[1] < iArr[2]) {
                    iArr2[1] = iArr[1];
                    iArr2[2] = iArr[2];
                } else {
                    iArr2[1] = iArr[2];
                    iArr2[2] = iArr[1];
                }
            } else if (iArr[1] < iArr[2]) {
                iArr2[0] = iArr[1];
                if (iArr[0] < iArr[2]) {
                    iArr2[1] = iArr[0];
                    iArr2[2] = iArr[2];
                } else {
                    iArr2[1] = iArr[2];
                    iArr2[2] = iArr[0];
                }
            } else {
                iArr2[0] = iArr[2];
                if (iArr[0] < iArr[1]) {
                    iArr2[1] = iArr[0];
                    iArr2[2] = iArr[1];
                } else {
                    iArr2[1] = iArr[1];
                    iArr2[2] = iArr[0];
                }
            }
        }
        return iArr2;
    }

    static AlgorithmParameterSpec createInitSpec(String str) {
        return new ECGenParameterSpec(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static AlgorithmParameters getAlgorithmParameters(JcaTlsCrypto jcaTlsCrypto, String str) {
        return getAlgorithmParameters(jcaTlsCrypto, new ECGenParameterSpec(str));
    }

    static AlgorithmParameters getAlgorithmParameters(JcaTlsCrypto jcaTlsCrypto, AlgorithmParameterSpec algorithmParameterSpec) {
        try {
            AlgorithmParameters createAlgorithmParameters = jcaTlsCrypto.getHelper().createAlgorithmParameters(KeyUtils.ALGORITHM_EC);
            createAlgorithmParameters.init(algorithmParameterSpec);
            if (((ECParameterSpec) createAlgorithmParameters.getParameterSpec(ECParameterSpec.class)) == null) {
                return null;
            }
            return createAlgorithmParameters;
        } catch (Exception unused) {
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ECParameterSpec getECParameterSpec(JcaTlsCrypto jcaTlsCrypto, String str) {
        return getECParameterSpec(jcaTlsCrypto, createInitSpec(str));
    }

    static ECParameterSpec getECParameterSpec(JcaTlsCrypto jcaTlsCrypto, AlgorithmParameterSpec algorithmParameterSpec) {
        try {
            AlgorithmParameters createAlgorithmParameters = jcaTlsCrypto.getHelper().createAlgorithmParameters(KeyUtils.ALGORITHM_EC);
            createAlgorithmParameters.init(algorithmParameterSpec);
            ECParameterSpec eCParameterSpec = (ECParameterSpec) createAlgorithmParameters.getParameterSpec(ECParameterSpec.class);
            if (eCParameterSpec != null) {
                return eCParameterSpec;
            }
        } catch (Exception unused) {
        }
        try {
            KeyPairGenerator createKeyPairGenerator = jcaTlsCrypto.getHelper().createKeyPairGenerator(KeyUtils.ALGORITHM_EC);
            createKeyPairGenerator.initialize(algorithmParameterSpec, jcaTlsCrypto.getSecureRandom());
            return ((ECKey) createKeyPairGenerator.generateKeyPair().getPrivate()).getParams();
        } catch (Exception unused2) {
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isCurveSupported(JcaTlsCrypto jcaTlsCrypto, String str) {
        return isCurveSupported(jcaTlsCrypto, new ECGenParameterSpec(str));
    }

    static boolean isCurveSupported(JcaTlsCrypto jcaTlsCrypto, ECGenParameterSpec eCGenParameterSpec) {
        return getECParameterSpec(jcaTlsCrypto, eCGenParameterSpec) != null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isECPrivateKey(PrivateKey privateKey) {
        return (privateKey instanceof ECPrivateKey) || KeyUtils.ALGORITHM_EC.equalsIgnoreCase(privateKey.getAlgorithm());
    }
}
