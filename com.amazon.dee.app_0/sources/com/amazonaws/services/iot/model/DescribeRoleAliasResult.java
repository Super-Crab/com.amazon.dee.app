package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class DescribeRoleAliasResult implements Serializable {
    private RoleAliasDescription roleAliasDescription;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof DescribeRoleAliasResult)) {
            return false;
        }
        DescribeRoleAliasResult describeRoleAliasResult = (DescribeRoleAliasResult) obj;
        if ((describeRoleAliasResult.getRoleAliasDescription() == null) ^ (getRoleAliasDescription() == null)) {
            return false;
        }
        return describeRoleAliasResult.getRoleAliasDescription() == null || describeRoleAliasResult.getRoleAliasDescription().equals(getRoleAliasDescription());
    }

    public RoleAliasDescription getRoleAliasDescription() {
        return this.roleAliasDescription;
    }

    public int hashCode() {
        return 31 + (getRoleAliasDescription() == null ? 0 : getRoleAliasDescription().hashCode());
    }

    public void setRoleAliasDescription(RoleAliasDescription roleAliasDescription) {
        this.roleAliasDescription = roleAliasDescription;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getRoleAliasDescription() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("roleAliasDescription: ");
            outline1072.append(getRoleAliasDescription());
            outline107.append(outline1072.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public DescribeRoleAliasResult withRoleAliasDescription(RoleAliasDescription roleAliasDescription) {
        this.roleAliasDescription = roleAliasDescription;
        return this;
    }
}
