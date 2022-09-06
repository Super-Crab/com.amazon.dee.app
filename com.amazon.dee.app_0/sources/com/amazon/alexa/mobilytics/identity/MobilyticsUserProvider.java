package com.amazon.alexa.mobilytics.identity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
/* loaded from: classes9.dex */
public interface MobilyticsUserProvider {

    @FunctionalInterface
    /* loaded from: classes9.dex */
    public interface Listener {
        void onUserChanged(@Nullable MobilyticsUser mobilyticsUser);
    }

    void addListener(@NonNull Listener listener);

    void removeListener(@NonNull Listener listener);

    MobilyticsUser user();
}
