package org.bouncycastle.math.ec.rfc8032;

import java.security.SecureRandom;
import kotlinx.coroutines.internal.LockFreeTaskQueueCore;
import org.bouncycastle.crypto.Xof;
import org.bouncycastle.crypto.digests.SHAKEDigest;
import org.bouncycastle.math.ec.rfc7748.X448;
import org.bouncycastle.math.ec.rfc7748.X448Field;
import org.bouncycastle.math.raw.Nat;
import org.bouncycastle.util.Arrays;
/* loaded from: classes5.dex */
public abstract class Ed448 {
    private static final int C_d = -39081;
    private static final int L4_0 = 43969588;
    private static final int L4_1 = 30366549;
    private static final int L4_2 = 163752818;
    private static final int L4_3 = 258169998;
    private static final int L4_4 = 96434764;
    private static final int L4_5 = 227822194;
    private static final int L4_6 = 149865618;
    private static final int L4_7 = 550336261;
    private static final int L_0 = 78101261;
    private static final int L_1 = 141809365;
    private static final int L_2 = 175155932;
    private static final int L_3 = 64542499;
    private static final int L_4 = 158326419;
    private static final int L_5 = 191173276;
    private static final int L_6 = 104575268;
    private static final int L_7 = 137584065;
    private static final long M26L = 67108863;
    private static final long M28L = 268435455;
    private static final long M32L = 4294967295L;
    private static final int POINT_BYTES = 57;
    private static final int PRECOMP_BLOCKS = 5;
    private static final int PRECOMP_MASK = 15;
    private static final int PRECOMP_POINTS = 16;
    private static final int PRECOMP_SPACING = 18;
    private static final int PRECOMP_TEETH = 5;
    public static final int PREHASH_SIZE = 64;
    public static final int PUBLIC_KEY_SIZE = 57;
    private static final int SCALAR_BYTES = 57;
    private static final int SCALAR_INTS = 14;
    public static final int SECRET_KEY_SIZE = 57;
    public static final int SIGNATURE_SIZE = 114;
    private static final int WNAF_WIDTH_BASE = 7;
    private static final byte[] DOM4_PREFIX = {83, 105, 103, 69, 100, 52, 52, 56};
    private static final int[] P = {-1, -1, -1, -1, -1, -1, -1, -2, -1, -1, -1, -1, -1, -1};
    private static final int[] L = {-1420278541, 595116690, -1916432555, 560775794, -1361693040, -1001465015, 2093622249, -1, -1, -1, -1, -1, -1, LockFreeTaskQueueCore.MAX_CAPACITY_MASK};
    private static final int[] B_x = {118276190, 40534716, 9670182, 135141552, 85017403, 259173222, 68333082, 171784774, 174973732, 15824510, 73756743, 57518561, 94773951, 248652241, 107736333, 82941708};
    private static final int[] B_y = {36764180, 8885695, 130592152, 20104429, 163904957, 30304195, 121295871, 5901357, 125344798, 171541512, 175338348, 209069246, 3626697, 38307682, 24032956, 110359655};
    private static final Object precompLock = new Object();
    private static PointExt[] precompBaseTable = null;
    private static int[] precompBase = null;

    /* loaded from: classes5.dex */
    public static final class Algorithm {
        public static final int Ed448 = 0;
        public static final int Ed448ph = 1;
    }

