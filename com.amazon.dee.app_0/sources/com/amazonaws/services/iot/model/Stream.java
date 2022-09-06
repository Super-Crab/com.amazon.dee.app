package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class Stream implements Serializable {
    private Integer fileId;
    private String streamId;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof Stream)) {
            return false;
        }
        Stream stream = (Stream) obj;
        if ((stream.getStreamId() == null) ^ (getStreamId() == null)) {
            return false;
        }
        if (stream.getStreamId() != null && !stream.getStreamId().equals(getStreamId())) {
            return false;
        }
        if ((stream.getFileId() == null) ^ (getFileId() == null)) {
            return false;
        }
        return stream.getFileId() == null || stream.getFileId().equals(getFileId());
    }

    public Integer getFileId() {
        return this.fileId;
    }

    public String getStreamId() {
        return this.streamId;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((getStreamId() == null ? 0 : getStreamId().hashCode()) + 31) * 31;
        if (getFileId() != null) {
            i = getFileId().hashCode();
        }
        return hashCode + i;
    }

    public void setFileId(Integer num) {
        this.fileId = num;
    }

    public void setStreamId(String str) {
        this.streamId = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getStreamId() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("streamId: ");
            outline1072.append(getStreamId());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getFileId() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("fileId: ");
            outline1073.append(getFileId());
            outline107.append(outline1073.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public Stream withFileId(Integer num) {
        this.fileId = num;
        return this;
    }

    public Stream withStreamId(String str) {
        this.streamId = str;
        return this;
    }
}
