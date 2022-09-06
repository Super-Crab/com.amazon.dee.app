package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class PolicyVersionIdentifier implements Serializable {
    private String policyName;
    private String policyVersionId;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof PolicyVersionIdentifier)) {
            return false;
        }
        PolicyVersionIdentifier policyVersionIdentifier = (PolicyVersionIdentifier) obj;
        if ((policyVersionIdentifier.getPolicyName() == null) ^ (getPolicyName() == null)) {
            return false;
        }
        if (policyVersionIdentifier.getPolicyName() != null && !policyVersionIdentifier.getPolicyName().equals(getPolicyName())) {
            return false;
        }
        if ((policyVersionIdentifier.getPolicyVersionId() == null) ^ (getPolicyVersionId() == null)) {
            return false;
        }
        return policyVersionIdentifier.getPolicyVersionId() == null || policyVersionIdentifier.getPolicyVersionId().equals(getPolicyVersionId());
    }

    public String getPolicyName() {
        return this.policyName;
    }

    public String getPolicyVersionId() {
        return this.policyVersionId;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((getPolicyName() == null ? 0 : getPolicyName().hashCode()) + 31) * 31;
        if (getPolicyVersionId() != null) {
            i = getPolicyVersionId().hashCode();
        }
        return hashCode + i;
    }

    public void setPolicyName(String str) {
        this.policyName = str;
    }

    public void setPolicyVersionId(String str) {
        this.policyVersionId = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getPolicyName() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("policyName: ");
            outline1072.append(getPolicyName());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getPolicyVersionId() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("policyVersionId: ");
            outline1073.append(getPolicyVersionId());
            outline107.append(outline1073.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public PolicyVersionIdentifier withPolicyName(String str) {
        this.policyName = str;
        return this;
    }

    public PolicyVersionIdentifier withPolicyVersionId(String str) {
        this.policyVersionId = str;
        return this;
    }
}
