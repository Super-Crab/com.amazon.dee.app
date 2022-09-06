package com.amazon.alexa.handsfree.uservoicerecognition.quebec.tuningsetting;

import androidx.annotation.NonNull;
import com.amazon.alexa.handsfree.protocols.callback.ResponseCallback;
import com.amazon.alexa.handsfree.protocols.callback.ResultCallback;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.api.UVRTuningSettings;
/* loaded from: classes8.dex */
public class QuebecUVRTuningSettings implements UVRTuningSettings {
    private static final String TAG = "QuebecUVRTuningSettings";

    @Override // com.amazon.alexa.handsfree.protocols.uservoicerecognition.api.UVRTuningSettings
    public void getAntiSpoofThreshold(@NonNull ResultCallback<Double> resultCallback) {
    }

    @Override // com.amazon.alexa.handsfree.protocols.uservoicerecognition.api.UVRTuningSettings
    public void getSecondStageThreshold(@NonNull ResultCallback<Integer> resultCallback) {
    }

    @Override // com.amazon.alexa.handsfree.protocols.uservoicerecognition.api.UVRTuningSettings
    public void getTrainingThreshold(@NonNull ResultCallback<Integer> resultCallback) {
    }

    @Override // com.amazon.alexa.handsfree.protocols.uservoicerecognition.api.UVRTuningSettings
    public void getUserConfidenceLevel(@NonNull ResultCallback<Integer> resultCallback) {
    }

    @Override // com.amazon.alexa.handsfree.protocols.uservoicerecognition.api.UVRTuningSettings
    public void setAntiSpoofThreshold(double d, @NonNull ResponseCallback responseCallback) {
    }

    @Override // com.amazon.alexa.handsfree.protocols.uservoicerecognition.api.UVRTuningSettings
    public void setSecondStageThreshold(int i, @NonNull ResponseCallback responseCallback) {
    }

    @Override // com.amazon.alexa.handsfree.protocols.uservoicerecognition.api.UVRTuningSettings
    public void setTrainingThreshold(int i, @NonNull ResponseCallback responseCallback) {
    }

    @Override // com.amazon.alexa.handsfree.protocols.uservoicerecognition.api.UVRTuningSettings
    public void setUserConfidenceLevel(int i, @NonNull ResponseCallback responseCallback) {
    }

    @Override // com.amazon.alexa.handsfree.protocols.uservoicerecognition.api.UVRTuningSettings
    public void setUtteranceTrainingTimeout(int i) {
    }
}
