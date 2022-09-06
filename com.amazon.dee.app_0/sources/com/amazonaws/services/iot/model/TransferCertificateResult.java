package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class TransferCertificateResult implements Serializable {
    private String transferredCertificateArn;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof TransferCertificateResult)) {
            return false;
        }
        TransferCertificateResult transferCertificateResult = (TransferCertificateResult) obj;
        if ((transferCertificateResult.getTransferredCertificateArn() == null) ^ (getTransferredCertificateArn() == null)) {
            return false;
        }
        return transferCertificateResult.getTransferredCertificateArn() == null || transferCertificateResult.getTransferredCertificateArn().equals(getTransferredCertificateArn());
    }

    public String getTransferredCertificateArn() {
        return this.transferredCertificateArn;
    }

    public int hashCode() {
        return 31 + (getTransferredCertificateArn() == null ? 0 : getTransferredCertificateArn().hashCode());
    }

    public void setTransferredCertificateArn(String str) {
        this.transferredCertificateArn = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getTransferredCertificateArn() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("transferredCertificateArn: ");
            outline1072.append(getTransferredCertificateArn());
            outline107.append(outline1072.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public TransferCertificateResult withTransferredCertificateArn(String str) {
        this.transferredCertificateArn = str;
        return this;
    }
}
