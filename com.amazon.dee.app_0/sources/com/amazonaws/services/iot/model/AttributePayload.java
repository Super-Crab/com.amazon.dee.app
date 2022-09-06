package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes13.dex */
public class AttributePayload implements Serializable {
    private Map<String, String> attributes;
    private Boolean merge;

    public AttributePayload addattributesEntry(String str, String str2) {
        if (this.attributes == null) {
            this.attributes = new HashMap();
        }
        if (!this.attributes.containsKey(str)) {
            this.attributes.put(str, str2);
            return this;
        }
        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline78(str, GeneratedOutlineSupport1.outline107("Duplicated keys ("), ") are provided."));
    }

    public AttributePayload clearattributesEntries() {
        this.attributes = null;
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof AttributePayload)) {
            return false;
        }
        AttributePayload attributePayload = (AttributePayload) obj;
        if ((attributePayload.getAttributes() == null) ^ (getAttributes() == null)) {
            return false;
        }
        if (attributePayload.getAttributes() != null && !attributePayload.getAttributes().equals(getAttributes())) {
            return false;
        }
        if ((attributePayload.getMerge() == null) ^ (getMerge() == null)) {
            return false;
        }
        return attributePayload.getMerge() == null || attributePayload.getMerge().equals(getMerge());
    }

    public Map<String, String> getAttributes() {
        return this.attributes;
    }

    public Boolean getMerge() {
        return this.merge;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((getAttributes() == null ? 0 : getAttributes().hashCode()) + 31) * 31;
        if (getMerge() != null) {
            i = getMerge().hashCode();
        }
        return hashCode + i;
    }

    public Boolean isMerge() {
        return this.merge;
    }

    public void setAttributes(Map<String, String> map) {
        this.attributes = map;
    }

    public void setMerge(Boolean bool) {
        this.merge = bool;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getAttributes() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("attributes: ");
            outline1072.append(getAttributes());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getMerge() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("merge: ");
            outline1073.append(getMerge());
            outline107.append(outline1073.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public AttributePayload withAttributes(Map<String, String> map) {
        this.attributes = map;
        return this;
    }

    public AttributePayload withMerge(Boolean bool) {
        this.merge = bool;
        return this;
    }
}
