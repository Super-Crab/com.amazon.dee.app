package com.amazon.rtcsc.android.typedapi.types;

import com.amazon.rtcsc.android.typedapi.constants.MediaDirection;
import com.amazon.rtcsc.android.typedapi.constants.RtcpMuxPolicy;
/* loaded from: classes13.dex */
public class AudioStream {
    private final String direction;
    private final String rtcpMuxPolicy;

    public AudioStream(MediaDirection mediaDirection, RtcpMuxPolicy rtcpMuxPolicy) {
        this.direction = mediaDirection.name();
        this.rtcpMuxPolicy = rtcpMuxPolicy.name();
    }

    public String getDirection() {
        return this.direction;
    }

    public String getRtcpMuxPolicy() {
        return this.rtcpMuxPolicy;
    }

    public AudioStream(MediaDirection mediaDirection) {
        this.direction = mediaDirection.name();
        this.rtcpMuxPolicy = null;
    }
}
