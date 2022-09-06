package com.sun.mail.pop3;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import javax.mail.util.SharedFileInputStream;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: TempFile.java */
/* loaded from: classes3.dex */
public class WritableSharedFile extends SharedFileInputStream {
    private AppendStream af;
    private RandomAccessFile raf;

    public WritableSharedFile(File file) throws IOException {
        super(file);
        try {
            this.raf = new RandomAccessFile(file, "rw");
        } catch (IOException unused) {
            super.close();
        }
    }

    @Override // javax.mail.util.SharedFileInputStream, java.io.BufferedInputStream, java.io.FilterInputStream, java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        try {
            super.close();
        } finally {
            this.raf.close();
        }
    }

    public synchronized AppendStream getAppendStream() throws IOException {
        if (this.af == null) {
            this.af = new AppendStream(this);
        } else {
            throw new IOException("POP3 file cache only supports single threaded access");
        }
        return this.af;
    }

    public RandomAccessFile getWritableFile() {
        return this.raf;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized long updateLength() throws IOException {
        this.datalen = this.in.length();
        this.af = null;
        return this.datalen;
    }
}
