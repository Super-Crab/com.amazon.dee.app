package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class CodeSigningCertificateChain implements Serializable {
    private String certificateName;
    private String inlineDocument;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof CodeSigningCertificateChain)) {
            return false;
        }
        CodeSigningCertificateChain codeSigningCertificateChain = (CodeSigningCertificateChain) obj;
        if ((codeSigningCertificateChain.getCertificateName() == null) ^ (getCertificateName() == null)) {
            return false;
        }
        if (codeSigningCertificateChain.getCertificateName() != null && !codeSigningCertificateChain.getCertificateName().equals(getCertificateName())) {
            return false;
        }
        if ((codeSigningCertificateChain.getInlineDocument() == null) ^ (getInlineDocument() == null)) {
            return false;
        }
        return codeSigningCertificateChain.getInlineDocument() == null || codeSigningCertificateChain.getInlineDocument().equals(getInlineDocument());
    }

    public String getCertificateName() {
        return this.certificateName;
    }

    public String getInlineDocument() {
        return this.inlineDocument;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((getCertificateName() == null ? 0 : getCertificateName().hashCode()) + 31) * 31;
        if (getInlineDocument() != null) {
            i = getInlineDocument().hashCode();
        }
        return hashCode + i;
    }

    public void setCertificateName(String str) {
        this.certificateName = str;
    }

    public void setInlineDocument(String str) {
        this.inlineDocument = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getCertificateName() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("certificateName: ");
            outline1072.append(getCertificateName());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getInlineDocument() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("inlineDocument: ");
            outline1073.append(getInlineDocument());
            outline107.append(outline1073.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public CodeSigningCertificateChain withCertificateName(String str) {
        this.certificateName = str;
        return this;
    }

    public CodeSigningCertificateChain withInlineDocument(String str) {
        this.inlineDocument = str;
        return this;
    }
}
