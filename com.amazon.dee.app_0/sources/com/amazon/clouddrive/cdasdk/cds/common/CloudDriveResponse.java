package com.amazon.clouddrive.cdasdk.cds.common;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.annotation.JsonProperty;
/* loaded from: classes11.dex */
public class CloudDriveResponse {
    @JsonProperty("statusCode")
    private Integer statusCode;

    protected boolean canEqual(Object obj) {
        return obj instanceof CloudDriveResponse;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof CloudDriveResponse)) {
            return false;
        }
        CloudDriveResponse cloudDriveResponse = (CloudDriveResponse) obj;
        if (!cloudDriveResponse.canEqual(this)) {
            return false;
        }
        Integer statusCode = getStatusCode();
        Integer statusCode2 = cloudDriveResponse.getStatusCode();
        return statusCode != null ? statusCode.equals(statusCode2) : statusCode2 == null;
    }

    public Integer getStatusCode() {
        return this.statusCode;
    }

    public int hashCode() {
        Integer statusCode = getStatusCode();
        return 59 + (statusCode == null ? 43 : statusCode.hashCode());
    }

    @JsonProperty("statusCode")
    public void setStatusCode(Integer num) {
        this.statusCode = num;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("CloudDriveResponse(statusCode=");
        outline107.append(getStatusCode());
        outline107.append(")");
        return outline107.toString();
    }
}
