package com.amazon.clouddrive.cdasdk.cds.account;

import com.amazon.clouddrive.cdasdk.cds.common.CloudDriveRequest;
/* loaded from: classes11.dex */
public class GetAccountInfoRequest extends CloudDriveRequest {
    @Override // com.amazon.clouddrive.cdasdk.cds.common.CloudDriveRequest
    protected boolean canEqual(Object obj) {
        return obj instanceof GetAccountInfoRequest;
    }

    @Override // com.amazon.clouddrive.cdasdk.cds.common.CloudDriveRequest
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof GetAccountInfoRequest) && ((GetAccountInfoRequest) obj).canEqual(this) && super.equals(obj);
    }

    @Override // com.amazon.clouddrive.cdasdk.cds.common.CloudDriveRequest
    public int hashCode() {
        return super.hashCode();
    }

    @Override // com.amazon.clouddrive.cdasdk.cds.common.CloudDriveRequest
    public String toString() {
        return "GetAccountInfoRequest()";
    }
}
