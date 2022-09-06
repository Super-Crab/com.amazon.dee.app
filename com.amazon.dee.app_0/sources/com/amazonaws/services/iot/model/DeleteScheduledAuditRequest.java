package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazonaws.AmazonWebServiceRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class DeleteScheduledAuditRequest extends AmazonWebServiceRequest implements Serializable {
    private String scheduledAuditName;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof DeleteScheduledAuditRequest)) {
            return false;
        }
        DeleteScheduledAuditRequest deleteScheduledAuditRequest = (DeleteScheduledAuditRequest) obj;
        if ((deleteScheduledAuditRequest.getScheduledAuditName() == null) ^ (getScheduledAuditName() == null)) {
            return false;
        }
        return deleteScheduledAuditRequest.getScheduledAuditName() == null || deleteScheduledAuditRequest.getScheduledAuditName().equals(getScheduledAuditName());
    }

    public String getScheduledAuditName() {
        return this.scheduledAuditName;
    }

    public int hashCode() {
        return 31 + (getScheduledAuditName() == null ? 0 : getScheduledAuditName().hashCode());
    }

    public void setScheduledAuditName(String str) {
        this.scheduledAuditName = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getScheduledAuditName() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("scheduledAuditName: ");
            outline1072.append(getScheduledAuditName());
            outline107.append(outline1072.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public DeleteScheduledAuditRequest withScheduledAuditName(String str) {
        this.scheduledAuditName = str;
        return this;
    }
}
