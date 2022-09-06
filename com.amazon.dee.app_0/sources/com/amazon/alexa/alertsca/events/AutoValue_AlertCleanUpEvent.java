package com.amazon.alexa.alertsca.events;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes6.dex */
public final class AutoValue_AlertCleanUpEvent extends AlertCleanUpEvent {
    private final int alertRecordId;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_AlertCleanUpEvent(int i) {
        this.alertRecordId = i;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof AlertCleanUpEvent) && this.alertRecordId == ((AlertCleanUpEvent) obj).getAlertRecordId();
    }

    @Override // com.amazon.alexa.alertsca.events.AlertCleanUpEvent
    public int getAlertRecordId() {
        return this.alertRecordId;
    }

    public int hashCode() {
        return this.alertRecordId ^ 1000003;
    }

    public String toString() {
        return GeneratedOutlineSupport1.outline86(GeneratedOutlineSupport1.outline107("AlertCleanUpEvent{alertRecordId="), this.alertRecordId, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}
