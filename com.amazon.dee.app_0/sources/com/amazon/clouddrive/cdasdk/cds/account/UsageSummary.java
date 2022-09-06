package com.amazon.clouddrive.cdasdk.cds.account;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.annotation.JsonProperty;
/* loaded from: classes11.dex */
public class UsageSummary {
    @JsonProperty("billable")
    private UsageDetail billable;
    @JsonProperty("total")
    private UsageDetail total;

    protected boolean canEqual(Object obj) {
        return obj instanceof UsageSummary;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof UsageSummary)) {
            return false;
        }
        UsageSummary usageSummary = (UsageSummary) obj;
        if (!usageSummary.canEqual(this)) {
            return false;
        }
        UsageDetail billable = getBillable();
        UsageDetail billable2 = usageSummary.getBillable();
        if (billable != null ? !billable.equals(billable2) : billable2 != null) {
            return false;
        }
        UsageDetail total = getTotal();
        UsageDetail total2 = usageSummary.getTotal();
        return total != null ? total.equals(total2) : total2 == null;
    }

    public UsageDetail getBillable() {
        return this.billable;
    }

    public UsageDetail getTotal() {
        return this.total;
    }

    public int hashCode() {
        UsageDetail billable = getBillable();
        int i = 43;
        int hashCode = billable == null ? 43 : billable.hashCode();
        UsageDetail total = getTotal();
        int i2 = (hashCode + 59) * 59;
        if (total != null) {
            i = total.hashCode();
        }
        return i2 + i;
    }

    @JsonProperty("billable")
    public void setBillable(UsageDetail usageDetail) {
        this.billable = usageDetail;
    }

    @JsonProperty("total")
    public void setTotal(UsageDetail usageDetail) {
        this.total = usageDetail;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("UsageSummary(billable=");
        outline107.append(getBillable());
        outline107.append(", total=");
        outline107.append(getTotal());
        outline107.append(")");
        return outline107.toString();
    }
}
