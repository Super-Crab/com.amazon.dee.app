package com.amazonaws.services.logs.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class ExportTask implements Serializable {
    private String destination;
    private String destinationPrefix;
    private ExportTaskExecutionInfo executionInfo;
    private Long from;
    private String logGroupName;
    private ExportTaskStatus status;
    private String taskId;
    private String taskName;
    private Long to;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ExportTask)) {
            return false;
        }
        ExportTask exportTask = (ExportTask) obj;
        if ((exportTask.getTaskId() == null) ^ (getTaskId() == null)) {
            return false;
        }
        if (exportTask.getTaskId() != null && !exportTask.getTaskId().equals(getTaskId())) {
            return false;
        }
        if ((exportTask.getTaskName() == null) ^ (getTaskName() == null)) {
            return false;
        }
        if (exportTask.getTaskName() != null && !exportTask.getTaskName().equals(getTaskName())) {
            return false;
        }
        if ((exportTask.getLogGroupName() == null) ^ (getLogGroupName() == null)) {
            return false;
        }
        if (exportTask.getLogGroupName() != null && !exportTask.getLogGroupName().equals(getLogGroupName())) {
            return false;
        }
        if ((exportTask.getFrom() == null) ^ (getFrom() == null)) {
            return false;
        }
        if (exportTask.getFrom() != null && !exportTask.getFrom().equals(getFrom())) {
            return false;
        }
        if ((exportTask.getTo() == null) ^ (getTo() == null)) {
            return false;
        }
        if (exportTask.getTo() != null && !exportTask.getTo().equals(getTo())) {
            return false;
        }
        if ((exportTask.getDestination() == null) ^ (getDestination() == null)) {
            return false;
        }
        if (exportTask.getDestination() != null && !exportTask.getDestination().equals(getDestination())) {
            return false;
        }
        if ((exportTask.getDestinationPrefix() == null) ^ (getDestinationPrefix() == null)) {
            return false;
        }
        if (exportTask.getDestinationPrefix() != null && !exportTask.getDestinationPrefix().equals(getDestinationPrefix())) {
            return false;
        }
        if ((exportTask.getStatus() == null) ^ (getStatus() == null)) {
            return false;
        }
        if (exportTask.getStatus() != null && !exportTask.getStatus().equals(getStatus())) {
            return false;
        }
        if ((exportTask.getExecutionInfo() == null) ^ (getExecutionInfo() == null)) {
            return false;
        }
        return exportTask.getExecutionInfo() == null || exportTask.getExecutionInfo().equals(getExecutionInfo());
    }

    public String getDestination() {
        return this.destination;
    }

    public String getDestinationPrefix() {
        return this.destinationPrefix;
    }

    public ExportTaskExecutionInfo getExecutionInfo() {
        return this.executionInfo;
    }

    public Long getFrom() {
        return this.from;
    }

    public String getLogGroupName() {
        return this.logGroupName;
    }

    public ExportTaskStatus getStatus() {
        return this.status;
    }

    public String getTaskId() {
        return this.taskId;
    }

    public String getTaskName() {
        return this.taskName;
    }

    public Long getTo() {
        return this.to;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((((((((((((((getTaskId() == null ? 0 : getTaskId().hashCode()) + 31) * 31) + (getTaskName() == null ? 0 : getTaskName().hashCode())) * 31) + (getLogGroupName() == null ? 0 : getLogGroupName().hashCode())) * 31) + (getFrom() == null ? 0 : getFrom().hashCode())) * 31) + (getTo() == null ? 0 : getTo().hashCode())) * 31) + (getDestination() == null ? 0 : getDestination().hashCode())) * 31) + (getDestinationPrefix() == null ? 0 : getDestinationPrefix().hashCode())) * 31) + (getStatus() == null ? 0 : getStatus().hashCode())) * 31;
        if (getExecutionInfo() != null) {
            i = getExecutionInfo().hashCode();
        }
        return hashCode + i;
    }

    public void setDestination(String str) {
        this.destination = str;
    }

    public void setDestinationPrefix(String str) {
        this.destinationPrefix = str;
    }

    public void setExecutionInfo(ExportTaskExecutionInfo exportTaskExecutionInfo) {
        this.executionInfo = exportTaskExecutionInfo;
    }

    public void setFrom(Long l) {
        this.from = l;
    }

    public void setLogGroupName(String str) {
        this.logGroupName = str;
    }

    public void setStatus(ExportTaskStatus exportTaskStatus) {
        this.status = exportTaskStatus;
    }

    public void setTaskId(String str) {
        this.taskId = str;
    }

    public void setTaskName(String str) {
        this.taskName = str;
    }

    public void setTo(Long l) {
        this.to = l;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getTaskId() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("taskId: ");
            outline1072.append(getTaskId());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getTaskName() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("taskName: ");
            outline1073.append(getTaskName());
            outline1073.append(",");
            outline107.append(outline1073.toString());
        }
        if (getLogGroupName() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("logGroupName: ");
            outline1074.append(getLogGroupName());
            outline1074.append(",");
            outline107.append(outline1074.toString());
        }
        if (getFrom() != null) {
            StringBuilder outline1075 = GeneratedOutlineSupport1.outline107("from: ");
            outline1075.append(getFrom());
            outline1075.append(",");
            outline107.append(outline1075.toString());
        }
        if (getTo() != null) {
            StringBuilder outline1076 = GeneratedOutlineSupport1.outline107("to: ");
            outline1076.append(getTo());
            outline1076.append(",");
            outline107.append(outline1076.toString());
        }
        if (getDestination() != null) {
            StringBuilder outline1077 = GeneratedOutlineSupport1.outline107("destination: ");
            outline1077.append(getDestination());
            outline1077.append(",");
            outline107.append(outline1077.toString());
        }
        if (getDestinationPrefix() != null) {
            StringBuilder outline1078 = GeneratedOutlineSupport1.outline107("destinationPrefix: ");
            outline1078.append(getDestinationPrefix());
            outline1078.append(",");
            outline107.append(outline1078.toString());
        }
        if (getStatus() != null) {
            StringBuilder outline1079 = GeneratedOutlineSupport1.outline107("status: ");
            outline1079.append(getStatus());
            outline1079.append(",");
            outline107.append(outline1079.toString());
        }
        if (getExecutionInfo() != null) {
            StringBuilder outline10710 = GeneratedOutlineSupport1.outline107("executionInfo: ");
            outline10710.append(getExecutionInfo());
            outline107.append(outline10710.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public ExportTask withDestination(String str) {
        this.destination = str;
        return this;
    }

    public ExportTask withDestinationPrefix(String str) {
        this.destinationPrefix = str;
        return this;
    }

    public ExportTask withExecutionInfo(ExportTaskExecutionInfo exportTaskExecutionInfo) {
        this.executionInfo = exportTaskExecutionInfo;
        return this;
    }

    public ExportTask withFrom(Long l) {
        this.from = l;
        return this;
    }

    public ExportTask withLogGroupName(String str) {
        this.logGroupName = str;
        return this;
    }

    public ExportTask withStatus(ExportTaskStatus exportTaskStatus) {
        this.status = exportTaskStatus;
        return this;
    }

    public ExportTask withTaskId(String str) {
        this.taskId = str;
        return this;
    }

    public ExportTask withTaskName(String str) {
        this.taskName = str;
        return this;
    }

    public ExportTask withTo(Long l) {
        this.to = l;
        return this;
    }
}
