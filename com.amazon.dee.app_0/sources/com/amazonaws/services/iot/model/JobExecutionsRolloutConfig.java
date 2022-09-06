package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class JobExecutionsRolloutConfig implements Serializable {
    private ExponentialRolloutRate exponentialRate;
    private Integer maximumPerMinute;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof JobExecutionsRolloutConfig)) {
            return false;
        }
        JobExecutionsRolloutConfig jobExecutionsRolloutConfig = (JobExecutionsRolloutConfig) obj;
        if ((jobExecutionsRolloutConfig.getMaximumPerMinute() == null) ^ (getMaximumPerMinute() == null)) {
            return false;
        }
        if (jobExecutionsRolloutConfig.getMaximumPerMinute() != null && !jobExecutionsRolloutConfig.getMaximumPerMinute().equals(getMaximumPerMinute())) {
            return false;
        }
        if ((jobExecutionsRolloutConfig.getExponentialRate() == null) ^ (getExponentialRate() == null)) {
            return false;
        }
        return jobExecutionsRolloutConfig.getExponentialRate() == null || jobExecutionsRolloutConfig.getExponentialRate().equals(getExponentialRate());
    }

    public ExponentialRolloutRate getExponentialRate() {
        return this.exponentialRate;
    }

    public Integer getMaximumPerMinute() {
        return this.maximumPerMinute;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((getMaximumPerMinute() == null ? 0 : getMaximumPerMinute().hashCode()) + 31) * 31;
        if (getExponentialRate() != null) {
            i = getExponentialRate().hashCode();
        }
        return hashCode + i;
    }

    public void setExponentialRate(ExponentialRolloutRate exponentialRolloutRate) {
        this.exponentialRate = exponentialRolloutRate;
    }

    public void setMaximumPerMinute(Integer num) {
        this.maximumPerMinute = num;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getMaximumPerMinute() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("maximumPerMinute: ");
            outline1072.append(getMaximumPerMinute());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getExponentialRate() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("exponentialRate: ");
            outline1073.append(getExponentialRate());
            outline107.append(outline1073.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public JobExecutionsRolloutConfig withExponentialRate(ExponentialRolloutRate exponentialRolloutRate) {
        this.exponentialRate = exponentialRolloutRate;
        return this;
    }

    public JobExecutionsRolloutConfig withMaximumPerMinute(Integer num) {
        this.maximumPerMinute = num;
        return this;
    }
}
