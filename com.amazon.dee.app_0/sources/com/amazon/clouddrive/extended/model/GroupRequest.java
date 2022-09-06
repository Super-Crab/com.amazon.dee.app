package com.amazon.clouddrive.extended.model;

import com.amazon.clouddrive.model.CloudDriveRequest;
import com.amazon.clouddrive.model.ObjectComparator;
/* loaded from: classes11.dex */
public class GroupRequest implements CloudDriveRequest {
    private String groupId;
    private String lang;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && getClass() == obj.getClass() && compareTo((CloudDriveRequest) obj) == 0;
    }

    public String getGroupId() {
        return this.groupId;
    }

    public String getLang() {
        return this.lang;
    }

    public int hashCode() {
        return (super.hashCode() * 31) + (getGroupId() != null ? getGroupId().hashCode() : 0);
    }

    public void setGroupId(String str) {
        this.groupId = str;
    }

    public void setLang(String str) {
        this.lang = str;
    }

    @Override // java.lang.Comparable
    public int compareTo(CloudDriveRequest cloudDriveRequest) {
        if (cloudDriveRequest == this) {
            return 0;
        }
        if (cloudDriveRequest == null) {
            return -1;
        }
        if (!(cloudDriveRequest instanceof GroupRequest)) {
            return 1;
        }
        GroupRequest groupRequest = (GroupRequest) cloudDriveRequest;
        int compare = ObjectComparator.compare(getGroupId(), groupRequest.getGroupId());
        if (compare != 0) {
            return compare;
        }
        int compare2 = ObjectComparator.compare(getLang(), groupRequest.getLang());
        if (compare2 == 0) {
            return 0;
        }
        return compare2;
    }
}
