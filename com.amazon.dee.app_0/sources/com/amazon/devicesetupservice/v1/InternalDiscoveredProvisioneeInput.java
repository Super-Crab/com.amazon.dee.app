package com.amazon.devicesetupservice.v1;

import com.amazon.CoralAndroidClient.ClientBase.ClientInput;
import com.amazon.CoralAndroidClient.ClientBase.ClientOutput;
import com.amazon.CoralAndroidClient.ClientBase.Helper;
/* loaded from: classes12.dex */
public class InternalDiscoveredProvisioneeInput implements ClientOutput, ClientInput {
    private static final int classNameHashCode = Helper.hash("com.amazon.devicesetupservice.v1.InternalDiscoveredProvisioneeInput");
    private DiscoveredProvisioneeInput discoveredProvisioneeInputData;
    private ProvisionerIdentity provisionerIdentity;

    public boolean equals(Object obj) {
        if (!(obj instanceof InternalDiscoveredProvisioneeInput)) {
            return false;
        }
        InternalDiscoveredProvisioneeInput internalDiscoveredProvisioneeInput = (InternalDiscoveredProvisioneeInput) obj;
        return Helper.equals(this.discoveredProvisioneeInputData, internalDiscoveredProvisioneeInput.discoveredProvisioneeInputData) && Helper.equals(this.provisionerIdentity, internalDiscoveredProvisioneeInput.provisionerIdentity);
    }

    public DiscoveredProvisioneeInput getDiscoveredProvisioneeInputData() {
        return this.discoveredProvisioneeInputData;
    }

    public ProvisionerIdentity getProvisionerIdentity() {
        return this.provisionerIdentity;
    }

    public int hashCode() {
        return Helper.hash(Integer.valueOf(classNameHashCode), this.discoveredProvisioneeInputData, this.provisionerIdentity);
    }

    public void setDiscoveredProvisioneeInputData(DiscoveredProvisioneeInput discoveredProvisioneeInput) {
        this.discoveredProvisioneeInputData = discoveredProvisioneeInput;
    }

    public void setProvisionerIdentity(ProvisionerIdentity provisionerIdentity) {
        this.provisionerIdentity = provisionerIdentity;
    }
}
