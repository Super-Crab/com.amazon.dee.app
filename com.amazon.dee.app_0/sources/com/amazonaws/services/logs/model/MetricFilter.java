package com.amazonaws.services.logs.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
/* loaded from: classes13.dex */
public class MetricFilter implements Serializable {
    private Long creationTime;
    private String filterName;
    private String filterPattern;
    private String logGroupName;
    private List<MetricTransformation> metricTransformations;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof MetricFilter)) {
            return false;
        }
        MetricFilter metricFilter = (MetricFilter) obj;
        if ((metricFilter.getFilterName() == null) ^ (getFilterName() == null)) {
            return false;
        }
        if (metricFilter.getFilterName() != null && !metricFilter.getFilterName().equals(getFilterName())) {
            return false;
        }
        if ((metricFilter.getFilterPattern() == null) ^ (getFilterPattern() == null)) {
            return false;
        }
        if (metricFilter.getFilterPattern() != null && !metricFilter.getFilterPattern().equals(getFilterPattern())) {
            return false;
        }
        if ((metricFilter.getMetricTransformations() == null) ^ (getMetricTransformations() == null)) {
            return false;
        }
        if (metricFilter.getMetricTransformations() != null && !metricFilter.getMetricTransformations().equals(getMetricTransformations())) {
            return false;
        }
        if ((metricFilter.getCreationTime() == null) ^ (getCreationTime() == null)) {
            return false;
        }
        if (metricFilter.getCreationTime() != null && !metricFilter.getCreationTime().equals(getCreationTime())) {
            return false;
        }
        if ((metricFilter.getLogGroupName() == null) ^ (getLogGroupName() == null)) {
            return false;
        }
        return metricFilter.getLogGroupName() == null || metricFilter.getLogGroupName().equals(getLogGroupName());
    }

    public Long getCreationTime() {
        return this.creationTime;
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
        int hashCode = ((((((((getFilterName() == null ? 0 : getFilterName().hashCode()) + 31) * 31) + (getFilterPattern() == null ? 0 : getFilterPattern().hashCode())) * 31) + (getMetricTransformations() == null ? 0 : getMetricTransformations().hashCode())) * 31) + (getCreationTime() == null ? 0 : getCreationTime().hashCode())) * 31;
        if (getLogGroupName() != null) {
            i = getLogGroupName().hashCode();
        }
        return hashCode + i;
    }

    public void setCreationTime(Long l) {
        this.creationTime = l;
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
        if (getFilterName() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("filterName: ");
            outline1072.append(getFilterName());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getFilterPattern() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("filterPattern: ");
            outline1073.append(getFilterPattern());
            outline1073.append(",");
            outline107.append(outline1073.toString());
        }
        if (getMetricTransformations() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("metricTransformations: ");
            outline1074.append(getMetricTransformations());
            outline1074.append(",");
            outline107.append(outline1074.toString());
        }
        if (getCreationTime() != null) {
            StringBuilder outline1075 = GeneratedOutlineSupport1.outline107("creationTime: ");
            outline1075.append(getCreationTime());
            outline1075.append(",");
            outline107.append(outline1075.toString());
        }
        if (getLogGroupName() != null) {
            StringBuilder outline1076 = GeneratedOutlineSupport1.outline107("logGroupName: ");
            outline1076.append(getLogGroupName());
            outline107.append(outline1076.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public MetricFilter withCreationTime(Long l) {
        this.creationTime = l;
        return this;
    }

    public MetricFilter withFilterName(String str) {
        this.filterName = str;
        return this;
    }

    public MetricFilter withFilterPattern(String str) {
        this.filterPattern = str;
        return this;
    }

    public MetricFilter withLogGroupName(String str) {
        this.logGroupName = str;
        return this;
    }

    public MetricFilter withMetricTransformations(MetricTransformation... metricTransformationArr) {
        if (getMetricTransformations() == null) {
            this.metricTransformations = new ArrayList(metricTransformationArr.length);
        }
        for (MetricTransformation metricTransformation : metricTransformationArr) {
            this.metricTransformations.add(metricTransformation);
        }
        return this;
    }

    public MetricFilter withMetricTransformations(Collection<MetricTransformation> collection) {
        setMetricTransformations(collection);
        return this;
    }
}
