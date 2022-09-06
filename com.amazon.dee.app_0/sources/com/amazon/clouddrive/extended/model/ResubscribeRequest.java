package com.amazon.clouddrive.extended.model;

import com.amazon.clouddrive.model.CloudDriveRequest;
/* loaded from: classes11.dex */
public class ResubscribeRequest implements CloudDriveRequest {
    private String marketplaceId;
    private String paymentInstrumentId;
    private String pendingPlanId;
    private String planIdToSubscribe;
    private Double refundPercent;
    private String subscriptionId;

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof ResubscribeRequest) && compareTo((CloudDriveRequest) ((ResubscribeRequest) obj)) == 0;
    }

    public String getMarketplaceId() {
        return this.marketplaceId;
    }

    public String getPaymentInstrumentId() {
        return this.paymentInstrumentId;
    }

    public String getPendingPlanId() {
        return this.pendingPlanId;
    }

    public String getPlanIdToSubscribe() {
        return this.planIdToSubscribe;
    }

    public Double getRefundPercent() {
        return this.refundPercent;
    }

    public String getSubscriptionId() {
        return this.subscriptionId;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = (getPlanIdToSubscribe() == null ? 0 : getPlanIdToSubscribe().hashCode()) + 1 + (getPendingPlanId() == null ? 0 : getPendingPlanId().hashCode()) + (getMarketplaceId() == null ? 0 : getMarketplaceId().hashCode()) + (getPaymentInstrumentId() == null ? 0 : getPaymentInstrumentId().hashCode()) + (getSubscriptionId() == null ? 0 : getSubscriptionId().hashCode());
        if (getRefundPercent() != null) {
            i = getRefundPercent().hashCode();
        }
        return ((hashCode + i) * 31) + super.hashCode();
    }

    public void setMarketplaceId(String str) {
        this.marketplaceId = str;
    }

    public void setPaymentInstrumentId(String str) {
        this.paymentInstrumentId = str;
    }

    public void setPendingPlanId(String str) {
        this.pendingPlanId = str;
    }

    public void setPlanIdToSubscribe(String str) {
        this.planIdToSubscribe = str;
    }

    public void setRefundPercent(Double d) {
        this.refundPercent = d;
    }

    public void setSubscriptionId(String str) {
        this.subscriptionId = str;
    }

    @Override // java.lang.Comparable
    public int compareTo(CloudDriveRequest cloudDriveRequest) {
        if (cloudDriveRequest == null) {
            return -1;
        }
        if (cloudDriveRequest == this) {
            return 0;
        }
        if (!(cloudDriveRequest instanceof ResubscribeRequest)) {
            return 1;
        }
        ResubscribeRequest resubscribeRequest = (ResubscribeRequest) cloudDriveRequest;
        String planIdToSubscribe = getPlanIdToSubscribe();
        String planIdToSubscribe2 = resubscribeRequest.getPlanIdToSubscribe();
        if (planIdToSubscribe != planIdToSubscribe2) {
            if (planIdToSubscribe == null) {
                return -1;
            }
            if (planIdToSubscribe2 == null) {
                return 1;
            }
            int compareTo = planIdToSubscribe.compareTo(planIdToSubscribe2);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        String pendingPlanId = getPendingPlanId();
        String pendingPlanId2 = resubscribeRequest.getPendingPlanId();
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
        String marketplaceId = getMarketplaceId();
        String marketplaceId2 = resubscribeRequest.getMarketplaceId();
        if (marketplaceId != marketplaceId2) {
            if (marketplaceId == null) {
                return -1;
            }
            if (marketplaceId2 == null) {
                return 1;
            }
            int compareTo3 = marketplaceId.compareTo(marketplaceId2);
            if (compareTo3 != 0) {
                return compareTo3;
            }
        }
        String paymentInstrumentId = getPaymentInstrumentId();
        String paymentInstrumentId2 = resubscribeRequest.getPaymentInstrumentId();
        if (paymentInstrumentId != paymentInstrumentId2) {
            if (paymentInstrumentId == null) {
                return -1;
            }
            if (paymentInstrumentId2 == null) {
                return 1;
            }
            int compareTo4 = paymentInstrumentId.compareTo(paymentInstrumentId2);
            if (compareTo4 != 0) {
                return compareTo4;
            }
        }
        String subscriptionId = getSubscriptionId();
        String subscriptionId2 = resubscribeRequest.getSubscriptionId();
        if (subscriptionId != subscriptionId2) {
            if (subscriptionId == null) {
                return -1;
            }
            if (subscriptionId2 == null) {
                return 1;
            }
            int compareTo5 = subscriptionId.compareTo(subscriptionId2);
            if (compareTo5 != 0) {
                return compareTo5;
            }
        }
        Double refundPercent = getRefundPercent();
        Double refundPercent2 = resubscribeRequest.getRefundPercent();
        if (refundPercent != refundPercent2) {
            if (refundPercent == null) {
                return -1;
            }
            if (refundPercent2 == null) {
                return 1;
            }
            int compareTo6 = refundPercent.compareTo(refundPercent2);
            if (compareTo6 != 0) {
                return compareTo6;
            }
        }
        return 0;
    }
}
