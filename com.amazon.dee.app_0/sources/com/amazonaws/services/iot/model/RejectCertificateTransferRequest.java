package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazonaws.AmazonWebServiceRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class RejectCertificateTransferRequest extends AmazonWebServiceRequest implements Serializable {
    private String certificateId;
    private String rejectReason;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof RejectCertificateTransferRequest)) {
            return false;
        }
        RejectCertificateTransferRequest rejectCertificateTransferRequest = (RejectCertificateTransferRequest) obj;
        if ((rejectCertificateTransferRequest.getCertificateId() == null) ^ (getCertificateId() == null)) {
            return false;
        }
        if (rejectCertificateTransferRequest.getCertificateId() != null && !rejectCertificateTransferRequest.getCertificateId().equals(getCertificateId())) {
            return false;
        }
        if ((rejectCertificateTransferRequest.getRejectReason() == null) ^ (getRejectReason() == null)) {
            return false;
        }
        return rejectCertificateTransferRequest.getRejectReason() == null || rejectCertificateTransferRequest.getRejectReason().equals(getRejectReason());
    }

    public String getCertificateId() {
        return this.certificateId;
    }

    public String getRejectReason() {
        return this.rejectReason;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((getCertificateId() == null ? 0 : getCertificateId().hashCode()) + 31) * 31;
        if (getRejectReason() != null) {
            i = getRejectReason().hashCode();
        }
        return hashCode + i;
    }

    public void setCertificateId(String str) {
        this.certificateId = str;
    }

    public void setRejectReason(String str) {
        this.rejectReason = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getCertificateId() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("certificateId: ");
            outline1072.append(getCertificateId());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getRejectReason() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("rejectReason: ");
            outline1073.append(getRejectReason());
            outline107.append(outline1073.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public RejectCertificateTransferRequest withCertificateId(String str) {
        this.certificateId = str;
        return this;
    }

    public RejectCertificateTransferRequest withRejectReason(String str) {
        this.rejectReason = str;
        return this;
    }
}
