package amazon.speech.simclient;

import amazon.speech.util.RuntimeDeviceTypeHelper;
import android.media.AudioManager;
import android.util.Log;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class TtsAudioFocusChangeListener implements AudioManager.OnAudioFocusChangeListener {
    private static final boolean DEBUG = false;
    private static final String TAG = TtsAudioFocusChangeListener.class.getSimpleName();
    private final TtsPlayer mTtsPlayer;

    /* JADX INFO: Access modifiers changed from: package-private */
    public TtsAudioFocusChangeListener(TtsPlayer ttsPlayer) {
        this.mTtsPlayer = ttsPlayer;
    }

    @Override // android.media.AudioManager.OnAudioFocusChangeListener
    public void onAudioFocusChange(int i) {
        String str = TAG;
        Log.i(str, "got focus change " + i);
        if (!RuntimeDeviceTypeHelper.isDeviceATablet()) {
            if (i == 1 || i == 2 || i == 4 || i == 3) {
                return;
            }
            this.mTtsPlayer.interrupt();
        } else if (i == -3) {
            this.mTtsPlayer.setVolume(0.5f);
        } else if (i != 1 && i != 2 && i != 3 && i != 4) {
            this.mTtsPlayer.interrupt();
        } else {
            this.mTtsPlayer.setVolume(1.0f);
        }
    }
}
