package com.amazon.clouddrive.model;
/* loaded from: classes11.dex */
public class UsageSummary implements Comparable<UsageSummary> {
    private UsageDetail billable;
    private UsageDetail total;

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof UsageSummary) && compareTo((UsageSummary) obj) == 0;
    }

    public UsageDetail getBillable() {
        return this.billable;
    }

    public UsageDetail getTotal() {
        return this.total;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = (getBillable() == null ? 0 : getBillable().hashCode()) + 1;
        if (getTotal() != null) {
            i = getTotal().hashCode();
        }
        return hashCode + i;
    }

    public void setBillable(UsageDetail usageDetail) {
        this.billable = usageDetail;
    }

    public void setTotal(UsageDetail usageDetail) {
        this.total = usageDetail;
    }

    @Override // java.lang.Comparable
    public int compareTo(UsageSummary usageSummary) {
        if (usageSummary == null) {
            return -1;
        }
        if (usageSummary == this) {
            return 0;
        }
        UsageDetail billable = getBillable();
        UsageDetail billable2 = usageSummary.getBillable();
        if (billable != billable2) {
            if (billable == null) {
                return -1;
            }
            if (billable2 == null) {
                return 1;
            }
            int compareTo = billable.compareTo(billable2);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        UsageDetail total = getTotal();
        UsageDetail total2 = usageSummary.getTotal();
        if (total != total2) {
            if (total == null) {
                return -1;
            }
            if (total2 == null) {
                return 1;
            }
            int compareTo2 = total.compareTo(total2);
            if (compareTo2 != 0) {
                return compareTo2;
            }
        }
        return 0;
    }
}
