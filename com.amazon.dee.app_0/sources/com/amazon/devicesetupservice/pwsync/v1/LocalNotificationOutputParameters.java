package com.amazon.devicesetupservice.pwsync.v1;

import com.amazon.CoralAndroidClient.ClientBase.ClientInput;
import com.amazon.CoralAndroidClient.ClientBase.ClientOutput;
import com.amazon.CoralAndroidClient.ClientBase.Helper;
import com.amazon.devicesetupservice.DiscoveryOutputParameters;
import com.amazon.devicesetupservice.v1.LegacyIdentifier;
/* loaded from: classes12.dex */
public class LocalNotificationOutputParameters extends DiscoveryOutputParameters implements ClientOutput, ClientInput {
    private static final int classNameHashCode = Helper.hash("com.amazon.devicesetupservice.pwsync.v1.LocalNotificationOutputParameters");
    private LegacyIdentifier provisioneeInformation;

    @Override // com.amazon.devicesetupservice.DiscoveryOutputParameters
    public boolean equals(Object obj) {
        if (!(obj instanceof LocalNotificationOutputParameters)) {
            return false;
        }
        return super.equals(obj) && Helper.equals(this.provisioneeInformation, ((LocalNotificationOutputParameters) obj).provisioneeInformation);
    }

    public LegacyIdentifier getProvisioneeInformation() {
        return this.provisioneeInformation;
    }

    @Override // com.amazon.devicesetupservice.DiscoveryOutputParameters
    public int hashCode() {
        return Helper.hash(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), this.provisioneeInformation);
    }

    public void setProvisioneeInformation(LegacyIdentifier legacyIdentifier) {
        this.provisioneeInformation = legacyIdentifier;
    }
}
