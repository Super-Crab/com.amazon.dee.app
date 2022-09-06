package com.amazon.alexa.enrollment.voiceSDK.enrollmentEvents.events;

import com.google.auto.value.AutoValue;
@AutoValue
/* loaded from: classes7.dex */
public abstract class UpdateTrainingPageUIEvent extends EnrollmentBaseEvent {
    public static UpdateTrainingPageUIEvent create(String str) {
        return new AutoValue_UpdateTrainingPageUIEvent(str);
    }

    public abstract String enrollmentState();
}
