package com.amazonaws.services.kinesis.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazonaws.AmazonWebServiceRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class DescribeStreamRequest extends AmazonWebServiceRequest implements Serializable {
    private String exclusiveStartShardId;
    private Integer limit;
    private String streamName;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof DescribeStreamRequest)) {
            return false;
        }
        DescribeStreamRequest describeStreamRequest = (DescribeStreamRequest) obj;
        if ((describeStreamRequest.getStreamName() == null) ^ (getStreamName() == null)) {
            return false;
        }
        if (describeStreamRequest.getStreamName() != null && !describeStreamRequest.getStreamName().equals(getStreamName())) {
            return false;
        }
        if ((describeStreamRequest.getLimit() == null) ^ (getLimit() == null)) {
            return false;
        }
        if (describeStreamRequest.getLimit() != null && !describeStreamRequest.getLimit().equals(getLimit())) {
            return false;
        }
        if ((describeStreamRequest.getExclusiveStartShardId() == null) ^ (getExclusiveStartShardId() == null)) {
            return false;
        }
        return describeStreamRequest.getExclusiveStartShardId() == null || describeStreamRequest.getExclusiveStartShardId().equals(getExclusiveStartShardId());
    }

    public String getExclusiveStartShardId() {
        return this.exclusiveStartShardId;
    }

    public Integer getLimit() {
        return this.limit;
    }

    public String getStreamName() {
        return this.streamName;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((getStreamName() == null ? 0 : getStreamName().hashCode()) + 31) * 31) + (getLimit() == null ? 0 : getLimit().hashCode())) * 31;
        if (getExclusiveStartShardId() != null) {
            i = getExclusiveStartShardId().hashCode();
        }
        return hashCode + i;
    }

    public void setExclusiveStartShardId(String str) {
        this.exclusiveStartShardId = str;
    }

    public void setLimit(Integer num) {
        this.limit = num;
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
        if (getLimit() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("Limit: ");
            outline1073.append(getLimit());
            outline1073.append(",");
            outline107.append(outline1073.toString());
        }
        if (getExclusiveStartShardId() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("ExclusiveStartShardId: ");
            outline1074.append(getExclusiveStartShardId());
            outline107.append(outline1074.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public DescribeStreamRequest withExclusiveStartShardId(String str) {
        this.exclusiveStartShardId = str;
        return this;
    }

    public DescribeStreamRequest withLimit(Integer num) {
        this.limit = num;
        return this;
    }

    public DescribeStreamRequest withStreamName(String str) {
        this.streamName = str;
        return this;
    }
}
