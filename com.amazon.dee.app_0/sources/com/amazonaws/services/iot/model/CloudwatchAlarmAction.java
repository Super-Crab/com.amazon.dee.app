package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class CloudwatchAlarmAction implements Serializable {
    private String alarmName;
    private String roleArn;
    private String stateReason;
    private String stateValue;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof CloudwatchAlarmAction)) {
            return false;
        }
        CloudwatchAlarmAction cloudwatchAlarmAction = (CloudwatchAlarmAction) obj;
        if ((cloudwatchAlarmAction.getRoleArn() == null) ^ (getRoleArn() == null)) {
            return false;
        }
        if (cloudwatchAlarmAction.getRoleArn() != null && !cloudwatchAlarmAction.getRoleArn().equals(getRoleArn())) {
            return false;
        }
        if ((cloudwatchAlarmAction.getAlarmName() == null) ^ (getAlarmName() == null)) {
            return false;
        }
        if (cloudwatchAlarmAction.getAlarmName() != null && !cloudwatchAlarmAction.getAlarmName().equals(getAlarmName())) {
            return false;
        }
        if ((cloudwatchAlarmAction.getStateReason() == null) ^ (getStateReason() == null)) {
            return false;
        }
        if (cloudwatchAlarmAction.getStateReason() != null && !cloudwatchAlarmAction.getStateReason().equals(getStateReason())) {
            return false;
        }
        if ((cloudwatchAlarmAction.getStateValue() == null) ^ (getStateValue() == null)) {
            return false;
        }
        return cloudwatchAlarmAction.getStateValue() == null || cloudwatchAlarmAction.getStateValue().equals(getStateValue());
    }

    public String getAlarmName() {
        return this.alarmName;
    }

    public String getRoleArn() {
        return this.roleArn;
    }

    public String getStateReason() {
        return this.stateReason;
    }

    public String getStateValue() {
        return this.stateValue;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((((getRoleArn() == null ? 0 : getRoleArn().hashCode()) + 31) * 31) + (getAlarmName() == null ? 0 : getAlarmName().hashCode())) * 31) + (getStateReason() == null ? 0 : getStateReason().hashCode())) * 31;
        if (getStateValue() != null) {
            i = getStateValue().hashCode();
        }
        return hashCode + i;
    }

    public void setAlarmName(String str) {
        this.alarmName = str;
    }

    public void setRoleArn(String str) {
        this.roleArn = str;
    }

    public void setStateReason(String str) {
        this.stateReason = str;
    }

    public void setStateValue(String str) {
        this.stateValue = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getRoleArn() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("roleArn: ");
            outline1072.append(getRoleArn());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getAlarmName() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("alarmName: ");
            outline1073.append(getAlarmName());
            outline1073.append(",");
            outline107.append(outline1073.toString());
        }
        if (getStateReason() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("stateReason: ");
            outline1074.append(getStateReason());
            outline1074.append(",");
            outline107.append(outline1074.toString());
        }
        if (getStateValue() != null) {
            StringBuilder outline1075 = GeneratedOutlineSupport1.outline107("stateValue: ");
            outline1075.append(getStateValue());
            outline107.append(outline1075.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public CloudwatchAlarmAction withAlarmName(String str) {
        this.alarmName = str;
        return this;
    }

    public CloudwatchAlarmAction withRoleArn(String str) {
        this.roleArn = str;
        return this;
    }

    public CloudwatchAlarmAction withStateReason(String str) {
        this.stateReason = str;
        return this;
    }

    public CloudwatchAlarmAction withStateValue(String str) {
        this.stateValue = str;
        return this;
    }
}
