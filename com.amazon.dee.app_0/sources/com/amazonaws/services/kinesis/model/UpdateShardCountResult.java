package com.amazonaws.services.kinesis.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class UpdateShardCountResult implements Serializable {
    private Integer currentShardCount;
    private String streamName;
    private Integer targetShardCount;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof UpdateShardCountResult)) {
            return false;
        }
        UpdateShardCountResult updateShardCountResult = (UpdateShardCountResult) obj;
        if ((updateShardCountResult.getStreamName() == null) ^ (getStreamName() == null)) {
            return false;
        }
        if (updateShardCountResult.getStreamName() != null && !updateShardCountResult.getStreamName().equals(getStreamName())) {
            return false;
        }
        if ((updateShardCountResult.getCurrentShardCount() == null) ^ (getCurrentShardCount() == null)) {
            return false;
        }
        if (updateShardCountResult.getCurrentShardCount() != null && !updateShardCountResult.getCurrentShardCount().equals(getCurrentShardCount())) {
            return false;
        }
        if ((updateShardCountResult.getTargetShardCount() == null) ^ (getTargetShardCount() == null)) {
            return false;
        }
        return updateShardCountResult.getTargetShardCount() == null || updateShardCountResult.getTargetShardCount().equals(getTargetShardCount());
    }

    public Integer getCurrentShardCount() {
        return this.currentShardCount;
    }

    public String getStreamName() {
        return this.streamName;
    }

    public Integer getTargetShardCount() {
        return this.targetShardCount;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((getStreamName() == null ? 0 : getStreamName().hashCode()) + 31) * 31) + (getCurrentShardCount() == null ? 0 : getCurrentShardCount().hashCode())) * 31;
        if (getTargetShardCount() != null) {
            i = getTargetShardCount().hashCode();
        }
        return hashCode + i;
    }

    public void setCurrentShardCount(Integer num) {
        this.currentShardCount = num;
    }

    public void setStreamName(String str) {
        this.streamName = str;
    }

    public void setTargetShardCount(Integer num) {
        this.targetShardCount = num;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getStreamName() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("StreamName: ");
            outline1072.append(getStreamName());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getCurrentShardCount() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("CurrentShardCount: ");
            outline1073.append(getCurrentShardCount());
            outline1073.append(",");
            outline107.append(outline1073.toString());
        }
        if (getTargetShardCount() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("TargetShardCount: ");
            outline1074.append(getTargetShardCount());
            outline107.append(outline1074.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public UpdateShardCountResult withCurrentShardCount(Integer num) {
        this.currentShardCount = num;
        return this;
    }

    public UpdateShardCountResult withStreamName(String str) {
        this.streamName = str;
        return this;
    }

    public UpdateShardCountResult withTargetShardCount(Integer num) {
        this.targetShardCount = num;
        return this;
    }
}
