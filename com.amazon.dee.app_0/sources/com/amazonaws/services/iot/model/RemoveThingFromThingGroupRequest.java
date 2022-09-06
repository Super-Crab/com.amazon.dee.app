package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazonaws.AmazonWebServiceRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class RemoveThingFromThingGroupRequest extends AmazonWebServiceRequest implements Serializable {
    private String thingArn;
    private String thingGroupArn;
    private String thingGroupName;
    private String thingName;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof RemoveThingFromThingGroupRequest)) {
            return false;
        }
        RemoveThingFromThingGroupRequest removeThingFromThingGroupRequest = (RemoveThingFromThingGroupRequest) obj;
        if ((removeThingFromThingGroupRequest.getThingGroupName() == null) ^ (getThingGroupName() == null)) {
            return false;
        }
        if (removeThingFromThingGroupRequest.getThingGroupName() != null && !removeThingFromThingGroupRequest.getThingGroupName().equals(getThingGroupName())) {
            return false;
        }
        if ((removeThingFromThingGroupRequest.getThingGroupArn() == null) ^ (getThingGroupArn() == null)) {
            return false;
        }
        if (removeThingFromThingGroupRequest.getThingGroupArn() != null && !removeThingFromThingGroupRequest.getThingGroupArn().equals(getThingGroupArn())) {
            return false;
        }
        if ((removeThingFromThingGroupRequest.getThingName() == null) ^ (getThingName() == null)) {
            return false;
        }
        if (removeThingFromThingGroupRequest.getThingName() != null && !removeThingFromThingGroupRequest.getThingName().equals(getThingName())) {
            return false;
        }
        if ((removeThingFromThingGroupRequest.getThingArn() == null) ^ (getThingArn() == null)) {
            return false;
        }
        return removeThingFromThingGroupRequest.getThingArn() == null || removeThingFromThingGroupRequest.getThingArn().equals(getThingArn());
    }

    public String getThingArn() {
        return this.thingArn;
    }

    public String getThingGroupArn() {
        return this.thingGroupArn;
    }

    public String getThingGroupName() {
        return this.thingGroupName;
    }

    public String getThingName() {
        return this.thingName;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((((getThingGroupName() == null ? 0 : getThingGroupName().hashCode()) + 31) * 31) + (getThingGroupArn() == null ? 0 : getThingGroupArn().hashCode())) * 31) + (getThingName() == null ? 0 : getThingName().hashCode())) * 31;
        if (getThingArn() != null) {
            i = getThingArn().hashCode();
        }
        return hashCode + i;
    }

    public void setThingArn(String str) {
        this.thingArn = str;
    }

    public void setThingGroupArn(String str) {
        this.thingGroupArn = str;
    }

    public void setThingGroupName(String str) {
        this.thingGroupName = str;
    }

    public void setThingName(String str) {
        this.thingName = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getThingGroupName() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("thingGroupName: ");
            outline1072.append(getThingGroupName());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getThingGroupArn() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("thingGroupArn: ");
            outline1073.append(getThingGroupArn());
            outline1073.append(",");
            outline107.append(outline1073.toString());
        }
        if (getThingName() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("thingName: ");
            outline1074.append(getThingName());
            outline1074.append(",");
            outline107.append(outline1074.toString());
        }
        if (getThingArn() != null) {
            StringBuilder outline1075 = GeneratedOutlineSupport1.outline107("thingArn: ");
            outline1075.append(getThingArn());
            outline107.append(outline1075.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public RemoveThingFromThingGroupRequest withThingArn(String str) {
        this.thingArn = str;
        return this;
    }

    public RemoveThingFromThingGroupRequest withThingGroupArn(String str) {
        this.thingGroupArn = str;
        return this;
    }

    public RemoveThingFromThingGroupRequest withThingGroupName(String str) {
        this.thingGroupName = str;
        return this;
    }

    public RemoveThingFromThingGroupRequest withThingName(String str) {
        this.thingName = str;
        return this;
    }
}
