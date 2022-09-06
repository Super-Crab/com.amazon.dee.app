package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazonaws.AmazonWebServiceRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class CreateThingRequest extends AmazonWebServiceRequest implements Serializable {
    private AttributePayload attributePayload;
    private String billingGroupName;
    private String thingName;
    private String thingTypeName;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof CreateThingRequest)) {
            return false;
        }
        CreateThingRequest createThingRequest = (CreateThingRequest) obj;
        if ((createThingRequest.getThingName() == null) ^ (getThingName() == null)) {
            return false;
        }
        if (createThingRequest.getThingName() != null && !createThingRequest.getThingName().equals(getThingName())) {
            return false;
        }
        if ((createThingRequest.getThingTypeName() == null) ^ (getThingTypeName() == null)) {
            return false;
        }
        if (createThingRequest.getThingTypeName() != null && !createThingRequest.getThingTypeName().equals(getThingTypeName())) {
            return false;
        }
        if ((createThingRequest.getAttributePayload() == null) ^ (getAttributePayload() == null)) {
            return false;
        }
        if (createThingRequest.getAttributePayload() != null && !createThingRequest.getAttributePayload().equals(getAttributePayload())) {
            return false;
        }
        if ((createThingRequest.getBillingGroupName() == null) ^ (getBillingGroupName() == null)) {
            return false;
        }
        return createThingRequest.getBillingGroupName() == null || createThingRequest.getBillingGroupName().equals(getBillingGroupName());
    }

    public AttributePayload getAttributePayload() {
        return this.attributePayload;
    }

    public String getBillingGroupName() {
        return this.billingGroupName;
    }

    public String getThingName() {
        return this.thingName;
    }

    public String getThingTypeName() {
        return this.thingTypeName;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((((getThingName() == null ? 0 : getThingName().hashCode()) + 31) * 31) + (getThingTypeName() == null ? 0 : getThingTypeName().hashCode())) * 31) + (getAttributePayload() == null ? 0 : getAttributePayload().hashCode())) * 31;
        if (getBillingGroupName() != null) {
            i = getBillingGroupName().hashCode();
        }
        return hashCode + i;
    }

    public void setAttributePayload(AttributePayload attributePayload) {
        this.attributePayload = attributePayload;
    }

    public void setBillingGroupName(String str) {
        this.billingGroupName = str;
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
        if (getBillingGroupName() != null) {
            StringBuilder outline1075 = GeneratedOutlineSupport1.outline107("billingGroupName: ");
            outline1075.append(getBillingGroupName());
            outline107.append(outline1075.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public CreateThingRequest withAttributePayload(AttributePayload attributePayload) {
        this.attributePayload = attributePayload;
        return this;
    }

    public CreateThingRequest withBillingGroupName(String str) {
        this.billingGroupName = str;
        return this;
    }

    public CreateThingRequest withThingName(String str) {
        this.thingName = str;
        return this;
    }

    public CreateThingRequest withThingTypeName(String str) {
        this.thingTypeName = str;
        return this;
    }
}
