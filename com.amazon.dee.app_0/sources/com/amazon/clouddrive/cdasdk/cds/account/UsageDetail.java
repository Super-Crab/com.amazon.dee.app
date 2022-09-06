package com.amazon.clouddrive.cdasdk.cds.account;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.annotation.JsonProperty;
/* loaded from: classes11.dex */
public class UsageDetail {
    @JsonProperty("bytes")
    private Long bytes;
    @JsonProperty("count")
    private Long count;

    protected boolean canEqual(Object obj) {
        return obj instanceof UsageDetail;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof UsageDetail)) {
            return false;
        }
        UsageDetail usageDetail = (UsageDetail) obj;
        if (!usageDetail.canEqual(this)) {
            return false;
        }
        Long count = getCount();
        Long count2 = usageDetail.getCount();
        if (count != null ? !count.equals(count2) : count2 != null) {
            return false;
        }
        Long bytes = getBytes();
        Long bytes2 = usageDetail.getBytes();
        return bytes != null ? bytes.equals(bytes2) : bytes2 == null;
    }

    public Long getBytes() {
        return this.bytes;
    }

    public Long getCount() {
        return this.count;
    }

    public int hashCode() {
        Long count = getCount();
        int i = 43;
        int hashCode = count == null ? 43 : count.hashCode();
        Long bytes = getBytes();
        int i2 = (hashCode + 59) * 59;
        if (bytes != null) {
            i = bytes.hashCode();
        }
        return i2 + i;
    }

    @JsonProperty("bytes")
    public void setBytes(Long l) {
        this.bytes = l;
    }

    @JsonProperty("count")
    public void setCount(Long l) {
        this.count = l;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("UsageDetail(count=");
        outline107.append(getCount());
        outline107.append(", bytes=");
        outline107.append(getBytes());
        outline107.append(")");
        return outline107.toString();
    }
}
