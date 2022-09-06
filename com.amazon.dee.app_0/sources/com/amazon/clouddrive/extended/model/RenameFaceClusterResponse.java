package com.amazon.clouddrive.extended.model;

import com.amazon.clouddrive.model.CloudDriveResponse;
import com.amazon.clouddrive.model.ObjectComparator;
/* loaded from: classes11.dex */
public class RenameFaceClusterResponse implements CloudDriveResponse {
    private String mClusterId;

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof RenameFaceClusterResponse) && compareTo((CloudDriveResponse) ((RenameFaceClusterResponse) obj)) == 0;
    }

    public String getClusterId() {
        return this.mClusterId;
    }

    public int hashCode() {
        return (((getClusterId() == null ? 0 : getClusterId().hashCode()) + 1) * 31) + super.hashCode();
    }

    public void setClusterId(String str) {
        this.mClusterId = str;
    }

    @Override // java.lang.Comparable
    public int compareTo(CloudDriveResponse cloudDriveResponse) {
        if (cloudDriveResponse == null) {
            return -1;
        }
        if (cloudDriveResponse == this) {
            return 0;
        }
        if (!(cloudDriveResponse instanceof RenameFaceClusterResponse)) {
            return 1;
        }
        int compare = ObjectComparator.compare(getClusterId(), ((RenameFaceClusterResponse) cloudDriveResponse).getClusterId());
        if (compare == 0) {
            return 0;
        }
        return compare;
    }
}
