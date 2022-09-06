package com.amazonaws.services.securitytoken.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class FederatedUser implements Serializable {
    private String arn;
    private String federatedUserId;

    public FederatedUser() {
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof FederatedUser)) {
            return false;
        }
        FederatedUser federatedUser = (FederatedUser) obj;
        if ((federatedUser.getFederatedUserId() == null) ^ (getFederatedUserId() == null)) {
            return false;
        }
        if (federatedUser.getFederatedUserId() != null && !federatedUser.getFederatedUserId().equals(getFederatedUserId())) {
            return false;
        }
        if ((federatedUser.getArn() == null) ^ (getArn() == null)) {
            return false;
        }
        return federatedUser.getArn() == null || federatedUser.getArn().equals(getArn());
    }

    public String getArn() {
        return this.arn;
    }

    public String getFederatedUserId() {
        return this.federatedUserId;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((getFederatedUserId() == null ? 0 : getFederatedUserId().hashCode()) + 31) * 31;
        if (getArn() != null) {
            i = getArn().hashCode();
        }
        return hashCode + i;
    }

    public void setArn(String str) {
        this.arn = str;
    }

    public void setFederatedUserId(String str) {
        this.federatedUserId = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getFederatedUserId() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("FederatedUserId: ");
            outline1072.append(getFederatedUserId());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getArn() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("Arn: ");
            outline1073.append(getArn());
            outline107.append(outline1073.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public FederatedUser withArn(String str) {
        this.arn = str;
        return this;
    }

    public FederatedUser withFederatedUserId(String str) {
        this.federatedUserId = str;
        return this;
    }

    public FederatedUser(String str, String str2) {
        setFederatedUserId(str);
        setArn(str2);
    }
}
