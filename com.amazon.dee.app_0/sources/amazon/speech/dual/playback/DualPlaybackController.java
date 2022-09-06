package amazon.speech.dual.playback;

import amazon.speech.playback.ITtsPlaybackController;
import amazon.speech.playback.ITtsPlaybackListener;
import android.content.Context;
/* loaded from: classes.dex */
public class DualPlaybackController implements ITtsPlaybackController {
    final DualModeClient mDualModeClient;

    public DualPlaybackController(Context context) {
        this(DualModeClient.isDualModePlaybackServiceAvailable(context), context);
    }

    @Override // amazon.speech.playback.ITtsPlaybackController
    public void TTSEnded() {
        DualModeClient dualModeClient = this.mDualModeClient;
        if (dualModeClient != null) {
            dualModeClient.endTTS();
        }
    }

    @Override // amazon.speech.playback.ITtsPlaybackController
    public void TTSStarted(ITtsPlaybackListener iTtsPlaybackListener) {
        DualModeClient dualModeClient = this.mDualModeClient;
        if (dualModeClient != null) {
            dualModeClient.playTTS(iTtsPlaybackListener);
        } else {
            iTtsPlaybackListener.notifyTTSPlaybackStarted(-1L);
        }
    }

    DualPlaybackController(boolean z, Context context) {
        this(z ? new DualModeClient(context) : null);
    }

    DualPlaybackController(DualModeClient dualModeClient) {
        this.mDualModeClient = dualModeClient;
    }
}
