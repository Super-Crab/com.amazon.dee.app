package org.webrtc;

import android.util.Log;
import com.amazon.alexa.mobilytics.configuration.Config;
/* loaded from: classes5.dex */
public class VideoEffectShim {
    private static final String AMAZON_HAL_EFFECTS = "amazon-hal-effects";
    private static final String AMAZON_HAL_EFFECTS_STATUS = "amazon-hal-effects-status";
    private static final String TAG = "VideoEffectShim";
    private final LocalAudioVideoShim audioVideoShim;
    private final WebRTCVideoEffectTransitionListener listener;

    /* loaded from: classes5.dex */
    public interface WebRTCVideoEffectTransitionListener {
        void onWebRTCVideoEffectTransition(String str);
    }

    private VideoEffectShim() {
        this.audioVideoShim = null;
        this.listener = null;
    }

    public void endVideoEffect() {
        Log.i(TAG, "endVideoEffect");
        this.audioVideoShim.endVideoEffect();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void init(CameraVideoCapturer cameraVideoCapturer) {
        Log.i(TAG, "VideoEffectShim initialize");
        if (this.listener == null || cameraVideoCapturer == null) {
            return;
        }
        Log.i(TAG, "setting cached video effect listener");
        cameraVideoCapturer.setVideoEffectStatusListener(AMAZON_HAL_EFFECTS_STATUS, new VideoEffectStatusListener() { // from class: org.webrtc.VideoEffectShim.1
            @Override // org.webrtc.VideoEffectStatusListener
            public void onStatusChanged(String str) {
                VideoEffectShim.this.listener.onWebRTCVideoEffectTransition(str);
            }
        });
    }

    public void setVideoEffect(String str) {
        String str2 = TAG;
        Log.i(str2, "setVideoEffect=" + str + Config.Compare.EQUAL_TO);
        this.audioVideoShim.setPlatformCameraParameter(AMAZON_HAL_EFFECTS, str);
    }

    public VideoEffectShim(LocalAudioVideoShim localAudioVideoShim, WebRTCVideoEffectTransitionListener webRTCVideoEffectTransitionListener) {
        this.listener = webRTCVideoEffectTransitionListener;
        this.audioVideoShim = localAudioVideoShim;
    }
}
