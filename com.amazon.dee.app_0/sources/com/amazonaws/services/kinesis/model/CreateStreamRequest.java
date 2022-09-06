package com.amazonaws.services.kinesis.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazonaws.AmazonWebServiceRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class CreateStreamRequest extends AmazonWebServiceRequest implements Serializable {
    private Integer shardCount;
    private String streamName;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof CreateStreamRequest)) {
            return false;
        }
        CreateStreamRequest createStreamRequest = (CreateStreamRequest) obj;
        if ((createStreamRequest.getStreamName() == null) ^ (getStreamName() == null)) {
            return false;
        }
        if (createStreamRequest.getStreamName() != null && !createStreamRequest.getStreamName().equals(getStreamName())) {
            return false;
        }
        if ((createStreamRequest.getShardCount() == null) ^ (getShardCount() == null)) {
            return false;
        }
        return createStreamRequest.getShardCount() == null || createStreamRequest.getShardCount().equals(getShardCount());
    }

    public Integer getShardCount() {
        return this.shardCount;
    }

    public String getStreamName() {
        return this.streamName;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((getStreamName() == null ? 0 : getStreamName().hashCode()) + 31) * 31;
        if (getShardCount() != null) {
            i = getShardCount().hashCode();
        }
        return hashCode + i;
    }

    public void setShardCount(Integer num) {
        this.shardCount = num;
    }

    public void setStreamName(String str) {
        this.streamName = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getStreamName() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("StreamName: ");
            outline1072.append(getStreamName());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getShardCount() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("ShardCount: ");
            outline1073.append(getShardCount());
            outline107.append(outline1073.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public CreateStreamRequest withShardCount(Integer num) {
        this.shardCount = num;
        return this;
    }

    public CreateStreamRequest withStreamName(String str) {
        this.streamName = str;
        return this;
    }
}
