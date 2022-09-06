package org.bouncycastle.pqc.math.linearalgebra;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.security.SecureRandom;
import org.bouncycastle.crypto.CryptoServicesRegistrar;
/* loaded from: classes5.dex */
public class GF2mField {
    private int degree;
    private int polynomial;

    public GF2mField(int i) {
        this.degree = 0;
        if (i < 32) {
            if (i < 1) {
                throw new IllegalArgumentException(" Error: the degree of field is non-positive ");
            }
            this.degree = i;
            this.polynomial = PolynomialRingGF2.getIrreduciblePolynomial(i);
            return;
        }
        throw new IllegalArgumentException(" Error: the degree of field is too large ");
    }

    public GF2mField(int i, int i2) {
        this.degree = 0;
        if (i == PolynomialRingGF2.degree(i2)) {
            if (!PolynomialRingGF2.isIrreducible(i2)) {
                throw new IllegalArgumentException(" Error: given polynomial is reducible");
            }
            this.degree = i;
            this.polynomial = i2;
            return;
        }
        throw new IllegalArgumentException(" Error: the degree is not correct");
    }

    public GF2mField(GF2mField gF2mField) {
        this.degree = 0;
        this.degree = gF2mField.degree;
        this.polynomial = gF2mField.polynomial;
    }

    public GF2mField(byte[] bArr) {
        this.degree = 0;
        if (bArr.length == 4) {
            this.polynomial = LittleEndianConversions.OS2IP(bArr);
            if (!PolynomialRingGF2.isIrreducible(this.polynomial)) {
                throw new IllegalArgumentException("byte array is not an encoded finite field");
            }
            this.degree = PolynomialRingGF2.degree(this.polynomial);
            return;
        }
        throw new IllegalArgumentException("byte array is not an encoded finite field");
    }

    private static String polyToString(int i) {
        if (i == 0) {
            return "0";
        }
        String str = ((byte) (i & 1)) == 1 ? "1" : "";
        int i2 = i >>> 1;
        int i3 = 1;
        while (i2 != 0) {
            if (((byte) (i2 & 1)) == 1) {
                str = GeneratedOutlineSupport1.outline74(str, "+x^", i3);
            }
            i2 >>>= 1;
            i3++;
        }
        return str;
    }

    public int add(int i, int i2) {
        return i ^ i2;
    }

    public String elementToStr(int i) {
        StringBuilder sb;
        String str;
        String str2 = "";
        for (int i2 = 0; i2 < this.degree; i2++) {
            if ((((byte) i) & 1) == 0) {
                sb = new StringBuilder();
                str = "0";
            } else {
                sb = new StringBuilder();
                str = "1";
            }
            str2 = GeneratedOutlineSupport1.outline91(sb, str, str2);
            i >>>= 1;
        }
        return str2;
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof GF2mField)) {
            GF2mField gF2mField = (GF2mField) obj;
            if (this.degree == gF2mField.degree && this.polynomial == gF2mField.polynomial) {
                return true;
            }
        }
        return false;
    }

    public int exp(int i, int i2) {
        if (i2 == 0) {
            return 1;
        }
        if (i == 0) {
            return 0;
        }
        if (i == 1) {
            return 1;
        }
        if (i2 < 0) {
            i = inverse(i);
            i2 = -i2;
        }
        int i3 = i;
        int i4 = 1;
        while (i2 != 0) {
            if ((i2 & 1) == 1) {
                i4 = mult(i4, i3);
            }
            i3 = mult(i3, i3);
            i2 >>>= 1;
        }
        return i4;
    }

    public int getDegree() {
        return this.degree;
    }

    public byte[] getEncoded() {
        return LittleEndianConversions.I2OSP(this.polynomial);
    }

    public int getPolynomial() {
        return this.polynomial;
    }

    public int getRandomElement(SecureRandom secureRandom) {
        return RandUtils.nextInt(secureRandom, 1 << this.degree);
    }

    public int getRandomNonZeroElement() {
        return getRandomNonZeroElement(CryptoServicesRegistrar.getSecureRandom());
    }

    public int getRandomNonZeroElement(SecureRandom secureRandom) {
        int nextInt = RandUtils.nextInt(secureRandom, 1 << this.degree);
        int i = 0;
        while (nextInt == 0 && i < 1048576) {
            nextInt = RandUtils.nextInt(secureRandom, 1 << this.degree);
            i++;
        }
        if (i == 1048576) {
            return 1;
        }
        return nextInt;
    }

    public int hashCode() {
        return this.polynomial;
    }

    public int inverse(int i) {
        return exp(i, (1 << this.degree) - 2);
    }

    public boolean isElementOfThisField(int i) {
        int i2 = this.degree;
        return i2 == 31 ? i >= 0 : i >= 0 && i < (1 << i2);
    }

    public int mult(int i, int i2) {
        return PolynomialRingGF2.modMultiply(i, i2, this.polynomial);
    }

    public int sqRoot(int i) {
        for (int i2 = 1; i2 < this.degree; i2++) {
            i = mult(i, i);
        }
        return i;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Finite Field GF(2^");
        outline107.append(this.degree);
        outline107.append(") = GF(2)[X]/<");
        return GeneratedOutlineSupport1.outline91(outline107, polyToString(this.polynomial), "> ");
    }
}