    /* loaded from: classes5.dex */
    private static class F extends X448Field {
        private F() {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes5.dex */
    public static class PointExt {
        int[] x;
        int[] y;
        int[] z;

        private PointExt() {
            this.x = X448Field.create();
            this.y = X448Field.create();
            this.z = X448Field.create();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes5.dex */
    public static class PointPrecomp {
        int[] x;
        int[] y;

        private PointPrecomp() {
            this.x = X448Field.create();
            this.y = X448Field.create();
        }
    }

    private static byte[] calculateS(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        int[] iArr = new int[28];
        decodeScalar(bArr, 0, iArr);
        int[] iArr2 = new int[14];
        decodeScalar(bArr2, 0, iArr2);
        int[] iArr3 = new int[14];
        decodeScalar(bArr3, 0, iArr3);
        Nat.mulAddTo(14, iArr2, iArr3, iArr);
        byte[] bArr4 = new byte[114];
        for (int i = 0; i < iArr.length; i++) {
            encode32(iArr[i], bArr4, i * 4);
        }
        return reduceScalar(bArr4);
    }

    private static boolean checkContextVar(byte[] bArr) {
        return bArr != null && bArr.length < 256;
    }

    private static int checkPoint(int[] iArr, int[] iArr2) {
        int[] create = X448Field.create();
        int[] create2 = X448Field.create();
        int[] create3 = X448Field.create();
        X448Field.sqr(iArr, create2);
        X448Field.sqr(iArr2, create3);
        X448Field.mul(create2, create3, create);
        X448Field.add(create2, create3, create2);
        X448Field.mul(create, 39081, create);
        X448Field.subOne(create);
        X448Field.add(create, create2, create);
        X448Field.normalize(create);
        return X448Field.isZero(create);
    }

    private static int checkPoint(int[] iArr, int[] iArr2, int[] iArr3) {
        int[] create = X448Field.create();
        int[] create2 = X448Field.create();
        int[] create3 = X448Field.create();
        int[] create4 = X448Field.create();
        X448Field.sqr(iArr, create2);
        X448Field.sqr(iArr2, create3);
        X448Field.sqr(iArr3, create4);
        X448Field.mul(create2, create3, create);
        X448Field.add(create2, create3, create2);
        X448Field.mul(create2, create4, create2);
        X448Field.sqr(create4, create4);
        X448Field.mul(create, 39081, create);
        X448Field.sub(create, create4, create);
        X448Field.add(create, create2, create);
        X448Field.normalize(create);
        return X448Field.isZero(create);
    }

    private static boolean checkPointVar(byte[] bArr) {
        if ((bArr[56] & Byte.MAX_VALUE) != 0) {
            return false;
        }
        int[] iArr = new int[14];
        decode32(bArr, 0, iArr, 0, 14);
        return !Nat.gte(14, iArr, P);
    }

    private static boolean checkScalarVar(byte[] bArr) {
        if (bArr[56] != 0) {
            return false;
        }
        int[] iArr = new int[14];
        decodeScalar(bArr, 0, iArr);
        return !Nat.gte(14, iArr, L);
    }

    public static Xof createPrehash() {
        return createXof();
    }

    private static Xof createXof() {
        return new SHAKEDigest(256);
    }

    private static int decode16(byte[] bArr, int i) {
        return ((bArr[i + 1] & 255) << 8) | (bArr[i] & 255);
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

    private static void decode32(byte[] bArr, int i, int[] iArr, int i2, int i3) {
        for (int i4 = 0; i4 < i3; i4++) {
            iArr[i2 + i4] = decode32(bArr, (i4 * 4) + i);
        }
    }

    private static boolean decodePointVar(byte[] bArr, int i, boolean z, PointExt pointExt) {
        byte[] copyOfRange = Arrays.copyOfRange(bArr, i, i + 57);
        boolean z2 = false;
        if (!checkPointVar(copyOfRange)) {
            return false;
        }
        int i2 = (copyOfRange[56] & 128) >>> 7;
        copyOfRange[56] = (byte) (copyOfRange[56] & Byte.MAX_VALUE);
        X448Field.decode(copyOfRange, 0, pointExt.y);
        int[] create = X448Field.create();
        int[] create2 = X448Field.create();
        X448Field.sqr(pointExt.y, create);
        X448Field.mul(create, 39081, create2);
        X448Field.negate(create, create);
        X448Field.addOne(create);
        X448Field.addOne(create2);
        if (!X448Field.sqrtRatioVar(create, create2, pointExt.x)) {
            return false;
        }
        X448Field.normalize(pointExt.x);
        if (i2 == 1 && X448Field.isZeroVar(pointExt.x)) {
            return false;
        }
        if (i2 != (pointExt.x[0] & 1)) {
            z2 = true;
        }
        if (z ^ z2) {
            int[] iArr = pointExt.x;
            X448Field.negate(iArr, iArr);
        }
        pointExtendXY(pointExt);
        return true;
    }

    private static void decodeScalar(byte[] bArr, int i, int[] iArr) {
        decode32(bArr, i, iArr, 0, 14);
    }

    private static void dom4(Xof xof, byte b, byte[] bArr) {
        byte[] bArr2 = DOM4_PREFIX;
        int length = bArr2.length;
        int i = length + 2;
        byte[] bArr3 = new byte[bArr.length + i];
        System.arraycopy(bArr2, 0, bArr3, 0, length);
        bArr3[length] = b;
        bArr3[length + 1] = (byte) bArr.length;
        System.arraycopy(bArr, 0, bArr3, i, bArr.length);
        xof.update(bArr3, 0, bArr3.length);
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

    private static void encode56(long j, byte[] bArr, int i) {
        encode32((int) j, bArr, i);
        encode24((int) (j >>> 32), bArr, i + 4);
    }

    private static int encodePoint(PointExt pointExt, byte[] bArr, int i) {
        int[] create = X448Field.create();
        int[] create2 = X448Field.create();
        X448Field.inv(pointExt.z, create2);
        X448Field.mul(pointExt.x, create2, create);
        X448Field.mul(pointExt.y, create2, create2);
        X448Field.normalize(create);
        X448Field.normalize(create2);
        int checkPoint = checkPoint(create, create2);
        X448Field.encode(create2, bArr, i);
        bArr[(i + 57) - 1] = (byte) ((create[0] & 1) << 7);
        return checkPoint;
    }

    public static void generatePrivateKey(SecureRandom secureRandom, byte[] bArr) {
        secureRandom.nextBytes(bArr);
    }

    public static void generatePublicKey(byte[] bArr, int i, byte[] bArr2, int i2) {
        Xof createXof = createXof();
        byte[] bArr3 = new byte[114];
        createXof.update(bArr, i, 57);
        createXof.doFinal(bArr3, 0, bArr3.length);
        byte[] bArr4 = new byte[57];
        pruneScalar(bArr3, 0, bArr4);
        scalarMultBaseEncoded(bArr4, bArr2, i2);
    }

    private static int getWindow4(int[] iArr, int i) {
        return (iArr[i >>> 3] >>> ((i & 7) << 2)) & 15;
    }

    private static byte[] getWnafVar(int[] iArr, int i) {
        int[] iArr2 = new int[28];
        int length = iArr2.length;
        int i2 = 0;
        int i3 = 14;
        int i4 = 0;
        while (true) {
            i3--;
            if (i3 < 0) {
                break;
            }
            int i5 = iArr[i3];
            int i6 = length - 1;
            iArr2[i6] = (i4 << 16) | (i5 >>> 16);
            length = i6 - 1;
            iArr2[length] = i5;
            i4 = i5;
        }
        byte[] bArr = new byte[447];
        int i7 = 1 << i;
        int i8 = i7 - 1;
        int i9 = i7 >>> 1;
        int i10 = 0;
        int i11 = 0;
        while (i2 < iArr2.length) {
            int i12 = iArr2[i2];
            while (i10 < 16) {
                int i13 = i12 >>> i10;
                if ((i13 & 1) == i11) {
                    i10++;
                } else {
                    int i14 = (i13 & i8) + i11;
                    int i15 = i14 & i9;
                    int i16 = i14 - (i15 << 1);
                    i11 = i15 >>> (i - 1);
                    bArr[(i2 << 4) + i10] = (byte) i16;
                    i10 += i;
                }
            }
            i2++;
            i10 -= 16;
        }
        return bArr;
    }

    private static void implSign(Xof xof, byte[] bArr, byte[] bArr2, byte[] bArr3, int i, byte[] bArr4, byte b, byte[] bArr5, int i2, int i3, byte[] bArr6, int i4) {
        dom4(xof, b, bArr4);
        xof.update(bArr, 57, 57);
        xof.update(bArr5, i2, i3);
        xof.doFinal(bArr, 0, bArr.length);
        byte[] reduceScalar = reduceScalar(bArr);
        byte[] bArr7 = new byte[57];
        scalarMultBaseEncoded(reduceScalar, bArr7, 0);
        dom4(xof, b, bArr4);
        xof.update(bArr7, 0, 57);
        xof.update(bArr3, i, 57);
        xof.update(bArr5, i2, i3);
        xof.doFinal(bArr, 0, bArr.length);
        byte[] calculateS = calculateS(reduceScalar, reduceScalar(bArr), bArr2);
        System.arraycopy(bArr7, 0, bArr6, i4, 57);
        System.arraycopy(calculateS, 0, bArr6, i4 + 57, 57);
    }

    private static void implSign(byte[] bArr, int i, byte[] bArr2, byte b, byte[] bArr3, int i2, int i3, byte[] bArr4, int i4) {
        if (checkContextVar(bArr2)) {
            Xof createXof = createXof();
            byte[] bArr5 = new byte[114];
            createXof.update(bArr, i, 57);
            createXof.doFinal(bArr5, 0, bArr5.length);
            byte[] bArr6 = new byte[57];
            pruneScalar(bArr5, 0, bArr6);
            byte[] bArr7 = new byte[57];
            scalarMultBaseEncoded(bArr6, bArr7, 0);
            implSign(createXof, bArr5, bArr6, bArr7, 0, bArr2, b, bArr3, i2, i3, bArr4, i4);
            return;
        }
        throw new IllegalArgumentException("ctx");
    }

    private static void implSign(byte[] bArr, int i, byte[] bArr2, int i2, byte[] bArr3, byte b, byte[] bArr4, int i3, int i4, byte[] bArr5, int i5) {
        if (checkContextVar(bArr3)) {
            Xof createXof = createXof();
            byte[] bArr6 = new byte[114];
            createXof.update(bArr, i, 57);
            createXof.doFinal(bArr6, 0, bArr6.length);
            byte[] bArr7 = new byte[57];
            pruneScalar(bArr6, 0, bArr7);
            implSign(createXof, bArr6, bArr7, bArr2, i2, bArr3, b, bArr4, i3, i4, bArr5, i5);
            return;
        }
        throw new IllegalArgumentException("ctx");
    }

    private static boolean implVerify(byte[] bArr, int i, byte[] bArr2, int i2, byte[] bArr3, byte b, byte[] bArr4, int i3, int i4) {
        if (checkContextVar(bArr3)) {
            int i5 = i + 57;
            byte[] copyOfRange = Arrays.copyOfRange(bArr, i, i5);
            byte[] copyOfRange2 = Arrays.copyOfRange(bArr, i5, i + 114);
            if (!checkPointVar(copyOfRange) || !checkScalarVar(copyOfRange2)) {
                return false;
            }
            PointExt pointExt = new PointExt();
            if (!decodePointVar(bArr2, i2, true, pointExt)) {
                return false;
            }
            Xof createXof = createXof();
            byte[] bArr5 = new byte[114];
            dom4(createXof, b, bArr3);
            createXof.update(copyOfRange, 0, 57);
            createXof.update(bArr2, i2, 57);
            createXof.update(bArr4, i3, i4);
            createXof.doFinal(bArr5, 0, bArr5.length);
            byte[] reduceScalar = reduceScalar(bArr5);
            int[] iArr = new int[14];
            decodeScalar(copyOfRange2, 0, iArr);
            int[] iArr2 = new int[14];
            decodeScalar(reduceScalar, 0, iArr2);
            PointExt pointExt2 = new PointExt();
            scalarMultStrausVar(iArr, iArr2, pointExt, pointExt2);
            byte[] bArr6 = new byte[57];
            return encodePoint(pointExt2, bArr6, 0) != 0 && Arrays.areEqual(bArr6, copyOfRange);
        }
        throw new IllegalArgumentException("ctx");
    }

    private static void pointAdd(PointExt pointExt, PointExt pointExt2) {
        int[] create = X448Field.create();
        int[] create2 = X448Field.create();
        int[] create3 = X448Field.create();
        int[] create4 = X448Field.create();
        int[] create5 = X448Field.create();
        int[] create6 = X448Field.create();
        int[] create7 = X448Field.create();
        int[] create8 = X448Field.create();
        X448Field.mul(pointExt.z, pointExt2.z, create);
        X448Field.sqr(create, create2);
        X448Field.mul(pointExt.x, pointExt2.x, create3);
        X448Field.mul(pointExt.y, pointExt2.y, create4);
        X448Field.mul(create3, create4, create5);
        X448Field.mul(create5, 39081, create5);
        X448Field.add(create2, create5, create6);
        X448Field.sub(create2, create5, create7);
        X448Field.add(pointExt.x, pointExt.y, create2);
        X448Field.add(pointExt2.x, pointExt2.y, create5);
        X448Field.mul(create2, create5, create8);
        X448Field.add(create4, create3, create2);
        X448Field.sub(create4, create3, create5);
        X448Field.carry(create2);
        X448Field.sub(create8, create2, create8);
        X448Field.mul(create8, create, create8);
        X448Field.mul(create5, create, create5);
        X448Field.mul(create6, create8, pointExt2.x);
        X448Field.mul(create5, create7, pointExt2.y);
        X448Field.mul(create6, create7, pointExt2.z);
    }

    private static void pointAddPrecomp(PointPrecomp pointPrecomp, PointExt pointExt) {
        int[] create = X448Field.create();
        int[] create2 = X448Field.create();
        int[] create3 = X448Field.create();
        int[] create4 = X448Field.create();
        int[] create5 = X448Field.create();
        int[] create6 = X448Field.create();
        int[] create7 = X448Field.create();
        X448Field.sqr(pointExt.z, create);
        X448Field.mul(pointPrecomp.x, pointExt.x, create2);
        X448Field.mul(pointPrecomp.y, pointExt.y, create3);
        X448Field.mul(create2, create3, create4);
        X448Field.mul(create4, 39081, create4);
        X448Field.add(create, create4, create5);
        X448Field.sub(create, create4, create6);
        X448Field.add(pointPrecomp.x, pointPrecomp.y, create);
        X448Field.add(pointExt.x, pointExt.y, create4);
        X448Field.mul(create, create4, create7);
        X448Field.add(create3, create2, create);
        X448Field.sub(create3, create2, create4);
        X448Field.carry(create);
        X448Field.sub(create7, create, create7);
        X448Field.mul(create7, pointExt.z, create7);
        X448Field.mul(create4, pointExt.z, create4);
        X448Field.mul(create5, create7, pointExt.x);
        X448Field.mul(create4, create6, pointExt.y);
        X448Field.mul(create5, create6, pointExt.z);
    }

    private static void pointAddVar(boolean z, PointExt pointExt, PointExt pointExt2) {
        int[] iArr;
        int[] iArr2;
        int[] iArr3;
        int[] iArr4;
        int[] create = X448Field.create();
        int[] create2 = X448Field.create();
        int[] create3 = X448Field.create();
        int[] create4 = X448Field.create();
        int[] create5 = X448Field.create();
        int[] create6 = X448Field.create();
        int[] create7 = X448Field.create();
        int[] create8 = X448Field.create();
        if (z) {
            X448Field.sub(pointExt.y, pointExt.x, create8);
            iArr2 = create2;
            iArr = create5;
            iArr4 = create6;
            iArr3 = create7;
        } else {
            X448Field.add(pointExt.y, pointExt.x, create8);
            iArr = create2;
            iArr2 = create5;
            iArr3 = create6;
            iArr4 = create7;
        }
        X448Field.mul(pointExt.z, pointExt2.z, create);
        X448Field.sqr(create, create2);
        X448Field.mul(pointExt.x, pointExt2.x, create3);
        X448Field.mul(pointExt.y, pointExt2.y, create4);
        X448Field.mul(create3, create4, create5);
        X448Field.mul(create5, 39081, create5);
        X448Field.add(create2, create5, iArr3);
        X448Field.sub(create2, create5, iArr4);
        X448Field.add(pointExt2.x, pointExt2.y, create5);
        X448Field.mul(create8, create5, create8);
        X448Field.add(create4, create3, iArr);
        X448Field.sub(create4, create3, iArr2);
        X448Field.carry(iArr);
        X448Field.sub(create8, create2, create8);
        X448Field.mul(create8, create, create8);
        X448Field.mul(create5, create, create5);
        X448Field.mul(create6, create8, pointExt2.x);
        X448Field.mul(create5, create7, pointExt2.y);
        X448Field.mul(create6, create7, pointExt2.z);
    }

    private static PointExt pointCopy(PointExt pointExt) {
        PointExt pointExt2 = new PointExt();
        pointCopy(pointExt, pointExt2);
        return pointExt2;
    }

    private static void pointCopy(PointExt pointExt, PointExt pointExt2) {
        X448Field.copy(pointExt.x, 0, pointExt2.x, 0);
        X448Field.copy(pointExt.y, 0, pointExt2.y, 0);
        X448Field.copy(pointExt.z, 0, pointExt2.z, 0);
    }

    private static void pointDouble(PointExt pointExt) {
        int[] create = X448Field.create();
        int[] create2 = X448Field.create();
        int[] create3 = X448Field.create();
        int[] create4 = X448Field.create();
        int[] create5 = X448Field.create();
        int[] create6 = X448Field.create();
        X448Field.add(pointExt.x, pointExt.y, create);
        X448Field.sqr(create, create);
        X448Field.sqr(pointExt.x, create2);
        X448Field.sqr(pointExt.y, create3);
        X448Field.add(create2, create3, create4);
        X448Field.carry(create4);
        X448Field.sqr(pointExt.z, create5);
        X448Field.add(create5, create5, create5);
        X448Field.carry(create5);
        X448Field.sub(create4, create5, create6);
        X448Field.sub(create, create4, create);
        X448Field.sub(create2, create3, create2);
        X448Field.mul(create, create6, pointExt.x);
        X448Field.mul(create4, create2, pointExt.y);
        X448Field.mul(create4, create6, pointExt.z);
    }

    private static void pointExtendXY(PointExt pointExt) {
        X448Field.one(pointExt.z);
    }

    private static void pointLookup(int i, int i2, PointPrecomp pointPrecomp) {
        int i3 = i * 16 * 2 * 16;
        for (int i4 = 0; i4 < 16; i4++) {
            int i5 = ((i4 ^ i2) - 1) >> 31;
            X448Field.cmov(i5, precompBase, i3, pointPrecomp.x, 0);
            int i6 = i3 + 16;
            X448Field.cmov(i5, precompBase, i6, pointPrecomp.y, 0);
            i3 = i6 + 16;
        }
    }

    private static void pointLookup(int[] iArr, int i, int[] iArr2, PointExt pointExt) {
        int window4 = getWindow4(iArr, i);
        int i2 = (window4 >>> 3) ^ 1;
        int i3 = (window4 ^ (-i2)) & 7;
        int i4 = 0;
        for (int i5 = 0; i5 < 8; i5++) {
            int i6 = ((i5 ^ i3) - 1) >> 31;
            X448Field.cmov(i6, iArr2, i4, pointExt.x, 0);
            int i7 = i4 + 16;
            X448Field.cmov(i6, iArr2, i7, pointExt.y, 0);
            int i8 = i7 + 16;
            X448Field.cmov(i6, iArr2, i8, pointExt.z, 0);
            i4 = i8 + 16;
        }
        X448Field.cnegate(i2, pointExt.x);
    }

    private static int[] pointPrecompute(PointExt pointExt, int i) {
        PointExt pointCopy = pointCopy(pointExt);
        PointExt pointCopy2 = pointCopy(pointCopy);
        pointDouble(pointCopy2);
        int[] createTable = X448Field.createTable(i * 3);
        int i2 = 0;
        int i3 = 0;
        while (true) {
            X448Field.copy(pointCopy.x, 0, createTable, i2);
            int i4 = i2 + 16;
            X448Field.copy(pointCopy.y, 0, createTable, i4);
            int i5 = i4 + 16;
            X448Field.copy(pointCopy.z, 0, createTable, i5);
            i2 = i5 + 16;
            i3++;
            if (i3 == i) {
                return createTable;
            }
            pointAdd(pointCopy2, pointCopy);
        }
    }

    private static PointExt[] pointPrecomputeVar(PointExt pointExt, int i) {
        PointExt pointCopy = pointCopy(pointExt);
        pointDouble(pointCopy);
        PointExt[] pointExtArr = new PointExt[i];
        pointExtArr[0] = pointCopy(pointExt);
        for (int i2 = 1; i2 < i; i2++) {
            pointExtArr[i2] = pointCopy(pointExtArr[i2 - 1]);
            pointAddVar(false, pointCopy, pointExtArr[i2]);
        }
        return pointExtArr;
    }

    private static void pointSetNeutral(PointExt pointExt) {
        X448Field.zero(pointExt.x);
        X448Field.one(pointExt.y);
        X448Field.one(pointExt.z);
    }

    public static void precompute() {
        synchronized (precompLock) {
            if (precompBase != null) {
                return;
            }
            PointExt pointExt = new PointExt();
            X448Field.copy(B_x, 0, pointExt.x, 0);
            X448Field.copy(B_y, 0, pointExt.y, 0);
            pointExtendXY(pointExt);
            precompBaseTable = pointPrecomputeVar(pointExt, 32);
            precompBase = X448Field.createTable(160);
            int i = 0;
            int i2 = 0;
            while (i < 5) {
                PointExt[] pointExtArr = new PointExt[5];
                PointExt pointExt2 = new PointExt();
                pointSetNeutral(pointExt2);
                int i3 = 0;
                while (true) {
                    if (i3 >= 5) {
                        break;
                    }
                    pointAddVar(true, pointExt, pointExt2);
                    pointDouble(pointExt);
                    pointExtArr[i3] = pointCopy(pointExt);
                    if (i + i3 != 8) {
                        for (int i4 = 1; i4 < 18; i4++) {
                            pointDouble(pointExt);
                        }
                    }
                    i3++;
                }
                PointExt[] pointExtArr2 = new PointExt[16];
                pointExtArr2[0] = pointExt2;
                int i5 = 0;
                int i6 = 1;
                while (i5 < 4) {
                    int i7 = 1 << i5;
                    int i8 = i6;
                    int i9 = 0;
                    while (i9 < i7) {
                        pointExtArr2[i8] = pointCopy(pointExtArr2[i8 - i7]);
                        pointAddVar(false, pointExtArr[i5], pointExtArr2[i8]);
                        i9++;
                        i8++;
                    }
                    i5++;
                    i6 = i8;
                }
                int[] createTable = X448Field.createTable(16);
                int[] create = X448Field.create();
                X448Field.copy(pointExtArr2[0].z, 0, create, 0);
                X448Field.copy(create, 0, createTable, 0);
                int i10 = 0;
                while (true) {
                    i10++;
                    if (i10 >= 16) {
                        break;
                    }
                    X448Field.mul(create, pointExtArr2[i10].z, create);
                    X448Field.copy(create, 0, createTable, i10 * 16);
                }
                X448Field.invVar(create, create);
                int i11 = i10 - 1;
                int[] create2 = X448Field.create();
                while (i11 > 0) {
                    int i12 = i11 - 1;
                    X448Field.copy(createTable, i12 * 16, create2, 0);
                    X448Field.mul(create2, create, create2);
                    X448Field.copy(create2, 0, createTable, i11 * 16);
                    X448Field.mul(create, pointExtArr2[i11].z, create);
                    i11 = i12;
                }
                X448Field.copy(create, 0, createTable, 0);
                int i13 = i2;
                for (int i14 = 0; i14 < 16; i14++) {
                    PointExt pointExt3 = pointExtArr2[i14];
                    X448Field.copy(createTable, i14 * 16, pointExt3.z, 0);
                    int[] iArr = pointExt3.x;
                    X448Field.mul(iArr, pointExt3.z, iArr);
                    int[] iArr2 = pointExt3.y;
                    X448Field.mul(iArr2, pointExt3.z, iArr2);
                    X448Field.copy(pointExt3.x, 0, precompBase, i13);
                    int i15 = i13 + 16;
                    X448Field.copy(pointExt3.y, 0, precompBase, i15);
                    i13 = i15 + 16;
                }
                i++;
                i2 = i13;
            }
        }
    }

    private static void pruneScalar(byte[] bArr, int i, byte[] bArr2) {
        System.arraycopy(bArr, i, bArr2, 0, 56);
        bArr2[0] = (byte) (bArr2[0] & 252);
        bArr2[55] = (byte) (bArr2[55] | 128);
        bArr2[56] = 0;
    }

    private static byte[] reduceScalar(byte[] bArr) {
        long decode32 = decode32(bArr, 0) & 4294967295L;
        long decode322 = decode32(bArr, 7) & 4294967295L;
        long decode24 = (decode24(bArr, 11) << 4) & 4294967295L;
        long decode323 = decode32(bArr, 14) & 4294967295L;
        long decode242 = (decode24(bArr, 18) << 4) & 4294967295L;
        long decode324 = decode32(bArr, 21) & 4294967295L;
        long decode243 = (decode24(bArr, 25) << 4) & 4294967295L;
        long decode325 = decode32(bArr, 28) & 4294967295L;
        long decode244 = (decode24(bArr, 32) << 4) & 4294967295L;
        long decode326 = decode32(bArr, 35) & 4294967295L;
        long decode245 = (decode24(bArr, 39) << 4) & 4294967295L;
        long decode327 = decode32(bArr, 42) & 4294967295L;
        long decode246 = (decode24(bArr, 46) << 4) & 4294967295L;
        long decode328 = decode32(bArr, 49) & 4294967295L;
        long decode247 = (decode24(bArr, 53) << 4) & 4294967295L;
        long decode248 = (decode24(bArr, 74) << 4) & 4294967295L;
        long decode329 = decode32(bArr, 77) & 4294967295L;
        long decode249 = (decode24(bArr, 81) << 4) & 4294967295L;
        long decode3210 = decode32(bArr, 84) & 4294967295L;
        long decode2410 = (decode24(bArr, 88) << 4) & 4294967295L;
        long decode3211 = decode32(bArr, 91) & 4294967295L;
        long decode2411 = (decode24(bArr, 95) << 4) & 4294967295L;
        long decode3212 = decode32(bArr, 98) & 4294967295L;
        long decode2412 = (decode24(bArr, 102) << 4) & 4294967295L;
        long decode3213 = decode32(bArr, 105) & 4294967295L;
        long decode2413 = (decode24(bArr, 109) << 4) & 4294967295L;
        long decode16 = decode16(bArr, 112) & 4294967295L;
        long j = decode2413 + (decode3213 >>> 28);
        long j2 = decode3213 & M28L;
        long j3 = j * 30366549;
        long decode3214 = j3 + (decode16 * 43969588) + (decode32(bArr, 56) & 4294967295L);
        long j4 = j * 163752818;
        long decode2414 = j4 + (decode16 * 30366549) + ((decode24(bArr, 60) << 4) & 4294967295L);
        long j5 = j * 258169998;
        long decode3215 = j5 + (decode16 * 163752818) + (decode32(bArr, 63) & 4294967295L);
        long j6 = j * 96434764;
        long decode2415 = j6 + (decode16 * 258169998) + ((decode24(bArr, 67) << 4) & 4294967295L);
        long j7 = j * 227822194;
        long decode3216 = j7 + (decode16 * 96434764) + (decode32(bArr, 70) & 4294967295L);
        long j8 = j2 * 30366549;
        long j9 = (j2 * 163752818) + decode3214;
        long j10 = (j2 * 258169998) + decode2414;
        long j11 = (j2 * 96434764) + decode3215;
        long j12 = (j2 * 227822194) + decode2415;
        long j13 = (j2 * 149865618) + decode3216;
        long j14 = (j2 * 550336261) + (j * 149865618) + (decode16 * 227822194) + decode248;
        long j15 = decode2412 + (decode3212 >>> 28);
        long j16 = decode3212 & M28L;
        long j17 = (j15 * 96434764) + j10;
        long j18 = (j15 * 227822194) + j11;
        long j19 = (j15 * 149865618) + j12;
        long j20 = (j15 * 550336261) + j13;
        long j21 = (j16 * 163752818) + (j15 * 30366549) + (j2 * 43969588) + decode328;
        long j22 = (j16 * 258169998) + (j15 * 163752818) + j8 + (j * 43969588) + decode247;
        long j23 = (j16 * 149865618) + j18;
        long j24 = (j16 * 550336261) + j19;
        long j25 = decode2411 + (decode3211 >>> 28);
        long j26 = decode3211 & M28L;
        long j27 = (j25 * 163752818) + (j16 * 30366549) + (j15 * 43969588) + decode246;
        long j28 = (j25 * 258169998) + j21;
        long j29 = (j26 * 163752818) + (j25 * 30366549) + (j16 * 43969588) + decode327;
        long j30 = (j26 * 258169998) + j27;
        long j31 = (j26 * 96434764) + j28;
        long j32 = (j26 * 227822194) + (j25 * 96434764) + j22;
        long j33 = j26 * 149865618;
        long j34 = j33 + (j25 * 227822194) + (j16 * 96434764) + (j15 * 258169998) + j9;
        long j35 = (j26 * 550336261) + (j25 * 149865618) + (j16 * 227822194) + j17;
        long j36 = decode2410 + (decode3210 >>> 28);
        long j37 = decode3210 & M28L;
        long j38 = (j36 * 163752818) + (j26 * 30366549) + (j25 * 43969588) + decode245;
        long j39 = (j36 * 258169998) + j29;
        long j40 = (j36 * 96434764) + j30;
        long j41 = (j36 * 227822194) + j31;
        long j42 = (j36 * 149865618) + j32;
        long j43 = j14 + (j20 >>> 28);
        long j44 = j20 & M28L;
        long j45 = (j * 550336261) + (decode16 * 149865618) + decode329 + (j43 >>> 28);
        long j46 = j43 & M28L;
        long j47 = (decode16 * 550336261) + decode249 + (j45 >>> 28);
        long j48 = j45 & M28L;
        long j49 = j37 + (j47 >>> 28);
        long j50 = j47 & M28L;
        long j51 = (j49 * 163752818) + (j36 * 30366549) + (j26 * 43969588) + decode326;
        long j52 = (j49 * 258169998) + j38;
        long j53 = (j49 * 96434764) + j39;
        long j54 = (j49 * 227822194) + j40;
        long j55 = (j50 * 163752818) + (j49 * 30366549) + (j36 * 43969588) + decode244;
        long j56 = (j50 * 258169998) + j51;
        long j57 = (j50 * 96434764) + j52;
        long j58 = (j50 * 227822194) + j53;
        long j59 = (j50 * 149865618) + j54;
        long j60 = (j50 * 550336261) + (j49 * 149865618) + j41;
        long j61 = (j48 * 163752818) + (j50 * 30366549) + (j49 * 43969588) + decode325;
        long j62 = (j48 * 96434764) + j56;
        long j63 = (j48 * 227822194) + j57;
        long j64 = (j48 * 149865618) + j58;
        long j65 = (j48 * 550336261) + j59;
        long j66 = (j25 * 550336261) + j23 + (j35 >>> 28);
        long j67 = j35 & M28L;
        long j68 = j24 + (j66 >>> 28);
        long j69 = j66 & M28L;
        long j70 = j44 + (j68 >>> 28);
        long j71 = j68 & M28L;
        long j72 = j46 + (j70 >>> 28);
        long j73 = j70 & M28L;
        long j74 = (j72 * 163752818) + (j48 * 30366549) + (j50 * 43969588) + decode243;
        long j75 = (j72 * 258169998) + j61;
        long j76 = (j72 * 227822194) + j62;
        long j77 = (j72 * 149865618) + j63;
        long j78 = (j72 * 550336261) + j64;
        long j79 = (j73 * 163752818) + (j72 * 30366549) + (j48 * 43969588) + decode324;
        long j80 = (j73 * 258169998) + j74;
        long j81 = (j73 * 96434764) + j75;
        long j82 = (j73 * 227822194) + (j72 * 96434764) + (j48 * 258169998) + j55;
        long j83 = j71 * 163752818;
        long j84 = j83 + (j73 * 30366549) + (j72 * 43969588) + decode242;
        long j85 = (j71 * 258169998) + j79;
        long j86 = (j71 * 96434764) + j80;
        long j87 = (j71 * 227822194) + j81;
        long j88 = (j71 * 149865618) + j82;
        long j89 = (j71 * 550336261) + (j73 * 149865618) + j76;
        long j90 = (j49 * 550336261) + j42 + (j60 >>> 28);
        long j91 = j60 & M28L;
        long j92 = (j36 * 550336261) + j34 + (j90 >>> 28);
        long j93 = j90 & M28L;
        long j94 = j67 + (j92 >>> 28);
        long j95 = j92 & M28L;
        long j96 = j69 + (j94 >>> 28);
        long j97 = j94 & M28L;
        long j98 = (j96 * 30366549) + (j71 * 43969588) + decode24;
        long j99 = (j96 * 163752818) + (j71 * 30366549) + (j73 * 43969588) + decode323;
        long j100 = (j96 * 258169998) + j84;
        long decode2416 = (43969588 * j97) + ((decode24(bArr, 4) << 4) & 4294967295L);
        long j101 = 30366549 * j97;
        long j102 = (163752818 * j97) + j98;
        long j103 = 149865618 * j97;
        long j104 = j93 & M26L;
        long j105 = (j95 * 4) + (j93 >>> 26) + 1;
        long j106 = (78101261 * j105) + decode32;
        long j107 = 175155932 * j105;
        long j108 = (64542499 * j105) + j102;
        long j109 = 158326419 * j105;
        long j110 = 191173276 * j105;
        long j111 = 104575268 * j105;
        long j112 = (141809365 * j105) + decode2416 + (j106 >>> 28);
        long j113 = j106 & M28L;
        long j114 = j107 + j101 + (j96 * 43969588) + decode322 + (j112 >>> 28);
        long j115 = j112 & M28L;
        long j116 = j108 + (j114 >>> 28);
        long j117 = j114 & M28L;
        long j118 = j109 + (258169998 * j97) + j99 + (j116 >>> 28);
        long j119 = j116 & M28L;
        long j120 = j110 + (96434764 * j97) + j100 + (j118 >>> 28);
        long j121 = j118 & M28L;
        long j122 = j111 + (227822194 * j97) + (j96 * 96434764) + j85 + (j120 >>> 28);
        long j123 = j120 & M28L;
        long j124 = (j105 * 137584065) + j103 + (j96 * 227822194) + j86 + (j122 >>> 28);
        long j125 = j122 & M28L;
        long j126 = (j97 * 550336261) + (j96 * 149865618) + j87 + (j124 >>> 28);
        long j127 = j124 & M28L;
        long j128 = (j96 * 550336261) + j88 + (j126 >>> 28);
        long j129 = j126 & M28L;
        long j130 = j89 + (j128 >>> 28);
        long j131 = j128 & M28L;
        long j132 = (j73 * 550336261) + j77 + (j130 >>> 28);
        long j133 = j130 & M28L;
        long j134 = j78 + (j132 >>> 28);
        long j135 = j132 & M28L;
        long j136 = j65 + (j134 >>> 28);
        long j137 = j134 & M28L;
        long j138 = j91 + (j136 >>> 28);
        long j139 = j136 & M28L;
        long j140 = j104 + (j138 >>> 28);
        long j141 = j138 & M28L;
        long j142 = j140 >>> 26;
        long j143 = j140 & M26L;
        long j144 = j142 - 1;
        long j145 = j113 - (j144 & 78101261);
        long j146 = (j115 - (j144 & 141809365)) + (j145 >> 28);
        long j147 = j145 & M28L;
        long j148 = (j117 - (j144 & 175155932)) + (j146 >> 28);
        long j149 = j146 & M28L;
        long j150 = (j119 - (j144 & 64542499)) + (j148 >> 28);
        long j151 = j148 & M28L;
        long j152 = (j121 - (j144 & 158326419)) + (j150 >> 28);
        long j153 = j150 & M28L;
        long j154 = (j123 - (j144 & 191173276)) + (j152 >> 28);
        long j155 = j152 & M28L;
        long j156 = (j125 - (j144 & 104575268)) + (j154 >> 28);
        long j157 = j154 & M28L;
        long j158 = (j127 - (j144 & 137584065)) + (j156 >> 28);
        long j159 = j156 & M28L;
        long j160 = j129 + (j158 >> 28);
        long j161 = j158 & M28L;
        long j162 = j131 + (j160 >> 28);
        long j163 = j160 & M28L;
        long j164 = j133 + (j162 >> 28);
        long j165 = j162 & M28L;
        long j166 = j135 + (j164 >> 28);
        long j167 = j164 & M28L;
        long j168 = j137 + (j166 >> 28);
        long j169 = j166 & M28L;
        long j170 = j139 + (j168 >> 28);
        long j171 = j168 & M28L;
        long j172 = j141 + (j170 >> 28);
        long j173 = j170 & M28L;
        long j174 = j172 & M28L;
        byte[] bArr2 = new byte[57];
        encode56(j147 | (j149 << 28), bArr2, 0);
        encode56((j153 << 28) | j151, bArr2, 7);
        encode56(j155 | (j157 << 28), bArr2, 14);
        encode56((j161 << 28) | j159, bArr2, 21);
        encode56(j163 | (j165 << 28), bArr2, 28);
        encode56(j167 | (j169 << 28), bArr2, 35);
        encode56(j171 | (j173 << 28), bArr2, 42);
        encode56(j174 | ((j143 + (j172 >> 28)) << 28), bArr2, 49);
        return bArr2;
    }

    private static void scalarMult(byte[] bArr, PointExt pointExt, PointExt pointExt2) {
        int[] iArr = new int[14];
        decodeScalar(bArr, 0, iArr);
        Nat.shiftDownBits(14, iArr, 2, 0);
        Nat.cadd(14, (~iArr[0]) & 1, iArr, L, iArr);
        Nat.shiftDownBit(14, iArr, 1);
        int[] pointPrecompute = pointPrecompute(pointExt, 8);
        PointExt pointExt3 = new PointExt();
        pointLookup(iArr, 111, pointPrecompute, pointExt2);
        for (int i = 110; i >= 0; i--) {
            for (int i2 = 0; i2 < 4; i2++) {
                pointDouble(pointExt2);
            }
            pointLookup(iArr, i, pointPrecompute, pointExt3);
            pointAdd(pointExt3, pointExt2);
        }
        for (int i3 = 0; i3 < 2; i3++) {
            pointDouble(pointExt2);
        }
    }

    private static void scalarMultBase(byte[] bArr, PointExt pointExt) {
        precompute();
        int[] iArr = new int[15];
        decodeScalar(bArr, 0, iArr);
        iArr[14] = Nat.cadd(14, (~iArr[0]) & 1, iArr, L, iArr) + 4;
        Nat.shiftDownBit(iArr.length, iArr, 0);
        PointPrecomp pointPrecomp = new PointPrecomp();
        pointSetNeutral(pointExt);
        int i = 17;
        while (true) {
            int i2 = 0;
            int i3 = i;
            while (i2 < 5) {
                int i4 = 0;
                int i5 = i3;
                for (int i6 = 0; i6 < 5; i6++) {
                    i4 = (i4 & (~(1 << i6))) ^ ((iArr[i5 >>> 5] >>> (i5 & 31)) << i6);
                    i5 += 18;
                }
                int i7 = (i4 >>> 4) & 1;
                pointLookup(i2, ((-i7) ^ i4) & 15, pointPrecomp);
                X448Field.cnegate(i7, pointPrecomp.x);
                pointAddPrecomp(pointPrecomp, pointExt);
                i2++;
                i3 = i5;
            }
            i--;
            if (i < 0) {
                return;
            }
            pointDouble(pointExt);
        }
    }

    private static void scalarMultBaseEncoded(byte[] bArr, byte[] bArr2, int i) {
        PointExt pointExt = new PointExt();
        scalarMultBase(bArr, pointExt);
        if (encodePoint(pointExt, bArr2, i) != 0) {
            return;
        }
        throw new IllegalStateException();
    }

    public static void scalarMultBaseXY(X448.Friend friend, byte[] bArr, int i, int[] iArr, int[] iArr2) {
        if (friend != null) {
            byte[] bArr2 = new byte[57];
            pruneScalar(bArr, i, bArr2);
            PointExt pointExt = new PointExt();
            scalarMultBase(bArr2, pointExt);
            if (checkPoint(pointExt.x, pointExt.y, pointExt.z) == 0) {
                throw new IllegalStateException();
            }
            X448Field.copy(pointExt.x, 0, iArr, 0);
            X448Field.copy(pointExt.y, 0, iArr2, 0);
            return;
        }
        throw new NullPointerException("This method is only for use by X448");
    }

    private static void scalarMultStrausVar(int[] iArr, int[] iArr2, PointExt pointExt, PointExt pointExt2) {
        precompute();
        byte[] wnafVar = getWnafVar(iArr, 7);
        byte[] wnafVar2 = getWnafVar(iArr2, 5);
        PointExt[] pointPrecomputeVar = pointPrecomputeVar(pointExt, 8);
        pointSetNeutral(pointExt2);
        int i = 446;
        while (true) {
            byte b = wnafVar[i];
            boolean z = false;
            if (b != 0) {
                int i2 = b >> 31;
                pointAddVar(i2 != 0, precompBaseTable[(b ^ i2) >>> 1], pointExt2);
            }
            byte b2 = wnafVar2[i];
            if (b2 != 0) {
                int i3 = b2 >> 31;
                int i4 = (b2 ^ i3) >>> 1;
                if (i3 != 0) {
                    z = true;
                }
                pointAddVar(z, pointPrecomputeVar[i4], pointExt2);
            }
            i--;
            if (i < 0) {
                return;
            }
            pointDouble(pointExt2);
        }
    }

    public static void sign(byte[] bArr, int i, byte[] bArr2, int i2, byte[] bArr3, byte[] bArr4, int i3, int i4, byte[] bArr5, int i5) {
        implSign(bArr, i, bArr2, i2, bArr3, (byte) 0, bArr4, i3, i4, bArr5, i5);
    }

    public static void sign(byte[] bArr, int i, byte[] bArr2, byte[] bArr3, int i2, int i3, byte[] bArr4, int i4) {
        implSign(bArr, i, bArr2, (byte) 0, bArr3, i2, i3, bArr4, i4);
    }

    public static void signPrehash(byte[] bArr, int i, byte[] bArr2, int i2, byte[] bArr3, Xof xof, byte[] bArr4, int i3) {
        byte[] bArr5 = new byte[64];
        if (64 == xof.doFinal(bArr5, 0, 64)) {
            implSign(bArr, i, bArr2, i2, bArr3, (byte) 1, bArr5, 0, bArr5.length, bArr4, i3);
            return;
        }
        throw new IllegalArgumentException("ph");
    }

    public static void signPrehash(byte[] bArr, int i, byte[] bArr2, int i2, byte[] bArr3, byte[] bArr4, int i3, byte[] bArr5, int i4) {
        implSign(bArr, i, bArr2, i2, bArr3, (byte) 1, bArr4, i3, 64, bArr5, i4);
    }

    public static void signPrehash(byte[] bArr, int i, byte[] bArr2, Xof xof, byte[] bArr3, int i2) {
        byte[] bArr4 = new byte[64];
        if (64 == xof.doFinal(bArr4, 0, 64)) {
            implSign(bArr, i, bArr2, (byte) 1, bArr4, 0, bArr4.length, bArr3, i2);
            return;
        }
        throw new IllegalArgumentException("ph");
    }

    public static void signPrehash(byte[] bArr, int i, byte[] bArr2, byte[] bArr3, int i2, byte[] bArr4, int i3) {
        implSign(bArr, i, bArr2, (byte) 1, bArr3, i2, 64, bArr4, i3);
    }

    public static boolean verify(byte[] bArr, int i, byte[] bArr2, int i2, byte[] bArr3, byte[] bArr4, int i3, int i4) {
        return implVerify(bArr, i, bArr2, i2, bArr3, (byte) 0, bArr4, i3, i4);
    }

    public static boolean verifyPrehash(byte[] bArr, int i, byte[] bArr2, int i2, byte[] bArr3, Xof xof) {
        byte[] bArr4 = new byte[64];
        if (64 == xof.doFinal(bArr4, 0, 64)) {
            return implVerify(bArr, i, bArr2, i2, bArr3, (byte) 1, bArr4, 0, bArr4.length);
        }
        throw new IllegalArgumentException("ph");
    }

    public static boolean verifyPrehash(byte[] bArr, int i, byte[] bArr2, int i2, byte[] bArr3, byte[] bArr4, int i3) {
        return implVerify(bArr, i, bArr2, i2, bArr3, (byte) 1, bArr4, i3, 64);
    }
}
