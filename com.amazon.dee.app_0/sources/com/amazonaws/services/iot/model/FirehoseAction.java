package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class FirehoseAction implements Serializable {
    private String deliveryStreamName;
    private String roleArn;
    private String separator;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof FirehoseAction)) {
            return false;
        }
        FirehoseAction firehoseAction = (FirehoseAction) obj;
        if ((firehoseAction.getRoleArn() == null) ^ (getRoleArn() == null)) {
            return false;
        }
        if (firehoseAction.getRoleArn() != null && !firehoseAction.getRoleArn().equals(getRoleArn())) {
            return false;
        }
        if ((firehoseAction.getDeliveryStreamName() == null) ^ (getDeliveryStreamName() == null)) {
            return false;
        }
        if (firehoseAction.getDeliveryStreamName() != null && !firehoseAction.getDeliveryStreamName().equals(getDeliveryStreamName())) {
            return false;
        }
        if ((firehoseAction.getSeparator() == null) ^ (getSeparator() == null)) {
            return false;
        }
        return firehoseAction.getSeparator() == null || firehoseAction.getSeparator().equals(getSeparator());
    }

    public String getDeliveryStreamName() {
        return this.deliveryStreamName;
    }

    public String getRoleArn() {
        return this.roleArn;
    }

    public String getSeparator() {
        return this.separator;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((getRoleArn() == null ? 0 : getRoleArn().hashCode()) + 31) * 31) + (getDeliveryStreamName() == null ? 0 : getDeliveryStreamName().hashCode())) * 31;
        if (getSeparator() != null) {
            i = getSeparator().hashCode();
        }
        return hashCode + i;
    }

    public void setDeliveryStreamName(String str) {
        this.deliveryStreamName = str;
    }

    public void setRoleArn(String str) {
        this.roleArn = str;
    }

    public void setSeparator(String str) {
        this.separator = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getRoleArn() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("roleArn: ");
            outline1072.append(getRoleArn());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getDeliveryStreamName() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("deliveryStreamName: ");
            outline1073.append(getDeliveryStreamName());
            outline1073.append(",");
            outline107.append(outline1073.toString());
        }
        if (getSeparator() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("separator: ");
            outline1074.append(getSeparator());
            outline107.append(outline1074.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public FirehoseAction withDeliveryStreamName(String str) {
        this.deliveryStreamName = str;
        return this;
    }

    public FirehoseAction withRoleArn(String str) {
        this.roleArn = str;
        return this;
    }

    public FirehoseAction withSeparator(String str) {
        this.separator = str;
        return this;
    }
}
