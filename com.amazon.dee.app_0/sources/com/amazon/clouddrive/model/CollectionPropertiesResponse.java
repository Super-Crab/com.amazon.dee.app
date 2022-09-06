package com.amazon.clouddrive.model;
/* loaded from: classes11.dex */
public class CollectionPropertiesResponse implements CloudDriveResponse {
    private CollectionProperties collectionProperties;
    private String id;

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof CollectionPropertiesResponse) && compareTo((CloudDriveResponse) ((CollectionPropertiesResponse) obj)) == 0;
    }

    public CollectionProperties getCollectionProperties() {
        return this.collectionProperties;
    }

    public String getId() {
        return this.id;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = (getCollectionProperties() == null ? 0 : getCollectionProperties().hashCode()) + 1;
        if (getId() != null) {
            i = getId().hashCode();
        }
        return hashCode + i;
    }

    public void setCollectionProperties(CollectionProperties collectionProperties) {
        this.collectionProperties = collectionProperties;
    }

    public void setId(String str) {
        this.id = str;
    }

    @Override // java.lang.Comparable
    public int compareTo(CloudDriveResponse cloudDriveResponse) {
        if (cloudDriveResponse == null) {
            return -1;
        }
        if (cloudDriveResponse == this) {
            return 0;
        }
        if (!(cloudDriveResponse instanceof CollectionPropertiesResponse)) {
            return 1;
        }
        CollectionPropertiesResponse collectionPropertiesResponse = (CollectionPropertiesResponse) cloudDriveResponse;
        int compare = ObjectComparator.compare(getCollectionProperties(), collectionPropertiesResponse.getCollectionProperties());
        if (compare != 0) {
            return compare;
        }
        int compare2 = ObjectComparator.compare(getId(), collectionPropertiesResponse.getId());
        if (compare2 == 0) {
            return 0;
        }
        return compare2;
    }
}
