package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class BillingGroupProperties implements Serializable {
    private String billingGroupDescription;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof BillingGroupProperties)) {
            return false;
        }
        BillingGroupProperties billingGroupProperties = (BillingGroupProperties) obj;
        if ((billingGroupProperties.getBillingGroupDescription() == null) ^ (getBillingGroupDescription() == null)) {
            return false;
        }
        return billingGroupProperties.getBillingGroupDescription() == null || billingGroupProperties.getBillingGroupDescription().equals(getBillingGroupDescription());
    }

    public String getBillingGroupDescription() {
        return this.billingGroupDescription;
    }

    public int hashCode() {
        return 31 + (getBillingGroupDescription() == null ? 0 : getBillingGroupDescription().hashCode());
    }

    public void setBillingGroupDescription(String str) {
        this.billingGroupDescription = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getBillingGroupDescription() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("billingGroupDescription: ");
            outline1072.append(getBillingGroupDescription());
            outline107.append(outline1072.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public BillingGroupProperties withBillingGroupDescription(String str) {
        this.billingGroupDescription = str;
        return this;
    }
}
