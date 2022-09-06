package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class EffectivePolicy implements Serializable {
    private String policyArn;
    private String policyDocument;
    private String policyName;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof EffectivePolicy)) {
            return false;
        }
        EffectivePolicy effectivePolicy = (EffectivePolicy) obj;
        if ((effectivePolicy.getPolicyName() == null) ^ (getPolicyName() == null)) {
            return false;
        }
        if (effectivePolicy.getPolicyName() != null && !effectivePolicy.getPolicyName().equals(getPolicyName())) {
            return false;
        }
        if ((effectivePolicy.getPolicyArn() == null) ^ (getPolicyArn() == null)) {
            return false;
        }
        if (effectivePolicy.getPolicyArn() != null && !effectivePolicy.getPolicyArn().equals(getPolicyArn())) {
            return false;
        }
        if ((effectivePolicy.getPolicyDocument() == null) ^ (getPolicyDocument() == null)) {
            return false;
        }
        return effectivePolicy.getPolicyDocument() == null || effectivePolicy.getPolicyDocument().equals(getPolicyDocument());
    }

    public String getPolicyArn() {
        return this.policyArn;
    }

    public String getPolicyDocument() {
        return this.policyDocument;
    }

    public String getPolicyName() {
        return this.policyName;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((getPolicyName() == null ? 0 : getPolicyName().hashCode()) + 31) * 31) + (getPolicyArn() == null ? 0 : getPolicyArn().hashCode())) * 31;
        if (getPolicyDocument() != null) {
            i = getPolicyDocument().hashCode();
        }
        return hashCode + i;
    }

    public void setPolicyArn(String str) {
        this.policyArn = str;
    }

    public void setPolicyDocument(String str) {
        this.policyDocument = str;
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
            outline1073.append(",");
            outline107.append(outline1073.toString());
        }
        if (getPolicyDocument() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("policyDocument: ");
            outline1074.append(getPolicyDocument());
            outline107.append(outline1074.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public EffectivePolicy withPolicyArn(String str) {
        this.policyArn = str;
        return this;
    }

    public EffectivePolicy withPolicyDocument(String str) {
        this.policyDocument = str;
        return this;
    }

    public EffectivePolicy withPolicyName(String str) {
        this.policyName = str;
        return this;
    }
}
