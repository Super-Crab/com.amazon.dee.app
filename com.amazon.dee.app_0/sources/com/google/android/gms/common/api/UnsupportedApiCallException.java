package com.google.android.gms.common.api;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.annotation.KeepForSdk;
/* loaded from: classes2.dex */
public final class UnsupportedApiCallException extends UnsupportedOperationException {
    private final Feature zzas;

    @KeepForSdk
    public UnsupportedApiCallException(Feature feature) {
        this.zzas = feature;
    }

    @Override // java.lang.Throwable
    public final String getMessage() {
        String valueOf = String.valueOf(this.zzas);
        return GeneratedOutlineSupport1.outline29(valueOf.length() + 8, "Missing ", valueOf);
    }
}
