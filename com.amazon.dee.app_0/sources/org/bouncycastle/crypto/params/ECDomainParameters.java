package org.bouncycastle.crypto.params;

import com.amazon.android.codahalemetricreporter.JsonReportFormat;
import java.math.BigInteger;
import org.bouncycastle.asn1.x9.X9ECParameters;
import org.bouncycastle.math.ec.ECAlgorithms;
import org.bouncycastle.math.ec.ECConstants;
import org.bouncycastle.math.ec.ECCurve;
import org.bouncycastle.math.ec.ECPoint;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.BigIntegers;
/* loaded from: classes4.dex */
public class ECDomainParameters implements ECConstants {
    private final ECPoint G;
    private final ECCurve curve;
    private final BigInteger h;
    private BigInteger hInv;
    private final BigInteger n;
    private final byte[] seed;

    public ECDomainParameters(X9ECParameters x9ECParameters) {
        this(x9ECParameters.getCurve(), x9ECParameters.getG(), x9ECParameters.getN(), x9ECParameters.getH(), x9ECParameters.getSeed());
    }

    public ECDomainParameters(ECCurve eCCurve, ECPoint eCPoint, BigInteger bigInteger) {
        this(eCCurve, eCPoint, bigInteger, ECConstants.ONE, null);
    }

    public ECDomainParameters(ECCurve eCCurve, ECPoint eCPoint, BigInteger bigInteger, BigInteger bigInteger2) {
        this(eCCurve, eCPoint, bigInteger, bigInteger2, null);
    }

    public ECDomainParameters(ECCurve eCCurve, ECPoint eCPoint, BigInteger bigInteger, BigInteger bigInteger2, byte[] bArr) {
        this.hInv = null;
        if (eCCurve != null) {
            if (bigInteger == null) {
                throw new NullPointerException(JsonReportFormat.COUNT);
            }
            this.curve = eCCurve;
            this.G = validatePublicPoint(eCCurve, eCPoint);
            this.n = bigInteger;
            this.h = bigInteger2;
            this.seed = Arrays.clone(bArr);
            return;
        }
        throw new NullPointerException("curve");
    }

    static ECPoint validatePublicPoint(ECCurve eCCurve, ECPoint eCPoint) {
        if (eCPoint != null) {
            ECPoint normalize = ECAlgorithms.importPoint(eCCurve, eCPoint).normalize();
            if (normalize.isInfinity()) {
                throw new IllegalArgumentException("Point at infinity");
            }
            if (!normalize.isValid()) {
                throw new IllegalArgumentException("Point not on curve");
            }
            return normalize;
        }
        throw new NullPointerException("Point cannot be null");
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ECDomainParameters)) {
            return false;
        }
        ECDomainParameters eCDomainParameters = (ECDomainParameters) obj;
        return this.curve.equals(eCDomainParameters.curve) && this.G.equals(eCDomainParameters.G) && this.n.equals(eCDomainParameters.n);
    }

    public ECCurve getCurve() {
        return this.curve;
    }

    public ECPoint getG() {
        return this.G;
    }

    public BigInteger getH() {
        return this.h;
    }

    public synchronized BigInteger getHInv() {
        if (this.hInv == null) {
            this.hInv = BigIntegers.modOddInverseVar(this.n, this.h);
        }
        return this.hInv;
    }

    public BigInteger getN() {
        return this.n;
    }

    public byte[] getSeed() {
        return Arrays.clone(this.seed);
    }

    public int hashCode() {
        return ((((this.curve.hashCode() ^ 1028) * 257) ^ this.G.hashCode()) * 257) ^ this.n.hashCode();
    }

    public BigInteger validatePrivateScalar(BigInteger bigInteger) {
        if (bigInteger != null) {
            if (bigInteger.compareTo(ECConstants.ONE) >= 0 && bigInteger.compareTo(getN()) < 0) {
                return bigInteger;
            }
            throw new IllegalArgumentException("Scalar is not in the interval [1, n - 1]");
        }
        throw new NullPointerException("Scalar cannot be null");
    }

    public ECPoint validatePublicPoint(ECPoint eCPoint) {
        return validatePublicPoint(getCurve(), eCPoint);
    }
}
