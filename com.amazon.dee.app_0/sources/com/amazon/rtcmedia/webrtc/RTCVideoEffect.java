package com.amazon.rtcmedia.webrtc;

import com.amazon.rtcmedia.util.RtcscLogger;
import com.android.tools.r8.GeneratedOutlineSupport1;
import org.webrtc.CameraVideoCapturer;
import org.webrtc.VideoEffectStatusListener;
/* loaded from: classes13.dex */
public class RTCVideoEffect {
    private static final String AMAZON_HAL_EFFECTS = "amazon-hal-effects";
    private static final String AMAZON_HAL_EFFECTS_STATUS = "amazon-hal-effects-status";
    private static RtcscLogger mLog = RtcscLogger.getLogger(RTCVideoEffect.class);
    private final WebRTCVideoEffectTransitionListener listener;
    private final RTCLocalVideoHandler localVideoHandler;

    /* loaded from: classes13.dex */
    public interface WebRTCVideoEffectTransitionListener {
        void onWebRTCVideoEffectTransition(String str);
    }

    private RTCVideoEffect() {
        this.localVideoHandler = null;
        this.listener = null;
    }

    public void endVideoEffect() {
        mLog.i("endVideoEffect");
        this.localVideoHandler.endVideoEffect();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void init(CameraVideoCapturer cameraVideoCapturer) {
        mLog.i("initialize");
        if (this.listener == null || cameraVideoCapturer == null) {
            return;
        }
        mLog.i("setting video effect listener");
        cameraVideoCapturer.setVideoEffectStatusListener(AMAZON_HAL_EFFECTS_STATUS, new VideoEffectStatusListener() { // from class: com.amazon.rtcmedia.webrtc.RTCVideoEffect.1
            @Override // org.webrtc.VideoEffectStatusListener
            public void onStatusChanged(String str) {
                RTCVideoEffect.this.listener.onWebRTCVideoEffectTransition(str);
            }
        });
    }

    public void setVideoEffect(String str) {
        GeneratedOutlineSupport1.outline160("setVideoEffect=", str, mLog);
        this.localVideoHandler.setPlatformCameraParameter(AMAZON_HAL_EFFECTS, str);
    }

    public RTCVideoEffect(RTCLocalVideoHandler rTCLocalVideoHandler, WebRTCVideoEffectTransitionListener webRTCVideoEffectTransitionListener) {
        this.listener = webRTCVideoEffectTransitionListener;
        this.localVideoHandler = rTCLocalVideoHandler;
    }
}
