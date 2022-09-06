package com.amazon.clouddrive.extended.model;

import com.amazon.clouddrive.model.CloudDriveRequest;
import com.amazon.clouddrive.model.ObjectComparator;
/* loaded from: classes11.dex */
public class ViewGroupRequest implements CloudDriveRequest {
    private String groupId;
    private String memberId;

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof ViewGroupRequest) && compareTo((CloudDriveRequest) ((ViewGroupRequest) obj)) == 0;
    }

    public String getGroupId() {
        return this.groupId;
    }

    public String getMemberId() {
        return this.memberId;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = (getGroupId() == null ? 0 : getGroupId().hashCode()) + 1;
        if (getMemberId() != null) {
            i = getMemberId().hashCode();
        }
        return ((hashCode + i) * 31) + super.hashCode();
    }

    public void setGroupId(String str) {
        this.groupId = str;
    }

    public void setMemberId(String str) {
        this.memberId = str;
    }

    public ViewGroupRequest withGroupId(String str) {
        setGroupId(str);
        return this;
    }

    public ViewGroupRequest withMemberId(String str) {
        setMemberId(str);
        return this;
    }

    @Override // java.lang.Comparable
    public int compareTo(CloudDriveRequest cloudDriveRequest) {
        if (cloudDriveRequest == null) {
            return -1;
        }
        if (cloudDriveRequest == this) {
            return 0;
        }
        if (!(cloudDriveRequest instanceof ViewGroupRequest)) {
            return 1;
        }
        ViewGroupRequest viewGroupRequest = (ViewGroupRequest) cloudDriveRequest;
        int compare = ObjectComparator.compare(getGroupId(), viewGroupRequest.getGroupId());
        if (compare != 0) {
            return compare;
        }
        int compare2 = ObjectComparator.compare(getMemberId(), viewGroupRequest.getMemberId());
        if (compare2 == 0) {
            return 0;
        }
        return compare2;
    }
}
