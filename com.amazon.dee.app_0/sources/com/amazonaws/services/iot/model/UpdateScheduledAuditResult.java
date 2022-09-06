package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class UpdateScheduledAuditResult implements Serializable {
    private String scheduledAuditArn;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof UpdateScheduledAuditResult)) {
            return false;
        }
        UpdateScheduledAuditResult updateScheduledAuditResult = (UpdateScheduledAuditResult) obj;
        if ((updateScheduledAuditResult.getScheduledAuditArn() == null) ^ (getScheduledAuditArn() == null)) {
            return false;
        }
        return updateScheduledAuditResult.getScheduledAuditArn() == null || updateScheduledAuditResult.getScheduledAuditArn().equals(getScheduledAuditArn());
    }

    public String getScheduledAuditArn() {
        return this.scheduledAuditArn;
    }

    public int hashCode() {
        return 31 + (getScheduledAuditArn() == null ? 0 : getScheduledAuditArn().hashCode());
    }

    public void setScheduledAuditArn(String str) {
        this.scheduledAuditArn = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getScheduledAuditArn() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("scheduledAuditArn: ");
            outline1072.append(getScheduledAuditArn());
            outline107.append(outline1072.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public UpdateScheduledAuditResult withScheduledAuditArn(String str) {
        this.scheduledAuditArn = str;
        return this;
    }
}
