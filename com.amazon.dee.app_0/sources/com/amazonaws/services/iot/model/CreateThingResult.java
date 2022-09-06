package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class CreateThingResult implements Serializable {
    private String thingArn;
    private String thingId;
    private String thingName;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof CreateThingResult)) {
            return false;
        }
        CreateThingResult createThingResult = (CreateThingResult) obj;
        if ((createThingResult.getThingName() == null) ^ (getThingName() == null)) {
            return false;
        }
        if (createThingResult.getThingName() != null && !createThingResult.getThingName().equals(getThingName())) {
            return false;
        }
        if ((createThingResult.getThingArn() == null) ^ (getThingArn() == null)) {
            return false;
        }
        if (createThingResult.getThingArn() != null && !createThingResult.getThingArn().equals(getThingArn())) {
            return false;
        }
        if ((createThingResult.getThingId() == null) ^ (getThingId() == null)) {
            return false;
        }
        return createThingResult.getThingId() == null || createThingResult.getThingId().equals(getThingId());
    }

    public String getThingArn() {
        return this.thingArn;
    }

    public String getThingId() {
        return this.thingId;
    }

    public String getThingName() {
        return this.thingName;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((getThingName() == null ? 0 : getThingName().hashCode()) + 31) * 31) + (getThingArn() == null ? 0 : getThingArn().hashCode())) * 31;
        if (getThingId() != null) {
            i = getThingId().hashCode();
        }
        return hashCode + i;
    }

    public void setThingArn(String str) {
        this.thingArn = str;
    }

    public void setThingId(String str) {
        this.thingId = str;
    }

    public void setThingName(String str) {
        this.thingName = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getThingName() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("thingName: ");
            outline1072.append(getThingName());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getThingArn() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("thingArn: ");
            outline1073.append(getThingArn());
            outline1073.append(",");
            outline107.append(outline1073.toString());
        }
        if (getThingId() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("thingId: ");
            outline1074.append(getThingId());
            outline107.append(outline1074.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public CreateThingResult withThingArn(String str) {
        this.thingArn = str;
        return this;
    }

    public CreateThingResult withThingId(String str) {
        this.thingId = str;
        return this;
    }

    public CreateThingResult withThingName(String str) {
        this.thingName = str;
        return this;
    }
}
