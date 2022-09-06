package com.amazonaws.services.logs.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazonaws.AmazonWebServiceRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class DescribeResourcePoliciesRequest extends AmazonWebServiceRequest implements Serializable {
    private Integer limit;
    private String nextToken;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof DescribeResourcePoliciesRequest)) {
            return false;
        }
        DescribeResourcePoliciesRequest describeResourcePoliciesRequest = (DescribeResourcePoliciesRequest) obj;
        if ((describeResourcePoliciesRequest.getNextToken() == null) ^ (getNextToken() == null)) {
            return false;
        }
        if (describeResourcePoliciesRequest.getNextToken() != null && !describeResourcePoliciesRequest.getNextToken().equals(getNextToken())) {
            return false;
        }
        if ((describeResourcePoliciesRequest.getLimit() == null) ^ (getLimit() == null)) {
            return false;
        }
        return describeResourcePoliciesRequest.getLimit() == null || describeResourcePoliciesRequest.getLimit().equals(getLimit());
    }

    public Integer getLimit() {
        return this.limit;
    }

    public String getNextToken() {
        return this.nextToken;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((getNextToken() == null ? 0 : getNextToken().hashCode()) + 31) * 31;
        if (getLimit() != null) {
            i = getLimit().hashCode();
        }
        return hashCode + i;
    }

    public void setLimit(Integer num) {
        this.limit = num;
    }

    public void setNextToken(String str) {
        this.nextToken = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getNextToken() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("nextToken: ");
            outline1072.append(getNextToken());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getLimit() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("limit: ");
            outline1073.append(getLimit());
            outline107.append(outline1073.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public DescribeResourcePoliciesRequest withLimit(Integer num) {
        this.limit = num;
        return this;
    }

    public DescribeResourcePoliciesRequest withNextToken(String str) {
        this.nextToken = str;
        return this;
    }
}
