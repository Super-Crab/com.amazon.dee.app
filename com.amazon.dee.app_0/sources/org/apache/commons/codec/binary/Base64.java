package org.apache.commons.codec.binary;

import com.amazon.alexa.accessory.internal.bluetooth.GenericAccessProfile;
import com.amazon.communication.gw.CorpusBuilder;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.base.Ascii;
import java.math.BigInteger;
import okio.Utf8;
import org.apache.commons.codec.binary.BaseNCodec;
import org.apache.commons.fileupload.MultipartStream;
/* loaded from: classes4.dex */
public class Base64 extends BaseNCodec {
    private static final int BITS_PER_ENCODED_BYTE = 6;
    private static final int BYTES_PER_ENCODED_BLOCK = 4;
    private static final int BYTES_PER_UNENCODED_BLOCK = 3;
    private static final int MASK_6BITS = 63;
    private final int decodeSize;
    private final byte[] decodeTable;
    private final int encodeSize;
    private final byte[] encodeTable;
    private final byte[] lineSeparator;
    static final byte[] CHUNK_SEPARATOR = {13, 10};
    private static final byte[] STANDARD_ENCODE_TABLE = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, GenericAccessProfile.MESH_BEACON, 47};
    private static final byte[] URL_SAFE_ENCODE_TABLE = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, MultipartStream.DASH, 95};
    private static final byte[] DECODE_TABLE = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, 62, -1, Utf8.REPLACEMENT_BYTE, 52, 53, 54, 55, 56, 57, 58, CorpusBuilder.DELIMETER, 60, GenericAccessProfile.INFORMATION_DATA_3D, -1, -1, -1, -1, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, Utf8.REPLACEMENT_BYTE, -1, Ascii.SUB, 27, 28, 29, 30, 31, 32, GenericAccessProfile.SERVICE_DATA_128BIT, GenericAccessProfile.LE_SECURE_CONNECTIONS_CONFIRMATION_VALUE, GenericAccessProfile.LE_SECURE_CONNECTIONS_RANDOM_VALUE, GenericAccessProfile.URI, GenericAccessProfile.INDOOR_POSITIONING, GenericAccessProfile.TRANSPORT_DISCOVERY_DATA, GenericAccessProfile.LE_SUPPORTED_FEATURES, GenericAccessProfile.CHANNEL_MAP_UPDATE_INDICATION, GenericAccessProfile.PB_ADV, GenericAccessProfile.MESH_MESSAGE, GenericAccessProfile.MESH_BEACON, 44, MultipartStream.DASH, 46, 47, 48, 49, 50, 51};

    public Base64() {
        this(0);
    }

    public static byte[] decodeBase64(String str) {
        return new Base64().decode(str);
    }

    public static BigInteger decodeInteger(byte[] bArr) {
        return new BigInteger(1, decodeBase64(bArr));
    }

    public static byte[] encodeBase64(byte[] bArr) {
        return encodeBase64(bArr, false);
    }

    public static byte[] encodeBase64Chunked(byte[] bArr) {
        return encodeBase64(bArr, true);
    }

    public static String encodeBase64String(byte[] bArr) {
        return StringUtils.newStringUtf8(encodeBase64(bArr, false));
    }

    public static byte[] encodeBase64URLSafe(byte[] bArr) {
        return encodeBase64(bArr, false, true);
    }

    public static String encodeBase64URLSafeString(byte[] bArr) {
        return StringUtils.newStringUtf8(encodeBase64(bArr, false, true));
    }

    public static byte[] encodeInteger(BigInteger bigInteger) {
        if (bigInteger != null) {
            return encodeBase64(toIntegerBytes(bigInteger), false);
        }
        throw new NullPointerException("encodeInteger called with null parameter");
    }

    @Deprecated
    public static boolean isArrayByteBase64(byte[] bArr) {
        return isBase64(bArr);
    }

    public static boolean isBase64(byte b) {
        if (b != 61) {
            if (b >= 0) {
                byte[] bArr = DECODE_TABLE;
                if (b >= bArr.length || bArr[b] == -1) {
                }
            }
            return false;
        }
        return true;
    }

    static byte[] toIntegerBytes(BigInteger bigInteger) {
        int bitLength = ((bigInteger.bitLength() + 7) >> 3) << 3;
        byte[] byteArray = bigInteger.toByteArray();
        if (bigInteger.bitLength() % 8 == 0 || (bigInteger.bitLength() / 8) + 1 != bitLength / 8) {
            int i = 0;
            int length = byteArray.length;
            if (bigInteger.bitLength() % 8 == 0) {
                length--;
                i = 1;
            }
            int i2 = bitLength / 8;
            int i3 = i2 - length;
            byte[] bArr = new byte[i2];
            System.arraycopy(byteArray, i, bArr, i3, length);
            return bArr;
        }
        return byteArray;
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
        while (true) {
            if (i3 >= i2) {
                break;
            }
            byte[] ensureBufferSize = ensureBufferSize(this.decodeSize, context);
            int i4 = i + 1;
            byte b2 = bArr[i];
            if (b2 == this.pad) {
                context.eof = true;
                break;
            }
            if (b2 >= 0) {
                byte[] bArr2 = DECODE_TABLE;
                if (b2 < bArr2.length && (b = bArr2[b2]) >= 0) {
                    context.modulus = (context.modulus + 1) % 4;
                    context.ibitWorkArea = (context.ibitWorkArea << 6) + b;
                    if (context.modulus == 0) {
                        int i5 = context.pos;
                        context.pos = i5 + 1;
                        int i6 = context.ibitWorkArea;
                        ensureBufferSize[i5] = (byte) ((i6 >> 16) & 255);
                        int i7 = context.pos;
                        context.pos = i7 + 1;
                        ensureBufferSize[i7] = (byte) ((i6 >> 8) & 255);
                        int i8 = context.pos;
                        context.pos = i8 + 1;
                        ensureBufferSize[i8] = (byte) (i6 & 255);
                    }
                }
            }
            i3++;
            i = i4;
        }
        if (!context.eof || context.modulus == 0) {
            return;
        }
        byte[] ensureBufferSize2 = ensureBufferSize(this.decodeSize, context);
        int i9 = context.modulus;
        if (i9 == 1) {
            return;
        }
        if (i9 == 2) {
            context.ibitWorkArea >>= 4;
            int i10 = context.pos;
            context.pos = i10 + 1;
            ensureBufferSize2[i10] = (byte) (context.ibitWorkArea & 255);
        } else if (i9 == 3) {
            context.ibitWorkArea >>= 2;
            int i11 = context.pos;
            context.pos = i11 + 1;
            int i12 = context.ibitWorkArea;
            ensureBufferSize2[i11] = (byte) ((i12 >> 8) & 255);
            int i13 = context.pos;
            context.pos = i13 + 1;
            ensureBufferSize2[i13] = (byte) (i12 & 255);
        } else {
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
            int i4 = 0;
            while (i4 < i2) {
                byte[] ensureBufferSize = ensureBufferSize(this.encodeSize, context);
                context.modulus = (context.modulus + 1) % 3;
                int i5 = i3 + 1;
                int i6 = bArr[i3];
                if (i6 < 0) {
                    i6 += 256;
                }
                context.ibitWorkArea = (context.ibitWorkArea << 8) + i6;
                if (context.modulus == 0) {
                    int i7 = context.pos;
                    context.pos = i7 + 1;
                    byte[] bArr2 = this.encodeTable;
                    int i8 = context.ibitWorkArea;
                    ensureBufferSize[i7] = bArr2[(i8 >> 18) & 63];
                    int i9 = context.pos;
                    context.pos = i9 + 1;
                    ensureBufferSize[i9] = bArr2[(i8 >> 12) & 63];
                    int i10 = context.pos;
                    context.pos = i10 + 1;
                    ensureBufferSize[i10] = bArr2[(i8 >> 6) & 63];
                    int i11 = context.pos;
                    context.pos = i11 + 1;
                    ensureBufferSize[i11] = bArr2[i8 & 63];
                    context.currentLinePos += 4;
                    int i12 = this.lineLength;
                    if (i12 > 0 && i12 <= context.currentLinePos) {
                        byte[] bArr3 = this.lineSeparator;
                        System.arraycopy(bArr3, 0, ensureBufferSize, context.pos, bArr3.length);
                        context.pos += this.lineSeparator.length;
                        context.currentLinePos = 0;
                    }
                }
                i4++;
                i3 = i5;
            }
            return;
        }
        context.eof = true;
        if (context.modulus == 0 && this.lineLength == 0) {
            return;
        }
        byte[] ensureBufferSize2 = ensureBufferSize(this.encodeSize, context);
        int i13 = context.pos;
        int i14 = context.modulus;
        if (i14 != 0) {
            if (i14 == 1) {
                context.pos = i13 + 1;
                byte[] bArr4 = this.encodeTable;
                int i15 = context.ibitWorkArea;
                ensureBufferSize2[i13] = bArr4[(i15 >> 2) & 63];
                int i16 = context.pos;
                context.pos = i16 + 1;
                ensureBufferSize2[i16] = bArr4[(i15 << 4) & 63];
                if (bArr4 == STANDARD_ENCODE_TABLE) {
                    int i17 = context.pos;
                    context.pos = i17 + 1;
                    byte b = this.pad;
                    ensureBufferSize2[i17] = b;
                    int i18 = context.pos;
                    context.pos = i18 + 1;
                    ensureBufferSize2[i18] = b;
                }
            } else if (i14 == 2) {
                context.pos = i13 + 1;
                byte[] bArr5 = this.encodeTable;
                int i19 = context.ibitWorkArea;
                ensureBufferSize2[i13] = bArr5[(i19 >> 10) & 63];
                int i20 = context.pos;
                context.pos = i20 + 1;
                ensureBufferSize2[i20] = bArr5[(i19 >> 4) & 63];
                int i21 = context.pos;
                context.pos = i21 + 1;
                ensureBufferSize2[i21] = bArr5[(i19 << 2) & 63];
                if (bArr5 == STANDARD_ENCODE_TABLE) {
                    int i22 = context.pos;
                    context.pos = i22 + 1;
                    ensureBufferSize2[i22] = this.pad;
                }
            } else {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Impossible modulus ");
                outline107.append(context.modulus);
                throw new IllegalStateException(outline107.toString());
            }
        }
        int i23 = context.currentLinePos;
        int i24 = context.pos;
        context.currentLinePos = (i24 - i13) + i23;
        if (this.lineLength <= 0 || context.currentLinePos <= 0) {
            return;
        }
        byte[] bArr6 = this.lineSeparator;
        System.arraycopy(bArr6, 0, ensureBufferSize2, i24, bArr6.length);
        context.pos += this.lineSeparator.length;
    }

    @Override // org.apache.commons.codec.binary.BaseNCodec
    protected boolean isInAlphabet(byte b) {
        if (b >= 0) {
            byte[] bArr = this.decodeTable;
            if (b < bArr.length && bArr[b] != -1) {
                return true;
            }
        }
        return false;
    }

    public boolean isUrlSafe() {
        return this.encodeTable == URL_SAFE_ENCODE_TABLE;
    }

    public Base64(boolean z) {
        this(76, CHUNK_SEPARATOR, z);
    }

    public static byte[] decodeBase64(byte[] bArr) {
        return new Base64().decode(bArr);
    }

    public static byte[] encodeBase64(byte[] bArr, boolean z) {
        return encodeBase64(bArr, z, false);
    }

    public static boolean isBase64(String str) {
        return isBase64(StringUtils.getBytesUtf8(str));
    }

    public Base64(int i) {
        this(i, CHUNK_SEPARATOR);
    }

    public static byte[] encodeBase64(byte[] bArr, boolean z, boolean z2) {
        return encodeBase64(bArr, z, z2, Integer.MAX_VALUE);
    }

    public static boolean isBase64(byte[] bArr) {
        for (int i = 0; i < bArr.length; i++) {
            if (!isBase64(bArr[i]) && !BaseNCodec.isWhiteSpace(bArr[i])) {
                return false;
            }
        }
        return true;
    }

    public Base64(int i, byte[] bArr) {
        this(i, bArr, false);
    }

    public static byte[] encodeBase64(byte[] bArr, boolean z, boolean z2, int i) {
        if (bArr == null || bArr.length == 0) {
            return bArr;
        }
        Base64 base64 = z ? new Base64(z2) : new Base64(0, CHUNK_SEPARATOR, z2);
        long encodedLength = base64.getEncodedLength(bArr);
        if (encodedLength <= i) {
            return base64.encode(bArr);
        }
        throw new IllegalArgumentException("Input array too big, the output array would be bigger (" + encodedLength + ") than the specified maximum size of " + i);
    }

    public Base64(int i, byte[] bArr, boolean z) {
        super(3, 4, i, bArr == null ? 0 : bArr.length);
        this.decodeTable = DECODE_TABLE;
        if (bArr != null) {
            if (containsAlphabetOrPad(bArr)) {
                throw new IllegalArgumentException(GeneratedOutlineSupport1.outline75("lineSeparator must not contain base64 characters: [", StringUtils.newStringUtf8(bArr), "]"));
            }
            if (i > 0) {
                this.encodeSize = bArr.length + 4;
                this.lineSeparator = new byte[bArr.length];
                System.arraycopy(bArr, 0, this.lineSeparator, 0, bArr.length);
            } else {
                this.encodeSize = 4;
                this.lineSeparator = null;
            }
        } else {
            this.encodeSize = 4;
            this.lineSeparator = null;
        }
        this.decodeSize = this.encodeSize - 1;
        this.encodeTable = z ? URL_SAFE_ENCODE_TABLE : STANDARD_ENCODE_TABLE;
    }
}
