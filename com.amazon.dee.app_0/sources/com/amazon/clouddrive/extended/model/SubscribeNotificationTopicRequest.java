package com.amazon.clouddrive.extended.model;

import com.amazon.clouddrive.model.CloudDriveRequest;
/* loaded from: classes11.dex */
public class SubscribeNotificationTopicRequest implements CloudDriveRequest {
    private String notificationTopicId;
    private String sourceId;

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof SubscribeNotificationTopicRequest) && compareTo((CloudDriveRequest) ((SubscribeNotificationTopicRequest) obj)) == 0;
    }

    public String getNotificationTopicId() {
        return this.notificationTopicId;
    }

    public String getSourceId() {
        return this.sourceId;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = (getNotificationTopicId() != null ? getNotificationTopicId().hashCode() : 0) * 31;
        if (getSourceId() != null) {
            i = getSourceId().hashCode();
        }
        return hashCode + i;
    }

    public void setNotificationTopicId(String str) {
        this.notificationTopicId = str;
    }

    public void setSourceId(String str) {
        this.sourceId = str;
    }

    @Override // java.lang.Comparable
    public int compareTo(CloudDriveRequest cloudDriveRequest) {
        if (cloudDriveRequest == null) {
            return -1;
        }
        if (cloudDriveRequest == this) {
            return 0;
        }
        if (!(cloudDriveRequest instanceof SubscribeNotificationTopicRequest)) {
            return 1;
        }
        SubscribeNotificationTopicRequest subscribeNotificationTopicRequest = (SubscribeNotificationTopicRequest) cloudDriveRequest;
        String notificationTopicId = getNotificationTopicId();
        String notificationTopicId2 = subscribeNotificationTopicRequest.getNotificationTopicId();
        if (notificationTopicId != notificationTopicId2) {
            if (notificationTopicId == null) {
                return -1;
            }
            if (notificationTopicId2 == null) {
                return 1;
            }
            int compareTo = notificationTopicId.compareTo(notificationTopicId2);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        String sourceId = getSourceId();
        String sourceId2 = subscribeNotificationTopicRequest.getSourceId();
        if (sourceId != sourceId2) {
            if (sourceId == null) {
                return -1;
            }
            if (sourceId2 == null) {
                return 1;
            }
            int compareTo2 = sourceId.compareTo(sourceId2);
            if (compareTo2 != 0) {
                return compareTo2;
            }
        }
        return 0;
    }
}
