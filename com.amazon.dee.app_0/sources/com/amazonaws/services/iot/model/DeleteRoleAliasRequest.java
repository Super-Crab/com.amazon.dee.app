package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazonaws.AmazonWebServiceRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class DeleteRoleAliasRequest extends AmazonWebServiceRequest implements Serializable {
    private String roleAlias;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof DeleteRoleAliasRequest)) {
            return false;
        }
        DeleteRoleAliasRequest deleteRoleAliasRequest = (DeleteRoleAliasRequest) obj;
        if ((deleteRoleAliasRequest.getRoleAlias() == null) ^ (getRoleAlias() == null)) {
            return false;
        }
        return deleteRoleAliasRequest.getRoleAlias() == null || deleteRoleAliasRequest.getRoleAlias().equals(getRoleAlias());
    }

    public String getRoleAlias() {
        return this.roleAlias;
    }

    public int hashCode() {
        return 31 + (getRoleAlias() == null ? 0 : getRoleAlias().hashCode());
    }

    public void setRoleAlias(String str) {
        this.roleAlias = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getRoleAlias() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("roleAlias: ");
            outline1072.append(getRoleAlias());
            outline107.append(outline1072.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public DeleteRoleAliasRequest withRoleAlias(String str) {
        this.roleAlias = str;
        return this;
    }
}
