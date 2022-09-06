package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class StreamSummary implements Serializable {
    private String description;
    private String streamArn;
    private String streamId;
    private Integer streamVersion;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof StreamSummary)) {
            return false;
        }
        StreamSummary streamSummary = (StreamSummary) obj;
        if ((streamSummary.getStreamId() == null) ^ (getStreamId() == null)) {
            return false;
        }
        if (streamSummary.getStreamId() != null && !streamSummary.getStreamId().equals(getStreamId())) {
            return false;
        }
        if ((streamSummary.getStreamArn() == null) ^ (getStreamArn() == null)) {
            return false;
        }
        if (streamSummary.getStreamArn() != null && !streamSummary.getStreamArn().equals(getStreamArn())) {
            return false;
        }
        if ((streamSummary.getStreamVersion() == null) ^ (getStreamVersion() == null)) {
            return false;
        }
        if (streamSummary.getStreamVersion() != null && !streamSummary.getStreamVersion().equals(getStreamVersion())) {
            return false;
        }
        if ((streamSummary.getDescription() == null) ^ (getDescription() == null)) {
            return false;
        }
        return streamSummary.getDescription() == null || streamSummary.getDescription().equals(getDescription());
    }

    public String getDescription() {
        return this.description;
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
        int hashCode = ((((((getStreamId() == null ? 0 : getStreamId().hashCode()) + 31) * 31) + (getStreamArn() == null ? 0 : getStreamArn().hashCode())) * 31) + (getStreamVersion() == null ? 0 : getStreamVersion().hashCode())) * 31;
        if (getDescription() != null) {
            i = getDescription().hashCode();
        }
        return hashCode + i;
    }

    public void setDescription(String str) {
        this.description = str;
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
            outline107.append(outline1075.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public StreamSummary withDescription(String str) {
        this.description = str;
        return this;
    }

    public StreamSummary withStreamArn(String str) {
        this.streamArn = str;
        return this;
    }

    public StreamSummary withStreamId(String str) {
        this.streamId = str;
        return this;
    }

    public StreamSummary withStreamVersion(Integer num) {
        this.streamVersion = num;
        return this;
    }
}
