package com.amazon.clouddrive.cdasdk.cdus;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.annotation.JsonProperty;
/* loaded from: classes11.dex */
public class CompleteMultipartRequest extends ServiceRequest {
    @JsonProperty("uploadId")
    private String uploadId;

    public CompleteMultipartRequest(String str) {
        this.uploadId = str;
    }

    @Override // com.amazon.clouddrive.cdasdk.cdus.ServiceRequest
    protected boolean canEqual(Object obj) {
        return obj instanceof CompleteMultipartRequest;
    }

    @Override // com.amazon.clouddrive.cdasdk.cdus.ServiceRequest
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof CompleteMultipartRequest)) {
            return false;
        }
        CompleteMultipartRequest completeMultipartRequest = (CompleteMultipartRequest) obj;
        if (!completeMultipartRequest.canEqual(this) || !super.equals(obj)) {
            return false;
        }
        String uploadId = getUploadId();
        String uploadId2 = completeMultipartRequest.getUploadId();
        return uploadId != null ? uploadId.equals(uploadId2) : uploadId2 == null;
    }

    public String getUploadId() {
        return this.uploadId;
    }

    @Override // com.amazon.clouddrive.cdasdk.cdus.ServiceRequest
    public int hashCode() {
        int hashCode = super.hashCode();
        String uploadId = getUploadId();
        return (hashCode * 59) + (uploadId == null ? 43 : uploadId.hashCode());
    }

    @JsonProperty("uploadId")
    public void setUploadId(String str) {
        this.uploadId = str;
    }

    @Override // com.amazon.clouddrive.cdasdk.cdus.ServiceRequest
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("CompleteMultipartRequest(uploadId=");
        outline107.append(getUploadId());
        outline107.append(")");
        return outline107.toString();
    }
}
