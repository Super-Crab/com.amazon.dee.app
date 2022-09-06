package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazonaws.AmazonWebServiceRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import java.util.Date;
/* loaded from: classes13.dex */
public class ListAuditTasksRequest extends AmazonWebServiceRequest implements Serializable {
    private Date endTime;
    private Integer maxResults;
    private String nextToken;
    private Date startTime;
    private String taskStatus;
    private String taskType;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ListAuditTasksRequest)) {
            return false;
        }
        ListAuditTasksRequest listAuditTasksRequest = (ListAuditTasksRequest) obj;
        if ((listAuditTasksRequest.getStartTime() == null) ^ (getStartTime() == null)) {
            return false;
        }
        if (listAuditTasksRequest.getStartTime() != null && !listAuditTasksRequest.getStartTime().equals(getStartTime())) {
            return false;
        }
        if ((listAuditTasksRequest.getEndTime() == null) ^ (getEndTime() == null)) {
            return false;
        }
        if (listAuditTasksRequest.getEndTime() != null && !listAuditTasksRequest.getEndTime().equals(getEndTime())) {
            return false;
        }
        if ((listAuditTasksRequest.getTaskType() == null) ^ (getTaskType() == null)) {
            return false;
        }
        if (listAuditTasksRequest.getTaskType() != null && !listAuditTasksRequest.getTaskType().equals(getTaskType())) {
            return false;
        }
        if ((listAuditTasksRequest.getTaskStatus() == null) ^ (getTaskStatus() == null)) {
            return false;
        }
        if (listAuditTasksRequest.getTaskStatus() != null && !listAuditTasksRequest.getTaskStatus().equals(getTaskStatus())) {
            return false;
        }
        if ((listAuditTasksRequest.getNextToken() == null) ^ (getNextToken() == null)) {
            return false;
        }
        if (listAuditTasksRequest.getNextToken() != null && !listAuditTasksRequest.getNextToken().equals(getNextToken())) {
            return false;
        }
        if ((listAuditTasksRequest.getMaxResults() == null) ^ (getMaxResults() == null)) {
            return false;
        }
        return listAuditTasksRequest.getMaxResults() == null || listAuditTasksRequest.getMaxResults().equals(getMaxResults());
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

    public Date getStartTime() {
        return this.startTime;
    }

    public String getTaskStatus() {
        return this.taskStatus;
    }

    public String getTaskType() {
        return this.taskType;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((((((((getStartTime() == null ? 0 : getStartTime().hashCode()) + 31) * 31) + (getEndTime() == null ? 0 : getEndTime().hashCode())) * 31) + (getTaskType() == null ? 0 : getTaskType().hashCode())) * 31) + (getTaskStatus() == null ? 0 : getTaskStatus().hashCode())) * 31) + (getNextToken() == null ? 0 : getNextToken().hashCode())) * 31;
        if (getMaxResults() != null) {
            i = getMaxResults().hashCode();
        }
        return hashCode + i;
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

    public void setStartTime(Date date) {
        this.startTime = date;
    }

    public void setTaskStatus(String str) {
        this.taskStatus = str;
    }

    public void setTaskType(String str) {
        this.taskType = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getStartTime() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("startTime: ");
            outline1072.append(getStartTime());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getEndTime() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("endTime: ");
            outline1073.append(getEndTime());
            outline1073.append(",");
            outline107.append(outline1073.toString());
        }
        if (getTaskType() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("taskType: ");
            outline1074.append(getTaskType());
            outline1074.append(",");
            outline107.append(outline1074.toString());
        }
        if (getTaskStatus() != null) {
            StringBuilder outline1075 = GeneratedOutlineSupport1.outline107("taskStatus: ");
            outline1075.append(getTaskStatus());
            outline1075.append(",");
            outline107.append(outline1075.toString());
        }
        if (getNextToken() != null) {
            StringBuilder outline1076 = GeneratedOutlineSupport1.outline107("nextToken: ");
            outline1076.append(getNextToken());
            outline1076.append(",");
            outline107.append(outline1076.toString());
        }
        if (getMaxResults() != null) {
            StringBuilder outline1077 = GeneratedOutlineSupport1.outline107("maxResults: ");
            outline1077.append(getMaxResults());
            outline107.append(outline1077.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public ListAuditTasksRequest withEndTime(Date date) {
        this.endTime = date;
        return this;
    }

    public ListAuditTasksRequest withMaxResults(Integer num) {
        this.maxResults = num;
        return this;
    }

    public ListAuditTasksRequest withNextToken(String str) {
        this.nextToken = str;
        return this;
    }

    public ListAuditTasksRequest withStartTime(Date date) {
        this.startTime = date;
        return this;
    }

    public ListAuditTasksRequest withTaskStatus(String str) {
        this.taskStatus = str;
        return this;
    }

    public ListAuditTasksRequest withTaskType(String str) {
        this.taskType = str;
        return this;
    }

    public void setTaskStatus(AuditTaskStatus auditTaskStatus) {
        this.taskStatus = auditTaskStatus.toString();
    }

    public void setTaskType(AuditTaskType auditTaskType) {
        this.taskType = auditTaskType.toString();
    }

    public ListAuditTasksRequest withTaskStatus(AuditTaskStatus auditTaskStatus) {
        this.taskStatus = auditTaskStatus.toString();
        return this;
    }

    public ListAuditTasksRequest withTaskType(AuditTaskType auditTaskType) {
        this.taskType = auditTaskType.toString();
        return this;
    }
}
