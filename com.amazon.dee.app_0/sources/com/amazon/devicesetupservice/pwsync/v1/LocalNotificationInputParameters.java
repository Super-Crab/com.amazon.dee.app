package com.amazon.devicesetupservice.pwsync.v1;

import com.amazon.CoralAndroidClient.ClientBase.ClientInput;
import com.amazon.CoralAndroidClient.ClientBase.ClientOutput;
import com.amazon.CoralAndroidClient.ClientBase.Helper;
import com.amazon.devicesetupservice.DiscoveryInputParameters;
/* loaded from: classes12.dex */
public class LocalNotificationInputParameters extends DiscoveryInputParameters implements ClientOutput, ClientInput {
    private static final int classNameHashCode = Helper.hash("com.amazon.devicesetupservice.pwsync.v1.LocalNotificationInputParameters");

    @Override // com.amazon.devicesetupservice.DiscoveryInputParameters
    public boolean equals(Object obj) {
        if (!(obj instanceof LocalNotificationInputParameters)) {
            return false;
        }
        return super.equals(obj);
    }

    @Override // com.amazon.devicesetupservice.DiscoveryInputParameters
    public int hashCode() {
        return Helper.hash(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode));
    }
}
