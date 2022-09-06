package org.apache.commons.collections4.splitmap;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import org.apache.commons.collections4.IterableGet;
import org.apache.commons.collections4.MapIterator;
import org.apache.commons.collections4.map.EntrySetToMapIteratorAdapter;
/* loaded from: classes4.dex */
public class AbstractIterableGetMapDecorator<K, V> implements IterableGet<K, V> {
    transient Map<K, V> map;

    public AbstractIterableGetMapDecorator(Map<K, V> map) {
        if (map != null) {
            this.map = map;
            return;
        }
        throw new NullPointerException("Map must not be null.");
    }

    @Override // org.apache.commons.collections4.Get
    public boolean containsKey(Object obj) {
        return decorated().containsKey(obj);
    }

    @Override // org.apache.commons.collections4.Get
    public boolean containsValue(Object obj) {
        return decorated().containsValue(obj);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Map<K, V> decorated() {
        return this.map;
    }

    @Override // org.apache.commons.collections4.Get
    public Set<Map.Entry<K, V>> entrySet() {
        return decorated().entrySet();
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return decorated().equals(obj);
    }

    @Override // org.apache.commons.collections4.Get
    /* renamed from: get */
    public V mo12663get(Object obj) {
        return decorated().get(obj);
    }

    public int hashCode() {
        return decorated().hashCode();
    }

    @Override // org.apache.commons.collections4.Get
    public boolean isEmpty() {
        return decorated().isEmpty();
    }

    @Override // org.apache.commons.collections4.Get
    public Set<K> keySet() {
        return decorated().keySet();
    }

    @Override // org.apache.commons.collections4.IterableGet
    /* renamed from: mapIterator */
    public MapIterator<K, V> mo12767mapIterator() {
        return new EntrySetToMapIteratorAdapter(entrySet());
    }

    @Override // org.apache.commons.collections4.Get
    /* renamed from: remove */
    public V mo12668remove(Object obj) {
        return decorated().remove(obj);
    }

    @Override // org.apache.commons.collections4.Get
    public int size() {
        return decorated().size();
    }

    public String toString() {
        return decorated().toString();
    }

    @Override // org.apache.commons.collections4.Get
    /* renamed from: values */
    public Collection<V> mo12691values() {
        return decorated().values();
    }

    protected AbstractIterableGetMapDecorator() {
    }
}
