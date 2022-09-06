package com.amazon.identity.auth.device;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLConnection;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class jr extends OutputStream {
    private final Object[] fD = new Object[0];
    private final ByteArrayOutputStream rO = new ByteArrayOutputStream();
    private final URLConnection rP;
    private OutputStream rQ;

    public jr(URLConnection uRLConnection) {
        this.rP = uRLConnection;
    }

    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        OutputStream outputStream = this.rQ;
        if (outputStream != null) {
            outputStream.close();
        }
        super.close();
    }

    @Override // java.io.OutputStream, java.io.Flushable
    public void flush() throws IOException {
        OutputStream outputStream = this.rQ;
        if (outputStream != null) {
            outputStream.flush();
        }
        super.flush();
    }

    public byte[] gV() {
        byte[] byteArray;
        synchronized (this.fD) {
            byteArray = this.rO.toByteArray();
        }
        return byteArray;
    }

    public void gW() throws IOException {
        synchronized (this.fD) {
            this.rQ = this.rP.getOutputStream();
            this.rQ.write(this.rO.toByteArray());
            flush();
        }
    }

    @Override // java.io.OutputStream
    public void write(int i) throws IOException {
        synchronized (this.fD) {
            if (this.rQ != null) {
                this.rQ.write(i);
            } else {
                this.rO.write(i);
            }
        }
    }
}
