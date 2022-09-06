package com.amazon.alexa.sharing.media;

import com.amazon.alexa.sharing.media.model.MediaContentBase;
import java.io.InputStream;
/* loaded from: classes10.dex */
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
