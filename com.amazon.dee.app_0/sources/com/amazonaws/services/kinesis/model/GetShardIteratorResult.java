package com.amazonaws.services.kinesis.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class GetShardIteratorResult implements Serializable {
    private String shardIterator;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof GetShardIteratorResult)) {
            return false;
        }
        GetShardIteratorResult getShardIteratorResult = (GetShardIteratorResult) obj;
        if ((getShardIteratorResult.getShardIterator() == null) ^ (getShardIterator() == null)) {
            return false;
        }
        return getShardIteratorResult.getShardIterator() == null || getShardIteratorResult.getShardIterator().equals(getShardIterator());
    }

    public String getShardIterator() {
        return this.shardIterator;
    }

    public int hashCode() {
        return 31 + (getShardIterator() == null ? 0 : getShardIterator().hashCode());
    }

    public void setShardIterator(String str) {
        this.shardIterator = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getShardIterator() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("ShardIterator: ");
            outline1072.append(getShardIterator());
            outline107.append(outline1072.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public GetShardIteratorResult withShardIterator(String str) {
        this.shardIterator = str;
        return this;
    }
}
