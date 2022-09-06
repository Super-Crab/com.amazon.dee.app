package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazonaws.AmazonWebServiceRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class TransferCertificateRequest extends AmazonWebServiceRequest implements Serializable {
    private String certificateId;
    private String targetAwsAccount;
    private String transferMessage;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof TransferCertificateRequest)) {
            return false;
        }
        TransferCertificateRequest transferCertificateRequest = (TransferCertificateRequest) obj;
        if ((transferCertificateRequest.getCertificateId() == null) ^ (getCertificateId() == null)) {
            return false;
        }
        if (transferCertificateRequest.getCertificateId() != null && !transferCertificateRequest.getCertificateId().equals(getCertificateId())) {
            return false;
        }
        if ((transferCertificateRequest.getTargetAwsAccount() == null) ^ (getTargetAwsAccount() == null)) {
            return false;
        }
        if (transferCertificateRequest.getTargetAwsAccount() != null && !transferCertificateRequest.getTargetAwsAccount().equals(getTargetAwsAccount())) {
            return false;
        }
        if ((transferCertificateRequest.getTransferMessage() == null) ^ (getTransferMessage() == null)) {
            return false;
        }
        return transferCertificateRequest.getTransferMessage() == null || transferCertificateRequest.getTransferMessage().equals(getTransferMessage());
    }

    public String getCertificateId() {
        return this.certificateId;
    }

    public String getTargetAwsAccount() {
        return this.targetAwsAccount;
    }

    public String getTransferMessage() {
        return this.transferMessage;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((getCertificateId() == null ? 0 : getCertificateId().hashCode()) + 31) * 31) + (getTargetAwsAccount() == null ? 0 : getTargetAwsAccount().hashCode())) * 31;
        if (getTransferMessage() != null) {
            i = getTransferMessage().hashCode();
        }
        return hashCode + i;
    }

    public void setCertificateId(String str) {
        this.certificateId = str;
    }

    public void setTargetAwsAccount(String str) {
        this.targetAwsAccount = str;
    }

    public void setTransferMessage(String str) {
        this.transferMessage = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getCertificateId() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("certificateId: ");
            outline1072.append(getCertificateId());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getTargetAwsAccount() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("targetAwsAccount: ");
            outline1073.append(getTargetAwsAccount());
            outline1073.append(",");
            outline107.append(outline1073.toString());
        }
        if (getTransferMessage() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("transferMessage: ");
            outline1074.append(getTransferMessage());
            outline107.append(outline1074.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public TransferCertificateRequest withCertificateId(String str) {
        this.certificateId = str;
        return this;
    }

    public TransferCertificateRequest withTargetAwsAccount(String str) {
        this.targetAwsAccount = str;
        return this;
    }

    public TransferCertificateRequest withTransferMessage(String str) {
        this.transferMessage = str;
        return this;
    }
}
