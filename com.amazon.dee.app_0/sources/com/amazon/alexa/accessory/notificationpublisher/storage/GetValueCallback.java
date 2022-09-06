package com.amazon.alexa.accessory.notificationpublisher.storage;
/* loaded from: classes.dex */
public interface GetValueCallback {
    void onError(Throwable th);

    void onValueNotPresent();

    void onValuePresent(Object obj);
}
