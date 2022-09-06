package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class CloudwatchMetricAction implements Serializable {
    private String metricName;
    private String metricNamespace;
    private String metricTimestamp;
    private String metricUnit;
    private String metricValue;
    private String roleArn;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof CloudwatchMetricAction)) {
            return false;
        }
        CloudwatchMetricAction cloudwatchMetricAction = (CloudwatchMetricAction) obj;
        if ((cloudwatchMetricAction.getRoleArn() == null) ^ (getRoleArn() == null)) {
            return false;
        }
        if (cloudwatchMetricAction.getRoleArn() != null && !cloudwatchMetricAction.getRoleArn().equals(getRoleArn())) {
            return false;
        }
        if ((cloudwatchMetricAction.getMetricNamespace() == null) ^ (getMetricNamespace() == null)) {
            return false;
        }
        if (cloudwatchMetricAction.getMetricNamespace() != null && !cloudwatchMetricAction.getMetricNamespace().equals(getMetricNamespace())) {
            return false;
        }
        if ((cloudwatchMetricAction.getMetricName() == null) ^ (getMetricName() == null)) {
            return false;
        }
        if (cloudwatchMetricAction.getMetricName() != null && !cloudwatchMetricAction.getMetricName().equals(getMetricName())) {
            return false;
        }
        if ((cloudwatchMetricAction.getMetricValue() == null) ^ (getMetricValue() == null)) {
            return false;
        }
        if (cloudwatchMetricAction.getMetricValue() != null && !cloudwatchMetricAction.getMetricValue().equals(getMetricValue())) {
            return false;
        }
        if ((cloudwatchMetricAction.getMetricUnit() == null) ^ (getMetricUnit() == null)) {
            return false;
        }
        if (cloudwatchMetricAction.getMetricUnit() != null && !cloudwatchMetricAction.getMetricUnit().equals(getMetricUnit())) {
            return false;
        }
        if ((cloudwatchMetricAction.getMetricTimestamp() == null) ^ (getMetricTimestamp() == null)) {
            return false;
        }
        return cloudwatchMetricAction.getMetricTimestamp() == null || cloudwatchMetricAction.getMetricTimestamp().equals(getMetricTimestamp());
    }

    public String getMetricName() {
        return this.metricName;
    }

    public String getMetricNamespace() {
        return this.metricNamespace;
    }

    public String getMetricTimestamp() {
        return this.metricTimestamp;
    }

    public String getMetricUnit() {
        return this.metricUnit;
    }

    public String getMetricValue() {
        return this.metricValue;
    }

    public String getRoleArn() {
        return this.roleArn;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((((((((getRoleArn() == null ? 0 : getRoleArn().hashCode()) + 31) * 31) + (getMetricNamespace() == null ? 0 : getMetricNamespace().hashCode())) * 31) + (getMetricName() == null ? 0 : getMetricName().hashCode())) * 31) + (getMetricValue() == null ? 0 : getMetricValue().hashCode())) * 31) + (getMetricUnit() == null ? 0 : getMetricUnit().hashCode())) * 31;
        if (getMetricTimestamp() != null) {
            i = getMetricTimestamp().hashCode();
        }
        return hashCode + i;
    }

    public void setMetricName(String str) {
        this.metricName = str;
    }

    public void setMetricNamespace(String str) {
        this.metricNamespace = str;
    }

    public void setMetricTimestamp(String str) {
        this.metricTimestamp = str;
    }

    public void setMetricUnit(String str) {
        this.metricUnit = str;
    }

    public void setMetricValue(String str) {
        this.metricValue = str;
    }

    public void setRoleArn(String str) {
        this.roleArn = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getRoleArn() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("roleArn: ");
            outline1072.append(getRoleArn());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getMetricNamespace() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("metricNamespace: ");
            outline1073.append(getMetricNamespace());
            outline1073.append(",");
            outline107.append(outline1073.toString());
        }
        if (getMetricName() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("metricName: ");
            outline1074.append(getMetricName());
            outline1074.append(",");
            outline107.append(outline1074.toString());
        }
        if (getMetricValue() != null) {
            StringBuilder outline1075 = GeneratedOutlineSupport1.outline107("metricValue: ");
            outline1075.append(getMetricValue());
            outline1075.append(",");
            outline107.append(outline1075.toString());
        }
        if (getMetricUnit() != null) {
            StringBuilder outline1076 = GeneratedOutlineSupport1.outline107("metricUnit: ");
            outline1076.append(getMetricUnit());
            outline1076.append(",");
            outline107.append(outline1076.toString());
        }
        if (getMetricTimestamp() != null) {
            StringBuilder outline1077 = GeneratedOutlineSupport1.outline107("metricTimestamp: ");
            outline1077.append(getMetricTimestamp());
            outline107.append(outline1077.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public CloudwatchMetricAction withMetricName(String str) {
        this.metricName = str;
        return this;
    }

    public CloudwatchMetricAction withMetricNamespace(String str) {
        this.metricNamespace = str;
        return this;
    }

    public CloudwatchMetricAction withMetricTimestamp(String str) {
        this.metricTimestamp = str;
        return this;
    }

    public CloudwatchMetricAction withMetricUnit(String str) {
        this.metricUnit = str;
        return this;
    }

    public CloudwatchMetricAction withMetricValue(String str) {
        this.metricValue = str;
        return this;
    }

    public CloudwatchMetricAction withRoleArn(String str) {
        this.roleArn = str;
        return this;
    }
}
