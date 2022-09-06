package com.amazon.alexa.handsfree.uservoicerecognition.edgesv.tuningsetting;

import androidx.annotation.NonNull;
import com.amazon.alexa.handsfree.protocols.callback.ResponseCallback;
import com.amazon.alexa.handsfree.protocols.callback.ResultCallback;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.api.UVRTuningSettings;
import com.amazon.alexa.handsfree.protocols.utils.Log;
/* loaded from: classes8.dex */
public class EdgeSVUVRTuningSettings implements UVRTuningSettings {
    private static final String TAG = "EdgeSVUVRTuning";

    @Override // com.amazon.alexa.handsfree.protocols.uservoicerecognition.api.UVRTuningSettings
    public void getAntiSpoofThreshold(@NonNull ResultCallback<Double> resultCallback) {
        Log.d(TAG, "Tuning ability is unavailable for 1pSV and SpeakerID");
    }

    @Override // com.amazon.alexa.handsfree.protocols.uservoicerecognition.api.UVRTuningSettings
    public void getSecondStageThreshold(@NonNull ResultCallback<Integer> resultCallback) {
        Log.d(TAG, "Tuning ability is unavailable for 1pSV and SpeakerID");
    }

    @Override // com.amazon.alexa.handsfree.protocols.uservoicerecognition.api.UVRTuningSettings
    public void getTrainingThreshold(@NonNull ResultCallback<Integer> resultCallback) {
        Log.d(TAG, "Tuning ability is unavailable for 1pSV and SpeakerID");
    }

    @Override // com.amazon.alexa.handsfree.protocols.uservoicerecognition.api.UVRTuningSettings
    public void getUserConfidenceLevel(@NonNull ResultCallback<Integer> resultCallback) {
        Log.d(TAG, "Tuning ability is unavailable for 1pSV and SpeakerID");
    }

    @Override // com.amazon.alexa.handsfree.protocols.uservoicerecognition.api.UVRTuningSettings
    public void setAntiSpoofThreshold(double d, @NonNull ResponseCallback responseCallback) {
        Log.d(TAG, "Tuning ability is unavailable for 1pSV and SpeakerID");
    }

    @Override // com.amazon.alexa.handsfree.protocols.uservoicerecognition.api.UVRTuningSettings
    public void setSecondStageThreshold(int i, @NonNull ResponseCallback responseCallback) {
        Log.d(TAG, "Tuning ability is unavailable for 1pSV and SpeakerID");
    }

    @Override // com.amazon.alexa.handsfree.protocols.uservoicerecognition.api.UVRTuningSettings
    public void setTrainingThreshold(int i, @NonNull ResponseCallback responseCallback) {
        Log.d(TAG, "Tuning ability is unavailable for 1pSV and SpeakerID");
    }

    @Override // com.amazon.alexa.handsfree.protocols.uservoicerecognition.api.UVRTuningSettings
    public void setUserConfidenceLevel(int i, @NonNull ResponseCallback responseCallback) {
        Log.d(TAG, "Tuning ability is unavailable for 1pSV and SpeakerID");
    }

    @Override // com.amazon.alexa.handsfree.protocols.uservoicerecognition.api.UVRTuningSettings
    public void setUtteranceTrainingTimeout(int i) {
        Log.d(TAG, "Tuning ability is unavailable for 1pSV and SpeakerID");
    }
}
