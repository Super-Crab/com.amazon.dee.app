package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazonaws.AmazonWebServiceRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class RegisterCACertificateRequest extends AmazonWebServiceRequest implements Serializable {
    private Boolean allowAutoRegistration;
    private String caCertificate;
    private RegistrationConfig registrationConfig;
    private Boolean setAsActive;
    private String verificationCertificate;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof RegisterCACertificateRequest)) {
            return false;
        }
        RegisterCACertificateRequest registerCACertificateRequest = (RegisterCACertificateRequest) obj;
        if ((registerCACertificateRequest.getCaCertificate() == null) ^ (getCaCertificate() == null)) {
            return false;
        }
        if (registerCACertificateRequest.getCaCertificate() != null && !registerCACertificateRequest.getCaCertificate().equals(getCaCertificate())) {
            return false;
        }
        if ((registerCACertificateRequest.getVerificationCertificate() == null) ^ (getVerificationCertificate() == null)) {
            return false;
        }
        if (registerCACertificateRequest.getVerificationCertificate() != null && !registerCACertificateRequest.getVerificationCertificate().equals(getVerificationCertificate())) {
            return false;
        }
        if ((registerCACertificateRequest.getSetAsActive() == null) ^ (getSetAsActive() == null)) {
            return false;
        }
        if (registerCACertificateRequest.getSetAsActive() != null && !registerCACertificateRequest.getSetAsActive().equals(getSetAsActive())) {
            return false;
        }
        if ((registerCACertificateRequest.getAllowAutoRegistration() == null) ^ (getAllowAutoRegistration() == null)) {
            return false;
        }
        if (registerCACertificateRequest.getAllowAutoRegistration() != null && !registerCACertificateRequest.getAllowAutoRegistration().equals(getAllowAutoRegistration())) {
            return false;
        }
        if ((registerCACertificateRequest.getRegistrationConfig() == null) ^ (getRegistrationConfig() == null)) {
            return false;
        }
        return registerCACertificateRequest.getRegistrationConfig() == null || registerCACertificateRequest.getRegistrationConfig().equals(getRegistrationConfig());
    }

    public Boolean getAllowAutoRegistration() {
        return this.allowAutoRegistration;
    }

    public String getCaCertificate() {
        return this.caCertificate;
    }

    public RegistrationConfig getRegistrationConfig() {
        return this.registrationConfig;
    }

    public Boolean getSetAsActive() {
        return this.setAsActive;
    }

    public String getVerificationCertificate() {
        return this.verificationCertificate;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((((((getCaCertificate() == null ? 0 : getCaCertificate().hashCode()) + 31) * 31) + (getVerificationCertificate() == null ? 0 : getVerificationCertificate().hashCode())) * 31) + (getSetAsActive() == null ? 0 : getSetAsActive().hashCode())) * 31) + (getAllowAutoRegistration() == null ? 0 : getAllowAutoRegistration().hashCode())) * 31;
        if (getRegistrationConfig() != null) {
            i = getRegistrationConfig().hashCode();
        }
        return hashCode + i;
    }

    public Boolean isAllowAutoRegistration() {
        return this.allowAutoRegistration;
    }

    public Boolean isSetAsActive() {
        return this.setAsActive;
    }

    public void setAllowAutoRegistration(Boolean bool) {
        this.allowAutoRegistration = bool;
    }

    public void setCaCertificate(String str) {
        this.caCertificate = str;
    }

    public void setRegistrationConfig(RegistrationConfig registrationConfig) {
        this.registrationConfig = registrationConfig;
    }

    public void setSetAsActive(Boolean bool) {
        this.setAsActive = bool;
    }

    public void setVerificationCertificate(String str) {
        this.verificationCertificate = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getCaCertificate() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("caCertificate: ");
            outline1072.append(getCaCertificate());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getVerificationCertificate() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("verificationCertificate: ");
            outline1073.append(getVerificationCertificate());
            outline1073.append(",");
            outline107.append(outline1073.toString());
        }
        if (getSetAsActive() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("setAsActive: ");
            outline1074.append(getSetAsActive());
            outline1074.append(",");
            outline107.append(outline1074.toString());
        }
        if (getAllowAutoRegistration() != null) {
            StringBuilder outline1075 = GeneratedOutlineSupport1.outline107("allowAutoRegistration: ");
            outline1075.append(getAllowAutoRegistration());
            outline1075.append(",");
            outline107.append(outline1075.toString());
        }
        if (getRegistrationConfig() != null) {
            StringBuilder outline1076 = GeneratedOutlineSupport1.outline107("registrationConfig: ");
            outline1076.append(getRegistrationConfig());
            outline107.append(outline1076.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public RegisterCACertificateRequest withAllowAutoRegistration(Boolean bool) {
        this.allowAutoRegistration = bool;
        return this;
    }

    public RegisterCACertificateRequest withCaCertificate(String str) {
        this.caCertificate = str;
        return this;
    }

    public RegisterCACertificateRequest withRegistrationConfig(RegistrationConfig registrationConfig) {
        this.registrationConfig = registrationConfig;
        return this;
    }

    public RegisterCACertificateRequest withSetAsActive(Boolean bool) {
        this.setAsActive = bool;
        return this;
    }

    public RegisterCACertificateRequest withVerificationCertificate(String str) {
        this.verificationCertificate = str;
        return this;
    }
}
