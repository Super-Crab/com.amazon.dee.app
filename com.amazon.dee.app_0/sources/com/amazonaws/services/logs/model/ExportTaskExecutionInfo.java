package com.amazonaws.services.logs.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class ExportTaskExecutionInfo implements Serializable {
    private Long completionTime;
    private Long creationTime;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ExportTaskExecutionInfo)) {
            return false;
        }
        ExportTaskExecutionInfo exportTaskExecutionInfo = (ExportTaskExecutionInfo) obj;
        if ((exportTaskExecutionInfo.getCreationTime() == null) ^ (getCreationTime() == null)) {
            return false;
        }
        if (exportTaskExecutionInfo.getCreationTime() != null && !exportTaskExecutionInfo.getCreationTime().equals(getCreationTime())) {
            return false;
        }
        if ((exportTaskExecutionInfo.getCompletionTime() == null) ^ (getCompletionTime() == null)) {
            return false;
        }
        return exportTaskExecutionInfo.getCompletionTime() == null || exportTaskExecutionInfo.getCompletionTime().equals(getCompletionTime());
    }

    public Long getCompletionTime() {
        return this.completionTime;
    }

    public Long getCreationTime() {
        return this.creationTime;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((getCreationTime() == null ? 0 : getCreationTime().hashCode()) + 31) * 31;
        if (getCompletionTime() != null) {
            i = getCompletionTime().hashCode();
        }
        return hashCode + i;
    }

    public void setCompletionTime(Long l) {
        this.completionTime = l;
    }

    public void setCreationTime(Long l) {
        this.creationTime = l;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getCreationTime() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("creationTime: ");
            outline1072.append(getCreationTime());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getCompletionTime() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("completionTime: ");
            outline1073.append(getCompletionTime());
            outline107.append(outline1073.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public ExportTaskExecutionInfo withCompletionTime(Long l) {
        this.completionTime = l;
        return this;
    }

    public ExportTaskExecutionInfo withCreationTime(Long l) {
        this.creationTime = l;
        return this;
    }
}
