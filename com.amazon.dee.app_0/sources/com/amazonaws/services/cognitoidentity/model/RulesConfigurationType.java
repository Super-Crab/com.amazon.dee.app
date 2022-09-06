package com.amazonaws.services.cognitoidentity.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
/* loaded from: classes13.dex */
public class RulesConfigurationType implements Serializable {
    private List<MappingRule> rules;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof RulesConfigurationType)) {
            return false;
        }
        RulesConfigurationType rulesConfigurationType = (RulesConfigurationType) obj;
        if ((rulesConfigurationType.getRules() == null) ^ (getRules() == null)) {
            return false;
        }
        return rulesConfigurationType.getRules() == null || rulesConfigurationType.getRules().equals(getRules());
    }

    public List<MappingRule> getRules() {
        return this.rules;
    }

    public int hashCode() {
        return 31 + (getRules() == null ? 0 : getRules().hashCode());
    }

    public void setRules(Collection<MappingRule> collection) {
        if (collection == null) {
            this.rules = null;
        } else {
            this.rules = new ArrayList(collection);
        }
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getRules() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Rules: ");
            outline1072.append(getRules());
            outline107.append(outline1072.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public RulesConfigurationType withRules(MappingRule... mappingRuleArr) {
        if (getRules() == null) {
            this.rules = new ArrayList(mappingRuleArr.length);
        }
        for (MappingRule mappingRule : mappingRuleArr) {
            this.rules.add(mappingRule);
        }
        return this;
    }

    public RulesConfigurationType withRules(Collection<MappingRule> collection) {
        setRules(collection);
        return this;
    }
}
