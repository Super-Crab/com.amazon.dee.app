package com.amazon.clouddrive.extended.model;

import com.amazon.clouddrive.model.CloudDriveResponse;
import com.amazon.clouddrive.model.NodeListingResponse;
import com.amazon.clouddrive.model.ObjectComparator;
import com.amazon.clouddrive.model.PaginatedCloudDriveResponse;
import java.util.List;
/* loaded from: classes11.dex */
public class ListNodesInTrashExtendedResponse extends PaginatedCloudDriveResponse implements NodeListingResponse<NodeExtended> {
    private List<NodeExtended> mData;

    @Override // com.amazon.clouddrive.model.PaginatedCloudDriveResponse
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof ListNodesInTrashExtendedResponse) && compareTo((CloudDriveResponse) ((ListNodesInTrashExtendedResponse) obj)) == 0;
    }

    @Override // com.amazon.clouddrive.model.NodeListingResponse
    public List<NodeExtended> getData() {
        return this.mData;
    }

    @Override // com.amazon.clouddrive.model.PaginatedCloudDriveResponse
    public int hashCode() {
        return (((getData() == null ? 0 : getData().hashCode()) + 1) * 31) + super.hashCode();
    }

    @Override // com.amazon.clouddrive.model.NodeListingResponse
    public void setData(List<NodeExtended> list) {
        this.mData = list;
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
        if (!(cloudDriveResponse instanceof ListNodesInTrashExtendedResponse)) {
            return 1;
        }
        int compare = ObjectComparator.compare(getData(), ((ListNodesInTrashExtendedResponse) cloudDriveResponse).getData());
        return compare != 0 ? compare : super.compareTo(cloudDriveResponse);
    }
}
