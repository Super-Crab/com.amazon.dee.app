package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class AwsJobExecutionsRolloutConfig implements Serializable {
    private Integer maximumPerMinute;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof AwsJobExecutionsRolloutConfig)) {
            return false;
        }
        AwsJobExecutionsRolloutConfig awsJobExecutionsRolloutConfig = (AwsJobExecutionsRolloutConfig) obj;
        if ((awsJobExecutionsRolloutConfig.getMaximumPerMinute() == null) ^ (getMaximumPerMinute() == null)) {
            return false;
        }
        return awsJobExecutionsRolloutConfig.getMaximumPerMinute() == null || awsJobExecutionsRolloutConfig.getMaximumPerMinute().equals(getMaximumPerMinute());
    }

    public Integer getMaximumPerMinute() {
        return this.maximumPerMinute;
    }

    public int hashCode() {
        return 31 + (getMaximumPerMinute() == null ? 0 : getMaximumPerMinute().hashCode());
    }

    public void setMaximumPerMinute(Integer num) {
        this.maximumPerMinute = num;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getMaximumPerMinute() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("maximumPerMinute: ");
            outline1072.append(getMaximumPerMinute());
            outline107.append(outline1072.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public AwsJobExecutionsRolloutConfig withMaximumPerMinute(Integer num) {
        this.maximumPerMinute = num;
        return this;
    }
}
