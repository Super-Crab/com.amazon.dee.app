package com.amazon.alexa.handsfree.protocols.uservoicerecognition.model.feedback;

import com.amazon.alexa.handsfree.protocols.utils.Preconditions;
import java.util.Locale;
/* loaded from: classes8.dex */
class VolumeUtteranceFeedback implements UtteranceFeedback {
    private final int mMaxVolume;
    private final int mMinVolume;
    private final int mVolume;

    /* JADX INFO: Access modifiers changed from: package-private */
    public VolumeUtteranceFeedback(int i, int i2, int i3) {
        Preconditions.checkValidRange(i2, i3);
        Preconditions.checkInRange(i, i2, i3);
        this.mVolume = i;
        this.mMinVolume = i2;
        this.mMaxVolume = i3;
    }

    @Override // com.amazon.alexa.handsfree.protocols.uservoicerecognition.model.feedback.UtteranceFeedback
    public float getScaledVolume() {
        int i = this.mVolume;
        int i2 = this.mMinVolume;
        return (i - i2) / (this.mMaxVolume - i2);
    }

    public String toString() {
        return String.format(Locale.getDefault(), "[Volume = %d]", Integer.valueOf(this.mVolume));
    }
}
