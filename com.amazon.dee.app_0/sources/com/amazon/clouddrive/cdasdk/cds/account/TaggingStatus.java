package com.amazon.clouddrive.cdasdk.cds.account;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.annotation.JsonProperty;
/* loaded from: classes11.dex */
public class TaggingStatus {
    @JsonProperty("status")
    private String status;

    protected boolean canEqual(Object obj) {
        return obj instanceof TaggingStatus;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof TaggingStatus)) {
            return false;
        }
        TaggingStatus taggingStatus = (TaggingStatus) obj;
        if (!taggingStatus.canEqual(this)) {
            return false;
        }
        String status = getStatus();
        String status2 = taggingStatus.getStatus();
        return status != null ? status.equals(status2) : status2 == null;
    }

    public String getStatus() {
        return this.status;
    }

    public int hashCode() {
        String status = getStatus();
        return 59 + (status == null ? 43 : status.hashCode());
    }

    @JsonProperty("status")
    public void setStatus(String str) {
        this.status = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("TaggingStatus(status=");
        outline107.append(getStatus());
        outline107.append(")");
        return outline107.toString();
    }
}
