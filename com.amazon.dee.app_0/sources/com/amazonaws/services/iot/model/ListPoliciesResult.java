package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
/* loaded from: classes13.dex */
public class ListPoliciesResult implements Serializable {
    private String nextMarker;
    private List<Policy> policies;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ListPoliciesResult)) {
            return false;
        }
        ListPoliciesResult listPoliciesResult = (ListPoliciesResult) obj;
        if ((listPoliciesResult.getPolicies() == null) ^ (getPolicies() == null)) {
            return false;
        }
        if (listPoliciesResult.getPolicies() != null && !listPoliciesResult.getPolicies().equals(getPolicies())) {
            return false;
        }
        if ((listPoliciesResult.getNextMarker() == null) ^ (getNextMarker() == null)) {
            return false;
        }
        return listPoliciesResult.getNextMarker() == null || listPoliciesResult.getNextMarker().equals(getNextMarker());
    }

    public String getNextMarker() {
        return this.nextMarker;
    }

    public List<Policy> getPolicies() {
        return this.policies;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((getPolicies() == null ? 0 : getPolicies().hashCode()) + 31) * 31;
        if (getNextMarker() != null) {
            i = getNextMarker().hashCode();
        }
        return hashCode + i;
    }

    public void setNextMarker(String str) {
        this.nextMarker = str;
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
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getNextMarker() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("nextMarker: ");
            outline1073.append(getNextMarker());
            outline107.append(outline1073.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public ListPoliciesResult withNextMarker(String str) {
        this.nextMarker = str;
        return this;
    }

    public ListPoliciesResult withPolicies(Policy... policyArr) {
        if (getPolicies() == null) {
            this.policies = new ArrayList(policyArr.length);
        }
        for (Policy policy : policyArr) {
            this.policies.add(policy);
        }
        return this;
    }

    public ListPoliciesResult withPolicies(Collection<Policy> collection) {
        setPolicies(collection);
        return this;
    }
}
