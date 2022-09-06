package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/* loaded from: classes13.dex */
public class ThingDocument implements Serializable {
    private Map<String, String> attributes;
    private ThingConnectivity connectivity;
    private String shadow;
    private List<String> thingGroupNames;
    private String thingId;
    private String thingName;
    private String thingTypeName;

    public ThingDocument addattributesEntry(String str, String str2) {
        if (this.attributes == null) {
            this.attributes = new HashMap();
        }
        if (!this.attributes.containsKey(str)) {
            this.attributes.put(str, str2);
            return this;
        }
        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline78(str, GeneratedOutlineSupport1.outline107("Duplicated keys ("), ") are provided."));
    }

    public ThingDocument clearattributesEntries() {
        this.attributes = null;
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ThingDocument)) {
            return false;
        }
        ThingDocument thingDocument = (ThingDocument) obj;
        if ((thingDocument.getThingName() == null) ^ (getThingName() == null)) {
            return false;
        }
        if (thingDocument.getThingName() != null && !thingDocument.getThingName().equals(getThingName())) {
            return false;
        }
        if ((thingDocument.getThingId() == null) ^ (getThingId() == null)) {
            return false;
        }
        if (thingDocument.getThingId() != null && !thingDocument.getThingId().equals(getThingId())) {
            return false;
        }
        if ((thingDocument.getThingTypeName() == null) ^ (getThingTypeName() == null)) {
            return false;
        }
        if (thingDocument.getThingTypeName() != null && !thingDocument.getThingTypeName().equals(getThingTypeName())) {
            return false;
        }
        if ((thingDocument.getThingGroupNames() == null) ^ (getThingGroupNames() == null)) {
            return false;
        }
        if (thingDocument.getThingGroupNames() != null && !thingDocument.getThingGroupNames().equals(getThingGroupNames())) {
            return false;
        }
        if ((thingDocument.getAttributes() == null) ^ (getAttributes() == null)) {
            return false;
        }
        if (thingDocument.getAttributes() != null && !thingDocument.getAttributes().equals(getAttributes())) {
            return false;
        }
        if ((thingDocument.getShadow() == null) ^ (getShadow() == null)) {
            return false;
        }
        if (thingDocument.getShadow() != null && !thingDocument.getShadow().equals(getShadow())) {
            return false;
        }
        if ((thingDocument.getConnectivity() == null) ^ (getConnectivity() == null)) {
            return false;
        }
        return thingDocument.getConnectivity() == null || thingDocument.getConnectivity().equals(getConnectivity());
    }

    public Map<String, String> getAttributes() {
        return this.attributes;
    }

    public ThingConnectivity getConnectivity() {
        return this.connectivity;
    }

    public String getShadow() {
        return this.shadow;
    }

    public List<String> getThingGroupNames() {
        return this.thingGroupNames;
    }

    public String getThingId() {
        return this.thingId;
    }

    public String getThingName() {
        return this.thingName;
    }

    public String getThingTypeName() {
        return this.thingTypeName;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((((((((((getThingName() == null ? 0 : getThingName().hashCode()) + 31) * 31) + (getThingId() == null ? 0 : getThingId().hashCode())) * 31) + (getThingTypeName() == null ? 0 : getThingTypeName().hashCode())) * 31) + (getThingGroupNames() == null ? 0 : getThingGroupNames().hashCode())) * 31) + (getAttributes() == null ? 0 : getAttributes().hashCode())) * 31) + (getShadow() == null ? 0 : getShadow().hashCode())) * 31;
        if (getConnectivity() != null) {
            i = getConnectivity().hashCode();
        }
        return hashCode + i;
    }

    public void setAttributes(Map<String, String> map) {
        this.attributes = map;
    }

    public void setConnectivity(ThingConnectivity thingConnectivity) {
        this.connectivity = thingConnectivity;
    }

    public void setShadow(String str) {
        this.shadow = str;
    }

    public void setThingGroupNames(Collection<String> collection) {
        if (collection == null) {
            this.thingGroupNames = null;
        } else {
            this.thingGroupNames = new ArrayList(collection);
        }
    }

    public void setThingId(String str) {
        this.thingId = str;
    }

    public void setThingName(String str) {
        this.thingName = str;
    }

    public void setThingTypeName(String str) {
        this.thingTypeName = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getThingName() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("thingName: ");
            outline1072.append(getThingName());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getThingId() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("thingId: ");
            outline1073.append(getThingId());
            outline1073.append(",");
            outline107.append(outline1073.toString());
        }
        if (getThingTypeName() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("thingTypeName: ");
            outline1074.append(getThingTypeName());
            outline1074.append(",");
            outline107.append(outline1074.toString());
        }
        if (getThingGroupNames() != null) {
            StringBuilder outline1075 = GeneratedOutlineSupport1.outline107("thingGroupNames: ");
            outline1075.append(getThingGroupNames());
            outline1075.append(",");
            outline107.append(outline1075.toString());
        }
        if (getAttributes() != null) {
            StringBuilder outline1076 = GeneratedOutlineSupport1.outline107("attributes: ");
            outline1076.append(getAttributes());
            outline1076.append(",");
            outline107.append(outline1076.toString());
        }
        if (getShadow() != null) {
            StringBuilder outline1077 = GeneratedOutlineSupport1.outline107("shadow: ");
            outline1077.append(getShadow());
            outline1077.append(",");
            outline107.append(outline1077.toString());
        }
        if (getConnectivity() != null) {
            StringBuilder outline1078 = GeneratedOutlineSupport1.outline107("connectivity: ");
            outline1078.append(getConnectivity());
            outline107.append(outline1078.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public ThingDocument withAttributes(Map<String, String> map) {
        this.attributes = map;
        return this;
    }

    public ThingDocument withConnectivity(ThingConnectivity thingConnectivity) {
        this.connectivity = thingConnectivity;
        return this;
    }

    public ThingDocument withShadow(String str) {
        this.shadow = str;
        return this;
    }

    public ThingDocument withThingGroupNames(String... strArr) {
        if (getThingGroupNames() == null) {
            this.thingGroupNames = new ArrayList(strArr.length);
        }
        for (String str : strArr) {
            this.thingGroupNames.add(str);
        }
        return this;
    }

    public ThingDocument withThingId(String str) {
        this.thingId = str;
        return this;
    }

    public ThingDocument withThingName(String str) {
        this.thingName = str;
        return this;
    }

    public ThingDocument withThingTypeName(String str) {
        this.thingTypeName = str;
        return this;
    }

    public ThingDocument withThingGroupNames(Collection<String> collection) {
        setThingGroupNames(collection);
        return this;
    }
}
