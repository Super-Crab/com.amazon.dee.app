package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class AuthorizerSummary implements Serializable {
    private String authorizerArn;
    private String authorizerName;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof AuthorizerSummary)) {
            return false;
        }
        AuthorizerSummary authorizerSummary = (AuthorizerSummary) obj;
        if ((authorizerSummary.getAuthorizerName() == null) ^ (getAuthorizerName() == null)) {
            return false;
        }
        if (authorizerSummary.getAuthorizerName() != null && !authorizerSummary.getAuthorizerName().equals(getAuthorizerName())) {
            return false;
        }
        if ((authorizerSummary.getAuthorizerArn() == null) ^ (getAuthorizerArn() == null)) {
            return false;
        }
        return authorizerSummary.getAuthorizerArn() == null || authorizerSummary.getAuthorizerArn().equals(getAuthorizerArn());
    }

    public String getAuthorizerArn() {
        return this.authorizerArn;
    }

    public String getAuthorizerName() {
        return this.authorizerName;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((getAuthorizerName() == null ? 0 : getAuthorizerName().hashCode()) + 31) * 31;
        if (getAuthorizerArn() != null) {
            i = getAuthorizerArn().hashCode();
        }
        return hashCode + i;
    }

    public void setAuthorizerArn(String str) {
        this.authorizerArn = str;
    }

    public void setAuthorizerName(String str) {
        this.authorizerName = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getAuthorizerName() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("authorizerName: ");
            outline1072.append(getAuthorizerName());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getAuthorizerArn() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("authorizerArn: ");
            outline1073.append(getAuthorizerArn());
            outline107.append(outline1073.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public AuthorizerSummary withAuthorizerArn(String str) {
        this.authorizerArn = str;
        return this;
    }

    public AuthorizerSummary withAuthorizerName(String str) {
        this.authorizerName = str;
        return this;
    }
}
