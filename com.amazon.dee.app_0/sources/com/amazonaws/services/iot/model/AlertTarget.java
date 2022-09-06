package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class AlertTarget implements Serializable {
    private String alertTargetArn;
    private String roleArn;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof AlertTarget)) {
            return false;
        }
        AlertTarget alertTarget = (AlertTarget) obj;
        if ((alertTarget.getAlertTargetArn() == null) ^ (getAlertTargetArn() == null)) {
            return false;
        }
        if (alertTarget.getAlertTargetArn() != null && !alertTarget.getAlertTargetArn().equals(getAlertTargetArn())) {
            return false;
        }
        if ((alertTarget.getRoleArn() == null) ^ (getRoleArn() == null)) {
            return false;
        }
        return alertTarget.getRoleArn() == null || alertTarget.getRoleArn().equals(getRoleArn());
    }

    public String getAlertTargetArn() {
        return this.alertTargetArn;
    }

    public String getRoleArn() {
        return this.roleArn;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((getAlertTargetArn() == null ? 0 : getAlertTargetArn().hashCode()) + 31) * 31;
        if (getRoleArn() != null) {
            i = getRoleArn().hashCode();
        }
        return hashCode + i;
    }

    public void setAlertTargetArn(String str) {
        this.alertTargetArn = str;
    }

    public void setRoleArn(String str) {
        this.roleArn = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getAlertTargetArn() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("alertTargetArn: ");
            outline1072.append(getAlertTargetArn());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getRoleArn() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("roleArn: ");
            outline1073.append(getRoleArn());
            outline107.append(outline1073.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public AlertTarget withAlertTargetArn(String str) {
        this.alertTargetArn = str;
        return this;
    }

    public AlertTarget withRoleArn(String str) {
        this.roleArn = str;
        return this;
    }
}
