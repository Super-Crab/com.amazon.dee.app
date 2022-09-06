package org.apache.commons.collections4;

import java.util.Iterator;
/* loaded from: classes4.dex */
public interface MapIterator<K, V> extends Iterator<K> {
    /* renamed from: getKey */
    K mo12681getKey();

    /* renamed from: getValue */
    V mo12682getValue();

    @Override // java.util.Iterator
    boolean hasNext();

    @Override // java.util.Iterator
    /* renamed from: next */
    K mo12683next();

    @Override // java.util.Iterator
    void remove();

    V setValue(V v);
}
