package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazonaws.AmazonWebServiceRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class DeleteCACertificateRequest extends AmazonWebServiceRequest implements Serializable {
    private String certificateId;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof DeleteCACertificateRequest)) {
            return false;
        }
        DeleteCACertificateRequest deleteCACertificateRequest = (DeleteCACertificateRequest) obj;
        if ((deleteCACertificateRequest.getCertificateId() == null) ^ (getCertificateId() == null)) {
            return false;
        }
        return deleteCACertificateRequest.getCertificateId() == null || deleteCACertificateRequest.getCertificateId().equals(getCertificateId());
    }

    public String getCertificateId() {
        return this.certificateId;
    }

    public int hashCode() {
        return 31 + (getCertificateId() == null ? 0 : getCertificateId().hashCode());
    }

    public void setCertificateId(String str) {
        this.certificateId = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getCertificateId() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("certificateId: ");
            outline1072.append(getCertificateId());
            outline107.append(outline1072.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public DeleteCACertificateRequest withCertificateId(String str) {
        this.certificateId = str;
        return this;
    }
}
