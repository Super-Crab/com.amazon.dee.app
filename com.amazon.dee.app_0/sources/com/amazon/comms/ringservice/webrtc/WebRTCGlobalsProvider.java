package com.amazon.comms.ringservice.webrtc;

import com.amazon.rtcmedia.webrtc.PeerConnectionFactoryWrapper;
/* loaded from: classes12.dex */
public interface WebRTCGlobalsProvider {
    PeerConnectionFactoryWrapper getPeerConnectionFactoryWrapper();

    VideoCodecHwSupportChecker getVideoCodecHwSupportChecker();
}
