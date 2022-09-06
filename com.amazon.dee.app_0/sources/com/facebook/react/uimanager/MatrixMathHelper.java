package com.facebook.react.uimanager;

import com.amazon.comms.ringservice.webrtc.FrostVideoEffectController;
import com.facebook.infer.annotation.Assertions;
import java.lang.reflect.Array;
/* loaded from: classes2.dex */
public class MatrixMathHelper {
    private static final double EPSILON = 1.0E-5d;

    /* loaded from: classes2.dex */
    public static class MatrixDecompositionContext {
        double[] perspective = new double[4];
        double[] scale = new double[3];
        double[] skew = new double[3];
        double[] translation = new double[3];
        double[] rotationDegrees = new double[3];

        private static void resetArray(double[] dArr) {
            for (int i = 0; i < dArr.length; i++) {
                dArr[i] = 0.0d;
            }
        }

        public void reset() {
            resetArray(this.perspective);
            resetArray(this.scale);
            resetArray(this.skew);
            resetArray(this.translation);
            resetArray(this.rotationDegrees);
        }
    }

    public static void applyPerspective(double[] dArr, double d) {
        dArr[11] = (-1.0d) / d;
    }

    public static void applyRotateX(double[] dArr, double d) {
        dArr[5] = Math.cos(d);
        dArr[6] = Math.sin(d);
        dArr[9] = -Math.sin(d);
        dArr[10] = Math.cos(d);
    }

    public static void applyRotateY(double[] dArr, double d) {
        dArr[0] = Math.cos(d);
        dArr[2] = -Math.sin(d);
        dArr[8] = Math.sin(d);
        dArr[10] = Math.cos(d);
    }

    public static void applyRotateZ(double[] dArr, double d) {
        dArr[0] = Math.cos(d);
        dArr[1] = Math.sin(d);
        dArr[4] = -Math.sin(d);
        dArr[5] = Math.cos(d);
    }

    public static void applyScaleX(double[] dArr, double d) {
        dArr[0] = d;
    }

    public static void applyScaleY(double[] dArr, double d) {
        dArr[5] = d;
    }

    public static void applyScaleZ(double[] dArr, double d) {
        dArr[10] = d;
    }

    public static void applySkewX(double[] dArr, double d) {
        dArr[4] = Math.tan(d);
    }

    public static void applySkewY(double[] dArr, double d) {
        dArr[1] = Math.tan(d);
    }

    public static void applyTranslate2D(double[] dArr, double d, double d2) {
        dArr[12] = d;
        dArr[13] = d2;
    }

    public static void applyTranslate3D(double[] dArr, double d, double d2, double d3) {
        dArr[12] = d;
        dArr[13] = d2;
        dArr[14] = d3;
    }

    public static double[] createIdentityMatrix() {
        double[] dArr = new double[16];
        resetIdentityMatrix(dArr);
        return dArr;
    }

