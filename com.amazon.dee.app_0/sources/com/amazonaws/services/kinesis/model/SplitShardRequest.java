package com.amazonaws.services.kinesis.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazonaws.AmazonWebServiceRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class SplitShardRequest extends AmazonWebServiceRequest implements Serializable {
    private String newStartingHashKey;
    private String shardToSplit;
    private String streamName;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof SplitShardRequest)) {
            return false;
        }
        SplitShardRequest splitShardRequest = (SplitShardRequest) obj;
        if ((splitShardRequest.getStreamName() == null) ^ (getStreamName() == null)) {
            return false;
        }
        if (splitShardRequest.getStreamName() != null && !splitShardRequest.getStreamName().equals(getStreamName())) {
            return false;
        }
        if ((splitShardRequest.getShardToSplit() == null) ^ (getShardToSplit() == null)) {
            return false;
        }
        if (splitShardRequest.getShardToSplit() != null && !splitShardRequest.getShardToSplit().equals(getShardToSplit())) {
            return false;
        }
        if ((splitShardRequest.getNewStartingHashKey() == null) ^ (getNewStartingHashKey() == null)) {
            return false;
        }
        return splitShardRequest.getNewStartingHashKey() == null || splitShardRequest.getNewStartingHashKey().equals(getNewStartingHashKey());
    }

    public String getNewStartingHashKey() {
        return this.newStartingHashKey;
    }

    public String getShardToSplit() {
        return this.shardToSplit;
    }

    public String getStreamName() {
        return this.streamName;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((getStreamName() == null ? 0 : getStreamName().hashCode()) + 31) * 31) + (getShardToSplit() == null ? 0 : getShardToSplit().hashCode())) * 31;
        if (getNewStartingHashKey() != null) {
            i = getNewStartingHashKey().hashCode();
        }
        return hashCode + i;
    }

    public void setNewStartingHashKey(String str) {
        this.newStartingHashKey = str;
    }

    public void setShardToSplit(String str) {
        this.shardToSplit = str;
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
        if (getShardToSplit() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("ShardToSplit: ");
            outline1073.append(getShardToSplit());
            outline1073.append(",");
            outline107.append(outline1073.toString());
        }
        if (getNewStartingHashKey() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("NewStartingHashKey: ");
            outline1074.append(getNewStartingHashKey());
            outline107.append(outline1074.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public SplitShardRequest withNewStartingHashKey(String str) {
        this.newStartingHashKey = str;
        return this;
    }

    public SplitShardRequest withShardToSplit(String str) {
        this.shardToSplit = str;
        return this;
    }

    public SplitShardRequest withStreamName(String str) {
        this.streamName = str;
        return this;
    }
}
