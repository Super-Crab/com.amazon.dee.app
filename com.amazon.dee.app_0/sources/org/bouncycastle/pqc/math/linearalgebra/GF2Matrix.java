package org.bouncycastle.pqc.math.linearalgebra;

import com.amazon.deecomms.calling.accessibility.RealTimeTextConstants;
import java.lang.reflect.Array;
import java.security.SecureRandom;
import org.apache.logging.log4j.util.Chars;
import org.bouncycastle.util.Arrays;
/* loaded from: classes5.dex */
public class GF2Matrix extends Matrix {
    private int length;
    private int[][] matrix;

    public GF2Matrix(int i, char c) {
        this(i, c, new SecureRandom());
    }

    public GF2Matrix(int i, char c, SecureRandom secureRandom) {
        if (i > 0) {
            if (c == 'I') {
                assignUnitMatrix(i);
                return;
            } else if (c == 'L') {
                assignRandomLowerTriangularMatrix(i, secureRandom);
                return;
            } else if (c == 'R') {
                assignRandomRegularMatrix(i, secureRandom);
                return;
            } else if (c == 'U') {
                assignRandomUpperTriangularMatrix(i, secureRandom);
                return;
            } else if (c != 'Z') {
                throw new ArithmeticException("Unknown matrix type.");
            } else {
                assignZeroMatrix(i, i);
                return;
            }
        }
        throw new ArithmeticException("Size of matrix is non-positive.");
    }

    private GF2Matrix(int i, int i2) {
        if (i2 <= 0 || i <= 0) {
            throw new ArithmeticException("size of matrix is non-positive");
        }
        assignZeroMatrix(i, i2);
    }

    public GF2Matrix(int i, int[][] iArr) {
        if (iArr[0].length == ((i + 31) >> 5)) {
            this.numColumns = i;
            this.numRows = iArr.length;
            this.length = iArr[0].length;
            int i2 = i & 31;
            int i3 = i2 == 0 ? -1 : (1 << i2) - 1;
            for (int i4 = 0; i4 < this.numRows; i4++) {
                int[] iArr2 = iArr[i4];
                int i5 = this.length - 1;
                iArr2[i5] = iArr2[i5] & i3;
            }
            this.matrix = iArr;
            return;
        }
        throw new ArithmeticException("Int array does not match given number of columns.");
    }

    public GF2Matrix(GF2Matrix gF2Matrix) {
        this.numColumns = gF2Matrix.getNumColumns();
        this.numRows = gF2Matrix.getNumRows();
        this.length = gF2Matrix.length;
        this.matrix = new int[gF2Matrix.matrix.length];
        int i = 0;
        while (true) {
            int[][] iArr = this.matrix;
            if (i < iArr.length) {
                iArr[i] = IntUtils.clone(gF2Matrix.matrix[i]);
                i++;
            } else {
                return;
            }
        }
    }

    public GF2Matrix(byte[] bArr) {
        if (bArr.length >= 9) {
            this.numRows = LittleEndianConversions.OS2IP(bArr, 0);
            this.numColumns = LittleEndianConversions.OS2IP(bArr, 4);
            int i = this.numColumns;
            int i2 = this.numRows;
            int i3 = ((i + 7) >>> 3) * i2;
            if (i2 > 0) {
                int i4 = 8;
                if (i3 == bArr.length - 8) {
                    this.length = (i + 31) >>> 5;
                    this.matrix = (int[][]) Array.newInstance(int.class, i2, this.length);
                    int i5 = this.numColumns;
                    int i6 = i5 >> 5;
                    int i7 = i5 & 31;
                    for (int i8 = 0; i8 < this.numRows; i8++) {
                        int i9 = 0;
                        while (i9 < i6) {
                            this.matrix[i8][i9] = LittleEndianConversions.OS2IP(bArr, i4);
                            i9++;
                            i4 += 4;
                        }
                        int i10 = 0;
                        while (i10 < i7) {
                            int[] iArr = this.matrix[i8];
                            iArr[i6] = ((bArr[i4] & 255) << i10) ^ iArr[i6];
                            i10 += 8;
                            i4++;
                        }
                    }
                    return;
                }
            }
            throw new ArithmeticException("given array is not an encoded matrix over GF(2)");
        }
        throw new ArithmeticException("given array is not an encoded matrix over GF(2)");
    }

