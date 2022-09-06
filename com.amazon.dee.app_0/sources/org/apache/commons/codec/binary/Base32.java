package org.apache.commons.codec.binary;

import com.amazon.alexa.accessory.internal.bluetooth.GenericAccessProfile;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.base.Ascii;
import org.apache.commons.codec.binary.BaseNCodec;
/* loaded from: classes4.dex */
public class Base32 extends BaseNCodec {
    private static final int BITS_PER_ENCODED_BYTE = 5;
    private static final int BYTES_PER_ENCODED_BLOCK = 8;
    private static final int BYTES_PER_UNENCODED_BLOCK = 5;
    private static final byte[] CHUNK_SEPARATOR = {13, 10};
    private static final byte[] DECODE_TABLE = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, Ascii.SUB, 27, 28, 29, 30, 31, -1, -1, -1, -1, -1, -1, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25};
    private static final byte[] ENCODE_TABLE = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 50, 51, 52, 53, 54, 55};
    private static final byte[] HEX_DECODE_TABLE = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, -1, -1, -1, -1, -1, -1, -1, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, Ascii.SUB, 27, 28, 29, 30, 31, 32};
    private static final byte[] HEX_ENCODE_TABLE = {48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86};
    private static final int MASK_5BITS = 31;
    private final int decodeSize;
    private final byte[] decodeTable;
    private final int encodeSize;
    private final byte[] encodeTable;
    private final byte[] lineSeparator;

    public Base32() {
        this(false);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.apache.commons.codec.binary.BaseNCodec
    public void decode(byte[] bArr, int i, int i2, BaseNCodec.Context context) {
        byte b;
        if (context.eof) {
            return;
        }
        if (i2 < 0) {
            context.eof = true;
        }
        int i3 = 0;
        boolean z = true;
        int i4 = i;
        while (true) {
            if (i3 >= i2) {
                break;
            }
            int i5 = i4 + 1;
            byte b2 = bArr[i4];
            if (b2 == this.pad) {
                context.eof = z;
                break;
            }
            byte[] ensureBufferSize = ensureBufferSize(this.decodeSize, context);
            if (b2 >= 0) {
                byte[] bArr2 = this.decodeTable;
                if (b2 < bArr2.length && (b = bArr2[b2]) >= 0) {
                    int i6 = context.modulus;
                    int i7 = z ? 1 : 0;
                    int i8 = z ? 1 : 0;
                    context.modulus = (i6 + i7) % 8;
                    context.lbitWorkArea = (context.lbitWorkArea << 5) + b;
                    if (context.modulus == 0) {
                        int i9 = context.pos;
                        context.pos = i9 + 1;
                        long j = context.lbitWorkArea;
                        ensureBufferSize[i9] = (byte) ((j >> 32) & 255);
                        int i10 = context.pos;
                        context.pos = i10 + 1;
                        ensureBufferSize[i10] = (byte) ((j >> 24) & 255);
                        int i11 = context.pos;
                        context.pos = i11 + 1;
                        ensureBufferSize[i11] = (byte) ((j >> 16) & 255);
                        int i12 = context.pos;
                        context.pos = i12 + 1;
                        ensureBufferSize[i12] = (byte) ((j >> 8) & 255);
                        int i13 = context.pos;
                        context.pos = i13 + 1;
                        ensureBufferSize[i13] = (byte) (j & 255);
                    }
                }
            }
            i3++;
            z = true;
            i4 = i5;
        }
        if (!context.eof || context.modulus < 2) {
            return;
        }
        byte[] ensureBufferSize2 = ensureBufferSize(this.decodeSize, context);
        switch (context.modulus) {
            case 2:
                int i14 = context.pos;
                context.pos = i14 + 1;
                ensureBufferSize2[i14] = (byte) ((context.lbitWorkArea >> 2) & 255);
                return;
            case 3:
                int i15 = context.pos;
                context.pos = i15 + 1;
                ensureBufferSize2[i15] = (byte) ((context.lbitWorkArea >> 7) & 255);
                return;
            case 4:
                context.lbitWorkArea >>= 4;
                int i16 = context.pos;
                context.pos = i16 + 1;
                long j2 = context.lbitWorkArea;
                ensureBufferSize2[i16] = (byte) ((j2 >> 8) & 255);
                int i17 = context.pos;
                context.pos = i17 + 1;
                ensureBufferSize2[i17] = (byte) (j2 & 255);
                return;
            case 5:
                context.lbitWorkArea >>= 1;
                int i18 = context.pos;
                context.pos = i18 + 1;
                long j3 = context.lbitWorkArea;
                ensureBufferSize2[i18] = (byte) ((j3 >> 16) & 255);
                int i19 = context.pos;
                context.pos = i19 + 1;
                ensureBufferSize2[i19] = (byte) ((j3 >> 8) & 255);
                int i20 = context.pos;
                context.pos = i20 + 1;
                ensureBufferSize2[i20] = (byte) (j3 & 255);
                return;
            case 6:
                context.lbitWorkArea >>= 6;
                int i21 = context.pos;
                context.pos = i21 + 1;
                long j4 = context.lbitWorkArea;
                ensureBufferSize2[i21] = (byte) ((j4 >> 16) & 255);
                int i22 = context.pos;
                context.pos = i22 + 1;
                ensureBufferSize2[i22] = (byte) ((j4 >> 8) & 255);
                int i23 = context.pos;
                context.pos = i23 + 1;
                ensureBufferSize2[i23] = (byte) (j4 & 255);
                return;
            case 7:
                context.lbitWorkArea >>= 3;
                int i24 = context.pos;
                context.pos = i24 + 1;
                long j5 = context.lbitWorkArea;
                ensureBufferSize2[i24] = (byte) ((j5 >> 24) & 255);
                int i25 = context.pos;
                context.pos = i25 + 1;
                ensureBufferSize2[i25] = (byte) ((j5 >> 16) & 255);
                int i26 = context.pos;
                context.pos = i26 + 1;
                ensureBufferSize2[i26] = (byte) ((j5 >> 8) & 255);
                int i27 = context.pos;
                context.pos = i27 + 1;
                ensureBufferSize2[i27] = (byte) (j5 & 255);
                return;
            default:
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Impossible modulus ");
                outline107.append(context.modulus);
                throw new IllegalStateException(outline107.toString());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.apache.commons.codec.binary.BaseNCodec
    public void encode(byte[] bArr, int i, int i2, BaseNCodec.Context context) {
        if (context.eof) {
            return;
        }
        if (i2 >= 0) {
            int i3 = i;
            int i4 = 1;
            int i5 = 0;
            while (i5 < i2) {
                byte[] ensureBufferSize = ensureBufferSize(this.encodeSize, context);
                context.modulus = (context.modulus + i4) % 5;
                int i6 = i3 + 1;
                int i7 = bArr[i3];
                if (i7 < 0) {
                    i7 += 256;
                }
                context.lbitWorkArea = (context.lbitWorkArea << 8) + i7;
                if (context.modulus == 0) {
                    int i8 = context.pos;
                    context.pos = i8 + 1;
                    byte[] bArr2 = this.encodeTable;
                    long j = context.lbitWorkArea;
                    ensureBufferSize[i8] = bArr2[((int) (j >> 35)) & 31];
                    int i9 = context.pos;
                    context.pos = i9 + 1;
                    ensureBufferSize[i9] = bArr2[((int) (j >> 30)) & 31];
                    int i10 = context.pos;
                    context.pos = i10 + 1;
                    ensureBufferSize[i10] = bArr2[((int) (j >> 25)) & 31];
                    int i11 = context.pos;
                    context.pos = i11 + 1;
                    ensureBufferSize[i11] = bArr2[((int) (j >> 20)) & 31];
                    int i12 = context.pos;
                    context.pos = i12 + 1;
                    ensureBufferSize[i12] = bArr2[((int) (j >> 15)) & 31];
                    int i13 = context.pos;
                    context.pos = i13 + 1;
                    ensureBufferSize[i13] = bArr2[((int) (j >> 10)) & 31];
                    int i14 = context.pos;
                    context.pos = i14 + 1;
                    ensureBufferSize[i14] = bArr2[((int) (j >> 5)) & 31];
                    int i15 = context.pos;
                    context.pos = i15 + 1;
                    ensureBufferSize[i15] = bArr2[((int) j) & 31];
                    context.currentLinePos += 8;
                    int i16 = this.lineLength;
                    if (i16 > 0 && i16 <= context.currentLinePos) {
                        byte[] bArr3 = this.lineSeparator;
                        System.arraycopy(bArr3, 0, ensureBufferSize, context.pos, bArr3.length);
                        context.pos += this.lineSeparator.length;
                        context.currentLinePos = 0;
                    }
                }
                i5++;
                i4 = 1;
                i3 = i6;
            }
            return;
        }
        context.eof = true;
        if (context.modulus == 0 && this.lineLength == 0) {
            return;
        }
        byte[] ensureBufferSize2 = ensureBufferSize(this.encodeSize, context);
        int i17 = context.pos;
        int i18 = context.modulus;
        if (i18 != 0) {
            if (i18 == 1) {
                context.pos = i17 + 1;
                byte[] bArr4 = this.encodeTable;
                long j2 = context.lbitWorkArea;
                ensureBufferSize2[i17] = bArr4[((int) (j2 >> 3)) & 31];
                int i19 = context.pos;
                context.pos = i19 + 1;
                ensureBufferSize2[i19] = bArr4[((int) (j2 << 2)) & 31];
                int i20 = context.pos;
                context.pos = i20 + 1;
                byte b = this.pad;
                ensureBufferSize2[i20] = b;
                int i21 = context.pos;
                context.pos = i21 + 1;
                ensureBufferSize2[i21] = b;
                int i22 = context.pos;
                context.pos = i22 + 1;
                ensureBufferSize2[i22] = b;
                int i23 = context.pos;
                context.pos = i23 + 1;
                ensureBufferSize2[i23] = b;
                int i24 = context.pos;
                context.pos = i24 + 1;
                ensureBufferSize2[i24] = b;
                int i25 = context.pos;
                context.pos = i25 + 1;
                ensureBufferSize2[i25] = b;
            } else if (i18 == 2) {
                context.pos = i17 + 1;
                byte[] bArr5 = this.encodeTable;
                long j3 = context.lbitWorkArea;
                ensureBufferSize2[i17] = bArr5[((int) (j3 >> 11)) & 31];
                int i26 = context.pos;
                context.pos = i26 + 1;
                ensureBufferSize2[i26] = bArr5[((int) (j3 >> 6)) & 31];
                int i27 = context.pos;
                context.pos = i27 + 1;
                ensureBufferSize2[i27] = bArr5[((int) (j3 >> 1)) & 31];
                int i28 = context.pos;
                context.pos = i28 + 1;
                ensureBufferSize2[i28] = bArr5[((int) (j3 << 4)) & 31];
                int i29 = context.pos;
                context.pos = i29 + 1;
                byte b2 = this.pad;
                ensureBufferSize2[i29] = b2;
                int i30 = context.pos;
                context.pos = i30 + 1;
                ensureBufferSize2[i30] = b2;
                int i31 = context.pos;
                context.pos = i31 + 1;
                ensureBufferSize2[i31] = b2;
                int i32 = context.pos;
                context.pos = i32 + 1;
                ensureBufferSize2[i32] = b2;
            } else if (i18 == 3) {
                context.pos = i17 + 1;
                byte[] bArr6 = this.encodeTable;
                long j4 = context.lbitWorkArea;
                ensureBufferSize2[i17] = bArr6[((int) (j4 >> 19)) & 31];
                int i33 = context.pos;
                context.pos = i33 + 1;
                ensureBufferSize2[i33] = bArr6[((int) (j4 >> 14)) & 31];
                int i34 = context.pos;
                context.pos = i34 + 1;
                ensureBufferSize2[i34] = bArr6[((int) (j4 >> 9)) & 31];
                int i35 = context.pos;
                context.pos = i35 + 1;
                ensureBufferSize2[i35] = bArr6[((int) (j4 >> 4)) & 31];
                int i36 = context.pos;
                context.pos = i36 + 1;
                ensureBufferSize2[i36] = bArr6[((int) (j4 << 1)) & 31];
                int i37 = context.pos;
                context.pos = i37 + 1;
                byte b3 = this.pad;
                ensureBufferSize2[i37] = b3;
                int i38 = context.pos;
                context.pos = i38 + 1;
                ensureBufferSize2[i38] = b3;
                int i39 = context.pos;
                context.pos = i39 + 1;
                ensureBufferSize2[i39] = b3;
            } else if (i18 == 4) {
                context.pos = i17 + 1;
                byte[] bArr7 = this.encodeTable;
                long j5 = context.lbitWorkArea;
                ensureBufferSize2[i17] = bArr7[((int) (j5 >> 27)) & 31];
                int i40 = context.pos;
                context.pos = i40 + 1;
                ensureBufferSize2[i40] = bArr7[((int) (j5 >> 22)) & 31];
                int i41 = context.pos;
                context.pos = i41 + 1;
                ensureBufferSize2[i41] = bArr7[((int) (j5 >> 17)) & 31];
                int i42 = context.pos;
                context.pos = i42 + 1;
                ensureBufferSize2[i42] = bArr7[((int) (j5 >> 12)) & 31];
                int i43 = context.pos;
                context.pos = i43 + 1;
                ensureBufferSize2[i43] = bArr7[((int) (j5 >> 7)) & 31];
                int i44 = context.pos;
                context.pos = i44 + 1;
                ensureBufferSize2[i44] = bArr7[((int) (j5 >> 2)) & 31];
                int i45 = context.pos;
                context.pos = i45 + 1;
                ensureBufferSize2[i45] = bArr7[((int) (j5 << 3)) & 31];
                int i46 = context.pos;
                context.pos = i46 + 1;
                ensureBufferSize2[i46] = this.pad;
            } else {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Impossible modulus ");
                outline107.append(context.modulus);
                throw new IllegalStateException(outline107.toString());
            }
        }
        int i47 = context.currentLinePos;
        int i48 = context.pos;
        context.currentLinePos = (i48 - i17) + i47;
        if (this.lineLength <= 0 || context.currentLinePos <= 0) {
            return;
        }
        byte[] bArr8 = this.lineSeparator;
        System.arraycopy(bArr8, 0, ensureBufferSize2, i48, bArr8.length);
        context.pos += this.lineSeparator.length;
    }

    @Override // org.apache.commons.codec.binary.BaseNCodec
    public boolean isInAlphabet(byte b) {
        if (b >= 0) {
            byte[] bArr = this.decodeTable;
            if (b < bArr.length && bArr[b] != -1) {
                return true;
            }
        }
        return false;
    }

    public Base32(byte b) {
        this(false, b);
    }

    public Base32(boolean z) {
        this(0, null, z, GenericAccessProfile.INFORMATION_DATA_3D);
    }

    public Base32(boolean z, byte b) {
        this(0, null, z, b);
    }

    public Base32(int i) {
        this(i, CHUNK_SEPARATOR);
    }

    public Base32(int i, byte[] bArr) {
        this(i, bArr, false, GenericAccessProfile.INFORMATION_DATA_3D);
    }

    public Base32(int i, byte[] bArr, boolean z) {
        this(i, bArr, z, GenericAccessProfile.INFORMATION_DATA_3D);
    }

    public Base32(int i, byte[] bArr, boolean z, byte b) {
        super(5, 8, i, bArr == null ? 0 : bArr.length, b);
        if (z) {
            this.encodeTable = HEX_ENCODE_TABLE;
            this.decodeTable = HEX_DECODE_TABLE;
        } else {
            this.encodeTable = ENCODE_TABLE;
            this.decodeTable = DECODE_TABLE;
        }
        if (i <= 0) {
            this.encodeSize = 8;
            this.lineSeparator = null;
        } else if (bArr != null) {
            if (!containsAlphabetOrPad(bArr)) {
                this.encodeSize = bArr.length + 8;
                this.lineSeparator = new byte[bArr.length];
                System.arraycopy(bArr, 0, this.lineSeparator, 0, bArr.length);
            } else {
                throw new IllegalArgumentException(GeneratedOutlineSupport1.outline75("lineSeparator must not contain Base32 characters: [", StringUtils.newStringUtf8(bArr), "]"));
            }
        } else {
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline52("lineLength ", i, " > 0, but lineSeparator is null"));
        }
        this.decodeSize = this.encodeSize - 1;
        if (isInAlphabet(b) || BaseNCodec.isWhiteSpace(b)) {
            throw new IllegalArgumentException("pad must not be in alphabet or whitespace");
        }
    }
}
