package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
/* loaded from: classes13.dex */
public class ListSecurityProfilesForTargetResult implements Serializable {
    private String nextToken;
    private List<SecurityProfileTargetMapping> securityProfileTargetMappings;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ListSecurityProfilesForTargetResult)) {
            return false;
        }
        ListSecurityProfilesForTargetResult listSecurityProfilesForTargetResult = (ListSecurityProfilesForTargetResult) obj;
        if ((listSecurityProfilesForTargetResult.getSecurityProfileTargetMappings() == null) ^ (getSecurityProfileTargetMappings() == null)) {
            return false;
        }
        if (listSecurityProfilesForTargetResult.getSecurityProfileTargetMappings() != null && !listSecurityProfilesForTargetResult.getSecurityProfileTargetMappings().equals(getSecurityProfileTargetMappings())) {
            return false;
        }
        if ((listSecurityProfilesForTargetResult.getNextToken() == null) ^ (getNextToken() == null)) {
            return false;
        }
        return listSecurityProfilesForTargetResult.getNextToken() == null || listSecurityProfilesForTargetResult.getNextToken().equals(getNextToken());
    }

    public String getNextToken() {
        return this.nextToken;
    }

    public List<SecurityProfileTargetMapping> getSecurityProfileTargetMappings() {
        return this.securityProfileTargetMappings;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((getSecurityProfileTargetMappings() == null ? 0 : getSecurityProfileTargetMappings().hashCode()) + 31) * 31;
        if (getNextToken() != null) {
            i = getNextToken().hashCode();
        }
        return hashCode + i;
    }

    public void setNextToken(String str) {
        this.nextToken = str;
    }

    public void setSecurityProfileTargetMappings(Collection<SecurityProfileTargetMapping> collection) {
        if (collection == null) {
            this.securityProfileTargetMappings = null;
        } else {
            this.securityProfileTargetMappings = new ArrayList(collection);
        }
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getSecurityProfileTargetMappings() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("securityProfileTargetMappings: ");
            outline1072.append(getSecurityProfileTargetMappings());
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

    public ListSecurityProfilesForTargetResult withNextToken(String str) {
        this.nextToken = str;
        return this;
    }

    public ListSecurityProfilesForTargetResult withSecurityProfileTargetMappings(SecurityProfileTargetMapping... securityProfileTargetMappingArr) {
        if (getSecurityProfileTargetMappings() == null) {
            this.securityProfileTargetMappings = new ArrayList(securityProfileTargetMappingArr.length);
        }
        for (SecurityProfileTargetMapping securityProfileTargetMapping : securityProfileTargetMappingArr) {
            this.securityProfileTargetMappings.add(securityProfileTargetMapping);
        }
        return this;
    }

    public ListSecurityProfilesForTargetResult withSecurityProfileTargetMappings(Collection<SecurityProfileTargetMapping> collection) {
        setSecurityProfileTargetMappings(collection);
        return this;
    }
}
