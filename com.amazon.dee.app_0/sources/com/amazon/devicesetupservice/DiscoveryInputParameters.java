package com.amazon.devicesetupservice;

import com.amazon.CoralAndroidClient.ClientBase.ClientInput;
import com.amazon.CoralAndroidClient.ClientBase.ClientOutput;
import com.amazon.CoralAndroidClient.ClientBase.Helper;
/* loaded from: classes12.dex */
public abstract class DiscoveryInputParameters implements ClientOutput, ClientInput {
    private static final int classNameHashCode = Helper.hash("com.amazon.devicesetupservice.DiscoveryInputParameters");

    public boolean equals(Object obj) {
        return obj instanceof DiscoveryInputParameters;
    }

    public int hashCode() {
        return Helper.hash(Integer.valueOf(classNameHashCode));
    }
}
