package com.amazon.devicesetupservice.pwsync.v1;

import com.amazon.CoralAndroidClient.ClientBase.ClientInput;
import com.amazon.CoralAndroidClient.ClientBase.ClientOutput;
import com.amazon.CoralAndroidClient.ClientBase.Helper;
import com.amazon.devicesetupservice.DiscoveryOutputParameters;
/* loaded from: classes12.dex */
public class DistressDiscoveryOutputParameters extends DiscoveryOutputParameters implements ClientOutput, ClientInput {
    private static final int classNameHashCode = Helper.hash("com.amazon.devicesetupservice.pwsync.v1.DistressDiscoveryOutputParameters");
    private String sessionToken;

    @Override // com.amazon.devicesetupservice.DiscoveryOutputParameters
    public boolean equals(Object obj) {
        if (!(obj instanceof DistressDiscoveryOutputParameters)) {
            return false;
        }
        return super.equals(obj) && Helper.equals(this.sessionToken, ((DistressDiscoveryOutputParameters) obj).sessionToken);
    }

    public String getSessionToken() {
        return this.sessionToken;
    }

    @Override // com.amazon.devicesetupservice.DiscoveryOutputParameters
    public int hashCode() {
        return Helper.hash(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), this.sessionToken);
    }

    public void setSessionToken(String str) {
        this.sessionToken = str;
    }
}
