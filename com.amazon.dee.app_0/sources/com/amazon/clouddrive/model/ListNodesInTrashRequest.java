package com.amazon.clouddrive.model;
/* loaded from: classes11.dex */
public class ListNodesInTrashRequest extends PaginatedCloudDriveRequest {
    @Override // com.amazon.clouddrive.model.PaginatedCloudDriveRequest
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof ListNodesInTrashRequest) && compareTo((CloudDriveRequest) ((ListNodesInTrashRequest) obj)) == 0;
    }

    @Override // com.amazon.clouddrive.model.PaginatedCloudDriveRequest
    public int hashCode() {
        return 31 + super.hashCode();
    }

    public ListNodesInTrashRequest withFields(String str) {
        setFields(str);
        return this;
    }

    public ListNodesInTrashRequest withFilters(String str) {
        setFilters(str);
        return this;
    }

    public ListNodesInTrashRequest withLimit(Integer num) {
        setLimit(num);
        return this;
    }

    public ListNodesInTrashRequest withOffset(Integer num) {
        setOffset(num);
        return this;
    }

    public ListNodesInTrashRequest withSort(String str) {
        setSort(str);
        return this;
    }

    public ListNodesInTrashRequest withStartToken(String str) {
        setStartToken(str);
        return this;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.clouddrive.model.PaginatedCloudDriveRequest, java.lang.Comparable
    public int compareTo(CloudDriveRequest cloudDriveRequest) {
        if (cloudDriveRequest == null) {
            return -1;
        }
        if (cloudDriveRequest == this) {
            return 0;
        }
        if (cloudDriveRequest instanceof ListNodesInTrashRequest) {
            return super.compareTo(cloudDriveRequest);
        }
        return 1;
    }
}
