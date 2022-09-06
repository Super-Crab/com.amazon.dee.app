package com.amazon.whisperjoin.devicesetupserviceandroidclient.data;

import com.amazon.devicesetupservice.smarthome.SmartHomeCredential;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import java.util.List;
/* loaded from: classes13.dex */
public class GetCustomerDevicesCredentialsResponse {
    private List<SmartHomeCredential> mAssociatedCredentials;

    public GetCustomerDevicesCredentialsResponse(List<SmartHomeCredential> list) {
        this.mAssociatedCredentials = list;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && GetCustomerDevicesCredentialsResponse.class == obj.getClass()) {
            return Objects.equal(this.mAssociatedCredentials, ((GetCustomerDevicesCredentialsResponse) obj).mAssociatedCredentials);
        }
        return false;
    }

    public List<SmartHomeCredential> getAssociatedCredentials() {
        return this.mAssociatedCredentials;
    }

    public int hashCode() {
        return Objects.hashCode(this.mAssociatedCredentials);
    }

    public void setAssociatedCredentials(List<SmartHomeCredential> list) {
        this.mAssociatedCredentials = list;
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("mAssociatedCredentials", this.mAssociatedCredentials.toString()).toString();
    }
}
