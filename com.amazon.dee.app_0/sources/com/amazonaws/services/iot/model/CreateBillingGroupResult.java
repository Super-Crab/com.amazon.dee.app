package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class CreateBillingGroupResult implements Serializable {
    private String billingGroupArn;
    private String billingGroupId;
    private String billingGroupName;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof CreateBillingGroupResult)) {
            return false;
        }
        CreateBillingGroupResult createBillingGroupResult = (CreateBillingGroupResult) obj;
        if ((createBillingGroupResult.getBillingGroupName() == null) ^ (getBillingGroupName() == null)) {
            return false;
        }
        if (createBillingGroupResult.getBillingGroupName() != null && !createBillingGroupResult.getBillingGroupName().equals(getBillingGroupName())) {
            return false;
        }
        if ((createBillingGroupResult.getBillingGroupArn() == null) ^ (getBillingGroupArn() == null)) {
            return false;
        }
        if (createBillingGroupResult.getBillingGroupArn() != null && !createBillingGroupResult.getBillingGroupArn().equals(getBillingGroupArn())) {
            return false;
        }
        if ((createBillingGroupResult.getBillingGroupId() == null) ^ (getBillingGroupId() == null)) {
            return false;
        }
        return createBillingGroupResult.getBillingGroupId() == null || createBillingGroupResult.getBillingGroupId().equals(getBillingGroupId());
    }

    public String getBillingGroupArn() {
        return this.billingGroupArn;
    }

    public String getBillingGroupId() {
        return this.billingGroupId;
    }

    public String getBillingGroupName() {
        return this.billingGroupName;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((getBillingGroupName() == null ? 0 : getBillingGroupName().hashCode()) + 31) * 31) + (getBillingGroupArn() == null ? 0 : getBillingGroupArn().hashCode())) * 31;
        if (getBillingGroupId() != null) {
            i = getBillingGroupId().hashCode();
        }
        return hashCode + i;
    }

    public void setBillingGroupArn(String str) {
        this.billingGroupArn = str;
    }

    public void setBillingGroupId(String str) {
        this.billingGroupId = str;
    }

    public void setBillingGroupName(String str) {
        this.billingGroupName = str;
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
        if (getBillingGroupId() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("billingGroupId: ");
            outline1074.append(getBillingGroupId());
            outline107.append(outline1074.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public CreateBillingGroupResult withBillingGroupArn(String str) {
        this.billingGroupArn = str;
        return this;
    }

    public CreateBillingGroupResult withBillingGroupId(String str) {
        this.billingGroupId = str;
        return this;
    }

    public CreateBillingGroupResult withBillingGroupName(String str) {
        this.billingGroupName = str;
        return this;
    }
}
