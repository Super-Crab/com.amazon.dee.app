package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazonaws.AmazonWebServiceRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import java.util.Date;
/* loaded from: classes13.dex */
public class ListAuditFindingsRequest extends AmazonWebServiceRequest implements Serializable {
    private String checkName;
    private Date endTime;
    private Integer maxResults;
    private String nextToken;
    private ResourceIdentifier resourceIdentifier;
    private Date startTime;
    private String taskId;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ListAuditFindingsRequest)) {
            return false;
        }
        ListAuditFindingsRequest listAuditFindingsRequest = (ListAuditFindingsRequest) obj;
        if ((listAuditFindingsRequest.getTaskId() == null) ^ (getTaskId() == null)) {
            return false;
        }
        if (listAuditFindingsRequest.getTaskId() != null && !listAuditFindingsRequest.getTaskId().equals(getTaskId())) {
            return false;
        }
        if ((listAuditFindingsRequest.getCheckName() == null) ^ (getCheckName() == null)) {
            return false;
        }
        if (listAuditFindingsRequest.getCheckName() != null && !listAuditFindingsRequest.getCheckName().equals(getCheckName())) {
            return false;
        }
        if ((listAuditFindingsRequest.getResourceIdentifier() == null) ^ (getResourceIdentifier() == null)) {
            return false;
        }
        if (listAuditFindingsRequest.getResourceIdentifier() != null && !listAuditFindingsRequest.getResourceIdentifier().equals(getResourceIdentifier())) {
            return false;
        }
        if ((listAuditFindingsRequest.getMaxResults() == null) ^ (getMaxResults() == null)) {
            return false;
        }
        if (listAuditFindingsRequest.getMaxResults() != null && !listAuditFindingsRequest.getMaxResults().equals(getMaxResults())) {
            return false;
        }
        if ((listAuditFindingsRequest.getNextToken() == null) ^ (getNextToken() == null)) {
            return false;
        }
        if (listAuditFindingsRequest.getNextToken() != null && !listAuditFindingsRequest.getNextToken().equals(getNextToken())) {
            return false;
        }
        if ((listAuditFindingsRequest.getStartTime() == null) ^ (getStartTime() == null)) {
            return false;
        }
        if (listAuditFindingsRequest.getStartTime() != null && !listAuditFindingsRequest.getStartTime().equals(getStartTime())) {
            return false;
        }
        if ((listAuditFindingsRequest.getEndTime() == null) ^ (getEndTime() == null)) {
            return false;
        }
        return listAuditFindingsRequest.getEndTime() == null || listAuditFindingsRequest.getEndTime().equals(getEndTime());
    }

    public String getCheckName() {
        return this.checkName;
    }

    public Date getEndTime() {
        return this.endTime;
    }

    public Integer getMaxResults() {
        return this.maxResults;
    }

    public String getNextToken() {
        return this.nextToken;
    }

    public ResourceIdentifier getResourceIdentifier() {
        return this.resourceIdentifier;
    }

    public Date getStartTime() {
        return this.startTime;
    }

    public String getTaskId() {
        return this.taskId;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((((((((((getTaskId() == null ? 0 : getTaskId().hashCode()) + 31) * 31) + (getCheckName() == null ? 0 : getCheckName().hashCode())) * 31) + (getResourceIdentifier() == null ? 0 : getResourceIdentifier().hashCode())) * 31) + (getMaxResults() == null ? 0 : getMaxResults().hashCode())) * 31) + (getNextToken() == null ? 0 : getNextToken().hashCode())) * 31) + (getStartTime() == null ? 0 : getStartTime().hashCode())) * 31;
        if (getEndTime() != null) {
            i = getEndTime().hashCode();
        }
        return hashCode + i;
    }

    public void setCheckName(String str) {
        this.checkName = str;
    }

    public void setEndTime(Date date) {
        this.endTime = date;
    }

    public void setMaxResults(Integer num) {
        this.maxResults = num;
    }

    public void setNextToken(String str) {
        this.nextToken = str;
    }

    public void setResourceIdentifier(ResourceIdentifier resourceIdentifier) {
        this.resourceIdentifier = resourceIdentifier;
    }

    public void setStartTime(Date date) {
        this.startTime = date;
    }

    public void setTaskId(String str) {
        this.taskId = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getTaskId() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("taskId: ");
            outline1072.append(getTaskId());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getCheckName() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("checkName: ");
            outline1073.append(getCheckName());
            outline1073.append(",");
            outline107.append(outline1073.toString());
        }
        if (getResourceIdentifier() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("resourceIdentifier: ");
            outline1074.append(getResourceIdentifier());
            outline1074.append(",");
            outline107.append(outline1074.toString());
        }
        if (getMaxResults() != null) {
            StringBuilder outline1075 = GeneratedOutlineSupport1.outline107("maxResults: ");
            outline1075.append(getMaxResults());
            outline1075.append(",");
            outline107.append(outline1075.toString());
        }
        if (getNextToken() != null) {
            StringBuilder outline1076 = GeneratedOutlineSupport1.outline107("nextToken: ");
            outline1076.append(getNextToken());
            outline1076.append(",");
            outline107.append(outline1076.toString());
        }
        if (getStartTime() != null) {
            StringBuilder outline1077 = GeneratedOutlineSupport1.outline107("startTime: ");
            outline1077.append(getStartTime());
            outline1077.append(",");
            outline107.append(outline1077.toString());
        }
        if (getEndTime() != null) {
            StringBuilder outline1078 = GeneratedOutlineSupport1.outline107("endTime: ");
            outline1078.append(getEndTime());
            outline107.append(outline1078.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public ListAuditFindingsRequest withCheckName(String str) {
        this.checkName = str;
        return this;
    }

    public ListAuditFindingsRequest withEndTime(Date date) {
        this.endTime = date;
        return this;
    }

    public ListAuditFindingsRequest withMaxResults(Integer num) {
        this.maxResults = num;
        return this;
    }

    public ListAuditFindingsRequest withNextToken(String str) {
        this.nextToken = str;
        return this;
    }

    public ListAuditFindingsRequest withResourceIdentifier(ResourceIdentifier resourceIdentifier) {
        this.resourceIdentifier = resourceIdentifier;
        return this;
    }

    public ListAuditFindingsRequest withStartTime(Date date) {
        this.startTime = date;
        return this;
    }

    public ListAuditFindingsRequest withTaskId(String str) {
        this.taskId = str;
        return this;
    }
}
