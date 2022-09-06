package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class DescribeCACertificateResult implements Serializable {
    private CACertificateDescription certificateDescription;
    private RegistrationConfig registrationConfig;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof DescribeCACertificateResult)) {
            return false;
        }
        DescribeCACertificateResult describeCACertificateResult = (DescribeCACertificateResult) obj;
        if ((describeCACertificateResult.getCertificateDescription() == null) ^ (getCertificateDescription() == null)) {
            return false;
        }
        if (describeCACertificateResult.getCertificateDescription() != null && !describeCACertificateResult.getCertificateDescription().equals(getCertificateDescription())) {
            return false;
        }
        if ((describeCACertificateResult.getRegistrationConfig() == null) ^ (getRegistrationConfig() == null)) {
            return false;
        }
        return describeCACertificateResult.getRegistrationConfig() == null || describeCACertificateResult.getRegistrationConfig().equals(getRegistrationConfig());
    }

    public CACertificateDescription getCertificateDescription() {
        return this.certificateDescription;
    }

    public RegistrationConfig getRegistrationConfig() {
        return this.registrationConfig;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((getCertificateDescription() == null ? 0 : getCertificateDescription().hashCode()) + 31) * 31;
        if (getRegistrationConfig() != null) {
            i = getRegistrationConfig().hashCode();
        }
        return hashCode + i;
    }

    public void setCertificateDescription(CACertificateDescription cACertificateDescription) {
        this.certificateDescription = cACertificateDescription;
    }

    public void setRegistrationConfig(RegistrationConfig registrationConfig) {
        this.registrationConfig = registrationConfig;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getCertificateDescription() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("certificateDescription: ");
            outline1072.append(getCertificateDescription());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getRegistrationConfig() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("registrationConfig: ");
            outline1073.append(getRegistrationConfig());
            outline107.append(outline1073.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public DescribeCACertificateResult withCertificateDescription(CACertificateDescription cACertificateDescription) {
        this.certificateDescription = cACertificateDescription;
        return this;
    }

    public DescribeCACertificateResult withRegistrationConfig(RegistrationConfig registrationConfig) {
        this.registrationConfig = registrationConfig;
        return this;
    }
}
