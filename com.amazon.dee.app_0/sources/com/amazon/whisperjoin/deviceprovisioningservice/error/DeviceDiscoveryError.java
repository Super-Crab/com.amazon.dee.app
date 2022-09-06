package com.amazon.whisperjoin.deviceprovisioningservice.error;
/* loaded from: classes13.dex */
public class DeviceDiscoveryError extends Exception {
    public DeviceDiscoveryError() {
    }

    public boolean equals(Object obj) {
        return obj != null && DeviceDiscoveryError.class == obj.getClass();
    }

    public int hashCode() {
        return super.hashCode();
    }

    public DeviceDiscoveryError(Throwable th) {
        super(th);
    }
}