    public static void decomposeMatrix(double[] dArr, MatrixDecompositionContext matrixDecompositionContext) {
        Assertions.assertCondition(dArr.length == 16);
        double[] dArr2 = matrixDecompositionContext.perspective;
        double[] dArr3 = matrixDecompositionContext.scale;
        double[] dArr4 = matrixDecompositionContext.skew;
        double[] dArr5 = matrixDecompositionContext.translation;
        double[] dArr6 = matrixDecompositionContext.rotationDegrees;
        if (isZero(dArr[15])) {
            return;
        }
        double[][] dArr7 = (double[][]) Array.newInstance(double.class, 4, 4);
        double[] dArr8 = new double[16];
        for (int i = 0; i < 4; i++) {
            for (int i2 = 0; i2 < 4; i2++) {
                int i3 = (i * 4) + i2;
                double d = dArr[i3] / dArr[15];
                dArr7[i][i2] = d;
                if (i2 == 3) {
                    d = FrostVideoEffectController.VIDEO_STRENGTH_CLEAR;
                }
                dArr8[i3] = d;
            }
        }
        dArr8[15] = 1.0d;
        if (isZero(determinant(dArr8))) {
            return;
        }
        if (isZero(dArr7[0][3]) && isZero(dArr7[1][3]) && isZero(dArr7[2][3])) {
            dArr2[2] = 0.0d;
            dArr2[1] = 0.0d;
            dArr2[0] = 0.0d;
            dArr2[3] = 1.0d;
        } else {
            multiplyVectorByMatrix(new double[]{dArr7[0][3], dArr7[1][3], dArr7[2][3], dArr7[3][3]}, transpose(inverse(dArr8)), dArr2);
        }
        for (int i4 = 0; i4 < 3; i4++) {
            dArr5[i4] = dArr7[3][i4];
        }
        double[][] dArr9 = (double[][]) Array.newInstance(double.class, 3, 3);
        for (int i5 = 0; i5 < 3; i5++) {
            dArr9[i5][0] = dArr7[i5][0];
            dArr9[i5][1] = dArr7[i5][1];
            dArr9[i5][2] = dArr7[i5][2];
        }
        dArr3[0] = v3Length(dArr9[0]);
        dArr9[0] = v3Normalize(dArr9[0], dArr3[0]);
        dArr4[0] = v3Dot(dArr9[0], dArr9[1]);
        dArr9[1] = v3Combine(dArr9[1], dArr9[0], 1.0d, -dArr4[0]);
        dArr3[1] = v3Length(dArr9[1]);
        dArr9[1] = v3Normalize(dArr9[1], dArr3[1]);
        dArr4[0] = dArr4[0] / dArr3[1];
        dArr4[1] = v3Dot(dArr9[0], dArr9[2]);
        dArr9[2] = v3Combine(dArr9[2], dArr9[0], 1.0d, -dArr4[1]);
        dArr4[2] = v3Dot(dArr9[1], dArr9[2]);
        dArr9[2] = v3Combine(dArr9[2], dArr9[1], 1.0d, -dArr4[2]);
        dArr3[2] = v3Length(dArr9[2]);
        dArr9[2] = v3Normalize(dArr9[2], dArr3[2]);
        dArr4[1] = dArr4[1] / dArr3[2];
        dArr4[2] = dArr4[2] / dArr3[2];
        if (v3Dot(dArr9[0], v3Cross(dArr9[1], dArr9[2])) < FrostVideoEffectController.VIDEO_STRENGTH_CLEAR) {
            for (int i6 = 0; i6 < 3; i6++) {
                dArr3[i6] = dArr3[i6] * (-1.0d);
                double[] dArr10 = dArr9[i6];
                dArr10[0] = dArr10[0] * (-1.0d);
                double[] dArr11 = dArr9[i6];
                dArr11[1] = dArr11[1] * (-1.0d);
                double[] dArr12 = dArr9[i6];
                dArr12[2] = dArr12[2] * (-1.0d);
            }
        }
        dArr6[0] = roundTo3Places((-Math.atan2(dArr9[2][1], dArr9[2][2])) * 57.29577951308232d);
        dArr6[1] = roundTo3Places((-Math.atan2(-dArr9[2][0], Math.sqrt((dArr9[2][2] * dArr9[2][2]) + (dArr9[2][1] * dArr9[2][1])))) * 57.29577951308232d);
        dArr6[2] = roundTo3Places((-Math.atan2(dArr9[1][0], dArr9[0][0])) * 57.29577951308232d);
    }

    public static double degreesToRadians(double d) {
        return (d * 3.141592653589793d) / 180.0d;
    }

