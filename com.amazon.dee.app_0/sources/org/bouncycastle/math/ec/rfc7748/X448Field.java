package org.bouncycastle.math.ec.rfc7748;

import org.bouncycastle.math.raw.Mod;
/* loaded from: classes5.dex */
public abstract class X448Field {
    private static final int M28 = 268435455;
    private static final int[] P32 = {-1, -1, -1, -1, -1, -1, -1, -2, -1, -1, -1, -1, -1, -1};
    public static final int SIZE = 16;
    private static final long U32 = 4294967295L;

    public static void add(int[] iArr, int[] iArr2, int[] iArr3) {
        for (int i = 0; i < 16; i++) {
            iArr3[i] = iArr[i] + iArr2[i];
        }
    }

    public static void addOne(int[] iArr) {
        iArr[0] = iArr[0] + 1;
    }

    public static void addOne(int[] iArr, int i) {
        iArr[i] = iArr[i] + 1;
    }

    public static void carry(int[] iArr) {
        int i = iArr[0];
        int i2 = iArr[1];
        int i3 = iArr[2];
        int i4 = iArr[3];
        int i5 = iArr[4];
        int i6 = iArr[5];
        int i7 = iArr[6];
        int i8 = iArr[7];
        int i9 = iArr[8];
        int i10 = iArr[9];
        int i11 = iArr[10];
        int i12 = iArr[11];
        int i13 = iArr[12];
        int i14 = iArr[13];
        int i15 = iArr[14];
        int i16 = iArr[15];
        int i17 = i2 + (i >>> 28);
        int i18 = i & M28;
        int i19 = i6 + (i5 >>> 28);
        int i20 = i5 & M28;
        int i21 = i10 + (i9 >>> 28);
        int i22 = i9 & M28;
        int i23 = i14 + (i13 >>> 28);
        int i24 = i13 & M28;
        int i25 = i3 + (i17 >>> 28);
        int i26 = i17 & M28;
        int i27 = i7 + (i19 >>> 28);
        int i28 = i19 & M28;
        int i29 = i11 + (i21 >>> 28);
        int i30 = i21 & M28;
        int i31 = i15 + (i23 >>> 28);
        int i32 = i23 & M28;
        int i33 = i4 + (i25 >>> 28);
        int i34 = i25 & M28;
        int i35 = i8 + (i27 >>> 28);
        int i36 = i27 & M28;
        int i37 = i12 + (i29 >>> 28);
        int i38 = i29 & M28;
        int i39 = i16 + (i31 >>> 28);
        int i40 = i31 & M28;
        int i41 = i39 >>> 28;
        int i42 = i39 & M28;
        int i43 = i18 + i41;
        int i44 = i20 + (i33 >>> 28);
        int i45 = i33 & M28;
        int i46 = i22 + i41 + (i35 >>> 28);
        int i47 = i35 & M28;
        int i48 = i24 + (i37 >>> 28);
        int i49 = i37 & M28;
        int i50 = i26 + (i43 >>> 28);
        int i51 = i43 & M28;
        int i52 = i28 + (i44 >>> 28);
        int i53 = i44 & M28;
        int i54 = i30 + (i46 >>> 28);
        int i55 = i46 & M28;
        int i56 = i48 & M28;
        iArr[0] = i51;
        iArr[1] = i50;
        iArr[2] = i34;
        iArr[3] = i45;
        iArr[4] = i53;
        iArr[5] = i52;
        iArr[6] = i36;
        iArr[7] = i47;
        iArr[8] = i55;
        iArr[9] = i54;
        iArr[10] = i38;
        iArr[11] = i49;
        iArr[12] = i56;
        iArr[13] = i32 + (i48 >>> 28);
        iArr[14] = i40;
        iArr[15] = i42;
    }

    public static void cmov(int i, int[] iArr, int i2, int[] iArr2, int i3) {
        for (int i4 = 0; i4 < 16; i4++) {
            int i5 = i3 + i4;
            int i6 = iArr2[i5];
            iArr2[i5] = i6 ^ ((iArr[i2 + i4] ^ i6) & i);
        }
    }

    public static void cnegate(int i, int[] iArr) {
        int[] create = create();
        sub(create, iArr, create);
        cmov(-i, create, 0, iArr, 0);
    }

    public static void copy(int[] iArr, int i, int[] iArr2, int i2) {
        for (int i3 = 0; i3 < 16; i3++) {
            iArr2[i2 + i3] = iArr[i + i3];
        }
    }

    public static int[] create() {
        return new int[16];
    }

    public static int[] createTable(int i) {
        return new int[i * 16];
    }

    public static void cswap(int i, int[] iArr, int[] iArr2) {
        int i2 = 0 - i;
        for (int i3 = 0; i3 < 16; i3++) {
            int i4 = iArr[i3];
            int i5 = iArr2[i3];
            int i6 = (i4 ^ i5) & i2;
            iArr[i3] = i4 ^ i6;
            iArr2[i3] = i5 ^ i6;
        }
    }

