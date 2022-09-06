package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazonaws.AmazonWebServiceRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes13.dex */
public class CancelJobExecutionRequest extends AmazonWebServiceRequest implements Serializable {
    private Long expectedVersion;
    private Boolean force;
    private String jobId;
    private Map<String, String> statusDetails;
    private String thingName;

    public CancelJobExecutionRequest addstatusDetailsEntry(String str, String str2) {
        if (this.statusDetails == null) {
            this.statusDetails = new HashMap();
        }
        if (!this.statusDetails.containsKey(str)) {
            this.statusDetails.put(str, str2);
            return this;
        }
        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline78(str, GeneratedOutlineSupport1.outline107("Duplicated keys ("), ") are provided."));
    }

    public CancelJobExecutionRequest clearstatusDetailsEntries() {
        this.statusDetails = null;
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof CancelJobExecutionRequest)) {
            return false;
        }
        CancelJobExecutionRequest cancelJobExecutionRequest = (CancelJobExecutionRequest) obj;
        if ((cancelJobExecutionRequest.getJobId() == null) ^ (getJobId() == null)) {
            return false;
        }
        if (cancelJobExecutionRequest.getJobId() != null && !cancelJobExecutionRequest.getJobId().equals(getJobId())) {
            return false;
        }
        if ((cancelJobExecutionRequest.getThingName() == null) ^ (getThingName() == null)) {
            return false;
        }
        if (cancelJobExecutionRequest.getThingName() != null && !cancelJobExecutionRequest.getThingName().equals(getThingName())) {
            return false;
        }
        if ((cancelJobExecutionRequest.getForce() == null) ^ (getForce() == null)) {
            return false;
        }
        if (cancelJobExecutionRequest.getForce() != null && !cancelJobExecutionRequest.getForce().equals(getForce())) {
            return false;
        }
        if ((cancelJobExecutionRequest.getExpectedVersion() == null) ^ (getExpectedVersion() == null)) {
            return false;
        }
        if (cancelJobExecutionRequest.getExpectedVersion() != null && !cancelJobExecutionRequest.getExpectedVersion().equals(getExpectedVersion())) {
            return false;
        }
        if ((cancelJobExecutionRequest.getStatusDetails() == null) ^ (getStatusDetails() == null)) {
            return false;
        }
        return cancelJobExecutionRequest.getStatusDetails() == null || cancelJobExecutionRequest.getStatusDetails().equals(getStatusDetails());
    }

    public Long getExpectedVersion() {
        return this.expectedVersion;
    }

    public Boolean getForce() {
        return this.force;
    }

    public String getJobId() {
        return this.jobId;
    }

    public Map<String, String> getStatusDetails() {
        return this.statusDetails;
    }

    public String getThingName() {
        return this.thingName;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((((((getJobId() == null ? 0 : getJobId().hashCode()) + 31) * 31) + (getThingName() == null ? 0 : getThingName().hashCode())) * 31) + (getForce() == null ? 0 : getForce().hashCode())) * 31) + (getExpectedVersion() == null ? 0 : getExpectedVersion().hashCode())) * 31;
        if (getStatusDetails() != null) {
            i = getStatusDetails().hashCode();
        }
        return hashCode + i;
    }

    public Boolean isForce() {
        return this.force;
    }

    public void setExpectedVersion(Long l) {
        this.expectedVersion = l;
    }

    public void setForce(Boolean bool) {
        this.force = bool;
    }

    public void setJobId(String str) {
        this.jobId = str;
    }

    public void setStatusDetails(Map<String, String> map) {
        this.statusDetails = map;
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
        if (getForce() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("force: ");
            outline1074.append(getForce());
            outline1074.append(",");
            outline107.append(outline1074.toString());
        }
        if (getExpectedVersion() != null) {
            StringBuilder outline1075 = GeneratedOutlineSupport1.outline107("expectedVersion: ");
            outline1075.append(getExpectedVersion());
            outline1075.append(",");
            outline107.append(outline1075.toString());
        }
        if (getStatusDetails() != null) {
            StringBuilder outline1076 = GeneratedOutlineSupport1.outline107("statusDetails: ");
            outline1076.append(getStatusDetails());
            outline107.append(outline1076.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public CancelJobExecutionRequest withExpectedVersion(Long l) {
        this.expectedVersion = l;
        return this;
    }

    public CancelJobExecutionRequest withForce(Boolean bool) {
        this.force = bool;
        return this;
    }

    public CancelJobExecutionRequest withJobId(String str) {
        this.jobId = str;
        return this;
    }

    public CancelJobExecutionRequest withStatusDetails(Map<String, String> map) {
        this.statusDetails = map;
        return this;
    }

    public CancelJobExecutionRequest withThingName(String str) {
        this.thingName = str;
        return this;
    }
}