    public static double determinant(double[] dArr) {
        double d = dArr[0];
        double d2 = dArr[1];
        double d3 = dArr[2];
        double d4 = dArr[3];
        double d5 = dArr[4];
        double d6 = dArr[5];
        double d7 = dArr[6];
        double d8 = dArr[7];
        double d9 = dArr[8];
        double d10 = dArr[9];
        double d11 = dArr[10];
        double d12 = dArr[11];
        double d13 = dArr[12];
        double d14 = dArr[13];
        double d15 = dArr[14];
        double d16 = dArr[15];
        double d17 = d4 * d7;
        double d18 = d3 * d8;
        double d19 = d4 * d6;
        double d20 = d2 * d8;
        double d21 = (d20 * d11 * d13) + ((((d17 * d10) * d13) - ((d18 * d10) * d13)) - ((d19 * d11) * d13));
        double d22 = d3 * d6;
        double d23 = (d22 * d12 * d13) + d21;
        double d24 = d2 * d7;
        double d25 = d4 * d5;
        double d26 = d25 * d11 * d14;
        double d27 = d8 * d;
        double d28 = d3 * d5;
        double d29 = ((d26 + (((d18 * d9) * d14) + ((d23 - ((d24 * d12) * d13)) - ((d17 * d9) * d14)))) - ((d27 * d11) * d14)) - ((d28 * d12) * d14);
        double d30 = d7 * d;
        double d31 = d2 * d5;
        double d32 = d * d6;
        return (d32 * d11 * d16) + (((((d28 * d10) * d16) + (((d24 * d9) * d16) + (((((d31 * d12) * d15) + (((d27 * d10) * d15) + (((((d19 * d9) * d15) + (((d30 * d12) * d14) + d29)) - ((d20 * d9) * d15)) - ((d25 * d10) * d15)))) - ((d12 * d32) * d15)) - ((d22 * d9) * d16)))) - ((d30 * d10) * d16)) - ((d31 * d11) * d16));
    }

    public static double[] inverse(double[] dArr) {
        double determinant = determinant(dArr);
        if (isZero(determinant)) {
            return dArr;
        }
        double d = dArr[0];
        double d2 = dArr[1];
        double d3 = dArr[2];
        double d4 = dArr[3];
        double d5 = dArr[4];
        double d6 = dArr[5];
        double d7 = dArr[6];
        double d8 = dArr[7];
        double d9 = dArr[8];
        double d10 = dArr[9];
        double d11 = dArr[10];
        double d12 = dArr[11];
        double d13 = dArr[12];
        double d14 = dArr[13];
        double d15 = dArr[14];
        double d16 = dArr[15];
        double d17 = d7 * d12;
        double d18 = d8 * d11;
        double d19 = d8 * d10;
        double d20 = d6 * d12;
        double d21 = d7 * d10;
        double d22 = (((d19 * d15) + ((d17 * d14) - (d18 * d14))) - (d20 * d15)) - (d21 * d16);
        double d23 = d6 * d11;
        double d24 = d4 * d11;
        double d25 = d3 * d12;
        double d26 = d4 * d10;
        double d27 = d2 * d12;
        double d28 = d3 * d10;
        double d29 = (d28 * d16) + (d27 * d15) + (((d24 * d14) - (d25 * d14)) - (d26 * d15));
        double d30 = d2 * d11;
        double d31 = d3 * d8;
        double d32 = d4 * d7;
        double d33 = d4 * d6;
        double d34 = d2 * d8;
        double d35 = d3 * d6;
        double d36 = d2 * d7;
        double d37 = (d18 * d13) - (d17 * d13);
        double d38 = d8 * d9;
        double d39 = d5 * d12;
        double d40 = (d39 * d15) + (d37 - (d38 * d15));
        double d41 = d7 * d9;
        double d42 = (d41 * d16) + d40;
        double d43 = d5 * d11;
        double d44 = (d25 * d13) - (d24 * d13);
        double d45 = d4 * d9;
        double d46 = (d45 * d15) + d44;
        double d47 = d * d12;
        double d48 = d3 * d9;
        double d49 = d * d11;
        double d50 = d4 * d5;
        double d51 = d8 * d;
        double d52 = d3 * d5;
        double d53 = d7 * d;
        double d54 = d6 * d9;
        double d55 = (((d38 * d14) + ((d20 * d13) - (d19 * d13))) - (d39 * d14)) - (d54 * d16);
        double d56 = d5 * d10;
        double d57 = d2 * d9;
        double d58 = (d57 * d16) + (d47 * d14) + (((d26 * d13) - (d27 * d13)) - (d45 * d14));
        double d59 = d * d10;
        double d60 = d2 * d5;
        double d61 = d * d6;
        return new double[]{((d23 * d16) + d22) / determinant, (d29 - (d30 * d16)) / determinant, ((d36 * d16) + ((((d33 * d15) + ((d31 * d14) - (d32 * d14))) - (d34 * d15)) - (d35 * d16))) / determinant, (((d35 * d12) + ((d34 * d11) + (((d32 * d10) - (d31 * d10)) - (d33 * d11)))) - (d36 * d12)) / determinant, (d42 - (d43 * d16)) / determinant, ((d49 * d16) + ((d46 - (d47 * d15)) - (d48 * d16))) / determinant, (((d52 * d16) + ((d51 * d15) + (((d32 * d13) - (d31 * d13)) - (d50 * d15)))) - (d53 * d16)) / determinant, ((d53 * d12) + ((((d50 * d11) + ((d31 * d9) - (d32 * d9))) - (d51 * d11)) - (d52 * d12))) / determinant, ((d56 * d16) + d55) / determinant, (d58 - (d59 * d16)) / determinant, ((d16 * d61) + ((((d50 * d14) + ((d34 * d13) - (d33 * d13))) - (d51 * d14)) - (d60 * d16))) / determinant, (((d60 * d12) + ((d51 * d10) + (((d33 * d9) - (d34 * d9)) - (d50 * d10)))) - (d12 * d61)) / determinant, (((d54 * d15) + ((d43 * d14) + (((d21 * d13) - (d23 * d13)) - (d41 * d14)))) - (d56 * d15)) / determinant, ((d59 * d15) + ((((d48 * d14) + ((d30 * d13) - (d28 * d13))) - (d49 * d14)) - (d57 * d15))) / determinant, (((d60 * d15) + ((d14 * d53) + (((d35 * d13) - (d13 * d36)) - (d52 * d14)))) - (d15 * d61)) / determinant, ((d61 * d11) + ((((d52 * d10) + ((d36 * d9) - (d35 * d9))) - (d53 * d10)) - (d60 * d11))) / determinant};
    }

