package com.amazon.clouddrive.model;
/* loaded from: classes11.dex */
public class MoveChildRequest implements CloudDriveRequest {
    private String childId;
    private String fromParentId;
    private String toParentId;

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof MoveChildRequest) && compareTo((CloudDriveRequest) ((MoveChildRequest) obj)) == 0;
    }

    public String getChildId() {
        return this.childId;
    }

    public String getFromParentId() {
        return this.fromParentId;
    }

    public String getToParentId() {
        return this.toParentId;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = (getFromParentId() == null ? 0 : getFromParentId().hashCode()) + 1 + (getToParentId() == null ? 0 : getToParentId().hashCode());
        if (getChildId() != null) {
            i = getChildId().hashCode();
        }
        return hashCode + i;
    }

    public void setChildId(String str) {
        this.childId = str;
    }

    public void setFromParentId(String str) {
        this.fromParentId = str;
    }

    public void setToParentId(String str) {
        this.toParentId = str;
    }

    @Override // java.lang.Comparable
    public int compareTo(CloudDriveRequest cloudDriveRequest) {
        if (cloudDriveRequest == null) {
            return -1;
        }
        if (cloudDriveRequest == this) {
            return 0;
        }
        if (!(cloudDriveRequest instanceof MoveChildRequest)) {
            return 1;
        }
        MoveChildRequest moveChildRequest = (MoveChildRequest) cloudDriveRequest;
        String childId = getChildId();
        String childId2 = moveChildRequest.getChildId();
        if (childId != childId2) {
            if (childId == null) {
                return -1;
            }
            if (childId2 == null) {
                return 1;
            }
            int compareTo = childId.compareTo(childId2);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        String fromParentId = getFromParentId();
        String fromParentId2 = moveChildRequest.getFromParentId();
        if (fromParentId != fromParentId2) {
            if (fromParentId == null) {
                return -1;
            }
            if (fromParentId2 == null) {
                return 1;
            }
            int compareTo2 = fromParentId.compareTo(fromParentId2);
            if (compareTo2 != 0) {
                return compareTo2;
            }
        }
        String toParentId = getToParentId();
        String toParentId2 = moveChildRequest.getToParentId();
        if (toParentId != toParentId2) {
            if (toParentId == null) {
                return -1;
            }
            if (toParentId2 == null) {
                return 1;
            }
            int compareTo3 = toParentId.compareTo(toParentId2);
            if (compareTo3 != 0) {
                return compareTo3;
            }
        }
        return 0;
    }
}
