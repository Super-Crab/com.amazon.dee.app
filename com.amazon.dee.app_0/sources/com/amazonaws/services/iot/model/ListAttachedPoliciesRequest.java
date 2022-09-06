package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazonaws.AmazonWebServiceRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class ListAttachedPoliciesRequest extends AmazonWebServiceRequest implements Serializable {
    private String marker;
    private Integer pageSize;
    private Boolean recursive;
    private String target;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ListAttachedPoliciesRequest)) {
            return false;
        }
        ListAttachedPoliciesRequest listAttachedPoliciesRequest = (ListAttachedPoliciesRequest) obj;
        if ((listAttachedPoliciesRequest.getTarget() == null) ^ (getTarget() == null)) {
            return false;
        }
        if (listAttachedPoliciesRequest.getTarget() != null && !listAttachedPoliciesRequest.getTarget().equals(getTarget())) {
            return false;
        }
        if ((listAttachedPoliciesRequest.getRecursive() == null) ^ (getRecursive() == null)) {
            return false;
        }
        if (listAttachedPoliciesRequest.getRecursive() != null && !listAttachedPoliciesRequest.getRecursive().equals(getRecursive())) {
            return false;
        }
        if ((listAttachedPoliciesRequest.getMarker() == null) ^ (getMarker() == null)) {
            return false;
        }
        if (listAttachedPoliciesRequest.getMarker() != null && !listAttachedPoliciesRequest.getMarker().equals(getMarker())) {
            return false;
        }
        if ((listAttachedPoliciesRequest.getPageSize() == null) ^ (getPageSize() == null)) {
            return false;
        }
        return listAttachedPoliciesRequest.getPageSize() == null || listAttachedPoliciesRequest.getPageSize().equals(getPageSize());
    }

    public String getMarker() {
        return this.marker;
    }

    public Integer getPageSize() {
        return this.pageSize;
    }

    public Boolean getRecursive() {
        return this.recursive;
    }

    public String getTarget() {
        return this.target;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((((getTarget() == null ? 0 : getTarget().hashCode()) + 31) * 31) + (getRecursive() == null ? 0 : getRecursive().hashCode())) * 31) + (getMarker() == null ? 0 : getMarker().hashCode())) * 31;
        if (getPageSize() != null) {
            i = getPageSize().hashCode();
        }
        return hashCode + i;
    }

    public Boolean isRecursive() {
        return this.recursive;
    }

    public void setMarker(String str) {
        this.marker = str;
    }

    public void setPageSize(Integer num) {
        this.pageSize = num;
    }

    public void setRecursive(Boolean bool) {
        this.recursive = bool;
    }

    public void setTarget(String str) {
        this.target = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getTarget() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("target: ");
            outline1072.append(getTarget());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getRecursive() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("recursive: ");
            outline1073.append(getRecursive());
            outline1073.append(",");
            outline107.append(outline1073.toString());
        }
        if (getMarker() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("marker: ");
            outline1074.append(getMarker());
            outline1074.append(",");
            outline107.append(outline1074.toString());
        }
        if (getPageSize() != null) {
            StringBuilder outline1075 = GeneratedOutlineSupport1.outline107("pageSize: ");
            outline1075.append(getPageSize());
            outline107.append(outline1075.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public ListAttachedPoliciesRequest withMarker(String str) {
        this.marker = str;
        return this;
    }

    public ListAttachedPoliciesRequest withPageSize(Integer num) {
        this.pageSize = num;
        return this;
    }

    public ListAttachedPoliciesRequest withRecursive(Boolean bool) {
        this.recursive = bool;
        return this;
    }

    public ListAttachedPoliciesRequest withTarget(String str) {
        this.target = str;
        return this;
    }
}
