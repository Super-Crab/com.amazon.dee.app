package com.amazon.alexa.accessory.engagement;

import androidx.annotation.NonNull;
/* loaded from: classes.dex */
interface LongRepository {

    @FunctionalInterface
    /* loaded from: classes.dex */
    public interface Factory {
        LongRepository create(String str);
    }

    boolean contains(@NonNull String str);

    long get(@NonNull String str);

    void put(@NonNull String str, long j);
}
