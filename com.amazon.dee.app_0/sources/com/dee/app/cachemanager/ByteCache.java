package com.dee.app.cachemanager;

import androidx.annotation.NonNull;
import com.dee.app.function.Callback;
import com.google.common.base.Optional;
/* loaded from: classes9.dex */
public interface ByteCache {
    void clear() throws CacheException;

    void forEach(@NonNull Callback<byte[]> callback) throws CacheException;

    Optional<byte[]> get(@NonNull String str) throws CacheException;

    void put(@NonNull String str, @NonNull byte[] bArr) throws CacheException;

    void remove(@NonNull String str) throws CacheException;
}
