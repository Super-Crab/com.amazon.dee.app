package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class BehaviorCriteria implements Serializable {
    private String comparisonOperator;
    private Integer durationSeconds;
    private MetricValue value;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof BehaviorCriteria)) {
            return false;
        }
        BehaviorCriteria behaviorCriteria = (BehaviorCriteria) obj;
        if ((behaviorCriteria.getComparisonOperator() == null) ^ (getComparisonOperator() == null)) {
            return false;
        }
        if (behaviorCriteria.getComparisonOperator() != null && !behaviorCriteria.getComparisonOperator().equals(getComparisonOperator())) {
            return false;
        }
        if ((behaviorCriteria.getValue() == null) ^ (getValue() == null)) {
            return false;
        }
        if (behaviorCriteria.getValue() != null && !behaviorCriteria.getValue().equals(getValue())) {
            return false;
        }
        if ((behaviorCriteria.getDurationSeconds() == null) ^ (getDurationSeconds() == null)) {
            return false;
        }
        return behaviorCriteria.getDurationSeconds() == null || behaviorCriteria.getDurationSeconds().equals(getDurationSeconds());
    }

    public String getComparisonOperator() {
        return this.comparisonOperator;
    }

    public Integer getDurationSeconds() {
        return this.durationSeconds;
    }

    public MetricValue getValue() {
        return this.value;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((getComparisonOperator() == null ? 0 : getComparisonOperator().hashCode()) + 31) * 31) + (getValue() == null ? 0 : getValue().hashCode())) * 31;
        if (getDurationSeconds() != null) {
            i = getDurationSeconds().hashCode();
        }
        return hashCode + i;
    }

    public void setComparisonOperator(String str) {
        this.comparisonOperator = str;
    }

    public void setDurationSeconds(Integer num) {
        this.durationSeconds = num;
    }

    public void setValue(MetricValue metricValue) {
        this.value = metricValue;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getComparisonOperator() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("comparisonOperator: ");
            outline1072.append(getComparisonOperator());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getValue() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("value: ");
            outline1073.append(getValue());
            outline1073.append(",");
            outline107.append(outline1073.toString());
        }
        if (getDurationSeconds() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("durationSeconds: ");
            outline1074.append(getDurationSeconds());
            outline107.append(outline1074.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public BehaviorCriteria withComparisonOperator(String str) {
        this.comparisonOperator = str;
        return this;
    }

    public BehaviorCriteria withDurationSeconds(Integer num) {
        this.durationSeconds = num;
        return this;
    }

    public BehaviorCriteria withValue(MetricValue metricValue) {
        this.value = metricValue;
        return this;
    }

    public void setComparisonOperator(ComparisonOperator comparisonOperator) {
        this.comparisonOperator = comparisonOperator.toString();
    }

    public BehaviorCriteria withComparisonOperator(ComparisonOperator comparisonOperator) {
        this.comparisonOperator = comparisonOperator.toString();
        return this;
    }
}
