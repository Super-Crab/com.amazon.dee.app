package com.amazon.clouddrive.model;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Set;
import kotlinx.serialization.json.internal.JsonReaderKt;
/* loaded from: classes11.dex */
public final class AccessRule {
    private final Set<NodeAction> allowedNodeActions;
    private final String ruleId;

    public AccessRule(String str, Set<NodeAction> set) {
        this.ruleId = str;
        this.allowedNodeActions = set;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || AccessRule.class != obj.getClass()) {
            return false;
        }
        AccessRule accessRule = (AccessRule) obj;
        return getRuleId().equals(accessRule.getRuleId()) && getAllowedNodeActions().equals(accessRule.getAllowedNodeActions());
    }

    public final Set<NodeAction> getAllowedNodeActions() {
        return this.allowedNodeActions;
    }

    public final String getRuleId() {
        return this.ruleId;
    }

    public int hashCode() {
        return getAllowedNodeActions().hashCode() + (getRuleId().hashCode() * 31);
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("AccessRule{ruleId=");
        outline107.append(this.ruleId);
        outline107.append(", allowedNodeActions=");
        outline107.append(this.allowedNodeActions);
        outline107.append(JsonReaderKt.END_OBJ);
        return outline107.toString();
    }
}
