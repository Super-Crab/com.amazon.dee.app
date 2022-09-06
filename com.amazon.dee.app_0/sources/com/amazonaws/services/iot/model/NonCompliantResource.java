package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes13.dex */
public class NonCompliantResource implements Serializable {
    private Map<String, String> additionalInfo;
    private ResourceIdentifier resourceIdentifier;
    private String resourceType;

    public NonCompliantResource addadditionalInfoEntry(String str, String str2) {
        if (this.additionalInfo == null) {
            this.additionalInfo = new HashMap();
        }
        if (!this.additionalInfo.containsKey(str)) {
            this.additionalInfo.put(str, str2);
            return this;
        }
        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline78(str, GeneratedOutlineSupport1.outline107("Duplicated keys ("), ") are provided."));
    }

    public NonCompliantResource clearadditionalInfoEntries() {
        this.additionalInfo = null;
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof NonCompliantResource)) {
            return false;
        }
        NonCompliantResource nonCompliantResource = (NonCompliantResource) obj;
        if ((nonCompliantResource.getResourceType() == null) ^ (getResourceType() == null)) {
            return false;
        }
        if (nonCompliantResource.getResourceType() != null && !nonCompliantResource.getResourceType().equals(getResourceType())) {
            return false;
        }
        if ((nonCompliantResource.getResourceIdentifier() == null) ^ (getResourceIdentifier() == null)) {
            return false;
        }
        if (nonCompliantResource.getResourceIdentifier() != null && !nonCompliantResource.getResourceIdentifier().equals(getResourceIdentifier())) {
            return false;
        }
        if ((nonCompliantResource.getAdditionalInfo() == null) ^ (getAdditionalInfo() == null)) {
            return false;
        }
        return nonCompliantResource.getAdditionalInfo() == null || nonCompliantResource.getAdditionalInfo().equals(getAdditionalInfo());
    }

    public Map<String, String> getAdditionalInfo() {
        return this.additionalInfo;
    }

    public ResourceIdentifier getResourceIdentifier() {
        return this.resourceIdentifier;
    }

    public String getResourceType() {
        return this.resourceType;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((getResourceType() == null ? 0 : getResourceType().hashCode()) + 31) * 31) + (getResourceIdentifier() == null ? 0 : getResourceIdentifier().hashCode())) * 31;
        if (getAdditionalInfo() != null) {
            i = getAdditionalInfo().hashCode();
        }
        return hashCode + i;
    }

    public void setAdditionalInfo(Map<String, String> map) {
        this.additionalInfo = map;
    }

    public void setResourceIdentifier(ResourceIdentifier resourceIdentifier) {
        this.resourceIdentifier = resourceIdentifier;
    }

    public void setResourceType(String str) {
        this.resourceType = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getResourceType() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("resourceType: ");
            outline1072.append(getResourceType());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getResourceIdentifier() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("resourceIdentifier: ");
            outline1073.append(getResourceIdentifier());
            outline1073.append(",");
            outline107.append(outline1073.toString());
        }
        if (getAdditionalInfo() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("additionalInfo: ");
            outline1074.append(getAdditionalInfo());
            outline107.append(outline1074.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public NonCompliantResource withAdditionalInfo(Map<String, String> map) {
        this.additionalInfo = map;
        return this;
    }

    public NonCompliantResource withResourceIdentifier(ResourceIdentifier resourceIdentifier) {
        this.resourceIdentifier = resourceIdentifier;
        return this;
    }

    public NonCompliantResource withResourceType(String str) {
        this.resourceType = str;
        return this;
    }

    public void setResourceType(ResourceType resourceType) {
        this.resourceType = resourceType.toString();
    }

    public NonCompliantResource withResourceType(ResourceType resourceType) {
        this.resourceType = resourceType.toString();
        return this;
    }
}
