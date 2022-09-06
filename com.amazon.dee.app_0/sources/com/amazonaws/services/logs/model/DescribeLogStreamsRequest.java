package com.amazonaws.services.logs.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazonaws.AmazonWebServiceRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class DescribeLogStreamsRequest extends AmazonWebServiceRequest implements Serializable {
    private Boolean descending;
    private Integer limit;
    private String logGroupName;
    private String logStreamNamePrefix;
    private String nextToken;
    private String orderBy;

    public DescribeLogStreamsRequest() {
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof DescribeLogStreamsRequest)) {
            return false;
        }
        DescribeLogStreamsRequest describeLogStreamsRequest = (DescribeLogStreamsRequest) obj;
        if ((describeLogStreamsRequest.getLogGroupName() == null) ^ (getLogGroupName() == null)) {
            return false;
        }
        if (describeLogStreamsRequest.getLogGroupName() != null && !describeLogStreamsRequest.getLogGroupName().equals(getLogGroupName())) {
            return false;
        }
        if ((describeLogStreamsRequest.getLogStreamNamePrefix() == null) ^ (getLogStreamNamePrefix() == null)) {
            return false;
        }
        if (describeLogStreamsRequest.getLogStreamNamePrefix() != null && !describeLogStreamsRequest.getLogStreamNamePrefix().equals(getLogStreamNamePrefix())) {
            return false;
        }
        if ((describeLogStreamsRequest.getOrderBy() == null) ^ (getOrderBy() == null)) {
            return false;
        }
        if (describeLogStreamsRequest.getOrderBy() != null && !describeLogStreamsRequest.getOrderBy().equals(getOrderBy())) {
            return false;
        }
        if ((describeLogStreamsRequest.getDescending() == null) ^ (getDescending() == null)) {
            return false;
        }
        if (describeLogStreamsRequest.getDescending() != null && !describeLogStreamsRequest.getDescending().equals(getDescending())) {
            return false;
        }
        if ((describeLogStreamsRequest.getNextToken() == null) ^ (getNextToken() == null)) {
            return false;
        }
        if (describeLogStreamsRequest.getNextToken() != null && !describeLogStreamsRequest.getNextToken().equals(getNextToken())) {
            return false;
        }
        if ((describeLogStreamsRequest.getLimit() == null) ^ (getLimit() == null)) {
            return false;
        }
        return describeLogStreamsRequest.getLimit() == null || describeLogStreamsRequest.getLimit().equals(getLimit());
    }

    public Boolean getDescending() {
        return this.descending;
    }

    public Integer getLimit() {
        return this.limit;
    }

    public String getLogGroupName() {
        return this.logGroupName;
    }

    public String getLogStreamNamePrefix() {
        return this.logStreamNamePrefix;
    }

    public String getNextToken() {
        return this.nextToken;
    }

    public String getOrderBy() {
        return this.orderBy;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((((((((getLogGroupName() == null ? 0 : getLogGroupName().hashCode()) + 31) * 31) + (getLogStreamNamePrefix() == null ? 0 : getLogStreamNamePrefix().hashCode())) * 31) + (getOrderBy() == null ? 0 : getOrderBy().hashCode())) * 31) + (getDescending() == null ? 0 : getDescending().hashCode())) * 31) + (getNextToken() == null ? 0 : getNextToken().hashCode())) * 31;
        if (getLimit() != null) {
            i = getLimit().hashCode();
        }
        return hashCode + i;
    }

    public Boolean isDescending() {
        return this.descending;
    }

    public void setDescending(Boolean bool) {
        this.descending = bool;
    }

    public void setLimit(Integer num) {
        this.limit = num;
    }

    public void setLogGroupName(String str) {
        this.logGroupName = str;
    }

    public void setLogStreamNamePrefix(String str) {
        this.logStreamNamePrefix = str;
    }

    public void setNextToken(String str) {
        this.nextToken = str;
    }

    public void setOrderBy(String str) {
        this.orderBy = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getLogGroupName() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("logGroupName: ");
            outline1072.append(getLogGroupName());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getLogStreamNamePrefix() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("logStreamNamePrefix: ");
            outline1073.append(getLogStreamNamePrefix());
            outline1073.append(",");
            outline107.append(outline1073.toString());
        }
        if (getOrderBy() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("orderBy: ");
            outline1074.append(getOrderBy());
            outline1074.append(",");
            outline107.append(outline1074.toString());
        }
        if (getDescending() != null) {
            StringBuilder outline1075 = GeneratedOutlineSupport1.outline107("descending: ");
            outline1075.append(getDescending());
            outline1075.append(",");
            outline107.append(outline1075.toString());
        }
        if (getNextToken() != null) {
            StringBuilder outline1076 = GeneratedOutlineSupport1.outline107("nextToken: ");
            outline1076.append(getNextToken());
            outline1076.append(",");
            outline107.append(outline1076.toString());
        }
        if (getLimit() != null) {
            StringBuilder outline1077 = GeneratedOutlineSupport1.outline107("limit: ");
            outline1077.append(getLimit());
            outline107.append(outline1077.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public DescribeLogStreamsRequest withDescending(Boolean bool) {
        this.descending = bool;
        return this;
    }

    public DescribeLogStreamsRequest withLimit(Integer num) {
        this.limit = num;
        return this;
    }

    public DescribeLogStreamsRequest withLogGroupName(String str) {
        this.logGroupName = str;
        return this;
    }

    public DescribeLogStreamsRequest withLogStreamNamePrefix(String str) {
        this.logStreamNamePrefix = str;
        return this;
    }

    public DescribeLogStreamsRequest withNextToken(String str) {
        this.nextToken = str;
        return this;
    }

    public DescribeLogStreamsRequest withOrderBy(String str) {
        this.orderBy = str;
        return this;
    }

    public DescribeLogStreamsRequest(String str) {
        setLogGroupName(str);
    }

    public void setOrderBy(OrderBy orderBy) {
        this.orderBy = orderBy.toString();
    }

    public DescribeLogStreamsRequest withOrderBy(OrderBy orderBy) {
        this.orderBy = orderBy.toString();
        return this;
    }
}
