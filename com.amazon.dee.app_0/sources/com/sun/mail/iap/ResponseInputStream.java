package com.sun.mail.iap;

import com.sun.mail.util.ASCIIUtility;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
/* loaded from: classes3.dex */
public class ResponseInputStream {
    private static final int incrementSlop = 16;
    private static final int maxIncrement = 262144;
    private static final int minIncrement = 256;
    private BufferedInputStream bin;

    public ResponseInputStream(InputStream inputStream) {
        this.bin = new BufferedInputStream(inputStream, 2048);
    }

    public int available() throws IOException {
        return this.bin.available();
    }

    public ByteArray readResponse() throws IOException {
        return readResponse(null);
    }

    public ByteArray readResponse(ByteArray byteArray) throws IOException {
        if (byteArray == null) {
            byteArray = new ByteArray(new byte[128], 0, 128);
        }
        byte[] bytes = byteArray.getBytes();
        int i = 0;
        while (true) {
            int i2 = 0;
            byte[] bArr = bytes;
            boolean z = false;
            while (!z && (i2 = this.bin.read()) != -1) {
                if (i2 == 10 && i > 0 && bArr[i - 1] == 13) {
                    z = true;
                }
                if (i >= bArr.length) {
                    int length = bArr.length;
                    if (length > 262144) {
                        length = 262144;
                    }
                    byteArray.grow(length);
                    bArr = byteArray.getBytes();
                }
                bArr[i] = (byte) i2;
                i++;
            }
            if (i2 == -1) {
                throw new IOException("Connection dropped by server?");
            }
            if (i < 5) {
                break;
            }
            int i3 = i - 3;
            if (bArr[i3] != 125) {
                break;
            }
            int i4 = i - 4;
            while (i4 >= 0 && bArr[i4] != 123) {
                i4--;
            }
            if (i4 < 0) {
                break;
            }
            try {
                int parseInt = ASCIIUtility.parseInt(bArr, i4 + 1, i3);
                if (parseInt > 0) {
                    int length2 = bArr.length - i;
                    int i5 = parseInt + 16;
                    if (i5 > length2) {
                        int i6 = i5 - length2;
                        int i7 = 256;
                        if (256 <= i6) {
                            i7 = i6;
                        }
                        byteArray.grow(i7);
                        bArr = byteArray.getBytes();
                    }
                    while (parseInt > 0) {
                        int read = this.bin.read(bArr, i, parseInt);
                        parseInt -= read;
                        i += read;
                    }
                }
                bytes = bArr;
            } catch (NumberFormatException unused) {
            }
        }
        byteArray.setCount(i);
        return byteArray;
    }
}
