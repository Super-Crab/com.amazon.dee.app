package com.amazon.alexa.handsfree.protocols.uservoicerecognition.api;

import androidx.annotation.NonNull;
import com.amazon.alexa.handsfree.protocols.callback.ResponseCallback;
import com.amazon.alexa.handsfree.protocols.callback.ResultCallback;
/* loaded from: classes8.dex */
public interface UVRTuningSettings {
    void getAntiSpoofThreshold(@NonNull ResultCallback<Double> resultCallback);

    void getSecondStageThreshold(@NonNull ResultCallback<Integer> resultCallback);

    void getTrainingThreshold(@NonNull ResultCallback<Integer> resultCallback);

    void getUserConfidenceLevel(@NonNull ResultCallback<Integer> resultCallback);

    void setAntiSpoofThreshold(double d, @NonNull ResponseCallback responseCallback);

    void setSecondStageThreshold(int i, @NonNull ResponseCallback responseCallback);

    void setTrainingThreshold(int i, @NonNull ResponseCallback responseCallback);

    void setUserConfidenceLevel(int i, @NonNull ResponseCallback responseCallback);

    void setUtteranceTrainingTimeout(int i);
}
