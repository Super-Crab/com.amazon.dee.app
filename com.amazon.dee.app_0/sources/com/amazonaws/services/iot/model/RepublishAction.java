package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class RepublishAction implements Serializable {
    private String roleArn;
    private String topic;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof RepublishAction)) {
            return false;
        }
        RepublishAction republishAction = (RepublishAction) obj;
        if ((republishAction.getRoleArn() == null) ^ (getRoleArn() == null)) {
            return false;
        }
        if (republishAction.getRoleArn() != null && !republishAction.getRoleArn().equals(getRoleArn())) {
            return false;
        }
        if ((republishAction.getTopic() == null) ^ (getTopic() == null)) {
            return false;
        }
        return republishAction.getTopic() == null || republishAction.getTopic().equals(getTopic());
    }

    public String getRoleArn() {
        return this.roleArn;
    }

    public String getTopic() {
        return this.topic;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((getRoleArn() == null ? 0 : getRoleArn().hashCode()) + 31) * 31;
        if (getTopic() != null) {
            i = getTopic().hashCode();
        }
        return hashCode + i;
    }

    public void setRoleArn(String str) {
        this.roleArn = str;
    }

    public void setTopic(String str) {
        this.topic = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getRoleArn() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("roleArn: ");
            outline1072.append(getRoleArn());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getTopic() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("topic: ");
            outline1073.append(getTopic());
            outline107.append(outline1073.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public RepublishAction withRoleArn(String str) {
        this.roleArn = str;
        return this;
    }

    public RepublishAction withTopic(String str) {
        this.topic = str;
        return this;
    }
}
