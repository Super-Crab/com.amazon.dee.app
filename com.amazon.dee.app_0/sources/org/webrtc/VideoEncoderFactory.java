package org.webrtc;
/* loaded from: classes5.dex */
public interface VideoEncoderFactory {
    VideoEncoder createEncoder(VideoCodecInfo videoCodecInfo);

    VideoCodecInfo[] getSupportedCodecs();
}
