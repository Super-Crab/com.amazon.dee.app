package com.amazonaws.services.kinesis.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazonaws.AmazonWebServiceRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class UpdateShardCountRequest extends AmazonWebServiceRequest implements Serializable {
    private String scalingType;
    private String streamName;
    private Integer targetShardCount;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof UpdateShardCountRequest)) {
            return false;
        }
        UpdateShardCountRequest updateShardCountRequest = (UpdateShardCountRequest) obj;
        if ((updateShardCountRequest.getStreamName() == null) ^ (getStreamName() == null)) {
            return false;
        }
        if (updateShardCountRequest.getStreamName() != null && !updateShardCountRequest.getStreamName().equals(getStreamName())) {
            return false;
        }
        if ((updateShardCountRequest.getTargetShardCount() == null) ^ (getTargetShardCount() == null)) {
            return false;
        }
        if (updateShardCountRequest.getTargetShardCount() != null && !updateShardCountRequest.getTargetShardCount().equals(getTargetShardCount())) {
            return false;
        }
        if ((updateShardCountRequest.getScalingType() == null) ^ (getScalingType() == null)) {
            return false;
        }
        return updateShardCountRequest.getScalingType() == null || updateShardCountRequest.getScalingType().equals(getScalingType());
    }

    public String getScalingType() {
        return this.scalingType;
    }

    public String getStreamName() {
        return this.streamName;
    }

    public Integer getTargetShardCount() {
        return this.targetShardCount;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((getStreamName() == null ? 0 : getStreamName().hashCode()) + 31) * 31) + (getTargetShardCount() == null ? 0 : getTargetShardCount().hashCode())) * 31;
        if (getScalingType() != null) {
            i = getScalingType().hashCode();
        }
        return hashCode + i;
    }

    public void setScalingType(String str) {
        this.scalingType = str;
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
        if (getTargetShardCount() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("TargetShardCount: ");
            outline1073.append(getTargetShardCount());
            outline1073.append(",");
            outline107.append(outline1073.toString());
        }
        if (getScalingType() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("ScalingType: ");
            outline1074.append(getScalingType());
            outline107.append(outline1074.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public UpdateShardCountRequest withScalingType(String str) {
        this.scalingType = str;
        return this;
    }

    public UpdateShardCountRequest withStreamName(String str) {
        this.streamName = str;
        return this;
    }

    public UpdateShardCountRequest withTargetShardCount(Integer num) {
        this.targetShardCount = num;
        return this;
    }

    public void setScalingType(ScalingType scalingType) {
        this.scalingType = scalingType.toString();
    }

    public UpdateShardCountRequest withScalingType(ScalingType scalingType) {
        this.scalingType = scalingType.toString();
        return this;
    }
}
