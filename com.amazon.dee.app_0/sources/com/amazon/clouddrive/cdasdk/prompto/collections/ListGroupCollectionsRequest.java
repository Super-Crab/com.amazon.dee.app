package com.amazon.clouddrive.cdasdk.prompto.collections;

import com.amazon.clouddrive.cdasdk.prompto.common.PromptoServicePaginatedRequest;
import com.amazon.deecomms.common.network.AppUrl;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.annotation.JsonProperty;
/* loaded from: classes11.dex */
public class ListGroupCollectionsRequest extends PromptoServicePaginatedRequest {
    @JsonProperty("addedBy")
    private String addedBy;
    @JsonProperty("direction")
    private String direction;
    @JsonProperty("groupId")
    private String groupId;
    @JsonProperty("kindFilter")
    private String kindFilter;
    @JsonProperty(AppUrl.ACMS.QueryParam.Keys.SORT_ORDER)
    private String sort;

    @Override // com.amazon.clouddrive.cdasdk.prompto.common.PromptoServicePaginatedRequest, com.amazon.clouddrive.cdasdk.prompto.common.PromptoServiceCustomerRequest, com.amazon.clouddrive.cdasdk.prompto.common.PromptoServiceRequest
    protected boolean canEqual(Object obj) {
        return obj instanceof ListGroupCollectionsRequest;
    }

    @Override // com.amazon.clouddrive.cdasdk.prompto.common.PromptoServicePaginatedRequest, com.amazon.clouddrive.cdasdk.prompto.common.PromptoServiceCustomerRequest, com.amazon.clouddrive.cdasdk.prompto.common.PromptoServiceRequest
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ListGroupCollectionsRequest)) {
            return false;
        }
        ListGroupCollectionsRequest listGroupCollectionsRequest = (ListGroupCollectionsRequest) obj;
        if (!listGroupCollectionsRequest.canEqual(this) || !super.equals(obj)) {
            return false;
        }
        String groupId = getGroupId();
        String groupId2 = listGroupCollectionsRequest.getGroupId();
        if (groupId != null ? !groupId.equals(groupId2) : groupId2 != null) {
            return false;
        }
        String kindFilter = getKindFilter();
        String kindFilter2 = listGroupCollectionsRequest.getKindFilter();
        if (kindFilter != null ? !kindFilter.equals(kindFilter2) : kindFilter2 != null) {
            return false;
        }
        String addedBy = getAddedBy();
        String addedBy2 = listGroupCollectionsRequest.getAddedBy();
        if (addedBy != null ? !addedBy.equals(addedBy2) : addedBy2 != null) {
            return false;
        }
        String sort = getSort();
        String sort2 = listGroupCollectionsRequest.getSort();
        if (sort != null ? !sort.equals(sort2) : sort2 != null) {
            return false;
        }
        String direction = getDirection();
        String direction2 = listGroupCollectionsRequest.getDirection();
        return direction != null ? direction.equals(direction2) : direction2 == null;
    }

    public String getAddedBy() {
        return this.addedBy;
    }

    public String getDirection() {
        return this.direction;
    }

    public String getGroupId() {
        return this.groupId;
    }

    public String getKindFilter() {
        return this.kindFilter;
    }

    public String getSort() {
        return this.sort;
    }

    @Override // com.amazon.clouddrive.cdasdk.prompto.common.PromptoServicePaginatedRequest, com.amazon.clouddrive.cdasdk.prompto.common.PromptoServiceCustomerRequest, com.amazon.clouddrive.cdasdk.prompto.common.PromptoServiceRequest
    public int hashCode() {
        int hashCode = super.hashCode();
        String groupId = getGroupId();
        int i = 43;
        int hashCode2 = (hashCode * 59) + (groupId == null ? 43 : groupId.hashCode());
        String kindFilter = getKindFilter();
        int hashCode3 = (hashCode2 * 59) + (kindFilter == null ? 43 : kindFilter.hashCode());
        String addedBy = getAddedBy();
        int hashCode4 = (hashCode3 * 59) + (addedBy == null ? 43 : addedBy.hashCode());
        String sort = getSort();
        int hashCode5 = (hashCode4 * 59) + (sort == null ? 43 : sort.hashCode());
        String direction = getDirection();
        int i2 = hashCode5 * 59;
        if (direction != null) {
            i = direction.hashCode();
        }
        return i2 + i;
    }

    @JsonProperty("addedBy")
    public void setAddedBy(String str) {
        this.addedBy = str;
    }

    @JsonProperty("direction")
    public void setDirection(String str) {
        this.direction = str;
    }

    @JsonProperty("groupId")
    public void setGroupId(String str) {
        this.groupId = str;
    }

    @JsonProperty("kindFilter")
    public void setKindFilter(String str) {
        this.kindFilter = str;
    }

    @JsonProperty(AppUrl.ACMS.QueryParam.Keys.SORT_ORDER)
    public void setSort(String str) {
        this.sort = str;
    }

    @Override // com.amazon.clouddrive.cdasdk.prompto.common.PromptoServicePaginatedRequest, com.amazon.clouddrive.cdasdk.prompto.common.PromptoServiceCustomerRequest, com.amazon.clouddrive.cdasdk.prompto.common.PromptoServiceRequest
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("ListGroupCollectionsRequest(groupId=");
        outline107.append(getGroupId());
        outline107.append(", kindFilter=");
        outline107.append(getKindFilter());
        outline107.append(", addedBy=");
        outline107.append(getAddedBy());
        outline107.append(", sort=");
        outline107.append(getSort());
        outline107.append(", direction=");
        outline107.append(getDirection());
        outline107.append(")");
        return outline107.toString();
    }
}
