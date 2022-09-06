package com.amazon.clouddrive.model;
/* loaded from: classes11.dex */
public class GetNodePropertyRequest implements CloudDriveRequest {
    private String id;
    private String key;
    private String owner;

    public GetNodePropertyRequest(String str, String str2, String str3) {
        this.id = str;
        this.owner = str2;
        this.key = str3;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof GetNodePropertyRequest) && compareTo((CloudDriveRequest) ((GetNodePropertyRequest) obj)) == 0;
    }

    public String getId() {
        return this.id;
    }

    public String getKey() {
        return this.key;
    }

    public String getOwner() {
        return this.owner;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = (getKey() == null ? 0 : getKey().hashCode()) + 1 + (getId() == null ? 0 : getId().hashCode());
        if (getOwner() != null) {
            i = getOwner().hashCode();
        }
        return hashCode + i;
    }

    public void setId(String str) {
        this.id = str;
    }

    public void setKey(String str) {
        this.key = str;
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
        if (!(cloudDriveRequest instanceof GetNodePropertyRequest)) {
            return 1;
        }
        GetNodePropertyRequest getNodePropertyRequest = (GetNodePropertyRequest) cloudDriveRequest;
        String key = getKey();
        String key2 = getNodePropertyRequest.getKey();
        if (key != key2) {
            if (key == null) {
                return -1;
            }
            if (key2 == null) {
                return 1;
            }
            int compareTo = key.compareTo(key2);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        String id = getId();
        String id2 = getNodePropertyRequest.getId();
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
        String owner = getOwner();
        String owner2 = getNodePropertyRequest.getOwner();
        if (owner != owner2) {
            if (owner == null) {
                return -1;
            }
            if (owner2 == null) {
                return 1;
            }
            int compareTo3 = owner.compareTo(owner2);
            if (compareTo3 != 0) {
                return compareTo3;
            }
        }
        return 0;
    }
}
