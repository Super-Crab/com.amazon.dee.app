package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes13.dex */
public class DescribeAuditTaskResult implements Serializable {
    private Map<String, AuditCheckDetails> auditDetails;
    private String scheduledAuditName;
    private Date taskStartTime;
    private TaskStatistics taskStatistics;
    private String taskStatus;
    private String taskType;

    public DescribeAuditTaskResult addauditDetailsEntry(String str, AuditCheckDetails auditCheckDetails) {
        if (this.auditDetails == null) {
            this.auditDetails = new HashMap();
        }
        if (!this.auditDetails.containsKey(str)) {
            this.auditDetails.put(str, auditCheckDetails);
            return this;
        }
        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline78(str, GeneratedOutlineSupport1.outline107("Duplicated keys ("), ") are provided."));
    }

    public DescribeAuditTaskResult clearauditDetailsEntries() {
        this.auditDetails = null;
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof DescribeAuditTaskResult)) {
            return false;
        }
        DescribeAuditTaskResult describeAuditTaskResult = (DescribeAuditTaskResult) obj;
        if ((describeAuditTaskResult.getTaskStatus() == null) ^ (getTaskStatus() == null)) {
            return false;
        }
        if (describeAuditTaskResult.getTaskStatus() != null && !describeAuditTaskResult.getTaskStatus().equals(getTaskStatus())) {
            return false;
        }
        if ((describeAuditTaskResult.getTaskType() == null) ^ (getTaskType() == null)) {
            return false;
        }
        if (describeAuditTaskResult.getTaskType() != null && !describeAuditTaskResult.getTaskType().equals(getTaskType())) {
            return false;
        }
        if ((describeAuditTaskResult.getTaskStartTime() == null) ^ (getTaskStartTime() == null)) {
            return false;
        }
        if (describeAuditTaskResult.getTaskStartTime() != null && !describeAuditTaskResult.getTaskStartTime().equals(getTaskStartTime())) {
            return false;
        }
        if ((describeAuditTaskResult.getTaskStatistics() == null) ^ (getTaskStatistics() == null)) {
            return false;
        }
        if (describeAuditTaskResult.getTaskStatistics() != null && !describeAuditTaskResult.getTaskStatistics().equals(getTaskStatistics())) {
            return false;
        }
        if ((describeAuditTaskResult.getScheduledAuditName() == null) ^ (getScheduledAuditName() == null)) {
            return false;
        }
        if (describeAuditTaskResult.getScheduledAuditName() != null && !describeAuditTaskResult.getScheduledAuditName().equals(getScheduledAuditName())) {
            return false;
        }
        if ((describeAuditTaskResult.getAuditDetails() == null) ^ (getAuditDetails() == null)) {
            return false;
        }
        return describeAuditTaskResult.getAuditDetails() == null || describeAuditTaskResult.getAuditDetails().equals(getAuditDetails());
    }

    public Map<String, AuditCheckDetails> getAuditDetails() {
        return this.auditDetails;
    }

    public String getScheduledAuditName() {
        return this.scheduledAuditName;
    }

    public Date getTaskStartTime() {
        return this.taskStartTime;
    }

    public TaskStatistics getTaskStatistics() {
        return this.taskStatistics;
    }

    public String getTaskStatus() {
        return this.taskStatus;
    }

    public String getTaskType() {
        return this.taskType;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((((((((getTaskStatus() == null ? 0 : getTaskStatus().hashCode()) + 31) * 31) + (getTaskType() == null ? 0 : getTaskType().hashCode())) * 31) + (getTaskStartTime() == null ? 0 : getTaskStartTime().hashCode())) * 31) + (getTaskStatistics() == null ? 0 : getTaskStatistics().hashCode())) * 31) + (getScheduledAuditName() == null ? 0 : getScheduledAuditName().hashCode())) * 31;
        if (getAuditDetails() != null) {
            i = getAuditDetails().hashCode();
        }
        return hashCode + i;
    }

    public void setAuditDetails(Map<String, AuditCheckDetails> map) {
        this.auditDetails = map;
    }

    public void setScheduledAuditName(String str) {
        this.scheduledAuditName = str;
    }

    public void setTaskStartTime(Date date) {
        this.taskStartTime = date;
    }

    public void setTaskStatistics(TaskStatistics taskStatistics) {
        this.taskStatistics = taskStatistics;
    }

    public void setTaskStatus(String str) {
        this.taskStatus = str;
    }

    public void setTaskType(String str) {
        this.taskType = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getTaskStatus() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("taskStatus: ");
            outline1072.append(getTaskStatus());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getTaskType() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("taskType: ");
            outline1073.append(getTaskType());
            outline1073.append(",");
            outline107.append(outline1073.toString());
        }
        if (getTaskStartTime() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("taskStartTime: ");
            outline1074.append(getTaskStartTime());
            outline1074.append(",");
            outline107.append(outline1074.toString());
        }
        if (getTaskStatistics() != null) {
            StringBuilder outline1075 = GeneratedOutlineSupport1.outline107("taskStatistics: ");
            outline1075.append(getTaskStatistics());
            outline1075.append(",");
            outline107.append(outline1075.toString());
        }
        if (getScheduledAuditName() != null) {
            StringBuilder outline1076 = GeneratedOutlineSupport1.outline107("scheduledAuditName: ");
            outline1076.append(getScheduledAuditName());
            outline1076.append(",");
            outline107.append(outline1076.toString());
        }
        if (getAuditDetails() != null) {
            StringBuilder outline1077 = GeneratedOutlineSupport1.outline107("auditDetails: ");
            outline1077.append(getAuditDetails());
            outline107.append(outline1077.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public DescribeAuditTaskResult withAuditDetails(Map<String, AuditCheckDetails> map) {
        this.auditDetails = map;
        return this;
    }

    public DescribeAuditTaskResult withScheduledAuditName(String str) {
        this.scheduledAuditName = str;
        return this;
    }

    public DescribeAuditTaskResult withTaskStartTime(Date date) {
        this.taskStartTime = date;
        return this;
    }

    public DescribeAuditTaskResult withTaskStatistics(TaskStatistics taskStatistics) {
        this.taskStatistics = taskStatistics;
        return this;
    }

    public DescribeAuditTaskResult withTaskStatus(String str) {
        this.taskStatus = str;
        return this;
    }

    public DescribeAuditTaskResult withTaskType(String str) {
        this.taskType = str;
        return this;
    }

    public void setTaskStatus(AuditTaskStatus auditTaskStatus) {
        this.taskStatus = auditTaskStatus.toString();
    }

    public void setTaskType(AuditTaskType auditTaskType) {
        this.taskType = auditTaskType.toString();
    }

    public DescribeAuditTaskResult withTaskStatus(AuditTaskStatus auditTaskStatus) {
        this.taskStatus = auditTaskStatus.toString();
        return this;
    }

    public DescribeAuditTaskResult withTaskType(AuditTaskType auditTaskType) {
        this.taskType = auditTaskType.toString();
        return this;
    }
}
