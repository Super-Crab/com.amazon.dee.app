package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class CreateRoleAliasResult implements Serializable {
    private String roleAlias;
    private String roleAliasArn;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof CreateRoleAliasResult)) {
            return false;
        }
        CreateRoleAliasResult createRoleAliasResult = (CreateRoleAliasResult) obj;
        if ((createRoleAliasResult.getRoleAlias() == null) ^ (getRoleAlias() == null)) {
            return false;
        }
        if (createRoleAliasResult.getRoleAlias() != null && !createRoleAliasResult.getRoleAlias().equals(getRoleAlias())) {
            return false;
        }
        if ((createRoleAliasResult.getRoleAliasArn() == null) ^ (getRoleAliasArn() == null)) {
            return false;
        }
        return createRoleAliasResult.getRoleAliasArn() == null || createRoleAliasResult.getRoleAliasArn().equals(getRoleAliasArn());
    }

    public String getRoleAlias() {
        return this.roleAlias;
    }

    public String getRoleAliasArn() {
        return this.roleAliasArn;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((getRoleAlias() == null ? 0 : getRoleAlias().hashCode()) + 31) * 31;
        if (getRoleAliasArn() != null) {
            i = getRoleAliasArn().hashCode();
        }
        return hashCode + i;
    }

    public void setRoleAlias(String str) {
        this.roleAlias = str;
    }

    public void setRoleAliasArn(String str) {
        this.roleAliasArn = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getRoleAlias() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("roleAlias: ");
            outline1072.append(getRoleAlias());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getRoleAliasArn() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("roleAliasArn: ");
            outline1073.append(getRoleAliasArn());
            outline107.append(outline1073.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public CreateRoleAliasResult withRoleAlias(String str) {
        this.roleAlias = str;
        return this;
    }

    public CreateRoleAliasResult withRoleAliasArn(String str) {
        this.roleAliasArn = str;
        return this;
    }
}
