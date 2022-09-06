package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class IotEventsAction implements Serializable {
    private String inputName;
    private String messageId;
    private String roleArn;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof IotEventsAction)) {
            return false;
        }
        IotEventsAction iotEventsAction = (IotEventsAction) obj;
        if ((iotEventsAction.getInputName() == null) ^ (getInputName() == null)) {
            return false;
        }
        if (iotEventsAction.getInputName() != null && !iotEventsAction.getInputName().equals(getInputName())) {
            return false;
        }
        if ((iotEventsAction.getMessageId() == null) ^ (getMessageId() == null)) {
            return false;
        }
        if (iotEventsAction.getMessageId() != null && !iotEventsAction.getMessageId().equals(getMessageId())) {
            return false;
        }
        if ((iotEventsAction.getRoleArn() == null) ^ (getRoleArn() == null)) {
            return false;
        }
        return iotEventsAction.getRoleArn() == null || iotEventsAction.getRoleArn().equals(getRoleArn());
    }

    public String getInputName() {
        return this.inputName;
    }

    public String getMessageId() {
        return this.messageId;
    }

    public String getRoleArn() {
        return this.roleArn;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((getInputName() == null ? 0 : getInputName().hashCode()) + 31) * 31) + (getMessageId() == null ? 0 : getMessageId().hashCode())) * 31;
        if (getRoleArn() != null) {
            i = getRoleArn().hashCode();
        }
        return hashCode + i;
    }

    public void setInputName(String str) {
        this.inputName = str;
    }

    public void setMessageId(String str) {
        this.messageId = str;
    }

    public void setRoleArn(String str) {
        this.roleArn = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getInputName() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("inputName: ");
            outline1072.append(getInputName());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getMessageId() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("messageId: ");
            outline1073.append(getMessageId());
            outline1073.append(",");
            outline107.append(outline1073.toString());
        }
        if (getRoleArn() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("roleArn: ");
            outline1074.append(getRoleArn());
            outline107.append(outline1074.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public IotEventsAction withInputName(String str) {
        this.inputName = str;
        return this;
    }

    public IotEventsAction withMessageId(String str) {
        this.messageId = str;
        return this;
    }

    public IotEventsAction withRoleArn(String str) {
        this.roleArn = str;
        return this;
    }
}
