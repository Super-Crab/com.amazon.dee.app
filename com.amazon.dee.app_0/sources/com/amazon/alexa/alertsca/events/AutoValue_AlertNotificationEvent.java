package com.amazon.alexa.alertsca.events;

import android.app.Notification;
import com.amazon.alexa.alertsca.AlertRecord;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes6.dex */
public final class AutoValue_AlertNotificationEvent extends AlertNotificationEvent {
    private final AlertRecord alertRecord;
    private final Notification notification;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_AlertNotificationEvent(AlertRecord alertRecord, Notification notification) {
        if (alertRecord != null) {
            this.alertRecord = alertRecord;
            if (notification != null) {
                this.notification = notification;
                return;
            }
            throw new NullPointerException("Null notification");
        }
        throw new NullPointerException("Null alertRecord");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof AlertNotificationEvent)) {
            return false;
        }
        AlertNotificationEvent alertNotificationEvent = (AlertNotificationEvent) obj;
        return this.alertRecord.equals(alertNotificationEvent.getAlertRecord()) && this.notification.equals(alertNotificationEvent.getNotification());
    }

    @Override // com.amazon.alexa.alertsca.events.AlertNotificationEvent
    public AlertRecord getAlertRecord() {
        return this.alertRecord;
    }

    @Override // com.amazon.alexa.alertsca.events.AlertNotificationEvent
    public Notification getNotification() {
        return this.notification;
    }

    public int hashCode() {
        return ((this.alertRecord.hashCode() ^ 1000003) * 1000003) ^ this.notification.hashCode();
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("AlertNotificationEvent{alertRecord=");
        outline107.append(this.alertRecord);
        outline107.append(", notification=");
        outline107.append(this.notification);
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }
}
