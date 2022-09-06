package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazonaws.AmazonWebServiceRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes13.dex */
public class UpdateAuthorizerRequest extends AmazonWebServiceRequest implements Serializable {
    private String authorizerFunctionArn;
    private String authorizerName;
    private String status;
    private String tokenKeyName;
    private Map<String, String> tokenSigningPublicKeys;

    public UpdateAuthorizerRequest addtokenSigningPublicKeysEntry(String str, String str2) {
        if (this.tokenSigningPublicKeys == null) {
            this.tokenSigningPublicKeys = new HashMap();
        }
        if (!this.tokenSigningPublicKeys.containsKey(str)) {
            this.tokenSigningPublicKeys.put(str, str2);
            return this;
        }
        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline78(str, GeneratedOutlineSupport1.outline107("Duplicated keys ("), ") are provided."));
    }

    public UpdateAuthorizerRequest cleartokenSigningPublicKeysEntries() {
        this.tokenSigningPublicKeys = null;
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof UpdateAuthorizerRequest)) {
            return false;
        }
        UpdateAuthorizerRequest updateAuthorizerRequest = (UpdateAuthorizerRequest) obj;
        if ((updateAuthorizerRequest.getAuthorizerName() == null) ^ (getAuthorizerName() == null)) {
            return false;
        }
        if (updateAuthorizerRequest.getAuthorizerName() != null && !updateAuthorizerRequest.getAuthorizerName().equals(getAuthorizerName())) {
            return false;
        }
        if ((updateAuthorizerRequest.getAuthorizerFunctionArn() == null) ^ (getAuthorizerFunctionArn() == null)) {
            return false;
        }
        if (updateAuthorizerRequest.getAuthorizerFunctionArn() != null && !updateAuthorizerRequest.getAuthorizerFunctionArn().equals(getAuthorizerFunctionArn())) {
            return false;
        }
        if ((updateAuthorizerRequest.getTokenKeyName() == null) ^ (getTokenKeyName() == null)) {
            return false;
        }
        if (updateAuthorizerRequest.getTokenKeyName() != null && !updateAuthorizerRequest.getTokenKeyName().equals(getTokenKeyName())) {
            return false;
        }
        if ((updateAuthorizerRequest.getTokenSigningPublicKeys() == null) ^ (getTokenSigningPublicKeys() == null)) {
            return false;
        }
        if (updateAuthorizerRequest.getTokenSigningPublicKeys() != null && !updateAuthorizerRequest.getTokenSigningPublicKeys().equals(getTokenSigningPublicKeys())) {
            return false;
        }
        if ((updateAuthorizerRequest.getStatus() == null) ^ (getStatus() == null)) {
            return false;
        }
        return updateAuthorizerRequest.getStatus() == null || updateAuthorizerRequest.getStatus().equals(getStatus());
    }

    public String getAuthorizerFunctionArn() {
        return this.authorizerFunctionArn;
    }

    public String getAuthorizerName() {
        return this.authorizerName;
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
        int hashCode = ((((((((getAuthorizerName() == null ? 0 : getAuthorizerName().hashCode()) + 31) * 31) + (getAuthorizerFunctionArn() == null ? 0 : getAuthorizerFunctionArn().hashCode())) * 31) + (getTokenKeyName() == null ? 0 : getTokenKeyName().hashCode())) * 31) + (getTokenSigningPublicKeys() == null ? 0 : getTokenSigningPublicKeys().hashCode())) * 31;
        if (getStatus() != null) {
            i = getStatus().hashCode();
        }
        return hashCode + i;
    }

    public void setAuthorizerFunctionArn(String str) {
        this.authorizerFunctionArn = str;
    }

    public void setAuthorizerName(String str) {
        this.authorizerName = str;
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
        if (getAuthorizerFunctionArn() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("authorizerFunctionArn: ");
            outline1073.append(getAuthorizerFunctionArn());
            outline1073.append(",");
            outline107.append(outline1073.toString());
        }
        if (getTokenKeyName() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("tokenKeyName: ");
            outline1074.append(getTokenKeyName());
            outline1074.append(",");
            outline107.append(outline1074.toString());
        }
        if (getTokenSigningPublicKeys() != null) {
            StringBuilder outline1075 = GeneratedOutlineSupport1.outline107("tokenSigningPublicKeys: ");
            outline1075.append(getTokenSigningPublicKeys());
            outline1075.append(",");
            outline107.append(outline1075.toString());
        }
        if (getStatus() != null) {
            StringBuilder outline1076 = GeneratedOutlineSupport1.outline107("status: ");
            outline1076.append(getStatus());
            outline107.append(outline1076.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public UpdateAuthorizerRequest withAuthorizerFunctionArn(String str) {
        this.authorizerFunctionArn = str;
        return this;
    }

    public UpdateAuthorizerRequest withAuthorizerName(String str) {
        this.authorizerName = str;
        return this;
    }

    public UpdateAuthorizerRequest withStatus(String str) {
        this.status = str;
        return this;
    }

    public UpdateAuthorizerRequest withTokenKeyName(String str) {
        this.tokenKeyName = str;
        return this;
    }

    public UpdateAuthorizerRequest withTokenSigningPublicKeys(Map<String, String> map) {
        this.tokenSigningPublicKeys = map;
        return this;
    }

    public void setStatus(AuthorizerStatus authorizerStatus) {
        this.status = authorizerStatus.toString();
    }

    public UpdateAuthorizerRequest withStatus(AuthorizerStatus authorizerStatus) {
        this.status = authorizerStatus.toString();
        return this;
    }
}
