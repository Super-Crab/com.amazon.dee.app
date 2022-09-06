package com.amazonaws.services.logs.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazonaws.AmazonWebServiceRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class AssociateKmsKeyRequest extends AmazonWebServiceRequest implements Serializable {
    private String kmsKeyId;
    private String logGroupName;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof AssociateKmsKeyRequest)) {
            return false;
        }
        AssociateKmsKeyRequest associateKmsKeyRequest = (AssociateKmsKeyRequest) obj;
        if ((associateKmsKeyRequest.getLogGroupName() == null) ^ (getLogGroupName() == null)) {
            return false;
        }
        if (associateKmsKeyRequest.getLogGroupName() != null && !associateKmsKeyRequest.getLogGroupName().equals(getLogGroupName())) {
            return false;
        }
        if ((associateKmsKeyRequest.getKmsKeyId() == null) ^ (getKmsKeyId() == null)) {
            return false;
        }
        return associateKmsKeyRequest.getKmsKeyId() == null || associateKmsKeyRequest.getKmsKeyId().equals(getKmsKeyId());
    }

    public String getKmsKeyId() {
        return this.kmsKeyId;
    }

    public String getLogGroupName() {
        return this.logGroupName;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((getLogGroupName() == null ? 0 : getLogGroupName().hashCode()) + 31) * 31;
        if (getKmsKeyId() != null) {
            i = getKmsKeyId().hashCode();
        }
        return hashCode + i;
    }

    public void setKmsKeyId(String str) {
        this.kmsKeyId = str;
    }

    public void setLogGroupName(String str) {
        this.logGroupName = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getLogGroupName() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("logGroupName: ");
            outline1072.append(getLogGroupName());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getKmsKeyId() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("kmsKeyId: ");
            outline1073.append(getKmsKeyId());
            outline107.append(outline1073.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public AssociateKmsKeyRequest withKmsKeyId(String str) {
        this.kmsKeyId = str;
        return this;
    }

    public AssociateKmsKeyRequest withLogGroupName(String str) {
        this.logGroupName = str;
        return this;
    }
}
