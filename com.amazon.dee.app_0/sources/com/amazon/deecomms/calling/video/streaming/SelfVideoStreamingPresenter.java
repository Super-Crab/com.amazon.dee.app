package com.amazon.deecomms.calling.video.streaming;

import androidx.annotation.NonNull;
import com.amazon.deecomms.app.SelfViewManager;
/* loaded from: classes12.dex */
public class SelfVideoStreamingPresenter implements VideoStreamingPresenterInterface {
    final SelfViewManager selfViewManager;

    public SelfVideoStreamingPresenter(@NonNull SelfViewManager selfViewManager) {
        this.selfViewManager = selfViewManager;
    }

    @Override // com.amazon.deecomms.calling.video.streaming.VideoStreamingPresenterInterface
    public void startStreaming() {
        this.selfViewManager.start();
    }

    @Override // com.amazon.deecomms.calling.video.streaming.VideoStreamingPresenterInterface
    public void stopStreaming() {
        this.selfViewManager.stop();
    }

    @Override // com.amazon.deecomms.calling.video.streaming.VideoStreamingPresenterInterface
    public void turnFrontSelfVideoFeedOn() {
        this.selfViewManager.setMirror(true);
    }

    @Override // com.amazon.deecomms.calling.video.streaming.VideoStreamingPresenterInterface
    public void turnRearSelfVideoFeedOn() {
        this.selfViewManager.setMirror(false);
    }
}