    public static void decode(byte[] bArr, int i, int[] iArr) {
        decode56(bArr, i, iArr, 0);
        decode56(bArr, i + 7, iArr, 2);
        decode56(bArr, i + 14, iArr, 4);
        decode56(bArr, i + 21, iArr, 6);
        decode56(bArr, i + 28, iArr, 8);
        decode56(bArr, i + 35, iArr, 10);
        decode56(bArr, i + 42, iArr, 12);
        decode56(bArr, i + 49, iArr, 14);
    }

    public static void decode(int[] iArr, int i, int[] iArr2) {
        decode224(iArr, i, iArr2, 0);
        decode224(iArr, i + 7, iArr2, 8);
    }

    private static void decode224(int[] iArr, int i, int[] iArr2, int i2) {
        int i3 = iArr[i + 0];
        int i4 = iArr[i + 1];
        int i5 = iArr[i + 2];
        int i6 = iArr[i + 3];
        int i7 = iArr[i + 4];
        int i8 = iArr[i + 5];
        int i9 = iArr[i + 6];
        iArr2[i2 + 0] = i3 & M28;
        iArr2[i2 + 1] = ((i3 >>> 28) | (i4 << 4)) & M28;
        iArr2[i2 + 2] = ((i4 >>> 24) | (i5 << 8)) & M28;
        iArr2[i2 + 3] = ((i5 >>> 20) | (i6 << 12)) & M28;
        iArr2[i2 + 4] = ((i6 >>> 16) | (i7 << 16)) & M28;
        iArr2[i2 + 5] = ((i7 >>> 12) | (i8 << 20)) & M28;
        iArr2[i2 + 6] = ((i8 >>> 8) | (i9 << 24)) & M28;
        iArr2[i2 + 7] = i9 >>> 4;
    }

    private static int decode24(byte[] bArr, int i) {
        int i2 = i + 1;
        return ((bArr[i2 + 1] & 255) << 16) | (bArr[i] & 255) | ((bArr[i2] & 255) << 8);
    }

    private static int decode32(byte[] bArr, int i) {
        int i2 = i + 1;
        int i3 = i2 + 1;
        return (bArr[i3 + 1] << 24) | (bArr[i] & 255) | ((bArr[i2] & 255) << 8) | ((bArr[i3] & 255) << 16);
    }

    private static void decode56(byte[] bArr, int i, int[] iArr, int i2) {
        int decode32 = decode32(bArr, i);
        int decode24 = decode24(bArr, i + 4);
        iArr[i2] = M28 & decode32;
        iArr[i2 + 1] = (decode24 << 4) | (decode32 >>> 28);
    }

    public static void encode(int[] iArr, byte[] bArr, int i) {
        encode56(iArr, 0, bArr, i);
        encode56(iArr, 2, bArr, i + 7);
        encode56(iArr, 4, bArr, i + 14);
        encode56(iArr, 6, bArr, i + 21);
        encode56(iArr, 8, bArr, i + 28);
        encode56(iArr, 10, bArr, i + 35);
        encode56(iArr, 12, bArr, i + 42);
        encode56(iArr, 14, bArr, i + 49);
    }

    public static void encode(int[] iArr, int[] iArr2, int i) {
        encode224(iArr, 0, iArr2, i);
        encode224(iArr, 8, iArr2, i + 7);
    }

    private static void encode224(int[] iArr, int i, int[] iArr2, int i2) {
        int i3 = iArr[i + 0];
        int i4 = iArr[i + 1];
        int i5 = iArr[i + 2];
        int i6 = iArr[i + 3];
        int i7 = iArr[i + 4];
        int i8 = iArr[i + 5];
        int i9 = iArr[i + 6];
        int i10 = iArr[i + 7];
        iArr2[i2 + 0] = i3 | (i4 << 28);
        iArr2[i2 + 1] = (i4 >>> 4) | (i5 << 24);
        iArr2[i2 + 2] = (i5 >>> 8) | (i6 << 20);
        iArr2[i2 + 3] = (i6 >>> 12) | (i7 << 16);
        iArr2[i2 + 4] = (i7 >>> 16) | (i8 << 12);
        iArr2[i2 + 5] = (i8 >>> 20) | (i9 << 8);
        iArr2[i2 + 6] = (i10 << 4) | (i9 >>> 24);
    }

    private static void encode24(int i, byte[] bArr, int i2) {
        bArr[i2] = (byte) i;
        int i3 = i2 + 1;
        bArr[i3] = (byte) (i >>> 8);
        bArr[i3 + 1] = (byte) (i >>> 16);
    }

    private static void encode32(int i, byte[] bArr, int i2) {
        bArr[i2] = (byte) i;
        int i3 = i2 + 1;
        bArr[i3] = (byte) (i >>> 8);
        int i4 = i3 + 1;
        bArr[i4] = (byte) (i >>> 16);
        bArr[i4 + 1] = (byte) (i >>> 24);
    }

    private static void encode56(int[] iArr, int i, byte[] bArr, int i2) {
        int i3 = iArr[i];
        int i4 = iArr[i + 1];
        encode32((i4 << 28) | i3, bArr, i2);
        encode24(i4 >>> 4, bArr, i2 + 4);
    }

