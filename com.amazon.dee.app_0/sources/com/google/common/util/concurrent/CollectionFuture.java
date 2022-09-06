package com.google.common.util.concurrent;

import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.util.concurrent.AggregateFuture;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
@GwtCompatible(emulated = true)
/* loaded from: classes3.dex */
abstract class CollectionFuture<V, C> extends AggregateFuture<V, C> {
    private List<Present<V>> values;

    /* loaded from: classes3.dex */
    static final class ListFuture<V> extends CollectionFuture<V, List<V>> {
        /* JADX INFO: Access modifiers changed from: package-private */
        public ListFuture(ImmutableCollection<? extends ListenableFuture<? extends V>> immutableCollection, boolean z) {
            super(immutableCollection, z);
            init();
        }

        @Override // com.google.common.util.concurrent.CollectionFuture
        /* renamed from: combine  reason: collision with other method in class */
        public List<V> mo8262combine(List<Present<V>> list) {
            ArrayList newArrayListWithCapacity = Lists.newArrayListWithCapacity(list.size());
            Iterator<Present<V>> it2 = list.iterator();
            while (it2.hasNext()) {
                Present<V> next = it2.next();
                newArrayListWithCapacity.add(next != null ? next.value : null);
            }
            return Collections.unmodifiableList(newArrayListWithCapacity);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public static final class Present<V> {
        V value;

        Present(V v) {
            this.value = v;
        }
    }

    CollectionFuture(ImmutableCollection<? extends ListenableFuture<? extends V>> immutableCollection, boolean z) {
        super(immutableCollection, z, true);
        List<Present<V>> newArrayListWithCapacity;
        if (immutableCollection.isEmpty()) {
            newArrayListWithCapacity = ImmutableList.of();
        } else {
            newArrayListWithCapacity = Lists.newArrayListWithCapacity(immutableCollection.size());
        }
        for (int i = 0; i < immutableCollection.size(); i++) {
            newArrayListWithCapacity.add(null);
        }
        this.values = newArrayListWithCapacity;
    }

    @Override // com.google.common.util.concurrent.AggregateFuture
    final void collectOneValue(int i, @NullableDecl V v) {
        List<Present<V>> list = this.values;
        if (list != null) {
            list.set(i, new Present<>(v));
        }
    }

    /* renamed from: combine */
    abstract C mo8262combine(List<Present<V>> list);

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.util.concurrent.AggregateFuture
    final void handleAllCompleted() {
        List<Present<V>> list = this.values;
        if (list != null) {
            set(mo8262combine(list));
        }
    }

    @Override // com.google.common.util.concurrent.AggregateFuture
    void releaseResources(AggregateFuture.ReleaseResourcesReason releaseResourcesReason) {
        super.releaseResources(releaseResourcesReason);
        this.values = null;
    }
}
