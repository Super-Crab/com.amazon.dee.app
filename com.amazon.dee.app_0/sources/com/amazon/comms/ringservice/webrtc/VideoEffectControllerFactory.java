package com.amazon.comms.ringservice.webrtc;

import com.amazon.comms.calling.service.Call;
import com.amazon.comms.ringservice.webrtc.VideoEffectController;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes12.dex */
public class VideoEffectControllerFactory {

    /* renamed from: com.amazon.comms.ringservice.webrtc.VideoEffectControllerFactory$1  reason: invalid class name */
    /* loaded from: classes12.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$comms$calling$service$Call$VideoEffect = new int[Call.VideoEffect.values().length];

        static {
            try {
                $SwitchMap$com$amazon$comms$calling$service$Call$VideoEffect[Call.VideoEffect.Frosted.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    public static VideoEffectController getVideoEffectController(Call.VideoEffect videoEffect, PeerConnectionClient peerConnectionClient, VideoEffectController.VideoEffectChangeListener videoEffectChangeListener) {
        if (videoEffect.ordinal() == 1) {
            return new FrostVideoEffectController(peerConnectionClient, videoEffectChangeListener);
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("VideoEffectControllerFactory:Unsupported VideoEffect ");
        outline107.append(videoEffect.toString());
        throw new IllegalArgumentException(outline107.toString());
    }
}
