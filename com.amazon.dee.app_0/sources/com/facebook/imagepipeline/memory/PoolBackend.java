package com.facebook.imagepipeline.memory;

import javax.annotation.Nullable;
/* loaded from: classes2.dex */
interface PoolBackend<T> {
    @Nullable
    /* renamed from: get */
    T mo6899get(int size);

    int getSize(T item);

    @Nullable
    T pop();

    void put(T item);
}
