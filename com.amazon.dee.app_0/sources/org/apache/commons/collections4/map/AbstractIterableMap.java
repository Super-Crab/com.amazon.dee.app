package org.apache.commons.collections4.map;

import org.apache.commons.collections4.IterableMap;
import org.apache.commons.collections4.MapIterator;
/* loaded from: classes4.dex */
public abstract class AbstractIterableMap<K, V> implements IterableMap<K, V> {
    /* renamed from: mapIterator */
    public MapIterator<K, V> mo12767mapIterator() {
        return new EntrySetToMapIteratorAdapter(entrySet());
    }
}
