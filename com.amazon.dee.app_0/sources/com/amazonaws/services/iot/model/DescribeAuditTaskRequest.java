package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazonaws.AmazonWebServiceRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class DescribeAuditTaskRequest extends AmazonWebServiceRequest implements Serializable {
    private String taskId;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof DescribeAuditTaskRequest)) {
            return false;
        }
        DescribeAuditTaskRequest describeAuditTaskRequest = (DescribeAuditTaskRequest) obj;
        if ((describeAuditTaskRequest.getTaskId() == null) ^ (getTaskId() == null)) {
            return false;
        }
        return describeAuditTaskRequest.getTaskId() == null || describeAuditTaskRequest.getTaskId().equals(getTaskId());
    }

    public String getTaskId() {
        return this.taskId;
    }

    public int hashCode() {
        return 31 + (getTaskId() == null ? 0 : getTaskId().hashCode());
    }

    public void setTaskId(String str) {
        this.taskId = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getTaskId() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("taskId: ");
            outline1072.append(getTaskId());
            outline107.append(outline1072.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public DescribeAuditTaskRequest withTaskId(String str) {
        this.taskId = str;
        return this;
    }
}
