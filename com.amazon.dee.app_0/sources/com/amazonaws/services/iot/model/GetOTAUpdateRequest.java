package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazonaws.AmazonWebServiceRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class GetOTAUpdateRequest extends AmazonWebServiceRequest implements Serializable {
    private String otaUpdateId;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof GetOTAUpdateRequest)) {
            return false;
        }
        GetOTAUpdateRequest getOTAUpdateRequest = (GetOTAUpdateRequest) obj;
        if ((getOTAUpdateRequest.getOtaUpdateId() == null) ^ (getOtaUpdateId() == null)) {
            return false;
        }
        return getOTAUpdateRequest.getOtaUpdateId() == null || getOTAUpdateRequest.getOtaUpdateId().equals(getOtaUpdateId());
    }

    public String getOtaUpdateId() {
        return this.otaUpdateId;
    }

    public int hashCode() {
        return 31 + (getOtaUpdateId() == null ? 0 : getOtaUpdateId().hashCode());
    }

    public void setOtaUpdateId(String str) {
        this.otaUpdateId = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getOtaUpdateId() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("otaUpdateId: ");
            outline1072.append(getOtaUpdateId());
            outline107.append(outline1072.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public GetOTAUpdateRequest withOtaUpdateId(String str) {
        this.otaUpdateId = str;
        return this;
    }
}
