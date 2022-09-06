package org.apache.commons.collections4.map;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
/* loaded from: classes4.dex */
public abstract class AbstractMapDecorator<K, V> extends AbstractIterableMap<K, V> {
    transient Map<K, V> map;

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractMapDecorator() {
    }

    @Override // java.util.Map, org.apache.commons.collections4.Put
    public void clear() {
        mo12722decorated().clear();
    }

    @Override // java.util.Map, org.apache.commons.collections4.Get
    public boolean containsKey(Object obj) {
        return mo12722decorated().containsKey(obj);
    }

    @Override // java.util.Map, org.apache.commons.collections4.Get
    public boolean containsValue(Object obj) {
        return mo12722decorated().containsValue(obj);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: decorated */
    public Map<K, V> mo12722decorated() {
        return this.map;
    }

    @Override // java.util.Map, org.apache.commons.collections4.Get
    public Set<Map.Entry<K, V>> entrySet() {
        return mo12722decorated().entrySet();
    }

    @Override // java.util.Map
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return mo12722decorated().equals(obj);
    }

    @Override // java.util.Map, org.apache.commons.collections4.Get
    /* renamed from: get */
    public V mo12663get(Object obj) {
        return mo12722decorated().get(obj);
    }

    @Override // java.util.Map
    public int hashCode() {
        return mo12722decorated().hashCode();
    }

    @Override // java.util.Map, org.apache.commons.collections4.Get
    public boolean isEmpty() {
        return mo12722decorated().isEmpty();
    }

    @Override // java.util.Map, org.apache.commons.collections4.Get
    public Set<K> keySet() {
        return mo12722decorated().keySet();
    }

    @Override // java.util.Map, org.apache.commons.collections4.Put
    public V put(K k, V v) {
        return mo12722decorated().put(k, v);
    }

    @Override // java.util.Map, org.apache.commons.collections4.Put
    public void putAll(Map<? extends K, ? extends V> map) {
        mo12722decorated().putAll(map);
    }

    @Override // java.util.Map, org.apache.commons.collections4.Get
    /* renamed from: remove */
    public V mo12668remove(Object obj) {
        return mo12722decorated().remove(obj);
    }

    @Override // java.util.Map, org.apache.commons.collections4.Get
    public int size() {
        return mo12722decorated().size();
    }

    public String toString() {
        return mo12722decorated().toString();
    }

    @Override // java.util.Map, org.apache.commons.collections4.Get
    /* renamed from: values */
    public Collection<V> mo12691values() {
        return mo12722decorated().values();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractMapDecorator(Map<K, V> map) {
        if (map != null) {
            this.map = map;
            return;
        }
        throw new NullPointerException("Map must not be null.");
    }
}
