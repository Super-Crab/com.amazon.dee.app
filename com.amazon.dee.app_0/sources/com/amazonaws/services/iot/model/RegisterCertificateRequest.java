package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazonaws.AmazonWebServiceRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class RegisterCertificateRequest extends AmazonWebServiceRequest implements Serializable {
    private String caCertificatePem;
    private String certificatePem;
    private Boolean setAsActive;
    private String status;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof RegisterCertificateRequest)) {
            return false;
        }
        RegisterCertificateRequest registerCertificateRequest = (RegisterCertificateRequest) obj;
        if ((registerCertificateRequest.getCertificatePem() == null) ^ (getCertificatePem() == null)) {
            return false;
        }
        if (registerCertificateRequest.getCertificatePem() != null && !registerCertificateRequest.getCertificatePem().equals(getCertificatePem())) {
            return false;
        }
        if ((registerCertificateRequest.getCaCertificatePem() == null) ^ (getCaCertificatePem() == null)) {
            return false;
        }
        if (registerCertificateRequest.getCaCertificatePem() != null && !registerCertificateRequest.getCaCertificatePem().equals(getCaCertificatePem())) {
            return false;
        }
        if ((registerCertificateRequest.getSetAsActive() == null) ^ (getSetAsActive() == null)) {
            return false;
        }
        if (registerCertificateRequest.getSetAsActive() != null && !registerCertificateRequest.getSetAsActive().equals(getSetAsActive())) {
            return false;
        }
        if ((registerCertificateRequest.getStatus() == null) ^ (getStatus() == null)) {
            return false;
        }
        return registerCertificateRequest.getStatus() == null || registerCertificateRequest.getStatus().equals(getStatus());
    }

    public String getCaCertificatePem() {
        return this.caCertificatePem;
    }

    public String getCertificatePem() {
        return this.certificatePem;
    }

    public Boolean getSetAsActive() {
        return this.setAsActive;
    }

    public String getStatus() {
        return this.status;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((((getCertificatePem() == null ? 0 : getCertificatePem().hashCode()) + 31) * 31) + (getCaCertificatePem() == null ? 0 : getCaCertificatePem().hashCode())) * 31) + (getSetAsActive() == null ? 0 : getSetAsActive().hashCode())) * 31;
        if (getStatus() != null) {
            i = getStatus().hashCode();
        }
        return hashCode + i;
    }

    public Boolean isSetAsActive() {
        return this.setAsActive;
    }

    public void setCaCertificatePem(String str) {
        this.caCertificatePem = str;
    }

    public void setCertificatePem(String str) {
        this.certificatePem = str;
    }

    public void setSetAsActive(Boolean bool) {
        this.setAsActive = bool;
    }

    public void setStatus(String str) {
        this.status = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getCertificatePem() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("certificatePem: ");
            outline1072.append(getCertificatePem());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getCaCertificatePem() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("caCertificatePem: ");
            outline1073.append(getCaCertificatePem());
            outline1073.append(",");
            outline107.append(outline1073.toString());
        }
        if (getSetAsActive() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("setAsActive: ");
            outline1074.append(getSetAsActive());
            outline1074.append(",");
            outline107.append(outline1074.toString());
        }
        if (getStatus() != null) {
            StringBuilder outline1075 = GeneratedOutlineSupport1.outline107("status: ");
            outline1075.append(getStatus());
            outline107.append(outline1075.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public RegisterCertificateRequest withCaCertificatePem(String str) {
        this.caCertificatePem = str;
        return this;
    }

    public RegisterCertificateRequest withCertificatePem(String str) {
        this.certificatePem = str;
        return this;
    }

    public RegisterCertificateRequest withSetAsActive(Boolean bool) {
        this.setAsActive = bool;
        return this;
    }

    public RegisterCertificateRequest withStatus(String str) {
        this.status = str;
        return this;
    }

    public void setStatus(CertificateStatus certificateStatus) {
        this.status = certificateStatus.toString();
    }

    public RegisterCertificateRequest withStatus(CertificateStatus certificateStatus) {
        this.status = certificateStatus.toString();
        return this;
    }
}
