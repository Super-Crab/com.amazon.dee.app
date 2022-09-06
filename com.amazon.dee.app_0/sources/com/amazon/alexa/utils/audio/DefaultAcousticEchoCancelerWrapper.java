package com.amazon.alexa.utils.audio;

import android.media.AudioRecord;
import android.media.audiofx.AcousticEchoCanceler;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.security.InvalidParameterException;
/* loaded from: classes10.dex */
public class DefaultAcousticEchoCancelerWrapper implements AcousticEchoCancelerWrapper {
    private static final String TAG = "DefaultAcousticEchoCancelerWrapper";
    private final AcousticEchoCanceler acousticEchoCanceler;

    /* JADX INFO: Access modifiers changed from: package-private */
    public DefaultAcousticEchoCancelerWrapper(AudioRecord audioRecord) {
        if (audioRecord == null || !AcousticEchoCanceler.isAvailable()) {
            throw new InvalidParameterException("invalid parameters to create AEC");
        }
        this.acousticEchoCanceler = AcousticEchoCanceler.create(audioRecord.getAudioSessionId());
    }

    @Override // com.amazon.alexa.utils.audio.AcousticEchoCancelerWrapper
    public boolean getEnabled() {
        return this.acousticEchoCanceler.getEnabled();
    }

    @Override // com.amazon.alexa.utils.audio.AcousticEchoCancelerWrapper
    public boolean isAvailable() {
        return true;
    }

    @Override // com.amazon.alexa.utils.audio.AcousticEchoCancelerWrapper
    public void release() {
        this.acousticEchoCanceler.release();
    }

    @Override // com.amazon.alexa.utils.audio.AcousticEchoCancelerWrapper
    public void setEnabled(boolean z) {
        GeneratedOutlineSupport1.outline172("AEC setEnabled = ", z);
        this.acousticEchoCanceler.setEnabled(z);
    }
}
