package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import java.util.Collection;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
@GwtCompatible
/* loaded from: classes3.dex */
abstract class AbstractSortedKeySortedSetMultimap<K, V> extends AbstractSortedSetMultimap<K, V> {
    /* JADX INFO: Access modifiers changed from: package-private */
    public AbstractSortedKeySortedSetMultimap(SortedMap<K, Collection<V>> sortedMap) {
        super(sortedMap);
    }

    @Override // com.google.common.collect.AbstractMapBasedMultimap, com.google.common.collect.AbstractMultimap
    Set<K> createKeySet() {
        return createMaybeNavigableKeySet();
    }

    @Override // com.google.common.collect.AbstractSortedSetMultimap, com.google.common.collect.AbstractSetMultimap, com.google.common.collect.AbstractMultimap, com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
    /* renamed from: asMap  reason: collision with other method in class */
    public SortedMap<K, Collection<V>> mo8101asMap() {
        return (SortedMap) super.mo8101asMap();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.common.collect.AbstractMapBasedMultimap
    /* renamed from: backingMap  reason: collision with other method in class */
    public SortedMap<K, Collection<V>> mo7571backingMap() {
        return (SortedMap) super.mo7571backingMap();
    }

    @Override // com.google.common.collect.AbstractMultimap, com.google.common.collect.Multimap
    /* renamed from: keySet  reason: collision with other method in class */
    public SortedSet<K> mo8105keySet() {
        return (SortedSet) super.mo8105keySet();
    }
}
