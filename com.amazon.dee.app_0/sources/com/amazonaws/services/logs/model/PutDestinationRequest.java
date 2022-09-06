package com.amazonaws.services.logs.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazonaws.AmazonWebServiceRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class PutDestinationRequest extends AmazonWebServiceRequest implements Serializable {
    private String destinationName;
    private String roleArn;
    private String targetArn;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof PutDestinationRequest)) {
            return false;
        }
        PutDestinationRequest putDestinationRequest = (PutDestinationRequest) obj;
        if ((putDestinationRequest.getDestinationName() == null) ^ (getDestinationName() == null)) {
            return false;
        }
        if (putDestinationRequest.getDestinationName() != null && !putDestinationRequest.getDestinationName().equals(getDestinationName())) {
            return false;
        }
        if ((putDestinationRequest.getTargetArn() == null) ^ (getTargetArn() == null)) {
            return false;
        }
        if (putDestinationRequest.getTargetArn() != null && !putDestinationRequest.getTargetArn().equals(getTargetArn())) {
            return false;
        }
        if ((putDestinationRequest.getRoleArn() == null) ^ (getRoleArn() == null)) {
            return false;
        }
        return putDestinationRequest.getRoleArn() == null || putDestinationRequest.getRoleArn().equals(getRoleArn());
    }

    public String getDestinationName() {
        return this.destinationName;
    }

    public String getRoleArn() {
        return this.roleArn;
    }

    public String getTargetArn() {
        return this.targetArn;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((getDestinationName() == null ? 0 : getDestinationName().hashCode()) + 31) * 31) + (getTargetArn() == null ? 0 : getTargetArn().hashCode())) * 31;
        if (getRoleArn() != null) {
            i = getRoleArn().hashCode();
        }
        return hashCode + i;
    }

    public void setDestinationName(String str) {
        this.destinationName = str;
    }

    public void setRoleArn(String str) {
        this.roleArn = str;
    }

    public void setTargetArn(String str) {
        this.targetArn = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getDestinationName() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("destinationName: ");
            outline1072.append(getDestinationName());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getTargetArn() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("targetArn: ");
            outline1073.append(getTargetArn());
            outline1073.append(",");
            outline107.append(outline1073.toString());
        }
        if (getRoleArn() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("roleArn: ");
            outline1074.append(getRoleArn());
            outline107.append(outline1074.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public PutDestinationRequest withDestinationName(String str) {
        this.destinationName = str;
        return this;
    }

    public PutDestinationRequest withRoleArn(String str) {
        this.roleArn = str;
        return this;
    }

    public PutDestinationRequest withTargetArn(String str) {
        this.targetArn = str;
        return this;
    }
}
