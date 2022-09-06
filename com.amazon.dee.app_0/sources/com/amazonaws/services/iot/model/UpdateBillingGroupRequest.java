package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazonaws.AmazonWebServiceRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class UpdateBillingGroupRequest extends AmazonWebServiceRequest implements Serializable {
    private String billingGroupName;
    private BillingGroupProperties billingGroupProperties;
    private Long expectedVersion;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof UpdateBillingGroupRequest)) {
            return false;
        }
        UpdateBillingGroupRequest updateBillingGroupRequest = (UpdateBillingGroupRequest) obj;
        if ((updateBillingGroupRequest.getBillingGroupName() == null) ^ (getBillingGroupName() == null)) {
            return false;
        }
        if (updateBillingGroupRequest.getBillingGroupName() != null && !updateBillingGroupRequest.getBillingGroupName().equals(getBillingGroupName())) {
            return false;
        }
        if ((updateBillingGroupRequest.getBillingGroupProperties() == null) ^ (getBillingGroupProperties() == null)) {
            return false;
        }
        if (updateBillingGroupRequest.getBillingGroupProperties() != null && !updateBillingGroupRequest.getBillingGroupProperties().equals(getBillingGroupProperties())) {
            return false;
        }
        if ((updateBillingGroupRequest.getExpectedVersion() == null) ^ (getExpectedVersion() == null)) {
            return false;
        }
        return updateBillingGroupRequest.getExpectedVersion() == null || updateBillingGroupRequest.getExpectedVersion().equals(getExpectedVersion());
    }

    public String getBillingGroupName() {
        return this.billingGroupName;
    }

    public BillingGroupProperties getBillingGroupProperties() {
        return this.billingGroupProperties;
    }

    public Long getExpectedVersion() {
        return this.expectedVersion;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((getBillingGroupName() == null ? 0 : getBillingGroupName().hashCode()) + 31) * 31) + (getBillingGroupProperties() == null ? 0 : getBillingGroupProperties().hashCode())) * 31;
        if (getExpectedVersion() != null) {
            i = getExpectedVersion().hashCode();
        }
        return hashCode + i;
    }

    public void setBillingGroupName(String str) {
        this.billingGroupName = str;
    }

    public void setBillingGroupProperties(BillingGroupProperties billingGroupProperties) {
        this.billingGroupProperties = billingGroupProperties;
    }

    public void setExpectedVersion(Long l) {
        this.expectedVersion = l;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getBillingGroupName() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("billingGroupName: ");
            outline1072.append(getBillingGroupName());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getBillingGroupProperties() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("billingGroupProperties: ");
            outline1073.append(getBillingGroupProperties());
            outline1073.append(",");
            outline107.append(outline1073.toString());
        }
        if (getExpectedVersion() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("expectedVersion: ");
            outline1074.append(getExpectedVersion());
            outline107.append(outline1074.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public UpdateBillingGroupRequest withBillingGroupName(String str) {
        this.billingGroupName = str;
        return this;
    }

    public UpdateBillingGroupRequest withBillingGroupProperties(BillingGroupProperties billingGroupProperties) {
        this.billingGroupProperties = billingGroupProperties;
        return this;
    }

    public UpdateBillingGroupRequest withExpectedVersion(Long l) {
        this.expectedVersion = l;
        return this;
    }
}
