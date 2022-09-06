package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazonaws.AmazonWebServiceRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class UpdateCertificateRequest extends AmazonWebServiceRequest implements Serializable {
    private String certificateId;
    private String newStatus;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof UpdateCertificateRequest)) {
            return false;
        }
        UpdateCertificateRequest updateCertificateRequest = (UpdateCertificateRequest) obj;
        if ((updateCertificateRequest.getCertificateId() == null) ^ (getCertificateId() == null)) {
            return false;
        }
        if (updateCertificateRequest.getCertificateId() != null && !updateCertificateRequest.getCertificateId().equals(getCertificateId())) {
            return false;
        }
        if ((updateCertificateRequest.getNewStatus() == null) ^ (getNewStatus() == null)) {
            return false;
        }
        return updateCertificateRequest.getNewStatus() == null || updateCertificateRequest.getNewStatus().equals(getNewStatus());
    }

    public String getCertificateId() {
        return this.certificateId;
    }

    public String getNewStatus() {
        return this.newStatus;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((getCertificateId() == null ? 0 : getCertificateId().hashCode()) + 31) * 31;
        if (getNewStatus() != null) {
            i = getNewStatus().hashCode();
        }
        return hashCode + i;
    }

    public void setCertificateId(String str) {
        this.certificateId = str;
    }

    public void setNewStatus(String str) {
        this.newStatus = str;
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
            outline107.append(outline1073.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public UpdateCertificateRequest withCertificateId(String str) {
        this.certificateId = str;
        return this;
    }

    public UpdateCertificateRequest withNewStatus(String str) {
        this.newStatus = str;
        return this;
    }

    public void setNewStatus(CertificateStatus certificateStatus) {
        this.newStatus = certificateStatus.toString();
    }

    public UpdateCertificateRequest withNewStatus(CertificateStatus certificateStatus) {
        this.newStatus = certificateStatus.toString();
        return this;
    }
}
