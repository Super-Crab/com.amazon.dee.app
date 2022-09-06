package com.amazon.blueshift.bluefront.android.audio;

import com.google.common.base.Preconditions;
import com.google.common.net.MediaType;
import java.io.InputStream;
/* loaded from: classes11.dex */
public class AudioStream extends AudioSource {
    private static final int BUF_SIZ = 320;
    private final InputStream mStream;

    public AudioStream(MediaType mediaType, InputStream inputStream) {
        super(mediaType);
        this.mStream = (InputStream) Preconditions.checkNotNull(inputStream, "Stream cannot be null");
    }

    @Override // com.amazon.blueshift.bluefront.android.audio.AudioSource
    public int getChunkSize() {
        return 320;
    }

    @Override // com.amazon.blueshift.bluefront.android.audio.AudioSource
    public InputStream getConsumerStream() {
        return this.mStream;
    }
}
