package com.amazonaws.services.securitytoken.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class GetFederationTokenResult implements Serializable {
    private Credentials credentials;
    private FederatedUser federatedUser;
    private Integer packedPolicySize;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof GetFederationTokenResult)) {
            return false;
        }
        GetFederationTokenResult getFederationTokenResult = (GetFederationTokenResult) obj;
        if ((getFederationTokenResult.getCredentials() == null) ^ (getCredentials() == null)) {
            return false;
        }
        if (getFederationTokenResult.getCredentials() != null && !getFederationTokenResult.getCredentials().equals(getCredentials())) {
            return false;
        }
        if ((getFederationTokenResult.getFederatedUser() == null) ^ (getFederatedUser() == null)) {
            return false;
        }
        if (getFederationTokenResult.getFederatedUser() != null && !getFederationTokenResult.getFederatedUser().equals(getFederatedUser())) {
            return false;
        }
        if ((getFederationTokenResult.getPackedPolicySize() == null) ^ (getPackedPolicySize() == null)) {
            return false;
        }
        return getFederationTokenResult.getPackedPolicySize() == null || getFederationTokenResult.getPackedPolicySize().equals(getPackedPolicySize());
    }

    public Credentials getCredentials() {
        return this.credentials;
    }

    public FederatedUser getFederatedUser() {
        return this.federatedUser;
    }

    public Integer getPackedPolicySize() {
        return this.packedPolicySize;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((getCredentials() == null ? 0 : getCredentials().hashCode()) + 31) * 31) + (getFederatedUser() == null ? 0 : getFederatedUser().hashCode())) * 31;
        if (getPackedPolicySize() != null) {
            i = getPackedPolicySize().hashCode();
        }
        return hashCode + i;
    }

    public void setCredentials(Credentials credentials) {
        this.credentials = credentials;
    }

    public void setFederatedUser(FederatedUser federatedUser) {
        this.federatedUser = federatedUser;
    }

    public void setPackedPolicySize(Integer num) {
        this.packedPolicySize = num;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getCredentials() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Credentials: ");
            outline1072.append(getCredentials());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getFederatedUser() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("FederatedUser: ");
            outline1073.append(getFederatedUser());
            outline1073.append(",");
            outline107.append(outline1073.toString());
        }
        if (getPackedPolicySize() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("PackedPolicySize: ");
            outline1074.append(getPackedPolicySize());
            outline107.append(outline1074.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public GetFederationTokenResult withCredentials(Credentials credentials) {
        this.credentials = credentials;
        return this;
    }

    public GetFederationTokenResult withFederatedUser(FederatedUser federatedUser) {
        this.federatedUser = federatedUser;
        return this;
    }

    public GetFederationTokenResult withPackedPolicySize(Integer num) {
        this.packedPolicySize = num;
        return this;
    }
}
