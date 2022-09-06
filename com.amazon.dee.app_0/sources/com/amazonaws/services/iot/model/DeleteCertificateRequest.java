package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazonaws.AmazonWebServiceRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class DeleteCertificateRequest extends AmazonWebServiceRequest implements Serializable {
    private String certificateId;
    private Boolean forceDelete;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof DeleteCertificateRequest)) {
            return false;
        }
        DeleteCertificateRequest deleteCertificateRequest = (DeleteCertificateRequest) obj;
        if ((deleteCertificateRequest.getCertificateId() == null) ^ (getCertificateId() == null)) {
            return false;
        }
        if (deleteCertificateRequest.getCertificateId() != null && !deleteCertificateRequest.getCertificateId().equals(getCertificateId())) {
            return false;
        }
        if ((deleteCertificateRequest.getForceDelete() == null) ^ (getForceDelete() == null)) {
            return false;
        }
        return deleteCertificateRequest.getForceDelete() == null || deleteCertificateRequest.getForceDelete().equals(getForceDelete());
    }

    public String getCertificateId() {
        return this.certificateId;
    }

    public Boolean getForceDelete() {
        return this.forceDelete;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((getCertificateId() == null ? 0 : getCertificateId().hashCode()) + 31) * 31;
        if (getForceDelete() != null) {
            i = getForceDelete().hashCode();
        }
        return hashCode + i;
    }

    public Boolean isForceDelete() {
        return this.forceDelete;
    }

    public void setCertificateId(String str) {
        this.certificateId = str;
    }

    public void setForceDelete(Boolean bool) {
        this.forceDelete = bool;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getCertificateId() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("certificateId: ");
            outline1072.append(getCertificateId());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getForceDelete() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("forceDelete: ");
            outline1073.append(getForceDelete());
            outline107.append(outline1073.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public DeleteCertificateRequest withCertificateId(String str) {
        this.certificateId = str;
        return this;
    }

    public DeleteCertificateRequest withForceDelete(Boolean bool) {
        this.forceDelete = bool;
        return this;
    }
}