    private static void addToRow(int[] iArr, int[] iArr2, int i) {
        for (int length = iArr2.length - 1; length >= i; length--) {
            iArr2[length] = iArr[length] ^ iArr2[length];
        }
    }

    private void assignRandomLowerTriangularMatrix(int i, SecureRandom secureRandom) {
        this.numRows = i;
        this.numColumns = i;
        this.length = (i + 31) >>> 5;
        this.matrix = (int[][]) Array.newInstance(int.class, this.numRows, this.length);
        for (int i2 = 0; i2 < this.numRows; i2++) {
            int i3 = i2 >>> 5;
            int i4 = i2 & 31;
            int i5 = 31 - i4;
            int i6 = 1 << i4;
            for (int i7 = 0; i7 < i3; i7++) {
                this.matrix[i2][i7] = secureRandom.nextInt();
            }
            this.matrix[i2][i3] = i6 | (secureRandom.nextInt() >>> i5);
            while (true) {
                i3++;
                if (i3 < this.length) {
                    this.matrix[i2][i3] = 0;
                }
            }
        }
    }

    private void assignRandomRegularMatrix(int i, SecureRandom secureRandom) {
        this.numRows = i;
        this.numColumns = i;
        this.length = (i + 31) >>> 5;
        this.matrix = (int[][]) Array.newInstance(int.class, this.numRows, this.length);
        GF2Matrix gF2Matrix = (GF2Matrix) new GF2Matrix(i, Matrix.MATRIX_TYPE_RANDOM_LT, secureRandom).rightMultiply(new GF2Matrix(i, Matrix.MATRIX_TYPE_RANDOM_UT, secureRandom));
        int[] vector = new Permutation(i, secureRandom).getVector();
        for (int i2 = 0; i2 < i; i2++) {
            System.arraycopy(gF2Matrix.matrix[i2], 0, this.matrix[vector[i2]], 0, this.length);
        }
    }

    private void assignRandomUpperTriangularMatrix(int i, SecureRandom secureRandom) {
        int i2;
        this.numRows = i;
        this.numColumns = i;
        this.length = (i + 31) >>> 5;
        this.matrix = (int[][]) Array.newInstance(int.class, this.numRows, this.length);
        int i3 = i & 31;
        int i4 = i3 == 0 ? -1 : (1 << i3) - 1;
        for (int i5 = 0; i5 < this.numRows; i5++) {
            int i6 = i5 >>> 5;
            int i7 = i5 & 31;
            int i8 = 1 << i7;
            for (int i9 = 0; i9 < i6; i9++) {
                this.matrix[i5][i9] = 0;
            }
            this.matrix[i5][i6] = (secureRandom.nextInt() << i7) | i8;
            while (true) {
                i6++;
                i2 = this.length;
                if (i6 < i2) {
                    this.matrix[i5][i6] = secureRandom.nextInt();
                }
            }
            int[] iArr = this.matrix[i5];
            int i10 = i2 - 1;
            iArr[i10] = iArr[i10] & i4;
        }
    }

    private void assignUnitMatrix(int i) {
        this.numRows = i;
        this.numColumns = i;
        this.length = (i + 31) >>> 5;
        this.matrix = (int[][]) Array.newInstance(int.class, this.numRows, this.length);
        for (int i2 = 0; i2 < this.numRows; i2++) {
            for (int i3 = 0; i3 < this.length; i3++) {
                this.matrix[i2][i3] = 0;
            }
        }
        for (int i4 = 0; i4 < this.numRows; i4++) {
            this.matrix[i4][i4 >>> 5] = 1 << (i4 & 31);
        }
    }

    private void assignZeroMatrix(int i, int i2) {
        this.numRows = i;
        this.numColumns = i2;
        this.length = (i2 + 31) >>> 5;
        this.matrix = (int[][]) Array.newInstance(int.class, this.numRows, this.length);
        for (int i3 = 0; i3 < this.numRows; i3++) {
            for (int i4 = 0; i4 < this.length; i4++) {
                this.matrix[i3][i4] = 0;
            }
        }
    }

