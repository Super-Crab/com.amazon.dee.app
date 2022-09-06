package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazonaws.AmazonWebServiceRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class ListTargetsForSecurityProfileRequest extends AmazonWebServiceRequest implements Serializable {
    private Integer maxResults;
    private String nextToken;
    private String securityProfileName;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ListTargetsForSecurityProfileRequest)) {
            return false;
        }
        ListTargetsForSecurityProfileRequest listTargetsForSecurityProfileRequest = (ListTargetsForSecurityProfileRequest) obj;
        if ((listTargetsForSecurityProfileRequest.getSecurityProfileName() == null) ^ (getSecurityProfileName() == null)) {
            return false;
        }
        if (listTargetsForSecurityProfileRequest.getSecurityProfileName() != null && !listTargetsForSecurityProfileRequest.getSecurityProfileName().equals(getSecurityProfileName())) {
            return false;
        }
        if ((listTargetsForSecurityProfileRequest.getNextToken() == null) ^ (getNextToken() == null)) {
            return false;
        }
        if (listTargetsForSecurityProfileRequest.getNextToken() != null && !listTargetsForSecurityProfileRequest.getNextToken().equals(getNextToken())) {
            return false;
        }
        if ((listTargetsForSecurityProfileRequest.getMaxResults() == null) ^ (getMaxResults() == null)) {
            return false;
        }
        return listTargetsForSecurityProfileRequest.getMaxResults() == null || listTargetsForSecurityProfileRequest.getMaxResults().equals(getMaxResults());
    }

    public Integer getMaxResults() {
        return this.maxResults;
    }

    public String getNextToken() {
        return this.nextToken;
    }

    public String getSecurityProfileName() {
        return this.securityProfileName;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((getSecurityProfileName() == null ? 0 : getSecurityProfileName().hashCode()) + 31) * 31) + (getNextToken() == null ? 0 : getNextToken().hashCode())) * 31;
        if (getMaxResults() != null) {
            i = getMaxResults().hashCode();
        }
        return hashCode + i;
    }

    public void setMaxResults(Integer num) {
        this.maxResults = num;
    }

    public void setNextToken(String str) {
        this.nextToken = str;
    }

    public void setSecurityProfileName(String str) {
        this.securityProfileName = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getSecurityProfileName() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("securityProfileName: ");
            outline1072.append(getSecurityProfileName());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getNextToken() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("nextToken: ");
            outline1073.append(getNextToken());
            outline1073.append(",");
            outline107.append(outline1073.toString());
        }
        if (getMaxResults() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("maxResults: ");
            outline1074.append(getMaxResults());
            outline107.append(outline1074.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public ListTargetsForSecurityProfileRequest withMaxResults(Integer num) {
        this.maxResults = num;
        return this;
    }

    public ListTargetsForSecurityProfileRequest withNextToken(String str) {
        this.nextToken = str;
        return this;
    }

    public ListTargetsForSecurityProfileRequest withSecurityProfileName(String str) {
        this.securityProfileName = str;
        return this;
    }
}
