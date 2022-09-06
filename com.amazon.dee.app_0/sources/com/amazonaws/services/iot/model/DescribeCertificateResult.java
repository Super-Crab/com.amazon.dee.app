package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class DescribeCertificateResult implements Serializable {
    private CertificateDescription certificateDescription;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof DescribeCertificateResult)) {
            return false;
        }
        DescribeCertificateResult describeCertificateResult = (DescribeCertificateResult) obj;
        if ((describeCertificateResult.getCertificateDescription() == null) ^ (getCertificateDescription() == null)) {
            return false;
        }
        return describeCertificateResult.getCertificateDescription() == null || describeCertificateResult.getCertificateDescription().equals(getCertificateDescription());
    }

    public CertificateDescription getCertificateDescription() {
        return this.certificateDescription;
    }

    public int hashCode() {
        return 31 + (getCertificateDescription() == null ? 0 : getCertificateDescription().hashCode());
    }

    public void setCertificateDescription(CertificateDescription certificateDescription) {
        this.certificateDescription = certificateDescription;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getCertificateDescription() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("certificateDescription: ");
            outline1072.append(getCertificateDescription());
            outline107.append(outline1072.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public DescribeCertificateResult withCertificateDescription(CertificateDescription certificateDescription) {
        this.certificateDescription = certificateDescription;
        return this;
    }
}
