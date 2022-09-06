package com.amazon.devicesetupservice.v1;

import com.amazon.CoralAndroidClient.ClientBase.ClientInput;
import com.amazon.CoralAndroidClient.ClientBase.ClientOutput;
import com.amazon.CoralAndroidClient.ClientBase.Helper;
/* loaded from: classes12.dex */
public class GetOptOutPreferenceOutput implements ClientOutput, ClientInput {
    private static final int classNameHashCode = Helper.hash("com.amazon.devicesetupservice.v1.GetOptOutPreferenceOutput");
    private Boolean hasOptedOut;

    public boolean equals(Object obj) {
        if (!(obj instanceof GetOptOutPreferenceOutput)) {
            return false;
        }
        return Helper.equals(this.hasOptedOut, ((GetOptOutPreferenceOutput) obj).hasOptedOut);
    }

    public int hashCode() {
        return Helper.hash(Integer.valueOf(classNameHashCode), this.hasOptedOut);
    }

    public Boolean isHasOptedOut() {
        return this.hasOptedOut;
    }

    public void setHasOptedOut(Boolean bool) {
        this.hasOptedOut = bool;
    }
}
