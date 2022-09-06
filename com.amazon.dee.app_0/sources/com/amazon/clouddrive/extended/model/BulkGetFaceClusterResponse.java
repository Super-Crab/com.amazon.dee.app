package com.amazon.clouddrive.extended.model;

import com.amazon.clouddrive.model.CloudDriveResponse;
import com.amazon.clouddrive.model.ObjectComparator;
import java.util.List;
import java.util.Map;
/* loaded from: classes11.dex */
public class BulkGetFaceClusterResponse implements CloudDriveResponse {
    private Map<String, Integer> mErrorMap;
    private List<FacePair> mFaceCluster;

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof BulkGetFaceClusterResponse) && compareTo((CloudDriveResponse) ((BulkGetFaceClusterResponse) obj)) == 0;
    }

    public Map<String, Integer> getErrorMap() {
        return this.mErrorMap;
    }

    public List<FacePair> getFaceCluster() {
        return this.mFaceCluster;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = (getFaceCluster() == null ? 0 : getFaceCluster().hashCode()) + 1;
        if (getErrorMap() != null) {
            i = getErrorMap().hashCode();
        }
        return ((hashCode + i) * 31) + super.hashCode();
    }

    public void setErrorMap(Map<String, Integer> map) {
        this.mErrorMap = map;
    }

    public void setFaceCluster(List<FacePair> list) {
        this.mFaceCluster = list;
    }

    @Override // java.lang.Comparable
    public int compareTo(CloudDriveResponse cloudDriveResponse) {
        if (cloudDriveResponse == null) {
            return -1;
        }
        if (cloudDriveResponse == this) {
            return 0;
        }
        BulkGetFaceClusterResponse bulkGetFaceClusterResponse = (BulkGetFaceClusterResponse) cloudDriveResponse;
        int compareCollections = ObjectComparator.compareCollections(getFaceCluster(), bulkGetFaceClusterResponse.getFaceCluster());
        if (compareCollections != 0) {
            return compareCollections;
        }
        int compare = ObjectComparator.compare(getErrorMap(), bulkGetFaceClusterResponse.getErrorMap());
        if (compare == 0) {
            return 0;
        }
        return compare;
    }
}
