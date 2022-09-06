package com.amazon.clouddrive.cdasdk.cds.account;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.annotation.JsonProperty;
/* loaded from: classes11.dex */
public class AvailableBenefit {
    @JsonProperty("benefit")
    private String benefit;

    protected boolean canEqual(Object obj) {
        return obj instanceof AvailableBenefit;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof AvailableBenefit)) {
            return false;
        }
        AvailableBenefit availableBenefit = (AvailableBenefit) obj;
        if (!availableBenefit.canEqual(this)) {
            return false;
        }
        String benefit = getBenefit();
        String benefit2 = availableBenefit.getBenefit();
        return benefit != null ? benefit.equals(benefit2) : benefit2 == null;
    }

    public String getBenefit() {
        return this.benefit;
    }

    public int hashCode() {
        String benefit = getBenefit();
        return 59 + (benefit == null ? 43 : benefit.hashCode());
    }

    @JsonProperty("benefit")
    public void setBenefit(String str) {
        this.benefit = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("AvailableBenefit(benefit=");
        outline107.append(getBenefit());
        outline107.append(")");
        return outline107.toString();
    }
}
