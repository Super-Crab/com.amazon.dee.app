package com.amazonaws.services.kinesis.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazonaws.AmazonWebServiceRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import java.util.Date;
/* loaded from: classes13.dex */
public class ListShardsRequest extends AmazonWebServiceRequest implements Serializable {
    private String exclusiveStartShardId;
    private Integer maxResults;
    private String nextToken;
    private Date streamCreationTimestamp;
    private String streamName;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ListShardsRequest)) {
            return false;
        }
        ListShardsRequest listShardsRequest = (ListShardsRequest) obj;
        if ((listShardsRequest.getStreamName() == null) ^ (getStreamName() == null)) {
            return false;
        }
        if (listShardsRequest.getStreamName() != null && !listShardsRequest.getStreamName().equals(getStreamName())) {
            return false;
        }
        if ((listShardsRequest.getNextToken() == null) ^ (getNextToken() == null)) {
            return false;
        }
        if (listShardsRequest.getNextToken() != null && !listShardsRequest.getNextToken().equals(getNextToken())) {
            return false;
        }
        if ((listShardsRequest.getExclusiveStartShardId() == null) ^ (getExclusiveStartShardId() == null)) {
            return false;
        }
        if (listShardsRequest.getExclusiveStartShardId() != null && !listShardsRequest.getExclusiveStartShardId().equals(getExclusiveStartShardId())) {
            return false;
        }
        if ((listShardsRequest.getMaxResults() == null) ^ (getMaxResults() == null)) {
            return false;
        }
        if (listShardsRequest.getMaxResults() != null && !listShardsRequest.getMaxResults().equals(getMaxResults())) {
            return false;
        }
        if ((listShardsRequest.getStreamCreationTimestamp() == null) ^ (getStreamCreationTimestamp() == null)) {
            return false;
        }
        return listShardsRequest.getStreamCreationTimestamp() == null || listShardsRequest.getStreamCreationTimestamp().equals(getStreamCreationTimestamp());
    }

    public String getExclusiveStartShardId() {
        return this.exclusiveStartShardId;
    }

    public Integer getMaxResults() {
        return this.maxResults;
    }

    public String getNextToken() {
        return this.nextToken;
    }

    public Date getStreamCreationTimestamp() {
        return this.streamCreationTimestamp;
    }

    public String getStreamName() {
        return this.streamName;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((((((getStreamName() == null ? 0 : getStreamName().hashCode()) + 31) * 31) + (getNextToken() == null ? 0 : getNextToken().hashCode())) * 31) + (getExclusiveStartShardId() == null ? 0 : getExclusiveStartShardId().hashCode())) * 31) + (getMaxResults() == null ? 0 : getMaxResults().hashCode())) * 31;
        if (getStreamCreationTimestamp() != null) {
            i = getStreamCreationTimestamp().hashCode();
        }
        return hashCode + i;
    }

    public void setExclusiveStartShardId(String str) {
        this.exclusiveStartShardId = str;
    }

    public void setMaxResults(Integer num) {
        this.maxResults = num;
    }

    public void setNextToken(String str) {
        this.nextToken = str;
    }

    public void setStreamCreationTimestamp(Date date) {
        this.streamCreationTimestamp = date;
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
        if (getNextToken() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("NextToken: ");
            outline1073.append(getNextToken());
            outline1073.append(",");
            outline107.append(outline1073.toString());
        }
        if (getExclusiveStartShardId() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("ExclusiveStartShardId: ");
            outline1074.append(getExclusiveStartShardId());
            outline1074.append(",");
            outline107.append(outline1074.toString());
        }
        if (getMaxResults() != null) {
            StringBuilder outline1075 = GeneratedOutlineSupport1.outline107("MaxResults: ");
            outline1075.append(getMaxResults());
            outline1075.append(",");
            outline107.append(outline1075.toString());
        }
        if (getStreamCreationTimestamp() != null) {
            StringBuilder outline1076 = GeneratedOutlineSupport1.outline107("StreamCreationTimestamp: ");
            outline1076.append(getStreamCreationTimestamp());
            outline107.append(outline1076.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public ListShardsRequest withExclusiveStartShardId(String str) {
        this.exclusiveStartShardId = str;
        return this;
    }

    public ListShardsRequest withMaxResults(Integer num) {
        this.maxResults = num;
        return this;
    }

    public ListShardsRequest withNextToken(String str) {
        this.nextToken = str;
        return this;
    }

    public ListShardsRequest withStreamCreationTimestamp(Date date) {
        this.streamCreationTimestamp = date;
        return this;
    }

    public ListShardsRequest withStreamName(String str) {
        this.streamName = str;
        return this;
    }
}
