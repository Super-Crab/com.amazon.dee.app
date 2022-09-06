package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class DescribeBillingGroupResult implements Serializable {
    private String billingGroupArn;
    private String billingGroupId;
    private BillingGroupMetadata billingGroupMetadata;
    private String billingGroupName;
    private BillingGroupProperties billingGroupProperties;
    private Long version;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof DescribeBillingGroupResult)) {
            return false;
        }
        DescribeBillingGroupResult describeBillingGroupResult = (DescribeBillingGroupResult) obj;
        if ((describeBillingGroupResult.getBillingGroupName() == null) ^ (getBillingGroupName() == null)) {
            return false;
        }
        if (describeBillingGroupResult.getBillingGroupName() != null && !describeBillingGroupResult.getBillingGroupName().equals(getBillingGroupName())) {
            return false;
        }
        if ((describeBillingGroupResult.getBillingGroupId() == null) ^ (getBillingGroupId() == null)) {
            return false;
        }
        if (describeBillingGroupResult.getBillingGroupId() != null && !describeBillingGroupResult.getBillingGroupId().equals(getBillingGroupId())) {
            return false;
        }
        if ((describeBillingGroupResult.getBillingGroupArn() == null) ^ (getBillingGroupArn() == null)) {
            return false;
        }
        if (describeBillingGroupResult.getBillingGroupArn() != null && !describeBillingGroupResult.getBillingGroupArn().equals(getBillingGroupArn())) {
            return false;
        }
        if ((describeBillingGroupResult.getVersion() == null) ^ (getVersion() == null)) {
            return false;
        }
        if (describeBillingGroupResult.getVersion() != null && !describeBillingGroupResult.getVersion().equals(getVersion())) {
            return false;
        }
        if ((describeBillingGroupResult.getBillingGroupProperties() == null) ^ (getBillingGroupProperties() == null)) {
            return false;
        }
        if (describeBillingGroupResult.getBillingGroupProperties() != null && !describeBillingGroupResult.getBillingGroupProperties().equals(getBillingGroupProperties())) {
            return false;
        }
        if ((describeBillingGroupResult.getBillingGroupMetadata() == null) ^ (getBillingGroupMetadata() == null)) {
            return false;
        }
        return describeBillingGroupResult.getBillingGroupMetadata() == null || describeBillingGroupResult.getBillingGroupMetadata().equals(getBillingGroupMetadata());
    }

    public String getBillingGroupArn() {
        return this.billingGroupArn;
    }

    public String getBillingGroupId() {
        return this.billingGroupId;
    }

    public BillingGroupMetadata getBillingGroupMetadata() {
        return this.billingGroupMetadata;
    }

    public String getBillingGroupName() {
        return this.billingGroupName;
    }

    public BillingGroupProperties getBillingGroupProperties() {
        return this.billingGroupProperties;
    }

    public Long getVersion() {
        return this.version;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((((((((getBillingGroupName() == null ? 0 : getBillingGroupName().hashCode()) + 31) * 31) + (getBillingGroupId() == null ? 0 : getBillingGroupId().hashCode())) * 31) + (getBillingGroupArn() == null ? 0 : getBillingGroupArn().hashCode())) * 31) + (getVersion() == null ? 0 : getVersion().hashCode())) * 31) + (getBillingGroupProperties() == null ? 0 : getBillingGroupProperties().hashCode())) * 31;
        if (getBillingGroupMetadata() != null) {
            i = getBillingGroupMetadata().hashCode();
        }
        return hashCode + i;
    }

    public void setBillingGroupArn(String str) {
        this.billingGroupArn = str;
    }

    public void setBillingGroupId(String str) {
        this.billingGroupId = str;
    }

    public void setBillingGroupMetadata(BillingGroupMetadata billingGroupMetadata) {
        this.billingGroupMetadata = billingGroupMetadata;
    }

    public void setBillingGroupName(String str) {
        this.billingGroupName = str;
    }

    public void setBillingGroupProperties(BillingGroupProperties billingGroupProperties) {
        this.billingGroupProperties = billingGroupProperties;
    }

    public void setVersion(Long l) {
        this.version = l;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getBillingGroupName() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("billingGroupName: ");
            outline1072.append(getBillingGroupName());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getBillingGroupId() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("billingGroupId: ");
            outline1073.append(getBillingGroupId());
            outline1073.append(",");
            outline107.append(outline1073.toString());
        }
        if (getBillingGroupArn() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("billingGroupArn: ");
            outline1074.append(getBillingGroupArn());
            outline1074.append(",");
            outline107.append(outline1074.toString());
        }
        if (getVersion() != null) {
            StringBuilder outline1075 = GeneratedOutlineSupport1.outline107("version: ");
            outline1075.append(getVersion());
            outline1075.append(",");
            outline107.append(outline1075.toString());
        }
        if (getBillingGroupProperties() != null) {
            StringBuilder outline1076 = GeneratedOutlineSupport1.outline107("billingGroupProperties: ");
            outline1076.append(getBillingGroupProperties());
            outline1076.append(",");
            outline107.append(outline1076.toString());
        }
        if (getBillingGroupMetadata() != null) {
            StringBuilder outline1077 = GeneratedOutlineSupport1.outline107("billingGroupMetadata: ");
            outline1077.append(getBillingGroupMetadata());
            outline107.append(outline1077.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public DescribeBillingGroupResult withBillingGroupArn(String str) {
        this.billingGroupArn = str;
        return this;
    }

    public DescribeBillingGroupResult withBillingGroupId(String str) {
        this.billingGroupId = str;
        return this;
    }

    public DescribeBillingGroupResult withBillingGroupMetadata(BillingGroupMetadata billingGroupMetadata) {
        this.billingGroupMetadata = billingGroupMetadata;
        return this;
    }

    public DescribeBillingGroupResult withBillingGroupName(String str) {
        this.billingGroupName = str;
        return this;
    }

    public DescribeBillingGroupResult withBillingGroupProperties(BillingGroupProperties billingGroupProperties) {
        this.billingGroupProperties = billingGroupProperties;
        return this;
    }

    public DescribeBillingGroupResult withVersion(Long l) {
        this.version = l;
        return this;
    }
}
