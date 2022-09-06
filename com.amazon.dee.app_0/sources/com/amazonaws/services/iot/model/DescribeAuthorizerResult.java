package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class DescribeAuthorizerResult implements Serializable {
    private AuthorizerDescription authorizerDescription;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof DescribeAuthorizerResult)) {
            return false;
        }
        DescribeAuthorizerResult describeAuthorizerResult = (DescribeAuthorizerResult) obj;
        if ((describeAuthorizerResult.getAuthorizerDescription() == null) ^ (getAuthorizerDescription() == null)) {
            return false;
        }
        return describeAuthorizerResult.getAuthorizerDescription() == null || describeAuthorizerResult.getAuthorizerDescription().equals(getAuthorizerDescription());
    }

    public AuthorizerDescription getAuthorizerDescription() {
        return this.authorizerDescription;
    }

    public int hashCode() {
        return 31 + (getAuthorizerDescription() == null ? 0 : getAuthorizerDescription().hashCode());
    }

    public void setAuthorizerDescription(AuthorizerDescription authorizerDescription) {
        this.authorizerDescription = authorizerDescription;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getAuthorizerDescription() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("authorizerDescription: ");
            outline1072.append(getAuthorizerDescription());
            outline107.append(outline1072.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public DescribeAuthorizerResult withAuthorizerDescription(AuthorizerDescription authorizerDescription) {
        this.authorizerDescription = authorizerDescription;
        return this;
    }
}
