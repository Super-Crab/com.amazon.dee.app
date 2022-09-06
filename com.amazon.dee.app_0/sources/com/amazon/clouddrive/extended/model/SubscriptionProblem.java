package com.amazon.clouddrive.extended.model;
/* loaded from: classes11.dex */
public class SubscriptionProblem implements Comparable<SubscriptionProblem> {
    private long billingPeriodNumber;
    private String effectiveDate;
    private String problemCode;
    private String problemID;
    private String problemStatus;

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof SubscriptionProblem) && compareTo((SubscriptionProblem) obj) == 0;
    }

    public long getBillingPeriodNumber() {
        return this.billingPeriodNumber;
    }

    public String getEffectiveDate() {
        return this.effectiveDate;
    }

    public String getProblemCode() {
        return this.problemCode;
    }

    public String getProblemID() {
        return this.problemID;
    }

    public String getProblemStatus() {
        return this.problemStatus;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = (getEffectiveDate() == null ? 0 : getEffectiveDate().hashCode()) + 1 + ((int) getBillingPeriodNumber()) + (getProblemID() == null ? 0 : getProblemID().hashCode()) + (getProblemCode() == null ? 0 : getProblemCode().hashCode());
        if (getProblemStatus() != null) {
            i = getProblemStatus().hashCode();
        }
        return hashCode + i;
    }

    public void setBillingPeriodNumber(long j) {
        this.billingPeriodNumber = j;
    }

    public void setEffectiveDate(String str) {
        this.effectiveDate = str;
    }

    public void setProblemCode(String str) {
        this.problemCode = str;
    }

    public void setProblemID(String str) {
        this.problemID = str;
    }

    public void setProblemStatus(String str) {
        this.problemStatus = str;
    }

    @Override // java.lang.Comparable
    public int compareTo(SubscriptionProblem subscriptionProblem) {
        if (subscriptionProblem == null) {
            return -1;
        }
        if (subscriptionProblem == this) {
            return 0;
        }
        String effectiveDate = getEffectiveDate();
        String effectiveDate2 = subscriptionProblem.getEffectiveDate();
        if (effectiveDate != effectiveDate2) {
            if (effectiveDate == null) {
                return -1;
            }
            if (effectiveDate2 == null) {
                return 1;
            }
            int compareTo = effectiveDate.compareTo(effectiveDate2);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        if (getBillingPeriodNumber() < subscriptionProblem.getBillingPeriodNumber()) {
            return -1;
        }
        if (getBillingPeriodNumber() > subscriptionProblem.getBillingPeriodNumber()) {
            return 1;
        }
        String problemID = getProblemID();
        String problemID2 = subscriptionProblem.getProblemID();
        if (problemID != problemID2) {
            if (problemID == null) {
                return -1;
            }
            if (problemID2 == null) {
                return 1;
            }
            int compareTo2 = problemID.compareTo(problemID2);
            if (compareTo2 != 0) {
                return compareTo2;
            }
        }
        String problemCode = getProblemCode();
        String problemCode2 = subscriptionProblem.getProblemCode();
        if (problemCode != problemCode2) {
            if (problemCode == null) {
                return -1;
            }
            if (problemCode2 == null) {
                return 1;
            }
            int compareTo3 = problemCode.compareTo(problemCode2);
            if (compareTo3 != 0) {
                return compareTo3;
            }
        }
        String problemStatus = getProblemStatus();
        String problemStatus2 = subscriptionProblem.getProblemStatus();
        if (problemStatus != problemStatus2) {
            if (problemStatus == null) {
                return -1;
            }
            if (problemStatus2 == null) {
                return 1;
            }
            int compareTo4 = problemStatus.compareTo(problemStatus2);
            if (compareTo4 != 0) {
                return compareTo4;
            }
        }
        return 0;
    }
}
