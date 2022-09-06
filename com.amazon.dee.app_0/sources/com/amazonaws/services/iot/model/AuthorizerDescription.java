package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes13.dex */
public class AuthorizerDescription implements Serializable {
    private String authorizerArn;
    private String authorizerFunctionArn;
    private String authorizerName;
    private Date creationDate;
    private Date lastModifiedDate;
    private String status;
    private String tokenKeyName;
    private Map<String, String> tokenSigningPublicKeys;

    public AuthorizerDescription addtokenSigningPublicKeysEntry(String str, String str2) {
        if (this.tokenSigningPublicKeys == null) {
            this.tokenSigningPublicKeys = new HashMap();
        }
        if (!this.tokenSigningPublicKeys.containsKey(str)) {
            this.tokenSigningPublicKeys.put(str, str2);
            return this;
        }
        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline78(str, GeneratedOutlineSupport1.outline107("Duplicated keys ("), ") are provided."));
    }

    public AuthorizerDescription cleartokenSigningPublicKeysEntries() {
        this.tokenSigningPublicKeys = null;
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof AuthorizerDescription)) {
            return false;
        }
        AuthorizerDescription authorizerDescription = (AuthorizerDescription) obj;
        if ((authorizerDescription.getAuthorizerName() == null) ^ (getAuthorizerName() == null)) {
            return false;
        }
        if (authorizerDescription.getAuthorizerName() != null && !authorizerDescription.getAuthorizerName().equals(getAuthorizerName())) {
            return false;
        }
        if ((authorizerDescription.getAuthorizerArn() == null) ^ (getAuthorizerArn() == null)) {
            return false;
        }
        if (authorizerDescription.getAuthorizerArn() != null && !authorizerDescription.getAuthorizerArn().equals(getAuthorizerArn())) {
            return false;
        }
        if ((authorizerDescription.getAuthorizerFunctionArn() == null) ^ (getAuthorizerFunctionArn() == null)) {
            return false;
        }
        if (authorizerDescription.getAuthorizerFunctionArn() != null && !authorizerDescription.getAuthorizerFunctionArn().equals(getAuthorizerFunctionArn())) {
            return false;
        }
        if ((authorizerDescription.getTokenKeyName() == null) ^ (getTokenKeyName() == null)) {
            return false;
        }
        if (authorizerDescription.getTokenKeyName() != null && !authorizerDescription.getTokenKeyName().equals(getTokenKeyName())) {
            return false;
        }
        if ((authorizerDescription.getTokenSigningPublicKeys() == null) ^ (getTokenSigningPublicKeys() == null)) {
            return false;
        }
        if (authorizerDescription.getTokenSigningPublicKeys() != null && !authorizerDescription.getTokenSigningPublicKeys().equals(getTokenSigningPublicKeys())) {
            return false;
        }
        if ((authorizerDescription.getStatus() == null) ^ (getStatus() == null)) {
            return false;
        }
        if (authorizerDescription.getStatus() != null && !authorizerDescription.getStatus().equals(getStatus())) {
            return false;
        }
        if ((authorizerDescription.getCreationDate() == null) ^ (getCreationDate() == null)) {
            return false;
        }
        if (authorizerDescription.getCreationDate() != null && !authorizerDescription.getCreationDate().equals(getCreationDate())) {
            return false;
        }
        if ((authorizerDescription.getLastModifiedDate() == null) ^ (getLastModifiedDate() == null)) {
            return false;
        }
        return authorizerDescription.getLastModifiedDate() == null || authorizerDescription.getLastModifiedDate().equals(getLastModifiedDate());
    }

    public String getAuthorizerArn() {
        return this.authorizerArn;
    }

    public String getAuthorizerFunctionArn() {
        return this.authorizerFunctionArn;
    }

    public String getAuthorizerName() {
        return this.authorizerName;
    }

    public Date getCreationDate() {
        return this.creationDate;
    }

    public Date getLastModifiedDate() {
        return this.lastModifiedDate;
    }

    public String getStatus() {
        return this.status;
    }

    public String getTokenKeyName() {
        return this.tokenKeyName;
    }

    public Map<String, String> getTokenSigningPublicKeys() {
        return this.tokenSigningPublicKeys;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((((((((((((getAuthorizerName() == null ? 0 : getAuthorizerName().hashCode()) + 31) * 31) + (getAuthorizerArn() == null ? 0 : getAuthorizerArn().hashCode())) * 31) + (getAuthorizerFunctionArn() == null ? 0 : getAuthorizerFunctionArn().hashCode())) * 31) + (getTokenKeyName() == null ? 0 : getTokenKeyName().hashCode())) * 31) + (getTokenSigningPublicKeys() == null ? 0 : getTokenSigningPublicKeys().hashCode())) * 31) + (getStatus() == null ? 0 : getStatus().hashCode())) * 31) + (getCreationDate() == null ? 0 : getCreationDate().hashCode())) * 31;
        if (getLastModifiedDate() != null) {
            i = getLastModifiedDate().hashCode();
        }
        return hashCode + i;
    }

    public void setAuthorizerArn(String str) {
        this.authorizerArn = str;
    }

    public void setAuthorizerFunctionArn(String str) {
        this.authorizerFunctionArn = str;
    }

    public void setAuthorizerName(String str) {
        this.authorizerName = str;
    }

    public void setCreationDate(Date date) {
        this.creationDate = date;
    }

    public void setLastModifiedDate(Date date) {
        this.lastModifiedDate = date;
    }

    public void setStatus(String str) {
        this.status = str;
    }

    public void setTokenKeyName(String str) {
        this.tokenKeyName = str;
    }

    public void setTokenSigningPublicKeys(Map<String, String> map) {
        this.tokenSigningPublicKeys = map;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getAuthorizerName() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("authorizerName: ");
            outline1072.append(getAuthorizerName());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getAuthorizerArn() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("authorizerArn: ");
            outline1073.append(getAuthorizerArn());
            outline1073.append(",");
            outline107.append(outline1073.toString());
        }
        if (getAuthorizerFunctionArn() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("authorizerFunctionArn: ");
            outline1074.append(getAuthorizerFunctionArn());
            outline1074.append(",");
            outline107.append(outline1074.toString());
        }
        if (getTokenKeyName() != null) {
            StringBuilder outline1075 = GeneratedOutlineSupport1.outline107("tokenKeyName: ");
            outline1075.append(getTokenKeyName());
            outline1075.append(",");
            outline107.append(outline1075.toString());
        }
        if (getTokenSigningPublicKeys() != null) {
            StringBuilder outline1076 = GeneratedOutlineSupport1.outline107("tokenSigningPublicKeys: ");
            outline1076.append(getTokenSigningPublicKeys());
            outline1076.append(",");
            outline107.append(outline1076.toString());
        }
        if (getStatus() != null) {
            StringBuilder outline1077 = GeneratedOutlineSupport1.outline107("status: ");
            outline1077.append(getStatus());
            outline1077.append(",");
            outline107.append(outline1077.toString());
        }
        if (getCreationDate() != null) {
            StringBuilder outline1078 = GeneratedOutlineSupport1.outline107("creationDate: ");
            outline1078.append(getCreationDate());
            outline1078.append(",");
            outline107.append(outline1078.toString());
        }
        if (getLastModifiedDate() != null) {
            StringBuilder outline1079 = GeneratedOutlineSupport1.outline107("lastModifiedDate: ");
            outline1079.append(getLastModifiedDate());
            outline107.append(outline1079.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public AuthorizerDescription withAuthorizerArn(String str) {
        this.authorizerArn = str;
        return this;
    }

    public AuthorizerDescription withAuthorizerFunctionArn(String str) {
        this.authorizerFunctionArn = str;
        return this;
    }

    public AuthorizerDescription withAuthorizerName(String str) {
        this.authorizerName = str;
        return this;
    }

    public AuthorizerDescription withCreationDate(Date date) {
        this.creationDate = date;
        return this;
    }

    public AuthorizerDescription withLastModifiedDate(Date date) {
        this.lastModifiedDate = date;
        return this;
    }

    public AuthorizerDescription withStatus(String str) {
        this.status = str;
        return this;
    }

    public AuthorizerDescription withTokenKeyName(String str) {
        this.tokenKeyName = str;
        return this;
    }

    public AuthorizerDescription withTokenSigningPublicKeys(Map<String, String> map) {
        this.tokenSigningPublicKeys = map;
        return this;
    }

    public void setStatus(AuthorizerStatus authorizerStatus) {
        this.status = authorizerStatus.toString();
    }

    public AuthorizerDescription withStatus(AuthorizerStatus authorizerStatus) {
        this.status = authorizerStatus.toString();
        return this;
    }
}
