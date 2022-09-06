package com.amazon.alexa.handsfree.protocols.uservoicerecognition.callback;

import androidx.annotation.NonNull;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.model.EnrollmentErrorContract;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.model.UtteranceTrainingMetadata;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.model.feedback.UtteranceFeedback;
/* loaded from: classes8.dex */
public interface EnrollmentCallback {
    void onError(@NonNull EnrollmentErrorContract enrollmentErrorContract, @NonNull UtteranceTrainingMetadata utteranceTrainingMetadata);

    void onFeedback(@NonNull UtteranceFeedback utteranceFeedback);

    void onStartProcessing();

    void onStartRecording();

    void onStopProcessing();

    void onStopRecording();

    void onSuccess(@NonNull UtteranceTrainingMetadata utteranceTrainingMetadata);
}
