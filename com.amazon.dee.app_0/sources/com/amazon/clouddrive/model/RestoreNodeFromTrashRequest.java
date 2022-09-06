package com.amazon.clouddrive.model;
/* loaded from: classes11.dex */
public class RestoreNodeFromTrashRequest implements CloudDriveRequest {
    private String id;

    public RestoreNodeFromTrashRequest(String str) {
        this.id = str;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof RestoreNodeFromTrashRequest) && compareTo((CloudDriveRequest) ((RestoreNodeFromTrashRequest) obj)) == 0;
    }

    public String getId() {
        return this.id;
    }

    public int hashCode() {
        return (getId() == null ? 0 : getId().hashCode()) + 1;
    }

    public void setId(String str) {
        this.id = str;
    }

    @Override // java.lang.Comparable
    public int compareTo(CloudDriveRequest cloudDriveRequest) {
        if (cloudDriveRequest == null) {
            return -1;
        }
        if (cloudDriveRequest == this) {
            return 0;
        }
        if (!(cloudDriveRequest instanceof RestoreNodeFromTrashRequest)) {
            return 1;
        }
        String id = getId();
        String id2 = ((RestoreNodeFromTrashRequest) cloudDriveRequest).getId();
        if (id != id2) {
            if (id == null) {
                return -1;
            }
            if (id2 == null) {
                return 1;
            }
            int compareTo = id.compareTo(id2);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        return 0;
    }
}
