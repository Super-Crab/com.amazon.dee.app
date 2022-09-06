package com.amazon.clouddrive.extended.model;

import java.util.Map;
/* loaded from: classes11.dex */
public class ServicePlan implements Comparable<ServicePlan> {
    private boolean available;
    private BillingSchedule billingSchedule;
    private Period contractLength;
    private boolean isPromotion;
    private String marketplaceId;
    private String planDescription;
    private String planGroupId;
    private String planId;
    private String planType;
    private String priceAttribute;
    private boolean renewable;
    private Map<String, Long> storageMap;

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof ServicePlan) && compareTo((ServicePlan) obj) == 0;
    }

    public BillingSchedule getBillingSchedule() {
        return this.billingSchedule;
    }

    public Period getContractLength() {
        return this.contractLength;
    }

    public String getMarketplaceId() {
        return this.marketplaceId;
    }

    public String getPlanDescription() {
        return this.planDescription;
    }

    public String getPlanGroupId() {
        return this.planGroupId;
    }

    public String getPlanId() {
        return this.planId;
    }

    public String getPlanType() {
        return this.planType;
    }

    public String getPriceAttribute() {
        return this.priceAttribute;
    }

    public Map<String, Long> getStorageMap() {
        return this.storageMap;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = (getPlanType() == null ? 0 : getPlanType().hashCode()) + 1 + (getPlanId() == null ? 0 : getPlanId().hashCode()) + (isRenewable() ? 1 : 0) + (getPlanDescription() == null ? 0 : getPlanDescription().hashCode()) + (getStorageMap() == null ? 0 : getStorageMap().hashCode()) + (isPromotion() ? 1 : 0) + (getMarketplaceId() == null ? 0 : getMarketplaceId().hashCode()) + (getBillingSchedule() == null ? 0 : getBillingSchedule().hashCode()) + (getPriceAttribute() == null ? 0 : getPriceAttribute().hashCode()) + (isAvailable() ? 1 : 0) + (getContractLength() == null ? 0 : getContractLength().hashCode());
        if (getPlanGroupId() != null) {
            i = getPlanGroupId().hashCode();
        }
        return hashCode + i;
    }

    public boolean isAvailable() {
        return this.available;
    }

    public boolean isPromotion() {
        return this.isPromotion;
    }

    public boolean isRenewable() {
        return this.renewable;
    }

    public void setAvailable(boolean z) {
        this.available = z;
    }

    public void setBillingSchedule(BillingSchedule billingSchedule) {
        this.billingSchedule = billingSchedule;
    }

    public void setContractLength(Period period) {
        this.contractLength = period;
    }

    public void setMarketplaceId(String str) {
        this.marketplaceId = str;
    }

    public void setPlanDescription(String str) {
        this.planDescription = str;
    }

    public void setPlanGroupId(String str) {
        this.planGroupId = str;
    }

    public void setPlanId(String str) {
        this.planId = str;
    }

    public void setPlanType(String str) {
        this.planType = str;
    }

    public void setPriceAttribute(String str) {
        this.priceAttribute = str;
    }

    public void setPromotion(boolean z) {
        this.isPromotion = z;
    }

    public void setRenewable(boolean z) {
        this.renewable = z;
    }

    public void setStorageMap(Map<String, Long> map) {
        this.storageMap = map;
    }

    @Override // java.lang.Comparable
    public int compareTo(ServicePlan servicePlan) {
        if (servicePlan == null) {
            return -1;
        }
        if (servicePlan == this) {
            return 0;
        }
        String planType = getPlanType();
        String planType2 = servicePlan.getPlanType();
        if (planType != planType2) {
            if (planType == null) {
                return -1;
            }
            if (planType2 == null) {
                return 1;
            }
            int compareTo = planType.compareTo(planType2);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        String planId = getPlanId();
        String planId2 = servicePlan.getPlanId();
        if (planId != planId2) {
            if (planId == null) {
                return -1;
            }
            if (planId2 == null) {
                return 1;
            }
            int compareTo2 = planId.compareTo(planId2);
            if (compareTo2 != 0) {
                return compareTo2;
            }
        }
        if (!isRenewable() && servicePlan.isRenewable()) {
            return -1;
        }
        if (isRenewable() && !servicePlan.isRenewable()) {
            return 1;
        }
        String planDescription = getPlanDescription();
        String planDescription2 = servicePlan.getPlanDescription();
        if (planDescription != planDescription2) {
            if (planDescription == null) {
                return -1;
            }
            if (planDescription2 == null) {
                return 1;
            }
            int compareTo3 = planDescription.compareTo(planDescription2);
            if (compareTo3 != 0) {
                return compareTo3;
            }
        }
        Map<String, Long> storageMap = getStorageMap();
        Map<String, Long> storageMap2 = servicePlan.getStorageMap();
        if (storageMap != storageMap2) {
            if (storageMap == null) {
                return -1;
            }
            if (storageMap2 == null) {
                return 1;
            }
            if (storageMap instanceof Comparable) {
                int compareTo4 = ((Comparable) storageMap).compareTo(storageMap2);
                if (compareTo4 != 0) {
                    return compareTo4;
                }
            } else if (!storageMap.equals(storageMap2)) {
                int hashCode = storageMap.hashCode();
                int hashCode2 = storageMap2.hashCode();
                if (hashCode < hashCode2) {
                    return -1;
                }
                if (hashCode > hashCode2) {
                    return 1;
                }
            }
        }
        if (!isPromotion() && servicePlan.isPromotion()) {
            return -1;
        }
        if (isPromotion() && !servicePlan.isPromotion()) {
            return 1;
        }
        String marketplaceId = getMarketplaceId();
        String marketplaceId2 = servicePlan.getMarketplaceId();
        if (marketplaceId != marketplaceId2) {
            if (marketplaceId == null) {
                return -1;
            }
            if (marketplaceId2 == null) {
                return 1;
            }
            int compareTo5 = marketplaceId.compareTo(marketplaceId2);
            if (compareTo5 != 0) {
                return compareTo5;
            }
        }
        BillingSchedule billingSchedule = getBillingSchedule();
        BillingSchedule billingSchedule2 = servicePlan.getBillingSchedule();
        if (billingSchedule != billingSchedule2) {
            if (billingSchedule == null) {
                return -1;
            }
            if (billingSchedule2 == null) {
                return 1;
            }
            int compareTo6 = billingSchedule.compareTo(billingSchedule2);
            if (compareTo6 != 0) {
                return compareTo6;
            }
        }
        String priceAttribute = getPriceAttribute();
        String priceAttribute2 = servicePlan.getPriceAttribute();
        if (priceAttribute != priceAttribute2) {
            if (priceAttribute == null) {
                return -1;
            }
            if (priceAttribute2 == null) {
                return 1;
            }
            int compareTo7 = priceAttribute.compareTo(priceAttribute2);
            if (compareTo7 != 0) {
                return compareTo7;
            }
        }
        if (!isAvailable() && servicePlan.isAvailable()) {
            return -1;
        }
        if (isAvailable() && !servicePlan.isAvailable()) {
            return 1;
        }
        Period contractLength = getContractLength();
        Period contractLength2 = servicePlan.getContractLength();
        if (contractLength != contractLength2) {
            if (contractLength == null) {
                return -1;
            }
            if (contractLength2 == null) {
                return 1;
            }
            int compareTo8 = contractLength.compareTo(contractLength2);
            if (compareTo8 != 0) {
                return compareTo8;
            }
        }
        String planGroupId = getPlanGroupId();
        String planGroupId2 = servicePlan.getPlanGroupId();
        if (planGroupId != planGroupId2) {
            if (planGroupId == null) {
                return -1;
            }
            if (planGroupId2 == null) {
                return 1;
            }
            int compareTo9 = planGroupId.compareTo(planGroupId2);
            if (compareTo9 != 0) {
                return compareTo9;
            }
        }
        return 0;
    }
}
