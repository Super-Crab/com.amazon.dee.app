package com.amazon.clouddrive.extended.model;

import com.amazon.clouddrive.model.CloudDriveRequest;
import com.amazon.clouddrive.model.ObjectComparator;
import java.util.List;
/* loaded from: classes11.dex */
public class CreateFaceClusterRequest implements CloudDriveRequest {
    private List<String> mClusterIdList;
    private String mContext;
    private String mNewName;

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof CreateFaceClusterRequest) && compareTo((CloudDriveRequest) ((CreateFaceClusterRequest) obj)) == 0;
    }

    public List<String> getClusterIdList() {
        return this.mClusterIdList;
    }

    public String getContext() {
        return this.mContext;
    }

    public String getNewName() {
        return this.mNewName;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = (getClusterIdList() == null ? 0 : getClusterIdList().hashCode()) + 1 + (getNewName() == null ? 0 : getNewName().hashCode());
        if (getContext() != null) {
            i = getContext().hashCode();
        }
        return ((hashCode + i) * 31) + super.hashCode();
    }

    public void setClusterIdList(List<String> list) {
        this.mClusterIdList = list;
    }

    public void setContext(String str) {
        this.mContext = str;
    }

    public void setNewName(String str) {
        this.mNewName = str;
    }

    public CreateFaceClusterRequest withClusterIdList(List<String> list) {
        setClusterIdList(list);
        return this;
    }

    public CreateFaceClusterRequest withContext(String str) {
        setContext(str);
        return this;
    }

    public CreateFaceClusterRequest withNewName(String str) {
        setNewName(str);
        return this;
    }

    @Override // java.lang.Comparable
    public int compareTo(CloudDriveRequest cloudDriveRequest) {
        if (cloudDriveRequest == null) {
            return -1;
        }
        if (cloudDriveRequest == this) {
            return 0;
        }
        if (!(cloudDriveRequest instanceof CreateFaceClusterRequest)) {
            return 1;
        }
        CreateFaceClusterRequest createFaceClusterRequest = (CreateFaceClusterRequest) cloudDriveRequest;
        int compareCollections = ObjectComparator.compareCollections(getClusterIdList(), createFaceClusterRequest.getClusterIdList());
        if (compareCollections != 0) {
            return compareCollections;
        }
        int compare = ObjectComparator.compare(getNewName(), createFaceClusterRequest.getNewName());
        if (compare != 0) {
            return compare;
        }
        int compare2 = ObjectComparator.compare(getContext(), createFaceClusterRequest.getContext());
        if (compare2 == 0) {
            return 0;
        }
        return compare2;
    }
}
