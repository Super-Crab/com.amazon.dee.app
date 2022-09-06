package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class GetRegistrationCodeResult implements Serializable {
    private String registrationCode;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof GetRegistrationCodeResult)) {
            return false;
        }
        GetRegistrationCodeResult getRegistrationCodeResult = (GetRegistrationCodeResult) obj;
        if ((getRegistrationCodeResult.getRegistrationCode() == null) ^ (getRegistrationCode() == null)) {
            return false;
        }
        return getRegistrationCodeResult.getRegistrationCode() == null || getRegistrationCodeResult.getRegistrationCode().equals(getRegistrationCode());
    }

    public String getRegistrationCode() {
        return this.registrationCode;
    }

    public int hashCode() {
        return 31 + (getRegistrationCode() == null ? 0 : getRegistrationCode().hashCode());
    }

    public void setRegistrationCode(String str) {
        this.registrationCode = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getRegistrationCode() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("registrationCode: ");
            outline1072.append(getRegistrationCode());
            outline107.append(outline1072.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public GetRegistrationCodeResult withRegistrationCode(String str) {
        this.registrationCode = str;
        return this;
    }
}
