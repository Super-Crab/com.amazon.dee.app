package com.amazon.clouddrive.model;

import java.util.List;
/* loaded from: classes11.dex */
public class ListNodesResponse extends PaginatedCloudDriveResponse implements NodeListingResponse<Node> {
    private List<Node> data;

    @Override // com.amazon.clouddrive.model.PaginatedCloudDriveResponse
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof ListNodesResponse) && compareTo((CloudDriveResponse) ((ListNodesResponse) obj)) == 0;
    }

    @Override // com.amazon.clouddrive.model.NodeListingResponse
    public List<Node> getData() {
        return this.data;
    }

    @Override // com.amazon.clouddrive.model.PaginatedCloudDriveResponse
    public int hashCode() {
        return (((getData() == null ? 0 : getData().hashCode()) + 1) * 31) + super.hashCode();
    }

    @Override // com.amazon.clouddrive.model.NodeListingResponse
    public void setData(List<Node> list) {
        this.data = list;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.clouddrive.model.PaginatedCloudDriveResponse, java.lang.Comparable
    public int compareTo(CloudDriveResponse cloudDriveResponse) {
        if (cloudDriveResponse == null) {
            return -1;
        }
        if (cloudDriveResponse == this) {
            return 0;
        }
        if (!(cloudDriveResponse instanceof ListNodesResponse)) {
            return 1;
        }
        List<Node> data = getData();
        List<Node> data2 = ((ListNodesResponse) cloudDriveResponse).getData();
        if (data != data2) {
            if (data == null) {
                return -1;
            }
            if (data2 == null) {
                return 1;
            }
            if (data instanceof Comparable) {
                int compareTo = ((Comparable) data).compareTo(data2);
                if (compareTo != 0) {
                    return compareTo;
                }
            } else if (!data.equals(data2)) {
                int hashCode = data.hashCode();
                int hashCode2 = data2.hashCode();
                if (hashCode < hashCode2) {
                    return -1;
                }
                if (hashCode > hashCode2) {
                    return 1;
                }
            }
        }
        return super.compareTo(cloudDriveResponse);
    }
}
