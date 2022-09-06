package com.amazonaws.services.s3.model;

import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes13.dex */
public class Grant {
    private Grantee grantee;
    private Permission permission;

    public Grant(Grantee grantee, Permission permission) {
        this.grantee = null;
        this.permission = null;
        this.grantee = grantee;
        this.permission = permission;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || Grant.class != obj.getClass()) {
            return false;
        }
        Grant grant = (Grant) obj;
        Grantee grantee = this.grantee;
        if (grantee == null) {
            if (grant.grantee != null) {
                return false;
            }
        } else if (!grantee.equals(grant.grantee)) {
            return false;
        }
        return this.permission == grant.permission;
    }

    public Grantee getGrantee() {
        return this.grantee;
    }

    public Permission getPermission() {
        return this.permission;
    }

    public int hashCode() {
        Grantee grantee = this.grantee;
        int i = 0;
        int hashCode = ((grantee == null ? 0 : grantee.hashCode()) + 31) * 31;
        Permission permission = this.permission;
        if (permission != null) {
            i = permission.hashCode();
        }
        return hashCode + i;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Grant [grantee=");
        outline107.append(this.grantee);
        outline107.append(", permission=");
        outline107.append(this.permission);
        outline107.append("]");
        return outline107.toString();
    }
}
