package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazonaws.AmazonWebServiceRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class CreatePolicyVersionRequest extends AmazonWebServiceRequest implements Serializable {
    private String policyDocument;
    private String policyName;
    private Boolean setAsDefault;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof CreatePolicyVersionRequest)) {
            return false;
        }
        CreatePolicyVersionRequest createPolicyVersionRequest = (CreatePolicyVersionRequest) obj;
        if ((createPolicyVersionRequest.getPolicyName() == null) ^ (getPolicyName() == null)) {
            return false;
        }
        if (createPolicyVersionRequest.getPolicyName() != null && !createPolicyVersionRequest.getPolicyName().equals(getPolicyName())) {
            return false;
        }
        if ((createPolicyVersionRequest.getPolicyDocument() == null) ^ (getPolicyDocument() == null)) {
            return false;
        }
        if (createPolicyVersionRequest.getPolicyDocument() != null && !createPolicyVersionRequest.getPolicyDocument().equals(getPolicyDocument())) {
            return false;
        }
        if ((createPolicyVersionRequest.getSetAsDefault() == null) ^ (getSetAsDefault() == null)) {
            return false;
        }
        return createPolicyVersionRequest.getSetAsDefault() == null || createPolicyVersionRequest.getSetAsDefault().equals(getSetAsDefault());
    }

    public String getPolicyDocument() {
        return this.policyDocument;
    }

    public String getPolicyName() {
        return this.policyName;
    }

    public Boolean getSetAsDefault() {
        return this.setAsDefault;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((getPolicyName() == null ? 0 : getPolicyName().hashCode()) + 31) * 31) + (getPolicyDocument() == null ? 0 : getPolicyDocument().hashCode())) * 31;
        if (getSetAsDefault() != null) {
            i = getSetAsDefault().hashCode();
        }
        return hashCode + i;
    }

    public Boolean isSetAsDefault() {
        return this.setAsDefault;
    }

    public void setPolicyDocument(String str) {
        this.policyDocument = str;
    }

    public void setPolicyName(String str) {
        this.policyName = str;
    }

    public void setSetAsDefault(Boolean bool) {
        this.setAsDefault = bool;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getPolicyName() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("policyName: ");
            outline1072.append(getPolicyName());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getPolicyDocument() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("policyDocument: ");
            outline1073.append(getPolicyDocument());
            outline1073.append(",");
            outline107.append(outline1073.toString());
        }
        if (getSetAsDefault() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("setAsDefault: ");
            outline1074.append(getSetAsDefault());
            outline107.append(outline1074.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public CreatePolicyVersionRequest withPolicyDocument(String str) {
        this.policyDocument = str;
        return this;
    }

    public CreatePolicyVersionRequest withPolicyName(String str) {
        this.policyName = str;
        return this;
    }

    public CreatePolicyVersionRequest withSetAsDefault(Boolean bool) {
        this.setAsDefault = bool;
        return this;
    }
}
