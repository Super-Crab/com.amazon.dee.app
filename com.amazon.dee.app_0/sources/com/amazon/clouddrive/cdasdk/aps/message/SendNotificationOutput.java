package com.amazon.clouddrive.cdasdk.aps.message;

import com.amazon.clouddrive.cdasdk.aps.common.APSOutput;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.annotation.JsonProperty;
/* loaded from: classes11.dex */
public class SendNotificationOutput extends APSOutput {
    @JsonProperty("message")
    private String message;
    @JsonProperty("status")
    private String status;

    @Override // com.amazon.clouddrive.cdasdk.aps.common.APSOutput
    protected boolean canEqual(Object obj) {
        return obj instanceof SendNotificationOutput;
    }

    @Override // com.amazon.clouddrive.cdasdk.aps.common.APSOutput
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SendNotificationOutput)) {
            return false;
        }
        SendNotificationOutput sendNotificationOutput = (SendNotificationOutput) obj;
        if (!sendNotificationOutput.canEqual(this) || !super.equals(obj)) {
            return false;
        }
        String status = getStatus();
        String status2 = sendNotificationOutput.getStatus();
        if (status != null ? !status.equals(status2) : status2 != null) {
            return false;
        }
        String message = getMessage();
        String message2 = sendNotificationOutput.getMessage();
        return message != null ? message.equals(message2) : message2 == null;
    }

    public String getMessage() {
        return this.message;
    }

    public String getStatus() {
        return this.status;
    }

    @Override // com.amazon.clouddrive.cdasdk.aps.common.APSOutput
    public int hashCode() {
        int hashCode = super.hashCode();
        String status = getStatus();
        int i = 43;
        int hashCode2 = (hashCode * 59) + (status == null ? 43 : status.hashCode());
        String message = getMessage();
        int i2 = hashCode2 * 59;
        if (message != null) {
            i = message.hashCode();
        }
        return i2 + i;
    }

    @JsonProperty("message")
    public void setMessage(String str) {
        this.message = str;
    }

    @JsonProperty("status")
    public void setStatus(String str) {
        this.status = str;
    }

    @Override // com.amazon.clouddrive.cdasdk.aps.common.APSOutput
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("SendNotificationOutput(status=");
        outline107.append(getStatus());
        outline107.append(", message=");
        outline107.append(getMessage());
        outline107.append(")");
        return outline107.toString();
    }
}
