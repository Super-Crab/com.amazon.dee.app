package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class CustomCodeSigning implements Serializable {
    private CodeSigningCertificateChain certificateChain;
    private String hashAlgorithm;
    private CodeSigningSignature signature;
    private String signatureAlgorithm;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof CustomCodeSigning)) {
            return false;
        }
        CustomCodeSigning customCodeSigning = (CustomCodeSigning) obj;
        if ((customCodeSigning.getSignature() == null) ^ (getSignature() == null)) {
            return false;
        }
        if (customCodeSigning.getSignature() != null && !customCodeSigning.getSignature().equals(getSignature())) {
            return false;
        }
        if ((customCodeSigning.getCertificateChain() == null) ^ (getCertificateChain() == null)) {
            return false;
        }
        if (customCodeSigning.getCertificateChain() != null && !customCodeSigning.getCertificateChain().equals(getCertificateChain())) {
            return false;
        }
        if ((customCodeSigning.getHashAlgorithm() == null) ^ (getHashAlgorithm() == null)) {
            return false;
        }
        if (customCodeSigning.getHashAlgorithm() != null && !customCodeSigning.getHashAlgorithm().equals(getHashAlgorithm())) {
            return false;
        }
        if ((customCodeSigning.getSignatureAlgorithm() == null) ^ (getSignatureAlgorithm() == null)) {
            return false;
        }
        return customCodeSigning.getSignatureAlgorithm() == null || customCodeSigning.getSignatureAlgorithm().equals(getSignatureAlgorithm());
    }

    public CodeSigningCertificateChain getCertificateChain() {
        return this.certificateChain;
    }

    public String getHashAlgorithm() {
        return this.hashAlgorithm;
    }

    public CodeSigningSignature getSignature() {
        return this.signature;
    }

    public String getSignatureAlgorithm() {
        return this.signatureAlgorithm;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((((getSignature() == null ? 0 : getSignature().hashCode()) + 31) * 31) + (getCertificateChain() == null ? 0 : getCertificateChain().hashCode())) * 31) + (getHashAlgorithm() == null ? 0 : getHashAlgorithm().hashCode())) * 31;
        if (getSignatureAlgorithm() != null) {
            i = getSignatureAlgorithm().hashCode();
        }
        return hashCode + i;
    }

    public void setCertificateChain(CodeSigningCertificateChain codeSigningCertificateChain) {
        this.certificateChain = codeSigningCertificateChain;
    }

    public void setHashAlgorithm(String str) {
        this.hashAlgorithm = str;
    }

    public void setSignature(CodeSigningSignature codeSigningSignature) {
        this.signature = codeSigningSignature;
    }

    public void setSignatureAlgorithm(String str) {
        this.signatureAlgorithm = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getSignature() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("signature: ");
            outline1072.append(getSignature());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getCertificateChain() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("certificateChain: ");
            outline1073.append(getCertificateChain());
            outline1073.append(",");
            outline107.append(outline1073.toString());
        }
        if (getHashAlgorithm() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("hashAlgorithm: ");
            outline1074.append(getHashAlgorithm());
            outline1074.append(",");
            outline107.append(outline1074.toString());
        }
        if (getSignatureAlgorithm() != null) {
            StringBuilder outline1075 = GeneratedOutlineSupport1.outline107("signatureAlgorithm: ");
            outline1075.append(getSignatureAlgorithm());
            outline107.append(outline1075.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public CustomCodeSigning withCertificateChain(CodeSigningCertificateChain codeSigningCertificateChain) {
        this.certificateChain = codeSigningCertificateChain;
        return this;
    }

    public CustomCodeSigning withHashAlgorithm(String str) {
        this.hashAlgorithm = str;
        return this;
    }

    public CustomCodeSigning withSignature(CodeSigningSignature codeSigningSignature) {
        this.signature = codeSigningSignature;
        return this;
    }

    public CustomCodeSigning withSignatureAlgorithm(String str) {
        this.signatureAlgorithm = str;
        return this;
    }
}
