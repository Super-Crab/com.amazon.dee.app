package com.dee.app.cachemanager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.dee.app.function.BiCallback;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
/* loaded from: classes9.dex */
public interface TwoTierLruMemoryCache<T> {
    public static final int MEMORY_EVICTION_TIER_ONE = 0;
    public static final int MEMORY_EVICTION_TIER_TWO = 1;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes9.dex */
    public @interface EvictionTier {
    }

    void clear();

    void forEach(@NonNull BiCallback<String, T> biCallback);

    @Nullable
    T get(@NonNull String str);

    boolean put(@NonNull String str, @NonNull T t, int i, int i2);

    boolean remove(@NonNull String str);
}
