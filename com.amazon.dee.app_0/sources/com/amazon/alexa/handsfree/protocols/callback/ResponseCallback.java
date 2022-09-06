package com.amazon.alexa.handsfree.protocols.callback;

import androidx.annotation.NonNull;
/* loaded from: classes8.dex */
public interface ResponseCallback {
    void onError(@NonNull CallbackErrorMetadata callbackErrorMetadata);

    void onSuccess();
}
