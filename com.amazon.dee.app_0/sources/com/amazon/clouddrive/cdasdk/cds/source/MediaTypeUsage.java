package com.amazon.clouddrive.cdasdk.cds.source;

import com.amazon.clouddrive.cdasdk.cds.common.ISO8601;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.annotation.JsonProperty;
/* loaded from: classes11.dex */
public class MediaTypeUsage {
    @JsonProperty("count")
    private Long count;
    @JsonProperty("lastUploadedTime")
    private ISO8601 lastUploadedTime;

    protected boolean canEqual(Object obj) {
        return obj instanceof MediaTypeUsage;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof MediaTypeUsage)) {
            return false;
        }
        MediaTypeUsage mediaTypeUsage = (MediaTypeUsage) obj;
        if (!mediaTypeUsage.canEqual(this)) {
            return false;
        }
        Long count = getCount();
        Long count2 = mediaTypeUsage.getCount();
        if (count != null ? !count.equals(count2) : count2 != null) {
            return false;
        }
        ISO8601 lastUploadedTime = getLastUploadedTime();
        ISO8601 lastUploadedTime2 = mediaTypeUsage.getLastUploadedTime();
        return lastUploadedTime != null ? lastUploadedTime.equals(lastUploadedTime2) : lastUploadedTime2 == null;
    }

    public Long getCount() {
        return this.count;
    }

    public ISO8601 getLastUploadedTime() {
        return this.lastUploadedTime;
    }

    public int hashCode() {
        Long count = getCount();
        int i = 43;
        int hashCode = count == null ? 43 : count.hashCode();
        ISO8601 lastUploadedTime = getLastUploadedTime();
        int i2 = (hashCode + 59) * 59;
        if (lastUploadedTime != null) {
            i = lastUploadedTime.hashCode();
        }
        return i2 + i;
    }

    @JsonProperty("count")
    public void setCount(Long l) {
        this.count = l;
    }

    @JsonProperty("lastUploadedTime")
    public void setLastUploadedTime(ISO8601 iso8601) {
        this.lastUploadedTime = iso8601;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("MediaTypeUsage(count=");
        outline107.append(getCount());
        outline107.append(", lastUploadedTime=");
        outline107.append(getLastUploadedTime());
        outline107.append(")");
        return outline107.toString();
    }
}
