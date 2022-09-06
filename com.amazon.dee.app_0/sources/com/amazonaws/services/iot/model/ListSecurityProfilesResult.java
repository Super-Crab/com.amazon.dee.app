package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
/* loaded from: classes13.dex */
public class ListSecurityProfilesResult implements Serializable {
    private String nextToken;
    private List<SecurityProfileIdentifier> securityProfileIdentifiers;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ListSecurityProfilesResult)) {
            return false;
        }
        ListSecurityProfilesResult listSecurityProfilesResult = (ListSecurityProfilesResult) obj;
        if ((listSecurityProfilesResult.getSecurityProfileIdentifiers() == null) ^ (getSecurityProfileIdentifiers() == null)) {
            return false;
        }
        if (listSecurityProfilesResult.getSecurityProfileIdentifiers() != null && !listSecurityProfilesResult.getSecurityProfileIdentifiers().equals(getSecurityProfileIdentifiers())) {
            return false;
        }
        if ((listSecurityProfilesResult.getNextToken() == null) ^ (getNextToken() == null)) {
            return false;
        }
        return listSecurityProfilesResult.getNextToken() == null || listSecurityProfilesResult.getNextToken().equals(getNextToken());
    }

    public String getNextToken() {
        return this.nextToken;
    }

    public List<SecurityProfileIdentifier> getSecurityProfileIdentifiers() {
        return this.securityProfileIdentifiers;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((getSecurityProfileIdentifiers() == null ? 0 : getSecurityProfileIdentifiers().hashCode()) + 31) * 31;
        if (getNextToken() != null) {
            i = getNextToken().hashCode();
        }
        return hashCode + i;
    }

    public void setNextToken(String str) {
        this.nextToken = str;
    }

    public void setSecurityProfileIdentifiers(Collection<SecurityProfileIdentifier> collection) {
        if (collection == null) {
            this.securityProfileIdentifiers = null;
        } else {
            this.securityProfileIdentifiers = new ArrayList(collection);
        }
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getSecurityProfileIdentifiers() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("securityProfileIdentifiers: ");
            outline1072.append(getSecurityProfileIdentifiers());
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

    public ListSecurityProfilesResult withNextToken(String str) {
        this.nextToken = str;
        return this;
    }

    public ListSecurityProfilesResult withSecurityProfileIdentifiers(SecurityProfileIdentifier... securityProfileIdentifierArr) {
        if (getSecurityProfileIdentifiers() == null) {
            this.securityProfileIdentifiers = new ArrayList(securityProfileIdentifierArr.length);
        }
        for (SecurityProfileIdentifier securityProfileIdentifier : securityProfileIdentifierArr) {
            this.securityProfileIdentifiers.add(securityProfileIdentifier);
        }
        return this;
    }

    public ListSecurityProfilesResult withSecurityProfileIdentifiers(Collection<SecurityProfileIdentifier> collection) {
        setSecurityProfileIdentifiers(collection);
        return this;
    }
}
