package com.amazon.alexa.accessory.notificationpublisher.storage;

import androidx.annotation.Nullable;
/* loaded from: classes.dex */
public interface AsyncLocalStorageCallback {
    void onComplete(@Nullable Object obj);

    void onError(Throwable th);
}
