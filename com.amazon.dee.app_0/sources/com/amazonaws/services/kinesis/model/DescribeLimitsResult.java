package com.amazonaws.services.kinesis.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class DescribeLimitsResult implements Serializable {
    private Integer openShardCount;
    private Integer shardLimit;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof DescribeLimitsResult)) {
            return false;
        }
        DescribeLimitsResult describeLimitsResult = (DescribeLimitsResult) obj;
        if ((describeLimitsResult.getShardLimit() == null) ^ (getShardLimit() == null)) {
            return false;
        }
        if (describeLimitsResult.getShardLimit() != null && !describeLimitsResult.getShardLimit().equals(getShardLimit())) {
            return false;
        }
        if ((describeLimitsResult.getOpenShardCount() == null) ^ (getOpenShardCount() == null)) {
            return false;
        }
        return describeLimitsResult.getOpenShardCount() == null || describeLimitsResult.getOpenShardCount().equals(getOpenShardCount());
    }

    public Integer getOpenShardCount() {
        return this.openShardCount;
    }

    public Integer getShardLimit() {
        return this.shardLimit;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((getShardLimit() == null ? 0 : getShardLimit().hashCode()) + 31) * 31;
        if (getOpenShardCount() != null) {
            i = getOpenShardCount().hashCode();
        }
        return hashCode + i;
    }

    public void setOpenShardCount(Integer num) {
        this.openShardCount = num;
    }

    public void setShardLimit(Integer num) {
        this.shardLimit = num;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getShardLimit() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("ShardLimit: ");
            outline1072.append(getShardLimit());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getOpenShardCount() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("OpenShardCount: ");
            outline1073.append(getOpenShardCount());
            outline107.append(outline1073.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public DescribeLimitsResult withOpenShardCount(Integer num) {
        this.openShardCount = num;
        return this;
    }

    public DescribeLimitsResult withShardLimit(Integer num) {
        this.shardLimit = num;
        return this;
    }
}
