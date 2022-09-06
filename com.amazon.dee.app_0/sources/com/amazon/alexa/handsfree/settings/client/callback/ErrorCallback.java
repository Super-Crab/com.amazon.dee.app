package com.amazon.alexa.handsfree.settings.client.callback;

import java.lang.Throwable;
/* loaded from: classes8.dex */
public interface ErrorCallback<T extends Throwable> {
    void onError(T t);
}
