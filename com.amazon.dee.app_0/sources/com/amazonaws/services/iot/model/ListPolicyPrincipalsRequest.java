package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazonaws.AmazonWebServiceRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class ListPolicyPrincipalsRequest extends AmazonWebServiceRequest implements Serializable {
    private Boolean ascendingOrder;
    private String marker;
    private Integer pageSize;
    private String policyName;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ListPolicyPrincipalsRequest)) {
            return false;
        }
        ListPolicyPrincipalsRequest listPolicyPrincipalsRequest = (ListPolicyPrincipalsRequest) obj;
        if ((listPolicyPrincipalsRequest.getPolicyName() == null) ^ (getPolicyName() == null)) {
            return false;
        }
        if (listPolicyPrincipalsRequest.getPolicyName() != null && !listPolicyPrincipalsRequest.getPolicyName().equals(getPolicyName())) {
            return false;
        }
        if ((listPolicyPrincipalsRequest.getMarker() == null) ^ (getMarker() == null)) {
            return false;
        }
        if (listPolicyPrincipalsRequest.getMarker() != null && !listPolicyPrincipalsRequest.getMarker().equals(getMarker())) {
            return false;
        }
        if ((listPolicyPrincipalsRequest.getPageSize() == null) ^ (getPageSize() == null)) {
            return false;
        }
        if (listPolicyPrincipalsRequest.getPageSize() != null && !listPolicyPrincipalsRequest.getPageSize().equals(getPageSize())) {
            return false;
        }
        if ((listPolicyPrincipalsRequest.getAscendingOrder() == null) ^ (getAscendingOrder() == null)) {
            return false;
        }
        return listPolicyPrincipalsRequest.getAscendingOrder() == null || listPolicyPrincipalsRequest.getAscendingOrder().equals(getAscendingOrder());
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

    public String getPolicyName() {
        return this.policyName;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((((getPolicyName() == null ? 0 : getPolicyName().hashCode()) + 31) * 31) + (getMarker() == null ? 0 : getMarker().hashCode())) * 31) + (getPageSize() == null ? 0 : getPageSize().hashCode())) * 31;
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

    public void setPolicyName(String str) {
        this.policyName = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getPolicyName() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("policyName: ");
            outline1072.append(getPolicyName());
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

    public ListPolicyPrincipalsRequest withAscendingOrder(Boolean bool) {
        this.ascendingOrder = bool;
        return this;
    }

    public ListPolicyPrincipalsRequest withMarker(String str) {
        this.marker = str;
        return this;
    }

    public ListPolicyPrincipalsRequest withPageSize(Integer num) {
        this.pageSize = num;
        return this;
    }

    public ListPolicyPrincipalsRequest withPolicyName(String str) {
        this.policyName = str;
        return this;
    }
}
