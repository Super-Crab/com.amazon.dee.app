package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazonaws.AmazonWebServiceRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class DeleteJobExecutionRequest extends AmazonWebServiceRequest implements Serializable {
    private Long executionNumber;
    private Boolean force;
    private String jobId;
    private String thingName;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof DeleteJobExecutionRequest)) {
            return false;
        }
        DeleteJobExecutionRequest deleteJobExecutionRequest = (DeleteJobExecutionRequest) obj;
        if ((deleteJobExecutionRequest.getJobId() == null) ^ (getJobId() == null)) {
            return false;
        }
        if (deleteJobExecutionRequest.getJobId() != null && !deleteJobExecutionRequest.getJobId().equals(getJobId())) {
            return false;
        }
        if ((deleteJobExecutionRequest.getThingName() == null) ^ (getThingName() == null)) {
            return false;
        }
        if (deleteJobExecutionRequest.getThingName() != null && !deleteJobExecutionRequest.getThingName().equals(getThingName())) {
            return false;
        }
        if ((deleteJobExecutionRequest.getExecutionNumber() == null) ^ (getExecutionNumber() == null)) {
            return false;
        }
        if (deleteJobExecutionRequest.getExecutionNumber() != null && !deleteJobExecutionRequest.getExecutionNumber().equals(getExecutionNumber())) {
            return false;
        }
        if ((deleteJobExecutionRequest.getForce() == null) ^ (getForce() == null)) {
            return false;
        }
        return deleteJobExecutionRequest.getForce() == null || deleteJobExecutionRequest.getForce().equals(getForce());
    }

    public Long getExecutionNumber() {
        return this.executionNumber;
    }

    public Boolean getForce() {
        return this.force;
    }

    public String getJobId() {
        return this.jobId;
    }

    public String getThingName() {
        return this.thingName;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((((getJobId() == null ? 0 : getJobId().hashCode()) + 31) * 31) + (getThingName() == null ? 0 : getThingName().hashCode())) * 31) + (getExecutionNumber() == null ? 0 : getExecutionNumber().hashCode())) * 31;
        if (getForce() != null) {
            i = getForce().hashCode();
        }
        return hashCode + i;
    }

    public Boolean isForce() {
        return this.force;
    }

    public void setExecutionNumber(Long l) {
        this.executionNumber = l;
    }

    public void setForce(Boolean bool) {
        this.force = bool;
    }

    public void setJobId(String str) {
        this.jobId = str;
    }

    public void setThingName(String str) {
        this.thingName = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getJobId() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("jobId: ");
            outline1072.append(getJobId());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getThingName() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("thingName: ");
            outline1073.append(getThingName());
            outline1073.append(",");
            outline107.append(outline1073.toString());
        }
        if (getExecutionNumber() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("executionNumber: ");
            outline1074.append(getExecutionNumber());
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

    public DeleteJobExecutionRequest withExecutionNumber(Long l) {
        this.executionNumber = l;
        return this;
    }

    public DeleteJobExecutionRequest withForce(Boolean bool) {
        this.force = bool;
        return this;
    }

    public DeleteJobExecutionRequest withJobId(String str) {
        this.jobId = str;
        return this;
    }

    public DeleteJobExecutionRequest withThingName(String str) {
        this.thingName = str;
        return this;
    }
}
