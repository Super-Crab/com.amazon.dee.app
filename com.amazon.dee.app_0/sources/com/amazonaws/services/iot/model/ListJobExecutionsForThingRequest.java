package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazonaws.AmazonWebServiceRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class ListJobExecutionsForThingRequest extends AmazonWebServiceRequest implements Serializable {
    private Integer maxResults;
    private String nextToken;
    private String status;
    private String thingName;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ListJobExecutionsForThingRequest)) {
            return false;
        }
        ListJobExecutionsForThingRequest listJobExecutionsForThingRequest = (ListJobExecutionsForThingRequest) obj;
        if ((listJobExecutionsForThingRequest.getThingName() == null) ^ (getThingName() == null)) {
            return false;
        }
        if (listJobExecutionsForThingRequest.getThingName() != null && !listJobExecutionsForThingRequest.getThingName().equals(getThingName())) {
            return false;
        }
        if ((listJobExecutionsForThingRequest.getStatus() == null) ^ (getStatus() == null)) {
            return false;
        }
        if (listJobExecutionsForThingRequest.getStatus() != null && !listJobExecutionsForThingRequest.getStatus().equals(getStatus())) {
            return false;
        }
        if ((listJobExecutionsForThingRequest.getMaxResults() == null) ^ (getMaxResults() == null)) {
            return false;
        }
        if (listJobExecutionsForThingRequest.getMaxResults() != null && !listJobExecutionsForThingRequest.getMaxResults().equals(getMaxResults())) {
            return false;
        }
        if ((listJobExecutionsForThingRequest.getNextToken() == null) ^ (getNextToken() == null)) {
            return false;
        }
        return listJobExecutionsForThingRequest.getNextToken() == null || listJobExecutionsForThingRequest.getNextToken().equals(getNextToken());
    }

    public Integer getMaxResults() {
        return this.maxResults;
    }

    public String getNextToken() {
        return this.nextToken;
    }

    public String getStatus() {
        return this.status;
    }

    public String getThingName() {
        return this.thingName;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((((getThingName() == null ? 0 : getThingName().hashCode()) + 31) * 31) + (getStatus() == null ? 0 : getStatus().hashCode())) * 31) + (getMaxResults() == null ? 0 : getMaxResults().hashCode())) * 31;
        if (getNextToken() != null) {
            i = getNextToken().hashCode();
        }
        return hashCode + i;
    }

    public void setMaxResults(Integer num) {
        this.maxResults = num;
    }

    public void setNextToken(String str) {
        this.nextToken = str;
    }

    public void setStatus(String str) {
        this.status = str;
    }

    public void setThingName(String str) {
        this.thingName = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getThingName() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("thingName: ");
            outline1072.append(getThingName());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getStatus() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("status: ");
            outline1073.append(getStatus());
            outline1073.append(",");
            outline107.append(outline1073.toString());
        }
        if (getMaxResults() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("maxResults: ");
            outline1074.append(getMaxResults());
            outline1074.append(",");
            outline107.append(outline1074.toString());
        }
        if (getNextToken() != null) {
            StringBuilder outline1075 = GeneratedOutlineSupport1.outline107("nextToken: ");
            outline1075.append(getNextToken());
            outline107.append(outline1075.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public ListJobExecutionsForThingRequest withMaxResults(Integer num) {
        this.maxResults = num;
        return this;
    }

    public ListJobExecutionsForThingRequest withNextToken(String str) {
        this.nextToken = str;
        return this;
    }

    public ListJobExecutionsForThingRequest withStatus(String str) {
        this.status = str;
        return this;
    }

    public ListJobExecutionsForThingRequest withThingName(String str) {
        this.thingName = str;
        return this;
    }

    public void setStatus(JobExecutionStatus jobExecutionStatus) {
        this.status = jobExecutionStatus.toString();
    }

    public ListJobExecutionsForThingRequest withStatus(JobExecutionStatus jobExecutionStatus) {
        this.status = jobExecutionStatus.toString();
        return this;
    }
}
