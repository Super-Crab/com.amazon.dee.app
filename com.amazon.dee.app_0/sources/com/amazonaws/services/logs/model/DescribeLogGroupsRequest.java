package com.amazonaws.services.logs.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazonaws.AmazonWebServiceRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class DescribeLogGroupsRequest extends AmazonWebServiceRequest implements Serializable {
    private Integer limit;
    private String logGroupNamePrefix;
    private String nextToken;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof DescribeLogGroupsRequest)) {
            return false;
        }
        DescribeLogGroupsRequest describeLogGroupsRequest = (DescribeLogGroupsRequest) obj;
        if ((describeLogGroupsRequest.getLogGroupNamePrefix() == null) ^ (getLogGroupNamePrefix() == null)) {
            return false;
        }
        if (describeLogGroupsRequest.getLogGroupNamePrefix() != null && !describeLogGroupsRequest.getLogGroupNamePrefix().equals(getLogGroupNamePrefix())) {
            return false;
        }
        if ((describeLogGroupsRequest.getNextToken() == null) ^ (getNextToken() == null)) {
            return false;
        }
        if (describeLogGroupsRequest.getNextToken() != null && !describeLogGroupsRequest.getNextToken().equals(getNextToken())) {
            return false;
        }
        if ((describeLogGroupsRequest.getLimit() == null) ^ (getLimit() == null)) {
            return false;
        }
        return describeLogGroupsRequest.getLimit() == null || describeLogGroupsRequest.getLimit().equals(getLimit());
    }

    public Integer getLimit() {
        return this.limit;
    }

    public String getLogGroupNamePrefix() {
        return this.logGroupNamePrefix;
    }

    public String getNextToken() {
        return this.nextToken;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((getLogGroupNamePrefix() == null ? 0 : getLogGroupNamePrefix().hashCode()) + 31) * 31) + (getNextToken() == null ? 0 : getNextToken().hashCode())) * 31;
        if (getLimit() != null) {
            i = getLimit().hashCode();
        }
        return hashCode + i;
    }

    public void setLimit(Integer num) {
        this.limit = num;
    }

    public void setLogGroupNamePrefix(String str) {
        this.logGroupNamePrefix = str;
    }

    public void setNextToken(String str) {
        this.nextToken = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getLogGroupNamePrefix() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("logGroupNamePrefix: ");
            outline1072.append(getLogGroupNamePrefix());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getNextToken() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("nextToken: ");
            outline1073.append(getNextToken());
            outline1073.append(",");
            outline107.append(outline1073.toString());
        }
        if (getLimit() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("limit: ");
            outline1074.append(getLimit());
            outline107.append(outline1074.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public DescribeLogGroupsRequest withLimit(Integer num) {
        this.limit = num;
        return this;
    }

    public DescribeLogGroupsRequest withLogGroupNamePrefix(String str) {
        this.logGroupNamePrefix = str;
        return this;
    }

    public DescribeLogGroupsRequest withNextToken(String str) {
        this.nextToken = str;
        return this;
    }
}
