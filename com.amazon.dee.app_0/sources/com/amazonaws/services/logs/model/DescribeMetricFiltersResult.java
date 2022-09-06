package com.amazonaws.services.logs.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
/* loaded from: classes13.dex */
public class DescribeMetricFiltersResult implements Serializable {
    private List<MetricFilter> metricFilters;
    private String nextToken;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof DescribeMetricFiltersResult)) {
            return false;
        }
        DescribeMetricFiltersResult describeMetricFiltersResult = (DescribeMetricFiltersResult) obj;
        if ((describeMetricFiltersResult.getMetricFilters() == null) ^ (getMetricFilters() == null)) {
            return false;
        }
        if (describeMetricFiltersResult.getMetricFilters() != null && !describeMetricFiltersResult.getMetricFilters().equals(getMetricFilters())) {
            return false;
        }
        if ((describeMetricFiltersResult.getNextToken() == null) ^ (getNextToken() == null)) {
            return false;
        }
        return describeMetricFiltersResult.getNextToken() == null || describeMetricFiltersResult.getNextToken().equals(getNextToken());
    }

    public List<MetricFilter> getMetricFilters() {
        return this.metricFilters;
    }

    public String getNextToken() {
        return this.nextToken;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((getMetricFilters() == null ? 0 : getMetricFilters().hashCode()) + 31) * 31;
        if (getNextToken() != null) {
            i = getNextToken().hashCode();
        }
        return hashCode + i;
    }

    public void setMetricFilters(Collection<MetricFilter> collection) {
        if (collection == null) {
            this.metricFilters = null;
        } else {
            this.metricFilters = new ArrayList(collection);
        }
    }

    public void setNextToken(String str) {
        this.nextToken = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getMetricFilters() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("metricFilters: ");
            outline1072.append(getMetricFilters());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getNextToken() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("nextToken: ");
            outline1073.append(getNextToken());
            outline107.append(outline1073.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public DescribeMetricFiltersResult withMetricFilters(MetricFilter... metricFilterArr) {
        if (getMetricFilters() == null) {
            this.metricFilters = new ArrayList(metricFilterArr.length);
        }
        for (MetricFilter metricFilter : metricFilterArr) {
            this.metricFilters.add(metricFilter);
        }
        return this;
    }

    public DescribeMetricFiltersResult withNextToken(String str) {
        this.nextToken = str;
        return this;
    }

    public DescribeMetricFiltersResult withMetricFilters(Collection<MetricFilter> collection) {
        setMetricFilters(collection);
        return this;
    }
}
