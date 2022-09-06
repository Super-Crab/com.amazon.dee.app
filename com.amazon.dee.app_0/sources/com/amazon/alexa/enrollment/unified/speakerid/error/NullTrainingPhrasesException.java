package com.amazon.alexa.enrollment.unified.speakerid.error;

import androidx.annotation.NonNull;
import com.amazon.alexa.enrollment.unified.speakerid.api.model.EnrollmentStates;
import java.util.Locale;
/* loaded from: classes7.dex */
public class NullTrainingPhrasesException extends Exception {
    private static final String MESSAGE_TEMPLATE = "Null training phrases obtained with enrollment state: %s";

    public NullTrainingPhrasesException(@NonNull EnrollmentStates enrollmentStates) {
        super(String.format(Locale.US, MESSAGE_TEMPLATE, enrollmentStates.name()));
    }
}
