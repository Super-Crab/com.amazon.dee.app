package com.amazon.alexa.voice.nowplaying;

import com.amazon.alexa.voice.ui.player.PlaybackController;
/* loaded from: classes11.dex */
public class NoOpPlaybackController implements PlaybackController {
    @Override // com.amazon.alexa.voice.ui.player.PlaybackController
    public void addCallback(PlaybackController.Callback callback) {
    }

    @Override // com.amazon.alexa.voice.ui.player.PlaybackController
    public long getCurrentPosition() {
        return 0L;
    }

    @Override // com.amazon.alexa.voice.ui.player.PlaybackController
    public long getDuration() {
        return 0L;
    }

    @Override // com.amazon.alexa.voice.ui.player.PlaybackController
    public boolean isPlaying() {
        return false;
    }

    @Override // com.amazon.alexa.voice.ui.player.PlaybackController
    public void next() {
    }

    @Override // com.amazon.alexa.voice.ui.player.PlaybackController
    public void pause() {
    }

    @Override // com.amazon.alexa.voice.ui.player.PlaybackController
    public void play() {
    }

    @Override // com.amazon.alexa.voice.ui.player.PlaybackController
    public void previous() {
    }

    @Override // com.amazon.alexa.voice.ui.player.PlaybackController
    public void removeCallback(PlaybackController.Callback callback) {
    }

    @Override // com.amazon.alexa.voice.ui.player.PlaybackController
    public void seek(long j) {
    }

    @Override // com.amazon.alexa.voice.ui.player.PlaybackController
    public void stop() {
    }
}
