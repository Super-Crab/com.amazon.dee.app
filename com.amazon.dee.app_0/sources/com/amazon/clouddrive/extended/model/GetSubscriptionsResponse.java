package com.amazon.clouddrive.extended.model;

import com.amazon.clouddrive.model.CloudDriveResponse;
import java.util.List;
/* loaded from: classes11.dex */
public class GetSubscriptionsResponse implements CloudDriveResponse {
    private List<CloudDriveSubscription> subscriptionsList;

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof GetSubscriptionsResponse) && compareTo((CloudDriveResponse) ((GetSubscriptionsResponse) obj)) == 0;
    }

    public List<CloudDriveSubscription> getSubscriptionsList() {
        return this.subscriptionsList;
    }

    public int hashCode() {
        return (((getSubscriptionsList() == null ? 0 : getSubscriptionsList().hashCode()) + 1) * 31) + super.hashCode();
    }

    public void setSubscriptionsList(List<CloudDriveSubscription> list) {
        this.subscriptionsList = list;
    }

    @Override // java.lang.Comparable
    public int compareTo(CloudDriveResponse cloudDriveResponse) {
        if (cloudDriveResponse == null) {
            return -1;
        }
        if (cloudDriveResponse == this) {
            return 0;
        }
        if (!(cloudDriveResponse instanceof GetSubscriptionsResponse)) {
            return 1;
        }
        List<CloudDriveSubscription> subscriptionsList = getSubscriptionsList();
        List<CloudDriveSubscription> subscriptionsList2 = ((GetSubscriptionsResponse) cloudDriveResponse).getSubscriptionsList();
        if (subscriptionsList != subscriptionsList2) {
            if (subscriptionsList == null) {
                return -1;
            }
            if (subscriptionsList2 == null) {
                return 1;
            }
            if (subscriptionsList instanceof Comparable) {
                int compareTo = ((Comparable) subscriptionsList).compareTo(subscriptionsList2);
                if (compareTo != 0) {
                    return compareTo;
                }
            } else if (!subscriptionsList.equals(subscriptionsList2)) {
                int hashCode = subscriptionsList.hashCode();
                int hashCode2 = subscriptionsList2.hashCode();
                if (hashCode < hashCode2) {
                    return -1;
                }
                if (hashCode > hashCode2) {
                    return 1;
                }
            }
        }
        return 0;
    }
}
