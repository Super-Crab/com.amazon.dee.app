package com.amazon.clouddrive.extended.model;

import com.amazon.clouddrive.model.CloudDriveRequest;
import com.amazon.clouddrive.model.ObjectComparator;
/* loaded from: classes11.dex */
public class GetFacesForNodeRequest implements CloudDriveRequest {
    private String nodeId;

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof GetFacesForNodeRequest) && compareTo((CloudDriveRequest) ((GetFacesForNodeRequest) obj)) == 0;
    }

    public String getNodeId() {
        return this.nodeId;
    }

    public int hashCode() {
        return (((getNodeId() == null ? 0 : getNodeId().hashCode()) + 1) * 31) + super.hashCode();
    }

    public void setNodeId(String str) {
        this.nodeId = str;
    }

    public GetFacesForNodeRequest withNodeId(String str) {
        setNodeId(str);
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
        if (!(cloudDriveRequest instanceof GetFacesForNodeRequest)) {
            return 1;
        }
        int compare = ObjectComparator.compare(getNodeId(), ((GetFacesForNodeRequest) cloudDriveRequest).getNodeId());
        if (compare == 0) {
            return 0;
        }
        return compare;
    }
}
