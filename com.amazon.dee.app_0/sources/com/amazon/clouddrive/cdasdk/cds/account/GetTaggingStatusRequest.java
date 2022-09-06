package com.amazon.clouddrive.cdasdk.cds.account;

import com.amazon.clouddrive.cdasdk.cds.common.CloudDriveRequest;
import com.amazon.clouddrive.cdasdk.cds.common.Context;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.annotation.JsonProperty;
/* loaded from: classes11.dex */
public class GetTaggingStatusRequest extends CloudDriveRequest {
    @JsonProperty("context")
    private Context context;

    @Override // com.amazon.clouddrive.cdasdk.cds.common.CloudDriveRequest
    protected boolean canEqual(Object obj) {
        return obj instanceof GetTaggingStatusRequest;
    }

    @Override // com.amazon.clouddrive.cdasdk.cds.common.CloudDriveRequest
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof GetTaggingStatusRequest)) {
            return false;
        }
        GetTaggingStatusRequest getTaggingStatusRequest = (GetTaggingStatusRequest) obj;
        if (!getTaggingStatusRequest.canEqual(this) || !super.equals(obj)) {
            return false;
        }
        Context context = getContext();
        Context context2 = getTaggingStatusRequest.getContext();
        return context != null ? context.equals(context2) : context2 == null;
    }

    public Context getContext() {
        return this.context;
    }

    @Override // com.amazon.clouddrive.cdasdk.cds.common.CloudDriveRequest
    public int hashCode() {
        int hashCode = super.hashCode();
        Context context = getContext();
        return (hashCode * 59) + (context == null ? 43 : context.hashCode());
    }

    @JsonProperty("context")
    public void setContext(Context context) {
        this.context = context;
    }

    @Override // com.amazon.clouddrive.cdasdk.cds.common.CloudDriveRequest
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("GetTaggingStatusRequest(context=");
        outline107.append(getContext());
        outline107.append(")");
        return outline107.toString();
    }
}
