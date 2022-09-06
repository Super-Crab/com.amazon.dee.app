package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import java.util.Date;
/* loaded from: classes13.dex */
public class CACertificateDescription implements Serializable {
    private String autoRegistrationStatus;
    private String certificateArn;
    private String certificateId;
    private String certificatePem;
    private Date creationDate;
    private Integer customerVersion;
    private String generationId;
    private Date lastModifiedDate;
    private String ownedBy;
    private String status;
    private CertificateValidity validity;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof CACertificateDescription)) {
            return false;
        }
        CACertificateDescription cACertificateDescription = (CACertificateDescription) obj;
        if ((cACertificateDescription.getCertificateArn() == null) ^ (getCertificateArn() == null)) {
            return false;
        }
        if (cACertificateDescription.getCertificateArn() != null && !cACertificateDescription.getCertificateArn().equals(getCertificateArn())) {
            return false;
        }
        if ((cACertificateDescription.getCertificateId() == null) ^ (getCertificateId() == null)) {
            return false;
        }
        if (cACertificateDescription.getCertificateId() != null && !cACertificateDescription.getCertificateId().equals(getCertificateId())) {
            return false;
        }
        if ((cACertificateDescription.getStatus() == null) ^ (getStatus() == null)) {
            return false;
        }
        if (cACertificateDescription.getStatus() != null && !cACertificateDescription.getStatus().equals(getStatus())) {
            return false;
        }
        if ((cACertificateDescription.getCertificatePem() == null) ^ (getCertificatePem() == null)) {
            return false;
        }
        if (cACertificateDescription.getCertificatePem() != null && !cACertificateDescription.getCertificatePem().equals(getCertificatePem())) {
            return false;
        }
        if ((cACertificateDescription.getOwnedBy() == null) ^ (getOwnedBy() == null)) {
            return false;
        }
        if (cACertificateDescription.getOwnedBy() != null && !cACertificateDescription.getOwnedBy().equals(getOwnedBy())) {
            return false;
        }
        if ((cACertificateDescription.getCreationDate() == null) ^ (getCreationDate() == null)) {
            return false;
        }
        if (cACertificateDescription.getCreationDate() != null && !cACertificateDescription.getCreationDate().equals(getCreationDate())) {
            return false;
        }
        if ((cACertificateDescription.getAutoRegistrationStatus() == null) ^ (getAutoRegistrationStatus() == null)) {
            return false;
        }
        if (cACertificateDescription.getAutoRegistrationStatus() != null && !cACertificateDescription.getAutoRegistrationStatus().equals(getAutoRegistrationStatus())) {
            return false;
        }
        if ((cACertificateDescription.getLastModifiedDate() == null) ^ (getLastModifiedDate() == null)) {
            return false;
        }
        if (cACertificateDescription.getLastModifiedDate() != null && !cACertificateDescription.getLastModifiedDate().equals(getLastModifiedDate())) {
            return false;
        }
        if ((cACertificateDescription.getCustomerVersion() == null) ^ (getCustomerVersion() == null)) {
            return false;
        }
        if (cACertificateDescription.getCustomerVersion() != null && !cACertificateDescription.getCustomerVersion().equals(getCustomerVersion())) {
            return false;
        }
        if ((cACertificateDescription.getGenerationId() == null) ^ (getGenerationId() == null)) {
            return false;
        }
        if (cACertificateDescription.getGenerationId() != null && !cACertificateDescription.getGenerationId().equals(getGenerationId())) {
            return false;
        }
        if ((cACertificateDescription.getValidity() == null) ^ (getValidity() == null)) {
            return false;
        }
        return cACertificateDescription.getValidity() == null || cACertificateDescription.getValidity().equals(getValidity());
    }

    public String getAutoRegistrationStatus() {
        return this.autoRegistrationStatus;
    }

    public String getCertificateArn() {
        return this.certificateArn;
    }

    public String getCertificateId() {
        return this.certificateId;
    }

    public String getCertificatePem() {
        return this.certificatePem;
    }

    public Date getCreationDate() {
        return this.creationDate;
    }

    public Integer getCustomerVersion() {
        return this.customerVersion;
    }

    public String getGenerationId() {
        return this.generationId;
    }

    public Date getLastModifiedDate() {
        return this.lastModifiedDate;
    }

    public String getOwnedBy() {
        return this.ownedBy;
    }

    public String getStatus() {
        return this.status;
    }

    public CertificateValidity getValidity() {
        return this.validity;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((((((((((((((((((getCertificateArn() == null ? 0 : getCertificateArn().hashCode()) + 31) * 31) + (getCertificateId() == null ? 0 : getCertificateId().hashCode())) * 31) + (getStatus() == null ? 0 : getStatus().hashCode())) * 31) + (getCertificatePem() == null ? 0 : getCertificatePem().hashCode())) * 31) + (getOwnedBy() == null ? 0 : getOwnedBy().hashCode())) * 31) + (getCreationDate() == null ? 0 : getCreationDate().hashCode())) * 31) + (getAutoRegistrationStatus() == null ? 0 : getAutoRegistrationStatus().hashCode())) * 31) + (getLastModifiedDate() == null ? 0 : getLastModifiedDate().hashCode())) * 31) + (getCustomerVersion() == null ? 0 : getCustomerVersion().hashCode())) * 31) + (getGenerationId() == null ? 0 : getGenerationId().hashCode())) * 31;
        if (getValidity() != null) {
            i = getValidity().hashCode();
        }
        return hashCode + i;
    }

    public void setAutoRegistrationStatus(String str) {
        this.autoRegistrationStatus = str;
    }

    public void setCertificateArn(String str) {
        this.certificateArn = str;
    }

    public void setCertificateId(String str) {
        this.certificateId = str;
    }

    public void setCertificatePem(String str) {
        this.certificatePem = str;
    }

    public void setCreationDate(Date date) {
        this.creationDate = date;
    }

    public void setCustomerVersion(Integer num) {
        this.customerVersion = num;
    }

    public void setGenerationId(String str) {
        this.generationId = str;
    }

    public void setLastModifiedDate(Date date) {
        this.lastModifiedDate = date;
    }

    public void setOwnedBy(String str) {
        this.ownedBy = str;
    }

    public void setStatus(String str) {
        this.status = str;
    }

    public void setValidity(CertificateValidity certificateValidity) {
        this.validity = certificateValidity;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getCertificateArn() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("certificateArn: ");
            outline1072.append(getCertificateArn());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getCertificateId() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("certificateId: ");
            outline1073.append(getCertificateId());
            outline1073.append(",");
            outline107.append(outline1073.toString());
        }
        if (getStatus() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("status: ");
            outline1074.append(getStatus());
            outline1074.append(",");
            outline107.append(outline1074.toString());
        }
        if (getCertificatePem() != null) {
            StringBuilder outline1075 = GeneratedOutlineSupport1.outline107("certificatePem: ");
            outline1075.append(getCertificatePem());
            outline1075.append(",");
            outline107.append(outline1075.toString());
        }
        if (getOwnedBy() != null) {
            StringBuilder outline1076 = GeneratedOutlineSupport1.outline107("ownedBy: ");
            outline1076.append(getOwnedBy());
            outline1076.append(",");
            outline107.append(outline1076.toString());
        }
        if (getCreationDate() != null) {
            StringBuilder outline1077 = GeneratedOutlineSupport1.outline107("creationDate: ");
            outline1077.append(getCreationDate());
            outline1077.append(",");
            outline107.append(outline1077.toString());
        }
        if (getAutoRegistrationStatus() != null) {
            StringBuilder outline1078 = GeneratedOutlineSupport1.outline107("autoRegistrationStatus: ");
            outline1078.append(getAutoRegistrationStatus());
            outline1078.append(",");
            outline107.append(outline1078.toString());
        }
        if (getLastModifiedDate() != null) {
            StringBuilder outline1079 = GeneratedOutlineSupport1.outline107("lastModifiedDate: ");
            outline1079.append(getLastModifiedDate());
            outline1079.append(",");
            outline107.append(outline1079.toString());
        }
        if (getCustomerVersion() != null) {
            StringBuilder outline10710 = GeneratedOutlineSupport1.outline107("customerVersion: ");
            outline10710.append(getCustomerVersion());
            outline10710.append(",");
            outline107.append(outline10710.toString());
        }
        if (getGenerationId() != null) {
            StringBuilder outline10711 = GeneratedOutlineSupport1.outline107("generationId: ");
            outline10711.append(getGenerationId());
            outline10711.append(",");
            outline107.append(outline10711.toString());
        }
        if (getValidity() != null) {
            StringBuilder outline10712 = GeneratedOutlineSupport1.outline107("validity: ");
            outline10712.append(getValidity());
            outline107.append(outline10712.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public CACertificateDescription withAutoRegistrationStatus(String str) {
        this.autoRegistrationStatus = str;
        return this;
    }

    public CACertificateDescription withCertificateArn(String str) {
        this.certificateArn = str;
        return this;
    }

    public CACertificateDescription withCertificateId(String str) {
        this.certificateId = str;
        return this;
    }

    public CACertificateDescription withCertificatePem(String str) {
        this.certificatePem = str;
        return this;
    }

    public CACertificateDescription withCreationDate(Date date) {
        this.creationDate = date;
        return this;
    }

    public CACertificateDescription withCustomerVersion(Integer num) {
        this.customerVersion = num;
        return this;
    }

    public CACertificateDescription withGenerationId(String str) {
        this.generationId = str;
        return this;
    }

    public CACertificateDescription withLastModifiedDate(Date date) {
        this.lastModifiedDate = date;
        return this;
    }

    public CACertificateDescription withOwnedBy(String str) {
        this.ownedBy = str;
        return this;
    }

    public CACertificateDescription withStatus(String str) {
        this.status = str;
        return this;
    }

    public CACertificateDescription withValidity(CertificateValidity certificateValidity) {
        this.validity = certificateValidity;
        return this;
    }

    public void setAutoRegistrationStatus(AutoRegistrationStatus autoRegistrationStatus) {
        this.autoRegistrationStatus = autoRegistrationStatus.toString();
    }

    public void setStatus(CACertificateStatus cACertificateStatus) {
        this.status = cACertificateStatus.toString();
    }

    public CACertificateDescription withAutoRegistrationStatus(AutoRegistrationStatus autoRegistrationStatus) {
        this.autoRegistrationStatus = autoRegistrationStatus.toString();
        return this;
    }

    public CACertificateDescription withStatus(CACertificateStatus cACertificateStatus) {
        this.status = cACertificateStatus.toString();
        return this;
    }
}
