package com.google.common.io;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;
import java.io.Writer;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
@GwtIncompatible
/* loaded from: classes3.dex */
class AppendableWriter extends Writer {
    private boolean closed;
    private final Appendable target;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AppendableWriter(Appendable appendable) {
        this.target = (Appendable) Preconditions.checkNotNull(appendable);
    }

    private void checkNotClosed() throws IOException {
        if (!this.closed) {
            return;
        }
        throw new IOException("Cannot write to a closed writer.");
    }

    @Override // java.io.Writer, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.closed = true;
        Appendable appendable = this.target;
        if (appendable instanceof Closeable) {
            ((Closeable) appendable).close();
        }
    }

    @Override // java.io.Writer, java.io.Flushable
    public void flush() throws IOException {
        checkNotClosed();
        Appendable appendable = this.target;
        if (appendable instanceof Flushable) {
            ((Flushable) appendable).flush();
        }
    }

    @Override // java.io.Writer
    public void write(char[] cArr, int i, int i2) throws IOException {
        checkNotClosed();
        this.target.append(new String(cArr, i, i2));
    }

    @Override // java.io.Writer
    public void write(int i) throws IOException {
        checkNotClosed();
        this.target.append((char) i);
    }

    @Override // java.io.Writer, java.lang.Appendable
    /* renamed from: append */
    public Writer mo8227append(char c) throws IOException {
        checkNotClosed();
        this.target.append(c);
        return this;
    }

    @Override // java.io.Writer
    public void write(@NullableDecl String str) throws IOException {
        checkNotClosed();
        this.target.append(str);
    }

    @Override // java.io.Writer, java.lang.Appendable
    /* renamed from: append */
    public Writer mo8228append(@NullableDecl CharSequence charSequence) throws IOException {
        checkNotClosed();
        this.target.append(charSequence);
        return this;
    }

    @Override // java.io.Writer
    public void write(@NullableDecl String str, int i, int i2) throws IOException {
        checkNotClosed();
        this.target.append(str, i, i2 + i);
    }

    @Override // java.io.Writer, java.lang.Appendable
    /* renamed from: append */
    public Writer mo8229append(@NullableDecl CharSequence charSequence, int i, int i2) throws IOException {
        checkNotClosed();
        this.target.append(charSequence, i, i2);
        return this;
    }
}