package com.amazon.clouddrive.model;
/* loaded from: classes11.dex */
public class CollectionPropertiesUpdateObject implements Comparable<CollectionPropertiesUpdateObject> {
    private CollectionProperties collectionProperties;
    private String op;
    private String path;

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof CollectionPropertiesUpdateObject) && compareTo((CollectionPropertiesUpdateObject) obj) == 0;
    }

    public CollectionProperties getCollectionProperties() {
        return this.collectionProperties;
    }

    public String getOp() {
        return this.op;
    }

    public String getPath() {
        return this.path;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = (getCollectionProperties() == null ? 0 : getCollectionProperties().hashCode()) + 1 + (getOp() == null ? 0 : getOp().hashCode());
        if (getPath() != null) {
            i = getPath().hashCode();
        }
        return hashCode + i;
    }

    public void setCollectionProperties(CollectionProperties collectionProperties) {
        this.collectionProperties = collectionProperties;
    }

    public void setOp(String str) {
        this.op = str;
    }

    public void setPath(String str) {
        this.path = str;
    }

    @Override // java.lang.Comparable
    public int compareTo(CollectionPropertiesUpdateObject collectionPropertiesUpdateObject) {
        if (collectionPropertiesUpdateObject == null) {
            return -1;
        }
        if (collectionPropertiesUpdateObject == this) {
            return 0;
        }
        int compare = ObjectComparator.compare(getCollectionProperties(), collectionPropertiesUpdateObject.getCollectionProperties());
        if (compare != 0) {
            return compare;
        }
        int compare2 = ObjectComparator.compare(getOp(), collectionPropertiesUpdateObject.getOp());
        if (compare2 != 0) {
            return compare2;
        }
        int compare3 = ObjectComparator.compare(getPath(), collectionPropertiesUpdateObject.getPath());
        if (compare3 == 0) {
            return 0;
        }
        return compare3;
    }
}
