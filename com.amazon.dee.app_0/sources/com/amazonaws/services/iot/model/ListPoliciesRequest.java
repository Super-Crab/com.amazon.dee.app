package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazonaws.AmazonWebServiceRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class ListPoliciesRequest extends AmazonWebServiceRequest implements Serializable {
    private Boolean ascendingOrder;
    private String marker;
    private Integer pageSize;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ListPoliciesRequest)) {
            return false;
        }
        ListPoliciesRequest listPoliciesRequest = (ListPoliciesRequest) obj;
        if ((listPoliciesRequest.getMarker() == null) ^ (getMarker() == null)) {
            return false;
        }
        if (listPoliciesRequest.getMarker() != null && !listPoliciesRequest.getMarker().equals(getMarker())) {
            return false;
        }
        if ((listPoliciesRequest.getPageSize() == null) ^ (getPageSize() == null)) {
            return false;
        }
        if (listPoliciesRequest.getPageSize() != null && !listPoliciesRequest.getPageSize().equals(getPageSize())) {
            return false;
        }
        if ((listPoliciesRequest.getAscendingOrder() == null) ^ (getAscendingOrder() == null)) {
            return false;
        }
        return listPoliciesRequest.getAscendingOrder() == null || listPoliciesRequest.getAscendingOrder().equals(getAscendingOrder());
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

    public int hashCode() {
        int i = 0;
        int hashCode = ((((getMarker() == null ? 0 : getMarker().hashCode()) + 31) * 31) + (getPageSize() == null ? 0 : getPageSize().hashCode())) * 31;
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

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getMarker() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("marker: ");
            outline1072.append(getMarker());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getPageSize() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("pageSize: ");
            outline1073.append(getPageSize());
            outline1073.append(",");
            outline107.append(outline1073.toString());
        }
        if (getAscendingOrder() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("ascendingOrder: ");
            outline1074.append(getAscendingOrder());
            outline107.append(outline1074.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public ListPoliciesRequest withAscendingOrder(Boolean bool) {
        this.ascendingOrder = bool;
        return this;
    }

    public ListPoliciesRequest withMarker(String str) {
        this.marker = str;
        return this;
    }

    public ListPoliciesRequest withPageSize(Integer num) {
        this.pageSize = num;
        return this;
    }
}
