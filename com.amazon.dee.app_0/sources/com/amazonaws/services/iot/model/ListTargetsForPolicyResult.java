package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
/* loaded from: classes13.dex */
public class ListTargetsForPolicyResult implements Serializable {
    private String nextMarker;
    private List<String> targets;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ListTargetsForPolicyResult)) {
            return false;
        }
        ListTargetsForPolicyResult listTargetsForPolicyResult = (ListTargetsForPolicyResult) obj;
        if ((listTargetsForPolicyResult.getTargets() == null) ^ (getTargets() == null)) {
            return false;
        }
        if (listTargetsForPolicyResult.getTargets() != null && !listTargetsForPolicyResult.getTargets().equals(getTargets())) {
            return false;
        }
        if ((listTargetsForPolicyResult.getNextMarker() == null) ^ (getNextMarker() == null)) {
            return false;
        }
        return listTargetsForPolicyResult.getNextMarker() == null || listTargetsForPolicyResult.getNextMarker().equals(getNextMarker());
    }

    public String getNextMarker() {
        return this.nextMarker;
    }

    public List<String> getTargets() {
        return this.targets;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((getTargets() == null ? 0 : getTargets().hashCode()) + 31) * 31;
        if (getNextMarker() != null) {
            i = getNextMarker().hashCode();
        }
        return hashCode + i;
    }

    public void setNextMarker(String str) {
        this.nextMarker = str;
    }

    public void setTargets(Collection<String> collection) {
        if (collection == null) {
            this.targets = null;
        } else {
            this.targets = new ArrayList(collection);
        }
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getTargets() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("targets: ");
            outline1072.append(getTargets());
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

    public ListTargetsForPolicyResult withNextMarker(String str) {
        this.nextMarker = str;
        return this;
    }

    public ListTargetsForPolicyResult withTargets(String... strArr) {
        if (getTargets() == null) {
            this.targets = new ArrayList(strArr.length);
        }
        for (String str : strArr) {
            this.targets.add(str);
        }
        return this;
    }

    public ListTargetsForPolicyResult withTargets(Collection<String> collection) {
        setTargets(collection);
        return this;
    }
}
