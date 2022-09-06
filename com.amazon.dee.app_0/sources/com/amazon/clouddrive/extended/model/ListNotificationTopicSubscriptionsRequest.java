package com.amazon.clouddrive.extended.model;

import com.amazon.clouddrive.model.CloudDriveRequest;
/* loaded from: classes11.dex */
public class ListNotificationTopicSubscriptionsRequest implements CloudDriveRequest {
    private String locale;
    private String sourceId;

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof ListNotificationTopicSubscriptionsRequest) && compareTo((CloudDriveRequest) ((ListNotificationTopicSubscriptionsRequest) obj)) == 0;
    }

    public String getLocale() {
        return this.locale;
    }

    public String getSourceId() {
        return this.sourceId;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = (getLocale() != null ? getLocale().hashCode() : 0) * 31;
        if (getSourceId() != null) {
            i = getSourceId().hashCode();
        }
        return hashCode + i;
    }

    public void setLocale(String str) {
        this.locale = str;
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
        if (!(cloudDriveRequest instanceof ListNotificationTopicSubscriptionsRequest)) {
            return 1;
        }
        ListNotificationTopicSubscriptionsRequest listNotificationTopicSubscriptionsRequest = (ListNotificationTopicSubscriptionsRequest) cloudDriveRequest;
        String sourceId = getSourceId();
        String sourceId2 = listNotificationTopicSubscriptionsRequest.getSourceId();
        if (sourceId != sourceId2) {
            if (sourceId == null) {
                return -1;
            }
            if (sourceId2 == null) {
                return 1;
            }
            int compareTo = sourceId.compareTo(sourceId2);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        String locale = getLocale();
        String locale2 = listNotificationTopicSubscriptionsRequest.getLocale();
        if (locale != locale2) {
            if (locale == null) {
                return -1;
            }
            if (locale2 == null) {
                return 1;
            }
            int compareTo2 = locale.compareTo(locale2);
            if (compareTo2 != 0) {
                return compareTo2;
            }
        }
        return 0;
    }
}
