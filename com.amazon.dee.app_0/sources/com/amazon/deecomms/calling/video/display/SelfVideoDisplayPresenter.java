package com.amazon.deecomms.calling.video.display;

import androidx.annotation.NonNull;
import com.amazon.comms.calling.service.WebRTCViewRenderer;
import com.amazon.deecomms.app.SelfViewManager;
/* loaded from: classes12.dex */
public class SelfVideoDisplayPresenter implements VideoDisplayInterface {
    private SelfViewManager selfViewManager;

    public SelfVideoDisplayPresenter(@NonNull SelfViewManager selfViewManager) {
        this.selfViewManager = selfViewManager;
    }

    public void hideScrim() {
        this.selfViewManager.hideScrim();
    }

    @Override // com.amazon.deecomms.calling.video.display.VideoDisplayInterface
    public void hideSelfView() {
        this.selfViewManager.hideSelfView();
    }

    @Override // com.amazon.deecomms.calling.video.display.VideoDisplayInterface
    public void maximiseVideo() {
        this.selfViewManager.maximizeSelfView();
    }

    @Override // com.amazon.deecomms.calling.video.display.VideoDisplayInterface
    public void minimizeVideo(int i) {
        this.selfViewManager.minimizeSelfView(i);
    }

    @Override // com.amazon.deecomms.calling.video.display.VideoDisplayInterface
    public void removePIP() {
        this.selfViewManager.removePIP();
    }

    @Override // com.amazon.deecomms.calling.video.display.VideoDisplayInterface
    public void setBackground(boolean z, boolean z2) {
        this.selfViewManager.setVideoViewBackground(null, z, z2);
    }

    @Override // com.amazon.deecomms.calling.video.display.VideoDisplayInterface
    public void setScalingType(@NonNull WebRTCViewRenderer.ScalingType scalingType) {
    }

    public void showScrim() {
        this.selfViewManager.showScrim();
    }

    @Override // com.amazon.deecomms.calling.video.display.VideoDisplayInterface
    public void showSelfView() {
        this.selfViewManager.showSelfView();
    }
}
