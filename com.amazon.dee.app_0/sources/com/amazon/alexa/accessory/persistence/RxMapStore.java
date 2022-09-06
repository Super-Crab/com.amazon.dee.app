package com.amazon.alexa.accessory.persistence;

import com.amazon.alexa.accessory.UserSupplier;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import java.util.Map;
import java.util.Set;
/* loaded from: classes.dex */
public interface RxMapStore<K, V> {
    void cleanCache(UserSupplier userSupplier);

    Completable clear();

    Completable delete(K k, V v);

    Single<Set<V>> get(K k);

    Single<V> put(K k, V v);

    Observable<Map<String, Set<V>>> queryValues();
}
