package com.amazon.devicesetupservice;

import com.amazon.CoralAndroidClient.ClientBase.ClientInput;
import com.amazon.CoralAndroidClient.ClientBase.ClientOutput;
import com.amazon.CoralAndroidClient.ClientBase.Helper;
/* loaded from: classes12.dex */
public abstract class ProvisioneeDetails implements ClientOutput, ClientInput {
    private static final int classNameHashCode = Helper.hash("com.amazon.devicesetupservice.ProvisioneeDetails");

    public boolean equals(Object obj) {
        return obj instanceof ProvisioneeDetails;
    }

    public int hashCode() {
        return Helper.hash(Integer.valueOf(classNameHashCode));
    }
}
