package com.amazon.whisperjoin.deviceprovisioningservice.error;
/* loaded from: classes13.dex */
public class NoConfiguredWifiNetworksException extends ProvisioningFailureException {
    public boolean equals(Object obj) {
        return obj != null && (obj instanceof NoConfiguredWifiNetworksException);
    }

    public int hashCode() {
        return super.hashCode();
    }
}
