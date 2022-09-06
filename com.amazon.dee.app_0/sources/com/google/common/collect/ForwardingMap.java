package com.google.common.collect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Objects;
import com.google.common.collect.Maps;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
@GwtCompatible
/* loaded from: classes3.dex */
public abstract class ForwardingMap<K, V> extends ForwardingObject implements Map<K, V> {

    @Beta
    /* loaded from: classes3.dex */
    protected abstract class StandardEntrySet extends Maps.EntrySet<K, V> {
        public StandardEntrySet() {
        }

        @Override // com.google.common.collect.Maps.EntrySet
        Map<K, V> map() {
            return ForwardingMap.this;
        }
    }

    @Beta
    /* loaded from: classes3.dex */
    protected class StandardKeySet extends Maps.KeySet<K, V> {
        public StandardKeySet(ForwardingMap forwardingMap) {
            super(forwardingMap);
        }
    }

    @Beta
    /* loaded from: classes3.dex */
    protected class StandardValues extends Maps.Values<K, V> {
        public StandardValues(ForwardingMap forwardingMap) {
            super(forwardingMap);
        }
    }

    public void clear() {
        mo8280delegate().clear();
    }

    @Override // java.util.Map
    public boolean containsKey(@NullableDecl Object obj) {
        return mo8280delegate().containsKey(obj);
    }

    public boolean containsValue(@NullableDecl Object obj) {
        return mo8280delegate().containsValue(obj);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.common.collect.ForwardingObject
    /* renamed from: delegate  reason: collision with other method in class */
    public abstract Map<K, V> mo8280delegate();

    public Set<Map.Entry<K, V>> entrySet() {
        return mo8280delegate().entrySet();
    }

    @Override // java.util.Map
    public boolean equals(@NullableDecl Object obj) {
        return obj == this || mo8280delegate().equals(obj);
    }

    @Override // java.util.Map
    public V get(@NullableDecl Object obj) {
        return mo8280delegate().get(obj);
    }

    @Override // java.util.Map
    public int hashCode() {
        return mo8280delegate().hashCode();
    }

    @Override // java.util.Map
    public boolean isEmpty() {
        return mo8280delegate().isEmpty();
    }

    public Set<K> keySet() {
        return mo8280delegate().keySet();
    }

    @CanIgnoreReturnValue
    public V put(K k, V v) {
        return mo8280delegate().put(k, v);
    }

    public void putAll(Map<? extends K, ? extends V> map) {
        mo8280delegate().putAll(map);
    }

    @CanIgnoreReturnValue
    public V remove(Object obj) {
        return mo8280delegate().remove(obj);
    }

    @Override // java.util.Map
    public int size() {
        return mo8280delegate().size();
    }

    protected void standardClear() {
        Iterators.clear(entrySet().iterator());
    }

    @Beta
    protected boolean standardContainsKey(@NullableDecl Object obj) {
        return Maps.containsKeyImpl(this, obj);
    }

    protected boolean standardContainsValue(@NullableDecl Object obj) {
        return Maps.containsValueImpl(this, obj);
    }

    protected boolean standardEquals(@NullableDecl Object obj) {
        return Maps.equalsImpl(this, obj);
    }

    protected int standardHashCode() {
        return Sets.hashCodeImpl(entrySet());
    }

    protected boolean standardIsEmpty() {
        return !entrySet().iterator().hasNext();
    }

    protected void standardPutAll(Map<? extends K, ? extends V> map) {
        Maps.putAllImpl(this, map);
    }

    @Beta
    protected V standardRemove(@NullableDecl Object obj) {
        Iterator<Map.Entry<K, V>> it2 = entrySet().iterator();
        while (it2.hasNext()) {
            Map.Entry<K, V> next = it2.next();
            if (Objects.equal(next.getKey(), obj)) {
                V value = next.getValue();
                it2.remove();
                return value;
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String standardToString() {
        return Maps.toStringImpl(this);
    }

    /* renamed from: values */
    public Collection<V> mo7932values() {
        return mo8280delegate().values();
    }
}
