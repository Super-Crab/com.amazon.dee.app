package com.amazon.clouddrive.cdasdk.prompto.groups;

import com.amazon.clouddrive.cdasdk.aps.account.FeatureName;
import com.amazon.clouddrive.cdasdk.prompto.common.PromptoServicePaginatedResponse;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
/* loaded from: classes11.dex */
public class ListGroupsResponse extends PromptoServicePaginatedResponse {
    @JsonProperty(FeatureName.GROUPS)
    private List<GroupResponse> groups;

    @Override // com.amazon.clouddrive.cdasdk.prompto.common.PromptoServicePaginatedResponse
    protected boolean canEqual(Object obj) {
        return obj instanceof ListGroupsResponse;
    }

    @Override // com.amazon.clouddrive.cdasdk.prompto.common.PromptoServicePaginatedResponse
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ListGroupsResponse)) {
            return false;
        }
        ListGroupsResponse listGroupsResponse = (ListGroupsResponse) obj;
        if (!listGroupsResponse.canEqual(this) || !super.equals(obj)) {
            return false;
        }
        List<GroupResponse> groups = getGroups();
        List<GroupResponse> groups2 = listGroupsResponse.getGroups();
        return groups != null ? groups.equals(groups2) : groups2 == null;
    }

    public List<GroupResponse> getGroups() {
        return this.groups;
    }

    @Override // com.amazon.clouddrive.cdasdk.prompto.common.PromptoServicePaginatedResponse
    public int hashCode() {
        int hashCode = super.hashCode();
        List<GroupResponse> groups = getGroups();
        return (hashCode * 59) + (groups == null ? 43 : groups.hashCode());
    }

    @JsonProperty(FeatureName.GROUPS)
    public void setGroups(List<GroupResponse> list) {
        this.groups = list;
    }

    @Override // com.amazon.clouddrive.cdasdk.prompto.common.PromptoServicePaginatedResponse
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("ListGroupsResponse(groups=");
        outline107.append(getGroups());
        outline107.append(")");
        return outline107.toString();
    }
}
