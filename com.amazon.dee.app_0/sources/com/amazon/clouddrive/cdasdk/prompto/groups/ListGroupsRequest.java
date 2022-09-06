package com.amazon.clouddrive.cdasdk.prompto.groups;

import com.amazon.clouddrive.cdasdk.prompto.common.PromptoServicePaginatedRequest;
import com.amazon.deecomms.common.network.AppUrl;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.annotation.JsonProperty;
/* loaded from: classes11.dex */
public class ListGroupsRequest extends PromptoServicePaginatedRequest {
    @JsonProperty("direction")
    private String direction;
    @JsonProperty("kindFilter")
    private String kindFilter;
    @JsonProperty(AppUrl.ACMS.QueryParam.Keys.SORT_ORDER)
    private String sort;

    @Override // com.amazon.clouddrive.cdasdk.prompto.common.PromptoServicePaginatedRequest, com.amazon.clouddrive.cdasdk.prompto.common.PromptoServiceCustomerRequest, com.amazon.clouddrive.cdasdk.prompto.common.PromptoServiceRequest
    protected boolean canEqual(Object obj) {
        return obj instanceof ListGroupsRequest;
    }

    @Override // com.amazon.clouddrive.cdasdk.prompto.common.PromptoServicePaginatedRequest, com.amazon.clouddrive.cdasdk.prompto.common.PromptoServiceCustomerRequest, com.amazon.clouddrive.cdasdk.prompto.common.PromptoServiceRequest
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ListGroupsRequest)) {
            return false;
        }
        ListGroupsRequest listGroupsRequest = (ListGroupsRequest) obj;
        if (!listGroupsRequest.canEqual(this) || !super.equals(obj)) {
            return false;
        }
        String sort = getSort();
        String sort2 = listGroupsRequest.getSort();
        if (sort != null ? !sort.equals(sort2) : sort2 != null) {
            return false;
        }
        String direction = getDirection();
        String direction2 = listGroupsRequest.getDirection();
        if (direction != null ? !direction.equals(direction2) : direction2 != null) {
            return false;
        }
        String kindFilter = getKindFilter();
        String kindFilter2 = listGroupsRequest.getKindFilter();
        return kindFilter != null ? kindFilter.equals(kindFilter2) : kindFilter2 == null;
    }

    public String getDirection() {
        return this.direction;
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
        String sort = getSort();
        int i = 43;
        int hashCode2 = (hashCode * 59) + (sort == null ? 43 : sort.hashCode());
        String direction = getDirection();
        int hashCode3 = (hashCode2 * 59) + (direction == null ? 43 : direction.hashCode());
        String kindFilter = getKindFilter();
        int i2 = hashCode3 * 59;
        if (kindFilter != null) {
            i = kindFilter.hashCode();
        }
        return i2 + i;
    }

    @JsonProperty("direction")
    public void setDirection(String str) {
        this.direction = str;
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
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("ListGroupsRequest(sort=");
        outline107.append(getSort());
        outline107.append(", direction=");
        outline107.append(getDirection());
        outline107.append(", kindFilter=");
        outline107.append(getKindFilter());
        outline107.append(")");
        return outline107.toString();
    }
}
