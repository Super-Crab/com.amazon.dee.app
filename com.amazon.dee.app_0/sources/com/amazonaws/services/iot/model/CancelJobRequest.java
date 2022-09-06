package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazonaws.AmazonWebServiceRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class CancelJobRequest extends AmazonWebServiceRequest implements Serializable {
    private String comment;
    private Boolean force;
    private String jobId;
    private String reasonCode;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof CancelJobRequest)) {
            return false;
        }
        CancelJobRequest cancelJobRequest = (CancelJobRequest) obj;
        if ((cancelJobRequest.getJobId() == null) ^ (getJobId() == null)) {
            return false;
        }
        if (cancelJobRequest.getJobId() != null && !cancelJobRequest.getJobId().equals(getJobId())) {
            return false;
        }
        if ((cancelJobRequest.getReasonCode() == null) ^ (getReasonCode() == null)) {
            return false;
        }
        if (cancelJobRequest.getReasonCode() != null && !cancelJobRequest.getReasonCode().equals(getReasonCode())) {
            return false;
        }
        if ((cancelJobRequest.getComment() == null) ^ (getComment() == null)) {
            return false;
        }
        if (cancelJobRequest.getComment() != null && !cancelJobRequest.getComment().equals(getComment())) {
            return false;
        }
        if ((cancelJobRequest.getForce() == null) ^ (getForce() == null)) {
            return false;
        }
        return cancelJobRequest.getForce() == null || cancelJobRequest.getForce().equals(getForce());
    }

    public String getComment() {
        return this.comment;
    }

    public Boolean getForce() {
        return this.force;
    }

    public String getJobId() {
        return this.jobId;
    }

    public String getReasonCode() {
        return this.reasonCode;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((((getJobId() == null ? 0 : getJobId().hashCode()) + 31) * 31) + (getReasonCode() == null ? 0 : getReasonCode().hashCode())) * 31) + (getComment() == null ? 0 : getComment().hashCode())) * 31;
        if (getForce() != null) {
            i = getForce().hashCode();
        }
        return hashCode + i;
    }

    public Boolean isForce() {
        return this.force;
    }

    public void setComment(String str) {
        this.comment = str;
    }

    public void setForce(Boolean bool) {
        this.force = bool;
    }

    public void setJobId(String str) {
        this.jobId = str;
    }

    public void setReasonCode(String str) {
        this.reasonCode = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getJobId() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("jobId: ");
            outline1072.append(getJobId());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getReasonCode() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("reasonCode: ");
            outline1073.append(getReasonCode());
            outline1073.append(",");
            outline107.append(outline1073.toString());
        }
        if (getComment() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("comment: ");
            outline1074.append(getComment());
            outline1074.append(",");
            outline107.append(outline1074.toString());
        }
        if (getForce() != null) {
            StringBuilder outline1075 = GeneratedOutlineSupport1.outline107("force: ");
            outline1075.append(getForce());
            outline107.append(outline1075.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public CancelJobRequest withComment(String str) {
        this.comment = str;
        return this;
    }

    public CancelJobRequest withForce(Boolean bool) {
        this.force = bool;
        return this;
    }

    public CancelJobRequest withJobId(String str) {
        this.jobId = str;
        return this;
    }

    public CancelJobRequest withReasonCode(String str) {
        this.reasonCode = str;
        return this;
    }
}
