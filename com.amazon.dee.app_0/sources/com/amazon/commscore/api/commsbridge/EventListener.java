package com.amazon.commscore.api.commsbridge;

import androidx.annotation.Nullable;
@FunctionalInterface
/* loaded from: classes12.dex */
public interface EventListener<T> {
    void onEvent(@Nullable T t);
}
