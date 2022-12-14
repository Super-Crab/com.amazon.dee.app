package org.bouncycastle.pqc.math.linearalgebra;

import java.security.SecureRandom;
import org.apache.logging.log4j.util.Chars;
import org.bouncycastle.util.Arrays;
/* loaded from: classes5.dex */
public class GF2Vector extends Vector {
    private int[] v;

    public GF2Vector(int i) {
        if (i >= 0) {
            this.length = i;
            this.v = new int[(i + 31) >> 5];
            return;
        }
        throw new ArithmeticException("Negative length.");
    }

    public GF2Vector(int i, int i2, SecureRandom secureRandom) {
        if (i2 <= i) {
            this.length = i;
            this.v = new int[(i + 31) >> 5];
            int[] iArr = new int[i];
            for (int i3 = 0; i3 < i; i3++) {
                iArr[i3] = i3;
            }
            for (int i4 = 0; i4 < i2; i4++) {
                int nextInt = RandUtils.nextInt(secureRandom, i);
                setBit(iArr[nextInt]);
                i--;
                iArr[nextInt] = iArr[i];
            }
            return;
        }
        throw new ArithmeticException("The hamming weight is greater than the length of vector.");
    }

    public GF2Vector(int i, SecureRandom secureRandom) {
        this.length = i;
        int i2 = (i + 31) >> 5;
        this.v = new int[i2];
        int i3 = i2 - 1;
        for (int i4 = i3; i4 >= 0; i4--) {
            this.v[i4] = secureRandom.nextInt();
        }
        int i5 = i & 31;
        if (i5 != 0) {
            int[] iArr = this.v;
            iArr[i3] = ((1 << i5) - 1) & iArr[i3];
        }
    }

    public GF2Vector(int i, int[] iArr) {
        if (i >= 0) {
            this.length = i;
            int i2 = (i + 31) >> 5;
            if (iArr.length != i2) {
                throw new ArithmeticException("length mismatch");
            }
            this.v = IntUtils.clone(iArr);
            int i3 = i & 31;
            if (i3 == 0) {
                return;
            }
            int[] iArr2 = this.v;
            int i4 = i2 - 1;
            iArr2[i4] = ((1 << i3) - 1) & iArr2[i4];
            return;
        }
        throw new ArithmeticException("negative length");
    }

