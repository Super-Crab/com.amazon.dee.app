package org.apache.commons.collections4.keyvalue;

import java.util.Map;
import org.apache.commons.collections4.KeyValue;
/* loaded from: classes4.dex */
public abstract class AbstractMapEntryDecorator<K, V> implements Map.Entry<K, V>, KeyValue<K, V> {
    private final Map.Entry<K, V> entry;

    public AbstractMapEntryDecorator(Map.Entry<K, V> entry) {
        if (entry != null) {
            this.entry = entry;
            return;
        }
        throw new NullPointerException("Map Entry must not be null.");
    }

    @Override // java.util.Map.Entry
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return this.entry.equals(obj);
    }

    @Override // java.util.Map.Entry, org.apache.commons.collections4.KeyValue
    /* renamed from: getKey */
    public K mo12677getKey() {
        return this.entry.getKey();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Map.Entry<K, V> getMapEntry() {
        return this.entry;
    }

    @Override // java.util.Map.Entry, org.apache.commons.collections4.KeyValue
    /* renamed from: getValue */
    public V mo12678getValue() {
        return this.entry.getValue();
    }

    @Override // java.util.Map.Entry
    public int hashCode() {
        return this.entry.hashCode();
    }

    @Override // java.util.Map.Entry
    public V setValue(V v) {
        return this.entry.setValue(v);
    }

    public String toString() {
        return this.entry.toString();
    }
}
