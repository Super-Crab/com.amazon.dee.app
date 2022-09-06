package com.amazonaws.services.s3.model;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class Owner implements Serializable {
    private static final long serialVersionUID = -8916731456944569115L;
    private String displayName;
    private String id;

    public Owner() {
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Owner)) {
            return false;
        }
        Owner owner = (Owner) obj;
        String id = owner.getId();
        String displayName = owner.getDisplayName();
        String id2 = getId();
        String displayName2 = getDisplayName();
        if (id == null) {
            id = "";
        }
        if (displayName == null) {
            displayName = "";
        }
        if (id2 == null) {
            id2 = "";
        }
        if (displayName2 == null) {
            displayName2 = "";
        }
        return id.equals(id2) && displayName.equals(displayName2);
    }

    public String getDisplayName() {
        return this.displayName;
    }

    public String getId() {
        return this.id;
    }

    public int hashCode() {
        String str = this.id;
        if (str != null) {
            return str.hashCode();
        }
        return 0;
    }

    public void setDisplayName(String str) {
        this.displayName = str;
    }

    public void setId(String str) {
        this.id = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("S3Owner [name=");
        outline107.append(getDisplayName());
        outline107.append(",id=");
        outline107.append(getId());
        outline107.append("]");
        return outline107.toString();
    }

    public Owner(String str, String str2) {
        this.id = str;
        this.displayName = str2;
    }
}
