package com.amazonaws.services.cognitoidentity.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
/* loaded from: classes13.dex */
public class LookupDeveloperIdentityResult implements Serializable {
    private List<String> developerUserIdentifierList;
    private String identityId;
    private String nextToken;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof LookupDeveloperIdentityResult)) {
            return false;
        }
        LookupDeveloperIdentityResult lookupDeveloperIdentityResult = (LookupDeveloperIdentityResult) obj;
        if ((lookupDeveloperIdentityResult.getIdentityId() == null) ^ (getIdentityId() == null)) {
            return false;
        }
        if (lookupDeveloperIdentityResult.getIdentityId() != null && !lookupDeveloperIdentityResult.getIdentityId().equals(getIdentityId())) {
            return false;
        }
        if ((lookupDeveloperIdentityResult.getDeveloperUserIdentifierList() == null) ^ (getDeveloperUserIdentifierList() == null)) {
            return false;
        }
        if (lookupDeveloperIdentityResult.getDeveloperUserIdentifierList() != null && !lookupDeveloperIdentityResult.getDeveloperUserIdentifierList().equals(getDeveloperUserIdentifierList())) {
            return false;
        }
        if ((lookupDeveloperIdentityResult.getNextToken() == null) ^ (getNextToken() == null)) {
            return false;
        }
        return lookupDeveloperIdentityResult.getNextToken() == null || lookupDeveloperIdentityResult.getNextToken().equals(getNextToken());
    }

    public List<String> getDeveloperUserIdentifierList() {
        return this.developerUserIdentifierList;
    }

    public String getIdentityId() {
        return this.identityId;
    }

    public String getNextToken() {
        return this.nextToken;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((getIdentityId() == null ? 0 : getIdentityId().hashCode()) + 31) * 31) + (getDeveloperUserIdentifierList() == null ? 0 : getDeveloperUserIdentifierList().hashCode())) * 31;
        if (getNextToken() != null) {
            i = getNextToken().hashCode();
        }
        return hashCode + i;
    }

    public void setDeveloperUserIdentifierList(Collection<String> collection) {
        if (collection == null) {
            this.developerUserIdentifierList = null;
        } else {
            this.developerUserIdentifierList = new ArrayList(collection);
        }
    }

    public void setIdentityId(String str) {
        this.identityId = str;
    }

    public void setNextToken(String str) {
        this.nextToken = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getIdentityId() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("IdentityId: ");
            outline1072.append(getIdentityId());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getDeveloperUserIdentifierList() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("DeveloperUserIdentifierList: ");
            outline1073.append(getDeveloperUserIdentifierList());
            outline1073.append(",");
            outline107.append(outline1073.toString());
        }
        if (getNextToken() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("NextToken: ");
            outline1074.append(getNextToken());
            outline107.append(outline1074.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public LookupDeveloperIdentityResult withDeveloperUserIdentifierList(String... strArr) {
        if (getDeveloperUserIdentifierList() == null) {
            this.developerUserIdentifierList = new ArrayList(strArr.length);
        }
        for (String str : strArr) {
            this.developerUserIdentifierList.add(str);
        }
        return this;
    }

    public LookupDeveloperIdentityResult withIdentityId(String str) {
        this.identityId = str;
        return this;
    }

    public LookupDeveloperIdentityResult withNextToken(String str) {
        this.nextToken = str;
        return this;
    }

    public LookupDeveloperIdentityResult withDeveloperUserIdentifierList(Collection<String> collection) {
        setDeveloperUserIdentifierList(collection);
        return this;
    }
}
