package io.reactivex.rxjava3.internal.util;

import io.reactivex.rxjava3.functions.Function;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
/* loaded from: classes3.dex */
public final class SorterFunction<T> implements Function<List<T>, List<T>> {
    final Comparator<? super T> comparator;

    public SorterFunction(Comparator<? super T> comparator) {
        this.comparator = comparator;
    }

    @Override // io.reactivex.rxjava3.functions.Function
    /* renamed from: apply */
    public /* bridge */ /* synthetic */ Object mo10358apply(Object t) throws Throwable {
        return apply((List) ((List) t));
    }

    public List<T> apply(List<T> t) {
        Collections.sort(t, this.comparator);
        return t;
    }
}
