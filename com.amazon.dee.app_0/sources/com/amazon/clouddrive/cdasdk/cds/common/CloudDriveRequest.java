package com.amazon.clouddrive.cdasdk.cds.common;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.annotation.JsonProperty;
/* loaded from: classes11.dex */
public abstract class CloudDriveRequest {
    @JsonProperty("eTag")
    private String eTag;

    protected boolean canEqual(Object obj) {
        return obj instanceof CloudDriveRequest;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof CloudDriveRequest)) {
            return false;
        }
        CloudDriveRequest cloudDriveRequest = (CloudDriveRequest) obj;
        if (!cloudDriveRequest.canEqual(this)) {
            return false;
        }
        String eTag = getETag();
        String eTag2 = cloudDriveRequest.getETag();
        return eTag != null ? eTag.equals(eTag2) : eTag2 == null;
    }

    public String getETag() {
        return this.eTag;
    }

    public int hashCode() {
        String eTag = getETag();
        return 59 + (eTag == null ? 43 : eTag.hashCode());
    }

    @JsonProperty("eTag")
    public void setETag(String str) {
        this.eTag = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("CloudDriveRequest(eTag=");
        outline107.append(getETag());
        outline107.append(")");
        return outline107.toString();
    }
}
