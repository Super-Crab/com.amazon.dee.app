package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class CreateKeysAndCertificateResult implements Serializable {
    private String certificateArn;
    private String certificateId;
    private String certificatePem;
    private KeyPair keyPair;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof CreateKeysAndCertificateResult)) {
            return false;
        }
        CreateKeysAndCertificateResult createKeysAndCertificateResult = (CreateKeysAndCertificateResult) obj;
        if ((createKeysAndCertificateResult.getCertificateArn() == null) ^ (getCertificateArn() == null)) {
            return false;
        }
        if (createKeysAndCertificateResult.getCertificateArn() != null && !createKeysAndCertificateResult.getCertificateArn().equals(getCertificateArn())) {
            return false;
        }
        if ((createKeysAndCertificateResult.getCertificateId() == null) ^ (getCertificateId() == null)) {
            return false;
        }
        if (createKeysAndCertificateResult.getCertificateId() != null && !createKeysAndCertificateResult.getCertificateId().equals(getCertificateId())) {
            return false;
        }
        if ((createKeysAndCertificateResult.getCertificatePem() == null) ^ (getCertificatePem() == null)) {
            return false;
        }
        if (createKeysAndCertificateResult.getCertificatePem() != null && !createKeysAndCertificateResult.getCertificatePem().equals(getCertificatePem())) {
            return false;
        }
        if ((createKeysAndCertificateResult.getKeyPair() == null) ^ (getKeyPair() == null)) {
            return false;
        }
        return createKeysAndCertificateResult.getKeyPair() == null || createKeysAndCertificateResult.getKeyPair().equals(getKeyPair());
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

    public KeyPair getKeyPair() {
        return this.keyPair;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((((getCertificateArn() == null ? 0 : getCertificateArn().hashCode()) + 31) * 31) + (getCertificateId() == null ? 0 : getCertificateId().hashCode())) * 31) + (getCertificatePem() == null ? 0 : getCertificatePem().hashCode())) * 31;
        if (getKeyPair() != null) {
            i = getKeyPair().hashCode();
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

    public void setKeyPair(KeyPair keyPair) {
        this.keyPair = keyPair;
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
            outline1074.append(",");
            outline107.append(outline1074.toString());
        }
        if (getKeyPair() != null) {
            StringBuilder outline1075 = GeneratedOutlineSupport1.outline107("keyPair: ");
            outline1075.append(getKeyPair());
            outline107.append(outline1075.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public CreateKeysAndCertificateResult withCertificateArn(String str) {
        this.certificateArn = str;
        return this;
    }

    public CreateKeysAndCertificateResult withCertificateId(String str) {
        this.certificateId = str;
        return this;
    }

    public CreateKeysAndCertificateResult withCertificatePem(String str) {
        this.certificatePem = str;
        return this;
    }

    public CreateKeysAndCertificateResult withKeyPair(KeyPair keyPair) {
        this.keyPair = keyPair;
        return this;
    }
}
