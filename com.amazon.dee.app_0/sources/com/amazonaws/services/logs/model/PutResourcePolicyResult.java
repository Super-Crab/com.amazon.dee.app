package com.amazonaws.services.logs.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class PutResourcePolicyResult implements Serializable {
    private ResourcePolicy resourcePolicy;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof PutResourcePolicyResult)) {
            return false;
        }
        PutResourcePolicyResult putResourcePolicyResult = (PutResourcePolicyResult) obj;
        if ((putResourcePolicyResult.getResourcePolicy() == null) ^ (getResourcePolicy() == null)) {
            return false;
        }
        return putResourcePolicyResult.getResourcePolicy() == null || putResourcePolicyResult.getResourcePolicy().equals(getResourcePolicy());
    }

    public ResourcePolicy getResourcePolicy() {
        return this.resourcePolicy;
    }

    public int hashCode() {
        return 31 + (getResourcePolicy() == null ? 0 : getResourcePolicy().hashCode());
    }

    public void setResourcePolicy(ResourcePolicy resourcePolicy) {
        this.resourcePolicy = resourcePolicy;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getResourcePolicy() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("resourcePolicy: ");
            outline1072.append(getResourcePolicy());
            outline107.append(outline1072.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public PutResourcePolicyResult withResourcePolicy(ResourcePolicy resourcePolicy) {
        this.resourcePolicy = resourcePolicy;
        return this;
    }
}
