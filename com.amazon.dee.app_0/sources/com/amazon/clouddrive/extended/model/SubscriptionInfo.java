package com.amazon.clouddrive.extended.model;
/* loaded from: classes11.dex */
public class SubscriptionInfo implements Comparable<SubscriptionInfo> {
    private Boolean autoRenewEnabled;
    private String contractEndDate;
    private Integer currentBillingPeriod;
    private Integer currentContractPeriod;
    private Boolean hasOpenProblems;
    private String marketplaceId;
    private String planId;
    private String statusStartDate;
    private String subscriptionId;
    private String subscriptionStartDate;
    private String subscriptionStatus;

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof SubscriptionInfo) && compareTo((SubscriptionInfo) obj) == 0;
    }

    public String getContractEndDate() {
        return this.contractEndDate;
    }

    public Integer getCurrentBillingPeriod() {
        return this.currentBillingPeriod;
    }

    public Integer getCurrentContractPeriod() {
        return this.currentContractPeriod;
    }

    public String getMarketplaceId() {
        return this.marketplaceId;
    }

    public String getPlanId() {
        return this.planId;
    }

    public String getStatusStartDate() {
        return this.statusStartDate;
    }

    public String getSubscriptionId() {
        return this.subscriptionId;
    }

    public String getSubscriptionStartDate() {
        return this.subscriptionStartDate;
    }

    public String getSubscriptionStatus() {
        return this.subscriptionStatus;
    }

    public Boolean hasOpenProblems() {
        return this.hasOpenProblems;
    }

    public int hashCode() {
        String str = this.contractEndDate;
        int i = 0;
        int hashCode = (str == null ? 0 : str.hashCode()) + 1;
        Boolean bool = this.autoRenewEnabled;
        int hashCode2 = hashCode + (bool == null ? 0 : bool.hashCode());
        String str2 = this.statusStartDate;
        int hashCode3 = hashCode2 + (str2 == null ? 0 : str2.hashCode());
        String str3 = this.subscriptionStartDate;
        int hashCode4 = hashCode3 + (str3 == null ? 0 : str3.hashCode());
        Integer num = this.currentBillingPeriod;
        int hashCode5 = hashCode4 + (num == null ? 0 : num.hashCode());
        String str4 = this.planId;
        int hashCode6 = hashCode5 + (str4 == null ? 0 : str4.hashCode());
        Integer num2 = this.currentContractPeriod;
        int hashCode7 = hashCode6 + (num2 == null ? 0 : num2.hashCode());
        String str5 = this.marketplaceId;
        int hashCode8 = hashCode7 + (str5 == null ? 0 : str5.hashCode());
        Boolean bool2 = this.hasOpenProblems;
        int hashCode9 = hashCode8 + (bool2 == null ? 0 : bool2.hashCode());
        String str6 = this.subscriptionStatus;
        int hashCode10 = hashCode9 + (str6 == null ? 0 : str6.hashCode());
        String str7 = this.subscriptionId;
        if (str7 != null) {
            i = str7.hashCode();
        }
        return hashCode10 + i;
    }

    public Boolean isAutoRenewEnabled() {
        return this.autoRenewEnabled;
    }

    public void setAutoRenewEnabled(Boolean bool) {
        this.autoRenewEnabled = bool;
    }

    public void setContractDate(String str) {
        this.contractEndDate = str;
    }

    public void setCurrentBillingPeriod(Integer num) {
        this.currentBillingPeriod = num;
    }

    public void setCurrentContractPeriod(Integer num) {
        this.currentContractPeriod = num;
    }

    public void setHasOpenProblems(Boolean bool) {
        this.hasOpenProblems = bool;
    }

    public void setMarketplaceId(String str) {
        this.marketplaceId = str;
    }

    public void setPlanId(String str) {
        this.planId = str;
    }

    public void setStatusStartDate(String str) {
        this.statusStartDate = str;
    }

    public void setSubscriptionId(String str) {
        this.subscriptionId = str;
    }

    public void setSubscriptionStartDate(String str) {
        this.subscriptionStartDate = str;
    }

    public void setSubscriptionStatus(String str) {
        this.subscriptionStatus = str;
    }

    @Override // java.lang.Comparable
    public int compareTo(SubscriptionInfo subscriptionInfo) {
        if (subscriptionInfo == null) {
            return -1;
        }
        if (subscriptionInfo == this) {
            return 0;
        }
        String contractEndDate = getContractEndDate();
        String contractEndDate2 = subscriptionInfo.getContractEndDate();
        if (contractEndDate != contractEndDate2) {
            if (contractEndDate == null) {
                return -1;
            }
            if (contractEndDate2 == null) {
                return 1;
            }
            int compareTo = contractEndDate.compareTo(contractEndDate2);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        Boolean isAutoRenewEnabled = isAutoRenewEnabled();
        Boolean isAutoRenewEnabled2 = subscriptionInfo.isAutoRenewEnabled();
        if (isAutoRenewEnabled != isAutoRenewEnabled2) {
            if (isAutoRenewEnabled == null) {
                return -1;
            }
            if (isAutoRenewEnabled2 == null) {
                return 1;
            }
            int compareTo2 = isAutoRenewEnabled.compareTo(isAutoRenewEnabled2);
            if (compareTo2 != 0) {
                return compareTo2;
            }
        }
        String statusStartDate = getStatusStartDate();
        String statusStartDate2 = subscriptionInfo.getStatusStartDate();
        if (statusStartDate != statusStartDate2) {
            if (statusStartDate == null) {
                return -1;
            }
            if (statusStartDate2 == null) {
                return 1;
            }
            int compareTo3 = statusStartDate.compareTo(statusStartDate2);
            if (compareTo3 != 0) {
                return compareTo3;
            }
        }
        String subscriptionStartDate = getSubscriptionStartDate();
        String subscriptionStartDate2 = subscriptionInfo.getSubscriptionStartDate();
        if (subscriptionStartDate != subscriptionStartDate2) {
            if (subscriptionStartDate == null) {
                return -1;
            }
            if (subscriptionStartDate2 == null) {
                return 1;
            }
            int compareTo4 = subscriptionStartDate.compareTo(subscriptionStartDate2);
            if (compareTo4 != 0) {
                return compareTo4;
            }
        }
        Integer currentBillingPeriod = getCurrentBillingPeriod();
        Integer currentBillingPeriod2 = subscriptionInfo.getCurrentBillingPeriod();
        if (currentBillingPeriod != currentBillingPeriod2) {
            if (currentBillingPeriod == null) {
                return -1;
            }
            if (currentBillingPeriod2 == null) {
                return 1;
            }
            int compareTo5 = currentBillingPeriod.compareTo(currentBillingPeriod2);
            if (compareTo5 != 0) {
                return compareTo5;
            }
        }
        String planId = getPlanId();
        String planId2 = subscriptionInfo.getPlanId();
        if (planId != planId2) {
            if (planId == null) {
                return -1;
            }
            if (planId2 == null) {
                return 1;
            }
            int compareTo6 = planId.compareTo(planId2);
            if (compareTo6 != 0) {
                return compareTo6;
            }
        }
        Integer currentContractPeriod = getCurrentContractPeriod();
        Integer currentContractPeriod2 = subscriptionInfo.getCurrentContractPeriod();
        if (currentContractPeriod != currentContractPeriod2) {
            if (currentContractPeriod == null) {
                return -1;
            }
            if (currentContractPeriod2 == null) {
                return 1;
            }
            int compareTo7 = currentContractPeriod.compareTo(currentContractPeriod2);
            if (compareTo7 != 0) {
                return compareTo7;
            }
        }
        String marketplaceId = getMarketplaceId();
        String marketplaceId2 = subscriptionInfo.getMarketplaceId();
        if (marketplaceId != marketplaceId2) {
            if (marketplaceId == null) {
                return -1;
            }
            if (marketplaceId2 == null) {
                return 1;
            }
            int compareTo8 = marketplaceId.compareTo(marketplaceId2);
            if (compareTo8 != 0) {
                return compareTo8;
            }
        }
        Boolean hasOpenProblems = hasOpenProblems();
        Boolean hasOpenProblems2 = subscriptionInfo.hasOpenProblems();
        if (hasOpenProblems != hasOpenProblems2) {
            if (hasOpenProblems == null) {
                return -1;
            }
            if (hasOpenProblems2 == null) {
                return 1;
            }
            int compareTo9 = hasOpenProblems.compareTo(hasOpenProblems2);
            if (compareTo9 != 0) {
                return compareTo9;
            }
        }
        String subscriptionStatus = getSubscriptionStatus();
        String subscriptionStatus2 = subscriptionInfo.getSubscriptionStatus();
        if (subscriptionStatus != subscriptionStatus2) {
            if (subscriptionStatus == null) {
                return -1;
            }
            if (subscriptionStatus2 == null) {
                return 1;
            }
            int compareTo10 = subscriptionStatus.compareTo(subscriptionStatus2);
            if (compareTo10 != 0) {
                return compareTo10;
            }
        }
        String subscriptionId = getSubscriptionId();
        String subscriptionId2 = subscriptionInfo.getSubscriptionId();
        if (subscriptionId != subscriptionId2) {
            if (subscriptionId == null) {
                return -1;
            }
            if (subscriptionId2 == null) {
                return 1;
            }
            int compareTo11 = subscriptionId.compareTo(subscriptionId2);
            if (compareTo11 != 0) {
                return compareTo11;
            }
        }
        return 0;
    }
}
