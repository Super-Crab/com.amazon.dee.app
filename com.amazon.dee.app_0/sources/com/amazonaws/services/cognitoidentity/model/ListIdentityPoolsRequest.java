package com.amazonaws.services.cognitoidentity.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazonaws.AmazonWebServiceRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class ListIdentityPoolsRequest extends AmazonWebServiceRequest implements Serializable {
    private Integer maxResults;
    private String nextToken;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ListIdentityPoolsRequest)) {
            return false;
        }
        ListIdentityPoolsRequest listIdentityPoolsRequest = (ListIdentityPoolsRequest) obj;
        if ((listIdentityPoolsRequest.getMaxResults() == null) ^ (getMaxResults() == null)) {
            return false;
        }
        if (listIdentityPoolsRequest.getMaxResults() != null && !listIdentityPoolsRequest.getMaxResults().equals(getMaxResults())) {
            return false;
        }
        if ((listIdentityPoolsRequest.getNextToken() == null) ^ (getNextToken() == null)) {
            return false;
        }
        return listIdentityPoolsRequest.getNextToken() == null || listIdentityPoolsRequest.getNextToken().equals(getNextToken());
    }

    public Integer getMaxResults() {
        return this.maxResults;
    }

    public String getNextToken() {
        return this.nextToken;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((getMaxResults() == null ? 0 : getMaxResults().hashCode()) + 31) * 31;
        if (getNextToken() != null) {
            i = getNextToken().hashCode();
        }
        return hashCode + i;
    }

    public void setMaxResults(Integer num) {
        this.maxResults = num;
    }

    public void setNextToken(String str) {
        this.nextToken = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getMaxResults() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("MaxResults: ");
            outline1072.append(getMaxResults());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getNextToken() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("NextToken: ");
            outline1073.append(getNextToken());
            outline107.append(outline1073.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public ListIdentityPoolsRequest withMaxResults(Integer num) {
        this.maxResults = num;
        return this;
    }

    public ListIdentityPoolsRequest withNextToken(String str) {
        this.nextToken = str;
        return this;
    }
}
