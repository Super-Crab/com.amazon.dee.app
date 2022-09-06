package com.amazon.clouddrive.cdasdk.onelens;

import com.amazon.clouddrive.cdasdk.onelens.common.OneLensRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.annotation.JsonProperty;
/* loaded from: classes11.dex */
public class GetContactInfoRequest extends OneLensRequest {
    @JsonProperty("customerId")
    private String customerId;

    @Override // com.amazon.clouddrive.cdasdk.onelens.common.OneLensRequest
    protected boolean canEqual(Object obj) {
        return obj instanceof GetContactInfoRequest;
    }

    @Override // com.amazon.clouddrive.cdasdk.onelens.common.OneLensRequest
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof GetContactInfoRequest)) {
            return false;
        }
        GetContactInfoRequest getContactInfoRequest = (GetContactInfoRequest) obj;
        if (!getContactInfoRequest.canEqual(this) || !super.equals(obj)) {
            return false;
        }
        String customerId = getCustomerId();
        String customerId2 = getContactInfoRequest.getCustomerId();
        return customerId != null ? customerId.equals(customerId2) : customerId2 == null;
    }

    public String getCustomerId() {
        return this.customerId;
    }

    @Override // com.amazon.clouddrive.cdasdk.onelens.common.OneLensRequest
    public int hashCode() {
        int hashCode = super.hashCode();
        String customerId = getCustomerId();
        return (hashCode * 59) + (customerId == null ? 43 : customerId.hashCode());
    }

    @JsonProperty("customerId")
    public void setCustomerId(String str) {
        this.customerId = str;
    }

    @Override // com.amazon.clouddrive.cdasdk.onelens.common.OneLensRequest
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("GetContactInfoRequest(customerId=");
        outline107.append(getCustomerId());
        outline107.append(")");
        return outline107.toString();
    }
}
