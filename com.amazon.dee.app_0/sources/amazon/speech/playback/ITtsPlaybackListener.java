package amazon.speech.playback;
/* loaded from: classes.dex */
public interface ITtsPlaybackListener {
    void notifyTTSPlaybackInterrupted(long j);

    void notifyTTSPlaybackStarted(long j);

    boolean ttsHeartbeat(long j);
}
