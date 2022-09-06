package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class GetOTAUpdateResult implements Serializable {
    private OTAUpdateInfo otaUpdateInfo;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof GetOTAUpdateResult)) {
            return false;
        }
        GetOTAUpdateResult getOTAUpdateResult = (GetOTAUpdateResult) obj;
        if ((getOTAUpdateResult.getOtaUpdateInfo() == null) ^ (getOtaUpdateInfo() == null)) {
            return false;
        }
        return getOTAUpdateResult.getOtaUpdateInfo() == null || getOTAUpdateResult.getOtaUpdateInfo().equals(getOtaUpdateInfo());
    }

    public OTAUpdateInfo getOtaUpdateInfo() {
        return this.otaUpdateInfo;
    }

    public int hashCode() {
        return 31 + (getOtaUpdateInfo() == null ? 0 : getOtaUpdateInfo().hashCode());
    }

    public void setOtaUpdateInfo(OTAUpdateInfo oTAUpdateInfo) {
        this.otaUpdateInfo = oTAUpdateInfo;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getOtaUpdateInfo() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("otaUpdateInfo: ");
            outline1072.append(getOtaUpdateInfo());
            outline107.append(outline1072.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public GetOTAUpdateResult withOtaUpdateInfo(OTAUpdateInfo oTAUpdateInfo) {
        this.otaUpdateInfo = oTAUpdateInfo;
        return this;
    }
}
