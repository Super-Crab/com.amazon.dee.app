package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class ThingTypeDefinition implements Serializable {
    private String thingTypeArn;
    private ThingTypeMetadata thingTypeMetadata;
    private String thingTypeName;
    private ThingTypeProperties thingTypeProperties;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ThingTypeDefinition)) {
            return false;
        }
        ThingTypeDefinition thingTypeDefinition = (ThingTypeDefinition) obj;
        if ((thingTypeDefinition.getThingTypeName() == null) ^ (getThingTypeName() == null)) {
            return false;
        }
        if (thingTypeDefinition.getThingTypeName() != null && !thingTypeDefinition.getThingTypeName().equals(getThingTypeName())) {
            return false;
        }
        if ((thingTypeDefinition.getThingTypeArn() == null) ^ (getThingTypeArn() == null)) {
            return false;
        }
        if (thingTypeDefinition.getThingTypeArn() != null && !thingTypeDefinition.getThingTypeArn().equals(getThingTypeArn())) {
            return false;
        }
        if ((thingTypeDefinition.getThingTypeProperties() == null) ^ (getThingTypeProperties() == null)) {
            return false;
        }
        if (thingTypeDefinition.getThingTypeProperties() != null && !thingTypeDefinition.getThingTypeProperties().equals(getThingTypeProperties())) {
            return false;
        }
        if ((thingTypeDefinition.getThingTypeMetadata() == null) ^ (getThingTypeMetadata() == null)) {
            return false;
        }
        return thingTypeDefinition.getThingTypeMetadata() == null || thingTypeDefinition.getThingTypeMetadata().equals(getThingTypeMetadata());
    }

    public String getThingTypeArn() {
        return this.thingTypeArn;
    }

    public ThingTypeMetadata getThingTypeMetadata() {
        return this.thingTypeMetadata;
    }

    public String getThingTypeName() {
        return this.thingTypeName;
    }

    public ThingTypeProperties getThingTypeProperties() {
        return this.thingTypeProperties;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((((getThingTypeName() == null ? 0 : getThingTypeName().hashCode()) + 31) * 31) + (getThingTypeArn() == null ? 0 : getThingTypeArn().hashCode())) * 31) + (getThingTypeProperties() == null ? 0 : getThingTypeProperties().hashCode())) * 31;
        if (getThingTypeMetadata() != null) {
            i = getThingTypeMetadata().hashCode();
        }
        return hashCode + i;
    }

    public void setThingTypeArn(String str) {
        this.thingTypeArn = str;
    }

    public void setThingTypeMetadata(ThingTypeMetadata thingTypeMetadata) {
        this.thingTypeMetadata = thingTypeMetadata;
    }

    public void setThingTypeName(String str) {
        this.thingTypeName = str;
    }

    public void setThingTypeProperties(ThingTypeProperties thingTypeProperties) {
        this.thingTypeProperties = thingTypeProperties;
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
        if (getThingTypeProperties() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("thingTypeProperties: ");
            outline1074.append(getThingTypeProperties());
            outline1074.append(",");
            outline107.append(outline1074.toString());
        }
        if (getThingTypeMetadata() != null) {
            StringBuilder outline1075 = GeneratedOutlineSupport1.outline107("thingTypeMetadata: ");
            outline1075.append(getThingTypeMetadata());
            outline107.append(outline1075.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public ThingTypeDefinition withThingTypeArn(String str) {
        this.thingTypeArn = str;
        return this;
    }

    public ThingTypeDefinition withThingTypeMetadata(ThingTypeMetadata thingTypeMetadata) {
        this.thingTypeMetadata = thingTypeMetadata;
        return this;
    }

    public ThingTypeDefinition withThingTypeName(String str) {
        this.thingTypeName = str;
        return this;
    }

    public ThingTypeDefinition withThingTypeProperties(ThingTypeProperties thingTypeProperties) {
        this.thingTypeProperties = thingTypeProperties;
        return this;
    }
}
