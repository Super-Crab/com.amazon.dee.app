package com.amazon.clouddrive.model;
/* loaded from: classes11.dex */
public class ListNodePropertiesRequest implements CloudDriveRequest {
    private String id;
    private String owner;

    public ListNodePropertiesRequest(String str, String str2) {
        this.id = str;
        this.owner = str2;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof ListNodePropertiesRequest) && compareTo((CloudDriveRequest) ((ListNodePropertiesRequest) obj)) == 0;
    }

    public String getId() {
        return this.id;
    }

    public String getOwner() {
        return this.owner;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = (getOwner() == null ? 0 : getOwner().hashCode()) + 1;
        if (getId() != null) {
            i = getId().hashCode();
        }
        return hashCode + i;
    }

    public void setId(String str) {
        this.id = str;
    }

    public void setOwner(String str) {
        this.owner = str;
    }

    @Override // java.lang.Comparable
    public int compareTo(CloudDriveRequest cloudDriveRequest) {
        if (cloudDriveRequest == null) {
            return -1;
        }
        if (cloudDriveRequest == this) {
            return 0;
        }
        if (!(cloudDriveRequest instanceof ListNodePropertiesRequest)) {
            return 1;
        }
        ListNodePropertiesRequest listNodePropertiesRequest = (ListNodePropertiesRequest) cloudDriveRequest;
        String owner = getOwner();
        String owner2 = listNodePropertiesRequest.getOwner();
        if (owner != owner2) {
            if (owner == null) {
                return -1;
            }
            if (owner2 == null) {
                return 1;
            }
            int compareTo = owner.compareTo(owner2);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        String id = getId();
        String id2 = listNodePropertiesRequest.getId();
        if (id != id2) {
            if (id == null) {
                return -1;
            }
            if (id2 == null) {
                return 1;
            }
            int compareTo2 = id.compareTo(id2);
            if (compareTo2 != 0) {
                return compareTo2;
            }
        }
        return 0;
    }
}