    public static void inv(int[] iArr, int[] iArr2) {
        int[] create = create();
        int[] iArr3 = new int[14];
        copy(iArr, 0, create, 0);
        normalize(create);
        encode(create, iArr3, 0);
        Mod.modOddInverse(P32, iArr3, iArr3);
        decode(iArr3, 0, iArr2);
    }

    public static void invVar(int[] iArr, int[] iArr2) {
        int[] create = create();
        int[] iArr3 = new int[14];
        copy(iArr, 0, create, 0);
        normalize(create);
        encode(create, iArr3, 0);
        Mod.modOddInverseVar(P32, iArr3, iArr3);
        decode(iArr3, 0, iArr2);
    }

    public static int isZero(int[] iArr) {
        int i = 0;
        for (int i2 = 0; i2 < 16; i2++) {
            i |= iArr[i2];
        }
        return (((i >>> 1) | (i & 1)) - 1) >> 31;
    }

    public static boolean isZeroVar(int[] iArr) {
        return isZero(iArr) != 0;
    }

    public static void mul(int[] iArr, int i, int[] iArr2) {
        int i2 = iArr[0];
        int i3 = iArr[1];
        int i4 = iArr[2];
        int i5 = iArr[3];
        int i6 = iArr[4];
        int i7 = iArr[5];
        int i8 = iArr[6];
        int i9 = iArr[7];
        int i10 = iArr[8];
        int i11 = iArr[9];
        int i12 = iArr[10];
        int i13 = iArr[11];
        int i14 = iArr[12];
        int i15 = iArr[13];
        int i16 = iArr[14];
        int i17 = iArr[15];
        long j = i3;
        long j2 = i;
        long j3 = j * j2;
        int i18 = ((int) j3) & M28;
        long j4 = i7 * j2;
        int i19 = ((int) j4) & M28;
        long j5 = i11 * j2;
        int i20 = ((int) j5) & M28;
        long j6 = i15 * j2;
        int i21 = ((int) j6) & M28;
        long j7 = (i4 * j2) + (j3 >>> 28);
        iArr2[2] = ((int) j7) & M28;
        long j8 = (i8 * j2) + (j4 >>> 28);
        iArr2[6] = ((int) j8) & M28;
        long j9 = (i12 * j2) + (j5 >>> 28);
        iArr2[10] = ((int) j9) & M28;
        long j10 = (i16 * j2) + (j6 >>> 28);
        iArr2[14] = ((int) j10) & M28;
        long j11 = (i5 * j2) + (j7 >>> 28);
        iArr2[3] = ((int) j11) & M28;
        long j12 = j11 >>> 28;
        long j13 = (i9 * j2) + (j8 >>> 28);
        iArr2[7] = ((int) j13) & M28;
        long j14 = (i13 * j2) + (j9 >>> 28);
        iArr2[11] = ((int) j14) & M28;
        long j15 = j14 >>> 28;
        long j16 = (i17 * j2) + (j10 >>> 28);
        iArr2[15] = ((int) j16) & M28;
        long j17 = j16 >>> 28;
        long j18 = (i6 * j2) + j12;
        iArr2[4] = ((int) j18) & M28;
        long j19 = j18 >>> 28;
        long j20 = (i10 * j2) + (j13 >>> 28) + j17;
        iArr2[8] = ((int) j20) & M28;
        long j21 = j20 >>> 28;
        long j22 = (i14 * j2) + j15;
        iArr2[12] = ((int) j22) & M28;
        long j23 = j22 >>> 28;
        long j24 = (i2 * j2) + j17;
        iArr2[0] = ((int) j24) & M28;
        iArr2[1] = i18 + ((int) (j24 >>> 28));
        iArr2[5] = i19 + ((int) j19);
        iArr2[9] = i20 + ((int) j21);
        iArr2[13] = i21 + ((int) j23);
    }

