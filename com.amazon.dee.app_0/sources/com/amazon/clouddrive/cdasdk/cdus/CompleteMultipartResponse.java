package com.amazon.clouddrive.cdasdk.cdus;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.annotation.JsonProperty;
/* loaded from: classes11.dex */
public class CompleteMultipartResponse {
    @JsonProperty("completeMultipartUploadExpirationTime")
    private String completeMultipartUploadExpirationTime;
    @JsonProperty("completeMultipartUploadStartTime")
    private String completeMultipartUploadStartTime;
    @JsonProperty("status")
    private String status;

    protected boolean canEqual(Object obj) {
        return obj instanceof CompleteMultipartResponse;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof CompleteMultipartResponse)) {
            return false;
        }
        CompleteMultipartResponse completeMultipartResponse = (CompleteMultipartResponse) obj;
        if (!completeMultipartResponse.canEqual(this)) {
            return false;
        }
        String status = getStatus();
        String status2 = completeMultipartResponse.getStatus();
        if (status != null ? !status.equals(status2) : status2 != null) {
            return false;
        }
        String completeMultipartUploadStartTime = getCompleteMultipartUploadStartTime();
        String completeMultipartUploadStartTime2 = completeMultipartResponse.getCompleteMultipartUploadStartTime();
        if (completeMultipartUploadStartTime != null ? !completeMultipartUploadStartTime.equals(completeMultipartUploadStartTime2) : completeMultipartUploadStartTime2 != null) {
            return false;
        }
        String completeMultipartUploadExpirationTime = getCompleteMultipartUploadExpirationTime();
        String completeMultipartUploadExpirationTime2 = completeMultipartResponse.getCompleteMultipartUploadExpirationTime();
        return completeMultipartUploadExpirationTime != null ? completeMultipartUploadExpirationTime.equals(completeMultipartUploadExpirationTime2) : completeMultipartUploadExpirationTime2 == null;
    }

    public String getCompleteMultipartUploadExpirationTime() {
        return this.completeMultipartUploadExpirationTime;
    }

    public String getCompleteMultipartUploadStartTime() {
        return this.completeMultipartUploadStartTime;
    }

    public String getStatus() {
        return this.status;
    }

    public int hashCode() {
        String status = getStatus();
        int i = 43;
        int hashCode = status == null ? 43 : status.hashCode();
        String completeMultipartUploadStartTime = getCompleteMultipartUploadStartTime();
        int hashCode2 = ((hashCode + 59) * 59) + (completeMultipartUploadStartTime == null ? 43 : completeMultipartUploadStartTime.hashCode());
        String completeMultipartUploadExpirationTime = getCompleteMultipartUploadExpirationTime();
        int i2 = hashCode2 * 59;
        if (completeMultipartUploadExpirationTime != null) {
            i = completeMultipartUploadExpirationTime.hashCode();
        }
        return i2 + i;
    }

    @JsonProperty("completeMultipartUploadExpirationTime")
    public void setCompleteMultipartUploadExpirationTime(String str) {
        this.completeMultipartUploadExpirationTime = str;
    }

    @JsonProperty("completeMultipartUploadStartTime")
    public void setCompleteMultipartUploadStartTime(String str) {
        this.completeMultipartUploadStartTime = str;
    }

    @JsonProperty("status")
    public void setStatus(String str) {
        this.status = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("CompleteMultipartResponse(status=");
        outline107.append(getStatus());
        outline107.append(", completeMultipartUploadStartTime=");
        outline107.append(getCompleteMultipartUploadStartTime());
        outline107.append(", completeMultipartUploadExpirationTime=");
        outline107.append(getCompleteMultipartUploadExpirationTime());
        outline107.append(")");
        return outline107.toString();
    }
}
