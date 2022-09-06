package com.here.sdk.core;

import androidx.annotation.NonNull;
/* loaded from: classes3.dex */
public interface LocationListener {
    @Deprecated
    void onLocationTimeout();

    void onLocationUpdated(@NonNull Location location);
}
