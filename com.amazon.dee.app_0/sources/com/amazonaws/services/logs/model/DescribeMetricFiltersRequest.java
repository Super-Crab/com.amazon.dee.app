package com.amazonaws.services.logs.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazonaws.AmazonWebServiceRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class DescribeMetricFiltersRequest extends AmazonWebServiceRequest implements Serializable {
    private String filterNamePrefix;
    private Integer limit;
    private String logGroupName;
    private String metricName;
    private String metricNamespace;
    private String nextToken;

    public DescribeMetricFiltersRequest() {
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof DescribeMetricFiltersRequest)) {
            return false;
        }
        DescribeMetricFiltersRequest describeMetricFiltersRequest = (DescribeMetricFiltersRequest) obj;
        if ((describeMetricFiltersRequest.getLogGroupName() == null) ^ (getLogGroupName() == null)) {
            return false;
        }
        if (describeMetricFiltersRequest.getLogGroupName() != null && !describeMetricFiltersRequest.getLogGroupName().equals(getLogGroupName())) {
            return false;
        }
        if ((describeMetricFiltersRequest.getFilterNamePrefix() == null) ^ (getFilterNamePrefix() == null)) {
            return false;
        }
        if (describeMetricFiltersRequest.getFilterNamePrefix() != null && !describeMetricFiltersRequest.getFilterNamePrefix().equals(getFilterNamePrefix())) {
            return false;
        }
        if ((describeMetricFiltersRequest.getNextToken() == null) ^ (getNextToken() == null)) {
            return false;
        }
        if (describeMetricFiltersRequest.getNextToken() != null && !describeMetricFiltersRequest.getNextToken().equals(getNextToken())) {
            return false;
        }
        if ((describeMetricFiltersRequest.getLimit() == null) ^ (getLimit() == null)) {
            return false;
        }
        if (describeMetricFiltersRequest.getLimit() != null && !describeMetricFiltersRequest.getLimit().equals(getLimit())) {
            return false;
        }
        if ((describeMetricFiltersRequest.getMetricName() == null) ^ (getMetricName() == null)) {
            return false;
        }
        if (describeMetricFiltersRequest.getMetricName() != null && !describeMetricFiltersRequest.getMetricName().equals(getMetricName())) {
            return false;
        }
        if ((describeMetricFiltersRequest.getMetricNamespace() == null) ^ (getMetricNamespace() == null)) {
            return false;
        }
        return describeMetricFiltersRequest.getMetricNamespace() == null || describeMetricFiltersRequest.getMetricNamespace().equals(getMetricNamespace());
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

    public String getMetricName() {
        return this.metricName;
    }

    public String getMetricNamespace() {
        return this.metricNamespace;
    }

    public String getNextToken() {
        return this.nextToken;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((((((((getLogGroupName() == null ? 0 : getLogGroupName().hashCode()) + 31) * 31) + (getFilterNamePrefix() == null ? 0 : getFilterNamePrefix().hashCode())) * 31) + (getNextToken() == null ? 0 : getNextToken().hashCode())) * 31) + (getLimit() == null ? 0 : getLimit().hashCode())) * 31) + (getMetricName() == null ? 0 : getMetricName().hashCode())) * 31;
        if (getMetricNamespace() != null) {
            i = getMetricNamespace().hashCode();
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

    public void setMetricName(String str) {
        this.metricName = str;
    }

    public void setMetricNamespace(String str) {
        this.metricNamespace = str;
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
            outline1075.append(",");
            outline107.append(outline1075.toString());
        }
        if (getMetricName() != null) {
            StringBuilder outline1076 = GeneratedOutlineSupport1.outline107("metricName: ");
            outline1076.append(getMetricName());
            outline1076.append(",");
            outline107.append(outline1076.toString());
        }
        if (getMetricNamespace() != null) {
            StringBuilder outline1077 = GeneratedOutlineSupport1.outline107("metricNamespace: ");
            outline1077.append(getMetricNamespace());
            outline107.append(outline1077.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public DescribeMetricFiltersRequest withFilterNamePrefix(String str) {
        this.filterNamePrefix = str;
        return this;
    }

    public DescribeMetricFiltersRequest withLimit(Integer num) {
        this.limit = num;
        return this;
    }

    public DescribeMetricFiltersRequest withLogGroupName(String str) {
        this.logGroupName = str;
        return this;
    }

    public DescribeMetricFiltersRequest withMetricName(String str) {
        this.metricName = str;
        return this;
    }

    public DescribeMetricFiltersRequest withMetricNamespace(String str) {
        this.metricNamespace = str;
        return this;
    }

    public DescribeMetricFiltersRequest withNextToken(String str) {
        this.nextToken = str;
        return this;
    }

    public DescribeMetricFiltersRequest(String str) {
        setLogGroupName(str);
    }
}
