package com.amazonaws.services.kinesis.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
/* loaded from: classes13.dex */
public class EnhancedMetrics implements Serializable {
    private List<String> shardLevelMetrics = new ArrayList();

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof EnhancedMetrics)) {
            return false;
        }
        EnhancedMetrics enhancedMetrics = (EnhancedMetrics) obj;
        if ((enhancedMetrics.getShardLevelMetrics() == null) ^ (getShardLevelMetrics() == null)) {
            return false;
        }
        return enhancedMetrics.getShardLevelMetrics() == null || enhancedMetrics.getShardLevelMetrics().equals(getShardLevelMetrics());
    }

    public List<String> getShardLevelMetrics() {
        return this.shardLevelMetrics;
    }

    public int hashCode() {
        return 31 + (getShardLevelMetrics() == null ? 0 : getShardLevelMetrics().hashCode());
    }

    public void setShardLevelMetrics(Collection<String> collection) {
        if (collection == null) {
            this.shardLevelMetrics = null;
        } else {
            this.shardLevelMetrics = new ArrayList(collection);
        }
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getShardLevelMetrics() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("ShardLevelMetrics: ");
            outline1072.append(getShardLevelMetrics());
            outline107.append(outline1072.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public EnhancedMetrics withShardLevelMetrics(String... strArr) {
        if (getShardLevelMetrics() == null) {
            this.shardLevelMetrics = new ArrayList(strArr.length);
        }
        for (String str : strArr) {
            this.shardLevelMetrics.add(str);
        }
        return this;
    }

    public EnhancedMetrics withShardLevelMetrics(Collection<String> collection) {
        setShardLevelMetrics(collection);
        return this;
    }
}