    public static void mul(int[] iArr, int[] iArr2, int[] iArr3) {
        int i = iArr[0];
        int i2 = iArr[1];
        int i3 = iArr[2];
        int i4 = iArr[3];
        int i5 = iArr[4];
        int i6 = iArr[5];
        int i7 = iArr[6];
        int i8 = iArr[7];
        int i9 = iArr[8];
        int i10 = iArr[9];
        int i11 = iArr[10];
        int i12 = iArr[11];
        int i13 = iArr[12];
        int i14 = iArr[13];
        int i15 = iArr[14];
        int i16 = iArr[15];
        int i17 = iArr2[0];
        int i18 = iArr2[1];
        int i19 = iArr2[2];
        int i20 = iArr2[3];
        int i21 = iArr2[4];
        int i22 = iArr2[5];
        int i23 = iArr2[6];
        int i24 = iArr2[7];
        int i25 = iArr2[8];
        int i26 = iArr2[9];
        int i27 = iArr2[10];
        int i28 = iArr2[11];
        int i29 = iArr2[12];
        int i30 = iArr2[13];
        int i31 = iArr2[14];
        int i32 = iArr2[15];
        int i33 = i + i9;
        int i34 = i3 + i11;
        int i35 = i4 + i12;
        int i36 = i5 + i13;
        int i37 = i6 + i14;
        int i38 = i7 + i15;
        int i39 = i17 + i25;
        int i40 = i18 + i26;
        int i41 = i19 + i27;
        int i42 = i20 + i28;
        int i43 = i21 + i29;
        int i44 = i22 + i30;
        int i45 = i23 + i31;
        int i46 = i24 + i32;
        long j = i;
        long j2 = i17;
        long j3 = j * j2;
        long j4 = i8;
        long j5 = i18;
        long j6 = j4 * j5;
        long j7 = i7;
        long j8 = i19;
        long j9 = i6;
        long j10 = i20;
        long j11 = j9 * j10;
        long j12 = i5;
        long j13 = i21;
        long j14 = j12 * j13;
        long j15 = i4;
        long j16 = i22;
        long j17 = j15 * j16;
        long j18 = i3;
        long j19 = i23;
        long j20 = j18 * j19;
        long j21 = i2;
        long j22 = i24;
        long j23 = j21 * j22;
        long j24 = i9;
        long j25 = i25;
        long j26 = j24 * j25;
        long j27 = i16;
        long j28 = i26;
        long j29 = j27 * j28;
        long j30 = i15;
        long j31 = i27;
        long j32 = (j30 * j31) + j29;
        long j33 = i14;
        long j34 = i28;
        long j35 = i13;
        long j36 = i29;
        long j37 = i12;
        long j38 = i30;
        long j39 = i11;
        long j40 = i31;
        long j41 = j39 * j40;
        long j42 = i10;
        long j43 = i32;
        long j44 = j42 * j43;
        long j45 = i33;
        long j46 = i39;
        long j47 = j45 * j46;
        long j48 = i8 + i16;
        long j49 = i40;
        long j50 = j48 * j49;
        long j51 = i38;
        long j52 = i41;
        long j53 = i37;
        long j54 = i42;
        long j55 = j53 * j54;
        long j56 = i36;
        long j57 = i43;
        long j58 = j56 * j57;
        long j59 = i35;
        long j60 = i44;
        long j61 = j59 * j60;
        long j62 = i34;
        long j63 = i45;
        long j64 = (j62 * j63) + j61 + j58 + j55 + (j51 * j52) + j50;
        long j65 = i2 + i10;
        long j66 = i46;
        long j67 = (j65 * j66) + j64;
        long j68 = ((j3 + j26) + j67) - (j23 + (j20 + (j17 + (j14 + (j11 + ((j7 * j8) + j6))))));
        int i47 = ((int) j68) & M28;
        long j69 = j68 >>> 28;
        long j70 = (((j44 + (j41 + ((j37 * j38) + ((j35 * j36) + ((j33 * j34) + j32))))) + j47) - j3) + j67;
        int i48 = ((int) j70) & M28;
        long j71 = (j * j5) + (j21 * j2);
        long j72 = (j39 * j43) + (j37 * j40) + (j35 * j38) + (j33 * j36) + (j30 * j34) + (j27 * j31);
        long j73 = (j45 * j49) + (j65 * j46);
        long j74 = (j62 * j66) + (j59 * j63) + (j56 * j60) + (j53 * j57) + (j51 * j54) + (j48 * j52);
        long j75 = (((j71 + ((j24 * j28) + (j42 * j25))) + j74) - ((j18 * j22) + ((j15 * j19) + ((j12 * j16) + ((j9 * j13) + ((j7 * j10) + (j4 * j8))))))) + j69;
        int i49 = ((int) j75) & M28;
        long j76 = ((j72 + j73) - j71) + j74 + (j70 >>> 28);
        int i50 = ((int) j76) & M28;
        long j77 = (j * j8) + (j21 * j5) + (j18 * j2);
        long j78 = (j37 * j43) + (j35 * j40) + (j33 * j38) + (j30 * j36) + (j27 * j34);
        long j79 = (j45 * j52) + (j65 * j49) + (j62 * j46);
        long j80 = (j59 * j66) + (j56 * j63) + (j53 * j60) + (j51 * j57) + (j48 * j54);
        long j81 = (((j77 + ((j24 * j31) + ((j42 * j28) + (j39 * j25)))) + j80) - ((j15 * j22) + ((j12 * j19) + ((j9 * j16) + ((j7 * j13) + (j4 * j10)))))) + (j75 >>> 28);
        int i51 = ((int) j81) & M28;
        long j82 = ((j78 + j79) - j77) + j80 + (j76 >>> 28);
        int i52 = ((int) j82) & M28;
        long j83 = (j * j10) + (j21 * j8) + (j18 * j5) + (j15 * j2);
        long j84 = (j35 * j43) + (j33 * j40) + (j30 * j38) + (j27 * j36);
        long j85 = (j45 * j54) + (j65 * j52) + (j62 * j49) + (j59 * j46);
        long j86 = (j56 * j66) + (j53 * j63) + (j51 * j60) + (j48 * j57);
        long j87 = (((j83 + ((j24 * j34) + ((j42 * j31) + ((j39 * j28) + (j37 * j25))))) + j86) - ((j12 * j22) + ((j9 * j19) + ((j7 * j16) + (j4 * j13))))) + (j81 >>> 28);
        int i53 = ((int) j87) & M28;
        long j88 = ((j84 + j85) - j83) + j86 + (j82 >>> 28);
        int i54 = ((int) j88) & M28;
        long j89 = (j * j13) + (j21 * j10) + (j18 * j8) + (j15 * j5) + (j12 * j2);
        long j90 = (j33 * j43) + (j30 * j40) + (j27 * j38);
        long j91 = (j45 * j57) + (j65 * j54) + (j62 * j52) + (j59 * j49) + (j56 * j46);
        long j92 = (j53 * j66) + (j51 * j63) + (j48 * j60);
        long j93 = (((j89 + ((j24 * j36) + ((j42 * j34) + ((j39 * j31) + ((j37 * j28) + (j35 * j25)))))) + j92) - ((j9 * j22) + ((j7 * j19) + (j4 * j16)))) + (j87 >>> 28);
        int i55 = ((int) j93) & M28;
        long j94 = ((j90 + j91) - j89) + j92 + (j88 >>> 28);
        int i56 = ((int) j94) & M28;
        long j95 = (j * j16) + (j21 * j13) + (j18 * j10) + (j15 * j8) + (j12 * j5) + (j9 * j2);
        long j96 = (j30 * j43) + (j27 * j40);
        long j97 = (j45 * j60) + (j65 * j57) + (j62 * j54) + (j59 * j52) + (j56 * j49) + (j53 * j46);
        long j98 = (j51 * j66) + (j48 * j63);
        long j99 = (((j95 + ((j24 * j38) + ((j42 * j36) + ((j39 * j34) + ((j37 * j31) + ((j35 * j28) + (j33 * j25))))))) + j98) - ((j7 * j22) + (j4 * j19))) + (j93 >>> 28);
        int i57 = ((int) j99) & M28;
        long j100 = ((j96 + j97) - j95) + j98 + (j94 >>> 28);
        int i58 = ((int) j100) & M28;
        long j101 = (j * j19) + (j21 * j16) + (j18 * j13) + (j15 * j10) + (j12 * j8) + (j9 * j5) + (j7 * j2);
        long j102 = j27 * j43;
        long j103 = (j45 * j63) + (j65 * j60) + (j62 * j57) + (j59 * j54) + (j56 * j52) + (j53 * j49) + (j51 * j46);
        long j104 = j48 * j66;
        long j105 = (((j101 + ((j24 * j40) + ((j42 * j38) + ((j39 * j36) + ((j37 * j34) + ((j35 * j31) + ((j33 * j28) + (j30 * j25)))))))) + j104) - (j4 * j22)) + (j99 >>> 28);
        int i59 = ((int) j105) & M28;
        long j106 = ((j102 + j103) - j101) + j104 + (j100 >>> 28);
        int i60 = ((int) j106) & M28;
        long j107 = j12 * j10;
        long j108 = j18 * j16;
        long j109 = (j * j22) + (j21 * j19) + j108 + (j15 * j13) + j107 + (j8 * j9) + (j7 * j5) + (j2 * j4);
        long j110 = (j37 * j36) + (j35 * j34) + (j31 * j33) + (j28 * j30) + (j27 * j25);
        long j111 = j109 + (j24 * j43) + (j42 * j40) + (j39 * j38) + j110 + (j105 >>> 28);
        int i61 = ((int) j111) & M28;
        long j112 = (((j45 * j66) + ((j65 * j63) + ((j62 * j60) + ((j59 * j57) + ((j56 * j54) + ((j53 * j52) + ((j51 * j49) + (j48 * j46)))))))) - j109) + (j106 >>> 28);
        int i62 = ((int) j112) & M28;
        long j113 = j112 >>> 28;
        long j114 = (j111 >>> 28) + j113 + i48;
        int i63 = ((int) j114) & M28;
        long j115 = j113 + i47;
        iArr3[0] = ((int) j115) & M28;
        iArr3[1] = i49 + ((int) (j115 >>> 28));
        iArr3[2] = i51;
        iArr3[3] = i53;
        iArr3[4] = i55;
        iArr3[5] = i57;
        iArr3[6] = i59;
        iArr3[7] = i61;
        iArr3[8] = i63;
        iArr3[9] = i50 + ((int) (j114 >>> 28));
        iArr3[10] = i52;
        iArr3[11] = i54;
        iArr3[12] = i56;
        iArr3[13] = i58;
        iArr3[14] = i60;
        iArr3[15] = i62;
    }

