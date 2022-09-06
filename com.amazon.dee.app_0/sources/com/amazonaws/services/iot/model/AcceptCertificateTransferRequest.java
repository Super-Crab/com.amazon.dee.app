package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazonaws.AmazonWebServiceRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class AcceptCertificateTransferRequest extends AmazonWebServiceRequest implements Serializable {
    private String certificateId;
    private Boolean setAsActive;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof AcceptCertificateTransferRequest)) {
            return false;
        }
        AcceptCertificateTransferRequest acceptCertificateTransferRequest = (AcceptCertificateTransferRequest) obj;
        if ((acceptCertificateTransferRequest.getCertificateId() == null) ^ (getCertificateId() == null)) {
            return false;
        }
        if (acceptCertificateTransferRequest.getCertificateId() != null && !acceptCertificateTransferRequest.getCertificateId().equals(getCertificateId())) {
            return false;
        }
        if ((acceptCertificateTransferRequest.getSetAsActive() == null) ^ (getSetAsActive() == null)) {
            return false;
        }
        return acceptCertificateTransferRequest.getSetAsActive() == null || acceptCertificateTransferRequest.getSetAsActive().equals(getSetAsActive());
    }

    public String getCertificateId() {
        return this.certificateId;
    }

    public Boolean getSetAsActive() {
        return this.setAsActive;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((getCertificateId() == null ? 0 : getCertificateId().hashCode()) + 31) * 31;
        if (getSetAsActive() != null) {
            i = getSetAsActive().hashCode();
        }
        return hashCode + i;
    }

    public Boolean isSetAsActive() {
        return this.setAsActive;
    }

    public void setCertificateId(String str) {
        this.certificateId = str;
    }

    public void setSetAsActive(Boolean bool) {
        this.setAsActive = bool;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getCertificateId() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("certificateId: ");
            outline1072.append(getCertificateId());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getSetAsActive() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("setAsActive: ");
            outline1073.append(getSetAsActive());
            outline107.append(outline1073.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public AcceptCertificateTransferRequest withCertificateId(String str) {
        this.certificateId = str;
        return this;
    }

    public AcceptCertificateTransferRequest withSetAsActive(Boolean bool) {
        this.setAsActive = bool;
        return this;
    }
}
