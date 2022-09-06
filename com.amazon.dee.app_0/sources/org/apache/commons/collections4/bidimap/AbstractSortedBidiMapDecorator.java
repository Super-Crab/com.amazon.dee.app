package org.apache.commons.collections4.bidimap;

import java.util.Comparator;
import java.util.SortedMap;
import org.apache.commons.collections4.SortedBidiMap;
/* loaded from: classes4.dex */
public abstract class AbstractSortedBidiMapDecorator<K, V> extends AbstractOrderedBidiMapDecorator<K, V> implements SortedBidiMap<K, V> {
    public AbstractSortedBidiMapDecorator(SortedBidiMap<K, V> sortedBidiMap) {
        super(sortedBidiMap);
    }

    @Override // java.util.SortedMap
    public Comparator<? super K> comparator() {
        return mo12722decorated().comparator();
    }

    @Override // java.util.SortedMap
    public SortedMap<K, V> headMap(K k) {
        return mo12722decorated().headMap(k);
    }

    @Override // java.util.SortedMap
    public SortedMap<K, V> subMap(K k, K k2) {
        return mo12722decorated().subMap(k, k2);
    }

    @Override // java.util.SortedMap
    public SortedMap<K, V> tailMap(K k) {
        return mo12722decorated().tailMap(k);
    }

    @Override // org.apache.commons.collections4.SortedBidiMap
    public Comparator<? super V> valueComparator() {
        return mo12722decorated().valueComparator();
    }

    @Override // org.apache.commons.collections4.bidimap.AbstractOrderedBidiMapDecorator, org.apache.commons.collections4.bidimap.AbstractBidiMapDecorator, org.apache.commons.collections4.BidiMap
    /* renamed from: inverseBidiMap */
    public SortedBidiMap<V, K> mo12689inverseBidiMap() {
        return mo12722decorated().mo12689inverseBidiMap();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.commons.collections4.bidimap.AbstractOrderedBidiMapDecorator, org.apache.commons.collections4.bidimap.AbstractBidiMapDecorator, org.apache.commons.collections4.map.AbstractMapDecorator
    /* renamed from: decorated  reason: collision with other method in class */
    public SortedBidiMap<K, V> mo12722decorated() {
        return (SortedBidiMap) super.mo12722decorated();
    }
}
