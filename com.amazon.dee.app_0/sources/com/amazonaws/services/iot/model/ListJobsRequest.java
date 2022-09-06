package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazonaws.AmazonWebServiceRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class ListJobsRequest extends AmazonWebServiceRequest implements Serializable {
    private Integer maxResults;
    private String nextToken;
    private String status;
    private String targetSelection;
    private String thingGroupId;
    private String thingGroupName;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ListJobsRequest)) {
            return false;
        }
        ListJobsRequest listJobsRequest = (ListJobsRequest) obj;
        if ((listJobsRequest.getStatus() == null) ^ (getStatus() == null)) {
            return false;
        }
        if (listJobsRequest.getStatus() != null && !listJobsRequest.getStatus().equals(getStatus())) {
            return false;
        }
        if ((listJobsRequest.getTargetSelection() == null) ^ (getTargetSelection() == null)) {
            return false;
        }
        if (listJobsRequest.getTargetSelection() != null && !listJobsRequest.getTargetSelection().equals(getTargetSelection())) {
            return false;
        }
        if ((listJobsRequest.getMaxResults() == null) ^ (getMaxResults() == null)) {
            return false;
        }
        if (listJobsRequest.getMaxResults() != null && !listJobsRequest.getMaxResults().equals(getMaxResults())) {
            return false;
        }
        if ((listJobsRequest.getNextToken() == null) ^ (getNextToken() == null)) {
            return false;
        }
        if (listJobsRequest.getNextToken() != null && !listJobsRequest.getNextToken().equals(getNextToken())) {
            return false;
        }
        if ((listJobsRequest.getThingGroupName() == null) ^ (getThingGroupName() == null)) {
            return false;
        }
        if (listJobsRequest.getThingGroupName() != null && !listJobsRequest.getThingGroupName().equals(getThingGroupName())) {
            return false;
        }
        if ((listJobsRequest.getThingGroupId() == null) ^ (getThingGroupId() == null)) {
            return false;
        }
        return listJobsRequest.getThingGroupId() == null || listJobsRequest.getThingGroupId().equals(getThingGroupId());
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

    public String getTargetSelection() {
        return this.targetSelection;
    }

    public String getThingGroupId() {
        return this.thingGroupId;
    }

    public String getThingGroupName() {
        return this.thingGroupName;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((((((((getStatus() == null ? 0 : getStatus().hashCode()) + 31) * 31) + (getTargetSelection() == null ? 0 : getTargetSelection().hashCode())) * 31) + (getMaxResults() == null ? 0 : getMaxResults().hashCode())) * 31) + (getNextToken() == null ? 0 : getNextToken().hashCode())) * 31) + (getThingGroupName() == null ? 0 : getThingGroupName().hashCode())) * 31;
        if (getThingGroupId() != null) {
            i = getThingGroupId().hashCode();
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

    public void setTargetSelection(String str) {
        this.targetSelection = str;
    }

    public void setThingGroupId(String str) {
        this.thingGroupId = str;
    }

    public void setThingGroupName(String str) {
        this.thingGroupName = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getStatus() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("status: ");
            outline1072.append(getStatus());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getTargetSelection() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("targetSelection: ");
            outline1073.append(getTargetSelection());
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
            outline1075.append(",");
            outline107.append(outline1075.toString());
        }
        if (getThingGroupName() != null) {
            StringBuilder outline1076 = GeneratedOutlineSupport1.outline107("thingGroupName: ");
            outline1076.append(getThingGroupName());
            outline1076.append(",");
            outline107.append(outline1076.toString());
        }
        if (getThingGroupId() != null) {
            StringBuilder outline1077 = GeneratedOutlineSupport1.outline107("thingGroupId: ");
            outline1077.append(getThingGroupId());
            outline107.append(outline1077.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public ListJobsRequest withMaxResults(Integer num) {
        this.maxResults = num;
        return this;
    }

    public ListJobsRequest withNextToken(String str) {
        this.nextToken = str;
        return this;
    }

    public ListJobsRequest withStatus(String str) {
        this.status = str;
        return this;
    }

    public ListJobsRequest withTargetSelection(String str) {
        this.targetSelection = str;
        return this;
    }

    public ListJobsRequest withThingGroupId(String str) {
        this.thingGroupId = str;
        return this;
    }

    public ListJobsRequest withThingGroupName(String str) {
        this.thingGroupName = str;
        return this;
    }

    public void setStatus(JobStatus jobStatus) {
        this.status = jobStatus.toString();
    }

    public void setTargetSelection(TargetSelection targetSelection) {
        this.targetSelection = targetSelection.toString();
    }

    public ListJobsRequest withStatus(JobStatus jobStatus) {
        this.status = jobStatus.toString();
        return this;
    }

    public ListJobsRequest withTargetSelection(TargetSelection targetSelection) {
        this.targetSelection = targetSelection.toString();
        return this;
    }
}
