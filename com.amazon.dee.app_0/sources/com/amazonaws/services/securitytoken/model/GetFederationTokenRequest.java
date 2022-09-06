package com.amazonaws.services.securitytoken.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazonaws.AmazonWebServiceRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class GetFederationTokenRequest extends AmazonWebServiceRequest implements Serializable {
    private Integer durationSeconds;
    private String name;
    private String policy;

    public GetFederationTokenRequest() {
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof GetFederationTokenRequest)) {
            return false;
        }
        GetFederationTokenRequest getFederationTokenRequest = (GetFederationTokenRequest) obj;
        if ((getFederationTokenRequest.getName() == null) ^ (getName() == null)) {
            return false;
        }
        if (getFederationTokenRequest.getName() != null && !getFederationTokenRequest.getName().equals(getName())) {
            return false;
        }
        if ((getFederationTokenRequest.getPolicy() == null) ^ (getPolicy() == null)) {
            return false;
        }
        if (getFederationTokenRequest.getPolicy() != null && !getFederationTokenRequest.getPolicy().equals(getPolicy())) {
            return false;
        }
        if ((getFederationTokenRequest.getDurationSeconds() == null) ^ (getDurationSeconds() == null)) {
            return false;
        }
        return getFederationTokenRequest.getDurationSeconds() == null || getFederationTokenRequest.getDurationSeconds().equals(getDurationSeconds());
    }

    public Integer getDurationSeconds() {
        return this.durationSeconds;
    }

    public String getName() {
        return this.name;
    }

    public String getPolicy() {
        return this.policy;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((getName() == null ? 0 : getName().hashCode()) + 31) * 31) + (getPolicy() == null ? 0 : getPolicy().hashCode())) * 31;
        if (getDurationSeconds() != null) {
            i = getDurationSeconds().hashCode();
        }
        return hashCode + i;
    }

    public void setDurationSeconds(Integer num) {
        this.durationSeconds = num;
    }

    public void setName(String str) {
        this.name = str;
    }

    public void setPolicy(String str) {
        this.policy = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getName() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Name: ");
            outline1072.append(getName());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getPolicy() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("Policy: ");
            outline1073.append(getPolicy());
            outline1073.append(",");
            outline107.append(outline1073.toString());
        }
        if (getDurationSeconds() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("DurationSeconds: ");
            outline1074.append(getDurationSeconds());
            outline107.append(outline1074.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public GetFederationTokenRequest withDurationSeconds(Integer num) {
        this.durationSeconds = num;
        return this;
    }

    public GetFederationTokenRequest withName(String str) {
        this.name = str;
        return this;
    }

    public GetFederationTokenRequest withPolicy(String str) {
        this.policy = str;
        return this;
    }

    public GetFederationTokenRequest(String str) {
        setName(str);
    }
}
