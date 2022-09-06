package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class CreateThingGroupResult implements Serializable {
    private String thingGroupArn;
    private String thingGroupId;
    private String thingGroupName;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof CreateThingGroupResult)) {
            return false;
        }
        CreateThingGroupResult createThingGroupResult = (CreateThingGroupResult) obj;
        if ((createThingGroupResult.getThingGroupName() == null) ^ (getThingGroupName() == null)) {
            return false;
        }
        if (createThingGroupResult.getThingGroupName() != null && !createThingGroupResult.getThingGroupName().equals(getThingGroupName())) {
            return false;
        }
        if ((createThingGroupResult.getThingGroupArn() == null) ^ (getThingGroupArn() == null)) {
            return false;
        }
        if (createThingGroupResult.getThingGroupArn() != null && !createThingGroupResult.getThingGroupArn().equals(getThingGroupArn())) {
            return false;
        }
        if ((createThingGroupResult.getThingGroupId() == null) ^ (getThingGroupId() == null)) {
            return false;
        }
        return createThingGroupResult.getThingGroupId() == null || createThingGroupResult.getThingGroupId().equals(getThingGroupId());
    }

    public String getThingGroupArn() {
        return this.thingGroupArn;
    }

    public String getThingGroupId() {
        return this.thingGroupId;
    }

    public String getThingGroupName() {
        return this.thingGroupName;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((getThingGroupName() == null ? 0 : getThingGroupName().hashCode()) + 31) * 31) + (getThingGroupArn() == null ? 0 : getThingGroupArn().hashCode())) * 31;
        if (getThingGroupId() != null) {
            i = getThingGroupId().hashCode();
        }
        return hashCode + i;
    }

    public void setThingGroupArn(String str) {
        this.thingGroupArn = str;
    }

    public void setThingGroupId(String str) {
        this.thingGroupId = str;
    }

    public void setThingGroupName(String str) {
        this.thingGroupName = str;
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
        if (getThingGroupId() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("thingGroupId: ");
            outline1074.append(getThingGroupId());
            outline107.append(outline1074.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public CreateThingGroupResult withThingGroupArn(String str) {
        this.thingGroupArn = str;
        return this;
    }

    public CreateThingGroupResult withThingGroupId(String str) {
        this.thingGroupId = str;
        return this;
    }

    public CreateThingGroupResult withThingGroupName(String str) {
        this.thingGroupName = str;
        return this;
    }
}
