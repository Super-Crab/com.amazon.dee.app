package com.amazon.alexa.voice.ui.player;
/* loaded from: classes11.dex */
public interface PlaybackController {

    /* loaded from: classes11.dex */
    public interface Callback {
        void onPlaybackPaused();

        void onPlaybackStarted();

        void onPlaybackStopped();
    }

    void addCallback(Callback callback);

    long getCurrentPosition();

    long getDuration();

    boolean isPlaying();

    void next();

    void pause();

    void play();

    void previous();

    void removeCallback(Callback callback);

    void seek(long j);

    void stop();
}
