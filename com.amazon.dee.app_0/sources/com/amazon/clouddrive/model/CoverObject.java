package com.amazon.clouddrive.model;
/* loaded from: classes11.dex */
public class CoverObject implements Comparable<CoverObject> {
    private String id;
    private Boolean isDefault;
    private String ownerId;
    private String tempLink;

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof CoverObject) && compareTo((CoverObject) obj) == 0;
    }

    public String getId() {
        return this.id;
    }

    public String getOwnerId() {
        return this.ownerId;
    }

    public String getTempLink() {
        return this.tempLink;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = (getOwnerId() == null ? 0 : getOwnerId().hashCode()) + 1 + (getId() == null ? 0 : getId().hashCode()) + (getTempLink() == null ? 0 : getTempLink().hashCode());
        if (isDefault() != null) {
            i = isDefault().hashCode();
        }
        return hashCode + i;
    }

    public Boolean isDefault() {
        return this.isDefault;
    }

    public void setDefault(Boolean bool) {
        this.isDefault = bool;
    }

    public void setId(String str) {
        this.id = str;
    }

    public void setOwnerId(String str) {
        this.ownerId = str;
    }

    public void setTempLink(String str) {
        this.tempLink = str;
    }

    @Override // java.lang.Comparable
    public int compareTo(CoverObject coverObject) {
        if (coverObject == null) {
            return -1;
        }
        if (coverObject == this) {
            return 0;
        }
        int compare = ObjectComparator.compare(getId(), coverObject.getId());
        if (compare != 0) {
            return compare;
        }
        int compare2 = ObjectComparator.compare(getTempLink(), coverObject.getTempLink());
        if (compare2 != 0) {
            return compare2;
        }
        int compare3 = ObjectComparator.compare(isDefault(), coverObject.isDefault());
        if (compare3 != 0) {
            return compare3;
        }
        int compare4 = ObjectComparator.compare(getOwnerId(), coverObject.getOwnerId());
        if (compare4 == 0) {
            return 0;
        }
        return compare4;
    }
}
