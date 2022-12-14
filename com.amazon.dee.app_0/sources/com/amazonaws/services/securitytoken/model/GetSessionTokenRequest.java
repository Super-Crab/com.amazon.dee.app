package com.amazonaws.services.securitytoken.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazonaws.AmazonWebServiceRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class GetSessionTokenRequest extends AmazonWebServiceRequest implements Serializable {
    private Integer durationSeconds;
    private String serialNumber;
    private String tokenCode;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof GetSessionTokenRequest)) {
            return false;
        }
        GetSessionTokenRequest getSessionTokenRequest = (GetSessionTokenRequest) obj;
        if ((getSessionTokenRequest.getDurationSeconds() == null) ^ (getDurationSeconds() == null)) {
            return false;
        }
        if (getSessionTokenRequest.getDurationSeconds() != null && !getSessionTokenRequest.getDurationSeconds().equals(getDurationSeconds())) {
            return false;
        }
        if ((getSessionTokenRequest.getSerialNumber() == null) ^ (getSerialNumber() == null)) {
            return false;
        }
        if (getSessionTokenRequest.getSerialNumber() != null && !getSessionTokenRequest.getSerialNumber().equals(getSerialNumber())) {
            return false;
        }
        if ((getSessionTokenRequest.getTokenCode() == null) ^ (getTokenCode() == null)) {
            return false;
        }
        return getSessionTokenRequest.getTokenCode() == null || getSessionTokenRequest.getTokenCode().equals(getTokenCode());
    }

    public Integer getDurationSeconds() {
        return this.durationSeconds;
    }

    public String getSerialNumber() {
        return this.serialNumber;
    }

    public String getTokenCode() {
        return this.tokenCode;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((getDurationSeconds() == null ? 0 : getDurationSeconds().hashCode()) + 31) * 31) + (getSerialNumber() == null ? 0 : getSerialNumber().hashCode())) * 31;
        if (getTokenCode() != null) {
            i = getTokenCode().hashCode();
        }
        return hashCode + i;
    }

    public void setDurationSeconds(Integer num) {
        this.durationSeconds = num;
    }

    public void setSerialNumber(String str) {
        this.serialNumber = str;
    }

    public void setTokenCode(String str) {
        this.tokenCode = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getDurationSeconds() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("DurationSeconds: ");
            outline1072.append(getDurationSeconds());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getSerialNumber() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("SerialNumber: ");
            outline1073.append(getSerialNumber());
            outline1073.append(",");
            outline107.append(outline1073.toString());
        }
        if (getTokenCode() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("TokenCode: ");
            outline1074.append(getTokenCode());
            outline107.append(outline1074.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public GetSessionTokenRequest withDurationSeconds(Integer num) {
        this.durationSeconds = num;
        return this;
    }

    public GetSessionTokenRequest withSerialNumber(String str) {
        this.serialNumber = str;
        return this;
    }

    public GetSessionTokenRequest withTokenCode(String str) {
        this.tokenCode = str;
        return this;
    }
}
