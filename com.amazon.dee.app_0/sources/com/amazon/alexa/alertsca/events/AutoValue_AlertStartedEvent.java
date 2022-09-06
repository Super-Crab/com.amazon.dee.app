package com.amazon.alexa.alertsca.events;

import com.amazon.alexa.alertsca.AlertRecord;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes6.dex */
public final class AutoValue_AlertStartedEvent extends AlertStartedEvent {
    private final AlertRecord alertRecord;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_AlertStartedEvent(AlertRecord alertRecord) {
        if (alertRecord != null) {
            this.alertRecord = alertRecord;
            return;
        }
        throw new NullPointerException("Null alertRecord");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof AlertStartedEvent)) {
            return false;
        }
        return this.alertRecord.equals(((AlertStartedEvent) obj).getAlertRecord());
    }

    @Override // com.amazon.alexa.alertsca.events.AlertEvent
    public AlertRecord getAlertRecord() {
        return this.alertRecord;
    }

    public int hashCode() {
        return this.alertRecord.hashCode() ^ 1000003;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("AlertStartedEvent{alertRecord=");
        outline107.append(this.alertRecord);
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }
}
