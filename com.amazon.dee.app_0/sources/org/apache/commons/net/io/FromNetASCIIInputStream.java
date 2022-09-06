package org.apache.commons.net.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;
/* loaded from: classes4.dex */
public final class FromNetASCIIInputStream extends PushbackInputStream {
    private int __length;
    static final String _lineSeparator = System.getProperty("line.separator");
    static final boolean _noConversionRequired = _lineSeparator.equals("\r\n");
    static final byte[] _lineSeparatorBytes = _lineSeparator.getBytes();

    public FromNetASCIIInputStream(InputStream inputStream) {
        super(inputStream, _lineSeparatorBytes.length + 1);
        this.__length = 0;
    }

    private int __read() throws IOException {
        int read = super.read();
        if (read == 13) {
            int read2 = super.read();
            if (read2 != 10) {
                if (read2 != -1) {
                    unread(read2);
                }
                return 13;
            }
            unread(_lineSeparatorBytes);
            this.__length--;
            return super.read();
        }
        return read;
    }

    public static final boolean isConversionRequired() {
        return !_noConversionRequired;
    }

    @Override // java.io.PushbackInputStream, java.io.FilterInputStream, java.io.InputStream
    public int available() throws IOException {
        return ((PushbackInputStream) this).in.available() + (((PushbackInputStream) this).buf.length - ((PushbackInputStream) this).pos);
    }

    @Override // java.io.PushbackInputStream, java.io.FilterInputStream, java.io.InputStream
    public int read() throws IOException {
        if (_noConversionRequired) {
            return super.read();
        }
        return __read();
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] bArr) throws IOException {
        return read(bArr, 0, bArr.length);
    }

    @Override // java.io.PushbackInputStream, java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        int i3;
        if (i2 < 1) {
            return 0;
        }
        int available = available();
        if (i2 > available) {
            i2 = available;
        }
        this.__length = i2;
        if (this.__length < 1) {
            this.__length = 1;
        }
        if (_noConversionRequired) {
            return super.read(bArr, i, this.__length);
        }
        int __read = __read();
        if (__read == -1) {
            return -1;
        }
        int i4 = __read;
        int i5 = i;
        while (true) {
            i3 = i5 + 1;
            bArr[i5] = (byte) i4;
            int i6 = this.__length - 1;
            this.__length = i6;
            if (i6 <= 0 || (i4 = __read()) == -1) {
                break;
            }
            i5 = i3;
        }
        return i3 - i;
    }
}
