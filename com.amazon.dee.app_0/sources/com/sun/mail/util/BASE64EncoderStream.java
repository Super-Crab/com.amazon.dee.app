package com.sun.mail.util;

import com.amazon.alexa.accessory.internal.bluetooth.GenericAccessProfile;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import org.bouncycastle.pqc.math.linearalgebra.Matrix;
/* loaded from: classes3.dex */
public class BASE64EncoderStream extends FilterOutputStream {
    private static byte[] newline = {13, 10};
    private static final char[] pem_array = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', Matrix.MATRIX_TYPE_RANDOM_LT, 'M', 'N', 'O', 'P', 'Q', Matrix.MATRIX_TYPE_RANDOM_REGULAR, 'S', 'T', Matrix.MATRIX_TYPE_RANDOM_UT, 'V', 'W', 'X', 'Y', Matrix.MATRIX_TYPE_ZERO, 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/'};
    private byte[] buffer;
    private int bufsize;
    private int bytesPerLine;
    private int count;
    private int lineLimit;
    private boolean noCRLF;
    private byte[] outbuf;

    public BASE64EncoderStream(OutputStream outputStream, int i) {
        super(outputStream);
        this.bufsize = 0;
        this.count = 0;
        this.noCRLF = false;
        this.buffer = new byte[3];
        if (i == Integer.MAX_VALUE || i < 4) {
            this.noCRLF = true;
            i = 76;
        }
        int i2 = (i / 4) * 4;
        this.bytesPerLine = i2;
        this.lineLimit = (i2 / 4) * 3;
        if (this.noCRLF) {
            this.outbuf = new byte[i2];
            return;
        }
        this.outbuf = new byte[i2 + 2];
        byte[] bArr = this.outbuf;
        bArr[i2] = 13;
        bArr[i2 + 1] = 10;
    }

    private void encode() throws IOException {
        int encodedSize = encodedSize(this.bufsize);
        ((FilterOutputStream) this).out.write(encode(this.buffer, 0, this.bufsize, this.outbuf), 0, encodedSize);
        this.count += encodedSize;
        if (this.count >= this.bytesPerLine) {
            if (!this.noCRLF) {
                ((FilterOutputStream) this).out.write(newline);
            }
            this.count = 0;
        }
    }

    private static int encodedSize(int i) {
        return ((i + 2) / 3) * 4;
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public synchronized void close() throws IOException {
        flush();
        if (this.count > 0 && !this.noCRLF) {
            ((FilterOutputStream) this).out.write(newline);
            ((FilterOutputStream) this).out.flush();
        }
        ((FilterOutputStream) this).out.close();
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream, java.io.Flushable
    public synchronized void flush() throws IOException {
        if (this.bufsize > 0) {
            encode();
            this.bufsize = 0;
        }
        ((FilterOutputStream) this).out.flush();
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream
    public synchronized void write(byte[] bArr, int i, int i2) throws IOException {
        int i3 = i2 + i;
        while (this.bufsize != 0 && i < i3) {
            write(bArr[i]);
            i++;
        }
        int i4 = ((this.bytesPerLine - this.count) / 4) * 3;
        int i5 = i + i4;
        if (i5 <= i3) {
            int encodedSize = encodedSize(i4);
            if (!this.noCRLF) {
                int i6 = encodedSize + 1;
                this.outbuf[encodedSize] = 13;
                this.outbuf[i6] = 10;
                encodedSize = i6 + 1;
            }
            ((FilterOutputStream) this).out.write(encode(bArr, i, i4, this.outbuf), 0, encodedSize);
            this.count = 0;
            i = i5;
        }
        while (this.lineLimit + i <= i3) {
            ((FilterOutputStream) this).out.write(encode(bArr, i, this.lineLimit, this.outbuf));
            i += this.lineLimit;
        }
        if (i + 3 <= i3) {
            int i7 = ((i3 - i) / 3) * 3;
            int encodedSize2 = encodedSize(i7);
            ((FilterOutputStream) this).out.write(encode(bArr, i, i7, this.outbuf), 0, encodedSize2);
            i += i7;
            this.count += encodedSize2;
        }
        while (i < i3) {
            write(bArr[i]);
            i++;
        }
    }

    public static byte[] encode(byte[] bArr) {
        return bArr.length == 0 ? bArr : encode(bArr, 0, bArr.length, null);
    }

    private static byte[] encode(byte[] bArr, int i, int i2, byte[] bArr2) {
        if (bArr2 == null) {
            bArr2 = new byte[encodedSize(i2)];
        }
        int i3 = 0;
        while (i2 >= 3) {
            int i4 = i + 1;
            int i5 = i4 + 1;
            int i6 = ((((bArr[i] & 255) << 8) | (bArr[i4] & 255)) << 8) | (bArr[i5] & 255);
            char[] cArr = pem_array;
            bArr2[i3 + 3] = (byte) cArr[i6 & 63];
            int i7 = i6 >> 6;
            bArr2[i3 + 2] = (byte) cArr[i7 & 63];
            int i8 = i7 >> 6;
            bArr2[i3 + 1] = (byte) cArr[i8 & 63];
            bArr2[i3 + 0] = (byte) cArr[(i8 >> 6) & 63];
            i2 -= 3;
            i3 += 4;
            i = i5 + 1;
        }
        if (i2 == 1) {
            int i9 = (bArr[i] & 255) << 4;
            bArr2[i3 + 3] = GenericAccessProfile.INFORMATION_DATA_3D;
            bArr2[i3 + 2] = GenericAccessProfile.INFORMATION_DATA_3D;
            char[] cArr2 = pem_array;
            bArr2[i3 + 1] = (byte) cArr2[i9 & 63];
            bArr2[i3 + 0] = (byte) cArr2[(i9 >> 6) & 63];
        } else if (i2 == 2) {
            int i10 = ((bArr[i + 1] & 255) | ((bArr[i] & 255) << 8)) << 2;
            bArr2[i3 + 3] = GenericAccessProfile.INFORMATION_DATA_3D;
            char[] cArr3 = pem_array;
            bArr2[i3 + 2] = (byte) cArr3[i10 & 63];
            int i11 = i10 >> 6;
            bArr2[i3 + 1] = (byte) cArr3[i11 & 63];
            bArr2[i3 + 0] = (byte) cArr3[(i11 >> 6) & 63];
        }
        return bArr2;
    }

    public BASE64EncoderStream(OutputStream outputStream) {
        this(outputStream, 76);
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream
    public void write(byte[] bArr) throws IOException {
        write(bArr, 0, bArr.length);
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream
    public synchronized void write(int i) throws IOException {
        byte[] bArr = this.buffer;
        int i2 = this.bufsize;
        this.bufsize = i2 + 1;
        bArr[i2] = (byte) i;
        if (this.bufsize == 3) {
            encode();
            this.bufsize = 0;
        }
    }
}
