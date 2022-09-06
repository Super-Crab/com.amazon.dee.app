package com.amazonaws.services.logs.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazonaws.AmazonWebServiceRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class DisassociateKmsKeyRequest extends AmazonWebServiceRequest implements Serializable {
    private String logGroupName;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof DisassociateKmsKeyRequest)) {
            return false;
        }
        DisassociateKmsKeyRequest disassociateKmsKeyRequest = (DisassociateKmsKeyRequest) obj;
        if ((disassociateKmsKeyRequest.getLogGroupName() == null) ^ (getLogGroupName() == null)) {
            return false;
        }
        return disassociateKmsKeyRequest.getLogGroupName() == null || disassociateKmsKeyRequest.getLogGroupName().equals(getLogGroupName());
    }

    public String getLogGroupName() {
        return this.logGroupName;
    }

    public int hashCode() {
        return 31 + (getLogGroupName() == null ? 0 : getLogGroupName().hashCode());
    }

    public void setLogGroupName(String str) {
        this.logGroupName = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getLogGroupName() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("logGroupName: ");
            outline1072.append(getLogGroupName());
            outline107.append(outline1072.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public DisassociateKmsKeyRequest withLogGroupName(String str) {
        this.logGroupName = str;
        return this;
    }
}
