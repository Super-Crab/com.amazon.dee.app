package com.amazon.clouddrive.cdasdk.cds.account;

import com.amazon.clouddrive.cdasdk.cds.common.CloudDriveResponse;
/* loaded from: classes11.dex */
public class SetupAccountResponse extends CloudDriveResponse {
    @Override // com.amazon.clouddrive.cdasdk.cds.common.CloudDriveResponse
    protected boolean canEqual(Object obj) {
        return obj instanceof SetupAccountResponse;
    }

    @Override // com.amazon.clouddrive.cdasdk.cds.common.CloudDriveResponse
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof SetupAccountResponse) && ((SetupAccountResponse) obj).canEqual(this) && super.equals(obj);
    }

    @Override // com.amazon.clouddrive.cdasdk.cds.common.CloudDriveResponse
    public int hashCode() {
        return super.hashCode();
    }

    @Override // com.amazon.clouddrive.cdasdk.cds.common.CloudDriveResponse
    public String toString() {
        return "SetupAccountResponse()";
    }
}
