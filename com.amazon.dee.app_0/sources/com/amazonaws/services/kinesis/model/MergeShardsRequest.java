package com.amazonaws.services.kinesis.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazonaws.AmazonWebServiceRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class MergeShardsRequest extends AmazonWebServiceRequest implements Serializable {
    private String adjacentShardToMerge;
    private String shardToMerge;
    private String streamName;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof MergeShardsRequest)) {
            return false;
        }
        MergeShardsRequest mergeShardsRequest = (MergeShardsRequest) obj;
        if ((mergeShardsRequest.getStreamName() == null) ^ (getStreamName() == null)) {
            return false;
        }
        if (mergeShardsRequest.getStreamName() != null && !mergeShardsRequest.getStreamName().equals(getStreamName())) {
            return false;
        }
        if ((mergeShardsRequest.getShardToMerge() == null) ^ (getShardToMerge() == null)) {
            return false;
        }
        if (mergeShardsRequest.getShardToMerge() != null && !mergeShardsRequest.getShardToMerge().equals(getShardToMerge())) {
            return false;
        }
        if ((mergeShardsRequest.getAdjacentShardToMerge() == null) ^ (getAdjacentShardToMerge() == null)) {
            return false;
        }
        return mergeShardsRequest.getAdjacentShardToMerge() == null || mergeShardsRequest.getAdjacentShardToMerge().equals(getAdjacentShardToMerge());
    }

    public String getAdjacentShardToMerge() {
        return this.adjacentShardToMerge;
    }

    public String getShardToMerge() {
        return this.shardToMerge;
    }

    public String getStreamName() {
        return this.streamName;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((getStreamName() == null ? 0 : getStreamName().hashCode()) + 31) * 31) + (getShardToMerge() == null ? 0 : getShardToMerge().hashCode())) * 31;
        if (getAdjacentShardToMerge() != null) {
            i = getAdjacentShardToMerge().hashCode();
        }
        return hashCode + i;
    }

    public void setAdjacentShardToMerge(String str) {
        this.adjacentShardToMerge = str;
    }

    public void setShardToMerge(String str) {
        this.shardToMerge = str;
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
        if (getShardToMerge() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("ShardToMerge: ");
            outline1073.append(getShardToMerge());
            outline1073.append(",");
            outline107.append(outline1073.toString());
        }
        if (getAdjacentShardToMerge() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("AdjacentShardToMerge: ");
            outline1074.append(getAdjacentShardToMerge());
            outline107.append(outline1074.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public MergeShardsRequest withAdjacentShardToMerge(String str) {
        this.adjacentShardToMerge = str;
        return this;
    }

    public MergeShardsRequest withShardToMerge(String str) {
        this.shardToMerge = str;
        return this;
    }

    public MergeShardsRequest withStreamName(String str) {
        this.streamName = str;
        return this;
    }
}