    public static GF2Matrix[] createRandomRegularMatrixAndItsInverse(int i, SecureRandom secureRandom) {
        GF2Matrix[] gF2MatrixArr = new GF2Matrix[2];
        int i2 = (i + 31) >> 5;
        GF2Matrix gF2Matrix = new GF2Matrix(i, Matrix.MATRIX_TYPE_RANDOM_LT, secureRandom);
        GF2Matrix gF2Matrix2 = new GF2Matrix(i, Matrix.MATRIX_TYPE_RANDOM_UT, secureRandom);
        GF2Matrix gF2Matrix3 = (GF2Matrix) gF2Matrix.rightMultiply(gF2Matrix2);
        Permutation permutation = new Permutation(i, secureRandom);
        int[] vector = permutation.getVector();
        int[][] iArr = (int[][]) Array.newInstance(int.class, i, i2);
        for (int i3 = 0; i3 < i; i3++) {
            System.arraycopy(gF2Matrix3.matrix[vector[i3]], 0, iArr[i3], 0, i2);
        }
        gF2MatrixArr[0] = new GF2Matrix(i, iArr);
        GF2Matrix gF2Matrix4 = new GF2Matrix(i, 'I');
        int i4 = 0;
        while (i4 < i) {
            int i5 = i4 >>> 5;
            int i6 = 1 << (i4 & 31);
            int i7 = i4 + 1;
            for (int i8 = i7; i8 < i; i8++) {
                if ((gF2Matrix.matrix[i8][i5] & i6) != 0) {
                    for (int i9 = 0; i9 <= i5; i9++) {
                        int[][] iArr2 = gF2Matrix4.matrix;
                        int[] iArr3 = iArr2[i8];
                        iArr3[i9] = iArr3[i9] ^ iArr2[i4][i9];
                    }
                }
            }
            i4 = i7;
        }
        GF2Matrix gF2Matrix5 = new GF2Matrix(i, 'I');
        for (int i10 = i - 1; i10 >= 0; i10--) {
            int i11 = i10 >>> 5;
            int i12 = 1 << (i10 & 31);
            for (int i13 = i10 - 1; i13 >= 0; i13--) {
                if ((gF2Matrix2.matrix[i13][i11] & i12) != 0) {
                    for (int i14 = i11; i14 < i2; i14++) {
                        int[][] iArr4 = gF2Matrix5.matrix;
                        int[] iArr5 = iArr4[i13];
                        iArr5[i14] = iArr4[i10][i14] ^ iArr5[i14];
                    }
                }
            }
        }
        gF2MatrixArr[1] = (GF2Matrix) gF2Matrix5.rightMultiply(gF2Matrix4.rightMultiply(permutation));
        return gF2MatrixArr;
    }

    private static void swapRows(int[][] iArr, int i, int i2) {
        int[] iArr2 = iArr[i];
        iArr[i] = iArr[i2];
        iArr[i2] = iArr2;
    }

    @Override // org.bouncycastle.pqc.math.linearalgebra.Matrix
    public Matrix computeInverse() {
        int i = this.numRows;
        if (i == this.numColumns) {
            int[][] iArr = (int[][]) Array.newInstance(int.class, i, this.length);
            for (int i2 = this.numRows - 1; i2 >= 0; i2--) {
                iArr[i2] = IntUtils.clone(this.matrix[i2]);
            }
            int[][] iArr2 = (int[][]) Array.newInstance(int.class, this.numRows, this.length);
            for (int i3 = this.numRows - 1; i3 >= 0; i3--) {
                iArr2[i3][i3 >> 5] = 1 << (i3 & 31);
            }
            for (int i4 = 0; i4 < this.numRows; i4++) {
                int i5 = i4 >> 5;
                int i6 = 1 << (i4 & 31);
                if ((iArr[i4][i5] & i6) == 0) {
                    int i7 = i4 + 1;
                    boolean z = false;
                    while (i7 < this.numRows) {
                        if ((iArr[i7][i5] & i6) != 0) {
                            swapRows(iArr, i4, i7);
                            swapRows(iArr2, i4, i7);
                            i7 = this.numRows;
                            z = true;
                        }
                        i7++;
                    }
                    if (!z) {
                        throw new ArithmeticException("Matrix is not invertible.");
                    }
                }
                for (int i8 = this.numRows - 1; i8 >= 0; i8--) {
                    if (i8 != i4 && (iArr[i8][i5] & i6) != 0) {
                        addToRow(iArr[i4], iArr[i8], i5);
                        addToRow(iArr2[i4], iArr2[i8], 0);
                    }
                }
            }
            return new GF2Matrix(this.numColumns, iArr2);
        }
        throw new ArithmeticException("Matrix is not invertible.");
    }

