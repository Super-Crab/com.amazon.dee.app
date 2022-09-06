package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class CreateCertificateFromCsrResult implements Serializable {
    private String certificateArn;
    private String certificateId;
    private String certificatePem;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof CreateCertificateFromCsrResult)) {
            return false;
        }
        CreateCertificateFromCsrResult createCertificateFromCsrResult = (CreateCertificateFromCsrResult) obj;
        if ((createCertificateFromCsrResult.getCertificateArn() == null) ^ (getCertificateArn() == null)) {
            return false;
        }
        if (createCertificateFromCsrResult.getCertificateArn() != null && !createCertificateFromCsrResult.getCertificateArn().equals(getCertificateArn())) {
            return false;
        }
        if ((createCertificateFromCsrResult.getCertificateId() == null) ^ (getCertificateId() == null)) {
            return false;
        }
        if (createCertificateFromCsrResult.getCertificateId() != null && !createCertificateFromCsrResult.getCertificateId().equals(getCertificateId())) {
            return false;
        }
        if ((createCertificateFromCsrResult.getCertificatePem() == null) ^ (getCertificatePem() == null)) {
            return false;
        }
        return createCertificateFromCsrResult.getCertificatePem() == null || createCertificateFromCsrResult.getCertificatePem().equals(getCertificatePem());
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

    public int hashCode() {
        int i = 0;
        int hashCode = ((((getCertificateArn() == null ? 0 : getCertificateArn().hashCode()) + 31) * 31) + (getCertificateId() == null ? 0 : getCertificateId().hashCode())) * 31;
        if (getCertificatePem() != null) {
            i = getCertificatePem().hashCode();
        }
        return hashCode + i;
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
        if (getCertificatePem() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("certificatePem: ");
            outline1074.append(getCertificatePem());
            outline107.append(outline1074.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public CreateCertificateFromCsrResult withCertificateArn(String str) {
        this.certificateArn = str;
        return this;
    }

    public CreateCertificateFromCsrResult withCertificateId(String str) {
        this.certificateId = str;
        return this;
    }

    public CreateCertificateFromCsrResult withCertificatePem(String str) {
        this.certificatePem = str;
        return this;
    }
}