    public static void negate(int[] iArr, int[] iArr2) {
        sub(create(), iArr, iArr2);
    }

    public static void normalize(int[] iArr) {
        reduce(iArr, 1);
        reduce(iArr, -1);
    }

    public static void one(int[] iArr) {
        iArr[0] = 1;
        for (int i = 1; i < 16; i++) {
            iArr[i] = 0;
        }
    }

    private static void powPm3d4(int[] iArr, int[] iArr2) {
        int[] create = create();
        sqr(iArr, create);
        mul(iArr, create, create);
        int[] create2 = create();
        sqr(create, create2);
        mul(iArr, create2, create2);
        int[] create3 = create();
        sqr(create2, 3, create3);
        mul(create2, create3, create3);
        int[] create4 = create();
        sqr(create3, 3, create4);
        mul(create2, create4, create4);
        int[] create5 = create();
        sqr(create4, 9, create5);
        mul(create4, create5, create5);
        int[] create6 = create();
        sqr(create5, create6);
        mul(iArr, create6, create6);
        int[] create7 = create();
        sqr(create6, 18, create7);
        mul(create5, create7, create7);
        int[] create8 = create();
        sqr(create7, 37, create8);
        mul(create7, create8, create8);
        int[] create9 = create();
        sqr(create8, 37, create9);
        mul(create7, create9, create9);
        int[] create10 = create();
        sqr(create9, 111, create10);
        mul(create9, create10, create10);
        int[] create11 = create();
        sqr(create10, create11);
        mul(iArr, create11, create11);
        int[] create12 = create();
        sqr(create11, 223, create12);
        mul(create12, create10, iArr2);
    }

