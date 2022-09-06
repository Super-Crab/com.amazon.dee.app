package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes13.dex */
public class ThingAttribute implements Serializable {
    private Map<String, String> attributes;
    private String thingArn;
    private String thingName;
    private String thingTypeName;
    private Long version;

    public ThingAttribute addattributesEntry(String str, String str2) {
        if (this.attributes == null) {
            this.attributes = new HashMap();
        }
        if (!this.attributes.containsKey(str)) {
            this.attributes.put(str, str2);
            return this;
        }
        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline78(str, GeneratedOutlineSupport1.outline107("Duplicated keys ("), ") are provided."));
    }

    public ThingAttribute clearattributesEntries() {
        this.attributes = null;
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ThingAttribute)) {
            return false;
        }
        ThingAttribute thingAttribute = (ThingAttribute) obj;
        if ((thingAttribute.getThingName() == null) ^ (getThingName() == null)) {
            return false;
        }
        if (thingAttribute.getThingName() != null && !thingAttribute.getThingName().equals(getThingName())) {
            return false;
        }
        if ((thingAttribute.getThingTypeName() == null) ^ (getThingTypeName() == null)) {
            return false;
        }
        if (thingAttribute.getThingTypeName() != null && !thingAttribute.getThingTypeName().equals(getThingTypeName())) {
            return false;
        }
        if ((thingAttribute.getThingArn() == null) ^ (getThingArn() == null)) {
            return false;
        }
        if (thingAttribute.getThingArn() != null && !thingAttribute.getThingArn().equals(getThingArn())) {
            return false;
        }
        if ((thingAttribute.getAttributes() == null) ^ (getAttributes() == null)) {
            return false;
        }
        if (thingAttribute.getAttributes() != null && !thingAttribute.getAttributes().equals(getAttributes())) {
            return false;
        }
        if ((thingAttribute.getVersion() == null) ^ (getVersion() == null)) {
            return false;
        }
        return thingAttribute.getVersion() == null || thingAttribute.getVersion().equals(getVersion());
    }

    public Map<String, String> getAttributes() {
        return this.attributes;
    }

    public String getThingArn() {
        return this.thingArn;
    }

    public String getThingName() {
        return this.thingName;
    }

    public String getThingTypeName() {
        return this.thingTypeName;
    }

    public Long getVersion() {
        return this.version;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((((((getThingName() == null ? 0 : getThingName().hashCode()) + 31) * 31) + (getThingTypeName() == null ? 0 : getThingTypeName().hashCode())) * 31) + (getThingArn() == null ? 0 : getThingArn().hashCode())) * 31) + (getAttributes() == null ? 0 : getAttributes().hashCode())) * 31;
        if (getVersion() != null) {
            i = getVersion().hashCode();
        }
        return hashCode + i;
    }

    public void setAttributes(Map<String, String> map) {
        this.attributes = map;
    }

    public void setThingArn(String str) {
        this.thingArn = str;
    }

    public void setThingName(String str) {
        this.thingName = str;
    }

    public void setThingTypeName(String str) {
        this.thingTypeName = str;
    }

    public void setVersion(Long l) {
        this.version = l;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getThingName() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("thingName: ");
            outline1072.append(getThingName());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getThingTypeName() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("thingTypeName: ");
            outline1073.append(getThingTypeName());
            outline1073.append(",");
            outline107.append(outline1073.toString());
        }
        if (getThingArn() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("thingArn: ");
            outline1074.append(getThingArn());
            outline1074.append(",");
            outline107.append(outline1074.toString());
        }
        if (getAttributes() != null) {
            StringBuilder outline1075 = GeneratedOutlineSupport1.outline107("attributes: ");
            outline1075.append(getAttributes());
            outline1075.append(",");
            outline107.append(outline1075.toString());
        }
        if (getVersion() != null) {
            StringBuilder outline1076 = GeneratedOutlineSupport1.outline107("version: ");
            outline1076.append(getVersion());
            outline107.append(outline1076.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public ThingAttribute withAttributes(Map<String, String> map) {
        this.attributes = map;
        return this;
    }

    public ThingAttribute withThingArn(String str) {
        this.thingArn = str;
        return this;
    }

    public ThingAttribute withThingName(String str) {
        this.thingName = str;
        return this;
    }

    public ThingAttribute withThingTypeName(String str) {
        this.thingTypeName = str;
        return this;
    }

    public ThingAttribute withVersion(Long l) {
        this.version = l;
        return this;
    }
}
