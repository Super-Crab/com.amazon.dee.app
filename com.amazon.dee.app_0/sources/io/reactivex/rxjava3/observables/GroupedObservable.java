package io.reactivex.rxjava3.observables;

import io.reactivex.rxjava3.annotations.Nullable;
import io.reactivex.rxjava3.core.Observable;
/* loaded from: classes3.dex */
public abstract class GroupedObservable<K, T> extends Observable<T> {
    final K key;

    /* JADX INFO: Access modifiers changed from: protected */
    public GroupedObservable(@Nullable K key) {
        this.key = key;
    }

    @Nullable
    public K getKey() {
        return this.key;
    }
}
