package com.google.common.io;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
@GwtIncompatible
/* loaded from: classes3.dex */
final class MultiInputStream extends InputStream {
    @NullableDecl
    private InputStream in;

    /* renamed from: it  reason: collision with root package name */
    private Iterator<? extends ByteSource> f12it;

    public MultiInputStream(Iterator<? extends ByteSource> it2) throws IOException {
        this.f12it = (Iterator) Preconditions.checkNotNull(it2);
        advance();
    }

    private void advance() throws IOException {
        close();
        if (this.f12it.hasNext()) {
            this.in = this.f12it.next().mo8235openStream();
        }
    }

    @Override // java.io.InputStream
    public int available() throws IOException {
        InputStream inputStream = this.in;
        if (inputStream == null) {
            return 0;
        }
        return inputStream.available();
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        InputStream inputStream = this.in;
        if (inputStream != null) {
            try {
                inputStream.close();
            } finally {
                this.in = null;
            }
        }
    }

    @Override // java.io.InputStream
    public boolean markSupported() {
        return false;
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        while (true) {
            InputStream inputStream = this.in;
            if (inputStream != null) {
                int read = inputStream.read();
                if (read != -1) {
                    return read;
                }
                advance();
            } else {
                return -1;
            }
        }
    }

    @Override // java.io.InputStream
    public long skip(long j) throws IOException {
        InputStream inputStream = this.in;
        if (inputStream == null || j <= 0) {
            return 0L;
        }
        long skip = inputStream.skip(j);
        if (skip != 0) {
            return skip;
        }
        if (read() != -1) {
            return this.in.skip(j - 1) + 1;
        }
        return 0L;
    }

    @Override // java.io.InputStream
    public int read(@NullableDecl byte[] bArr, int i, int i2) throws IOException {
        while (true) {
            InputStream inputStream = this.in;
            if (inputStream != null) {
                int read = inputStream.read(bArr, i, i2);
                if (read != -1) {
                    return read;
                }
                advance();
            } else {
                return -1;
            }
        }
    }
}
