package org.bouncycastle.gpg;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.io.InputStream;
import org.bouncycastle.bcpg.S2K;
import org.bouncycastle.util.io.Streams;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes4.dex */
public class SXprUtils {
    SXprUtils() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static S2K parseS2K(InputStream inputStream) throws IOException {
        skipOpenParenthesis(inputStream);
        readString(inputStream, inputStream.read());
        byte[] readBytes = readBytes(inputStream, inputStream.read());
        final long parseLong = Long.parseLong(readString(inputStream, inputStream.read()));
        skipCloseParenthesis(inputStream);
        return new S2K(2, readBytes, (int) parseLong) { // from class: org.bouncycastle.gpg.SXprUtils.1
            @Override // org.bouncycastle.bcpg.S2K
            public long getIterationCount() {
                return parseLong;
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static byte[] readBytes(InputStream inputStream, int i) throws IOException {
        byte[] bArr = new byte[readLength(inputStream, i)];
        Streams.readFully(inputStream, bArr);
        return bArr;
    }

    private static int readLength(InputStream inputStream, int i) throws IOException {
        int i2;
        while (true) {
            i2 = i - 48;
            int read = inputStream.read();
            if (read < 0 || read == 58) {
                break;
            }
            i = (i2 * 10) + read;
        }
        return i2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String readString(InputStream inputStream, int i) throws IOException {
        char[] cArr = new char[readLength(inputStream, i)];
        for (int i2 = 0; i2 != cArr.length; i2++) {
            cArr[i2] = (char) inputStream.read();
        }
        return new String(cArr);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void skipCloseParenthesis(InputStream inputStream) throws IOException {
        if (inputStream.read() == 41) {
            return;
        }
        throw new IOException("unknown character encountered");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void skipOpenParenthesis(InputStream inputStream) throws IOException {
        int read = inputStream.read();
        if (read == 40) {
            return;
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("unknown character encountered: ");
        outline107.append((char) read);
        throw new IOException(outline107.toString());
    }
}
