package com.amazon.clouddrive.extended.model;
/* loaded from: classes11.dex */
public class NotificationTopicSubscription implements Comparable<NotificationTopicSubscription> {
    private boolean isSubscribed;
    private NotificationTopic notificationTopic;

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof NotificationTopicSubscription) && compareTo((NotificationTopicSubscription) obj) == 0;
    }

    public NotificationTopic getNotificationTopic() {
        return this.notificationTopic;
    }

    public int hashCode() {
        return ((getNotificationTopic() != null ? getNotificationTopic().hashCode() : 0) * 31) + (isSubscribed() ? 1 : 0);
    }

    public boolean isSubscribed() {
        return this.isSubscribed;
    }

    public void setNotificationTopic(NotificationTopic notificationTopic) {
        this.notificationTopic = notificationTopic;
    }

    public void setSubscribed(boolean z) {
        this.isSubscribed = z;
    }

    @Override // java.lang.Comparable
    public int compareTo(NotificationTopicSubscription notificationTopicSubscription) {
        if (notificationTopicSubscription == null) {
            return -1;
        }
        if (notificationTopicSubscription == this) {
            return 0;
        }
        NotificationTopic notificationTopic = getNotificationTopic();
        NotificationTopic notificationTopic2 = notificationTopicSubscription.getNotificationTopic();
        if (notificationTopic != notificationTopic2) {
            if (notificationTopic == null) {
                return -1;
            }
            if (notificationTopic2 == null) {
                return 1;
            }
            int compareTo = notificationTopic.compareTo(notificationTopic2);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        Boolean valueOf = Boolean.valueOf(isSubscribed());
        Boolean valueOf2 = Boolean.valueOf(notificationTopicSubscription.isSubscribed());
        if (valueOf != valueOf2) {
            if (valueOf == null) {
                return -1;
            }
            if (valueOf2 == null) {
                return 1;
            }
            int compareTo2 = valueOf.compareTo(valueOf2);
            if (compareTo2 != 0) {
                return compareTo2;
            }
        }
        return 0;
    }
}
