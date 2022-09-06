package com.amazon.clouddrive.cdasdk.cds.account;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.annotation.JsonProperty;
/* loaded from: classes11.dex */
public class GetAccountInfoResponse {
    @JsonProperty("status")
    private String status;
    @JsonProperty("termsOfUse")
    private String termsOfUse;

    protected boolean canEqual(Object obj) {
        return obj instanceof GetAccountInfoResponse;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof GetAccountInfoResponse)) {
            return false;
        }
        GetAccountInfoResponse getAccountInfoResponse = (GetAccountInfoResponse) obj;
        if (!getAccountInfoResponse.canEqual(this)) {
            return false;
        }
        String status = getStatus();
        String status2 = getAccountInfoResponse.getStatus();
        if (status != null ? !status.equals(status2) : status2 != null) {
            return false;
        }
        String termsOfUse = getTermsOfUse();
        String termsOfUse2 = getAccountInfoResponse.getTermsOfUse();
        return termsOfUse != null ? termsOfUse.equals(termsOfUse2) : termsOfUse2 == null;
    }

    public String getStatus() {
        return this.status;
    }

    public String getTermsOfUse() {
        return this.termsOfUse;
    }

    public int hashCode() {
        String status = getStatus();
        int i = 43;
        int hashCode = status == null ? 43 : status.hashCode();
        String termsOfUse = getTermsOfUse();
        int i2 = (hashCode + 59) * 59;
        if (termsOfUse != null) {
            i = termsOfUse.hashCode();
        }
        return i2 + i;
    }

    @JsonProperty("status")
    public void setStatus(String str) {
        this.status = str;
    }

    @JsonProperty("termsOfUse")
    public void setTermsOfUse(String str) {
        this.termsOfUse = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("GetAccountInfoResponse(status=");
        outline107.append(getStatus());
        outline107.append(", termsOfUse=");
        outline107.append(getTermsOfUse());
        outline107.append(")");
        return outline107.toString();
    }
}
