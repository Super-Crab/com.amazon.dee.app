package org.apache.thrift.orig.transport;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
/* loaded from: classes4.dex */
public class TStandardFile implements TSeekableFile {
    protected RandomAccessFile inputFile_;
    protected String path_;

    public TStandardFile(String str) throws IOException {
        this.path_ = null;
        this.inputFile_ = null;
        this.path_ = str;
        this.inputFile_ = new RandomAccessFile(this.path_, "r");
    }

    @Override // org.apache.thrift.orig.transport.TSeekableFile
    public void close() throws IOException {
        RandomAccessFile randomAccessFile = this.inputFile_;
        if (randomAccessFile != null) {
            randomAccessFile.close();
        }
    }

    @Override // org.apache.thrift.orig.transport.TSeekableFile
    public InputStream getInputStream() throws IOException {
        return new FileInputStream(this.inputFile_.getFD());
    }

    @Override // org.apache.thrift.orig.transport.TSeekableFile
    public OutputStream getOutputStream() throws IOException {
        return new FileOutputStream(this.path_);
    }

    @Override // org.apache.thrift.orig.transport.TSeekableFile
    public long length() throws IOException {
        return this.inputFile_.length();
    }

    @Override // org.apache.thrift.orig.transport.TSeekableFile
    public void seek(long j) throws IOException {
        this.inputFile_.seek(j);
    }
}
