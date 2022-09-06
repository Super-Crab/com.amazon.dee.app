package com.amazonaws.services.kinesis.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
/* loaded from: classes13.dex */
public class EnableEnhancedMonitoringResult implements Serializable {
    private List<String> currentShardLevelMetrics = new ArrayList();
    private List<String> desiredShardLevelMetrics = new ArrayList();
    private String streamName;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof EnableEnhancedMonitoringResult)) {
            return false;
        }
        EnableEnhancedMonitoringResult enableEnhancedMonitoringResult = (EnableEnhancedMonitoringResult) obj;
        if ((enableEnhancedMonitoringResult.getStreamName() == null) ^ (getStreamName() == null)) {
            return false;
        }
        if (enableEnhancedMonitoringResult.getStreamName() != null && !enableEnhancedMonitoringResult.getStreamName().equals(getStreamName())) {
            return false;
        }
        if ((enableEnhancedMonitoringResult.getCurrentShardLevelMetrics() == null) ^ (getCurrentShardLevelMetrics() == null)) {
            return false;
        }
        if (enableEnhancedMonitoringResult.getCurrentShardLevelMetrics() != null && !enableEnhancedMonitoringResult.getCurrentShardLevelMetrics().equals(getCurrentShardLevelMetrics())) {
            return false;
        }
        if ((enableEnhancedMonitoringResult.getDesiredShardLevelMetrics() == null) ^ (getDesiredShardLevelMetrics() == null)) {
            return false;
        }
        return enableEnhancedMonitoringResult.getDesiredShardLevelMetrics() == null || enableEnhancedMonitoringResult.getDesiredShardLevelMetrics().equals(getDesiredShardLevelMetrics());
    }

    public List<String> getCurrentShardLevelMetrics() {
        return this.currentShardLevelMetrics;
    }

    public List<String> getDesiredShardLevelMetrics() {
        return this.desiredShardLevelMetrics;
    }

    public String getStreamName() {
        return this.streamName;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((getStreamName() == null ? 0 : getStreamName().hashCode()) + 31) * 31) + (getCurrentShardLevelMetrics() == null ? 0 : getCurrentShardLevelMetrics().hashCode())) * 31;
        if (getDesiredShardLevelMetrics() != null) {
            i = getDesiredShardLevelMetrics().hashCode();
        }
        return hashCode + i;
    }

    public void setCurrentShardLevelMetrics(Collection<String> collection) {
        if (collection == null) {
            this.currentShardLevelMetrics = null;
        } else {
            this.currentShardLevelMetrics = new ArrayList(collection);
        }
    }

    public void setDesiredShardLevelMetrics(Collection<String> collection) {
        if (collection == null) {
            this.desiredShardLevelMetrics = null;
        } else {
            this.desiredShardLevelMetrics = new ArrayList(collection);
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
        if (getCurrentShardLevelMetrics() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("CurrentShardLevelMetrics: ");
            outline1073.append(getCurrentShardLevelMetrics());
            outline1073.append(",");
            outline107.append(outline1073.toString());
        }
        if (getDesiredShardLevelMetrics() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("DesiredShardLevelMetrics: ");
            outline1074.append(getDesiredShardLevelMetrics());
            outline107.append(outline1074.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public EnableEnhancedMonitoringResult withCurrentShardLevelMetrics(String... strArr) {
        if (getCurrentShardLevelMetrics() == null) {
            this.currentShardLevelMetrics = new ArrayList(strArr.length);
        }
        for (String str : strArr) {
            this.currentShardLevelMetrics.add(str);
        }
        return this;
    }

    public EnableEnhancedMonitoringResult withDesiredShardLevelMetrics(String... strArr) {
        if (getDesiredShardLevelMetrics() == null) {
            this.desiredShardLevelMetrics = new ArrayList(strArr.length);
        }
        for (String str : strArr) {
            this.desiredShardLevelMetrics.add(str);
        }
        return this;
    }

    public EnableEnhancedMonitoringResult withStreamName(String str) {
        this.streamName = str;
        return this;
    }

    public EnableEnhancedMonitoringResult withCurrentShardLevelMetrics(Collection<String> collection) {
        setCurrentShardLevelMetrics(collection);
        return this;
    }

    public EnableEnhancedMonitoringResult withDesiredShardLevelMetrics(Collection<String> collection) {
        setDesiredShardLevelMetrics(collection);
        return this;
    }
}
