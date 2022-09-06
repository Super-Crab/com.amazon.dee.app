package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazonaws.AmazonWebServiceRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
/* loaded from: classes13.dex */
public class TestAuthorizationRequest extends AmazonWebServiceRequest implements Serializable {
    private List<AuthInfo> authInfos;
    private String clientId;
    private String cognitoIdentityPoolId;
    private List<String> policyNamesToAdd;
    private List<String> policyNamesToSkip;
    private String principal;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof TestAuthorizationRequest)) {
            return false;
        }
        TestAuthorizationRequest testAuthorizationRequest = (TestAuthorizationRequest) obj;
        if ((testAuthorizationRequest.getPrincipal() == null) ^ (getPrincipal() == null)) {
            return false;
        }
        if (testAuthorizationRequest.getPrincipal() != null && !testAuthorizationRequest.getPrincipal().equals(getPrincipal())) {
            return false;
        }
        if ((testAuthorizationRequest.getCognitoIdentityPoolId() == null) ^ (getCognitoIdentityPoolId() == null)) {
            return false;
        }
        if (testAuthorizationRequest.getCognitoIdentityPoolId() != null && !testAuthorizationRequest.getCognitoIdentityPoolId().equals(getCognitoIdentityPoolId())) {
            return false;
        }
        if ((testAuthorizationRequest.getAuthInfos() == null) ^ (getAuthInfos() == null)) {
            return false;
        }
        if (testAuthorizationRequest.getAuthInfos() != null && !testAuthorizationRequest.getAuthInfos().equals(getAuthInfos())) {
            return false;
        }
        if ((testAuthorizationRequest.getClientId() == null) ^ (getClientId() == null)) {
            return false;
        }
        if (testAuthorizationRequest.getClientId() != null && !testAuthorizationRequest.getClientId().equals(getClientId())) {
            return false;
        }
        if ((testAuthorizationRequest.getPolicyNamesToAdd() == null) ^ (getPolicyNamesToAdd() == null)) {
            return false;
        }
        if (testAuthorizationRequest.getPolicyNamesToAdd() != null && !testAuthorizationRequest.getPolicyNamesToAdd().equals(getPolicyNamesToAdd())) {
            return false;
        }
        if ((testAuthorizationRequest.getPolicyNamesToSkip() == null) ^ (getPolicyNamesToSkip() == null)) {
            return false;
        }
        return testAuthorizationRequest.getPolicyNamesToSkip() == null || testAuthorizationRequest.getPolicyNamesToSkip().equals(getPolicyNamesToSkip());
    }

    public List<AuthInfo> getAuthInfos() {
        return this.authInfos;
    }

    public String getClientId() {
        return this.clientId;
    }

    public String getCognitoIdentityPoolId() {
        return this.cognitoIdentityPoolId;
    }

    public List<String> getPolicyNamesToAdd() {
        return this.policyNamesToAdd;
    }

    public List<String> getPolicyNamesToSkip() {
        return this.policyNamesToSkip;
    }

    public String getPrincipal() {
        return this.principal;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((((((((getPrincipal() == null ? 0 : getPrincipal().hashCode()) + 31) * 31) + (getCognitoIdentityPoolId() == null ? 0 : getCognitoIdentityPoolId().hashCode())) * 31) + (getAuthInfos() == null ? 0 : getAuthInfos().hashCode())) * 31) + (getClientId() == null ? 0 : getClientId().hashCode())) * 31) + (getPolicyNamesToAdd() == null ? 0 : getPolicyNamesToAdd().hashCode())) * 31;
        if (getPolicyNamesToSkip() != null) {
            i = getPolicyNamesToSkip().hashCode();
        }
        return hashCode + i;
    }

    public void setAuthInfos(Collection<AuthInfo> collection) {
        if (collection == null) {
            this.authInfos = null;
        } else {
            this.authInfos = new ArrayList(collection);
        }
    }

    public void setClientId(String str) {
        this.clientId = str;
    }

    public void setCognitoIdentityPoolId(String str) {
        this.cognitoIdentityPoolId = str;
    }

    public void setPolicyNamesToAdd(Collection<String> collection) {
        if (collection == null) {
            this.policyNamesToAdd = null;
        } else {
            this.policyNamesToAdd = new ArrayList(collection);
        }
    }

    public void setPolicyNamesToSkip(Collection<String> collection) {
        if (collection == null) {
            this.policyNamesToSkip = null;
        } else {
            this.policyNamesToSkip = new ArrayList(collection);
        }
    }

    public void setPrincipal(String str) {
        this.principal = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getPrincipal() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("principal: ");
            outline1072.append(getPrincipal());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getCognitoIdentityPoolId() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("cognitoIdentityPoolId: ");
            outline1073.append(getCognitoIdentityPoolId());
            outline1073.append(",");
            outline107.append(outline1073.toString());
        }
        if (getAuthInfos() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("authInfos: ");
            outline1074.append(getAuthInfos());
            outline1074.append(",");
            outline107.append(outline1074.toString());
        }
        if (getClientId() != null) {
            StringBuilder outline1075 = GeneratedOutlineSupport1.outline107("clientId: ");
            outline1075.append(getClientId());
            outline1075.append(",");
            outline107.append(outline1075.toString());
        }
        if (getPolicyNamesToAdd() != null) {
            StringBuilder outline1076 = GeneratedOutlineSupport1.outline107("policyNamesToAdd: ");
            outline1076.append(getPolicyNamesToAdd());
            outline1076.append(",");
            outline107.append(outline1076.toString());
        }
        if (getPolicyNamesToSkip() != null) {
            StringBuilder outline1077 = GeneratedOutlineSupport1.outline107("policyNamesToSkip: ");
            outline1077.append(getPolicyNamesToSkip());
            outline107.append(outline1077.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public TestAuthorizationRequest withAuthInfos(AuthInfo... authInfoArr) {
        if (getAuthInfos() == null) {
            this.authInfos = new ArrayList(authInfoArr.length);
        }
        for (AuthInfo authInfo : authInfoArr) {
            this.authInfos.add(authInfo);
        }
        return this;
    }

    public TestAuthorizationRequest withClientId(String str) {
        this.clientId = str;
        return this;
    }

    public TestAuthorizationRequest withCognitoIdentityPoolId(String str) {
        this.cognitoIdentityPoolId = str;
        return this;
    }

    public TestAuthorizationRequest withPolicyNamesToAdd(String... strArr) {
        if (getPolicyNamesToAdd() == null) {
            this.policyNamesToAdd = new ArrayList(strArr.length);
        }
        for (String str : strArr) {
            this.policyNamesToAdd.add(str);
        }
        return this;
    }

    public TestAuthorizationRequest withPolicyNamesToSkip(String... strArr) {
        if (getPolicyNamesToSkip() == null) {
            this.policyNamesToSkip = new ArrayList(strArr.length);
        }
        for (String str : strArr) {
            this.policyNamesToSkip.add(str);
        }
        return this;
    }

    public TestAuthorizationRequest withPrincipal(String str) {
        this.principal = str;
        return this;
    }

    public TestAuthorizationRequest withAuthInfos(Collection<AuthInfo> collection) {
        setAuthInfos(collection);
        return this;
    }

    public TestAuthorizationRequest withPolicyNamesToAdd(Collection<String> collection) {
        setPolicyNamesToAdd(collection);
        return this;
    }

    public TestAuthorizationRequest withPolicyNamesToSkip(Collection<String> collection) {
        setPolicyNamesToSkip(collection);
        return this;
    }
}
