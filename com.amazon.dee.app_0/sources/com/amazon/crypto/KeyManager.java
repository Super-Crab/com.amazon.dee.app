package com.amazon.crypto;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
/* loaded from: classes12.dex */
public interface KeyManager<T> {
    boolean contains(@Nullable String str) throws SecurityException;

    @NonNull
    /* renamed from: generate */
    T mo3298generate(@NonNull String str) throws IllegalArgumentException, SecurityException;

    void remove(@NonNull String str) throws IllegalArgumentException, SecurityException;

    @NonNull
    /* renamed from: retrieve */
    T mo3299retrieve(@NonNull String str) throws IllegalArgumentException, SecurityException;
}
