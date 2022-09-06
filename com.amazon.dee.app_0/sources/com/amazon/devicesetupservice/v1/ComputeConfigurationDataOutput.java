package com.amazon.devicesetupservice.v1;

import com.amazon.CoralAndroidClient.ClientBase.ClientInput;
import com.amazon.CoralAndroidClient.ClientBase.ClientOutput;
import com.amazon.CoralAndroidClient.ClientBase.Helper;
import java.util.Map;
/* loaded from: classes12.dex */
public class ComputeConfigurationDataOutput implements ClientOutput, ClientInput {
    private static final int classNameHashCode = Helper.hash("com.amazon.devicesetupservice.v1.ComputeConfigurationDataOutput");
    private Map<String, String> configuration;
    private String endpointToUse;
    private String nonce;
    private RegistrationDetails registrationDetails;
    private String signature;

    public boolean equals(Object obj) {
        if (!(obj instanceof ComputeConfigurationDataOutput)) {
            return false;
        }
        ComputeConfigurationDataOutput computeConfigurationDataOutput = (ComputeConfigurationDataOutput) obj;
        return Helper.equals(this.endpointToUse, computeConfigurationDataOutput.endpointToUse) && Helper.equals(this.nonce, computeConfigurationDataOutput.nonce) && Helper.equals(this.registrationDetails, computeConfigurationDataOutput.registrationDetails) && Helper.equals(this.configuration, computeConfigurationDataOutput.configuration) && Helper.equals(this.signature, computeConfigurationDataOutput.signature);
    }

    public Map<String, String> getConfiguration() {
        return this.configuration;
    }

    public String getEndpointToUse() {
        return this.endpointToUse;
    }

    public String getNonce() {
        return this.nonce;
    }

    public RegistrationDetails getRegistrationDetails() {
        return this.registrationDetails;
    }

    public String getSignature() {
        return this.signature;
    }

    public int hashCode() {
        return Helper.hash(Integer.valueOf(classNameHashCode), this.endpointToUse, this.nonce, this.registrationDetails, this.configuration, this.signature);
    }

    public void setConfiguration(Map<String, String> map) {
        this.configuration = map;
    }

    public void setEndpointToUse(String str) {
        this.endpointToUse = str;
    }

    public void setNonce(String str) {
        this.nonce = str;
    }

    public void setRegistrationDetails(RegistrationDetails registrationDetails) {
        this.registrationDetails = registrationDetails;
    }

    public void setSignature(String str) {
        this.signature = str;
    }
}
