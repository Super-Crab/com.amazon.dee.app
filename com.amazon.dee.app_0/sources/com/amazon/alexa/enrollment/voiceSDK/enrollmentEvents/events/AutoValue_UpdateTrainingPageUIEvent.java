package com.amazon.alexa.enrollment.voiceSDK.enrollmentEvents.events;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes7.dex */
public final class AutoValue_UpdateTrainingPageUIEvent extends UpdateTrainingPageUIEvent {
    private final String enrollmentState;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_UpdateTrainingPageUIEvent(String str) {
        if (str != null) {
            this.enrollmentState = str;
            return;
        }
        throw new NullPointerException("Null enrollmentState");
    }

    @Override // com.amazon.alexa.enrollment.voiceSDK.enrollmentEvents.events.UpdateTrainingPageUIEvent
    public String enrollmentState() {
        return this.enrollmentState;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof UpdateTrainingPageUIEvent)) {
            return false;
        }
        return this.enrollmentState.equals(((UpdateTrainingPageUIEvent) obj).enrollmentState());
    }

    public int hashCode() {
        return this.enrollmentState.hashCode() ^ 1000003;
    }

    public String toString() {
        return GeneratedOutlineSupport1.outline91(GeneratedOutlineSupport1.outline107("UpdateTrainingPageUIEvent{enrollmentState="), this.enrollmentState, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}
