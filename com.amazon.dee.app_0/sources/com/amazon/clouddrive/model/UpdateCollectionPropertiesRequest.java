package com.amazon.clouddrive.model;

import java.util.List;
/* loaded from: classes11.dex */
public class UpdateCollectionPropertiesRequest implements CloudDriveRequest {
    private String id;
    private List<CollectionPropertiesUpdateObject> operations;

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof UpdateCollectionPropertiesRequest) && compareTo((CloudDriveRequest) ((UpdateCollectionPropertiesRequest) obj)) == 0;
    }

    public String getId() {
        return this.id;
    }

    public List<CollectionPropertiesUpdateObject> getOperations() {
        return this.operations;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = (getId() == null ? 0 : getId().hashCode()) + 1;
        if (getOperations() != null) {
            i = getOperations().hashCode();
        }
        return ((hashCode + i) * 31) + super.hashCode();
    }

    public void setId(String str) {
        this.id = str;
    }

    public void setOperations(List<CollectionPropertiesUpdateObject> list) {
        this.operations = list;
    }

    @Override // java.lang.Comparable
    public int compareTo(CloudDriveRequest cloudDriveRequest) {
        if (cloudDriveRequest == null) {
            return -1;
        }
        if (cloudDriveRequest == this) {
            return 0;
        }
        if (!(cloudDriveRequest instanceof UpdateCollectionPropertiesRequest)) {
            return 1;
        }
        UpdateCollectionPropertiesRequest updateCollectionPropertiesRequest = (UpdateCollectionPropertiesRequest) cloudDriveRequest;
        int compare = ObjectComparator.compare(getId(), updateCollectionPropertiesRequest.getId());
        if (compare != 0) {
            return compare;
        }
        int compare2 = ObjectComparator.compare(getOperations(), updateCollectionPropertiesRequest.getOperations());
        if (compare2 == 0) {
            return 0;
        }
        return compare2;
    }
}
