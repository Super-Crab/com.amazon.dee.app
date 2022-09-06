package com.amazon.alexa.utils.audio;

import android.media.AudioRecord;
import android.media.audiofx.AcousticEchoCanceler;
import android.util.Log;
/* loaded from: classes10.dex */
public class AcousticEchoCancelerWrapperFactory {
    private static final String TAG = "AcousticEchoCancelerWrapperFactory";
    private static final NoOpAcousticEchoCancelerWrapper nop = new NoOpAcousticEchoCancelerWrapper();

    public AcousticEchoCancelerWrapper create(AudioRecord audioRecord) {
        if (audioRecord == null) {
            return nop;
        }
        if (AcousticEchoCanceler.isAvailable()) {
            return new DefaultAcousticEchoCancelerWrapper(audioRecord);
        }
        Log.w(TAG, "AEC is not available");
        return nop;
    }
}
