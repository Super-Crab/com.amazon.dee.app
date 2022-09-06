package com.amazon.clouddrive.cdasdk.aps.account;

import com.amazon.clouddrive.cdasdk.aps.common.APSInput;
/* loaded from: classes11.dex */
public class AccountFeaturesInput extends APSInput {
    @Override // com.amazon.clouddrive.cdasdk.aps.common.APSInput
    protected boolean canEqual(Object obj) {
        return obj instanceof AccountFeaturesInput;
    }

    @Override // com.amazon.clouddrive.cdasdk.aps.common.APSInput
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof AccountFeaturesInput) && ((AccountFeaturesInput) obj).canEqual(this) && super.equals(obj);
    }

    @Override // com.amazon.clouddrive.cdasdk.aps.common.APSInput
    public int hashCode() {
        return super.hashCode();
    }

    @Override // com.amazon.clouddrive.cdasdk.aps.common.APSInput
    public String toString() {
        return "AccountFeaturesInput()";
    }
}
