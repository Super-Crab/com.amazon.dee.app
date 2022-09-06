package com.amazon.alexa.enrollment.voiceSDK.enrollmentEvents.events;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes7.dex */
public final class AutoValue_EnrollmentTerminateEvent extends EnrollmentTerminateEvent {
    private final String failureReason;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_EnrollmentTerminateEvent(String str) {
        if (str != null) {
            this.failureReason = str;
            return;
        }
        throw new NullPointerException("Null failureReason");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof EnrollmentTerminateEvent)) {
            return false;
        }
        return this.failureReason.equals(((EnrollmentTerminateEvent) obj).failureReason());
    }

    @Override // com.amazon.alexa.enrollment.voiceSDK.enrollmentEvents.events.EnrollmentTerminateEvent
    public String failureReason() {
        return this.failureReason;
    }

    public int hashCode() {
        return this.failureReason.hashCode() ^ 1000003;
    }

    public String toString() {
        return GeneratedOutlineSupport1.outline91(GeneratedOutlineSupport1.outline107("EnrollmentTerminateEvent{failureReason="), this.failureReason, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}
