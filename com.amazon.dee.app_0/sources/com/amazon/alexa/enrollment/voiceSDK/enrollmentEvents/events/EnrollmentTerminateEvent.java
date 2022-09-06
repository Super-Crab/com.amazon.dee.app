package com.amazon.alexa.enrollment.voiceSDK.enrollmentEvents.events;

import com.google.auto.value.AutoValue;
@AutoValue
/* loaded from: classes7.dex */
public abstract class EnrollmentTerminateEvent extends EnrollmentBaseEvent {
    public static EnrollmentTerminateEvent create(String str) {
        return new AutoValue_EnrollmentTerminateEvent(str);
    }

    public abstract String failureReason();
}
