package com.amazonaws.services.kinesis.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
/* loaded from: classes13.dex */
public class ListShardsResult implements Serializable {
    private String nextToken;
    private List<Shard> shards = new ArrayList();

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ListShardsResult)) {
            return false;
        }
        ListShardsResult listShardsResult = (ListShardsResult) obj;
        if ((listShardsResult.getShards() == null) ^ (getShards() == null)) {
            return false;
        }
        if (listShardsResult.getShards() != null && !listShardsResult.getShards().equals(getShards())) {
            return false;
        }
        if ((listShardsResult.getNextToken() == null) ^ (getNextToken() == null)) {
            return false;
        }
        return listShardsResult.getNextToken() == null || listShardsResult.getNextToken().equals(getNextToken());
    }

    public String getNextToken() {
        return this.nextToken;
    }

    public List<Shard> getShards() {
        return this.shards;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((getShards() == null ? 0 : getShards().hashCode()) + 31) * 31;
        if (getNextToken() != null) {
            i = getNextToken().hashCode();
        }
        return hashCode + i;
    }

    public void setNextToken(String str) {
        this.nextToken = str;
    }

    public void setShards(Collection<Shard> collection) {
        if (collection == null) {
            this.shards = null;
        } else {
            this.shards = new ArrayList(collection);
        }
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getShards() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Shards: ");
            outline1072.append(getShards());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getNextToken() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("NextToken: ");
            outline1073.append(getNextToken());
            outline107.append(outline1073.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public ListShardsResult withNextToken(String str) {
        this.nextToken = str;
        return this;
    }

    public ListShardsResult withShards(Shard... shardArr) {
        if (getShards() == null) {
            this.shards = new ArrayList(shardArr.length);
        }
        for (Shard shard : shardArr) {
            this.shards.add(shard);
        }
        return this;
    }

    public ListShardsResult withShards(Collection<Shard> collection) {
        setShards(collection);
        return this;
    }
}
