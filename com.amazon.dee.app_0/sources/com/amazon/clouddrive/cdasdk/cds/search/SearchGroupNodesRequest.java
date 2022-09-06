package com.amazon.clouddrive.cdasdk.cds.search;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.annotation.JsonProperty;
/* loaded from: classes11.dex */
public class SearchGroupNodesRequest extends SearchKeyRequest {
    @JsonProperty("groupShareToken")
    private String groupShareToken;

    @Override // com.amazon.clouddrive.cdasdk.cds.search.SearchKeyRequest, com.amazon.clouddrive.cdasdk.cds.common.PaginatedCloudDriveRequest, com.amazon.clouddrive.cdasdk.cds.common.CloudDriveRequest
    protected boolean canEqual(Object obj) {
        return obj instanceof SearchGroupNodesRequest;
    }

    @Override // com.amazon.clouddrive.cdasdk.cds.search.SearchKeyRequest, com.amazon.clouddrive.cdasdk.cds.common.PaginatedCloudDriveRequest, com.amazon.clouddrive.cdasdk.cds.common.CloudDriveRequest
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SearchGroupNodesRequest)) {
            return false;
        }
        SearchGroupNodesRequest searchGroupNodesRequest = (SearchGroupNodesRequest) obj;
        if (!searchGroupNodesRequest.canEqual(this) || !super.equals(obj)) {
            return false;
        }
        String groupShareToken = getGroupShareToken();
        String groupShareToken2 = searchGroupNodesRequest.getGroupShareToken();
        return groupShareToken != null ? groupShareToken.equals(groupShareToken2) : groupShareToken2 == null;
    }

    public String getGroupShareToken() {
        return this.groupShareToken;
    }

    @Override // com.amazon.clouddrive.cdasdk.cds.search.SearchKeyRequest, com.amazon.clouddrive.cdasdk.cds.common.PaginatedCloudDriveRequest, com.amazon.clouddrive.cdasdk.cds.common.CloudDriveRequest
    public int hashCode() {
        int hashCode = super.hashCode();
        String groupShareToken = getGroupShareToken();
        return (hashCode * 59) + (groupShareToken == null ? 43 : groupShareToken.hashCode());
    }

    @JsonProperty("groupShareToken")
    public void setGroupShareToken(String str) {
        this.groupShareToken = str;
    }

    @Override // com.amazon.clouddrive.cdasdk.cds.search.SearchKeyRequest, com.amazon.clouddrive.cdasdk.cds.common.PaginatedCloudDriveRequest, com.amazon.clouddrive.cdasdk.cds.common.CloudDriveRequest
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("SearchGroupNodesRequest(groupShareToken=");
        outline107.append(getGroupShareToken());
        outline107.append(")");
        return outline107.toString();
    }
}
