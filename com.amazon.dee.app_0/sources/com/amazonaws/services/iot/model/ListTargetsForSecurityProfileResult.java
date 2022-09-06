package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
/* loaded from: classes13.dex */
public class ListTargetsForSecurityProfileResult implements Serializable {
    private String nextToken;
    private List<SecurityProfileTarget> securityProfileTargets;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ListTargetsForSecurityProfileResult)) {
            return false;
        }
        ListTargetsForSecurityProfileResult listTargetsForSecurityProfileResult = (ListTargetsForSecurityProfileResult) obj;
        if ((listTargetsForSecurityProfileResult.getSecurityProfileTargets() == null) ^ (getSecurityProfileTargets() == null)) {
            return false;
        }
        if (listTargetsForSecurityProfileResult.getSecurityProfileTargets() != null && !listTargetsForSecurityProfileResult.getSecurityProfileTargets().equals(getSecurityProfileTargets())) {
            return false;
        }
        if ((listTargetsForSecurityProfileResult.getNextToken() == null) ^ (getNextToken() == null)) {
            return false;
        }
        return listTargetsForSecurityProfileResult.getNextToken() == null || listTargetsForSecurityProfileResult.getNextToken().equals(getNextToken());
    }

    public String getNextToken() {
        return this.nextToken;
    }

    public List<SecurityProfileTarget> getSecurityProfileTargets() {
        return this.securityProfileTargets;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((getSecurityProfileTargets() == null ? 0 : getSecurityProfileTargets().hashCode()) + 31) * 31;
        if (getNextToken() != null) {
            i = getNextToken().hashCode();
        }
        return hashCode + i;
    }

    public void setNextToken(String str) {
        this.nextToken = str;
    }

    public void setSecurityProfileTargets(Collection<SecurityProfileTarget> collection) {
        if (collection == null) {
            this.securityProfileTargets = null;
        } else {
            this.securityProfileTargets = new ArrayList(collection);
        }
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getSecurityProfileTargets() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("securityProfileTargets: ");
            outline1072.append(getSecurityProfileTargets());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getNextToken() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("nextToken: ");
            outline1073.append(getNextToken());
            outline107.append(outline1073.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public ListTargetsForSecurityProfileResult withNextToken(String str) {
        this.nextToken = str;
        return this;
    }

    public ListTargetsForSecurityProfileResult withSecurityProfileTargets(SecurityProfileTarget... securityProfileTargetArr) {
        if (getSecurityProfileTargets() == null) {
            this.securityProfileTargets = new ArrayList(securityProfileTargetArr.length);
        }
        for (SecurityProfileTarget securityProfileTarget : securityProfileTargetArr) {
            this.securityProfileTargets.add(securityProfileTarget);
        }
        return this;
    }

    public ListTargetsForSecurityProfileResult withSecurityProfileTargets(Collection<SecurityProfileTarget> collection) {
        setSecurityProfileTargets(collection);
        return this;
    }
}
