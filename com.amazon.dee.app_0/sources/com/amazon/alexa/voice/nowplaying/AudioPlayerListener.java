package com.amazon.alexa.voice.nowplaying;
/* loaded from: classes11.dex */
public interface AudioPlayerListener {
    void onBuffering();

    void onError();

    void onIdle();

    void onPaused();

    void onPlaybackEnded();

    void onPlaying();
}
