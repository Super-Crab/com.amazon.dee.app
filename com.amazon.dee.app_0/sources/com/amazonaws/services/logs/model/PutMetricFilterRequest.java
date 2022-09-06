package com.amazonaws.services.logs.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazonaws.AmazonWebServiceRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
/* loaded from: classes13.dex */
public class PutMetricFilterRequest extends AmazonWebServiceRequest implements Serializable {
    private String filterName;
    private String filterPattern;
    private String logGroupName;
    private List<MetricTransformation> metricTransformations;

    public PutMetricFilterRequest() {
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof PutMetricFilterRequest)) {
            return false;
        }
        PutMetricFilterRequest putMetricFilterRequest = (PutMetricFilterRequest) obj;
        if ((putMetricFilterRequest.getLogGroupName() == null) ^ (getLogGroupName() == null)) {
            return false;
        }
        if (putMetricFilterRequest.getLogGroupName() != null && !putMetricFilterRequest.getLogGroupName().equals(getLogGroupName())) {
            return false;
        }
        if ((putMetricFilterRequest.getFilterName() == null) ^ (getFilterName() == null)) {
            return false;
        }
        if (putMetricFilterRequest.getFilterName() != null && !putMetricFilterRequest.getFilterName().equals(getFilterName())) {
            return false;
        }
        if ((putMetricFilterRequest.getFilterPattern() == null) ^ (getFilterPattern() == null)) {
            return false;
        }
        if (putMetricFilterRequest.getFilterPattern() != null && !putMetricFilterRequest.getFilterPattern().equals(getFilterPattern())) {
            return false;
        }
        if ((putMetricFilterRequest.getMetricTransformations() == null) ^ (getMetricTransformations() == null)) {
            return false;
        }
        return putMetricFilterRequest.getMetricTransformations() == null || putMetricFilterRequest.getMetricTransformations().equals(getMetricTransformations());
    }

    public String getFilterName() {
        return this.filterName;
    }

    public String getFilterPattern() {
        return this.filterPattern;
    }

    public String getLogGroupName() {
        return this.logGroupName;
    }

    public List<MetricTransformation> getMetricTransformations() {
        return this.metricTransformations;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((((getLogGroupName() == null ? 0 : getLogGroupName().hashCode()) + 31) * 31) + (getFilterName() == null ? 0 : getFilterName().hashCode())) * 31) + (getFilterPattern() == null ? 0 : getFilterPattern().hashCode())) * 31;
        if (getMetricTransformations() != null) {
            i = getMetricTransformations().hashCode();
        }
        return hashCode + i;
    }

    public void setFilterName(String str) {
        this.filterName = str;
    }

    public void setFilterPattern(String str) {
        this.filterPattern = str;
    }

    public void setLogGroupName(String str) {
        this.logGroupName = str;
    }

    public void setMetricTransformations(Collection<MetricTransformation> collection) {
        if (collection == null) {
            this.metricTransformations = null;
        } else {
            this.metricTransformations = new ArrayList(collection);
        }
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getLogGroupName() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("logGroupName: ");
            outline1072.append(getLogGroupName());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getFilterName() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("filterName: ");
            outline1073.append(getFilterName());
            outline1073.append(",");
            outline107.append(outline1073.toString());
        }
        if (getFilterPattern() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("filterPattern: ");
            outline1074.append(getFilterPattern());
            outline1074.append(",");
            outline107.append(outline1074.toString());
        }
        if (getMetricTransformations() != null) {
            StringBuilder outline1075 = GeneratedOutlineSupport1.outline107("metricTransformations: ");
            outline1075.append(getMetricTransformations());
            outline107.append(outline1075.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public PutMetricFilterRequest withFilterName(String str) {
        this.filterName = str;
        return this;
    }

    public PutMetricFilterRequest withFilterPattern(String str) {
        this.filterPattern = str;
        return this;
    }

    public PutMetricFilterRequest withLogGroupName(String str) {
        this.logGroupName = str;
        return this;
    }

    public PutMetricFilterRequest withMetricTransformations(MetricTransformation... metricTransformationArr) {
        if (getMetricTransformations() == null) {
            this.metricTransformations = new ArrayList(metricTransformationArr.length);
        }
        for (MetricTransformation metricTransformation : metricTransformationArr) {
            this.metricTransformations.add(metricTransformation);
        }
        return this;
    }

    public PutMetricFilterRequest(String str, String str2, String str3, List<MetricTransformation> list) {
        setLogGroupName(str);
        setFilterName(str2);
        setFilterPattern(str3);
        setMetricTransformations(list);
    }

    public PutMetricFilterRequest withMetricTransformations(Collection<MetricTransformation> collection) {
        setMetricTransformations(collection);
        return this;
    }
}
