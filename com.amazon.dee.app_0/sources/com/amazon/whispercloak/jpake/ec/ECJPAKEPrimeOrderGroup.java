package com.amazon.whispercloak.jpake.ec;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import org.bouncycastle.crypto.agreement.jpake.JPAKEUtil;
import org.bouncycastle.jce.ECNamedCurveTable;
import org.bouncycastle.jce.spec.ECNamedCurveParameterSpec;
import org.bouncycastle.math.ec.ECCurve;
import org.bouncycastle.math.ec.ECPoint;
/* loaded from: classes13.dex */
public class ECJPAKEPrimeOrderGroup {
    private static final Set<String> ALLOWED_CURVE_NAMES = new HashSet(Arrays.asList("prime256v1", "brainpoolp256r1", "brainpoolp384r1", "brainpoolp512r1"));
    private final BigInteger mCoFactor;
    private final ECCurve.Fp mCurve;
    private final ECPoint mG;
    private final BigInteger mN;
    private final BigInteger mQ;

    public ECJPAKEPrimeOrderGroup(String str) {
        JPAKEUtil.validateNotNull(str, "curveName");
        if (ALLOWED_CURVE_NAMES.contains(str)) {
            ECNamedCurveParameterSpec parameterSpec = ECNamedCurveTable.getParameterSpec(str);
            this.mCurve = (ECCurve.Fp) parameterSpec.getCurve();
            this.mQ = this.mCurve.getQ();
            this.mCoFactor = parameterSpec.getH();
            this.mG = parameterSpec.getG();
            this.mN = parameterSpec.getN();
            return;
        }
        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline75("Curve ", str, " not allowed"));
    }

    public BigInteger getCoFactor() {
        return this.mCoFactor;
    }

    public ECCurve.Fp getCurve() {
        return this.mCurve;
    }

    public ECPoint getG() {
        return this.mG;
    }

    public BigInteger getN() {
        return this.mN;
    }

    public BigInteger getQ() {
        return this.mQ;
    }
}
