package com.amazonaws.services.cognitoidentity.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
/* loaded from: classes13.dex */
public class ListIdentityPoolsResult implements Serializable {
    private List<IdentityPoolShortDescription> identityPools;
    private String nextToken;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ListIdentityPoolsResult)) {
            return false;
        }
        ListIdentityPoolsResult listIdentityPoolsResult = (ListIdentityPoolsResult) obj;
        if ((listIdentityPoolsResult.getIdentityPools() == null) ^ (getIdentityPools() == null)) {
            return false;
        }
        if (listIdentityPoolsResult.getIdentityPools() != null && !listIdentityPoolsResult.getIdentityPools().equals(getIdentityPools())) {
            return false;
        }
        if ((listIdentityPoolsResult.getNextToken() == null) ^ (getNextToken() == null)) {
            return false;
        }
        return listIdentityPoolsResult.getNextToken() == null || listIdentityPoolsResult.getNextToken().equals(getNextToken());
    }

    public List<IdentityPoolShortDescription> getIdentityPools() {
        return this.identityPools;
    }

    public String getNextToken() {
        return this.nextToken;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((getIdentityPools() == null ? 0 : getIdentityPools().hashCode()) + 31) * 31;
        if (getNextToken() != null) {
            i = getNextToken().hashCode();
        }
        return hashCode + i;
    }

    public void setIdentityPools(Collection<IdentityPoolShortDescription> collection) {
        if (collection == null) {
            this.identityPools = null;
        } else {
            this.identityPools = new ArrayList(collection);
        }
    }

    public void setNextToken(String str) {
        this.nextToken = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getIdentityPools() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("IdentityPools: ");
            outline1072.append(getIdentityPools());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getNextToken() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("NextToken: ");
            outline1073.append(getNextToken());
            outline107.append(outline1073.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public ListIdentityPoolsResult withIdentityPools(IdentityPoolShortDescription... identityPoolShortDescriptionArr) {
        if (getIdentityPools() == null) {
            this.identityPools = new ArrayList(identityPoolShortDescriptionArr.length);
        }
        for (IdentityPoolShortDescription identityPoolShortDescription : identityPoolShortDescriptionArr) {
            this.identityPools.add(identityPoolShortDescription);
        }
        return this;
    }

    public ListIdentityPoolsResult withNextToken(String str) {
        this.nextToken = str;
        return this;
    }

    public ListIdentityPoolsResult withIdentityPools(Collection<IdentityPoolShortDescription> collection) {
        setIdentityPools(collection);
        return this;
    }
}
