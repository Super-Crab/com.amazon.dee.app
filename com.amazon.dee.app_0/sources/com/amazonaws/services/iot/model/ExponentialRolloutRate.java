package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class ExponentialRolloutRate implements Serializable {
    private Integer baseRatePerMinute;
    private Double incrementFactor;
    private RateIncreaseCriteria rateIncreaseCriteria;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ExponentialRolloutRate)) {
            return false;
        }
        ExponentialRolloutRate exponentialRolloutRate = (ExponentialRolloutRate) obj;
        if ((exponentialRolloutRate.getBaseRatePerMinute() == null) ^ (getBaseRatePerMinute() == null)) {
            return false;
        }
        if (exponentialRolloutRate.getBaseRatePerMinute() != null && !exponentialRolloutRate.getBaseRatePerMinute().equals(getBaseRatePerMinute())) {
            return false;
        }
        if ((exponentialRolloutRate.getIncrementFactor() == null) ^ (getIncrementFactor() == null)) {
            return false;
        }
        if (exponentialRolloutRate.getIncrementFactor() != null && !exponentialRolloutRate.getIncrementFactor().equals(getIncrementFactor())) {
            return false;
        }
        if ((exponentialRolloutRate.getRateIncreaseCriteria() == null) ^ (getRateIncreaseCriteria() == null)) {
            return false;
        }
        return exponentialRolloutRate.getRateIncreaseCriteria() == null || exponentialRolloutRate.getRateIncreaseCriteria().equals(getRateIncreaseCriteria());
    }

    public Integer getBaseRatePerMinute() {
        return this.baseRatePerMinute;
    }

    public Double getIncrementFactor() {
        return this.incrementFactor;
    }

    public RateIncreaseCriteria getRateIncreaseCriteria() {
        return this.rateIncreaseCriteria;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((getBaseRatePerMinute() == null ? 0 : getBaseRatePerMinute().hashCode()) + 31) * 31) + (getIncrementFactor() == null ? 0 : getIncrementFactor().hashCode())) * 31;
        if (getRateIncreaseCriteria() != null) {
            i = getRateIncreaseCriteria().hashCode();
        }
        return hashCode + i;
    }

    public void setBaseRatePerMinute(Integer num) {
        this.baseRatePerMinute = num;
    }

    public void setIncrementFactor(Double d) {
        this.incrementFactor = d;
    }

    public void setRateIncreaseCriteria(RateIncreaseCriteria rateIncreaseCriteria) {
        this.rateIncreaseCriteria = rateIncreaseCriteria;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getBaseRatePerMinute() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("baseRatePerMinute: ");
            outline1072.append(getBaseRatePerMinute());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getIncrementFactor() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("incrementFactor: ");
            outline1073.append(getIncrementFactor());
            outline1073.append(",");
            outline107.append(outline1073.toString());
        }
        if (getRateIncreaseCriteria() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("rateIncreaseCriteria: ");
            outline1074.append(getRateIncreaseCriteria());
            outline107.append(outline1074.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public ExponentialRolloutRate withBaseRatePerMinute(Integer num) {
        this.baseRatePerMinute = num;
        return this;
    }

    public ExponentialRolloutRate withIncrementFactor(Double d) {
        this.incrementFactor = d;
        return this;
    }

    public ExponentialRolloutRate withRateIncreaseCriteria(RateIncreaseCriteria rateIncreaseCriteria) {
        this.rateIncreaseCriteria = rateIncreaseCriteria;
        return this;
    }
}
