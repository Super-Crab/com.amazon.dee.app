package com.here.sdk.core;

import androidx.annotation.Nullable;
@FunctionalInterface
/* loaded from: classes3.dex */
public interface AuthenticationCallback {
    void onTokenReceived(@Nullable AuthenticationError authenticationError, @Nullable AuthenticationData authenticationData);
}
