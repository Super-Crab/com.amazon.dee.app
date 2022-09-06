package org.apache.commons.collections4.map;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.apache.commons.collections4.MapIterator;
import org.apache.commons.collections4.ResettableIterator;
/* loaded from: classes4.dex */
public class EntrySetToMapIteratorAdapter<K, V> implements MapIterator<K, V>, ResettableIterator<K> {
    transient Map.Entry<K, V> entry;
    Set<Map.Entry<K, V>> entrySet;
    transient Iterator<Map.Entry<K, V>> iterator;

    public EntrySetToMapIteratorAdapter(Set<Map.Entry<K, V>> set) {
        this.entrySet = set;
        reset();
    }

    protected synchronized Map.Entry<K, V> current() {
        if (this.entry != null) {
        } else {
            throw new IllegalStateException();
        }
        return this.entry;
    }

    @Override // org.apache.commons.collections4.MapIterator
    /* renamed from: getKey */
    public K mo12681getKey() {
        return current().getKey();
    }

    @Override // org.apache.commons.collections4.MapIterator
    /* renamed from: getValue */
    public V mo12682getValue() {
        return current().getValue();
    }

    @Override // org.apache.commons.collections4.MapIterator, java.util.Iterator
    public boolean hasNext() {
        return this.iterator.hasNext();
    }

    @Override // org.apache.commons.collections4.MapIterator, java.util.Iterator
    /* renamed from: next */
    public K mo12683next() {
        this.entry = this.iterator.next();
        return mo12681getKey();
    }

    @Override // org.apache.commons.collections4.MapIterator, java.util.Iterator
    public void remove() {
        this.iterator.remove();
        this.entry = null;
    }

    public synchronized void reset() {
        this.iterator = this.entrySet.iterator();
    }

    @Override // org.apache.commons.collections4.MapIterator
    public V setValue(V v) {
        return current().setValue(v);
    }
}
