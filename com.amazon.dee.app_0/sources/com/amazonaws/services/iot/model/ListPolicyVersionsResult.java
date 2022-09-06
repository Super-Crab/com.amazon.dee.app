package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
/* loaded from: classes13.dex */
public class ListPolicyVersionsResult implements Serializable {
    private List<PolicyVersion> policyVersions;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ListPolicyVersionsResult)) {
            return false;
        }
        ListPolicyVersionsResult listPolicyVersionsResult = (ListPolicyVersionsResult) obj;
        if ((listPolicyVersionsResult.getPolicyVersions() == null) ^ (getPolicyVersions() == null)) {
            return false;
        }
        return listPolicyVersionsResult.getPolicyVersions() == null || listPolicyVersionsResult.getPolicyVersions().equals(getPolicyVersions());
    }

    public List<PolicyVersion> getPolicyVersions() {
        return this.policyVersions;
    }

    public int hashCode() {
        return 31 + (getPolicyVersions() == null ? 0 : getPolicyVersions().hashCode());
    }

    public void setPolicyVersions(Collection<PolicyVersion> collection) {
        if (collection == null) {
            this.policyVersions = null;
        } else {
            this.policyVersions = new ArrayList(collection);
        }
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getPolicyVersions() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("policyVersions: ");
            outline1072.append(getPolicyVersions());
            outline107.append(outline1072.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public ListPolicyVersionsResult withPolicyVersions(PolicyVersion... policyVersionArr) {
        if (getPolicyVersions() == null) {
            this.policyVersions = new ArrayList(policyVersionArr.length);
        }
        for (PolicyVersion policyVersion : policyVersionArr) {
            this.policyVersions.add(policyVersion);
        }
        return this;
    }

    public ListPolicyVersionsResult withPolicyVersions(Collection<PolicyVersion> collection) {
        setPolicyVersions(collection);
        return this;
    }
}
