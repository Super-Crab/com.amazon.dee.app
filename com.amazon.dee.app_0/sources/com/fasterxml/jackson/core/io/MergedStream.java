package com.fasterxml.jackson.core.io;

import java.io.IOException;
import java.io.InputStream;
/* loaded from: classes2.dex */
public final class MergedStream extends InputStream {
    private byte[] _b;
    private final IOContext _ctxt;
    private final int _end;
    private final InputStream _in;
    private int _ptr;

    public MergedStream(IOContext iOContext, InputStream inputStream, byte[] bArr, int i, int i2) {
        this._ctxt = iOContext;
        this._in = inputStream;
        this._b = bArr;
        this._ptr = i;
        this._end = i2;
    }

    private void _free() {
        byte[] bArr = this._b;
        if (bArr != null) {
            this._b = null;
            IOContext iOContext = this._ctxt;
            if (iOContext == null) {
                return;
            }
            iOContext.releaseReadIOBuffer(bArr);
        }
    }

    @Override // java.io.InputStream
    public int available() throws IOException {
        if (this._b != null) {
            return this._end - this._ptr;
        }
        return this._in.available();
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        _free();
        this._in.close();
    }

    @Override // java.io.InputStream
    public synchronized void mark(int i) {
        if (this._b == null) {
            this._in.mark(i);
        }
    }

    @Override // java.io.InputStream
    public boolean markSupported() {
        return this._b == null && this._in.markSupported();
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        byte[] bArr = this._b;
        if (bArr != null) {
            int i = this._ptr;
            this._ptr = i + 1;
            int i2 = bArr[i] & 255;
            if (this._ptr >= this._end) {
                _free();
            }
            return i2;
        }
        return this._in.read();
    }

    @Override // java.io.InputStream
    public synchronized void reset() throws IOException {
        if (this._b == null) {
            this._in.reset();
        }
    }

    @Override // java.io.InputStream
    public long skip(long j) throws IOException {
        long j2;
        if (this._b != null) {
            int i = this._end;
            int i2 = this._ptr;
            long j3 = i - i2;
            if (j3 > j) {
                this._ptr = i2 + ((int) j);
                return j;
            }
            _free();
            j2 = j3 + 0;
            j -= j3;
        } else {
            j2 = 0;
        }
        return j > 0 ? j2 + this._in.skip(j) : j2;
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr) throws IOException {
        return read(bArr, 0, bArr.length);
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        if (this._b != null) {
            int i3 = this._end - this._ptr;
            if (i2 > i3) {
                i2 = i3;
            }
            System.arraycopy(this._b, this._ptr, bArr, i, i2);
            this._ptr += i2;
            if (this._ptr >= this._end) {
                _free();
            }
            return i2;
        }
        return this._in.read(bArr, i, i2);
    }
}
