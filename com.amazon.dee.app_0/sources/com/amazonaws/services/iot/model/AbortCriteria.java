package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class AbortCriteria implements Serializable {
    private String action;
    private String failureType;
    private Integer minNumberOfExecutedThings;
    private Double thresholdPercentage;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof AbortCriteria)) {
            return false;
        }
        AbortCriteria abortCriteria = (AbortCriteria) obj;
        if ((abortCriteria.getFailureType() == null) ^ (getFailureType() == null)) {
            return false;
        }
        if (abortCriteria.getFailureType() != null && !abortCriteria.getFailureType().equals(getFailureType())) {
            return false;
        }
        if ((abortCriteria.getAction() == null) ^ (getAction() == null)) {
            return false;
        }
        if (abortCriteria.getAction() != null && !abortCriteria.getAction().equals(getAction())) {
            return false;
        }
        if ((abortCriteria.getThresholdPercentage() == null) ^ (getThresholdPercentage() == null)) {
            return false;
        }
        if (abortCriteria.getThresholdPercentage() != null && !abortCriteria.getThresholdPercentage().equals(getThresholdPercentage())) {
            return false;
        }
        if ((abortCriteria.getMinNumberOfExecutedThings() == null) ^ (getMinNumberOfExecutedThings() == null)) {
            return false;
        }
        return abortCriteria.getMinNumberOfExecutedThings() == null || abortCriteria.getMinNumberOfExecutedThings().equals(getMinNumberOfExecutedThings());
    }

    public String getAction() {
        return this.action;
    }

    public String getFailureType() {
        return this.failureType;
    }

    public Integer getMinNumberOfExecutedThings() {
        return this.minNumberOfExecutedThings;
    }

    public Double getThresholdPercentage() {
        return this.thresholdPercentage;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((((getFailureType() == null ? 0 : getFailureType().hashCode()) + 31) * 31) + (getAction() == null ? 0 : getAction().hashCode())) * 31) + (getThresholdPercentage() == null ? 0 : getThresholdPercentage().hashCode())) * 31;
        if (getMinNumberOfExecutedThings() != null) {
            i = getMinNumberOfExecutedThings().hashCode();
        }
        return hashCode + i;
    }

    public void setAction(String str) {
        this.action = str;
    }

    public void setFailureType(String str) {
        this.failureType = str;
    }

    public void setMinNumberOfExecutedThings(Integer num) {
        this.minNumberOfExecutedThings = num;
    }

    public void setThresholdPercentage(Double d) {
        this.thresholdPercentage = d;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getFailureType() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("failureType: ");
            outline1072.append(getFailureType());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getAction() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("action: ");
            outline1073.append(getAction());
            outline1073.append(",");
            outline107.append(outline1073.toString());
        }
        if (getThresholdPercentage() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("thresholdPercentage: ");
            outline1074.append(getThresholdPercentage());
            outline1074.append(",");
            outline107.append(outline1074.toString());
        }
        if (getMinNumberOfExecutedThings() != null) {
            StringBuilder outline1075 = GeneratedOutlineSupport1.outline107("minNumberOfExecutedThings: ");
            outline1075.append(getMinNumberOfExecutedThings());
            outline107.append(outline1075.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public AbortCriteria withAction(String str) {
        this.action = str;
        return this;
    }

    public AbortCriteria withFailureType(String str) {
        this.failureType = str;
        return this;
    }

    public AbortCriteria withMinNumberOfExecutedThings(Integer num) {
        this.minNumberOfExecutedThings = num;
        return this;
    }

    public AbortCriteria withThresholdPercentage(Double d) {
        this.thresholdPercentage = d;
        return this;
    }

    public void setAction(AbortAction abortAction) {
        this.action = abortAction.toString();
    }

    public void setFailureType(JobExecutionFailureType jobExecutionFailureType) {
        this.failureType = jobExecutionFailureType.toString();
    }

    public AbortCriteria withAction(AbortAction abortAction) {
        this.action = abortAction.toString();
        return this;
    }

    public AbortCriteria withFailureType(JobExecutionFailureType jobExecutionFailureType) {
        this.failureType = jobExecutionFailureType.toString();
        return this;
    }
}
