package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazonaws.AmazonWebServiceRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class DescribeAuthorizerRequest extends AmazonWebServiceRequest implements Serializable {
    private String authorizerName;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof DescribeAuthorizerRequest)) {
            return false;
        }
        DescribeAuthorizerRequest describeAuthorizerRequest = (DescribeAuthorizerRequest) obj;
        if ((describeAuthorizerRequest.getAuthorizerName() == null) ^ (getAuthorizerName() == null)) {
            return false;
        }
        return describeAuthorizerRequest.getAuthorizerName() == null || describeAuthorizerRequest.getAuthorizerName().equals(getAuthorizerName());
    }

    public String getAuthorizerName() {
        return this.authorizerName;
    }

    public int hashCode() {
        return 31 + (getAuthorizerName() == null ? 0 : getAuthorizerName().hashCode());
    }

    public void setAuthorizerName(String str) {
        this.authorizerName = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getAuthorizerName() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("authorizerName: ");
            outline1072.append(getAuthorizerName());
            outline107.append(outline1072.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public DescribeAuthorizerRequest withAuthorizerName(String str) {
        this.authorizerName = str;
        return this;
    }
}
