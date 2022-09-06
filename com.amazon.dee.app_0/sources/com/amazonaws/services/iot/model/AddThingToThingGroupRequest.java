package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazonaws.AmazonWebServiceRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class AddThingToThingGroupRequest extends AmazonWebServiceRequest implements Serializable {
    private Boolean overrideDynamicGroups;
    private String thingArn;
    private String thingGroupArn;
    private String thingGroupName;
    private String thingName;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof AddThingToThingGroupRequest)) {
            return false;
        }
        AddThingToThingGroupRequest addThingToThingGroupRequest = (AddThingToThingGroupRequest) obj;
        if ((addThingToThingGroupRequest.getThingGroupName() == null) ^ (getThingGroupName() == null)) {
            return false;
        }
        if (addThingToThingGroupRequest.getThingGroupName() != null && !addThingToThingGroupRequest.getThingGroupName().equals(getThingGroupName())) {
            return false;
        }
        if ((addThingToThingGroupRequest.getThingGroupArn() == null) ^ (getThingGroupArn() == null)) {
            return false;
        }
        if (addThingToThingGroupRequest.getThingGroupArn() != null && !addThingToThingGroupRequest.getThingGroupArn().equals(getThingGroupArn())) {
            return false;
        }
        if ((addThingToThingGroupRequest.getThingName() == null) ^ (getThingName() == null)) {
            return false;
        }
        if (addThingToThingGroupRequest.getThingName() != null && !addThingToThingGroupRequest.getThingName().equals(getThingName())) {
            return false;
        }
        if ((addThingToThingGroupRequest.getThingArn() == null) ^ (getThingArn() == null)) {
            return false;
        }
        if (addThingToThingGroupRequest.getThingArn() != null && !addThingToThingGroupRequest.getThingArn().equals(getThingArn())) {
            return false;
        }
        if ((addThingToThingGroupRequest.getOverrideDynamicGroups() == null) ^ (getOverrideDynamicGroups() == null)) {
            return false;
        }
        return addThingToThingGroupRequest.getOverrideDynamicGroups() == null || addThingToThingGroupRequest.getOverrideDynamicGroups().equals(getOverrideDynamicGroups());
    }

    public Boolean getOverrideDynamicGroups() {
        return this.overrideDynamicGroups;
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
        int hashCode = ((((((((getThingGroupName() == null ? 0 : getThingGroupName().hashCode()) + 31) * 31) + (getThingGroupArn() == null ? 0 : getThingGroupArn().hashCode())) * 31) + (getThingName() == null ? 0 : getThingName().hashCode())) * 31) + (getThingArn() == null ? 0 : getThingArn().hashCode())) * 31;
        if (getOverrideDynamicGroups() != null) {
            i = getOverrideDynamicGroups().hashCode();
        }
        return hashCode + i;
    }

    public Boolean isOverrideDynamicGroups() {
        return this.overrideDynamicGroups;
    }

    public void setOverrideDynamicGroups(Boolean bool) {
        this.overrideDynamicGroups = bool;
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
            outline1075.append(",");
            outline107.append(outline1075.toString());
        }
        if (getOverrideDynamicGroups() != null) {
            StringBuilder outline1076 = GeneratedOutlineSupport1.outline107("overrideDynamicGroups: ");
            outline1076.append(getOverrideDynamicGroups());
            outline107.append(outline1076.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public AddThingToThingGroupRequest withOverrideDynamicGroups(Boolean bool) {
        this.overrideDynamicGroups = bool;
        return this;
    }

    public AddThingToThingGroupRequest withThingArn(String str) {
        this.thingArn = str;
        return this;
    }

    public AddThingToThingGroupRequest withThingGroupArn(String str) {
        this.thingGroupArn = str;
        return this;
    }

    public AddThingToThingGroupRequest withThingGroupName(String str) {
        this.thingGroupName = str;
        return this;
    }

    public AddThingToThingGroupRequest withThingName(String str) {
        this.thingName = str;
        return this;
    }
}
