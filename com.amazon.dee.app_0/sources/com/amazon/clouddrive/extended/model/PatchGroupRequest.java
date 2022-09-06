package com.amazon.clouddrive.extended.model;

import com.amazon.clouddrive.model.CloudDriveRequest;
import com.amazon.clouddrive.model.ObjectComparator;
/* loaded from: classes11.dex */
public class PatchGroupRequest implements CloudDriveRequest {
    private GroupCoverPhoto coverNode;
    private String groupId;
    private String name;

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof PatchGroupRequest) && compareTo((CloudDriveRequest) ((PatchGroupRequest) obj)) == 0;
    }

    public GroupCoverPhoto getCoverNode() {
        return this.coverNode;
    }

    public String getGroupId() {
        return this.groupId;
    }

    public String getName() {
        return this.name;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = (getName() == null ? 0 : getName().hashCode()) + 1 + (getGroupId() == null ? 0 : getGroupId().hashCode());
        if (getCoverNode() != null) {
            i = getCoverNode().hashCode();
        }
        return ((hashCode + i) * 31) + super.hashCode();
    }

    public void setCoverNode(GroupCoverPhoto groupCoverPhoto) {
        this.coverNode = groupCoverPhoto;
    }

    public void setGroupId(String str) {
        this.groupId = str;
    }

    public void setName(String str) {
        this.name = str;
    }

    public PatchGroupRequest withCoverNode(GroupCoverPhoto groupCoverPhoto) {
        setCoverNode(groupCoverPhoto);
        return this;
    }

    public PatchGroupRequest withGroupId(String str) {
        setGroupId(str);
        return this;
    }

    public PatchGroupRequest withName(String str) {
        setName(str);
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
        if (!(cloudDriveRequest instanceof PatchGroupRequest)) {
            return 1;
        }
        PatchGroupRequest patchGroupRequest = (PatchGroupRequest) cloudDriveRequest;
        int compare = ObjectComparator.compare(getName(), patchGroupRequest.getName());
        if (compare != 0) {
            return compare;
        }
        int compare2 = ObjectComparator.compare(getGroupId(), patchGroupRequest.getGroupId());
        if (compare2 != 0) {
            return compare2;
        }
        int compare3 = ObjectComparator.compare(getCoverNode(), patchGroupRequest.getCoverNode());
        if (compare3 == 0) {
            return 0;
        }
        return compare3;
    }
}
