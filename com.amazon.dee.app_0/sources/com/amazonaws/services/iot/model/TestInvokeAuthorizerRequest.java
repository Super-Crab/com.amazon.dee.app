package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazonaws.AmazonWebServiceRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class TestInvokeAuthorizerRequest extends AmazonWebServiceRequest implements Serializable {
    private String authorizerName;
    private String token;
    private String tokenSignature;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof TestInvokeAuthorizerRequest)) {
            return false;
        }
        TestInvokeAuthorizerRequest testInvokeAuthorizerRequest = (TestInvokeAuthorizerRequest) obj;
        if ((testInvokeAuthorizerRequest.getAuthorizerName() == null) ^ (getAuthorizerName() == null)) {
            return false;
        }
        if (testInvokeAuthorizerRequest.getAuthorizerName() != null && !testInvokeAuthorizerRequest.getAuthorizerName().equals(getAuthorizerName())) {
            return false;
        }
        if ((testInvokeAuthorizerRequest.getToken() == null) ^ (getToken() == null)) {
            return false;
        }
        if (testInvokeAuthorizerRequest.getToken() != null && !testInvokeAuthorizerRequest.getToken().equals(getToken())) {
            return false;
        }
        if ((testInvokeAuthorizerRequest.getTokenSignature() == null) ^ (getTokenSignature() == null)) {
            return false;
        }
        return testInvokeAuthorizerRequest.getTokenSignature() == null || testInvokeAuthorizerRequest.getTokenSignature().equals(getTokenSignature());
    }

    public String getAuthorizerName() {
        return this.authorizerName;
    }

    public String getToken() {
        return this.token;
    }

    public String getTokenSignature() {
        return this.tokenSignature;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((getAuthorizerName() == null ? 0 : getAuthorizerName().hashCode()) + 31) * 31) + (getToken() == null ? 0 : getToken().hashCode())) * 31;
        if (getTokenSignature() != null) {
            i = getTokenSignature().hashCode();
        }
        return hashCode + i;
    }

    public void setAuthorizerName(String str) {
        this.authorizerName = str;
    }

    public void setToken(String str) {
        this.token = str;
    }

    public void setTokenSignature(String str) {
        this.tokenSignature = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getAuthorizerName() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("authorizerName: ");
            outline1072.append(getAuthorizerName());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getToken() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("token: ");
            outline1073.append(getToken());
            outline1073.append(",");
            outline107.append(outline1073.toString());
        }
        if (getTokenSignature() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("tokenSignature: ");
            outline1074.append(getTokenSignature());
            outline107.append(outline1074.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public TestInvokeAuthorizerRequest withAuthorizerName(String str) {
        this.authorizerName = str;
        return this;
    }

    public TestInvokeAuthorizerRequest withToken(String str) {
        this.token = str;
        return this;
    }

    public TestInvokeAuthorizerRequest withTokenSignature(String str) {
        this.tokenSignature = str;
        return this;
    }
}
