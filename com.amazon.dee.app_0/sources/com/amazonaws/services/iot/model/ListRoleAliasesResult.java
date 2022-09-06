package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
/* loaded from: classes13.dex */
public class ListRoleAliasesResult implements Serializable {
    private String nextMarker;
    private List<String> roleAliases;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ListRoleAliasesResult)) {
            return false;
        }
        ListRoleAliasesResult listRoleAliasesResult = (ListRoleAliasesResult) obj;
        if ((listRoleAliasesResult.getRoleAliases() == null) ^ (getRoleAliases() == null)) {
            return false;
        }
        if (listRoleAliasesResult.getRoleAliases() != null && !listRoleAliasesResult.getRoleAliases().equals(getRoleAliases())) {
            return false;
        }
        if ((listRoleAliasesResult.getNextMarker() == null) ^ (getNextMarker() == null)) {
            return false;
        }
        return listRoleAliasesResult.getNextMarker() == null || listRoleAliasesResult.getNextMarker().equals(getNextMarker());
    }

    public String getNextMarker() {
        return this.nextMarker;
    }

    public List<String> getRoleAliases() {
        return this.roleAliases;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((getRoleAliases() == null ? 0 : getRoleAliases().hashCode()) + 31) * 31;
        if (getNextMarker() != null) {
            i = getNextMarker().hashCode();
        }
        return hashCode + i;
    }

    public void setNextMarker(String str) {
        this.nextMarker = str;
    }

    public void setRoleAliases(Collection<String> collection) {
        if (collection == null) {
            this.roleAliases = null;
        } else {
            this.roleAliases = new ArrayList(collection);
        }
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getRoleAliases() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("roleAliases: ");
            outline1072.append(getRoleAliases());
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

    public ListRoleAliasesResult withNextMarker(String str) {
        this.nextMarker = str;
        return this;
    }

    public ListRoleAliasesResult withRoleAliases(String... strArr) {
        if (getRoleAliases() == null) {
            this.roleAliases = new ArrayList(strArr.length);
        }
        for (String str : strArr) {
            this.roleAliases.add(str);
        }
        return this;
    }

    public ListRoleAliasesResult withRoleAliases(Collection<String> collection) {
        setRoleAliases(collection);
        return this;
    }
}
