package com.amazon.alexa.enrollment.unified.api;

import androidx.annotation.NonNull;
import com.amazon.alexa.handsfree.protocols.callback.ResponseCallback;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.model.EnrollmentErrorContract;
import java.util.List;
/* loaded from: classes7.dex */
public interface EnrollmentProvider {

    /* loaded from: classes7.dex */
    public interface UtteranceTrainingCallback {
        void onAudioCaptured(@NonNull short[] sArr);

        void onError(@NonNull EnrollmentErrorContract enrollmentErrorContract);

        void onSuccess();
    }

    void cancelUserVoiceEnrollment(@NonNull ResponseCallback responseCallback);

    void cancelUtteranceTraining(@NonNull ResponseCallback responseCallback);

    void finishUserVoiceEnrollment(@NonNull ResponseCallback responseCallback);

    List<String> getUtterances();

    void startUserVoiceEnrollment(@NonNull ResponseCallback responseCallback);

    void startUtteranceTraining(@NonNull UtteranceTrainingCallback utteranceTrainingCallback);
}
