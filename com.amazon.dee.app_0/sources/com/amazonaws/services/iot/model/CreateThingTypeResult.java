package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class CreateThingTypeResult implements Serializable {
    private String thingTypeArn;
    private String thingTypeId;
    private String thingTypeName;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof CreateThingTypeResult)) {
            return false;
        }
        CreateThingTypeResult createThingTypeResult = (CreateThingTypeResult) obj;
        if ((createThingTypeResult.getThingTypeName() == null) ^ (getThingTypeName() == null)) {
            return false;
        }
        if (createThingTypeResult.getThingTypeName() != null && !createThingTypeResult.getThingTypeName().equals(getThingTypeName())) {
            return false;
        }
        if ((createThingTypeResult.getThingTypeArn() == null) ^ (getThingTypeArn() == null)) {
            return false;
        }
        if (createThingTypeResult.getThingTypeArn() != null && !createThingTypeResult.getThingTypeArn().equals(getThingTypeArn())) {
            return false;
        }
        if ((createThingTypeResult.getThingTypeId() == null) ^ (getThingTypeId() == null)) {
            return false;
        }
        return createThingTypeResult.getThingTypeId() == null || createThingTypeResult.getThingTypeId().equals(getThingTypeId());
    }

    public String getThingTypeArn() {
        return this.thingTypeArn;
    }

    public String getThingTypeId() {
        return this.thingTypeId;
    }

    public String getThingTypeName() {
        return this.thingTypeName;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((getThingTypeName() == null ? 0 : getThingTypeName().hashCode()) + 31) * 31) + (getThingTypeArn() == null ? 0 : getThingTypeArn().hashCode())) * 31;
        if (getThingTypeId() != null) {
            i = getThingTypeId().hashCode();
        }
        return hashCode + i;
    }

    public void setThingTypeArn(String str) {
        this.thingTypeArn = str;
    }

    public void setThingTypeId(String str) {
        this.thingTypeId = str;
    }

    public void setThingTypeName(String str) {
        this.thingTypeName = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getThingTypeName() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("thingTypeName: ");
            outline1072.append(getThingTypeName());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getThingTypeArn() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("thingTypeArn: ");
            outline1073.append(getThingTypeArn());
            outline1073.append(",");
            outline107.append(outline1073.toString());
        }
        if (getThingTypeId() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("thingTypeId: ");
            outline1074.append(getThingTypeId());
            outline107.append(outline1074.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public CreateThingTypeResult withThingTypeArn(String str) {
        this.thingTypeArn = str;
        return this;
    }

    public CreateThingTypeResult withThingTypeId(String str) {
        this.thingTypeId = str;
        return this;
    }

    public CreateThingTypeResult withThingTypeName(String str) {
        this.thingTypeName = str;
        return this;
    }
}
