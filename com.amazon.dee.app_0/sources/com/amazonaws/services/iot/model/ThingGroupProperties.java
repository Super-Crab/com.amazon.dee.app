package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class ThingGroupProperties implements Serializable {
    private AttributePayload attributePayload;
    private String thingGroupDescription;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ThingGroupProperties)) {
            return false;
        }
        ThingGroupProperties thingGroupProperties = (ThingGroupProperties) obj;
        if ((thingGroupProperties.getThingGroupDescription() == null) ^ (getThingGroupDescription() == null)) {
            return false;
        }
        if (thingGroupProperties.getThingGroupDescription() != null && !thingGroupProperties.getThingGroupDescription().equals(getThingGroupDescription())) {
            return false;
        }
        if ((thingGroupProperties.getAttributePayload() == null) ^ (getAttributePayload() == null)) {
            return false;
        }
        return thingGroupProperties.getAttributePayload() == null || thingGroupProperties.getAttributePayload().equals(getAttributePayload());
    }

    public AttributePayload getAttributePayload() {
        return this.attributePayload;
    }

    public String getThingGroupDescription() {
        return this.thingGroupDescription;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((getThingGroupDescription() == null ? 0 : getThingGroupDescription().hashCode()) + 31) * 31;
        if (getAttributePayload() != null) {
            i = getAttributePayload().hashCode();
        }
        return hashCode + i;
    }

    public void setAttributePayload(AttributePayload attributePayload) {
        this.attributePayload = attributePayload;
    }

    public void setThingGroupDescription(String str) {
        this.thingGroupDescription = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getThingGroupDescription() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("thingGroupDescription: ");
            outline1072.append(getThingGroupDescription());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getAttributePayload() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("attributePayload: ");
            outline1073.append(getAttributePayload());
            outline107.append(outline1073.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public ThingGroupProperties withAttributePayload(AttributePayload attributePayload) {
        this.attributePayload = attributePayload;
        return this;
    }

    public ThingGroupProperties withThingGroupDescription(String str) {
        this.thingGroupDescription = str;
        return this;
    }
}
