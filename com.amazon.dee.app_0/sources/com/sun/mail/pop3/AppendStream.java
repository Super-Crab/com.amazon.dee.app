package com.sun.mail.pop3;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: TempFile.java */
/* loaded from: classes3.dex */
public class AppendStream extends OutputStream {
    private long end;
    private RandomAccessFile raf;
    private final long start;
    private final WritableSharedFile tf;

    public AppendStream(WritableSharedFile writableSharedFile) throws IOException {
        this.tf = writableSharedFile;
        this.raf = writableSharedFile.getWritableFile();
        this.start = this.raf.length();
        this.raf.seek(this.start);
    }

    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public synchronized void close() throws IOException {
        this.end = this.tf.updateLength();
        this.raf = null;
    }

    public synchronized InputStream getInputStream() throws IOException {
        return this.tf.newStream(this.start, this.end);
    }

    @Override // java.io.OutputStream
    public void write(int i) throws IOException {
        this.raf.write(i);
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr) throws IOException {
        this.raf.write(bArr);
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr, int i, int i2) throws IOException {
        this.raf.write(bArr, i, i2);
    }
}
