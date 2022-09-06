package com.amazon.clouddrive.extended.model;

import com.amazon.clouddrive.model.CloudDriveResponse;
import com.amazon.clouddrive.model.ObjectComparator;
import java.util.List;
/* loaded from: classes11.dex */
public class GetAggregationsResponse implements CloudDriveResponse {
    private List<Aggregation> mAggregation;
    private String mCategory;

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof GetAggregationsResponse) && compareTo((CloudDriveResponse) ((GetAggregationsResponse) obj)) == 0;
    }

    public List<Aggregation> getAggregation() {
        return this.mAggregation;
    }

    public String getCategory() {
        return this.mCategory;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = (getCategory() == null ? 0 : getCategory().hashCode()) + 1;
        if (getAggregation() != null) {
            i = getAggregation().hashCode();
        }
        return ((hashCode + i) * 31) + super.hashCode();
    }

    public void setAggregation(List<Aggregation> list) {
        this.mAggregation = list;
    }

    public void setCategory(String str) {
        this.mCategory = str;
    }

    @Override // java.lang.Comparable
    public int compareTo(CloudDriveResponse cloudDriveResponse) {
        if (cloudDriveResponse == null) {
            return -1;
        }
        if (cloudDriveResponse == this) {
            return 0;
        }
        GetAggregationsResponse getAggregationsResponse = (GetAggregationsResponse) cloudDriveResponse;
        int compareCollections = ObjectComparator.compareCollections(getAggregation(), getAggregationsResponse.getAggregation());
        if (compareCollections != 0) {
            return compareCollections;
        }
        int compare = ObjectComparator.compare(getCategory(), getAggregationsResponse.getCategory());
        if (compare == 0) {
            return 0;
        }
        return compare;
    }
}
