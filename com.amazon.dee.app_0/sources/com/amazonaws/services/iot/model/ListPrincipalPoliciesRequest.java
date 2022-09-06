package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazonaws.AmazonWebServiceRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class ListPrincipalPoliciesRequest extends AmazonWebServiceRequest implements Serializable {
    private Boolean ascendingOrder;
    private String marker;
    private Integer pageSize;
    private String principal;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ListPrincipalPoliciesRequest)) {
            return false;
        }
        ListPrincipalPoliciesRequest listPrincipalPoliciesRequest = (ListPrincipalPoliciesRequest) obj;
        if ((listPrincipalPoliciesRequest.getPrincipal() == null) ^ (getPrincipal() == null)) {
            return false;
        }
        if (listPrincipalPoliciesRequest.getPrincipal() != null && !listPrincipalPoliciesRequest.getPrincipal().equals(getPrincipal())) {
            return false;
        }
        if ((listPrincipalPoliciesRequest.getMarker() == null) ^ (getMarker() == null)) {
            return false;
        }
        if (listPrincipalPoliciesRequest.getMarker() != null && !listPrincipalPoliciesRequest.getMarker().equals(getMarker())) {
            return false;
        }
        if ((listPrincipalPoliciesRequest.getPageSize() == null) ^ (getPageSize() == null)) {
            return false;
        }
        if (listPrincipalPoliciesRequest.getPageSize() != null && !listPrincipalPoliciesRequest.getPageSize().equals(getPageSize())) {
            return false;
        }
        if ((listPrincipalPoliciesRequest.getAscendingOrder() == null) ^ (getAscendingOrder() == null)) {
            return false;
        }
        return listPrincipalPoliciesRequest.getAscendingOrder() == null || listPrincipalPoliciesRequest.getAscendingOrder().equals(getAscendingOrder());
    }

    public Boolean getAscendingOrder() {
        return this.ascendingOrder;
    }

    public String getMarker() {
        return this.marker;
    }

    public Integer getPageSize() {
        return this.pageSize;
    }

    public String getPrincipal() {
        return this.principal;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((((getPrincipal() == null ? 0 : getPrincipal().hashCode()) + 31) * 31) + (getMarker() == null ? 0 : getMarker().hashCode())) * 31) + (getPageSize() == null ? 0 : getPageSize().hashCode())) * 31;
        if (getAscendingOrder() != null) {
            i = getAscendingOrder().hashCode();
        }
        return hashCode + i;
    }

    public Boolean isAscendingOrder() {
        return this.ascendingOrder;
    }

    public void setAscendingOrder(Boolean bool) {
        this.ascendingOrder = bool;
    }

    public void setMarker(String str) {
        this.marker = str;
    }

    public void setPageSize(Integer num) {
        this.pageSize = num;
    }

    public void setPrincipal(String str) {
        this.principal = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getPrincipal() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("principal: ");
            outline1072.append(getPrincipal());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getMarker() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("marker: ");
            outline1073.append(getMarker());
            outline1073.append(",");
            outline107.append(outline1073.toString());
        }
        if (getPageSize() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("pageSize: ");
            outline1074.append(getPageSize());
            outline1074.append(",");
            outline107.append(outline1074.toString());
        }
        if (getAscendingOrder() != null) {
            StringBuilder outline1075 = GeneratedOutlineSupport1.outline107("ascendingOrder: ");
            outline1075.append(getAscendingOrder());
            outline107.append(outline1075.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public ListPrincipalPoliciesRequest withAscendingOrder(Boolean bool) {
        this.ascendingOrder = bool;
        return this;
    }

    public ListPrincipalPoliciesRequest withMarker(String str) {
        this.marker = str;
        return this;
    }

    public ListPrincipalPoliciesRequest withPageSize(Integer num) {
        this.pageSize = num;
        return this;
    }

    public ListPrincipalPoliciesRequest withPrincipal(String str) {
        this.principal = str;
        return this;
    }
}
