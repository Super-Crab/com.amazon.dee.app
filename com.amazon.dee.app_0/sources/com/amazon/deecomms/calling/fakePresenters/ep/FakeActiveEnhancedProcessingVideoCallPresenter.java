package com.amazon.deecomms.calling.fakePresenters.ep;

import androidx.annotation.NonNull;
import com.amazon.deecomms.calling.accessibility.RealTimeTextEnablementAuthority;
import com.amazon.deecomms.calling.accessibility.RealTimeTextPresenter;
import com.amazon.deecomms.calling.contracts.active.ActiveVideoCallPresenterContract;
import com.amazon.deecomms.calling.contracts.active.ActiveVideoCallViewContract;
import com.amazon.deecomms.calling.enums.CallType;
import com.amazon.deecomms.calling.ui.listener.VideoStreamingListener;
import com.amazon.deecomms.core.CapabilitiesManager;
/* loaded from: classes12.dex */
public abstract class FakeActiveEnhancedProcessingVideoCallPresenter implements ActiveVideoCallPresenterContract {
    final ActiveVideoCallViewContract activeVideoCallViewContract;
    final CallType callType;
    final CapabilitiesManager capabilitiesManager;
    final VideoStreamingListener videoStreamingListener;

    public FakeActiveEnhancedProcessingVideoCallPresenter(@NonNull ActiveVideoCallViewContract activeVideoCallViewContract, @NonNull CapabilitiesManager capabilitiesManager, @NonNull VideoStreamingListener videoStreamingListener, @NonNull boolean z) {
        this.activeVideoCallViewContract = activeVideoCallViewContract;
        this.capabilitiesManager = capabilitiesManager;
        this.videoStreamingListener = videoStreamingListener;
        this.callType = z ? CallType.VIDEO_DROP_IN : CallType.VIDEO;
    }

    @Override // com.amazon.deecomms.calling.contracts.active.ActiveVideoCallPresenterContract
    public void endCall() {
    }

    @Override // com.amazon.deecomms.calling.contracts.active.ActiveVideoCallPresenterContract
    public RealTimeTextEnablementAuthority getRealTimeTextEnablementAuthority() {
        return null;
    }

    @Override // com.amazon.deecomms.calling.contracts.active.ActiveVideoCallPresenterContract
    public RealTimeTextPresenter getRealTimeTextPresenter() {
        return null;
    }

    @Override // com.amazon.deecomms.calling.contracts.active.ActiveVideoCallPresenterContract
    public String getRemoteParticipantName() {
        return null;
    }

    @Override // com.amazon.deecomms.calling.contracts.active.ActiveVideoCallPresenterContract
    public boolean isRTTEnabled() {
        return false;
    }

    @Override // com.amazon.deecomms.calling.contracts.active.ActiveVideoCallPresenterContract
    public void onViewBackgrounded() {
    }

    @Override // com.amazon.deecomms.calling.contracts.BasePresenterContract
    public void onViewCreated() {
    }

    @Override // com.amazon.deecomms.calling.contracts.BasePresenterContract
    public void onViewDestroyed() {
    }

    @Override // com.amazon.deecomms.calling.contracts.active.ActiveVideoCallPresenterContract
    public void onViewForegrounded() {
    }

    @Override // com.amazon.deecomms.calling.contracts.active.ActiveVideoCallPresenterContract
    public void toggleCamera() {
    }

    @Override // com.amazon.deecomms.calling.contracts.active.ActiveVideoCallPresenterContract
    public void toggleMic() {
    }

    @Override // com.amazon.deecomms.calling.contracts.active.ActiveVideoCallPresenterContract
    public void toggleSpeaker() {
    }

    @Override // com.amazon.deecomms.calling.contracts.active.ActiveVideoCallPresenterContract
    public void toggleVideo() {
    }
}
