package com.amazon.alexa.wakeword.speakerverification.profile;

import androidx.annotation.NonNull;
import com.amazon.alexa.wakeword.speakerverification.errors.TrainingFailure;
/* loaded from: classes11.dex */
public interface ProfileGenerationListener {
    void onGenerationFail(@NonNull TrainingFailure trainingFailure);

    void onGenerationSuccess();
}
