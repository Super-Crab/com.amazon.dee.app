package com.amazon.alexa.alertsca.events;

import com.amazon.alexa.alertsca.AlertRecord;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes6.dex */
public final class AutoValue_AlertStoppedEvent extends AlertStoppedEvent {
    private final AlertRecord alertRecord;
    private final boolean dismissedByUser;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_AlertStoppedEvent(AlertRecord alertRecord, boolean z) {
        if (alertRecord != null) {
            this.alertRecord = alertRecord;
            this.dismissedByUser = z;
            return;
        }
        throw new NullPointerException("Null alertRecord");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof AlertStoppedEvent)) {
            return false;
        }
        AlertStoppedEvent alertStoppedEvent = (AlertStoppedEvent) obj;
        return this.alertRecord.equals(alertStoppedEvent.getAlertRecord()) && this.dismissedByUser == alertStoppedEvent.isDismissedByUser();
    }

    @Override // com.amazon.alexa.alertsca.events.AlertEvent
    public AlertRecord getAlertRecord() {
        return this.alertRecord;
    }

    public int hashCode() {
        return ((this.alertRecord.hashCode() ^ 1000003) * 1000003) ^ (this.dismissedByUser ? 1231 : 1237);
    }

    @Override // com.amazon.alexa.alertsca.events.AlertStoppedEvent
    public boolean isDismissedByUser() {
        return this.dismissedByUser;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("AlertStoppedEvent{alertRecord=");
        outline107.append(this.alertRecord);
        outline107.append(", dismissedByUser=");
        return GeneratedOutlineSupport1.outline97(outline107, this.dismissedByUser, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}
