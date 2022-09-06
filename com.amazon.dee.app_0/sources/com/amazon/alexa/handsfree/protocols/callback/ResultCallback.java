package com.amazon.alexa.handsfree.protocols.callback;

import androidx.annotation.NonNull;
/* loaded from: classes8.dex */
public interface ResultCallback<T> {
    void onError(@NonNull CallbackErrorMetadata callbackErrorMetadata);

    void onResult(T t);
}
