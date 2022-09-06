package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
/* loaded from: classes13.dex */
public class Allowed implements Serializable {
    private List<Policy> policies;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof Allowed)) {
            return false;
        }
        Allowed allowed = (Allowed) obj;
        if ((allowed.getPolicies() == null) ^ (getPolicies() == null)) {
            return false;
        }
        return allowed.getPolicies() == null || allowed.getPolicies().equals(getPolicies());
    }

    public List<Policy> getPolicies() {
        return this.policies;
    }

    public int hashCode() {
        return 31 + (getPolicies() == null ? 0 : getPolicies().hashCode());
    }

    public void setPolicies(Collection<Policy> collection) {
        if (collection == null) {
            this.policies = null;
        } else {
            this.policies = new ArrayList(collection);
        }
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getPolicies() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("policies: ");
            outline1072.append(getPolicies());
            outline107.append(outline1072.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public Allowed withPolicies(Policy... policyArr) {
        if (getPolicies() == null) {
            this.policies = new ArrayList(policyArr.length);
        }
        for (Policy policy : policyArr) {
            this.policies.add(policy);
        }
        return this;
    }

    public Allowed withPolicies(Collection<Policy> collection) {
        setPolicies(collection);
        return this;
    }
}
