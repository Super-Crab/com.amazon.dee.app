package com.amazon.clouddrive.cdasdk.prompto.common;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.annotation.JsonProperty;
/* loaded from: classes11.dex */
public class PromptoServicePaginatedRequest extends PromptoServiceCustomerRequest {
    @JsonProperty("maxResults")
    private Integer maxResults;
    @JsonProperty("nextToken")
    private String nextToken;

    @Override // com.amazon.clouddrive.cdasdk.prompto.common.PromptoServiceCustomerRequest, com.amazon.clouddrive.cdasdk.prompto.common.PromptoServiceRequest
    protected boolean canEqual(Object obj) {
        return obj instanceof PromptoServicePaginatedRequest;
    }

    @Override // com.amazon.clouddrive.cdasdk.prompto.common.PromptoServiceCustomerRequest, com.amazon.clouddrive.cdasdk.prompto.common.PromptoServiceRequest
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof PromptoServicePaginatedRequest)) {
            return false;
        }
        PromptoServicePaginatedRequest promptoServicePaginatedRequest = (PromptoServicePaginatedRequest) obj;
        if (!promptoServicePaginatedRequest.canEqual(this) || !super.equals(obj)) {
            return false;
        }
        String nextToken = getNextToken();
        String nextToken2 = promptoServicePaginatedRequest.getNextToken();
        if (nextToken != null ? !nextToken.equals(nextToken2) : nextToken2 != null) {
            return false;
        }
        Integer maxResults = getMaxResults();
        Integer maxResults2 = promptoServicePaginatedRequest.getMaxResults();
        return maxResults != null ? maxResults.equals(maxResults2) : maxResults2 == null;
    }

    public Integer getMaxResults() {
        return this.maxResults;
    }

    public String getNextToken() {
        return this.nextToken;
    }

    @Override // com.amazon.clouddrive.cdasdk.prompto.common.PromptoServiceCustomerRequest, com.amazon.clouddrive.cdasdk.prompto.common.PromptoServiceRequest
    public int hashCode() {
        int hashCode = super.hashCode();
        String nextToken = getNextToken();
        int i = 43;
        int hashCode2 = (hashCode * 59) + (nextToken == null ? 43 : nextToken.hashCode());
        Integer maxResults = getMaxResults();
        int i2 = hashCode2 * 59;
        if (maxResults != null) {
            i = maxResults.hashCode();
        }
        return i2 + i;
    }

    @JsonProperty("maxResults")
    public void setMaxResults(Integer num) {
        this.maxResults = num;
    }

    @JsonProperty("nextToken")
    public void setNextToken(String str) {
        this.nextToken = str;
    }

    @Override // com.amazon.clouddrive.cdasdk.prompto.common.PromptoServiceCustomerRequest, com.amazon.clouddrive.cdasdk.prompto.common.PromptoServiceRequest
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("PromptoServicePaginatedRequest(nextToken=");
        outline107.append(getNextToken());
        outline107.append(", maxResults=");
        outline107.append(getMaxResults());
        outline107.append(")");
        return outline107.toString();
    }
}
