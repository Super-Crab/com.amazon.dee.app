package com.google.zxing.aztec.encoder;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.zxing.common.BitArray;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.reedsolomon.GenericGF;
import com.google.zxing.common.reedsolomon.ReedSolomonEncoder;
/* loaded from: classes3.dex */
public final class Encoder {
    public static final int DEFAULT_AZTEC_LAYERS = 0;
    public static final int DEFAULT_EC_PERCENT = 33;
    private static final int MAX_NB_BITS = 32;
    private static final int MAX_NB_BITS_COMPACT = 4;
    private static final int[] WORD_SIZE = {4, 6, 6, 8, 8, 8, 8, 8, 8, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12};

    private Encoder() {
    }

    private static int[] bitsToWords(BitArray bitArray, int i, int i2) {
        int[] iArr = new int[i2];
        int size = bitArray.getSize() / i;
        for (int i3 = 0; i3 < size; i3++) {
            int i4 = 0;
            for (int i5 = 0; i5 < i; i5++) {
                i4 |= bitArray.get((i3 * i) + i5) ? 1 << ((i - i5) - 1) : 0;
            }
            iArr[i3] = i4;
        }
        return iArr;
    }

    private static void drawBullsEye(BitMatrix bitMatrix, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3 += 2) {
            int i4 = i - i3;
            int i5 = i4;
            while (true) {
                int i6 = i + i3;
                if (i5 <= i6) {
                    bitMatrix.set(i5, i4);
                    bitMatrix.set(i5, i6);
                    bitMatrix.set(i4, i5);
                    bitMatrix.set(i6, i5);
                    i5++;
                }
            }
        }
        int i7 = i - i2;
        bitMatrix.set(i7, i7);
        int i8 = i7 + 1;
        bitMatrix.set(i8, i7);
        bitMatrix.set(i7, i8);
        int i9 = i + i2;
        bitMatrix.set(i9, i7);
        bitMatrix.set(i9, i8);
        bitMatrix.set(i9, i9 - 1);
    }

    private static void drawModeMessage(BitMatrix bitMatrix, boolean z, int i, BitArray bitArray) {
        int i2 = i / 2;
        int i3 = 0;
        if (z) {
            while (i3 < 7) {
                int i4 = (i2 - 3) + i3;
                if (bitArray.get(i3)) {
                    bitMatrix.set(i4, i2 - 5);
                }
                if (bitArray.get(i3 + 7)) {
                    bitMatrix.set(i2 + 5, i4);
                }
                if (bitArray.get(20 - i3)) {
                    bitMatrix.set(i4, i2 + 5);
                }
                if (bitArray.get(27 - i3)) {
                    bitMatrix.set(i2 - 5, i4);
                }
                i3++;
            }
            return;
        }
        while (i3 < 10) {
            int i5 = (i3 / 5) + (i2 - 5) + i3;
            if (bitArray.get(i3)) {
                bitMatrix.set(i5, i2 - 7);
            }
            if (bitArray.get(i3 + 10)) {
                bitMatrix.set(i2 + 7, i5);
            }
            if (bitArray.get(29 - i3)) {
                bitMatrix.set(i5, i2 + 7);
            }
            if (bitArray.get(39 - i3)) {
                bitMatrix.set(i2 - 7, i5);
            }
            i3++;
        }
    }

    public static AztecCode encode(byte[] bArr) {
        return encode(bArr, 33, 0);
    }

    private static BitArray generateCheckWords(BitArray bitArray, int i, int i2) {
        ReedSolomonEncoder reedSolomonEncoder = new ReedSolomonEncoder(getGF(i2));
        int i3 = i / i2;
        int[] bitsToWords = bitsToWords(bitArray, i2, i3);
        reedSolomonEncoder.encode(bitsToWords, i3 - (bitArray.getSize() / i2));
        BitArray bitArray2 = new BitArray();
        bitArray2.appendBits(0, i % i2);
        for (int i4 : bitsToWords) {
            bitArray2.appendBits(i4, i2);
        }
        return bitArray2;
    }

    static BitArray generateModeMessage(boolean z, int i, int i2) {
        BitArray bitArray = new BitArray();
        if (z) {
            bitArray.appendBits(i - 1, 2);
            bitArray.appendBits(i2 - 1, 6);
            return generateCheckWords(bitArray, 28, 4);
        }
        bitArray.appendBits(i - 1, 5);
        bitArray.appendBits(i2 - 1, 11);
        return generateCheckWords(bitArray, 40, 4);
    }

    private static GenericGF getGF(int i) {
        if (i != 4) {
            if (i == 6) {
                return GenericGF.AZTEC_DATA_6;
            }
            if (i == 8) {
                return GenericGF.AZTEC_DATA_8;
            }
            if (i == 10) {
                return GenericGF.AZTEC_DATA_10;
            }
            if (i == 12) {
                return GenericGF.AZTEC_DATA_12;
            }
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline49("Unsupported word size ", i));
        }
        return GenericGF.AZTEC_PARAM;
    }

    static BitArray stuffBits(BitArray bitArray, int i) {
        BitArray bitArray2 = new BitArray();
        int size = bitArray.getSize();
        int i2 = (1 << i) - 2;
        int i3 = 0;
        while (i3 < size) {
            int i4 = 0;
            for (int i5 = 0; i5 < i; i5++) {
                int i6 = i3 + i5;
                if (i6 >= size || bitArray.get(i6)) {
                    i4 |= 1 << ((i - 1) - i5);
                }
            }
            int i7 = i4 & i2;
            if (i7 == i2) {
                bitArray2.appendBits(i7, i);
            } else if (i7 == 0) {
                bitArray2.appendBits(i4 | 1, i);
            } else {
                bitArray2.appendBits(i4, i);
                i3 += i;
            }
            i3--;
            i3 += i;
        }
        return bitArray2;
    }

    private static int totalBitsInLayer(int i, boolean z) {
        return GeneratedOutlineSupport1.outline4(i, 16, z ? 88 : 112, i);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static AztecCode encode(byte[] bArr, int i, int i2) {
        int i3;
        int i4;
        BitArray stuffBits;
        BitArray bitArray;
        boolean z;
        int i5;
        int i6;
        int i7;
        int i8;
        BitArray encode = new HighLevelEncoder(bArr).encode();
        int size = ((encode.getSize() * i) / 100) + 11;
        int size2 = encode.getSize() + size;
        int i9 = 32;
        int i10 = 4;
        int i11 = 0;
        int i12 = 1;
        if (i2 != 0) {
            z = i2 < 0;
            i5 = Math.abs(i2);
            if (z) {
                i9 = 4;
            }
            if (i5 <= i9) {
                int i13 = totalBitsInLayer(i5, z);
                i4 = WORD_SIZE[i5];
                int i14 = i13 - (i13 % i4);
                bitArray = stuffBits(encode, i4);
                if (bitArray.getSize() + size <= i14) {
                    if (z && bitArray.getSize() > i4 * 64) {
                        throw new IllegalArgumentException("Data to large for user specified layer");
                    }
                    i3 = i13;
                } else {
                    throw new IllegalArgumentException("Data to large for user specified layer");
                }
            } else {
                throw new IllegalArgumentException(String.format("Illegal value %s for layers", Integer.valueOf(i2)));
            }
        } else {
            int i15 = 0;
            BitArray bitArray2 = null;
            int i16 = 0;
            while (i16 <= 32) {
                boolean z2 = i16 <= 3 ? i12 : i11;
                int i17 = z2 ? i16 + 1 : i16;
                i3 = totalBitsInLayer(i17, z2);
                if (size2 > i3) {
                    i6 = i12;
                } else {
                    if (bitArray2 == null || i15 != WORD_SIZE[i17]) {
                        i4 = WORD_SIZE[i17];
                        stuffBits = stuffBits(encode, i4);
                    } else {
                        int i18 = i15;
                        stuffBits = bitArray2;
                        i4 = i18;
                    }
                    int i19 = i3 - (i3 % i4);
                    if ((!z2 || stuffBits.getSize() <= i4 * 64) && stuffBits.getSize() + size <= i19) {
                        bitArray = stuffBits;
                        z = z2 ? 1 : 0;
                        i5 = i17;
                    } else {
                        i6 = i12;
                        BitArray bitArray3 = stuffBits;
                        i15 = i4;
                        bitArray2 = bitArray3;
                    }
                }
                i16++;
                i12 = i6;
                i10 = 4;
                i11 = 0;
            }
            throw new IllegalArgumentException("Data too large for an Aztec code");
        }
        BitArray generateCheckWords = generateCheckWords(bitArray, i3, i4);
        int size3 = bitArray.getSize() / i4;
        BitArray generateModeMessage = generateModeMessage(z, i5, size3);
        int i20 = (i5 * 4) + (z ? 11 : 14);
        int[] iArr = new int[i20];
        int i21 = 2;
        if (z) {
            for (int i22 = i11; i22 < iArr.length; i22++) {
                iArr[i22] = i22;
            }
            i7 = i20;
        } else {
            int i23 = i20 / 2;
            i7 = i20 + 1 + (((i23 - 1) / 15) * 2);
            int i24 = i7 / 2;
            for (int i25 = i11; i25 < i23; i25++) {
                iArr[(i23 - i25) - 1] = (i24 - i8) - 1;
                iArr[i23 + i25] = (i25 / 15) + i25 + i24 + i12;
            }
        }
        BitMatrix bitMatrix = new BitMatrix(i7);
        int i26 = i11;
        int i27 = i26;
        while (i26 < i5) {
            int i28 = ((i5 - i26) * i10) + (z ? 9 : 12);
            int i29 = i11;
            while (i29 < i28) {
                int i30 = i29 * 2;
                while (i11 < i21) {
                    if (generateCheckWords.get(i27 + i30 + i11)) {
                        int i31 = i26 * 2;
                        bitMatrix.set(iArr[i31 + i11], iArr[i31 + i29]);
                    }
                    if (generateCheckWords.get((i28 * 2) + i27 + i30 + i11)) {
                        int i32 = i26 * 2;
                        bitMatrix.set(iArr[i32 + i29], iArr[((i20 - 1) - i32) - i11]);
                    }
                    if (generateCheckWords.get((i28 * 4) + i27 + i30 + i11)) {
                        int i33 = (i20 - 1) - (i26 * 2);
                        bitMatrix.set(iArr[i33 - i11], iArr[i33 - i29]);
                    }
                    if (generateCheckWords.get((i28 * 6) + i27 + i30 + i11)) {
                        int i34 = i26 * 2;
                        bitMatrix.set(iArr[((i20 - 1) - i34) - i29], iArr[i34 + i11]);
                    }
                    i11++;
                    i21 = 2;
                }
                i29++;
                i11 = 0;
                i21 = 2;
            }
            i27 += i28 * 8;
            i26++;
            i10 = 4;
            i11 = 0;
            i21 = 2;
        }
        drawModeMessage(bitMatrix, z, i7, generateModeMessage);
        if (z) {
            drawBullsEye(bitMatrix, i7 / 2, 5);
        } else {
            int i35 = i7 / 2;
            drawBullsEye(bitMatrix, i35, 7);
            int i36 = 0;
            int i37 = 0;
            while (i36 < (i20 / 2) - 1) {
                for (int i38 = i35 & 1; i38 < i7; i38 += 2) {
                    int i39 = i35 - i37;
                    bitMatrix.set(i39, i38);
                    int i40 = i35 + i37;
                    bitMatrix.set(i40, i38);
                    bitMatrix.set(i38, i39);
                    bitMatrix.set(i38, i40);
                }
                i36 += 15;
                i37 += 16;
            }
        }
        AztecCode aztecCode = new AztecCode();
        aztecCode.setCompact(z);
        aztecCode.setSize(i7);
        aztecCode.setLayers(i5);
        aztecCode.setCodeWords(size3);
        aztecCode.setMatrix(bitMatrix);
        return aztecCode;
    }
}
