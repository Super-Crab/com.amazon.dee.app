package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
/* loaded from: classes13.dex */
public class GetEffectivePoliciesResult implements Serializable {
    private List<EffectivePolicy> effectivePolicies;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof GetEffectivePoliciesResult)) {
            return false;
        }
        GetEffectivePoliciesResult getEffectivePoliciesResult = (GetEffectivePoliciesResult) obj;
        if ((getEffectivePoliciesResult.getEffectivePolicies() == null) ^ (getEffectivePolicies() == null)) {
            return false;
        }
        return getEffectivePoliciesResult.getEffectivePolicies() == null || getEffectivePoliciesResult.getEffectivePolicies().equals(getEffectivePolicies());
    }

    public List<EffectivePolicy> getEffectivePolicies() {
        return this.effectivePolicies;
    }

    public int hashCode() {
        return 31 + (getEffectivePolicies() == null ? 0 : getEffectivePolicies().hashCode());
    }

    public void setEffectivePolicies(Collection<EffectivePolicy> collection) {
        if (collection == null) {
            this.effectivePolicies = null;
        } else {
            this.effectivePolicies = new ArrayList(collection);
        }
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getEffectivePolicies() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("effectivePolicies: ");
            outline1072.append(getEffectivePolicies());
            outline107.append(outline1072.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public GetEffectivePoliciesResult withEffectivePolicies(EffectivePolicy... effectivePolicyArr) {
        if (getEffectivePolicies() == null) {
            this.effectivePolicies = new ArrayList(effectivePolicyArr.length);
        }
        for (EffectivePolicy effectivePolicy : effectivePolicyArr) {
            this.effectivePolicies.add(effectivePolicy);
        }
        return this;
    }

    public GetEffectivePoliciesResult withEffectivePolicies(Collection<EffectivePolicy> collection) {
        setEffectivePolicies(collection);
        return this;
    }
}
