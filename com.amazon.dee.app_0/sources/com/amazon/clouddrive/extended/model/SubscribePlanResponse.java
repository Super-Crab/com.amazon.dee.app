package com.amazon.clouddrive.extended.model;

import com.amazon.clouddrive.model.CloudDriveResponse;
/* loaded from: classes11.dex */
public class SubscribePlanResponse implements CloudDriveResponse {
    private SubscriptionInfo subscriptionInfo;

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof SubscribePlanResponse) && compareTo((CloudDriveResponse) ((SubscribePlanResponse) obj)) == 0;
    }

    public SubscriptionInfo getSubscriptionInfo() {
        return this.subscriptionInfo;
    }

    public int hashCode() {
        SubscriptionInfo subscriptionInfo = this.subscriptionInfo;
        return (((subscriptionInfo == null ? 0 : subscriptionInfo.hashCode()) + 1) * 31) + super.hashCode();
    }

    public void setSubscriptionInfo(SubscriptionInfo subscriptionInfo) {
        this.subscriptionInfo = subscriptionInfo;
    }

    @Override // java.lang.Comparable
    public int compareTo(CloudDriveResponse cloudDriveResponse) {
        if (cloudDriveResponse == null) {
            return -1;
        }
        if (cloudDriveResponse == this) {
            return 0;
        }
        if (!(cloudDriveResponse instanceof SubscribePlanResponse)) {
            return 1;
        }
        SubscriptionInfo subscriptionInfo = getSubscriptionInfo();
        SubscriptionInfo subscriptionInfo2 = ((SubscribePlanResponse) cloudDriveResponse).getSubscriptionInfo();
        if (subscriptionInfo != subscriptionInfo2) {
            if (subscriptionInfo == null) {
                return -1;
            }
            if (subscriptionInfo2 == null) {
                return 1;
            }
            int compareTo = subscriptionInfo.compareTo(subscriptionInfo2);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        return 0;
    }
}
