package amazon.speech.tts;

import amazon.speech.util.RuntimeDeviceTypeHelper;
import android.media.AudioManager;
import android.util.Log;
/* loaded from: classes.dex */
public class TtsAudioFocusChangeListener implements AudioManager.OnAudioFocusChangeListener {
    private static final boolean DEBUG = false;
    private static final float HALF = 0.5f;
    private static final String TAG = TtsAudioFocusChangeListener.class.getSimpleName();
    final boolean mIgnoreAudioFocusChange;
    final ITtsMediaPlayer mTtsMediaPlayer;

    /* JADX INFO: Access modifiers changed from: package-private */
    public TtsAudioFocusChangeListener(ITtsMediaPlayer iTtsMediaPlayer, boolean z) {
        this.mTtsMediaPlayer = iTtsMediaPlayer;
        this.mIgnoreAudioFocusChange = z;
    }

    @Override // android.media.AudioManager.OnAudioFocusChangeListener
    public void onAudioFocusChange(int i) {
        String str = TAG;
        Log.i(str, "got focus change " + i);
        if (!RuntimeDeviceTypeHelper.isDeviceATablet()) {
            if (i == 1 || i == 2 || i == 4 || i == 3 || this.mIgnoreAudioFocusChange) {
                return;
            }
            this.mTtsMediaPlayer.interrupt();
        } else if (i == -3) {
            this.mTtsMediaPlayer.setVolume(HALF);
        } else if (i != 1 && i != 2 && i != 3 && i != 4) {
            if (this.mIgnoreAudioFocusChange) {
                return;
            }
            this.mTtsMediaPlayer.interrupt();
        } else {
            this.mTtsMediaPlayer.setVolume(1.0f);
        }
    }
}
