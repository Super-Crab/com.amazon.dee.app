package com.amazon.whisperjoin.deviceprovisioningservice.arcus;

import com.google.common.base.Objects;
/* loaded from: classes13.dex */
public class ArcusSyncThrottled extends Exception {
    private final long mWaitTime;

    public ArcusSyncThrottled(long j) {
        this.mWaitTime = j;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ArcusSyncThrottled) && this.mWaitTime == ((ArcusSyncThrottled) obj).mWaitTime;
    }

    public long getWaitTime() {
        return this.mWaitTime;
    }

    public int hashCode() {
        return Objects.hashCode(Long.valueOf(this.mWaitTime));
    }
}
