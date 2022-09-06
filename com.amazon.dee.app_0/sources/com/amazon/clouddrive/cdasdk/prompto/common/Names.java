package com.amazon.clouddrive.cdasdk.prompto.common;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.annotation.JsonProperty;
/* loaded from: classes11.dex */
public class Names {
    @JsonProperty("firstName")
    private String firstName;
    @JsonProperty("fullName")
    private String fullName;

    protected boolean canEqual(Object obj) {
        return obj instanceof Names;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Names)) {
            return false;
        }
        Names names = (Names) obj;
        if (!names.canEqual(this)) {
            return false;
        }
        String firstName = getFirstName();
        String firstName2 = names.getFirstName();
        if (firstName != null ? !firstName.equals(firstName2) : firstName2 != null) {
            return false;
        }
        String fullName = getFullName();
        String fullName2 = names.getFullName();
        return fullName != null ? fullName.equals(fullName2) : fullName2 == null;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getFullName() {
        return this.fullName;
    }

    public int hashCode() {
        String firstName = getFirstName();
        int i = 43;
        int hashCode = firstName == null ? 43 : firstName.hashCode();
        String fullName = getFullName();
        int i2 = (hashCode + 59) * 59;
        if (fullName != null) {
            i = fullName.hashCode();
        }
        return i2 + i;
    }

    @JsonProperty("firstName")
    public void setFirstName(String str) {
        this.firstName = str;
    }

    @JsonProperty("fullName")
    public void setFullName(String str) {
        this.fullName = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Names(firstName=");
        outline107.append(getFirstName());
        outline107.append(", fullName=");
        outline107.append(getFullName());
        outline107.append(")");
        return outline107.toString();
    }
}
