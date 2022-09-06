package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class SnsAction implements Serializable {
    private String messageFormat;
    private String roleArn;
    private String targetArn;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof SnsAction)) {
            return false;
        }
        SnsAction snsAction = (SnsAction) obj;
        if ((snsAction.getTargetArn() == null) ^ (getTargetArn() == null)) {
            return false;
        }
        if (snsAction.getTargetArn() != null && !snsAction.getTargetArn().equals(getTargetArn())) {
            return false;
        }
        if ((snsAction.getRoleArn() == null) ^ (getRoleArn() == null)) {
            return false;
        }
        if (snsAction.getRoleArn() != null && !snsAction.getRoleArn().equals(getRoleArn())) {
            return false;
        }
        if ((snsAction.getMessageFormat() == null) ^ (getMessageFormat() == null)) {
            return false;
        }
        return snsAction.getMessageFormat() == null || snsAction.getMessageFormat().equals(getMessageFormat());
    }

    public String getMessageFormat() {
        return this.messageFormat;
    }

    public String getRoleArn() {
        return this.roleArn;
    }

    public String getTargetArn() {
        return this.targetArn;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((getTargetArn() == null ? 0 : getTargetArn().hashCode()) + 31) * 31) + (getRoleArn() == null ? 0 : getRoleArn().hashCode())) * 31;
        if (getMessageFormat() != null) {
            i = getMessageFormat().hashCode();
        }
        return hashCode + i;
    }

    public void setMessageFormat(String str) {
        this.messageFormat = str;
    }

    public void setRoleArn(String str) {
        this.roleArn = str;
    }

    public void setTargetArn(String str) {
        this.targetArn = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getTargetArn() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("targetArn: ");
            outline1072.append(getTargetArn());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getRoleArn() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("roleArn: ");
            outline1073.append(getRoleArn());
            outline1073.append(",");
            outline107.append(outline1073.toString());
        }
        if (getMessageFormat() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("messageFormat: ");
            outline1074.append(getMessageFormat());
            outline107.append(outline1074.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public SnsAction withMessageFormat(String str) {
        this.messageFormat = str;
        return this;
    }

    public SnsAction withRoleArn(String str) {
        this.roleArn = str;
        return this;
    }

    public SnsAction withTargetArn(String str) {
        this.targetArn = str;
        return this;
    }

    public void setMessageFormat(MessageFormat messageFormat) {
        this.messageFormat = messageFormat.toString();
    }

    public SnsAction withMessageFormat(MessageFormat messageFormat) {
        this.messageFormat = messageFormat.toString();
        return this;
    }
}
