package com.amazon.clouddrive.extended.model;

import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes11.dex */
public class SearchGroupNodesExtendedResponse extends SearchGroupNodesResponse {
    GroupsData groupsData;

    protected boolean canEqual(Object obj) {
        return obj instanceof SearchGroupNodesExtendedResponse;
    }

    @Override // com.amazon.clouddrive.extended.model.SearchKeyResponse, com.amazon.clouddrive.model.PaginatedCloudDriveResponse
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SearchGroupNodesExtendedResponse)) {
            return false;
        }
        SearchGroupNodesExtendedResponse searchGroupNodesExtendedResponse = (SearchGroupNodesExtendedResponse) obj;
        if (!searchGroupNodesExtendedResponse.canEqual(this)) {
            return false;
        }
        GroupsData groupsData = getGroupsData();
        GroupsData groupsData2 = searchGroupNodesExtendedResponse.getGroupsData();
        return groupsData != null ? groupsData.equals(groupsData2) : groupsData2 == null;
    }

    public GroupsData getGroupsData() {
        return this.groupsData;
    }

    @Override // com.amazon.clouddrive.extended.model.SearchKeyResponse, com.amazon.clouddrive.model.PaginatedCloudDriveResponse
    public int hashCode() {
        GroupsData groupsData = getGroupsData();
        return 59 + (groupsData == null ? 43 : groupsData.hashCode());
    }

    public void setGroupsData(GroupsData groupsData) {
        this.groupsData = groupsData;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("SearchGroupNodesExtendedResponse(groupsData=");
        outline107.append(getGroupsData());
        outline107.append(")");
        return outline107.toString();
    }
}
