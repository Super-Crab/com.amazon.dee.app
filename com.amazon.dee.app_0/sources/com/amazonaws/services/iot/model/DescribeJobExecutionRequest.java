package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazonaws.AmazonWebServiceRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class DescribeJobExecutionRequest extends AmazonWebServiceRequest implements Serializable {
    private Long executionNumber;
    private String jobId;
    private String thingName;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof DescribeJobExecutionRequest)) {
            return false;
        }
        DescribeJobExecutionRequest describeJobExecutionRequest = (DescribeJobExecutionRequest) obj;
        if ((describeJobExecutionRequest.getJobId() == null) ^ (getJobId() == null)) {
            return false;
        }
        if (describeJobExecutionRequest.getJobId() != null && !describeJobExecutionRequest.getJobId().equals(getJobId())) {
            return false;
        }
        if ((describeJobExecutionRequest.getThingName() == null) ^ (getThingName() == null)) {
            return false;
        }
        if (describeJobExecutionRequest.getThingName() != null && !describeJobExecutionRequest.getThingName().equals(getThingName())) {
            return false;
        }
        if ((describeJobExecutionRequest.getExecutionNumber() == null) ^ (getExecutionNumber() == null)) {
            return false;
        }
        return describeJobExecutionRequest.getExecutionNumber() == null || describeJobExecutionRequest.getExecutionNumber().equals(getExecutionNumber());
    }

    public Long getExecutionNumber() {
        return this.executionNumber;
    }

    public String getJobId() {
        return this.jobId;
    }

    public String getThingName() {
        return this.thingName;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((getJobId() == null ? 0 : getJobId().hashCode()) + 31) * 31) + (getThingName() == null ? 0 : getThingName().hashCode())) * 31;
        if (getExecutionNumber() != null) {
            i = getExecutionNumber().hashCode();
        }
        return hashCode + i;
    }

    public void setExecutionNumber(Long l) {
        this.executionNumber = l;
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
            outline107.append(outline1074.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public DescribeJobExecutionRequest withExecutionNumber(Long l) {
        this.executionNumber = l;
        return this;
    }

    public DescribeJobExecutionRequest withJobId(String str) {
        this.jobId = str;
        return this;
    }

    public DescribeJobExecutionRequest withThingName(String str) {
        this.thingName = str;
        return this;
    }
}
