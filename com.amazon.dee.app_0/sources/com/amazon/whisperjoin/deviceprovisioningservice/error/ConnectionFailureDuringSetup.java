package com.amazon.whisperjoin.deviceprovisioningservice.error;

import com.amazon.whisperjoin.deviceprovisioningservice.workflow.state.DeviceSession;
import com.google.common.base.Objects;
/* loaded from: classes13.dex */
public class ConnectionFailureDuringSetup extends Exception {
    private final DeviceSession mDeviceSession;

    public ConnectionFailureDuringSetup(Throwable th, DeviceSession deviceSession) {
        super(th);
        this.mDeviceSession = deviceSession;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && ConnectionFailureDuringSetup.class == obj.getClass()) {
            return Objects.equal(this.mDeviceSession, ((ConnectionFailureDuringSetup) obj).mDeviceSession);
        }
        return false;
    }

    public DeviceSession getDeviceSession() {
        return this.mDeviceSession;
    }

    public int hashCode() {
        return Objects.hashCode(this.mDeviceSession);
    }
}
