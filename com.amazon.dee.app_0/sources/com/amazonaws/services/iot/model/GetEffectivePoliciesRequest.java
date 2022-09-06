package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazonaws.AmazonWebServiceRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class GetEffectivePoliciesRequest extends AmazonWebServiceRequest implements Serializable {
    private String cognitoIdentityPoolId;
    private String principal;
    private String thingName;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof GetEffectivePoliciesRequest)) {
            return false;
        }
        GetEffectivePoliciesRequest getEffectivePoliciesRequest = (GetEffectivePoliciesRequest) obj;
        if ((getEffectivePoliciesRequest.getPrincipal() == null) ^ (getPrincipal() == null)) {
            return false;
        }
        if (getEffectivePoliciesRequest.getPrincipal() != null && !getEffectivePoliciesRequest.getPrincipal().equals(getPrincipal())) {
            return false;
        }
        if ((getEffectivePoliciesRequest.getCognitoIdentityPoolId() == null) ^ (getCognitoIdentityPoolId() == null)) {
            return false;
        }
        if (getEffectivePoliciesRequest.getCognitoIdentityPoolId() != null && !getEffectivePoliciesRequest.getCognitoIdentityPoolId().equals(getCognitoIdentityPoolId())) {
            return false;
        }
        if ((getEffectivePoliciesRequest.getThingName() == null) ^ (getThingName() == null)) {
            return false;
        }
        return getEffectivePoliciesRequest.getThingName() == null || getEffectivePoliciesRequest.getThingName().equals(getThingName());
    }

    public String getCognitoIdentityPoolId() {
        return this.cognitoIdentityPoolId;
    }

    public String getPrincipal() {
        return this.principal;
    }

    public String getThingName() {
        return this.thingName;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((getPrincipal() == null ? 0 : getPrincipal().hashCode()) + 31) * 31) + (getCognitoIdentityPoolId() == null ? 0 : getCognitoIdentityPoolId().hashCode())) * 31;
        if (getThingName() != null) {
            i = getThingName().hashCode();
        }
        return hashCode + i;
    }

    public void setCognitoIdentityPoolId(String str) {
        this.cognitoIdentityPoolId = str;
    }

    public void setPrincipal(String str) {
        this.principal = str;
    }

    public void setThingName(String str) {
        this.thingName = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getPrincipal() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("principal: ");
            outline1072.append(getPrincipal());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getCognitoIdentityPoolId() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("cognitoIdentityPoolId: ");
            outline1073.append(getCognitoIdentityPoolId());
            outline1073.append(",");
            outline107.append(outline1073.toString());
        }
        if (getThingName() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("thingName: ");
            outline1074.append(getThingName());
            outline107.append(outline1074.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public GetEffectivePoliciesRequest withCognitoIdentityPoolId(String str) {
        this.cognitoIdentityPoolId = str;
        return this;
    }

    public GetEffectivePoliciesRequest withPrincipal(String str) {
        this.principal = str;
        return this;
    }

    public GetEffectivePoliciesRequest withThingName(String str) {
        this.thingName = str;
        return this;
    }
}
