package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import java.util.Date;
/* loaded from: classes13.dex */
public class CertificateDescription implements Serializable {
    private String caCertificateId;
    private String certificateArn;
    private String certificateId;
    private String certificatePem;
    private Date creationDate;
    private Integer customerVersion;
    private String generationId;
    private Date lastModifiedDate;
    private String ownedBy;
    private String previousOwnedBy;
    private String status;
    private TransferData transferData;
    private CertificateValidity validity;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof CertificateDescription)) {
            return false;
        }
        CertificateDescription certificateDescription = (CertificateDescription) obj;
        if ((certificateDescription.getCertificateArn() == null) ^ (getCertificateArn() == null)) {
            return false;
        }
        if (certificateDescription.getCertificateArn() != null && !certificateDescription.getCertificateArn().equals(getCertificateArn())) {
            return false;
        }
        if ((certificateDescription.getCertificateId() == null) ^ (getCertificateId() == null)) {
            return false;
        }
        if (certificateDescription.getCertificateId() != null && !certificateDescription.getCertificateId().equals(getCertificateId())) {
            return false;
        }
        if ((certificateDescription.getCaCertificateId() == null) ^ (getCaCertificateId() == null)) {
            return false;
        }
        if (certificateDescription.getCaCertificateId() != null && !certificateDescription.getCaCertificateId().equals(getCaCertificateId())) {
            return false;
        }
        if ((certificateDescription.getStatus() == null) ^ (getStatus() == null)) {
            return false;
        }
        if (certificateDescription.getStatus() != null && !certificateDescription.getStatus().equals(getStatus())) {
            return false;
        }
        if ((certificateDescription.getCertificatePem() == null) ^ (getCertificatePem() == null)) {
            return false;
        }
        if (certificateDescription.getCertificatePem() != null && !certificateDescription.getCertificatePem().equals(getCertificatePem())) {
            return false;
        }
        if ((certificateDescription.getOwnedBy() == null) ^ (getOwnedBy() == null)) {
            return false;
        }
        if (certificateDescription.getOwnedBy() != null && !certificateDescription.getOwnedBy().equals(getOwnedBy())) {
            return false;
        }
        if ((certificateDescription.getPreviousOwnedBy() == null) ^ (getPreviousOwnedBy() == null)) {
            return false;
        }
        if (certificateDescription.getPreviousOwnedBy() != null && !certificateDescription.getPreviousOwnedBy().equals(getPreviousOwnedBy())) {
            return false;
        }
        if ((certificateDescription.getCreationDate() == null) ^ (getCreationDate() == null)) {
            return false;
        }
        if (certificateDescription.getCreationDate() != null && !certificateDescription.getCreationDate().equals(getCreationDate())) {
            return false;
        }
        if ((certificateDescription.getLastModifiedDate() == null) ^ (getLastModifiedDate() == null)) {
            return false;
        }
        if (certificateDescription.getLastModifiedDate() != null && !certificateDescription.getLastModifiedDate().equals(getLastModifiedDate())) {
            return false;
        }
        if ((certificateDescription.getCustomerVersion() == null) ^ (getCustomerVersion() == null)) {
            return false;
        }
        if (certificateDescription.getCustomerVersion() != null && !certificateDescription.getCustomerVersion().equals(getCustomerVersion())) {
            return false;
        }
        if ((certificateDescription.getTransferData() == null) ^ (getTransferData() == null)) {
            return false;
        }
        if (certificateDescription.getTransferData() != null && !certificateDescription.getTransferData().equals(getTransferData())) {
            return false;
        }
        if ((certificateDescription.getGenerationId() == null) ^ (getGenerationId() == null)) {
            return false;
        }
        if (certificateDescription.getGenerationId() != null && !certificateDescription.getGenerationId().equals(getGenerationId())) {
            return false;
        }
        if ((certificateDescription.getValidity() == null) ^ (getValidity() == null)) {
            return false;
        }
        return certificateDescription.getValidity() == null || certificateDescription.getValidity().equals(getValidity());
    }

    public String getCaCertificateId() {
        return this.caCertificateId;
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

    public String getPreviousOwnedBy() {
        return this.previousOwnedBy;
    }

    public String getStatus() {
        return this.status;
    }

    public TransferData getTransferData() {
        return this.transferData;
    }

    public CertificateValidity getValidity() {
        return this.validity;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((((((((((((((((((((((getCertificateArn() == null ? 0 : getCertificateArn().hashCode()) + 31) * 31) + (getCertificateId() == null ? 0 : getCertificateId().hashCode())) * 31) + (getCaCertificateId() == null ? 0 : getCaCertificateId().hashCode())) * 31) + (getStatus() == null ? 0 : getStatus().hashCode())) * 31) + (getCertificatePem() == null ? 0 : getCertificatePem().hashCode())) * 31) + (getOwnedBy() == null ? 0 : getOwnedBy().hashCode())) * 31) + (getPreviousOwnedBy() == null ? 0 : getPreviousOwnedBy().hashCode())) * 31) + (getCreationDate() == null ? 0 : getCreationDate().hashCode())) * 31) + (getLastModifiedDate() == null ? 0 : getLastModifiedDate().hashCode())) * 31) + (getCustomerVersion() == null ? 0 : getCustomerVersion().hashCode())) * 31) + (getTransferData() == null ? 0 : getTransferData().hashCode())) * 31) + (getGenerationId() == null ? 0 : getGenerationId().hashCode())) * 31;
        if (getValidity() != null) {
            i = getValidity().hashCode();
        }
        return hashCode + i;
    }

    public void setCaCertificateId(String str) {
        this.caCertificateId = str;
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

    public void setPreviousOwnedBy(String str) {
        this.previousOwnedBy = str;
    }

    public void setStatus(String str) {
        this.status = str;
    }

    public void setTransferData(TransferData transferData) {
        this.transferData = transferData;
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
        if (getCaCertificateId() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("caCertificateId: ");
            outline1074.append(getCaCertificateId());
            outline1074.append(",");
            outline107.append(outline1074.toString());
        }
        if (getStatus() != null) {
            StringBuilder outline1075 = GeneratedOutlineSupport1.outline107("status: ");
            outline1075.append(getStatus());
            outline1075.append(",");
            outline107.append(outline1075.toString());
        }
        if (getCertificatePem() != null) {
            StringBuilder outline1076 = GeneratedOutlineSupport1.outline107("certificatePem: ");
            outline1076.append(getCertificatePem());
            outline1076.append(",");
            outline107.append(outline1076.toString());
        }
        if (getOwnedBy() != null) {
            StringBuilder outline1077 = GeneratedOutlineSupport1.outline107("ownedBy: ");
            outline1077.append(getOwnedBy());
            outline1077.append(",");
            outline107.append(outline1077.toString());
        }
        if (getPreviousOwnedBy() != null) {
            StringBuilder outline1078 = GeneratedOutlineSupport1.outline107("previousOwnedBy: ");
            outline1078.append(getPreviousOwnedBy());
            outline1078.append(",");
            outline107.append(outline1078.toString());
        }
        if (getCreationDate() != null) {
            StringBuilder outline1079 = GeneratedOutlineSupport1.outline107("creationDate: ");
            outline1079.append(getCreationDate());
            outline1079.append(",");
            outline107.append(outline1079.toString());
        }
        if (getLastModifiedDate() != null) {
            StringBuilder outline10710 = GeneratedOutlineSupport1.outline107("lastModifiedDate: ");
            outline10710.append(getLastModifiedDate());
            outline10710.append(",");
            outline107.append(outline10710.toString());
        }
        if (getCustomerVersion() != null) {
            StringBuilder outline10711 = GeneratedOutlineSupport1.outline107("customerVersion: ");
            outline10711.append(getCustomerVersion());
            outline10711.append(",");
            outline107.append(outline10711.toString());
        }
        if (getTransferData() != null) {
            StringBuilder outline10712 = GeneratedOutlineSupport1.outline107("transferData: ");
            outline10712.append(getTransferData());
            outline10712.append(",");
            outline107.append(outline10712.toString());
        }
        if (getGenerationId() != null) {
            StringBuilder outline10713 = GeneratedOutlineSupport1.outline107("generationId: ");
            outline10713.append(getGenerationId());
            outline10713.append(",");
            outline107.append(outline10713.toString());
        }
        if (getValidity() != null) {
            StringBuilder outline10714 = GeneratedOutlineSupport1.outline107("validity: ");
            outline10714.append(getValidity());
            outline107.append(outline10714.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public CertificateDescription withCaCertificateId(String str) {
        this.caCertificateId = str;
        return this;
    }

    public CertificateDescription withCertificateArn(String str) {
        this.certificateArn = str;
        return this;
    }

    public CertificateDescription withCertificateId(String str) {
        this.certificateId = str;
        return this;
    }

    public CertificateDescription withCertificatePem(String str) {
        this.certificatePem = str;
        return this;
    }

    public CertificateDescription withCreationDate(Date date) {
        this.creationDate = date;
        return this;
    }

    public CertificateDescription withCustomerVersion(Integer num) {
        this.customerVersion = num;
        return this;
    }

    public CertificateDescription withGenerationId(String str) {
        this.generationId = str;
        return this;
    }

    public CertificateDescription withLastModifiedDate(Date date) {
        this.lastModifiedDate = date;
        return this;
    }

    public CertificateDescription withOwnedBy(String str) {
        this.ownedBy = str;
        return this;
    }

    public CertificateDescription withPreviousOwnedBy(String str) {
        this.previousOwnedBy = str;
        return this;
    }

    public CertificateDescription withStatus(String str) {
        this.status = str;
        return this;
    }

    public CertificateDescription withTransferData(TransferData transferData) {
        this.transferData = transferData;
        return this;
    }

    public CertificateDescription withValidity(CertificateValidity certificateValidity) {
        this.validity = certificateValidity;
        return this;
    }

    public void setStatus(CertificateStatus certificateStatus) {
        this.status = certificateStatus.toString();
    }

    public CertificateDescription withStatus(CertificateStatus certificateStatus) {
        this.status = certificateStatus.toString();
        return this;
    }
}
