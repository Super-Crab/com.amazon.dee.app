package com.amazonaws.services.cognitoidentity.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class GetOpenIdTokenResult implements Serializable {
    private String identityId;
    private String token;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof GetOpenIdTokenResult)) {
            return false;
        }
        GetOpenIdTokenResult getOpenIdTokenResult = (GetOpenIdTokenResult) obj;
        if ((getOpenIdTokenResult.getIdentityId() == null) ^ (getIdentityId() == null)) {
            return false;
        }
        if (getOpenIdTokenResult.getIdentityId() != null && !getOpenIdTokenResult.getIdentityId().equals(getIdentityId())) {
            return false;
        }
        if ((getOpenIdTokenResult.getToken() == null) ^ (getToken() == null)) {
            return false;
        }
        return getOpenIdTokenResult.getToken() == null || getOpenIdTokenResult.getToken().equals(getToken());
    }

    public String getIdentityId() {
        return this.identityId;
    }

    public String getToken() {
        return this.token;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((getIdentityId() == null ? 0 : getIdentityId().hashCode()) + 31) * 31;
        if (getToken() != null) {
            i = getToken().hashCode();
        }
        return hashCode + i;
    }

    public void setIdentityId(String str) {
        this.identityId = str;
    }

    public void setToken(String str) {
        this.token = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getIdentityId() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("IdentityId: ");
            outline1072.append(getIdentityId());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getToken() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("Token: ");
            outline1073.append(getToken());
            outline107.append(outline1073.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public GetOpenIdTokenResult withIdentityId(String str) {
        this.identityId = str;
        return this;
    }

    public GetOpenIdTokenResult withToken(String str) {
        this.token = str;
        return this;
    }
}
