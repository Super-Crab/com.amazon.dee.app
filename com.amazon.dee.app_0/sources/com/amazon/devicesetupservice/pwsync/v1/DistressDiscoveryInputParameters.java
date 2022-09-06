package com.amazon.devicesetupservice.pwsync.v1;

import com.amazon.CoralAndroidClient.ClientBase.ClientInput;
import com.amazon.CoralAndroidClient.ClientBase.ClientOutput;
import com.amazon.CoralAndroidClient.ClientBase.Helper;
import com.amazon.devicesetupservice.DiscoveryInputParameters;
/* loaded from: classes12.dex */
public class DistressDiscoveryInputParameters extends DiscoveryInputParameters implements ClientOutput, ClientInput {
    private static final int classNameHashCode = Helper.hash("com.amazon.devicesetupservice.pwsync.v1.DistressDiscoveryInputParameters");
    private String nonce;
    private String provisioningMethod;
    private String trustMethod;

    @Override // com.amazon.devicesetupservice.DiscoveryInputParameters
    public boolean equals(Object obj) {
        if (!(obj instanceof DistressDiscoveryInputParameters)) {
            return false;
        }
        DistressDiscoveryInputParameters distressDiscoveryInputParameters = (DistressDiscoveryInputParameters) obj;
        return super.equals(obj) && Helper.equals(this.trustMethod, distressDiscoveryInputParameters.trustMethod) && Helper.equals(this.provisioningMethod, distressDiscoveryInputParameters.provisioningMethod) && Helper.equals(this.nonce, distressDiscoveryInputParameters.nonce);
    }

    public String getNonce() {
        return this.nonce;
    }

    public String getProvisioningMethod() {
        return this.provisioningMethod;
    }

    public String getTrustMethod() {
        return this.trustMethod;
    }

    @Override // com.amazon.devicesetupservice.DiscoveryInputParameters
    public int hashCode() {
        return Helper.hash(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), this.trustMethod, this.provisioningMethod, this.nonce);
    }

    public void setNonce(String str) {
        this.nonce = str;
    }

    public void setProvisioningMethod(String str) {
        this.provisioningMethod = str;
    }

    public void setTrustMethod(String str) {
        this.trustMethod = str;
    }
}
