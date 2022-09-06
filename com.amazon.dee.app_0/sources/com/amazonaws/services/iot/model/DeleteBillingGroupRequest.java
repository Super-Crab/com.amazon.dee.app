package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazonaws.AmazonWebServiceRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class DeleteBillingGroupRequest extends AmazonWebServiceRequest implements Serializable {
    private String billingGroupName;
    private Long expectedVersion;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof DeleteBillingGroupRequest)) {
            return false;
        }
        DeleteBillingGroupRequest deleteBillingGroupRequest = (DeleteBillingGroupRequest) obj;
        if ((deleteBillingGroupRequest.getBillingGroupName() == null) ^ (getBillingGroupName() == null)) {
            return false;
        }
        if (deleteBillingGroupRequest.getBillingGroupName() != null && !deleteBillingGroupRequest.getBillingGroupName().equals(getBillingGroupName())) {
            return false;
        }
        if ((deleteBillingGroupRequest.getExpectedVersion() == null) ^ (getExpectedVersion() == null)) {
            return false;
        }
        return deleteBillingGroupRequest.getExpectedVersion() == null || deleteBillingGroupRequest.getExpectedVersion().equals(getExpectedVersion());
    }

    public String getBillingGroupName() {
        return this.billingGroupName;
    }

    public Long getExpectedVersion() {
        return this.expectedVersion;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((getBillingGroupName() == null ? 0 : getBillingGroupName().hashCode()) + 31) * 31;
        if (getExpectedVersion() != null) {
            i = getExpectedVersion().hashCode();
        }
        return hashCode + i;
    }

    public void setBillingGroupName(String str) {
        this.billingGroupName = str;
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
        if (getExpectedVersion() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("expectedVersion: ");
            outline1073.append(getExpectedVersion());
            outline107.append(outline1073.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public DeleteBillingGroupRequest withBillingGroupName(String str) {
        this.billingGroupName = str;
        return this;
    }

    public DeleteBillingGroupRequest withExpectedVersion(Long l) {
        this.expectedVersion = l;
        return this;
    }
}
