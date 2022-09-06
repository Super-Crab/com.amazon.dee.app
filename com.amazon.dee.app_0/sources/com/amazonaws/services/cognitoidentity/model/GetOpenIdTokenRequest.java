package com.amazonaws.services.cognitoidentity.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazonaws.AmazonWebServiceRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes13.dex */
public class GetOpenIdTokenRequest extends AmazonWebServiceRequest implements Serializable {
    private String identityId;
    private Map<String, String> logins;

    public GetOpenIdTokenRequest addLoginsEntry(String str, String str2) {
        if (this.logins == null) {
            this.logins = new HashMap();
        }
        if (!this.logins.containsKey(str)) {
            this.logins.put(str, str2);
            return this;
        }
        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline78(str, GeneratedOutlineSupport1.outline107("Duplicated keys ("), ") are provided."));
    }

    public GetOpenIdTokenRequest clearLoginsEntries() {
        this.logins = null;
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof GetOpenIdTokenRequest)) {
            return false;
        }
        GetOpenIdTokenRequest getOpenIdTokenRequest = (GetOpenIdTokenRequest) obj;
        if ((getOpenIdTokenRequest.getIdentityId() == null) ^ (getIdentityId() == null)) {
            return false;
        }
        if (getOpenIdTokenRequest.getIdentityId() != null && !getOpenIdTokenRequest.getIdentityId().equals(getIdentityId())) {
            return false;
        }
        if ((getOpenIdTokenRequest.getLogins() == null) ^ (getLogins() == null)) {
            return false;
        }
        return getOpenIdTokenRequest.getLogins() == null || getOpenIdTokenRequest.getLogins().equals(getLogins());
    }

    public String getIdentityId() {
        return this.identityId;
    }

    public Map<String, String> getLogins() {
        return this.logins;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((getIdentityId() == null ? 0 : getIdentityId().hashCode()) + 31) * 31;
        if (getLogins() != null) {
            i = getLogins().hashCode();
        }
        return hashCode + i;
    }

    public void setIdentityId(String str) {
        this.identityId = str;
    }

    public void setLogins(Map<String, String> map) {
        this.logins = map;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getIdentityId() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("IdentityId: ");
            outline1072.append(getIdentityId());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getLogins() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("Logins: ");
            outline1073.append(getLogins());
            outline107.append(outline1073.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public GetOpenIdTokenRequest withIdentityId(String str) {
        this.identityId = str;
        return this;
    }

    public GetOpenIdTokenRequest withLogins(Map<String, String> map) {
        this.logins = map;
        return this;
    }
}
