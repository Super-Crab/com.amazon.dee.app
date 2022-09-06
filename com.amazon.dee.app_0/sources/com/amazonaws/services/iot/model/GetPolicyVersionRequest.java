package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazonaws.AmazonWebServiceRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class GetPolicyVersionRequest extends AmazonWebServiceRequest implements Serializable {
    private String policyName;
    private String policyVersionId;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof GetPolicyVersionRequest)) {
            return false;
        }
        GetPolicyVersionRequest getPolicyVersionRequest = (GetPolicyVersionRequest) obj;
        if ((getPolicyVersionRequest.getPolicyName() == null) ^ (getPolicyName() == null)) {
            return false;
        }
        if (getPolicyVersionRequest.getPolicyName() != null && !getPolicyVersionRequest.getPolicyName().equals(getPolicyName())) {
            return false;
        }
        if ((getPolicyVersionRequest.getPolicyVersionId() == null) ^ (getPolicyVersionId() == null)) {
            return false;
        }
        return getPolicyVersionRequest.getPolicyVersionId() == null || getPolicyVersionRequest.getPolicyVersionId().equals(getPolicyVersionId());
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

    public GetPolicyVersionRequest withPolicyName(String str) {
        this.policyName = str;
        return this;
    }

    public GetPolicyVersionRequest withPolicyVersionId(String str) {
        this.policyVersionId = str;
        return this;
    }
}
