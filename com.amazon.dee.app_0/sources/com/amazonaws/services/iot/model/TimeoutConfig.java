package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class TimeoutConfig implements Serializable {
    private Long inProgressTimeoutInMinutes;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof TimeoutConfig)) {
            return false;
        }
        TimeoutConfig timeoutConfig = (TimeoutConfig) obj;
        if ((timeoutConfig.getInProgressTimeoutInMinutes() == null) ^ (getInProgressTimeoutInMinutes() == null)) {
            return false;
        }
        return timeoutConfig.getInProgressTimeoutInMinutes() == null || timeoutConfig.getInProgressTimeoutInMinutes().equals(getInProgressTimeoutInMinutes());
    }

    public Long getInProgressTimeoutInMinutes() {
        return this.inProgressTimeoutInMinutes;
    }

    public int hashCode() {
        return 31 + (getInProgressTimeoutInMinutes() == null ? 0 : getInProgressTimeoutInMinutes().hashCode());
    }

    public void setInProgressTimeoutInMinutes(Long l) {
        this.inProgressTimeoutInMinutes = l;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getInProgressTimeoutInMinutes() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("inProgressTimeoutInMinutes: ");
            outline1072.append(getInProgressTimeoutInMinutes());
            outline107.append(outline1072.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public TimeoutConfig withInProgressTimeoutInMinutes(Long l) {
        this.inProgressTimeoutInMinutes = l;
        return this;
    }
}
