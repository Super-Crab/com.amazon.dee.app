package com.amazonaws.services.logs.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazonaws.AmazonWebServiceRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class PutDestinationPolicyRequest extends AmazonWebServiceRequest implements Serializable {
    private String accessPolicy;
    private String destinationName;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof PutDestinationPolicyRequest)) {
            return false;
        }
        PutDestinationPolicyRequest putDestinationPolicyRequest = (PutDestinationPolicyRequest) obj;
        if ((putDestinationPolicyRequest.getDestinationName() == null) ^ (getDestinationName() == null)) {
            return false;
        }
        if (putDestinationPolicyRequest.getDestinationName() != null && !putDestinationPolicyRequest.getDestinationName().equals(getDestinationName())) {
            return false;
        }
        if ((putDestinationPolicyRequest.getAccessPolicy() == null) ^ (getAccessPolicy() == null)) {
            return false;
        }
        return putDestinationPolicyRequest.getAccessPolicy() == null || putDestinationPolicyRequest.getAccessPolicy().equals(getAccessPolicy());
    }

    public String getAccessPolicy() {
        return this.accessPolicy;
    }

    public String getDestinationName() {
        return this.destinationName;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((getDestinationName() == null ? 0 : getDestinationName().hashCode()) + 31) * 31;
        if (getAccessPolicy() != null) {
            i = getAccessPolicy().hashCode();
        }
        return hashCode + i;
    }

    public void setAccessPolicy(String str) {
        this.accessPolicy = str;
    }

    public void setDestinationName(String str) {
        this.destinationName = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getDestinationName() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("destinationName: ");
            outline1072.append(getDestinationName());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getAccessPolicy() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("accessPolicy: ");
            outline1073.append(getAccessPolicy());
            outline107.append(outline1073.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public PutDestinationPolicyRequest withAccessPolicy(String str) {
        this.accessPolicy = str;
        return this;
    }

    public PutDestinationPolicyRequest withDestinationName(String str) {
        this.destinationName = str;
        return this;
    }
}
