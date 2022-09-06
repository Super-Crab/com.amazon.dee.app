package com.amazonaws.services.logs.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class LogStream implements Serializable {
    private String arn;
    private Long creationTime;
    private Long firstEventTimestamp;
    private Long lastEventTimestamp;
    private Long lastIngestionTime;
    private String logStreamName;
    private Long storedBytes;
    private String uploadSequenceToken;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof LogStream)) {
            return false;
        }
        LogStream logStream = (LogStream) obj;
        if ((logStream.getLogStreamName() == null) ^ (getLogStreamName() == null)) {
            return false;
        }
        if (logStream.getLogStreamName() != null && !logStream.getLogStreamName().equals(getLogStreamName())) {
            return false;
        }
        if ((logStream.getCreationTime() == null) ^ (getCreationTime() == null)) {
            return false;
        }
        if (logStream.getCreationTime() != null && !logStream.getCreationTime().equals(getCreationTime())) {
            return false;
        }
        if ((logStream.getFirstEventTimestamp() == null) ^ (getFirstEventTimestamp() == null)) {
            return false;
        }
        if (logStream.getFirstEventTimestamp() != null && !logStream.getFirstEventTimestamp().equals(getFirstEventTimestamp())) {
            return false;
        }
        if ((logStream.getLastEventTimestamp() == null) ^ (getLastEventTimestamp() == null)) {
            return false;
        }
        if (logStream.getLastEventTimestamp() != null && !logStream.getLastEventTimestamp().equals(getLastEventTimestamp())) {
            return false;
        }
        if ((logStream.getLastIngestionTime() == null) ^ (getLastIngestionTime() == null)) {
            return false;
        }
        if (logStream.getLastIngestionTime() != null && !logStream.getLastIngestionTime().equals(getLastIngestionTime())) {
            return false;
        }
        if ((logStream.getUploadSequenceToken() == null) ^ (getUploadSequenceToken() == null)) {
            return false;
        }
        if (logStream.getUploadSequenceToken() != null && !logStream.getUploadSequenceToken().equals(getUploadSequenceToken())) {
            return false;
        }
        if ((logStream.getArn() == null) ^ (getArn() == null)) {
            return false;
        }
        if (logStream.getArn() != null && !logStream.getArn().equals(getArn())) {
            return false;
        }
        if ((logStream.getStoredBytes() == null) ^ (getStoredBytes() == null)) {
            return false;
        }
        return logStream.getStoredBytes() == null || logStream.getStoredBytes().equals(getStoredBytes());
    }

    public String getArn() {
        return this.arn;
    }

    public Long getCreationTime() {
        return this.creationTime;
    }

    public Long getFirstEventTimestamp() {
        return this.firstEventTimestamp;
    }

    public Long getLastEventTimestamp() {
        return this.lastEventTimestamp;
    }

    public Long getLastIngestionTime() {
        return this.lastIngestionTime;
    }

    public String getLogStreamName() {
        return this.logStreamName;
    }

    public Long getStoredBytes() {
        return this.storedBytes;
    }

    public String getUploadSequenceToken() {
        return this.uploadSequenceToken;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((((((((((((getLogStreamName() == null ? 0 : getLogStreamName().hashCode()) + 31) * 31) + (getCreationTime() == null ? 0 : getCreationTime().hashCode())) * 31) + (getFirstEventTimestamp() == null ? 0 : getFirstEventTimestamp().hashCode())) * 31) + (getLastEventTimestamp() == null ? 0 : getLastEventTimestamp().hashCode())) * 31) + (getLastIngestionTime() == null ? 0 : getLastIngestionTime().hashCode())) * 31) + (getUploadSequenceToken() == null ? 0 : getUploadSequenceToken().hashCode())) * 31) + (getArn() == null ? 0 : getArn().hashCode())) * 31;
        if (getStoredBytes() != null) {
            i = getStoredBytes().hashCode();
        }
        return hashCode + i;
    }

    public void setArn(String str) {
        this.arn = str;
    }

    public void setCreationTime(Long l) {
        this.creationTime = l;
    }

    public void setFirstEventTimestamp(Long l) {
        this.firstEventTimestamp = l;
    }

    public void setLastEventTimestamp(Long l) {
        this.lastEventTimestamp = l;
    }

    public void setLastIngestionTime(Long l) {
        this.lastIngestionTime = l;
    }

    public void setLogStreamName(String str) {
        this.logStreamName = str;
    }

    public void setStoredBytes(Long l) {
        this.storedBytes = l;
    }

    public void setUploadSequenceToken(String str) {
        this.uploadSequenceToken = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getLogStreamName() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("logStreamName: ");
            outline1072.append(getLogStreamName());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getCreationTime() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("creationTime: ");
            outline1073.append(getCreationTime());
            outline1073.append(",");
            outline107.append(outline1073.toString());
        }
        if (getFirstEventTimestamp() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("firstEventTimestamp: ");
            outline1074.append(getFirstEventTimestamp());
            outline1074.append(",");
            outline107.append(outline1074.toString());
        }
        if (getLastEventTimestamp() != null) {
            StringBuilder outline1075 = GeneratedOutlineSupport1.outline107("lastEventTimestamp: ");
            outline1075.append(getLastEventTimestamp());
            outline1075.append(",");
            outline107.append(outline1075.toString());
        }
        if (getLastIngestionTime() != null) {
            StringBuilder outline1076 = GeneratedOutlineSupport1.outline107("lastIngestionTime: ");
            outline1076.append(getLastIngestionTime());
            outline1076.append(",");
            outline107.append(outline1076.toString());
        }
        if (getUploadSequenceToken() != null) {
            StringBuilder outline1077 = GeneratedOutlineSupport1.outline107("uploadSequenceToken: ");
            outline1077.append(getUploadSequenceToken());
            outline1077.append(",");
            outline107.append(outline1077.toString());
        }
        if (getArn() != null) {
            StringBuilder outline1078 = GeneratedOutlineSupport1.outline107("arn: ");
            outline1078.append(getArn());
            outline1078.append(",");
            outline107.append(outline1078.toString());
        }
        if (getStoredBytes() != null) {
            StringBuilder outline1079 = GeneratedOutlineSupport1.outline107("storedBytes: ");
            outline1079.append(getStoredBytes());
            outline107.append(outline1079.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public LogStream withArn(String str) {
        this.arn = str;
        return this;
    }

    public LogStream withCreationTime(Long l) {
        this.creationTime = l;
        return this;
    }

    public LogStream withFirstEventTimestamp(Long l) {
        this.firstEventTimestamp = l;
        return this;
    }

    public LogStream withLastEventTimestamp(Long l) {
        this.lastEventTimestamp = l;
        return this;
    }

    public LogStream withLastIngestionTime(Long l) {
        this.lastIngestionTime = l;
        return this;
    }

    public LogStream withLogStreamName(String str) {
        this.logStreamName = str;
        return this;
    }

    public LogStream withStoredBytes(Long l) {
        this.storedBytes = l;
        return this;
    }

    public LogStream withUploadSequenceToken(String str) {
        this.uploadSequenceToken = str;
        return this;
    }
}
