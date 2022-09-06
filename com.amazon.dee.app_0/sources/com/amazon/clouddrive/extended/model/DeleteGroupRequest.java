package com.amazon.clouddrive.extended.model;

import com.amazon.clouddrive.model.CloudDriveRequest;
import com.amazon.clouddrive.model.ObjectComparator;
/* loaded from: classes11.dex */
public class DeleteGroupRequest implements CloudDriveRequest {
    private String groupId;

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof DeleteGroupRequest) && compareTo((CloudDriveRequest) ((DeleteGroupRequest) obj)) == 0;
    }

    public String getGroupId() {
        return this.groupId;
    }

    public int hashCode() {
        return (((getGroupId() == null ? 0 : getGroupId().hashCode()) + 1) * 31) + super.hashCode();
    }

    public void setGroupId(String str) {
        this.groupId = str;
    }

    public DeleteGroupRequest withGroupId(String str) {
        setGroupId(str);
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
        if (!(cloudDriveRequest instanceof DeleteGroupRequest)) {
            return 1;
        }
        int compare = ObjectComparator.compare(getGroupId(), ((DeleteGroupRequest) cloudDriveRequest).getGroupId());
        if (compare == 0) {
            return 0;
        }
        return compare;
    }
}