    private static boolean isZero(double d) {
        return !Double.isNaN(d) && Math.abs(d) < 1.0E-5d;
    }

    public static void multiplyInto(double[] dArr, double[] dArr2, double[] dArr3) {
        double d = dArr2[0];
        double d2 = dArr2[1];
        double d3 = dArr2[2];
        double d4 = dArr2[3];
        double d5 = dArr2[4];
        double d6 = dArr2[5];
        double d7 = dArr2[6];
        double d8 = dArr2[7];
        double d9 = dArr2[8];
        double d10 = dArr2[9];
        double d11 = dArr2[10];
        double d12 = dArr2[11];
        double d13 = dArr2[12];
        double d14 = dArr2[13];
        double d15 = dArr2[14];
        double d16 = dArr2[15];
        double d17 = dArr3[0];
        double d18 = dArr3[1];
        double d19 = dArr3[2];
        double d20 = dArr3[3];
        dArr[0] = (d20 * d13) + (d19 * d9) + (d18 * d5) + (d17 * d);
        dArr[1] = (d20 * d14) + (d19 * d10) + (d18 * d6) + (d17 * d2);
        dArr[2] = (d20 * d15) + (d19 * d11) + (d18 * d7) + (d17 * d3);
        double d21 = d19 * d12;
        double d22 = d20 * d16;
        dArr[3] = d22 + d21 + (d18 * d8) + (d17 * d4);
        double d23 = dArr3[4];
        double d24 = dArr3[5];
        double d25 = dArr3[6];
        double d26 = dArr3[7];
        dArr[4] = (d26 * d13) + (d25 * d9) + (d24 * d5) + (d23 * d);
        dArr[5] = (d26 * d14) + (d25 * d10) + (d24 * d6) + (d23 * d2);
        dArr[6] = (d26 * d15) + (d25 * d11) + (d24 * d7) + (d23 * d3);
        double d27 = d25 * d12;
        double d28 = d26 * d16;
        dArr[7] = d28 + d27 + (d24 * d8) + (d23 * d4);
        double d29 = dArr3[8];
        double d30 = dArr3[9];
        double d31 = dArr3[10];
        double d32 = dArr3[11];
        dArr[8] = (d32 * d13) + (d31 * d9) + (d30 * d5) + (d29 * d);
        dArr[9] = (d32 * d14) + (d31 * d10) + (d30 * d6) + (d29 * d2);
        dArr[10] = (d32 * d15) + (d31 * d11) + (d30 * d7) + (d29 * d3);
        double d33 = d31 * d12;
        double d34 = d32 * d16;
        dArr[11] = d34 + d33 + (d30 * d8) + (d29 * d4);
        double d35 = dArr3[12];
        double d36 = dArr3[13];
        double d37 = dArr3[14];
        double d38 = dArr3[15];
        double d39 = d9 * d37;
        double d40 = d13 * d38;
        dArr[12] = d40 + d39 + (d5 * d36) + (d * d35);
        double d41 = d10 * d37;
        double d42 = d14 * d38;
        dArr[13] = d42 + d41 + (d6 * d36) + (d2 * d35);
        double d43 = d11 * d37;
        double d44 = d15 * d38;
        dArr[14] = d44 + d43 + (d7 * d36) + (d3 * d35);
        double d45 = d37 * d12;
        double d46 = d38 * d16;
        dArr[15] = d46 + d45 + (d36 * d8) + (d35 * d4);
    }

