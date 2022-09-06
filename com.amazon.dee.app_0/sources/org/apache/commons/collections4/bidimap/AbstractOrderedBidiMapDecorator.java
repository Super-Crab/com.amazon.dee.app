package org.apache.commons.collections4.bidimap;

import org.apache.commons.collections4.OrderedBidiMap;
import org.apache.commons.collections4.OrderedMapIterator;
/* loaded from: classes4.dex */
public abstract class AbstractOrderedBidiMapDecorator<K, V> extends AbstractBidiMapDecorator<K, V> implements OrderedBidiMap<K, V> {
    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractOrderedBidiMapDecorator(OrderedBidiMap<K, V> orderedBidiMap) {
        super(orderedBidiMap);
    }

    @Override // org.apache.commons.collections4.OrderedMap
    /* renamed from: firstKey */
    public K mo12662firstKey() {
        return mo12722decorated().mo12662firstKey();
    }

    @Override // org.apache.commons.collections4.OrderedMap
    /* renamed from: lastKey */
    public K mo12666lastKey() {
        return mo12722decorated().mo12666lastKey();
    }

    @Override // org.apache.commons.collections4.OrderedMap
    public K nextKey(K k) {
        return mo12722decorated().nextKey(k);
    }

    @Override // org.apache.commons.collections4.OrderedMap
    public K previousKey(K k) {
        return mo12722decorated().previousKey(k);
    }

    @Override // org.apache.commons.collections4.bidimap.AbstractBidiMapDecorator, org.apache.commons.collections4.BidiMap
    /* renamed from: inverseBidiMap  reason: collision with other method in class */
    public OrderedBidiMap<V, K> mo12689inverseBidiMap() {
        return mo12722decorated().mo12689inverseBidiMap();
    }

    @Override // org.apache.commons.collections4.bidimap.AbstractBidiMapDecorator, org.apache.commons.collections4.map.AbstractIterableMap, org.apache.commons.collections4.IterableGet
    /* renamed from: mapIterator  reason: collision with other method in class */
    public OrderedMapIterator<K, V> mo12767mapIterator() {
        return mo12722decorated().mo12767mapIterator();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.commons.collections4.bidimap.AbstractBidiMapDecorator, org.apache.commons.collections4.map.AbstractMapDecorator
    /* renamed from: decorated */
    public OrderedBidiMap<K, V> mo12722decorated() {
        return (OrderedBidiMap) super.mo12722decorated();
    }
}