    private static void reduce(int[] iArr, int i) {
        int i2;
        int i3 = iArr[15];
        int i4 = i3 & M28;
        long j = (i3 >>> 28) + i;
        int i5 = 0;
        long j2 = j;
        while (true) {
            if (i5 >= 8) {
                break;
            }
            long j3 = j2 + (4294967295L & iArr[i5]);
            iArr[i5] = ((int) j3) & M28;
            j2 = j3 >> 28;
            i5++;
        }
        long j4 = j2 + j;
        for (i2 = 8; i2 < 15; i2++) {
            long j5 = j4 + (iArr[i2] & 4294967295L);
            iArr[i2] = ((int) j5) & M28;
            j4 = j5 >> 28;
        }
        iArr[15] = i4 + ((int) j4);
    }

    public static void sqr(int[] iArr, int i, int[] iArr2) {
        sqr(iArr, iArr2);
        while (true) {
            i--;
            if (i > 0) {
                sqr(iArr2, iArr2);
            } else {
                return;
            }
        }
    }

    public static void sqr(int[] iArr, int[] iArr2) {
        int i = iArr[0];
        int i2 = iArr[1];
        int i3 = iArr[2];
        int i4 = iArr[3];
        int i5 = iArr[4];
        int i6 = iArr[5];
        int i7 = iArr[6];
        int i8 = iArr[7];
        int i9 = iArr[8];
        int i10 = iArr[9];
        int i11 = iArr[10];
        int i12 = iArr[11];
        int i13 = iArr[12];
        int i14 = iArr[13];
        int i15 = iArr[14];
        int i16 = iArr[15];
        int i17 = i * 2;
        int i18 = i2 * 2;
        int i19 = i3 * 2;
        int i20 = i4 * 2;
        int i21 = i5 * 2;
        int i22 = i6 * 2;
        int i23 = i7 * 2;
        int i24 = i9 * 2;
        int i25 = i10 * 2;
        int i26 = i11 * 2;
        int i27 = i12 * 2;
        int i28 = i13 * 2;
        int i29 = i14 * 2;
        int i30 = i15 * 2;
        int i31 = i + i9;
        int i32 = i2 + i10;
        int i33 = i3 + i11;
        int i34 = i4 + i12;
        int i35 = i5 + i13;
        int i36 = i6 + i14;
        int i37 = i7 + i15;
        int i38 = i8 + i16;
        int i39 = i31 * 2;
        int i40 = i32 * 2;
        int i41 = i33 * 2;
        int i42 = i34 * 2;
        int i43 = i36 * 2;
        long j = i;
        long j2 = j * j;
        long j3 = i8;
        long j4 = i18;
        long j5 = j3 * j4;
        long j6 = i7;
        long j7 = i19;
        long j8 = (j6 * j7) + j5;
        long j9 = i6;
        long j10 = i20;
        long j11 = i5;
        long j12 = j11 * j11;
        long j13 = i9;
        long j14 = i16;
        long j15 = i25;
        long j16 = j14 * j15;
        long j17 = i15;
        long j18 = i26;
        long j19 = i14;
        long j20 = i27;
        long j21 = j19 * j20;
        long j22 = i13;
        long j23 = i31;
        long j24 = i38;
        long j25 = i40 & 4294967295L;
        long j26 = j24 * j25;
        long j27 = i37;
        long j28 = i41 & 4294967295L;
        long j29 = i36;
        long j30 = i42 & 4294967295L;
        long j31 = (j29 * j30) + (j27 * j28) + j26;
        long j32 = i35;
        long j33 = (j32 * j32) + j31;
        long j34 = ((j2 + (j13 * j13)) + j33) - (j12 + ((j9 * j10) + j8));
        int i44 = ((int) j34) & M28;
        long j35 = ((((j22 * j22) + (j21 + ((j17 * j18) + j16))) + (j23 * j23)) - j2) + j33;
        int i45 = ((int) j35) & M28;
        long j36 = j35 >>> 28;
        long j37 = i2;
        long j38 = i17;
        long j39 = j37 * j38;
        long j40 = j6 * j10;
        long j41 = i21;
        long j42 = j9 * j41;
        long j43 = i10;
        long j44 = i24;
        long j45 = j43 * j44;
        long j46 = i28;
        long j47 = j19 * j46;
        long j48 = i32;
        long j49 = i39 & 4294967295L;
        long j50 = (i35 * 2) & 4294967295L;
        long j51 = (j29 * j50) + (j27 * j30) + (j24 * j28);
        long j52 = (((j39 + j45) + j51) - (j42 + (j40 + (j3 * j7)))) + (j34 >>> 28);
        int i46 = ((int) j52) & M28;
        long j53 = (((j47 + ((j17 * j20) + (j14 * j18))) + (j48 * j49)) - j39) + j51 + j36;
        int i47 = ((int) j53) & M28;
        long j54 = j53 >>> 28;
        long j55 = i3;
        long j56 = (j37 * j37) + (j55 * j38);
        long j57 = i11;
        long j58 = (j43 * j43) + (j57 * j44);
        long j59 = i33;
        long j60 = (j29 * j29) + (j27 * j50) + (j24 * j30);
        long j61 = (((j56 + j58) + j60) - ((j9 * j9) + ((j6 * j41) + (j3 * j10)))) + (j52 >>> 28);
        int i48 = ((int) j61) & M28;
        long j62 = ((((j19 * j19) + ((j17 * j46) + (j14 * j20))) + ((j48 * j48) + (j59 * j49))) - j56) + j60 + j54;
        int i49 = ((int) j62) & M28;
        long j63 = i4;
        long j64 = (j55 * j4) + (j63 * j38);
        long j65 = i22;
        long j66 = i12;
        long j67 = (j57 * j15) + (j66 * j44);
        long j68 = i29;
        long j69 = j17 * j68;
        long j70 = i34;
        long j71 = j59 * j25;
        long j72 = j50 * j24;
        long j73 = i43 & 4294967295L;
        long j74 = (j27 * j73) + j72;
        long j75 = (((j64 + j67) + j74) - ((j6 * j65) + (j3 * j41))) + (j61 >>> 28);
        int i50 = ((int) j75) & M28;
        long j76 = j75 >>> 28;
        long j77 = (((j69 + (j14 * j46)) + (j71 + (j70 * j49))) - j64) + j74 + (j62 >>> 28);
        int i51 = ((int) j77) & M28;
        long j78 = (j55 * j55) + (j63 * j4) + (j11 * j38);
        long j79 = (j6 * j6) + (j65 * j3);
        long j80 = (j57 * j57) + (j66 * j15) + (j22 * j44);
        long j81 = (j27 * j27) + (j73 * j24);
        long j82 = (((j78 + j80) + j81) - j79) + j76;
        int i52 = ((int) j82) & M28;
        long j83 = j82 >>> 28;
        long j84 = ((((j17 * j17) + (j14 * j68)) + ((j59 * j59) + ((j70 * j25) + (j32 * j49)))) - j78) + j81 + (j77 >>> 28);
        int i53 = ((int) j84) & M28;
        long j85 = (j63 * j7) + (j11 * j4) + (j9 * j38);
        long j86 = (j66 * j18) + (j22 * j15) + (j19 * j44);
        long j87 = ((i37 * 2) & 4294967295L) * j24;
        long j88 = (((j85 + j86) + j87) - (i23 * j3)) + j83;
        int i54 = ((int) j88) & M28;
        long j89 = (((i30 * j14) + ((j70 * j28) + ((j32 * j25) + (j29 * j49)))) - j85) + j87 + (j84 >>> 28);
        int i55 = ((int) j89) & M28;
        long j90 = (j63 * j63) + (j11 * j7) + (j9 * j4) + (j6 * j38);
        long j91 = j24 * j24;
        long j92 = (((j90 + ((j66 * j66) + ((j22 * j18) + ((j19 * j15) + (j17 * j44))))) + j91) - (j3 * j3)) + (j88 >>> 28);
        int i56 = ((int) j92) & M28;
        long j93 = (((j14 * j14) + ((j70 * j70) + ((j32 * j28) + ((j29 * j25) + (j27 * j49))))) - j90) + j91 + (j89 >>> 28);
        int i57 = ((int) j93) & M28;
        long j94 = (j11 * j10) + (j9 * j7) + (j6 * j4) + (j3 * j38);
        long j95 = (j22 * j20) + (j19 * j18) + (j17 * j15) + (j44 * j14) + j94 + (j92 >>> 28);
        int i58 = ((int) j95) & M28;
        long j96 = (((j32 * j30) + ((j29 * j28) + ((j27 * j25) + (j24 * j49)))) - j94) + (j93 >>> 28);
        int i59 = ((int) j96) & M28;
        long j97 = j96 >>> 28;
        long j98 = (j95 >>> 28) + j97 + i45;
        int i60 = ((int) j98) & M28;
        long j99 = j97 + i44;
        iArr2[0] = ((int) j99) & M28;
        iArr2[1] = i46 + ((int) (j99 >>> 28));
        iArr2[2] = i48;
        iArr2[3] = i50;
        iArr2[4] = i52;
        iArr2[5] = i54;
        iArr2[6] = i56;
        iArr2[7] = i58;
        iArr2[8] = i60;
        iArr2[9] = i47 + ((int) (j98 >>> 28));
        iArr2[10] = i49;
        iArr2[11] = i51;
        iArr2[12] = i53;
        iArr2[13] = i55;
        iArr2[14] = i57;
        iArr2[15] = i59;
    }

