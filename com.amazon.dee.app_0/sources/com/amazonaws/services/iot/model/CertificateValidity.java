package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import java.util.Date;
/* loaded from: classes13.dex */
public class CertificateValidity implements Serializable {
    private Date notAfter;
    private Date notBefore;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof CertificateValidity)) {
            return false;
        }
        CertificateValidity certificateValidity = (CertificateValidity) obj;
        if ((certificateValidity.getNotBefore() == null) ^ (getNotBefore() == null)) {
            return false;
        }
        if (certificateValidity.getNotBefore() != null && !certificateValidity.getNotBefore().equals(getNotBefore())) {
            return false;
        }
        if ((certificateValidity.getNotAfter() == null) ^ (getNotAfter() == null)) {
            return false;
        }
        return certificateValidity.getNotAfter() == null || certificateValidity.getNotAfter().equals(getNotAfter());
    }

    public Date getNotAfter() {
        return this.notAfter;
    }

    public Date getNotBefore() {
        return this.notBefore;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((getNotBefore() == null ? 0 : getNotBefore().hashCode()) + 31) * 31;
        if (getNotAfter() != null) {
            i = getNotAfter().hashCode();
        }
        return hashCode + i;
    }

    public void setNotAfter(Date date) {
        this.notAfter = date;
    }

    public void setNotBefore(Date date) {
        this.notBefore = date;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getNotBefore() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("notBefore: ");
            outline1072.append(getNotBefore());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getNotAfter() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("notAfter: ");
            outline1073.append(getNotAfter());
            outline107.append(outline1073.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public CertificateValidity withNotAfter(Date date) {
        this.notAfter = date;
        return this;
    }

    public CertificateValidity withNotBefore(Date date) {
        this.notBefore = date;
        return this;
    }
}
