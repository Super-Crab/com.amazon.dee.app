package com.here.sdk.core;

import androidx.annotation.NonNull;
import com.facebook.imageutils.JfifUtil;
import java.util.Objects;
/* loaded from: classes3.dex */
public final class AuthenticationData {
    public final long expiryTimeInSeconds;
    @NonNull
    public final String token;

    public AuthenticationData(@NonNull String str, long j) {
        this.token = str;
        this.expiryTimeInSeconds = j;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof AuthenticationData)) {
            return false;
        }
        AuthenticationData authenticationData = (AuthenticationData) obj;
        return Objects.equals(this.token, authenticationData.token) && this.expiryTimeInSeconds == authenticationData.expiryTimeInSeconds;
    }

    public int hashCode() {
        String str = this.token;
        int hashCode = str != null ? str.hashCode() : 0;
        long j = this.expiryTimeInSeconds;
        return ((hashCode + JfifUtil.MARKER_EOI) * 31) + ((int) (j ^ (j >>> 32)));
    }
}
