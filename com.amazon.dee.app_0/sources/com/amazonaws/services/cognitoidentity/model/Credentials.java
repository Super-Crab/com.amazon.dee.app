package com.amazonaws.services.cognitoidentity.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import java.util.Date;
/* loaded from: classes13.dex */
public class Credentials implements Serializable {
    private String accessKeyId;
    private Date expiration;
    private String secretKey;
    private String sessionToken;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof Credentials)) {
            return false;
        }
        Credentials credentials = (Credentials) obj;
        if ((credentials.getAccessKeyId() == null) ^ (getAccessKeyId() == null)) {
            return false;
        }
        if (credentials.getAccessKeyId() != null && !credentials.getAccessKeyId().equals(getAccessKeyId())) {
            return false;
        }
        if ((credentials.getSecretKey() == null) ^ (getSecretKey() == null)) {
            return false;
        }
        if (credentials.getSecretKey() != null && !credentials.getSecretKey().equals(getSecretKey())) {
            return false;
        }
        if ((credentials.getSessionToken() == null) ^ (getSessionToken() == null)) {
            return false;
        }
        if (credentials.getSessionToken() != null && !credentials.getSessionToken().equals(getSessionToken())) {
            return false;
        }
        if ((credentials.getExpiration() == null) ^ (getExpiration() == null)) {
            return false;
        }
        return credentials.getExpiration() == null || credentials.getExpiration().equals(getExpiration());
    }

    public String getAccessKeyId() {
        return this.accessKeyId;
    }

    public Date getExpiration() {
        return this.expiration;
    }

    public String getSecretKey() {
        return this.secretKey;
    }

    public String getSessionToken() {
        return this.sessionToken;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((((getAccessKeyId() == null ? 0 : getAccessKeyId().hashCode()) + 31) * 31) + (getSecretKey() == null ? 0 : getSecretKey().hashCode())) * 31) + (getSessionToken() == null ? 0 : getSessionToken().hashCode())) * 31;
        if (getExpiration() != null) {
            i = getExpiration().hashCode();
        }
        return hashCode + i;
    }

    public void setAccessKeyId(String str) {
        this.accessKeyId = str;
    }

    public void setExpiration(Date date) {
        this.expiration = date;
    }

    public void setSecretKey(String str) {
        this.secretKey = str;
    }

    public void setSessionToken(String str) {
        this.sessionToken = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getAccessKeyId() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("AccessKeyId: ");
            outline1072.append(getAccessKeyId());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getSecretKey() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("SecretKey: ");
            outline1073.append(getSecretKey());
            outline1073.append(",");
            outline107.append(outline1073.toString());
        }
        if (getSessionToken() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("SessionToken: ");
            outline1074.append(getSessionToken());
            outline1074.append(",");
            outline107.append(outline1074.toString());
        }
        if (getExpiration() != null) {
            StringBuilder outline1075 = GeneratedOutlineSupport1.outline107("Expiration: ");
            outline1075.append(getExpiration());
            outline107.append(outline1075.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public Credentials withAccessKeyId(String str) {
        this.accessKeyId = str;
        return this;
    }

    public Credentials withExpiration(Date date) {
        this.expiration = date;
        return this;
    }

    public Credentials withSecretKey(String str) {
        this.secretKey = str;
        return this;
    }

    public Credentials withSessionToken(String str) {
        this.sessionToken = str;
        return this;
    }
}
