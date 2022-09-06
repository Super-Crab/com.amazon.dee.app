package com.amazonaws.services.kinesis.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazonaws.AmazonWebServiceRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class IncreaseStreamRetentionPeriodRequest extends AmazonWebServiceRequest implements Serializable {
    private Integer retentionPeriodHours;
    private String streamName;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof IncreaseStreamRetentionPeriodRequest)) {
            return false;
        }
        IncreaseStreamRetentionPeriodRequest increaseStreamRetentionPeriodRequest = (IncreaseStreamRetentionPeriodRequest) obj;
        if ((increaseStreamRetentionPeriodRequest.getStreamName() == null) ^ (getStreamName() == null)) {
            return false;
        }
        if (increaseStreamRetentionPeriodRequest.getStreamName() != null && !increaseStreamRetentionPeriodRequest.getStreamName().equals(getStreamName())) {
            return false;
        }
        if ((increaseStreamRetentionPeriodRequest.getRetentionPeriodHours() == null) ^ (getRetentionPeriodHours() == null)) {
            return false;
        }
        return increaseStreamRetentionPeriodRequest.getRetentionPeriodHours() == null || increaseStreamRetentionPeriodRequest.getRetentionPeriodHours().equals(getRetentionPeriodHours());
    }

    public Integer getRetentionPeriodHours() {
        return this.retentionPeriodHours;
    }

    public String getStreamName() {
        return this.streamName;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((getStreamName() == null ? 0 : getStreamName().hashCode()) + 31) * 31;
        if (getRetentionPeriodHours() != null) {
            i = getRetentionPeriodHours().hashCode();
        }
        return hashCode + i;
    }

    public void setRetentionPeriodHours(Integer num) {
        this.retentionPeriodHours = num;
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
        if (getRetentionPeriodHours() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("RetentionPeriodHours: ");
            outline1073.append(getRetentionPeriodHours());
            outline107.append(outline1073.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public IncreaseStreamRetentionPeriodRequest withRetentionPeriodHours(Integer num) {
        this.retentionPeriodHours = num;
        return this;
    }

    public IncreaseStreamRetentionPeriodRequest withStreamName(String str) {
        this.streamName = str;
        return this;
    }
}
