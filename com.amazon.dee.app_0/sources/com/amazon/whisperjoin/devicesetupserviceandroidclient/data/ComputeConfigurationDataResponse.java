package com.amazon.whisperjoin.devicesetupserviceandroidclient.data;

import com.amazon.devicesetupservice.v1.RegistrationDetails;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import java.util.Map;
/* loaded from: classes13.dex */
public class ComputeConfigurationDataResponse {
    private final Map<String, String> mConfiguration;
    private final String mNonce;
    private final RegistrationDetails mRegistrationDetails;
    private final String mSignature;

    public ComputeConfigurationDataResponse(String str, Map<String, String> map, String str2, RegistrationDetails registrationDetails) {
        this.mNonce = str;
        this.mConfiguration = map;
        this.mSignature = str2;
        this.mRegistrationDetails = registrationDetails;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || ComputeConfigurationDataResponse.class != obj.getClass()) {
            return false;
        }
        ComputeConfigurationDataResponse computeConfigurationDataResponse = (ComputeConfigurationDataResponse) obj;
        return Objects.equal(this.mNonce, computeConfigurationDataResponse.mNonce) && Objects.equal(this.mConfiguration, computeConfigurationDataResponse.mConfiguration) && Objects.equal(this.mSignature, computeConfigurationDataResponse.mSignature) && Objects.equal(this.mRegistrationDetails, computeConfigurationDataResponse.mRegistrationDetails);
    }

    public Map<String, String> getConfiguration() {
        return this.mConfiguration;
    }

    public String getNonce() {
        return this.mNonce;
    }

    public RegistrationDetails getRegistrationDetails() {
        return this.mRegistrationDetails;
    }

    public String getSignature() {
        return this.mSignature;
    }

    public int hashCode() {
        return Objects.hashCode(this.mNonce, this.mConfiguration, this.mSignature, this.mRegistrationDetails);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("mNonce", this.mNonce).add("mConfiguration", this.mConfiguration).add("mSignature", this.mSignature).add("mRegistrationDetails", this.mRegistrationDetails).toString();
    }
}
