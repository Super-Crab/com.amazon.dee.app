package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazonaws.AmazonWebServiceRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class UpdateRoleAliasRequest extends AmazonWebServiceRequest implements Serializable {
    private Integer credentialDurationSeconds;
    private String roleAlias;
    private String roleArn;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof UpdateRoleAliasRequest)) {
            return false;
        }
        UpdateRoleAliasRequest updateRoleAliasRequest = (UpdateRoleAliasRequest) obj;
        if ((updateRoleAliasRequest.getRoleAlias() == null) ^ (getRoleAlias() == null)) {
            return false;
        }
        if (updateRoleAliasRequest.getRoleAlias() != null && !updateRoleAliasRequest.getRoleAlias().equals(getRoleAlias())) {
            return false;
        }
        if ((updateRoleAliasRequest.getRoleArn() == null) ^ (getRoleArn() == null)) {
            return false;
        }
        if (updateRoleAliasRequest.getRoleArn() != null && !updateRoleAliasRequest.getRoleArn().equals(getRoleArn())) {
            return false;
        }
        if ((updateRoleAliasRequest.getCredentialDurationSeconds() == null) ^ (getCredentialDurationSeconds() == null)) {
            return false;
        }
        return updateRoleAliasRequest.getCredentialDurationSeconds() == null || updateRoleAliasRequest.getCredentialDurationSeconds().equals(getCredentialDurationSeconds());
    }

    public Integer getCredentialDurationSeconds() {
        return this.credentialDurationSeconds;
    }

    public String getRoleAlias() {
        return this.roleAlias;
    }

    public String getRoleArn() {
        return this.roleArn;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((getRoleAlias() == null ? 0 : getRoleAlias().hashCode()) + 31) * 31) + (getRoleArn() == null ? 0 : getRoleArn().hashCode())) * 31;
        if (getCredentialDurationSeconds() != null) {
            i = getCredentialDurationSeconds().hashCode();
        }
        return hashCode + i;
    }

    public void setCredentialDurationSeconds(Integer num) {
        this.credentialDurationSeconds = num;
    }

    public void setRoleAlias(String str) {
        this.roleAlias = str;
    }

    public void setRoleArn(String str) {
        this.roleArn = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getRoleAlias() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("roleAlias: ");
            outline1072.append(getRoleAlias());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getRoleArn() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("roleArn: ");
            outline1073.append(getRoleArn());
            outline1073.append(",");
            outline107.append(outline1073.toString());
        }
        if (getCredentialDurationSeconds() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("credentialDurationSeconds: ");
            outline1074.append(getCredentialDurationSeconds());
            outline107.append(outline1074.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public UpdateRoleAliasRequest withCredentialDurationSeconds(Integer num) {
        this.credentialDurationSeconds = num;
        return this;
    }

    public UpdateRoleAliasRequest withRoleAlias(String str) {
        this.roleAlias = str;
        return this;
    }

    public UpdateRoleAliasRequest withRoleArn(String str) {
        this.roleArn = str;
        return this;
    }
}
