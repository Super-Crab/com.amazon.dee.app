package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class AuditTaskMetadata implements Serializable {
    private String taskId;
    private String taskStatus;
    private String taskType;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof AuditTaskMetadata)) {
            return false;
        }
        AuditTaskMetadata auditTaskMetadata = (AuditTaskMetadata) obj;
        if ((auditTaskMetadata.getTaskId() == null) ^ (getTaskId() == null)) {
            return false;
        }
        if (auditTaskMetadata.getTaskId() != null && !auditTaskMetadata.getTaskId().equals(getTaskId())) {
            return false;
        }
        if ((auditTaskMetadata.getTaskStatus() == null) ^ (getTaskStatus() == null)) {
            return false;
        }
        if (auditTaskMetadata.getTaskStatus() != null && !auditTaskMetadata.getTaskStatus().equals(getTaskStatus())) {
            return false;
        }
        if ((auditTaskMetadata.getTaskType() == null) ^ (getTaskType() == null)) {
            return false;
        }
        return auditTaskMetadata.getTaskType() == null || auditTaskMetadata.getTaskType().equals(getTaskType());
    }

    public String getTaskId() {
        return this.taskId;
    }

    public String getTaskStatus() {
        return this.taskStatus;
    }

    public String getTaskType() {
        return this.taskType;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((getTaskId() == null ? 0 : getTaskId().hashCode()) + 31) * 31) + (getTaskStatus() == null ? 0 : getTaskStatus().hashCode())) * 31;
        if (getTaskType() != null) {
            i = getTaskType().hashCode();
        }
        return hashCode + i;
    }

    public void setTaskId(String str) {
        this.taskId = str;
    }

    public void setTaskStatus(String str) {
        this.taskStatus = str;
    }

    public void setTaskType(String str) {
        this.taskType = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getTaskId() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("taskId: ");
            outline1072.append(getTaskId());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getTaskStatus() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("taskStatus: ");
            outline1073.append(getTaskStatus());
            outline1073.append(",");
            outline107.append(outline1073.toString());
        }
        if (getTaskType() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("taskType: ");
            outline1074.append(getTaskType());
            outline107.append(outline1074.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public AuditTaskMetadata withTaskId(String str) {
        this.taskId = str;
        return this;
    }

    public AuditTaskMetadata withTaskStatus(String str) {
        this.taskStatus = str;
        return this;
    }

    public AuditTaskMetadata withTaskType(String str) {
        this.taskType = str;
        return this;
    }

    public void setTaskStatus(AuditTaskStatus auditTaskStatus) {
        this.taskStatus = auditTaskStatus.toString();
    }

    public void setTaskType(AuditTaskType auditTaskType) {
        this.taskType = auditTaskType.toString();
    }

    public AuditTaskMetadata withTaskStatus(AuditTaskStatus auditTaskStatus) {
        this.taskStatus = auditTaskStatus.toString();
        return this;
    }

    public AuditTaskMetadata withTaskType(AuditTaskType auditTaskType) {
        this.taskType = auditTaskType.toString();
        return this;
    }
}
