package com.amazonaws.services.kinesis.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazonaws.AmazonWebServiceRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import java.util.Date;
/* loaded from: classes13.dex */
public class GetShardIteratorRequest extends AmazonWebServiceRequest implements Serializable {
    private String shardId;
    private String shardIteratorType;
    private String startingSequenceNumber;
    private String streamName;
    private Date timestamp;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof GetShardIteratorRequest)) {
            return false;
        }
        GetShardIteratorRequest getShardIteratorRequest = (GetShardIteratorRequest) obj;
        if ((getShardIteratorRequest.getStreamName() == null) ^ (getStreamName() == null)) {
            return false;
        }
        if (getShardIteratorRequest.getStreamName() != null && !getShardIteratorRequest.getStreamName().equals(getStreamName())) {
            return false;
        }
        if ((getShardIteratorRequest.getShardId() == null) ^ (getShardId() == null)) {
            return false;
        }
        if (getShardIteratorRequest.getShardId() != null && !getShardIteratorRequest.getShardId().equals(getShardId())) {
            return false;
        }
        if ((getShardIteratorRequest.getShardIteratorType() == null) ^ (getShardIteratorType() == null)) {
            return false;
        }
        if (getShardIteratorRequest.getShardIteratorType() != null && !getShardIteratorRequest.getShardIteratorType().equals(getShardIteratorType())) {
            return false;
        }
        if ((getShardIteratorRequest.getStartingSequenceNumber() == null) ^ (getStartingSequenceNumber() == null)) {
            return false;
        }
        if (getShardIteratorRequest.getStartingSequenceNumber() != null && !getShardIteratorRequest.getStartingSequenceNumber().equals(getStartingSequenceNumber())) {
            return false;
        }
        if ((getShardIteratorRequest.getTimestamp() == null) ^ (getTimestamp() == null)) {
            return false;
        }
        return getShardIteratorRequest.getTimestamp() == null || getShardIteratorRequest.getTimestamp().equals(getTimestamp());
    }

    public String getShardId() {
        return this.shardId;
    }

    public String getShardIteratorType() {
        return this.shardIteratorType;
    }

    public String getStartingSequenceNumber() {
        return this.startingSequenceNumber;
    }

    public String getStreamName() {
        return this.streamName;
    }

    public Date getTimestamp() {
        return this.timestamp;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((((((getStreamName() == null ? 0 : getStreamName().hashCode()) + 31) * 31) + (getShardId() == null ? 0 : getShardId().hashCode())) * 31) + (getShardIteratorType() == null ? 0 : getShardIteratorType().hashCode())) * 31) + (getStartingSequenceNumber() == null ? 0 : getStartingSequenceNumber().hashCode())) * 31;
        if (getTimestamp() != null) {
            i = getTimestamp().hashCode();
        }
        return hashCode + i;
    }

    public void setShardId(String str) {
        this.shardId = str;
    }

    public void setShardIteratorType(String str) {
        this.shardIteratorType = str;
    }

    public void setStartingSequenceNumber(String str) {
        this.startingSequenceNumber = str;
    }

    public void setStreamName(String str) {
        this.streamName = str;
    }

    public void setTimestamp(Date date) {
        this.timestamp = date;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getStreamName() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("StreamName: ");
            outline1072.append(getStreamName());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getShardId() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("ShardId: ");
            outline1073.append(getShardId());
            outline1073.append(",");
            outline107.append(outline1073.toString());
        }
        if (getShardIteratorType() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("ShardIteratorType: ");
            outline1074.append(getShardIteratorType());
            outline1074.append(",");
            outline107.append(outline1074.toString());
        }
        if (getStartingSequenceNumber() != null) {
            StringBuilder outline1075 = GeneratedOutlineSupport1.outline107("StartingSequenceNumber: ");
            outline1075.append(getStartingSequenceNumber());
            outline1075.append(",");
            outline107.append(outline1075.toString());
        }
        if (getTimestamp() != null) {
            StringBuilder outline1076 = GeneratedOutlineSupport1.outline107("Timestamp: ");
            outline1076.append(getTimestamp());
            outline107.append(outline1076.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public GetShardIteratorRequest withShardId(String str) {
        this.shardId = str;
        return this;
    }

    public GetShardIteratorRequest withShardIteratorType(String str) {
        this.shardIteratorType = str;
        return this;
    }

    public GetShardIteratorRequest withStartingSequenceNumber(String str) {
        this.startingSequenceNumber = str;
        return this;
    }

    public GetShardIteratorRequest withStreamName(String str) {
        this.streamName = str;
        return this;
    }

    public GetShardIteratorRequest withTimestamp(Date date) {
        this.timestamp = date;
        return this;
    }

    public void setShardIteratorType(ShardIteratorType shardIteratorType) {
        this.shardIteratorType = shardIteratorType.toString();
    }

    public GetShardIteratorRequest withShardIteratorType(ShardIteratorType shardIteratorType) {
        this.shardIteratorType = shardIteratorType.toString();
        return this;
    }
}
