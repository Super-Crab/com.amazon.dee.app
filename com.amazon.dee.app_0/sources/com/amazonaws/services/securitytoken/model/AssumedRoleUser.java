package com.amazonaws.services.securitytoken.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class AssumedRoleUser implements Serializable {
    private String arn;
    private String assumedRoleId;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof AssumedRoleUser)) {
            return false;
        }
        AssumedRoleUser assumedRoleUser = (AssumedRoleUser) obj;
        if ((assumedRoleUser.getAssumedRoleId() == null) ^ (getAssumedRoleId() == null)) {
            return false;
        }
        if (assumedRoleUser.getAssumedRoleId() != null && !assumedRoleUser.getAssumedRoleId().equals(getAssumedRoleId())) {
            return false;
        }
        if ((assumedRoleUser.getArn() == null) ^ (getArn() == null)) {
            return false;
        }
        return assumedRoleUser.getArn() == null || assumedRoleUser.getArn().equals(getArn());
    }

    public String getArn() {
        return this.arn;
    }

    public String getAssumedRoleId() {
        return this.assumedRoleId;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((getAssumedRoleId() == null ? 0 : getAssumedRoleId().hashCode()) + 31) * 31;
        if (getArn() != null) {
            i = getArn().hashCode();
        }
        return hashCode + i;
    }

    public void setArn(String str) {
        this.arn = str;
    }

    public void setAssumedRoleId(String str) {
        this.assumedRoleId = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getAssumedRoleId() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("AssumedRoleId: ");
            outline1072.append(getAssumedRoleId());
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

    public AssumedRoleUser withArn(String str) {
        this.arn = str;
        return this;
    }

    public AssumedRoleUser withAssumedRoleId(String str) {
        this.assumedRoleId = str;
        return this;
    }
}
