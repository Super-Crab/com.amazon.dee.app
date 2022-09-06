package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazonaws.AmazonWebServiceRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class CreateCertificateFromCsrRequest extends AmazonWebServiceRequest implements Serializable {
    private String certificateSigningRequest;
    private Boolean setAsActive;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof CreateCertificateFromCsrRequest)) {
            return false;
        }
        CreateCertificateFromCsrRequest createCertificateFromCsrRequest = (CreateCertificateFromCsrRequest) obj;
        if ((createCertificateFromCsrRequest.getCertificateSigningRequest() == null) ^ (getCertificateSigningRequest() == null)) {
            return false;
        }
        if (createCertificateFromCsrRequest.getCertificateSigningRequest() != null && !createCertificateFromCsrRequest.getCertificateSigningRequest().equals(getCertificateSigningRequest())) {
            return false;
        }
        if ((createCertificateFromCsrRequest.getSetAsActive() == null) ^ (getSetAsActive() == null)) {
            return false;
        }
        return createCertificateFromCsrRequest.getSetAsActive() == null || createCertificateFromCsrRequest.getSetAsActive().equals(getSetAsActive());
    }

    public String getCertificateSigningRequest() {
        return this.certificateSigningRequest;
    }

    public Boolean getSetAsActive() {
        return this.setAsActive;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((getCertificateSigningRequest() == null ? 0 : getCertificateSigningRequest().hashCode()) + 31) * 31;
        if (getSetAsActive() != null) {
            i = getSetAsActive().hashCode();
        }
        return hashCode + i;
    }

    public Boolean isSetAsActive() {
        return this.setAsActive;
    }

    public void setCertificateSigningRequest(String str) {
        this.certificateSigningRequest = str;
    }

    public void setSetAsActive(Boolean bool) {
        this.setAsActive = bool;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getCertificateSigningRequest() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("certificateSigningRequest: ");
            outline1072.append(getCertificateSigningRequest());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getSetAsActive() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("setAsActive: ");
            outline1073.append(getSetAsActive());
            outline107.append(outline1073.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public CreateCertificateFromCsrRequest withCertificateSigningRequest(String str) {
        this.certificateSigningRequest = str;
        return this;
    }

    public CreateCertificateFromCsrRequest withSetAsActive(Boolean bool) {
        this.setAsActive = bool;
        return this;
    }
}
