package amazon.speech.tts.playback;

import amazon.speech.playback.ITtsPlaybackController;
import amazon.speech.playback.ITtsPlaybackListener;
import amazon.speech.util.Log;
/* loaded from: classes.dex */
public class DefaultPlaybackController implements ITtsPlaybackController {
    private static String TAG = "DefaultPlaybackController";

    @Override // amazon.speech.playback.ITtsPlaybackController
    public void TTSEnded() {
        Log.i(TAG, "endTTS no-op");
    }

    @Override // amazon.speech.playback.ITtsPlaybackController
    public void TTSStarted(ITtsPlaybackListener iTtsPlaybackListener) {
        Log.i(TAG, "playTTS");
        if (iTtsPlaybackListener != null) {
            iTtsPlaybackListener.notifyTTSPlaybackStarted(-1L);
            return;
        }
        throw new IllegalArgumentException("ITtsPlaybackListener cannot be null");
    }
}
