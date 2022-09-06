package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class CreateStreamResult implements Serializable {
    private String description;
    private String streamArn;
    private String streamId;
    private Integer streamVersion;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof CreateStreamResult)) {
            return false;
        }
        CreateStreamResult createStreamResult = (CreateStreamResult) obj;
        if ((createStreamResult.getStreamId() == null) ^ (getStreamId() == null)) {
            return false;
        }
        if (createStreamResult.getStreamId() != null && !createStreamResult.getStreamId().equals(getStreamId())) {
            return false;
        }
        if ((createStreamResult.getStreamArn() == null) ^ (getStreamArn() == null)) {
            return false;
        }
        if (createStreamResult.getStreamArn() != null && !createStreamResult.getStreamArn().equals(getStreamArn())) {
            return false;
        }
        if ((createStreamResult.getDescription() == null) ^ (getDescription() == null)) {
            return false;
        }
        if (createStreamResult.getDescription() != null && !createStreamResult.getDescription().equals(getDescription())) {
            return false;
        }
        if ((createStreamResult.getStreamVersion() == null) ^ (getStreamVersion() == null)) {
            return false;
        }
        return createStreamResult.getStreamVersion() == null || createStreamResult.getStreamVersion().equals(getStreamVersion());
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
        int hashCode = ((((((getStreamId() == null ? 0 : getStreamId().hashCode()) + 31) * 31) + (getStreamArn() == null ? 0 : getStreamArn().hashCode())) * 31) + (getDescription() == null ? 0 : getDescription().hashCode())) * 31;
        if (getStreamVersion() != null) {
            i = getStreamVersion().hashCode();
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
        if (getDescription() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("description: ");
            outline1074.append(getDescription());
            outline1074.append(",");
            outline107.append(outline1074.toString());
        }
        if (getStreamVersion() != null) {
            StringBuilder outline1075 = GeneratedOutlineSupport1.outline107("streamVersion: ");
            outline1075.append(getStreamVersion());
            outline107.append(outline1075.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public CreateStreamResult withDescription(String str) {
        this.description = str;
        return this;
    }

    public CreateStreamResult withStreamArn(String str) {
        this.streamArn = str;
        return this;
    }

    public CreateStreamResult withStreamId(String str) {
        this.streamId = str;
        return this;
    }

    public CreateStreamResult withStreamVersion(Integer num) {
        this.streamVersion = num;
        return this;
    }
}
