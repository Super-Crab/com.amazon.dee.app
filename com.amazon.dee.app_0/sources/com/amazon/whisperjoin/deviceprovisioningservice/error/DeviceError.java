package com.amazon.whisperjoin.deviceprovisioningservice.error;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.base.Objects;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class DeviceError extends Exception implements Serializable {
    private final String mOperation;

    public DeviceError(Throwable th, String str) {
        super(GeneratedOutlineSupport1.outline72("ProvisionableDevice Error: ", str), th);
        this.mOperation = str;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && DeviceError.class == obj.getClass()) {
            return Objects.equal(this.mOperation, ((DeviceError) obj).mOperation);
        }
        return false;
    }

    public String getOperation() {
        return this.mOperation;
    }

    public int hashCode() {
        return Objects.hashCode(this.mOperation);
    }

    public DeviceError(String str) {
        super(GeneratedOutlineSupport1.outline72("ProvisionableDevice Error: ", str));
        this.mOperation = str;
    }
}
