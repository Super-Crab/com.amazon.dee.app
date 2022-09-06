package com.amazon.clouddrive.extended.model;

import com.amazon.clouddrive.model.ObjectComparator;
/* loaded from: classes11.dex */
public class CustomerInfo implements Comparable<CustomerInfo> {
    private String avatarNodeId;
    private String avatarTempLink;
    private String customerId;
    private String modifiedDate;
    private String name;
    private String resourceId;
    private String role;

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof CustomerInfo) && compareTo((CustomerInfo) obj) == 0;
    }

    public String getAvatarNodeId() {
        return this.avatarNodeId;
    }

    public String getAvatarTempLink() {
        return this.avatarTempLink;
    }

    public String getCustomerId() {
        return this.customerId;
    }

    public String getModifiedDate() {
        return this.modifiedDate;
    }

    public String getName() {
        return this.name;
    }

    public String getResourceId() {
        return this.resourceId;
    }

    public String getRole() {
        return this.role;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = (getCustomerId() == null ? 0 : getCustomerId().hashCode()) + 1 + (getName() == null ? 0 : getName().hashCode()) + (getAvatarNodeId() == null ? 0 : getAvatarNodeId().hashCode()) + (getRole() == null ? 0 : getRole().hashCode()) + (getResourceId() == null ? 0 : getResourceId().hashCode()) + (getModifiedDate() == null ? 0 : getModifiedDate().hashCode());
        if (getAvatarTempLink() != null) {
            i = getAvatarTempLink().hashCode();
        }
        return ((hashCode + i) * 31) + super.hashCode();
    }

    public void setAvatarNodeId(String str) {
        this.avatarNodeId = str;
    }

    public void setAvatarTempLink(String str) {
        this.avatarTempLink = str;
    }

    public void setCustomerId(String str) {
        this.customerId = str;
    }

    public void setModifiedDate(String str) {
        this.modifiedDate = str;
    }

    public void setName(String str) {
        this.name = str;
    }

    public void setResourceId(String str) {
        this.resourceId = str;
    }

    public void setRole(String str) {
        this.role = str;
    }

    @Override // java.lang.Comparable
    public int compareTo(CustomerInfo customerInfo) {
        if (customerInfo == null) {
            return -1;
        }
        if (customerInfo == this) {
            return 0;
        }
        int compare = ObjectComparator.compare(getCustomerId(), customerInfo.getCustomerId());
        if (compare != 0) {
            return compare;
        }
        int compare2 = ObjectComparator.compare(getName(), customerInfo.getName());
        if (compare2 != 0) {
            return compare2;
        }
        int compare3 = ObjectComparator.compare(getAvatarNodeId(), customerInfo.getAvatarNodeId());
        if (compare3 != 0) {
            return compare3;
        }
        int compare4 = ObjectComparator.compare(getRole(), customerInfo.getRole());
        if (compare4 != 0) {
            return compare4;
        }
        int compare5 = ObjectComparator.compare(getResourceId(), customerInfo.getResourceId());
        if (compare5 != 0) {
            return compare5;
        }
        int compare6 = ObjectComparator.compare(getModifiedDate(), customerInfo.getModifiedDate());
        if (compare6 != 0) {
            return compare6;
        }
        int compare7 = ObjectComparator.compare(getAvatarTempLink(), customerInfo.getAvatarTempLink());
        if (compare7 == 0) {
            return 0;
        }
        return compare7;
    }
}
