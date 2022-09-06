package com.amazon.deecomms.calling.video.display;

import android.content.Context;
import androidx.annotation.NonNull;
import com.amazon.comms.calling.service.WebRTCViewRenderer;
import com.amazon.deecomms.app.RemoteViewManager;
/* loaded from: classes12.dex */
public class RemoteVideoDisplayPresenter implements VideoDisplayInterface {
    private Context context;
    private RemoteViewManager remoteViewManager;

    public RemoteVideoDisplayPresenter(@NonNull RemoteViewManager remoteViewManager, @NonNull Context context) {
        this.remoteViewManager = remoteViewManager;
        this.context = context;
    }

    @Override // com.amazon.deecomms.calling.video.display.VideoDisplayInterface
    public void hideSelfView() {
    }

    @Override // com.amazon.deecomms.calling.video.display.VideoDisplayInterface
    public void maximiseVideo() {
    }

    @Override // com.amazon.deecomms.calling.video.display.VideoDisplayInterface
    public void minimizeVideo(int i) {
    }

    @Override // com.amazon.deecomms.calling.video.display.VideoDisplayInterface
    public void removePIP() {
    }

    @Override // com.amazon.deecomms.calling.video.display.VideoDisplayInterface
    public void setBackground(boolean z, boolean z2) {
        this.remoteViewManager.setVideoViewBackground(this.context, z, z2);
    }

    @Override // com.amazon.deecomms.calling.video.display.VideoDisplayInterface
    public void setScalingType(@NonNull WebRTCViewRenderer.ScalingType scalingType) {
        this.remoteViewManager.setScalingType(scalingType);
    }

    @Override // com.amazon.deecomms.calling.video.display.VideoDisplayInterface
    public void showSelfView() {
    }
}
