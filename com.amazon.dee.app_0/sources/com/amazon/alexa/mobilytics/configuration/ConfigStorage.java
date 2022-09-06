package com.amazon.alexa.mobilytics.configuration;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
/* loaded from: classes9.dex */
public interface ConfigStorage {
    void clear();

    @Nullable
    Config content();

    @Nullable
    String lastUpdatedTime();

    void storeContent(@NonNull Config config);

    void storeUpdatedTime(@NonNull String str);
}
