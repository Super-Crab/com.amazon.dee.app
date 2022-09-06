package com.amazon.clouddrive.extended.model;

import com.amazon.clouddrive.model.CloudDriveRequest;
import com.amazon.clouddrive.model.ObjectComparator;
import java.util.List;
/* loaded from: classes11.dex */
public class GetAggregationsRequest implements CloudDriveRequest {
    private String mAggregationContext;
    private String mCategory;
    private String mDedupeContext;
    private List<String> mFamilyMembers;
    private String mGroupBy;
    private String mLanguage;
    private Long mLimit;

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof GetAggregationsRequest) && compareTo((CloudDriveRequest) ((GetAggregationsRequest) obj)) == 0;
    }

    public String getAggregationContext() {
        return this.mAggregationContext;
    }

    public String getCategory() {
        return this.mCategory;
    }

    public String getDedupeContext() {
        return this.mDedupeContext;
    }

    public List<String> getFamilyMembers() {
        return this.mFamilyMembers;
    }

    public String getGroupBy() {
        return this.mGroupBy;
    }

    public String getLanguage() {
        return this.mLanguage;
    }

    public Long getLimit() {
        return this.mLimit;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = (getCategory() == null ? 0 : getCategory().hashCode()) + 1 + (getLimit() == null ? 0 : getLimit().hashCode()) + (getFamilyMembers() == null ? 0 : getFamilyMembers().hashCode()) + (getDedupeContext() == null ? 0 : getDedupeContext().hashCode()) + (getLanguage() == null ? 0 : getLanguage().hashCode()) + (getGroupBy() == null ? 0 : getGroupBy().hashCode());
        if (getAggregationContext() != null) {
            i = getAggregationContext().hashCode();
        }
        return ((hashCode + i) * 31) + super.hashCode();
    }

    public void setAggregationContext(String str) {
        this.mAggregationContext = str;
    }

    public void setCategory(String str) {
        this.mCategory = str;
    }

    public void setDedupeContext(String str) {
        this.mDedupeContext = str;
    }

    public void setFamilyMembers(List<String> list) {
        this.mFamilyMembers = list;
    }

    public void setGroupBy(String str) {
        this.mGroupBy = str;
    }

    public void setLanguage(String str) {
        this.mLanguage = str;
    }

    public void setLimit(Long l) {
        this.mLimit = l;
    }

    @Override // java.lang.Comparable
    public int compareTo(CloudDriveRequest cloudDriveRequest) {
        if (cloudDriveRequest == null) {
            return -1;
        }
        if (cloudDriveRequest == this) {
            return 0;
        }
        GetAggregationsRequest getAggregationsRequest = (GetAggregationsRequest) cloudDriveRequest;
        int compareCollections = ObjectComparator.compareCollections(getFamilyMembers(), getAggregationsRequest.getFamilyMembers());
        if (compareCollections != 0) {
            return compareCollections;
        }
        int compare = ObjectComparator.compare(getCategory(), getAggregationsRequest.getCategory());
        if (compare != 0) {
            return compare;
        }
        int compare2 = ObjectComparator.compare(getLimit(), getAggregationsRequest.getLimit());
        if (compare2 != 0) {
            return compare2;
        }
        int compare3 = ObjectComparator.compare(getDedupeContext(), getAggregationsRequest.getDedupeContext());
        if (compare3 != 0) {
            return compare3;
        }
        int compare4 = ObjectComparator.compare(getGroupBy(), getAggregationsRequest.getGroupBy());
        if (compare4 != 0) {
            return compare4;
        }
        int compare5 = ObjectComparator.compare(getAggregationContext(), getAggregationsRequest.getAggregationContext());
        if (compare5 != 0) {
            return compare5;
        }
        int compare6 = ObjectComparator.compare(getLanguage(), getAggregationsRequest.getLanguage());
        if (compare6 == 0) {
            return 0;
        }
        return compare6;
    }
}
