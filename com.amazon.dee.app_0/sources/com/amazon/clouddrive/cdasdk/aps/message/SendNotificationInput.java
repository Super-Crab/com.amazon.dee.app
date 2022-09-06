package com.amazon.clouddrive.cdasdk.aps.message;

import com.amazon.alexa.tarazed.dmps.DMPSHandlerDefault;
import com.amazon.clouddrive.cdasdk.aps.common.APSInput;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.annotation.JsonProperty;
/* loaded from: classes11.dex */
public class SendNotificationInput extends APSInput {
    @JsonProperty("address")
    private String address;
    @JsonProperty(DMPSHandlerDefault.SET_PERMISSION_PAYLOAD_NOTIFICATION_TYPE_KEY)
    private NotificationType notificationType;

    @Override // com.amazon.clouddrive.cdasdk.aps.common.APSInput
    protected boolean canEqual(Object obj) {
        return obj instanceof SendNotificationInput;
    }

    @Override // com.amazon.clouddrive.cdasdk.aps.common.APSInput
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SendNotificationInput)) {
            return false;
        }
        SendNotificationInput sendNotificationInput = (SendNotificationInput) obj;
        if (!sendNotificationInput.canEqual(this) || !super.equals(obj)) {
            return false;
        }
        String address = getAddress();
        String address2 = sendNotificationInput.getAddress();
        if (address != null ? !address.equals(address2) : address2 != null) {
            return false;
        }
        NotificationType notificationType = getNotificationType();
        NotificationType notificationType2 = sendNotificationInput.getNotificationType();
        return notificationType != null ? notificationType.equals(notificationType2) : notificationType2 == null;
    }

    public String getAddress() {
        return this.address;
    }

    public NotificationType getNotificationType() {
        return this.notificationType;
    }

    @Override // com.amazon.clouddrive.cdasdk.aps.common.APSInput
    public int hashCode() {
        int hashCode = super.hashCode();
        String address = getAddress();
        int i = 43;
        int hashCode2 = (hashCode * 59) + (address == null ? 43 : address.hashCode());
        NotificationType notificationType = getNotificationType();
        int i2 = hashCode2 * 59;
        if (notificationType != null) {
            i = notificationType.hashCode();
        }
        return i2 + i;
    }

    @JsonProperty("address")
    public void setAddress(String str) {
        this.address = str;
    }

    @JsonProperty(DMPSHandlerDefault.SET_PERMISSION_PAYLOAD_NOTIFICATION_TYPE_KEY)
    public void setNotificationType(NotificationType notificationType) {
        this.notificationType = notificationType;
    }

    @Override // com.amazon.clouddrive.cdasdk.aps.common.APSInput
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("SendNotificationInput(address=");
        outline107.append(getAddress());
        outline107.append(", notificationType=");
        outline107.append(getNotificationType());
        outline107.append(")");
        return outline107.toString();
    }
}
