package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class RegisterCACertificateResult implements Serializable {
    private String certificateArn;
    private String certificateId;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof RegisterCACertificateResult)) {
            return false;
        }
        RegisterCACertificateResult registerCACertificateResult = (RegisterCACertificateResult) obj;
        if ((registerCACertificateResult.getCertificateArn() == null) ^ (getCertificateArn() == null)) {
            return false;
        }
        if (registerCACertificateResult.getCertificateArn() != null && !registerCACertificateResult.getCertificateArn().equals(getCertificateArn())) {
            return false;
        }
        if ((registerCACertificateResult.getCertificateId() == null) ^ (getCertificateId() == null)) {
            return false;
        }
        return registerCACertificateResult.getCertificateId() == null || registerCACertificateResult.getCertificateId().equals(getCertificateId());
    }

    public String getCertificateArn() {
        return this.certificateArn;
    }

    public String getCertificateId() {
        return this.certificateId;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((getCertificateArn() == null ? 0 : getCertificateArn().hashCode()) + 31) * 31;
        if (getCertificateId() != null) {
            i = getCertificateId().hashCode();
        }
        return hashCode + i;
    }

    public void setCertificateArn(String str) {
        this.certificateArn = str;
    }

    public void setCertificateId(String str) {
        this.certificateId = str;
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
            outline107.append(outline1073.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public RegisterCACertificateResult withCertificateArn(String str) {
        this.certificateArn = str;
        return this;
    }

    public RegisterCACertificateResult withCertificateId(String str) {
        this.certificateId = str;
        return this;
    }
}
