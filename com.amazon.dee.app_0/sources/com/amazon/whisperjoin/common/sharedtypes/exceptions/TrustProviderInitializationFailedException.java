package com.amazon.whisperjoin.common.sharedtypes.exceptions;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
/* loaded from: classes13.dex */
public class TrustProviderInitializationFailedException extends Exception {
    private final Throwable mFailureCause;

    public TrustProviderInitializationFailedException(String str, Throwable th) {
        super(str);
        this.mFailureCause = th;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && TrustProviderInitializationFailedException.class == obj.getClass()) {
            return Objects.equal(this.mFailureCause, ((TrustProviderInitializationFailedException) obj).mFailureCause);
        }
        return false;
    }

    public Throwable getFailureCause() {
        return this.mFailureCause;
    }

    public int hashCode() {
        return Objects.hashCode(this.mFailureCause);
    }

    @Override // java.lang.Throwable
    public String toString() {
        return MoreObjects.toStringHelper(this).add("mFailureCause", this.mFailureCause).toString();
    }

    public TrustProviderInitializationFailedException(Throwable th) {
        this("TrustProvider initialization failed", th);
    }
}
