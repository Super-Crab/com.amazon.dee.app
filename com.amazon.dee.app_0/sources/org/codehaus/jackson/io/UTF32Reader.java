package org.codehaus.jackson.io;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.core.base.GeneratorBase;
import java.io.CharConversionException;
import java.io.IOException;
import java.io.InputStream;
/* loaded from: classes5.dex */
public class UTF32Reader extends BaseReader {
    protected final boolean _bigEndian;
    protected int _byteCount;
    protected int _charCount;
    protected final boolean _managedBuffers;
    protected char _surrogate;

    public UTF32Reader(IOContext iOContext, InputStream inputStream, byte[] bArr, int i, int i2, boolean z) {
        super(iOContext, inputStream, bArr, i, i2);
        boolean z2 = false;
        this._surrogate = (char) 0;
        this._charCount = 0;
        this._byteCount = 0;
        this._bigEndian = z;
        this._managedBuffers = inputStream != null ? true : z2;
    }

    private boolean loadMore(int i) throws IOException {
        int read;
        this._byteCount = (this._length - i) + this._byteCount;
        if (i > 0) {
            if (this._ptr > 0) {
                for (int i2 = 0; i2 < i; i2++) {
                    byte[] bArr = this._buffer;
                    bArr[i2] = bArr[this._ptr + i2];
                }
                this._ptr = 0;
            }
            this._length = i;
        } else {
            this._ptr = 0;
            InputStream inputStream = this._in;
            int read2 = inputStream == null ? -1 : inputStream.read(this._buffer);
            if (read2 < 1) {
                this._length = 0;
                if (read2 < 0) {
                    if (this._managedBuffers) {
                        freeBuffers();
                    }
                    return false;
                }
                reportStrangeStream();
            }
            this._length = read2;
        }
        while (true) {
            int i3 = this._length;
            if (i3 < 4) {
                InputStream inputStream2 = this._in;
                if (inputStream2 == null) {
                    read = -1;
                } else {
                    byte[] bArr2 = this._buffer;
                    read = inputStream2.read(bArr2, i3, bArr2.length - i3);
                }
                if (read < 1) {
                    if (read < 0) {
                        if (this._managedBuffers) {
                            freeBuffers();
                        }
                        reportUnexpectedEOF(this._length, 4);
                    }
                    reportStrangeStream();
                }
                this._length += read;
            } else {
                return true;
            }
        }
    }

    private void reportInvalid(int i, int i2, String str) throws IOException {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Invalid UTF-32 character 0x");
        outline107.append(Integer.toHexString(i));
        outline107.append(str);
        outline107.append(" at char #");
        outline107.append(this._charCount + i2);
        outline107.append(", byte #");
        throw new CharConversionException(GeneratedOutlineSupport1.outline86(outline107, (this._byteCount + this._ptr) - 1, ")"));
    }

    private void reportUnexpectedEOF(int i, int i2) throws IOException {
        int i3 = this._byteCount + i;
        int i4 = this._charCount;
        StringBuilder outline110 = GeneratedOutlineSupport1.outline110("Unexpected EOF in the middle of a 4-byte UTF-32 char: got ", i, ", needed ", i2, ", at char #");
        outline110.append(i4);
        outline110.append(", byte #");
        outline110.append(i3);
        outline110.append(")");
        throw new CharConversionException(outline110.toString());
    }

    @Override // org.codehaus.jackson.io.BaseReader, java.io.Reader, java.io.Closeable, java.lang.AutoCloseable
    public /* bridge */ /* synthetic */ void close() throws IOException {
        super.close();
    }

    @Override // org.codehaus.jackson.io.BaseReader, java.io.Reader
    public /* bridge */ /* synthetic */ int read() throws IOException {
        return super.read();
    }

    @Override // java.io.Reader
    public int read(char[] cArr, int i, int i2) throws IOException {
        int i3;
        int i4;
        int i5;
        int i6;
        if (this._buffer == null) {
            return -1;
        }
        if (i2 < 1) {
            return i2;
        }
        if (i < 0 || i + i2 > cArr.length) {
            reportBounds(cArr, i, i2);
        }
        int i7 = i2 + i;
        char c = this._surrogate;
        if (c != 0) {
            i3 = i + 1;
            cArr[i] = c;
            this._surrogate = (char) 0;
        } else {
            int i8 = this._length - this._ptr;
            if (i8 < 4 && !loadMore(i8)) {
                return -1;
            }
            i3 = i;
        }
        while (true) {
            if (i3 >= i7) {
                i4 = i3;
                break;
            }
            int i9 = this._ptr;
            if (this._bigEndian) {
                byte[] bArr = this._buffer;
                i5 = (bArr[i9] << 24) | ((bArr[i9 + 1] & 255) << 16) | ((bArr[i9 + 2] & 255) << 8);
                i6 = bArr[i9 + 3] & 255;
            } else {
                byte[] bArr2 = this._buffer;
                i5 = (bArr2[i9] & 255) | ((bArr2[i9 + 1] & 255) << 8) | ((bArr2[i9 + 2] & 255) << 16);
                i6 = bArr2[i9 + 3] << 24;
            }
            int i10 = i6 | i5;
            this._ptr += 4;
            if (i10 > 65535) {
                if (i10 > 1114111) {
                    reportInvalid(i10, i3 - i, GeneratedOutlineSupport1.outline33(1114111, GeneratedOutlineSupport1.outline107("(above "), ") "));
                }
                int i11 = i10 - 65536;
                i4 = i3 + 1;
                cArr[i3] = (char) ((i11 >> 10) + GeneratorBase.SURR1_FIRST);
                i10 = (i11 & 1023) | 56320;
                if (i4 >= i7) {
                    this._surrogate = (char) i10;
                    break;
                }
                i3 = i4;
            }
            i4 = i3 + 1;
            cArr[i3] = (char) i10;
            if (this._ptr >= this._length) {
                break;
            }
            i3 = i4;
        }
        int i12 = i4 - i;
        this._charCount += i12;
        return i12;
    }
}
