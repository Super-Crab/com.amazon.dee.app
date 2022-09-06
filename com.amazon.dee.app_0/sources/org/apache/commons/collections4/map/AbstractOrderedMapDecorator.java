package org.apache.commons.collections4.map;

import org.apache.commons.collections4.OrderedMap;
import org.apache.commons.collections4.OrderedMapIterator;
/* loaded from: classes4.dex */
public abstract class AbstractOrderedMapDecorator<K, V> extends AbstractMapDecorator<K, V> implements OrderedMap<K, V> {
    protected AbstractOrderedMapDecorator() {
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

    public AbstractOrderedMapDecorator(OrderedMap<K, V> orderedMap) {
        super(orderedMap);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.commons.collections4.map.AbstractMapDecorator
    /* renamed from: decorated  reason: collision with other method in class */
    public OrderedMap<K, V> mo12722decorated() {
        return (OrderedMap) super.mo12722decorated();
    }

    @Override // org.apache.commons.collections4.map.AbstractIterableMap, org.apache.commons.collections4.IterableGet
    /* renamed from: mapIterator  reason: collision with other method in class */
    public OrderedMapIterator<K, V> mo12767mapIterator() {
        return mo12722decorated().mo12767mapIterator();
    }
}
