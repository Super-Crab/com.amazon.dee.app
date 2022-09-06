package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class CreatePolicyResult implements Serializable {
    private String policyArn;
    private String policyDocument;
    private String policyName;
    private String policyVersionId;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof CreatePolicyResult)) {
            return false;
        }
        CreatePolicyResult createPolicyResult = (CreatePolicyResult) obj;
        if ((createPolicyResult.getPolicyName() == null) ^ (getPolicyName() == null)) {
            return false;
        }
        if (createPolicyResult.getPolicyName() != null && !createPolicyResult.getPolicyName().equals(getPolicyName())) {
            return false;
        }
        if ((createPolicyResult.getPolicyArn() == null) ^ (getPolicyArn() == null)) {
            return false;
        }
        if (createPolicyResult.getPolicyArn() != null && !createPolicyResult.getPolicyArn().equals(getPolicyArn())) {
            return false;
        }
        if ((createPolicyResult.getPolicyDocument() == null) ^ (getPolicyDocument() == null)) {
            return false;
        }
        if (createPolicyResult.getPolicyDocument() != null && !createPolicyResult.getPolicyDocument().equals(getPolicyDocument())) {
            return false;
        }
        if ((createPolicyResult.getPolicyVersionId() == null) ^ (getPolicyVersionId() == null)) {
            return false;
        }
        return createPolicyResult.getPolicyVersionId() == null || createPolicyResult.getPolicyVersionId().equals(getPolicyVersionId());
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

    public String getPolicyVersionId() {
        return this.policyVersionId;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((((getPolicyName() == null ? 0 : getPolicyName().hashCode()) + 31) * 31) + (getPolicyArn() == null ? 0 : getPolicyArn().hashCode())) * 31) + (getPolicyDocument() == null ? 0 : getPolicyDocument().hashCode())) * 31;
        if (getPolicyVersionId() != null) {
            i = getPolicyVersionId().hashCode();
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
        if (getPolicyArn() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("policyArn: ");
            outline1073.append(getPolicyArn());
            outline1073.append(",");
            outline107.append(outline1073.toString());
        }
        if (getPolicyDocument() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("policyDocument: ");
            outline1074.append(getPolicyDocument());
            outline1074.append(",");
            outline107.append(outline1074.toString());
        }
        if (getPolicyVersionId() != null) {
            StringBuilder outline1075 = GeneratedOutlineSupport1.outline107("policyVersionId: ");
            outline1075.append(getPolicyVersionId());
            outline107.append(outline1075.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public CreatePolicyResult withPolicyArn(String str) {
        this.policyArn = str;
        return this;
    }

    public CreatePolicyResult withPolicyDocument(String str) {
        this.policyDocument = str;
        return this;
    }

    public CreatePolicyResult withPolicyName(String str) {
        this.policyName = str;
        return this;
    }

    public CreatePolicyResult withPolicyVersionId(String str) {
        this.policyVersionId = str;
        return this;
    }
}
