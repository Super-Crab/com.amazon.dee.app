package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import java.util.Date;
/* loaded from: classes13.dex */
public class Certificate implements Serializable {
    private String certificateArn;
    private String certificateId;
    private Date creationDate;
    private String status;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof Certificate)) {
            return false;
        }
        Certificate certificate = (Certificate) obj;
        if ((certificate.getCertificateArn() == null) ^ (getCertificateArn() == null)) {
            return false;
        }
        if (certificate.getCertificateArn() != null && !certificate.getCertificateArn().equals(getCertificateArn())) {
            return false;
        }
        if ((certificate.getCertificateId() == null) ^ (getCertificateId() == null)) {
            return false;
        }
        if (certificate.getCertificateId() != null && !certificate.getCertificateId().equals(getCertificateId())) {
            return false;
        }
        if ((certificate.getStatus() == null) ^ (getStatus() == null)) {
            return false;
        }
        if (certificate.getStatus() != null && !certificate.getStatus().equals(getStatus())) {
            return false;
        }
        if ((certificate.getCreationDate() == null) ^ (getCreationDate() == null)) {
            return false;
        }
        return certificate.getCreationDate() == null || certificate.getCreationDate().equals(getCreationDate());
    }

    public String getCertificateArn() {
        return this.certificateArn;
    }

    public String getCertificateId() {
        return this.certificateId;
    }

    public Date getCreationDate() {
        return this.creationDate;
    }

    public String getStatus() {
        return this.status;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((((getCertificateArn() == null ? 0 : getCertificateArn().hashCode()) + 31) * 31) + (getCertificateId() == null ? 0 : getCertificateId().hashCode())) * 31) + (getStatus() == null ? 0 : getStatus().hashCode())) * 31;
        if (getCreationDate() != null) {
            i = getCreationDate().hashCode();
        }
        return hashCode + i;
    }

    public void setCertificateArn(String str) {
        this.certificateArn = str;
    }

    public void setCertificateId(String str) {
        this.certificateId = str;
    }

    public void setCreationDate(Date date) {
        this.creationDate = date;
    }

    public void setStatus(String str) {
        this.status = str;
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
        if (getCreationDate() != null) {
            StringBuilder outline1075 = GeneratedOutlineSupport1.outline107("creationDate: ");
            outline1075.append(getCreationDate());
            outline107.append(outline1075.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public Certificate withCertificateArn(String str) {
        this.certificateArn = str;
        return this;
    }

    public Certificate withCertificateId(String str) {
        this.certificateId = str;
        return this;
    }

    public Certificate withCreationDate(Date date) {
        this.creationDate = date;
        return this;
    }

    public Certificate withStatus(String str) {
        this.status = str;
        return this;
    }

    public void setStatus(CertificateStatus certificateStatus) {
        this.status = certificateStatus.toString();
    }

    public Certificate withStatus(CertificateStatus certificateStatus) {
        this.status = certificateStatus.toString();
        return this;
    }
}
