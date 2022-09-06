package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazonaws.AmazonWebServiceRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class ListTargetsForPolicyRequest extends AmazonWebServiceRequest implements Serializable {
    private String marker;
    private Integer pageSize;
    private String policyName;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ListTargetsForPolicyRequest)) {
            return false;
        }
        ListTargetsForPolicyRequest listTargetsForPolicyRequest = (ListTargetsForPolicyRequest) obj;
        if ((listTargetsForPolicyRequest.getPolicyName() == null) ^ (getPolicyName() == null)) {
            return false;
        }
        if (listTargetsForPolicyRequest.getPolicyName() != null && !listTargetsForPolicyRequest.getPolicyName().equals(getPolicyName())) {
            return false;
        }
        if ((listTargetsForPolicyRequest.getMarker() == null) ^ (getMarker() == null)) {
            return false;
        }
        if (listTargetsForPolicyRequest.getMarker() != null && !listTargetsForPolicyRequest.getMarker().equals(getMarker())) {
            return false;
        }
        if ((listTargetsForPolicyRequest.getPageSize() == null) ^ (getPageSize() == null)) {
            return false;
        }
        return listTargetsForPolicyRequest.getPageSize() == null || listTargetsForPolicyRequest.getPageSize().equals(getPageSize());
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
        int hashCode = ((((getPolicyName() == null ? 0 : getPolicyName().hashCode()) + 31) * 31) + (getMarker() == null ? 0 : getMarker().hashCode())) * 31;
        if (getPageSize() != null) {
            i = getPageSize().hashCode();
        }
        return hashCode + i;
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
            outline107.append(outline1074.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public ListTargetsForPolicyRequest withMarker(String str) {
        this.marker = str;
        return this;
    }

    public ListTargetsForPolicyRequest withPageSize(Integer num) {
        this.pageSize = num;
        return this;
    }

    public ListTargetsForPolicyRequest withPolicyName(String str) {
        this.policyName = str;
        return this;
    }
}
