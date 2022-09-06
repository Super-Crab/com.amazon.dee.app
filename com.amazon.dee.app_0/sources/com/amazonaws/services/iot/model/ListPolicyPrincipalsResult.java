package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
/* loaded from: classes13.dex */
public class ListPolicyPrincipalsResult implements Serializable {
    private String nextMarker;
    private List<String> principals;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ListPolicyPrincipalsResult)) {
            return false;
        }
        ListPolicyPrincipalsResult listPolicyPrincipalsResult = (ListPolicyPrincipalsResult) obj;
        if ((listPolicyPrincipalsResult.getPrincipals() == null) ^ (getPrincipals() == null)) {
            return false;
        }
        if (listPolicyPrincipalsResult.getPrincipals() != null && !listPolicyPrincipalsResult.getPrincipals().equals(getPrincipals())) {
            return false;
        }
        if ((listPolicyPrincipalsResult.getNextMarker() == null) ^ (getNextMarker() == null)) {
            return false;
        }
        return listPolicyPrincipalsResult.getNextMarker() == null || listPolicyPrincipalsResult.getNextMarker().equals(getNextMarker());
    }

    public String getNextMarker() {
        return this.nextMarker;
    }

    public List<String> getPrincipals() {
        return this.principals;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((getPrincipals() == null ? 0 : getPrincipals().hashCode()) + 31) * 31;
        if (getNextMarker() != null) {
            i = getNextMarker().hashCode();
        }
        return hashCode + i;
    }

    public void setNextMarker(String str) {
        this.nextMarker = str;
    }

    public void setPrincipals(Collection<String> collection) {
        if (collection == null) {
            this.principals = null;
        } else {
            this.principals = new ArrayList(collection);
        }
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getPrincipals() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("principals: ");
            outline1072.append(getPrincipals());
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

    public ListPolicyPrincipalsResult withNextMarker(String str) {
        this.nextMarker = str;
        return this;
    }

    public ListPolicyPrincipalsResult withPrincipals(String... strArr) {
        if (getPrincipals() == null) {
            this.principals = new ArrayList(strArr.length);
        }
        for (String str : strArr) {
            this.principals.add(str);
        }
        return this;
    }

    public ListPolicyPrincipalsResult withPrincipals(Collection<String> collection) {
        setPrincipals(collection);
        return this;
    }
}
