package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazonaws.AmazonWebServiceRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class GetPolicyRequest extends AmazonWebServiceRequest implements Serializable {
    private String policyName;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof GetPolicyRequest)) {
            return false;
        }
        GetPolicyRequest getPolicyRequest = (GetPolicyRequest) obj;
        if ((getPolicyRequest.getPolicyName() == null) ^ (getPolicyName() == null)) {
            return false;
        }
        return getPolicyRequest.getPolicyName() == null || getPolicyRequest.getPolicyName().equals(getPolicyName());
    }

    public String getPolicyName() {
        return this.policyName;
    }

    public int hashCode() {
        return 31 + (getPolicyName() == null ? 0 : getPolicyName().hashCode());
    }

    public void setPolicyName(String str) {
        this.policyName = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getPolicyName() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("policyName: ");
            outline1072.append(getPolicyName());
            outline107.append(outline1072.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public GetPolicyRequest withPolicyName(String str) {
        this.policyName = str;
        return this;
    }
}
