package org.apache.commons.fileupload.util.mime;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.io.OutputStream;
/* loaded from: classes4.dex */
final class QuotedPrintableDecoder {
    private static final int UPPER_NIBBLE_SHIFT = 4;

    private QuotedPrintableDecoder() {
    }

    public static int decode(byte[] bArr, OutputStream outputStream) throws IOException {
        int i = 0;
        int length = bArr.length + 0;
        int i2 = 0;
        while (i < length) {
            int i3 = i + 1;
            int i4 = bArr[i];
            if (i4 == 95) {
                outputStream.write(32);
            } else if (i4 == 61) {
                int i5 = i3 + 1;
                if (i5 < length) {
                    byte b = bArr[i3];
                    int i6 = i5 + 1;
                    byte b2 = bArr[i5];
                    if (b != 13) {
                        outputStream.write(hexToBinary(b2) | (hexToBinary(b) << 4));
                        i2++;
                    } else if (b2 != 10) {
                        throw new IOException("Invalid quoted printable encoding; CR must be followed by LF");
                    }
                    i = i6;
                } else {
                    throw new IOException("Invalid quoted printable encoding; truncated escape sequence");
                }
            } else {
                outputStream.write(i4);
                i2++;
            }
            i = i3;
        }
        return i2;
    }

    private static int hexToBinary(byte b) throws IOException {
        int digit = Character.digit((char) b, 16);
        if (digit != -1) {
            return digit;
        }
        throw new IOException(GeneratedOutlineSupport1.outline49("Invalid quoted printable encoding: not a valid hex digit: ", b));
    }
}
