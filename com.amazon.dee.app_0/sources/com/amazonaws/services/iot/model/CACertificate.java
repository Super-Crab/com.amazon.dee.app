package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import java.util.Date;
/* loaded from: classes13.dex */
public class CACertificate implements Serializable {
    private String certificateArn;
    private String certificateId;
    private Date creationDate;
    private String status;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof CACertificate)) {
            return false;
        }
        CACertificate cACertificate = (CACertificate) obj;
        if ((cACertificate.getCertificateArn() == null) ^ (getCertificateArn() == null)) {
            return false;
        }
        if (cACertificate.getCertificateArn() != null && !cACertificate.getCertificateArn().equals(getCertificateArn())) {
            return false;
        }
        if ((cACertificate.getCertificateId() == null) ^ (getCertificateId() == null)) {
            return false;
        }
        if (cACertificate.getCertificateId() != null && !cACertificate.getCertificateId().equals(getCertificateId())) {
            return false;
        }
        if ((cACertificate.getStatus() == null) ^ (getStatus() == null)) {
            return false;
        }
        if (cACertificate.getStatus() != null && !cACertificate.getStatus().equals(getStatus())) {
            return false;
        }
        if ((cACertificate.getCreationDate() == null) ^ (getCreationDate() == null)) {
            return false;
        }
        return cACertificate.getCreationDate() == null || cACertificate.getCreationDate().equals(getCreationDate());
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

    public CACertificate withCertificateArn(String str) {
        this.certificateArn = str;
        return this;
    }

    public CACertificate withCertificateId(String str) {
        this.certificateId = str;
        return this;
    }

    public CACertificate withCreationDate(Date date) {
        this.creationDate = date;
        return this;
    }

    public CACertificate withStatus(String str) {
        this.status = str;
        return this;
    }

    public void setStatus(CACertificateStatus cACertificateStatus) {
        this.status = cACertificateStatus.toString();
    }

    public CACertificate withStatus(CACertificateStatus cACertificateStatus) {
        this.status = cACertificateStatus.toString();
        return this;
    }
}
