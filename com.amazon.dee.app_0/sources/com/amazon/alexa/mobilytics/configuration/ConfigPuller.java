package com.amazon.alexa.mobilytics.configuration;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.mobilytics.auth.CredentialsProvider;
import com.amazonaws.regions.Regions;
/* loaded from: classes9.dex */
public interface ConfigPuller {
    void initialize(@NonNull CredentialsProvider credentialsProvider, @NonNull Regions regions, int i);

    @Nullable
    String read(@Nullable String str);

    @NonNull
    String updatedTime();
}
