package org.apache.thrift.orig;
/* loaded from: classes4.dex */
public class EncodingUtils {
    public static final byte clearBit(byte b, int i) {
        return (byte) clearBit((int) b, i);
    }

    public static final int clearBit(int i, int i2) {
        return i & (~(1 << i2));
    }

    public static final long clearBit(long j, int i) {
        return j & (~(1 << i));
    }

    public static final int decodeBigEndian(byte[] bArr) {
        return decodeBigEndian(bArr, 0);
    }

    public static final void encodeBigEndian(int i, byte[] bArr) {
        encodeBigEndian(i, bArr, 0);
    }

    public static final byte setBit(byte b, int i, boolean z) {
        return (byte) setBit((int) b, i, z);
    }

    public static final boolean testBit(byte b, int i) {
        return testBit((int) b, i);
    }

    public static final boolean testBit(int i, int i2) {
        return (i & (1 << i2)) != 0;
    }

    public static final boolean testBit(long j, int i) {
        return (j & (1 << i)) != 0;
    }

    public static final short clearBit(short s, int i) {
        return (short) clearBit((int) s, i);
    }

    public static final int decodeBigEndian(byte[] bArr, int i) {
        return (bArr[i + 3] & 255) | ((bArr[i] & 255) << 24) | ((bArr[i + 1] & 255) << 16) | ((bArr[i + 2] & 255) << 8);
    }

    public static final void encodeBigEndian(int i, byte[] bArr, int i2) {
        bArr[i2] = (byte) ((i >> 24) & 255);
        bArr[i2 + 1] = (byte) ((i >> 16) & 255);
        bArr[i2 + 2] = (byte) ((i >> 8) & 255);
        bArr[i2 + 3] = (byte) (i & 255);
    }

    public static final short setBit(short s, int i, boolean z) {
        return (short) setBit((int) s, i, z);
    }

    public static final boolean testBit(short s, int i) {
        return testBit((int) s, i);
    }

    public static final int setBit(int i, int i2, boolean z) {
        return z ? i | (1 << i2) : clearBit(i, i2);
    }

    public static final long setBit(long j, int i, boolean z) {
        return z ? j | (1 << i) : clearBit(j, i);
    }
}
