package com.amazonaws.services.cognitoidentity.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class RoleMapping implements Serializable {
    private String ambiguousRoleResolution;
    private RulesConfigurationType rulesConfiguration;
    private String type;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof RoleMapping)) {
            return false;
        }
        RoleMapping roleMapping = (RoleMapping) obj;
        if ((roleMapping.getType() == null) ^ (getType() == null)) {
            return false;
        }
        if (roleMapping.getType() != null && !roleMapping.getType().equals(getType())) {
            return false;
        }
        if ((roleMapping.getAmbiguousRoleResolution() == null) ^ (getAmbiguousRoleResolution() == null)) {
            return false;
        }
        if (roleMapping.getAmbiguousRoleResolution() != null && !roleMapping.getAmbiguousRoleResolution().equals(getAmbiguousRoleResolution())) {
            return false;
        }
        if ((roleMapping.getRulesConfiguration() == null) ^ (getRulesConfiguration() == null)) {
            return false;
        }
        return roleMapping.getRulesConfiguration() == null || roleMapping.getRulesConfiguration().equals(getRulesConfiguration());
    }

    public String getAmbiguousRoleResolution() {
        return this.ambiguousRoleResolution;
    }

    public RulesConfigurationType getRulesConfiguration() {
        return this.rulesConfiguration;
    }

    public String getType() {
        return this.type;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((getType() == null ? 0 : getType().hashCode()) + 31) * 31) + (getAmbiguousRoleResolution() == null ? 0 : getAmbiguousRoleResolution().hashCode())) * 31;
        if (getRulesConfiguration() != null) {
            i = getRulesConfiguration().hashCode();
        }
        return hashCode + i;
    }

    public void setAmbiguousRoleResolution(String str) {
        this.ambiguousRoleResolution = str;
    }

    public void setRulesConfiguration(RulesConfigurationType rulesConfigurationType) {
        this.rulesConfiguration = rulesConfigurationType;
    }

    public void setType(String str) {
        this.type = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getType() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Type: ");
            outline1072.append(getType());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getAmbiguousRoleResolution() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("AmbiguousRoleResolution: ");
            outline1073.append(getAmbiguousRoleResolution());
            outline1073.append(",");
            outline107.append(outline1073.toString());
        }
        if (getRulesConfiguration() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("RulesConfiguration: ");
            outline1074.append(getRulesConfiguration());
            outline107.append(outline1074.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public RoleMapping withAmbiguousRoleResolution(String str) {
        this.ambiguousRoleResolution = str;
        return this;
    }

    public RoleMapping withRulesConfiguration(RulesConfigurationType rulesConfigurationType) {
        this.rulesConfiguration = rulesConfigurationType;
        return this;
    }

    public RoleMapping withType(String str) {
        this.type = str;
        return this;
    }

    public void setAmbiguousRoleResolution(AmbiguousRoleResolutionType ambiguousRoleResolutionType) {
        this.ambiguousRoleResolution = ambiguousRoleResolutionType.toString();
    }

    public void setType(RoleMappingType roleMappingType) {
        this.type = roleMappingType.toString();
    }

    public RoleMapping withAmbiguousRoleResolution(AmbiguousRoleResolutionType ambiguousRoleResolutionType) {
        this.ambiguousRoleResolution = ambiguousRoleResolutionType.toString();
        return this;
    }

    public RoleMapping withType(RoleMappingType roleMappingType) {
        this.type = roleMappingType.toString();
        return this;
    }
}
