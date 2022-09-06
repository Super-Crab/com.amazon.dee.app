package com.amazon.comms.ringservice.webrtc;
/* loaded from: classes12.dex */
public interface VideoCodecHwSupportChecker {
    boolean isH264HwDecodeSupported();

    boolean isH264HwEncodeSupported();

    boolean isH264OrVp8HwDecodeSupported();

    boolean isH264OrVp8HwEncodeAndDecodeSupported();

    boolean isVp8HwDecodeSupported();

    boolean isVp8HwEncodeSupported();
}
