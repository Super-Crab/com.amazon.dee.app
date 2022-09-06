package com.amazonaws.services.cognitoidentity.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class CognitoIdentityProvider implements Serializable {
    private String clientId;
    private String providerName;
    private Boolean serverSideTokenCheck;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof CognitoIdentityProvider)) {
            return false;
        }
        CognitoIdentityProvider cognitoIdentityProvider = (CognitoIdentityProvider) obj;
        if ((cognitoIdentityProvider.getProviderName() == null) ^ (getProviderName() == null)) {
            return false;
        }
        if (cognitoIdentityProvider.getProviderName() != null && !cognitoIdentityProvider.getProviderName().equals(getProviderName())) {
            return false;
        }
        if ((cognitoIdentityProvider.getClientId() == null) ^ (getClientId() == null)) {
            return false;
        }
        if (cognitoIdentityProvider.getClientId() != null && !cognitoIdentityProvider.getClientId().equals(getClientId())) {
            return false;
        }
        if ((cognitoIdentityProvider.getServerSideTokenCheck() == null) ^ (getServerSideTokenCheck() == null)) {
            return false;
        }
        return cognitoIdentityProvider.getServerSideTokenCheck() == null || cognitoIdentityProvider.getServerSideTokenCheck().equals(getServerSideTokenCheck());
    }

    public String getClientId() {
        return this.clientId;
    }

    public String getProviderName() {
        return this.providerName;
    }

    public Boolean getServerSideTokenCheck() {
        return this.serverSideTokenCheck;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((getProviderName() == null ? 0 : getProviderName().hashCode()) + 31) * 31) + (getClientId() == null ? 0 : getClientId().hashCode())) * 31;
        if (getServerSideTokenCheck() != null) {
            i = getServerSideTokenCheck().hashCode();
        }
        return hashCode + i;
    }

    public Boolean isServerSideTokenCheck() {
        return this.serverSideTokenCheck;
    }

    public void setClientId(String str) {
        this.clientId = str;
    }

    public void setProviderName(String str) {
        this.providerName = str;
    }

    public void setServerSideTokenCheck(Boolean bool) {
        this.serverSideTokenCheck = bool;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getProviderName() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("ProviderName: ");
            outline1072.append(getProviderName());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getClientId() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("ClientId: ");
            outline1073.append(getClientId());
            outline1073.append(",");
            outline107.append(outline1073.toString());
        }
        if (getServerSideTokenCheck() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("ServerSideTokenCheck: ");
            outline1074.append(getServerSideTokenCheck());
            outline107.append(outline1074.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public CognitoIdentityProvider withClientId(String str) {
        this.clientId = str;
        return this;
    }

    public CognitoIdentityProvider withProviderName(String str) {
        this.providerName = str;
        return this;
    }

    public CognitoIdentityProvider withServerSideTokenCheck(Boolean bool) {
        this.serverSideTokenCheck = bool;
        return this;
    }
}