    public static void multiplyVectorByMatrix(double[] dArr, double[] dArr2, double[] dArr3) {
        double d = dArr[0];
        double d2 = dArr[1];
        double d3 = dArr[2];
        double d4 = dArr[3];
        dArr3[0] = (dArr2[12] * d4) + (dArr2[8] * d3) + (dArr2[4] * d2) + (dArr2[0] * d);
        dArr3[1] = (dArr2[13] * d4) + (dArr2[9] * d3) + (dArr2[5] * d2) + (dArr2[1] * d);
        dArr3[2] = (dArr2[14] * d4) + (dArr2[10] * d3) + (dArr2[6] * d2) + (dArr2[2] * d);
        double d5 = d * dArr2[3];
        double d6 = d3 * dArr2[11];
        dArr3[3] = (d4 * dArr2[15]) + d6 + (d2 * dArr2[7]) + d5;
    }

    public static void resetIdentityMatrix(double[] dArr) {
        dArr[14] = 0.0d;
        dArr[13] = 0.0d;
        dArr[12] = 0.0d;
        dArr[11] = 0.0d;
        dArr[9] = 0.0d;
        dArr[8] = 0.0d;
        dArr[7] = 0.0d;
        dArr[6] = 0.0d;
        dArr[4] = 0.0d;
        dArr[3] = 0.0d;
        dArr[2] = 0.0d;
        dArr[1] = 0.0d;
        dArr[15] = 1.0d;
        dArr[10] = 1.0d;
        dArr[5] = 1.0d;
        dArr[0] = 1.0d;
    }

    public static double roundTo3Places(double d) {
        return Math.round(d * 1000.0d) * 0.001d;
    }

    public static double[] transpose(double[] dArr) {
        return new double[]{dArr[0], dArr[4], dArr[8], dArr[12], dArr[1], dArr[5], dArr[9], dArr[13], dArr[2], dArr[6], dArr[10], dArr[14], dArr[3], dArr[7], dArr[11], dArr[15]};
    }

    public static double[] v3Combine(double[] dArr, double[] dArr2, double d, double d2) {
        return new double[]{(dArr2[0] * d2) + (dArr[0] * d), (dArr2[1] * d2) + (dArr[1] * d), (d2 * dArr2[2]) + (d * dArr[2])};
    }

    public static double[] v3Cross(double[] dArr, double[] dArr2) {
        return new double[]{(dArr[1] * dArr2[2]) - (dArr[2] * dArr2[1]), (dArr[2] * dArr2[0]) - (dArr[0] * dArr2[2]), (dArr[0] * dArr2[1]) - (dArr[1] * dArr2[0])};
    }

    public static double v3Dot(double[] dArr, double[] dArr2) {
        return (dArr[2] * dArr2[2]) + (dArr[1] * dArr2[1]) + (dArr[0] * dArr2[0]);
    }

    public static double v3Length(double[] dArr) {
        return Math.sqrt((dArr[2] * dArr[2]) + (dArr[1] * dArr[1]) + (dArr[0] * dArr[0]));
    }

    public static double[] v3Normalize(double[] dArr, double d) {
        if (isZero(d)) {
            d = v3Length(dArr);
        }
        double d2 = 1.0d / d;
        return new double[]{dArr[0] * d2, dArr[1] * d2, dArr[2] * d2};
    }
}
