package com.amazon.clouddrive.model;
/* loaded from: classes11.dex */
public class RemoveChildFromParentRequest implements CloudDriveRequest {
    private String childId;
    private String parentId;

    public RemoveChildFromParentRequest(String str, String str2) {
        this.parentId = str;
        this.childId = str2;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof RemoveChildFromParentRequest) && compareTo((CloudDriveRequest) ((RemoveChildFromParentRequest) obj)) == 0;
    }

    public String getChildId() {
        return this.childId;
    }

    public String getParentId() {
        return this.parentId;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = (getParentId() == null ? 0 : getParentId().hashCode()) + 1;
        if (getChildId() != null) {
            i = getChildId().hashCode();
        }
        return hashCode + i;
    }

    public void setChildId(String str) {
        this.childId = str;
    }

    public void setParentId(String str) {
        this.parentId = str;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.lang.Comparable
    public int compareTo(CloudDriveRequest cloudDriveRequest) {
        if (cloudDriveRequest == null) {
            return -1;
        }
        if (cloudDriveRequest == this) {
            return 0;
        }
        if (!(cloudDriveRequest instanceof RemoveChildFromParentRequest)) {
            return 1;
        }
        RemoveChildFromParentRequest removeChildFromParentRequest = (RemoveChildFromParentRequest) cloudDriveRequest;
        String parentId = getParentId();
        String parentId2 = removeChildFromParentRequest.getParentId();
        if (parentId != parentId2) {
            if (parentId == null) {
                return -1;
            }
            if (parentId2 == null) {
                return 1;
            }
            int compareTo = parentId.compareTo(parentId2);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        String childId = getChildId();
        String childId2 = removeChildFromParentRequest.getChildId();
        if (childId != childId2) {
            if (childId == null) {
                return -1;
            }
            if (childId2 == null) {
                return 1;
            }
            int compareTo2 = childId.compareTo(childId2);
            if (compareTo2 != 0) {
                return compareTo2;
            }
        }
        return 0;
    }
}
