package com.amazon.rtcsc.android.typedapi.types;

import com.amazon.rtcsc.android.typedapi.constants.BundlePolicy;
import lombok.NonNull;
/* loaded from: classes13.dex */
public class MediaStreams {
    @NonNull
    private final AudioStream audio;
    private String bundlePolicy;
    @NonNull
    private final VideoStream video;

    public MediaStreams(@NonNull AudioStream audioStream, @NonNull VideoStream videoStream) {
        if (audioStream != null) {
            if (videoStream == null) {
                throw new NullPointerException("video");
            }
            this.audio = audioStream;
            this.video = videoStream;
            return;
        }
        throw new NullPointerException("audio");
    }

    @NonNull
    public AudioStream getAudio() {
        return this.audio;
    }

    public String getBundlePolicy() {
        return this.bundlePolicy;
    }

    @NonNull
    public VideoStream getVideo() {
        return this.video;
    }

    public MediaStreams(BundlePolicy bundlePolicy, AudioStream audioStream, VideoStream videoStream) {
        this.bundlePolicy = bundlePolicy.name();
        this.audio = audioStream;
        this.video = videoStream;
    }
}