    public static boolean sqrtRatioVar(int[] iArr, int[] iArr2, int[] iArr3) {
        int[] create = create();
        int[] create2 = create();
        sqr(iArr, create);
        mul(create, iArr2, create);
        sqr(create, create2);
        mul(create, iArr, create);
        mul(create2, iArr, create2);
        mul(create2, iArr2, create2);
        int[] create3 = create();
        powPm3d4(create2, create3);
        mul(create3, create, create3);
        int[] create4 = create();
        sqr(create3, create4);
        mul(create4, iArr2, create4);
        sub(iArr, create4, create4);
        normalize(create4);
        if (isZeroVar(create4)) {
            copy(create3, 0, iArr3, 0);
            return true;
        }
        return false;
    }

    public static void sub(int[] iArr, int[] iArr2, int[] iArr3) {
        int i = iArr[0];
        int i2 = iArr[1];
        int i3 = iArr[2];
        int i4 = iArr[3];
        int i5 = iArr[4];
        int i6 = iArr[5];
        int i7 = iArr[6];
        int i8 = iArr[7];
        int i9 = iArr[8];
        int i10 = iArr[9];
        int i11 = iArr[10];
        int i12 = iArr[11];
        int i13 = iArr[12];
        int i14 = iArr[13];
        int i15 = iArr[14];
        int i16 = iArr[15];
        int i17 = iArr2[0];
        int i18 = iArr2[1];
        int i19 = iArr2[2];
        int i20 = iArr2[3];
        int i21 = iArr2[4];
        int i22 = iArr2[5];
        int i23 = iArr2[6];
        int i24 = iArr2[7];
        int i25 = iArr2[8];
        int i26 = iArr2[9];
        int i27 = iArr2[10];
        int i28 = iArr2[11];
        int i29 = iArr2[12];
        int i30 = iArr2[13];
        int i31 = iArr2[14];
        int i32 = (i2 + 536870910) - i18;
        int i33 = (i6 + 536870910) - i22;
        int i34 = (i10 + 536870910) - i26;
        int i35 = (i14 + 536870910) - i30;
        int i36 = ((i3 + 536870910) - i19) + (i32 >>> 28);
        int i37 = i32 & M28;
        int i38 = ((i7 + 536870910) - i23) + (i33 >>> 28);
        int i39 = i33 & M28;
        int i40 = ((i11 + 536870910) - i27) + (i34 >>> 28);
        int i41 = i34 & M28;
        int i42 = ((i15 + 536870910) - i31) + (i35 >>> 28);
        int i43 = i35 & M28;
        int i44 = ((i4 + 536870910) - i20) + (i36 >>> 28);
        int i45 = i36 & M28;
        int i46 = ((i8 + 536870910) - i24) + (i38 >>> 28);
        int i47 = i38 & M28;
        int i48 = ((i12 + 536870910) - i28) + (i40 >>> 28);
        int i49 = i40 & M28;
        int i50 = ((i16 + 536870910) - iArr2[15]) + (i42 >>> 28);
        int i51 = i42 & M28;
        int i52 = i50 >>> 28;
        int i53 = i50 & M28;
        int i54 = ((i + 536870910) - i17) + i52;
        int i55 = ((i5 + 536870910) - i21) + (i44 >>> 28);
        int i56 = i44 & M28;
        int i57 = ((i9 + 536870908) - i25) + i52 + (i46 >>> 28);
        int i58 = i46 & M28;
        int i59 = ((i13 + 536870910) - i29) + (i48 >>> 28);
        int i60 = i48 & M28;
        int i61 = i37 + (i54 >>> 28);
        int i62 = i54 & M28;
        int i63 = i39 + (i55 >>> 28);
        int i64 = i55 & M28;
        int i65 = i41 + (i57 >>> 28);
        int i66 = i57 & M28;
        int i67 = i59 & M28;
        iArr3[0] = i62;
        iArr3[1] = i61;
        iArr3[2] = i45;
        iArr3[3] = i56;
        iArr3[4] = i64;
        iArr3[5] = i63;
        iArr3[6] = i47;
        iArr3[7] = i58;
        iArr3[8] = i66;
        iArr3[9] = i65;
        iArr3[10] = i49;
        iArr3[11] = i60;
        iArr3[12] = i67;
        iArr3[13] = i43 + (i59 >>> 28);
        iArr3[14] = i51;
        iArr3[15] = i53;
    }

    public static void subOne(int[] iArr) {
        int[] create = create();
        create[0] = 1;
        sub(iArr, create, iArr);
    }

    public static void zero(int[] iArr) {
        for (int i = 0; i < 16; i++) {
            iArr[i] = 0;
        }
    }
}
