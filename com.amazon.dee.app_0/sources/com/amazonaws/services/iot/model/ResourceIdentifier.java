package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class ResourceIdentifier implements Serializable {
    private String account;
    private String caCertificateId;
    private String clientId;
    private String cognitoIdentityPoolId;
    private String deviceCertificateId;
    private PolicyVersionIdentifier policyVersionIdentifier;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ResourceIdentifier)) {
            return false;
        }
        ResourceIdentifier resourceIdentifier = (ResourceIdentifier) obj;
        if ((resourceIdentifier.getDeviceCertificateId() == null) ^ (getDeviceCertificateId() == null)) {
            return false;
        }
        if (resourceIdentifier.getDeviceCertificateId() != null && !resourceIdentifier.getDeviceCertificateId().equals(getDeviceCertificateId())) {
            return false;
        }
        if ((resourceIdentifier.getCaCertificateId() == null) ^ (getCaCertificateId() == null)) {
            return false;
        }
        if (resourceIdentifier.getCaCertificateId() != null && !resourceIdentifier.getCaCertificateId().equals(getCaCertificateId())) {
            return false;
        }
        if ((resourceIdentifier.getCognitoIdentityPoolId() == null) ^ (getCognitoIdentityPoolId() == null)) {
            return false;
        }
        if (resourceIdentifier.getCognitoIdentityPoolId() != null && !resourceIdentifier.getCognitoIdentityPoolId().equals(getCognitoIdentityPoolId())) {
            return false;
        }
        if ((resourceIdentifier.getClientId() == null) ^ (getClientId() == null)) {
            return false;
        }
        if (resourceIdentifier.getClientId() != null && !resourceIdentifier.getClientId().equals(getClientId())) {
            return false;
        }
        if ((resourceIdentifier.getPolicyVersionIdentifier() == null) ^ (getPolicyVersionIdentifier() == null)) {
            return false;
        }
        if (resourceIdentifier.getPolicyVersionIdentifier() != null && !resourceIdentifier.getPolicyVersionIdentifier().equals(getPolicyVersionIdentifier())) {
            return false;
        }
        if ((resourceIdentifier.getAccount() == null) ^ (getAccount() == null)) {
            return false;
        }
        return resourceIdentifier.getAccount() == null || resourceIdentifier.getAccount().equals(getAccount());
    }

    public String getAccount() {
        return this.account;
    }

    public String getCaCertificateId() {
        return this.caCertificateId;
    }

    public String getClientId() {
        return this.clientId;
    }

    public String getCognitoIdentityPoolId() {
        return this.cognitoIdentityPoolId;
    }

    public String getDeviceCertificateId() {
        return this.deviceCertificateId;
    }

    public PolicyVersionIdentifier getPolicyVersionIdentifier() {
        return this.policyVersionIdentifier;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((((((((getDeviceCertificateId() == null ? 0 : getDeviceCertificateId().hashCode()) + 31) * 31) + (getCaCertificateId() == null ? 0 : getCaCertificateId().hashCode())) * 31) + (getCognitoIdentityPoolId() == null ? 0 : getCognitoIdentityPoolId().hashCode())) * 31) + (getClientId() == null ? 0 : getClientId().hashCode())) * 31) + (getPolicyVersionIdentifier() == null ? 0 : getPolicyVersionIdentifier().hashCode())) * 31;
        if (getAccount() != null) {
            i = getAccount().hashCode();
        }
        return hashCode + i;
    }

    public void setAccount(String str) {
        this.account = str;
    }

    public void setCaCertificateId(String str) {
        this.caCertificateId = str;
    }

    public void setClientId(String str) {
        this.clientId = str;
    }

    public void setCognitoIdentityPoolId(String str) {
        this.cognitoIdentityPoolId = str;
    }

    public void setDeviceCertificateId(String str) {
        this.deviceCertificateId = str;
    }

    public void setPolicyVersionIdentifier(PolicyVersionIdentifier policyVersionIdentifier) {
        this.policyVersionIdentifier = policyVersionIdentifier;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getDeviceCertificateId() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("deviceCertificateId: ");
            outline1072.append(getDeviceCertificateId());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getCaCertificateId() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("caCertificateId: ");
            outline1073.append(getCaCertificateId());
            outline1073.append(",");
            outline107.append(outline1073.toString());
        }
        if (getCognitoIdentityPoolId() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("cognitoIdentityPoolId: ");
            outline1074.append(getCognitoIdentityPoolId());
            outline1074.append(",");
            outline107.append(outline1074.toString());
        }
        if (getClientId() != null) {
            StringBuilder outline1075 = GeneratedOutlineSupport1.outline107("clientId: ");
            outline1075.append(getClientId());
            outline1075.append(",");
            outline107.append(outline1075.toString());
        }
        if (getPolicyVersionIdentifier() != null) {
            StringBuilder outline1076 = GeneratedOutlineSupport1.outline107("policyVersionIdentifier: ");
            outline1076.append(getPolicyVersionIdentifier());
            outline1076.append(",");
            outline107.append(outline1076.toString());
        }
        if (getAccount() != null) {
            StringBuilder outline1077 = GeneratedOutlineSupport1.outline107("account: ");
            outline1077.append(getAccount());
            outline107.append(outline1077.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public ResourceIdentifier withAccount(String str) {
        this.account = str;
        return this;
    }

    public ResourceIdentifier withCaCertificateId(String str) {
        this.caCertificateId = str;
        return this;
    }

    public ResourceIdentifier withClientId(String str) {
        this.clientId = str;
        return this;
    }

    public ResourceIdentifier withCognitoIdentityPoolId(String str) {
        this.cognitoIdentityPoolId = str;
        return this;
    }

    public ResourceIdentifier withDeviceCertificateId(String str) {
        this.deviceCertificateId = str;
        return this;
    }

    public ResourceIdentifier withPolicyVersionIdentifier(PolicyVersionIdentifier policyVersionIdentifier) {
        this.policyVersionIdentifier = policyVersionIdentifier;
        return this;
    }
}
