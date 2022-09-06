package com.amazon.deecomms.media.model;

import java.io.InputStream;
/* loaded from: classes12.dex */
public class MediaStreamContent extends MediaContentBase implements AutoCloseable {
    private InputStream inputStream;

    @Override // java.lang.AutoCloseable
    public void close() throws Exception {
        InputStream inputStream = this.inputStream;
        if (inputStream != null) {
            inputStream.close();
        }
    }

    public InputStream getInputStream() {
        return this.inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }
}
