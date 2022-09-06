package com.amazonaws.internal;

import com.amazonaws.AmazonClientException;
import com.amazonaws.logging.Log;
import com.amazonaws.logging.LogFactory;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
/* loaded from: classes13.dex */
public class ResettableInputStream extends ReleasableInputStream {
    private static final Log log = LogFactory.getLog(ResettableInputStream.class);
    private final File file;
    private final FileChannel fileChannel;
    private final FileInputStream fis;
    private long markPos;

    public ResettableInputStream(File file) throws IOException {
        this(new FileInputStream(file), file);
    }

    public static ResettableInputStream newResettableInputStream(File file) {
        return newResettableInputStream(file, (String) null);
    }

    @Override // com.amazonaws.internal.SdkFilterInputStream, java.io.FilterInputStream, java.io.InputStream
    public int available() throws IOException {
        abortIfNeeded();
        return this.fis.available();
    }

    public File getFile() {
        return this.file;
    }

    @Override // com.amazonaws.internal.SdkFilterInputStream, java.io.FilterInputStream, java.io.InputStream
    public void mark(int i) {
        abortIfNeeded();
        try {
            this.markPos = this.fileChannel.position();
            if (!log.isTraceEnabled()) {
                return;
            }
            Log log2 = log;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("File input stream marked at position ");
            outline107.append(this.markPos);
            log2.trace(outline107.toString());
        } catch (IOException e) {
            throw new AmazonClientException("Failed to mark the file position", e);
        }
    }

    @Override // com.amazonaws.internal.SdkFilterInputStream, java.io.FilterInputStream, java.io.InputStream
    public final boolean markSupported() {
        return true;
    }

    @Override // com.amazonaws.internal.SdkFilterInputStream, java.io.FilterInputStream, java.io.InputStream
    public int read() throws IOException {
        abortIfNeeded();
        return this.fis.read();
    }

    @Override // com.amazonaws.internal.SdkFilterInputStream, java.io.FilterInputStream, java.io.InputStream
    public void reset() throws IOException {
        abortIfNeeded();
        this.fileChannel.position(this.markPos);
        if (log.isTraceEnabled()) {
            Log log2 = log;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Reset to position ");
            outline107.append(this.markPos);
            log2.trace(outline107.toString());
        }
    }

    @Override // com.amazonaws.internal.SdkFilterInputStream, java.io.FilterInputStream, java.io.InputStream
    public long skip(long j) throws IOException {
        abortIfNeeded();
        return this.fis.skip(j);
    }

    public ResettableInputStream(FileInputStream fileInputStream) throws IOException {
        this(fileInputStream, null);
    }

    public static ResettableInputStream newResettableInputStream(File file, String str) {
        try {
            return new ResettableInputStream(file);
        } catch (IOException e) {
            if (str != null) {
                throw new AmazonClientException(str, e);
            }
            throw new AmazonClientException(e);
        }
    }

    private ResettableInputStream(FileInputStream fileInputStream, File file) throws IOException {
        super(fileInputStream);
        this.file = file;
        this.fis = fileInputStream;
        this.fileChannel = fileInputStream.getChannel();
        this.markPos = this.fileChannel.position();
    }

    @Override // com.amazonaws.internal.SdkFilterInputStream, java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        abortIfNeeded();
        return this.fis.read(bArr, i, i2);
    }

    public static ResettableInputStream newResettableInputStream(FileInputStream fileInputStream) {
        return newResettableInputStream(fileInputStream, (String) null);
    }

    public static ResettableInputStream newResettableInputStream(FileInputStream fileInputStream, String str) {
        try {
            return new ResettableInputStream(fileInputStream);
        } catch (IOException e) {
            throw new AmazonClientException(str, e);
        }
    }
}
