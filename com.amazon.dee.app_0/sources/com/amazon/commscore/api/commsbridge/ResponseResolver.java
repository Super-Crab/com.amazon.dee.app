package com.amazon.commscore.api.commsbridge;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
/* loaded from: classes12.dex */
public interface ResponseResolver {
    String getRequestId();

    void reject(@NonNull Throwable th);

    void resolve(@Nullable Object obj);
}
