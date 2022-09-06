package com.amazon.clouddrive.extended.model;

import com.amazon.clouddrive.model.CloudDriveRequest;
import com.amazon.clouddrive.model.ObjectComparator;
/* loaded from: classes11.dex */
public class RenameFaceClusterRequest implements CloudDriveRequest {
    private String mContext;
    private String mNewName;
    private String mSourceClusterId;

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof RenameFaceClusterRequest) && compareTo((CloudDriveRequest) ((RenameFaceClusterRequest) obj)) == 0;
    }

    public String getContext() {
        return this.mContext;
    }

    public String getNewName() {
        return this.mNewName;
    }

    public String getSourceClusterId() {
        return this.mSourceClusterId;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = (getSourceClusterId() == null ? 0 : getSourceClusterId().hashCode()) + 1 + (getNewName() == null ? 0 : getNewName().hashCode());
        if (getContext() != null) {
            i = getContext().hashCode();
        }
        return ((hashCode + i) * 31) + super.hashCode();
    }

    public void setContext(String str) {
        this.mContext = str;
    }

    public void setNewName(String str) {
        this.mNewName = str;
    }

    public void setSourceClusterId(String str) {
        this.mSourceClusterId = str;
    }

    public RenameFaceClusterRequest withContext(String str) {
        setContext(str);
        return this;
    }

    public RenameFaceClusterRequest withNewName(String str) {
        setNewName(str);
        return this;
    }

    public RenameFaceClusterRequest withSourceClusterId(String str) {
        setSourceClusterId(str);
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
        if (!(cloudDriveRequest instanceof RenameFaceClusterRequest)) {
            return 1;
        }
        RenameFaceClusterRequest renameFaceClusterRequest = (RenameFaceClusterRequest) cloudDriveRequest;
        int compare = ObjectComparator.compare(getSourceClusterId(), renameFaceClusterRequest.getSourceClusterId());
        if (compare != 0) {
            return compare;
        }
        int compare2 = ObjectComparator.compare(getNewName(), renameFaceClusterRequest.getNewName());
        if (compare2 != 0) {
            return compare2;
        }
        int compare3 = ObjectComparator.compare(getContext(), renameFaceClusterRequest.getContext());
        if (compare3 == 0) {
            return 0;
        }
        return compare3;
    }
}
