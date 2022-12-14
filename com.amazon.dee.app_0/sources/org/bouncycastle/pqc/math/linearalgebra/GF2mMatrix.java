package org.bouncycastle.pqc.math.linearalgebra;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.lang.reflect.Array;
/* loaded from: classes5.dex */
public class GF2mMatrix extends Matrix {
    protected GF2mField field;
    protected int[][] matrix;

    public GF2mMatrix(GF2mField gF2mField, byte[] bArr) {
        this.field = gF2mField;
        int i = 8;
        int i2 = 1;
        while (gF2mField.getDegree() > i) {
            i2++;
            i += 8;
        }
        if (bArr.length >= 5) {
            this.numRows = ((((bArr[3] & 255) << 24) ^ ((bArr[2] & 255) << 16)) ^ ((bArr[1] & 255) << 8)) ^ (bArr[0] & 255);
            int i3 = this.numRows;
            int i4 = i2 * i3;
            if (i3 > 0) {
                int i5 = 4;
                if ((bArr.length - 4) % i4 == 0) {
                    this.numColumns = (bArr.length - 4) / i4;
                    this.matrix = (int[][]) Array.newInstance(int.class, i3, this.numColumns);
                    for (int i6 = 0; i6 < this.numRows; i6++) {
                        for (int i7 = 0; i7 < this.numColumns; i7++) {
                            int i8 = 0;
                            while (i8 < i) {
                                int[] iArr = this.matrix[i6];
                                iArr[i7] = ((bArr[i5] & 255) << i8) ^ iArr[i7];
                                i8 += 8;
                                i5++;
                            }
                            if (!this.field.isElementOfThisField(this.matrix[i6][i7])) {
                                throw new IllegalArgumentException(" Error: given array is not encoded matrix over GF(2^m)");
                            }
                        }
                    }
                    return;
                }
            }
            throw new IllegalArgumentException(" Error: given array is not encoded matrix over GF(2^m)");
        }
        throw new IllegalArgumentException(" Error: given array is not encoded matrix over GF(2^m)");
    }

    protected GF2mMatrix(GF2mField gF2mField, int[][] iArr) {
        this.field = gF2mField;
        this.matrix = iArr;
        this.numRows = iArr.length;
        this.numColumns = iArr[0].length;
    }

    public GF2mMatrix(GF2mMatrix gF2mMatrix) {
        this.numRows = gF2mMatrix.numRows;
        this.numColumns = gF2mMatrix.numColumns;
        this.field = gF2mMatrix.field;
        this.matrix = new int[this.numRows];
        for (int i = 0; i < this.numRows; i++) {
            this.matrix[i] = IntUtils.clone(gF2mMatrix.matrix[i]);
        }
    }

    private void addToRow(int[] iArr, int[] iArr2) {
        for (int length = iArr2.length - 1; length >= 0; length--) {
            iArr2[length] = this.field.add(iArr[length], iArr2[length]);
        }
    }

    private int[] multRowWithElement(int[] iArr, int i) {
        int[] iArr2 = new int[iArr.length];
        for (int length = iArr.length - 1; length >= 0; length--) {
            iArr2[length] = this.field.mult(iArr[length], i);
        }
        return iArr2;
    }

    private void multRowWithElementThis(int[] iArr, int i) {
        for (int length = iArr.length - 1; length >= 0; length--) {
            iArr[length] = this.field.mult(iArr[length], i);
        }
    }

    private static void swapColumns(int[][] iArr, int i, int i2) {
        int[] iArr2 = iArr[i];
        iArr[i] = iArr[i2];
        iArr[i2] = iArr2;
    }

