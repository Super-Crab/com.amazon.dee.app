package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazonaws.AmazonWebServiceRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class AttachPrincipalPolicyRequest extends AmazonWebServiceRequest implements Serializable {
    private String policyName;
    private String principal;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof AttachPrincipalPolicyRequest)) {
            return false;
        }
        AttachPrincipalPolicyRequest attachPrincipalPolicyRequest = (AttachPrincipalPolicyRequest) obj;
        if ((attachPrincipalPolicyRequest.getPolicyName() == null) ^ (getPolicyName() == null)) {
            return false;
        }
        if (attachPrincipalPolicyRequest.getPolicyName() != null && !attachPrincipalPolicyRequest.getPolicyName().equals(getPolicyName())) {
            return false;
        }
        if ((attachPrincipalPolicyRequest.getPrincipal() == null) ^ (getPrincipal() == null)) {
            return false;
        }
        return attachPrincipalPolicyRequest.getPrincipal() == null || attachPrincipalPolicyRequest.getPrincipal().equals(getPrincipal());
    }

    public String getPolicyName() {
        return this.policyName;
    }

    public String getPrincipal() {
        return this.principal;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((getPolicyName() == null ? 0 : getPolicyName().hashCode()) + 31) * 31;
        if (getPrincipal() != null) {
            i = getPrincipal().hashCode();
        }
        return hashCode + i;
    }

    public void setPolicyName(String str) {
        this.policyName = str;
    }

    public void setPrincipal(String str) {
        this.principal = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getPolicyName() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("policyName: ");
            outline1072.append(getPolicyName());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getPrincipal() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("principal: ");
            outline1073.append(getPrincipal());
            outline107.append(outline1073.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public AttachPrincipalPolicyRequest withPolicyName(String str) {
        this.policyName = str;
        return this;
    }

    public AttachPrincipalPolicyRequest withPrincipal(String str) {
        this.principal = str;
        return this;
    }
}
