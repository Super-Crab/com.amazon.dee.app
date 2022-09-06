package com.amazonaws.services.securitytoken.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class GetCallerIdentityResult implements Serializable {
    private String account;
    private String arn;
    private String userId;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof GetCallerIdentityResult)) {
            return false;
        }
        GetCallerIdentityResult getCallerIdentityResult = (GetCallerIdentityResult) obj;
        if ((getCallerIdentityResult.getUserId() == null) ^ (getUserId() == null)) {
            return false;
        }
        if (getCallerIdentityResult.getUserId() != null && !getCallerIdentityResult.getUserId().equals(getUserId())) {
            return false;
        }
        if ((getCallerIdentityResult.getAccount() == null) ^ (getAccount() == null)) {
            return false;
        }
        if (getCallerIdentityResult.getAccount() != null && !getCallerIdentityResult.getAccount().equals(getAccount())) {
            return false;
        }
        if ((getCallerIdentityResult.getArn() == null) ^ (getArn() == null)) {
            return false;
        }
        return getCallerIdentityResult.getArn() == null || getCallerIdentityResult.getArn().equals(getArn());
    }

    public String getAccount() {
        return this.account;
    }

    public String getArn() {
        return this.arn;
    }

    public String getUserId() {
        return this.userId;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((getUserId() == null ? 0 : getUserId().hashCode()) + 31) * 31) + (getAccount() == null ? 0 : getAccount().hashCode())) * 31;
        if (getArn() != null) {
            i = getArn().hashCode();
        }
        return hashCode + i;
    }

    public void setAccount(String str) {
        this.account = str;
    }

    public void setArn(String str) {
        this.arn = str;
    }

    public void setUserId(String str) {
        this.userId = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getUserId() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("UserId: ");
            outline1072.append(getUserId());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getAccount() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("Account: ");
            outline1073.append(getAccount());
            outline1073.append(",");
            outline107.append(outline1073.toString());
        }
        if (getArn() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("Arn: ");
            outline1074.append(getArn());
            outline107.append(outline1074.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public GetCallerIdentityResult withAccount(String str) {
        this.account = str;
        return this;
    }

    public GetCallerIdentityResult withArn(String str) {
        this.arn = str;
        return this;
    }

    public GetCallerIdentityResult withUserId(String str) {
        this.userId = str;
        return this;
    }
}
