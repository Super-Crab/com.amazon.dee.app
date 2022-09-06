package com.amazon.clouddrive.extended.model;
/* loaded from: classes11.dex */
public class PendingPlan implements Comparable<PendingPlan> {
    private String endDate;
    private String planId;
    private String startDate;

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof PendingPlan) && compareTo((PendingPlan) obj) == 0;
    }

    public String getEndDate() {
        return this.endDate;
    }

    public String getPlanId() {
        return this.planId;
    }

    public String getStartDate() {
        return this.startDate;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = (getPlanId() == null ? 0 : getPlanId().hashCode()) + 1 + (getEndDate() == null ? 0 : getEndDate().hashCode());
        if (getStartDate() != null) {
            i = getStartDate().hashCode();
        }
        return hashCode + i;
    }

    public void setEndDate(String str) {
        this.endDate = str;
    }

    public void setPlanId(String str) {
        this.planId = str;
    }

    public void setStartDate(String str) {
        this.startDate = str;
    }

    @Override // java.lang.Comparable
    public int compareTo(PendingPlan pendingPlan) {
        if (pendingPlan == null) {
            return -1;
        }
        if (pendingPlan == this) {
            return 0;
        }
        String planId = getPlanId();
        String planId2 = pendingPlan.getPlanId();
        if (planId != planId2) {
            if (planId == null) {
                return -1;
            }
            if (planId2 == null) {
                return 1;
            }
            int compareTo = planId.compareTo(planId2);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        String endDate = getEndDate();
        String endDate2 = pendingPlan.getEndDate();
        if (endDate != endDate2) {
            if (endDate == null) {
                return -1;
            }
            if (endDate2 == null) {
                return 1;
            }
            int compareTo2 = endDate.compareTo(endDate2);
            if (compareTo2 != 0) {
                return compareTo2;
            }
        }
        String startDate = getStartDate();
        String startDate2 = pendingPlan.getStartDate();
        if (startDate != startDate2) {
            if (startDate == null) {
                return -1;
            }
            if (startDate2 == null) {
                return 1;
            }
            int compareTo3 = startDate.compareTo(startDate2);
            if (compareTo3 != 0) {
                return compareTo3;
            }
        }
        return 0;
    }
}
