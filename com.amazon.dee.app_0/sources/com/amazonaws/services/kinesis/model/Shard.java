package com.amazonaws.services.kinesis.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class Shard implements Serializable {
    private String adjacentParentShardId;
    private HashKeyRange hashKeyRange;
    private String parentShardId;
    private SequenceNumberRange sequenceNumberRange;
    private String shardId;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof Shard)) {
            return false;
        }
        Shard shard = (Shard) obj;
        if ((shard.getShardId() == null) ^ (getShardId() == null)) {
            return false;
        }
        if (shard.getShardId() != null && !shard.getShardId().equals(getShardId())) {
            return false;
        }
        if ((shard.getParentShardId() == null) ^ (getParentShardId() == null)) {
            return false;
        }
        if (shard.getParentShardId() != null && !shard.getParentShardId().equals(getParentShardId())) {
            return false;
        }
        if ((shard.getAdjacentParentShardId() == null) ^ (getAdjacentParentShardId() == null)) {
            return false;
        }
        if (shard.getAdjacentParentShardId() != null && !shard.getAdjacentParentShardId().equals(getAdjacentParentShardId())) {
            return false;
        }
        if ((shard.getHashKeyRange() == null) ^ (getHashKeyRange() == null)) {
            return false;
        }
        if (shard.getHashKeyRange() != null && !shard.getHashKeyRange().equals(getHashKeyRange())) {
            return false;
        }
        if ((shard.getSequenceNumberRange() == null) ^ (getSequenceNumberRange() == null)) {
            return false;
        }
        return shard.getSequenceNumberRange() == null || shard.getSequenceNumberRange().equals(getSequenceNumberRange());
    }

    public String getAdjacentParentShardId() {
        return this.adjacentParentShardId;
    }

    public HashKeyRange getHashKeyRange() {
        return this.hashKeyRange;
    }

    public String getParentShardId() {
        return this.parentShardId;
    }

    public SequenceNumberRange getSequenceNumberRange() {
        return this.sequenceNumberRange;
    }

    public String getShardId() {
        return this.shardId;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((((((getShardId() == null ? 0 : getShardId().hashCode()) + 31) * 31) + (getParentShardId() == null ? 0 : getParentShardId().hashCode())) * 31) + (getAdjacentParentShardId() == null ? 0 : getAdjacentParentShardId().hashCode())) * 31) + (getHashKeyRange() == null ? 0 : getHashKeyRange().hashCode())) * 31;
        if (getSequenceNumberRange() != null) {
            i = getSequenceNumberRange().hashCode();
        }
        return hashCode + i;
    }

    public void setAdjacentParentShardId(String str) {
        this.adjacentParentShardId = str;
    }

    public void setHashKeyRange(HashKeyRange hashKeyRange) {
        this.hashKeyRange = hashKeyRange;
    }

    public void setParentShardId(String str) {
        this.parentShardId = str;
    }

    public void setSequenceNumberRange(SequenceNumberRange sequenceNumberRange) {
        this.sequenceNumberRange = sequenceNumberRange;
    }

    public void setShardId(String str) {
        this.shardId = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getShardId() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("ShardId: ");
            outline1072.append(getShardId());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getParentShardId() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("ParentShardId: ");
            outline1073.append(getParentShardId());
            outline1073.append(",");
            outline107.append(outline1073.toString());
        }
        if (getAdjacentParentShardId() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("AdjacentParentShardId: ");
            outline1074.append(getAdjacentParentShardId());
            outline1074.append(",");
            outline107.append(outline1074.toString());
        }
        if (getHashKeyRange() != null) {
            StringBuilder outline1075 = GeneratedOutlineSupport1.outline107("HashKeyRange: ");
            outline1075.append(getHashKeyRange());
            outline1075.append(",");
            outline107.append(outline1075.toString());
        }
        if (getSequenceNumberRange() != null) {
            StringBuilder outline1076 = GeneratedOutlineSupport1.outline107("SequenceNumberRange: ");
            outline1076.append(getSequenceNumberRange());
            outline107.append(outline1076.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public Shard withAdjacentParentShardId(String str) {
        this.adjacentParentShardId = str;
        return this;
    }

    public Shard withHashKeyRange(HashKeyRange hashKeyRange) {
        this.hashKeyRange = hashKeyRange;
        return this;
    }

    public Shard withParentShardId(String str) {
        this.parentShardId = str;
        return this;
    }

    public Shard withSequenceNumberRange(SequenceNumberRange sequenceNumberRange) {
        this.sequenceNumberRange = sequenceNumberRange;
        return this;
    }

    public Shard withShardId(String str) {
        this.shardId = str;
        return this;
    }
}
