package com.amazon.alexa.protocols.service.api;

import androidx.annotation.NonNull;
/* loaded from: classes9.dex */
public interface ComponentGetter {
    @NonNull
    <T> LazyComponent<T> get(@NonNull Class<T> cls);
}
