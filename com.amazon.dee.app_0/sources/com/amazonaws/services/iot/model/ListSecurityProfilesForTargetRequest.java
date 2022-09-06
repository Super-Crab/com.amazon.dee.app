package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazonaws.AmazonWebServiceRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class ListSecurityProfilesForTargetRequest extends AmazonWebServiceRequest implements Serializable {
    private Integer maxResults;
    private String nextToken;
    private Boolean recursive;
    private String securityProfileTargetArn;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ListSecurityProfilesForTargetRequest)) {
            return false;
        }
        ListSecurityProfilesForTargetRequest listSecurityProfilesForTargetRequest = (ListSecurityProfilesForTargetRequest) obj;
        if ((listSecurityProfilesForTargetRequest.getNextToken() == null) ^ (getNextToken() == null)) {
            return false;
        }
        if (listSecurityProfilesForTargetRequest.getNextToken() != null && !listSecurityProfilesForTargetRequest.getNextToken().equals(getNextToken())) {
            return false;
        }
        if ((listSecurityProfilesForTargetRequest.getMaxResults() == null) ^ (getMaxResults() == null)) {
            return false;
        }
        if (listSecurityProfilesForTargetRequest.getMaxResults() != null && !listSecurityProfilesForTargetRequest.getMaxResults().equals(getMaxResults())) {
            return false;
        }
        if ((listSecurityProfilesForTargetRequest.getRecursive() == null) ^ (getRecursive() == null)) {
            return false;
        }
        if (listSecurityProfilesForTargetRequest.getRecursive() != null && !listSecurityProfilesForTargetRequest.getRecursive().equals(getRecursive())) {
            return false;
        }
        if ((listSecurityProfilesForTargetRequest.getSecurityProfileTargetArn() == null) ^ (getSecurityProfileTargetArn() == null)) {
            return false;
        }
        return listSecurityProfilesForTargetRequest.getSecurityProfileTargetArn() == null || listSecurityProfilesForTargetRequest.getSecurityProfileTargetArn().equals(getSecurityProfileTargetArn());
    }

    public Integer getMaxResults() {
        return this.maxResults;
    }

    public String getNextToken() {
        return this.nextToken;
    }

    public Boolean getRecursive() {
        return this.recursive;
    }

    public String getSecurityProfileTargetArn() {
        return this.securityProfileTargetArn;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((((getNextToken() == null ? 0 : getNextToken().hashCode()) + 31) * 31) + (getMaxResults() == null ? 0 : getMaxResults().hashCode())) * 31) + (getRecursive() == null ? 0 : getRecursive().hashCode())) * 31;
        if (getSecurityProfileTargetArn() != null) {
            i = getSecurityProfileTargetArn().hashCode();
        }
        return hashCode + i;
    }

    public Boolean isRecursive() {
        return this.recursive;
    }

    public void setMaxResults(Integer num) {
        this.maxResults = num;
    }

    public void setNextToken(String str) {
        this.nextToken = str;
    }

    public void setRecursive(Boolean bool) {
        this.recursive = bool;
    }

    public void setSecurityProfileTargetArn(String str) {
        this.securityProfileTargetArn = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getNextToken() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("nextToken: ");
            outline1072.append(getNextToken());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getMaxResults() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("maxResults: ");
            outline1073.append(getMaxResults());
            outline1073.append(",");
            outline107.append(outline1073.toString());
        }
        if (getRecursive() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("recursive: ");
            outline1074.append(getRecursive());
            outline1074.append(",");
            outline107.append(outline1074.toString());
        }
        if (getSecurityProfileTargetArn() != null) {
            StringBuilder outline1075 = GeneratedOutlineSupport1.outline107("securityProfileTargetArn: ");
            outline1075.append(getSecurityProfileTargetArn());
            outline107.append(outline1075.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public ListSecurityProfilesForTargetRequest withMaxResults(Integer num) {
        this.maxResults = num;
        return this;
    }

    public ListSecurityProfilesForTargetRequest withNextToken(String str) {
        this.nextToken = str;
        return this;
    }

    public ListSecurityProfilesForTargetRequest withRecursive(Boolean bool) {
        this.recursive = bool;
        return this;
    }

    public ListSecurityProfilesForTargetRequest withSecurityProfileTargetArn(String str) {
        this.securityProfileTargetArn = str;
        return this;
    }
}
