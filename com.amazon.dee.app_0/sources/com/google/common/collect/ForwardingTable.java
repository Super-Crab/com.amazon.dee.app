package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.Table;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
@GwtCompatible
/* loaded from: classes3.dex */
public abstract class ForwardingTable<R, C, V> extends ForwardingObject implements Table<R, C, V> {
    @Override // com.google.common.collect.Table
    /* renamed from: cellSet */
    public Set<Table.Cell<R, C, V>> mo7854cellSet() {
        return mo8280delegate().mo7854cellSet();
    }

    @Override // com.google.common.collect.Table
    public void clear() {
        mo8280delegate().clear();
    }

    @Override // com.google.common.collect.Table
    /* renamed from: column */
    public Map<R, V> mo8030column(C c) {
        return mo8280delegate().mo8030column(c);
    }

    @Override // com.google.common.collect.Table
    /* renamed from: columnKeySet */
    public Set<C> mo7856columnKeySet() {
        return mo8280delegate().mo7856columnKeySet();
    }

    @Override // com.google.common.collect.Table
    /* renamed from: columnMap */
    public Map<C, Map<R, V>> mo8036columnMap() {
        return mo8280delegate().mo8036columnMap();
    }

    @Override // com.google.common.collect.Table
    public boolean contains(Object obj, Object obj2) {
        return mo8280delegate().contains(obj, obj2);
    }

    @Override // com.google.common.collect.Table
    public boolean containsColumn(Object obj) {
        return mo8280delegate().containsColumn(obj);
    }

    @Override // com.google.common.collect.Table
    public boolean containsRow(Object obj) {
        return mo8280delegate().containsRow(obj);
    }

    @Override // com.google.common.collect.Table
    public boolean containsValue(Object obj) {
        return mo8280delegate().containsValue(obj);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.common.collect.ForwardingObject
    /* renamed from: delegate */
    public abstract Table<R, C, V> mo8280delegate();

    @Override // com.google.common.collect.Table
    public boolean equals(Object obj) {
        return obj == this || mo8280delegate().equals(obj);
    }

    @Override // com.google.common.collect.Table
    public V get(Object obj, Object obj2) {
        return mo8280delegate().get(obj, obj2);
    }

    @Override // com.google.common.collect.Table
    public int hashCode() {
        return mo8280delegate().hashCode();
    }

    @Override // com.google.common.collect.Table
    public boolean isEmpty() {
        return mo8280delegate().isEmpty();
    }

    @Override // com.google.common.collect.Table
    @CanIgnoreReturnValue
    public V put(R r, C c, V v) {
        return mo8280delegate().put(r, c, v);
    }

    @Override // com.google.common.collect.Table
    public void putAll(Table<? extends R, ? extends C, ? extends V> table) {
        mo8280delegate().putAll(table);
    }

    @Override // com.google.common.collect.Table
    @CanIgnoreReturnValue
    public V remove(Object obj, Object obj2) {
        return mo8280delegate().remove(obj, obj2);
    }

    @Override // com.google.common.collect.Table
    /* renamed from: row */
    public Map<C, V> mo8094row(R r) {
        return mo8280delegate().mo8094row(r);
    }

    @Override // com.google.common.collect.Table
    /* renamed from: rowKeySet */
    public Set<R> mo8095rowKeySet() {
        return mo8280delegate().mo8095rowKeySet();
    }

    @Override // com.google.common.collect.Table
    /* renamed from: rowMap */
    public Map<R, Map<C, V>> mo8096rowMap() {
        return mo8280delegate().mo8096rowMap();
    }

    @Override // com.google.common.collect.Table
    public int size() {
        return mo8280delegate().size();
    }

    @Override // com.google.common.collect.Table
    /* renamed from: values */
    public Collection<V> mo7863values() {
        return mo8280delegate().mo7863values();
    }
}
