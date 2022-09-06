package com.amazon.clouddrive.extended.model;

import com.amazon.clouddrive.model.CloudDriveRequest;
import com.amazon.clouddrive.model.ObjectComparator;
import java.util.List;
/* loaded from: classes11.dex */
public class BulkGetFaceClusterRequest implements CloudDriveRequest {
    private List<String> mClusterIdList;

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof BulkGetFaceClusterRequest) && compareTo((CloudDriveRequest) ((BulkGetFaceClusterRequest) obj)) == 0;
    }

    public List<String> getClusterIdList() {
        return this.mClusterIdList;
    }

    public int hashCode() {
        return (((getClusterIdList() == null ? 0 : getClusterIdList().hashCode()) + 1) * 31) + super.hashCode();
    }

    public void setClusterIdList(List<String> list) {
        this.mClusterIdList = list;
    }

    public BulkGetFaceClusterRequest withClusterIdList(List<String> list) {
        setClusterIdList(list);
        return this;
    }

    @Override // java.lang.Comparable
    public int compareTo(CloudDriveRequest cloudDriveRequest) {
        int compareCollections;
        if (cloudDriveRequest == null) {
            return -1;
        }
        if (cloudDriveRequest != this && (compareCollections = ObjectComparator.compareCollections(getClusterIdList(), ((BulkGetFaceClusterRequest) cloudDriveRequest).getClusterIdList())) != 0) {
            return compareCollections;
        }
        return 0;
    }
}
