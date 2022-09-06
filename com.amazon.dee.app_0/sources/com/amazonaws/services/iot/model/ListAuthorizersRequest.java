package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazonaws.AmazonWebServiceRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class ListAuthorizersRequest extends AmazonWebServiceRequest implements Serializable {
    private Boolean ascendingOrder;
    private String marker;
    private Integer pageSize;
    private String status;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ListAuthorizersRequest)) {
            return false;
        }
        ListAuthorizersRequest listAuthorizersRequest = (ListAuthorizersRequest) obj;
        if ((listAuthorizersRequest.getPageSize() == null) ^ (getPageSize() == null)) {
            return false;
        }
        if (listAuthorizersRequest.getPageSize() != null && !listAuthorizersRequest.getPageSize().equals(getPageSize())) {
            return false;
        }
        if ((listAuthorizersRequest.getMarker() == null) ^ (getMarker() == null)) {
            return false;
        }
        if (listAuthorizersRequest.getMarker() != null && !listAuthorizersRequest.getMarker().equals(getMarker())) {
            return false;
        }
        if ((listAuthorizersRequest.getAscendingOrder() == null) ^ (getAscendingOrder() == null)) {
            return false;
        }
        if (listAuthorizersRequest.getAscendingOrder() != null && !listAuthorizersRequest.getAscendingOrder().equals(getAscendingOrder())) {
            return false;
        }
        if ((listAuthorizersRequest.getStatus() == null) ^ (getStatus() == null)) {
            return false;
        }
        return listAuthorizersRequest.getStatus() == null || listAuthorizersRequest.getStatus().equals(getStatus());
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

    public String getStatus() {
        return this.status;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((((getPageSize() == null ? 0 : getPageSize().hashCode()) + 31) * 31) + (getMarker() == null ? 0 : getMarker().hashCode())) * 31) + (getAscendingOrder() == null ? 0 : getAscendingOrder().hashCode())) * 31;
        if (getStatus() != null) {
            i = getStatus().hashCode();
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

    public void setStatus(String str) {
        this.status = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getPageSize() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("pageSize: ");
            outline1072.append(getPageSize());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getMarker() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("marker: ");
            outline1073.append(getMarker());
            outline1073.append(",");
            outline107.append(outline1073.toString());
        }
        if (getAscendingOrder() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("ascendingOrder: ");
            outline1074.append(getAscendingOrder());
            outline1074.append(",");
            outline107.append(outline1074.toString());
        }
        if (getStatus() != null) {
            StringBuilder outline1075 = GeneratedOutlineSupport1.outline107("status: ");
            outline1075.append(getStatus());
            outline107.append(outline1075.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public ListAuthorizersRequest withAscendingOrder(Boolean bool) {
        this.ascendingOrder = bool;
        return this;
    }

    public ListAuthorizersRequest withMarker(String str) {
        this.marker = str;
        return this;
    }

    public ListAuthorizersRequest withPageSize(Integer num) {
        this.pageSize = num;
        return this;
    }

    public ListAuthorizersRequest withStatus(String str) {
        this.status = str;
        return this;
    }

    public void setStatus(AuthorizerStatus authorizerStatus) {
        this.status = authorizerStatus.toString();
    }

    public ListAuthorizersRequest withStatus(AuthorizerStatus authorizerStatus) {
        this.status = authorizerStatus.toString();
        return this;
    }
}
