package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazonaws.AmazonWebServiceRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class CreateKeysAndCertificateRequest extends AmazonWebServiceRequest implements Serializable {
    private Boolean setAsActive;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof CreateKeysAndCertificateRequest)) {
            return false;
        }
        CreateKeysAndCertificateRequest createKeysAndCertificateRequest = (CreateKeysAndCertificateRequest) obj;
        if ((createKeysAndCertificateRequest.getSetAsActive() == null) ^ (getSetAsActive() == null)) {
            return false;
        }
        return createKeysAndCertificateRequest.getSetAsActive() == null || createKeysAndCertificateRequest.getSetAsActive().equals(getSetAsActive());
    }

    public Boolean getSetAsActive() {
        return this.setAsActive;
    }

    public int hashCode() {
        return 31 + (getSetAsActive() == null ? 0 : getSetAsActive().hashCode());
    }

    public Boolean isSetAsActive() {
        return this.setAsActive;
    }

    public void setSetAsActive(Boolean bool) {
        this.setAsActive = bool;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getSetAsActive() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("setAsActive: ");
            outline1072.append(getSetAsActive());
            outline107.append(outline1072.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public CreateKeysAndCertificateRequest withSetAsActive(Boolean bool) {
        this.setAsActive = bool;
        return this;
    }
}
