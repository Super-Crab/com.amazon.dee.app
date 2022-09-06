package com.amazonaws.services.cognitoidentity.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class MappingRule implements Serializable {
    private String claim;
    private String matchType;
    private String roleARN;
    private String value;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof MappingRule)) {
            return false;
        }
        MappingRule mappingRule = (MappingRule) obj;
        if ((mappingRule.getClaim() == null) ^ (getClaim() == null)) {
            return false;
        }
        if (mappingRule.getClaim() != null && !mappingRule.getClaim().equals(getClaim())) {
            return false;
        }
        if ((mappingRule.getMatchType() == null) ^ (getMatchType() == null)) {
            return false;
        }
        if (mappingRule.getMatchType() != null && !mappingRule.getMatchType().equals(getMatchType())) {
            return false;
        }
        if ((mappingRule.getValue() == null) ^ (getValue() == null)) {
            return false;
        }
        if (mappingRule.getValue() != null && !mappingRule.getValue().equals(getValue())) {
            return false;
        }
        if ((mappingRule.getRoleARN() == null) ^ (getRoleARN() == null)) {
            return false;
        }
        return mappingRule.getRoleARN() == null || mappingRule.getRoleARN().equals(getRoleARN());
    }

    public String getClaim() {
        return this.claim;
    }

    public String getMatchType() {
        return this.matchType;
    }

    public String getRoleARN() {
        return this.roleARN;
    }

    public String getValue() {
        return this.value;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((((getClaim() == null ? 0 : getClaim().hashCode()) + 31) * 31) + (getMatchType() == null ? 0 : getMatchType().hashCode())) * 31) + (getValue() == null ? 0 : getValue().hashCode())) * 31;
        if (getRoleARN() != null) {
            i = getRoleARN().hashCode();
        }
        return hashCode + i;
    }

    public void setClaim(String str) {
        this.claim = str;
    }

    public void setMatchType(String str) {
        this.matchType = str;
    }

    public void setRoleARN(String str) {
        this.roleARN = str;
    }

    public void setValue(String str) {
        this.value = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getClaim() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Claim: ");
            outline1072.append(getClaim());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getMatchType() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("MatchType: ");
            outline1073.append(getMatchType());
            outline1073.append(",");
            outline107.append(outline1073.toString());
        }
        if (getValue() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("Value: ");
            outline1074.append(getValue());
            outline1074.append(",");
            outline107.append(outline1074.toString());
        }
        if (getRoleARN() != null) {
            StringBuilder outline1075 = GeneratedOutlineSupport1.outline107("RoleARN: ");
            outline1075.append(getRoleARN());
            outline107.append(outline1075.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public MappingRule withClaim(String str) {
        this.claim = str;
        return this;
    }

    public MappingRule withMatchType(String str) {
        this.matchType = str;
        return this;
    }

    public MappingRule withRoleARN(String str) {
        this.roleARN = str;
        return this;
    }

    public MappingRule withValue(String str) {
        this.value = str;
        return this;
    }

    public void setMatchType(MappingRuleMatchType mappingRuleMatchType) {
        this.matchType = mappingRuleMatchType.toString();
    }

    public MappingRule withMatchType(MappingRuleMatchType mappingRuleMatchType) {
        this.matchType = mappingRuleMatchType.toString();
        return this;
    }
}
