package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
/* loaded from: classes13.dex */
public class StreamInfo implements Serializable {
    private Date createdAt;
    private String description;
    private List<StreamFile> files;
    private Date lastUpdatedAt;
    private String roleArn;
    private String streamArn;
    private String streamId;
    private Integer streamVersion;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof StreamInfo)) {
            return false;
        }
        StreamInfo streamInfo = (StreamInfo) obj;
        if ((streamInfo.getStreamId() == null) ^ (getStreamId() == null)) {
            return false;
        }
        if (streamInfo.getStreamId() != null && !streamInfo.getStreamId().equals(getStreamId())) {
            return false;
        }
        if ((streamInfo.getStreamArn() == null) ^ (getStreamArn() == null)) {
            return false;
        }
        if (streamInfo.getStreamArn() != null && !streamInfo.getStreamArn().equals(getStreamArn())) {
            return false;
        }
        if ((streamInfo.getStreamVersion() == null) ^ (getStreamVersion() == null)) {
            return false;
        }
        if (streamInfo.getStreamVersion() != null && !streamInfo.getStreamVersion().equals(getStreamVersion())) {
            return false;
        }
        if ((streamInfo.getDescription() == null) ^ (getDescription() == null)) {
            return false;
        }
        if (streamInfo.getDescription() != null && !streamInfo.getDescription().equals(getDescription())) {
            return false;
        }
        if ((streamInfo.getFiles() == null) ^ (getFiles() == null)) {
            return false;
        }
        if (streamInfo.getFiles() != null && !streamInfo.getFiles().equals(getFiles())) {
            return false;
        }
        if ((streamInfo.getCreatedAt() == null) ^ (getCreatedAt() == null)) {
            return false;
        }
        if (streamInfo.getCreatedAt() != null && !streamInfo.getCreatedAt().equals(getCreatedAt())) {
            return false;
        }
        if ((streamInfo.getLastUpdatedAt() == null) ^ (getLastUpdatedAt() == null)) {
            return false;
        }
        if (streamInfo.getLastUpdatedAt() != null && !streamInfo.getLastUpdatedAt().equals(getLastUpdatedAt())) {
            return false;
        }
        if ((streamInfo.getRoleArn() == null) ^ (getRoleArn() == null)) {
            return false;
        }
        return streamInfo.getRoleArn() == null || streamInfo.getRoleArn().equals(getRoleArn());
    }

    public Date getCreatedAt() {
        return this.createdAt;
    }

    public String getDescription() {
        return this.description;
    }

    public List<StreamFile> getFiles() {
        return this.files;
    }

    public Date getLastUpdatedAt() {
        return this.lastUpdatedAt;
    }

    public String getRoleArn() {
        return this.roleArn;
    }

    public String getStreamArn() {
        return this.streamArn;
    }

    public String getStreamId() {
        return this.streamId;
    }

    public Integer getStreamVersion() {
        return this.streamVersion;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((((((((((((getStreamId() == null ? 0 : getStreamId().hashCode()) + 31) * 31) + (getStreamArn() == null ? 0 : getStreamArn().hashCode())) * 31) + (getStreamVersion() == null ? 0 : getStreamVersion().hashCode())) * 31) + (getDescription() == null ? 0 : getDescription().hashCode())) * 31) + (getFiles() == null ? 0 : getFiles().hashCode())) * 31) + (getCreatedAt() == null ? 0 : getCreatedAt().hashCode())) * 31) + (getLastUpdatedAt() == null ? 0 : getLastUpdatedAt().hashCode())) * 31;
        if (getRoleArn() != null) {
            i = getRoleArn().hashCode();
        }
        return hashCode + i;
    }

    public void setCreatedAt(Date date) {
        this.createdAt = date;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public void setFiles(Collection<StreamFile> collection) {
        if (collection == null) {
            this.files = null;
        } else {
            this.files = new ArrayList(collection);
        }
    }

    public void setLastUpdatedAt(Date date) {
        this.lastUpdatedAt = date;
    }

    public void setRoleArn(String str) {
        this.roleArn = str;
    }

    public void setStreamArn(String str) {
        this.streamArn = str;
    }

    public void setStreamId(String str) {
        this.streamId = str;
    }

    public void setStreamVersion(Integer num) {
        this.streamVersion = num;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getStreamId() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("streamId: ");
            outline1072.append(getStreamId());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getStreamArn() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("streamArn: ");
            outline1073.append(getStreamArn());
            outline1073.append(",");
            outline107.append(outline1073.toString());
        }
        if (getStreamVersion() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("streamVersion: ");
            outline1074.append(getStreamVersion());
            outline1074.append(",");
            outline107.append(outline1074.toString());
        }
        if (getDescription() != null) {
            StringBuilder outline1075 = GeneratedOutlineSupport1.outline107("description: ");
            outline1075.append(getDescription());
            outline1075.append(",");
            outline107.append(outline1075.toString());
        }
        if (getFiles() != null) {
            StringBuilder outline1076 = GeneratedOutlineSupport1.outline107("files: ");
            outline1076.append(getFiles());
            outline1076.append(",");
            outline107.append(outline1076.toString());
        }
        if (getCreatedAt() != null) {
            StringBuilder outline1077 = GeneratedOutlineSupport1.outline107("createdAt: ");
            outline1077.append(getCreatedAt());
            outline1077.append(",");
            outline107.append(outline1077.toString());
        }
        if (getLastUpdatedAt() != null) {
            StringBuilder outline1078 = GeneratedOutlineSupport1.outline107("lastUpdatedAt: ");
            outline1078.append(getLastUpdatedAt());
            outline1078.append(",");
            outline107.append(outline1078.toString());
        }
        if (getRoleArn() != null) {
            StringBuilder outline1079 = GeneratedOutlineSupport1.outline107("roleArn: ");
            outline1079.append(getRoleArn());
            outline107.append(outline1079.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public StreamInfo withCreatedAt(Date date) {
        this.createdAt = date;
        return this;
    }

    public StreamInfo withDescription(String str) {
        this.description = str;
        return this;
    }

    public StreamInfo withFiles(StreamFile... streamFileArr) {
        if (getFiles() == null) {
            this.files = new ArrayList(streamFileArr.length);
        }
        for (StreamFile streamFile : streamFileArr) {
            this.files.add(streamFile);
        }
        return this;
    }

    public StreamInfo withLastUpdatedAt(Date date) {
        this.lastUpdatedAt = date;
        return this;
    }

    public StreamInfo withRoleArn(String str) {
        this.roleArn = str;
        return this;
    }

    public StreamInfo withStreamArn(String str) {
        this.streamArn = str;
        return this;
    }

    public StreamInfo withStreamId(String str) {
        this.streamId = str;
        return this;
    }

    public StreamInfo withStreamVersion(Integer num) {
        this.streamVersion = num;
        return this;
    }

    public StreamInfo withFiles(Collection<StreamFile> collection) {
        setFiles(collection);
        return this;
    }
}
