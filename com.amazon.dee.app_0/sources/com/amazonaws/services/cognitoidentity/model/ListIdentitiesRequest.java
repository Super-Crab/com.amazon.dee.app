package com.amazonaws.services.cognitoidentity.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazonaws.AmazonWebServiceRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class ListIdentitiesRequest extends AmazonWebServiceRequest implements Serializable {
    private Boolean hideDisabled;
    private String identityPoolId;
    private Integer maxResults;
    private String nextToken;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ListIdentitiesRequest)) {
            return false;
        }
        ListIdentitiesRequest listIdentitiesRequest = (ListIdentitiesRequest) obj;
        if ((listIdentitiesRequest.getIdentityPoolId() == null) ^ (getIdentityPoolId() == null)) {
            return false;
        }
        if (listIdentitiesRequest.getIdentityPoolId() != null && !listIdentitiesRequest.getIdentityPoolId().equals(getIdentityPoolId())) {
            return false;
        }
        if ((listIdentitiesRequest.getMaxResults() == null) ^ (getMaxResults() == null)) {
            return false;
        }
        if (listIdentitiesRequest.getMaxResults() != null && !listIdentitiesRequest.getMaxResults().equals(getMaxResults())) {
            return false;
        }
        if ((listIdentitiesRequest.getNextToken() == null) ^ (getNextToken() == null)) {
            return false;
        }
        if (listIdentitiesRequest.getNextToken() != null && !listIdentitiesRequest.getNextToken().equals(getNextToken())) {
            return false;
        }
        if ((listIdentitiesRequest.getHideDisabled() == null) ^ (getHideDisabled() == null)) {
            return false;
        }
        return listIdentitiesRequest.getHideDisabled() == null || listIdentitiesRequest.getHideDisabled().equals(getHideDisabled());
    }

    public Boolean getHideDisabled() {
        return this.hideDisabled;
    }

    public String getIdentityPoolId() {
        return this.identityPoolId;
    }

    public Integer getMaxResults() {
        return this.maxResults;
    }

    public String getNextToken() {
        return this.nextToken;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((((getIdentityPoolId() == null ? 0 : getIdentityPoolId().hashCode()) + 31) * 31) + (getMaxResults() == null ? 0 : getMaxResults().hashCode())) * 31) + (getNextToken() == null ? 0 : getNextToken().hashCode())) * 31;
        if (getHideDisabled() != null) {
            i = getHideDisabled().hashCode();
        }
        return hashCode + i;
    }

    public Boolean isHideDisabled() {
        return this.hideDisabled;
    }

    public void setHideDisabled(Boolean bool) {
        this.hideDisabled = bool;
    }

    public void setIdentityPoolId(String str) {
        this.identityPoolId = str;
    }

    public void setMaxResults(Integer num) {
        this.maxResults = num;
    }

    public void setNextToken(String str) {
        this.nextToken = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getIdentityPoolId() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("IdentityPoolId: ");
            outline1072.append(getIdentityPoolId());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getMaxResults() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("MaxResults: ");
            outline1073.append(getMaxResults());
            outline1073.append(",");
            outline107.append(outline1073.toString());
        }
        if (getNextToken() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("NextToken: ");
            outline1074.append(getNextToken());
            outline1074.append(",");
            outline107.append(outline1074.toString());
        }
        if (getHideDisabled() != null) {
            StringBuilder outline1075 = GeneratedOutlineSupport1.outline107("HideDisabled: ");
            outline1075.append(getHideDisabled());
            outline107.append(outline1075.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public ListIdentitiesRequest withHideDisabled(Boolean bool) {
        this.hideDisabled = bool;
        return this;
    }

    public ListIdentitiesRequest withIdentityPoolId(String str) {
        this.identityPoolId = str;
        return this;
    }

    public ListIdentitiesRequest withMaxResults(Integer num) {
        this.maxResults = num;
        return this;
    }

    public ListIdentitiesRequest withNextToken(String str) {
        this.nextToken = str;
        return this;
    }
}
