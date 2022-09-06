package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import java.util.Date;
/* loaded from: classes13.dex */
public class OutgoingCertificate implements Serializable {
    private String certificateArn;
    private String certificateId;
    private Date creationDate;
    private Date transferDate;
    private String transferMessage;
    private String transferredTo;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof OutgoingCertificate)) {
            return false;
        }
        OutgoingCertificate outgoingCertificate = (OutgoingCertificate) obj;
        if ((outgoingCertificate.getCertificateArn() == null) ^ (getCertificateArn() == null)) {
            return false;
        }
        if (outgoingCertificate.getCertificateArn() != null && !outgoingCertificate.getCertificateArn().equals(getCertificateArn())) {
            return false;
        }
        if ((outgoingCertificate.getCertificateId() == null) ^ (getCertificateId() == null)) {
            return false;
        }
        if (outgoingCertificate.getCertificateId() != null && !outgoingCertificate.getCertificateId().equals(getCertificateId())) {
            return false;
        }
        if ((outgoingCertificate.getTransferredTo() == null) ^ (getTransferredTo() == null)) {
            return false;
        }
        if (outgoingCertificate.getTransferredTo() != null && !outgoingCertificate.getTransferredTo().equals(getTransferredTo())) {
            return false;
        }
        if ((outgoingCertificate.getTransferDate() == null) ^ (getTransferDate() == null)) {
            return false;
        }
        if (outgoingCertificate.getTransferDate() != null && !outgoingCertificate.getTransferDate().equals(getTransferDate())) {
            return false;
        }
        if ((outgoingCertificate.getTransferMessage() == null) ^ (getTransferMessage() == null)) {
            return false;
        }
        if (outgoingCertificate.getTransferMessage() != null && !outgoingCertificate.getTransferMessage().equals(getTransferMessage())) {
            return false;
        }
        if ((outgoingCertificate.getCreationDate() == null) ^ (getCreationDate() == null)) {
            return false;
        }
        return outgoingCertificate.getCreationDate() == null || outgoingCertificate.getCreationDate().equals(getCreationDate());
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

    public Date getTransferDate() {
        return this.transferDate;
    }

    public String getTransferMessage() {
        return this.transferMessage;
    }

    public String getTransferredTo() {
        return this.transferredTo;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((((((((getCertificateArn() == null ? 0 : getCertificateArn().hashCode()) + 31) * 31) + (getCertificateId() == null ? 0 : getCertificateId().hashCode())) * 31) + (getTransferredTo() == null ? 0 : getTransferredTo().hashCode())) * 31) + (getTransferDate() == null ? 0 : getTransferDate().hashCode())) * 31) + (getTransferMessage() == null ? 0 : getTransferMessage().hashCode())) * 31;
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

    public void setTransferDate(Date date) {
        this.transferDate = date;
    }

    public void setTransferMessage(String str) {
        this.transferMessage = str;
    }

    public void setTransferredTo(String str) {
        this.transferredTo = str;
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
        if (getTransferredTo() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("transferredTo: ");
            outline1074.append(getTransferredTo());
            outline1074.append(",");
            outline107.append(outline1074.toString());
        }
        if (getTransferDate() != null) {
            StringBuilder outline1075 = GeneratedOutlineSupport1.outline107("transferDate: ");
            outline1075.append(getTransferDate());
            outline1075.append(",");
            outline107.append(outline1075.toString());
        }
        if (getTransferMessage() != null) {
            StringBuilder outline1076 = GeneratedOutlineSupport1.outline107("transferMessage: ");
            outline1076.append(getTransferMessage());
            outline1076.append(",");
            outline107.append(outline1076.toString());
        }
        if (getCreationDate() != null) {
            StringBuilder outline1077 = GeneratedOutlineSupport1.outline107("creationDate: ");
            outline1077.append(getCreationDate());
            outline107.append(outline1077.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public OutgoingCertificate withCertificateArn(String str) {
        this.certificateArn = str;
        return this;
    }

    public OutgoingCertificate withCertificateId(String str) {
        this.certificateId = str;
        return this;
    }

    public OutgoingCertificate withCreationDate(Date date) {
        this.creationDate = date;
        return this;
    }

    public OutgoingCertificate withTransferDate(Date date) {
        this.transferDate = date;
        return this;
    }

    public OutgoingCertificate withTransferMessage(String str) {
        this.transferMessage = str;
        return this;
    }

    public OutgoingCertificate withTransferredTo(String str) {
        this.transferredTo = str;
        return this;
    }
}
