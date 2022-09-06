package com.amazonaws.services.logs.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class FilteredLogEvent implements Serializable {
    private String eventId;
    private Long ingestionTime;
    private String logStreamName;
    private String message;
    private Long timestamp;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof FilteredLogEvent)) {
            return false;
        }
        FilteredLogEvent filteredLogEvent = (FilteredLogEvent) obj;
        if ((filteredLogEvent.getLogStreamName() == null) ^ (getLogStreamName() == null)) {
            return false;
        }
        if (filteredLogEvent.getLogStreamName() != null && !filteredLogEvent.getLogStreamName().equals(getLogStreamName())) {
            return false;
        }
        if ((filteredLogEvent.getTimestamp() == null) ^ (getTimestamp() == null)) {
            return false;
        }
        if (filteredLogEvent.getTimestamp() != null && !filteredLogEvent.getTimestamp().equals(getTimestamp())) {
            return false;
        }
        if ((filteredLogEvent.getMessage() == null) ^ (getMessage() == null)) {
            return false;
        }
        if (filteredLogEvent.getMessage() != null && !filteredLogEvent.getMessage().equals(getMessage())) {
            return false;
        }
        if ((filteredLogEvent.getIngestionTime() == null) ^ (getIngestionTime() == null)) {
            return false;
        }
        if (filteredLogEvent.getIngestionTime() != null && !filteredLogEvent.getIngestionTime().equals(getIngestionTime())) {
            return false;
        }
        if ((filteredLogEvent.getEventId() == null) ^ (getEventId() == null)) {
            return false;
        }
        return filteredLogEvent.getEventId() == null || filteredLogEvent.getEventId().equals(getEventId());
    }

    public String getEventId() {
        return this.eventId;
    }

    public Long getIngestionTime() {
        return this.ingestionTime;
    }

    public String getLogStreamName() {
        return this.logStreamName;
    }

    public String getMessage() {
        return this.message;
    }

    public Long getTimestamp() {
        return this.timestamp;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((((((getLogStreamName() == null ? 0 : getLogStreamName().hashCode()) + 31) * 31) + (getTimestamp() == null ? 0 : getTimestamp().hashCode())) * 31) + (getMessage() == null ? 0 : getMessage().hashCode())) * 31) + (getIngestionTime() == null ? 0 : getIngestionTime().hashCode())) * 31;
        if (getEventId() != null) {
            i = getEventId().hashCode();
        }
        return hashCode + i;
    }

    public void setEventId(String str) {
        this.eventId = str;
    }

    public void setIngestionTime(Long l) {
        this.ingestionTime = l;
    }

    public void setLogStreamName(String str) {
        this.logStreamName = str;
    }

    public void setMessage(String str) {
        this.message = str;
    }

    public void setTimestamp(Long l) {
        this.timestamp = l;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getLogStreamName() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("logStreamName: ");
            outline1072.append(getLogStreamName());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getTimestamp() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("timestamp: ");
            outline1073.append(getTimestamp());
            outline1073.append(",");
            outline107.append(outline1073.toString());
        }
        if (getMessage() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("message: ");
            outline1074.append(getMessage());
            outline1074.append(",");
            outline107.append(outline1074.toString());
        }
        if (getIngestionTime() != null) {
            StringBuilder outline1075 = GeneratedOutlineSupport1.outline107("ingestionTime: ");
            outline1075.append(getIngestionTime());
            outline1075.append(",");
            outline107.append(outline1075.toString());
        }
        if (getEventId() != null) {
            StringBuilder outline1076 = GeneratedOutlineSupport1.outline107("eventId: ");
            outline1076.append(getEventId());
            outline107.append(outline1076.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public FilteredLogEvent withEventId(String str) {
        this.eventId = str;
        return this;
    }

    public FilteredLogEvent withIngestionTime(Long l) {
        this.ingestionTime = l;
        return this;
    }

    public FilteredLogEvent withLogStreamName(String str) {
        this.logStreamName = str;
        return this;
    }

    public FilteredLogEvent withMessage(String str) {
        this.message = str;
        return this;
    }

    public FilteredLogEvent withTimestamp(Long l) {
        this.timestamp = l;
        return this;
    }
}
