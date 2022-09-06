package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class StreamFile implements Serializable {
    private Integer fileId;
    private S3Location s3Location;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof StreamFile)) {
            return false;
        }
        StreamFile streamFile = (StreamFile) obj;
        if ((streamFile.getFileId() == null) ^ (getFileId() == null)) {
            return false;
        }
        if (streamFile.getFileId() != null && !streamFile.getFileId().equals(getFileId())) {
            return false;
        }
        if ((streamFile.getS3Location() == null) ^ (getS3Location() == null)) {
            return false;
        }
        return streamFile.getS3Location() == null || streamFile.getS3Location().equals(getS3Location());
    }

    public Integer getFileId() {
        return this.fileId;
    }

    public S3Location getS3Location() {
        return this.s3Location;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((getFileId() == null ? 0 : getFileId().hashCode()) + 31) * 31;
        if (getS3Location() != null) {
            i = getS3Location().hashCode();
        }
        return hashCode + i;
    }

    public void setFileId(Integer num) {
        this.fileId = num;
    }

    public void setS3Location(S3Location s3Location) {
        this.s3Location = s3Location;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getFileId() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("fileId: ");
            outline1072.append(getFileId());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getS3Location() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("s3Location: ");
            outline1073.append(getS3Location());
            outline107.append(outline1073.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public StreamFile withFileId(Integer num) {
        this.fileId = num;
        return this;
    }

    public StreamFile withS3Location(S3Location s3Location) {
        this.s3Location = s3Location;
        return this;
    }
}
