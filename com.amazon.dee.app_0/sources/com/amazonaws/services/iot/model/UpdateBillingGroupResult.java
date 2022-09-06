package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class UpdateBillingGroupResult implements Serializable {
    private Long version;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof UpdateBillingGroupResult)) {
            return false;
        }
        UpdateBillingGroupResult updateBillingGroupResult = (UpdateBillingGroupResult) obj;
        if ((updateBillingGroupResult.getVersion() == null) ^ (getVersion() == null)) {
            return false;
        }
        return updateBillingGroupResult.getVersion() == null || updateBillingGroupResult.getVersion().equals(getVersion());
    }

    public Long getVersion() {
        return this.version;
    }

    public int hashCode() {
        return 31 + (getVersion() == null ? 0 : getVersion().hashCode());
    }

    public void setVersion(Long l) {
        this.version = l;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getVersion() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("version: ");
            outline1072.append(getVersion());
            outline107.append(outline1072.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public UpdateBillingGroupResult withVersion(Long l) {
        this.version = l;
        return this;
    }
}
