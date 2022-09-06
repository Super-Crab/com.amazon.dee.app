package com.amazonaws.services.logs.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class MetricTransformation implements Serializable {
    private Double defaultValue;
    private String metricName;
    private String metricNamespace;
    private String metricValue;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof MetricTransformation)) {
            return false;
        }
        MetricTransformation metricTransformation = (MetricTransformation) obj;
        if ((metricTransformation.getMetricName() == null) ^ (getMetricName() == null)) {
            return false;
        }
        if (metricTransformation.getMetricName() != null && !metricTransformation.getMetricName().equals(getMetricName())) {
            return false;
        }
        if ((metricTransformation.getMetricNamespace() == null) ^ (getMetricNamespace() == null)) {
            return false;
        }
        if (metricTransformation.getMetricNamespace() != null && !metricTransformation.getMetricNamespace().equals(getMetricNamespace())) {
            return false;
        }
        if ((metricTransformation.getMetricValue() == null) ^ (getMetricValue() == null)) {
            return false;
        }
        if (metricTransformation.getMetricValue() != null && !metricTransformation.getMetricValue().equals(getMetricValue())) {
            return false;
        }
        if ((metricTransformation.getDefaultValue() == null) ^ (getDefaultValue() == null)) {
            return false;
        }
        return metricTransformation.getDefaultValue() == null || metricTransformation.getDefaultValue().equals(getDefaultValue());
    }

    public Double getDefaultValue() {
        return this.defaultValue;
    }

    public String getMetricName() {
        return this.metricName;
    }

    public String getMetricNamespace() {
        return this.metricNamespace;
    }

    public String getMetricValue() {
        return this.metricValue;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((((getMetricName() == null ? 0 : getMetricName().hashCode()) + 31) * 31) + (getMetricNamespace() == null ? 0 : getMetricNamespace().hashCode())) * 31) + (getMetricValue() == null ? 0 : getMetricValue().hashCode())) * 31;
        if (getDefaultValue() != null) {
            i = getDefaultValue().hashCode();
        }
        return hashCode + i;
    }

    public void setDefaultValue(Double d) {
        this.defaultValue = d;
    }

    public void setMetricName(String str) {
        this.metricName = str;
    }

    public void setMetricNamespace(String str) {
        this.metricNamespace = str;
    }

    public void setMetricValue(String str) {
        this.metricValue = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getMetricName() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("metricName: ");
            outline1072.append(getMetricName());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getMetricNamespace() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("metricNamespace: ");
            outline1073.append(getMetricNamespace());
            outline1073.append(",");
            outline107.append(outline1073.toString());
        }
        if (getMetricValue() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("metricValue: ");
            outline1074.append(getMetricValue());
            outline1074.append(",");
            outline107.append(outline1074.toString());
        }
        if (getDefaultValue() != null) {
            StringBuilder outline1075 = GeneratedOutlineSupport1.outline107("defaultValue: ");
            outline1075.append(getDefaultValue());
            outline107.append(outline1075.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public MetricTransformation withDefaultValue(Double d) {
        this.defaultValue = d;
        return this;
    }

    public MetricTransformation withMetricName(String str) {
        this.metricName = str;
        return this;
    }

    public MetricTransformation withMetricNamespace(String str) {
        this.metricNamespace = str;
        return this;
    }

    public MetricTransformation withMetricValue(String str) {
        this.metricValue = str;
        return this;
    }
}