    public GF2Vector(GF2Vector gF2Vector) {
        this.length = gF2Vector.length;
        this.v = IntUtils.clone(gF2Vector.v);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public GF2Vector(int[] iArr, int i) {
        this.v = iArr;
        this.length = i;
    }

    public static GF2Vector OS2VP(int i, byte[] bArr) {
        if (i >= 0) {
            if (bArr.length > ((i + 7) >> 3)) {
                throw new ArithmeticException("length mismatch");
            }
            return new GF2Vector(i, LittleEndianConversions.toIntArray(bArr));
        }
        throw new ArithmeticException("negative length");
    }

    @Override // org.bouncycastle.pqc.math.linearalgebra.Vector
    public Vector add(Vector vector) {
        if (vector instanceof GF2Vector) {
            GF2Vector gF2Vector = (GF2Vector) vector;
            if (this.length != gF2Vector.length) {
                throw new ArithmeticException("length mismatch");
            }
            int[] clone = IntUtils.clone(gF2Vector.v);
            for (int length = clone.length - 1; length >= 0; length--) {
                clone[length] = clone[length] ^ this.v[length];
            }
            return new GF2Vector(this.length, clone);
        }
        throw new ArithmeticException("vector is not defined over GF(2)");
    }

    @Override // org.bouncycastle.pqc.math.linearalgebra.Vector
    public boolean equals(Object obj) {
        if (!(obj instanceof GF2Vector)) {
            return false;
        }
        GF2Vector gF2Vector = (GF2Vector) obj;
        return this.length == gF2Vector.length && IntUtils.equals(this.v, gF2Vector.v);
    }

    public GF2Vector extractLeftVector(int i) {
        int i2 = this.length;
        if (i <= i2) {
            if (i == i2) {
                return new GF2Vector(this);
            }
            GF2Vector gF2Vector = new GF2Vector(i);
            int i3 = i >> 5;
            int i4 = i & 31;
            System.arraycopy(this.v, 0, gF2Vector.v, 0, i3);
            if (i4 != 0) {
                gF2Vector.v[i3] = ((1 << i4) - 1) & this.v[i3];
            }
            return gF2Vector;
        }
        throw new ArithmeticException("invalid length");
    }

    public GF2Vector extractRightVector(int i) {
        int i2;
        int i3 = this.length;
        if (i <= i3) {
            if (i == i3) {
                return new GF2Vector(this);
            }
            GF2Vector gF2Vector = new GF2Vector(i);
            int i4 = this.length;
            int i5 = (i4 - i) >> 5;
            int i6 = (i4 - i) & 31;
            int i7 = (i + 31) >> 5;
            int i8 = 0;
            if (i6 != 0) {
                while (true) {
                    i2 = i7 - 1;
                    if (i8 >= i2) {
                        break;
                    }
                    int[] iArr = gF2Vector.v;
                    int[] iArr2 = this.v;
                    int i9 = i5 + 1;
                    iArr[i8] = (iArr2[i5] >>> i6) | (iArr2[i9] << (32 - i6));
                    i8++;
                    i5 = i9;
                }
                int[] iArr3 = gF2Vector.v;
                int[] iArr4 = this.v;
                int i10 = i5 + 1;
                iArr3[i2] = iArr4[i5] >>> i6;
                if (i10 < iArr4.length) {
                    iArr3[i2] = (iArr4[i10] << (32 - i6)) | iArr3[i2];
                }
            } else {
                System.arraycopy(this.v, i5, gF2Vector.v, 0, i7);
            }
            return gF2Vector;
        }
        throw new ArithmeticException("invalid length");
    }

    public GF2Vector extractVector(int[] iArr) {
        int length = iArr.length;
        if (iArr[length - 1] <= this.length) {
            GF2Vector gF2Vector = new GF2Vector(length);
            for (int i = 0; i < length; i++) {
                if ((this.v[iArr[i] >> 5] & (1 << (iArr[i] & 31))) != 0) {
                    int[] iArr2 = gF2Vector.v;
                    int i2 = i >> 5;
                    iArr2[i2] = (1 << (i & 31)) | iArr2[i2];
                }
            }
            return gF2Vector;
        }
        throw new ArithmeticException("invalid index set");
    }

    public int getBit(int i) {
        if (i < this.length) {
            int i2 = i >> 5;
            int i3 = i & 31;
            return (this.v[i2] & (1 << i3)) >>> i3;
        }
        throw new IndexOutOfBoundsException();
    }

    @Override // org.bouncycastle.pqc.math.linearalgebra.Vector
    public byte[] getEncoded() {
        return LittleEndianConversions.toByteArray(this.v, (this.length + 7) >> 3);
    }

    public int getHammingWeight() {
        int i = 0;
        int i2 = 0;
        while (true) {
            int[] iArr = this.v;
            if (i < iArr.length) {
                int i3 = iArr[i];
                int i4 = i2;
                for (int i5 = 0; i5 < 32; i5++) {
                    if ((i3 & 1) != 0) {
                        i4++;
                    }
                    i3 >>>= 1;
                }
                i++;
                i2 = i4;
            } else {
                return i2;
            }
        }
    }

    public int[] getVecArray() {
        return this.v;
    }

    @Override // org.bouncycastle.pqc.math.linearalgebra.Vector
    public int hashCode() {
        return Arrays.hashCode(this.v) + (this.length * 31);
    }

    @Override // org.bouncycastle.pqc.math.linearalgebra.Vector
    public boolean isZero() {
        for (int length = this.v.length - 1; length >= 0; length--) {
            if (this.v[length] != 0) {
                return false;
            }
        }
        return true;
    }

    @Override // org.bouncycastle.pqc.math.linearalgebra.Vector
    public Vector multiply(Permutation permutation) {
        int[] vector = permutation.getVector();
        int i = this.length;
        if (i == vector.length) {
            GF2Vector gF2Vector = new GF2Vector(i);
            for (int i2 = 0; i2 < vector.length; i2++) {
                if ((this.v[vector[i2] >> 5] & (1 << (vector[i2] & 31))) != 0) {
                    int[] iArr = gF2Vector.v;
                    int i3 = i2 >> 5;
                    iArr[i3] = (1 << (i2 & 31)) | iArr[i3];
                }
            }
            return gF2Vector;
        }
        throw new ArithmeticException("length mismatch");
    }

    public void setBit(int i) {
        if (i < this.length) {
            int[] iArr = this.v;
            int i2 = i >> 5;
            iArr[i2] = (1 << (i & 31)) | iArr[i2];
            return;
        }
        throw new IndexOutOfBoundsException();
    }

    public GF2mVector toExtensionFieldVector(GF2mField gF2mField) {
        int degree = gF2mField.getDegree();
        int i = this.length;
        if (i % degree == 0) {
            int i2 = i / degree;
            int[] iArr = new int[i2];
            int i3 = 0;
            for (int i4 = i2 - 1; i4 >= 0; i4--) {
                for (int degree2 = gF2mField.getDegree() - 1; degree2 >= 0; degree2--) {
                    if (((this.v[i3 >>> 5] >>> (i3 & 31)) & 1) == 1) {
                        iArr[i4] = iArr[i4] ^ (1 << degree2);
                    }
                    i3++;
                }
            }
            return new GF2mVector(gF2mField, iArr);
        }
        throw new ArithmeticException("conversion is impossible");
    }

    @Override // org.bouncycastle.pqc.math.linearalgebra.Vector
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < this.length; i++) {
            if (i != 0 && (i & 31) == 0) {
                stringBuffer.append(Chars.SPACE);
            }
            stringBuffer.append((this.v[i >> 5] & (1 << (i & 31))) == 0 ? '0' : '1');
        }
        return stringBuffer.toString();
    }
}
