package com.amazon.devicesetupservice.v1;

import com.amazon.CoralAndroidClient.ClientBase.ClientInput;
import com.amazon.CoralAndroidClient.ClientBase.ClientOutput;
import com.amazon.CoralAndroidClient.ClientBase.Helper;
/* loaded from: classes12.dex */
public abstract class OptOutPreferenceInput implements ClientOutput, ClientInput {
    private static final int classNameHashCode = Helper.hash("com.amazon.devicesetupservice.v1.OptOutPreferenceInput");
    private CustomerCredentials customerCredentials;
    private String customerId;

    public boolean equals(Object obj) {
        if (!(obj instanceof OptOutPreferenceInput)) {
            return false;
        }
        OptOutPreferenceInput optOutPreferenceInput = (OptOutPreferenceInput) obj;
        return Helper.equals(this.customerId, optOutPreferenceInput.customerId) && Helper.equals(this.customerCredentials, optOutPreferenceInput.customerCredentials);
    }

    public CustomerCredentials getCustomerCredentials() {
        return this.customerCredentials;
    }

    public String getCustomerId() {
        return this.customerId;
    }

    public int hashCode() {
        return Helper.hash(Integer.valueOf(classNameHashCode), this.customerId, this.customerCredentials);
    }

    public void setCustomerCredentials(CustomerCredentials customerCredentials) {
        this.customerCredentials = customerCredentials;
    }

    public void setCustomerId(String str) {
        this.customerId = str;
    }
}
