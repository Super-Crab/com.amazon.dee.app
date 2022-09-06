package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
/* loaded from: classes13.dex */
public class ListOutgoingCertificatesResult implements Serializable {
    private String nextMarker;
    private List<OutgoingCertificate> outgoingCertificates;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ListOutgoingCertificatesResult)) {
            return false;
        }
        ListOutgoingCertificatesResult listOutgoingCertificatesResult = (ListOutgoingCertificatesResult) obj;
        if ((listOutgoingCertificatesResult.getOutgoingCertificates() == null) ^ (getOutgoingCertificates() == null)) {
            return false;
        }
        if (listOutgoingCertificatesResult.getOutgoingCertificates() != null && !listOutgoingCertificatesResult.getOutgoingCertificates().equals(getOutgoingCertificates())) {
            return false;
        }
        if ((listOutgoingCertificatesResult.getNextMarker() == null) ^ (getNextMarker() == null)) {
            return false;
        }
        return listOutgoingCertificatesResult.getNextMarker() == null || listOutgoingCertificatesResult.getNextMarker().equals(getNextMarker());
    }

    public String getNextMarker() {
        return this.nextMarker;
    }

    public List<OutgoingCertificate> getOutgoingCertificates() {
        return this.outgoingCertificates;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((getOutgoingCertificates() == null ? 0 : getOutgoingCertificates().hashCode()) + 31) * 31;
        if (getNextMarker() != null) {
            i = getNextMarker().hashCode();
        }
        return hashCode + i;
    }

    public void setNextMarker(String str) {
        this.nextMarker = str;
    }

    public void setOutgoingCertificates(Collection<OutgoingCertificate> collection) {
        if (collection == null) {
            this.outgoingCertificates = null;
        } else {
            this.outgoingCertificates = new ArrayList(collection);
        }
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getOutgoingCertificates() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("outgoingCertificates: ");
            outline1072.append(getOutgoingCertificates());
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

    public ListOutgoingCertificatesResult withNextMarker(String str) {
        this.nextMarker = str;
        return this;
    }

    public ListOutgoingCertificatesResult withOutgoingCertificates(OutgoingCertificate... outgoingCertificateArr) {
        if (getOutgoingCertificates() == null) {
            this.outgoingCertificates = new ArrayList(outgoingCertificateArr.length);
        }
        for (OutgoingCertificate outgoingCertificate : outgoingCertificateArr) {
            this.outgoingCertificates.add(outgoingCertificate);
        }
        return this;
    }

    public ListOutgoingCertificatesResult withOutgoingCertificates(Collection<OutgoingCertificate> collection) {
        setOutgoingCertificates(collection);
        return this;
    }
}
