package com.amazon.comms.ringservice.webrtc;

import com.amazon.comms.calling.service.Call;
/* loaded from: classes12.dex */
public interface VideoEffectController {

    /* loaded from: classes12.dex */
    public interface VideoEffectChangeListener {
        void onVideoEffectChanged(VideoEffectController videoEffectController);
    }

    Call.VideoEffect getVideoEffect();

    void shutdown();

    void start(double d);

    void stop(double d);
}
