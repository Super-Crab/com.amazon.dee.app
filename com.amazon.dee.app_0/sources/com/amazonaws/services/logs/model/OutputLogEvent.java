package com.amazonaws.services.logs.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class OutputLogEvent implements Serializable {
    private Long ingestionTime;
    private String message;
    private Long timestamp;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof OutputLogEvent)) {
            return false;
        }
        OutputLogEvent outputLogEvent = (OutputLogEvent) obj;
        if ((outputLogEvent.getTimestamp() == null) ^ (getTimestamp() == null)) {
            return false;
        }
        if (outputLogEvent.getTimestamp() != null && !outputLogEvent.getTimestamp().equals(getTimestamp())) {
            return false;
        }
        if ((outputLogEvent.getMessage() == null) ^ (getMessage() == null)) {
            return false;
        }
        if (outputLogEvent.getMessage() != null && !outputLogEvent.getMessage().equals(getMessage())) {
            return false;
        }
        if ((outputLogEvent.getIngestionTime() == null) ^ (getIngestionTime() == null)) {
            return false;
        }
        return outputLogEvent.getIngestionTime() == null || outputLogEvent.getIngestionTime().equals(getIngestionTime());
    }

    public Long getIngestionTime() {
        return this.ingestionTime;
    }

    public String getMessage() {
        return this.message;
    }

    public Long getTimestamp() {
        return this.timestamp;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((getTimestamp() == null ? 0 : getTimestamp().hashCode()) + 31) * 31) + (getMessage() == null ? 0 : getMessage().hashCode())) * 31;
        if (getIngestionTime() != null) {
            i = getIngestionTime().hashCode();
        }
        return hashCode + i;
    }

    public void setIngestionTime(Long l) {
        this.ingestionTime = l;
    }

    public void setMessage(String str) {
        this.message = str;
    }

    public void setTimestamp(Long l) {
        this.timestamp = l;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getTimestamp() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("timestamp: ");
            outline1072.append(getTimestamp());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getMessage() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("message: ");
            outline1073.append(getMessage());
            outline1073.append(",");
            outline107.append(outline1073.toString());
        }
        if (getIngestionTime() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("ingestionTime: ");
            outline1074.append(getIngestionTime());
            outline107.append(outline1074.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public OutputLogEvent withIngestionTime(Long l) {
        this.ingestionTime = l;
        return this;
    }

    public OutputLogEvent withMessage(String str) {
        this.message = str;
        return this;
    }

    public OutputLogEvent withTimestamp(Long l) {
        this.timestamp = l;
        return this;
    }
}
