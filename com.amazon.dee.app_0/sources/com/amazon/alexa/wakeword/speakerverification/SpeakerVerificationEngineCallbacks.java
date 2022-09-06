package com.amazon.alexa.wakeword.speakerverification;

import androidx.annotation.NonNull;
import com.amazon.alexa.wakeword.speakerverification.enrollment.EnrollmentUtterance;
/* loaded from: classes11.dex */
public interface SpeakerVerificationEngineCallbacks {
    void onExampleAccepted(@NonNull EnrollmentUtterance enrollmentUtterance);

    void onExampleRejected();

    void onProfileGenerated(@NonNull byte[] bArr);
}
