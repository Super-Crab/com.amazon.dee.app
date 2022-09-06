package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazonaws.AmazonWebServiceRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
/* loaded from: classes13.dex */
public class StartOnDemandAuditTaskRequest extends AmazonWebServiceRequest implements Serializable {
    private List<String> targetCheckNames;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof StartOnDemandAuditTaskRequest)) {
            return false;
        }
        StartOnDemandAuditTaskRequest startOnDemandAuditTaskRequest = (StartOnDemandAuditTaskRequest) obj;
        if ((startOnDemandAuditTaskRequest.getTargetCheckNames() == null) ^ (getTargetCheckNames() == null)) {
            return false;
        }
        return startOnDemandAuditTaskRequest.getTargetCheckNames() == null || startOnDemandAuditTaskRequest.getTargetCheckNames().equals(getTargetCheckNames());
    }

    public List<String> getTargetCheckNames() {
        return this.targetCheckNames;
    }

    public int hashCode() {
        return 31 + (getTargetCheckNames() == null ? 0 : getTargetCheckNames().hashCode());
    }

    public void setTargetCheckNames(Collection<String> collection) {
        if (collection == null) {
            this.targetCheckNames = null;
        } else {
            this.targetCheckNames = new ArrayList(collection);
        }
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getTargetCheckNames() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("targetCheckNames: ");
            outline1072.append(getTargetCheckNames());
            outline107.append(outline1072.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public StartOnDemandAuditTaskRequest withTargetCheckNames(String... strArr) {
        if (getTargetCheckNames() == null) {
            this.targetCheckNames = new ArrayList(strArr.length);
        }
        for (String str : strArr) {
            this.targetCheckNames.add(str);
        }
        return this;
    }

    public StartOnDemandAuditTaskRequest withTargetCheckNames(Collection<String> collection) {
        setTargetCheckNames(collection);
        return this;
    }
}
