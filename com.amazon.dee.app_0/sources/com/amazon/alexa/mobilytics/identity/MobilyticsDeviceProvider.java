package com.amazon.alexa.mobilytics.identity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
/* loaded from: classes9.dex */
public interface MobilyticsDeviceProvider {

    @FunctionalInterface
    /* loaded from: classes9.dex */
    public interface Listener {
        void onDeviceDetailChange(@Nullable MobilyticsDevice mobilyticsDevice);
    }

    void addListener(@NonNull Listener listener);

    MobilyticsDevice device();

    void removeListener(@NonNull Listener listener);
}
