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
public class ThingGroupDocument implements Serializable {
    private Map<String, String> attributes;
    private List<String> parentGroupNames;
    private String thingGroupDescription;
    private String thingGroupId;
    private String thingGroupName;

    public ThingGroupDocument addattributesEntry(String str, String str2) {
        if (this.attributes == null) {
            this.attributes = new HashMap();
        }
        if (!this.attributes.containsKey(str)) {
            this.attributes.put(str, str2);
            return this;
        }
        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline78(str, GeneratedOutlineSupport1.outline107("Duplicated keys ("), ") are provided."));
    }

    public ThingGroupDocument clearattributesEntries() {
        this.attributes = null;
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ThingGroupDocument)) {
            return false;
        }
        ThingGroupDocument thingGroupDocument = (ThingGroupDocument) obj;
        if ((thingGroupDocument.getThingGroupName() == null) ^ (getThingGroupName() == null)) {
            return false;
        }
        if (thingGroupDocument.getThingGroupName() != null && !thingGroupDocument.getThingGroupName().equals(getThingGroupName())) {
            return false;
        }
        if ((thingGroupDocument.getThingGroupId() == null) ^ (getThingGroupId() == null)) {
            return false;
        }
        if (thingGroupDocument.getThingGroupId() != null && !thingGroupDocument.getThingGroupId().equals(getThingGroupId())) {
            return false;
        }
        if ((thingGroupDocument.getThingGroupDescription() == null) ^ (getThingGroupDescription() == null)) {
            return false;
        }
        if (thingGroupDocument.getThingGroupDescription() != null && !thingGroupDocument.getThingGroupDescription().equals(getThingGroupDescription())) {
            return false;
        }
        if ((thingGroupDocument.getAttributes() == null) ^ (getAttributes() == null)) {
            return false;
        }
        if (thingGroupDocument.getAttributes() != null && !thingGroupDocument.getAttributes().equals(getAttributes())) {
            return false;
        }
        if ((thingGroupDocument.getParentGroupNames() == null) ^ (getParentGroupNames() == null)) {
            return false;
        }
        return thingGroupDocument.getParentGroupNames() == null || thingGroupDocument.getParentGroupNames().equals(getParentGroupNames());
    }

    public Map<String, String> getAttributes() {
        return this.attributes;
    }

    public List<String> getParentGroupNames() {
        return this.parentGroupNames;
    }

    public String getThingGroupDescription() {
        return this.thingGroupDescription;
    }

    public String getThingGroupId() {
        return this.thingGroupId;
    }

    public String getThingGroupName() {
        return this.thingGroupName;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((((((getThingGroupName() == null ? 0 : getThingGroupName().hashCode()) + 31) * 31) + (getThingGroupId() == null ? 0 : getThingGroupId().hashCode())) * 31) + (getThingGroupDescription() == null ? 0 : getThingGroupDescription().hashCode())) * 31) + (getAttributes() == null ? 0 : getAttributes().hashCode())) * 31;
        if (getParentGroupNames() != null) {
            i = getParentGroupNames().hashCode();
        }
        return hashCode + i;
    }

    public void setAttributes(Map<String, String> map) {
        this.attributes = map;
    }

    public void setParentGroupNames(Collection<String> collection) {
        if (collection == null) {
            this.parentGroupNames = null;
        } else {
            this.parentGroupNames = new ArrayList(collection);
        }
    }

    public void setThingGroupDescription(String str) {
        this.thingGroupDescription = str;
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
        if (getThingGroupId() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("thingGroupId: ");
            outline1073.append(getThingGroupId());
            outline1073.append(",");
            outline107.append(outline1073.toString());
        }
        if (getThingGroupDescription() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("thingGroupDescription: ");
            outline1074.append(getThingGroupDescription());
            outline1074.append(",");
            outline107.append(outline1074.toString());
        }
        if (getAttributes() != null) {
            StringBuilder outline1075 = GeneratedOutlineSupport1.outline107("attributes: ");
            outline1075.append(getAttributes());
            outline1075.append(",");
            outline107.append(outline1075.toString());
        }
        if (getParentGroupNames() != null) {
            StringBuilder outline1076 = GeneratedOutlineSupport1.outline107("parentGroupNames: ");
            outline1076.append(getParentGroupNames());
            outline107.append(outline1076.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public ThingGroupDocument withAttributes(Map<String, String> map) {
        this.attributes = map;
        return this;
    }

    public ThingGroupDocument withParentGroupNames(String... strArr) {
        if (getParentGroupNames() == null) {
            this.parentGroupNames = new ArrayList(strArr.length);
        }
        for (String str : strArr) {
            this.parentGroupNames.add(str);
        }
        return this;
    }

    public ThingGroupDocument withThingGroupDescription(String str) {
        this.thingGroupDescription = str;
        return this;
    }

    public ThingGroupDocument withThingGroupId(String str) {
        this.thingGroupId = str;
        return this;
    }

    public ThingGroupDocument withThingGroupName(String str) {
        this.thingGroupName = str;
        return this;
    }

    public ThingGroupDocument withParentGroupNames(Collection<String> collection) {
        setParentGroupNames(collection);
        return this;
    }
}
