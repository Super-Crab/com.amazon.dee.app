package com.amazon.clouddrive.extended.model;

import com.amazon.clouddrive.model.CloudDriveResponse;
/* loaded from: classes11.dex */
public class SubscribeNotificationTopicResponse implements CloudDriveResponse {
    private NotificationTopicSubscription notificationTopicSubscription;

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof SubscribeNotificationTopicResponse) && compareTo((CloudDriveResponse) ((SubscribeNotificationTopicResponse) obj)) == 0;
    }

    public NotificationTopicSubscription getNotificationTopicSubscription() {
        return this.notificationTopicSubscription;
    }

    public int hashCode() {
        if (getNotificationTopicSubscription() != null) {
            return getNotificationTopicSubscription().hashCode();
        }
        return 0;
    }

    public void setNotificationTopicSubscription(NotificationTopicSubscription notificationTopicSubscription) {
        this.notificationTopicSubscription = notificationTopicSubscription;
    }

    @Override // java.lang.Comparable
    public int compareTo(CloudDriveResponse cloudDriveResponse) {
        if (cloudDriveResponse == null) {
            return -1;
        }
        if (cloudDriveResponse == this) {
            return 0;
        }
        if (!(cloudDriveResponse instanceof SubscribeNotificationTopicResponse)) {
            return 1;
        }
        NotificationTopicSubscription notificationTopicSubscription = getNotificationTopicSubscription();
        NotificationTopicSubscription notificationTopicSubscription2 = ((SubscribeNotificationTopicResponse) cloudDriveResponse).getNotificationTopicSubscription();
        if (notificationTopicSubscription != notificationTopicSubscription2) {
            if (notificationTopicSubscription == null) {
                return -1;
            }
            if (notificationTopicSubscription2 == null) {
                return 1;
            }
            int compareTo = notificationTopicSubscription.compareTo(notificationTopicSubscription2);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        return 0;
    }
}
