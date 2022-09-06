package com.amazonaws.services.logs.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazonaws.AmazonWebServiceRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class DescribeSubscriptionFiltersRequest extends AmazonWebServiceRequest implements Serializable {
    private String filterNamePrefix;
    private Integer limit;
    private String logGroupName;
    private String nextToken;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof DescribeSubscriptionFiltersRequest)) {
            return false;
        }
        DescribeSubscriptionFiltersRequest describeSubscriptionFiltersRequest = (DescribeSubscriptionFiltersRequest) obj;
        if ((describeSubscriptionFiltersRequest.getLogGroupName() == null) ^ (getLogGroupName() == null)) {
            return false;
        }
        if (describeSubscriptionFiltersRequest.getLogGroupName() != null && !describeSubscriptionFiltersRequest.getLogGroupName().equals(getLogGroupName())) {
            return false;
        }
        if ((describeSubscriptionFiltersRequest.getFilterNamePrefix() == null) ^ (getFilterNamePrefix() == null)) {
            return false;
        }
        if (describeSubscriptionFiltersRequest.getFilterNamePrefix() != null && !describeSubscriptionFiltersRequest.getFilterNamePrefix().equals(getFilterNamePrefix())) {
            return false;
        }
        if ((describeSubscriptionFiltersRequest.getNextToken() == null) ^ (getNextToken() == null)) {
            return false;
        }
        if (describeSubscriptionFiltersRequest.getNextToken() != null && !describeSubscriptionFiltersRequest.getNextToken().equals(getNextToken())) {
            return false;
        }
        if ((describeSubscriptionFiltersRequest.getLimit() == null) ^ (getLimit() == null)) {
            return false;
        }
        return describeSubscriptionFiltersRequest.getLimit() == null || describeSubscriptionFiltersRequest.getLimit().equals(getLimit());
    }

    public String getFilterNamePrefix() {
        return this.filterNamePrefix;
    }

    public Integer getLimit() {
        return this.limit;
    }

    public String getLogGroupName() {
        return this.logGroupName;
    }

    public String getNextToken() {
        return this.nextToken;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((((getLogGroupName() == null ? 0 : getLogGroupName().hashCode()) + 31) * 31) + (getFilterNamePrefix() == null ? 0 : getFilterNamePrefix().hashCode())) * 31) + (getNextToken() == null ? 0 : getNextToken().hashCode())) * 31;
        if (getLimit() != null) {
            i = getLimit().hashCode();
        }
        return hashCode + i;
    }

    public void setFilterNamePrefix(String str) {
        this.filterNamePrefix = str;
    }

    public void setLimit(Integer num) {
        this.limit = num;
    }

    public void setLogGroupName(String str) {
        this.logGroupName = str;
    }

    public void setNextToken(String str) {
        this.nextToken = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getLogGroupName() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("logGroupName: ");
            outline1072.append(getLogGroupName());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getFilterNamePrefix() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("filterNamePrefix: ");
            outline1073.append(getFilterNamePrefix());
            outline1073.append(",");
            outline107.append(outline1073.toString());
        }
        if (getNextToken() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("nextToken: ");
            outline1074.append(getNextToken());
            outline1074.append(",");
            outline107.append(outline1074.toString());
        }
        if (getLimit() != null) {
            StringBuilder outline1075 = GeneratedOutlineSupport1.outline107("limit: ");
            outline1075.append(getLimit());
            outline107.append(outline1075.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public DescribeSubscriptionFiltersRequest withFilterNamePrefix(String str) {
        this.filterNamePrefix = str;
        return this;
    }

    public DescribeSubscriptionFiltersRequest withLimit(Integer num) {
        this.limit = num;
        return this;
    }

    public DescribeSubscriptionFiltersRequest withLogGroupName(String str) {
        this.logGroupName = str;
        return this;
    }

    public DescribeSubscriptionFiltersRequest withNextToken(String str) {
        this.nextToken = str;
        return this;
    }
}
