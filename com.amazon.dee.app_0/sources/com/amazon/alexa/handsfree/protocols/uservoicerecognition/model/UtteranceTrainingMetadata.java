package com.amazon.alexa.handsfree.protocols.uservoicerecognition.model;

import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.handsfree.protocols.utils.Preconditions;
import java.util.Locale;
import java.util.Objects;
/* loaded from: classes8.dex */
public final class UtteranceTrainingMetadata {
    @VisibleForTesting
    static final int MAX_SOUND_TO_NOISE_RATIO = 100;
    @VisibleForTesting
    static final int MAX_UTTERANCE_CONFIDENCE = 100;
    @VisibleForTesting
    static final int MIN_SOUND_TO_NOISE_RATIO = 0;
    @VisibleForTesting
    static final int MIN_UTTERANCE_CONFIDENCE = 0;
    private final int mSoundToNoiseRatio;
    private final int mUtteranceConfidence;

    public UtteranceTrainingMetadata(int i, int i2) {
        Preconditions.checkArgument(i >= 0 && i <= 100, String.format(Locale.getDefault(), "Sound to noise ratio should be between %d and %d.", 0, 100));
        Preconditions.checkArgument(i2 >= 0 && i2 <= 100, String.format(Locale.getDefault(), "Utterance confidence should be between %d and %d.", 0, 100));
        this.mSoundToNoiseRatio = i;
        this.mUtteranceConfidence = i2;
    }

    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != UtteranceTrainingMetadata.class) {
            return false;
        }
        UtteranceTrainingMetadata utteranceTrainingMetadata = (UtteranceTrainingMetadata) obj;
        return this.mSoundToNoiseRatio == utteranceTrainingMetadata.mSoundToNoiseRatio && this.mUtteranceConfidence == utteranceTrainingMetadata.mUtteranceConfidence;
    }

    public int getSoundToNoiseRatio() {
        return this.mSoundToNoiseRatio;
    }

    public int getUtteranceConfidence() {
        return this.mUtteranceConfidence;
    }

    public int hashCode() {
        return Objects.hash(Integer.valueOf(this.mSoundToNoiseRatio), Integer.valueOf(this.mUtteranceConfidence));
    }

    public String toString() {
        return String.format(Locale.getDefault(), "[SNR = %d, Utterance confidence = %d]", Integer.valueOf(this.mSoundToNoiseRatio), Integer.valueOf(this.mUtteranceConfidence));
    }
}
