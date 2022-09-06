package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazonaws.AmazonWebServiceRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class AddThingToBillingGroupRequest extends AmazonWebServiceRequest implements Serializable {
    private String billingGroupArn;
    private String billingGroupName;
    private String thingArn;
    private String thingName;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof AddThingToBillingGroupRequest)) {
            return false;
        }
        AddThingToBillingGroupRequest addThingToBillingGroupRequest = (AddThingToBillingGroupRequest) obj;
        if ((addThingToBillingGroupRequest.getBillingGroupName() == null) ^ (getBillingGroupName() == null)) {
            return false;
        }
        if (addThingToBillingGroupRequest.getBillingGroupName() != null && !addThingToBillingGroupRequest.getBillingGroupName().equals(getBillingGroupName())) {
            return false;
        }
        if ((addThingToBillingGroupRequest.getBillingGroupArn() == null) ^ (getBillingGroupArn() == null)) {
            return false;
        }
        if (addThingToBillingGroupRequest.getBillingGroupArn() != null && !addThingToBillingGroupRequest.getBillingGroupArn().equals(getBillingGroupArn())) {
            return false;
        }
        if ((addThingToBillingGroupRequest.getThingName() == null) ^ (getThingName() == null)) {
            return false;
        }
        if (addThingToBillingGroupRequest.getThingName() != null && !addThingToBillingGroupRequest.getThingName().equals(getThingName())) {
            return false;
        }
        if ((addThingToBillingGroupRequest.getThingArn() == null) ^ (getThingArn() == null)) {
            return false;
        }
        return addThingToBillingGroupRequest.getThingArn() == null || addThingToBillingGroupRequest.getThingArn().equals(getThingArn());
    }

    public String getBillingGroupArn() {
        return this.billingGroupArn;
    }

    public String getBillingGroupName() {
        return this.billingGroupName;
    }

    public String getThingArn() {
        return this.thingArn;
    }

    public String getThingName() {
        return this.thingName;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((((getBillingGroupName() == null ? 0 : getBillingGroupName().hashCode()) + 31) * 31) + (getBillingGroupArn() == null ? 0 : getBillingGroupArn().hashCode())) * 31) + (getThingName() == null ? 0 : getThingName().hashCode())) * 31;
        if (getThingArn() != null) {
            i = getThingArn().hashCode();
        }
        return hashCode + i;
    }

    public void setBillingGroupArn(String str) {
        this.billingGroupArn = str;
    }

    public void setBillingGroupName(String str) {
        this.billingGroupName = str;
    }

    public void setThingArn(String str) {
        this.thingArn = str;
    }

    public void setThingName(String str) {
        this.thingName = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getBillingGroupName() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("billingGroupName: ");
            outline1072.append(getBillingGroupName());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getBillingGroupArn() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("billingGroupArn: ");
            outline1073.append(getBillingGroupArn());
            outline1073.append(",");
            outline107.append(outline1073.toString());
        }
        if (getThingName() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("thingName: ");
            outline1074.append(getThingName());
            outline1074.append(",");
            outline107.append(outline1074.toString());
        }
        if (getThingArn() != null) {
            StringBuilder outline1075 = GeneratedOutlineSupport1.outline107("thingArn: ");
            outline1075.append(getThingArn());
            outline107.append(outline1075.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public AddThingToBillingGroupRequest withBillingGroupArn(String str) {
        this.billingGroupArn = str;
        return this;
    }

    public AddThingToBillingGroupRequest withBillingGroupName(String str) {
        this.billingGroupName = str;
        return this;
    }

    public AddThingToBillingGroupRequest withThingArn(String str) {
        this.thingArn = str;
        return this;
    }

    public AddThingToBillingGroupRequest withThingName(String str) {
        this.thingName = str;
        return this;
    }
}
