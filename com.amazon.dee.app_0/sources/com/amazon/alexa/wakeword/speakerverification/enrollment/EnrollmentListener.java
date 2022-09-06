package com.amazon.alexa.wakeword.speakerverification.enrollment;

import androidx.annotation.NonNull;
import com.amazon.alexa.wakeword.WakeWordData;
import com.amazon.alexa.wakeword.speakerverification.errors.TrainingFailure;
/* loaded from: classes11.dex */
public interface EnrollmentListener {

    /* loaded from: classes11.dex */
    public interface ResponseCallback {
        void onFailure(@NonNull TrainingFailure trainingFailure);

        void onSuccess();
    }

    /* loaded from: classes11.dex */
    public interface UtteranceTrainingCallback {
        void onAudioCaptured(@NonNull short[] sArr);

        void onUtteranceTrainingFailure(@NonNull TrainingFailure trainingFailure);

        void onUtteranceTrainingSuccess(@NonNull WakeWordData wakeWordData);

        void onWakeWordDetected(@NonNull WakeWordData wakeWordData, @NonNull String str, @NonNull String str2, long j);
    }
}
