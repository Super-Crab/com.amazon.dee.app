package com.amazon.clouddrive.cdasdk.aps.account;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.annotation.JsonProperty;
/* loaded from: classes11.dex */
public class AccountFeature {
    @JsonProperty("name")
    private String name;
    @JsonProperty("state")
    private String state;

    protected boolean canEqual(Object obj) {
        return obj instanceof AccountFeature;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof AccountFeature)) {
            return false;
        }
        AccountFeature accountFeature = (AccountFeature) obj;
        if (!accountFeature.canEqual(this)) {
            return false;
        }
        String name = getName();
        String name2 = accountFeature.getName();
        if (name != null ? !name.equals(name2) : name2 != null) {
            return false;
        }
        String state = getState();
        String state2 = accountFeature.getState();
        return state != null ? state.equals(state2) : state2 == null;
    }

    public String getName() {
        return this.name;
    }

    public String getState() {
        return this.state;
    }

    public int hashCode() {
        String name = getName();
        int i = 43;
        int hashCode = name == null ? 43 : name.hashCode();
        String state = getState();
        int i2 = (hashCode + 59) * 59;
        if (state != null) {
            i = state.hashCode();
        }
        return i2 + i;
    }

    @JsonProperty("name")
    public void setName(String str) {
        this.name = str;
    }

    @JsonProperty("state")
    public void setState(String str) {
        this.state = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("AccountFeature(name=");
        outline107.append(getName());
        outline107.append(", state=");
        outline107.append(getState());
        outline107.append(")");
        return outline107.toString();
    }
}
