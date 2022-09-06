package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class KinesisAction implements Serializable {
    private String partitionKey;
    private String roleArn;
    private String streamName;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof KinesisAction)) {
            return false;
        }
        KinesisAction kinesisAction = (KinesisAction) obj;
        if ((kinesisAction.getRoleArn() == null) ^ (getRoleArn() == null)) {
            return false;
        }
        if (kinesisAction.getRoleArn() != null && !kinesisAction.getRoleArn().equals(getRoleArn())) {
            return false;
        }
        if ((kinesisAction.getStreamName() == null) ^ (getStreamName() == null)) {
            return false;
        }
        if (kinesisAction.getStreamName() != null && !kinesisAction.getStreamName().equals(getStreamName())) {
            return false;
        }
        if ((kinesisAction.getPartitionKey() == null) ^ (getPartitionKey() == null)) {
            return false;
        }
        return kinesisAction.getPartitionKey() == null || kinesisAction.getPartitionKey().equals(getPartitionKey());
    }

    public String getPartitionKey() {
        return this.partitionKey;
    }

    public String getRoleArn() {
        return this.roleArn;
    }

    public String getStreamName() {
        return this.streamName;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((getRoleArn() == null ? 0 : getRoleArn().hashCode()) + 31) * 31) + (getStreamName() == null ? 0 : getStreamName().hashCode())) * 31;
        if (getPartitionKey() != null) {
            i = getPartitionKey().hashCode();
        }
        return hashCode + i;
    }

    public void setPartitionKey(String str) {
        this.partitionKey = str;
    }

    public void setRoleArn(String str) {
        this.roleArn = str;
    }

    public void setStreamName(String str) {
        this.streamName = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getRoleArn() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("roleArn: ");
            outline1072.append(getRoleArn());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getStreamName() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("streamName: ");
            outline1073.append(getStreamName());
            outline1073.append(",");
            outline107.append(outline1073.toString());
        }
        if (getPartitionKey() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("partitionKey: ");
            outline1074.append(getPartitionKey());
            outline107.append(outline1074.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public KinesisAction withPartitionKey(String str) {
        this.partitionKey = str;
        return this;
    }

    public KinesisAction withRoleArn(String str) {
        this.roleArn = str;
        return this;
    }

    public KinesisAction withStreamName(String str) {
        this.streamName = str;
        return this;
    }
}
