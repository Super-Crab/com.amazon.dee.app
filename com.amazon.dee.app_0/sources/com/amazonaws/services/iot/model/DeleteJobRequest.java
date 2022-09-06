package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazonaws.AmazonWebServiceRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class DeleteJobRequest extends AmazonWebServiceRequest implements Serializable {
    private Boolean force;
    private String jobId;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof DeleteJobRequest)) {
            return false;
        }
        DeleteJobRequest deleteJobRequest = (DeleteJobRequest) obj;
        if ((deleteJobRequest.getJobId() == null) ^ (getJobId() == null)) {
            return false;
        }
        if (deleteJobRequest.getJobId() != null && !deleteJobRequest.getJobId().equals(getJobId())) {
            return false;
        }
        if ((deleteJobRequest.getForce() == null) ^ (getForce() == null)) {
            return false;
        }
        return deleteJobRequest.getForce() == null || deleteJobRequest.getForce().equals(getForce());
    }

    public Boolean getForce() {
        return this.force;
    }

    public String getJobId() {
        return this.jobId;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((getJobId() == null ? 0 : getJobId().hashCode()) + 31) * 31;
        if (getForce() != null) {
            i = getForce().hashCode();
        }
        return hashCode + i;
    }

    public Boolean isForce() {
        return this.force;
    }

    public void setForce(Boolean bool) {
        this.force = bool;
    }

    public void setJobId(String str) {
        this.jobId = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getJobId() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("jobId: ");
            outline1072.append(getJobId());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getForce() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("force: ");
            outline1073.append(getForce());
            outline107.append(outline1073.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public DeleteJobRequest withForce(Boolean bool) {
        this.force = bool;
        return this;
    }

    public DeleteJobRequest withJobId(String str) {
        this.jobId = str;
        return this;
    }
}
