package com.here.sdk.core;

import androidx.annotation.Nullable;
@Deprecated
/* loaded from: classes3.dex */
public interface LocationProvider {
    @Nullable
    LocationListener getListener();

    void setListener(@Nullable LocationListener locationListener);

    void start();

    void stop();
}
