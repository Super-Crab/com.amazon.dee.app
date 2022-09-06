package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class CreatePolicyVersionResult implements Serializable {
    private Boolean isDefaultVersion;
    private String policyArn;
    private String policyDocument;
    private String policyVersionId;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof CreatePolicyVersionResult)) {
            return false;
        }
        CreatePolicyVersionResult createPolicyVersionResult = (CreatePolicyVersionResult) obj;
        if ((createPolicyVersionResult.getPolicyArn() == null) ^ (getPolicyArn() == null)) {
            return false;
        }
        if (createPolicyVersionResult.getPolicyArn() != null && !createPolicyVersionResult.getPolicyArn().equals(getPolicyArn())) {
            return false;
        }
        if ((createPolicyVersionResult.getPolicyDocument() == null) ^ (getPolicyDocument() == null)) {
            return false;
        }
        if (createPolicyVersionResult.getPolicyDocument() != null && !createPolicyVersionResult.getPolicyDocument().equals(getPolicyDocument())) {
            return false;
        }
        if ((createPolicyVersionResult.getPolicyVersionId() == null) ^ (getPolicyVersionId() == null)) {
            return false;
        }
        if (createPolicyVersionResult.getPolicyVersionId() != null && !createPolicyVersionResult.getPolicyVersionId().equals(getPolicyVersionId())) {
            return false;
        }
        if ((createPolicyVersionResult.getIsDefaultVersion() == null) ^ (getIsDefaultVersion() == null)) {
            return false;
        }
        return createPolicyVersionResult.getIsDefaultVersion() == null || createPolicyVersionResult.getIsDefaultVersion().equals(getIsDefaultVersion());
    }

    public Boolean getIsDefaultVersion() {
        return this.isDefaultVersion;
    }

    public String getPolicyArn() {
        return this.policyArn;
    }

    public String getPolicyDocument() {
        return this.policyDocument;
    }

    public String getPolicyVersionId() {
        return this.policyVersionId;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((((getPolicyArn() == null ? 0 : getPolicyArn().hashCode()) + 31) * 31) + (getPolicyDocument() == null ? 0 : getPolicyDocument().hashCode())) * 31) + (getPolicyVersionId() == null ? 0 : getPolicyVersionId().hashCode())) * 31;
        if (getIsDefaultVersion() != null) {
            i = getIsDefaultVersion().hashCode();
        }
        return hashCode + i;
    }

    public Boolean isIsDefaultVersion() {
        return this.isDefaultVersion;
    }

    public void setIsDefaultVersion(Boolean bool) {
        this.isDefaultVersion = bool;
    }

    public void setPolicyArn(String str) {
        this.policyArn = str;
    }

    public void setPolicyDocument(String str) {
        this.policyDocument = str;
    }

    public void setPolicyVersionId(String str) {
        this.policyVersionId = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getPolicyArn() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("policyArn: ");
            outline1072.append(getPolicyArn());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getPolicyDocument() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("policyDocument: ");
            outline1073.append(getPolicyDocument());
            outline1073.append(",");
            outline107.append(outline1073.toString());
        }
        if (getPolicyVersionId() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("policyVersionId: ");
            outline1074.append(getPolicyVersionId());
            outline1074.append(",");
            outline107.append(outline1074.toString());
        }
        if (getIsDefaultVersion() != null) {
            StringBuilder outline1075 = GeneratedOutlineSupport1.outline107("isDefaultVersion: ");
            outline1075.append(getIsDefaultVersion());
            outline107.append(outline1075.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public CreatePolicyVersionResult withIsDefaultVersion(Boolean bool) {
        this.isDefaultVersion = bool;
        return this;
    }

    public CreatePolicyVersionResult withPolicyArn(String str) {
        this.policyArn = str;
        return this;
    }

    public CreatePolicyVersionResult withPolicyDocument(String str) {
        this.policyDocument = str;
        return this;
    }

    public CreatePolicyVersionResult withPolicyVersionId(String str) {
        this.policyVersionId = str;
        return this;
    }
}
