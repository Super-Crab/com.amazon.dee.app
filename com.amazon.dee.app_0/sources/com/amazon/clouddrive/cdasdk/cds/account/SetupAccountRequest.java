package com.amazon.clouddrive.cdasdk.cds.account;

import com.amazon.clouddrive.cdasdk.cds.common.CloudDriveRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.annotation.JsonProperty;
/* loaded from: classes11.dex */
public class SetupAccountRequest extends CloudDriveRequest {
    @JsonProperty("overrideMarketplaceId")
    private String overrideMarketplaceId;
    @JsonProperty("termsOfUse")
    private String termsOfUse;

    @Override // com.amazon.clouddrive.cdasdk.cds.common.CloudDriveRequest
    protected boolean canEqual(Object obj) {
        return obj instanceof SetupAccountRequest;
    }

    @Override // com.amazon.clouddrive.cdasdk.cds.common.CloudDriveRequest
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SetupAccountRequest)) {
            return false;
        }
        SetupAccountRequest setupAccountRequest = (SetupAccountRequest) obj;
        if (!setupAccountRequest.canEqual(this) || !super.equals(obj)) {
            return false;
        }
        String termsOfUse = getTermsOfUse();
        String termsOfUse2 = setupAccountRequest.getTermsOfUse();
        if (termsOfUse != null ? !termsOfUse.equals(termsOfUse2) : termsOfUse2 != null) {
            return false;
        }
        String overrideMarketplaceId = getOverrideMarketplaceId();
        String overrideMarketplaceId2 = setupAccountRequest.getOverrideMarketplaceId();
        return overrideMarketplaceId != null ? overrideMarketplaceId.equals(overrideMarketplaceId2) : overrideMarketplaceId2 == null;
    }

    public String getOverrideMarketplaceId() {
        return this.overrideMarketplaceId;
    }

    public String getTermsOfUse() {
        return this.termsOfUse;
    }

    @Override // com.amazon.clouddrive.cdasdk.cds.common.CloudDriveRequest
    public int hashCode() {
        int hashCode = super.hashCode();
        String termsOfUse = getTermsOfUse();
        int i = 43;
        int hashCode2 = (hashCode * 59) + (termsOfUse == null ? 43 : termsOfUse.hashCode());
        String overrideMarketplaceId = getOverrideMarketplaceId();
        int i2 = hashCode2 * 59;
        if (overrideMarketplaceId != null) {
            i = overrideMarketplaceId.hashCode();
        }
        return i2 + i;
    }

    @JsonProperty("overrideMarketplaceId")
    public void setOverrideMarketplaceId(String str) {
        this.overrideMarketplaceId = str;
    }

    @JsonProperty("termsOfUse")
    public void setTermsOfUse(String str) {
        this.termsOfUse = str;
    }

    @Override // com.amazon.clouddrive.cdasdk.cds.common.CloudDriveRequest
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("SetupAccountRequest(termsOfUse=");
        outline107.append(getTermsOfUse());
        outline107.append(", overrideMarketplaceId=");
        outline107.append(getOverrideMarketplaceId());
        outline107.append(")");
        return outline107.toString();
    }
}
