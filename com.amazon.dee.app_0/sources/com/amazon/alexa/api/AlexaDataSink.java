package com.amazon.alexa.api;

import android.os.ParcelFileDescriptor;
import android.util.Log;
import java.io.IOException;
import java.io.OutputStream;
/* loaded from: classes6.dex */
public class AlexaDataSink extends OutputStream {
    private boolean isWriteable;
    private final OutputStream outputStream;
    protected final ParcelFileDescriptor readDescriptor;
    private String tag;
    protected final ParcelFileDescriptor writeDescriptor;

    public AlexaDataSink() throws IOException {
        this.tag = AlexaDataSink.class.getSimpleName();
        ParcelFileDescriptor[] createReliableSocketPair = ParcelFileDescriptor.createReliableSocketPair();
        this.readDescriptor = createReliableSocketPair[0];
        this.writeDescriptor = createReliableSocketPair[1];
        this.outputStream = new ParcelFileDescriptor.AutoCloseOutputStream(this.writeDescriptor);
        this.isWriteable = true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public AlexaDataSink(ParcelFileDescriptor parcelFileDescriptor, ParcelFileDescriptor parcelFileDescriptor2) {
        this.tag = AlexaDataSink.class.getSimpleName();
        this.readDescriptor = parcelFileDescriptor;
        this.writeDescriptor = parcelFileDescriptor2;
        this.outputStream = null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public AlexaDataSink(AlexaDataSink alexaDataSink) {
        this(alexaDataSink.readDescriptor, alexaDataSink.writeDescriptor);
    }

    public synchronized void abandon() {
        try {
            if (this.outputStream != null) {
                this.outputStream.close();
            }
            if (this.readDescriptor != null) {
                this.readDescriptor.close();
            }
            if (this.writeDescriptor != null) {
                this.writeDescriptor.close();
            }
        } catch (IOException e) {
            String str = this.tag;
            Log.e(str, "Could not abandon " + this.tag, e);
        }
        this.isWriteable = false;
    }

    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public synchronized void close() {
        try {
            if (this.outputStream != null) {
                this.outputStream.close();
            }
        } catch (IOException e) {
            Log.e(this.tag, "Could not close output stream.", e);
        }
        this.isWriteable = false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized ParcelFileDescriptor getReadDescriptor() {
        return this.readDescriptor;
    }

    public synchronized boolean isWriteable() {
        return this.isWriteable;
    }

    public synchronized OutputStream openForWriting() {
        return this;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setTag(String str) {
        this.tag = str;
    }

    @Override // java.io.OutputStream
    public void write(int i) throws IOException {
        this.outputStream.write(i);
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr) throws IOException {
        this.outputStream.write(bArr);
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr, int i, int i2) throws IOException {
        this.outputStream.write(bArr, i, i2);
    }
}
