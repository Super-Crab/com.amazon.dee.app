package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class Policy implements Serializable {
    private String policyArn;
    private String policyName;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof Policy)) {
            return false;
        }
        Policy policy = (Policy) obj;
        if ((policy.getPolicyName() == null) ^ (getPolicyName() == null)) {
            return false;
        }
        if (policy.getPolicyName() != null && !policy.getPolicyName().equals(getPolicyName())) {
            return false;
        }
        if ((policy.getPolicyArn() == null) ^ (getPolicyArn() == null)) {
            return false;
        }
        return policy.getPolicyArn() == null || policy.getPolicyArn().equals(getPolicyArn());
    }

    public String getPolicyArn() {
        return this.policyArn;
    }

    public String getPolicyName() {
        return this.policyName;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((getPolicyName() == null ? 0 : getPolicyName().hashCode()) + 31) * 31;
        if (getPolicyArn() != null) {
            i = getPolicyArn().hashCode();
        }
        return hashCode + i;
    }

    public void setPolicyArn(String str) {
        this.policyArn = str;
    }

    public void setPolicyName(String str) {
        this.policyName = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getPolicyName() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("policyName: ");
            outline1072.append(getPolicyName());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getPolicyArn() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("policyArn: ");
            outline1073.append(getPolicyArn());
            outline107.append(outline1073.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public Policy withPolicyArn(String str) {
        this.policyArn = str;
        return this;
    }

    public Policy withPolicyName(String str) {
        this.policyName = str;
        return this;
    }
}
