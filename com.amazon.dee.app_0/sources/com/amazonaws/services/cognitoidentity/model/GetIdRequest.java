package com.amazonaws.services.cognitoidentity.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazonaws.AmazonWebServiceRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes13.dex */
public class GetIdRequest extends AmazonWebServiceRequest implements Serializable {
    private String accountId;
    private String identityPoolId;
    private Map<String, String> logins;

    public GetIdRequest addLoginsEntry(String str, String str2) {
        if (this.logins == null) {
            this.logins = new HashMap();
        }
        if (!this.logins.containsKey(str)) {
            this.logins.put(str, str2);
            return this;
        }
        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline78(str, GeneratedOutlineSupport1.outline107("Duplicated keys ("), ") are provided."));
    }

    public GetIdRequest clearLoginsEntries() {
        this.logins = null;
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof GetIdRequest)) {
            return false;
        }
        GetIdRequest getIdRequest = (GetIdRequest) obj;
        if ((getIdRequest.getAccountId() == null) ^ (getAccountId() == null)) {
            return false;
        }
        if (getIdRequest.getAccountId() != null && !getIdRequest.getAccountId().equals(getAccountId())) {
            return false;
        }
        if ((getIdRequest.getIdentityPoolId() == null) ^ (getIdentityPoolId() == null)) {
            return false;
        }
        if (getIdRequest.getIdentityPoolId() != null && !getIdRequest.getIdentityPoolId().equals(getIdentityPoolId())) {
            return false;
        }
        if ((getIdRequest.getLogins() == null) ^ (getLogins() == null)) {
            return false;
        }
        return getIdRequest.getLogins() == null || getIdRequest.getLogins().equals(getLogins());
    }

    public String getAccountId() {
        return this.accountId;
    }

    public String getIdentityPoolId() {
        return this.identityPoolId;
    }

    public Map<String, String> getLogins() {
        return this.logins;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((getAccountId() == null ? 0 : getAccountId().hashCode()) + 31) * 31) + (getIdentityPoolId() == null ? 0 : getIdentityPoolId().hashCode())) * 31;
        if (getLogins() != null) {
            i = getLogins().hashCode();
        }
        return hashCode + i;
    }

    public void setAccountId(String str) {
        this.accountId = str;
    }

    public void setIdentityPoolId(String str) {
        this.identityPoolId = str;
    }

    public void setLogins(Map<String, String> map) {
        this.logins = map;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getAccountId() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("AccountId: ");
            outline1072.append(getAccountId());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getIdentityPoolId() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("IdentityPoolId: ");
            outline1073.append(getIdentityPoolId());
            outline1073.append(",");
            outline107.append(outline1073.toString());
        }
        if (getLogins() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("Logins: ");
            outline1074.append(getLogins());
            outline107.append(outline1074.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public GetIdRequest withAccountId(String str) {
        this.accountId = str;
        return this;
    }

    public GetIdRequest withIdentityPoolId(String str) {
        this.identityPoolId = str;
        return this;
    }

    public GetIdRequest withLogins(Map<String, String> map) {
        this.logins = map;
        return this;
    }
}
