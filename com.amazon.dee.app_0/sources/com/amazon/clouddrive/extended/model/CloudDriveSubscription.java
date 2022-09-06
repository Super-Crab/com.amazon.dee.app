package com.amazon.clouddrive.extended.model;

import java.util.List;
/* loaded from: classes11.dex */
public class CloudDriveSubscription implements Comparable<CloudDriveSubscription> {
    private Boolean autoRenewEnabled;
    private String baseCurrencyCode;
    private String contractEndDate;
    private Long currentBillingPeriod;
    private Long currentContractPeriod;
    private String expectedStatusEndDate;
    private Long gracePeriodBeforeCancellation;
    private Boolean hasOpenProblems;
    private Boolean isMFARequired;
    private String marketplaceId;
    private String nextBillAmount;
    private String nextBillDate;
    private PendingPlan pendingPlan;
    private String planId;
    private String preferredPaymentPlanId;
    private String statusStartDate;
    private String subscriptionId;
    private List<SubscriptionProblem> subscriptionProblemsList;
    private String subscriptionStartDate;
    private String subscriptionStatus;

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof CloudDriveSubscription) && compareTo((CloudDriveSubscription) obj) == 0;
    }

    public String getBaseCurrencyCode() {
        return this.baseCurrencyCode;
    }

    public String getContractEndDate() {
        return this.contractEndDate;
    }

    public Long getCurrentBillingPeriod() {
        return this.currentBillingPeriod;
    }

    public Long getCurrentContractPeriod() {
        return this.currentContractPeriod;
    }

    public String getExpectedStatusEndDate() {
        return this.expectedStatusEndDate;
    }

    public Long getGracePeriodBeforeCancellation() {
        return this.gracePeriodBeforeCancellation;
    }

    public String getMarketplaceId() {
        return this.marketplaceId;
    }

    public String getNextBillAmount() {
        return this.nextBillAmount;
    }

    public String getNextBillDate() {
        return this.nextBillDate;
    }

    public PendingPlan getPendingPlan() {
        return this.pendingPlan;
    }

    public String getPlanId() {
        return this.planId;
    }

    public String getPreferredPaymentPlanId() {
        return this.preferredPaymentPlanId;
    }

    public String getStatusStartDate() {
        return this.statusStartDate;
    }

    public String getSubscriptionId() {
        return this.subscriptionId;
    }

    public List<SubscriptionProblem> getSubscriptionProblemsList() {
        return this.subscriptionProblemsList;
    }

    public String getSubscriptionStartDate() {
        return this.subscriptionStartDate;
    }

    public String getSubscriptionStatus() {
        return this.subscriptionStatus;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = (getBaseCurrencyCode() == null ? 0 : getBaseCurrencyCode().hashCode()) + 1 + (isAutoRenewEnabled() == null ? 0 : isAutoRenewEnabled().hashCode()) + (getCurrentContractPeriod() == null ? 0 : getCurrentContractPeriod().hashCode()) + (getSubscriptionId() == null ? 0 : getSubscriptionId().hashCode()) + (getSubscriptionStartDate() == null ? 0 : getSubscriptionStartDate().hashCode()) + (getMarketplaceId() == null ? 0 : getMarketplaceId().hashCode()) + (getPendingPlan() == null ? 0 : getPendingPlan().hashCode()) + (getExpectedStatusEndDate() == null ? 0 : getExpectedStatusEndDate().hashCode()) + (getSubscriptionProblemsList() == null ? 0 : getSubscriptionProblemsList().hashCode()) + (getPlanId() == null ? 0 : getPlanId().hashCode()) + (getPreferredPaymentPlanId() == null ? 0 : getPreferredPaymentPlanId().hashCode()) + (getGracePeriodBeforeCancellation() == null ? 0 : getGracePeriodBeforeCancellation().hashCode()) + (getCurrentBillingPeriod() == null ? 0 : getCurrentBillingPeriod().hashCode()) + (getNextBillDate() == null ? 0 : getNextBillDate().hashCode()) + (getNextBillAmount() == null ? 0 : getNextBillAmount().hashCode()) + (getContractEndDate() == null ? 0 : getContractEndDate().hashCode()) + (getSubscriptionStatus() == null ? 0 : getSubscriptionStatus().hashCode()) + (isHasOpenProblems() == null ? 0 : isHasOpenProblems().hashCode()) + (getStatusStartDate() == null ? 0 : getStatusStartDate().hashCode());
        if (isMFARequired() != null) {
            i = isMFARequired().hashCode();
        }
        return hashCode + i;
    }

    public Boolean isAutoRenewEnabled() {
        return this.autoRenewEnabled;
    }

    public Boolean isHasOpenProblems() {
        return this.hasOpenProblems;
    }

    public Boolean isMFARequired() {
        return this.isMFARequired;
    }

    public void setAutoRenewEnabled(Boolean bool) {
        this.autoRenewEnabled = bool;
    }

    public void setBaseCurrencyCode(String str) {
        this.baseCurrencyCode = str;
    }

    public void setContractEndDate(String str) {
        this.contractEndDate = str;
    }

    public void setCurrentBillingPeriod(Long l) {
        this.currentBillingPeriod = l;
    }

    public void setCurrentContractPeriod(Long l) {
        this.currentContractPeriod = l;
    }

    public void setExpectedStatusEndDate(String str) {
        this.expectedStatusEndDate = str;
    }

    public void setGracePeriodBeforeCancellation(Long l) {
        this.gracePeriodBeforeCancellation = l;
    }

    public void setHasOpenProblems(Boolean bool) {
        this.hasOpenProblems = bool;
    }

    public void setMFARequired(Boolean bool) {
        this.isMFARequired = bool;
    }

    public void setMarketplaceId(String str) {
        this.marketplaceId = str;
    }

    public void setNextBillAmount(String str) {
        this.nextBillAmount = str;
    }

    public void setNextBillDate(String str) {
        this.nextBillDate = str;
    }

    public void setPendingPlan(PendingPlan pendingPlan) {
        this.pendingPlan = pendingPlan;
    }

    public void setPlanId(String str) {
        this.planId = str;
    }

    public void setPreferredPaymentPlanId(String str) {
        this.preferredPaymentPlanId = str;
    }

    public void setStatusStartDate(String str) {
        this.statusStartDate = str;
    }

    public void setSubscriptionId(String str) {
        this.subscriptionId = str;
    }

    public void setSubscriptionProblemsList(List<SubscriptionProblem> list) {
        this.subscriptionProblemsList = list;
    }

    public void setSubscriptionStartDate(String str) {
        this.subscriptionStartDate = str;
    }

    public void setSubscriptionStatus(String str) {
        this.subscriptionStatus = str;
    }

    @Override // java.lang.Comparable
    public int compareTo(CloudDriveSubscription cloudDriveSubscription) {
        if (cloudDriveSubscription == null) {
            return -1;
        }
        if (cloudDriveSubscription == this) {
            return 0;
        }
        String baseCurrencyCode = getBaseCurrencyCode();
        String baseCurrencyCode2 = cloudDriveSubscription.getBaseCurrencyCode();
        if (baseCurrencyCode != baseCurrencyCode2) {
            if (baseCurrencyCode == null) {
                return -1;
            }
            if (baseCurrencyCode2 == null) {
                return 1;
            }
            int compareTo = baseCurrencyCode.compareTo(baseCurrencyCode2);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        Boolean isAutoRenewEnabled = isAutoRenewEnabled();
        Boolean isAutoRenewEnabled2 = cloudDriveSubscription.isAutoRenewEnabled();
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
        Long currentContractPeriod = getCurrentContractPeriod();
        Long currentContractPeriod2 = cloudDriveSubscription.getCurrentContractPeriod();
        if (currentContractPeriod != currentContractPeriod2) {
            if (currentContractPeriod == null) {
                return -1;
            }
            if (currentContractPeriod2 == null) {
                return 1;
            }
            int compareTo3 = currentContractPeriod.compareTo(currentContractPeriod2);
            if (compareTo3 != 0) {
                return compareTo3;
            }
        }
        String subscriptionId = getSubscriptionId();
        String subscriptionId2 = cloudDriveSubscription.getSubscriptionId();
        if (subscriptionId != subscriptionId2) {
            if (subscriptionId == null) {
                return -1;
            }
            if (subscriptionId2 == null) {
                return 1;
            }
            int compareTo4 = subscriptionId.compareTo(subscriptionId2);
            if (compareTo4 != 0) {
                return compareTo4;
            }
        }
        String subscriptionStartDate = getSubscriptionStartDate();
        String subscriptionStartDate2 = cloudDriveSubscription.getSubscriptionStartDate();
        if (subscriptionStartDate != subscriptionStartDate2) {
            if (subscriptionStartDate == null) {
                return -1;
            }
            if (subscriptionStartDate2 == null) {
                return 1;
            }
            int compareTo5 = subscriptionStartDate.compareTo(subscriptionStartDate2);
            if (compareTo5 != 0) {
                return compareTo5;
            }
        }
        String marketplaceId = getMarketplaceId();
        String marketplaceId2 = cloudDriveSubscription.getMarketplaceId();
        if (marketplaceId != marketplaceId2) {
            if (marketplaceId == null) {
                return -1;
            }
            if (marketplaceId2 == null) {
                return 1;
            }
            int compareTo6 = marketplaceId.compareTo(marketplaceId2);
            if (compareTo6 != 0) {
                return compareTo6;
            }
        }
        PendingPlan pendingPlan = getPendingPlan();
        PendingPlan pendingPlan2 = cloudDriveSubscription.getPendingPlan();
        if (pendingPlan != pendingPlan2) {
            if (pendingPlan == null) {
                return -1;
            }
            if (pendingPlan2 == null) {
                return 1;
            }
            int compareTo7 = pendingPlan.compareTo(pendingPlan2);
            if (compareTo7 != 0) {
                return compareTo7;
            }
        }
        String expectedStatusEndDate = getExpectedStatusEndDate();
        String expectedStatusEndDate2 = cloudDriveSubscription.getExpectedStatusEndDate();
        if (expectedStatusEndDate != expectedStatusEndDate2) {
            if (expectedStatusEndDate == null) {
                return -1;
            }
            if (expectedStatusEndDate2 == null) {
                return 1;
            }
            int compareTo8 = expectedStatusEndDate.compareTo(expectedStatusEndDate2);
            if (compareTo8 != 0) {
                return compareTo8;
            }
        }
        List<SubscriptionProblem> subscriptionProblemsList = getSubscriptionProblemsList();
        List<SubscriptionProblem> subscriptionProblemsList2 = cloudDriveSubscription.getSubscriptionProblemsList();
        if (subscriptionProblemsList != subscriptionProblemsList2) {
            if (subscriptionProblemsList == null) {
                return -1;
            }
            if (subscriptionProblemsList2 == null) {
                return 1;
            }
            if (subscriptionProblemsList instanceof Comparable) {
                int compareTo9 = ((Comparable) subscriptionProblemsList).compareTo(subscriptionProblemsList2);
                if (compareTo9 != 0) {
                    return compareTo9;
                }
            } else if (!subscriptionProblemsList.equals(subscriptionProblemsList2)) {
                int hashCode = subscriptionProblemsList.hashCode();
                int hashCode2 = subscriptionProblemsList2.hashCode();
                if (hashCode < hashCode2) {
                    return -1;
                }
                if (hashCode > hashCode2) {
                    return 1;
                }
            }
        }
        String planId = getPlanId();
        String planId2 = cloudDriveSubscription.getPlanId();
        if (planId != planId2) {
            if (planId == null) {
                return -1;
            }
            if (planId2 == null) {
                return 1;
            }
            int compareTo10 = planId.compareTo(planId2);
            if (compareTo10 != 0) {
                return compareTo10;
            }
        }
        String preferredPaymentPlanId = getPreferredPaymentPlanId();
        String preferredPaymentPlanId2 = cloudDriveSubscription.getPreferredPaymentPlanId();
        if (preferredPaymentPlanId != preferredPaymentPlanId2) {
            if (preferredPaymentPlanId == null) {
                return -1;
            }
            if (preferredPaymentPlanId2 == null) {
                return 1;
            }
            int compareTo11 = preferredPaymentPlanId.compareTo(preferredPaymentPlanId2);
            if (compareTo11 != 0) {
                return compareTo11;
            }
        }
        Long gracePeriodBeforeCancellation = getGracePeriodBeforeCancellation();
        Long gracePeriodBeforeCancellation2 = cloudDriveSubscription.getGracePeriodBeforeCancellation();
        if (gracePeriodBeforeCancellation != gracePeriodBeforeCancellation2) {
            if (gracePeriodBeforeCancellation == null) {
                return -1;
            }
            if (gracePeriodBeforeCancellation2 == null) {
                return 1;
            }
            int compareTo12 = gracePeriodBeforeCancellation.compareTo(gracePeriodBeforeCancellation2);
            if (compareTo12 != 0) {
                return compareTo12;
            }
        }
        Long currentBillingPeriod = getCurrentBillingPeriod();
        Long currentBillingPeriod2 = cloudDriveSubscription.getCurrentBillingPeriod();
        if (currentBillingPeriod != currentBillingPeriod2) {
            if (currentBillingPeriod == null) {
                return -1;
            }
            if (currentBillingPeriod2 == null) {
                return 1;
            }
            int compareTo13 = currentBillingPeriod.compareTo(currentBillingPeriod2);
            if (compareTo13 != 0) {
                return compareTo13;
            }
        }
        String nextBillDate = getNextBillDate();
        String nextBillDate2 = cloudDriveSubscription.getNextBillDate();
        if (nextBillDate != nextBillDate2) {
            if (nextBillDate == null) {
                return -1;
            }
            if (nextBillDate2 == null) {
                return 1;
            }
            int compareTo14 = nextBillDate.compareTo(nextBillDate2);
            if (compareTo14 != 0) {
                return compareTo14;
            }
        }
        String nextBillAmount = getNextBillAmount();
        String nextBillAmount2 = cloudDriveSubscription.getNextBillAmount();
        if (nextBillAmount != nextBillAmount2) {
            if (nextBillAmount == null) {
                return -1;
            }
            if (nextBillAmount2 == null) {
                return 1;
            }
            int compareTo15 = nextBillAmount.compareTo(nextBillAmount2);
            if (compareTo15 != 0) {
                return compareTo15;
            }
        }
        String contractEndDate = getContractEndDate();
        String contractEndDate2 = cloudDriveSubscription.getContractEndDate();
        if (contractEndDate != contractEndDate2) {
            if (contractEndDate == null) {
                return -1;
            }
            if (contractEndDate2 == null) {
                return 1;
            }
            int compareTo16 = contractEndDate.compareTo(contractEndDate2);
            if (compareTo16 != 0) {
                return compareTo16;
            }
        }
        String subscriptionStatus = getSubscriptionStatus();
        String subscriptionStatus2 = cloudDriveSubscription.getSubscriptionStatus();
        if (subscriptionStatus != subscriptionStatus2) {
            if (subscriptionStatus == null) {
                return -1;
            }
            if (subscriptionStatus2 == null) {
                return 1;
            }
            int compareTo17 = subscriptionStatus.compareTo(subscriptionStatus2);
            if (compareTo17 != 0) {
                return compareTo17;
            }
        }
        Boolean isHasOpenProblems = isHasOpenProblems();
        Boolean isHasOpenProblems2 = cloudDriveSubscription.isHasOpenProblems();
        if (isHasOpenProblems != isHasOpenProblems2) {
            if (isHasOpenProblems == null) {
                return -1;
            }
            if (isHasOpenProblems2 == null) {
                return 1;
            }
            int compareTo18 = isHasOpenProblems.compareTo(isHasOpenProblems2);
            if (compareTo18 != 0) {
                return compareTo18;
            }
        }
        String statusStartDate = getStatusStartDate();
        String statusStartDate2 = cloudDriveSubscription.getStatusStartDate();
        if (statusStartDate != statusStartDate2) {
            if (statusStartDate == null) {
                return -1;
            }
            if (statusStartDate2 == null) {
                return 1;
            }
            int compareTo19 = statusStartDate.compareTo(statusStartDate2);
            if (compareTo19 != 0) {
                return compareTo19;
            }
        }
        Boolean isMFARequired = isMFARequired();
        Boolean isMFARequired2 = cloudDriveSubscription.isMFARequired();
        if (isMFARequired != isMFARequired2) {
            if (isMFARequired == null) {
                return -1;
            }
            if (isMFARequired2 == null) {
                return 1;
            }
            int compareTo20 = isMFARequired.compareTo(isMFARequired2);
            if (compareTo20 != 0) {
                return compareTo20;
            }
        }
        return 0;
    }
}
