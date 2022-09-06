package com.amazon.clouddrive.cdasdk.cds.account;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.annotation.JsonProperty;
/* loaded from: classes11.dex */
public class Benefit {
    @JsonProperty("benefit")
    private String benefit;
    @JsonProperty("expiration")
    private String expiration;

    protected boolean canEqual(Object obj) {
        return obj instanceof Benefit;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Benefit)) {
            return false;
        }
        Benefit benefit = (Benefit) obj;
        if (!benefit.canEqual(this)) {
            return false;
        }
        String benefit2 = getBenefit();
        String benefit3 = benefit.getBenefit();
        if (benefit2 != null ? !benefit2.equals(benefit3) : benefit3 != null) {
            return false;
        }
        String expiration = getExpiration();
        String expiration2 = benefit.getExpiration();
        return expiration != null ? expiration.equals(expiration2) : expiration2 == null;
    }

    public String getBenefit() {
        return this.benefit;
    }

    public String getExpiration() {
        return this.expiration;
    }

    public int hashCode() {
        String benefit = getBenefit();
        int i = 43;
        int hashCode = benefit == null ? 43 : benefit.hashCode();
        String expiration = getExpiration();
        int i2 = (hashCode + 59) * 59;
        if (expiration != null) {
            i = expiration.hashCode();
        }
        return i2 + i;
    }

    @JsonProperty("benefit")
    public void setBenefit(String str) {
        this.benefit = str;
    }

    @JsonProperty("expiration")
    public void setExpiration(String str) {
        this.expiration = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Benefit(benefit=");
        outline107.append(getBenefit());
        outline107.append(", expiration=");
        outline107.append(getExpiration());
        outline107.append(")");
        return outline107.toString();
    }
}
