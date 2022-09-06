package com.amazon.alexa.accessory.internal.repositories;

import com.amazon.alexa.accessory.repositories.Producer;
/* loaded from: classes.dex */
public interface ResultCallable<T> {
    void call(Producer.Result<T> result);
}
