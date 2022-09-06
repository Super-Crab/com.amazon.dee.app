package com.amazon.identity.auth.device.api;
/* loaded from: classes12.dex */
public interface Listener<T, U> {
    void onError(U u);

    void onSuccess(T t);
}
