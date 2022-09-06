package com.amazon.whisperjoin.provisionerSDK.radios.error;

import com.amazon.whisperjoin.common.sharedtypes.exceptions.ConnectionFailureException;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
/* loaded from: classes13.dex */
public class GattConnectionError extends ConnectionFailureException {
    final int mGattStatusCode;

    public GattConnectionError(int i) {
        this.mGattStatusCode = i;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && GattConnectionError.class == obj.getClass() && this.mGattStatusCode == ((GattConnectionError) obj).mGattStatusCode;
    }

    public int getGattStatusCode() {
        return this.mGattStatusCode;
    }

    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(this.mGattStatusCode));
    }

    @Override // java.lang.Throwable
    public String toString() {
        return MoreObjects.toStringHelper(this).add("mGattStatusCode", this.mGattStatusCode).toString();
    }
}
