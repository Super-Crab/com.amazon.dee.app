package com.amazon.clouddrive.cdasdk.cdrs;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.annotation.JsonProperty;
/* loaded from: classes11.dex */
class CDRSServiceCustomerRequest extends CDRSServiceRequest {
    @JsonProperty("customerId")
    private String customerId;

    @Override // com.amazon.clouddrive.cdasdk.cdrs.CDRSServiceRequest
    protected boolean canEqual(Object obj) {
        return obj instanceof CDRSServiceCustomerRequest;
    }

    @Override // com.amazon.clouddrive.cdasdk.cdrs.CDRSServiceRequest
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof CDRSServiceCustomerRequest)) {
            return false;
        }
        CDRSServiceCustomerRequest cDRSServiceCustomerRequest = (CDRSServiceCustomerRequest) obj;
        if (!cDRSServiceCustomerRequest.canEqual(this) || !super.equals(obj)) {
            return false;
        }
        String customerId = getCustomerId();
        String customerId2 = cDRSServiceCustomerRequest.getCustomerId();
        return customerId != null ? customerId.equals(customerId2) : customerId2 == null;
    }

    public String getCustomerId() {
        return this.customerId;
    }

    @Override // com.amazon.clouddrive.cdasdk.cdrs.CDRSServiceRequest
    public int hashCode() {
        int hashCode = super.hashCode();
        String customerId = getCustomerId();
        return (hashCode * 59) + (customerId == null ? 43 : customerId.hashCode());
    }

    @JsonProperty("customerId")
    public void setCustomerId(String str) {
        this.customerId = str;
    }

    @Override // com.amazon.clouddrive.cdasdk.cdrs.CDRSServiceRequest
    public String toString() {
        return GeneratedOutlineSupport1.outline91(GeneratedOutlineSupport1.outline107("CDRSServiceCustomerRequest(customerId="), getCustomerId(), ")");
    }
}
