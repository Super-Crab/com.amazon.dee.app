package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazonaws.AmazonWebServiceRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class AttachPolicyRequest extends AmazonWebServiceRequest implements Serializable {
    private String policyName;
    private String target;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof AttachPolicyRequest)) {
            return false;
        }
        AttachPolicyRequest attachPolicyRequest = (AttachPolicyRequest) obj;
        if ((attachPolicyRequest.getPolicyName() == null) ^ (getPolicyName() == null)) {
            return false;
        }
        if (attachPolicyRequest.getPolicyName() != null && !attachPolicyRequest.getPolicyName().equals(getPolicyName())) {
            return false;
        }
        if ((attachPolicyRequest.getTarget() == null) ^ (getTarget() == null)) {
            return false;
        }
        return attachPolicyRequest.getTarget() == null || attachPolicyRequest.getTarget().equals(getTarget());
    }

    public String getPolicyName() {
        return this.policyName;
    }

    public String getTarget() {
        return this.target;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((getPolicyName() == null ? 0 : getPolicyName().hashCode()) + 31) * 31;
        if (getTarget() != null) {
            i = getTarget().hashCode();
        }
        return hashCode + i;
    }

    public void setPolicyName(String str) {
        this.policyName = str;
    }

    public void setTarget(String str) {
        this.target = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getPolicyName() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("policyName: ");
            outline1072.append(getPolicyName());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getTarget() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("target: ");
            outline1073.append(getTarget());
            outline107.append(outline1073.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public AttachPolicyRequest withPolicyName(String str) {
        this.policyName = str;
        return this;
    }

    public AttachPolicyRequest withTarget(String str) {
        this.target = str;
        return this;
    }
}
