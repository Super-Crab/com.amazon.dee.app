package com.amazon.clouddrive.model;

import java.util.List;
/* loaded from: classes11.dex */
public class CreateBatchLinkRequest implements CloudDriveRequest {
    private List<String> nodeIds;

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof CreateBatchLinkRequest) && compareTo((CloudDriveRequest) obj) == 0;
    }

    public List<String> getNodeIds() {
        return this.nodeIds;
    }

    public int hashCode() {
        List<String> list = this.nodeIds;
        return ((list != null ? list.hashCode() : 0) * 31) + super.hashCode();
    }

    public void setNodeIds(List<String> list) {
        this.nodeIds = list;
    }

    public CreateBatchLinkRequest withNodeIds(List<String> list) {
        setNodeIds(list);
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
        if (!(cloudDriveRequest instanceof CreateBatchLinkRequest)) {
            return 1;
        }
        List<String> nodeIds = getNodeIds();
        List<String> nodeIds2 = ((CreateBatchLinkRequest) cloudDriveRequest).getNodeIds();
        if (nodeIds != nodeIds2) {
            if (nodeIds == null) {
                return -1;
            }
            if (nodeIds2 == null) {
                return 1;
            }
            if (nodeIds instanceof Comparable) {
                int compareTo = ((Comparable) nodeIds).compareTo(nodeIds2);
                if (compareTo != 0) {
                    return compareTo;
                }
            } else if (!nodeIds.equals(nodeIds2)) {
                int hashCode = nodeIds.hashCode();
                int hashCode2 = nodeIds2.hashCode();
                if (hashCode < hashCode2) {
                    return -1;
                }
                if (hashCode > hashCode2) {
                    return 1;
                }
            }
        }
        return 0;
    }
}
