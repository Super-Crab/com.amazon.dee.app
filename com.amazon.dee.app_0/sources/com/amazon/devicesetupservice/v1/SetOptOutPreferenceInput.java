package com.amazon.devicesetupservice.v1;

import com.amazon.CoralAndroidClient.ClientBase.ClientInput;
import com.amazon.CoralAndroidClient.ClientBase.ClientOutput;
import com.amazon.CoralAndroidClient.ClientBase.Helper;
/* loaded from: classes12.dex */
public class SetOptOutPreferenceInput extends OptOutPreferenceInput implements ClientOutput, ClientInput {
    private static final int classNameHashCode = Helper.hash("com.amazon.devicesetupservice.v1.SetOptOutPreferenceInput");
    private Boolean hasOptedOut;

    @Override // com.amazon.devicesetupservice.v1.OptOutPreferenceInput
    public boolean equals(Object obj) {
        if (!(obj instanceof SetOptOutPreferenceInput)) {
            return false;
        }
        return super.equals(obj) && Helper.equals(this.hasOptedOut, ((SetOptOutPreferenceInput) obj).hasOptedOut);
    }

    @Override // com.amazon.devicesetupservice.v1.OptOutPreferenceInput
    public int hashCode() {
        return Helper.hash(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), this.hasOptedOut);
    }

    public Boolean isHasOptedOut() {
        return this.hasOptedOut;
    }

    public void setHasOptedOut(Boolean bool) {
        this.hasOptedOut = bool;
    }
}
