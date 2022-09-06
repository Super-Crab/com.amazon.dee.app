package com.amazon.clouddrive.cdasdk.dps.settings;

import com.amazon.clouddrive.cdasdk.dps.common.DPSRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.annotation.JsonProperty;
/* loaded from: classes11.dex */
public class GetSettingsRequest extends DPSRequest {
    @JsonProperty("principalId")
    private String principalId;
    @JsonProperty("principalType")
    private String principalType;

    @Override // com.amazon.clouddrive.cdasdk.dps.common.DPSRequest
    protected boolean canEqual(Object obj) {
        return obj instanceof GetSettingsRequest;
    }

    @Override // com.amazon.clouddrive.cdasdk.dps.common.DPSRequest
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof GetSettingsRequest)) {
            return false;
        }
        GetSettingsRequest getSettingsRequest = (GetSettingsRequest) obj;
        if (!getSettingsRequest.canEqual(this) || !super.equals(obj)) {
            return false;
        }
        String principalType = getPrincipalType();
        String principalType2 = getSettingsRequest.getPrincipalType();
        if (principalType != null ? !principalType.equals(principalType2) : principalType2 != null) {
            return false;
        }
        String principalId = getPrincipalId();
        String principalId2 = getSettingsRequest.getPrincipalId();
        return principalId != null ? principalId.equals(principalId2) : principalId2 == null;
    }

    public String getPrincipalId() {
        return this.principalId;
    }

    public String getPrincipalType() {
        return this.principalType;
    }

    @Override // com.amazon.clouddrive.cdasdk.dps.common.DPSRequest
    public int hashCode() {
        int hashCode = super.hashCode();
        String principalType = getPrincipalType();
        int i = 43;
        int hashCode2 = (hashCode * 59) + (principalType == null ? 43 : principalType.hashCode());
        String principalId = getPrincipalId();
        int i2 = hashCode2 * 59;
        if (principalId != null) {
            i = principalId.hashCode();
        }
        return i2 + i;
    }

    @JsonProperty("principalId")
    public void setPrincipalId(String str) {
        this.principalId = str;
    }

    @JsonProperty("principalType")
    public void setPrincipalType(String str) {
        this.principalType = str;
    }

    @Override // com.amazon.clouddrive.cdasdk.dps.common.DPSRequest
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("GetSettingsRequest(principalType=");
        outline107.append(getPrincipalType());
        outline107.append(", principalId=");
        outline107.append(getPrincipalId());
        outline107.append(")");
        return outline107.toString();
    }
}