    public Matrix computeTranspose() {
        int[][] iArr = (int[][]) Array.newInstance(int.class, this.numColumns, (this.numRows + 31) >>> 5);
        int i = 0;
        while (true) {
            int i2 = this.numRows;
            if (i < i2) {
                for (int i3 = 0; i3 < this.numColumns; i3++) {
                    int i4 = i >>> 5;
                    int i5 = i & 31;
                    if (((this.matrix[i][i3 >>> 5] >>> (i3 & 31)) & 1) == 1) {
                        int[] iArr2 = iArr[i3];
                        iArr2[i4] = (1 << i5) | iArr2[i4];
                    }
                }
                i++;
            } else {
                return new GF2Matrix(i2, iArr);
            }
        }
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof GF2Matrix)) {
            return false;
        }
        GF2Matrix gF2Matrix = (GF2Matrix) obj;
        if (this.numRows != gF2Matrix.numRows || this.numColumns != gF2Matrix.numColumns || this.length != gF2Matrix.length) {
            return false;
        }
        for (int i = 0; i < this.numRows; i++) {
            if (!IntUtils.equals(this.matrix[i], gF2Matrix.matrix[i])) {
                return false;
            }
        }
        return true;
    }

    public GF2Matrix extendLeftCompactForm() {
        int i = this.numColumns;
        int i2 = this.numRows;
        GF2Matrix gF2Matrix = new GF2Matrix(i2, i + i2);
        int i3 = this.numRows;
        int i4 = (i3 - 1) + this.numColumns;
        int i5 = i3 - 1;
        while (i5 >= 0) {
            System.arraycopy(this.matrix[i5], 0, gF2Matrix.matrix[i5], 0, this.length);
            int[] iArr = gF2Matrix.matrix[i5];
            int i6 = i4 >> 5;
            iArr[i6] = iArr[i6] | (1 << (i4 & 31));
            i5--;
            i4--;
        }
        return gF2Matrix;
    }

    public GF2Matrix extendRightCompactForm() {
        int i;
        int i2 = this.numRows;
        GF2Matrix gF2Matrix = new GF2Matrix(i2, this.numColumns + i2);
        int i3 = this.numRows;
        int i4 = i3 >> 5;
        int i5 = i3 & 31;
        for (int i6 = i3 - 1; i6 >= 0; i6--) {
            int[][] iArr = gF2Matrix.matrix;
            int[] iArr2 = iArr[i6];
            int i7 = i6 >> 5;
            iArr2[i7] = iArr2[i7] | (1 << (i6 & 31));
            int i8 = 0;
            if (i5 != 0) {
                int i9 = i4;
                while (true) {
                    i = this.length;
                    if (i8 >= i - 1) {
                        break;
                    }
                    int i10 = this.matrix[i6][i8];
                    int[][] iArr3 = gF2Matrix.matrix;
                    int[] iArr4 = iArr3[i6];
                    int i11 = i9 + 1;
                    iArr4[i9] = iArr4[i9] | (i10 << i5);
                    int[] iArr5 = iArr3[i6];
                    iArr5[i11] = (i10 >>> (32 - i5)) | iArr5[i11];
                    i8++;
                    i9 = i11;
                }
                int i12 = this.matrix[i6][i - 1];
                int[][] iArr6 = gF2Matrix.matrix;
                int[] iArr7 = iArr6[i6];
                int i13 = i9 + 1;
                iArr7[i9] = iArr7[i9] | (i12 << i5);
                if (i13 < gF2Matrix.length) {
                    int[] iArr8 = iArr6[i6];
                    iArr8[i13] = (i12 >>> (32 - i5)) | iArr8[i13];
                }
            } else {
                System.arraycopy(this.matrix[i6], 0, iArr[i6], i4, this.length);
            }
        }
        return gF2Matrix;
    }

    @Override // org.bouncycastle.pqc.math.linearalgebra.Matrix
    public byte[] getEncoded() {
        int i = this.numRows;
        byte[] bArr = new byte[(((this.numColumns + 7) >>> 3) * i) + 8];
        LittleEndianConversions.I2OSP(i, bArr, 0);
        LittleEndianConversions.I2OSP(this.numColumns, bArr, 4);
        int i2 = this.numColumns;
        int i3 = i2 >>> 5;
        int i4 = i2 & 31;
        int i5 = 8;
        int i6 = 0;
        while (i6 < this.numRows) {
            int i7 = i5;
            int i8 = 0;
            while (i8 < i3) {
                LittleEndianConversions.I2OSP(this.matrix[i6][i8], bArr, i7);
                i8++;
                i7 += 4;
            }
            int i9 = 0;
            while (i9 < i4) {
                bArr[i7] = (byte) ((this.matrix[i6][i3] >>> i9) & 255);
                i9 += 8;
                i7++;
            }
            i6++;
            i5 = i7;
        }
        return bArr;
    }

    public double getHammingWeight() {
        int i = this.numColumns & 31;
        int i2 = this.length;
        if (i != 0) {
            i2--;
        }
        double d = 0.0d;
        double d2 = 0.0d;
        for (int i3 = 0; i3 < this.numRows; i3++) {
            int i4 = 0;
            while (i4 < i2) {
                int i5 = this.matrix[i3][i4];
                double d3 = d2;
                double d4 = d;
                for (int i6 = 0; i6 < 32; i6++) {
                    d4 += (i5 >>> i6) & 1;
                    d3 += 1.0d;
                }
                i4++;
                d = d4;
                d2 = d3;
            }
            int i7 = this.matrix[i3][this.length - 1];
            for (int i8 = 0; i8 < i; i8++) {
                d += (i7 >>> i8) & 1;
                d2 += 1.0d;
            }
        }
        return d / d2;
    }

    public int[][] getIntArray() {
        return this.matrix;
    }

    public GF2Matrix getLeftSubMatrix() {
        int i = this.numColumns;
        int i2 = this.numRows;
        if (i > i2) {
            int i3 = (i2 + 31) >> 5;
            int[][] iArr = (int[][]) Array.newInstance(int.class, i2, i3);
            int i4 = (1 << (this.numRows & 31)) - 1;
            if (i4 == 0) {
                i4 = -1;
            }
            for (int i5 = this.numRows - 1; i5 >= 0; i5--) {
                System.arraycopy(this.matrix[i5], 0, iArr[i5], 0, i3);
                int[] iArr2 = iArr[i5];
                int i6 = i3 - 1;
                iArr2[i6] = iArr2[i6] & i4;
            }
            return new GF2Matrix(this.numRows, iArr);
        }
        throw new ArithmeticException("empty submatrix");
    }

    public int getLength() {
        return this.length;
    }

    public GF2Matrix getRightSubMatrix() {
        int i;
        int i2 = this.numColumns;
        int i3 = this.numRows;
        if (i2 > i3) {
            int i4 = i3 >> 5;
            int i5 = i3 & 31;
            GF2Matrix gF2Matrix = new GF2Matrix(i3, i2 - i3);
            for (int i6 = this.numRows - 1; i6 >= 0; i6--) {
                int i7 = 0;
                if (i5 != 0) {
                    int i8 = i4;
                    while (true) {
                        i = gF2Matrix.length;
                        if (i7 >= i - 1) {
                            break;
                        }
                        int[] iArr = gF2Matrix.matrix[i6];
                        int[][] iArr2 = this.matrix;
                        int i9 = i8 + 1;
                        iArr[i7] = (iArr2[i6][i8] >>> i5) | (iArr2[i6][i9] << (32 - i5));
                        i7++;
                        i8 = i9;
                    }
                    int[][] iArr3 = gF2Matrix.matrix;
                    int[][] iArr4 = this.matrix;
                    int i10 = i8 + 1;
                    iArr3[i6][i - 1] = iArr4[i6][i8] >>> i5;
                    if (i10 < this.length) {
                        int[] iArr5 = iArr3[i6];
                        int i11 = i - 1;
                        iArr5[i11] = iArr5[i11] | (iArr4[i6][i10] << (32 - i5));
                    }
                } else {
                    System.arraycopy(this.matrix[i6], i4, gF2Matrix.matrix[i6], 0, gF2Matrix.length);
                }
            }
            return gF2Matrix;
        }
        throw new ArithmeticException("empty submatrix");
    }

    public int[] getRow(int i) {
        return this.matrix[i];
    }

    public int hashCode() {
        int i = (((this.numRows * 31) + this.numColumns) * 31) + this.length;
        for (int i2 = 0; i2 < this.numRows; i2++) {
            i = (i * 31) + Arrays.hashCode(this.matrix[i2]);
        }
        return i;
    }

    @Override // org.bouncycastle.pqc.math.linearalgebra.Matrix
    public boolean isZero() {
        for (int i = 0; i < this.numRows; i++) {
            for (int i2 = 0; i2 < this.length; i2++) {
                if (this.matrix[i][i2] != 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public Matrix leftMultiply(Permutation permutation) {
        int[] vector = permutation.getVector();
        int length = vector.length;
        int i = this.numRows;
        if (length == i) {
            int[][] iArr = new int[i];
            for (int i2 = i - 1; i2 >= 0; i2--) {
                iArr[i2] = IntUtils.clone(this.matrix[vector[i2]]);
            }
            return new GF2Matrix(this.numRows, iArr);
        }
        throw new ArithmeticException("length mismatch");
    }

    @Override // org.bouncycastle.pqc.math.linearalgebra.Matrix
    public Vector leftMultiply(Vector vector) {
        if (vector instanceof GF2Vector) {
            if (vector.length != this.numRows) {
                throw new ArithmeticException("length mismatch");
            }
            int[] vecArray = ((GF2Vector) vector).getVecArray();
            int[] iArr = new int[this.length];
            int i = this.numRows;
            int i2 = i >> 5;
            int i3 = 1 << (i & 31);
            int i4 = 0;
            int i5 = 0;
            while (i4 < i2) {
                int i6 = i5;
                int i7 = 1;
                do {
                    if ((vecArray[i4] & i7) != 0) {
                        for (int i8 = 0; i8 < this.length; i8++) {
                            iArr[i8] = iArr[i8] ^ this.matrix[i6][i8];
                        }
                    }
                    i6++;
                    i7 <<= 1;
                } while (i7 != 0);
                i4++;
                i5 = i6;
            }
            for (int i9 = 1; i9 != i3; i9 <<= 1) {
                if ((vecArray[i2] & i9) != 0) {
                    for (int i10 = 0; i10 < this.length; i10++) {
                        iArr[i10] = iArr[i10] ^ this.matrix[i5][i10];
                    }
                }
                i5++;
            }
            return new GF2Vector(iArr, this.numColumns);
        }
        throw new ArithmeticException("vector is not defined over GF(2)");
    }

    public Vector leftMultiplyLeftCompactForm(Vector vector) {
        if (vector instanceof GF2Vector) {
            if (vector.length != this.numRows) {
                throw new ArithmeticException("length mismatch");
            }
            int[] vecArray = ((GF2Vector) vector).getVecArray();
            int i = this.numRows;
            int[] iArr = new int[((this.numColumns + i) + 31) >>> 5];
            int i2 = i >>> 5;
            int i3 = 0;
            int i4 = 0;
            while (i3 < i2) {
                int i5 = i4;
                int i6 = 1;
                do {
                    if ((vecArray[i3] & i6) != 0) {
                        for (int i7 = 0; i7 < this.length; i7++) {
                            iArr[i7] = iArr[i7] ^ this.matrix[i5][i7];
                        }
                        int i8 = this.numColumns;
                        int i9 = (i8 + i5) >>> 5;
                        iArr[i9] = (1 << ((i8 + i5) & 31)) | iArr[i9];
                    }
                    i5++;
                    i6 <<= 1;
                } while (i6 != 0);
                i3++;
                i4 = i5;
            }
            int i10 = 1 << (this.numRows & 31);
            int i11 = i4;
            for (int i12 = 1; i12 != i10; i12 <<= 1) {
                if ((vecArray[i2] & i12) != 0) {
                    for (int i13 = 0; i13 < this.length; i13++) {
                        iArr[i13] = iArr[i13] ^ this.matrix[i11][i13];
                    }
                    int i14 = this.numColumns;
                    int i15 = (i14 + i11) >>> 5;
                    iArr[i15] = (1 << ((i14 + i11) & 31)) | iArr[i15];
                }
                i11++;
            }
            return new GF2Vector(iArr, this.numRows + this.numColumns);
        }
        throw new ArithmeticException("vector is not defined over GF(2)");
    }

    @Override // org.bouncycastle.pqc.math.linearalgebra.Matrix
    public Matrix rightMultiply(Matrix matrix) {
        if (matrix instanceof GF2Matrix) {
            if (matrix.numRows != this.numColumns) {
                throw new ArithmeticException("length mismatch");
            }
            GF2Matrix gF2Matrix = (GF2Matrix) matrix;
            GF2Matrix gF2Matrix2 = new GF2Matrix(this.numRows, matrix.numColumns);
            int i = this.numColumns & 31;
            int i2 = this.length;
            if (i != 0) {
                i2--;
            }
            for (int i3 = 0; i3 < this.numRows; i3++) {
                int i4 = 0;
                int i5 = 0;
                while (i4 < i2) {
                    int i6 = this.matrix[i3][i4];
                    int i7 = i5;
                    for (int i8 = 0; i8 < 32; i8++) {
                        if (((1 << i8) & i6) != 0) {
                            for (int i9 = 0; i9 < gF2Matrix.length; i9++) {
                                int[] iArr = gF2Matrix2.matrix[i3];
                                iArr[i9] = iArr[i9] ^ gF2Matrix.matrix[i7][i9];
                            }
                        }
                        i7++;
                    }
                    i4++;
                    i5 = i7;
                }
                int i10 = this.matrix[i3][this.length - 1];
                int i11 = i5;
                for (int i12 = 0; i12 < i; i12++) {
                    if (((1 << i12) & i10) != 0) {
                        for (int i13 = 0; i13 < gF2Matrix.length; i13++) {
                            int[] iArr2 = gF2Matrix2.matrix[i3];
                            iArr2[i13] = iArr2[i13] ^ gF2Matrix.matrix[i11][i13];
                        }
                    }
                    i11++;
                }
            }
            return gF2Matrix2;
        }
        throw new ArithmeticException("matrix is not defined over GF(2)");
    }

    @Override // org.bouncycastle.pqc.math.linearalgebra.Matrix
    public Matrix rightMultiply(Permutation permutation) {
        int[] vector = permutation.getVector();
        int length = vector.length;
        int i = this.numColumns;
        if (length == i) {
            GF2Matrix gF2Matrix = new GF2Matrix(this.numRows, i);
            for (int i2 = this.numColumns - 1; i2 >= 0; i2--) {
                int i3 = i2 >>> 5;
                int i4 = i2 & 31;
                int i5 = vector[i2] >>> 5;
                int i6 = vector[i2] & 31;
                for (int i7 = this.numRows - 1; i7 >= 0; i7--) {
                    int[] iArr = gF2Matrix.matrix[i7];
                    iArr[i3] = iArr[i3] | (((this.matrix[i7][i5] >>> i6) & 1) << i4);
                }
            }
            return gF2Matrix;
        }
        throw new ArithmeticException("length mismatch");
    }

    @Override // org.bouncycastle.pqc.math.linearalgebra.Matrix
    public Vector rightMultiply(Vector vector) {
        if (vector instanceof GF2Vector) {
            if (vector.length != this.numColumns) {
                throw new ArithmeticException("length mismatch");
            }
            int[] vecArray = ((GF2Vector) vector).getVecArray();
            int[] iArr = new int[(this.numRows + 31) >>> 5];
            int i = 0;
            while (true) {
                int i2 = this.numRows;
                if (i >= i2) {
                    return new GF2Vector(iArr, i2);
                }
                int i3 = 0;
                for (int i4 = 0; i4 < this.length; i4++) {
                    i3 ^= this.matrix[i][i4] & vecArray[i4];
                }
                int i5 = 0;
                for (int i6 = 0; i6 < 32; i6++) {
                    i5 ^= (i3 >>> i6) & 1;
                }
                if (i5 == 1) {
                    int i7 = i >>> 5;
                    iArr[i7] = iArr[i7] | (1 << (i & 31));
                }
                i++;
            }
        } else {
            throw new ArithmeticException("vector is not defined over GF(2)");
        }
    }

    public Vector rightMultiplyRightCompactForm(Vector vector) {
        int i;
        if (vector instanceof GF2Vector) {
            if (vector.length != this.numColumns + this.numRows) {
                throw new ArithmeticException("length mismatch");
            }
            int[] vecArray = ((GF2Vector) vector).getVecArray();
            int i2 = this.numRows;
            int[] iArr = new int[(i2 + 31) >>> 5];
            int i3 = i2 >> 5;
            int i4 = i2 & 31;
            int i5 = 0;
            while (true) {
                int i6 = this.numRows;
                if (i5 >= i6) {
                    return new GF2Vector(iArr, i6);
                }
                int i7 = i5 >> 5;
                int i8 = i5 & 31;
                int i9 = (vecArray[i7] >>> i8) & 1;
                if (i4 != 0) {
                    int i10 = i3;
                    int i11 = i9;
                    int i12 = 0;
                    while (i12 < this.length - 1) {
                        int i13 = i10 + 1;
                        i11 ^= ((vecArray[i10] >>> i4) | (vecArray[i13] << (32 - i4))) & this.matrix[i5][i12];
                        i12++;
                        i10 = i13;
                    }
                    int i14 = i10 + 1;
                    int i15 = vecArray[i10] >>> i4;
                    if (i14 < vecArray.length) {
                        i15 |= vecArray[i14] << (32 - i4);
                    }
                    i = (this.matrix[i5][this.length - 1] & i15) ^ i11;
                } else {
                    int i16 = i3;
                    int i17 = i9;
                    int i18 = 0;
                    while (i18 < this.length) {
                        i17 ^= vecArray[i16] & this.matrix[i5][i18];
                        i18++;
                        i16++;
                    }
                    i = i17;
                }
                int i19 = 0;
                int i20 = i;
                for (int i21 = 0; i21 < 32; i21++) {
                    i19 ^= i20 & 1;
                    i20 >>>= 1;
                }
                if (i19 == 1) {
                    iArr[i7] = iArr[i7] | (1 << i8);
                }
                i5++;
            }
        } else {
            throw new ArithmeticException("vector is not defined over GF(2)");
        }
    }

    @Override // org.bouncycastle.pqc.math.linearalgebra.Matrix
    public String toString() {
        int i = this.numColumns & 31;
        int i2 = this.length;
        if (i != 0) {
            i2--;
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (int i3 = 0; i3 < this.numRows; i3++) {
            stringBuffer.append(i3 + RealTimeTextConstants.COLON_SPACE);
            for (int i4 = 0; i4 < i2; i4++) {
                int i5 = this.matrix[i3][i4];
                for (int i6 = 0; i6 < 32; i6++) {
                    if (((i5 >>> i6) & 1) == 0) {
                        stringBuffer.append('0');
                    } else {
                        stringBuffer.append('1');
                    }
                }
                stringBuffer.append(Chars.SPACE);
            }
            int i7 = this.matrix[i3][this.length - 1];
            for (int i8 = 0; i8 < i; i8++) {
                if (((i7 >>> i8) & 1) == 0) {
                    stringBuffer.append('0');
                } else {
                    stringBuffer.append('1');
                }
            }
            stringBuffer.append('\n');
        }
        return stringBuffer.toString();
    }
}
