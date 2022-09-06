package com.amazonaws.services.logs.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazonaws.AmazonWebServiceRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class CreateExportTaskRequest extends AmazonWebServiceRequest implements Serializable {
    private String destination;
    private String destinationPrefix;
    private Long from;
    private String logGroupName;
    private String logStreamNamePrefix;
    private String taskName;
    private Long to;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof CreateExportTaskRequest)) {
            return false;
        }
        CreateExportTaskRequest createExportTaskRequest = (CreateExportTaskRequest) obj;
        if ((createExportTaskRequest.getTaskName() == null) ^ (getTaskName() == null)) {
            return false;
        }
        if (createExportTaskRequest.getTaskName() != null && !createExportTaskRequest.getTaskName().equals(getTaskName())) {
            return false;
        }
        if ((createExportTaskRequest.getLogGroupName() == null) ^ (getLogGroupName() == null)) {
            return false;
        }
        if (createExportTaskRequest.getLogGroupName() != null && !createExportTaskRequest.getLogGroupName().equals(getLogGroupName())) {
            return false;
        }
        if ((createExportTaskRequest.getLogStreamNamePrefix() == null) ^ (getLogStreamNamePrefix() == null)) {
            return false;
        }
        if (createExportTaskRequest.getLogStreamNamePrefix() != null && !createExportTaskRequest.getLogStreamNamePrefix().equals(getLogStreamNamePrefix())) {
            return false;
        }
        if ((createExportTaskRequest.getFrom() == null) ^ (getFrom() == null)) {
            return false;
        }
        if (createExportTaskRequest.getFrom() != null && !createExportTaskRequest.getFrom().equals(getFrom())) {
            return false;
        }
        if ((createExportTaskRequest.getTo() == null) ^ (getTo() == null)) {
            return false;
        }
        if (createExportTaskRequest.getTo() != null && !createExportTaskRequest.getTo().equals(getTo())) {
            return false;
        }
        if ((createExportTaskRequest.getDestination() == null) ^ (getDestination() == null)) {
            return false;
        }
        if (createExportTaskRequest.getDestination() != null && !createExportTaskRequest.getDestination().equals(getDestination())) {
            return false;
        }
        if ((createExportTaskRequest.getDestinationPrefix() == null) ^ (getDestinationPrefix() == null)) {
            return false;
        }
        return createExportTaskRequest.getDestinationPrefix() == null || createExportTaskRequest.getDestinationPrefix().equals(getDestinationPrefix());
    }

    public String getDestination() {
        return this.destination;
    }

    public String getDestinationPrefix() {
        return this.destinationPrefix;
    }

    public Long getFrom() {
        return this.from;
    }

    public String getLogGroupName() {
        return this.logGroupName;
    }

    public String getLogStreamNamePrefix() {
        return this.logStreamNamePrefix;
    }

    public String getTaskName() {
        return this.taskName;
    }

    public Long getTo() {
        return this.to;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((((((((((getTaskName() == null ? 0 : getTaskName().hashCode()) + 31) * 31) + (getLogGroupName() == null ? 0 : getLogGroupName().hashCode())) * 31) + (getLogStreamNamePrefix() == null ? 0 : getLogStreamNamePrefix().hashCode())) * 31) + (getFrom() == null ? 0 : getFrom().hashCode())) * 31) + (getTo() == null ? 0 : getTo().hashCode())) * 31) + (getDestination() == null ? 0 : getDestination().hashCode())) * 31;
        if (getDestinationPrefix() != null) {
            i = getDestinationPrefix().hashCode();
        }
        return hashCode + i;
    }

    public void setDestination(String str) {
        this.destination = str;
    }

    public void setDestinationPrefix(String str) {
        this.destinationPrefix = str;
    }

    public void setFrom(Long l) {
        this.from = l;
    }

    public void setLogGroupName(String str) {
        this.logGroupName = str;
    }

    public void setLogStreamNamePrefix(String str) {
        this.logStreamNamePrefix = str;
    }

    public void setTaskName(String str) {
        this.taskName = str;
    }

    public void setTo(Long l) {
        this.to = l;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getTaskName() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("taskName: ");
            outline1072.append(getTaskName());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getLogGroupName() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("logGroupName: ");
            outline1073.append(getLogGroupName());
            outline1073.append(",");
            outline107.append(outline1073.toString());
        }
        if (getLogStreamNamePrefix() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("logStreamNamePrefix: ");
            outline1074.append(getLogStreamNamePrefix());
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
            outline107.append(outline1078.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public CreateExportTaskRequest withDestination(String str) {
        this.destination = str;
        return this;
    }

    public CreateExportTaskRequest withDestinationPrefix(String str) {
        this.destinationPrefix = str;
        return this;
    }

    public CreateExportTaskRequest withFrom(Long l) {
        this.from = l;
        return this;
    }

    public CreateExportTaskRequest withLogGroupName(String str) {
        this.logGroupName = str;
        return this;
    }

    public CreateExportTaskRequest withLogStreamNamePrefix(String str) {
        this.logStreamNamePrefix = str;
        return this;
    }

    public CreateExportTaskRequest withTaskName(String str) {
        this.taskName = str;
        return this;
    }

    public CreateExportTaskRequest withTo(Long l) {
        this.to = l;
        return this;
    }
}
