package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazonaws.AmazonWebServiceRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class UpdateThingRequest extends AmazonWebServiceRequest implements Serializable {
    private AttributePayload attributePayload;
    private Long expectedVersion;
    private Boolean removeThingType;
    private String thingName;
    private String thingTypeName;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof UpdateThingRequest)) {
            return false;
        }
        UpdateThingRequest updateThingRequest = (UpdateThingRequest) obj;
        if ((updateThingRequest.getThingName() == null) ^ (getThingName() == null)) {
            return false;
        }
        if (updateThingRequest.getThingName() != null && !updateThingRequest.getThingName().equals(getThingName())) {
            return false;
        }
        if ((updateThingRequest.getThingTypeName() == null) ^ (getThingTypeName() == null)) {
            return false;
        }
        if (updateThingRequest.getThingTypeName() != null && !updateThingRequest.getThingTypeName().equals(getThingTypeName())) {
            return false;
        }
        if ((updateThingRequest.getAttributePayload() == null) ^ (getAttributePayload() == null)) {
            return false;
        }
        if (updateThingRequest.getAttributePayload() != null && !updateThingRequest.getAttributePayload().equals(getAttributePayload())) {
            return false;
        }
        if ((updateThingRequest.getExpectedVersion() == null) ^ (getExpectedVersion() == null)) {
            return false;
        }
        if (updateThingRequest.getExpectedVersion() != null && !updateThingRequest.getExpectedVersion().equals(getExpectedVersion())) {
            return false;
        }
        if ((updateThingRequest.getRemoveThingType() == null) ^ (getRemoveThingType() == null)) {
            return false;
        }
        return updateThingRequest.getRemoveThingType() == null || updateThingRequest.getRemoveThingType().equals(getRemoveThingType());
    }

    public AttributePayload getAttributePayload() {
        return this.attributePayload;
    }

    public Long getExpectedVersion() {
        return this.expectedVersion;
    }

    public Boolean getRemoveThingType() {
        return this.removeThingType;
    }

    public String getThingName() {
        return this.thingName;
    }

    public String getThingTypeName() {
        return this.thingTypeName;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((((((getThingName() == null ? 0 : getThingName().hashCode()) + 31) * 31) + (getThingTypeName() == null ? 0 : getThingTypeName().hashCode())) * 31) + (getAttributePayload() == null ? 0 : getAttributePayload().hashCode())) * 31) + (getExpectedVersion() == null ? 0 : getExpectedVersion().hashCode())) * 31;
        if (getRemoveThingType() != null) {
            i = getRemoveThingType().hashCode();
        }
        return hashCode + i;
    }

    public Boolean isRemoveThingType() {
        return this.removeThingType;
    }

    public void setAttributePayload(AttributePayload attributePayload) {
        this.attributePayload = attributePayload;
    }

    public void setExpectedVersion(Long l) {
        this.expectedVersion = l;
    }

    public void setRemoveThingType(Boolean bool) {
        this.removeThingType = bool;
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
        if (getThingTypeName() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("thingTypeName: ");
            outline1073.append(getThingTypeName());
            outline1073.append(",");
            outline107.append(outline1073.toString());
        }
        if (getAttributePayload() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("attributePayload: ");
            outline1074.append(getAttributePayload());
            outline1074.append(",");
            outline107.append(outline1074.toString());
        }
        if (getExpectedVersion() != null) {
            StringBuilder outline1075 = GeneratedOutlineSupport1.outline107("expectedVersion: ");
            outline1075.append(getExpectedVersion());
            outline1075.append(",");
            outline107.append(outline1075.toString());
        }
        if (getRemoveThingType() != null) {
            StringBuilder outline1076 = GeneratedOutlineSupport1.outline107("removeThingType: ");
            outline1076.append(getRemoveThingType());
            outline107.append(outline1076.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public UpdateThingRequest withAttributePayload(AttributePayload attributePayload) {
        this.attributePayload = attributePayload;
        return this;
    }

    public UpdateThingRequest withExpectedVersion(Long l) {
        this.expectedVersion = l;
        return this;
    }

    public UpdateThingRequest withRemoveThingType(Boolean bool) {
        this.removeThingType = bool;
        return this;
    }

    public UpdateThingRequest withThingName(String str) {
        this.thingName = str;
        return this;
    }

    public UpdateThingRequest withThingTypeName(String str) {
        this.thingTypeName = str;
        return this;
    }
}
