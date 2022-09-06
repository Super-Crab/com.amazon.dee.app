package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazonaws.AmazonWebServiceRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class UpdateCACertificateRequest extends AmazonWebServiceRequest implements Serializable {
    private String certificateId;
    private String newAutoRegistrationStatus;
    private String newStatus;
    private RegistrationConfig registrationConfig;
    private Boolean removeAutoRegistration;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof UpdateCACertificateRequest)) {
            return false;
        }
        UpdateCACertificateRequest updateCACertificateRequest = (UpdateCACertificateRequest) obj;
        if ((updateCACertificateRequest.getCertificateId() == null) ^ (getCertificateId() == null)) {
            return false;
        }
        if (updateCACertificateRequest.getCertificateId() != null && !updateCACertificateRequest.getCertificateId().equals(getCertificateId())) {
            return false;
        }
        if ((updateCACertificateRequest.getNewStatus() == null) ^ (getNewStatus() == null)) {
            return false;
        }
        if (updateCACertificateRequest.getNewStatus() != null && !updateCACertificateRequest.getNewStatus().equals(getNewStatus())) {
            return false;
        }
        if ((updateCACertificateRequest.getNewAutoRegistrationStatus() == null) ^ (getNewAutoRegistrationStatus() == null)) {
            return false;
        }
        if (updateCACertificateRequest.getNewAutoRegistrationStatus() != null && !updateCACertificateRequest.getNewAutoRegistrationStatus().equals(getNewAutoRegistrationStatus())) {
            return false;
        }
        if ((updateCACertificateRequest.getRegistrationConfig() == null) ^ (getRegistrationConfig() == null)) {
            return false;
        }
        if (updateCACertificateRequest.getRegistrationConfig() != null && !updateCACertificateRequest.getRegistrationConfig().equals(getRegistrationConfig())) {
            return false;
        }
        if ((updateCACertificateRequest.getRemoveAutoRegistration() == null) ^ (getRemoveAutoRegistration() == null)) {
            return false;
        }
        return updateCACertificateRequest.getRemoveAutoRegistration() == null || updateCACertificateRequest.getRemoveAutoRegistration().equals(getRemoveAutoRegistration());
    }

    public String getCertificateId() {
        return this.certificateId;
    }

    public String getNewAutoRegistrationStatus() {
        return this.newAutoRegistrationStatus;
    }

    public String getNewStatus() {
        return this.newStatus;
    }

    public RegistrationConfig getRegistrationConfig() {
        return this.registrationConfig;
    }

    public Boolean getRemoveAutoRegistration() {
        return this.removeAutoRegistration;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((((((getCertificateId() == null ? 0 : getCertificateId().hashCode()) + 31) * 31) + (getNewStatus() == null ? 0 : getNewStatus().hashCode())) * 31) + (getNewAutoRegistrationStatus() == null ? 0 : getNewAutoRegistrationStatus().hashCode())) * 31) + (getRegistrationConfig() == null ? 0 : getRegistrationConfig().hashCode())) * 31;
        if (getRemoveAutoRegistration() != null) {
            i = getRemoveAutoRegistration().hashCode();
        }
        return hashCode + i;
    }

    public Boolean isRemoveAutoRegistration() {
        return this.removeAutoRegistration;
    }

    public void setCertificateId(String str) {
        this.certificateId = str;
    }

    public void setNewAutoRegistrationStatus(String str) {
        this.newAutoRegistrationStatus = str;
    }

    public void setNewStatus(String str) {
        this.newStatus = str;
    }

    public void setRegistrationConfig(RegistrationConfig registrationConfig) {
        this.registrationConfig = registrationConfig;
    }

    public void setRemoveAutoRegistration(Boolean bool) {
        this.removeAutoRegistration = bool;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getCertificateId() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("certificateId: ");
            outline1072.append(getCertificateId());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getNewStatus() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("newStatus: ");
            outline1073.append(getNewStatus());
            outline1073.append(",");
            outline107.append(outline1073.toString());
        }
        if (getNewAutoRegistrationStatus() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("newAutoRegistrationStatus: ");
            outline1074.append(getNewAutoRegistrationStatus());
            outline1074.append(",");
            outline107.append(outline1074.toString());
        }
        if (getRegistrationConfig() != null) {
            StringBuilder outline1075 = GeneratedOutlineSupport1.outline107("registrationConfig: ");
            outline1075.append(getRegistrationConfig());
            outline1075.append(",");
            outline107.append(outline1075.toString());
        }
        if (getRemoveAutoRegistration() != null) {
            StringBuilder outline1076 = GeneratedOutlineSupport1.outline107("removeAutoRegistration: ");
            outline1076.append(getRemoveAutoRegistration());
            outline107.append(outline1076.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public UpdateCACertificateRequest withCertificateId(String str) {
        this.certificateId = str;
        return this;
    }

    public UpdateCACertificateRequest withNewAutoRegistrationStatus(String str) {
        this.newAutoRegistrationStatus = str;
        return this;
    }

    public UpdateCACertificateRequest withNewStatus(String str) {
        this.newStatus = str;
        return this;
    }

    public UpdateCACertificateRequest withRegistrationConfig(RegistrationConfig registrationConfig) {
        this.registrationConfig = registrationConfig;
        return this;
    }

    public UpdateCACertificateRequest withRemoveAutoRegistration(Boolean bool) {
        this.removeAutoRegistration = bool;
        return this;
    }

    public void setNewAutoRegistrationStatus(AutoRegistrationStatus autoRegistrationStatus) {
        this.newAutoRegistrationStatus = autoRegistrationStatus.toString();
    }

    public void setNewStatus(CACertificateStatus cACertificateStatus) {
        this.newStatus = cACertificateStatus.toString();
    }

    public UpdateCACertificateRequest withNewAutoRegistrationStatus(AutoRegistrationStatus autoRegistrationStatus) {
        this.newAutoRegistrationStatus = autoRegistrationStatus.toString();
        return this;
    }

    public UpdateCACertificateRequest withNewStatus(CACertificateStatus cACertificateStatus) {
        this.newStatus = cACertificateStatus.toString();
        return this;
    }
}
