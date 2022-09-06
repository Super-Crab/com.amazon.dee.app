package com.amazon.alexa.handsfree.protocols.uservoicerecognition.model.feedback;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.audio.ScaledVolumeProcessor;
import java.nio.ShortBuffer;
import java.util.Objects;
/* loaded from: classes8.dex */
class PcmDataUtteranceFeedback implements UtteranceFeedback {
    private final ShortBuffer mBuffer;
    private final ScaledVolumeProcessor mScaledVolumeProcessor;

    /* JADX INFO: Access modifiers changed from: package-private */
    public PcmDataUtteranceFeedback(short[] sArr) {
        this(ShortBuffer.wrap((short[]) Objects.requireNonNull(sArr, "Buffer must not be null.")), new ScaledVolumeProcessor());
    }

    @Override // com.amazon.alexa.handsfree.protocols.uservoicerecognition.model.feedback.UtteranceFeedback
    public float getScaledVolume() {
        return this.mScaledVolumeProcessor.computeScaledVolume(this.mBuffer);
    }

    public String toString() {
        return Objects.toString(this.mBuffer);
    }

    @VisibleForTesting
    PcmDataUtteranceFeedback(@NonNull ShortBuffer shortBuffer, @NonNull ScaledVolumeProcessor scaledVolumeProcessor) {
        this.mBuffer = shortBuffer;
        this.mScaledVolumeProcessor = scaledVolumeProcessor;
    }
}
