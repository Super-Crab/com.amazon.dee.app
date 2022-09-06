package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazonaws.AmazonWebServiceRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class CreatePolicyRequest extends AmazonWebServiceRequest implements Serializable {
    private String policyDocument;
    private String policyName;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof CreatePolicyRequest)) {
            return false;
        }
        CreatePolicyRequest createPolicyRequest = (CreatePolicyRequest) obj;
        if ((createPolicyRequest.getPolicyName() == null) ^ (getPolicyName() == null)) {
            return false;
        }
        if (createPolicyRequest.getPolicyName() != null && !createPolicyRequest.getPolicyName().equals(getPolicyName())) {
            return false;
        }
        if ((createPolicyRequest.getPolicyDocument() == null) ^ (getPolicyDocument() == null)) {
            return false;
        }
        return createPolicyRequest.getPolicyDocument() == null || createPolicyRequest.getPolicyDocument().equals(getPolicyDocument());
    }

    public String getPolicyDocument() {
        return this.policyDocument;
    }

    public String getPolicyName() {
        return this.policyName;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((getPolicyName() == null ? 0 : getPolicyName().hashCode()) + 31) * 31;
        if (getPolicyDocument() != null) {
            i = getPolicyDocument().hashCode();
        }
        return hashCode + i;
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
        if (getPolicyDocument() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("policyDocument: ");
            outline1073.append(getPolicyDocument());
            outline107.append(outline1073.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public CreatePolicyRequest withPolicyDocument(String str) {
        this.policyDocument = str;
        return this;
    }

    public CreatePolicyRequest withPolicyName(String str) {
        this.policyName = str;
        return this;
    }
}
