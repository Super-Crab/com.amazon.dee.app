package org.apache.commons.collections4;
/* loaded from: classes4.dex */
public interface OrderedMap<K, V> extends IterableMap<K, V> {
    /* renamed from: firstKey */
    K mo12662firstKey();

    /* renamed from: lastKey */
    K mo12666lastKey();

    @Override // org.apache.commons.collections4.IterableGet
    /* renamed from: mapIterator */
    OrderedMapIterator<K, V> mo12767mapIterator();

    K nextKey(K k);

    K previousKey(K k);
}
