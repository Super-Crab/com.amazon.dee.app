package com.amazon.blueshift.bluefront.android.audio;

import com.amazon.blueshift.bluefront.android.http.part.InputStreamPart;
import com.google.common.base.Preconditions;
/* loaded from: classes11.dex */
public final class AudioSourcePart extends InputStreamPart {
    private static final String AUDIO_PART_NAME = "audio";

    public AudioSourcePart(AudioSource audioSource) {
        super(((AudioSource) Preconditions.checkNotNull(audioSource, "AudioSource cannot be null")).getConsumerStream(), "audio", audioSource.getContentType(), audioSource.getChunkSize());
    }
}
