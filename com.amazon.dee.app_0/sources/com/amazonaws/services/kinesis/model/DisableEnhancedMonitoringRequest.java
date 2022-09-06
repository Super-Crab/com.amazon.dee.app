package com.amazonaws.services.kinesis.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazonaws.AmazonWebServiceRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
/* loaded from: classes13.dex */
public class DisableEnhancedMonitoringRequest extends AmazonWebServiceRequest implements Serializable {
    private List<String> shardLevelMetrics = new ArrayList();
    private String streamName;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof DisableEnhancedMonitoringRequest)) {
            return false;
        }
        DisableEnhancedMonitoringRequest disableEnhancedMonitoringRequest = (DisableEnhancedMonitoringRequest) obj;
        if ((disableEnhancedMonitoringRequest.getStreamName() == null) ^ (getStreamName() == null)) {
            return false;
        }
        if (disableEnhancedMonitoringRequest.getStreamName() != null && !disableEnhancedMonitoringRequest.getStreamName().equals(getStreamName())) {
            return false;
        }
        if ((disableEnhancedMonitoringRequest.getShardLevelMetrics() == null) ^ (getShardLevelMetrics() == null)) {
            return false;
        }
        return disableEnhancedMonitoringRequest.getShardLevelMetrics() == null || disableEnhancedMonitoringRequest.getShardLevelMetrics().equals(getShardLevelMetrics());
    }

    public List<String> getShardLevelMetrics() {
        return this.shardLevelMetrics;
    }

    public String getStreamName() {
        return this.streamName;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((getStreamName() == null ? 0 : getStreamName().hashCode()) + 31) * 31;
        if (getShardLevelMetrics() != null) {
            i = getShardLevelMetrics().hashCode();
        }
        return hashCode + i;
    }

    public void setShardLevelMetrics(Collection<String> collection) {
        if (collection == null) {
            this.shardLevelMetrics = null;
        } else {
            this.shardLevelMetrics = new ArrayList(collection);
        }
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
        if (getShardLevelMetrics() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("ShardLevelMetrics: ");
            outline1073.append(getShardLevelMetrics());
            outline107.append(outline1073.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public DisableEnhancedMonitoringRequest withShardLevelMetrics(String... strArr) {
        if (getShardLevelMetrics() == null) {
            this.shardLevelMetrics = new ArrayList(strArr.length);
        }
        for (String str : strArr) {
            this.shardLevelMetrics.add(str);
        }
        return this;
    }

    public DisableEnhancedMonitoringRequest withStreamName(String str) {
        this.streamName = str;
        return this;
    }

    public DisableEnhancedMonitoringRequest withShardLevelMetrics(Collection<String> collection) {
        setShardLevelMetrics(collection);
        return this;
    }
}
