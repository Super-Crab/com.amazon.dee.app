package io.reactivex.rxjava3.internal.operators.maybe;

import io.reactivex.rxjava3.core.MaybeSource;
import io.reactivex.rxjava3.functions.Function;
import org.reactivestreams.Publisher;
/* loaded from: classes3.dex */
public enum MaybeToPublisher implements Function<MaybeSource<Object>, Publisher<Object>> {
    INSTANCE;

    public static <T> Function<MaybeSource<T>, Publisher<T>> instance() {
        return INSTANCE;
    }

    @Override // io.reactivex.rxjava3.functions.Function
    /* renamed from: apply  reason: avoid collision after fix types in other method */
    public Publisher<Object> mo10358apply(MaybeSource<Object> t) {
        return new MaybeToFlowable(t);
    }
}
