package com.amazon.devicesetupservice.v1;

import com.amazon.CoralAndroidClient.ClientBase.ClientInput;
import com.amazon.CoralAndroidClient.ClientBase.ClientOutput;
import com.amazon.CoralAndroidClient.ClientBase.Helper;
/* loaded from: classes12.dex */
public class GetOptOutPreferenceInput extends OptOutPreferenceInput implements ClientOutput, ClientInput {
    private static final int classNameHashCode = Helper.hash("com.amazon.devicesetupservice.v1.GetOptOutPreferenceInput");

    @Override // com.amazon.devicesetupservice.v1.OptOutPreferenceInput
    public boolean equals(Object obj) {
        if (!(obj instanceof GetOptOutPreferenceInput)) {
            return false;
        }
        return super.equals(obj);
    }

    @Override // com.amazon.devicesetupservice.v1.OptOutPreferenceInput
    public int hashCode() {
        return Helper.hash(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode));
    }
}