    @Override // org.bouncycastle.pqc.math.linearalgebra.Matrix
    public Matrix computeInverse() {
        int i;
        int i2 = this.numRows;
        if (i2 == this.numColumns) {
            int[][] iArr = (int[][]) Array.newInstance(int.class, i2, i2);
            for (int i3 = this.numRows - 1; i3 >= 0; i3--) {
                iArr[i3] = IntUtils.clone(this.matrix[i3]);
            }
            int i4 = this.numRows;
            int[][] iArr2 = (int[][]) Array.newInstance(int.class, i4, i4);
            for (int i5 = this.numRows - 1; i5 >= 0; i5--) {
                iArr2[i5][i5] = 1;
            }
            for (int i6 = 0; i6 < this.numRows; i6++) {
                if (iArr[i6][i6] == 0) {
                    int i7 = i6 + 1;
                    boolean z = false;
                    while (i7 < this.numRows) {
                        if (iArr[i7][i6] != 0) {
                            swapColumns(iArr, i6, i7);
                            swapColumns(iArr2, i6, i7);
                            i7 = this.numRows;
                            z = true;
                        }
                        i7++;
                    }
                    if (!z) {
                        throw new ArithmeticException("Matrix is not invertible.");
                    }
                }
                int inverse = this.field.inverse(iArr[i6][i6]);
                multRowWithElementThis(iArr[i6], inverse);
                multRowWithElementThis(iArr2[i6], inverse);
                for (int i8 = 0; i8 < this.numRows; i8++) {
                    if (i8 != i6 && (i = iArr[i8][i6]) != 0) {
                        int[] multRowWithElement = multRowWithElement(iArr[i6], i);
                        int[] multRowWithElement2 = multRowWithElement(iArr2[i6], i);
                        addToRow(multRowWithElement, iArr[i8]);
                        addToRow(multRowWithElement2, iArr2[i8]);
                    }
                }
            }
            return new GF2mMatrix(this.field, iArr2);
        }
        throw new ArithmeticException("Matrix is not invertible.");
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof GF2mMatrix)) {
            GF2mMatrix gF2mMatrix = (GF2mMatrix) obj;
            if (this.field.equals(gF2mMatrix.field)) {
                int i = gF2mMatrix.numRows;
                int i2 = this.numColumns;
                if (i == i2 && gF2mMatrix.numColumns == i2) {
                    for (int i3 = 0; i3 < this.numRows; i3++) {
                        for (int i4 = 0; i4 < this.numColumns; i4++) {
                            if (this.matrix[i3][i4] != gF2mMatrix.matrix[i3][i4]) {
                                return false;
                            }
                        }
                    }
                    return true;
                }
            }
        }
        return false;
    }

    @Override // org.bouncycastle.pqc.math.linearalgebra.Matrix
    public byte[] getEncoded() {
        int i = 8;
        int i2 = 1;
        while (this.field.getDegree() > i) {
            i2++;
            i += 8;
        }
        int i3 = this.numRows;
        int i4 = this.numColumns * i3 * i2;
        int i5 = 4;
        byte[] bArr = new byte[i4 + 4];
        bArr[0] = (byte) (i3 & 255);
        bArr[1] = (byte) ((i3 >>> 8) & 255);
        bArr[2] = (byte) ((i3 >>> 16) & 255);
        bArr[3] = (byte) ((i3 >>> 24) & 255);
        int i6 = 0;
        while (i6 < this.numRows) {
            int i7 = i5;
            int i8 = 0;
            while (i8 < this.numColumns) {
                int i9 = i7;
                int i10 = 0;
                while (i10 < i) {
                    bArr[i9] = (byte) (this.matrix[i6][i8] >>> i10);
                    i10 += 8;
                    i9++;
                }
                i8++;
                i7 = i9;
            }
            i6++;
            i5 = i7;
        }
        return bArr;
    }

    public int hashCode() {
        int hashCode = (((this.field.hashCode() * 31) + this.numRows) * 31) + this.numColumns;
        int i = 0;
        while (i < this.numRows) {
            int i2 = hashCode;
            for (int i3 = 0; i3 < this.numColumns; i3++) {
                i2 = (i2 * 31) + this.matrix[i][i3];
            }
            i++;
            hashCode = i2;
        }
        return hashCode;
    }

    @Override // org.bouncycastle.pqc.math.linearalgebra.Matrix
    public boolean isZero() {
        for (int i = 0; i < this.numRows; i++) {
            for (int i2 = 0; i2 < this.numColumns; i2++) {
                if (this.matrix[i][i2] != 0) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override // org.bouncycastle.pqc.math.linearalgebra.Matrix
    public Vector leftMultiply(Vector vector) {
        throw new RuntimeException("Not implemented.");
    }

    @Override // org.bouncycastle.pqc.math.linearalgebra.Matrix
    public Matrix rightMultiply(Matrix matrix) {
        throw new RuntimeException("Not implemented.");
    }

    @Override // org.bouncycastle.pqc.math.linearalgebra.Matrix
    public Matrix rightMultiply(Permutation permutation) {
        throw new RuntimeException("Not implemented.");
    }

    @Override // org.bouncycastle.pqc.math.linearalgebra.Matrix
    public Vector rightMultiply(Vector vector) {
        throw new RuntimeException("Not implemented.");
    }

    @Override // org.bouncycastle.pqc.math.linearalgebra.Matrix
    public String toString() {
        String str = this.numRows + " x " + this.numColumns + " Matrix over " + this.field.toString() + ": \n";
        for (int i = 0; i < this.numRows; i++) {
            String str2 = str;
            for (int i2 = 0; i2 < this.numColumns; i2++) {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107(str2);
                outline107.append(this.field.elementToStr(this.matrix[i][i2]));
                outline107.append(" : ");
                str2 = outline107.toString();
            }
            str = GeneratedOutlineSupport1.outline72(str2, "\n");
        }
        return str;
    }
}
