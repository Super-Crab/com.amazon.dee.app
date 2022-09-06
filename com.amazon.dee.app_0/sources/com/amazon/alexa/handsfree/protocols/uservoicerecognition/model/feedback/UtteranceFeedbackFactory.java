package com.amazon.alexa.handsfree.protocols.uservoicerecognition.model.feedback;
/* loaded from: classes8.dex */
public class UtteranceFeedbackFactory {
    public UtteranceFeedback getPcmDataFeedback(short[] sArr) {
        return new PcmDataUtteranceFeedback(sArr);
    }

    public UtteranceFeedback getVolumeFeedback(int i, int i2, int i3) {
        return new VolumeUtteranceFeedback(i, i2, i3);
    }
}
