package com.amazon.clouddrive.extended.model;

import com.amazon.clouddrive.model.CloudDriveRequest;
/* loaded from: classes11.dex */
public class SubscribePlanRequest implements CloudDriveRequest {
    private String marketPlaceId;
    private String paymentInstrumentId;
    private String pendingPlanId;
    private String planId;

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof SubscribePlanRequest) && compareTo((CloudDriveRequest) ((SubscribePlanRequest) obj)) == 0;
    }

    public String getMarketplaceId() {
        return this.marketPlaceId;
    }

    public String getPaymentInstrumentId() {
        return this.paymentInstrumentId;
    }

    public String getPendingPlanId() {
        return this.pendingPlanId;
    }

    public String getPlanId() {
        return this.planId;
    }

    public int hashCode() {
        String str = this.planId;
        int i = 0;
        int hashCode = (str == null ? 0 : str.hashCode()) + 1;
        String str2 = this.pendingPlanId;
        int hashCode2 = hashCode + (str2 == null ? 0 : str2.hashCode());
        String str3 = this.paymentInstrumentId;
        int hashCode3 = hashCode2 + (str3 == null ? 0 : str3.hashCode());
        String str4 = this.marketPlaceId;
        if (str4 != null) {
            i = str4.hashCode();
        }
        return ((hashCode3 + i) * 31) + super.hashCode();
    }

    public void setMarketplaceId(String str) {
        this.marketPlaceId = str;
    }

    public void setPaymentInstrumentId(String str) {
        this.paymentInstrumentId = str;
    }

    public void setPendingPlanId(String str) {
        this.pendingPlanId = str;
    }

    public void setPlanId(String str) {
        this.planId = str;
    }

    @Override // java.lang.Comparable
    public int compareTo(CloudDriveRequest cloudDriveRequest) {
        if (cloudDriveRequest == null) {
            return -1;
        }
        if (cloudDriveRequest == this) {
            return 0;
        }
        if (!(cloudDriveRequest instanceof SubscribePlanRequest)) {
            return 1;
        }
        SubscribePlanRequest subscribePlanRequest = (SubscribePlanRequest) cloudDriveRequest;
        String planId = getPlanId();
        String planId2 = subscribePlanRequest.getPlanId();
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
        String pendingPlanId = getPendingPlanId();
        String pendingPlanId2 = subscribePlanRequest.getPendingPlanId();
        if (pendingPlanId != pendingPlanId2) {
            if (pendingPlanId == null) {
                return -1;
            }
            if (pendingPlanId2 == null) {
                return 1;
            }
            int compareTo2 = pendingPlanId.compareTo(pendingPlanId2);
            if (compareTo2 != 0) {
                return compareTo2;
            }
        }
        String paymentInstrumentId = getPaymentInstrumentId();
        String paymentInstrumentId2 = subscribePlanRequest.getPaymentInstrumentId();
        if (paymentInstrumentId != paymentInstrumentId2) {
            if (paymentInstrumentId == null) {
                return -1;
            }
            if (paymentInstrumentId2 == null) {
                return 1;
            }
            int compareTo3 = paymentInstrumentId.compareTo(paymentInstrumentId2);
            if (compareTo3 != 0) {
                return compareTo3;
            }
        }
        String marketplaceId = getMarketplaceId();
        String marketplaceId2 = subscribePlanRequest.getMarketplaceId();
        if (marketplaceId != marketplaceId2) {
            if (marketplaceId == null) {
                return -1;
            }
            if (marketplaceId2 == null) {
                return 1;
            }
            int compareTo4 = marketplaceId.compareTo(marketplaceId2);
            if (compareTo4 != 0) {
                return compareTo4;
            }
        }
        return 0;
    }
}
