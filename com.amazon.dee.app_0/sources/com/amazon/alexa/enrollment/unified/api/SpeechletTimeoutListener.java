package com.amazon.alexa.enrollment.unified.api;

import androidx.annotation.NonNull;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.model.EnrollmentErrorContract;
/* loaded from: classes7.dex */
public interface SpeechletTimeoutListener {
    void onSpeechletTimedOut(@NonNull EnrollmentErrorContract enrollmentErrorContract);
}
