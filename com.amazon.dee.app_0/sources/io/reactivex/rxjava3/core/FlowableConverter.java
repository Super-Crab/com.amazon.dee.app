package io.reactivex.rxjava3.core;

import io.reactivex.rxjava3.annotations.NonNull;
@FunctionalInterface
/* loaded from: classes3.dex */
public interface FlowableConverter<T, R> {
    R apply(@NonNull Flowable<T> upstream);
}
