package com.amazon.deecomms.media;

import androidx.annotation.NonNull;
import com.amazon.comms.calling.service.Call;
import com.amazon.deecomms.common.sip.SipClientState;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
@Singleton
/* loaded from: classes12.dex */
public class VideoStateController {
    private final SipClientState sipClientState;

    @Inject
    public VideoStateController(@NonNull @Named("CurrentCall") SipClientState sipClientState) {
        this.sipClientState = sipClientState;
    }

    public boolean isVideoOn() {
        Call currentActiveCall = this.sipClientState.getCurrentActiveCall();
        if (currentActiveCall == null || currentActiveCall.getMediaStatus() == null) {
            return false;
        }
        return currentActiveCall.getMediaStatus().isLocalVideoEnabled();
    }

    public void toggle() {
        Call currentActiveCall = this.sipClientState.getCurrentActiveCall();
        if (currentActiveCall == null || currentActiveCall.getMediaStatus() == null) {
            return;
        }
        boolean z = !currentActiveCall.getMediaStatus().isLocalVideoEnabled();
        currentActiveCall.setLocalVideoState(z);
        this.sipClientState.setUserTurnedVideoOff(z);
    }
}
