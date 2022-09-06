package com.amazon.comms.ringservice;

import com.amazon.comms.calling.service.Call;
import com.amazon.comms.calling.service.ErrorCode;
import com.amazon.comms.calling.service.MediaStateChangeTrigger;
import com.amazon.comms.calling.service.MediaStats;
import com.amazon.comms.calling.service.MediaStatus;
import com.amazon.comms.ringservice.Sdp;
/* loaded from: classes12.dex */
public interface MediaSessionListener {

    /* loaded from: classes12.dex */
    public static class SdpParams {
        private final Sdp sdp;
        private final Sdp.Type type;

        public SdpParams(Sdp sdp, Sdp.Type type) {
            this.sdp = sdp;
            this.type = type;
        }

        public Sdp getSdp() {
            return this.sdp;
        }

        public Sdp.Type getType() {
            return this.type;
        }
    }

    void onCameraInUseUpdate(boolean z);

    void onCameraOpening(String str);

    void onCameraSwitchDone(boolean z);

    void onCameraSwitchError(String str);

    void onDtmfInserted(boolean z, String str, int i, int i2);

    void onError(ErrorCode errorCode, String str);

    void onFirstSdpAvailable(SdpParams sdpParams);

    void onIceCandidatesGenerated(Sdp sdp);

    void onIceConnected();

    void onIceDisconnected();

    void onIceFailed();

    void onMediaStatsUpdated(MediaStats mediaStats);

    void onMediaStatusUpdated(Call.Side side, MediaStatus mediaStatus, MediaStateChangeTrigger mediaStateChangeTrigger);

    void onSdpUpdate(SdpParams sdpParams);

    void onVideoEffectChanged(Call.VideoEffect videoEffect);

    void onVideoReEnabled(Call.Side side);
}
