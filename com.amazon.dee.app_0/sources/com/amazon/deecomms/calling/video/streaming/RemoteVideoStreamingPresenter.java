package com.amazon.deecomms.calling.video.streaming;

import androidx.annotation.NonNull;
import com.amazon.deecomms.app.RemoteViewManager;
/* loaded from: classes12.dex */
public class RemoteVideoStreamingPresenter implements VideoStreamingPresenterInterface {
    private final RemoteViewManager mRemoteViewManager;

    public RemoteVideoStreamingPresenter(@NonNull RemoteViewManager remoteViewManager) {
        this.mRemoteViewManager = remoteViewManager;
    }

    @Override // com.amazon.deecomms.calling.video.streaming.VideoStreamingPresenterInterface
    public void startStreaming() {
        this.mRemoteViewManager.start();
    }

    @Override // com.amazon.deecomms.calling.video.streaming.VideoStreamingPresenterInterface
    public void stopStreaming() {
        this.mRemoteViewManager.stop();
    }

    @Override // com.amazon.deecomms.calling.video.streaming.VideoStreamingPresenterInterface
    public void turnFrontSelfVideoFeedOn() {
    }

    @Override // com.amazon.deecomms.calling.video.streaming.VideoStreamingPresenterInterface
    public void turnRearSelfVideoFeedOn() {
    }
}
