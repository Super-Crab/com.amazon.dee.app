package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazonaws.AmazonWebServiceRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class DescribeScheduledAuditRequest extends AmazonWebServiceRequest implements Serializable {
    private String scheduledAuditName;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof DescribeScheduledAuditRequest)) {
            return false;
        }
        DescribeScheduledAuditRequest describeScheduledAuditRequest = (DescribeScheduledAuditRequest) obj;
        if ((describeScheduledAuditRequest.getScheduledAuditName() == null) ^ (getScheduledAuditName() == null)) {
            return false;
        }
        return describeScheduledAuditRequest.getScheduledAuditName() == null || describeScheduledAuditRequest.getScheduledAuditName().equals(getScheduledAuditName());
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

    public DescribeScheduledAuditRequest withScheduledAuditName(String str) {
        this.scheduledAuditName = str;
        return this;
    }
}
