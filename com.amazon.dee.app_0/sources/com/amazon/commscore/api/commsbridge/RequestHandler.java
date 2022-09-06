package com.amazon.commscore.api.commsbridge;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
@FunctionalInterface
/* loaded from: classes12.dex */
public interface RequestHandler<T> {
    void handleRequest(@Nullable T t, @NonNull ResponseResolver responseResolver);
}
