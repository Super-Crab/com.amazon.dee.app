package com.amazon.blueshift.bluefront.android.audio;

import com.amazon.blueshift.bluefront.android.audio.AudioSourceListener;
import com.google.common.base.Preconditions;
import com.google.common.net.MediaType;
import java.io.InputStream;
import java.util.concurrent.atomic.AtomicBoolean;
/* loaded from: classes11.dex */
public abstract class AudioSource {
    private final MediaType mContentType;
    private AudioSourceListener mListener = new AudioSourceListener.NullListener();
    private final AtomicBoolean mIsCancelled = new AtomicBoolean(false);

    /* JADX INFO: Access modifiers changed from: package-private */
    public AudioSource(MediaType mediaType) {
        this.mContentType = (MediaType) Preconditions.checkNotNull(mediaType, "ContentType cannot be null");
    }

    public void cancel() {
        this.mIsCancelled.set(true);
    }

    public AudioSourceListener getAudioSourceListener() {
        return this.mListener;
    }

    public abstract int getChunkSize();

    public abstract InputStream getConsumerStream();

    public MediaType getContentType() {
        return this.mContentType;
    }

    public boolean isCancelled() {
        return this.mIsCancelled.get();
    }

    public void setAudioSourceListener(AudioSourceListener audioSourceListener) {
        this.mListener = (AudioSourceListener) Preconditions.checkNotNull(audioSourceListener, "AudioSourceListener cannot be null");
    }
}
