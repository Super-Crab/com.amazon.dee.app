package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class SigningProfileParameter implements Serializable {
    private String certificateArn;
    private String certificatePathOnDevice;
    private String platform;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof SigningProfileParameter)) {
            return false;
        }
        SigningProfileParameter signingProfileParameter = (SigningProfileParameter) obj;
        if ((signingProfileParameter.getCertificateArn() == null) ^ (getCertificateArn() == null)) {
            return false;
        }
        if (signingProfileParameter.getCertificateArn() != null && !signingProfileParameter.getCertificateArn().equals(getCertificateArn())) {
            return false;
        }
        if ((signingProfileParameter.getPlatform() == null) ^ (getPlatform() == null)) {
            return false;
        }
        if (signingProfileParameter.getPlatform() != null && !signingProfileParameter.getPlatform().equals(getPlatform())) {
            return false;
        }
        if ((signingProfileParameter.getCertificatePathOnDevice() == null) ^ (getCertificatePathOnDevice() == null)) {
            return false;
        }
        return signingProfileParameter.getCertificatePathOnDevice() == null || signingProfileParameter.getCertificatePathOnDevice().equals(getCertificatePathOnDevice());
    }

    public String getCertificateArn() {
        return this.certificateArn;
    }

    public String getCertificatePathOnDevice() {
        return this.certificatePathOnDevice;
    }

    public String getPlatform() {
        return this.platform;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((getCertificateArn() == null ? 0 : getCertificateArn().hashCode()) + 31) * 31) + (getPlatform() == null ? 0 : getPlatform().hashCode())) * 31;
        if (getCertificatePathOnDevice() != null) {
            i = getCertificatePathOnDevice().hashCode();
        }
        return hashCode + i;
    }

    public void setCertificateArn(String str) {
        this.certificateArn = str;
    }

    public void setCertificatePathOnDevice(String str) {
        this.certificatePathOnDevice = str;
    }

    public void setPlatform(String str) {
        this.platform = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getCertificateArn() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("certificateArn: ");
            outline1072.append(getCertificateArn());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getPlatform() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("platform: ");
            outline1073.append(getPlatform());
            outline1073.append(",");
            outline107.append(outline1073.toString());
        }
        if (getCertificatePathOnDevice() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("certificatePathOnDevice: ");
            outline1074.append(getCertificatePathOnDevice());
            outline107.append(outline1074.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public SigningProfileParameter withCertificateArn(String str) {
        this.certificateArn = str;
        return this;
    }

    public SigningProfileParameter withCertificatePathOnDevice(String str) {
        this.certificatePathOnDevice = str;
        return this;
    }

    public SigningProfileParameter withPlatform(String str) {
        this.platform = str;
        return this;
    }
}
