package com.amazonaws.util;

import com.android.tools.r8.GeneratedOutlineSupport1;
import okio.Utf8;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class Base64Codec implements Codec {
    private static final int BITS_3 = 3;
    private static final int BITS_4 = 4;
    private static final int BITS_6 = 6;
    private static final int MASK_2BITS = 3;
    private static final int MASK_4BITS = 15;
    private static final int MASK_6BITS = 63;
    private static final int OFFSET_0_VALUE = 52;
    private static final int OFFSET_OF_0 = -4;
    private static final int OFFSET_OF_PLUS = -19;
    private static final int OFFSET_OF_SLASH = -16;
    private static final int OFFSET_OF_a = 71;
    private static final int OFFSET_PLUS_VALUE = 62;
    private static final int OFFSET_SLASH_VALUE = 63;
    private static final int OFFSET_a_VALUE = 26;
    private static final byte PAD = 61;
    private final byte[] alpahbets;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class LazyHolder {
        private static final byte[] DECODED = decodeTable();

        private LazyHolder() {
        }

        private static byte[] decodeTable() {
            byte[] bArr = new byte[123];
            for (int i = 0; i <= 122; i++) {
                if (i >= 65 && i <= 90) {
                    bArr[i] = (byte) (i - 65);
                } else if (i >= 48 && i <= 57) {
                    bArr[i] = (byte) (i + 4);
                } else if (i == 43) {
                    bArr[i] = (byte) (i + 19);
                } else if (i == 47) {
                    bArr[i] = (byte) (i + 16);
                } else if (i >= 97 && i <= 122) {
                    bArr[i] = (byte) (i - 71);
                } else {
                    bArr[i] = -1;
                }
            }
            return bArr;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Base64Codec() {
        this.alpahbets = CodecUtils.toBytesDirect("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/");
    }

    @Override // com.amazonaws.util.Codec
    public byte[] decode(byte[] bArr, int i) {
        if (i % 4 == 0) {
            int i2 = i - 1;
            int i3 = 0;
            while (i3 < 2 && i2 > -1 && bArr[i2] == 61) {
                i2--;
                i3++;
            }
            int i4 = 1;
            if (i3 == 0) {
                i4 = 3;
            } else if (i3 == 1) {
                i4 = 2;
            } else if (i3 != 2) {
                throw new Error("Impossible");
            }
            byte[] bArr2 = new byte[((i / 4) * 3) - (3 - i4)];
            int i5 = 0;
            int i6 = 0;
            while (i6 < bArr2.length - (i4 % 3)) {
                decode4bytes(bArr, i5, bArr2, i6);
                i5 += 4;
                i6 += 3;
            }
            if (i4 < 3) {
                decode1to3bytes(i4, bArr, i5, bArr2, i6);
            }
            return bArr2;
        }
        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline49("Input is expected to be encoded in multiple of 4 bytes but found: ", i));
    }

    void decode1to3bytes(int i, byte[] bArr, int i2, byte[] bArr2, int i3) {
        int i4 = i3 + 1;
        int i5 = i2 + 1;
        int i6 = i5 + 1;
        int pos = pos(bArr[i5]);
        bArr2[i3] = (byte) ((pos(bArr[i2]) << 2) | ((pos >>> 4) & 3));
        if (i == 1) {
            CodecUtils.sanityCheckLastPos(pos, 15);
            return;
        }
        int i7 = i4 + 1;
        int i8 = i6 + 1;
        int pos2 = pos(bArr[i6]);
        bArr2[i4] = (byte) ((15 & (pos2 >>> 2)) | ((pos & 15) << 4));
        if (i == 2) {
            CodecUtils.sanityCheckLastPos(pos2, 3);
        } else {
            bArr2[i7] = (byte) (((pos2 & 3) << 6) | pos(bArr[i8]));
        }
    }

    void decode4bytes(byte[] bArr, int i, byte[] bArr2, int i2) {
        int i3 = i2 + 1;
        int i4 = i + 1;
        int i5 = i4 + 1;
        int pos = pos(bArr[i4]);
        bArr2[i2] = (byte) ((pos(bArr[i]) << 2) | ((pos >>> 4) & 3));
        int i6 = i5 + 1;
        int pos2 = pos(bArr[i5]);
        bArr2[i3] = (byte) (((pos & 15) << 4) | ((pos2 >>> 2) & 15));
        bArr2[i3 + 1] = (byte) (pos(bArr[i6]) | ((pos2 & 3) << 6));
    }

    @Override // com.amazonaws.util.Codec
    public byte[] encode(byte[] bArr) {
        int length = bArr.length / 3;
        int length2 = bArr.length % 3;
        int i = 0;
        if (length2 == 0) {
            byte[] bArr2 = new byte[length * 4];
            int i2 = 0;
            while (i < bArr.length) {
                encode3bytes(bArr, i, bArr2, i2);
                i += 3;
                i2 += 4;
            }
            return bArr2;
        }
        byte[] bArr3 = new byte[(length + 1) * 4];
        int i3 = 0;
        while (i < bArr.length - length2) {
            encode3bytes(bArr, i, bArr3, i3);
            i += 3;
            i3 += 4;
        }
        if (length2 == 1) {
            encode1byte(bArr, i, bArr3, i3);
        } else if (length2 == 2) {
            encode2bytes(bArr, i, bArr3, i3);
        }
        return bArr3;
    }

    void encode1byte(byte[] bArr, int i, byte[] bArr2, int i2) {
        int i3 = i2 + 1;
        byte[] bArr3 = this.alpahbets;
        byte b = bArr[i];
        bArr2[i2] = bArr3[(b >>> 2) & 63];
        int i4 = i3 + 1;
        bArr2[i3] = bArr3[(b & 3) << 4];
        bArr2[i4] = 61;
        bArr2[i4 + 1] = 61;
    }

    void encode2bytes(byte[] bArr, int i, byte[] bArr2, int i2) {
        int i3 = i2 + 1;
        byte[] bArr3 = this.alpahbets;
        int i4 = i + 1;
        byte b = bArr[i];
        bArr2[i2] = bArr3[(b >>> 2) & 63];
        int i5 = i3 + 1;
        byte b2 = bArr[i4];
        bArr2[i3] = bArr3[((b & 3) << 4) | ((b2 >>> 4) & 15)];
        bArr2[i5] = bArr3[(b2 & 15) << 2];
        bArr2[i5 + 1] = 61;
    }

    void encode3bytes(byte[] bArr, int i, byte[] bArr2, int i2) {
        int i3 = i2 + 1;
        byte[] bArr3 = this.alpahbets;
        int i4 = i + 1;
        byte b = bArr[i];
        bArr2[i2] = bArr3[(b >>> 2) & 63];
        int i5 = i3 + 1;
        int i6 = i4 + 1;
        byte b2 = bArr[i4];
        bArr2[i3] = bArr3[((b & 3) << 4) | ((b2 >>> 4) & 15)];
        byte b3 = bArr[i6];
        bArr2[i5] = bArr3[((b2 & 15) << 2) | ((b3 >>> 6) & 3)];
        bArr2[i5 + 1] = bArr3[b3 & Utf8.REPLACEMENT_BYTE];
    }

    protected int pos(byte b) {
        byte b2 = LazyHolder.DECODED[b];
        if (b2 > -1) {
            return b2;
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Invalid base 64 character: '");
        outline107.append((char) b);
        outline107.append("'");
        throw new IllegalArgumentException(outline107.toString());
    }

    protected Base64Codec(byte[] bArr) {
        this.alpahbets = bArr;
    }
}
