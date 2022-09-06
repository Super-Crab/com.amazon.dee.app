package com.facebook.react.modules.network;

import java.io.IOException;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.BufferedSink;
import okio.Okio;
import okio.Sink;
/* loaded from: classes2.dex */
public class ProgressRequestBody extends RequestBody {
    private long mContentLength = 0;
    private final ProgressListener mProgressListener;
    private final RequestBody mRequestBody;

    public ProgressRequestBody(RequestBody requestBody, ProgressListener progressListener) {
        this.mRequestBody = requestBody;
        this.mProgressListener = progressListener;
    }

    private Sink outputStreamSink(BufferedSink bufferedSink) {
        return Okio.sink(new CountingOutputStream(bufferedSink.outputStream()) { // from class: com.facebook.react.modules.network.ProgressRequestBody.1
            private void sendProgressUpdate() throws IOException {
                long count = getCount();
                long contentLength = ProgressRequestBody.this.contentLength();
                ProgressRequestBody.this.mProgressListener.onProgress(count, contentLength, count == contentLength);
            }

            @Override // com.facebook.react.modules.network.CountingOutputStream, java.io.FilterOutputStream, java.io.OutputStream
            public void write(byte[] bArr, int i, int i2) throws IOException {
                super.write(bArr, i, i2);
                sendProgressUpdate();
            }

            @Override // com.facebook.react.modules.network.CountingOutputStream, java.io.FilterOutputStream, java.io.OutputStream
            public void write(int i) throws IOException {
                super.write(i);
                sendProgressUpdate();
            }
        });
    }

    @Override // okhttp3.RequestBody
    public long contentLength() throws IOException {
        if (this.mContentLength == 0) {
            this.mContentLength = this.mRequestBody.contentLength();
        }
        return this.mContentLength;
    }

    @Override // okhttp3.RequestBody
    public MediaType contentType() {
        return this.mRequestBody.contentType();
    }

    @Override // okhttp3.RequestBody
    public void writeTo(BufferedSink bufferedSink) throws IOException {
        BufferedSink buffer = Okio.buffer(outputStreamSink(bufferedSink));
        contentLength();
        this.mRequestBody.writeTo(buffer);
        buffer.flush();
    }
}
