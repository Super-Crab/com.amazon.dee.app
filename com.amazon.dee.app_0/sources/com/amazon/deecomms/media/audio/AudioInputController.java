package com.amazon.deecomms.media.audio;

import android.media.AudioManager;
import androidx.annotation.NonNull;
import com.amazon.deecomms.calling.model.CallContext;
import javax.inject.Inject;
import javax.inject.Singleton;
@Singleton
/* loaded from: classes12.dex */
public class AudioInputController {
    private final AudioManager audioManager;
    private final CallContext callContext;

    @Inject
    public AudioInputController(@NonNull AudioManager audioManager, @NonNull CallContext callContext) {
        this.audioManager = audioManager;
        this.callContext = callContext;
    }

    public boolean isMicMuted() {
        return this.audioManager.isMicrophoneMute();
    }

    public void toggleMic() {
        if (this.callContext.getCurrentCall() != null) {
            boolean isLocalAudioEnabled = this.callContext.getCurrentCall().getMediaStatus().isLocalAudioEnabled();
            this.callContext.getCurrentCall().setLocalAudioState(!isLocalAudioEnabled);
            this.audioManager.setMicrophoneMute(isLocalAudioEnabled);
        }
    }
}
