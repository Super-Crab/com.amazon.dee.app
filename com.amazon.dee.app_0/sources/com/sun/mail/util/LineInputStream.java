package com.sun.mail.util;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;
/* loaded from: classes3.dex */
public class LineInputStream extends FilterInputStream {
    private static int MAX_INCR = 1048576;
    private char[] lineBuffer;

    public LineInputStream(InputStream inputStream) {
        super(inputStream);
        this.lineBuffer = null;
    }

    public String readLine() throws IOException {
        int read;
        char[] cArr = this.lineBuffer;
        if (cArr == null) {
            cArr = new char[128];
            this.lineBuffer = cArr;
        }
        int length = cArr.length;
        int i = 0;
        while (true) {
            read = ((FilterInputStream) this).in.read();
            if (read == -1 || read == 10) {
                break;
            }
            boolean z = true;
            if (read == 13) {
                if (((FilterInputStream) this).in.markSupported()) {
                    ((FilterInputStream) this).in.mark(2);
                }
                int read2 = ((FilterInputStream) this).in.read();
                if (read2 == 13) {
                    read2 = ((FilterInputStream) this).in.read();
                } else {
                    z = false;
                }
                if (read2 != 10) {
                    if (((FilterInputStream) this).in.markSupported()) {
                        ((FilterInputStream) this).in.reset();
                    } else {
                        if (!(((FilterInputStream) this).in instanceof PushbackInputStream)) {
                            ((FilterInputStream) this).in = new PushbackInputStream(((FilterInputStream) this).in, 2);
                        }
                        if (read2 != -1) {
                            ((PushbackInputStream) ((FilterInputStream) this).in).unread(read2);
                        }
                        if (z) {
                            ((PushbackInputStream) ((FilterInputStream) this).in).unread(13);
                        }
                    }
                }
            } else {
                length--;
                if (length < 0) {
                    int length2 = cArr.length;
                    int i2 = MAX_INCR;
                    if (length2 < i2) {
                        cArr = new char[cArr.length * 2];
                    } else {
                        cArr = new char[cArr.length + i2];
                    }
                    length = (cArr.length - i) - 1;
                    System.arraycopy(this.lineBuffer, 0, cArr, 0, i);
                    this.lineBuffer = cArr;
                }
                cArr[i] = (char) read;
                i++;
            }
        }
        if (read == -1 && i == 0) {
            return null;
        }
        return String.copyValueOf(cArr, 0, i);
    }
}
