package com.amazon.whisperjoin.deviceprovisioningservice.error;

import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlinx.serialization.json.internal.JsonReaderKt;
/* loaded from: classes13.dex */
public class DeviceProvisioningDeferredException extends Exception {
    public final long mWaitTime;

    public DeviceProvisioningDeferredException(long j) {
        this.mWaitTime = j;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && DeviceProvisioningDeferredException.class == obj.getClass() && this.mWaitTime == ((DeviceProvisioningDeferredException) obj).mWaitTime;
    }

    public int hashCode() {
        long j = this.mWaitTime;
        return (int) (j ^ (j >>> 32));
    }

    @Override // java.lang.Throwable
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("DeviceProvisioningDeferredException{mWaitTime=");
        outline107.append(this.mWaitTime);
        outline107.append(JsonReaderKt.END_OBJ);
        return outline107.toString();
    }
}
