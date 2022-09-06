package com.amazon.clouddrive.cdasdk.cds.account;

import com.amazon.clouddrive.cdasdk.cds.common.CloudDriveRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.annotation.JsonProperty;
/* loaded from: classes11.dex */
public class GetQuotaRequest extends CloudDriveRequest {
    @JsonProperty("includeFamilyBenefit")
    private Boolean includeFamilyBenefit;

    @Override // com.amazon.clouddrive.cdasdk.cds.common.CloudDriveRequest
    protected boolean canEqual(Object obj) {
        return obj instanceof GetQuotaRequest;
    }

    @Override // com.amazon.clouddrive.cdasdk.cds.common.CloudDriveRequest
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof GetQuotaRequest)) {
            return false;
        }
        GetQuotaRequest getQuotaRequest = (GetQuotaRequest) obj;
        if (!getQuotaRequest.canEqual(this) || !super.equals(obj)) {
            return false;
        }
        Boolean includeFamilyBenefit = getIncludeFamilyBenefit();
        Boolean includeFamilyBenefit2 = getQuotaRequest.getIncludeFamilyBenefit();
        return includeFamilyBenefit != null ? includeFamilyBenefit.equals(includeFamilyBenefit2) : includeFamilyBenefit2 == null;
    }

    public Boolean getIncludeFamilyBenefit() {
        return this.includeFamilyBenefit;
    }

    @Override // com.amazon.clouddrive.cdasdk.cds.common.CloudDriveRequest
    public int hashCode() {
        int hashCode = super.hashCode();
        Boolean includeFamilyBenefit = getIncludeFamilyBenefit();
        return (hashCode * 59) + (includeFamilyBenefit == null ? 43 : includeFamilyBenefit.hashCode());
    }

    @JsonProperty("includeFamilyBenefit")
    public void setIncludeFamilyBenefit(Boolean bool) {
        this.includeFamilyBenefit = bool;
    }

    @Override // com.amazon.clouddrive.cdasdk.cds.common.CloudDriveRequest
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("GetQuotaRequest(includeFamilyBenefit=");
        outline107.append(getIncludeFamilyBenefit());
        outline107.append(")");
        return outline107.toString();
    }
}
