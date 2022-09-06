package com.amazon.clouddrive.cdasdk.cdus;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.annotation.JsonProperty;
/* loaded from: classes11.dex */
public class UploadPartRequest extends ServiceRequest {
    @JsonProperty("partSize")
    private Long partSize;
    @JsonProperty("uploadId")
    private String uploadId;

    public UploadPartRequest(String str, Long l) {
        this.uploadId = str;
        this.partSize = l;
    }

    @Override // com.amazon.clouddrive.cdasdk.cdus.ServiceRequest
    protected boolean canEqual(Object obj) {
        return obj instanceof UploadPartRequest;
    }

    @Override // com.amazon.clouddrive.cdasdk.cdus.ServiceRequest
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof UploadPartRequest)) {
            return false;
        }
        UploadPartRequest uploadPartRequest = (UploadPartRequest) obj;
        if (!uploadPartRequest.canEqual(this) || !super.equals(obj)) {
            return false;
        }
        String uploadId = getUploadId();
        String uploadId2 = uploadPartRequest.getUploadId();
        if (uploadId != null ? !uploadId.equals(uploadId2) : uploadId2 != null) {
            return false;
        }
        Long partSize = getPartSize();
        Long partSize2 = uploadPartRequest.getPartSize();
        return partSize != null ? partSize.equals(partSize2) : partSize2 == null;
    }

    public Long getPartSize() {
        return this.partSize;
    }

    public String getUploadId() {
        return this.uploadId;
    }

    @Override // com.amazon.clouddrive.cdasdk.cdus.ServiceRequest
    public int hashCode() {
        int hashCode = super.hashCode();
        String uploadId = getUploadId();
        int i = 43;
        int hashCode2 = (hashCode * 59) + (uploadId == null ? 43 : uploadId.hashCode());
        Long partSize = getPartSize();
        int i2 = hashCode2 * 59;
        if (partSize != null) {
            i = partSize.hashCode();
        }
        return i2 + i;
    }

    @JsonProperty("partSize")
    public void setPartSize(Long l) {
        this.partSize = l;
    }

    @JsonProperty("uploadId")
    public void setUploadId(String str) {
        this.uploadId = str;
    }

    @Override // com.amazon.clouddrive.cdasdk.cdus.ServiceRequest
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("UploadPartRequest(uploadId=");
        outline107.append(getUploadId());
        outline107.append(", partSize=");
        outline107.append(getPartSize());
        outline107.append(")");
        return outline107.toString();
    }
}
