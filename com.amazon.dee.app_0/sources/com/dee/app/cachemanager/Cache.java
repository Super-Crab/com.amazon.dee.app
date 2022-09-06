package com.dee.app.cachemanager;

import androidx.annotation.NonNull;
import com.google.common.base.Optional;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import rx.Observable;
/* loaded from: classes9.dex */
public interface Cache<T> {
    public static final int DEFAULT_PRIORITY = 0;
    public static final int HIGH_PRIORITY = 1;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes9.dex */
    public @interface Priority {
    }

    Observable<Void> clear(@NonNull CacheMetadata cacheMetadata);

    Observable<Void> clear(@NonNull String str);

    Observable<Optional<T>> get(@NonNull String str, @NonNull CacheMetadata cacheMetadata);

    Observable<CacheEvent> getCacheEvents();

    Observable<Void> put(@NonNull String str, @NonNull T t, @NonNull CacheMetadata cacheMetadata);

    Observable<Void> remove(@NonNull String str, @NonNull CacheMetadata cacheMetadata);
}
