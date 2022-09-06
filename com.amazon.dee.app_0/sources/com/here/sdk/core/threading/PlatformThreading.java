package com.here.sdk.core.threading;

import androidx.annotation.NonNull;
/* loaded from: classes3.dex */
public interface PlatformThreading {
    @NonNull
    TaskHandle postToMainThread(@NonNull Runnable runnable);

    @NonNull
    TaskHandle postToMainThread(@NonNull Runnable runnable, long j);

    @NonNull
    TaskHandle runOnMainThread(@NonNull Runnable runnable);
}
