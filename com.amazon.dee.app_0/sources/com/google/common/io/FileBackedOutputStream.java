package com.google.common.io;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.errorprone.annotations.concurrent.GuardedBy;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
@Beta
@GwtIncompatible
/* loaded from: classes3.dex */
public final class FileBackedOutputStream extends OutputStream {
    @NullableDecl
    @GuardedBy("this")
    private File file;
    private final int fileThreshold;
    @GuardedBy("this")
    private MemoryOutput memory;
    @GuardedBy("this")
    private OutputStream out;
    @NullableDecl
    private final File parentDirectory;
    private final boolean resetOnFinalize;
    private final ByteSource source;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public static class MemoryOutput extends ByteArrayOutputStream {
        private MemoryOutput() {
        }

        byte[] getBuffer() {
            return ((ByteArrayOutputStream) this).buf;
        }

        int getCount() {
            return ((ByteArrayOutputStream) this).count;
        }
    }

    public FileBackedOutputStream(int i) {
        this(i, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized InputStream openInputStream() throws IOException {
        if (this.file != null) {
            return new FileInputStream(this.file);
        }
        return new ByteArrayInputStream(this.memory.getBuffer(), 0, this.memory.getCount());
    }

    @GuardedBy("this")
    private void update(int i) throws IOException {
        if (this.file != null || this.memory.getCount() + i <= this.fileThreshold) {
            return;
        }
        File createTempFile = File.createTempFile("FileBackedOutputStream", null, this.parentDirectory);
        if (this.resetOnFinalize) {
            createTempFile.deleteOnExit();
        }
        FileOutputStream fileOutputStream = new FileOutputStream(createTempFile);
        fileOutputStream.write(this.memory.getBuffer(), 0, this.memory.getCount());
        fileOutputStream.flush();
        this.out = fileOutputStream;
        this.file = createTempFile;
        this.memory = null;
    }

    public ByteSource asByteSource() {
        return this.source;
    }

    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public synchronized void close() throws IOException {
        this.out.close();
    }

    @Override // java.io.OutputStream, java.io.Flushable
    public synchronized void flush() throws IOException {
        this.out.flush();
    }

    @VisibleForTesting
    synchronized File getFile() {
        return this.file;
    }

    public synchronized void reset() throws IOException {
        close();
        if (this.memory == null) {
            this.memory = new MemoryOutput();
        } else {
            this.memory.reset();
        }
        this.out = this.memory;
        if (this.file != null) {
            File file = this.file;
            this.file = null;
            if (!file.delete()) {
                String valueOf = String.valueOf(file);
                StringBuilder sb = new StringBuilder(valueOf.length() + 18);
                sb.append("Could not delete: ");
                sb.append(valueOf);
                throw new IOException(sb.toString());
            }
        }
    }

    @Override // java.io.OutputStream
    public synchronized void write(int i) throws IOException {
        update(1);
        this.out.write(i);
    }

    public FileBackedOutputStream(int i, boolean z) {
        this(i, z, null);
    }

    private FileBackedOutputStream(int i, boolean z, @NullableDecl File file) {
        this.fileThreshold = i;
        this.resetOnFinalize = z;
        this.parentDirectory = file;
        this.memory = new MemoryOutput();
        this.out = this.memory;
        if (z) {
            this.source = new ByteSource() { // from class: com.google.common.io.FileBackedOutputStream.1
                protected void finalize() {
                    try {
                        FileBackedOutputStream.this.reset();
                    } catch (Throwable th) {
                        th.printStackTrace(System.err);
                    }
                }

                @Override // com.google.common.io.ByteSource
                /* renamed from: openStream */
                public InputStream mo8235openStream() throws IOException {
                    return FileBackedOutputStream.this.openInputStream();
                }
            };
        } else {
            this.source = new ByteSource() { // from class: com.google.common.io.FileBackedOutputStream.2
                @Override // com.google.common.io.ByteSource
                /* renamed from: openStream */
                public InputStream mo8235openStream() throws IOException {
                    return FileBackedOutputStream.this.openInputStream();
                }
            };
        }
    }

    @Override // java.io.OutputStream
    public synchronized void write(byte[] bArr) throws IOException {
        write(bArr, 0, bArr.length);
    }

    @Override // java.io.OutputStream
    public synchronized void write(byte[] bArr, int i, int i2) throws IOException {
        update(i2);
        this.out.write(bArr, i, i2);
    }
}
