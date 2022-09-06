package io.reactivex.rxjava3.internal.util;

import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.functions.Supplier;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes3.dex */
public enum ArrayListSupplier implements Supplier<List<Object>>, Function<Object, List<Object>> {
    INSTANCE;

    public static <T, O> Function<O, List<T>> asFunction() {
        return INSTANCE;
    }

    public static <T> Supplier<List<T>> asSupplier() {
        return INSTANCE;
    }

    @Override // io.reactivex.rxjava3.functions.Function
    /* renamed from: apply  reason: collision with other method in class */
    public List<Object> mo10358apply(Object o) {
        return new ArrayList();
    }

    @Override // io.reactivex.rxjava3.functions.Supplier
    /* renamed from: get  reason: collision with other method in class */
    public List<Object> mo10365get() {
        return new ArrayList();
    }
}
