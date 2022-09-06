package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazonaws.AmazonWebServiceRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class DescribeBillingGroupRequest extends AmazonWebServiceRequest implements Serializable {
    private String billingGroupName;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof DescribeBillingGroupRequest)) {
            return false;
        }
        DescribeBillingGroupRequest describeBillingGroupRequest = (DescribeBillingGroupRequest) obj;
        if ((describeBillingGroupRequest.getBillingGroupName() == null) ^ (getBillingGroupName() == null)) {
            return false;
        }
        return describeBillingGroupRequest.getBillingGroupName() == null || describeBillingGroupRequest.getBillingGroupName().equals(getBillingGroupName());
    }

    public String getBillingGroupName() {
        return this.billingGroupName;
    }

    public int hashCode() {
        return 31 + (getBillingGroupName() == null ? 0 : getBillingGroupName().hashCode());
    }

    public void setBillingGroupName(String str) {
        this.billingGroupName = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getBillingGroupName() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("billingGroupName: ");
            outline1072.append(getBillingGroupName());
            outline107.append(outline1072.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public DescribeBillingGroupRequest withBillingGroupName(String str) {
        this.billingGroupName = str;
        return this;
    }
}
