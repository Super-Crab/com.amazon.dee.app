package com.amazon.device.utils.det;

import android.util.Log;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.ByteBuffer;
import java.util.zip.GZIPOutputStream;
/* loaded from: classes12.dex */
public class MfbsInputStream extends InputStream {
    private static final String NEW_LINE = "\n";
    private ByteBuffer body;
    private UnsafeByteArrayOutputStream byteStream;
    private OutputStream compressedByteStream;
    private ByteBuffer header;
    private Writer logFileWriter;
    private BufferedReader mBufferedReader;

    /* loaded from: classes12.dex */
    private static class UnsafeByteArrayOutputStream extends ByteArrayOutputStream {
        private UnsafeByteArrayOutputStream() {
        }

        byte[] getBuf() {
            return ((ByteArrayOutputStream) this).buf;
        }

        int getCount() {
            return ((ByteArrayOutputStream) this).count;
        }
    }

    private MfbsInputStream() throws IOException {
        this.byteStream = new UnsafeByteArrayOutputStream();
        this.compressedByteStream = new GZIPOutputStream(this.byteStream);
        this.logFileWriter = new BufferedWriter(new OutputStreamWriter(this.compressedByteStream));
    }

    private static ByteBuffer headerBuffer(int i, String str) {
        return ByteBuffer.wrap(("\nFiles: messages.0\n------------------\nMFBS/1.0 1\n\nContent-Type: text/plain\nContent-Encoding: " + str + "\nContent-Length: " + i + "\nContent-Name: Content\n\n").getBytes());
    }

    @Override // java.io.InputStream
    public int available() {
        return this.body.remaining() + this.header.remaining();
    }

    public Writer getLogFileWriter() {
        return this.logFileWriter;
    }

    public void pump() throws IOException {
        ByteBuffer wrap;
        while (true) {
            try {
                try {
                    String readLine = this.mBufferedReader.readLine();
                    if (readLine == null) {
                        break;
                    }
                    Writer writer = this.logFileWriter;
                    writer.write(readLine + "\n");
                } catch (IOException e) {
                    Log.e("pump", "Error while writing to log/crash file. File with partial information would be uploaded", e);
                    this.logFileWriter.flush();
                    this.compressedByteStream.close();
                    this.header = headerBuffer(this.byteStream.getCount(), "GZIP");
                    wrap = ByteBuffer.wrap(this.byteStream.getBuf(), 0, this.byteStream.getCount());
                }
            } catch (Throwable th) {
                this.logFileWriter.flush();
                this.compressedByteStream.close();
                this.header = headerBuffer(this.byteStream.getCount(), "GZIP");
                this.body = ByteBuffer.wrap(this.byteStream.getBuf(), 0, this.byteStream.getCount());
                this.header.mark();
                this.body.mark();
                throw th;
            }
        }
        this.logFileWriter.flush();
        this.compressedByteStream.close();
        this.header = headerBuffer(this.byteStream.getCount(), "GZIP");
        wrap = ByteBuffer.wrap(this.byteStream.getBuf(), 0, this.byteStream.getCount());
        this.body = wrap;
        this.header.mark();
        this.body.mark();
    }

    @Override // java.io.InputStream
    public int read() {
        byte b;
        if (this.header.hasRemaining()) {
            b = this.header.get();
        } else if (!this.body.hasRemaining()) {
            return -1;
        } else {
            b = this.body.get();
        }
        return b & 255;
    }

    @Override // java.io.InputStream
    public synchronized void reset() throws IOException {
        this.header.reset();
        this.body.reset();
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i, int i2) {
        int min = Math.min(i2, this.header.remaining());
        if (min > 0) {
            this.header.get(bArr, i, min);
        }
        int min2 = Math.min(i2 - min, this.body.remaining());
        if (min2 > 0) {
            this.body.get(bArr, i + min, min2);
        }
        int i3 = min + min2;
        if (i3 > 0) {
            return i3;
        }
        return -1;
    }

    public MfbsInputStream(BufferedReader bufferedReader) throws IOException {
        this();
        this.mBufferedReader = bufferedReader;
    }
}
