package com.sun.mail.util;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.EOFException;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.bouncycastle.pqc.math.linearalgebra.Matrix;
/* loaded from: classes3.dex */
public class BASE64DecoderStream extends FilterInputStream {
    private static final char[] pem_array = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', Matrix.MATRIX_TYPE_RANDOM_LT, 'M', 'N', 'O', 'P', 'Q', Matrix.MATRIX_TYPE_RANDOM_REGULAR, 'S', 'T', Matrix.MATRIX_TYPE_RANDOM_UT, 'V', 'W', 'X', 'Y', Matrix.MATRIX_TYPE_ZERO, 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/'};
    private static final byte[] pem_convert_array = new byte[256];
    private byte[] buffer;
    private int bufsize;
    private boolean ignoreErrors;
    private int index;
    private byte[] input_buffer;
    private int input_len;
    private int input_pos;

    static {
        int i = 0;
        for (int i2 = 0; i2 < 255; i2++) {
            pem_convert_array[i2] = -1;
        }
        while (true) {
            char[] cArr = pem_array;
            if (i < cArr.length) {
                pem_convert_array[cArr[i]] = (byte) i;
                i++;
            } else {
                return;
            }
        }
    }

    public BASE64DecoderStream(InputStream inputStream) {
        super(inputStream);
        this.buffer = new byte[3];
        this.bufsize = 0;
        this.index = 0;
        this.input_buffer = new byte[8190];
        this.input_pos = 0;
        this.input_len = 0;
        this.ignoreErrors = false;
        this.ignoreErrors = PropUtil.getBooleanSystemProperty("mail.mime.base64.ignoreerrors", false);
    }

    private int decode(byte[] bArr, int i, int i2) throws IOException {
        int i3 = i;
        while (i2 >= 3) {
            boolean z = false;
            int i4 = 0;
            int i5 = 0;
            while (i4 < 4) {
                int i6 = getByte();
                if (i6 == -1 || i6 == -2) {
                    if (i6 == -1) {
                        if (i4 == 0) {
                            return i3 - i;
                        }
                        if (!this.ignoreErrors) {
                            StringBuilder outline109 = GeneratedOutlineSupport1.outline109("BASE64Decoder: Error in encoded stream: needed 4 valid base64 characters but only got ", i4, " before EOF");
                            outline109.append(recentChars());
                            throw new DecodingException(outline109.toString());
                        }
                        z = true;
                    } else if (i4 < 2 && !this.ignoreErrors) {
                        StringBuilder outline1092 = GeneratedOutlineSupport1.outline109("BASE64Decoder: Error in encoded stream: needed at least 2 valid base64 characters, but only got ", i4, " before padding character (=)");
                        outline1092.append(recentChars());
                        throw new DecodingException(outline1092.toString());
                    } else if (i4 == 0) {
                        return i3 - i;
                    }
                    int i7 = i4 - 1;
                    if (i7 == 0) {
                        i7 = 1;
                    }
                    int i8 = i5 << 6;
                    for (int i9 = i4 + 1; i9 < 4; i9++) {
                        if (!z) {
                            int i10 = getByte();
                            if (i10 == -1) {
                                if (!this.ignoreErrors) {
                                    StringBuilder outline107 = GeneratedOutlineSupport1.outline107("BASE64Decoder: Error in encoded stream: hit EOF while looking for padding characters (=)");
                                    outline107.append(recentChars());
                                    throw new DecodingException(outline107.toString());
                                }
                            } else if (i10 != -2 && !this.ignoreErrors) {
                                StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("BASE64Decoder: Error in encoded stream: found valid base64 character after a padding character (=)");
                                outline1072.append(recentChars());
                                throw new DecodingException(outline1072.toString());
                            }
                        }
                        i8 <<= 6;
                    }
                    int i11 = i8 >> 8;
                    if (i7 == 2) {
                        bArr[i3 + 1] = (byte) (i11 & 255);
                    }
                    bArr[i3] = (byte) ((i11 >> 8) & 255);
                    return (i3 + i7) - i;
                }
                i4++;
                i5 = (i5 << 6) | i6;
            }
            bArr[i3 + 2] = (byte) (i5 & 255);
            int i12 = i5 >> 8;
            bArr[i3 + 1] = (byte) (i12 & 255);
            bArr[i3] = (byte) ((i12 >> 8) & 255);
            i2 -= 3;
            i3 += 3;
        }
        return i3 - i;
    }

    private int getByte() throws IOException {
        byte b;
        do {
            if (this.input_pos >= this.input_len) {
                try {
                    this.input_len = ((FilterInputStream) this).in.read(this.input_buffer);
                    if (this.input_len <= 0) {
                        return -1;
                    }
                    this.input_pos = 0;
                } catch (EOFException unused) {
                    return -1;
                }
            }
            byte[] bArr = this.input_buffer;
            int i = this.input_pos;
            this.input_pos = i + 1;
            int i2 = bArr[i] & 255;
            if (i2 == 61) {
                return -2;
            }
            b = pem_convert_array[i2];
        } while (b == -1);
        return b;
    }

    private String recentChars() {
        String outline72;
        int i = this.input_pos;
        if (i > 10) {
            i = 10;
        }
        if (i > 0) {
            String str = ", the " + i + " most recent characters were: \"";
            for (int i2 = this.input_pos - i; i2 < this.input_pos; i2++) {
                char c = (char) (this.input_buffer[i2] & 255);
                if (c == '\t') {
                    outline72 = GeneratedOutlineSupport1.outline72(str, "\\t");
                } else if (c == '\n') {
                    outline72 = GeneratedOutlineSupport1.outline72(str, "\\n");
                } else if (c == '\r') {
                    outline72 = GeneratedOutlineSupport1.outline72(str, "\\r");
                } else if (c >= ' ' && c < 127) {
                    outline72 = GeneratedOutlineSupport1.outline47(str, c);
                } else {
                    outline72 = GeneratedOutlineSupport1.outline74(str, "\\", c);
                }
                str = outline72;
            }
            return GeneratedOutlineSupport1.outline72(str, EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_PLAYER_INFO_PAYLOAD_DESERIALIZED);
        }
        return "";
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int available() throws IOException {
        return (this.bufsize - this.index) + ((((FilterInputStream) this).in.available() * 3) / 4);
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public boolean markSupported() {
        return false;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read() throws IOException {
        if (this.index >= this.bufsize) {
            byte[] bArr = this.buffer;
            this.bufsize = decode(bArr, 0, bArr.length);
            if (this.bufsize <= 0) {
                return -1;
            }
            this.index = 0;
        }
        byte[] bArr2 = this.buffer;
        int i = this.index;
        this.index = i + 1;
        return bArr2[i] & 255;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public long skip(long j) throws IOException {
        long j2 = 0;
        while (true) {
            long j3 = j - 1;
            if (j <= 0 || read() < 0) {
                break;
            }
            j2++;
            j = j3;
        }
        return j2;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        int i3 = i;
        while (true) {
            int i4 = this.index;
            if (i4 >= this.bufsize || i2 <= 0) {
                break;
            }
            byte[] bArr2 = this.buffer;
            this.index = i4 + 1;
            bArr[i3] = bArr2[i4];
            i2--;
            i3++;
        }
        if (this.index >= this.bufsize) {
            this.index = 0;
            this.bufsize = 0;
        }
        int i5 = (i2 / 3) * 3;
        if (i5 > 0) {
            int decode = decode(bArr, i3, i5);
            i3 += decode;
            i2 -= decode;
            if (decode != i5) {
                if (i3 != i) {
                    return i3 - i;
                }
                return -1;
            }
        }
        while (i2 > 0) {
            int read = read();
            if (read == -1) {
                break;
            }
            bArr[i3] = (byte) read;
            i2--;
            i3++;
        }
        if (i3 == i) {
            return -1;
        }
        return i3 - i;
    }

    public BASE64DecoderStream(InputStream inputStream, boolean z) {
        super(inputStream);
        this.buffer = new byte[3];
        this.bufsize = 0;
        this.index = 0;
        this.input_buffer = new byte[8190];
        this.input_pos = 0;
        this.input_len = 0;
        this.ignoreErrors = false;
        this.ignoreErrors = z;
    }

    public static byte[] decode(byte[] bArr) {
        int i;
        int i2;
        int length = (bArr.length / 4) * 3;
        if (length == 0) {
            return bArr;
        }
        if (bArr[bArr.length - 1] == 61) {
            length--;
            if (bArr[bArr.length - 2] == 61) {
                length--;
            }
        }
        byte[] bArr2 = new byte[length];
        int length2 = bArr.length;
        int i3 = 0;
        int i4 = 0;
        while (length2 > 0) {
            byte[] bArr3 = pem_convert_array;
            int i5 = i3 + 1;
            int i6 = i5 + 1;
            int i7 = ((bArr3[bArr[i3] & 255] << 6) | bArr3[bArr[i5] & 255]) << 6;
            if (bArr[i6] != 61) {
                i2 = i6 + 1;
                i7 |= bArr3[bArr[i6] & 255];
                i = 3;
            } else {
                i = 2;
                i2 = i6;
            }
            int i8 = i7 << 6;
            if (bArr[i2] != 61) {
                i8 |= pem_convert_array[bArr[i2] & 255];
                i2++;
            } else {
                i--;
            }
            if (i > 2) {
                bArr2[i4 + 2] = (byte) (i8 & 255);
            }
            int i9 = i8 >> 8;
            if (i > 1) {
                bArr2[i4 + 1] = (byte) (i9 & 255);
            }
            bArr2[i4] = (byte) ((i9 >> 8) & 255);
            i4 += i;
            length2 -= 4;
            i3 = i2;
        }
        return bArr2;
    }
}
