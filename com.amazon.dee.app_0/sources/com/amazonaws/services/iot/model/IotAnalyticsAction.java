package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class IotAnalyticsAction implements Serializable {
    private String channelArn;
    private String channelName;
    private String roleArn;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof IotAnalyticsAction)) {
            return false;
        }
        IotAnalyticsAction iotAnalyticsAction = (IotAnalyticsAction) obj;
        if ((iotAnalyticsAction.getChannelArn() == null) ^ (getChannelArn() == null)) {
            return false;
        }
        if (iotAnalyticsAction.getChannelArn() != null && !iotAnalyticsAction.getChannelArn().equals(getChannelArn())) {
            return false;
        }
        if ((iotAnalyticsAction.getChannelName() == null) ^ (getChannelName() == null)) {
            return false;
        }
        if (iotAnalyticsAction.getChannelName() != null && !iotAnalyticsAction.getChannelName().equals(getChannelName())) {
            return false;
        }
        if ((iotAnalyticsAction.getRoleArn() == null) ^ (getRoleArn() == null)) {
            return false;
        }
        return iotAnalyticsAction.getRoleArn() == null || iotAnalyticsAction.getRoleArn().equals(getRoleArn());
    }

    public String getChannelArn() {
        return this.channelArn;
    }

    public String getChannelName() {
        return this.channelName;
    }

    public String getRoleArn() {
        return this.roleArn;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((getChannelArn() == null ? 0 : getChannelArn().hashCode()) + 31) * 31) + (getChannelName() == null ? 0 : getChannelName().hashCode())) * 31;
        if (getRoleArn() != null) {
            i = getRoleArn().hashCode();
        }
        return hashCode + i;
    }

    public void setChannelArn(String str) {
        this.channelArn = str;
    }

    public void setChannelName(String str) {
        this.channelName = str;
    }

    public void setRoleArn(String str) {
        this.roleArn = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getChannelArn() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("channelArn: ");
            outline1072.append(getChannelArn());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getChannelName() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("channelName: ");
            outline1073.append(getChannelName());
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

    public IotAnalyticsAction withChannelArn(String str) {
        this.channelArn = str;
        return this;
    }

    public IotAnalyticsAction withChannelName(String str) {
        this.channelName = str;
        return this;
    }

    public IotAnalyticsAction withRoleArn(String str) {
        this.roleArn = str;
        return this;
    }
}
