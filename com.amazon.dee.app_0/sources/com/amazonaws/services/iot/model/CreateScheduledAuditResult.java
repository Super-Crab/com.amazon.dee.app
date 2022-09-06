package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class CreateScheduledAuditResult implements Serializable {
    private String scheduledAuditArn;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof CreateScheduledAuditResult)) {
            return false;
        }
        CreateScheduledAuditResult createScheduledAuditResult = (CreateScheduledAuditResult) obj;
        if ((createScheduledAuditResult.getScheduledAuditArn() == null) ^ (getScheduledAuditArn() == null)) {
            return false;
        }
        return createScheduledAuditResult.getScheduledAuditArn() == null || createScheduledAuditResult.getScheduledAuditArn().equals(getScheduledAuditArn());
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

    public CreateScheduledAuditResult withScheduledAuditArn(String str) {
        this.scheduledAuditArn = str;
        return this;
    }
}
