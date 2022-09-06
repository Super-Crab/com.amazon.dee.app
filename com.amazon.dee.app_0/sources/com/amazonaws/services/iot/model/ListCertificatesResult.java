package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
/* loaded from: classes13.dex */
public class ListCertificatesResult implements Serializable {
    private List<Certificate> certificates;
    private String nextMarker;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ListCertificatesResult)) {
            return false;
        }
        ListCertificatesResult listCertificatesResult = (ListCertificatesResult) obj;
        if ((listCertificatesResult.getCertificates() == null) ^ (getCertificates() == null)) {
            return false;
        }
        if (listCertificatesResult.getCertificates() != null && !listCertificatesResult.getCertificates().equals(getCertificates())) {
            return false;
        }
        if ((listCertificatesResult.getNextMarker() == null) ^ (getNextMarker() == null)) {
            return false;
        }
        return listCertificatesResult.getNextMarker() == null || listCertificatesResult.getNextMarker().equals(getNextMarker());
    }

    public List<Certificate> getCertificates() {
        return this.certificates;
    }

    public String getNextMarker() {
        return this.nextMarker;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((getCertificates() == null ? 0 : getCertificates().hashCode()) + 31) * 31;
        if (getNextMarker() != null) {
            i = getNextMarker().hashCode();
        }
        return hashCode + i;
    }

    public void setCertificates(Collection<Certificate> collection) {
        if (collection == null) {
            this.certificates = null;
        } else {
            this.certificates = new ArrayList(collection);
        }
    }

    public void setNextMarker(String str) {
        this.nextMarker = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getCertificates() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("certificates: ");
            outline1072.append(getCertificates());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getNextMarker() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("nextMarker: ");
            outline1073.append(getNextMarker());
            outline107.append(outline1073.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public ListCertificatesResult withCertificates(Certificate... certificateArr) {
        if (getCertificates() == null) {
            this.certificates = new ArrayList(certificateArr.length);
        }
        for (Certificate certificate : certificateArr) {
            this.certificates.add(certificate);
        }
        return this;
    }

    public ListCertificatesResult withNextMarker(String str) {
        this.nextMarker = str;
        return this;
    }

    public ListCertificatesResult withCertificates(Collection<Certificate> collection) {
        setCertificates(collection);
        return this;
    }
}
